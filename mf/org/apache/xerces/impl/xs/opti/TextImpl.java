package mf.org.apache.xerces.impl.xs.opti;

import mf.org.w3c.dom.DOMException;
import mf.org.w3c.dom.Node;

public class TextImpl extends DefaultText {
    int fCol;
    String fData = null;
    int fRow;
    SchemaDOM fSchemaDOM = null;

    public TextImpl(StringBuffer str, SchemaDOM sDOM, int row, int col) {
        this.fData = str.toString();
        this.fSchemaDOM = sDOM;
        this.fRow = row;
        this.fCol = col;
        this.uri = null;
        this.localpart = null;
        this.prefix = null;
        this.rawname = null;
        this.nodeType = (short) 3;
    }

    public String getNodeName() {
        return "#text";
    }

    public Node getParentNode() {
        return this.fSchemaDOM.relations[this.fRow][0];
    }

    public Node getPreviousSibling() {
        if (this.fCol == 1) {
            return null;
        }
        return this.fSchemaDOM.relations[this.fRow][this.fCol - 1];
    }

    public Node getNextSibling() {
        if (this.fCol == this.fSchemaDOM.relations[this.fRow].length - 1) {
            return null;
        }
        return this.fSchemaDOM.relations[this.fRow][this.fCol + 1];
    }

    public String getData() throws DOMException {
        return this.fData;
    }

    public int getLength() {
        if (this.fData == null) {
            return 0;
        }
        return this.fData.length();
    }

    public String substringData(int offset, int count) throws DOMException {
        if (this.fData == null) {
            return null;
        }
        if (count < 0 || offset < 0 || offset > this.fData.length()) {
            throw new DOMException((short) 1, "parameter error");
        } else if (offset + count >= this.fData.length()) {
            return this.fData.substring(offset);
        } else {
            return this.fData.substring(offset, offset + count);
        }
    }
}
