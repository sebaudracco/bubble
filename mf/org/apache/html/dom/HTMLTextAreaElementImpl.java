package mf.org.apache.html.dom;

import com.google.firebase.analytics.FirebaseAnalytics$Param;
import mf.org.w3c.dom.html.HTMLTextAreaElement;

public class HTMLTextAreaElementImpl extends HTMLElementImpl implements HTMLTextAreaElement, HTMLFormControl {
    private static final long serialVersionUID = -6737778308542678104L;

    public String getDefaultValue() {
        return getAttribute("default-value");
    }

    public void setDefaultValue(String defaultValue) {
        setAttribute("default-value", defaultValue);
    }

    public String getAccessKey() {
        String accessKey = getAttribute("accesskey");
        if (accessKey == null || accessKey.length() <= 1) {
            return accessKey;
        }
        return accessKey.substring(0, 1);
    }

    public void setAccessKey(String accessKey) {
        if (accessKey != null && accessKey.length() > 1) {
            accessKey = accessKey.substring(0, 1);
        }
        setAttribute("accesskey", accessKey);
    }

    public int getCols() {
        return getInteger(getAttribute("cols"));
    }

    public void setCols(int cols) {
        setAttribute("cols", String.valueOf(cols));
    }

    public boolean getDisabled() {
        return getBinary("disabled");
    }

    public void setDisabled(boolean disabled) {
        setAttribute("disabled", disabled);
    }

    public String getName() {
        return getAttribute("name");
    }

    public void setName(String name) {
        setAttribute("name", name);
    }

    public boolean getReadOnly() {
        return getBinary("readonly");
    }

    public void setReadOnly(boolean readOnly) {
        setAttribute("readonly", readOnly);
    }

    public int getRows() {
        return getInteger(getAttribute("rows"));
    }

    public void setRows(int rows) {
        setAttribute("rows", String.valueOf(rows));
    }

    public int getTabIndex() {
        return getInteger(getAttribute("tabindex"));
    }

    public void setTabIndex(int tabIndex) {
        setAttribute("tabindex", String.valueOf(tabIndex));
    }

    public String getType() {
        return getAttribute("type");
    }

    public String getValue() {
        return getAttribute(FirebaseAnalytics$Param.VALUE);
    }

    public void setValue(String value) {
        setAttribute(FirebaseAnalytics$Param.VALUE, value);
    }

    public void blur() {
    }

    public void focus() {
    }

    public void select() {
    }

    public HTMLTextAreaElementImpl(HTMLDocumentImpl owner, String name) {
        super(owner, name);
    }
}
