package mf.org.apache.xerces.xni.parser;

import java.io.InputStream;
import java.io.Reader;
import mf.org.apache.xerces.xni.XMLResourceIdentifier;

public class XMLInputSource {
    protected String fBaseSystemId;
    protected InputStream fByteStream;
    protected Reader fCharStream;
    protected String fEncoding;
    protected String fPublicId;
    protected String fSystemId;

    public XMLInputSource(String publicId, String systemId, String baseSystemId) {
        this.fPublicId = publicId;
        this.fSystemId = systemId;
        this.fBaseSystemId = baseSystemId;
    }

    public XMLInputSource(XMLResourceIdentifier resourceIdentifier) {
        this.fPublicId = resourceIdentifier.getPublicId();
        this.fSystemId = resourceIdentifier.getLiteralSystemId();
        this.fBaseSystemId = resourceIdentifier.getBaseSystemId();
    }

    public XMLInputSource(String publicId, String systemId, String baseSystemId, InputStream byteStream, String encoding) {
        this.fPublicId = publicId;
        this.fSystemId = systemId;
        this.fBaseSystemId = baseSystemId;
        this.fByteStream = byteStream;
        this.fEncoding = encoding;
    }

    public XMLInputSource(String publicId, String systemId, String baseSystemId, Reader charStream, String encoding) {
        this.fPublicId = publicId;
        this.fSystemId = systemId;
        this.fBaseSystemId = baseSystemId;
        this.fCharStream = charStream;
        this.fEncoding = encoding;
    }

    public void setPublicId(String publicId) {
        this.fPublicId = publicId;
    }

    public String getPublicId() {
        return this.fPublicId;
    }

    public void setSystemId(String systemId) {
        this.fSystemId = systemId;
    }

    public String getSystemId() {
        return this.fSystemId;
    }

    public void setBaseSystemId(String baseSystemId) {
        this.fBaseSystemId = baseSystemId;
    }

    public String getBaseSystemId() {
        return this.fBaseSystemId;
    }

    public void setByteStream(InputStream byteStream) {
        this.fByteStream = byteStream;
    }

    public InputStream getByteStream() {
        return this.fByteStream;
    }

    public void setCharacterStream(Reader charStream) {
        this.fCharStream = charStream;
    }

    public Reader getCharacterStream() {
        return this.fCharStream;
    }

    public void setEncoding(String encoding) {
        this.fEncoding = encoding;
    }

    public String getEncoding() {
        return this.fEncoding;
    }
}
