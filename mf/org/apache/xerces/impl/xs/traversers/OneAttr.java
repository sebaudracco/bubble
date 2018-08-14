package mf.org.apache.xerces.impl.xs.traversers;

/* compiled from: XSAttributeChecker */
class OneAttr {
    public Object dfltValue;
    public int dvIndex;
    public String name;
    public int valueIndex;

    public OneAttr(String name, int dvIndex, int valueIndex, Object dfltValue) {
        this.name = name;
        this.dvIndex = dvIndex;
        this.valueIndex = valueIndex;
        this.dfltValue = dfltValue;
    }
}
