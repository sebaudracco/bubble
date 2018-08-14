package mf.org.apache.xml.serialize;

import java.io.IOException;
import mf.org.w3c.dom.Document;
import mf.org.w3c.dom.DocumentFragment;
import mf.org.w3c.dom.Element;

public interface DOMSerializer {
    void serialize(Document document) throws IOException;

    void serialize(DocumentFragment documentFragment) throws IOException;

    void serialize(Element element) throws IOException;
}
