package mf.org.apache.wml.dom;

import com.bgjd.ici.p030e.C1485h.C1484a;
import mf.org.apache.wml.WMLAnchorElement;

public class WMLAnchorElementImpl extends WMLElementImpl implements WMLAnchorElement {
    private static final long serialVersionUID = 5720492496046133176L;

    public WMLAnchorElementImpl(WMLDocumentImpl owner, String tagName) {
        super(owner, tagName);
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
}
