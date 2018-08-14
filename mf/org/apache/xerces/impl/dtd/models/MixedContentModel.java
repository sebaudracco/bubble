package mf.org.apache.xerces.impl.dtd.models;

import mf.org.apache.xerces.xni.QName;

public class MixedContentModel implements ContentModelValidator {
    private final QName[] fChildren = new QName[this.fCount];
    private final int[] fChildrenType = new int[this.fCount];
    private final int fCount;
    private final boolean fOrdered;

    public MixedContentModel(QName[] children, int[] type, int offset, int length, boolean ordered) {
        this.fCount = length;
        for (int i = 0; i < this.fCount; i++) {
            this.fChildren[i] = new QName(children[offset + i]);
            this.fChildrenType[i] = type[offset + i];
        }
        this.fOrdered = ordered;
    }

    public int validate(QName[] children, int offset, int length) {
        int inIndex;
        int outIndex;
        int type;
        String uri;
        if (this.fOrdered) {
            inIndex = 0;
            outIndex = 0;
            while (outIndex < length) {
                if (children[offset + outIndex].localpart != null) {
                    type = this.fChildrenType[inIndex];
                    if (type == 0) {
                        if (this.fChildren[inIndex].rawname != children[offset + outIndex].rawname) {
                            return outIndex;
                        }
                    } else if (type == 6) {
                        uri = this.fChildren[inIndex].uri;
                        if (!(uri == null || uri == children[outIndex].uri)) {
                            return outIndex;
                        }
                    } else if (type == 8) {
                        if (children[outIndex].uri != null) {
                            return outIndex;
                        }
                    } else if (type == 7 && this.fChildren[inIndex].uri == children[outIndex].uri) {
                        return outIndex;
                    }
                    inIndex++;
                }
                outIndex++;
            }
        } else {
            outIndex = 0;
            while (outIndex < length) {
                QName curChild = children[offset + outIndex];
                if (curChild.localpart != null) {
                    inIndex = 0;
                    while (inIndex < this.fCount) {
                        type = this.fChildrenType[inIndex];
                        if (type != 0) {
                            if (type != 6) {
                                if (type != 8) {
                                    if (type == 7 && this.fChildren[inIndex].uri != children[outIndex].uri) {
                                        break;
                                    }
                                } else if (children[outIndex].uri == null) {
                                    break;
                                }
                            } else {
                                uri = this.fChildren[inIndex].uri;
                                if (uri == null) {
                                    break;
                                } else if (uri == children[outIndex].uri) {
                                    break;
                                }
                            }
                        } else if (curChild.rawname == this.fChildren[inIndex].rawname) {
                            break;
                        }
                        inIndex++;
                    }
                    if (inIndex == this.fCount) {
                        return outIndex;
                    }
                }
                outIndex++;
            }
        }
        return -1;
    }
}
