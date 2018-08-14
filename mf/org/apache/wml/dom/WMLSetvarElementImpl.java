package mf.org.apache.wml.dom;

import com.bgjd.ici.p030e.C1485h.C1484a;
import com.google.firebase.analytics.FirebaseAnalytics$Param;
import mf.org.apache.wml.WMLSetvarElement;

public class WMLSetvarElementImpl extends WMLElementImpl implements WMLSetvarElement {
    private static final long serialVersionUID = -1944519734782236471L;

    public WMLSetvarElementImpl(WMLDocumentImpl owner, String tagName) {
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
