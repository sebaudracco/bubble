package mf.org.apache.xerces.impl.dtd;

public class XMLContentSpec {
    public static final short CONTENTSPECNODE_ANY = (short) 6;
    public static final short CONTENTSPECNODE_ANY_LAX = (short) 22;
    public static final short CONTENTSPECNODE_ANY_LOCAL = (short) 8;
    public static final short CONTENTSPECNODE_ANY_LOCAL_LAX = (short) 24;
    public static final short CONTENTSPECNODE_ANY_LOCAL_SKIP = (short) 40;
    public static final short CONTENTSPECNODE_ANY_OTHER = (short) 7;
    public static final short CONTENTSPECNODE_ANY_OTHER_LAX = (short) 23;
    public static final short CONTENTSPECNODE_ANY_OTHER_SKIP = (short) 39;
    public static final short CONTENTSPECNODE_ANY_SKIP = (short) 38;
    public static final short CONTENTSPECNODE_CHOICE = (short) 4;
    public static final short CONTENTSPECNODE_LEAF = (short) 0;
    public static final short CONTENTSPECNODE_ONE_OR_MORE = (short) 3;
    public static final short CONTENTSPECNODE_SEQ = (short) 5;
    public static final short CONTENTSPECNODE_ZERO_OR_MORE = (short) 2;
    public static final short CONTENTSPECNODE_ZERO_OR_ONE = (short) 1;
    public Object otherValue;
    public short type;
    public Object value;

    public interface Provider {
        boolean getContentSpec(int i, XMLContentSpec xMLContentSpec);
    }

    public XMLContentSpec() {
        clear();
    }

    public XMLContentSpec(short type, Object value, Object otherValue) {
        setValues(type, value, otherValue);
    }

    public XMLContentSpec(XMLContentSpec contentSpec) {
        setValues(contentSpec);
    }

    public XMLContentSpec(Provider provider, int contentSpecIndex) {
        setValues(provider, contentSpecIndex);
    }

    public void clear() {
        this.type = (short) -1;
        this.value = null;
        this.otherValue = null;
    }

    public void setValues(short type, Object value, Object otherValue) {
        this.type = type;
        this.value = value;
        this.otherValue = otherValue;
    }

    public void setValues(XMLContentSpec contentSpec) {
        this.type = contentSpec.type;
        this.value = contentSpec.value;
        this.otherValue = contentSpec.otherValue;
    }

    public void setValues(Provider provider, int contentSpecIndex) {
        if (!provider.getContentSpec(contentSpecIndex, this)) {
            clear();
        }
    }

    public int hashCode() {
        return ((this.type << 16) | (this.value.hashCode() << 8)) | this.otherValue.hashCode();
    }

    public boolean equals(Object object) {
        if (object == null || !(object instanceof XMLContentSpec)) {
            return false;
        }
        XMLContentSpec contentSpec = (XMLContentSpec) object;
        if (this.type == contentSpec.type && this.value == contentSpec.value && this.otherValue == contentSpec.otherValue) {
            return true;
        }
        return false;
    }
}
