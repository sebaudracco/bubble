package mf.org.apache.html.dom;

import java.io.Serializable;
import mf.org.w3c.dom.Element;
import mf.org.w3c.dom.Node;
import mf.org.w3c.dom.html.HTMLCollection;
import mf.org.w3c.dom.html.HTMLElement;

class HTMLCollectionImpl implements HTMLCollection, Serializable {
    static final short ANCHOR = (short) 1;
    static final short APPLET = (short) 4;
    static final short AREA = (short) -1;
    static final short CELL = (short) -3;
    static final short ELEMENT = (short) 8;
    static final short FORM = (short) 2;
    static final short IMAGE = (short) 3;
    static final short LINK = (short) 5;
    static final short OPTION = (short) 6;
    static final short ROW = (short) 7;
    static final short TBODY = (short) -2;
    private static final long serialVersionUID = 9112122196669185082L;
    private short _lookingFor;
    private Element _topLevel;

    HTMLCollectionImpl(HTMLElement topLevel, short lookingFor) {
        if (topLevel == null) {
            throw new NullPointerException("HTM011 Argument 'topLevel' is null.");
        }
        this._topLevel = topLevel;
        this._lookingFor = lookingFor;
    }

    public final int getLength() {
        return getLength(this._topLevel);
    }

    public final Node item(int index) {
        if (index >= 0) {
            return item(this._topLevel, new CollectionIndex(index));
        }
        throw new IllegalArgumentException("HTM012 Argument 'index' is negative.");
    }

    public final Node namedItem(String name) {
        if (name != null) {
            return namedItem(this._topLevel, name);
        }
        throw new NullPointerException("HTM013 Argument 'name' is null.");
    }

    private int getLength(Element topLevel) {
        int length;
        synchronized (topLevel) {
            length = 0;
            for (Node node = topLevel.getFirstChild(); node != null; node = node.getNextSibling()) {
                if (node instanceof Element) {
                    if (collectionMatch((Element) node, null)) {
                        length++;
                    } else if (recurse()) {
                        length += getLength((Element) node);
                    }
                }
            }
        }
        return length;
    }

    private Node item(Element topLevel, CollectionIndex index) {
        synchronized (topLevel) {
            for (Node node = topLevel.getFirstChild(); node != null; node = node.getNextSibling()) {
                if (node instanceof Element) {
                    if (collectionMatch((Element) node, null)) {
                        if (index.isZero()) {
                            return node;
                        }
                        index.decrement();
                    } else if (recurse()) {
                        Node result = item((Element) node, index);
                        if (result != null) {
                            return result;
                        }
                    } else {
                        continue;
                    }
                }
            }
            return null;
        }
    }

    private Node namedItem(Element topLevel, String name) {
        synchronized (topLevel) {
            Node node = topLevel.getFirstChild();
            while (node != null) {
                if (node instanceof Element) {
                    if (collectionMatch((Element) node, name)) {
                        return node;
                    } else if (recurse()) {
                        Node result = namedItem((Element) node, name);
                        if (result != null) {
                            return result;
                        }
                    }
                }
                node = node.getNextSibling();
            }
            return node;
        }
    }

    protected boolean recurse() {
        return this._lookingFor > (short) 0;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected boolean collectionMatch(mf.org.w3c.dom.Element r6, java.lang.String r7) {
        /*
        r5 = this;
        r2 = 0;
        r1 = 1;
        monitor-enter(r6);
        r0 = 0;
        r3 = r5._lookingFor;	 Catch:{ all -> 0x00b6 }
        switch(r3) {
            case -3: goto L_0x00a3;
            case -2: goto L_0x008d;
            case -1: goto L_0x0083;
            case 0: goto L_0x0009;
            case 1: goto L_0x0020;
            case 2: goto L_0x0035;
            case 3: goto L_0x0038;
            case 4: goto L_0x003b;
            case 5: goto L_0x006a;
            case 6: goto L_0x0086;
            case 7: goto L_0x0089;
            case 8: goto L_0x0067;
            default: goto L_0x0009;
        };	 Catch:{ all -> 0x00b6 }
    L_0x0009:
        if (r0 == 0) goto L_0x00b2;
    L_0x000b:
        if (r7 == 0) goto L_0x00b2;
    L_0x000d:
        r2 = r6 instanceof mf.org.w3c.dom.html.HTMLAnchorElement;	 Catch:{ all -> 0x00b6 }
        if (r2 == 0) goto L_0x00a7;
    L_0x0011:
        r2 = "name";
        r2 = r6.getAttribute(r2);	 Catch:{ all -> 0x00b6 }
        r2 = r7.equals(r2);	 Catch:{ all -> 0x00b6 }
        if (r2 == 0) goto L_0x00a7;
    L_0x001e:
        monitor-exit(r6);	 Catch:{ all -> 0x00b6 }
    L_0x001f:
        return r1;
    L_0x0020:
        r3 = r6 instanceof mf.org.w3c.dom.html.HTMLAnchorElement;	 Catch:{ all -> 0x00b6 }
        if (r3 == 0) goto L_0x0033;
    L_0x0024:
        r3 = "name";
        r3 = r6.getAttribute(r3);	 Catch:{ all -> 0x00b6 }
        r3 = r3.length();	 Catch:{ all -> 0x00b6 }
        if (r3 <= 0) goto L_0x0033;
    L_0x0031:
        r0 = r1;
    L_0x0032:
        goto L_0x0009;
    L_0x0033:
        r0 = r2;
        goto L_0x0032;
    L_0x0035:
        r0 = r6 instanceof mf.org.w3c.dom.html.HTMLFormElement;	 Catch:{ all -> 0x00b6 }
        goto L_0x0009;
    L_0x0038:
        r0 = r6 instanceof mf.org.w3c.dom.html.HTMLImageElement;	 Catch:{ all -> 0x00b6 }
        goto L_0x0009;
    L_0x003b:
        r3 = r6 instanceof mf.org.w3c.dom.html.HTMLAppletElement;	 Catch:{ all -> 0x00b6 }
        if (r3 != 0) goto L_0x0065;
    L_0x003f:
        r3 = r6 instanceof mf.org.w3c.dom.html.HTMLObjectElement;	 Catch:{ all -> 0x00b6 }
        if (r3 == 0) goto L_0x0063;
    L_0x0043:
        r3 = "application/java";
        r4 = "codetype";
        r4 = r6.getAttribute(r4);	 Catch:{ all -> 0x00b6 }
        r3 = r3.equals(r4);	 Catch:{ all -> 0x00b6 }
        if (r3 != 0) goto L_0x0065;
    L_0x0053:
        r3 = "classid";
        r3 = r6.getAttribute(r3);	 Catch:{ all -> 0x00b6 }
        r4 = "java:";
        r3 = r3.startsWith(r4);	 Catch:{ all -> 0x00b6 }
        if (r3 != 0) goto L_0x0065;
    L_0x0063:
        r0 = r2;
    L_0x0064:
        goto L_0x0009;
    L_0x0065:
        r0 = r1;
        goto L_0x0064;
    L_0x0067:
        r0 = r6 instanceof mf.org.apache.html.dom.HTMLFormControl;	 Catch:{ all -> 0x00b6 }
        goto L_0x0009;
    L_0x006a:
        r3 = r6 instanceof mf.org.w3c.dom.html.HTMLAnchorElement;	 Catch:{ all -> 0x00b6 }
        if (r3 != 0) goto L_0x0072;
    L_0x006e:
        r3 = r6 instanceof mf.org.w3c.dom.html.HTMLAreaElement;	 Catch:{ all -> 0x00b6 }
        if (r3 == 0) goto L_0x0081;
    L_0x0072:
        r3 = "href";
        r3 = r6.getAttribute(r3);	 Catch:{ all -> 0x00b6 }
        r3 = r3.length();	 Catch:{ all -> 0x00b6 }
        if (r3 <= 0) goto L_0x0081;
    L_0x007f:
        r0 = r1;
    L_0x0080:
        goto L_0x0009;
    L_0x0081:
        r0 = r2;
        goto L_0x0080;
    L_0x0083:
        r0 = r6 instanceof mf.org.w3c.dom.html.HTMLAreaElement;	 Catch:{ all -> 0x00b6 }
        goto L_0x0009;
    L_0x0086:
        r0 = r6 instanceof mf.org.w3c.dom.html.HTMLOptionElement;	 Catch:{ all -> 0x00b6 }
        goto L_0x0009;
    L_0x0089:
        r0 = r6 instanceof mf.org.w3c.dom.html.HTMLTableRowElement;	 Catch:{ all -> 0x00b6 }
        goto L_0x0009;
    L_0x008d:
        r3 = r6 instanceof mf.org.w3c.dom.html.HTMLTableSectionElement;	 Catch:{ all -> 0x00b6 }
        if (r3 == 0) goto L_0x00a1;
    L_0x0091:
        r3 = r6.getTagName();	 Catch:{ all -> 0x00b6 }
        r4 = "TBODY";
        r3 = r3.equals(r4);	 Catch:{ all -> 0x00b6 }
        if (r3 == 0) goto L_0x00a1;
    L_0x009e:
        r0 = r1;
    L_0x009f:
        goto L_0x0009;
    L_0x00a1:
        r0 = r2;
        goto L_0x009f;
    L_0x00a3:
        r0 = r6 instanceof mf.org.w3c.dom.html.HTMLTableCellElement;	 Catch:{ all -> 0x00b6 }
        goto L_0x0009;
    L_0x00a7:
        r1 = "id";
        r1 = r6.getAttribute(r1);	 Catch:{ all -> 0x00b6 }
        r0 = r7.equals(r1);	 Catch:{ all -> 0x00b6 }
    L_0x00b2:
        monitor-exit(r6);	 Catch:{ all -> 0x00b6 }
        r1 = r0;
        goto L_0x001f;
    L_0x00b6:
        r1 = move-exception;
        monitor-exit(r6);	 Catch:{ all -> 0x00b6 }
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: mf.org.apache.html.dom.HTMLCollectionImpl.collectionMatch(mf.org.w3c.dom.Element, java.lang.String):boolean");
    }
}
