package mf.org.apache.xerces.util;

import java.io.InputStream;
import java.io.Reader;
import mf.org.apache.xerces.xni.parser.XMLInputSource;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

public final class SAXInputSource extends XMLInputSource {
    private InputSource fInputSource;
    private XMLReader fXMLReader;

    public SAXInputSource() {
        this(null);
    }

    public SAXInputSource(InputSource inputSource) {
        this(null, inputSource);
    }

    public SAXInputSource(XMLReader reader, InputSource inputSource) {
        String systemId;
        String publicId = inputSource != null ? inputSource.getPublicId() : null;
        if (inputSource != null) {
            systemId = inputSource.getSystemId();
        } else {
            systemId = null;
        }
        super(publicId, systemId, null);
        if (inputSource != null) {
            setByteStream(inputSource.getByteStream());
            setCharacterStream(inputSource.getCharacterStream());
            setEncoding(inputSource.getEncoding());
        }
        this.fInputSource = inputSource;
        this.fXMLReader = reader;
    }

    public void setXMLReader(XMLReader reader) {
        this.fXMLReader = reader;
    }

    public XMLReader getXMLReader() {
        return this.fXMLReader;
    }

    public void setInputSource(InputSource inputSource) {
        if (inputSource != null) {
            setPublicId(inputSource.getPublicId());
            setSystemId(inputSource.getSystemId());
            setByteStream(inputSource.getByteStream());
            setCharacterStream(inputSource.getCharacterStream());
            setEncoding(inputSource.getEncoding());
        } else {
            setPublicId(null);
            setSystemId(null);
            setByteStream(null);
            setCharacterStream(null);
            setEncoding(null);
        }
        this.fInputSource = inputSource;
    }

    public InputSource getInputSource() {
        return this.fInputSource;
    }

    public void setPublicId(String publicId) {
        super.setPublicId(publicId);
        if (this.fInputSource == null) {
            this.fInputSource = new InputSource();
        }
        this.fInputSource.setPublicId(publicId);
    }

    public void setSystemId(String systemId) {
        super.setSystemId(systemId);
        if (this.fInputSource == null) {
            this.fInputSource = new InputSource();
        }
        this.fInputSource.setSystemId(systemId);
    }

    public void setByteStream(InputStream byteStream) {
        super.setByteStream(byteStream);
        if (this.fInputSource == null) {
            this.fInputSource = new InputSource();
        }
        this.fInputSource.setByteStream(byteStream);
    }

    public void setCharacterStream(Reader charStream) {
        super.setCharacterStream(charStream);
        if (this.fInputSource == null) {
            this.fInputSource = new InputSource();
        }
        this.fInputSource.setCharacterStream(charStream);
    }

    public void setEncoding(String encoding) {
        super.setEncoding(encoding);
        if (this.fInputSource == null) {
            this.fInputSource = new InputSource();
        }
        this.fInputSource.setEncoding(encoding);
    }
}
