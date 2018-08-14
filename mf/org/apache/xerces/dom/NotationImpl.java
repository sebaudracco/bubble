package mf.org.apache.xerces.dom;

import mf.org.apache.xerces.util.URI;
import mf.org.apache.xerces.util.URI.MalformedURIException;
import mf.org.w3c.dom.DOMException;
import mf.org.w3c.dom.Notation;

public class NotationImpl extends NodeImpl implements Notation {
    static final long serialVersionUID = -764632195890658402L;
    protected String baseURI;
    protected String name;
    protected String publicId;
    protected String systemId;

    public NotationImpl(CoreDocumentImpl ownerDoc, String name) {
        super(ownerDoc);
        this.name = name;
    }

    public short getNodeType() {
        return (short) 12;
    }

    public String getNodeName() {
        if (needsSyncData()) {
            synchronizeData();
        }
        return this.name;
    }

    public String getPublicId() {
        if (needsSyncData()) {
            synchronizeData();
        }
        return this.publicId;
    }

    public String getSystemId() {
        if (needsSyncData()) {
            synchronizeData();
        }
        return this.systemId;
    }

    public void setPublicId(String id) {
        if (isReadOnly()) {
            throw new DOMException((short) 7, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NO_MODIFICATION_ALLOWED_ERR", null));
        }
        if (needsSyncData()) {
            synchronizeData();
        }
        this.publicId = id;
    }

    public void setSystemId(String id) {
        if (isReadOnly()) {
            throw new DOMException((short) 7, DOMMessageFormatter.formatMessage(DOMMessageFormatter.DOM_DOMAIN, "NO_MODIFICATION_ALLOWED_ERR", null));
        }
        if (needsSyncData()) {
            synchronizeData();
        }
        this.systemId = id;
    }

    public String getBaseURI() {
        if (needsSyncData()) {
            synchronizeData();
        }
        if (this.baseURI == null || this.baseURI.length() == 0) {
            return this.baseURI;
        }
        try {
            return new URI(this.baseURI).toString();
        } catch (MalformedURIException e) {
            return null;
        }
    }

    public void setBaseURI(String uri) {
        if (needsSyncData()) {
            synchronizeData();
        }
        this.baseURI = uri;
    }
}
