package mf.org.apache.xerces.dom;

import mf.org.w3c.dom.DocumentFragment;
import mf.org.w3c.dom.Text;

public class DocumentFragmentImpl extends ParentNode implements DocumentFragment {
    static final long serialVersionUID = -7596449967279236746L;

    public DocumentFragmentImpl(CoreDocumentImpl ownerDoc) {
        super(ownerDoc);
    }

    public short getNodeType() {
        return (short) 11;
    }

    public String getNodeName() {
        return "#document-fragment";
    }

    public void normalize() {
        if (!isNormalized()) {
            if (needsSyncChildren()) {
                synchronizeChildren();
            }
            ChildNode kid = this.firstChild;
            while (kid != null) {
                ChildNode next = kid.nextSibling;
                if (kid.getNodeType() == (short) 3) {
                    if (next != null && next.getNodeType() == (short) 3) {
                        ((Text) kid).appendData(next.getNodeValue());
                        removeChild(next);
                        next = kid;
                    } else if (kid.getNodeValue() == null || kid.getNodeValue().length() == 0) {
                        removeChild(kid);
                    }
                }
                kid.normalize();
                kid = next;
            }
            isNormalized(true);
        }
    }
}
