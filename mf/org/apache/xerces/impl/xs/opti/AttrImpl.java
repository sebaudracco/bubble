package mf.org.apache.xerces.impl.xs.opti;

import mf.org.w3c.dom.Attr;
import mf.org.w3c.dom.DOMException;
import mf.org.w3c.dom.Document;
import mf.org.w3c.dom.Element;
import mf.org.w3c.dom.TypeInfo;

public class AttrImpl extends NodeImpl implements Attr {
    Element element;
    String value;

    public AttrImpl() {
        this.nodeType = (short) 2;
    }

    public AttrImpl(Element element, String prefix, String localpart, String rawname, String uri, String value) {
        super(prefix, localpart, rawname, uri, (short) 2);
        this.element = element;
        this.value = value;
    }

    public String getName() {
        return this.rawname;
    }

    public boolean getSpecified() {
        return true;
    }

    public String getValue() {
        return this.value;
    }

    public String getNodeValue() {
        return getValue();
    }

    public Element getOwnerElement() {
        return this.element;
    }

    public Document getOwnerDocument() {
        return this.element.getOwnerDocument();
    }

    public void setValue(String value) throws DOMException {
        this.value = value;
    }

    public boolean isId() {
        return false;
    }

    public TypeInfo getSchemaTypeInfo() {
        return null;
    }

    public String toString() {
        return getName() + "=" + "\"" + getValue() + "\"";
    }
}
