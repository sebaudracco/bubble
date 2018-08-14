package mf.org.apache.xerces.impl.dtd;

import mf.org.apache.xerces.util.SymbolTable;
import mf.org.apache.xerces.xni.Augmentations;
import mf.org.apache.xerces.xni.XNIException;

final class BalancedDTDGrammar extends DTDGrammar {
    private int fDepth = 0;
    private int[][] fGroupIndexStack;
    private int[] fGroupIndexStackSizes;
    private boolean fMixed;
    private short[] fOpStack = null;

    public BalancedDTDGrammar(SymbolTable symbolTable, XMLDTDDescription desc) {
        super(symbolTable, desc);
    }

    public final void startContentModel(String elementName, Augmentations augs) throws XNIException {
        this.fDepth = 0;
        initializeContentModelStacks();
        super.startContentModel(elementName, augs);
    }

    public final void startGroup(Augmentations augs) throws XNIException {
        this.fDepth++;
        initializeContentModelStacks();
        this.fMixed = false;
    }

    public final void pcdata(Augmentations augs) throws XNIException {
        this.fMixed = true;
    }

    public final void element(String elementName, Augmentations augs) throws XNIException {
        addToCurrentGroup(addUniqueLeafNode(elementName));
    }

    public final void separator(short separator, Augmentations augs) throws XNIException {
        if (separator == (short) 0) {
            this.fOpStack[this.fDepth] = (short) 4;
        } else if (separator == (short) 1) {
            this.fOpStack[this.fDepth] = (short) 5;
        }
    }

    public final void occurrence(short occurrence, Augmentations augs) throws XNIException {
        if (!this.fMixed) {
            int currentIndex = this.fGroupIndexStackSizes[this.fDepth] - 1;
            if (occurrence == (short) 2) {
                this.fGroupIndexStack[this.fDepth][currentIndex] = addContentSpecNode((short) 1, this.fGroupIndexStack[this.fDepth][currentIndex], -1);
            } else if (occurrence == (short) 3) {
                this.fGroupIndexStack[this.fDepth][currentIndex] = addContentSpecNode((short) 2, this.fGroupIndexStack[this.fDepth][currentIndex], -1);
            } else if (occurrence == (short) 4) {
                this.fGroupIndexStack[this.fDepth][currentIndex] = addContentSpecNode((short) 3, this.fGroupIndexStack[this.fDepth][currentIndex], -1);
            }
        }
    }

    public final void endGroup(Augmentations augs) throws XNIException {
        int length = this.fGroupIndexStackSizes[this.fDepth];
        int group = length > 0 ? addContentSpecNodes(0, length - 1) : addUniqueLeafNode(null);
        this.fDepth--;
        addToCurrentGroup(group);
    }

    public final void endDTD(Augmentations augs) throws XNIException {
        super.endDTD(augs);
        this.fOpStack = null;
        this.fGroupIndexStack = null;
        this.fGroupIndexStackSizes = null;
    }

    protected final void addContentSpecToElement(XMLElementDecl elementDecl) {
        setContentSpecIndex(this.fCurrentElementIndex, this.fGroupIndexStackSizes[0] > 0 ? this.fGroupIndexStack[0][0] : -1);
    }

    private int addContentSpecNodes(int begin, int end) {
        if (begin == end) {
            return this.fGroupIndexStack[this.fDepth][begin];
        }
        int middle = (begin + end) >>> 1;
        return addContentSpecNode(this.fOpStack[this.fDepth], addContentSpecNodes(begin, middle), addContentSpecNodes(middle + 1, end));
    }

    private void initializeContentModelStacks() {
        if (this.fOpStack == null) {
            this.fOpStack = new short[8];
            this.fGroupIndexStack = new int[8][];
            this.fGroupIndexStackSizes = new int[8];
        } else if (this.fDepth == this.fOpStack.length) {
            short[] newOpStack = new short[(this.fDepth * 2)];
            System.arraycopy(this.fOpStack, 0, newOpStack, 0, this.fDepth);
            this.fOpStack = newOpStack;
            int[][] newGroupIndexStack = new int[(this.fDepth * 2)][];
            System.arraycopy(this.fGroupIndexStack, 0, newGroupIndexStack, 0, this.fDepth);
            this.fGroupIndexStack = newGroupIndexStack;
            int[] newGroupIndexStackLengths = new int[(this.fDepth * 2)];
            System.arraycopy(this.fGroupIndexStackSizes, 0, newGroupIndexStackLengths, 0, this.fDepth);
            this.fGroupIndexStackSizes = newGroupIndexStackLengths;
        }
        this.fOpStack[this.fDepth] = (short) -1;
        this.fGroupIndexStackSizes[this.fDepth] = 0;
    }

    private void addToCurrentGroup(int contentSpec) {
        int[] currentGroup = this.fGroupIndexStack[this.fDepth];
        int[] iArr = this.fGroupIndexStackSizes;
        int i = this.fDepth;
        int length = iArr[i];
        iArr[i] = length + 1;
        if (currentGroup == null) {
            currentGroup = new int[8];
            this.fGroupIndexStack[this.fDepth] = currentGroup;
        } else if (length == currentGroup.length) {
            int[] newGroup = new int[(currentGroup.length * 2)];
            System.arraycopy(currentGroup, 0, newGroup, 0, currentGroup.length);
            currentGroup = newGroup;
            this.fGroupIndexStack[this.fDepth] = currentGroup;
        }
        currentGroup[length] = contentSpec;
    }
}
