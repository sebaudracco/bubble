package mf.org.w3c.dom.css;

import mf.org.w3c.dom.Element;
import mf.org.w3c.dom.stylesheets.DocumentStyle;

public interface DocumentCSS extends DocumentStyle {
    CSSStyleDeclaration getOverrideStyle(Element element, String str);
}
