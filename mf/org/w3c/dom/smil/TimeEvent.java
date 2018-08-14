package mf.org.w3c.dom.smil;

import mf.org.w3c.dom.events.Event;
import mf.org.w3c.dom.views.AbstractView;

public interface TimeEvent extends Event {
    int getDetail();

    AbstractView getView();

    void initTimeEvent(String str, AbstractView abstractView, int i);
}
