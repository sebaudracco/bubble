package mf.org.apache.xerces.impl.xs.traversers;

import mf.org.apache.xerces.impl.xs.SchemaGrammar;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import mf.org.apache.xerces.impl.xs.XSAnnotationImpl;
import mf.org.apache.xerces.impl.xs.XSAttributeGroupDecl;
import mf.org.apache.xerces.impl.xs.util.XSObjectListImpl;
import mf.org.apache.xerces.util.DOMUtil;
import mf.org.apache.xerces.util.XMLSymbols;
import mf.org.apache.xerces.xni.QName;
import mf.org.apache.xerces.xs.XSObjectList;
import mf.org.w3c.dom.Element;

class XSDAttributeGroupTraverser extends XSDAbstractTraverser {
    XSDAttributeGroupTraverser(XSDHandler handler, XSAttributeChecker gAttrCheck) {
        super(handler, gAttrCheck);
    }

    XSAttributeGroupDecl traverseLocal(Element elmNode, XSDocumentInfo schemaDoc, SchemaGrammar grammar) {
        Object[] attrValues = this.fAttrChecker.checkAttributes(elmNode, false, schemaDoc);
        QName refAttr = attrValues[XSAttributeChecker.ATTIDX_REF];
        if (refAttr == null) {
            reportSchemaError("s4s-att-must-appear", new Object[]{"attributeGroup (local)", "ref"}, elmNode);
            this.fAttrChecker.returnAttrArray(attrValues, schemaDoc);
            return null;
        }
        XSAttributeGroupDecl attrGrp = (XSAttributeGroupDecl) this.fSchemaHandler.getGlobalDecl(schemaDoc, 2, refAttr, elmNode);
        Element child = DOMUtil.getFirstChildElement(elmNode);
        if (child != null) {
            if (DOMUtil.getLocalName(child).equals(SchemaSymbols.ELT_ANNOTATION)) {
                traverseAnnotationDecl(child, attrValues, false, schemaDoc);
                child = DOMUtil.getNextSiblingElement(child);
            } else {
                String text = DOMUtil.getSyntheticAnnotation(child);
                if (text != null) {
                    traverseSyntheticAnnotation(child, text, attrValues, false, schemaDoc);
                }
            }
            if (child != null) {
                reportSchemaError("s4s-elt-must-match.1", new Object[]{refAttr.rawname, "(annotation?)", DOMUtil.getLocalName(child)}, child);
            }
        }
        this.fAttrChecker.returnAttrArray(attrValues, schemaDoc);
        return attrGrp;
    }

    XSAttributeGroupDecl traverseGlobal(Element elmNode, XSDocumentInfo schemaDoc, SchemaGrammar grammar) {
        Element child;
        XSAttributeGroupDecl attrGrp = new XSAttributeGroupDecl();
        Object[] attrValues = this.fAttrChecker.checkAttributes(elmNode, true, schemaDoc);
        String nameAttr = attrValues[XSAttributeChecker.ATTIDX_NAME];
        if (nameAttr == null) {
            reportSchemaError("s4s-att-must-appear", new Object[]{"attributeGroup (global)", "name"}, elmNode);
            nameAttr = "(no name)";
        }
        attrGrp.fName = nameAttr;
        attrGrp.fTargetNamespace = schemaDoc.fTargetNamespace;
        Element child2 = DOMUtil.getFirstChildElement(elmNode);
        XSAnnotationImpl annotation = null;
        if (child2 == null || !DOMUtil.getLocalName(child2).equals(SchemaSymbols.ELT_ANNOTATION)) {
            String text = DOMUtil.getSyntheticAnnotation(elmNode);
            if (text != null) {
                annotation = traverseSyntheticAnnotation(elmNode, text, attrValues, false, schemaDoc);
                child = child2;
            } else {
                child = child2;
            }
        } else {
            annotation = traverseAnnotationDecl(child2, attrValues, false, schemaDoc);
            child = DOMUtil.getNextSiblingElement(child2);
        }
        Element nextNode = traverseAttrsAndAttrGrps(child, attrGrp, schemaDoc, grammar, null);
        if (nextNode != null) {
            reportSchemaError("s4s-elt-must-match.1", new Object[]{nameAttr, "(annotation?, ((attribute | attributeGroup)*, anyAttribute?))", DOMUtil.getLocalName(nextNode)}, nextNode);
        }
        if (nameAttr.equals("(no name)")) {
            this.fAttrChecker.returnAttrArray(attrValues, schemaDoc);
            return null;
        }
        XSObjectList annotations;
        attrGrp.removeProhibitedAttrs();
        XSAttributeGroupDecl redefinedAttrGrp = (XSAttributeGroupDecl) this.fSchemaHandler.getGrpOrAttrGrpRedefinedByRestriction(2, new QName(XMLSymbols.EMPTY_STRING, nameAttr, nameAttr, schemaDoc.fTargetNamespace), schemaDoc, elmNode);
        if (redefinedAttrGrp != null) {
            Object[] errArgs = attrGrp.validRestrictionOf(nameAttr, redefinedAttrGrp);
            if (errArgs != null) {
                reportSchemaError((String) errArgs[errArgs.length - 1], errArgs, child);
                reportSchemaError("src-redefine.7.2.2", new Object[]{nameAttr, errArgs[errArgs.length - 1]}, child);
            }
        }
        if (annotation != null) {
            annotations = new XSObjectListImpl();
            ((XSObjectListImpl) annotations).addXSObject(annotation);
        } else {
            annotations = XSObjectListImpl.EMPTY_LIST;
        }
        attrGrp.fAnnotations = annotations;
        if (grammar.getGlobalAttributeGroupDecl(attrGrp.fName) == null) {
            grammar.addGlobalAttributeGroupDecl(attrGrp);
        }
        String loc = this.fSchemaHandler.schemaDocument2SystemId(schemaDoc);
        XSAttributeGroupDecl attrGrp2 = grammar.getGlobalAttributeGroupDecl(attrGrp.fName, loc);
        if (attrGrp2 == null) {
            grammar.addGlobalAttributeGroupDecl(attrGrp, loc);
        }
        if (this.fSchemaHandler.fTolerateDuplicates) {
            if (attrGrp2 != null) {
                attrGrp = attrGrp2;
            }
            this.fSchemaHandler.addGlobalAttributeGroupDecl(attrGrp);
        }
        this.fAttrChecker.returnAttrArray(attrValues, schemaDoc);
        return attrGrp;
    }
}
