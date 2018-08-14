package mf.org.apache.xerces.dom;

import mf.org.w3c.dom.NamedNodeMap;
import mf.org.w3c.dom.Node;

public class ElementDefinitionImpl extends ParentNode {
    static final long serialVersionUID = -8373890672670022714L;
    protected NamedNodeMapImpl attributes;
    protected String name;

    public ElementDefinitionImpl(CoreDocumentImpl ownerDocument, String name) {
        super(ownerDocument);
        this.name = name;
        this.attributes = new NamedNodeMapImpl(ownerDocument);
    }

    public short getNodeType() {
        return (short) 21;
    }

    public String getNodeName() {
        if (needsSyncData()) {
            synchronizeData();
        }
        return this.name;
    }

    public Node cloneNode(boolean deep) {
        NodeImpl newnode = (ElementDefinitionImpl) super.cloneNode(deep);
        newnode.attributes = this.attributes.cloneMap(newnode);
        return newnode;
    }

    public NamedNodeMap getAttributes() {
        if (needsSyncChildren()) {
            synchronizeChildren();
        }
        return this.attributes;
    }
}
