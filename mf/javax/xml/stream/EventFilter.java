package mf.javax.xml.stream;

import mf.javax.xml.stream.events.XMLEvent;

public interface EventFilter {
    boolean accept(XMLEvent xMLEvent);
}
