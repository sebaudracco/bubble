package mf.org.apache.wml.dom;

import com.bgjd.ici.p030e.C1485h.C1484a;
import cz.msebera.android.httpclient.cookie.ClientCookie;
import mf.org.apache.wml.WMLAccessElement;

public class WMLAccessElementImpl extends WMLElementImpl implements WMLAccessElement {
    private static final long serialVersionUID = -235627181817012806L;

    public WMLAccessElementImpl(WMLDocumentImpl owner, String tagName) {
        super(owner, tagName);
    }

    public void setClassName(String newValue) {
        setAttribute(C1484a.f2398e, newValue);
    }

    public String getClassName() {
        return getAttribute(C1484a.f2398e);
    }

    public void setDomain(String newValue) {
        setAttribute(ClientCookie.DOMAIN_ATTR, newValue);
    }

    public String getDomain() {
        return getAttribute(ClientCookie.DOMAIN_ATTR);
    }

    public void setId(String newValue) {
        setAttribute("id", newValue);
    }

    public String getId() {
        return getAttribute("id");
    }

    public void setPath(String newValue) {
        setAttribute(ClientCookie.PATH_ATTR, newValue);
    }

    public String getPath() {
        return getAttribute(ClientCookie.PATH_ATTR);
    }
}
