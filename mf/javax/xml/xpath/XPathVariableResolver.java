package mf.javax.xml.xpath;

import mf.javax.xml.namespace.QName;

public interface XPathVariableResolver {
    Object resolveVariable(QName qName);
}
