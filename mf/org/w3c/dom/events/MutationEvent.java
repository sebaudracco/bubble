package mf.org.w3c.dom.events;

import mf.org.w3c.dom.Node;

public interface MutationEvent extends Event {
    public static final short ADDITION = (short) 2;
    public static final short MODIFICATION = (short) 1;
    public static final short REMOVAL = (short) 3;

    short getAttrChange();

    String getAttrName();

    String getNewValue();

    String getPrevValue();

    Node getRelatedNode();

    void initMutationEvent(String str, boolean z, boolean z2, Node node, String str2, String str3, String str4, short s);
}
