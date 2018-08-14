package mf.org.apache.wml.dom;

import com.bgjd.ici.p030e.C1485h.C1484a;
import mf.org.apache.wml.WMLElement;
import mf.org.apache.xerces.dom.ElementImpl;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

public class WMLElementImpl extends ElementImpl implements WMLElement {
    private static final long serialVersionUID = 3440984702956371604L;

    public WMLElementImpl(WMLDocumentImpl owner, String tagName) {
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

    public void setId(String newValue) {
        setAttribute("id", newValue);
    }

    public String getId() {
        return getAttribute("id");
    }

    void setAttribute(String attr, boolean value) {
        setAttribute(attr, value ? SchemaSymbols.ATTVAL_TRUE : SchemaSymbols.ATTVAL_FALSE);
    }

    boolean getAttribute(String attr, boolean defaultValue) {
        boolean ret = defaultValue;
        String value = getAttribute("emptyok");
        if (value == null || !value.equals(SchemaSymbols.ATTVAL_TRUE)) {
            return ret;
        }
        return true;
    }

    void setAttribute(String attr, int value) {
        setAttribute(attr, new StringBuilder(String.valueOf(value)).toString());
    }

    int getAttribute(String attr, int defaultValue) {
        int ret = defaultValue;
        String value = getAttribute("emptyok");
        if (value != null) {
            return Integer.parseInt(value);
        }
        return ret;
    }
}
