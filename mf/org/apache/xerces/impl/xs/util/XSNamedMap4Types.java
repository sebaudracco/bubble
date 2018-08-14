package mf.org.apache.xerces.impl.xs.util;

import mf.org.apache.xerces.util.SymbolHash;
import mf.org.apache.xerces.xs.XSObject;
import mf.org.apache.xerces.xs.XSTypeDefinition;

public final class XSNamedMap4Types extends XSNamedMapImpl {
    private final short fType;

    public XSNamedMap4Types(String namespace, SymbolHash map, short type) {
        super(namespace, map);
        this.fType = type;
    }

    public XSNamedMap4Types(String[] namespaces, SymbolHash[] maps, int num, short type) {
        super(namespaces, maps, num);
        this.fType = type;
    }

    public synchronized int getLength() {
        if (this.fLength == -1) {
            int i;
            int length = 0;
            for (i = 0; i < this.fNSNum; i++) {
                length += this.fMaps[i].getLength();
            }
            int pos = 0;
            XSObject[] array = new XSObject[length];
            for (i = 0; i < this.fNSNum; i++) {
                pos += this.fMaps[i].getValues(array, pos);
            }
            this.fLength = 0;
            this.fArray = new XSObject[length];
            for (i = 0; i < length; i++) {
                XSTypeDefinition type = array[i];
                if (type.getTypeCategory() == this.fType) {
                    XSObject[] xSObjectArr = this.fArray;
                    int i2 = this.fLength;
                    this.fLength = i2 + 1;
                    xSObjectArr[i2] = type;
                }
            }
        }
        return this.fLength;
    }

    public XSObject itemByName(String namespace, String localName) {
        for (int i = 0; i < this.fNSNum; i++) {
            if (XSNamedMapImpl.isEqual(namespace, this.fNamespaces[i])) {
                XSTypeDefinition type = (XSTypeDefinition) this.fMaps[i].get(localName);
                if (type == null || type.getTypeCategory() != this.fType) {
                    return null;
                }
                return type;
            }
        }
        return null;
    }

    public synchronized XSObject item(int index) {
        XSObject xSObject;
        if (this.fArray == null) {
            getLength();
        }
        if (index < 0 || index >= this.fLength) {
            xSObject = null;
        } else {
            xSObject = this.fArray[index];
        }
        return xSObject;
    }
}
