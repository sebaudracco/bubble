package mf.org.apache.xerces.impl.xs.traversers;

import mf.org.apache.xerces.impl.xs.SchemaGrammar;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import mf.org.apache.xerces.impl.xs.XSAnnotationImpl;
import mf.org.apache.xerces.impl.xs.XSConstraints;
import mf.org.apache.xerces.impl.xs.XSGroupDecl;
import mf.org.apache.xerces.impl.xs.XSModelGroupImpl;
import mf.org.apache.xerces.impl.xs.XSParticleDecl;
import mf.org.apache.xerces.impl.xs.util.XInt;
import mf.org.apache.xerces.impl.xs.util.XSObjectListImpl;
import mf.org.apache.xerces.util.DOMUtil;
import mf.org.apache.xerces.util.XMLSymbols;
import mf.org.apache.xerces.xni.QName;
import mf.org.apache.xerces.xs.XSObjectList;
import mf.org.w3c.dom.Element;

class XSDGroupTraverser extends XSDAbstractParticleTraverser {
    XSDGroupTraverser(XSDHandler handler, XSAttributeChecker gAttrCheck) {
        super(handler, gAttrCheck);
    }

    XSParticleDecl traverseLocal(Element elmNode, XSDocumentInfo schemaDoc, SchemaGrammar grammar) {
        Object[] attrValues = this.fAttrChecker.checkAttributes(elmNode, false, schemaDoc);
        QName refAttr = attrValues[XSAttributeChecker.ATTIDX_REF];
        XInt minAttr = attrValues[XSAttributeChecker.ATTIDX_MINOCCURS];
        XInt maxAttr = attrValues[XSAttributeChecker.ATTIDX_MAXOCCURS];
        XSGroupDecl group = null;
        if (refAttr == null) {
            reportSchemaError("s4s-att-must-appear", new Object[]{"group (local)", "ref"}, elmNode);
        } else {
            group = (XSGroupDecl) this.fSchemaHandler.getGlobalDecl(schemaDoc, 4, refAttr, elmNode);
        }
        XSAnnotationImpl annotation = null;
        Element child = DOMUtil.getFirstChildElement(elmNode);
        if (child == null || !DOMUtil.getLocalName(child).equals(SchemaSymbols.ELT_ANNOTATION)) {
            String text = DOMUtil.getSyntheticAnnotation(elmNode);
            if (text != null) {
                annotation = traverseSyntheticAnnotation(elmNode, text, attrValues, false, schemaDoc);
            }
        } else {
            annotation = traverseAnnotationDecl(child, attrValues, false, schemaDoc);
            child = DOMUtil.getNextSiblingElement(child);
        }
        if (child != null) {
            reportSchemaError("s4s-elt-must-match.1", new Object[]{"group (local)", "(annotation?)", DOMUtil.getLocalName(elmNode)}, elmNode);
        }
        int minOccurs = minAttr.intValue();
        int maxOccurs = maxAttr.intValue();
        XSParticleDecl particle = null;
        if (!(group == null || group.fModelGroup == null || (minOccurs == 0 && maxOccurs == 0))) {
            if (this.fSchemaHandler.fDeclPool != null) {
                particle = this.fSchemaHandler.fDeclPool.getParticleDecl();
            } else {
                particle = new XSParticleDecl();
            }
            particle.fType = (short) 3;
            particle.fValue = group.fModelGroup;
            particle.fMinOccurs = minOccurs;
            particle.fMaxOccurs = maxOccurs;
            if (group.fModelGroup.fCompositor == (short) 103) {
                particle = checkOccurrences(particle, SchemaSymbols.ELT_GROUP, (Element) elmNode.getParentNode(), 2, attrValues[XSAttributeChecker.ATTIDX_FROMDEFAULT].longValue());
            }
            if (refAttr != null) {
                XSObjectList annotations;
                if (annotation != null) {
                    annotations = new XSObjectListImpl();
                    ((XSObjectListImpl) annotations).addXSObject(annotation);
                } else {
                    annotations = XSObjectListImpl.EMPTY_LIST;
                }
                particle.fAnnotations = annotations;
            } else {
                particle.fAnnotations = group.fAnnotations;
            }
        }
        this.fAttrChecker.returnAttrArray(attrValues, schemaDoc);
        return particle;
    }

    XSGroupDecl traverseGlobal(Element elmNode, XSDocumentInfo schemaDoc, SchemaGrammar grammar) {
        Object[] attrValues = this.fAttrChecker.checkAttributes(elmNode, true, schemaDoc);
        String strNameAttr = attrValues[XSAttributeChecker.ATTIDX_NAME];
        if (strNameAttr == null) {
            reportSchemaError("s4s-att-must-appear", new Object[]{"group (global)", "name"}, elmNode);
        }
        XSGroupDecl group = new XSGroupDecl();
        XSParticleDecl particle = null;
        Element l_elmChild = DOMUtil.getFirstChildElement(elmNode);
        XSAnnotationImpl annotation = null;
        if (l_elmChild == null) {
            reportSchemaError("s4s-elt-must-match.2", new Object[]{"group (global)", "(annotation?, (all | choice | sequence))"}, elmNode);
        } else {
            Element l_elmChild2;
            String childName = l_elmChild.getLocalName();
            if (childName.equals(SchemaSymbols.ELT_ANNOTATION)) {
                annotation = traverseAnnotationDecl(l_elmChild, attrValues, true, schemaDoc);
                l_elmChild2 = DOMUtil.getNextSiblingElement(l_elmChild);
                if (l_elmChild2 != null) {
                    childName = l_elmChild2.getLocalName();
                }
            } else {
                String text = DOMUtil.getSyntheticAnnotation(elmNode);
                if (text != null) {
                    annotation = traverseSyntheticAnnotation(elmNode, text, attrValues, false, schemaDoc);
                    l_elmChild2 = l_elmChild;
                } else {
                    l_elmChild2 = l_elmChild;
                }
            }
            if (l_elmChild2 == null) {
                reportSchemaError("s4s-elt-must-match.2", new Object[]{"group (global)", "(annotation?, (all | choice | sequence))"}, elmNode);
            } else if (childName.equals(SchemaSymbols.ELT_ALL)) {
                particle = traverseAll(l_elmChild2, schemaDoc, grammar, 4, group);
            } else if (childName.equals(SchemaSymbols.ELT_CHOICE)) {
                particle = traverseChoice(l_elmChild2, schemaDoc, grammar, 4, group);
            } else if (childName.equals(SchemaSymbols.ELT_SEQUENCE)) {
                particle = traverseSequence(l_elmChild2, schemaDoc, grammar, 4, group);
            } else {
                reportSchemaError("s4s-elt-must-match.1", new Object[]{"group (global)", "(annotation?, (all | choice | sequence))", DOMUtil.getLocalName(l_elmChild2)}, l_elmChild2);
            }
            if (!(l_elmChild2 == null || DOMUtil.getNextSiblingElement(l_elmChild2) == null)) {
                reportSchemaError("s4s-elt-must-match.1", new Object[]{"group (global)", "(annotation?, (all | choice | sequence))", DOMUtil.getLocalName(DOMUtil.getNextSiblingElement(l_elmChild2))}, DOMUtil.getNextSiblingElement(l_elmChild2));
            }
        }
        if (strNameAttr != null) {
            XSObjectList annotations;
            group.fName = strNameAttr;
            group.fTargetNamespace = schemaDoc.fTargetNamespace;
            if (particle == null) {
                particle = XSConstraints.getEmptySequence();
            }
            group.fModelGroup = (XSModelGroupImpl) particle.fValue;
            if (annotation != null) {
                annotations = new XSObjectListImpl();
                ((XSObjectListImpl) annotations).addXSObject(annotation);
            } else {
                annotations = XSObjectListImpl.EMPTY_LIST;
            }
            group.fAnnotations = annotations;
            if (grammar.getGlobalGroupDecl(group.fName) == null) {
                grammar.addGlobalGroupDecl(group);
            }
            String loc = this.fSchemaHandler.schemaDocument2SystemId(schemaDoc);
            XSGroupDecl group2 = grammar.getGlobalGroupDecl(group.fName, loc);
            if (group2 == null) {
                grammar.addGlobalGroupDecl(group, loc);
            }
            if (this.fSchemaHandler.fTolerateDuplicates) {
                if (group2 != null) {
                    group = group2;
                }
                this.fSchemaHandler.addGlobalGroupDecl(group);
            }
        } else {
            group = null;
        }
        if (group != null) {
            Object redefinedGrp = this.fSchemaHandler.getGrpOrAttrGrpRedefinedByRestriction(4, new QName(XMLSymbols.EMPTY_STRING, strNameAttr, strNameAttr, schemaDoc.fTargetNamespace), schemaDoc, elmNode);
            if (redefinedGrp != null) {
                grammar.addRedefinedGroupDecl(group, (XSGroupDecl) redefinedGrp, this.fSchemaHandler.element2Locator(elmNode));
            }
        }
        this.fAttrChecker.returnAttrArray(attrValues, schemaDoc);
        return group;
    }
}
