package mf.org.apache.xerces.impl.dv.xs;

import com.mopub.mobileads.dfp.adapters.MoPubAdapter;
import mf.org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import mf.org.apache.xerces.impl.dv.ValidationContext;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import mf.org.apache.xerces.xs.datatypes.XSDouble;

public class DoubleDV extends TypeValidator {

    private static final class XDouble implements XSDouble {
        private String canonical;
        private final double value;

        public XDouble(String s) throws NumberFormatException {
            if (DoubleDV.isPossibleFP(s)) {
                this.value = Double.parseDouble(s);
            } else if (s.equals("INF")) {
                this.value = Double.POSITIVE_INFINITY;
            } else if (s.equals("-INF")) {
                this.value = Double.NEGATIVE_INFINITY;
            } else if (s.equals("NaN")) {
                this.value = Double.NaN;
            } else {
                throw new NumberFormatException(s);
            }
        }

        public boolean equals(Object val) {
            if (val == this) {
                return true;
            }
            if (!(val instanceof XDouble)) {
                return false;
            }
            XDouble oval = (XDouble) val;
            if (this.value == oval.value) {
                return true;
            }
            if (this.value == this.value || oval.value == oval.value) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            if (this.value == 0.0d) {
                return 0;
            }
            long v = Double.doubleToLongBits(this.value);
            return (int) ((v >>> 32) ^ v);
        }

        public boolean isIdentical(XDouble val) {
            if (val == this) {
                return true;
            }
            if (this.value == val.value) {
                if (this.value != 0.0d || Double.doubleToLongBits(this.value) == Double.doubleToLongBits(val.value)) {
                    return true;
                }
                return false;
            } else if (this.value == this.value || val.value == val.value) {
                return false;
            } else {
                return true;
            }
        }

        private int compareTo(XDouble val) {
            double oval = val.value;
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
                if (this.value == Double.POSITIVE_INFINITY) {
                    this.canonical = "INF";
                } else if (this.value == Double.NEGATIVE_INFINITY) {
                    this.canonical = "-INF";
                } else if (this.value != this.value) {
                    this.canonical = "NaN";
                } else if (this.value == 0.0d) {
                    this.canonical = "0.0E1";
                } else {
                    this.canonical = Double.toString(this.value);
                    if (this.canonical.indexOf(69) == -1) {
                        int len = this.canonical.length();
                        char[] chars = new char[(len + 3)];
                        this.canonical.getChars(0, len, chars, 0);
                        int edp = chars[0] == '-' ? 2 : 1;
                        int i;
                        int i2;
                        if (this.value >= MoPubAdapter.DEFAULT_MOPUB_IMAGE_SCALE || this.value <= -1.0d) {
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

        public double getValue() {
            return this.value;
        }
    }

    public short getAllowedFacets() {
        return (short) 2552;
    }

    public Object getActualValue(String content, ValidationContext context) throws InvalidDatatypeValueException {
        try {
            return new XDouble(content);
        } catch (NumberFormatException e) {
            throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[]{content, SchemaSymbols.ATTVAL_DOUBLE});
        }
    }

    public int compare(Object value1, Object value2) {
        return ((XDouble) value1).compareTo((XDouble) value2);
    }

    public boolean isIdentical(Object value1, Object value2) {
        if (value2 instanceof XDouble) {
            return ((XDouble) value1).isIdentical((XDouble) value2);
        }
        return false;
    }

    static boolean isPossibleFP(String val) {
        int length = val.length();
        for (int i = 0; i < length; i++) {
            char c = val.charAt(i);
            if ((c < '0' || c > '9') && c != '.' && c != '-' && c != '+' && c != 'E' && c != 'e') {
                return false;
            }
        }
        return true;
    }
}
