package mf.org.apache.html.dom;

import com.bgjd.ici.p025b.C1408j.C1404b;
import com.google.firebase.analytics.FirebaseAnalytics$Param;
import mf.org.w3c.dom.html.HTMLInputElement;

public class HTMLInputElementImpl extends HTMLElementImpl implements HTMLInputElement, HTMLFormControl {
    private static final long serialVersionUID = 640139325394332007L;

    public String getDefaultValue() {
        return getAttribute("defaultValue");
    }

    public void setDefaultValue(String defaultValue) {
        setAttribute("defaultValue", defaultValue);
    }

    public boolean getDefaultChecked() {
        return getBinary("defaultChecked");
    }

    public void setDefaultChecked(boolean defaultChecked) {
        setAttribute("defaultChecked", defaultChecked);
    }

    public String getAccept() {
        return getAttribute(C1404b.f2108L);
    }

    public void setAccept(String accept) {
        setAttribute(C1404b.f2108L, accept);
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

    public String getAlign() {
        return capitalize(getAttribute("align"));
    }

    public void setAlign(String align) {
        setAttribute("align", align);
    }

    public String getAlt() {
        return getAttribute("alt");
    }

    public void setAlt(String alt) {
        setAttribute("alt", alt);
    }

    public boolean getChecked() {
        return getBinary("checked");
    }

    public void setChecked(boolean checked) {
        setAttribute("checked", checked);
    }

    public boolean getDisabled() {
        return getBinary("disabled");
    }

    public void setDisabled(boolean disabled) {
        setAttribute("disabled", disabled);
    }

    public int getMaxLength() {
        return getInteger(getAttribute("maxlength"));
    }

    public void setMaxLength(int maxLength) {
        setAttribute("maxlength", String.valueOf(maxLength));
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

    public String getSize() {
        return getAttribute("size");
    }

    public void setSize(String size) {
        setAttribute("size", size);
    }

    public String getSrc() {
        return getAttribute("src");
    }

    public void setSrc(String src) {
        setAttribute("src", src);
    }

    public int getTabIndex() {
        try {
            return Integer.parseInt(getAttribute("tabindex"));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public void setTabIndex(int tabIndex) {
        setAttribute("tabindex", String.valueOf(tabIndex));
    }

    public String getType() {
        return getAttribute("type");
    }

    public String getUseMap() {
        return getAttribute("useMap");
    }

    public void setUseMap(String useMap) {
        setAttribute("useMap", useMap);
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

    public void click() {
    }

    public HTMLInputElementImpl(HTMLDocumentImpl owner, String name) {
        super(owner, name);
    }
}
