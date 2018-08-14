package mf.org.apache.xerces.dom.events;

import mf.org.w3c.dom.events.EventTarget;
import mf.org.w3c.dom.events.MouseEvent;
import mf.org.w3c.dom.views.AbstractView;

public class MouseEventImpl extends UIEventImpl implements MouseEvent {
    private boolean fAltKey;
    private short fButton;
    private int fClientX;
    private int fClientY;
    private boolean fCtrlKey;
    private boolean fMetaKey;
    private EventTarget fRelatedTarget;
    private int fScreenX;
    private int fScreenY;
    private boolean fShiftKey;

    public int getScreenX() {
        return this.fScreenX;
    }

    public int getScreenY() {
        return this.fScreenY;
    }

    public int getClientX() {
        return this.fClientX;
    }

    public int getClientY() {
        return this.fClientY;
    }

    public boolean getCtrlKey() {
        return this.fCtrlKey;
    }

    public boolean getAltKey() {
        return this.fAltKey;
    }

    public boolean getShiftKey() {
        return this.fShiftKey;
    }

    public boolean getMetaKey() {
        return this.fMetaKey;
    }

    public short getButton() {
        return this.fButton;
    }

    public EventTarget getRelatedTarget() {
        return this.fRelatedTarget;
    }

    public void initMouseEvent(String typeArg, boolean canBubbleArg, boolean cancelableArg, AbstractView viewArg, int detailArg, int screenXArg, int screenYArg, int clientXArg, int clientYArg, boolean ctrlKeyArg, boolean altKeyArg, boolean shiftKeyArg, boolean metaKeyArg, short buttonArg, EventTarget relatedTargetArg) {
        this.fScreenX = screenXArg;
        this.fScreenY = screenYArg;
        this.fClientX = clientXArg;
        this.fClientY = clientYArg;
        this.fCtrlKey = ctrlKeyArg;
        this.fAltKey = altKeyArg;
        this.fShiftKey = shiftKeyArg;
        this.fMetaKey = metaKeyArg;
        this.fButton = buttonArg;
        this.fRelatedTarget = relatedTargetArg;
        super.initUIEvent(typeArg, canBubbleArg, cancelableArg, viewArg, detailArg);
    }
}
