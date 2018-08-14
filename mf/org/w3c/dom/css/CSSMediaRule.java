package mf.org.w3c.dom.css;

import mf.org.w3c.dom.DOMException;
import mf.org.w3c.dom.stylesheets.MediaList;

public interface CSSMediaRule extends CSSRule {
    void deleteRule(int i) throws DOMException;

    CSSRuleList getCssRules();

    MediaList getMedia();

    int insertRule(String str, int i) throws DOMException;
}
