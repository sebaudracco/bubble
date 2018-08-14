package mf.javax.xml.transform.dom;

import mf.javax.xml.transform.SourceLocator;
import mf.org.w3c.dom.Node;

public interface DOMLocator extends SourceLocator {
    Node getOriginatingNode();
}
