package mf.org.w3c.dom.css;

import mf.org.w3c.dom.DOMException;

public interface CSSValue {
    public static final short CSS_CUSTOM = (short) 3;
    public static final short CSS_INHERIT = (short) 0;
    public static final short CSS_PRIMITIVE_VALUE = (short) 1;
    public static final short CSS_VALUE_LIST = (short) 2;

    String getCssText();

    short getCssValueType();

    void setCssText(String str) throws DOMException;
}
