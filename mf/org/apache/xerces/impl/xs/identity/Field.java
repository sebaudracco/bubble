package mf.org.apache.xerces.impl.xs.identity;

import mf.org.apache.xerces.impl.xpath.XPathException;
import mf.org.apache.xerces.impl.xs.util.ShortListImpl;
import mf.org.apache.xerces.util.SymbolTable;
import mf.org.apache.xerces.util.XMLChar;
import mf.org.apache.xerces.xni.NamespaceContext;
import mf.org.apache.xerces.xs.ShortList;
import mf.org.apache.xerces.xs.XSComplexTypeDefinition;
import mf.org.apache.xerces.xs.XSTypeDefinition;

public class Field {
    protected final IdentityConstraint fIdentityConstraint;
    protected final XPath fXPath;

    protected class Matcher extends XPathMatcher {
        protected boolean fMayMatch = true;
        protected final ValueStore fStore;

        public Matcher(XPath xpath, ValueStore store) {
            super(xpath);
            this.fStore = store;
        }

        protected void matched(Object actualValue, short valueType, ShortList itemValueType, boolean isNil) {
            super.matched(actualValue, valueType, itemValueType, isNil);
            if (isNil && Field.this.fIdentityConstraint.getCategory() == (short) 1) {
                this.fStore.reportError("KeyMatchesNillable", new Object[]{Field.this.fIdentityConstraint.getElementName(), Field.this.fIdentityConstraint.getIdentityConstraintName()});
            }
            this.fStore.addValue(Field.this, this.fMayMatch, actualValue, convertToPrimitiveKind(valueType), convertToPrimitiveKind(itemValueType));
            this.fMayMatch = false;
        }

        private short convertToPrimitiveKind(short valueType) {
            if (valueType <= (short) 20) {
                return valueType;
            }
            if (valueType <= (short) 29) {
                return (short) 2;
            }
            if (valueType <= (short) 42) {
                return (short) 4;
            }
            return valueType;
        }

        private ShortList convertToPrimitiveKind(ShortList itemValueType) {
            if (itemValueType == null) {
                return itemValueType;
            }
            int length = itemValueType.getLength();
            int i = 0;
            while (i < length) {
                short type = itemValueType.item(i);
                if (type != convertToPrimitiveKind(type)) {
                    break;
                }
                i++;
            }
            if (i == length) {
                return itemValueType;
            }
            short[] arr = new short[length];
            for (int j = 0; j < i; j++) {
                arr[j] = itemValueType.item(j);
            }
            while (i < length) {
                arr[i] = convertToPrimitiveKind(itemValueType.item(i));
                i++;
            }
            return new ShortListImpl(arr, arr.length);
        }

        protected void handleContent(XSTypeDefinition type, boolean nillable, Object actualValue, short valueType, ShortList itemValueType) {
            if (type == null || (type.getTypeCategory() == (short) 15 && ((XSComplexTypeDefinition) type).getContentType() != (short) 1)) {
                this.fStore.reportError("cvc-id.3", new Object[]{Field.this.fIdentityConstraint.getName(), Field.this.fIdentityConstraint.getElementName()});
            }
            this.fMatchedString = actualValue;
            matched(this.fMatchedString, valueType, itemValueType, nillable);
        }
    }

    public static class XPath extends mf.org.apache.xerces.impl.xpath.XPath {
        public XPath(String xpath, SymbolTable symbolTable, NamespaceContext context) throws XPathException {
            super(fixupXPath(xpath), symbolTable, context);
            int i = 0;
            while (i < this.fLocationPaths.length) {
                int j = 0;
                while (j < this.fLocationPaths[i].steps.length) {
                    if (this.fLocationPaths[i].steps[j].axis.type != (short) 2 || j >= this.fLocationPaths[i].steps.length - 1) {
                        j++;
                    } else {
                        throw new XPathException("c-fields-xpaths");
                    }
                }
                i++;
            }
        }

        private static String fixupXPath(String xpath) {
            int end = xpath.length();
            boolean whitespace = true;
            for (int offset = 0; offset < end; offset++) {
                char c = xpath.charAt(offset);
                if (whitespace) {
                    if (XMLChar.isSpace(c)) {
                        continue;
                    } else if (c == '.' || c == '/') {
                        whitespace = false;
                    } else if (c != '|') {
                        return fixupXPath2(xpath, offset, end);
                    }
                } else if (c == '|') {
                    whitespace = true;
                }
            }
            return xpath;
        }

        private static String fixupXPath2(String xpath, int offset, int end) {
            StringBuffer buffer = new StringBuffer(end + 2);
            for (int i = 0; i < offset; i++) {
                buffer.append(xpath.charAt(i));
            }
            buffer.append("./");
            boolean whitespace = false;
            while (offset < end) {
                char c = xpath.charAt(offset);
                if (whitespace) {
                    if (!XMLChar.isSpace(c)) {
                        if (c == '.' || c == '/') {
                            whitespace = false;
                        } else if (c != '|') {
                            buffer.append("./");
                            whitespace = false;
                        }
                    }
                } else if (c == '|') {
                    whitespace = true;
                }
                buffer.append(c);
                offset++;
            }
            return buffer.toString();
        }
    }

    public Field(XPath xpath, IdentityConstraint identityConstraint) {
        this.fXPath = xpath;
        this.fIdentityConstraint = identityConstraint;
    }

    public mf.org.apache.xerces.impl.xpath.XPath getXPath() {
        return this.fXPath;
    }

    public IdentityConstraint getIdentityConstraint() {
        return this.fIdentityConstraint;
    }

    public XPathMatcher createMatcher(ValueStore store) {
        return new Matcher(this.fXPath, store);
    }

    public String toString() {
        return this.fXPath.toString();
    }
}
