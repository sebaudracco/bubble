package mf.org.apache.wml.dom;

import com.bgjd.ici.p030e.C1485h.C1484a;
import com.google.firebase.analytics.FirebaseAnalytics$Param;
import mf.org.apache.wml.WMLMetaElement;

public class WMLMetaElementImpl extends WMLElementImpl implements WMLMetaElement {
    private static final long serialVersionUID = -2791663042188681846L;

    public WMLMetaElementImpl(WMLDocumentImpl owner, String tagName) {
        super(owner, tagName);
    }

    public void setForua(boolean newValue) {
        setAttribute("forua", newValue);
    }

    public boolean getForua() {
        return getAttribute("forua", false);
    }

    public void setScheme(String newValue) {
        setAttribute("scheme", newValue);
    }

    public String getScheme() {
        return getAttribute("scheme");
    }

    public void setClassName(String newValue) {
        setAttribute(C1484a.f2398e, newValue);
    }

    public String getClassName() {
        return getAttribute(C1484a.f2398e);
    }

    public void setHttpEquiv(String newValue) {
        setAttribute("http-equiv", newValue);
    }

    public String getHttpEquiv() {
        return getAttribute("http-equiv");
    }

    public void setId(String newValue) {
        setAttribute("id", newValue);
    }

    public String getId() {
        return getAttribute("id");
    }

    public void setContent(String newValue) {
        setAttribute(FirebaseAnalytics$Param.CONTENT, newValue);
    }

    public String getContent() {
        return getAttribute(FirebaseAnalytics$Param.CONTENT);
    }

    public void setName(String newValue) {
        setAttribute("name", newValue);
    }

    public String getName() {
        return getAttribute("name");
    }
}
