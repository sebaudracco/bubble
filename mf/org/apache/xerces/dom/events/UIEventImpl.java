package mf.org.apache.xerces.dom.events;

import mf.org.w3c.dom.events.UIEvent;
import mf.org.w3c.dom.views.AbstractView;

public class UIEventImpl extends EventImpl implements UIEvent {
    private int fDetail;
    private AbstractView fView;

    public AbstractView getView() {
        return this.fView;
    }

    public int getDetail() {
        return this.fDetail;
    }

    public void initUIEvent(String typeArg, boolean canBubbleArg, boolean cancelableArg, AbstractView viewArg, int detailArg) {
        this.fView = viewArg;
        this.fDetail = detailArg;
        super.initEvent(typeArg, canBubbleArg, cancelableArg);
    }
}
