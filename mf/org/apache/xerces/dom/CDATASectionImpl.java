package mf.org.apache.xerces.dom;

import mf.org.w3c.dom.CDATASection;

public class CDATASectionImpl extends TextImpl implements CDATASection {
    static final long serialVersionUID = 2372071297878177780L;

    public CDATASectionImpl(CoreDocumentImpl ownerDoc, String data) {
        super(ownerDoc, data);
    }

    public short getNodeType() {
        return (short) 4;
    }

    public String getNodeName() {
        return "#cdata-section";
    }
}
