package mf.org.apache.xerces.impl.dtd.models;

public class CMAny extends CMNode {
    private int fPosition = -1;
    private final int fType;
    private final String fURI;

    public CMAny(int type, String uri, int position) {
        super(type);
        this.fType = type;
        this.fURI = uri;
        this.fPosition = position;
    }

    final int getType() {
        return this.fType;
    }

    final String getURI() {
        return this.fURI;
    }

    final int getPosition() {
        return this.fPosition;
    }

    final void setPosition(int newPosition) {
        this.fPosition = newPosition;
    }

    public boolean isNullable() {
        return this.fPosition == -1;
    }

    public String toString() {
        StringBuffer strRet = new StringBuffer();
        strRet.append('(');
        strRet.append("##any:uri=");
        strRet.append(this.fURI);
        strRet.append(')');
        if (this.fPosition >= 0) {
            strRet.append(" (Pos:").append(Integer.toString(this.fPosition)).append(')');
        }
        return strRet.toString();
    }

    protected void calcFirstPos(CMStateSet toSet) {
        if (this.fPosition == -1) {
            toSet.zeroBits();
        } else {
            toSet.setBit(this.fPosition);
        }
    }

    protected void calcLastPos(CMStateSet toSet) {
        if (this.fPosition == -1) {
            toSet.zeroBits();
        } else {
            toSet.setBit(this.fPosition);
        }
    }
}
