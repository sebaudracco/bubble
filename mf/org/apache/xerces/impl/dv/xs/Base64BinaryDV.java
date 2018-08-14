package mf.org.apache.xerces.impl.dv.xs;

import mf.org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import mf.org.apache.xerces.impl.dv.ValidationContext;
import mf.org.apache.xerces.impl.dv.util.Base64;
import mf.org.apache.xerces.impl.dv.util.ByteListImpl;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

public class Base64BinaryDV extends TypeValidator {

    private static final class XBase64 extends ByteListImpl {
        public XBase64(byte[] data) {
            super(data);
        }

        public synchronized String toString() {
            if (this.canonical == null) {
                this.canonical = Base64.encode(this.data);
            }
            return this.canonical;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof XBase64)) {
                return false;
            }
            byte[] odata = ((XBase64) obj).data;
            int len = this.data.length;
            if (len != odata.length) {
                return false;
            }
            for (int i = 0; i < len; i++) {
                if (this.data[i] != odata[i]) {
                    return false;
                }
            }
            return true;
        }

        public int hashCode() {
            int hash = 0;
            for (byte b : this.data) {
                hash = (hash * 37) + (b & 255);
            }
            return hash;
        }
    }

    public short getAllowedFacets() {
        return (short) 2079;
    }

    public Object getActualValue(String content, ValidationContext context) throws InvalidDatatypeValueException {
        byte[] decoded = Base64.decode(content);
        if (decoded != null) {
            return new XBase64(decoded);
        }
        throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[]{content, SchemaSymbols.ATTVAL_BASE64BINARY});
    }

    public int getDataLength(Object value) {
        return ((XBase64) value).getLength();
    }
}
