package mf.org.apache.wml.dom;

import com.bgjd.ici.p030e.C1485h.C1484a;
import com.google.android.gms.plus.PlusShare;
import mf.org.apache.wml.WMLDoElement;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

public class WMLDoElementImpl extends WMLElementImpl implements WMLDoElement {
    private static final long serialVersionUID = 7755861458464251322L;

    public WMLDoElementImpl(WMLDocumentImpl owner, String tagName) {
        super(owner, tagName);
    }

    public void setOptional(String newValue) {
        setAttribute(SchemaSymbols.ATTVAL_OPTIONAL, newValue);
    }

    public String getOptional() {
        return getAttribute(SchemaSymbols.ATTVAL_OPTIONAL);
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

    public void setLabel(String newValue) {
        setAttribute(PlusShare.KEY_CALL_TO_ACTION_LABEL, newValue);
    }

    public String getLabel() {
        return getAttribute(PlusShare.KEY_CALL_TO_ACTION_LABEL);
    }

    public void setType(String newValue) {
        setAttribute("type", newValue);
    }

    public String getType() {
        return getAttribute("type");
    }

    public void setName(String newValue) {
        setAttribute("name", newValue);
    }

    public String getName() {
        return getAttribute("name");
    }
}
