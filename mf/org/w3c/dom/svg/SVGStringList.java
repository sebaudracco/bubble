package mf.org.w3c.dom.svg;

import mf.org.w3c.dom.DOMException;

public interface SVGStringList {
    String appendItem(String str) throws DOMException, SVGException;

    void clear() throws DOMException;

    String getItem(int i) throws DOMException;

    int getNumberOfItems();

    String initialize(String str) throws DOMException, SVGException;

    String insertItemBefore(String str, int i) throws DOMException, SVGException;

    String removeItem(int i) throws DOMException;

    String replaceItem(String str, int i) throws DOMException, SVGException;
}
