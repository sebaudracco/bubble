package mf.org.w3c.dom.ls;

import mf.org.w3c.dom.Document;
import mf.org.w3c.dom.events.Event;

public interface LSLoadEvent extends Event {
    LSInput getInput();

    Document getNewDocument();
}
