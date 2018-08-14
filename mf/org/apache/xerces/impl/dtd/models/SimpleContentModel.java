package mf.org.apache.xerces.impl.dtd.models;

import mf.org.apache.xerces.xni.QName;

public class SimpleContentModel implements ContentModelValidator {
    public static final short CHOICE = (short) -1;
    public static final short SEQUENCE = (short) -1;
    private final QName fFirstChild = new QName();
    private final int fOperator;
    private final QName fSecondChild = new QName();

    public SimpleContentModel(short operator, QName firstChild, QName secondChild) {
        this.fFirstChild.setValues(firstChild);
        if (secondChild != null) {
            this.fSecondChild.setValues(secondChild);
        } else {
            this.fSecondChild.clear();
        }
        this.fOperator = operator;
    }

    public int validate(QName[] children, int offset, int length) {
        int index;
        switch (this.fOperator) {
            case 0:
                if (length == 0 || children[offset].rawname != this.fFirstChild.rawname) {
                    return 0;
                }
                if (length > 1) {
                    return 1;
                }
                break;
            case 1:
                if (length == 1 && children[offset].rawname != this.fFirstChild.rawname) {
                    return 0;
                }
                if (length > 1) {
                    return 1;
                }
                break;
            case 2:
                if (length > 0) {
                    for (index = 0; index < length; index++) {
                        if (children[offset + index].rawname != this.fFirstChild.rawname) {
                            return index;
                        }
                    }
                    break;
                }
                break;
            case 3:
                if (length != 0) {
                    for (index = 0; index < length; index++) {
                        if (children[offset + index].rawname != this.fFirstChild.rawname) {
                            return index;
                        }
                    }
                    break;
                }
                return 0;
            case 4:
                if (length == 0) {
                    return 0;
                }
                if (children[offset].rawname != this.fFirstChild.rawname && children[offset].rawname != this.fSecondChild.rawname) {
                    return 0;
                }
                if (length > 1) {
                    return 1;
                }
                break;
            case 5:
                if (length == 2) {
                    if (children[offset].rawname != this.fFirstChild.rawname) {
                        return 0;
                    }
                    if (children[offset + 1].rawname != this.fSecondChild.rawname) {
                        return 1;
                    }
                } else if (length > 2) {
                    return 2;
                } else {
                    return length;
                }
                break;
            default:
                throw new RuntimeException("ImplementationMessages.VAL_CST");
        }
        return -1;
    }
}
