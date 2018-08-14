package mf.org.apache.xerces.impl.xs.opti;

import mf.org.w3c.dom.DOMException;
import mf.org.w3c.dom.Text;

public class DefaultText extends NodeImpl implements Text {
    public String getData() throws DOMException {
        return null;
    }

    public void setData(String data) throws DOMException {
        throw new DOMException((short) 9, "Method not supported");
    }

    public int getLength() {
        return 0;
    }

    public String substringData(int offset, int count) throws DOMException {
        throw new DOMException((short) 9, "Method not supported");
    }

    public void appendData(String arg) throws DOMException {
        throw new DOMException((short) 9, "Method not supported");
    }

    public void insertData(int offset, String arg) throws DOMException {
        throw new DOMException((short) 9, "Method not supported");
    }

    public void deleteData(int offset, int count) throws DOMException {
        throw new DOMException((short) 9, "Method not supported");
    }

    public void replaceData(int offset, int count, String arg) throws DOMException {
        throw new DOMException((short) 9, "Method not supported");
    }

    public Text splitText(int offset) throws DOMException {
        throw new DOMException((short) 9, "Method not supported");
    }

    public boolean isElementContentWhitespace() {
        throw new DOMException((short) 9, "Method not supported");
    }

    public String getWholeText() {
        throw new DOMException((short) 9, "Method not supported");
    }

    public Text replaceWholeText(String content) throws DOMException {
        throw new DOMException((short) 9, "Method not supported");
    }
}
