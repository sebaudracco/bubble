package mf.org.apache.xerces.util;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class DatatypeMessageFormatter {
    private static final String BASE_NAME = "mf.org.apache.xerces.impl.msg.DatatypeMessages";

    public static String formatMessage(Locale locale, String key, Object[] arguments) throws MissingResourceException {
        if (locale == null) {
            locale = Locale.getDefault();
        }
        ResourceBundle resourceBundle = ResourceBundle.getBundle(BASE_NAME, locale);
        try {
            String msg = resourceBundle.getString(key);
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
}
