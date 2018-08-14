package mf.org.apache.xerces.impl.xs.traversers;

import mf.org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import mf.org.apache.xerces.impl.dv.ValidatedInfo;
import mf.org.apache.xerces.impl.dv.XSSimpleType;
import mf.org.apache.xerces.impl.xs.SchemaGrammar;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import mf.org.apache.xerces.impl.xs.XSAnnotationImpl;
import mf.org.apache.xerces.impl.xs.XSAttributeDecl;
import mf.org.apache.xerces.impl.xs.XSAttributeUseImpl;
import mf.org.apache.xerces.impl.xs.XSComplexTypeDecl;
import mf.org.apache.xerces.impl.xs.util.XInt;
import mf.org.apache.xerces.impl.xs.util.XSObjectListImpl;
import mf.org.apache.xerces.util.DOMUtil;
import mf.org.apache.xerces.util.XMLSymbols;
import mf.org.apache.xerces.xni.QName;
import mf.org.apache.xerces.xs.XSObjectList;
import mf.org.apache.xerces.xs.XSTypeDefinition;
import mf.org.w3c.dom.Element;

class XSDAttributeTraverser extends XSDAbstractTraverser {
    public XSDAttributeTraverser(XSDHandler handler, XSAttributeChecker gAttrCheck) {
        super(handler, gAttrCheck);
    }

    protected XSAttributeUseImpl traverseLocal(Element attrDecl, XSDocumentInfo schemaDoc, SchemaGrammar grammar, XSComplexTypeDecl enclosingCT) {
        XSAttributeDecl attribute;
        Object[] attrValues = this.fAttrChecker.checkAttributes(attrDecl, false, schemaDoc);
        String defaultAtt = attrValues[XSAttributeChecker.ATTIDX_DEFAULT];
        String fixedAtt = attrValues[XSAttributeChecker.ATTIDX_FIXED];
        String nameAtt = attrValues[XSAttributeChecker.ATTIDX_NAME];
        QName refAtt = attrValues[XSAttributeChecker.ATTIDX_REF];
        XInt useAtt = attrValues[XSAttributeChecker.ATTIDX_USE];
        XSAnnotationImpl annotation = null;
        if (attrDecl.getAttributeNode(SchemaSymbols.ATT_REF) == null) {
            attribute = traverseNamedAttr(attrDecl, attrValues, schemaDoc, grammar, false, enclosingCT);
        } else if (refAtt != null) {
            attribute = (XSAttributeDecl) this.fSchemaHandler.getGlobalDecl(schemaDoc, 1, refAtt, attrDecl);
            Element child = DOMUtil.getFirstChildElement(attrDecl);
            if (child == null || !DOMUtil.getLocalName(child).equals(SchemaSymbols.ELT_ANNOTATION)) {
                String text = DOMUtil.getSyntheticAnnotation(attrDecl);
                if (text != null) {
                    annotation = traverseSyntheticAnnotation(attrDecl, text, attrValues, false, schemaDoc);
                }
            } else {
                annotation = traverseAnnotationDecl(child, attrValues, false, schemaDoc);
                child = DOMUtil.getNextSiblingElement(child);
            }
            if (child != null) {
                reportSchemaError("src-attribute.3.2", new Object[]{refAtt.rawname}, child);
            }
            nameAtt = refAtt.localpart;
        } else {
            attribute = null;
        }
        short consType = (short) 0;
        if (defaultAtt != null) {
            consType = (short) 1;
        } else if (fixedAtt != null) {
            consType = (short) 2;
            defaultAtt = fixedAtt;
            fixedAtt = null;
        }
        XSAttributeUseImpl attrUse = null;
        if (attribute != null) {
            if (this.fSchemaHandler.fDeclPool != null) {
                attrUse = this.fSchemaHandler.fDeclPool.getAttributeUse();
            } else {
                attrUse = new XSAttributeUseImpl();
            }
            attrUse.fAttrDecl = attribute;
            attrUse.fUse = useAtt.shortValue();
            attrUse.fConstraintType = consType;
            if (defaultAtt != null) {
                attrUse.fDefault = new ValidatedInfo();
                attrUse.fDefault.normalizedValue = defaultAtt;
            }
            if (attrDecl.getAttributeNode(SchemaSymbols.ATT_REF) == null) {
                attrUse.fAnnotations = attribute.getAnnotations();
            } else {
                XSObjectList annotations;
                if (annotation != null) {
                    annotations = new XSObjectListImpl();
                    ((XSObjectListImpl) annotations).addXSObject(annotation);
                } else {
                    annotations = XSObjectListImpl.EMPTY_LIST;
                }
                attrUse.fAnnotations = annotations;
            }
        }
        if (!(defaultAtt == null || fixedAtt == null)) {
            reportSchemaError("src-attribute.1", new Object[]{nameAtt}, attrDecl);
        }
        if (!(consType != (short) 1 || useAtt == null || useAtt.intValue() == 0)) {
            reportSchemaError("src-attribute.2", new Object[]{nameAtt}, attrDecl);
            attrUse.fUse = (short) 0;
        }
        if (!(defaultAtt == null || attrUse == null)) {
            this.fValidationState.setNamespaceSupport(schemaDoc.fNamespaceSupport);
            try {
                checkDefaultValid(attrUse);
            } catch (InvalidDatatypeValueException ide) {
                reportSchemaError(ide.getKey(), ide.getArgs(), attrDecl);
                reportSchemaError("a-props-correct.2", new Object[]{nameAtt, defaultAtt}, attrDecl);
                attrUse.fDefault = null;
                attrUse.fConstraintType = (short) 0;
            }
            if (((XSSimpleType) attribute.getTypeDefinition()).isIDType()) {
                reportSchemaError("a-props-correct.3", new Object[]{nameAtt}, attrDecl);
                attrUse.fDefault = null;
                attrUse.fConstraintType = (short) 0;
            }
            if (!(attrUse.fAttrDecl.getConstraintType() != (short) 2 || attrUse.fConstraintType == (short) 0 || (attrUse.fConstraintType == (short) 2 && attrUse.fAttrDecl.getValInfo().actualValue.equals(attrUse.fDefault.actualValue)))) {
                reportSchemaError("au-props-correct.2", new Object[]{nameAtt, attrUse.fAttrDecl.getValInfo().stringValue()}, attrDecl);
                attrUse.fDefault = attrUse.fAttrDecl.getValInfo();
                attrUse.fConstraintType = (short) 2;
            }
        }
        this.fAttrChecker.returnAttrArray(attrValues, schemaDoc);
        return attrUse;
    }

    protected XSAttributeDecl traverseGlobal(Element attrDecl, XSDocumentInfo schemaDoc, SchemaGrammar grammar) {
        Object[] attrValues = this.fAttrChecker.checkAttributes(attrDecl, true, schemaDoc);
        XSAttributeDecl attribute = traverseNamedAttr(attrDecl, attrValues, schemaDoc, grammar, true, null);
        this.fAttrChecker.returnAttrArray(attrValues, schemaDoc);
        return attribute;
    }

    XSAttributeDecl traverseNamedAttr(Element attrDecl, Object[] attrValues, XSDocumentInfo schemaDoc, SchemaGrammar grammar, boolean isGlobal, XSComplexTypeDecl enclosingCT) {
        XSAttributeDecl attribute;
        String nameAtt;
        XSObjectList annotations;
        String defaultAtt = attrValues[XSAttributeChecker.ATTIDX_DEFAULT];
        String fixedAtt = attrValues[XSAttributeChecker.ATTIDX_FIXED];
        XInt formAtt = attrValues[XSAttributeChecker.ATTIDX_FORM];
        String nameAtt2 = attrValues[XSAttributeChecker.ATTIDX_NAME];
        QName typeAtt = attrValues[XSAttributeChecker.ATTIDX_TYPE];
        if (this.fSchemaHandler.fDeclPool != null) {
            attribute = this.fSchemaHandler.fDeclPool.getAttributeDecl();
        } else {
            attribute = new XSAttributeDecl();
        }
        if (nameAtt2 != null) {
            nameAtt = this.fSymbolTable.addSymbol(nameAtt2);
        } else {
            nameAtt = nameAtt2;
        }
        String tnsAtt = null;
        XSComplexTypeDecl enclCT = null;
        short scope = (short) 0;
        if (isGlobal) {
            tnsAtt = schemaDoc.fTargetNamespace;
            scope = (short) 1;
        } else {
            if (enclosingCT != null) {
                enclCT = enclosingCT;
                scope = (short) 2;
            }
            if (formAtt != null) {
                if (formAtt.intValue() == 1) {
                    tnsAtt = schemaDoc.fTargetNamespace;
                }
            } else if (schemaDoc.fAreLocalAttributesQualified) {
                tnsAtt = schemaDoc.fTargetNamespace;
            }
        }
        ValidatedInfo attDefault = null;
        short constraintType = (short) 0;
        if (isGlobal) {
            if (fixedAtt != null) {
                attDefault = new ValidatedInfo();
                attDefault.normalizedValue = fixedAtt;
                constraintType = (short) 2;
            } else if (defaultAtt != null) {
                attDefault = new ValidatedInfo();
                attDefault.normalizedValue = defaultAtt;
                constraintType = (short) 1;
            }
        }
        Element child = DOMUtil.getFirstChildElement(attrDecl);
        XSAnnotationImpl annotation = null;
        if (child == null || !DOMUtil.getLocalName(child).equals(SchemaSymbols.ELT_ANNOTATION)) {
            String text = DOMUtil.getSyntheticAnnotation(attrDecl);
            if (text != null) {
                annotation = traverseSyntheticAnnotation(attrDecl, text, attrValues, false, schemaDoc);
            }
        } else {
            annotation = traverseAnnotationDecl(child, attrValues, false, schemaDoc);
            child = DOMUtil.getNextSiblingElement(child);
        }
        XSSimpleType attrType = null;
        boolean haveAnonType = false;
        if (child != null) {
            if (DOMUtil.getLocalName(child).equals(SchemaSymbols.ELT_SIMPLETYPE)) {
                attrType = this.fSchemaHandler.fSimpleTypeTraverser.traverseLocal(child, schemaDoc, grammar);
                haveAnonType = true;
                child = DOMUtil.getNextSiblingElement(child);
            }
        }
        if (attrType == null && typeAtt != null) {
            XSTypeDefinition type = (XSTypeDefinition) this.fSchemaHandler.getGlobalDecl(schemaDoc, 7, typeAtt, attrDecl);
            if (type == null || type.getTypeCategory() != (short) 16) {
                reportSchemaError("src-resolve", new Object[]{typeAtt.rawname, "simpleType definition"}, attrDecl);
                if (type == null) {
                    attribute.fUnresolvedTypeName = typeAtt;
                }
            } else {
                attrType = (XSSimpleType) type;
            }
        }
        if (attrType == null) {
            attrType = SchemaGrammar.fAnySimpleType;
        }
        if (annotation != null) {
            annotations = new XSObjectListImpl();
            ((XSObjectListImpl) annotations).addXSObject(annotation);
        } else {
            annotations = XSObjectListImpl.EMPTY_LIST;
        }
        attribute.setValues(nameAtt, tnsAtt, attrType, constraintType, scope, attDefault, enclCT, annotations);
        if (nameAtt == null) {
            if (isGlobal) {
                reportSchemaError("s4s-att-must-appear", new Object[]{SchemaSymbols.ELT_ATTRIBUTE, SchemaSymbols.ATT_NAME}, attrDecl);
            } else {
                reportSchemaError("src-attribute.3.1", null, attrDecl);
            }
            nameAtt2 = "(no name)";
        } else {
            nameAtt2 = nameAtt;
        }
        if (child != null) {
            reportSchemaError("s4s-elt-must-match.1", new Object[]{nameAtt2, "(annotation?, (simpleType?))", DOMUtil.getLocalName(child)}, child);
        }
        if (!(defaultAtt == null || fixedAtt == null)) {
            reportSchemaError("src-attribute.1", new Object[]{nameAtt2}, attrDecl);
        }
        if (haveAnonType && typeAtt != null) {
            reportSchemaError("src-attribute.4", new Object[]{nameAtt2}, attrDecl);
        }
        checkNotationType(nameAtt2, attrType, attrDecl);
        if (attDefault != null) {
            this.fValidationState.setNamespaceSupport(schemaDoc.fNamespaceSupport);
            try {
                checkDefaultValid(attribute);
            } catch (InvalidDatatypeValueException ide) {
                reportSchemaError(ide.getKey(), ide.getArgs(), attrDecl);
                reportSchemaError("a-props-correct.2", new Object[]{nameAtt2, attDefault.normalizedValue}, attrDecl);
                attDefault = null;
                attribute.setValues(nameAtt2, tnsAtt, attrType, (short) 0, scope, null, enclCT, annotations);
            }
        }
        if (attDefault != null && attrType.isIDType()) {
            reportSchemaError("a-props-correct.3", new Object[]{nameAtt2}, attrDecl);
            attribute.setValues(nameAtt2, tnsAtt, attrType, (short) 0, scope, null, enclCT, annotations);
        }
        if (nameAtt2 == null || !nameAtt2.equals(XMLSymbols.PREFIX_XMLNS)) {
            if (tnsAtt != null) {
                if (tnsAtt.equals(SchemaSymbols.URI_XSI)) {
                    reportSchemaError("no-xsi", new Object[]{SchemaSymbols.URI_XSI}, attrDecl);
                    return null;
                }
            }
            if (nameAtt2.equals("(no name)")) {
                return null;
            }
            if (isGlobal) {
                if (grammar.getGlobalAttributeDecl(nameAtt2) == null) {
                    grammar.addGlobalAttributeDecl(attribute);
                }
                String loc = this.fSchemaHandler.schemaDocument2SystemId(schemaDoc);
                XSAttributeDecl attribute2 = grammar.getGlobalAttributeDecl(nameAtt2, loc);
                if (attribute2 == null) {
                    grammar.addGlobalAttributeDecl(attribute, loc);
                }
                if (this.fSchemaHandler.fTolerateDuplicates) {
                    if (attribute2 != null) {
                        attribute = attribute2;
                    }
                    this.fSchemaHandler.addGlobalAttributeDecl(attribute);
                }
            }
            return attribute;
        }
        reportSchemaError("no-xmlns", null, attrDecl);
        return null;
    }

    void checkDefaultValid(XSAttributeDecl attribute) throws InvalidDatatypeValueException {
        ((XSSimpleType) attribute.getTypeDefinition()).validate(attribute.getValInfo().normalizedValue, this.fValidationState, attribute.getValInfo());
        ((XSSimpleType) attribute.getTypeDefinition()).validate(attribute.getValInfo().stringValue(), this.fValidationState, attribute.getValInfo());
    }

    void checkDefaultValid(XSAttributeUseImpl attrUse) throws InvalidDatatypeValueException {
        ((XSSimpleType) attrUse.fAttrDecl.getTypeDefinition()).validate(attrUse.fDefault.normalizedValue, this.fValidationState, attrUse.fDefault);
        ((XSSimpleType) attrUse.fAttrDecl.getTypeDefinition()).validate(attrUse.fDefault.stringValue(), this.fValidationState, attrUse.fDefault);
    }
}
