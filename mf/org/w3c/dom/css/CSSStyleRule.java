package mf.org.w3c.dom.css;

import mf.org.w3c.dom.DOMException;

public interface CSSStyleRule extends CSSRule {
    String getSelectorText();

    CSSStyleDeclaration getStyle();

    void setSelectorText(String str) throws DOMException;
}
