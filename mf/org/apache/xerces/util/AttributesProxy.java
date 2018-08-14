package mf.org.apache.xerces.util;

import mf.org.apache.xerces.impl.Constants;
import mf.org.apache.xerces.xni.XMLAttributes;
import org.xml.sax.AttributeList;
import org.xml.sax.ext.Attributes2;

public final class AttributesProxy implements AttributeList, Attributes2 {
    private XMLAttributes fAttributes;

    public AttributesProxy(XMLAttributes attributes) {
        this.fAttributes = attributes;
    }

    public void setAttributes(XMLAttributes attributes) {
        this.fAttributes = attributes;
    }

    public XMLAttributes getAttributes() {
        return this.fAttributes;
    }

    public int getLength() {
        return this.fAttributes.getLength();
    }

    public String getQName(int index) {
        return this.fAttributes.getQName(index);
    }

    public String getURI(int index) {
        String uri = this.fAttributes.getURI(index);
        return uri != null ? uri : XMLSymbols.EMPTY_STRING;
    }

    public String getLocalName(int index) {
        return this.fAttributes.getLocalName(index);
    }

    public String getType(int i) {
        return this.fAttributes.getType(i);
    }

    public String getType(String name) {
        return this.fAttributes.getType(name);
    }

    public String getType(String uri, String localName) {
        if (uri.equals(XMLSymbols.EMPTY_STRING)) {
            return this.fAttributes.getType(null, localName);
        }
        return this.fAttributes.getType(uri, localName);
    }

    public String getValue(int i) {
        return this.fAttributes.getValue(i);
    }

    public String getValue(String name) {
        return this.fAttributes.getValue(name);
    }

    public String getValue(String uri, String localName) {
        if (uri.equals(XMLSymbols.EMPTY_STRING)) {
            return this.fAttributes.getValue(null, localName);
        }
        return this.fAttributes.getValue(uri, localName);
    }

    public int getIndex(String qName) {
        return this.fAttributes.getIndex(qName);
    }

    public int getIndex(String uri, String localPart) {
        if (uri.equals(XMLSymbols.EMPTY_STRING)) {
            return this.fAttributes.getIndex(null, localPart);
        }
        return this.fAttributes.getIndex(uri, localPart);
    }

    public boolean isDeclared(int index) {
        if (index >= 0 && index < this.fAttributes.getLength()) {
            return Boolean.TRUE.equals(this.fAttributes.getAugmentations(index).getItem(Constants.ATTRIBUTE_DECLARED));
        }
        throw new ArrayIndexOutOfBoundsException(index);
    }

    public boolean isDeclared(String qName) {
        int index = getIndex(qName);
        if (index != -1) {
            return Boolean.TRUE.equals(this.fAttributes.getAugmentations(index).getItem(Constants.ATTRIBUTE_DECLARED));
        }
        throw new IllegalArgumentException(qName);
    }

    public boolean isDeclared(String uri, String localName) {
        int index = getIndex(uri, localName);
        if (index != -1) {
            return Boolean.TRUE.equals(this.fAttributes.getAugmentations(index).getItem(Constants.ATTRIBUTE_DECLARED));
        }
        throw new IllegalArgumentException(localName);
    }

    public boolean isSpecified(int index) {
        if (index >= 0 && index < this.fAttributes.getLength()) {
            return this.fAttributes.isSpecified(index);
        }
        throw new ArrayIndexOutOfBoundsException(index);
    }

    public boolean isSpecified(String qName) {
        int index = getIndex(qName);
        if (index != -1) {
            return this.fAttributes.isSpecified(index);
        }
        throw new IllegalArgumentException(qName);
    }

    public boolean isSpecified(String uri, String localName) {
        int index = getIndex(uri, localName);
        if (index != -1) {
            return this.fAttributes.isSpecified(index);
        }
        throw new IllegalArgumentException(localName);
    }

    public String getName(int i) {
        return this.fAttributes.getQName(i);
    }
}
