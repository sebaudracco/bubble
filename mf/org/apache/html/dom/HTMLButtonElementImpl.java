package mf.org.apache.html.dom;

import com.google.firebase.analytics.FirebaseAnalytics$Param;
import mf.org.w3c.dom.html.HTMLButtonElement;

public class HTMLButtonElementImpl extends HTMLElementImpl implements HTMLButtonElement, HTMLFormControl {
    private static final long serialVersionUID = -753685852948076730L;

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
        return capitalize(getAttribute("type"));
    }

    public String getValue() {
        return getAttribute(FirebaseAnalytics$Param.VALUE);
    }

    public void setValue(String value) {
        setAttribute(FirebaseAnalytics$Param.VALUE, value);
    }

    public HTMLButtonElementImpl(HTMLDocumentImpl owner, String name) {
        super(owner, name);
    }
}
