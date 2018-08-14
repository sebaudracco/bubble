package mf.org.apache.xerces.impl.xs.util;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;
import mf.javax.xml.namespace.QName;
import mf.org.apache.xerces.util.SymbolHash;
import mf.org.apache.xerces.xs.XSNamedMap;
import mf.org.apache.xerces.xs.XSObject;

public class XSNamedMapImpl extends AbstractMap implements XSNamedMap {
    public static final XSNamedMapImpl EMPTY_MAP = new XSNamedMapImpl(new XSObject[0], 0);
    XSObject[] fArray = null;
    private Set fEntrySet = null;
    int fLength = -1;
    final SymbolHash[] fMaps;
    final int fNSNum;
    final String[] fNamespaces;

    private static final class XSNamedMapEntry implements Entry {
        private final QName key;
        private final XSObject value;

        public XSNamedMapEntry(QName key, XSObject value) {
            this.key = key;
            this.value = value;
        }

        public Object getKey() {
            return this.key;
        }

        public Object getValue() {
            return this.value;
        }

        public Object setValue(Object value) {
            throw new UnsupportedOperationException();
        }

        public boolean equals(Object o) {
            if (!(o instanceof Entry)) {
                return false;
            }
            Entry e = (Entry) o;
            Object otherKey = e.getKey();
            Object otherValue = e.getValue();
            if (this.key == null) {
                if (otherKey != null) {
                    return false;
                }
            } else if (!this.key.equals(otherKey)) {
                return false;
            }
            if (this.value == null) {
                if (otherValue != null) {
                    return false;
                }
            } else if (!this.value.equals(otherValue)) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            int i = 0;
            int hashCode = this.key == null ? 0 : this.key.hashCode();
            if (this.value != null) {
                i = this.value.hashCode();
            }
            return hashCode ^ i;
        }

        public String toString() {
            StringBuffer buffer = new StringBuffer();
            buffer.append(String.valueOf(this.key));
            buffer.append('=');
            buffer.append(String.valueOf(this.value));
            return buffer.toString();
        }
    }

    public XSNamedMapImpl(String namespace, SymbolHash map) {
        this.fNamespaces = new String[]{namespace};
        this.fMaps = new SymbolHash[]{map};
        this.fNSNum = 1;
    }

    public XSNamedMapImpl(String[] namespaces, SymbolHash[] maps, int num) {
        this.fNamespaces = namespaces;
        this.fMaps = maps;
        this.fNSNum = num;
    }

    public XSNamedMapImpl(XSObject[] array, int length) {
        if (length == 0) {
            this.fNamespaces = null;
            this.fMaps = null;
            this.fNSNum = 0;
            this.fArray = array;
            this.fLength = 0;
            return;
        }
        this.fNamespaces = new String[]{array[0].getNamespace()};
        this.fMaps = null;
        this.fNSNum = 1;
        this.fArray = array;
        this.fLength = length;
    }

    public synchronized int getLength() {
        if (this.fLength == -1) {
            this.fLength = 0;
            for (int i = 0; i < this.fNSNum; i++) {
                this.fLength += this.fMaps[i].getLength();
            }
        }
        return this.fLength;
    }

    public XSObject itemByName(String namespace, String localName) {
        int i = 0;
        while (i < this.fNSNum) {
            if (!isEqual(namespace, this.fNamespaces[i])) {
                i++;
            } else if (this.fMaps != null) {
                return (XSObject) this.fMaps[i].get(localName);
            } else {
                for (int j = 0; j < this.fLength; j++) {
                    XSObject ret = this.fArray[j];
                    if (ret.getName().equals(localName)) {
                        return ret;
                    }
                }
                return null;
            }
        }
        return null;
    }

    public synchronized XSObject item(int index) {
        XSObject xSObject;
        if (this.fArray == null) {
            getLength();
            this.fArray = new XSObject[this.fLength];
            int pos = 0;
            for (int i = 0; i < this.fNSNum; i++) {
                pos += this.fMaps[i].getValues(this.fArray, pos);
            }
        }
        if (index < 0 || index >= this.fLength) {
            xSObject = null;
        } else {
            xSObject = this.fArray[index];
        }
        return xSObject;
    }

    static boolean isEqual(String one, String two) {
        if (one != null) {
            return one.equals(two);
        }
        return two == null;
    }

    public boolean containsKey(Object key) {
        return get(key) != null;
    }

    public Object get(Object key) {
        if (!(key instanceof QName)) {
            return null;
        }
        QName name = (QName) key;
        String namespaceURI = name.getNamespaceURI();
        if ("".equals(namespaceURI)) {
            namespaceURI = null;
        }
        return itemByName(namespaceURI, name.getLocalPart());
    }

    public int size() {
        return getLength();
    }

    public synchronized Set entrySet() {
        if (this.fEntrySet == null) {
            final int length = getLength();
            final XSNamedMapEntry[] entries = new XSNamedMapEntry[length];
            for (int i = 0; i < length; i++) {
                XSObject xso = item(i);
                entries[i] = new XSNamedMapEntry(new QName(xso.getNamespace(), xso.getName()), xso);
            }
            this.fEntrySet = new AbstractSet() {
                public Iterator iterator() {
                    final int i = length;
                    final XSNamedMapEntry[] xSNamedMapEntryArr = entries;
                    return new Iterator() {
                        private int index = 0;

                        public boolean hasNext() {
                            return this.index < i;
                        }

                        public Object next() {
                            if (this.index < i) {
                                XSNamedMapEntry[] xSNamedMapEntryArr = xSNamedMapEntryArr;
                                int i = this.index;
                                this.index = i + 1;
                                return xSNamedMapEntryArr[i];
                            }
                            throw new NoSuchElementException();
                        }

                        public void remove() {
                            throw new UnsupportedOperationException();
                        }
                    };
                }

                public int size() {
                    return length;
                }
            };
        }
        return this.fEntrySet;
    }
}
