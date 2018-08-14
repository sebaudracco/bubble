package mf.org.apache.xerces.xinclude;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import mf.org.apache.xerces.util.MessageFormatter;

public class XIncludeMessageFormatter implements MessageFormatter {
    public static final String XINCLUDE_DOMAIN = "http://www.w3.org/TR/xinclude";
    private Locale fLocale = null;
    private ResourceBundle fResourceBundle = null;

    public String formatMessage(Locale locale, String key, Object[] arguments) throws MissingResourceException {
        if (locale == null) {
            locale = Locale.getDefault();
        }
        if (locale != this.fLocale) {
            this.fResourceBundle = ResourceBundle.getBundle("mf.org.apache.xerces.impl.msg.XIncludeMessages", locale);
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
        throw new MissingResourceException(this.fResourceBundle.getString("BadMessageKey"), "mf.org.apache.xerces.impl.msg.XIncludeMessages", key);
    }
}
