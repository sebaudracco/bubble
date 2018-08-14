package mf.org.apache.xerces.impl.xs.traversers;

import mf.org.apache.xerces.impl.xs.SchemaGrammar;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import mf.org.apache.xerces.impl.xs.XSElementDecl;
import mf.org.apache.xerces.impl.xs.identity.IdentityConstraint;
import mf.org.apache.xerces.impl.xs.identity.KeyRef;
import mf.org.apache.xerces.impl.xs.identity.UniqueOrKey;
import mf.org.apache.xerces.xni.QName;
import mf.org.w3c.dom.Element;

class XSDKeyrefTraverser extends XSDAbstractIDConstraintTraverser {
    public XSDKeyrefTraverser(XSDHandler handler, XSAttributeChecker gAttrCheck) {
        super(handler, gAttrCheck);
    }

    void traverse(Element krElem, XSElementDecl element, XSDocumentInfo schemaDoc, SchemaGrammar grammar) {
        Object[] attrValues = this.fAttrChecker.checkAttributes(krElem, false, schemaDoc);
        String krName = attrValues[XSAttributeChecker.ATTIDX_NAME];
        if (krName == null) {
            reportSchemaError("s4s-att-must-appear", new Object[]{SchemaSymbols.ELT_KEYREF, SchemaSymbols.ATT_NAME}, krElem);
            this.fAttrChecker.returnAttrArray(attrValues, schemaDoc);
            return;
        }
        QName kName = attrValues[XSAttributeChecker.ATTIDX_REFER];
        if (kName == null) {
            reportSchemaError("s4s-att-must-appear", new Object[]{SchemaSymbols.ELT_KEYREF, SchemaSymbols.ATT_REFER}, krElem);
            this.fAttrChecker.returnAttrArray(attrValues, schemaDoc);
            return;
        }
        UniqueOrKey key = null;
        IdentityConstraint ret = (IdentityConstraint) this.fSchemaHandler.getGlobalDecl(schemaDoc, 5, kName, krElem);
        if (ret != null) {
            if (ret.getCategory() == (short) 1 || ret.getCategory() == (short) 3) {
                key = (UniqueOrKey) ret;
            } else {
                reportSchemaError("src-resolve", new Object[]{kName.rawname, "identity constraint key/unique"}, krElem);
            }
        }
        if (key == null) {
            this.fAttrChecker.returnAttrArray(attrValues, schemaDoc);
            return;
        }
        KeyRef keyRef = new KeyRef(schemaDoc.fTargetNamespace, krName, element.fName, key);
        if (traverseIdentityConstraint(keyRef, krElem, schemaDoc, attrValues)) {
            if (key.getFieldCount() != keyRef.getFieldCount()) {
                reportSchemaError("c-props-correct.2", new Object[]{krName, key.getIdentityConstraintName()}, krElem);
            } else {
                if (grammar.getIDConstraintDecl(keyRef.getIdentityConstraintName()) == null) {
                    grammar.addIDConstraintDecl(element, keyRef);
                }
                String loc = this.fSchemaHandler.schemaDocument2SystemId(schemaDoc);
                IdentityConstraint idc = grammar.getIDConstraintDecl(keyRef.getIdentityConstraintName(), loc);
                if (idc == null) {
                    grammar.addIDConstraintDecl(element, keyRef, loc);
                }
                if (this.fSchemaHandler.fTolerateDuplicates) {
                    if (idc != null && (idc instanceof KeyRef)) {
                        keyRef = (KeyRef) idc;
                    }
                    this.fSchemaHandler.addIDConstraintDecl(keyRef);
                }
            }
        }
        this.fAttrChecker.returnAttrArray(attrValues, schemaDoc);
    }
}
