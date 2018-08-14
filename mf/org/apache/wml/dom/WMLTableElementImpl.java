package mf.org.apache.wml.dom;

import com.bgjd.ici.p030e.C1485h.C1484a;
import mf.org.apache.wml.WMLTableElement;

public class WMLTableElementImpl extends WMLElementImpl implements WMLTableElement {
    private static final long serialVersionUID = 7676208849347355339L;

    public WMLTableElementImpl(WMLDocumentImpl owner, String tagName) {
        super(owner, tagName);
    }

    public void setColumns(int newValue) {
        setAttribute("columns", newValue);
    }

    public int getColumns() {
        return getAttribute("columns", 0);
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

    public void setAlign(String newValue) {
        setAttribute("align", newValue);
    }

    public String getAlign() {
        return getAttribute("align");
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
