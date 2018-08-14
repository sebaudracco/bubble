package mf.org.apache.html.dom;

import com.google.android.gms.plus.PlusShare;
import mf.org.w3c.dom.html.HTMLOptGroupElement;

public class HTMLOptGroupElementImpl extends HTMLElementImpl implements HTMLOptGroupElement {
    private static final long serialVersionUID = -8807098641226171501L;

    public boolean getDisabled() {
        return getBinary("disabled");
    }

    public void setDisabled(boolean disabled) {
        setAttribute("disabled", disabled);
    }

    public String getLabel() {
        return capitalize(getAttribute(PlusShare.KEY_CALL_TO_ACTION_LABEL));
    }

    public void setLabel(String label) {
        setAttribute(PlusShare.KEY_CALL_TO_ACTION_LABEL, label);
    }

    public HTMLOptGroupElementImpl(HTMLDocumentImpl owner, String name) {
        super(owner, name);
    }
}
