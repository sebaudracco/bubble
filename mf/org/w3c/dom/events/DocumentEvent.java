package mf.org.w3c.dom.events;

import mf.org.w3c.dom.DOMException;

public interface DocumentEvent {
    Event createEvent(String str) throws DOMException;
}
