package mf.org.w3c.dom.smil;

import mf.org.w3c.dom.DOMException;

public interface ElementTimeControl {
    boolean beginElement() throws DOMException;

    boolean beginElementAt(float f) throws DOMException;

    boolean endElement() throws DOMException;

    boolean endElementAt(float f) throws DOMException;
}
