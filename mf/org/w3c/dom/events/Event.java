package mf.org.w3c.dom.events;

public interface Event {
    public static final short AT_TARGET = (short) 2;
    public static final short BUBBLING_PHASE = (short) 3;
    public static final short CAPTURING_PHASE = (short) 1;

    boolean getBubbles();

    boolean getCancelable();

    EventTarget getCurrentTarget();

    short getEventPhase();

    EventTarget getTarget();

    long getTimeStamp();

    String getType();

    void initEvent(String str, boolean z, boolean z2);

    void preventDefault();

    void stopPropagation();
}
