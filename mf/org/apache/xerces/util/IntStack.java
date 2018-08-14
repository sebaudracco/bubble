package mf.org.apache.xerces.util;

public final class IntStack {
    private int[] fData;
    private int fDepth;

    public int size() {
        return this.fDepth;
    }

    public void push(int value) {
        ensureCapacity(this.fDepth + 1);
        int[] iArr = this.fData;
        int i = this.fDepth;
        this.fDepth = i + 1;
        iArr[i] = value;
    }

    public int peek() {
        return this.fData[this.fDepth - 1];
    }

    public int elementAt(int depth) {
        return this.fData[depth];
    }

    public int pop() {
        int[] iArr = this.fData;
        int i = this.fDepth - 1;
        this.fDepth = i;
        return iArr[i];
    }

    public void clear() {
        this.fDepth = 0;
    }

    public void print() {
        System.out.print('(');
        System.out.print(this.fDepth);
        System.out.print(") {");
        for (int i = 0; i < this.fDepth; i++) {
            if (i == 3) {
                System.out.print(" ...");
                break;
            }
            System.out.print(' ');
            System.out.print(this.fData[i]);
            if (i < this.fDepth - 1) {
                System.out.print(',');
            }
        }
        System.out.print(" }");
        System.out.println();
    }

    private void ensureCapacity(int size) {
        if (this.fData == null) {
            this.fData = new int[32];
        } else if (this.fData.length <= size) {
            int[] newdata = new int[(this.fData.length * 2)];
            System.arraycopy(this.fData, 0, newdata, 0, this.fData.length);
            this.fData = newdata;
        }
    }
}
