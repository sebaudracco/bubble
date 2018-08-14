package mf.org.apache.xerces.impl.xs.traversers;

import mf.org.apache.xerces.impl.xs.SchemaGrammar;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import mf.org.apache.xerces.impl.xs.XSAnnotationImpl;
import mf.org.apache.xerces.impl.xs.XSNotationDecl;
import mf.org.apache.xerces.impl.xs.util.XSObjectListImpl;
import mf.org.apache.xerces.util.DOMUtil;
import mf.org.apache.xerces.xs.XSObjectList;
import mf.org.w3c.dom.Element;

class XSDNotationTraverser extends XSDAbstractTraverser {
    XSDNotationTraverser(XSDHandler handler, XSAttributeChecker gAttrCheck) {
        super(handler, gAttrCheck);
    }

    XSNotationDecl traverse(Element elmNode, XSDocumentInfo schemaDoc, SchemaGrammar grammar) {
        Object[] attrValues = this.fAttrChecker.checkAttributes(elmNode, true, schemaDoc);
        String nameAttr = attrValues[XSAttributeChecker.ATTIDX_NAME];
        String publicAttr = attrValues[XSAttributeChecker.ATTIDX_PUBLIC];
        String systemAttr = attrValues[XSAttributeChecker.ATTIDX_SYSTEM];
        if (nameAttr == null) {
            reportSchemaError("s4s-att-must-appear", new Object[]{SchemaSymbols.ELT_NOTATION, SchemaSymbols.ATT_NAME}, elmNode);
            this.fAttrChecker.returnAttrArray(attrValues, schemaDoc);
            return null;
        }
        XSObjectList annotations;
        if (systemAttr == null && publicAttr == null) {
            reportSchemaError("PublicSystemOnNotation", null, elmNode);
            publicAttr = "missing";
        }
        XSNotationDecl notation = new XSNotationDecl();
        notation.fName = nameAttr;
        notation.fTargetNamespace = schemaDoc.fTargetNamespace;
        notation.fPublicId = publicAttr;
        notation.fSystemId = systemAttr;
        Element content = DOMUtil.getFirstChildElement(elmNode);
        XSAnnotationImpl annotation = null;
        if (content == null || !DOMUtil.getLocalName(content).equals(SchemaSymbols.ELT_ANNOTATION)) {
            String text = DOMUtil.getSyntheticAnnotation(elmNode);
            if (text != null) {
                annotation = traverseSyntheticAnnotation(elmNode, text, attrValues, false, schemaDoc);
            }
        } else {
            annotation = traverseAnnotationDecl(content, attrValues, false, schemaDoc);
            content = DOMUtil.getNextSiblingElement(content);
        }
        if (annotation != null) {
            annotations = new XSObjectListImpl();
            ((XSObjectListImpl) annotations).addXSObject(annotation);
        } else {
            annotations = XSObjectListImpl.EMPTY_LIST;
        }
        notation.fAnnotations = annotations;
        if (content != null) {
            reportSchemaError("s4s-elt-must-match.1", new Object[]{SchemaSymbols.ELT_NOTATION, "(annotation?)", DOMUtil.getLocalName(content)}, content);
        }
        if (grammar.getGlobalNotationDecl(notation.fName) == null) {
            grammar.addGlobalNotationDecl(notation);
        }
        String loc = this.fSchemaHandler.schemaDocument2SystemId(schemaDoc);
        XSNotationDecl notation2 = grammar.getGlobalNotationDecl(notation.fName, loc);
        if (notation2 == null) {
            grammar.addGlobalNotationDecl(notation, loc);
        }
        if (this.fSchemaHandler.fTolerateDuplicates) {
            if (notation2 != null) {
                notation = notation2;
            }
            this.fSchemaHandler.addGlobalNotationDecl(notation);
        }
        this.fAttrChecker.returnAttrArray(attrValues, schemaDoc);
        return notation;
    }
}
