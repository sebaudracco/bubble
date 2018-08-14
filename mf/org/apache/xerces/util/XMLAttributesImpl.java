package mf.org.apache.xerces.util;

import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import mf.org.apache.xerces.xni.Augmentations;
import mf.org.apache.xerces.xni.QName;
import mf.org.apache.xerces.xni.XMLAttributes;

public class XMLAttributesImpl implements XMLAttributes {
    protected static final int SIZE_LIMIT = 20;
    protected static final int TABLE_SIZE = 101;
    protected Attribute[] fAttributeTableView;
    protected int[] fAttributeTableViewChainState;
    protected Attribute[] fAttributes;
    protected boolean fIsTableViewConsistent;
    protected int fLargeCount;
    protected int fLength;
    protected boolean fNamespaces;
    protected int fTableViewBuckets;

    static class Attribute {
        public Augmentations augs = new AugmentationsImpl();
        public final QName name = new QName();
        public Attribute next;
        public String nonNormalizedValue;
        public boolean specified;
        public String type;
        public String value;

        Attribute() {
        }
    }

    public XMLAttributesImpl() {
        this(101);
    }

    public XMLAttributesImpl(int tableSize) {
        this.fNamespaces = true;
        this.fLargeCount = 1;
        this.fAttributes = new Attribute[4];
        this.fTableViewBuckets = tableSize;
        for (int i = 0; i < this.fAttributes.length; i++) {
            this.fAttributes[i] = new Attribute();
        }
    }

    public void setNamespaces(boolean namespaces) {
        this.fNamespaces = namespaces;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int addAttribute(mf.org.apache.xerces.xni.QName r12, java.lang.String r13, java.lang.String r14) {
        /*
        r11 = this;
        r10 = 20;
        r9 = -1;
        r8 = 0;
        r6 = r11.fLength;
        if (r6 >= r10) goto L_0x006a;
    L_0x0008:
        r6 = r12.uri;
        if (r6 == 0) goto L_0x0059;
    L_0x000c:
        r6 = r12.uri;
        r6 = r6.length();
        if (r6 == 0) goto L_0x0059;
    L_0x0014:
        r6 = r12.uri;
        r7 = r12.localpart;
        r5 = r11.getIndexFast(r6, r7);
    L_0x001c:
        if (r5 != r9) goto L_0x0042;
    L_0x001e:
        r5 = r11.fLength;
        r6 = r11.fLength;
        r7 = r6 + 1;
        r11.fLength = r7;
        r7 = r11.fAttributes;
        r7 = r7.length;
        if (r6 != r7) goto L_0x0042;
    L_0x002b:
        r6 = r11.fAttributes;
        r6 = r6.length;
        r6 = r6 + 4;
        r1 = new mf.org.apache.xerces.util.XMLAttributesImpl.Attribute[r6];
        r6 = r11.fAttributes;
        r7 = r11.fAttributes;
        r7 = r7.length;
        java.lang.System.arraycopy(r6, r8, r1, r8, r7);
        r6 = r11.fAttributes;
        r4 = r6.length;
    L_0x003d:
        r6 = r1.length;
        if (r4 < r6) goto L_0x0060;
    L_0x0040:
        r11.fAttributes = r1;
    L_0x0042:
        r6 = r11.fAttributes;
        r0 = r6[r5];
        r6 = r0.name;
        r6.setValues(r12);
        r0.type = r13;
        r0.value = r14;
        r0.nonNormalizedValue = r14;
        r0.specified = r8;
        r6 = r0.augs;
        r6.removeAllItems();
        return r5;
    L_0x0059:
        r6 = r12.rawname;
        r5 = r11.getIndexFast(r6);
        goto L_0x001c;
    L_0x0060:
        r6 = new mf.org.apache.xerces.util.XMLAttributesImpl$Attribute;
        r6.<init>();
        r1[r4] = r6;
        r4 = r4 + 1;
        goto L_0x003d;
    L_0x006a:
        r6 = r12.uri;
        if (r6 == 0) goto L_0x0080;
    L_0x006e:
        r6 = r12.uri;
        r6 = r6.length();
        if (r6 == 0) goto L_0x0080;
    L_0x0076:
        r6 = r12.uri;
        r7 = r12.localpart;
        r5 = r11.getIndexFast(r6, r7);
        if (r5 != r9) goto L_0x0042;
    L_0x0080:
        r6 = r11.fIsTableViewConsistent;
        if (r6 == 0) goto L_0x0088;
    L_0x0084:
        r6 = r11.fLength;
        if (r6 != r10) goto L_0x008e;
    L_0x0088:
        r11.prepareAndPopulateTableView();
        r6 = 1;
        r11.fIsTableViewConsistent = r6;
    L_0x008e:
        r6 = r12.rawname;
        r2 = r11.getTableViewBucket(r6);
        r6 = r11.fAttributeTableViewChainState;
        r6 = r6[r2];
        r7 = r11.fLargeCount;
        if (r6 == r7) goto L_0x00e1;
    L_0x009c:
        r5 = r11.fLength;
        r6 = r11.fLength;
        r7 = r6 + 1;
        r11.fLength = r7;
        r7 = r11.fAttributes;
        r7 = r7.length;
        if (r6 != r7) goto L_0x00c0;
    L_0x00a9:
        r6 = r11.fAttributes;
        r6 = r6.length;
        r6 = r6 << 1;
        r1 = new mf.org.apache.xerces.util.XMLAttributesImpl.Attribute[r6];
        r6 = r11.fAttributes;
        r7 = r11.fAttributes;
        r7 = r7.length;
        java.lang.System.arraycopy(r6, r8, r1, r8, r7);
        r6 = r11.fAttributes;
        r4 = r6.length;
    L_0x00bb:
        r6 = r1.length;
        if (r4 < r6) goto L_0x00d7;
    L_0x00be:
        r11.fAttributes = r1;
    L_0x00c0:
        r6 = r11.fAttributeTableViewChainState;
        r7 = r11.fLargeCount;
        r6[r2] = r7;
        r6 = r11.fAttributes;
        r6 = r6[r5];
        r7 = 0;
        r6.next = r7;
        r6 = r11.fAttributeTableView;
        r7 = r11.fAttributes;
        r7 = r7[r5];
        r6[r2] = r7;
        goto L_0x0042;
    L_0x00d7:
        r6 = new mf.org.apache.xerces.util.XMLAttributesImpl$Attribute;
        r6.<init>();
        r1[r4] = r6;
        r4 = r4 + 1;
        goto L_0x00bb;
    L_0x00e1:
        r6 = r11.fAttributeTableView;
        r3 = r6[r2];
    L_0x00e5:
        if (r3 != 0) goto L_0x0121;
    L_0x00e7:
        if (r3 != 0) goto L_0x0136;
    L_0x00e9:
        r5 = r11.fLength;
        r6 = r11.fLength;
        r7 = r6 + 1;
        r11.fLength = r7;
        r7 = r11.fAttributes;
        r7 = r7.length;
        if (r6 != r7) goto L_0x010d;
    L_0x00f6:
        r6 = r11.fAttributes;
        r6 = r6.length;
        r6 = r6 << 1;
        r1 = new mf.org.apache.xerces.util.XMLAttributesImpl.Attribute[r6];
        r6 = r11.fAttributes;
        r7 = r11.fAttributes;
        r7 = r7.length;
        java.lang.System.arraycopy(r6, r8, r1, r8, r7);
        r6 = r11.fAttributes;
        r4 = r6.length;
    L_0x0108:
        r6 = r1.length;
        if (r4 < r6) goto L_0x012c;
    L_0x010b:
        r11.fAttributes = r1;
    L_0x010d:
        r6 = r11.fAttributes;
        r6 = r6[r5];
        r7 = r11.fAttributeTableView;
        r7 = r7[r2];
        r6.next = r7;
        r6 = r11.fAttributeTableView;
        r7 = r11.fAttributes;
        r7 = r7[r5];
        r6[r2] = r7;
        goto L_0x0042;
    L_0x0121:
        r6 = r3.name;
        r6 = r6.rawname;
        r7 = r12.rawname;
        if (r6 == r7) goto L_0x00e7;
    L_0x0129:
        r3 = r3.next;
        goto L_0x00e5;
    L_0x012c:
        r6 = new mf.org.apache.xerces.util.XMLAttributesImpl$Attribute;
        r6.<init>();
        r1[r4] = r6;
        r4 = r4 + 1;
        goto L_0x0108;
    L_0x0136:
        r6 = r12.rawname;
        r5 = r11.getIndexFast(r6);
        goto L_0x0042;
        */
        throw new UnsupportedOperationException("Method not decompiled: mf.org.apache.xerces.util.XMLAttributesImpl.addAttribute(mf.org.apache.xerces.xni.QName, java.lang.String, java.lang.String):int");
    }

    public void removeAllAttributes() {
        this.fLength = 0;
    }

    public void removeAttributeAt(int attrIndex) {
        this.fIsTableViewConsistent = false;
        if (attrIndex < this.fLength - 1) {
            Attribute removedAttr = this.fAttributes[attrIndex];
            System.arraycopy(this.fAttributes, attrIndex + 1, this.fAttributes, attrIndex, (this.fLength - attrIndex) - 1);
            this.fAttributes[this.fLength - 1] = removedAttr;
        }
        this.fLength--;
    }

    public void setName(int attrIndex, QName attrName) {
        this.fAttributes[attrIndex].name.setValues(attrName);
    }

    public void getName(int attrIndex, QName attrName) {
        attrName.setValues(this.fAttributes[attrIndex].name);
    }

    public void setType(int attrIndex, String attrType) {
        this.fAttributes[attrIndex].type = attrType;
    }

    public void setValue(int attrIndex, String attrValue) {
        Attribute attribute = this.fAttributes[attrIndex];
        attribute.value = attrValue;
        attribute.nonNormalizedValue = attrValue;
    }

    public void setNonNormalizedValue(int attrIndex, String attrValue) {
        if (attrValue == null) {
            attrValue = this.fAttributes[attrIndex].value;
        }
        this.fAttributes[attrIndex].nonNormalizedValue = attrValue;
    }

    public String getNonNormalizedValue(int attrIndex) {
        return this.fAttributes[attrIndex].nonNormalizedValue;
    }

    public void setSpecified(int attrIndex, boolean specified) {
        this.fAttributes[attrIndex].specified = specified;
    }

    public boolean isSpecified(int attrIndex) {
        return this.fAttributes[attrIndex].specified;
    }

    public int getLength() {
        return this.fLength;
    }

    public String getType(int index) {
        if (index < 0 || index >= this.fLength) {
            return null;
        }
        return getReportableType(this.fAttributes[index].type);
    }

    public String getType(String qname) {
        int index = getIndex(qname);
        return index != -1 ? getReportableType(this.fAttributes[index].type) : null;
    }

    public String getValue(int index) {
        if (index < 0 || index >= this.fLength) {
            return null;
        }
        return this.fAttributes[index].value;
    }

    public String getValue(String qname) {
        int index = getIndex(qname);
        return index != -1 ? this.fAttributes[index].value : null;
    }

    public String getName(int index) {
        if (index < 0 || index >= this.fLength) {
            return null;
        }
        return this.fAttributes[index].name.rawname;
    }

    public int getIndex(String qName) {
        for (int i = 0; i < this.fLength; i++) {
            Attribute attribute = this.fAttributes[i];
            if (attribute.name.rawname != null && attribute.name.rawname.equals(qName)) {
                return i;
            }
        }
        return -1;
    }

    public int getIndex(String uri, String localPart) {
        for (int i = 0; i < this.fLength; i++) {
            Attribute attribute = this.fAttributes[i];
            if (attribute.name.localpart != null && attribute.name.localpart.equals(localPart)) {
                if (uri == attribute.name.uri) {
                    return i;
                }
                if (!(uri == null || attribute.name.uri == null || !attribute.name.uri.equals(uri))) {
                    return i;
                }
            }
        }
        return -1;
    }

    public String getLocalName(int index) {
        if (!this.fNamespaces) {
            return "";
        }
        if (index < 0 || index >= this.fLength) {
            return null;
        }
        return this.fAttributes[index].name.localpart;
    }

    public String getQName(int index) {
        if (index < 0 || index >= this.fLength) {
            return null;
        }
        String rawname = this.fAttributes[index].name.rawname;
        return rawname == null ? "" : rawname;
    }

    public String getType(String uri, String localName) {
        if (!this.fNamespaces) {
            return null;
        }
        int index = getIndex(uri, localName);
        if (index != -1) {
            return getReportableType(this.fAttributes[index].type);
        }
        return null;
    }

    public String getPrefix(int index) {
        if (index < 0 || index >= this.fLength) {
            return null;
        }
        String prefix = this.fAttributes[index].name.prefix;
        return prefix == null ? "" : prefix;
    }

    public String getURI(int index) {
        if (index < 0 || index >= this.fLength) {
            return null;
        }
        return this.fAttributes[index].name.uri;
    }

    public String getValue(String uri, String localName) {
        int index = getIndex(uri, localName);
        return index != -1 ? getValue(index) : null;
    }

    public Augmentations getAugmentations(String uri, String localName) {
        int index = getIndex(uri, localName);
        return index != -1 ? this.fAttributes[index].augs : null;
    }

    public Augmentations getAugmentations(String qName) {
        int index = getIndex(qName);
        return index != -1 ? this.fAttributes[index].augs : null;
    }

    public Augmentations getAugmentations(int attributeIndex) {
        if (attributeIndex < 0 || attributeIndex >= this.fLength) {
            return null;
        }
        return this.fAttributes[attributeIndex].augs;
    }

    public void setAugmentations(int attrIndex, Augmentations augs) {
        this.fAttributes[attrIndex].augs = augs;
    }

    public void setURI(int attrIndex, String uri) {
        this.fAttributes[attrIndex].name.uri = uri;
    }

    public int getIndexFast(String qName) {
        for (int i = 0; i < this.fLength; i++) {
            if (this.fAttributes[i].name.rawname == qName) {
                return i;
            }
        }
        return -1;
    }

    public void addAttributeNS(QName name, String type, String value) {
        int index = this.fLength;
        int i = this.fLength;
        this.fLength = i + 1;
        if (i == this.fAttributes.length) {
            Attribute[] attributes;
            if (this.fLength < 20) {
                attributes = new Attribute[(this.fAttributes.length + 4)];
            } else {
                attributes = new Attribute[(this.fAttributes.length << 1)];
            }
            System.arraycopy(this.fAttributes, 0, attributes, 0, this.fAttributes.length);
            for (int i2 = this.fAttributes.length; i2 < attributes.length; i2++) {
                attributes[i2] = new Attribute();
            }
            this.fAttributes = attributes;
        }
        Attribute attribute = this.fAttributes[index];
        attribute.name.setValues(name);
        attribute.type = type;
        attribute.value = value;
        attribute.nonNormalizedValue = value;
        attribute.specified = false;
        attribute.augs.removeAllItems();
    }

    public QName checkDuplicatesNS() {
        int i;
        if (this.fLength <= 20) {
            for (i = 0; i < this.fLength - 1; i++) {
                Attribute att1 = this.fAttributes[i];
                for (int j = i + 1; j < this.fLength; j++) {
                    Attribute att2 = this.fAttributes[j];
                    if (att1.name.localpart == att2.name.localpart && att1.name.uri == att2.name.uri) {
                        return att2.name;
                    }
                }
            }
            return null;
        }
        this.fIsTableViewConsistent = false;
        prepareTableView();
        for (i = this.fLength - 1; i >= 0; i--) {
            Attribute attr = this.fAttributes[i];
            int bucket = getTableViewBucket(attr.name.localpart, attr.name.uri);
            if (this.fAttributeTableViewChainState[bucket] != this.fLargeCount) {
                this.fAttributeTableViewChainState[bucket] = this.fLargeCount;
                attr.next = null;
                this.fAttributeTableView[bucket] = attr;
            } else {
                Attribute found = this.fAttributeTableView[bucket];
                while (found != null) {
                    if (found.name.localpart == attr.name.localpart && found.name.uri == attr.name.uri) {
                        return attr.name;
                    }
                    found = found.next;
                }
                attr.next = this.fAttributeTableView[bucket];
                this.fAttributeTableView[bucket] = attr;
            }
        }
        return null;
    }

    public int getIndexFast(String uri, String localPart) {
        for (int i = 0; i < this.fLength; i++) {
            Attribute attribute = this.fAttributes[i];
            if (attribute.name.localpart == localPart && attribute.name.uri == uri) {
                return i;
            }
        }
        return -1;
    }

    private String getReportableType(String type) {
        if (type.charAt(0) == '(') {
            return SchemaSymbols.ATTVAL_NMTOKEN;
        }
        return type;
    }

    protected int getTableViewBucket(String qname) {
        return (qname.hashCode() & Integer.MAX_VALUE) % this.fTableViewBuckets;
    }

    protected int getTableViewBucket(String localpart, String uri) {
        if (uri == null) {
            return (localpart.hashCode() & Integer.MAX_VALUE) % this.fTableViewBuckets;
        }
        return ((localpart.hashCode() + uri.hashCode()) & Integer.MAX_VALUE) % this.fTableViewBuckets;
    }

    protected void cleanTableView() {
        int i = this.fLargeCount + 1;
        this.fLargeCount = i;
        if (i < 0) {
            if (this.fAttributeTableViewChainState != null) {
                for (int i2 = this.fTableViewBuckets - 1; i2 >= 0; i2--) {
                    this.fAttributeTableViewChainState[i2] = 0;
                }
            }
            this.fLargeCount = 1;
        }
    }

    protected void prepareTableView() {
        if (this.fAttributeTableView == null) {
            this.fAttributeTableView = new Attribute[this.fTableViewBuckets];
            this.fAttributeTableViewChainState = new int[this.fTableViewBuckets];
            return;
        }
        cleanTableView();
    }

    protected void prepareAndPopulateTableView() {
        prepareTableView();
        for (int i = 0; i < this.fLength; i++) {
            Attribute attr = this.fAttributes[i];
            int bucket = getTableViewBucket(attr.name.rawname);
            if (this.fAttributeTableViewChainState[bucket] != this.fLargeCount) {
                this.fAttributeTableViewChainState[bucket] = this.fLargeCount;
                attr.next = null;
                this.fAttributeTableView[bucket] = attr;
            } else {
                attr.next = this.fAttributeTableView[bucket];
                this.fAttributeTableView[bucket] = attr;
            }
        }
    }
}
