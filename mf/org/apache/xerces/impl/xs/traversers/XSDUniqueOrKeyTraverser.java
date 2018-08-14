package mf.org.apache.xerces.impl.xs.traversers;

import mf.org.apache.xerces.impl.xs.SchemaGrammar;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import mf.org.apache.xerces.impl.xs.XSElementDecl;
import mf.org.apache.xerces.impl.xs.identity.IdentityConstraint;
import mf.org.apache.xerces.impl.xs.identity.UniqueOrKey;
import mf.org.apache.xerces.util.DOMUtil;
import mf.org.w3c.dom.Element;

class XSDUniqueOrKeyTraverser extends XSDAbstractIDConstraintTraverser {
    public XSDUniqueOrKeyTraverser(XSDHandler handler, XSAttributeChecker gAttrCheck) {
        super(handler, gAttrCheck);
    }

    void traverse(Element uElem, XSElementDecl element, XSDocumentInfo schemaDoc, SchemaGrammar grammar) {
        Object[] attrValues = this.fAttrChecker.checkAttributes(uElem, false, schemaDoc);
        String uName = attrValues[XSAttributeChecker.ATTIDX_NAME];
        if (uName == null) {
            reportSchemaError("s4s-att-must-appear", new Object[]{DOMUtil.getLocalName(uElem), SchemaSymbols.ATT_NAME}, uElem);
            this.fAttrChecker.returnAttrArray(attrValues, schemaDoc);
            return;
        }
        UniqueOrKey uniqueOrKey;
        if (DOMUtil.getLocalName(uElem).equals(SchemaSymbols.ELT_UNIQUE)) {
            uniqueOrKey = new UniqueOrKey(schemaDoc.fTargetNamespace, uName, element.fName, (short) 3);
        } else {
            uniqueOrKey = new UniqueOrKey(schemaDoc.fTargetNamespace, uName, element.fName, (short) 1);
        }
        if (traverseIdentityConstraint(uniqueOrKey, uElem, schemaDoc, attrValues)) {
            if (grammar.getIDConstraintDecl(uniqueOrKey.getIdentityConstraintName()) == null) {
                grammar.addIDConstraintDecl(element, uniqueOrKey);
            }
            String loc = this.fSchemaHandler.schemaDocument2SystemId(schemaDoc);
            IdentityConstraint idc = grammar.getIDConstraintDecl(uniqueOrKey.getIdentityConstraintName(), loc);
            if (idc == null) {
                grammar.addIDConstraintDecl(element, uniqueOrKey, loc);
            }
            if (this.fSchemaHandler.fTolerateDuplicates) {
                if (idc == null || !(idc instanceof UniqueOrKey)) {
                    this.fSchemaHandler.addIDConstraintDecl(uniqueOrKey);
                } else {
                    this.fSchemaHandler.addIDConstraintDecl(uniqueOrKey);
                }
            }
        }
        this.fAttrChecker.returnAttrArray(attrValues, schemaDoc);
    }
}
