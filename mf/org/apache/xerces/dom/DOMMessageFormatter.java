package mf.org.apache.xerces.dom;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class DOMMessageFormatter {
    public static final String DOM_DOMAIN = "http://www.w3.org/dom/DOMTR";
    public static final String SERIALIZER_DOMAIN = "http://apache.org/xml/serializer";
    public static final String XML_DOMAIN = "http://www.w3.org/TR/1998/REC-xml-19980210";
    private static ResourceBundle domResourceBundle = null;
    private static Locale locale = null;
    private static ResourceBundle serResourceBundle = null;
    private static ResourceBundle xmlResourceBundle = null;

    DOMMessageFormatter() {
        locale = Locale.getDefault();
    }

    public static String formatMessage(String domain, String key, Object[] arguments) throws MissingResourceException {
        ResourceBundle resourceBundle = getResourceBundle(domain);
        if (resourceBundle == null) {
            init();
            resourceBundle = getResourceBundle(domain);
            if (resourceBundle == null) {
                throw new MissingResourceException("Unknown domain" + domain, null, key);
            }
        }
        try {
            String msg = new StringBuilder(String.valueOf(key)).append(": ").append(resourceBundle.getString(key)).toString();
            if (arguments != null) {
                try {
                    msg = MessageFormat.format(msg, arguments);
                } catch (Exception e) {
                    msg = resourceBundle.getString("FormatFailed") + " " + resourceBundle.getString(key);
                }
            }
            if (msg == null) {
                msg = key;
                if (arguments.length > 0) {
                    StringBuffer str = new StringBuffer(msg);
                    str.append('?');
                    for (int i = 0; i < arguments.length; i++) {
                        if (i > 0) {
                            str.append('&');
                        }
                        str.append(String.valueOf(arguments[i]));
                    }
                }
            }
            return msg;
        } catch (MissingResourceException e2) {
            throw new MissingResourceException(key, resourceBundle.getString("BadMessageKey"), key);
        }
    }

    static ResourceBundle getResourceBundle(String domain) {
        if (domain == DOM_DOMAIN || domain.equals(DOM_DOMAIN)) {
            return domResourceBundle;
        }
        if (domain == "http://www.w3.org/TR/1998/REC-xml-19980210" || domain.equals("http://www.w3.org/TR/1998/REC-xml-19980210")) {
            return xmlResourceBundle;
        }
        if (domain == SERIALIZER_DOMAIN || domain.equals(SERIALIZER_DOMAIN)) {
            return serResourceBundle;
        }
        return null;
    }

    public static void init() {
        Locale _locale = locale;
        if (_locale == null) {
            _locale = Locale.getDefault();
        }
        domResourceBundle = ResourceBundle.getBundle("mf.org.apache.xerces.impl.msg.DOMMessages", _locale);
        serResourceBundle = ResourceBundle.getBundle("mf.org.apache.xerces.impl.msg.XMLSerializerMessages", _locale);
        xmlResourceBundle = ResourceBundle.getBundle("mf.org.apache.xerces.impl.msg.XMLMessages", _locale);
    }

    public static void setLocale(Locale dlocale) {
        locale = dlocale;
    }
}
