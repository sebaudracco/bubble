package mf.org.apache.xerces.impl.xs.traversers;

import mf.org.apache.xerces.impl.xpath.XPathException;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import mf.org.apache.xerces.impl.xs.identity.Field;
import mf.org.apache.xerces.impl.xs.identity.IdentityConstraint;
import mf.org.apache.xerces.impl.xs.identity.Selector;
import mf.org.apache.xerces.impl.xs.identity.Selector.XPath;
import mf.org.apache.xerces.util.DOMUtil;
import mf.org.apache.xerces.util.XMLChar;
import mf.org.w3c.dom.Element;

class XSDAbstractIDConstraintTraverser extends XSDAbstractTraverser {
    public XSDAbstractIDConstraintTraverser(XSDHandler handler, XSAttributeChecker gAttrCheck) {
        super(handler, gAttrCheck);
    }

    boolean traverseIdentityConstraint(IdentityConstraint ic, Element icElem, XSDocumentInfo schemaDoc, Object[] icElemAttrs) {
        XPathException e;
        Element sElem = DOMUtil.getFirstChildElement(icElem);
        if (sElem == null) {
            reportSchemaError("s4s-elt-must-match.2", new Object[]{"identity constraint", "(annotation?, selector, field+)"}, icElem);
            return false;
        }
        if (DOMUtil.getLocalName(sElem).equals(SchemaSymbols.ELT_ANNOTATION)) {
            ic.addAnnotation(traverseAnnotationDecl(sElem, icElemAttrs, false, schemaDoc));
            sElem = DOMUtil.getNextSiblingElement(sElem);
            if (sElem == null) {
                reportSchemaError("s4s-elt-must-match.2", new Object[]{"identity constraint", "(annotation?, selector, field+)"}, icElem);
                return false;
            }
        }
        String text = DOMUtil.getSyntheticAnnotation(icElem);
        if (text != null) {
            ic.addAnnotation(traverseSyntheticAnnotation(icElem, text, icElemAttrs, false, schemaDoc));
        }
        if (DOMUtil.getLocalName(sElem).equals(SchemaSymbols.ELT_SELECTOR)) {
            Object[] attrValues = this.fAttrChecker.checkAttributes(sElem, false, schemaDoc);
            Element selChild = DOMUtil.getFirstChildElement(sElem);
            if (selChild != null) {
                if (DOMUtil.getLocalName(selChild).equals(SchemaSymbols.ELT_ANNOTATION)) {
                    ic.addAnnotation(traverseAnnotationDecl(selChild, attrValues, false, schemaDoc));
                    selChild = DOMUtil.getNextSiblingElement(selChild);
                } else {
                    reportSchemaError("s4s-elt-must-match.1", new Object[]{SchemaSymbols.ELT_SELECTOR, "(annotation?)", DOMUtil.getLocalName(selChild)}, selChild);
                }
                if (selChild != null) {
                    reportSchemaError("s4s-elt-must-match.1", new Object[]{SchemaSymbols.ELT_SELECTOR, "(annotation?)", DOMUtil.getLocalName(selChild)}, selChild);
                }
            } else {
                text = DOMUtil.getSyntheticAnnotation(sElem);
                if (text != null) {
                    ic.addAnnotation(traverseSyntheticAnnotation(icElem, text, attrValues, false, schemaDoc));
                }
            }
            String sText = attrValues[XSAttributeChecker.ATTIDX_XPATH];
            if (sText == null) {
                reportSchemaError("s4s-att-must-appear", new Object[]{SchemaSymbols.ELT_SELECTOR, SchemaSymbols.ATT_XPATH}, sElem);
                return false;
            }
            try {
                XPath xPath = new XPath(XMLChar.trim(sText), this.fSymbolTable, schemaDoc.fNamespaceSupport);
                try {
                    ic.setSelector(new Selector(xPath, ic));
                    this.fAttrChecker.returnAttrArray(attrValues, schemaDoc);
                    Element fElem = DOMUtil.getNextSiblingElement(sElem);
                    if (fElem == null) {
                        reportSchemaError("s4s-elt-must-match.2", new Object[]{"identity constraint", "(annotation?, selector, field+)"}, sElem);
                        return false;
                    }
                    while (fElem != null) {
                        if (DOMUtil.getLocalName(fElem).equals(SchemaSymbols.ELT_FIELD)) {
                            attrValues = this.fAttrChecker.checkAttributes(fElem, false, schemaDoc);
                            Element fieldChild = DOMUtil.getFirstChildElement(fElem);
                            if (fieldChild != null && DOMUtil.getLocalName(fieldChild).equals(SchemaSymbols.ELT_ANNOTATION)) {
                                ic.addAnnotation(traverseAnnotationDecl(fieldChild, attrValues, false, schemaDoc));
                                fieldChild = DOMUtil.getNextSiblingElement(fieldChild);
                            }
                            if (fieldChild != null) {
                                reportSchemaError("s4s-elt-must-match.1", new Object[]{SchemaSymbols.ELT_FIELD, "(annotation?)", DOMUtil.getLocalName(fieldChild)}, fieldChild);
                            } else {
                                text = DOMUtil.getSyntheticAnnotation(fElem);
                                if (text != null) {
                                    ic.addAnnotation(traverseSyntheticAnnotation(icElem, text, attrValues, false, schemaDoc));
                                }
                            }
                            String fText = attrValues[XSAttributeChecker.ATTIDX_XPATH];
                            if (fText == null) {
                                reportSchemaError("s4s-att-must-appear", new Object[]{SchemaSymbols.ELT_FIELD, SchemaSymbols.ATT_XPATH}, fElem);
                                this.fAttrChecker.returnAttrArray(attrValues, schemaDoc);
                                return false;
                            }
                            try {
                                ic.addField(new Field(new Field.XPath(XMLChar.trim(fText), this.fSymbolTable, schemaDoc.fNamespaceSupport), ic));
                                fElem = DOMUtil.getNextSiblingElement(fElem);
                                this.fAttrChecker.returnAttrArray(attrValues, schemaDoc);
                            } catch (XPathException e2) {
                                reportSchemaError(e2.getKey(), new Object[]{fText}, fElem);
                                this.fAttrChecker.returnAttrArray(attrValues, schemaDoc);
                                return false;
                            }
                        }
                        reportSchemaError("s4s-elt-must-match.1", new Object[]{"identity constraint", "(annotation?, selector, field+)", SchemaSymbols.ELT_FIELD}, fElem);
                        fElem = DOMUtil.getNextSiblingElement(fElem);
                    }
                    if (ic.getFieldCount() > 0) {
                        return true;
                    }
                    return false;
                } catch (XPathException e3) {
                    e2 = e3;
                    XPath xPath2 = xPath;
                    reportSchemaError(e2.getKey(), new Object[]{sText}, sElem);
                    this.fAttrChecker.returnAttrArray(attrValues, schemaDoc);
                    return false;
                }
            } catch (XPathException e4) {
                e2 = e4;
                reportSchemaError(e2.getKey(), new Object[]{sText}, sElem);
                this.fAttrChecker.returnAttrArray(attrValues, schemaDoc);
                return false;
            }
        }
        reportSchemaError("s4s-elt-must-match.1", new Object[]{"identity constraint", "(annotation?, selector, field+)", SchemaSymbols.ELT_SELECTOR}, sElem);
        return false;
    }
}
