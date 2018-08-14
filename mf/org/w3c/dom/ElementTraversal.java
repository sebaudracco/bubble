package mf.org.w3c.dom;

public interface ElementTraversal {
    int getChildElementCount();

    Element getFirstElementChild();

    Element getLastElementChild();

    Element getNextElementSibling();

    Element getPreviousElementSibling();
}
