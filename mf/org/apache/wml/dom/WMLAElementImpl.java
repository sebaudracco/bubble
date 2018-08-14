package mf.org.apache.wml.dom;

import com.bgjd.ici.p030e.C1485h.C1484a;
import mf.org.apache.wml.WMLAElement;

public class WMLAElementImpl extends WMLElementImpl implements WMLAElement {
    private static final long serialVersionUID = 2628169803370301255L;

    public WMLAElementImpl(WMLDocumentImpl owner, String tagName) {
        super(owner, tagName);
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
