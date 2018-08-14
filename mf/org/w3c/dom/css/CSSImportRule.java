package mf.org.w3c.dom.css;

import mf.org.w3c.dom.stylesheets.MediaList;

public interface CSSImportRule extends CSSRule {
    String getHref();

    MediaList getMedia();

    CSSStyleSheet getStyleSheet();
}
