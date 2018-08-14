package mf.org.apache.xml.serialize;

import com.facebook.ads.AudienceNetworkActivity;
import cz.msebera.android.httpclient.protocol.HTTP;
import java.io.UnsupportedEncodingException;
import mf.org.w3c.dom.Document;
import mf.org.w3c.dom.DocumentType;
import mf.org.w3c.dom.Node;
import mf.org.w3c.dom.html.HTMLDocument;

public class OutputFormat {
    private boolean _allowJavaNames;
    private String[] _cdataElements;
    private String _doctypePublic;
    private String _doctypeSystem;
    private String _encoding;
    private EncodingInfo _encodingInfo;
    private int _indent;
    private String _lineSeparator;
    private int _lineWidth;
    private String _mediaType;
    private String _method;
    private String[] _nonEscapingElements;
    private boolean _omitComments;
    private boolean _omitDoctype;
    private boolean _omitXmlDeclaration;
    private boolean _preserve;
    private boolean _preserveEmptyAttributes;
    private boolean _standalone;
    private String _version;

    public static class DTD {
        public static final String HTMLPublicId = "-//W3C//DTD HTML 4.01//EN";
        public static final String HTMLSystemId = "http://www.w3.org/TR/html4/strict.dtd";
        public static final String XHTMLPublicId = "-//W3C//DTD XHTML 1.0 Strict//EN";
        public static final String XHTMLSystemId = "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd";
    }

    public static class Defaults {
        public static final String Encoding = "UTF-8";
        public static final int Indent = 4;
        public static final int LineWidth = 72;
    }

    public OutputFormat() {
        this._indent = 0;
        this._encoding = "UTF-8";
        this._encodingInfo = null;
        this._allowJavaNames = false;
        this._omitXmlDeclaration = false;
        this._omitDoctype = false;
        this._omitComments = false;
        this._standalone = false;
        this._lineSeparator = "\n";
        this._lineWidth = 72;
        this._preserve = false;
        this._preserveEmptyAttributes = false;
    }

    public OutputFormat(String method, String encoding, boolean indenting) {
        this._indent = 0;
        this._encoding = "UTF-8";
        this._encodingInfo = null;
        this._allowJavaNames = false;
        this._omitXmlDeclaration = false;
        this._omitDoctype = false;
        this._omitComments = false;
        this._standalone = false;
        this._lineSeparator = "\n";
        this._lineWidth = 72;
        this._preserve = false;
        this._preserveEmptyAttributes = false;
        setMethod(method);
        setEncoding(encoding);
        setIndenting(indenting);
    }

    public OutputFormat(Document doc) {
        this._indent = 0;
        this._encoding = "UTF-8";
        this._encodingInfo = null;
        this._allowJavaNames = false;
        this._omitXmlDeclaration = false;
        this._omitDoctype = false;
        this._omitComments = false;
        this._standalone = false;
        this._lineSeparator = "\n";
        this._lineWidth = 72;
        this._preserve = false;
        this._preserveEmptyAttributes = false;
        setMethod(whichMethod(doc));
        setDoctype(whichDoctypePublic(doc), whichDoctypeSystem(doc));
        setMediaType(whichMediaType(getMethod()));
    }

    public OutputFormat(Document doc, String encoding, boolean indenting) {
        this(doc);
        setEncoding(encoding);
        setIndenting(indenting);
    }

    public String getMethod() {
        return this._method;
    }

    public void setMethod(String method) {
        this._method = method;
    }

    public String getVersion() {
        return this._version;
    }

    public void setVersion(String version) {
        this._version = version;
    }

    public int getIndent() {
        return this._indent;
    }

    public boolean getIndenting() {
        return this._indent > 0;
    }

    public void setIndent(int indent) {
        if (indent < 0) {
            this._indent = 0;
        } else {
            this._indent = indent;
        }
    }

    public void setIndenting(boolean on) {
        if (on) {
            this._indent = 4;
            this._lineWidth = 72;
            return;
        }
        this._indent = 0;
        this._lineWidth = 0;
    }

    public String getEncoding() {
        return this._encoding;
    }

    public void setEncoding(String encoding) {
        this._encoding = encoding;
        this._encodingInfo = null;
    }

    public void setEncoding(EncodingInfo encInfo) {
        this._encoding = encInfo.getIANAName();
        this._encodingInfo = encInfo;
    }

    public EncodingInfo getEncodingInfo() throws UnsupportedEncodingException {
        if (this._encodingInfo == null) {
            this._encodingInfo = Encodings.getEncodingInfo(this._encoding, this._allowJavaNames);
        }
        return this._encodingInfo;
    }

    public void setAllowJavaNames(boolean allow) {
        this._allowJavaNames = allow;
    }

    public boolean setAllowJavaNames() {
        return this._allowJavaNames;
    }

    public String getMediaType() {
        return this._mediaType;
    }

    public void setMediaType(String mediaType) {
        this._mediaType = mediaType;
    }

    public void setDoctype(String publicId, String systemId) {
        this._doctypePublic = publicId;
        this._doctypeSystem = systemId;
    }

    public String getDoctypePublic() {
        return this._doctypePublic;
    }

    public String getDoctypeSystem() {
        return this._doctypeSystem;
    }

    public boolean getOmitComments() {
        return this._omitComments;
    }

    public void setOmitComments(boolean omit) {
        this._omitComments = omit;
    }

    public boolean getOmitDocumentType() {
        return this._omitDoctype;
    }

    public void setOmitDocumentType(boolean omit) {
        this._omitDoctype = omit;
    }

    public boolean getOmitXMLDeclaration() {
        return this._omitXmlDeclaration;
    }

    public void setOmitXMLDeclaration(boolean omit) {
        this._omitXmlDeclaration = omit;
    }

    public boolean getStandalone() {
        return this._standalone;
    }

    public void setStandalone(boolean standalone) {
        this._standalone = standalone;
    }

    public String[] getCDataElements() {
        return this._cdataElements;
    }

    public boolean isCDataElement(String tagName) {
        if (this._cdataElements == null) {
            return false;
        }
        for (String equals : this._cdataElements) {
            if (equals.equals(tagName)) {
                return true;
            }
        }
        return false;
    }

    public void setCDataElements(String[] cdataElements) {
        this._cdataElements = cdataElements;
    }

    public String[] getNonEscapingElements() {
        return this._nonEscapingElements;
    }

    public boolean isNonEscapingElement(String tagName) {
        if (this._nonEscapingElements == null) {
            return false;
        }
        for (String equals : this._nonEscapingElements) {
            if (equals.equals(tagName)) {
                return true;
            }
        }
        return false;
    }

    public void setNonEscapingElements(String[] nonEscapingElements) {
        this._nonEscapingElements = nonEscapingElements;
    }

    public String getLineSeparator() {
        return this._lineSeparator;
    }

    public void setLineSeparator(String lineSeparator) {
        if (lineSeparator == null) {
            this._lineSeparator = "\n";
        } else {
            this._lineSeparator = lineSeparator;
        }
    }

    public boolean getPreserveSpace() {
        return this._preserve;
    }

    public void setPreserveSpace(boolean preserve) {
        this._preserve = preserve;
    }

    public int getLineWidth() {
        return this._lineWidth;
    }

    public void setLineWidth(int lineWidth) {
        if (lineWidth <= 0) {
            this._lineWidth = 0;
        } else {
            this._lineWidth = lineWidth;
        }
    }

    public boolean getPreserveEmptyAttributes() {
        return this._preserveEmptyAttributes;
    }

    public void setPreserveEmptyAttributes(boolean preserve) {
        this._preserveEmptyAttributes = preserve;
    }

    public char getLastPrintable() {
        if (getEncoding() == null || !getEncoding().equalsIgnoreCase(HTTP.ASCII)) {
            return '￿';
        }
        return 'ÿ';
    }

    public static String whichMethod(Document doc) {
        if (doc instanceof HTMLDocument) {
            return "html";
        }
        Node node = doc.getFirstChild();
        while (node != null) {
            if (node.getNodeType() != (short) 1) {
                if (node.getNodeType() == (short) 3) {
                    String value = node.getNodeValue();
                    int i = 0;
                    while (i < value.length()) {
                        if (value.charAt(i) != ' ' && value.charAt(i) != '\n' && value.charAt(i) != '\t' && value.charAt(i) != '\r') {
                            return "xml";
                        }
                        i++;
                    }
                    continue;
                }
                node = node.getNextSibling();
            } else if (node.getNodeName().equalsIgnoreCase("html")) {
                return "html";
            } else {
                if (node.getNodeName().equalsIgnoreCase("root")) {
                    return Method.FOP;
                }
                return "xml";
            }
        }
        return "xml";
    }

    public static String whichDoctypePublic(Document doc) {
        DocumentType doctype = doc.getDoctype();
        if (doctype != null) {
            try {
                return doctype.getPublicId();
            } catch (Error e) {
            }
        }
        if (doc instanceof HTMLDocument) {
            return "-//W3C//DTD XHTML 1.0 Strict//EN";
        }
        return null;
    }

    public static String whichDoctypeSystem(Document doc) {
        DocumentType doctype = doc.getDoctype();
        if (doctype != null) {
            try {
                return doctype.getSystemId();
            } catch (Error e) {
            }
        }
        if (doc instanceof HTMLDocument) {
            return "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd";
        }
        return null;
    }

    public static String whichMediaType(String method) {
        if (method.equalsIgnoreCase("xml")) {
            return "text/xml";
        }
        if (method.equalsIgnoreCase("html")) {
            return AudienceNetworkActivity.WEBVIEW_MIME_TYPE;
        }
        if (method.equalsIgnoreCase(Method.XHTML)) {
            return AudienceNetworkActivity.WEBVIEW_MIME_TYPE;
        }
        if (method.equalsIgnoreCase("text")) {
            return HTTP.PLAIN_TEXT_TYPE;
        }
        if (method.equalsIgnoreCase(Method.FOP)) {
            return "application/pdf";
        }
        return null;
    }
}
