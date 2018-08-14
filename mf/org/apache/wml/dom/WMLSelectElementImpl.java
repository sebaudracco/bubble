package mf.org.apache.wml.dom;

import com.bgjd.ici.p030e.C1485h.C1484a;
import com.google.firebase.analytics.FirebaseAnalytics$Param;
import mf.org.apache.wml.WMLSelectElement;

public class WMLSelectElementImpl extends WMLElementImpl implements WMLSelectElement {
    private static final long serialVersionUID = 6489112443257247261L;

    public WMLSelectElementImpl(WMLDocumentImpl owner, String tagName) {
        super(owner, tagName);
    }

    public void setMultiple(boolean newValue) {
        setAttribute("multiple", newValue);
    }

    public boolean getMultiple() {
        return getAttribute("multiple", false);
    }

    public void setValue(String newValue) {
        setAttribute(FirebaseAnalytics$Param.VALUE, newValue);
    }

    public String getValue() {
        return getAttribute(FirebaseAnalytics$Param.VALUE);
    }

    public void setTabIndex(int newValue) {
        setAttribute("tabindex", newValue);
    }

    public int getTabIndex() {
        return getAttribute("tabindex", 0);
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

    public void setIValue(String newValue) {
        setAttribute("ivalue", newValue);
    }

    public String getIValue() {
        return getAttribute("ivalue");
    }

    public void setId(String newValue) {
        setAttribute("id", newValue);
    }

    public String getId() {
        return getAttribute("id");
    }

    public void setIName(String newValue) {
        setAttribute("iname", newValue);
    }

    public String getIName() {
        return getAttribute("iname");
    }

    public void setName(String newValue) {
        setAttribute("name", newValue);
    }

    public String getName() {
        return getAttribute("name");
    }
}
