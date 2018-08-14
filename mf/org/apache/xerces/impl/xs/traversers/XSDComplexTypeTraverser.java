package mf.org.apache.xerces.impl.xs.traversers;

import mf.org.apache.xerces.impl.dv.InvalidDatatypeFacetException;
import mf.org.apache.xerces.impl.dv.XSFacets;
import mf.org.apache.xerces.impl.dv.XSSimpleType;
import mf.org.apache.xerces.impl.dv.xs.XSSimpleTypeDecl;
import mf.org.apache.xerces.impl.xs.SchemaGrammar;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import mf.org.apache.xerces.impl.xs.XSAnnotationImpl;
import mf.org.apache.xerces.impl.xs.XSAttributeGroupDecl;
import mf.org.apache.xerces.impl.xs.XSAttributeUseImpl;
import mf.org.apache.xerces.impl.xs.XSComplexTypeDecl;
import mf.org.apache.xerces.impl.xs.XSConstraints;
import mf.org.apache.xerces.impl.xs.XSModelGroupImpl;
import mf.org.apache.xerces.impl.xs.XSParticleDecl;
import mf.org.apache.xerces.impl.xs.XSWildcardDecl;
import mf.org.apache.xerces.impl.xs.util.XInt;
import mf.org.apache.xerces.impl.xs.util.XSObjectListImpl;
import mf.org.apache.xerces.util.DOMUtil;
import mf.org.apache.xerces.xni.QName;
import mf.org.apache.xerces.xs.XSAttributeUse;
import mf.org.apache.xerces.xs.XSObject;
import mf.org.apache.xerces.xs.XSObjectList;
import mf.org.apache.xerces.xs.XSTypeDefinition;
import mf.org.w3c.dom.Element;

class XSDComplexTypeTraverser extends XSDAbstractParticleTraverser {
    private static final boolean DEBUG = false;
    private static final int GLOBAL_NUM = 11;
    private static XSParticleDecl fErrorContent = null;
    private static XSWildcardDecl fErrorWildcard = null;
    private XSAnnotationImpl[] fAnnotations = null;
    private XSAttributeGroupDecl fAttrGrp = null;
    private XSTypeDefinition fBaseType = null;
    private short fBlock = (short) 0;
    private XSComplexTypeDecl fComplexTypeDecl = null;
    private short fContentType = (short) 0;
    private short fDerivedBy = (short) 2;
    private short fFinal = (short) 0;
    private Object[] fGlobalStore = null;
    private int fGlobalStorePos = 0;
    private boolean fIsAbstract = false;
    private String fName = null;
    private XSParticleDecl fParticle = null;
    private String fTargetNamespace = null;
    private XSSimpleType fXSSimpleType = null;

    private static final class ComplexTypeRecoverableError extends Exception {
        private static final long serialVersionUID = 6802729912091130335L;
        Element errorElem = null;
        Object[] errorSubstText = null;

        ComplexTypeRecoverableError() {
        }

        ComplexTypeRecoverableError(String msgKey, Object[] args, Element e) {
            super(msgKey);
            this.errorSubstText = args;
            this.errorElem = e;
        }
    }

    private static XSParticleDecl getErrorContent() {
        if (fErrorContent == null) {
            XSParticleDecl particle = new XSParticleDecl();
            particle.fType = (short) 2;
            particle.fValue = getErrorWildcard();
            particle.fMinOccurs = 0;
            particle.fMaxOccurs = -1;
            XSModelGroupImpl group = new XSModelGroupImpl();
            group.fCompositor = (short) 102;
            group.fParticleCount = 1;
            group.fParticles = new XSParticleDecl[1];
            group.fParticles[0] = particle;
            XSParticleDecl errorContent = new XSParticleDecl();
            errorContent.fType = (short) 3;
            errorContent.fValue = group;
            fErrorContent = errorContent;
        }
        return fErrorContent;
    }

    private static XSWildcardDecl getErrorWildcard() {
        if (fErrorWildcard == null) {
            XSWildcardDecl wildcard = new XSWildcardDecl();
            wildcard.fProcessContents = (short) 2;
            fErrorWildcard = wildcard;
        }
        return fErrorWildcard;
    }

    XSDComplexTypeTraverser(XSDHandler handler, XSAttributeChecker gAttrCheck) {
        super(handler, gAttrCheck);
    }

    XSComplexTypeDecl traverseLocal(Element complexTypeNode, XSDocumentInfo schemaDoc, SchemaGrammar grammar) {
        Object[] attrValues = this.fAttrChecker.checkAttributes(complexTypeNode, false, schemaDoc);
        String complexTypeName = genAnonTypeName(complexTypeNode);
        contentBackup();
        XSComplexTypeDecl type = traverseComplexTypeDecl(complexTypeNode, complexTypeName, attrValues, schemaDoc, grammar);
        contentRestore();
        grammar.addComplexTypeDecl(type, this.fSchemaHandler.element2Locator(complexTypeNode));
        type.setIsAnonymous();
        this.fAttrChecker.returnAttrArray(attrValues, schemaDoc);
        return type;
    }

    XSComplexTypeDecl traverseGlobal(Element complexTypeNode, XSDocumentInfo schemaDoc, SchemaGrammar grammar) {
        Object[] attrValues = this.fAttrChecker.checkAttributes(complexTypeNode, true, schemaDoc);
        String complexTypeName = attrValues[XSAttributeChecker.ATTIDX_NAME];
        contentBackup();
        XSComplexTypeDecl type = traverseComplexTypeDecl(complexTypeNode, complexTypeName, attrValues, schemaDoc, grammar);
        contentRestore();
        grammar.addComplexTypeDecl(type, this.fSchemaHandler.element2Locator(complexTypeNode));
        if (complexTypeName == null) {
            reportSchemaError("s4s-att-must-appear", new Object[]{SchemaSymbols.ELT_COMPLEXTYPE, SchemaSymbols.ATT_NAME}, complexTypeNode);
            type = null;
        } else {
            if (grammar.getGlobalTypeDecl(type.getName()) == null) {
                grammar.addGlobalComplexTypeDecl(type);
            }
            String loc = this.fSchemaHandler.schemaDocument2SystemId(schemaDoc);
            XSTypeDefinition type2 = grammar.getGlobalTypeDecl(type.getName(), loc);
            if (type2 == null) {
                grammar.addGlobalComplexTypeDecl(type, loc);
            }
            if (this.fSchemaHandler.fTolerateDuplicates) {
                if (type2 != null && (type2 instanceof XSComplexTypeDecl)) {
                    type = (XSComplexTypeDecl) type2;
                }
                this.fSchemaHandler.addGlobalTypeDecl(type);
            }
        }
        this.fAttrChecker.returnAttrArray(attrValues, schemaDoc);
        return type;
    }

    private XSComplexTypeDecl traverseComplexTypeDecl(Element complexTypeDecl, String complexTypeName, Object[] attrValues, XSDocumentInfo schemaDoc, SchemaGrammar grammar) {
        ComplexTypeRecoverableError e;
        XSComplexTypeDecl xSComplexTypeDecl;
        String str;
        String str2;
        XSTypeDefinition xSTypeDefinition;
        short s;
        short s2;
        short s3;
        short s4;
        boolean z;
        XSAttributeGroupDecl xSAttributeGroupDecl;
        XSSimpleType xSSimpleType;
        XSParticleDecl xSParticleDecl;
        XSObject[] xSObjectArr;
        int i;
        this.fComplexTypeDecl = new XSComplexTypeDecl();
        this.fAttrGrp = new XSAttributeGroupDecl();
        Boolean abstractAtt = attrValues[XSAttributeChecker.ATTIDX_ABSTRACT];
        XInt blockAtt = attrValues[XSAttributeChecker.ATTIDX_BLOCK];
        Boolean mixedAtt = attrValues[XSAttributeChecker.ATTIDX_MIXED];
        XInt finalAtt = attrValues[XSAttributeChecker.ATTIDX_FINAL];
        this.fName = complexTypeName;
        this.fComplexTypeDecl.setName(this.fName);
        this.fTargetNamespace = schemaDoc.fTargetNamespace;
        this.fBlock = blockAtt == null ? schemaDoc.fBlockDefault : blockAtt.shortValue();
        this.fFinal = finalAtt == null ? schemaDoc.fFinalDefault : finalAtt.shortValue();
        this.fBlock = (short) (this.fBlock & 3);
        this.fFinal = (short) (this.fFinal & 3);
        boolean z2 = abstractAtt != null && abstractAtt.booleanValue();
        this.fIsAbstract = z2;
        this.fAnnotations = null;
        try {
            Element child;
            Element child2 = DOMUtil.getFirstChildElement(complexTypeDecl);
            String text;
            if (child2 != null) {
                try {
                    if (DOMUtil.getLocalName(child2).equals(SchemaSymbols.ELT_ANNOTATION)) {
                        addAnnotation(traverseAnnotationDecl(child2, attrValues, false, schemaDoc));
                        child = DOMUtil.getNextSiblingElement(child2);
                    } else {
                        text = DOMUtil.getSyntheticAnnotation(complexTypeDecl);
                        if (text != null) {
                            addAnnotation(traverseSyntheticAnnotation(complexTypeDecl, text, attrValues, false, schemaDoc));
                        }
                        child = child2;
                    }
                    if (child != null) {
                        if (DOMUtil.getLocalName(child).equals(SchemaSymbols.ELT_ANNOTATION)) {
                            throw new ComplexTypeRecoverableError("s4s-elt-invalid-content.1", new Object[]{this.fName, SchemaSymbols.ELT_ANNOTATION}, child);
                        }
                    }
                } catch (ComplexTypeRecoverableError e2) {
                    e = e2;
                    child = child2;
                    handleComplexTypeError(e.getMessage(), e.errorSubstText, e.errorElem);
                    xSComplexTypeDecl = this.fComplexTypeDecl;
                    str = this.fName;
                    str2 = this.fTargetNamespace;
                    xSTypeDefinition = this.fBaseType;
                    s = this.fDerivedBy;
                    s2 = this.fFinal;
                    s3 = this.fBlock;
                    s4 = this.fContentType;
                    z = this.fIsAbstract;
                    xSAttributeGroupDecl = this.fAttrGrp;
                    xSSimpleType = this.fXSSimpleType;
                    xSParticleDecl = this.fParticle;
                    xSObjectArr = this.fAnnotations;
                    if (this.fAnnotations == null) {
                        i = 0;
                    } else {
                        i = this.fAnnotations.length;
                    }
                    xSComplexTypeDecl.setValues(str, str2, xSTypeDefinition, s, s2, s3, s4, z, xSAttributeGroupDecl, xSSimpleType, xSParticleDecl, new XSObjectListImpl(xSObjectArr, i));
                    return this.fComplexTypeDecl;
                }
            }
            text = DOMUtil.getSyntheticAnnotation(complexTypeDecl);
            if (text != null) {
                addAnnotation(traverseSyntheticAnnotation(complexTypeDecl, text, attrValues, false, schemaDoc));
            }
            child = child2;
            if (child == null) {
                this.fBaseType = SchemaGrammar.fAnyType;
                this.fDerivedBy = (short) 2;
                processComplexContent(child, mixedAtt.booleanValue(), false, schemaDoc, grammar);
            } else if (DOMUtil.getLocalName(child).equals(SchemaSymbols.ELT_SIMPLECONTENT)) {
                traverseSimpleContent(child, schemaDoc, grammar);
                elemTmp = DOMUtil.getNextSiblingElement(child);
                if (elemTmp != null) {
                    siblingName = DOMUtil.getLocalName(elemTmp);
                    throw new ComplexTypeRecoverableError("s4s-elt-invalid-content.1", new Object[]{this.fName, siblingName}, elemTmp);
                }
            } else if (DOMUtil.getLocalName(child).equals(SchemaSymbols.ELT_COMPLEXCONTENT)) {
                traverseComplexContent(child, mixedAtt.booleanValue(), schemaDoc, grammar);
                elemTmp = DOMUtil.getNextSiblingElement(child);
                if (elemTmp != null) {
                    siblingName = DOMUtil.getLocalName(elemTmp);
                    throw new ComplexTypeRecoverableError("s4s-elt-invalid-content.1", new Object[]{this.fName, siblingName}, elemTmp);
                }
            } else {
                this.fBaseType = SchemaGrammar.fAnyType;
                this.fDerivedBy = (short) 2;
                processComplexContent(child, mixedAtt.booleanValue(), false, schemaDoc, grammar);
            }
        } catch (ComplexTypeRecoverableError e3) {
            e = e3;
            handleComplexTypeError(e.getMessage(), e.errorSubstText, e.errorElem);
            xSComplexTypeDecl = this.fComplexTypeDecl;
            str = this.fName;
            str2 = this.fTargetNamespace;
            xSTypeDefinition = this.fBaseType;
            s = this.fDerivedBy;
            s2 = this.fFinal;
            s3 = this.fBlock;
            s4 = this.fContentType;
            z = this.fIsAbstract;
            xSAttributeGroupDecl = this.fAttrGrp;
            xSSimpleType = this.fXSSimpleType;
            xSParticleDecl = this.fParticle;
            xSObjectArr = this.fAnnotations;
            if (this.fAnnotations == null) {
                i = this.fAnnotations.length;
            } else {
                i = 0;
            }
            xSComplexTypeDecl.setValues(str, str2, xSTypeDefinition, s, s2, s3, s4, z, xSAttributeGroupDecl, xSSimpleType, xSParticleDecl, new XSObjectListImpl(xSObjectArr, i));
            return this.fComplexTypeDecl;
        }
        xSComplexTypeDecl = this.fComplexTypeDecl;
        str = this.fName;
        str2 = this.fTargetNamespace;
        xSTypeDefinition = this.fBaseType;
        s = this.fDerivedBy;
        s2 = this.fFinal;
        s3 = this.fBlock;
        s4 = this.fContentType;
        z = this.fIsAbstract;
        xSAttributeGroupDecl = this.fAttrGrp;
        xSSimpleType = this.fXSSimpleType;
        xSParticleDecl = this.fParticle;
        xSObjectArr = this.fAnnotations;
        if (this.fAnnotations == null) {
            i = 0;
        } else {
            i = this.fAnnotations.length;
        }
        xSComplexTypeDecl.setValues(str, str2, xSTypeDefinition, s, s2, s3, s4, z, xSAttributeGroupDecl, xSSimpleType, xSParticleDecl, new XSObjectListImpl(xSObjectArr, i));
        return this.fComplexTypeDecl;
    }

    private void traverseSimpleContent(Element simpleContentElement, XSDocumentInfo schemaDoc, SchemaGrammar grammar) throws ComplexTypeRecoverableError {
        String text;
        Object[] simpleContentAttrValues = this.fAttrChecker.checkAttributes(simpleContentElement, false, schemaDoc);
        this.fContentType = (short) 1;
        this.fParticle = null;
        Element simpleContent = DOMUtil.getFirstChildElement(simpleContentElement);
        if (simpleContent == null || !DOMUtil.getLocalName(simpleContent).equals(SchemaSymbols.ELT_ANNOTATION)) {
            text = DOMUtil.getSyntheticAnnotation(simpleContentElement);
            if (text != null) {
                addAnnotation(traverseSyntheticAnnotation(simpleContentElement, text, simpleContentAttrValues, false, schemaDoc));
            }
        } else {
            addAnnotation(traverseAnnotationDecl(simpleContent, simpleContentAttrValues, false, schemaDoc));
            simpleContent = DOMUtil.getNextSiblingElement(simpleContent);
        }
        if (simpleContent == null) {
            this.fAttrChecker.returnAttrArray(simpleContentAttrValues, schemaDoc);
            throw new ComplexTypeRecoverableError("s4s-elt-invalid-content.2", new Object[]{this.fName, SchemaSymbols.ELT_SIMPLECONTENT}, simpleContentElement);
        }
        String simpleContentName = DOMUtil.getLocalName(simpleContent);
        if (simpleContentName.equals(SchemaSymbols.ELT_RESTRICTION)) {
            this.fDerivedBy = (short) 2;
        } else {
            if (simpleContentName.equals(SchemaSymbols.ELT_EXTENSION)) {
                this.fDerivedBy = (short) 1;
            } else {
                this.fAttrChecker.returnAttrArray(simpleContentAttrValues, schemaDoc);
                throw new ComplexTypeRecoverableError("s4s-elt-invalid-content.1", new Object[]{this.fName, simpleContentName}, simpleContent);
            }
        }
        Element elemTmp = DOMUtil.getNextSiblingElement(simpleContent);
        if (elemTmp != null) {
            this.fAttrChecker.returnAttrArray(simpleContentAttrValues, schemaDoc);
            String siblingName = DOMUtil.getLocalName(elemTmp);
            throw new ComplexTypeRecoverableError("s4s-elt-invalid-content.1", new Object[]{this.fName, siblingName}, elemTmp);
        }
        Object[] derivationTypeAttrValues = this.fAttrChecker.checkAttributes(simpleContent, false, schemaDoc);
        QName baseTypeName = derivationTypeAttrValues[XSAttributeChecker.ATTIDX_BASE];
        if (baseTypeName == null) {
            this.fAttrChecker.returnAttrArray(simpleContentAttrValues, schemaDoc);
            this.fAttrChecker.returnAttrArray(derivationTypeAttrValues, schemaDoc);
            throw new ComplexTypeRecoverableError("s4s-att-must-appear", new Object[]{simpleContentName, "base"}, simpleContent);
        }
        XSTypeDefinition type = (XSTypeDefinition) this.fSchemaHandler.getGlobalDecl(schemaDoc, 7, baseTypeName, simpleContent);
        if (type == null) {
            this.fAttrChecker.returnAttrArray(simpleContentAttrValues, schemaDoc);
            this.fAttrChecker.returnAttrArray(derivationTypeAttrValues, schemaDoc);
            throw new ComplexTypeRecoverableError();
        }
        this.fBaseType = type;
        XSSimpleType baseValidator = null;
        XSComplexTypeDecl baseComplexType = null;
        if (type.getTypeCategory() == (short) 15) {
            baseComplexType = (XSComplexTypeDecl) type;
            int baseFinalSet = baseComplexType.getFinal();
            if (baseComplexType.getContentType() == (short) 1) {
                baseValidator = (XSSimpleType) baseComplexType.getSimpleType();
            } else if (!(this.fDerivedBy == (short) 2 && baseComplexType.getContentType() == (short) 3 && ((XSParticleDecl) baseComplexType.getParticle()).emptiable())) {
                this.fAttrChecker.returnAttrArray(simpleContentAttrValues, schemaDoc);
                this.fAttrChecker.returnAttrArray(derivationTypeAttrValues, schemaDoc);
                throw new ComplexTypeRecoverableError("src-ct.2.1", new Object[]{this.fName, baseComplexType.getName()}, simpleContent);
            }
        }
        baseValidator = (XSSimpleType) type;
        if (this.fDerivedBy == (short) 2) {
            this.fAttrChecker.returnAttrArray(simpleContentAttrValues, schemaDoc);
            this.fAttrChecker.returnAttrArray(derivationTypeAttrValues, schemaDoc);
            throw new ComplexTypeRecoverableError("src-ct.2.1", new Object[]{this.fName, baseValidator.getName()}, simpleContent);
        }
        baseFinalSet = baseValidator.getFinal();
        if ((this.fDerivedBy & baseFinalSet) != 0) {
            this.fAttrChecker.returnAttrArray(simpleContentAttrValues, schemaDoc);
            this.fAttrChecker.returnAttrArray(derivationTypeAttrValues, schemaDoc);
            throw new ComplexTypeRecoverableError(this.fDerivedBy == (short) 1 ? "cos-ct-extends.1.1" : "derivation-ok-restriction.1", new Object[]{this.fName, this.fBaseType.getName()}, simpleContent);
        }
        Element scElement = simpleContent;
        simpleContent = DOMUtil.getFirstChildElement(simpleContent);
        if (simpleContent != null) {
            if (DOMUtil.getLocalName(simpleContent).equals(SchemaSymbols.ELT_ANNOTATION)) {
                addAnnotation(traverseAnnotationDecl(simpleContent, derivationTypeAttrValues, false, schemaDoc));
                simpleContent = DOMUtil.getNextSiblingElement(simpleContent);
            } else {
                text = DOMUtil.getSyntheticAnnotation(scElement);
                if (text != null) {
                    addAnnotation(traverseSyntheticAnnotation(scElement, text, derivationTypeAttrValues, false, schemaDoc));
                }
            }
            if (simpleContent != null && DOMUtil.getLocalName(simpleContent).equals(SchemaSymbols.ELT_ANNOTATION)) {
                this.fAttrChecker.returnAttrArray(simpleContentAttrValues, schemaDoc);
                this.fAttrChecker.returnAttrArray(derivationTypeAttrValues, schemaDoc);
                throw new ComplexTypeRecoverableError("s4s-elt-invalid-content.1", new Object[]{this.fName, SchemaSymbols.ELT_ANNOTATION}, simpleContent);
            }
        }
        text = DOMUtil.getSyntheticAnnotation(scElement);
        if (text != null) {
            addAnnotation(traverseSyntheticAnnotation(scElement, text, derivationTypeAttrValues, false, schemaDoc));
        }
        Element attrNode;
        Element node;
        if (this.fDerivedBy == (short) 2) {
            if (simpleContent != null && DOMUtil.getLocalName(simpleContent).equals(SchemaSymbols.ELT_SIMPLETYPE)) {
                XSSimpleType dv = this.fSchemaHandler.fSimpleTypeTraverser.traverseLocal(simpleContent, schemaDoc, grammar);
                if (dv == null) {
                    this.fAttrChecker.returnAttrArray(simpleContentAttrValues, schemaDoc);
                    this.fAttrChecker.returnAttrArray(derivationTypeAttrValues, schemaDoc);
                    throw new ComplexTypeRecoverableError();
                }
                if (baseValidator != null) {
                    if (!XSConstraints.checkSimpleDerivationOk(dv, baseValidator, baseValidator.getFinal())) {
                        this.fAttrChecker.returnAttrArray(simpleContentAttrValues, schemaDoc);
                        this.fAttrChecker.returnAttrArray(derivationTypeAttrValues, schemaDoc);
                        throw new ComplexTypeRecoverableError("derivation-ok-restriction.5.2.2.1", new Object[]{this.fName, dv.getName(), baseValidator.getName()}, simpleContent);
                    }
                }
                baseValidator = dv;
                simpleContent = DOMUtil.getNextSiblingElement(simpleContent);
            }
            if (baseValidator == null) {
                this.fAttrChecker.returnAttrArray(simpleContentAttrValues, schemaDoc);
                this.fAttrChecker.returnAttrArray(derivationTypeAttrValues, schemaDoc);
                throw new ComplexTypeRecoverableError("src-ct.2.2", new Object[]{this.fName}, simpleContent);
            }
            attrNode = null;
            XSFacets facetData = null;
            short presentFacets = (short) 0;
            short fixedFacets = (short) 0;
            if (simpleContent != null) {
                FacetInfo fi = traverseFacets(simpleContent, baseValidator, schemaDoc);
                attrNode = fi.nodeAfterFacets;
                facetData = fi.facetdata;
                presentFacets = fi.fPresentFacets;
                fixedFacets = fi.fFixedFacets;
            }
            String name = genAnonTypeName(simpleContentElement);
            this.fXSSimpleType = this.fSchemaHandler.fDVFactory.createTypeRestriction(name, schemaDoc.fTargetNamespace, (short) 0, baseValidator, null);
            try {
                this.fValidationState.setNamespaceSupport(schemaDoc.fNamespaceSupport);
                this.fXSSimpleType.applyFacets(facetData, presentFacets, fixedFacets, this.fValidationState);
            } catch (InvalidDatatypeFacetException ex) {
                reportSchemaError(ex.getKey(), ex.getArgs(), simpleContent);
                this.fXSSimpleType = this.fSchemaHandler.fDVFactory.createTypeRestriction(name, schemaDoc.fTargetNamespace, (short) 0, baseValidator, null);
            }
            if (this.fXSSimpleType instanceof XSSimpleTypeDecl) {
                ((XSSimpleTypeDecl) this.fXSSimpleType).setAnonymous(true);
            }
            if (attrNode != null) {
                if (isAttrOrAttrGroup(attrNode)) {
                    node = traverseAttrsAndAttrGrps(attrNode, this.fAttrGrp, schemaDoc, grammar, this.fComplexTypeDecl);
                    if (node != null) {
                        this.fAttrChecker.returnAttrArray(simpleContentAttrValues, schemaDoc);
                        this.fAttrChecker.returnAttrArray(derivationTypeAttrValues, schemaDoc);
                        throw new ComplexTypeRecoverableError("s4s-elt-invalid-content.1", new Object[]{this.fName, DOMUtil.getLocalName(node)}, node);
                    }
                }
                this.fAttrChecker.returnAttrArray(simpleContentAttrValues, schemaDoc);
                this.fAttrChecker.returnAttrArray(derivationTypeAttrValues, schemaDoc);
                throw new ComplexTypeRecoverableError("s4s-elt-invalid-content.1", new Object[]{this.fName, DOMUtil.getLocalName(attrNode)}, attrNode);
            }
            try {
                mergeAttributes(baseComplexType.getAttrGrp(), this.fAttrGrp, this.fName, false, simpleContentElement);
                this.fAttrGrp.removeProhibitedAttrs();
                Object[] errArgs = this.fAttrGrp.validRestrictionOf(this.fName, baseComplexType.getAttrGrp());
                if (errArgs != null) {
                    this.fAttrChecker.returnAttrArray(simpleContentAttrValues, schemaDoc);
                    this.fAttrChecker.returnAttrArray(derivationTypeAttrValues, schemaDoc);
                    throw new ComplexTypeRecoverableError((String) errArgs[errArgs.length - 1], errArgs, attrNode);
                }
            } catch (ComplexTypeRecoverableError e) {
                this.fAttrChecker.returnAttrArray(simpleContentAttrValues, schemaDoc);
                this.fAttrChecker.returnAttrArray(derivationTypeAttrValues, schemaDoc);
                throw e;
            }
        }
        this.fXSSimpleType = baseValidator;
        if (simpleContent != null) {
            attrNode = simpleContent;
            if (isAttrOrAttrGroup(attrNode)) {
                node = traverseAttrsAndAttrGrps(attrNode, this.fAttrGrp, schemaDoc, grammar, this.fComplexTypeDecl);
                if (node != null) {
                    this.fAttrChecker.returnAttrArray(simpleContentAttrValues, schemaDoc);
                    this.fAttrChecker.returnAttrArray(derivationTypeAttrValues, schemaDoc);
                    throw new ComplexTypeRecoverableError("s4s-elt-invalid-content.1", new Object[]{this.fName, DOMUtil.getLocalName(node)}, node);
                }
                this.fAttrGrp.removeProhibitedAttrs();
            } else {
                this.fAttrChecker.returnAttrArray(simpleContentAttrValues, schemaDoc);
                this.fAttrChecker.returnAttrArray(derivationTypeAttrValues, schemaDoc);
                throw new ComplexTypeRecoverableError("s4s-elt-invalid-content.1", new Object[]{this.fName, DOMUtil.getLocalName(attrNode)}, attrNode);
            }
        }
        if (baseComplexType != null) {
            try {
                mergeAttributes(baseComplexType.getAttrGrp(), this.fAttrGrp, this.fName, true, simpleContentElement);
            } catch (ComplexTypeRecoverableError e2) {
                this.fAttrChecker.returnAttrArray(simpleContentAttrValues, schemaDoc);
                this.fAttrChecker.returnAttrArray(derivationTypeAttrValues, schemaDoc);
                throw e2;
            }
        }
        this.fAttrChecker.returnAttrArray(simpleContentAttrValues, schemaDoc);
        this.fAttrChecker.returnAttrArray(derivationTypeAttrValues, schemaDoc);
    }

    private void traverseComplexContent(Element complexContentElement, boolean mixedOnType, XSDocumentInfo schemaDoc, SchemaGrammar grammar) throws ComplexTypeRecoverableError {
        String text;
        Element complexContent;
        Object[] complexContentAttrValues = this.fAttrChecker.checkAttributes(complexContentElement, false, schemaDoc);
        boolean mixedContent = mixedOnType;
        Boolean mixedAtt = complexContentAttrValues[XSAttributeChecker.ATTIDX_MIXED];
        if (mixedAtt != null) {
            mixedContent = mixedAtt.booleanValue();
        }
        this.fXSSimpleType = null;
        Element complexContent2 = DOMUtil.getFirstChildElement(complexContentElement);
        if (complexContent2 == null || !DOMUtil.getLocalName(complexContent2).equals(SchemaSymbols.ELT_ANNOTATION)) {
            text = DOMUtil.getSyntheticAnnotation(complexContentElement);
            if (text != null) {
                addAnnotation(traverseSyntheticAnnotation(complexContentElement, text, complexContentAttrValues, false, schemaDoc));
            }
            complexContent = complexContent2;
        } else {
            addAnnotation(traverseAnnotationDecl(complexContent2, complexContentAttrValues, false, schemaDoc));
            complexContent = DOMUtil.getNextSiblingElement(complexContent2);
        }
        if (complexContent == null) {
            this.fAttrChecker.returnAttrArray(complexContentAttrValues, schemaDoc);
            throw new ComplexTypeRecoverableError("s4s-elt-invalid-content.2", new Object[]{this.fName, SchemaSymbols.ELT_COMPLEXCONTENT}, complexContentElement);
        }
        String complexContentName = DOMUtil.getLocalName(complexContent);
        if (complexContentName.equals(SchemaSymbols.ELT_RESTRICTION)) {
            this.fDerivedBy = (short) 2;
        } else {
            if (complexContentName.equals(SchemaSymbols.ELT_EXTENSION)) {
                this.fDerivedBy = (short) 1;
            } else {
                this.fAttrChecker.returnAttrArray(complexContentAttrValues, schemaDoc);
                throw new ComplexTypeRecoverableError("s4s-elt-invalid-content.1", new Object[]{this.fName, complexContentName}, complexContent);
            }
        }
        Element elemTmp = DOMUtil.getNextSiblingElement(complexContent);
        if (elemTmp != null) {
            this.fAttrChecker.returnAttrArray(complexContentAttrValues, schemaDoc);
            String siblingName = DOMUtil.getLocalName(elemTmp);
            throw new ComplexTypeRecoverableError("s4s-elt-invalid-content.1", new Object[]{this.fName, siblingName}, elemTmp);
        }
        Object[] derivationTypeAttrValues = this.fAttrChecker.checkAttributes(complexContent, false, schemaDoc);
        QName baseTypeName = derivationTypeAttrValues[XSAttributeChecker.ATTIDX_BASE];
        if (baseTypeName == null) {
            this.fAttrChecker.returnAttrArray(complexContentAttrValues, schemaDoc);
            this.fAttrChecker.returnAttrArray(derivationTypeAttrValues, schemaDoc);
            throw new ComplexTypeRecoverableError("s4s-att-must-appear", new Object[]{complexContentName, "base"}, complexContent);
        }
        XSTypeDefinition type = (XSTypeDefinition) this.fSchemaHandler.getGlobalDecl(schemaDoc, 7, baseTypeName, complexContent);
        if (type == null) {
            this.fAttrChecker.returnAttrArray(complexContentAttrValues, schemaDoc);
            this.fAttrChecker.returnAttrArray(derivationTypeAttrValues, schemaDoc);
            throw new ComplexTypeRecoverableError();
        } else if (type instanceof XSComplexTypeDecl) {
            XSComplexTypeDecl baseType = (XSComplexTypeDecl) type;
            this.fBaseType = baseType;
            if ((baseType.getFinal() & this.fDerivedBy) != 0) {
                this.fAttrChecker.returnAttrArray(complexContentAttrValues, schemaDoc);
                this.fAttrChecker.returnAttrArray(derivationTypeAttrValues, schemaDoc);
                throw new ComplexTypeRecoverableError(this.fDerivedBy == (short) 1 ? "cos-ct-extends.1.1" : "derivation-ok-restriction.1", new Object[]{this.fName, this.fBaseType.getName()}, complexContent);
            }
            complexContent = DOMUtil.getFirstChildElement(complexContent);
            if (complexContent != null) {
                if (DOMUtil.getLocalName(complexContent).equals(SchemaSymbols.ELT_ANNOTATION)) {
                    addAnnotation(traverseAnnotationDecl(complexContent, derivationTypeAttrValues, false, schemaDoc));
                    complexContent = DOMUtil.getNextSiblingElement(complexContent);
                } else {
                    text = DOMUtil.getSyntheticAnnotation(complexContent);
                    if (text != null) {
                        addAnnotation(traverseSyntheticAnnotation(complexContent, text, derivationTypeAttrValues, false, schemaDoc));
                    }
                }
                if (complexContent != null && DOMUtil.getLocalName(complexContent).equals(SchemaSymbols.ELT_ANNOTATION)) {
                    this.fAttrChecker.returnAttrArray(complexContentAttrValues, schemaDoc);
                    this.fAttrChecker.returnAttrArray(derivationTypeAttrValues, schemaDoc);
                    throw new ComplexTypeRecoverableError("s4s-elt-invalid-content.1", new Object[]{this.fName, SchemaSymbols.ELT_ANNOTATION}, complexContent);
                }
            }
            text = DOMUtil.getSyntheticAnnotation(complexContent);
            if (text != null) {
                addAnnotation(traverseSyntheticAnnotation(complexContent, text, derivationTypeAttrValues, false, schemaDoc));
            }
            try {
                processComplexContent(complexContent, mixedContent, true, schemaDoc, grammar);
                XSParticleDecl baseContent = (XSParticleDecl) baseType.getParticle();
                if (this.fDerivedBy != (short) 2) {
                    if (this.fParticle == null) {
                        this.fContentType = baseType.getContentType();
                        this.fXSSimpleType = (XSSimpleType) baseType.getSimpleType();
                        this.fParticle = baseContent;
                    } else if (baseType.getContentType() != (short) 0) {
                        if (this.fContentType == (short) 2 && baseType.getContentType() != (short) 2) {
                            this.fAttrChecker.returnAttrArray(complexContentAttrValues, schemaDoc);
                            this.fAttrChecker.returnAttrArray(derivationTypeAttrValues, schemaDoc);
                            throw new ComplexTypeRecoverableError("cos-ct-extends.1.4.3.2.2.1.a", new Object[]{this.fName}, complexContent);
                        } else if (this.fContentType == (short) 3 && baseType.getContentType() != (short) 3) {
                            this.fAttrChecker.returnAttrArray(complexContentAttrValues, schemaDoc);
                            this.fAttrChecker.returnAttrArray(derivationTypeAttrValues, schemaDoc);
                            throw new ComplexTypeRecoverableError("cos-ct-extends.1.4.3.2.2.1.b", new Object[]{this.fName}, complexContent);
                        } else if ((this.fParticle.fType == (short) 3 && ((XSModelGroupImpl) this.fParticle.fValue).fCompositor == (short) 103) || (((XSParticleDecl) baseType.getParticle()).fType == (short) 3 && ((XSModelGroupImpl) ((XSParticleDecl) baseType.getParticle()).fValue).fCompositor == (short) 103)) {
                            this.fAttrChecker.returnAttrArray(complexContentAttrValues, schemaDoc);
                            this.fAttrChecker.returnAttrArray(derivationTypeAttrValues, schemaDoc);
                            throw new ComplexTypeRecoverableError("cos-all-limited.1.2", new Object[0], complexContent);
                        } else {
                            XSModelGroupImpl group = new XSModelGroupImpl();
                            group.fCompositor = (short) 102;
                            group.fParticleCount = 2;
                            group.fParticles = new XSParticleDecl[2];
                            group.fParticles[0] = (XSParticleDecl) baseType.getParticle();
                            group.fParticles[1] = this.fParticle;
                            group.fAnnotations = XSObjectListImpl.EMPTY_LIST;
                            XSParticleDecl particle = new XSParticleDecl();
                            particle.fType = (short) 3;
                            particle.fValue = group;
                            particle.fAnnotations = XSObjectListImpl.EMPTY_LIST;
                            this.fParticle = particle;
                        }
                    }
                    this.fAttrGrp.removeProhibitedAttrs();
                    try {
                        mergeAttributes(baseType.getAttrGrp(), this.fAttrGrp, this.fName, true, complexContent);
                    } catch (ComplexTypeRecoverableError e) {
                        this.fAttrChecker.returnAttrArray(complexContentAttrValues, schemaDoc);
                        this.fAttrChecker.returnAttrArray(derivationTypeAttrValues, schemaDoc);
                        throw e;
                    }
                } else if (this.fContentType != (short) 3 || baseType.getContentType() == (short) 3) {
                    try {
                        mergeAttributes(baseType.getAttrGrp(), this.fAttrGrp, this.fName, false, complexContent);
                        this.fAttrGrp.removeProhibitedAttrs();
                        if (baseType != SchemaGrammar.fAnyType) {
                            Object[] errArgs = this.fAttrGrp.validRestrictionOf(this.fName, baseType.getAttrGrp());
                            if (errArgs != null) {
                                this.fAttrChecker.returnAttrArray(complexContentAttrValues, schemaDoc);
                                this.fAttrChecker.returnAttrArray(derivationTypeAttrValues, schemaDoc);
                                throw new ComplexTypeRecoverableError((String) errArgs[errArgs.length - 1], errArgs, complexContent);
                            }
                        }
                    } catch (ComplexTypeRecoverableError e2) {
                        this.fAttrChecker.returnAttrArray(complexContentAttrValues, schemaDoc);
                        this.fAttrChecker.returnAttrArray(derivationTypeAttrValues, schemaDoc);
                        throw e2;
                    }
                } else {
                    this.fAttrChecker.returnAttrArray(complexContentAttrValues, schemaDoc);
                    this.fAttrChecker.returnAttrArray(derivationTypeAttrValues, schemaDoc);
                    throw new ComplexTypeRecoverableError("derivation-ok-restriction.5.4.1.2", new Object[]{this.fName, baseType.getName()}, complexContent);
                }
                this.fAttrChecker.returnAttrArray(complexContentAttrValues, schemaDoc);
                this.fAttrChecker.returnAttrArray(derivationTypeAttrValues, schemaDoc);
            } catch (ComplexTypeRecoverableError e22) {
                this.fAttrChecker.returnAttrArray(complexContentAttrValues, schemaDoc);
                this.fAttrChecker.returnAttrArray(derivationTypeAttrValues, schemaDoc);
                throw e22;
            }
        } else {
            this.fAttrChecker.returnAttrArray(complexContentAttrValues, schemaDoc);
            this.fAttrChecker.returnAttrArray(derivationTypeAttrValues, schemaDoc);
            throw new ComplexTypeRecoverableError("src-ct.1", new Object[]{this.fName, type.getName()}, complexContent);
        }
    }

    private void mergeAttributes(XSAttributeGroupDecl fromAttrGrp, XSAttributeGroupDecl toAttrGrp, String typeName, boolean extension, Element elem) throws ComplexTypeRecoverableError {
        XSObjectList attrUseS = fromAttrGrp.getAttributeUses();
        int attrCount = attrUseS.getLength();
        for (int i = 0; i < attrCount; i++) {
            XSAttributeUse oneAttrUse = (XSAttributeUseImpl) attrUseS.item(i);
            XSAttributeUse existingAttrUse = toAttrGrp.getAttributeUse(oneAttrUse.fAttrDecl.getNamespace(), oneAttrUse.fAttrDecl.getName());
            if (existingAttrUse == null) {
                if (toAttrGrp.addAttributeUse(oneAttrUse) != null) {
                    throw new ComplexTypeRecoverableError("ct-props-correct.5", new Object[]{typeName, toAttrGrp.addAttributeUse(oneAttrUse), oneAttrUse.fAttrDecl.getName()}, elem);
                }
            } else if (existingAttrUse != oneAttrUse && extension) {
                reportSchemaError("ct-props-correct.4", new Object[]{typeName, oneAttrUse.fAttrDecl.getName()}, elem);
                toAttrGrp.replaceAttributeUse(existingAttrUse, oneAttrUse);
            }
        }
        if (!extension) {
            return;
        }
        if (toAttrGrp.fAttributeWC == null) {
            toAttrGrp.fAttributeWC = fromAttrGrp.fAttributeWC;
        } else if (fromAttrGrp.fAttributeWC != null) {
            toAttrGrp.fAttributeWC = toAttrGrp.fAttributeWC.performUnionWith(fromAttrGrp.fAttributeWC, toAttrGrp.fAttributeWC.fProcessContents);
            if (toAttrGrp.fAttributeWC == null) {
                throw new ComplexTypeRecoverableError("src-ct.5", new Object[]{typeName}, elem);
            }
        }
    }

    private void processComplexContent(Element complexContentChild, boolean isMixed, boolean isDerivation, XSDocumentInfo schemaDoc, SchemaGrammar grammar) throws ComplexTypeRecoverableError {
        Element attrNode = null;
        XSParticleDecl particle = null;
        boolean emptyParticle = false;
        if (complexContentChild != null) {
            String childName = DOMUtil.getLocalName(complexContentChild);
            if (childName.equals(SchemaSymbols.ELT_GROUP)) {
                particle = this.fSchemaHandler.fGroupTraverser.traverseLocal(complexContentChild, schemaDoc, grammar);
                attrNode = DOMUtil.getNextSiblingElement(complexContentChild);
            } else if (childName.equals(SchemaSymbols.ELT_SEQUENCE)) {
                particle = traverseSequence(complexContentChild, schemaDoc, grammar, 0, this.fComplexTypeDecl);
                if (particle != null && particle.fValue.fParticleCount == 0) {
                    emptyParticle = true;
                }
                attrNode = DOMUtil.getNextSiblingElement(complexContentChild);
            } else if (childName.equals(SchemaSymbols.ELT_CHOICE)) {
                particle = traverseChoice(complexContentChild, schemaDoc, grammar, 0, this.fComplexTypeDecl);
                if (particle != null && particle.fMinOccurs == 0 && ((XSModelGroupImpl) particle.fValue).fParticleCount == 0) {
                    emptyParticle = true;
                }
                attrNode = DOMUtil.getNextSiblingElement(complexContentChild);
            } else if (childName.equals(SchemaSymbols.ELT_ALL)) {
                particle = traverseAll(complexContentChild, schemaDoc, grammar, 8, this.fComplexTypeDecl);
                if (particle != null && ((XSModelGroupImpl) particle.fValue).fParticleCount == 0) {
                    emptyParticle = true;
                }
                attrNode = DOMUtil.getNextSiblingElement(complexContentChild);
            } else {
                attrNode = complexContentChild;
            }
        }
        if (emptyParticle) {
            Element child = DOMUtil.getFirstChildElement(complexContentChild);
            if (child != null && DOMUtil.getLocalName(child).equals(SchemaSymbols.ELT_ANNOTATION)) {
                child = DOMUtil.getNextSiblingElement(child);
            }
            if (child == null) {
                particle = null;
            }
        }
        if (particle == null && isMixed) {
            particle = XSConstraints.getEmptySequence();
        }
        this.fParticle = particle;
        if (this.fParticle == null) {
            this.fContentType = (short) 0;
        } else if (isMixed) {
            this.fContentType = (short) 3;
        } else {
            this.fContentType = (short) 2;
        }
        if (attrNode == null) {
            return;
        }
        if (isAttrOrAttrGroup(attrNode)) {
            Element node = traverseAttrsAndAttrGrps(attrNode, this.fAttrGrp, schemaDoc, grammar, this.fComplexTypeDecl);
            if (node != null) {
                throw new ComplexTypeRecoverableError("s4s-elt-invalid-content.1", new Object[]{this.fName, DOMUtil.getLocalName(node)}, node);
            } else if (!isDerivation) {
                this.fAttrGrp.removeProhibitedAttrs();
                return;
            } else {
                return;
            }
        }
        throw new ComplexTypeRecoverableError("s4s-elt-invalid-content.1", new Object[]{this.fName, DOMUtil.getLocalName(attrNode)}, attrNode);
    }

    private boolean isAttrOrAttrGroup(Element e) {
        String elementName = DOMUtil.getLocalName(e);
        if (elementName.equals(SchemaSymbols.ELT_ATTRIBUTE) || elementName.equals(SchemaSymbols.ELT_ATTRIBUTEGROUP) || elementName.equals(SchemaSymbols.ELT_ANYATTRIBUTE)) {
            return true;
        }
        return false;
    }

    private void traverseSimpleContentDecl(Element simpleContentDecl) {
    }

    private void traverseComplexContentDecl(Element complexContentDecl, boolean mixedOnComplexTypeDecl) {
    }

    private String genAnonTypeName(Element complexTypeDecl) {
        StringBuffer typeName = new StringBuffer("#AnonType_");
        Element node = DOMUtil.getParent(complexTypeDecl);
        while (node != null && node != DOMUtil.getRoot(DOMUtil.getDocument(node))) {
            typeName.append(node.getAttribute(SchemaSymbols.ATT_NAME));
            node = DOMUtil.getParent(node);
        }
        return typeName.toString();
    }

    private void handleComplexTypeError(String messageId, Object[] args, Element e) {
        if (messageId != null) {
            reportSchemaError(messageId, args, e);
        }
        this.fBaseType = SchemaGrammar.fAnyType;
        this.fContentType = (short) 3;
        this.fXSSimpleType = null;
        this.fParticle = getErrorContent();
        this.fAttrGrp.fAttributeWC = getErrorWildcard();
    }

    private void contentBackup() {
        if (this.fGlobalStore == null) {
            this.fGlobalStore = new Object[11];
            this.fGlobalStorePos = 0;
        }
        if (this.fGlobalStorePos == this.fGlobalStore.length) {
            Object[] newArray = new Object[(this.fGlobalStorePos + 11)];
            System.arraycopy(this.fGlobalStore, 0, newArray, 0, this.fGlobalStorePos);
            this.fGlobalStore = newArray;
        }
        Object[] objArr = this.fGlobalStore;
        int i = this.fGlobalStorePos;
        this.fGlobalStorePos = i + 1;
        objArr[i] = this.fComplexTypeDecl;
        Object[] objArr2 = this.fGlobalStore;
        int i2 = this.fGlobalStorePos;
        this.fGlobalStorePos = i2 + 1;
        objArr2[i2] = this.fIsAbstract ? Boolean.TRUE : Boolean.FALSE;
        objArr = this.fGlobalStore;
        i = this.fGlobalStorePos;
        this.fGlobalStorePos = i + 1;
        objArr[i] = this.fName;
        objArr = this.fGlobalStore;
        i = this.fGlobalStorePos;
        this.fGlobalStorePos = i + 1;
        objArr[i] = this.fTargetNamespace;
        objArr = this.fGlobalStore;
        i = this.fGlobalStorePos;
        this.fGlobalStorePos = i + 1;
        objArr[i] = new Integer((this.fDerivedBy << 16) + this.fFinal);
        objArr = this.fGlobalStore;
        i = this.fGlobalStorePos;
        this.fGlobalStorePos = i + 1;
        objArr[i] = new Integer((this.fBlock << 16) + this.fContentType);
        objArr = this.fGlobalStore;
        i = this.fGlobalStorePos;
        this.fGlobalStorePos = i + 1;
        objArr[i] = this.fBaseType;
        objArr = this.fGlobalStore;
        i = this.fGlobalStorePos;
        this.fGlobalStorePos = i + 1;
        objArr[i] = this.fAttrGrp;
        objArr = this.fGlobalStore;
        i = this.fGlobalStorePos;
        this.fGlobalStorePos = i + 1;
        objArr[i] = this.fParticle;
        objArr = this.fGlobalStore;
        i = this.fGlobalStorePos;
        this.fGlobalStorePos = i + 1;
        objArr[i] = this.fXSSimpleType;
        objArr = this.fGlobalStore;
        i = this.fGlobalStorePos;
        this.fGlobalStorePos = i + 1;
        objArr[i] = this.fAnnotations;
    }

    private void contentRestore() {
        Object[] objArr = this.fGlobalStore;
        int i = this.fGlobalStorePos - 1;
        this.fGlobalStorePos = i;
        this.fAnnotations = (XSAnnotationImpl[]) objArr[i];
        objArr = this.fGlobalStore;
        i = this.fGlobalStorePos - 1;
        this.fGlobalStorePos = i;
        this.fXSSimpleType = (XSSimpleType) objArr[i];
        objArr = this.fGlobalStore;
        i = this.fGlobalStorePos - 1;
        this.fGlobalStorePos = i;
        this.fParticle = (XSParticleDecl) objArr[i];
        objArr = this.fGlobalStore;
        i = this.fGlobalStorePos - 1;
        this.fGlobalStorePos = i;
        this.fAttrGrp = (XSAttributeGroupDecl) objArr[i];
        objArr = this.fGlobalStore;
        i = this.fGlobalStorePos - 1;
        this.fGlobalStorePos = i;
        this.fBaseType = (XSTypeDefinition) objArr[i];
        objArr = this.fGlobalStore;
        i = this.fGlobalStorePos - 1;
        this.fGlobalStorePos = i;
        int i2 = ((Integer) objArr[i]).intValue();
        this.fBlock = (short) (i2 >> 16);
        this.fContentType = (short) i2;
        objArr = this.fGlobalStore;
        i = this.fGlobalStorePos - 1;
        this.fGlobalStorePos = i;
        i2 = ((Integer) objArr[i]).intValue();
        this.fDerivedBy = (short) (i2 >> 16);
        this.fFinal = (short) i2;
        objArr = this.fGlobalStore;
        i = this.fGlobalStorePos - 1;
        this.fGlobalStorePos = i;
        this.fTargetNamespace = (String) objArr[i];
        objArr = this.fGlobalStore;
        i = this.fGlobalStorePos - 1;
        this.fGlobalStorePos = i;
        this.fName = (String) objArr[i];
        objArr = this.fGlobalStore;
        i = this.fGlobalStorePos - 1;
        this.fGlobalStorePos = i;
        this.fIsAbstract = ((Boolean) objArr[i]).booleanValue();
        objArr = this.fGlobalStore;
        i = this.fGlobalStorePos - 1;
        this.fGlobalStorePos = i;
        this.fComplexTypeDecl = (XSComplexTypeDecl) objArr[i];
    }

    private void addAnnotation(XSAnnotationImpl annotation) {
        if (annotation != null) {
            if (this.fAnnotations == null) {
                this.fAnnotations = new XSAnnotationImpl[1];
            } else {
                XSAnnotationImpl[] tempArray = new XSAnnotationImpl[(this.fAnnotations.length + 1)];
                System.arraycopy(this.fAnnotations, 0, tempArray, 0, this.fAnnotations.length);
                this.fAnnotations = tempArray;
            }
            this.fAnnotations[this.fAnnotations.length - 1] = annotation;
        }
    }
}
