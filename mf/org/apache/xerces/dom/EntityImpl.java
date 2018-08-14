package mf.org.apache.xerces.dom;

import mf.org.w3c.dom.Entity;
import mf.org.w3c.dom.Node;

public class EntityImpl extends ParentNode implements Entity {
    static final long serialVersionUID = -3575760943444303423L;
    protected String baseURI;
    protected String encoding;
    protected String inputEncoding;
    protected String name;
    protected String notationName;
    protected String publicId;
    protected String systemId;
    protected String version;

    public EntityImpl(CoreDocumentImpl ownerDoc, String name) {
        super(ownerDoc);
        this.name = name;
        isReadOnly(true);
    }

    public short getNodeType() {
        return (short) 6;
    }

    public String getNodeName() {
        if (needsSyncData()) {
            synchronizeData();
        }
        return this.name;
    }

    public Node cloneNode(boolean deep) {
        EntityImpl newentity = (EntityImpl) super.cloneNode(deep);
        newentity.setReadOnly(true, deep);
        return newentity;
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

    public String getXmlVersion() {
        if (needsSyncData()) {
            synchronizeData();
        }
        return this.version;
    }

    public String getXmlEncoding() {
        if (needsSyncData()) {
            synchronizeData();
        }
        return this.encoding;
    }

    public String getNotationName() {
        if (needsSyncData()) {
            synchronizeData();
        }
        return this.notationName;
    }

    public void setPublicId(String id) {
        if (needsSyncData()) {
            synchronizeData();
        }
        this.publicId = id;
    }

    public void setXmlEncoding(String value) {
        if (needsSyncData()) {
            synchronizeData();
        }
        this.encoding = value;
    }

    public String getInputEncoding() {
        if (needsSyncData()) {
            synchronizeData();
        }
        return this.inputEncoding;
    }

    public void setInputEncoding(String inputEncoding) {
        if (needsSyncData()) {
            synchronizeData();
        }
        this.inputEncoding = inputEncoding;
    }

    public void setXmlVersion(String value) {
        if (needsSyncData()) {
            synchronizeData();
        }
        this.version = value;
    }

    public void setSystemId(String id) {
        if (needsSyncData()) {
            synchronizeData();
        }
        this.systemId = id;
    }

    public void setNotationName(String name) {
        if (needsSyncData()) {
            synchronizeData();
        }
        this.notationName = name;
    }

    public String getBaseURI() {
        if (needsSyncData()) {
            synchronizeData();
        }
        return this.baseURI != null ? this.baseURI : ((CoreDocumentImpl) getOwnerDocument()).getBaseURI();
    }

    public void setBaseURI(String uri) {
        if (needsSyncData()) {
            synchronizeData();
        }
        this.baseURI = uri;
    }
}
