package mf.org.apache.xerces.xinclude;

import cz.msebera.android.httpclient.protocol.HTTP;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map.Entry;
import mf.org.apache.xerces.impl.XMLEntityManager;
import mf.org.apache.xerces.impl.XMLErrorReporter;
import mf.org.apache.xerces.impl.io.ASCIIReader;
import mf.org.apache.xerces.impl.io.Latin1Reader;
import mf.org.apache.xerces.impl.io.UTF16Reader;
import mf.org.apache.xerces.impl.io.UTF8Reader;
import mf.org.apache.xerces.util.EncodingMap;
import mf.org.apache.xerces.util.HTTPInputSource;
import mf.org.apache.xerces.util.XMLChar;
import mf.org.apache.xerces.xni.XMLString;
import mf.org.apache.xerces.xni.parser.XMLInputSource;

public class XIncludeTextReader {
    private XMLErrorReporter fErrorReporter;
    private final XIncludeHandler fHandler;
    private Reader fReader;
    private XMLInputSource fSource;
    private XMLString fTempString = new XMLString();

    public XIncludeTextReader(XMLInputSource source, XIncludeHandler handler, int bufferSize) throws IOException {
        this.fHandler = handler;
        this.fSource = source;
        this.fTempString = new XMLString(new char[(bufferSize + 1)], 0, 0);
    }

    public void setErrorReporter(XMLErrorReporter errorReporter) {
        this.fErrorReporter = errorReporter;
    }

    protected Reader getReader(XMLInputSource source) throws IOException {
        if (source.getCharacterStream() != null) {
            return source.getCharacterStream();
        }
        InputStream stream;
        String encoding = source.getEncoding();
        if (encoding == null) {
            encoding = "UTF-8";
        }
        if (source.getByteStream() != null) {
            stream = source.getByteStream();
            if (!(stream instanceof BufferedInputStream)) {
                stream = new BufferedInputStream(stream, this.fTempString.ch.length);
            }
        } else {
            String contentType;
            URLConnection urlCon = new URL(XMLEntityManager.expandSystemId(source.getSystemId(), source.getBaseSystemId(), false)).openConnection();
            if ((urlCon instanceof HttpURLConnection) && (source instanceof HTTPInputSource)) {
                HttpURLConnection urlConnection = (HttpURLConnection) urlCon;
                HTTPInputSource httpInputSource = (HTTPInputSource) source;
                Iterator propIter = httpInputSource.getHTTPRequestProperties();
                while (propIter.hasNext()) {
                    Entry entry = (Entry) propIter.next();
                    urlConnection.setRequestProperty((String) entry.getKey(), (String) entry.getValue());
                }
                boolean followRedirects = httpInputSource.getFollowHTTPRedirects();
                if (!followRedirects) {
                    urlConnection.setInstanceFollowRedirects(followRedirects);
                }
            }
            InputStream bufferedInputStream = new BufferedInputStream(urlCon.getInputStream());
            String rawContentType = urlCon.getContentType();
            int index = rawContentType != null ? rawContentType.indexOf(59) : -1;
            String charset = null;
            if (index != -1) {
                contentType = rawContentType.substring(0, index).trim();
                charset = rawContentType.substring(index + 1).trim();
                if (charset.startsWith("charset=")) {
                    charset = charset.substring(8).trim();
                    if ((charset.charAt(0) == '\"' && charset.charAt(charset.length() - 1) == '\"') || (charset.charAt(0) == '\'' && charset.charAt(charset.length() - 1) == '\'')) {
                        charset = charset.substring(1, charset.length() - 1);
                    }
                } else {
                    charset = null;
                }
            } else {
                contentType = rawContentType.trim();
            }
            String detectedEncoding = null;
            if (contentType.equals("text/xml")) {
                if (charset != null) {
                    detectedEncoding = charset;
                } else {
                    detectedEncoding = "US-ASCII";
                }
            } else if (contentType.equals("application/xml")) {
                if (charset != null) {
                    detectedEncoding = charset;
                } else {
                    detectedEncoding = getEncodingName(bufferedInputStream);
                }
            } else if (contentType.endsWith("+xml")) {
                detectedEncoding = getEncodingName(bufferedInputStream);
            }
            if (detectedEncoding != null) {
                encoding = detectedEncoding;
            }
        }
        encoding = consumeBOM(stream, encoding.toUpperCase(Locale.ENGLISH));
        if (encoding.equals("UTF-8")) {
            return createUTF8Reader(stream);
        }
        if (encoding.equals("UTF-16BE")) {
            return createUTF16Reader(stream, true);
        }
        if (encoding.equals("UTF-16LE")) {
            return createUTF16Reader(stream, false);
        }
        String javaEncoding = EncodingMap.getIANA2JavaMapping(encoding);
        if (javaEncoding == null) {
            throw new IOException(this.fErrorReporter.getMessageFormatter("http://www.w3.org/TR/1998/REC-xml-19980210").formatMessage(this.fErrorReporter.getLocale(), "EncodingDeclInvalid", new Object[]{encoding}));
        } else if (javaEncoding.equals(HTTP.ASCII)) {
            return createASCIIReader(stream);
        } else {
            if (javaEncoding.equals("ISO8859_1")) {
                return createLatin1Reader(stream);
            }
            return new InputStreamReader(stream, javaEncoding);
        }
    }

    private Reader createUTF8Reader(InputStream stream) {
        return new UTF8Reader(stream, this.fTempString.ch.length, this.fErrorReporter.getMessageFormatter("http://www.w3.org/TR/1998/REC-xml-19980210"), this.fErrorReporter.getLocale());
    }

    private Reader createUTF16Reader(InputStream stream, boolean isBigEndian) {
        return new UTF16Reader(stream, this.fTempString.ch.length << 1, isBigEndian, this.fErrorReporter.getMessageFormatter("http://www.w3.org/TR/1998/REC-xml-19980210"), this.fErrorReporter.getLocale());
    }

    private Reader createASCIIReader(InputStream stream) {
        return new ASCIIReader(stream, this.fTempString.ch.length, this.fErrorReporter.getMessageFormatter("http://www.w3.org/TR/1998/REC-xml-19980210"), this.fErrorReporter.getLocale());
    }

    private Reader createLatin1Reader(InputStream stream) {
        return new Latin1Reader(stream, this.fTempString.ch.length);
    }

    protected String getEncodingName(InputStream stream) throws IOException {
        byte[] b4 = new byte[4];
        stream.mark(4);
        int count = stream.read(b4, 0, 4);
        stream.reset();
        if (count == 4) {
            return getEncodingName(b4);
        }
        return null;
    }

    protected String consumeBOM(InputStream stream, String encoding) throws IOException {
        byte[] b = new byte[3];
        stream.mark(3);
        int b1;
        if (encoding.equals("UTF-8")) {
            if (stream.read(b, 0, 3) == 3) {
                b1 = b[1] & 255;
                int b2 = b[2] & 255;
                if ((b[0] & 255) == 239 && b1 == 187 && b2 == 191) {
                    return encoding;
                }
                stream.reset();
                return encoding;
            }
            stream.reset();
            return encoding;
        } else if (!encoding.startsWith("UTF-16")) {
            return encoding;
        } else {
            if (stream.read(b, 0, 2) == 2) {
                int b0 = b[0] & 255;
                b1 = b[1] & 255;
                if (b0 == 254 && b1 == 255) {
                    return "UTF-16BE";
                }
                if (b0 == 255 && b1 == 254) {
                    return "UTF-16LE";
                }
            }
            stream.reset();
            return encoding;
        }
    }

    protected String getEncodingName(byte[] b4) {
        int b0 = b4[0] & 255;
        int b1 = b4[1] & 255;
        if (b0 == 254 && b1 == 255) {
            return "UTF-16BE";
        }
        if (b0 == 255 && b1 == 254) {
            return "UTF-16LE";
        }
        int b2 = b4[2] & 255;
        if (b0 == 239 && b1 == 187 && b2 == 191) {
            return "UTF-8";
        }
        int b3 = b4[3] & 255;
        if (b0 == 0 && b1 == 0 && b2 == 0 && b3 == 60) {
            return "ISO-10646-UCS-4";
        }
        if (b0 == 60 && b1 == 0 && b2 == 0 && b3 == 0) {
            return "ISO-10646-UCS-4";
        }
        if (b0 == 0 && b1 == 0 && b2 == 60 && b3 == 0) {
            return "ISO-10646-UCS-4";
        }
        if (b0 == 0 && b1 == 60 && b2 == 0 && b3 == 0) {
            return "ISO-10646-UCS-4";
        }
        if (b0 == 0 && b1 == 60 && b2 == 0 && b3 == 63) {
            return "UTF-16BE";
        }
        if (b0 == 60 && b1 == 0 && b2 == 63 && b3 == 0) {
            return "UTF-16LE";
        }
        if (b0 == 76 && b1 == 111 && b2 == 167 && b3 == 148) {
            return "CP037";
        }
        return null;
    }

    public void parse() throws IOException {
        this.fReader = getReader(this.fSource);
        this.fSource = null;
        int readSize = this.fReader.read(this.fTempString.ch, 0, this.fTempString.ch.length - 1);
        this.fHandler.fHasIncludeReportedContent = true;
        while (readSize != -1) {
            int i = 0;
            int readSize2 = readSize;
            while (i < readSize2) {
                char ch = this.fTempString.ch[i];
                if (!isValid(ch)) {
                    if (XMLChar.isHighSurrogate(ch)) {
                        int ch2;
                        i++;
                        if (i < readSize2) {
                            ch2 = this.fTempString.ch[i];
                            readSize = readSize2;
                        } else {
                            ch2 = this.fReader.read();
                            if (ch2 != -1) {
                                readSize = readSize2 + 1;
                                this.fTempString.ch[readSize2] = (char) ch2;
                            } else {
                                readSize = readSize2;
                            }
                        }
                        if (XMLChar.isLowSurrogate(ch2)) {
                            if (!isValid(XMLChar.supplemental(ch, (char) ch2))) {
                                this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "InvalidCharInContent", new Object[]{Integer.toString(XMLChar.supplemental(ch, (char) ch2), 16)}, (short) 2);
                            }
                        } else {
                            this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "InvalidCharInContent", new Object[]{Integer.toString(ch2, 16)}, (short) 2);
                        }
                        i++;
                        readSize2 = readSize;
                    } else {
                        this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "InvalidCharInContent", new Object[]{Integer.toString(ch, 16)}, (short) 2);
                    }
                }
                readSize = readSize2;
                i++;
                readSize2 = readSize;
            }
            if (this.fHandler != null && readSize2 > 0) {
                this.fTempString.offset = 0;
                this.fTempString.length = readSize2;
                this.fHandler.characters(this.fTempString, this.fHandler.modifyAugmentations(null, true));
            }
            readSize = this.fReader.read(this.fTempString.ch, 0, this.fTempString.ch.length - 1);
        }
    }

    public void setInputSource(XMLInputSource source) {
        this.fSource = source;
    }

    public void close() throws IOException {
        if (this.fReader != null) {
            this.fReader.close();
            this.fReader = null;
        }
    }

    protected boolean isValid(int ch) {
        return XMLChar.isValid(ch);
    }

    protected void setBufferSize(int bufferSize) {
        bufferSize++;
        if (this.fTempString.ch.length != bufferSize) {
            this.fTempString.ch = new char[bufferSize];
        }
    }
}
