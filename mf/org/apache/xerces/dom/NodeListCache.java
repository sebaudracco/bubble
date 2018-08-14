package mf.org.apache.xerces.dom;

import java.io.Serializable;

class NodeListCache implements Serializable {
    private static final long serialVersionUID = -7927529254918631002L;
    ChildNode fChild;
    int fChildIndex = -1;
    int fLength = -1;
    ParentNode fOwner;
    NodeListCache next;

    NodeListCache(ParentNode owner) {
        this.fOwner = owner;
    }
}
