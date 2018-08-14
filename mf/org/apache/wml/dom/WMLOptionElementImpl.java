package mf.org.apache.wml.dom;

import com.bgjd.ici.p030e.C1485h.C1484a;
import com.google.firebase.analytics.FirebaseAnalytics$Param;
import mf.org.apache.wml.WMLOptionElement;

public class WMLOptionElementImpl extends WMLElementImpl implements WMLOptionElement {
    private static final long serialVersionUID = -3432299264888771937L;

    public WMLOptionElementImpl(WMLDocumentImpl owner, String tagName) {
        super(owner, tagName);
    }

    public void setValue(String newValue) {
        setAttribute(FirebaseAnalytics$Param.VALUE, newValue);
    }

    public String getValue() {
        return getAttribute(FirebaseAnalytics$Param.VALUE);
    }

    public void setClassName(String newValue) {
        setAttribute(C1484a.f2398e, newValue);
    }

    public String getClassName() {
        return getAttribute(C1484a.f2398e);
    }

    public void setXmlLang(String newValue) {
        setAttribute("xml:lang", newValue);
    }

    public String getXmlLang() {
        return getAttribute("xml:lang");
    }

    public void setTitle(String newValue) {
        setAttribute("title", newValue);
    }

    public String getTitle() {
        return getAttribute("title");
    }

    public void setId(String newValue) {
        setAttribute("id", newValue);
    }

    public String getId() {
        return getAttribute("id");
    }

    public void setOnPick(String newValue) {
        setAttribute("onpick", newValue);
    }

    public String getOnPick() {
        return getAttribute("onpick");
    }
}
