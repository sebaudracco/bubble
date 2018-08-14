package mf.org.apache.xerces.impl.dv.xs;

import java.math.BigDecimal;
import java.math.BigInteger;
import mf.org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import mf.org.apache.xerces.impl.dv.ValidationContext;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import mf.org.apache.xerces.xs.datatypes.XSDecimal;

public class DecimalDV extends TypeValidator {

    static class XDecimal implements XSDecimal {
        private String canonical;
        int fracDigits = 0;
        String fvalue = "";
        int intDigits = 0;
        boolean integer = false;
        String ivalue = "";
        int sign = 1;
        int totalDigits = 0;

        XDecimal(String content) throws NumberFormatException {
            initD(content);
        }

        XDecimal(String content, boolean integer) throws NumberFormatException {
            if (integer) {
                initI(content);
            } else {
                initD(content);
            }
        }

        void initD(String content) throws NumberFormatException {
            int len = content.length();
            if (len == 0) {
                throw new NumberFormatException();
            }
            int intStart = 0;
            int fracStart = 0;
            int fracEnd = 0;
            if (content.charAt(0) == '+') {
                intStart = 1;
            } else if (content.charAt(0) == '-') {
                intStart = 1;
                this.sign = -1;
            }
            int actualIntStart = intStart;
            while (actualIntStart < len && content.charAt(actualIntStart) == '0') {
                actualIntStart++;
            }
            int intEnd = actualIntStart;
            while (intEnd < len && TypeValidator.isDigit(content.charAt(intEnd))) {
                intEnd++;
            }
            if (intEnd < len) {
                if (content.charAt(intEnd) != '.') {
                    throw new NumberFormatException();
                }
                fracStart = intEnd + 1;
                fracEnd = len;
            }
            if (intStart == intEnd && fracStart == fracEnd) {
                throw new NumberFormatException();
            }
            while (fracEnd > fracStart && content.charAt(fracEnd - 1) == '0') {
                fracEnd--;
            }
            int fracPos = fracStart;
            while (fracPos < fracEnd) {
                if (TypeValidator.isDigit(content.charAt(fracPos))) {
                    fracPos++;
                } else {
                    throw new NumberFormatException();
                }
            }
            this.intDigits = intEnd - actualIntStart;
            this.fracDigits = fracEnd - fracStart;
            this.totalDigits = this.intDigits + this.fracDigits;
            if (this.intDigits > 0) {
                this.ivalue = content.substring(actualIntStart, intEnd);
                if (this.fracDigits > 0) {
                    this.fvalue = content.substring(fracStart, fracEnd);
                }
            } else if (this.fracDigits > 0) {
                this.fvalue = content.substring(fracStart, fracEnd);
            } else {
                this.sign = 0;
            }
        }

        void initI(String content) throws NumberFormatException {
            int len = content.length();
            if (len == 0) {
                throw new NumberFormatException();
            }
            int intStart = 0;
            if (content.charAt(0) == '+') {
                intStart = 1;
            } else if (content.charAt(0) == '-') {
                intStart = 1;
                this.sign = -1;
            }
            int actualIntStart = intStart;
            while (actualIntStart < len && content.charAt(actualIntStart) == '0') {
                actualIntStart++;
            }
            int intEnd = actualIntStart;
            while (intEnd < len && TypeValidator.isDigit(content.charAt(intEnd))) {
                intEnd++;
            }
            if (intEnd < len) {
                throw new NumberFormatException();
            } else if (intStart == intEnd) {
                throw new NumberFormatException();
            } else {
                this.intDigits = intEnd - actualIntStart;
                this.fracDigits = 0;
                this.totalDigits = this.intDigits;
                if (this.intDigits > 0) {
                    this.ivalue = content.substring(actualIntStart, intEnd);
                } else {
                    this.sign = 0;
                }
                this.integer = true;
            }
        }

        public boolean equals(Object val) {
            if (val == this) {
                return true;
            }
            if (!(val instanceof XDecimal)) {
                return false;
            }
            XDecimal oval = (XDecimal) val;
            if (this.sign != oval.sign) {
                return false;
            }
            if (this.sign == 0) {
                return true;
            }
            if (this.intDigits == oval.intDigits && this.fracDigits == oval.fracDigits && this.ivalue.equals(oval.ivalue) && this.fvalue.equals(oval.fvalue)) {
                return true;
            }
            return false;
        }

        public int compareTo(XDecimal val) {
            if (this.sign != val.sign) {
                return this.sign > val.sign ? 1 : -1;
            } else {
                if (this.sign == 0) {
                    return 0;
                }
                return this.sign * intComp(val);
            }
        }

        private int intComp(XDecimal val) {
            if (this.intDigits == val.intDigits) {
                int ret = this.ivalue.compareTo(val.ivalue);
                if (ret == 0) {
                    ret = this.fvalue.compareTo(val.fvalue);
                    if (ret == 0) {
                        return 0;
                    }
                    if (ret <= 0) {
                        return -1;
                    }
                    return 1;
                } else if (ret <= 0) {
                    return -1;
                } else {
                    return 1;
                }
            } else if (this.intDigits > val.intDigits) {
                return 1;
            } else {
                return -1;
            }
        }

        public synchronized String toString() {
            if (this.canonical == null) {
                makeCanonical();
            }
            return this.canonical;
        }

        private void makeCanonical() {
            if (this.sign == 0) {
                if (this.integer) {
                    this.canonical = SchemaSymbols.ATTVAL_FALSE_0;
                } else {
                    this.canonical = "0.0";
                }
            } else if (!this.integer || this.sign <= 0) {
                StringBuffer buffer = new StringBuffer(this.totalDigits + 3);
                if (this.sign == -1) {
                    buffer.append('-');
                }
                if (this.intDigits != 0) {
                    buffer.append(this.ivalue);
                } else {
                    buffer.append('0');
                }
                if (!this.integer) {
                    buffer.append('.');
                    if (this.fracDigits != 0) {
                        buffer.append(this.fvalue);
                    } else {
                        buffer.append('0');
                    }
                }
                this.canonical = buffer.toString();
            } else {
                this.canonical = this.ivalue;
            }
        }

        public BigDecimal getBigDecimal() {
            if (this.sign == 0) {
                return new BigDecimal(BigInteger.ZERO);
            }
            return new BigDecimal(toString());
        }

        public BigInteger getBigInteger() throws NumberFormatException {
            if (this.fracDigits != 0) {
                throw new NumberFormatException();
            } else if (this.sign == 0) {
                return BigInteger.ZERO;
            } else {
                if (this.sign == 1) {
                    return new BigInteger(this.ivalue);
                }
                return new BigInteger("-" + this.ivalue);
            }
        }

        public long getLong() throws NumberFormatException {
            if (this.fracDigits != 0) {
                throw new NumberFormatException();
            } else if (this.sign == 0) {
                return 0;
            } else {
                if (this.sign == 1) {
                    return Long.parseLong(this.ivalue);
                }
                return Long.parseLong("-" + this.ivalue);
            }
        }

        public int getInt() throws NumberFormatException {
            if (this.fracDigits != 0) {
                throw new NumberFormatException();
            } else if (this.sign == 0) {
                return 0;
            } else {
                if (this.sign == 1) {
                    return Integer.parseInt(this.ivalue);
                }
                return Integer.parseInt("-" + this.ivalue);
            }
        }

        public short getShort() throws NumberFormatException {
            if (this.fracDigits != 0) {
                throw new NumberFormatException();
            } else if (this.sign == 0) {
                return (short) 0;
            } else {
                if (this.sign == 1) {
                    return Short.parseShort(this.ivalue);
                }
                return Short.parseShort("-" + this.ivalue);
            }
        }

        public byte getByte() throws NumberFormatException {
            if (this.fracDigits != 0) {
                throw new NumberFormatException();
            } else if (this.sign == 0) {
                return (byte) 0;
            } else {
                if (this.sign == 1) {
                    return Byte.parseByte(this.ivalue);
                }
                return Byte.parseByte("-" + this.ivalue);
            }
        }
    }

    public final short getAllowedFacets() {
        return (short) 4088;
    }

    public Object getActualValue(String content, ValidationContext context) throws InvalidDatatypeValueException {
        try {
            return new XDecimal(content);
        } catch (NumberFormatException e) {
            throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[]{content, SchemaSymbols.ATTVAL_DECIMAL});
        }
    }

    public final int compare(Object value1, Object value2) {
        return ((XDecimal) value1).compareTo((XDecimal) value2);
    }

    public final int getTotalDigits(Object value) {
        return ((XDecimal) value).totalDigits;
    }

    public final int getFractionDigits(Object value) {
        return ((XDecimal) value).fracDigits;
    }
}
