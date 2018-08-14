package mf.javax.xml.xpath;

import mf.javax.xml.namespace.QName;

public interface XPathFunctionResolver {
    XPathFunction resolveFunction(QName qName, int i);
}
