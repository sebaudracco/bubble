package mf.org.apache.xerces.impl.dtd.models;

import java.util.HashMap;
import mf.org.apache.xerces.xni.QName;

public class DFAContentModel implements ContentModelValidator {
    private static final boolean DEBUG_VALIDATE_CONTENT = false;
    private static String fEOCString;
    private static String fEpsilonString;
    private int fEOCPos = 0;
    private QName[] fElemMap = null;
    private int fElemMapSize = 0;
    private int[] fElemMapType = null;
    private boolean fEmptyContentIsValid = false;
    private boolean[] fFinalStateFlags = null;
    private CMStateSet[] fFollowList = null;
    private CMNode fHeadNode = null;
    private int fLeafCount = 0;
    private CMLeaf[] fLeafList = null;
    private int[] fLeafListType = null;
    private boolean fMixed;
    private final QName fQName = new QName();
    private int[][] fTransTable = null;
    private int fTransTableSize = 0;

    static {
        fEpsilonString = "<<CMNODE_EPSILON>>";
        fEOCString = "<<CMNODE_EOC>>";
        fEpsilonString = fEpsilonString.intern();
        fEOCString = fEOCString.intern();
    }

    public DFAContentModel(CMNode syntaxTree, int leafCount, boolean mixed) {
        this.fLeafCount = leafCount;
        this.fMixed = mixed;
        buildDFA(syntaxTree);
    }

    public int validate(QName[] children, int offset, int length) {
        if (length != 0) {
            int curState = 0;
            for (int childIndex = 0; childIndex < length; childIndex++) {
                QName curElem = children[offset + childIndex];
                if (!this.fMixed || curElem.localpart != null) {
                    int elemIndex = 0;
                    while (elemIndex < this.fElemMapSize) {
                        int type = this.fElemMapType[elemIndex] & 15;
                        if (type != 0) {
                            if (type != 6) {
                                if (type != 8) {
                                    if (type == 7 && this.fElemMap[elemIndex].uri != curElem.uri) {
                                        break;
                                    }
                                } else if (curElem.uri == null) {
                                    break;
                                }
                            } else {
                                String uri = this.fElemMap[elemIndex].uri;
                                if (uri == null) {
                                    break;
                                } else if (uri == curElem.uri) {
                                    break;
                                }
                            }
                        } else if (this.fElemMap[elemIndex].rawname == curElem.rawname) {
                            break;
                        }
                        elemIndex++;
                    }
                    if (elemIndex == this.fElemMapSize) {
                        return childIndex;
                    }
                    curState = this.fTransTable[curState][elemIndex];
                    if (curState == -1) {
                        return childIndex;
                    }
                }
            }
            if (this.fFinalStateFlags[curState]) {
                return -1;
            }
            return length;
        } else if (this.fEmptyContentIsValid) {
            return -1;
        } else {
            return 0;
        }
    }

    private void buildDFA(CMNode syntaxTree) {
        this.fQName.setValues(null, fEOCString, fEOCString, null);
        CMNode cMLeaf = new CMLeaf(this.fQName);
        this.fHeadNode = new CMBinOp(5, syntaxTree, cMLeaf);
        this.fEOCPos = this.fLeafCount;
        int i = this.fLeafCount;
        this.fLeafCount = i + 1;
        cMLeaf.setPosition(i);
        this.fLeafList = new CMLeaf[this.fLeafCount];
        this.fLeafListType = new int[this.fLeafCount];
        postTreeBuildInit(this.fHeadNode, 0);
        this.fFollowList = new CMStateSet[this.fLeafCount];
        for (int index = 0; index < this.fLeafCount; index++) {
            this.fFollowList[index] = new CMStateSet(this.fLeafCount);
        }
        calcFollowList(this.fHeadNode);
        this.fElemMap = new QName[this.fLeafCount];
        this.fElemMapType = new int[this.fLeafCount];
        this.fElemMapSize = 0;
        for (int outIndex = 0; outIndex < this.fLeafCount; outIndex++) {
            this.fElemMap[outIndex] = new QName();
            QName element = this.fLeafList[outIndex].getElement();
            int inIndex = 0;
            while (inIndex < this.fElemMapSize && this.fElemMap[inIndex].rawname != element.rawname) {
                inIndex++;
            }
            if (inIndex == this.fElemMapSize) {
                this.fElemMap[this.fElemMapSize].setValues(element);
                this.fElemMapType[this.fElemMapSize] = this.fLeafListType[outIndex];
                this.fElemMapSize++;
            }
        }
        int[] fLeafSorter = new int[(this.fLeafCount + this.fElemMapSize)];
        int fSortCount = 0;
        int elemIndex = 0;
        while (elemIndex < this.fElemMapSize) {
            int leafIndex;
            int fSortCount2;
            for (leafIndex = 0; leafIndex < this.fLeafCount; leafIndex++) {
                if (this.fLeafList[leafIndex].getElement().rawname == this.fElemMap[elemIndex].rawname) {
                    fSortCount2 = fSortCount + 1;
                    fLeafSorter[fSortCount] = leafIndex;
                    fSortCount = fSortCount2;
                }
            }
            fSortCount2 = fSortCount + 1;
            fLeafSorter[fSortCount] = -1;
            elemIndex++;
            fSortCount = fSortCount2;
        }
        int curArraySize = this.fLeafCount * 4;
        CMStateSet[] statesToDo = new CMStateSet[curArraySize];
        this.fFinalStateFlags = new boolean[curArraySize];
        this.fTransTable = new int[curArraySize][];
        CMStateSet setT = this.fHeadNode.firstPos();
        int unmarkedState = 0;
        this.fTransTable[0] = makeDefStateList();
        statesToDo[0] = setT;
        int curState = 0 + 1;
        HashMap stateTable = new HashMap();
        while (unmarkedState < curState) {
            setT = statesToDo[unmarkedState];
            int[] transEntry = this.fTransTable[unmarkedState];
            this.fFinalStateFlags[unmarkedState] = setT.getBit(this.fEOCPos);
            unmarkedState++;
            CMStateSet newSet = null;
            int sorterIndex = 0;
            elemIndex = 0;
            while (elemIndex < this.fElemMapSize) {
                if (newSet == null) {
                    CMStateSet cMStateSet = new CMStateSet(this.fLeafCount);
                } else {
                    newSet.zeroBits();
                }
                int sorterIndex2 = sorterIndex + 1;
                leafIndex = fLeafSorter[sorterIndex];
                while (leafIndex != -1) {
                    if (setT.getBit(leafIndex)) {
                        newSet.union(this.fFollowList[leafIndex]);
                    }
                    sorterIndex = sorterIndex2 + 1;
                    leafIndex = fLeafSorter[sorterIndex2];
                    sorterIndex2 = sorterIndex;
                }
                if (!newSet.isEmpty()) {
                    Integer stateObj = (Integer) stateTable.get(newSet);
                    int stateIndex = stateObj == null ? curState : stateObj.intValue();
                    if (stateIndex == curState) {
                        statesToDo[curState] = newSet;
                        this.fTransTable[curState] = makeDefStateList();
                        stateTable.put(newSet, new Integer(curState));
                        curState++;
                        newSet = null;
                    }
                    transEntry[elemIndex] = stateIndex;
                    if (curState == curArraySize) {
                        int newSize = (int) (((double) curArraySize) * 1.5d);
                        Object newToDo = new CMStateSet[newSize];
                        boolean[] newFinalFlags = new boolean[newSize];
                        Object newTransTable = new int[newSize][];
                        System.arraycopy(statesToDo, 0, newToDo, 0, curArraySize);
                        System.arraycopy(this.fFinalStateFlags, 0, newFinalFlags, 0, curArraySize);
                        System.arraycopy(this.fTransTable, 0, newTransTable, 0, curArraySize);
                        curArraySize = newSize;
                        statesToDo = newToDo;
                        this.fFinalStateFlags = newFinalFlags;
                        this.fTransTable = newTransTable;
                    }
                }
                elemIndex++;
                sorterIndex = sorterIndex2;
            }
        }
        this.fEmptyContentIsValid = ((CMBinOp) this.fHeadNode).getLeft().isNullable();
        this.fHeadNode = null;
        this.fLeafList = null;
        this.fFollowList = null;
    }

    private void calcFollowList(CMNode nodeCur) {
        if (nodeCur.type() == 4) {
            calcFollowList(((CMBinOp) nodeCur).getLeft());
            calcFollowList(((CMBinOp) nodeCur).getRight());
        } else if (nodeCur.type() == 5) {
            calcFollowList(((CMBinOp) nodeCur).getLeft());
            calcFollowList(((CMBinOp) nodeCur).getRight());
            last = ((CMBinOp) nodeCur).getLeft().lastPos();
            first = ((CMBinOp) nodeCur).getRight().firstPos();
            for (index = 0; index < this.fLeafCount; index++) {
                if (last.getBit(index)) {
                    this.fFollowList[index].union(first);
                }
            }
        } else if (nodeCur.type() == 2 || nodeCur.type() == 3) {
            calcFollowList(((CMUniOp) nodeCur).getChild());
            first = nodeCur.firstPos();
            last = nodeCur.lastPos();
            for (index = 0; index < this.fLeafCount; index++) {
                if (last.getBit(index)) {
                    this.fFollowList[index].union(first);
                }
            }
        } else if (nodeCur.type() == 1) {
            calcFollowList(((CMUniOp) nodeCur).getChild());
        }
    }

    private void dumpTree(CMNode nodeCur, int level) {
        for (int index = 0; index < level; index++) {
            System.out.print("   ");
        }
        int type = nodeCur.type();
        if (type == 4 || type == 5) {
            if (type == 4) {
                System.out.print("Choice Node ");
            } else {
                System.out.print("Seq Node ");
            }
            if (nodeCur.isNullable()) {
                System.out.print("Nullable ");
            }
            System.out.print("firstPos=");
            System.out.print(nodeCur.firstPos().toString());
            System.out.print(" lastPos=");
            System.out.println(nodeCur.lastPos().toString());
            dumpTree(((CMBinOp) nodeCur).getLeft(), level + 1);
            dumpTree(((CMBinOp) nodeCur).getRight(), level + 1);
        } else if (nodeCur.type() == 2) {
            System.out.print("Rep Node ");
            if (nodeCur.isNullable()) {
                System.out.print("Nullable ");
            }
            System.out.print("firstPos=");
            System.out.print(nodeCur.firstPos().toString());
            System.out.print(" lastPos=");
            System.out.println(nodeCur.lastPos().toString());
            dumpTree(((CMUniOp) nodeCur).getChild(), level + 1);
        } else if (nodeCur.type() == 0) {
            System.out.print("Leaf: (pos=" + ((CMLeaf) nodeCur).getPosition() + "), " + ((CMLeaf) nodeCur).getElement() + "(elemIndex=" + ((CMLeaf) nodeCur).getElement() + ") ");
            if (nodeCur.isNullable()) {
                System.out.print(" Nullable ");
            }
            System.out.print("firstPos=");
            System.out.print(nodeCur.firstPos().toString());
            System.out.print(" lastPos=");
            System.out.println(nodeCur.lastPos().toString());
        } else {
            throw new RuntimeException("ImplementationMessages.VAL_NIICM");
        }
    }

    private int[] makeDefStateList() {
        int[] retArray = new int[this.fElemMapSize];
        for (int index = 0; index < this.fElemMapSize; index++) {
            retArray[index] = -1;
        }
        return retArray;
    }

    private int postTreeBuildInit(CMNode nodeCur, int curIndex) {
        nodeCur.setMaxStates(this.fLeafCount);
        if ((nodeCur.type() & 15) == 6 || (nodeCur.type() & 15) == 8 || (nodeCur.type() & 15) == 7) {
            this.fLeafList[curIndex] = new CMLeaf(new QName(null, null, null, ((CMAny) nodeCur).getURI()), ((CMAny) nodeCur).getPosition());
            this.fLeafListType[curIndex] = nodeCur.type();
            return curIndex + 1;
        } else if (nodeCur.type() == 4 || nodeCur.type() == 5) {
            return postTreeBuildInit(((CMBinOp) nodeCur).getRight(), postTreeBuildInit(((CMBinOp) nodeCur).getLeft(), curIndex));
        } else if (nodeCur.type() == 2 || nodeCur.type() == 3 || nodeCur.type() == 1) {
            return postTreeBuildInit(((CMUniOp) nodeCur).getChild(), curIndex);
        } else {
            if (nodeCur.type() != 0) {
                throw new RuntimeException("ImplementationMessages.VAL_NIICM: type=" + nodeCur.type());
            } else if (((CMLeaf) nodeCur).getElement().localpart == fEpsilonString) {
                return curIndex;
            } else {
                this.fLeafList[curIndex] = (CMLeaf) nodeCur;
                this.fLeafListType[curIndex] = 0;
                return curIndex + 1;
            }
        }
    }
}
