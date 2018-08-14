package mf.org.apache.html.dom;

import com.google.android.gms.common.Scopes;
import mf.org.w3c.dom.html.HTMLHeadElement;

public class HTMLHeadElementImpl extends HTMLElementImpl implements HTMLHeadElement {
    private static final long serialVersionUID = 6438668473721292232L;

    public String getProfile() {
        return getAttribute(Scopes.PROFILE);
    }

    public void setProfile(String profile) {
        setAttribute(Scopes.PROFILE, profile);
    }

    public HTMLHeadElementImpl(HTMLDocumentImpl owner, String name) {
        super(owner, name);
    }
}
