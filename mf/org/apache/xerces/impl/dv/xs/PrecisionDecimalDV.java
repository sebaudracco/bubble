package mf.org.apache.xerces.impl.dv.xs;

import mf.org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import mf.org.apache.xerces.impl.dv.ValidationContext;

class PrecisionDecimalDV extends TypeValidator {

    static class XPrecisionDecimal {
        private String canonical;
        int fracDigits = 0;
        String fvalue = "";
        int intDigits = 0;
        String ivalue = "";
        int pvalue = 0;
        int sign = 1;
        int totalDigits = 0;

        XPrecisionDecimal(String content) throws NumberFormatException {
            if (content.equals("NaN")) {
                this.ivalue = content;
                this.sign = 0;
            }
            if (content.equals("+INF") || content.equals("INF") || content.equals("-INF")) {
                if (content.charAt(0) == '+') {
                    content = content.substring(1);
                }
                this.ivalue = content;
                return;
            }
            initD(content);
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
                if (content.charAt(intEnd) != '.' && content.charAt(intEnd) != 'E' && content.charAt(intEnd) != 'e') {
                    throw new NumberFormatException();
                } else if (content.charAt(intEnd) == '.') {
                    fracStart = intEnd + 1;
                    fracEnd = fracStart;
                    while (fracEnd < len && TypeValidator.isDigit(content.charAt(fracEnd))) {
                        fracEnd++;
                    }
                } else {
                    this.pvalue = Integer.parseInt(content.substring(intEnd + 1, len));
                }
            }
            if (intStart == intEnd && fracStart == fracEnd) {
                throw new NumberFormatException();
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
            if (this.intDigits > 0) {
                this.ivalue = content.substring(actualIntStart, intEnd);
            }
            if (this.fracDigits > 0) {
                this.fvalue = content.substring(fracStart, fracEnd);
                if (fracEnd < len) {
                    this.pvalue = Integer.parseInt(content.substring(fracEnd + 1, len));
                }
            }
            this.totalDigits = this.intDigits + this.fracDigits;
        }

        public boolean equals(Object val) {
            if (val == this) {
                return true;
            }
            if (!(val instanceof XPrecisionDecimal)) {
                return false;
            }
            if (compareTo((XPrecisionDecimal) val) != 0) {
                return false;
            }
            return true;
        }

        private int compareFractionalPart(XPrecisionDecimal oval) {
            if (this.fvalue.equals(oval.fvalue)) {
                return 0;
            }
            StringBuffer temp1 = new StringBuffer(this.fvalue);
            StringBuffer temp2 = new StringBuffer(oval.fvalue);
            truncateTrailingZeros(temp1, temp2);
            return temp1.toString().compareTo(temp2.toString());
        }

        private void truncateTrailingZeros(StringBuffer fValue, StringBuffer otherFValue) {
            int i = fValue.length() - 1;
            while (i >= 0 && fValue.charAt(i) == '0') {
                fValue.deleteCharAt(i);
                i--;
            }
            i = otherFValue.length() - 1;
            while (i >= 0 && otherFValue.charAt(i) == '0') {
                otherFValue.deleteCharAt(i);
                i--;
            }
        }

        public int compareTo(XPrecisionDecimal val) {
            if (this.sign == 0) {
                return 2;
            }
            if (this.ivalue.equals("INF") || val.ivalue.equals("INF")) {
                if (this.ivalue.equals(val.ivalue)) {
                    return 0;
                }
                if (this.ivalue.equals("INF")) {
                    return 1;
                }
                return -1;
            } else if (this.ivalue.equals("-INF") || val.ivalue.equals("-INF")) {
                if (this.ivalue.equals(val.ivalue)) {
                    return 0;
                }
                if (this.ivalue.equals("-INF")) {
                    return -1;
                }
                return 1;
            } else if (this.sign == val.sign) {
                return this.sign * compare(val);
            } else {
                if (this.sign <= val.sign) {
                    return -1;
                }
                return 1;
            }
        }

        private int compare(XPrecisionDecimal val) {
            if (this.pvalue == 0 && val.pvalue == 0) {
                return intComp(val);
            }
            if (this.pvalue == val.pvalue) {
                return intComp(val);
            }
            if (this.intDigits + this.pvalue != val.intDigits + val.pvalue) {
                return this.intDigits + this.pvalue > val.intDigits + val.pvalue ? 1 : -1;
            } else {
                int expDiff;
                StringBuffer buffer;
                StringBuffer fbuffer;
                int i;
                if (this.pvalue > val.pvalue) {
                    expDiff = this.pvalue - val.pvalue;
                    buffer = new StringBuffer(this.ivalue);
                    fbuffer = new StringBuffer(this.fvalue);
                    for (i = 0; i < expDiff; i++) {
                        if (i < this.fracDigits) {
                            buffer.append(this.fvalue.charAt(i));
                            fbuffer.deleteCharAt(i);
                        } else {
                            buffer.append('0');
                        }
                    }
                    return compareDecimal(buffer.toString(), val.ivalue, fbuffer.toString(), val.fvalue);
                }
                expDiff = val.pvalue - this.pvalue;
                buffer = new StringBuffer(val.ivalue);
                fbuffer = new StringBuffer(val.fvalue);
                for (i = 0; i < expDiff; i++) {
                    if (i < val.fracDigits) {
                        buffer.append(val.fvalue.charAt(i));
                        fbuffer.deleteCharAt(i);
                    } else {
                        buffer.append('0');
                    }
                }
                return compareDecimal(this.ivalue, buffer.toString(), this.fvalue, fbuffer.toString());
            }
        }

        private int intComp(XPrecisionDecimal val) {
            if (this.intDigits != val.intDigits) {
                return this.intDigits > val.intDigits ? 1 : -1;
            } else {
                return compareDecimal(this.ivalue, val.ivalue, this.fvalue, val.fvalue);
            }
        }

        private int compareDecimal(String iValue, String fValue, String otherIValue, String otherFValue) {
            int ret = iValue.compareTo(otherIValue);
            if (ret != 0) {
                if (ret > 0) {
                    return 1;
                }
                return -1;
            } else if (fValue.equals(otherFValue)) {
                return 0;
            } else {
                StringBuffer temp1 = new StringBuffer(fValue);
                StringBuffer temp2 = new StringBuffer(otherFValue);
                truncateTrailingZeros(temp1, temp2);
                ret = temp1.toString().compareTo(temp2.toString());
                if (ret == 0) {
                    return 0;
                }
                if (ret <= 0) {
                    return -1;
                }
                return 1;
            }
        }

        public synchronized String toString() {
            if (this.canonical == null) {
                makeCanonical();
            }
            return this.canonical;
        }

        private void makeCanonical() {
            this.canonical = "TBD by Working Group";
        }

        public boolean isIdentical(XPrecisionDecimal decimal) {
            if (this.ivalue.equals(decimal.ivalue) && (this.ivalue.equals("INF") || this.ivalue.equals("-INF") || this.ivalue.equals("NaN"))) {
                return true;
            }
            if (this.sign == decimal.sign && this.intDigits == decimal.intDigits && this.fracDigits == decimal.fracDigits && this.pvalue == decimal.pvalue && this.ivalue.equals(decimal.ivalue) && this.fvalue.equals(decimal.fvalue)) {
                return true;
            }
            return false;
        }
    }

    PrecisionDecimalDV() {
    }

    public short getAllowedFacets() {
        return (short) 4088;
    }

    public Object getActualValue(String content, ValidationContext context) throws InvalidDatatypeValueException {
        try {
            return new XPrecisionDecimal(content);
        } catch (NumberFormatException e) {
            throw new InvalidDatatypeValueException("cvc-datatype-valid.1.2.1", new Object[]{content, "precisionDecimal"});
        }
    }

    public int compare(Object value1, Object value2) {
        return ((XPrecisionDecimal) value1).compareTo((XPrecisionDecimal) value2);
    }

    public int getFractionDigits(Object value) {
        return ((XPrecisionDecimal) value).fracDigits;
    }

    public int getTotalDigits(Object value) {
        return ((XPrecisionDecimal) value).totalDigits;
    }

    public boolean isIdentical(Object value1, Object value2) {
        if ((value2 instanceof XPrecisionDecimal) && (value1 instanceof XPrecisionDecimal)) {
            return ((XPrecisionDecimal) value1).isIdentical((XPrecisionDecimal) value2);
        }
        return false;
    }
}
