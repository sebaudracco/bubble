package mf.org.apache.xerces.impl.xs.traversers;

import java.util.Locale;
import mf.org.apache.xerces.impl.dv.ValidatedInfo;
import mf.org.apache.xerces.impl.dv.XSSimpleType;
import mf.org.apache.xerces.impl.xs.SchemaGrammar;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import mf.org.apache.xerces.impl.xs.XSAnnotationImpl;
import mf.org.apache.xerces.impl.xs.XSComplexTypeDecl;
import mf.org.apache.xerces.impl.xs.XSConstraints;
import mf.org.apache.xerces.impl.xs.XSElementDecl;
import mf.org.apache.xerces.impl.xs.XSParticleDecl;
import mf.org.apache.xerces.impl.xs.util.XInt;
import mf.org.apache.xerces.impl.xs.util.XSObjectListImpl;
import mf.org.apache.xerces.util.DOMUtil;
import mf.org.apache.xerces.util.SymbolTable;
import mf.org.apache.xerces.util.XMLChar;
import mf.org.apache.xerces.xni.QName;
import mf.org.apache.xerces.xs.XSObject;
import mf.org.apache.xerces.xs.XSObjectList;
import mf.org.apache.xerces.xs.XSTypeDefinition;
import mf.org.w3c.dom.Attr;
import mf.org.w3c.dom.Element;

class XSDElementTraverser extends XSDAbstractTraverser {
    boolean fDeferTraversingLocalElements;
    protected final XSElementDecl fTempElementDecl = new XSElementDecl();

    XSDElementTraverser(XSDHandler handler, XSAttributeChecker gAttrCheck) {
        super(handler, gAttrCheck);
    }

    XSParticleDecl traverseLocal(Element elmDecl, XSDocumentInfo schemaDoc, SchemaGrammar grammar, int allContextFlags, XSObject parent) {
        XSParticleDecl particle;
        if (this.fSchemaHandler.fDeclPool != null) {
            particle = this.fSchemaHandler.fDeclPool.getParticleDecl();
        } else {
            particle = new XSParticleDecl();
        }
        if (this.fDeferTraversingLocalElements) {
            particle.fType = (short) 1;
            Attr attr = elmDecl.getAttributeNode(SchemaSymbols.ATT_MINOCCURS);
            if (attr != null) {
                try {
                    int m = Integer.parseInt(XMLChar.trim(attr.getValue()));
                    if (m >= 0) {
                        particle.fMinOccurs = m;
                    }
                } catch (NumberFormatException e) {
                }
            }
            this.fSchemaHandler.fillInLocalElemInfo(elmDecl, schemaDoc, allContextFlags, parent, particle);
            return particle;
        }
        traverseLocal(particle, elmDecl, schemaDoc, grammar, allContextFlags, parent, null);
        if (particle.fType == (short) 0) {
            return null;
        }
        return particle;
    }

    protected void traverseLocal(XSParticleDecl particle, Element elmDecl, XSDocumentInfo schemaDoc, SchemaGrammar grammar, int allContextFlags, XSObject parent, String[] localNSDecls) {
        XSElementDecl element;
        if (localNSDecls != null) {
            schemaDoc.fNamespaceSupport.setEffectiveContext(localNSDecls);
        }
        Object[] attrValues = this.fAttrChecker.checkAttributes(elmDecl, false, schemaDoc);
        QName refAtt = attrValues[XSAttributeChecker.ATTIDX_REF];
        XInt minAtt = attrValues[XSAttributeChecker.ATTIDX_MINOCCURS];
        XInt maxAtt = attrValues[XSAttributeChecker.ATTIDX_MAXOCCURS];
        XSAnnotationImpl annotation = null;
        if (elmDecl.getAttributeNode(SchemaSymbols.ATT_REF) == null) {
            element = traverseNamedElement(elmDecl, attrValues, schemaDoc, grammar, false, parent);
        } else if (refAtt != null) {
            element = (XSElementDecl) this.fSchemaHandler.getGlobalDecl(schemaDoc, 3, refAtt, elmDecl);
            Element child = DOMUtil.getFirstChildElement(elmDecl);
            if (child == null || !DOMUtil.getLocalName(child).equals(SchemaSymbols.ELT_ANNOTATION)) {
                String text = DOMUtil.getSyntheticAnnotation(elmDecl);
                if (text != null) {
                    annotation = traverseSyntheticAnnotation(elmDecl, text, attrValues, false, schemaDoc);
                }
            } else {
                annotation = traverseAnnotationDecl(child, attrValues, false, schemaDoc);
                child = DOMUtil.getNextSiblingElement(child);
            }
            if (child != null) {
                reportSchemaError("src-element.2.2", new Object[]{refAtt.rawname, DOMUtil.getLocalName(child)}, child);
            }
        } else {
            element = null;
        }
        particle.fMinOccurs = minAtt.intValue();
        particle.fMaxOccurs = maxAtt.intValue();
        if (element != null) {
            particle.fType = (short) 1;
            particle.fValue = element;
        } else {
            particle.fType = (short) 0;
        }
        if (refAtt != null) {
            XSObjectList annotations;
            if (annotation != null) {
                annotations = new XSObjectListImpl();
                ((XSObjectListImpl) annotations).addXSObject(annotation);
            } else {
                annotations = XSObjectListImpl.EMPTY_LIST;
            }
            particle.fAnnotations = annotations;
        } else {
            XSObjectList xSObjectList;
            if (element != null) {
                xSObjectList = element.fAnnotations;
            } else {
                xSObjectList = XSObjectListImpl.EMPTY_LIST;
            }
            particle.fAnnotations = xSObjectList;
        }
        XSParticleDecl xSParticleDecl = particle;
        int i = allContextFlags;
        checkOccurrences(xSParticleDecl, SchemaSymbols.ELT_ELEMENT, (Element) elmDecl.getParentNode(), i, attrValues[XSAttributeChecker.ATTIDX_FROMDEFAULT].longValue());
        this.fAttrChecker.returnAttrArray(attrValues, schemaDoc);
    }

    XSElementDecl traverseGlobal(Element elmDecl, XSDocumentInfo schemaDoc, SchemaGrammar grammar) {
        Object[] attrValues = this.fAttrChecker.checkAttributes(elmDecl, true, schemaDoc);
        XSElementDecl element = traverseNamedElement(elmDecl, attrValues, schemaDoc, grammar, true, null);
        this.fAttrChecker.returnAttrArray(attrValues, schemaDoc);
        return element;
    }

    XSElementDecl traverseNamedElement(Element elmDecl, Object[] attrValues, XSDocumentInfo schemaDoc, SchemaGrammar grammar, boolean isGlobal, XSObject parent) {
        XSElementDecl element;
        XSObjectList annotations;
        String childName;
        Boolean abstractAtt = attrValues[XSAttributeChecker.ATTIDX_ABSTRACT];
        XInt blockAtt = attrValues[XSAttributeChecker.ATTIDX_BLOCK];
        String defaultAtt = attrValues[XSAttributeChecker.ATTIDX_DEFAULT];
        XInt finalAtt = attrValues[XSAttributeChecker.ATTIDX_FINAL];
        String fixedAtt = attrValues[XSAttributeChecker.ATTIDX_FIXED];
        XInt formAtt = attrValues[XSAttributeChecker.ATTIDX_FORM];
        String nameAtt = attrValues[XSAttributeChecker.ATTIDX_NAME];
        Boolean nillableAtt = attrValues[XSAttributeChecker.ATTIDX_NILLABLE];
        QName subGroupAtt = attrValues[XSAttributeChecker.ATTIDX_SUBSGROUP];
        QName typeAtt = attrValues[XSAttributeChecker.ATTIDX_TYPE];
        if (this.fSchemaHandler.fDeclPool != null) {
            element = this.fSchemaHandler.fDeclPool.getElementDecl();
        } else {
            element = new XSElementDecl();
        }
        if (nameAtt != null) {
            element.fName = this.fSymbolTable.addSymbol(nameAtt);
        }
        if (isGlobal) {
            element.fTargetNamespace = schemaDoc.fTargetNamespace;
            element.setIsGlobal();
        } else {
            if (parent instanceof XSComplexTypeDecl) {
                element.setIsLocal((XSComplexTypeDecl) parent);
            }
            if (formAtt != null) {
                if (formAtt.intValue() == 1) {
                    element.fTargetNamespace = schemaDoc.fTargetNamespace;
                } else {
                    element.fTargetNamespace = null;
                }
            } else if (schemaDoc.fAreLocalElementsQualified) {
                element.fTargetNamespace = schemaDoc.fTargetNamespace;
            } else {
                element.fTargetNamespace = null;
            }
        }
        element.fBlock = blockAtt == null ? schemaDoc.fBlockDefault : blockAtt.shortValue();
        element.fFinal = finalAtt == null ? schemaDoc.fFinalDefault : finalAtt.shortValue();
        element.fBlock = (short) (element.fBlock & 7);
        element.fFinal = (short) (element.fFinal & 3);
        if (nillableAtt.booleanValue()) {
            element.setIsNillable();
        }
        if (abstractAtt != null && abstractAtt.booleanValue()) {
            element.setIsAbstract();
        }
        if (fixedAtt != null) {
            element.fDefault = new ValidatedInfo();
            element.fDefault.normalizedValue = fixedAtt;
            element.setConstraintType((short) 2);
        } else if (defaultAtt != null) {
            element.fDefault = new ValidatedInfo();
            element.fDefault.normalizedValue = defaultAtt;
            element.setConstraintType((short) 1);
        } else {
            element.setConstraintType((short) 0);
        }
        if (subGroupAtt != null) {
            element.fSubGroup = (XSElementDecl) this.fSchemaHandler.getGlobalDecl(schemaDoc, 3, subGroupAtt, elmDecl);
        }
        Element child = DOMUtil.getFirstChildElement(elmDecl);
        XSAnnotationImpl annotation = null;
        if (child == null || !DOMUtil.getLocalName(child).equals(SchemaSymbols.ELT_ANNOTATION)) {
            String text = DOMUtil.getSyntheticAnnotation(elmDecl);
            if (text != null) {
                annotation = traverseSyntheticAnnotation(elmDecl, text, attrValues, false, schemaDoc);
            }
        } else {
            annotation = traverseAnnotationDecl(child, attrValues, false, schemaDoc);
            child = DOMUtil.getNextSiblingElement(child);
        }
        if (annotation != null) {
            annotations = new XSObjectListImpl();
            ((XSObjectListImpl) annotations).addXSObject(annotation);
        } else {
            annotations = XSObjectListImpl.EMPTY_LIST;
        }
        element.fAnnotations = annotations;
        XSTypeDefinition elementType = null;
        boolean haveAnonType = false;
        if (child != null) {
            childName = DOMUtil.getLocalName(child);
            if (childName.equals(SchemaSymbols.ELT_COMPLEXTYPE)) {
                elementType = this.fSchemaHandler.fComplexTypeTraverser.traverseLocal(child, schemaDoc, grammar);
                haveAnonType = true;
                child = DOMUtil.getNextSiblingElement(child);
            } else {
                if (childName.equals(SchemaSymbols.ELT_SIMPLETYPE)) {
                    elementType = this.fSchemaHandler.fSimpleTypeTraverser.traverseLocal(child, schemaDoc, grammar);
                    haveAnonType = true;
                    child = DOMUtil.getNextSiblingElement(child);
                }
            }
        }
        if (elementType == null && typeAtt != null) {
            elementType = (XSTypeDefinition) this.fSchemaHandler.getGlobalDecl(schemaDoc, 7, typeAtt, elmDecl);
            if (elementType == null) {
                element.fUnresolvedTypeName = typeAtt;
            }
        }
        if (elementType == null && element.fSubGroup != null) {
            elementType = element.fSubGroup.fType;
        }
        if (elementType == null) {
            elementType = SchemaGrammar.fAnyType;
        }
        element.fType = elementType;
        if (child != null) {
            childName = DOMUtil.getLocalName(child);
            while (child != null) {
                if (!childName.equals(SchemaSymbols.ELT_KEY)) {
                    if (!childName.equals(SchemaSymbols.ELT_KEYREF)) {
                        if (!childName.equals(SchemaSymbols.ELT_UNIQUE)) {
                            break;
                        }
                    }
                }
                if (!childName.equals(SchemaSymbols.ELT_KEY)) {
                    if (!childName.equals(SchemaSymbols.ELT_UNIQUE)) {
                        if (childName.equals(SchemaSymbols.ELT_KEYREF)) {
                            this.fSchemaHandler.storeKeyRef(child, schemaDoc, element);
                        }
                        child = DOMUtil.getNextSiblingElement(child);
                        if (child != null) {
                            childName = DOMUtil.getLocalName(child);
                        }
                    }
                }
                DOMUtil.setHidden(child, this.fSchemaHandler.fHiddenNodes);
                this.fSchemaHandler.fUniqueOrKeyTraverser.traverse(child, element, schemaDoc, grammar);
                if (DOMUtil.getAttrValue(child, SchemaSymbols.ATT_NAME).length() != 0) {
                    String str;
                    XSDHandler xSDHandler = this.fSchemaHandler;
                    if (schemaDoc.fTargetNamespace == null) {
                        str = "," + DOMUtil.getAttrValue(child, SchemaSymbols.ATT_NAME);
                    } else {
                        str = schemaDoc.fTargetNamespace + "," + DOMUtil.getAttrValue(child, SchemaSymbols.ATT_NAME);
                    }
                    xSDHandler.checkForDuplicateNames(str, 1, this.fSchemaHandler.getIDRegistry(), this.fSchemaHandler.getIDRegistry_sub(), child, schemaDoc);
                }
                child = DOMUtil.getNextSiblingElement(child);
                if (child != null) {
                    childName = DOMUtil.getLocalName(child);
                }
            }
        }
        if (nameAtt == null) {
            if (isGlobal) {
                reportSchemaError("s4s-att-must-appear", new Object[]{SchemaSymbols.ELT_ELEMENT, SchemaSymbols.ATT_NAME}, elmDecl);
            } else {
                reportSchemaError("src-element.2.1", null, elmDecl);
            }
            nameAtt = "(no name)";
        }
        if (child != null) {
            reportSchemaError("s4s-elt-must-match.1", new Object[]{nameAtt, "(annotation?, (simpleType | complexType)?, (unique | key | keyref)*))", DOMUtil.getLocalName(child)}, child);
        }
        if (!(defaultAtt == null || fixedAtt == null)) {
            reportSchemaError("src-element.1", new Object[]{nameAtt}, elmDecl);
        }
        if (haveAnonType && typeAtt != null) {
            reportSchemaError("src-element.3", new Object[]{nameAtt}, elmDecl);
        }
        checkNotationType(nameAtt, elementType, elmDecl);
        if (element.fDefault != null) {
            this.fValidationState.setNamespaceSupport(schemaDoc.fNamespaceSupport);
            if (XSConstraints.ElementDefaultValidImmediate(element.fType, element.fDefault.normalizedValue, this.fValidationState, element.fDefault) == null) {
                reportSchemaError("e-props-correct.2", new Object[]{nameAtt, element.fDefault.normalizedValue}, elmDecl);
                element.fDefault = null;
                element.setConstraintType((short) 0);
            }
        }
        if (!(element.fSubGroup == null || XSConstraints.checkTypeDerivationOk(element.fType, element.fSubGroup.fType, element.fSubGroup.fFinal))) {
            reportSchemaError("e-props-correct.4", new Object[]{nameAtt, subGroupAtt.prefix + ":" + subGroupAtt.localpart}, elmDecl);
            element.fSubGroup = null;
        }
        if (element.fDefault != null && ((elementType.getTypeCategory() == (short) 16 && ((XSSimpleType) elementType).isIDType()) || (elementType.getTypeCategory() == (short) 15 && ((XSComplexTypeDecl) elementType).containsTypeID()))) {
            reportSchemaError("e-props-correct.5", new Object[]{element.fName}, elmDecl);
            element.fDefault = null;
            element.setConstraintType((short) 0);
        }
        if (element.fName == null) {
            return null;
        }
        if (isGlobal) {
            grammar.addGlobalElementDeclAll(element);
            if (grammar.getGlobalElementDecl(element.fName) == null) {
                grammar.addGlobalElementDecl(element);
            }
            String loc = this.fSchemaHandler.schemaDocument2SystemId(schemaDoc);
            XSElementDecl element2 = grammar.getGlobalElementDecl(element.fName, loc);
            if (element2 == null) {
                grammar.addGlobalElementDecl(element, loc);
            }
            if (this.fSchemaHandler.fTolerateDuplicates) {
                if (element2 != null) {
                    element = element2;
                }
                this.fSchemaHandler.addGlobalElementDecl(element);
            }
        }
        return element;
    }

    void reset(SymbolTable symbolTable, boolean validateAnnotations, Locale locale) {
        super.reset(symbolTable, validateAnnotations, locale);
        this.fDeferTraversingLocalElements = true;
    }
}
