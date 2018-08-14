package mf.org.w3c.dom.events;

import mf.org.w3c.dom.views.AbstractView;

public interface UIEvent extends Event {
    int getDetail();

    AbstractView getView();

    void initUIEvent(String str, boolean z, boolean z2, AbstractView abstractView, int i);
}
