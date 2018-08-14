package mf.org.apache.xerces.dom;

import mf.org.apache.xerces.util.URI;
import mf.org.apache.xerces.util.URI.MalformedURIException;
import mf.org.w3c.dom.DocumentType;
import mf.org.w3c.dom.EntityReference;
import mf.org.w3c.dom.NamedNodeMap;
import mf.org.w3c.dom.Node;

public class EntityReferenceImpl extends ParentNode implements EntityReference {
    static final long serialVersionUID = -7381452955687102062L;
    protected String baseURI;
    protected String name;

    public EntityReferenceImpl(CoreDocumentImpl ownerDoc, String name) {
        super(ownerDoc);
        this.name = name;
        isReadOnly(true);
        needsSyncChildren(true);
    }

    public short getNodeType() {
        return (short) 5;
    }

    public String getNodeName() {
        if (needsSyncData()) {
            synchronizeData();
        }
        return this.name;
    }

    public Node cloneNode(boolean deep) {
        EntityReferenceImpl er = (EntityReferenceImpl) super.cloneNode(deep);
        er.setReadOnly(true, deep);
        return er;
    }

    public String getBaseURI() {
        if (needsSyncData()) {
            synchronizeData();
        }
        if (this.baseURI == null) {
            DocumentType doctype = getOwnerDocument().getDoctype();
            if (doctype != null) {
                NamedNodeMap entities = doctype.getEntities();
                if (entities != null) {
                    EntityImpl entDef = (EntityImpl) entities.getNamedItem(getNodeName());
                    if (entDef != null) {
                        return entDef.getBaseURI();
                    }
                }
            }
        } else if (!(this.baseURI == null || this.baseURI.length() == 0)) {
            try {
                return new URI(this.baseURI).toString();
            } catch (MalformedURIException e) {
                return null;
            }
        }
        return this.baseURI;
    }

    public void setBaseURI(String uri) {
        if (needsSyncData()) {
            synchronizeData();
        }
        this.baseURI = uri;
    }

    protected String getEntityRefValue() {
        if (needsSyncChildren()) {
            synchronizeChildren();
        }
        String value = "";
        if (this.firstChild == null) {
            return "";
        }
        if (this.firstChild.getNodeType() == (short) 5) {
            value = ((EntityReferenceImpl) this.firstChild).getEntityRefValue();
        } else if (this.firstChild.getNodeType() != (short) 3) {
            return null;
        } else {
            value = this.firstChild.getNodeValue();
        }
        if (this.firstChild.nextSibling == null) {
            return value;
        }
        StringBuffer buff = new StringBuffer(value);
        for (ChildNode next = this.firstChild.nextSibling; next != null; next = next.nextSibling) {
            if (next.getNodeType() == (short) 5) {
                value = ((EntityReferenceImpl) next).getEntityRefValue();
            } else if (next.getNodeType() != (short) 3) {
                return null;
            } else {
                value = next.getNodeValue();
            }
            buff.append(value);
        }
        return buff.toString();
    }

    protected void synchronizeChildren() {
        needsSyncChildren(false);
        DocumentType doctype = getOwnerDocument().getDoctype();
        if (doctype != null) {
            NamedNodeMap entities = doctype.getEntities();
            if (entities != null) {
                EntityImpl entDef = (EntityImpl) entities.getNamedItem(getNodeName());
                if (entDef != null) {
                    isReadOnly(false);
                    for (Node defkid = entDef.getFirstChild(); defkid != null; defkid = defkid.getNextSibling()) {
                        insertBefore(defkid.cloneNode(true), null);
                    }
                    setReadOnly(true, true);
                }
            }
        }
    }

    public void setReadOnly(boolean readOnly, boolean deep) {
        if (needsSyncData()) {
            synchronizeData();
        }
        if (deep) {
            if (needsSyncChildren()) {
                synchronizeChildren();
            }
            for (ChildNode mykid = this.firstChild; mykid != null; mykid = mykid.nextSibling) {
                mykid.setReadOnly(readOnly, true);
            }
        }
        isReadOnly(readOnly);
    }
}
