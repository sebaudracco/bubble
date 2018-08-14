package mf.org.apache.xerces.impl.dv;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class DatatypeException extends Exception {
    static final long serialVersionUID = 1940805832730465578L;
    protected final Object[] args;
    protected final String key;

    public DatatypeException(String key, Object[] args) {
        super(key);
        this.key = key;
        this.args = args;
    }

    public String getKey() {
        return this.key;
    }

    public Object[] getArgs() {
        return this.args;
    }

    public String getMessage() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("mf.org.apache.xerces.impl.msg.XMLSchemaMessages");
        if (resourceBundle == null) {
            throw new MissingResourceException("Property file not found!", "mf.org.apache.xerces.impl.msg.XMLSchemaMessages", this.key);
        }
        String msg = resourceBundle.getString(this.key);
        if (msg == null) {
            throw new MissingResourceException(resourceBundle.getString("BadMessageKey"), "mf.org.apache.xerces.impl.msg.XMLSchemaMessages", this.key);
        } else if (this.args == null) {
            return msg;
        } else {
            try {
                return MessageFormat.format(msg, this.args);
            } catch (Exception e) {
                return resourceBundle.getString("FormatFailed") + " " + resourceBundle.getString(this.key);
            }
        }
    }
}
