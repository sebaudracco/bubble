package mf.org.apache.wml.dom;

import com.bgjd.ici.p030e.C1485h.C1484a;
import mf.org.apache.wml.WMLImgElement;

public class WMLImgElementImpl extends WMLElementImpl implements WMLImgElement {
    private static final long serialVersionUID = -500092034867051550L;

    public WMLImgElementImpl(WMLDocumentImpl owner, String tagName) {
        super(owner, tagName);
    }

    public void setWidth(String newValue) {
        setAttribute("width", newValue);
    }

    public String getWidth() {
        return getAttribute("width");
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

    public void setLocalSrc(String newValue) {
        setAttribute("localsrc", newValue);
    }

    public String getLocalSrc() {
        return getAttribute("localsrc");
    }

    public void setHeight(String newValue) {
        setAttribute("height", newValue);
    }

    public String getHeight() {
        return getAttribute("height");
    }

    public void setAlign(String newValue) {
        setAttribute("align", newValue);
    }

    public String getAlign() {
        return getAttribute("align");
    }

    public void setVspace(String newValue) {
        setAttribute("vspace", newValue);
    }

    public String getVspace() {
        return getAttribute("vspace");
    }

    public void setAlt(String newValue) {
        setAttribute("alt", newValue);
    }

    public String getAlt() {
        return getAttribute("alt");
    }

    public void setId(String newValue) {
        setAttribute("id", newValue);
    }

    public String getId() {
        return getAttribute("id");
    }

    public void setHspace(String newValue) {
        setAttribute("hspace", newValue);
    }

    public String getHspace() {
        return getAttribute("hspace");
    }

    public void setSrc(String newValue) {
        setAttribute("src", newValue);
    }

    public String getSrc() {
        return getAttribute("src");
    }
}
