package mf.org.apache.xerces.impl.dtd.models;

public class CMUniOp extends CMNode {
    private final CMNode fChild;

    public CMUniOp(int type, CMNode childNode) {
        super(type);
        if (type() == 1 || type() == 2 || type() == 3) {
            this.fChild = childNode;
            return;
        }
        throw new RuntimeException("ImplementationMessages.VAL_UST");
    }

    final CMNode getChild() {
        return this.fChild;
    }

    public boolean isNullable() {
        if (type() == 3) {
            return this.fChild.isNullable();
        }
        return true;
    }

    protected void calcFirstPos(CMStateSet toSet) {
        toSet.setTo(this.fChild.firstPos());
    }

    protected void calcLastPos(CMStateSet toSet) {
        toSet.setTo(this.fChild.lastPos());
    }
}
