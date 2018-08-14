package mf.org.apache.xerces.impl.xs.models;

import mf.org.apache.xerces.impl.dtd.models.CMNode;
import mf.org.apache.xerces.impl.dtd.models.CMStateSet;

public class XSCMBinOp extends CMNode {
    private CMNode fLeftChild;
    private CMNode fRightChild;

    public XSCMBinOp(int type, CMNode leftNode, CMNode rightNode) {
        super(type);
        if (type() == 101 || type() == 102) {
            this.fLeftChild = leftNode;
            this.fRightChild = rightNode;
            return;
        }
        throw new RuntimeException("ImplementationMessages.VAL_BST");
    }

    final CMNode getLeft() {
        return this.fLeftChild;
    }

    final CMNode getRight() {
        return this.fRightChild;
    }

    public boolean isNullable() {
        if (type() == 101) {
            if (this.fLeftChild.isNullable() || this.fRightChild.isNullable()) {
                return true;
            }
            return false;
        } else if (type() != 102) {
            throw new RuntimeException("ImplementationMessages.VAL_BST");
        } else if (this.fLeftChild.isNullable() && this.fRightChild.isNullable()) {
            return true;
        } else {
            return false;
        }
    }

    protected void calcFirstPos(CMStateSet toSet) {
        if (type() == 101) {
            toSet.setTo(this.fLeftChild.firstPos());
            toSet.union(this.fRightChild.firstPos());
        } else if (type() == 102) {
            toSet.setTo(this.fLeftChild.firstPos());
            if (this.fLeftChild.isNullable()) {
                toSet.union(this.fRightChild.firstPos());
            }
        } else {
            throw new RuntimeException("ImplementationMessages.VAL_BST");
        }
    }

    protected void calcLastPos(CMStateSet toSet) {
        if (type() == 101) {
            toSet.setTo(this.fLeftChild.lastPos());
            toSet.union(this.fRightChild.lastPos());
        } else if (type() == 102) {
            toSet.setTo(this.fRightChild.lastPos());
            if (this.fRightChild.isNullable()) {
                toSet.union(this.fLeftChild.lastPos());
            }
        } else {
            throw new RuntimeException("ImplementationMessages.VAL_BST");
        }
    }
}
