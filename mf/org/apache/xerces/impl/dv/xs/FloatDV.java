package mf.org.apache.xerces.impl.dv.xs;

import mf.org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import mf.org.apache.xerces.impl.dv.ValidationContext;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import mf.org.apache.xerces.xs.datatypes.XSFloat;

public class FloatDV extends TypeValidator {

    private static final class XFloat implements XSFloat {
        private String canonical;
        private final float value;

        public XFloat(String s) throws NumberFormatException {
            if (DoubleDV.isPossibleFP(s)) {
                this.value = Float.parseFloat(s);
            } else if (s.equals("INF")) {
                this.value = Float.POSITIVE_INFINITY;
            } else if (s.equals("-INF")) {
                this.value = Float.NEGATIVE_INFINITY;
            } else if (s.equals("NaN")) {
                this.value = Float.NaN;
            } else {
                throw new NumberFormatException(s);
            }
        }

        public boolean equals(Object val) {
            if (val == this) {
                return true;
            }
            if (!(val instanceof XFloat)) {
                return false;
            }
            XFloat oval = (XFloat) val;
            if (this.value == oval.value) {
                return true;
            }
            if (this.value == this.value || oval.value == oval.value) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return this.value == 0.0f ? 0 : Float.floatToIntBits(this.value);
        }

        public boolean isIdentical(XFloat val) {
            if (val == this) {
                return true;
            }
            if (this.value == val.value) {
                if (this.value != 0.0f || Float.floatToIntBits(this.value) == Float.floatToIntBits(val.value)) {
                    return true;
                }
                return false;
            } else if (this.value == this.value || val.value == val.value) {
                return false;
            } else {
                return true;
            }
        }

        private int compareTo(XFloat val) {
            float oval = val.value;
            if (this.value < oval) {
                return -1;
            }
            if (this.value > oval) {
                return 1;
            }
            if (this.value == oval) {
                return 0;
            }
            if (this.value == this.value) {
                return 2;
            }
            if (oval == oval) {
                return 2;
            }
            return 0;
        }

        public synchronized String toString() {
            if (this.canonical == null) {
                if (this.value == Float.POSITIVE_INFINITY) {
                    this.canonical = "INF";
                } else if (this.value == Float.NEGATIVE_INFINITY) {
                    this.canonical = "-INF";
                } else if (this.value != this.value) {
                    this.canonical = "NaN";
                } else if (this.value == 0.0f) {
                    this.canonical = "0.0E1";
                } else {
                    this.canonical = Float.toString(this.value);
                    if (this.canonical.indexOf(69) == -1) {
                        int len = this.canonical.length();
                        char[] chars = new char[(len + 3)];
                        this.canonical.getChars(0, len, chars, 0);
                        int edp = chars[0] == '-' ? 2 : 1;
                        int i;
                        int i2;
                        if (this.value >= 1.0f || this.value <= -1.0f) {
                            int dp = this.canonical.indexOf(46);
                            for (i = dp; i > edp; i--) {
                                chars[i] = chars[i - 1];
                            }
                            chars[edp] = '.';
                            while (chars[len - 1] == '0') {
                                len--;
                            }
                            if (chars[len - 1] == '.') {
                                i2 = len + 1;
                            } else {
                                i2 = len;
                            }
                            len = i2 + 1;
                            chars[i2] = 'E';
                            i2 = len + 1;
                            chars[len] = (char) ((dp - edp) + 48);
                            len = i2;
                        } else {
                            int nzp = edp + 1;
                            while (chars[nzp] == '0') {
                                nzp++;
                            }
                            chars[edp - 1] = chars[nzp];
                            chars[edp] = '.';
                            i = nzp + 1;
                            int j = edp + 1;
                            while (i < len) {
                                chars[j] = chars[i];
                                i++;
                                j++;
                            }
                            len -= nzp - edp;
                            if (len == edp + 1) {
                                i2 = len + 1;
                                chars[len] = '0';
                            } else {
                                i2 = len;
                            }
                            len = i2 + 1;
                            chars[i2] = 'E';
                            i2 = len + 1;
                            chars[len] = '-';
                            len = i2 + 1;
                            chars[i2] = (char) ((nzp - edp) + 48);
                        }
                        this.canonical = new String(chars, 0, len);
                    }
                }
            }
            return this.canonical;
        }

        public float getValue() {
            return this.value;
        }
    }

    public short getAllowedFacets() {
        return (short) 2552;
    }

    public Object getActualValue(String content, ValidationContext context) throws InvalidDatatypeValueException {
        try {
            return new XFloat(content);
        } catch (NumberFormatException e) {
            throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[]{content, SchemaSymbols.ATTVAL_FLOAT});
        }
    }

    public int compare(Object value1, Object value2) {
        return ((XFloat) value1).compareTo((XFloat) value2);
    }

    public boolean isIdentical(Object value1, Object value2) {
        if (value2 instanceof XFloat) {
            return ((XFloat) value1).isIdentical((XFloat) value2);
        }
        return false;
    }
}
