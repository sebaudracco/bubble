package mf.org.apache.wml.dom;

import com.bgjd.ici.p030e.C1485h.C1484a;
import mf.org.apache.wml.WMLGoElement;

public class WMLGoElementImpl extends WMLElementImpl implements WMLGoElement {
    private static final long serialVersionUID = -2052250142899797905L;

    public WMLGoElementImpl(WMLDocumentImpl owner, String tagName) {
        super(owner, tagName);
    }

    public void setSendreferer(String newValue) {
        setAttribute("sendreferer", newValue);
    }

    public String getSendreferer() {
        return getAttribute("sendreferer");
    }

    public void setAcceptCharset(String newValue) {
        setAttribute("accept-charset", newValue);
    }

    public String getAcceptCharset() {
        return getAttribute("accept-charset");
    }

    public void setHref(String newValue) {
        setAttribute("href", newValue);
    }

    public String getHref() {
        return getAttribute("href");
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

    public void setMethod(String newValue) {
        setAttribute("method", newValue);
    }

    public String getMethod() {
        return getAttribute("method");
    }
}
