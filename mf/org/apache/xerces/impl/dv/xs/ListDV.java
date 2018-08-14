package mf.org.apache.xerces.impl.dv.xs;

import java.util.AbstractList;
import mf.org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import mf.org.apache.xerces.impl.dv.ValidationContext;
import mf.org.apache.xerces.xs.datatypes.ObjectList;

public class ListDV extends TypeValidator {

    static final class ListData extends AbstractList implements ObjectList {
        private String canonical;
        final Object[] data;

        public ListData(Object[] data) {
            this.data = data;
        }

        public synchronized String toString() {
            if (this.canonical == null) {
                int len = this.data.length;
                StringBuffer buf = new StringBuffer();
                if (len > 0) {
                    buf.append(this.data[0].toString());
                }
                for (int i = 1; i < len; i++) {
                    buf.append(' ');
                    buf.append(this.data[i].toString());
                }
                this.canonical = buf.toString();
            }
            return this.canonical;
        }

        public int getLength() {
            return this.data.length;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof ListData)) {
                return false;
            }
            Object[] odata = ((ListData) obj).data;
            int count = this.data.length;
            if (count != odata.length) {
                return false;
            }
            for (int i = 0; i < count; i++) {
                if (!this.data[i].equals(odata[i])) {
                    return false;
                }
            }
            return true;
        }

        public int hashCode() {
            int hash = 0;
            for (Object hashCode : this.data) {
                hash ^= hashCode.hashCode();
            }
            return hash;
        }

        public boolean contains(Object item) {
            for (Object obj : this.data) {
                if (item == obj) {
                    return true;
                }
            }
            return false;
        }

        public Object item(int index) {
            if (index < 0 || index >= this.data.length) {
                return null;
            }
            return this.data[index];
        }

        public Object get(int index) {
            if (index >= 0 && index < this.data.length) {
                return this.data[index];
            }
            throw new IndexOutOfBoundsException("Index: " + index);
        }

        public int size() {
            return getLength();
        }
    }

    public short getAllowedFacets() {
        return (short) 2079;
    }

    public Object getActualValue(String content, ValidationContext context) throws InvalidDatatypeValueException {
        return content;
    }

    public int getDataLength(Object value) {
        return ((ListData) value).getLength();
    }
}
