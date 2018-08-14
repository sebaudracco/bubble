package mf.org.apache.html.dom;

import com.google.firebase.analytics.FirebaseAnalytics$Param;
import mf.org.w3c.dom.html.HTMLParamElement;

public class HTMLParamElementImpl extends HTMLElementImpl implements HTMLParamElement {
    private static final long serialVersionUID = -8513050483880341412L;

    public String getName() {
        return getAttribute("name");
    }

    public void setName(String name) {
        setAttribute("name", name);
    }

    public String getType() {
        return getAttribute("type");
    }

    public void setType(String type) {
        setAttribute("type", type);
    }

    public String getValue() {
        return getAttribute(FirebaseAnalytics$Param.VALUE);
    }

    public void setValue(String value) {
        setAttribute(FirebaseAnalytics$Param.VALUE, value);
    }

    public String getValueType() {
        return capitalize(getAttribute("valuetype"));
    }

    public void setValueType(String valueType) {
        setAttribute("valuetype", valueType);
    }

    public HTMLParamElementImpl(HTMLDocumentImpl owner, String name) {
        super(owner, name);
    }
}
