package mf.org.apache.wml.dom;

import com.bgjd.ici.p030e.C1485h.C1484a;
import mf.org.apache.wml.WMLOneventElement;

public class WMLOneventElementImpl extends WMLElementImpl implements WMLOneventElement {
    private static final long serialVersionUID = -4279215241146570871L;

    public WMLOneventElementImpl(WMLDocumentImpl owner, String tagName) {
        super(owner, tagName);
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

    public void setType(String newValue) {
        setAttribute("type", newValue);
    }

    public String getType() {
        return getAttribute("type");
    }
}
