package mf.org.apache.xerces.impl.xs.models;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Vector;
import mf.org.apache.xerces.impl.dtd.models.CMNode;
import mf.org.apache.xerces.impl.dtd.models.CMStateSet;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import mf.org.apache.xerces.impl.xs.SubstitutionGroupHandler;
import mf.org.apache.xerces.impl.xs.XMLSchemaException;
import mf.org.apache.xerces.impl.xs.XSConstraints;
import mf.org.apache.xerces.impl.xs.XSElementDecl;
import mf.org.apache.xerces.impl.xs.XSWildcardDecl;
import mf.org.apache.xerces.xni.QName;

public class XSDFACM implements XSCMValidator {
    private static final boolean DEBUG = false;
    private static final boolean DEBUG_VALIDATE_CONTENT = false;
    private static long time = 0;
    private Occurence[] fCountingStates = null;
    private Object[] fElemMap = null;
    private int[] fElemMapId = null;
    private int fElemMapSize = 0;
    private int[] fElemMapType = null;
    private boolean[] fFinalStateFlags = null;
    private CMStateSet[] fFollowList = null;
    private CMNode fHeadNode = null;
    private boolean fIsCompactedForUPA;
    private int fLeafCount = 0;
    private XSCMLeaf[] fLeafList = null;
    private int[] fLeafListType = null;
    private int[][] fTransTable = null;
    private int fTransTableSize = 0;

    static final class Occurence {
        final int elemIndex;
        final int maxOccurs;
        final int minOccurs;

        public Occurence(XSCMRepeatingLeaf leaf, int elemIndex) {
            this.minOccurs = leaf.getMinOccurs();
            this.maxOccurs = leaf.getMaxOccurs();
            this.elemIndex = elemIndex;
        }

        public String toString() {
            return "minOccurs=" + this.minOccurs + ";maxOccurs=" + (this.maxOccurs != -1 ? Integer.toString(this.maxOccurs) : SchemaSymbols.ATTVAL_UNBOUNDED);
        }
    }

    public XSDFACM(CMNode syntaxTree, int leafCount) {
        this.fLeafCount = leafCount;
        this.fIsCompactedForUPA = syntaxTree.isCompactedForUPA();
        buildDFA(syntaxTree);
    }

    public boolean isFinalState(int state) {
        if (state < 0) {
            return false;
        }
        return this.fFinalStateFlags[state];
    }

    public Object oneTransition(QName curElem, int[] state, SubstitutionGroupHandler subGroupHandler) {
        int i = 1;
        int curState = state[0];
        if (curState == -1 || curState == -2) {
            if (curState == -1) {
                state[0] = -2;
            }
            return findMatchingDecl(curElem, subGroupHandler);
        }
        int nextState = 0;
        int elemIndex = 0;
        Object obj = null;
        while (elemIndex < this.fElemMapSize) {
            nextState = this.fTransTable[curState][elemIndex];
            if (nextState != -1) {
                int type = this.fElemMapType[elemIndex];
                if (type != 1) {
                    if (type == 2 && ((XSWildcardDecl) this.fElemMap[elemIndex]).allowNamespace(curElem.uri)) {
                        obj = this.fElemMap[elemIndex];
                        break;
                    }
                }
                obj = subGroupHandler.getMatchingElemDecl(curElem, (XSElementDecl) this.fElemMap[elemIndex]);
                if (obj != null) {
                    break;
                }
            }
            elemIndex++;
        }
        if (elemIndex == this.fElemMapSize) {
            state[1] = state[0];
            state[0] = -1;
            return findMatchingDecl(curElem, subGroupHandler);
        }
        if (this.fCountingStates != null) {
            Occurence o = this.fCountingStates[curState];
            if (o == null) {
                o = this.fCountingStates[nextState];
                if (o != null) {
                    if (elemIndex != o.elemIndex) {
                        i = 0;
                    }
                    state[2] = i;
                }
            } else if (curState == nextState) {
                int i2 = state[2] + 1;
                state[2] = i2;
                if (i2 > o.maxOccurs && o.maxOccurs != -1) {
                    return findMatchingDecl(curElem, state, subGroupHandler, elemIndex);
                }
            } else if (state[2] < o.minOccurs) {
                state[1] = state[0];
                state[0] = -1;
                return findMatchingDecl(curElem, subGroupHandler);
            } else {
                o = this.fCountingStates[nextState];
                if (o != null) {
                    state[2] = elemIndex == o.elemIndex ? 1 : 0;
                }
            }
        }
        state[0] = nextState;
        return obj;
    }

    Object findMatchingDecl(QName curElem, SubstitutionGroupHandler subGroupHandler) {
        int elemIndex = 0;
        while (elemIndex < this.fElemMapSize) {
            int type = this.fElemMapType[elemIndex];
            if (type == 1) {
                Object matchingDecl = subGroupHandler.getMatchingElemDecl(curElem, (XSElementDecl) this.fElemMap[elemIndex]);
                if (matchingDecl != null) {
                    return matchingDecl;
                }
            } else if (type == 2 && ((XSWildcardDecl) this.fElemMap[elemIndex]).allowNamespace(curElem.uri)) {
                return this.fElemMap[elemIndex];
            }
            elemIndex++;
        }
        return null;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    java.lang.Object findMatchingDecl(mf.org.apache.xerces.xni.QName r12, int[] r13, mf.org.apache.xerces.impl.xs.SubstitutionGroupHandler r14, int r15) {
        /*
        r11 = this;
        r10 = 2;
        r9 = -1;
        r6 = 1;
        r7 = 0;
        r0 = r13[r7];
        r2 = 0;
        r1 = 0;
    L_0x0008:
        r15 = r15 + 1;
        r5 = r11.fElemMapSize;
        if (r15 < r5) goto L_0x001d;
    L_0x000e:
        r5 = r11.fElemMapSize;
        if (r15 != r5) goto L_0x004d;
    L_0x0012:
        r5 = r13[r7];
        r13[r6] = r5;
        r13[r7] = r9;
        r1 = r11.findMatchingDecl(r12, r14);
    L_0x001c:
        return r1;
    L_0x001d:
        r5 = r11.fTransTable;
        r5 = r5[r0];
        r2 = r5[r15];
        if (r2 == r9) goto L_0x0008;
    L_0x0025:
        r5 = r11.fElemMapType;
        r4 = r5[r15];
        if (r4 != r6) goto L_0x0038;
    L_0x002b:
        r5 = r11.fElemMap;
        r5 = r5[r15];
        r5 = (mf.org.apache.xerces.impl.xs.XSElementDecl) r5;
        r1 = r14.getMatchingElemDecl(r12, r5);
        if (r1 == 0) goto L_0x0008;
    L_0x0037:
        goto L_0x000e;
    L_0x0038:
        if (r4 != r10) goto L_0x0008;
    L_0x003a:
        r5 = r11.fElemMap;
        r5 = r5[r15];
        r5 = (mf.org.apache.xerces.impl.xs.XSWildcardDecl) r5;
        r8 = r12.uri;
        r5 = r5.allowNamespace(r8);
        if (r5 == 0) goto L_0x0008;
    L_0x0048:
        r5 = r11.fElemMap;
        r1 = r5[r15];
        goto L_0x000e;
    L_0x004d:
        r13[r7] = r2;
        r5 = r11.fCountingStates;
        r3 = r5[r2];
        if (r3 == 0) goto L_0x001c;
    L_0x0055:
        r5 = r3.elemIndex;
        if (r15 != r5) goto L_0x005d;
    L_0x0059:
        r5 = r6;
    L_0x005a:
        r13[r10] = r5;
        goto L_0x001c;
    L_0x005d:
        r5 = r7;
        goto L_0x005a;
        */
        throw new UnsupportedOperationException("Method not decompiled: mf.org.apache.xerces.impl.xs.models.XSDFACM.findMatchingDecl(mf.org.apache.xerces.xni.QName, int[], mf.org.apache.xerces.impl.xs.SubstitutionGroupHandler, int):java.lang.Object");
    }

    public int[] startContentModel() {
        return new int[3];
    }

    public boolean endContentModel(int[] state) {
        int curState = state[0];
        if (!this.fFinalStateFlags[curState]) {
            return false;
        }
        if (this.fCountingStates != null) {
            Occurence o = this.fCountingStates[curState];
            if (o != null && state[2] < o.minOccurs) {
                return false;
            }
        }
        return true;
    }

    private void buildDFA(CMNode syntaxTree) {
        int leafIndex;
        int EOCPos = this.fLeafCount;
        int i = this.fLeafCount;
        this.fLeafCount = i + 1;
        this.fHeadNode = new XSCMBinOp(102, syntaxTree, new XSCMLeaf(1, null, -1, i));
        this.fLeafList = new XSCMLeaf[this.fLeafCount];
        this.fLeafListType = new int[this.fLeafCount];
        postTreeBuildInit(this.fHeadNode);
        this.fFollowList = new CMStateSet[this.fLeafCount];
        for (int index = 0; index < this.fLeafCount; index++) {
            this.fFollowList[index] = new CMStateSet(this.fLeafCount);
        }
        calcFollowList(this.fHeadNode);
        this.fElemMap = new Object[this.fLeafCount];
        this.fElemMapType = new int[this.fLeafCount];
        this.fElemMapId = new int[this.fLeafCount];
        this.fElemMapSize = 0;
        Occurence[] elemOccurenceMap = null;
        for (int outIndex = 0; outIndex < this.fLeafCount; outIndex++) {
            this.fElemMap[outIndex] = null;
            int inIndex = 0;
            int id = this.fLeafList[outIndex].getParticleId();
            while (inIndex < this.fElemMapSize && id != this.fElemMapId[inIndex]) {
                inIndex++;
            }
            if (inIndex == this.fElemMapSize) {
                XSCMLeaf leaf = this.fLeafList[outIndex];
                this.fElemMap[this.fElemMapSize] = leaf.getLeaf();
                if (leaf instanceof XSCMRepeatingLeaf) {
                    if (elemOccurenceMap == null) {
                        elemOccurenceMap = new Occurence[this.fLeafCount];
                    }
                    elemOccurenceMap[this.fElemMapSize] = new Occurence((XSCMRepeatingLeaf) leaf, this.fElemMapSize);
                }
                this.fElemMapType[this.fElemMapSize] = this.fLeafListType[outIndex];
                this.fElemMapId[this.fElemMapSize] = id;
                this.fElemMapSize++;
            }
        }
        this.fElemMapSize--;
        int[] fLeafSorter = new int[(this.fLeafCount + this.fElemMapSize)];
        int fSortCount = 0;
        int elemIndex = 0;
        while (elemIndex < this.fElemMapSize) {
            int fSortCount2;
            id = this.fElemMapId[elemIndex];
            for (leafIndex = 0; leafIndex < this.fLeafCount; leafIndex++) {
                if (id == this.fLeafList[leafIndex].getParticleId()) {
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
            this.fFinalStateFlags[unmarkedState] = setT.getBit(EOCPos);
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
                        Object newFinalFlags = new boolean[newSize];
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
        if (elemOccurenceMap != null) {
            this.fCountingStates = new Occurence[curState];
            for (int i2 = 0; i2 < curState; i2++) {
                int[] transitions = this.fTransTable[i2];
                for (int j = 0; j < transitions.length; j++) {
                    if (i2 == transitions[j]) {
                        this.fCountingStates[i2] = elemOccurenceMap[j];
                        break;
                    }
                }
            }
        }
        this.fHeadNode = null;
        this.fLeafList = null;
        this.fFollowList = null;
        this.fLeafListType = null;
        this.fElemMapId = null;
    }

    private void calcFollowList(CMNode nodeCur) {
        if (nodeCur.type() == 101) {
            calcFollowList(((XSCMBinOp) nodeCur).getLeft());
            calcFollowList(((XSCMBinOp) nodeCur).getRight());
        } else if (nodeCur.type() == 102) {
            calcFollowList(((XSCMBinOp) nodeCur).getLeft());
            calcFollowList(((XSCMBinOp) nodeCur).getRight());
            last = ((XSCMBinOp) nodeCur).getLeft().lastPos();
            first = ((XSCMBinOp) nodeCur).getRight().firstPos();
            for (index = 0; index < this.fLeafCount; index++) {
                if (last.getBit(index)) {
                    this.fFollowList[index].union(first);
                }
            }
        } else if (nodeCur.type() == 4 || nodeCur.type() == 6) {
            calcFollowList(((XSCMUniOp) nodeCur).getChild());
            first = nodeCur.firstPos();
            last = nodeCur.lastPos();
            for (index = 0; index < this.fLeafCount; index++) {
                if (last.getBit(index)) {
                    this.fFollowList[index].union(first);
                }
            }
        } else if (nodeCur.type() == 5) {
            calcFollowList(((XSCMUniOp) nodeCur).getChild());
        }
    }

    private void dumpTree(CMNode nodeCur, int level) {
        for (int index = 0; index < level; index++) {
            System.out.print("   ");
        }
        int type = nodeCur.type();
        switch (type) {
            case 1:
                System.out.print("Leaf: (pos=" + ((XSCMLeaf) nodeCur).getPosition() + "), " + "(elemIndex=" + ((XSCMLeaf) nodeCur).getLeaf() + ") ");
                if (nodeCur.isNullable()) {
                    System.out.print(" Nullable ");
                }
                System.out.print("firstPos=");
                System.out.print(nodeCur.firstPos().toString());
                System.out.print(" lastPos=");
                System.out.println(nodeCur.lastPos().toString());
                return;
            case 2:
                System.out.print("Any Node: ");
                System.out.print("firstPos=");
                System.out.print(nodeCur.firstPos().toString());
                System.out.print(" lastPos=");
                System.out.println(nodeCur.lastPos().toString());
                return;
            case 4:
            case 5:
            case 6:
                System.out.print("Rep Node ");
                if (nodeCur.isNullable()) {
                    System.out.print("Nullable ");
                }
                System.out.print("firstPos=");
                System.out.print(nodeCur.firstPos().toString());
                System.out.print(" lastPos=");
                System.out.println(nodeCur.lastPos().toString());
                dumpTree(((XSCMUniOp) nodeCur).getChild(), level + 1);
                return;
            case 101:
            case 102:
                if (type == 101) {
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
                dumpTree(((XSCMBinOp) nodeCur).getLeft(), level + 1);
                dumpTree(((XSCMBinOp) nodeCur).getRight(), level + 1);
                return;
            default:
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

    private void postTreeBuildInit(CMNode nodeCur) throws RuntimeException {
        nodeCur.setMaxStates(this.fLeafCount);
        XSCMLeaf leaf;
        int pos;
        if (nodeCur.type() == 2) {
            leaf = (XSCMLeaf) nodeCur;
            pos = leaf.getPosition();
            this.fLeafList[pos] = leaf;
            this.fLeafListType[pos] = 2;
        } else if (nodeCur.type() == 101 || nodeCur.type() == 102) {
            postTreeBuildInit(((XSCMBinOp) nodeCur).getLeft());
            postTreeBuildInit(((XSCMBinOp) nodeCur).getRight());
        } else if (nodeCur.type() == 4 || nodeCur.type() == 6 || nodeCur.type() == 5) {
            postTreeBuildInit(((XSCMUniOp) nodeCur).getChild());
        } else if (nodeCur.type() == 1) {
            leaf = (XSCMLeaf) nodeCur;
            pos = leaf.getPosition();
            this.fLeafList[pos] = leaf;
            this.fLeafListType[pos] = 1;
        } else {
            throw new RuntimeException("ImplementationMessages.VAL_NIICM");
        }
    }

    public boolean checkUniqueParticleAttribution(SubstitutionGroupHandler subGroupHandler) throws XMLSchemaException {
        byte[][] conflictTable = (byte[][]) Array.newInstance(Byte.TYPE, new int[]{this.fElemMapSize, this.fElemMapSize});
        int i = 0;
        while (i < this.fTransTable.length && this.fTransTable[i] != null) {
            int j = 0;
            while (j < this.fElemMapSize) {
                int k = j + 1;
                while (k < this.fElemMapSize) {
                    if (!(this.fTransTable[i][j] == -1 || this.fTransTable[i][k] == -1 || conflictTable[j][k] != (byte) 0)) {
                        if (XSConstraints.overlapUPA(this.fElemMap[j], this.fElemMap[k], subGroupHandler)) {
                            if (this.fCountingStates != null) {
                                Occurence o = this.fCountingStates[i];
                                if (o != null) {
                                    int i2;
                                    if (this.fTransTable[i][j] == i) {
                                        i2 = 1;
                                    } else {
                                        i2 = 0;
                                    }
                                    if ((i2 ^ (this.fTransTable[i][k] == i ? 1 : 0)) != 0 && o.minOccurs == o.maxOccurs) {
                                        conflictTable[j][k] = (byte) -1;
                                    }
                                }
                            }
                            conflictTable[j][k] = (byte) 1;
                        } else {
                            conflictTable[j][k] = (byte) -1;
                        }
                    }
                    k++;
                }
                j++;
            }
            i++;
        }
        for (i = 0; i < this.fElemMapSize; i++) {
            for (j = 0; j < this.fElemMapSize; j++) {
                if (conflictTable[i][j] == (byte) 1) {
                    throw new XMLSchemaException("cos-nonambig", new Object[]{this.fElemMap[i].toString(), this.fElemMap[j].toString()});
                }
            }
        }
        for (i = 0; i < this.fElemMapSize; i++) {
            if (this.fElemMapType[i] == 2) {
                XSWildcardDecl wildcard = this.fElemMap[i];
                if (wildcard.fType == (short) 3 || wildcard.fType == (short) 2) {
                    return true;
                }
            }
        }
        return false;
    }

    public Vector whatCanGoHere(int[] state) {
        int curState = state[0];
        if (curState < 0) {
            curState = state[1];
        }
        Occurence o = this.fCountingStates != null ? this.fCountingStates[curState] : null;
        int count = state[2];
        Vector ret = new Vector();
        for (int elemIndex = 0; elemIndex < this.fElemMapSize; elemIndex++) {
            int nextState = this.fTransTable[curState][elemIndex];
            if (nextState != -1) {
                if (o != null) {
                    if (curState == nextState) {
                        if (count >= o.maxOccurs && o.maxOccurs != -1) {
                        }
                    } else if (count < o.minOccurs) {
                    }
                }
                ret.addElement(this.fElemMap[elemIndex]);
            }
        }
        return ret;
    }

    public int[] occurenceInfo(int[] state) {
        if (this.fCountingStates != null) {
            int curState = state[0];
            if (curState < 0) {
                curState = state[1];
            }
            if (this.fCountingStates[curState] != null) {
                return new int[]{this.fCountingStates[curState].minOccurs, this.fCountingStates[curState].maxOccurs, state[2], this.fCountingStates[curState].elemIndex};
            }
        }
        return null;
    }

    public String getTermName(int termId) {
        Object term = this.fElemMap[termId];
        return term != null ? term.toString() : null;
    }

    public boolean isCompactedForUPA() {
        return this.fIsCompactedForUPA;
    }
}
