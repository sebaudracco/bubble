package mf.org.apache.xerces.impl.xs.traversers;

import java.util.ArrayList;
import java.util.Vector;
import mf.org.apache.xerces.impl.dv.InvalidDatatypeFacetException;
import mf.org.apache.xerces.impl.dv.SchemaDVFactory;
import mf.org.apache.xerces.impl.dv.XSSimpleType;
import mf.org.apache.xerces.impl.dv.xs.XSSimpleTypeDecl;
import mf.org.apache.xerces.impl.xs.SchemaGrammar;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import mf.org.apache.xerces.impl.xs.XSAnnotationImpl;
import mf.org.apache.xerces.impl.xs.util.XInt;
import mf.org.apache.xerces.impl.xs.util.XSObjectListImpl;
import mf.org.apache.xerces.util.DOMUtil;
import mf.org.apache.xerces.xni.QName;
import mf.org.apache.xerces.xs.XSObjectList;
import mf.org.apache.xerces.xs.XSTypeDefinition;
import mf.org.w3c.dom.Element;

class XSDSimpleTypeTraverser extends XSDAbstractTraverser {
    private boolean fIsBuiltIn = false;

    XSDSimpleTypeTraverser(XSDHandler handler, XSAttributeChecker gAttrCheck) {
        super(handler, gAttrCheck);
    }

    XSSimpleType traverseGlobal(Element elmNode, XSDocumentInfo schemaDoc, SchemaGrammar grammar) {
        Object[] attrValues = this.fAttrChecker.checkAttributes(elmNode, true, schemaDoc);
        String nameAtt = attrValues[XSAttributeChecker.ATTIDX_NAME];
        if (nameAtt == null) {
            attrValues[XSAttributeChecker.ATTIDX_NAME] = "(no name)";
        }
        XSSimpleType type = traverseSimpleTypeDecl(elmNode, attrValues, schemaDoc, grammar);
        this.fAttrChecker.returnAttrArray(attrValues, schemaDoc);
        if (nameAtt == null) {
            reportSchemaError("s4s-att-must-appear", new Object[]{SchemaSymbols.ELT_SIMPLETYPE, SchemaSymbols.ATT_NAME}, elmNode);
            type = null;
        }
        if (type != null) {
            if (grammar.getGlobalTypeDecl(type.getName()) == null) {
                grammar.addGlobalSimpleTypeDecl(type);
            }
            String loc = this.fSchemaHandler.schemaDocument2SystemId(schemaDoc);
            XSTypeDefinition type2 = grammar.getGlobalTypeDecl(type.getName(), loc);
            if (type2 == null) {
                grammar.addGlobalSimpleTypeDecl(type, loc);
            }
            if (this.fSchemaHandler.fTolerateDuplicates) {
                if (type2 != null && (type2 instanceof XSSimpleType)) {
                    type = (XSSimpleType) type2;
                }
                this.fSchemaHandler.addGlobalTypeDecl(type);
            }
        }
        return type;
    }

    XSSimpleType traverseLocal(Element elmNode, XSDocumentInfo schemaDoc, SchemaGrammar grammar) {
        Object[] attrValues = this.fAttrChecker.checkAttributes(elmNode, false, schemaDoc);
        XSSimpleType type = getSimpleType(genAnonTypeName(elmNode), elmNode, attrValues, schemaDoc, grammar);
        if (type instanceof XSSimpleTypeDecl) {
            ((XSSimpleTypeDecl) type).setAnonymous(true);
        }
        this.fAttrChecker.returnAttrArray(attrValues, schemaDoc);
        return type;
    }

    private XSSimpleType traverseSimpleTypeDecl(Element simpleTypeDecl, Object[] attrValues, XSDocumentInfo schemaDoc, SchemaGrammar grammar) {
        return getSimpleType(attrValues[XSAttributeChecker.ATTIDX_NAME], simpleTypeDecl, attrValues, schemaDoc, grammar);
    }

    private String genAnonTypeName(Element simpleTypeDecl) {
        StringBuffer typeName = new StringBuffer("#AnonType_");
        Element node = DOMUtil.getParent(simpleTypeDecl);
        while (node != null && node != DOMUtil.getRoot(DOMUtil.getDocument(node))) {
            typeName.append(node.getAttribute(SchemaSymbols.ATT_NAME));
            node = DOMUtil.getParent(node);
        }
        return typeName.toString();
    }

    private XSSimpleType getSimpleType(String name, Element simpleTypeDecl, Object[] attrValues, XSDocumentInfo schemaDoc, SchemaGrammar grammar) {
        Element child;
        XInt finalAttr = attrValues[XSAttributeChecker.ATTIDX_FINAL];
        int finalProperty = finalAttr == null ? schemaDoc.fFinalDefault : finalAttr.intValue();
        Element child2 = DOMUtil.getFirstChildElement(simpleTypeDecl);
        XSAnnotationImpl[] annotations = null;
        if (child2 == null || !DOMUtil.getLocalName(child2).equals(SchemaSymbols.ELT_ANNOTATION)) {
            if (DOMUtil.getSyntheticAnnotation(simpleTypeDecl) != null) {
                annotations = new XSAnnotationImpl[]{traverseSyntheticAnnotation(simpleTypeDecl, DOMUtil.getSyntheticAnnotation(simpleTypeDecl), attrValues, false, schemaDoc)};
                child = child2;
            } else {
                child = child2;
            }
        } else {
            if (traverseAnnotationDecl(child2, attrValues, false, schemaDoc) != null) {
                annotations = new XSAnnotationImpl[]{traverseAnnotationDecl(child2, attrValues, false, schemaDoc)};
            }
            child = DOMUtil.getNextSiblingElement(child2);
        }
        if (child == null) {
            reportSchemaError("s4s-elt-must-match.2", new Object[]{SchemaSymbols.ELT_SIMPLETYPE, "(annotation?, (restriction | list | union))"}, simpleTypeDecl);
            return errorType(name, schemaDoc.fTargetNamespace, (short) 2);
        }
        short refType;
        int i;
        XSSimpleType dv;
        XSObjectList dvs;
        int j;
        String varietyProperty = DOMUtil.getLocalName(child);
        boolean restriction = false;
        boolean list = false;
        boolean union = false;
        if (varietyProperty.equals(SchemaSymbols.ELT_RESTRICTION)) {
            refType = (short) 2;
            restriction = true;
        } else {
            if (varietyProperty.equals(SchemaSymbols.ELT_LIST)) {
                refType = (short) 16;
                list = true;
            } else {
                if (varietyProperty.equals(SchemaSymbols.ELT_UNION)) {
                    refType = (short) 8;
                    union = true;
                } else {
                    reportSchemaError("s4s-elt-must-match.1", new Object[]{SchemaSymbols.ELT_SIMPLETYPE, "(annotation?, (restriction | list | union))", varietyProperty}, simpleTypeDecl);
                    return errorType(name, schemaDoc.fTargetNamespace, (short) 2);
                }
            }
        }
        Element nextChild = DOMUtil.getNextSiblingElement(child);
        if (nextChild != null) {
            reportSchemaError("s4s-elt-must-match.1", new Object[]{SchemaSymbols.ELT_SIMPLETYPE, "(annotation?, (restriction | list | union))", DOMUtil.getLocalName(nextChild)}, nextChild);
        }
        Object[] contentAttrs = this.fAttrChecker.checkAttributes(child, false, schemaDoc);
        if (restriction) {
            i = XSAttributeChecker.ATTIDX_BASE;
        } else {
            i = XSAttributeChecker.ATTIDX_ITEMTYPE;
        }
        QName baseTypeName = contentAttrs[i];
        Vector memberTypes = contentAttrs[XSAttributeChecker.ATTIDX_MEMBERTYPES];
        Element content = DOMUtil.getFirstChildElement(child);
        XSAnnotationImpl annotation;
        XSAnnotationImpl[] tempArray;
        if (content == null || !DOMUtil.getLocalName(content).equals(SchemaSymbols.ELT_ANNOTATION)) {
            String text = DOMUtil.getSyntheticAnnotation(child);
            if (text != null) {
                annotation = traverseSyntheticAnnotation(child, text, contentAttrs, false, schemaDoc);
                if (annotations == null) {
                    annotations = new XSAnnotationImpl[]{annotation};
                } else {
                    tempArray = new XSAnnotationImpl[2];
                    tempArray[0] = annotations[0];
                    annotations = tempArray;
                    annotations[1] = annotation;
                }
            }
        } else {
            annotation = traverseAnnotationDecl(content, contentAttrs, false, schemaDoc);
            if (annotation != null) {
                if (annotations == null) {
                    annotations = new XSAnnotationImpl[]{annotation};
                } else {
                    tempArray = new XSAnnotationImpl[2];
                    tempArray[0] = annotations[0];
                    annotations = tempArray;
                    annotations[1] = annotation;
                }
            }
            content = DOMUtil.getNextSiblingElement(content);
        }
        XSSimpleType baseValidator = null;
        if ((restriction || list) && baseTypeName != null) {
            baseValidator = findDTValidator(child, name, baseTypeName, refType, schemaDoc);
            if (baseValidator == null && this.fIsBuiltIn) {
                this.fIsBuiltIn = false;
                return null;
            }
        }
        XSSimpleType baseValidator2 = baseValidator;
        ArrayList arrayList = null;
        if (union && memberTypes != null && memberTypes.size() > 0) {
            int size = memberTypes.size();
            ArrayList arrayList2 = new ArrayList(size);
            for (int i2 = 0; i2 < size; i2++) {
                dv = findDTValidator(child, name, (QName) memberTypes.elementAt(i2), (short) 8, schemaDoc);
                if (dv != null) {
                    if (dv.getVariety() == (short) 3) {
                        dvs = dv.getMemberTypes();
                        for (j = 0; j < dvs.getLength(); j++) {
                            arrayList2.add(dvs.item(j));
                        }
                    } else {
                        arrayList2.add(dv);
                    }
                }
            }
        }
        if (content != null && DOMUtil.getLocalName(content).equals(SchemaSymbols.ELT_SIMPLETYPE)) {
            if (!restriction && !list) {
                if (union) {
                    if (arrayList == null) {
                        arrayList2 = new ArrayList(2);
                    }
                    do {
                        dv = traverseLocal(content, schemaDoc, grammar);
                        if (dv != null) {
                            if (dv.getVariety() == (short) 3) {
                                dvs = dv.getMemberTypes();
                                for (j = 0; j < dvs.getLength(); j++) {
                                    arrayList.add(dvs.item(j));
                                }
                            } else {
                                arrayList.add(dv);
                            }
                        }
                        content = DOMUtil.getNextSiblingElement(content);
                        if (content != null) {
                        }
                    } while (DOMUtil.getLocalName(content).equals(SchemaSymbols.ELT_SIMPLETYPE));
                    baseValidator = baseValidator2;
                }
                baseValidator = baseValidator2;
                break;
            }
            if (baseTypeName != null) {
                reportSchemaError(list ? "src-simple-type.3.a" : "src-simple-type.2.a", null, content);
            }
            if (baseValidator2 == null) {
                baseValidator = traverseLocal(content, schemaDoc, grammar);
            } else {
                baseValidator = baseValidator2;
            }
            content = DOMUtil.getNextSiblingElement(content);
        } else if ((!restriction && !list) || baseTypeName != null) {
            if (union && (memberTypes == null || memberTypes.size() == 0)) {
                reportSchemaError("src-union-memberTypes-or-simpleTypes", null, child);
            }
            baseValidator = baseValidator2;
            break;
        } else {
            reportSchemaError(list ? "src-simple-type.3.b" : "src-simple-type.2.b", null, child);
            baseValidator = baseValidator2;
        }
        if ((restriction || list) && baseValidator == null) {
            this.fAttrChecker.returnAttrArray(contentAttrs, schemaDoc);
            return errorType(name, schemaDoc.fTargetNamespace, restriction ? (short) 2 : (short) 16);
        } else if (union && (arrayList == null || arrayList.size() == 0)) {
            this.fAttrChecker.returnAttrArray(contentAttrs, schemaDoc);
            return errorType(name, schemaDoc.fTargetNamespace, (short) 8);
        } else if (list && isListDatatype(baseValidator)) {
            reportSchemaError("cos-st-restricts.2.1", new Object[]{name, baseValidator.getName()}, child);
            this.fAttrChecker.returnAttrArray(contentAttrs, schemaDoc);
            return errorType(name, schemaDoc.fTargetNamespace, (short) 16);
        } else {
            SchemaDVFactory schemaDVFactory;
            String str;
            short s;
            XSObjectList xSObjectList;
            XSObjectList xSObjectListImpl;
            XSSimpleType newDecl = null;
            if (restriction) {
                schemaDVFactory = this.fSchemaHandler.fDVFactory;
                str = schemaDoc.fTargetNamespace;
                s = (short) finalProperty;
                if (annotations == null) {
                    xSObjectList = null;
                } else {
                    xSObjectListImpl = new XSObjectListImpl(annotations, annotations.length);
                }
                newDecl = schemaDVFactory.createTypeRestriction(name, str, s, baseValidator, xSObjectList);
            } else if (list) {
                schemaDVFactory = this.fSchemaHandler.fDVFactory;
                str = schemaDoc.fTargetNamespace;
                s = (short) finalProperty;
                if (annotations == null) {
                    xSObjectList = null;
                } else {
                    xSObjectListImpl = new XSObjectListImpl(annotations, annotations.length);
                }
                newDecl = schemaDVFactory.createTypeList(name, str, s, baseValidator, xSObjectList);
            } else if (union) {
                XSObjectList xSObjectList2;
                XSSimpleType[] memberDecls = (XSSimpleType[]) arrayList.toArray(new XSSimpleType[arrayList.size()]);
                SchemaDVFactory schemaDVFactory2 = this.fSchemaHandler.fDVFactory;
                String str2 = schemaDoc.fTargetNamespace;
                short s2 = (short) finalProperty;
                if (annotations == null) {
                    xSObjectList2 = null;
                } else {
                    xSObjectListImpl = new XSObjectListImpl(annotations, annotations.length);
                }
                newDecl = schemaDVFactory2.createTypeUnion(name, str2, s2, memberDecls, xSObjectList2);
            }
            if (restriction && content != null) {
                FacetInfo fi = traverseFacets(content, baseValidator, schemaDoc);
                content = fi.nodeAfterFacets;
                try {
                    this.fValidationState.setNamespaceSupport(schemaDoc.fNamespaceSupport);
                    newDecl.applyFacets(fi.facetdata, fi.fPresentFacets, fi.fFixedFacets, this.fValidationState);
                } catch (InvalidDatatypeFacetException ex) {
                    reportSchemaError(ex.getKey(), ex.getArgs(), child);
                    schemaDVFactory = this.fSchemaHandler.fDVFactory;
                    str = schemaDoc.fTargetNamespace;
                    s = (short) finalProperty;
                    if (annotations == null) {
                        xSObjectList = null;
                    } else {
                        xSObjectListImpl = new XSObjectListImpl(annotations, annotations.length);
                    }
                    newDecl = schemaDVFactory.createTypeRestriction(name, str, s, baseValidator, xSObjectList);
                }
            }
            if (content != null) {
                if (restriction) {
                    reportSchemaError("s4s-elt-must-match.1", new Object[]{SchemaSymbols.ELT_RESTRICTION, "(annotation?, (simpleType?, (minExclusive | minInclusive | maxExclusive | maxInclusive | totalDigits | fractionDigits | length | minLength | maxLength | enumeration | whiteSpace | pattern)*))", DOMUtil.getLocalName(content)}, content);
                } else if (list) {
                    reportSchemaError("s4s-elt-must-match.1", new Object[]{SchemaSymbols.ELT_LIST, "(annotation?, (simpleType?))", DOMUtil.getLocalName(content)}, content);
                } else if (union) {
                    reportSchemaError("s4s-elt-must-match.1", new Object[]{SchemaSymbols.ELT_UNION, "(annotation?, (simpleType*))", DOMUtil.getLocalName(content)}, content);
                }
            }
            this.fAttrChecker.returnAttrArray(contentAttrs, schemaDoc);
            return newDecl;
        }
    }

    private XSSimpleType findDTValidator(Element elm, String refName, QName baseTypeStr, short baseRefContext, XSDocumentInfo schemaDoc) {
        if (baseTypeStr == null) {
            return null;
        }
        XSTypeDefinition baseType = (XSTypeDefinition) this.fSchemaHandler.getGlobalDecl(schemaDoc, 7, baseTypeStr, elm);
        if (baseType == null) {
            return null;
        }
        if (baseType.getTypeCategory() != (short) 16) {
            reportSchemaError("cos-st-restricts.1.1", new Object[]{baseTypeStr.rawname, refName}, elm);
            return null;
        } else if (baseType == SchemaGrammar.fAnySimpleType && baseRefContext == (short) 2) {
            if (checkBuiltIn(refName, schemaDoc.fTargetNamespace)) {
                return null;
            }
            reportSchemaError("cos-st-restricts.1.1", new Object[]{baseTypeStr.rawname, refName}, elm);
            return null;
        } else if ((baseType.getFinal() & baseRefContext) == 0) {
            return (XSSimpleType) baseType;
        } else {
            if (baseRefContext == (short) 2) {
                reportSchemaError("st-props-correct.3", new Object[]{refName, baseTypeStr.rawname}, elm);
            } else if (baseRefContext == (short) 16) {
                reportSchemaError("cos-st-restricts.2.3.1.1", new Object[]{baseTypeStr.rawname, refName}, elm);
            } else if (baseRefContext == (short) 8) {
                reportSchemaError("cos-st-restricts.3.3.1.1", new Object[]{baseTypeStr.rawname, refName}, elm);
            }
            return null;
        }
    }

    private final boolean checkBuiltIn(String name, String namespace) {
        if (namespace != SchemaSymbols.URI_SCHEMAFORSCHEMA) {
            return false;
        }
        if (SchemaGrammar.SG_SchemaNS.getGlobalTypeDecl(name) != null) {
            this.fIsBuiltIn = true;
        }
        return this.fIsBuiltIn;
    }

    private boolean isListDatatype(XSSimpleType validator) {
        if (validator.getVariety() == (short) 2) {
            return true;
        }
        if (validator.getVariety() == (short) 3) {
            XSObjectList temp = validator.getMemberTypes();
            for (int i = 0; i < temp.getLength(); i++) {
                if (((XSSimpleType) temp.item(i)).getVariety() == (short) 2) {
                    return true;
                }
            }
        }
        return false;
    }

    private XSSimpleType errorType(String name, String namespace, short refType) {
        XSSimpleType stringType = (XSSimpleType) SchemaGrammar.SG_SchemaNS.getTypeDefinition(SchemaSymbols.ATTVAL_STRING);
        switch (refType) {
            case (short) 2:
                return this.fSchemaHandler.fDVFactory.createTypeRestriction(name, namespace, (short) 0, stringType, null);
            case (short) 8:
                return this.fSchemaHandler.fDVFactory.createTypeUnion(name, namespace, (short) 0, new XSSimpleType[]{stringType}, null);
            case (short) 16:
                return this.fSchemaHandler.fDVFactory.createTypeList(name, namespace, (short) 0, stringType, null);
            default:
                return null;
        }
    }
}
