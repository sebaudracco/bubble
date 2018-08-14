package mf.org.w3c.dom.xpath;

import mf.org.w3c.dom.DOMException;
import mf.org.w3c.dom.Node;

public interface XPathExpression {
    Object evaluate(Node node, short s, Object obj) throws XPathException, DOMException;
}
