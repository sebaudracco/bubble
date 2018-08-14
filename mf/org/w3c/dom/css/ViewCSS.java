package mf.org.w3c.dom.css;

import mf.org.w3c.dom.Element;
import mf.org.w3c.dom.views.AbstractView;

public interface ViewCSS extends AbstractView {
    CSSStyleDeclaration getComputedStyle(Element element, String str);
}
