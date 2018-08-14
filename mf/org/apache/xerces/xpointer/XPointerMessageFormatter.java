package mf.org.apache.xerces.xpointer;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import mf.org.apache.xerces.util.MessageFormatter;

final class XPointerMessageFormatter implements MessageFormatter {
    public static final String XPOINTER_DOMAIN = "http://www.w3.org/TR/XPTR";
    private Locale fLocale = null;
    private ResourceBundle fResourceBundle = null;

    XPointerMessageFormatter() {
    }

    public String formatMessage(Locale locale, String key, Object[] arguments) throws MissingResourceException {
        if (locale == null) {
            locale = Locale.getDefault();
        }
        if (locale != this.fLocale) {
            this.fResourceBundle = ResourceBundle.getBundle("mf.org.apache.xerces.impl.msg.XPointerMessages", locale);
            this.fLocale = locale;
        }
        String msg = this.fResourceBundle.getString(key);
        if (arguments != null) {
            try {
                msg = MessageFormat.format(msg, arguments);
            } catch (Exception e) {
                msg = new StringBuilder(String.valueOf(this.fResourceBundle.getString("FormatFailed"))).append(" ").append(this.fResourceBundle.getString(key)).toString();
            }
        }
        if (msg != null) {
            return msg;
        }
        throw new MissingResourceException(this.fResourceBundle.getString("BadMessageKey"), "mf.org.apache.xerces.impl.msg.XPointerMessages", key);
    }
}
