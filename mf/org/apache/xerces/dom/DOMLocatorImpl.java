package mf.org.apache.xerces.dom;

import mf.org.w3c.dom.DOMLocator;
import mf.org.w3c.dom.Node;

public class DOMLocatorImpl implements DOMLocator {
    public int fByteOffset = -1;
    public int fColumnNumber = -1;
    public int fLineNumber = -1;
    public Node fRelatedNode = null;
    public String fUri = null;
    public int fUtf16Offset = -1;

    public DOMLocatorImpl(int lineNumber, int columnNumber, String uri) {
        this.fLineNumber = lineNumber;
        this.fColumnNumber = columnNumber;
        this.fUri = uri;
    }

    public DOMLocatorImpl(int lineNumber, int columnNumber, int utf16Offset, String uri) {
        this.fLineNumber = lineNumber;
        this.fColumnNumber = columnNumber;
        this.fUri = uri;
        this.fUtf16Offset = utf16Offset;
    }

    public DOMLocatorImpl(int lineNumber, int columnNumber, int byteoffset, Node relatedData, String uri) {
        this.fLineNumber = lineNumber;
        this.fColumnNumber = columnNumber;
        this.fByteOffset = byteoffset;
        this.fRelatedNode = relatedData;
        this.fUri = uri;
    }

    public DOMLocatorImpl(int lineNumber, int columnNumber, int byteoffset, Node relatedData, String uri, int utf16Offset) {
        this.fLineNumber = lineNumber;
        this.fColumnNumber = columnNumber;
        this.fByteOffset = byteoffset;
        this.fRelatedNode = relatedData;
        this.fUri = uri;
        this.fUtf16Offset = utf16Offset;
    }

    public int getLineNumber() {
        return this.fLineNumber;
    }

    public int getColumnNumber() {
        return this.fColumnNumber;
    }

    public String getUri() {
        return this.fUri;
    }

    public Node getRelatedNode() {
        return this.fRelatedNode;
    }

    public int getByteOffset() {
        return this.fByteOffset;
    }

    public int getUtf16Offset() {
        return this.fUtf16Offset;
    }
}
