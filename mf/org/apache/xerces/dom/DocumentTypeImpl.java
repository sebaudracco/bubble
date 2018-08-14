package mf.org.apache.xerces.dom;

import java.util.Hashtable;
import mf.org.w3c.dom.DOMException;
import mf.org.w3c.dom.DocumentType;
import mf.org.w3c.dom.NamedNodeMap;
import mf.org.w3c.dom.Node;
import mf.org.w3c.dom.UserDataHandler;

public class DocumentTypeImpl extends ParentNode implements DocumentType {
    static final long serialVersionUID = 7751299192316526485L;
    private int doctypeNumber;
    protected NamedNodeMapImpl elements;
    protected NamedNodeMapImpl entities;
    protected String internalSubset;
    protected String name;
    protected NamedNodeMapImpl notations;
    protected String publicID;
    protected String systemID;
    private Hashtable userData;

    public DocumentTypeImpl(CoreDocumentImpl ownerDocument, String name) {
        super(ownerDocument);
        this.doctypeNumber = 0;
        this.userData = null;
        this.name = name;
        this.entities = new NamedNodeMapImpl(this);
        this.notations = new NamedNodeMapImpl(this);
        this.elements = new NamedNodeMapImpl(this);
    }

    public DocumentTypeImpl(CoreDocumentImpl ownerDocument, String qualifiedName, String publicID, String systemID) {
        this(ownerDocument, qualifiedName);
        this.publicID = publicID;
        this.systemID = systemID;
    }

    public String getPublicId() {
        if (needsSyncData()) {
            synchronizeData();
        }
        return this.publicID;
    }

    public String getSystemId() {
        if (needsSyncData()) {
            synchronizeData();
        }
        return this.systemID;
    }

    public void setInternalSubset(String internalSubset) {
        if (needsSyncData()) {
            synchronizeData();
        }
        this.internalSubset = internalSubset;
    }

    public String getInternalSubset() {
        if (needsSyncData()) {
            synchronizeData();
        }
        return this.internalSubset;
    }

    public short getNodeType() {
        return (short) 10;
    }

    public String getNodeName() {
        if (needsSyncData()) {
            synchronizeData();
        }
        return this.name;
    }

    public Node cloneNode(boolean deep) {
        NodeImpl newnode = (DocumentTypeImpl) super.cloneNode(deep);
        newnode.entities = this.entities.cloneMap(newnode);
        newnode.notations = this.notations.cloneMap(newnode);
        newnode.elements = this.elements.cloneMap(newnode);
        return newnode;
    }

    public String getTextContent() throws DOMException {
        return null;
    }

    public void setTextContent(String textContent) throws DOMException {
    }

    public boolean isEqualNode(Node arg) {
        if (!super.isEqualNode(arg)) {
            return false;
        }
        if (needsSyncData()) {
            synchronizeData();
        }
        DocumentTypeImpl argDocType = (DocumentTypeImpl) arg;
        if (getPublicId() == null && argDocType.getPublicId() != null) {
            return false;
        }
        if (getPublicId() != null && argDocType.getPublicId() == null) {
            return false;
        }
        if (getSystemId() == null && argDocType.getSystemId() != null) {
            return false;
        }
        if (getSystemId() != null && argDocType.getSystemId() == null) {
            return false;
        }
        if (getInternalSubset() == null && argDocType.getInternalSubset() != null) {
            return false;
        }
        if (getInternalSubset() != null && argDocType.getInternalSubset() == null) {
            return false;
        }
        if (getPublicId() != null && !getPublicId().equals(argDocType.getPublicId())) {
            return false;
        }
        if (getSystemId() != null && !getSystemId().equals(argDocType.getSystemId())) {
            return false;
        }
        if (getInternalSubset() != null && !getInternalSubset().equals(argDocType.getInternalSubset())) {
            return false;
        }
        NamedNodeMapImpl argEntities = argDocType.entities;
        if (this.entities == null && argEntities != null) {
            return false;
        }
        if (this.entities != null && argEntities == null) {
            return false;
        }
        int index;
        if (!(this.entities == null || argEntities == null)) {
            if (this.entities.getLength() != argEntities.getLength()) {
                return false;
            }
            for (index = 0; this.entities.item(index) != null; index++) {
                Node entNode1 = this.entities.item(index);
                if (!((NodeImpl) entNode1).isEqualNode(argEntities.getNamedItem(entNode1.getNodeName()))) {
                    return false;
                }
            }
        }
        NamedNodeMapImpl argNotations = argDocType.notations;
        if (this.notations == null && argNotations != null) {
            return false;
        }
        if (this.notations != null && argNotations == null) {
            return false;
        }
        if (!(this.notations == null || argNotations == null)) {
            if (this.notations.getLength() != argNotations.getLength()) {
                return false;
            }
            for (index = 0; this.notations.item(index) != null; index++) {
                Node noteNode1 = this.notations.item(index);
                if (!((NodeImpl) noteNode1).isEqualNode(argNotations.getNamedItem(noteNode1.getNodeName()))) {
                    return false;
                }
            }
        }
        return true;
    }

    protected void setOwnerDocument(CoreDocumentImpl doc) {
        super.setOwnerDocument(doc);
        this.entities.setOwnerDocument(doc);
        this.notations.setOwnerDocument(doc);
        this.elements.setOwnerDocument(doc);
    }

    protected int getNodeNumber() {
        if (getOwnerDocument() != null) {
            return super.getNodeNumber();
        }
        if (this.doctypeNumber == 0) {
            this.doctypeNumber = ((CoreDOMImplementationImpl) CoreDOMImplementationImpl.getDOMImplementation()).assignDocTypeNumber();
        }
        return this.doctypeNumber;
    }

    public String getName() {
        if (needsSyncData()) {
            synchronizeData();
        }
        return this.name;
    }

    public NamedNodeMap getEntities() {
        if (needsSyncChildren()) {
            synchronizeChildren();
        }
        return this.entities;
    }

    public NamedNodeMap getNotations() {
        if (needsSyncChildren()) {
            synchronizeChildren();
        }
        return this.notations;
    }

    public void setReadOnly(boolean readOnly, boolean deep) {
        if (needsSyncChildren()) {
            synchronizeChildren();
        }
        super.setReadOnly(readOnly, deep);
        this.elements.setReadOnly(readOnly, true);
        this.entities.setReadOnly(readOnly, true);
        this.notations.setReadOnly(readOnly, true);
    }

    public NamedNodeMap getElements() {
        if (needsSyncChildren()) {
            synchronizeChildren();
        }
        return this.elements;
    }

    public Object setUserData(String key, Object data, UserDataHandler handler) {
        if (this.userData == null) {
            this.userData = new Hashtable();
        }
        if (data != null) {
            Object o = this.userData.put(key, new UserDataRecord(data, handler));
            if (o != null) {
                return ((UserDataRecord) o).fData;
            }
            return null;
        } else if (this.userData == null) {
            return null;
        } else {
            UserDataRecord o2 = this.userData.remove(key);
            if (o2 != null) {
                return o2.fData;
            }
            return null;
        }
    }

    public Object getUserData(String key) {
        if (this.userData == null) {
            return null;
        }
        UserDataRecord o = this.userData.get(key);
        if (o != null) {
            return o.fData;
        }
        return null;
    }

    protected Hashtable getUserDataRecord() {
        return this.userData;
    }
}
