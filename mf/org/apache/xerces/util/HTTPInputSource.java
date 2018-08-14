package mf.org.apache.xerces.util;

import java.io.InputStream;
import java.io.Reader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import mf.org.apache.xerces.xni.XMLResourceIdentifier;
import mf.org.apache.xerces.xni.parser.XMLInputSource;

public final class HTTPInputSource extends XMLInputSource {
    protected boolean fFollowRedirects = true;
    protected Map fHTTPRequestProperties = new HashMap();

    public HTTPInputSource(String publicId, String systemId, String baseSystemId) {
        super(publicId, systemId, baseSystemId);
    }

    public HTTPInputSource(XMLResourceIdentifier resourceIdentifier) {
        super(resourceIdentifier);
    }

    public HTTPInputSource(String publicId, String systemId, String baseSystemId, InputStream byteStream, String encoding) {
        super(publicId, systemId, baseSystemId, byteStream, encoding);
    }

    public HTTPInputSource(String publicId, String systemId, String baseSystemId, Reader charStream, String encoding) {
        super(publicId, systemId, baseSystemId, charStream, encoding);
    }

    public boolean getFollowHTTPRedirects() {
        return this.fFollowRedirects;
    }

    public void setFollowHTTPRedirects(boolean followRedirects) {
        this.fFollowRedirects = followRedirects;
    }

    public String getHTTPRequestProperty(String key) {
        return (String) this.fHTTPRequestProperties.get(key);
    }

    public Iterator getHTTPRequestProperties() {
        return this.fHTTPRequestProperties.entrySet().iterator();
    }

    public void setHTTPRequestProperty(String key, String value) {
        if (value != null) {
            this.fHTTPRequestProperties.put(key, value);
        } else {
            this.fHTTPRequestProperties.remove(key);
        }
    }
}
