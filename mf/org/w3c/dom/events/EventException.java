package mf.org.w3c.dom.events;

public class EventException extends RuntimeException {
    public static final short UNSPECIFIED_EVENT_TYPE_ERR = (short) 0;
    public short code;

    public EventException(short code, String message) {
        super(message);
        this.code = code;
    }
}
