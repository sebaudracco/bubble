package mf.org.apache.xerces.impl.xs.identity;

import mf.org.apache.xerces.impl.xs.XSAnnotationImpl;
import mf.org.apache.xerces.impl.xs.util.StringListImpl;
import mf.org.apache.xerces.impl.xs.util.XSObjectListImpl;
import mf.org.apache.xerces.xs.StringList;
import mf.org.apache.xerces.xs.XSIDCDefinition;
import mf.org.apache.xerces.xs.XSNamespaceItem;
import mf.org.apache.xerces.xs.XSObjectList;

public abstract class IdentityConstraint implements XSIDCDefinition {
    protected XSAnnotationImpl[] fAnnotations = null;
    protected final String fElementName;
    protected int fFieldCount;
    protected Field[] fFields;
    protected final String fIdentityConstraintName;
    protected final String fNamespace;
    protected int fNumAnnotations;
    protected Selector fSelector;
    protected short type;

    protected IdentityConstraint(String namespace, String identityConstraintName, String elemName) {
        this.fNamespace = namespace;
        this.fIdentityConstraintName = identityConstraintName;
        this.fElementName = elemName;
    }

    public String getIdentityConstraintName() {
        return this.fIdentityConstraintName;
    }

    public void setSelector(Selector selector) {
        this.fSelector = selector;
    }

    public Selector getSelector() {
        return this.fSelector;
    }

    public void addField(Field field) {
        if (this.fFields == null) {
            this.fFields = new Field[4];
        } else if (this.fFieldCount == this.fFields.length) {
            this.fFields = resize(this.fFields, this.fFieldCount * 2);
        }
        Field[] fieldArr = this.fFields;
        int i = this.fFieldCount;
        this.fFieldCount = i + 1;
        fieldArr[i] = field;
    }

    public int getFieldCount() {
        return this.fFieldCount;
    }

    public Field getFieldAt(int index) {
        return this.fFields[index];
    }

    public String getElementName() {
        return this.fElementName;
    }

    public String toString() {
        String s = super.toString();
        int index1 = s.lastIndexOf(36);
        if (index1 != -1) {
            return s.substring(index1 + 1);
        }
        int index2 = s.lastIndexOf(46);
        if (index2 != -1) {
            return s.substring(index2 + 1);
        }
        return s;
    }

    public boolean equals(IdentityConstraint id) {
        if (!this.fIdentityConstraintName.equals(id.fIdentityConstraintName) || !this.fSelector.toString().equals(id.fSelector.toString())) {
            return false;
        }
        boolean areEqual;
        if (this.fFieldCount == id.fFieldCount) {
            areEqual = true;
        } else {
            areEqual = false;
        }
        if (!areEqual) {
            return false;
        }
        for (int i = 0; i < this.fFieldCount; i++) {
            if (!this.fFields[i].toString().equals(id.fFields[i].toString())) {
                return false;
            }
        }
        return true;
    }

    static final Field[] resize(Field[] oldArray, int newSize) {
        Field[] newArray = new Field[newSize];
        System.arraycopy(oldArray, 0, newArray, 0, oldArray.length);
        return newArray;
    }

    public short getType() {
        return (short) 10;
    }

    public String getName() {
        return this.fIdentityConstraintName;
    }

    public String getNamespace() {
        return this.fNamespace;
    }

    public short getCategory() {
        return this.type;
    }

    public String getSelectorStr() {
        return this.fSelector != null ? this.fSelector.toString() : null;
    }

    public StringList getFieldStrs() {
        String[] strs = new String[this.fFieldCount];
        for (int i = 0; i < this.fFieldCount; i++) {
            strs[i] = this.fFields[i].toString();
        }
        return new StringListImpl(strs, this.fFieldCount);
    }

    public XSIDCDefinition getRefKey() {
        return null;
    }

    public XSObjectList getAnnotations() {
        return new XSObjectListImpl(this.fAnnotations, this.fNumAnnotations);
    }

    public XSNamespaceItem getNamespaceItem() {
        return null;
    }

    public void addAnnotation(XSAnnotationImpl annotation) {
        if (annotation != null) {
            if (this.fAnnotations == null) {
                this.fAnnotations = new XSAnnotationImpl[2];
            } else if (this.fNumAnnotations == this.fAnnotations.length) {
                XSAnnotationImpl[] newArray = new XSAnnotationImpl[(this.fNumAnnotations << 1)];
                System.arraycopy(this.fAnnotations, 0, newArray, 0, this.fNumAnnotations);
                this.fAnnotations = newArray;
            }
            XSAnnotationImpl[] xSAnnotationImplArr = this.fAnnotations;
            int i = this.fNumAnnotations;
            this.fNumAnnotations = i + 1;
            xSAnnotationImplArr[i] = annotation;
        }
    }
}
