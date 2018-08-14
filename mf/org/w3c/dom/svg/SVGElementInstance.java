package mf.org.w3c.dom.svg;

import mf.org.w3c.dom.events.EventTarget;

public interface SVGElementInstance extends EventTarget {
    SVGElementInstanceList getChildNodes();

    SVGElement getCorrespondingElement();

    SVGUseElement getCorrespondingUseElement();

    SVGElementInstance getFirstChild();

    SVGElementInstance getLastChild();

    SVGElementInstance getNextSibling();

    SVGElementInstance getParentNode();

    SVGElementInstance getPreviousSibling();
}
