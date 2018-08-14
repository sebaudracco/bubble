package mf.org.apache.xml.serialize;

import android.support.v4.internal.view.SupportMenu;
import cz.msebera.android.httpclient.protocol.HTTP;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.util.Locale;
import mf.org.apache.xerces.dom.DOMMessageFormatter;

public final class HTMLdtd {
    private static final int ALLOWED_HEAD = 32;
    private static final int CLOSE_DD_DT = 128;
    private static final int CLOSE_P = 64;
    private static final int CLOSE_SELF = 256;
    private static final int CLOSE_TABLE = 512;
    private static final int CLOSE_TH_TD = 16384;
    private static final int ELEM_CONTENT = 2;
    private static final int EMPTY = 17;
    private static final String ENTITIES_RESOURCE = "HTMLEntities.res";
    public static final String HTMLPublicId = "-//W3C//DTD HTML 4.01//EN";
    public static final String HTMLSystemId = "http://www.w3.org/TR/html4/strict.dtd";
    private static final int ONLY_OPENING = 1;
    private static final int OPT_CLOSING = 8;
    private static final int PRESERVE = 4;
    public static final String XHTMLPublicId = "-//W3C//DTD XHTML 1.0 Strict//EN";
    public static final String XHTMLSystemId = "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd";
    private static Hashtable _boolAttrs = new Hashtable();
    private static Hashtable _byChar;
    private static Hashtable _byName;
    private static Hashtable _elemDefs = new Hashtable();

    public static boolean isEmptyTag(String tagName) {
        return isElement(tagName, 17);
    }

    public static boolean isElementContent(String tagName) {
        return isElement(tagName, 2);
    }

    public static boolean isPreserveSpace(String tagName) {
        return isElement(tagName, 4);
    }

    public static boolean isOptionalClosing(String tagName) {
        return isElement(tagName, 8);
    }

    public static boolean isOnlyOpening(String tagName) {
        return isElement(tagName, 1);
    }

    public static boolean isClosing(String tagName, String openTag) {
        if (openTag.equalsIgnoreCase("HEAD")) {
            if (isElement(tagName, 32)) {
                return false;
            }
            return true;
        } else if (openTag.equalsIgnoreCase("P")) {
            return isElement(tagName, 64);
        } else {
            if (openTag.equalsIgnoreCase("DT") || openTag.equalsIgnoreCase("DD")) {
                return isElement(tagName, 128);
            }
            if (openTag.equalsIgnoreCase("LI") || openTag.equalsIgnoreCase("OPTION")) {
                return isElement(tagName, 256);
            }
            if (openTag.equalsIgnoreCase("THEAD") || openTag.equalsIgnoreCase("TFOOT") || openTag.equalsIgnoreCase("TBODY") || openTag.equalsIgnoreCase("TR") || openTag.equalsIgnoreCase("COLGROUP")) {
                return isElement(tagName, 512);
            }
            if (openTag.equalsIgnoreCase("TH") || openTag.equalsIgnoreCase("TD")) {
                return isElement(tagName, 16384);
            }
            return false;
        }
    }

    public static boolean isURI(String tagName, String attrName) {
        return attrName.equalsIgnoreCase("href") || attrName.equalsIgnoreCase("src");
    }

    public static boolean isBoolean(String tagName, String attrName) {
        String[] attrNames = (String[]) _boolAttrs.get(tagName.toUpperCase(Locale.ENGLISH));
        if (attrNames == null) {
            return false;
        }
        for (String equalsIgnoreCase : attrNames) {
            if (equalsIgnoreCase.equalsIgnoreCase(attrName)) {
                return true;
            }
        }
        return false;
    }

    public static int charFromName(String name) {
        initialize();
        Object value = _byName.get(name);
        if (value == null || !(value instanceof Integer)) {
            return -1;
        }
        return ((Integer) value).intValue();
    }

    public static String fromChar(int value) {
        if (value > SupportMenu.USER_MASK) {
            return null;
        }
        initialize();
        return (String) _byChar.get(new Integer(value));
    }

    private static void initialize() {
        Exception except;
        Throwable th;
        InputStream is = null;
        if (_byName == null) {
            try {
                _byName = new Hashtable();
                _byChar = new Hashtable();
                is = HTMLdtd.class.getResourceAsStream(ENTITIES_RESOURCE);
                if (is == null) {
                    throw new RuntimeException(DOMMessageFormatter.formatMessage(DOMMessageFormatter.SERIALIZER_DOMAIN, "ResourceNotFound", new Object[]{ENTITIES_RESOURCE}));
                }
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, HTTP.ASCII));
                BufferedReader bufferedReader;
                try {
                    String line = reader.readLine();
                    while (line != null) {
                        if (line.length() == 0 || line.charAt(0) == '#') {
                            line = reader.readLine();
                        } else {
                            int index = line.indexOf(32);
                            if (index > 1) {
                                String name = line.substring(0, index);
                                index++;
                                if (index < line.length()) {
                                    String value = line.substring(index);
                                    index = value.indexOf(32);
                                    if (index > 0) {
                                        value = value.substring(0, index);
                                    }
                                    defineEntity(name, (char) Integer.parseInt(value));
                                }
                            }
                            line = reader.readLine();
                        }
                    }
                    is.close();
                    if (is != null) {
                        try {
                            is.close();
                        } catch (Exception e) {
                        }
                    }
                    bufferedReader = reader;
                } catch (Exception e2) {
                    except = e2;
                    bufferedReader = reader;
                } catch (Throwable th2) {
                    th = th2;
                    bufferedReader = reader;
                }
            } catch (Exception e3) {
                except = e3;
                try {
                    throw new RuntimeException(DOMMessageFormatter.formatMessage(DOMMessageFormatter.SERIALIZER_DOMAIN, "ResourceNotLoaded", new Object[]{ENTITIES_RESOURCE, except.toString()}));
                } catch (Throwable th3) {
                    th = th3;
                    if (is != null) {
                        try {
                            is.close();
                        } catch (Exception e4) {
                        }
                    }
                    throw th;
                }
            }
        }
    }

    private static void defineEntity(String name, char value) {
        if (_byName.get(name) == null) {
            _byName.put(name, new Integer(value));
            _byChar.put(new Integer(value), name);
        }
    }

    private static void defineElement(String name, int flags) {
        _elemDefs.put(name, new Integer(flags));
    }

    private static void defineBoolean(String tagName, String attrName) {
        defineBoolean(tagName, new String[]{attrName});
    }

    private static void defineBoolean(String tagName, String[] attrNames) {
        _boolAttrs.put(tagName, attrNames);
    }

    private static boolean isElement(String name, int flag) {
        Integer flags = (Integer) _elemDefs.get(name.toUpperCase(Locale.ENGLISH));
        if (flags != null && (flags.intValue() & flag) == flag) {
            return true;
        }
        return false;
    }

    static {
        defineElement("ADDRESS", 64);
        defineElement("AREA", 17);
        defineElement("BASE", 49);
        defineElement("BASEFONT", 17);
        defineElement("BLOCKQUOTE", 64);
        defineElement("BODY", 8);
        defineElement("BR", 17);
        defineElement("COL", 17);
        defineElement("COLGROUP", 522);
        defineElement("DD", 137);
        defineElement("DIV", 64);
        defineElement("DL", 66);
        defineElement("DT", 137);
        defineElement("FIELDSET", 64);
        defineElement("FORM", 64);
        defineElement("FRAME", 25);
        defineElement("H1", 64);
        defineElement("H2", 64);
        defineElement("H3", 64);
        defineElement("H4", 64);
        defineElement("H5", 64);
        defineElement("H6", 64);
        defineElement("HEAD", 10);
        defineElement("HR", 81);
        defineElement("HTML", 10);
        defineElement("IMG", 17);
        defineElement("INPUT", 17);
        defineElement("ISINDEX", 49);
        defineElement("LI", 265);
        defineElement("LINK", 49);
        defineElement("MAP", 32);
        defineElement("META", 49);
        defineElement("OL", 66);
        defineElement("OPTGROUP", 2);
        defineElement("OPTION", 265);
        defineElement("P", 328);
        defineElement("PARAM", 17);
        defineElement("PRE", 68);
        defineElement("SCRIPT", 36);
        defineElement("NOSCRIPT", 36);
        defineElement("SELECT", 2);
        defineElement("STYLE", 36);
        defineElement("TABLE", 66);
        defineElement("TBODY", 522);
        defineElement("TD", 16392);
        defineElement("TEXTAREA", 4);
        defineElement("TFOOT", 522);
        defineElement("TH", 16392);
        defineElement("THEAD", 522);
        defineElement("TITLE", 32);
        defineElement("TR", 522);
        defineElement("UL", 66);
        defineBoolean("AREA", "href");
        defineBoolean("BUTTON", "disabled");
        defineBoolean("DIR", "compact");
        defineBoolean("DL", "compact");
        defineBoolean("FRAME", "noresize");
        defineBoolean("HR", "noshade");
        defineBoolean("IMAGE", "ismap");
        defineBoolean("INPUT", new String[]{"defaultchecked", "checked", "readonly", "disabled"});
        defineBoolean("LINK", "link");
        defineBoolean("MENU", "compact");
        defineBoolean("OBJECT", "declare");
        defineBoolean("OL", "compact");
        defineBoolean("OPTGROUP", "disabled");
        defineBoolean("OPTION", new String[]{"default-selected", "selected", "disabled"});
        defineBoolean("SCRIPT", "defer");
        defineBoolean("SELECT", new String[]{"multiple", "disabled"});
        defineBoolean("STYLE", "disabled");
        defineBoolean("TD", "nowrap");
        defineBoolean("TH", "nowrap");
        defineBoolean("TEXTAREA", new String[]{"disabled", "readonly"});
        defineBoolean("UL", "compact");
        initialize();
    }
}
