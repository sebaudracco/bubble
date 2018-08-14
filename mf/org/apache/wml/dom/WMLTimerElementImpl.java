package mf.org.apache.wml.dom;

import com.bgjd.ici.p030e.C1485h.C1484a;
import com.google.firebase.analytics.FirebaseAnalytics$Param;
import mf.org.apache.wml.WMLTimerElement;

public class WMLTimerElementImpl extends WMLElementImpl implements WMLTimerElement {
    private static final long serialVersionUID = 9055622169756832726L;

    public WMLTimerElementImpl(WMLDocumentImpl owner, String tagName) {
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

    public void setId(String newValue) {
        setAttribute("id", newValue);
    }

    public String getId() {
        return getAttribute("id");
    }

    public void setName(String newValue) {
        setAttribute("name", newValue);
    }

    public String getName() {
        return getAttribute("name");
    }
}
