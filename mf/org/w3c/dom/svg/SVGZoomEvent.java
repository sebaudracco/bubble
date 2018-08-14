package mf.org.w3c.dom.svg;

import mf.org.w3c.dom.events.UIEvent;

public interface SVGZoomEvent extends UIEvent {
    float getNewScale();

    SVGPoint getNewTranslate();

    float getPreviousScale();

    SVGPoint getPreviousTranslate();

    SVGRect getZoomRectScreen();
}
