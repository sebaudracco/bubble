package mf.org.apache.xerces.dom;

import mf.org.w3c.dom.ProcessingInstruction;

public class ProcessingInstructionImpl extends CharacterDataImpl implements ProcessingInstruction {
    static final long serialVersionUID = 7554435174099981510L;
    protected String target;

    public ProcessingInstructionImpl(CoreDocumentImpl ownerDoc, String target, String data) {
        super(ownerDoc, data);
        this.target = target;
    }

    public short getNodeType() {
        return (short) 7;
    }

    public String getNodeName() {
        if (needsSyncData()) {
            synchronizeData();
        }
        return this.target;
    }

    public String getTarget() {
        if (needsSyncData()) {
            synchronizeData();
        }
        return this.target;
    }

    public String getBaseURI() {
        if (needsSyncData()) {
            synchronizeData();
        }
        return this.ownerNode.getBaseURI();
    }
}
