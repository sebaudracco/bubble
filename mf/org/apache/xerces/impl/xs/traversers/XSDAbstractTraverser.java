package mf.org.apache.xerces.impl.xs.traversers;

import com.google.firebase.analytics.FirebaseAnalytics$Param;
import java.util.Locale;
import java.util.Vector;
import mf.org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import mf.org.apache.xerces.impl.dv.XSFacets;
import mf.org.apache.xerces.impl.dv.XSSimpleType;
import mf.org.apache.xerces.impl.validation.ValidationState;
import mf.org.apache.xerces.impl.xs.SchemaGrammar;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import mf.org.apache.xerces.impl.xs.XSAnnotationImpl;
import mf.org.apache.xerces.impl.xs.XSAttributeGroupDecl;
import mf.org.apache.xerces.impl.xs.XSAttributeUseImpl;
import mf.org.apache.xerces.impl.xs.XSComplexTypeDecl;
import mf.org.apache.xerces.impl.xs.XSElementDecl;
import mf.org.apache.xerces.impl.xs.XSParticleDecl;
import mf.org.apache.xerces.impl.xs.XSWildcardDecl;
import mf.org.apache.xerces.impl.xs.util.XInt;
import mf.org.apache.xerces.impl.xs.util.XSObjectListImpl;
import mf.org.apache.xerces.util.DOMUtil;
import mf.org.apache.xerces.util.NamespaceSupport;
import mf.org.apache.xerces.util.SymbolTable;
import mf.org.apache.xerces.xni.QName;
import mf.org.apache.xerces.xs.XSAttributeUse;
import mf.org.apache.xerces.xs.XSObjectList;
import mf.org.apache.xerces.xs.XSTypeDefinition;
import mf.org.w3c.dom.Element;

abstract class XSDAbstractTraverser {
    protected static final int CHILD_OF_GROUP = 4;
    protected static final int GROUP_REF_WITH_ALL = 2;
    protected static final int NOT_ALL_CONTEXT = 0;
    protected static final String NO_NAME = "(no name)";
    protected static final int PROCESSING_ALL_EL = 1;
    protected static final int PROCESSING_ALL_GP = 8;
    private static final XSSimpleType fQNameDV = ((XSSimpleType) SchemaGrammar.SG_SchemaNS.getGlobalTypeDecl(SchemaSymbols.ATTVAL_QNAME));
    protected XSAttributeChecker fAttrChecker = null;
    private StringBuffer fPattern = new StringBuffer();
    protected XSDHandler fSchemaHandler = null;
    protected SymbolTable fSymbolTable = null;
    protected boolean fValidateAnnotations = false;
    ValidationState fValidationState = new ValidationState();
    private final XSFacets xsFacets = new XSFacets();

    static final class FacetInfo {
        final short fFixedFacets;
        final short fPresentFacets;
        final XSFacets facetdata;
        final Element nodeAfterFacets;

        FacetInfo(XSFacets facets, Element nodeAfterFacets, short presentFacets, short fixedFacets) {
            this.facetdata = facets;
            this.nodeAfterFacets = nodeAfterFacets;
            this.fPresentFacets = presentFacets;
            this.fFixedFacets = fixedFacets;
        }
    }

    XSDAbstractTraverser(XSDHandler handler, XSAttributeChecker attrChecker) {
        this.fSchemaHandler = handler;
        this.fAttrChecker = attrChecker;
    }

    void reset(SymbolTable symbolTable, boolean validateAnnotations, Locale locale) {
        this.fSymbolTable = symbolTable;
        this.fValidateAnnotations = validateAnnotations;
        this.fValidationState.setExtraChecking(false);
        this.fValidationState.setSymbolTable(symbolTable);
        this.fValidationState.setLocale(locale);
    }

    XSAnnotationImpl traverseAnnotationDecl(Element annotationDecl, Object[] parentAttrs, boolean isGlobal, XSDocumentInfo schemaDoc) {
        this.fAttrChecker.returnAttrArray(this.fAttrChecker.checkAttributes(annotationDecl, isGlobal, schemaDoc), schemaDoc);
        String contents = DOMUtil.getAnnotation(annotationDecl);
        Element child = DOMUtil.getFirstChildElement(annotationDecl);
        if (child != null) {
            do {
                String name = DOMUtil.getLocalName(child);
                if (name.equals(SchemaSymbols.ELT_APPINFO) || name.equals(SchemaSymbols.ELT_DOCUMENTATION)) {
                    this.fAttrChecker.returnAttrArray(this.fAttrChecker.checkAttributes(child, true, schemaDoc), schemaDoc);
                } else {
                    reportSchemaError("src-annotation", new Object[]{name}, child);
                }
                child = DOMUtil.getNextSiblingElement(child);
            } while (child != null);
        }
        if (contents == null) {
            return null;
        }
        SchemaGrammar grammar = this.fSchemaHandler.getGrammar(schemaDoc.fTargetNamespace);
        Vector annotationLocalAttrs = parentAttrs[XSAttributeChecker.ATTIDX_NONSCHEMA];
        if (annotationLocalAttrs == null || annotationLocalAttrs.isEmpty()) {
            if (this.fValidateAnnotations) {
                schemaDoc.addAnnotation(new XSAnnotationInfo(contents, annotationDecl));
            }
            return new XSAnnotationImpl(contents, grammar);
        }
        StringBuffer localStrBuffer = new StringBuffer(64);
        localStrBuffer.append(" ");
        int i = 0;
        while (i < annotationLocalAttrs.size()) {
            String prefix;
            String localpart;
            int i2 = i + 1;
            String rawname = (String) annotationLocalAttrs.elementAt(i);
            int colonIndex = rawname.indexOf(58);
            if (colonIndex == -1) {
                prefix = "";
                localpart = rawname;
            } else {
                prefix = rawname.substring(0, colonIndex);
                localpart = rawname.substring(colonIndex + 1);
            }
            if (annotationDecl.getAttributeNS(schemaDoc.fNamespaceSupport.getURI(this.fSymbolTable.addSymbol(prefix)), localpart).length() != 0) {
                i = i2 + 1;
            } else {
                localStrBuffer.append(rawname).append("=\"");
                i = i2 + 1;
                localStrBuffer.append(processAttValue((String) annotationLocalAttrs.elementAt(i2))).append("\" ");
            }
        }
        StringBuffer contentBuffer = new StringBuffer(contents.length() + localStrBuffer.length());
        int annotationTokenEnd = contents.indexOf(SchemaSymbols.ELT_ANNOTATION);
        if (annotationTokenEnd == -1) {
            return null;
        }
        annotationTokenEnd += SchemaSymbols.ELT_ANNOTATION.length();
        contentBuffer.append(contents.substring(0, annotationTokenEnd));
        contentBuffer.append(localStrBuffer.toString());
        contentBuffer.append(contents.substring(annotationTokenEnd, contents.length()));
        String annotation = contentBuffer.toString();
        if (this.fValidateAnnotations) {
            schemaDoc.addAnnotation(new XSAnnotationInfo(annotation, annotationDecl));
        }
        return new XSAnnotationImpl(annotation, grammar);
    }

    XSAnnotationImpl traverseSyntheticAnnotation(Element annotationParent, String initialContent, Object[] parentAttrs, boolean isGlobal, XSDocumentInfo schemaDoc) {
        String contents = initialContent;
        SchemaGrammar grammar = this.fSchemaHandler.getGrammar(schemaDoc.fTargetNamespace);
        Vector annotationLocalAttrs = parentAttrs[XSAttributeChecker.ATTIDX_NONSCHEMA];
        if (annotationLocalAttrs == null || annotationLocalAttrs.isEmpty()) {
            if (this.fValidateAnnotations) {
                schemaDoc.addAnnotation(new XSAnnotationInfo(contents, annotationParent));
            }
            return new XSAnnotationImpl(contents, grammar);
        }
        StringBuffer localStrBuffer = new StringBuffer(64);
        localStrBuffer.append(" ");
        int i = 0;
        while (i < annotationLocalAttrs.size()) {
            String prefix;
            int i2 = i + 1;
            String rawname = (String) annotationLocalAttrs.elementAt(i);
            int colonIndex = rawname.indexOf(58);
            String localpart;
            if (colonIndex == -1) {
                prefix = "";
                localpart = rawname;
            } else {
                prefix = rawname.substring(0, colonIndex);
                localpart = rawname.substring(colonIndex + 1);
            }
            String uri = schemaDoc.fNamespaceSupport.getURI(this.fSymbolTable.addSymbol(prefix));
            localStrBuffer.append(rawname).append("=\"");
            i = i2 + 1;
            localStrBuffer.append(processAttValue((String) annotationLocalAttrs.elementAt(i2))).append("\" ");
        }
        StringBuffer contentBuffer = new StringBuffer(contents.length() + localStrBuffer.length());
        int annotationTokenEnd = contents.indexOf(SchemaSymbols.ELT_ANNOTATION);
        if (annotationTokenEnd == -1) {
            return null;
        }
        annotationTokenEnd += SchemaSymbols.ELT_ANNOTATION.length();
        contentBuffer.append(contents.substring(0, annotationTokenEnd));
        contentBuffer.append(localStrBuffer.toString());
        contentBuffer.append(contents.substring(annotationTokenEnd, contents.length()));
        String annotation = contentBuffer.toString();
        if (this.fValidateAnnotations) {
            schemaDoc.addAnnotation(new XSAnnotationInfo(annotation, annotationParent));
        }
        return new XSAnnotationImpl(annotation, grammar);
    }

    FacetInfo traverseFacets(Element content, XSSimpleType baseValidator, XSDocumentInfo schemaDoc) {
        short facetsPresent = (short) 0;
        short facetsFixed = (short) 0;
        boolean hasQName = containsQName(baseValidator);
        Vector enumData = null;
        XSObjectList enumAnnotations = null;
        XSObjectListImpl patternAnnotations = null;
        Vector enumNSDecls = hasQName ? new Vector() : null;
        this.xsFacets.reset();
        while (content != null) {
            Object[] attrs;
            String facet = DOMUtil.getLocalName(content);
            Element child;
            String text;
            if (facet.equals(SchemaSymbols.ELT_ENUMERATION)) {
                attrs = this.fAttrChecker.checkAttributes(content, false, schemaDoc, hasQName);
                String enumVal = attrs[XSAttributeChecker.ATTIDX_VALUE];
                if (enumVal == null) {
                    reportSchemaError("s4s-att-must-appear", new Object[]{SchemaSymbols.ELT_ENUMERATION, SchemaSymbols.ATT_VALUE}, content);
                    this.fAttrChecker.returnAttrArray(attrs, schemaDoc);
                    content = DOMUtil.getNextSiblingElement(content);
                } else {
                    NamespaceSupport nsDecls = attrs[XSAttributeChecker.ATTIDX_ENUMNSDECLS];
                    if (baseValidator.getVariety() == (short) 1 && baseValidator.getPrimitiveKind() == (short) 20) {
                        schemaDoc.fValidationContext.setNamespaceSupport(nsDecls);
                        Object notation = null;
                        try {
                            notation = this.fSchemaHandler.getGlobalDecl(schemaDoc, 6, (QName) fQNameDV.validate(enumVal, schemaDoc.fValidationContext, null), content);
                        } catch (InvalidDatatypeValueException ex) {
                            reportSchemaError(ex.getKey(), ex.getArgs(), content);
                        }
                        if (notation == null) {
                            this.fAttrChecker.returnAttrArray(attrs, schemaDoc);
                            content = DOMUtil.getNextSiblingElement(content);
                        } else {
                            schemaDoc.fValidationContext.setNamespaceSupport(schemaDoc.fNamespaceSupport);
                        }
                    }
                    if (enumData == null) {
                        enumData = new Vector();
                        enumAnnotations = new XSObjectListImpl();
                    }
                    enumData.addElement(enumVal);
                    enumAnnotations.addXSObject(null);
                    if (hasQName) {
                        enumNSDecls.addElement(nsDecls);
                    }
                    child = DOMUtil.getFirstChildElement(content);
                    if (child == null || !DOMUtil.getLocalName(child).equals(SchemaSymbols.ELT_ANNOTATION)) {
                        text = DOMUtil.getSyntheticAnnotation(content);
                        if (text != null) {
                            enumAnnotations.addXSObject(enumAnnotations.getLength() - 1, traverseSyntheticAnnotation(content, text, attrs, false, schemaDoc));
                        }
                    } else {
                        enumAnnotations.addXSObject(enumAnnotations.getLength() - 1, traverseAnnotationDecl(child, attrs, false, schemaDoc));
                        child = DOMUtil.getNextSiblingElement(child);
                    }
                    if (child != null) {
                        reportSchemaError("s4s-elt-must-match.1", new Object[]{"enumeration", "(annotation?)", DOMUtil.getLocalName(child)}, child);
                    }
                }
            } else {
                if (facet.equals(SchemaSymbols.ELT_PATTERN)) {
                    attrs = this.fAttrChecker.checkAttributes(content, false, schemaDoc);
                    String patternVal = attrs[XSAttributeChecker.ATTIDX_VALUE];
                    if (patternVal == null) {
                        reportSchemaError("s4s-att-must-appear", new Object[]{SchemaSymbols.ELT_PATTERN, SchemaSymbols.ATT_VALUE}, content);
                        this.fAttrChecker.returnAttrArray(attrs, schemaDoc);
                        content = DOMUtil.getNextSiblingElement(content);
                    } else {
                        if (this.fPattern.length() == 0) {
                            this.fPattern.append(patternVal);
                        } else {
                            this.fPattern.append("|");
                            this.fPattern.append(patternVal);
                        }
                        child = DOMUtil.getFirstChildElement(content);
                        if (child == null || !DOMUtil.getLocalName(child).equals(SchemaSymbols.ELT_ANNOTATION)) {
                            text = DOMUtil.getSyntheticAnnotation(content);
                            if (text != null) {
                                if (patternAnnotations == null) {
                                    patternAnnotations = new XSObjectListImpl();
                                }
                                patternAnnotations.addXSObject(traverseSyntheticAnnotation(content, text, attrs, false, schemaDoc));
                            }
                        } else {
                            if (patternAnnotations == null) {
                                patternAnnotations = new XSObjectListImpl();
                            }
                            patternAnnotations.addXSObject(traverseAnnotationDecl(child, attrs, false, schemaDoc));
                            child = DOMUtil.getNextSiblingElement(child);
                        }
                        if (child != null) {
                            reportSchemaError("s4s-elt-must-match.1", new Object[]{"pattern", "(annotation?)", DOMUtil.getLocalName(child)}, child);
                        }
                    }
                } else {
                    int currentFacet;
                    if (facet.equals(SchemaSymbols.ELT_MINLENGTH)) {
                        currentFacet = 2;
                    } else {
                        if (facet.equals(SchemaSymbols.ELT_MAXLENGTH)) {
                            currentFacet = 4;
                        } else {
                            if (facet.equals(SchemaSymbols.ELT_MAXEXCLUSIVE)) {
                                currentFacet = 64;
                            } else {
                                if (facet.equals(SchemaSymbols.ELT_MAXINCLUSIVE)) {
                                    currentFacet = 32;
                                } else {
                                    if (facet.equals(SchemaSymbols.ELT_MINEXCLUSIVE)) {
                                        currentFacet = 128;
                                    } else {
                                        if (facet.equals(SchemaSymbols.ELT_MININCLUSIVE)) {
                                            currentFacet = 256;
                                        } else {
                                            if (facet.equals(SchemaSymbols.ELT_TOTALDIGITS)) {
                                                currentFacet = 512;
                                            } else {
                                                if (facet.equals(SchemaSymbols.ELT_FRACTIONDIGITS)) {
                                                    currentFacet = 1024;
                                                } else {
                                                    if (facet.equals(SchemaSymbols.ELT_WHITESPACE)) {
                                                        currentFacet = 16;
                                                    } else {
                                                        if (facet.equals(SchemaSymbols.ELT_LENGTH)) {
                                                            currentFacet = 1;
                                                        } else {
                                                            if (enumData != null) {
                                                                facetsPresent = (short) (facetsPresent | 2048);
                                                                this.xsFacets.enumeration = enumData;
                                                                this.xsFacets.enumNSDecls = enumNSDecls;
                                                                this.xsFacets.enumAnnotations = enumAnnotations;
                                                            }
                                                            if (this.fPattern.length() != 0) {
                                                                facetsPresent = (short) (facetsPresent | 8);
                                                                this.xsFacets.pattern = this.fPattern.toString();
                                                                this.xsFacets.patternAnnotations = patternAnnotations;
                                                            }
                                                            this.fPattern.setLength(0);
                                                            return new FacetInfo(this.xsFacets, content, facetsPresent, facetsFixed);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    attrs = this.fAttrChecker.checkAttributes(content, false, schemaDoc);
                    if ((facetsPresent & currentFacet) != 0) {
                        reportSchemaError("src-single-facet-value", new Object[]{facet}, content);
                        this.fAttrChecker.returnAttrArray(attrs, schemaDoc);
                        content = DOMUtil.getNextSiblingElement(content);
                    } else if (attrs[XSAttributeChecker.ATTIDX_VALUE] == null) {
                        if (content.getAttributeNodeNS(null, FirebaseAnalytics$Param.VALUE) == null) {
                            reportSchemaError("s4s-att-must-appear", new Object[]{content.getLocalName(), SchemaSymbols.ATT_VALUE}, content);
                        }
                        this.fAttrChecker.returnAttrArray(attrs, schemaDoc);
                        content = DOMUtil.getNextSiblingElement(content);
                    } else {
                        facetsPresent = (short) (facetsPresent | currentFacet);
                        if (((Boolean) attrs[XSAttributeChecker.ATTIDX_FIXED]).booleanValue()) {
                            facetsFixed = (short) (facetsFixed | currentFacet);
                        }
                        switch (currentFacet) {
                            case 1:
                                this.xsFacets.length = ((XInt) attrs[XSAttributeChecker.ATTIDX_VALUE]).intValue();
                                break;
                            case 2:
                                this.xsFacets.minLength = ((XInt) attrs[XSAttributeChecker.ATTIDX_VALUE]).intValue();
                                break;
                            case 4:
                                this.xsFacets.maxLength = ((XInt) attrs[XSAttributeChecker.ATTIDX_VALUE]).intValue();
                                break;
                            case 16:
                                this.xsFacets.whiteSpace = ((XInt) attrs[XSAttributeChecker.ATTIDX_VALUE]).shortValue();
                                break;
                            case 32:
                                this.xsFacets.maxInclusive = (String) attrs[XSAttributeChecker.ATTIDX_VALUE];
                                break;
                            case 64:
                                this.xsFacets.maxExclusive = (String) attrs[XSAttributeChecker.ATTIDX_VALUE];
                                break;
                            case 128:
                                this.xsFacets.minExclusive = (String) attrs[XSAttributeChecker.ATTIDX_VALUE];
                                break;
                            case 256:
                                this.xsFacets.minInclusive = (String) attrs[XSAttributeChecker.ATTIDX_VALUE];
                                break;
                            case 512:
                                this.xsFacets.totalDigits = ((XInt) attrs[XSAttributeChecker.ATTIDX_VALUE]).intValue();
                                break;
                            case 1024:
                                this.xsFacets.fractionDigits = ((XInt) attrs[XSAttributeChecker.ATTIDX_VALUE]).intValue();
                                break;
                        }
                        child = DOMUtil.getFirstChildElement(content);
                        XSAnnotationImpl annotation = null;
                        if (child == null || !DOMUtil.getLocalName(child).equals(SchemaSymbols.ELT_ANNOTATION)) {
                            text = DOMUtil.getSyntheticAnnotation(content);
                            if (text != null) {
                                annotation = traverseSyntheticAnnotation(content, text, attrs, false, schemaDoc);
                            }
                        } else {
                            annotation = traverseAnnotationDecl(child, attrs, false, schemaDoc);
                            child = DOMUtil.getNextSiblingElement(child);
                        }
                        switch (currentFacet) {
                            case 1:
                                this.xsFacets.lengthAnnotation = annotation;
                                break;
                            case 2:
                                this.xsFacets.minLengthAnnotation = annotation;
                                break;
                            case 4:
                                this.xsFacets.maxLengthAnnotation = annotation;
                                break;
                            case 16:
                                this.xsFacets.whiteSpaceAnnotation = annotation;
                                break;
                            case 32:
                                this.xsFacets.maxInclusiveAnnotation = annotation;
                                break;
                            case 64:
                                this.xsFacets.maxExclusiveAnnotation = annotation;
                                break;
                            case 128:
                                this.xsFacets.minExclusiveAnnotation = annotation;
                                break;
                            case 256:
                                this.xsFacets.minInclusiveAnnotation = annotation;
                                break;
                            case 512:
                                this.xsFacets.totalDigitsAnnotation = annotation;
                                break;
                            case 1024:
                                this.xsFacets.fractionDigitsAnnotation = annotation;
                                break;
                        }
                        if (child != null) {
                            reportSchemaError("s4s-elt-must-match.1", new Object[]{facet, "(annotation?)", DOMUtil.getLocalName(child)}, child);
                        }
                    }
                }
            }
            this.fAttrChecker.returnAttrArray(attrs, schemaDoc);
            content = DOMUtil.getNextSiblingElement(content);
        }
        if (enumData != null) {
            facetsPresent = (short) (facetsPresent | 2048);
            this.xsFacets.enumeration = enumData;
            this.xsFacets.enumNSDecls = enumNSDecls;
            this.xsFacets.enumAnnotations = enumAnnotations;
        }
        if (this.fPattern.length() != 0) {
            facetsPresent = (short) (facetsPresent | 8);
            this.xsFacets.pattern = this.fPattern.toString();
            this.xsFacets.patternAnnotations = patternAnnotations;
        }
        this.fPattern.setLength(0);
        return new FacetInfo(this.xsFacets, content, facetsPresent, facetsFixed);
    }

    private boolean containsQName(XSSimpleType type) {
        if (type.getVariety() == (short) 1) {
            short primitive = type.getPrimitiveKind();
            return primitive == (short) 18 || primitive == (short) 20;
        } else if (type.getVariety() == (short) 2) {
            return containsQName((XSSimpleType) type.getItemType());
        } else {
            if (type.getVariety() == (short) 3) {
                XSObjectList members = type.getMemberTypes();
                for (int i = 0; i < members.getLength(); i++) {
                    if (containsQName((XSSimpleType) members.item(i))) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    Element traverseAttrsAndAttrGrps(Element firstAttr, XSAttributeGroupDecl attrGrp, XSDocumentInfo schemaDoc, SchemaGrammar grammar, XSComplexTypeDecl enclosingCT) {
        String code;
        String name;
        Element child = firstAttr;
        while (child != null) {
            String childName = DOMUtil.getLocalName(child);
            if (!childName.equals(SchemaSymbols.ELT_ATTRIBUTE)) {
                if (!childName.equals(SchemaSymbols.ELT_ATTRIBUTEGROUP)) {
                    break;
                }
                XSAttributeGroupDecl tempAttrGrp = this.fSchemaHandler.fAttributeGroupTraverser.traverseLocal(child, schemaDoc, grammar);
                if (tempAttrGrp != null) {
                    XSObjectList attrUseS = tempAttrGrp.getAttributeUses();
                    int attrCount = attrUseS.getLength();
                    for (int i = 0; i < attrCount; i++) {
                        XSAttributeUseImpl oneAttrUse = (XSAttributeUseImpl) attrUseS.item(i);
                        if (oneAttrUse.fUse == (short) 2) {
                            attrGrp.addAttributeUse(oneAttrUse);
                        } else {
                            Object otherUse = attrGrp.getAttributeUseNoProhibited(oneAttrUse.fAttrDecl.getNamespace(), oneAttrUse.fAttrDecl.getName());
                            if (otherUse == null) {
                                if (attrGrp.addAttributeUse(oneAttrUse) != null) {
                                    code = enclosingCT == null ? "ag-props-correct.3" : "ct-props-correct.5";
                                    name = enclosingCT == null ? attrGrp.fName : enclosingCT.getName();
                                    reportSchemaError(code, new Object[]{name, oneAttrUse.fAttrDecl.getName(), idName}, child);
                                }
                            } else if (oneAttrUse != otherUse) {
                                code = enclosingCT == null ? "ag-props-correct.2" : "ct-props-correct.4";
                                name = enclosingCT == null ? attrGrp.fName : enclosingCT.getName();
                                reportSchemaError(code, new Object[]{name, oneAttrUse.fAttrDecl.getName()}, child);
                            }
                        }
                    }
                    if (tempAttrGrp.fAttributeWC != null) {
                        if (attrGrp.fAttributeWC == null) {
                            attrGrp.fAttributeWC = tempAttrGrp.fAttributeWC;
                        } else {
                            attrGrp.fAttributeWC = attrGrp.fAttributeWC.performIntersectionWith(tempAttrGrp.fAttributeWC, attrGrp.fAttributeWC.fProcessContents);
                            if (attrGrp.fAttributeWC == null) {
                                code = enclosingCT == null ? "src-attribute_group.2" : "src-ct.4";
                                name = enclosingCT == null ? attrGrp.fName : enclosingCT.getName();
                                reportSchemaError(code, new Object[]{name}, child);
                            }
                        }
                    }
                }
            } else {
                XSAttributeUse tempAttrUse = this.fSchemaHandler.fAttributeTraverser.traverseLocal(child, schemaDoc, grammar, enclosingCT);
                if (tempAttrUse != null) {
                    if (tempAttrUse.fUse == (short) 2) {
                        attrGrp.addAttributeUse(tempAttrUse);
                    } else {
                        XSAttributeUse otherUse2 = attrGrp.getAttributeUseNoProhibited(tempAttrUse.fAttrDecl.getNamespace(), tempAttrUse.fAttrDecl.getName());
                        if (otherUse2 == null) {
                            if (attrGrp.addAttributeUse(tempAttrUse) != null) {
                                code = enclosingCT == null ? "ag-props-correct.3" : "ct-props-correct.5";
                                name = enclosingCT == null ? attrGrp.fName : enclosingCT.getName();
                                reportSchemaError(code, new Object[]{name, tempAttrUse.fAttrDecl.getName(), idName}, child);
                            }
                        } else if (otherUse2 != tempAttrUse) {
                            code = enclosingCT == null ? "ag-props-correct.2" : "ct-props-correct.4";
                            name = enclosingCT == null ? attrGrp.fName : enclosingCT.getName();
                            reportSchemaError(code, new Object[]{name, tempAttrUse.fAttrDecl.getName()}, child);
                        }
                    }
                }
            }
            child = DOMUtil.getNextSiblingElement(child);
        }
        if (child == null || !DOMUtil.getLocalName(child).equals(SchemaSymbols.ELT_ANYATTRIBUTE)) {
            return child;
        }
        XSWildcardDecl tempAttrWC = this.fSchemaHandler.fWildCardTraverser.traverseAnyAttribute(child, schemaDoc, grammar);
        if (attrGrp.fAttributeWC == null) {
            attrGrp.fAttributeWC = tempAttrWC;
        } else {
            attrGrp.fAttributeWC = tempAttrWC.performIntersectionWith(attrGrp.fAttributeWC, tempAttrWC.fProcessContents);
            if (attrGrp.fAttributeWC == null) {
                code = enclosingCT == null ? "src-attribute_group.2" : "src-ct.4";
                name = enclosingCT == null ? attrGrp.fName : enclosingCT.getName();
                reportSchemaError(code, new Object[]{name}, child);
            }
        }
        return DOMUtil.getNextSiblingElement(child);
    }

    void reportSchemaError(String key, Object[] args, Element ele) {
        this.fSchemaHandler.reportSchemaError(key, args, ele);
    }

    void checkNotationType(String refName, XSTypeDefinition typeDecl, Element elem) {
        if (typeDecl.getTypeCategory() == (short) 16 && ((XSSimpleType) typeDecl).getVariety() == (short) 1 && ((XSSimpleType) typeDecl).getPrimitiveKind() == (short) 20 && (((XSSimpleType) typeDecl).getDefinedFacets() & 2048) == 0) {
            reportSchemaError("enumeration-required-notation", new Object[]{typeDecl.getName(), refName, DOMUtil.getLocalName(elem)}, elem);
        }
    }

    protected XSParticleDecl checkOccurrences(XSParticleDecl particle, String particleName, Element parent, int allContextFlags, long defaultVals) {
        int min = particle.fMinOccurs;
        int max = particle.fMaxOccurs;
        boolean defaultMin = (((long) (1 << XSAttributeChecker.ATTIDX_MINOCCURS)) & defaultVals) != 0;
        boolean defaultMax = (((long) (1 << XSAttributeChecker.ATTIDX_MAXOCCURS)) & defaultVals) != 0;
        boolean processingAllEl = (allContextFlags & 1) != 0;
        boolean processingAllGP = (allContextFlags & 8) != 0;
        boolean groupRefWithAll = (allContextFlags & 2) != 0;
        if ((allContextFlags & 4) != 0) {
            if (!defaultMin) {
                reportSchemaError("s4s-att-not-allowed", new Object[]{particleName, "minOccurs"}, parent);
                min = 1;
            }
            if (!defaultMax) {
                reportSchemaError("s4s-att-not-allowed", new Object[]{particleName, "maxOccurs"}, parent);
                max = 1;
            }
        }
        if (min == 0 && max == 0) {
            particle.fType = (short) 0;
            return null;
        }
        if (processingAllEl) {
            if (max != 1) {
                String str = "cos-all-limited.2";
                Object[] objArr = new Object[2];
                objArr[0] = max == -1 ? SchemaSymbols.ATTVAL_UNBOUNDED : Integer.toString(max);
                objArr[1] = ((XSElementDecl) particle.fValue).getName();
                reportSchemaError(str, objArr, parent);
                max = 1;
                if (min > 1) {
                    min = 1;
                }
            }
        } else if ((processingAllGP || groupRefWithAll) && max != 1) {
            reportSchemaError("cos-all-limited.1.2", null, parent);
            if (min > 1) {
                min = 1;
            }
            max = 1;
        }
        particle.fMinOccurs = min;
        particle.fMaxOccurs = max;
        return particle;
    }

    private static String processAttValue(String original) {
        int length = original.length();
        for (int i = 0; i < length; i++) {
            char currChar = original.charAt(i);
            if (currChar == '\"' || currChar == '<' || currChar == '&' || currChar == '\t' || currChar == '\n' || currChar == '\r') {
                return escapeAttValue(original, i);
            }
        }
        return original;
    }

    private static String escapeAttValue(String original, int from) {
        int length = original.length();
        StringBuffer newVal = new StringBuffer(length);
        newVal.append(original.substring(0, from));
        for (int i = from; i < length; i++) {
            char currChar = original.charAt(i);
            if (currChar == '\"') {
                newVal.append("&quot;");
            } else if (currChar == '<') {
                newVal.append("&lt;");
            } else if (currChar == '&') {
                newVal.append("&amp;");
            } else if (currChar == '\t') {
                newVal.append("&#x9;");
            } else if (currChar == '\n') {
                newVal.append("&#xA;");
            } else if (currChar == '\r') {
                newVal.append("&#xD;");
            } else {
                newVal.append(currChar);
            }
        }
        return newVal.toString();
    }
}
