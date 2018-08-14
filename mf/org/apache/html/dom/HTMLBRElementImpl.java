package mf.org.apache.html.dom;

import com.mopub.common.AdType;
import mf.org.w3c.dom.html.HTMLBRElement;

public class HTMLBRElementImpl extends HTMLElementImpl implements HTMLBRElement {
    private static final long serialVersionUID = 311960206282154750L;

    public String getClear() {
        return capitalize(getAttribute(AdType.CLEAR));
    }

    public void setClear(String clear) {
        setAttribute(AdType.CLEAR, clear);
    }

    public HTMLBRElementImpl(HTMLDocumentImpl owner, String name) {
        super(owner, name);
    }
}
