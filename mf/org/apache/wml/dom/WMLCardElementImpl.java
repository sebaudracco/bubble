package mf.org.apache.wml.dom;

import com.bgjd.ici.p030e.C1485h.C1484a;
import mf.org.apache.wml.WMLCardElement;

public class WMLCardElementImpl extends WMLElementImpl implements WMLCardElement {
    private static final long serialVersionUID = -3571126568344328924L;

    public WMLCardElementImpl(WMLDocumentImpl owner, String tagName) {
        super(owner, tagName);
    }

    public void setOnTimer(String newValue) {
        setAttribute("ontimer", newValue);
    }

    public String getOnTimer() {
        return getAttribute("ontimer");
    }

    public void setOrdered(boolean newValue) {
        setAttribute("ordered", newValue);
    }

    public boolean getOrdered() {
        return getAttribute("ordered", true);
    }

    public void setOnEnterBackward(String newValue) {
        setAttribute("onenterbackward", newValue);
    }

    public String getOnEnterBackward() {
        return getAttribute("onenterbackward");
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

    public void setNewContext(boolean newValue) {
        setAttribute("newcontext", newValue);
    }

    public boolean getNewContext() {
        return getAttribute("newcontext", false);
    }

    public void setId(String newValue) {
        setAttribute("id", newValue);
    }

    public String getId() {
        return getAttribute("id");
    }

    public void setOnEnterForward(String newValue) {
        setAttribute("onenterforward", newValue);
    }

    public String getOnEnterForward() {
        return getAttribute("onenterforward");
    }
}
