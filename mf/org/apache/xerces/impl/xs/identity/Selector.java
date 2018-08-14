package mf.org.apache.xerces.impl.xs.identity;

import com.github.lzyzsd.jsbridge.BridgeUtil;
import mf.org.apache.xerces.impl.xpath.XPathException;
import mf.org.apache.xerces.util.SymbolTable;
import mf.org.apache.xerces.util.XMLChar;
import mf.org.apache.xerces.xni.NamespaceContext;
import mf.org.apache.xerces.xni.QName;
import mf.org.apache.xerces.xni.XMLAttributes;
import mf.org.apache.xerces.xs.ShortList;
import mf.org.apache.xerces.xs.XSTypeDefinition;

public class Selector {
    protected IdentityConstraint fIDConstraint;
    protected final IdentityConstraint fIdentityConstraint;
    protected final XPath fXPath;

    public class Matcher extends XPathMatcher {
        protected int fElementDepth;
        protected final FieldActivator fFieldActivator;
        protected final int fInitialDepth;
        protected int fMatchedDepth;

        public Matcher(XPath xpath, FieldActivator activator, int initialDepth) {
            super(xpath);
            this.fFieldActivator = activator;
            this.fInitialDepth = initialDepth;
        }

        public void startDocumentFragment() {
            super.startDocumentFragment();
            this.fElementDepth = 0;
            this.fMatchedDepth = -1;
        }

        public void startElement(QName element, XMLAttributes attributes) {
            super.startElement(element, attributes);
            this.fElementDepth++;
            if (isMatched()) {
                this.fMatchedDepth = this.fElementDepth;
                this.fFieldActivator.startValueScopeFor(Selector.this.fIdentityConstraint, this.fInitialDepth);
                int count = Selector.this.fIdentityConstraint.getFieldCount();
                for (int i = 0; i < count; i++) {
                    this.fFieldActivator.activateField(Selector.this.fIdentityConstraint.getFieldAt(i), this.fInitialDepth).startElement(element, attributes);
                }
            }
        }

        public void endElement(QName element, XSTypeDefinition type, boolean nillable, Object actualValue, short valueType, ShortList itemValueType) {
            super.endElement(element, type, nillable, actualValue, valueType, itemValueType);
            int i = this.fElementDepth;
            this.fElementDepth = i - 1;
            if (i == this.fMatchedDepth) {
                this.fMatchedDepth = -1;
                this.fFieldActivator.endValueScopeFor(Selector.this.fIdentityConstraint, this.fInitialDepth);
            }
        }

        public IdentityConstraint getIdentityConstraint() {
            return Selector.this.fIdentityConstraint;
        }

        public int getInitialDepth() {
            return this.fInitialDepth;
        }
    }

    public static class XPath extends mf.org.apache.xerces.impl.xpath.XPath {
        public XPath(String xpath, SymbolTable symbolTable, NamespaceContext context) throws XPathException {
            super(normalize(xpath), symbolTable, context);
            for (int i = 0; i < this.fLocationPaths.length; i++) {
                if (this.fLocationPaths[i].steps[this.fLocationPaths[i].steps.length - 1].axis.type == (short) 2) {
                    throw new XPathException("c-selector-xpath");
                }
            }
        }

        private static String normalize(String xpath) {
            StringBuffer modifiedXPath = new StringBuffer(xpath.length() + 5);
            while (true) {
                if (!(XMLChar.trim(xpath).startsWith(BridgeUtil.SPLIT_MARK) || XMLChar.trim(xpath).startsWith("."))) {
                    modifiedXPath.append("./");
                }
                int unionIndex = xpath.indexOf(124);
                if (unionIndex == -1) {
                    modifiedXPath.append(xpath);
                    return modifiedXPath.toString();
                }
                modifiedXPath.append(xpath.substring(0, unionIndex + 1));
                xpath = xpath.substring(unionIndex + 1, xpath.length());
            }
        }
    }

    public Selector(XPath xpath, IdentityConstraint identityConstraint) {
        this.fXPath = xpath;
        this.fIdentityConstraint = identityConstraint;
    }

    public mf.org.apache.xerces.impl.xpath.XPath getXPath() {
        return this.fXPath;
    }

    public IdentityConstraint getIDConstraint() {
        return this.fIdentityConstraint;
    }

    public XPathMatcher createMatcher(FieldActivator activator, int initialDepth) {
        return new Matcher(this.fXPath, activator, initialDepth);
    }

    public String toString() {
        return this.fXPath.toString();
    }
}
