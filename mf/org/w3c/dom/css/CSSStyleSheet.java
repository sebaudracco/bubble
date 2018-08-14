package mf.org.w3c.dom.css;

import mf.org.w3c.dom.DOMException;
import mf.org.w3c.dom.stylesheets.StyleSheet;

public interface CSSStyleSheet extends StyleSheet {
    void deleteRule(int i) throws DOMException;

    CSSRuleList getCssRules();

    CSSRule getOwnerRule();

    int insertRule(String str, int i) throws DOMException;
}
