package mf.org.w3c.dom.html;

import mf.org.w3c.dom.Node;

public interface HTMLCollection {
    int getLength();

    Node item(int i);

    Node namedItem(String str);
}
