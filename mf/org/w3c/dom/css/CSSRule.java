package mf.org.w3c.dom.css;

import mf.org.w3c.dom.DOMException;

public interface CSSRule {
    public static final short CHARSET_RULE = (short) 2;
    public static final short FONT_FACE_RULE = (short) 5;
    public static final short IMPORT_RULE = (short) 3;
    public static final short MEDIA_RULE = (short) 4;
    public static final short PAGE_RULE = (short) 6;
    public static final short STYLE_RULE = (short) 1;
    public static final short UNKNOWN_RULE = (short) 0;

    String getCssText();

    CSSRule getParentRule();

    CSSStyleSheet getParentStyleSheet();

    short getType();

    void setCssText(String str) throws DOMException;
}
