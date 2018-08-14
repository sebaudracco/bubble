package mf.org.apache.xerces.impl.xs.traversers;

import mf.org.apache.xerces.impl.xs.SchemaGrammar;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import mf.org.apache.xerces.impl.xs.XSAnnotationImpl;
import mf.org.apache.xerces.impl.xs.XSParticleDecl;
import mf.org.apache.xerces.impl.xs.XSWildcardDecl;
import mf.org.apache.xerces.impl.xs.util.XInt;
import mf.org.apache.xerces.impl.xs.util.XSObjectListImpl;
import mf.org.apache.xerces.util.DOMUtil;
import mf.org.apache.xerces.xs.XSObjectList;
import mf.org.w3c.dom.Element;

class XSDWildcardTraverser extends XSDAbstractTraverser {
    XSDWildcardTraverser(XSDHandler handler, XSAttributeChecker gAttrCheck) {
        super(handler, gAttrCheck);
    }

    XSParticleDecl traverseAny(Element elmNode, XSDocumentInfo schemaDoc, SchemaGrammar grammar) {
        Object[] attrValues = this.fAttrChecker.checkAttributes(elmNode, false, schemaDoc);
        XSWildcardDecl wildcard = traverseWildcardDecl(elmNode, attrValues, schemaDoc, grammar);
        XSParticleDecl particle = null;
        if (wildcard != null) {
            int min = ((XInt) attrValues[XSAttributeChecker.ATTIDX_MINOCCURS]).intValue();
            int max = ((XInt) attrValues[XSAttributeChecker.ATTIDX_MAXOCCURS]).intValue();
            if (max != 0) {
                if (this.fSchemaHandler.fDeclPool != null) {
                    particle = this.fSchemaHandler.fDeclPool.getParticleDecl();
                } else {
                    particle = new XSParticleDecl();
                }
                particle.fType = (short) 2;
                particle.fValue = wildcard;
                particle.fMinOccurs = min;
                particle.fMaxOccurs = max;
                particle.fAnnotations = wildcard.fAnnotations;
            }
        }
        this.fAttrChecker.returnAttrArray(attrValues, schemaDoc);
        return particle;
    }

    XSWildcardDecl traverseAnyAttribute(Element elmNode, XSDocumentInfo schemaDoc, SchemaGrammar grammar) {
        Object[] attrValues = this.fAttrChecker.checkAttributes(elmNode, false, schemaDoc);
        XSWildcardDecl wildcard = traverseWildcardDecl(elmNode, attrValues, schemaDoc, grammar);
        this.fAttrChecker.returnAttrArray(attrValues, schemaDoc);
        return wildcard;
    }

    XSWildcardDecl traverseWildcardDecl(Element elmNode, Object[] attrValues, XSDocumentInfo schemaDoc, SchemaGrammar grammar) {
        XSObjectList annotations;
        XSWildcardDecl wildcard = new XSWildcardDecl();
        wildcard.fType = attrValues[XSAttributeChecker.ATTIDX_NAMESPACE].shortValue();
        wildcard.fNamespaceList = (String[]) attrValues[XSAttributeChecker.ATTIDX_NAMESPACE_LIST];
        wildcard.fProcessContents = attrValues[XSAttributeChecker.ATTIDX_PROCESSCONTENTS].shortValue();
        Element child = DOMUtil.getFirstChildElement(elmNode);
        XSAnnotationImpl annotation = null;
        String text;
        if (child != null) {
            if (DOMUtil.getLocalName(child).equals(SchemaSymbols.ELT_ANNOTATION)) {
                annotation = traverseAnnotationDecl(child, attrValues, false, schemaDoc);
                child = DOMUtil.getNextSiblingElement(child);
            } else {
                text = DOMUtil.getSyntheticAnnotation(elmNode);
                if (text != null) {
                    annotation = traverseSyntheticAnnotation(elmNode, text, attrValues, false, schemaDoc);
                }
            }
            if (child != null) {
                reportSchemaError("s4s-elt-must-match.1", new Object[]{"wildcard", "(annotation?)", DOMUtil.getLocalName(child)}, elmNode);
            }
        } else {
            text = DOMUtil.getSyntheticAnnotation(elmNode);
            if (text != null) {
                annotation = traverseSyntheticAnnotation(elmNode, text, attrValues, false, schemaDoc);
            }
        }
        if (annotation != null) {
            annotations = new XSObjectListImpl();
            ((XSObjectListImpl) annotations).addXSObject(annotation);
        } else {
            annotations = XSObjectListImpl.EMPTY_LIST;
        }
        wildcard.fAnnotations = annotations;
        return wildcard;
    }
}
