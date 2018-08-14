package mf.org.apache.xerces.dom.events;

import mf.org.w3c.dom.events.Event;
import mf.org.w3c.dom.events.EventTarget;

public class EventImpl implements Event {
    public boolean bubbles = true;
    public boolean cancelable = false;
    public EventTarget currentTarget;
    public short eventPhase;
    public boolean initialized = false;
    public boolean preventDefault = false;
    public boolean stopPropagation = false;
    public EventTarget target;
    protected long timeStamp = System.currentTimeMillis();
    public String type = null;

    public void initEvent(String eventTypeArg, boolean canBubbleArg, boolean cancelableArg) {
        this.type = eventTypeArg;
        this.bubbles = canBubbleArg;
        this.cancelable = cancelableArg;
        this.initialized = true;
    }

    public boolean getBubbles() {
        return this.bubbles;
    }

    public boolean getCancelable() {
        return this.cancelable;
    }

    public EventTarget getCurrentTarget() {
        return this.currentTarget;
    }

    public short getEventPhase() {
        return this.eventPhase;
    }

    public EventTarget getTarget() {
        return this.target;
    }

    public String getType() {
        return this.type;
    }

    public long getTimeStamp() {
        return this.timeStamp;
    }

    public void stopPropagation() {
        this.stopPropagation = true;
    }

    public void preventDefault() {
        this.preventDefault = true;
    }
}
