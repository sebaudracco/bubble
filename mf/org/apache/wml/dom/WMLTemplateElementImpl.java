package mf.org.apache.wml.dom;

import com.bgjd.ici.p030e.C1485h.C1484a;
import mf.org.apache.wml.WMLTemplateElement;

public class WMLTemplateElementImpl extends WMLElementImpl implements WMLTemplateElement {
    private static final long serialVersionUID = 4231732841621131049L;

    public WMLTemplateElementImpl(WMLDocumentImpl owner, String tagName) {
        super(owner, tagName);
    }

    public void setOnTimer(String newValue) {
        setAttribute("ontimer", newValue);
    }

    public String getOnTimer() {
        return getAttribute("ontimer");
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
