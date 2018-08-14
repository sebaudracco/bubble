package mf.org.apache.xerces.impl.xs;

import java.lang.ref.SoftReference;
import java.util.Vector;
import mf.org.apache.xerces.impl.dv.SchemaDVFactory;
import mf.org.apache.xerces.impl.dv.ValidatedInfo;
import mf.org.apache.xerces.impl.dv.XSSimpleType;
import mf.org.apache.xerces.impl.dv.xs.XSSimpleTypeDecl;
import mf.org.apache.xerces.impl.xs.identity.IdentityConstraint;
import mf.org.apache.xerces.impl.xs.util.ObjectListImpl;
import mf.org.apache.xerces.impl.xs.util.SimpleLocator;
import mf.org.apache.xerces.impl.xs.util.StringListImpl;
import mf.org.apache.xerces.impl.xs.util.XSNamedMap4Types;
import mf.org.apache.xerces.impl.xs.util.XSNamedMapImpl;
import mf.org.apache.xerces.impl.xs.util.XSObjectListImpl;
import mf.org.apache.xerces.parsers.DOMParser;
import mf.org.apache.xerces.parsers.SAXParser;
import mf.org.apache.xerces.util.SymbolHash;
import mf.org.apache.xerces.util.SymbolTable;
import mf.org.apache.xerces.xni.NamespaceContext;
import mf.org.apache.xerces.xni.grammars.XMLGrammarDescription;
import mf.org.apache.xerces.xni.grammars.XSGrammar;
import mf.org.apache.xerces.xs.StringList;
import mf.org.apache.xerces.xs.XSAnnotation;
import mf.org.apache.xerces.xs.XSAttributeDeclaration;
import mf.org.apache.xerces.xs.XSAttributeGroupDefinition;
import mf.org.apache.xerces.xs.XSElementDeclaration;
import mf.org.apache.xerces.xs.XSIDCDefinition;
import mf.org.apache.xerces.xs.XSModel;
import mf.org.apache.xerces.xs.XSModelGroupDefinition;
import mf.org.apache.xerces.xs.XSNamedMap;
import mf.org.apache.xerces.xs.XSNamespaceItem;
import mf.org.apache.xerces.xs.XSNotationDeclaration;
import mf.org.apache.xerces.xs.XSObjectList;
import mf.org.apache.xerces.xs.XSParticle;
import mf.org.apache.xerces.xs.XSTypeDefinition;
import mf.org.apache.xerces.xs.XSWildcard;
import mf.org.apache.xerces.xs.datatypes.ObjectList;

public class SchemaGrammar implements XSGrammar, XSNamespaceItem {
    private static final int BASICSET_COUNT = 29;
    private static final int FULLSET_COUNT = 46;
    private static final boolean[] GLOBAL_COMP;
    private static final int GRAMMAR_XS = 1;
    private static final int GRAMMAR_XSI = 2;
    private static final int INC_SIZE = 16;
    private static final int INITIAL_SIZE = 16;
    private static final short MAX_COMP_IDX = (short) 16;
    private static final int REDEFINED_GROUP_INIT_SIZE = 2;
    public static final BuiltinSchemaGrammar SG_SchemaNS = new BuiltinSchemaGrammar(1, (short) 1);
    private static final BuiltinSchemaGrammar SG_SchemaNSExtended = new BuiltinSchemaGrammar(1, (short) 2);
    public static final BuiltinSchemaGrammar SG_XSI = new BuiltinSchemaGrammar(2, (short) 1);
    public static final XSSimpleType fAnySimpleType = ((XSSimpleType) SG_SchemaNS.getGlobalTypeDecl(SchemaSymbols.ATTVAL_ANYSIMPLETYPE));
    public static final XSComplexTypeDecl fAnyType = new XSAnyType();
    SymbolHash fAllGlobalElemDecls;
    XSAnnotationImpl[] fAnnotations;
    private int fCTCount;
    private SimpleLocator[] fCTLocators;
    private XSComplexTypeDecl[] fComplexTypeDecls;
    private XSNamedMap[] fComponents;
    private ObjectList[] fComponentsExt;
    private SoftReference fDOMParser;
    private Vector fDocuments;
    boolean fFullChecked;
    SymbolHash fGlobalAttrDecls;
    SymbolHash fGlobalAttrDeclsExt;
    SymbolHash fGlobalAttrGrpDecls;
    SymbolHash fGlobalAttrGrpDeclsExt;
    SymbolHash fGlobalElemDecls;
    SymbolHash fGlobalElemDeclsExt;
    SymbolHash fGlobalGroupDecls;
    SymbolHash fGlobalGroupDeclsExt;
    SymbolHash fGlobalIDConstraintDecls;
    SymbolHash fGlobalIDConstraintDeclsExt;
    SymbolHash fGlobalNotationDecls;
    SymbolHash fGlobalNotationDeclsExt;
    SymbolHash fGlobalTypeDecls;
    SymbolHash fGlobalTypeDeclsExt;
    XSDDescription fGrammarDescription;
    Vector fImported;
    private boolean fIsImmutable;
    private Vector fLocations;
    int fNumAnnotations;
    private int fRGCount;
    private SimpleLocator[] fRGLocators;
    private XSGroupDecl[] fRedefinedGroupDecls;
    private SoftReference fSAXParser;
    private int fSubGroupCount;
    private XSElementDecl[] fSubGroups;
    private SymbolTable fSymbolTable;
    String fTargetNamespace;

    private static class BuiltinAttrDecl extends XSAttributeDecl {
        public BuiltinAttrDecl(String name, String tns, XSSimpleType type, short scope) {
            this.fName = name;
            this.fTargetNamespace = tns;
            this.fType = type;
            this.fScope = scope;
        }

        public void setValues(String name, String targetNamespace, XSSimpleType simpleType, short constraintType, short scope, ValidatedInfo valInfo, XSComplexTypeDecl enclosingCT) {
        }

        public void reset() {
        }

        public XSAnnotation getAnnotation() {
            return null;
        }

        public XSNamespaceItem getNamespaceItem() {
            return SchemaGrammar.SG_XSI;
        }
    }

    public static class BuiltinSchemaGrammar extends SchemaGrammar {
        private static final String EXTENDED_SCHEMA_FACTORY_CLASS = "mf.org.apache.xerces.impl.dv.xs.ExtendedSchemaDVFactoryImpl";

        public BuiltinSchemaGrammar(int grammar, short schemaVersion) {
            SchemaDVFactory schemaFactory;
            if (schemaVersion == (short) 1) {
                schemaFactory = SchemaDVFactory.getInstance();
            } else {
                schemaFactory = SchemaDVFactory.getInstance(EXTENDED_SCHEMA_FACTORY_CLASS);
            }
            if (grammar == 1) {
                this.fTargetNamespace = SchemaSymbols.URI_SCHEMAFORSCHEMA;
                this.fGrammarDescription = new XSDDescription();
                this.fGrammarDescription.fContextType = (short) 3;
                this.fGrammarDescription.setNamespace(SchemaSymbols.URI_SCHEMAFORSCHEMA);
                this.fGlobalAttrDecls = new SymbolHash(1);
                this.fGlobalAttrGrpDecls = new SymbolHash(1);
                this.fGlobalElemDecls = new SymbolHash(1);
                this.fGlobalGroupDecls = new SymbolHash(1);
                this.fGlobalNotationDecls = new SymbolHash(1);
                this.fGlobalIDConstraintDecls = new SymbolHash(1);
                this.fGlobalAttrDeclsExt = new SymbolHash(1);
                this.fGlobalAttrGrpDeclsExt = new SymbolHash(1);
                this.fGlobalElemDeclsExt = new SymbolHash(1);
                this.fGlobalGroupDeclsExt = new SymbolHash(1);
                this.fGlobalNotationDeclsExt = new SymbolHash(1);
                this.fGlobalIDConstraintDeclsExt = new SymbolHash(1);
                this.fGlobalTypeDeclsExt = new SymbolHash(1);
                this.fAllGlobalElemDecls = new SymbolHash(1);
                this.fGlobalTypeDecls = schemaFactory.getBuiltInTypes();
                int length = this.fGlobalTypeDecls.getLength();
                XSTypeDefinition[] typeDefinitions = new XSTypeDefinition[length];
                this.fGlobalTypeDecls.getValues(typeDefinitions, 0);
                for (int i = 0; i < length; i++) {
                    XSTypeDefinition xtd = typeDefinitions[i];
                    if (xtd instanceof XSSimpleTypeDecl) {
                        ((XSSimpleTypeDecl) xtd).setNamespaceItem(this);
                    }
                }
                this.fGlobalTypeDecls.put(fAnyType.getName(), fAnyType);
            } else if (grammar == 2) {
                this.fTargetNamespace = SchemaSymbols.URI_XSI;
                this.fGrammarDescription = new XSDDescription();
                this.fGrammarDescription.fContextType = (short) 3;
                this.fGrammarDescription.setNamespace(SchemaSymbols.URI_XSI);
                this.fGlobalAttrGrpDecls = new SymbolHash(1);
                this.fGlobalElemDecls = new SymbolHash(1);
                this.fGlobalGroupDecls = new SymbolHash(1);
                this.fGlobalNotationDecls = new SymbolHash(1);
                this.fGlobalIDConstraintDecls = new SymbolHash(1);
                this.fGlobalTypeDecls = new SymbolHash(1);
                this.fGlobalAttrDeclsExt = new SymbolHash(1);
                this.fGlobalAttrGrpDeclsExt = new SymbolHash(1);
                this.fGlobalElemDeclsExt = new SymbolHash(1);
                this.fGlobalGroupDeclsExt = new SymbolHash(1);
                this.fGlobalNotationDeclsExt = new SymbolHash(1);
                this.fGlobalIDConstraintDeclsExt = new SymbolHash(1);
                this.fGlobalTypeDeclsExt = new SymbolHash(1);
                this.fAllGlobalElemDecls = new SymbolHash(1);
                this.fGlobalAttrDecls = new SymbolHash(8);
                String name = SchemaSymbols.XSI_TYPE;
                this.fGlobalAttrDecls.put(name, new BuiltinAttrDecl(name, SchemaSymbols.URI_XSI, schemaFactory.getBuiltInType(SchemaSymbols.ATTVAL_QNAME), (short) 1));
                name = SchemaSymbols.XSI_NIL;
                this.fGlobalAttrDecls.put(name, new BuiltinAttrDecl(name, SchemaSymbols.URI_XSI, schemaFactory.getBuiltInType(SchemaSymbols.ATTVAL_BOOLEAN), (short) 1));
                XSSimpleType anyURI = schemaFactory.getBuiltInType(SchemaSymbols.ATTVAL_ANYURI);
                name = SchemaSymbols.XSI_SCHEMALOCATION;
                String tns = SchemaSymbols.URI_XSI;
                XSSimpleType type = schemaFactory.createTypeList("#AnonType_schemaLocation", SchemaSymbols.URI_XSI, (short) 0, anyURI, null);
                if (type instanceof XSSimpleTypeDecl) {
                    ((XSSimpleTypeDecl) type).setAnonymous(true);
                }
                this.fGlobalAttrDecls.put(name, new BuiltinAttrDecl(name, tns, type, (short) 1));
                name = SchemaSymbols.XSI_NONAMESPACESCHEMALOCATION;
                this.fGlobalAttrDecls.put(name, new BuiltinAttrDecl(name, SchemaSymbols.URI_XSI, anyURI, (short) 1));
            }
        }

        public XMLGrammarDescription getGrammarDescription() {
            return this.fGrammarDescription.makeClone();
        }

        public void setImportedGrammars(Vector importedGrammars) {
        }

        public void addGlobalAttributeDecl(XSAttributeDecl decl) {
        }

        public void addGlobalAttributeDecl(XSAttributeDecl decl, String location) {
        }

        public void addGlobalAttributeGroupDecl(XSAttributeGroupDecl decl) {
        }

        public void addGlobalAttributeGroupDecl(XSAttributeGroupDecl decl, String location) {
        }

        public void addGlobalElementDecl(XSElementDecl decl) {
        }

        public void addGlobalElementDecl(XSElementDecl decl, String location) {
        }

        public void addGlobalElementDeclAll(XSElementDecl decl) {
        }

        public void addGlobalGroupDecl(XSGroupDecl decl) {
        }

        public void addGlobalGroupDecl(XSGroupDecl decl, String location) {
        }

        public void addGlobalNotationDecl(XSNotationDecl decl) {
        }

        public void addGlobalNotationDecl(XSNotationDecl decl, String location) {
        }

        public void addGlobalTypeDecl(XSTypeDefinition decl) {
        }

        public void addGlobalTypeDecl(XSTypeDefinition decl, String location) {
        }

        public void addGlobalComplexTypeDecl(XSComplexTypeDecl decl) {
        }

        public void addGlobalComplexTypeDecl(XSComplexTypeDecl decl, String location) {
        }

        public void addGlobalSimpleTypeDecl(XSSimpleType decl) {
        }

        public void addGlobalSimpleTypeDecl(XSSimpleType decl, String location) {
        }

        public void addComplexTypeDecl(XSComplexTypeDecl decl, SimpleLocator locator) {
        }

        public void addRedefinedGroupDecl(XSGroupDecl derived, XSGroupDecl base, SimpleLocator locator) {
        }

        public synchronized void addDocument(Object document, String location) {
        }

        synchronized DOMParser getDOMParser() {
            return null;
        }

        synchronized SAXParser getSAXParser() {
            return null;
        }
    }

    public static final class Schema4Annotations extends SchemaGrammar {
        public static final Schema4Annotations INSTANCE = new Schema4Annotations();

        private Schema4Annotations() {
            this.fTargetNamespace = SchemaSymbols.URI_SCHEMAFORSCHEMA;
            this.fGrammarDescription = new XSDDescription();
            this.fGrammarDescription.fContextType = (short) 3;
            this.fGrammarDescription.setNamespace(SchemaSymbols.URI_SCHEMAFORSCHEMA);
            this.fGlobalAttrDecls = new SymbolHash(1);
            this.fGlobalAttrGrpDecls = new SymbolHash(1);
            this.fGlobalElemDecls = new SymbolHash(6);
            this.fGlobalGroupDecls = new SymbolHash(1);
            this.fGlobalNotationDecls = new SymbolHash(1);
            this.fGlobalIDConstraintDecls = new SymbolHash(1);
            this.fGlobalAttrDeclsExt = new SymbolHash(1);
            this.fGlobalAttrGrpDeclsExt = new SymbolHash(1);
            this.fGlobalElemDeclsExt = new SymbolHash(6);
            this.fGlobalGroupDeclsExt = new SymbolHash(1);
            this.fGlobalNotationDeclsExt = new SymbolHash(1);
            this.fGlobalIDConstraintDeclsExt = new SymbolHash(1);
            this.fGlobalTypeDeclsExt = new SymbolHash(1);
            this.fAllGlobalElemDecls = new SymbolHash(6);
            this.fGlobalTypeDecls = SG_SchemaNS.fGlobalTypeDecls;
            XSElementDecl annotationDecl = createAnnotationElementDecl(SchemaSymbols.ELT_ANNOTATION);
            XSElementDecl documentationDecl = createAnnotationElementDecl(SchemaSymbols.ELT_DOCUMENTATION);
            XSElementDecl appinfoDecl = createAnnotationElementDecl(SchemaSymbols.ELT_APPINFO);
            this.fGlobalElemDecls.put(annotationDecl.fName, annotationDecl);
            this.fGlobalElemDecls.put(documentationDecl.fName, documentationDecl);
            this.fGlobalElemDecls.put(appinfoDecl.fName, appinfoDecl);
            this.fGlobalElemDeclsExt.put("," + annotationDecl.fName, annotationDecl);
            this.fGlobalElemDeclsExt.put("," + documentationDecl.fName, documentationDecl);
            this.fGlobalElemDeclsExt.put("," + appinfoDecl.fName, appinfoDecl);
            this.fAllGlobalElemDecls.put(annotationDecl, annotationDecl);
            this.fAllGlobalElemDecls.put(documentationDecl, documentationDecl);
            this.fAllGlobalElemDecls.put(appinfoDecl, appinfoDecl);
            XSComplexTypeDecl annotationType = new XSComplexTypeDecl();
            XSComplexTypeDecl documentationType = new XSComplexTypeDecl();
            XSComplexTypeDecl appinfoType = new XSComplexTypeDecl();
            annotationDecl.fType = annotationType;
            documentationDecl.fType = documentationType;
            appinfoDecl.fType = appinfoType;
            XSAttributeGroupDecl annotationAttrs = new XSAttributeGroupDecl();
            XSAttributeGroupDecl documentationAttrs = new XSAttributeGroupDecl();
            XSAttributeGroupDecl appinfoAttrs = new XSAttributeGroupDecl();
            XSAttributeUseImpl annotationIDAttr = new XSAttributeUseImpl();
            annotationIDAttr.fAttrDecl = new XSAttributeDecl();
            annotationIDAttr.fAttrDecl.setValues(SchemaSymbols.ATT_ID, null, (XSSimpleType) this.fGlobalTypeDecls.get(SchemaSymbols.ATTVAL_ID), (short) 0, (short) 2, null, annotationType, null);
            annotationIDAttr.fUse = (short) 0;
            annotationIDAttr.fConstraintType = (short) 0;
            XSAttributeUseImpl documentationSourceAttr = new XSAttributeUseImpl();
            documentationSourceAttr.fAttrDecl = new XSAttributeDecl();
            documentationSourceAttr.fAttrDecl.setValues(SchemaSymbols.ATT_SOURCE, null, (XSSimpleType) this.fGlobalTypeDecls.get(SchemaSymbols.ATTVAL_ANYURI), (short) 0, (short) 2, null, documentationType, null);
            documentationSourceAttr.fUse = (short) 0;
            documentationSourceAttr.fConstraintType = (short) 0;
            XSAttributeUseImpl documentationLangAttr = new XSAttributeUseImpl();
            documentationLangAttr.fAttrDecl = new XSAttributeDecl();
            documentationLangAttr.fAttrDecl.setValues("lang".intern(), NamespaceContext.XML_URI, (XSSimpleType) this.fGlobalTypeDecls.get(SchemaSymbols.ATTVAL_LANGUAGE), (short) 0, (short) 2, null, documentationType, null);
            documentationLangAttr.fUse = (short) 0;
            documentationLangAttr.fConstraintType = (short) 0;
            XSAttributeUseImpl appinfoSourceAttr = new XSAttributeUseImpl();
            appinfoSourceAttr.fAttrDecl = new XSAttributeDecl();
            appinfoSourceAttr.fAttrDecl.setValues(SchemaSymbols.ATT_SOURCE, null, (XSSimpleType) this.fGlobalTypeDecls.get(SchemaSymbols.ATTVAL_ANYURI), (short) 0, (short) 2, null, appinfoType, null);
            appinfoSourceAttr.fUse = (short) 0;
            appinfoSourceAttr.fConstraintType = (short) 0;
            XSWildcardDecl otherAttrs = new XSWildcardDecl();
            String[] strArr = new String[2];
            strArr[0] = this.fTargetNamespace;
            otherAttrs.fNamespaceList = strArr;
            otherAttrs.fType = (short) 2;
            otherAttrs.fProcessContents = (short) 3;
            annotationAttrs.addAttributeUse(annotationIDAttr);
            annotationAttrs.fAttributeWC = otherAttrs;
            documentationAttrs.addAttributeUse(documentationSourceAttr);
            documentationAttrs.addAttributeUse(documentationLangAttr);
            documentationAttrs.fAttributeWC = otherAttrs;
            appinfoAttrs.addAttributeUse(appinfoSourceAttr);
            appinfoAttrs.fAttributeWC = otherAttrs;
            XSParticleDecl annotationParticle = createUnboundedModelGroupParticle();
            XSModelGroupImpl annotationChoice = new XSModelGroupImpl();
            annotationChoice.fCompositor = (short) 101;
            annotationChoice.fParticleCount = 2;
            annotationChoice.fParticles = new XSParticleDecl[2];
            annotationChoice.fParticles[0] = createChoiceElementParticle(appinfoDecl);
            annotationChoice.fParticles[1] = createChoiceElementParticle(documentationDecl);
            annotationParticle.fValue = annotationChoice;
            XSParticleDecl anyWCSequenceParticle = createUnboundedAnyWildcardSequenceParticle();
            annotationType.setValues("#AnonType_" + SchemaSymbols.ELT_ANNOTATION, this.fTargetNamespace, SchemaGrammar.fAnyType, (short) 2, (short) 0, (short) 3, (short) 2, false, annotationAttrs, null, annotationParticle, new XSObjectListImpl(null, 0));
            annotationType.setName("#AnonType_" + SchemaSymbols.ELT_ANNOTATION);
            annotationType.setIsAnonymous();
            documentationType.setValues("#AnonType_" + SchemaSymbols.ELT_DOCUMENTATION, this.fTargetNamespace, SchemaGrammar.fAnyType, (short) 2, (short) 0, (short) 3, (short) 3, false, documentationAttrs, null, anyWCSequenceParticle, new XSObjectListImpl(null, 0));
            documentationType.setName("#AnonType_" + SchemaSymbols.ELT_DOCUMENTATION);
            documentationType.setIsAnonymous();
            appinfoType.setValues("#AnonType_" + SchemaSymbols.ELT_APPINFO, this.fTargetNamespace, SchemaGrammar.fAnyType, (short) 2, (short) 0, (short) 3, (short) 3, false, appinfoAttrs, null, anyWCSequenceParticle, new XSObjectListImpl(null, 0));
            appinfoType.setName("#AnonType_" + SchemaSymbols.ELT_APPINFO);
            appinfoType.setIsAnonymous();
        }

        public XMLGrammarDescription getGrammarDescription() {
            return this.fGrammarDescription.makeClone();
        }

        public void setImportedGrammars(Vector importedGrammars) {
        }

        public void addGlobalAttributeDecl(XSAttributeDecl decl) {
        }

        public void addGlobalAttributeDecl(XSAttributeGroupDecl decl, String location) {
        }

        public void addGlobalAttributeGroupDecl(XSAttributeGroupDecl decl) {
        }

        public void addGlobalAttributeGroupDecl(XSAttributeGroupDecl decl, String location) {
        }

        public void addGlobalElementDecl(XSElementDecl decl) {
        }

        public void addGlobalElementDecl(XSElementDecl decl, String location) {
        }

        public void addGlobalElementDeclAll(XSElementDecl decl) {
        }

        public void addGlobalGroupDecl(XSGroupDecl decl) {
        }

        public void addGlobalGroupDecl(XSGroupDecl decl, String location) {
        }

        public void addGlobalNotationDecl(XSNotationDecl decl) {
        }

        public void addGlobalNotationDecl(XSNotationDecl decl, String location) {
        }

        public void addGlobalTypeDecl(XSTypeDefinition decl) {
        }

        public void addGlobalTypeDecl(XSTypeDefinition decl, String location) {
        }

        public void addGlobalComplexTypeDecl(XSComplexTypeDecl decl) {
        }

        public void addGlobalComplexTypeDecl(XSComplexTypeDecl decl, String location) {
        }

        public void addGlobalSimpleTypeDecl(XSSimpleType decl) {
        }

        public void addGlobalSimpleTypeDecl(XSSimpleType decl, String location) {
        }

        public void addComplexTypeDecl(XSComplexTypeDecl decl, SimpleLocator locator) {
        }

        public void addRedefinedGroupDecl(XSGroupDecl derived, XSGroupDecl base, SimpleLocator locator) {
        }

        public synchronized void addDocument(Object document, String location) {
        }

        synchronized DOMParser getDOMParser() {
            return null;
        }

        synchronized SAXParser getSAXParser() {
            return null;
        }

        private XSElementDecl createAnnotationElementDecl(String localName) {
            XSElementDecl eDecl = new XSElementDecl();
            eDecl.fName = localName;
            eDecl.fTargetNamespace = this.fTargetNamespace;
            eDecl.setIsGlobal();
            eDecl.fBlock = (short) 7;
            eDecl.setConstraintType((short) 0);
            return eDecl;
        }

        private XSParticleDecl createUnboundedModelGroupParticle() {
            XSParticleDecl particle = new XSParticleDecl();
            particle.fMinOccurs = 0;
            particle.fMaxOccurs = -1;
            particle.fType = (short) 3;
            return particle;
        }

        private XSParticleDecl createChoiceElementParticle(XSElementDecl ref) {
            XSParticleDecl particle = new XSParticleDecl();
            particle.fMinOccurs = 1;
            particle.fMaxOccurs = 1;
            particle.fType = (short) 1;
            particle.fValue = ref;
            return particle;
        }

        private XSParticleDecl createUnboundedAnyWildcardSequenceParticle() {
            XSParticleDecl particle = createUnboundedModelGroupParticle();
            XSModelGroupImpl sequence = new XSModelGroupImpl();
            sequence.fCompositor = (short) 102;
            sequence.fParticleCount = 1;
            sequence.fParticles = new XSParticleDecl[1];
            sequence.fParticles[0] = createAnyLaxWildcardParticle();
            particle.fValue = sequence;
            return particle;
        }

        private XSParticleDecl createAnyLaxWildcardParticle() {
            XSParticleDecl particle = new XSParticleDecl();
            particle.fMinOccurs = 1;
            particle.fMaxOccurs = 1;
            particle.fType = (short) 2;
            XSWildcardDecl anyWC = new XSWildcardDecl();
            anyWC.fNamespaceList = null;
            anyWC.fType = (short) 1;
            anyWC.fProcessContents = (short) 3;
            particle.fValue = anyWC;
            return particle;
        }
    }

    private static class XSAnyType extends XSComplexTypeDecl {
        public XSAnyType() {
            this.fName = SchemaSymbols.ATTVAL_ANYTYPE;
            this.fTargetNamespace = SchemaSymbols.URI_SCHEMAFORSCHEMA;
            this.fBaseType = this;
            this.fDerivedBy = (short) 2;
            this.fContentType = (short) 3;
            this.fParticle = null;
            this.fAttrGrp = null;
        }

        public void setValues(String name, String targetNamespace, XSTypeDefinition baseType, short derivedBy, short schemaFinal, short block, short contentType, boolean isAbstract, XSAttributeGroupDecl attrGrp, XSSimpleType simpleType, XSParticleDecl particle) {
        }

        public void setName(String name) {
        }

        public void setIsAbstractType() {
        }

        public void setContainsTypeID() {
        }

        public void setIsAnonymous() {
        }

        public void reset() {
        }

        public XSObjectList getAttributeUses() {
            return XSObjectListImpl.EMPTY_LIST;
        }

        public XSAttributeGroupDecl getAttrGrp() {
            XSWildcardDecl wildcard = new XSWildcardDecl();
            wildcard.fProcessContents = (short) 3;
            XSAttributeGroupDecl attrGrp = new XSAttributeGroupDecl();
            attrGrp.fAttributeWC = wildcard;
            return attrGrp;
        }

        public XSWildcard getAttributeWildcard() {
            XSWildcardDecl wildcard = new XSWildcardDecl();
            wildcard.fProcessContents = (short) 3;
            return wildcard;
        }

        public XSParticle getParticle() {
            XSWildcardDecl wildcard = new XSWildcardDecl();
            wildcard.fProcessContents = (short) 3;
            XSParticleDecl particleW = new XSParticleDecl();
            particleW.fMinOccurs = 0;
            particleW.fMaxOccurs = -1;
            particleW.fType = (short) 2;
            particleW.fValue = wildcard;
            XSModelGroupImpl group = new XSModelGroupImpl();
            group.fCompositor = (short) 102;
            group.fParticleCount = 1;
            group.fParticles = new XSParticleDecl[1];
            group.fParticles[0] = particleW;
            XSParticleDecl particleG = new XSParticleDecl();
            particleG.fType = (short) 3;
            particleG.fValue = group;
            return particleG;
        }

        public XSObjectList getAnnotations() {
            return XSObjectListImpl.EMPTY_LIST;
        }

        public XSNamespaceItem getNamespaceItem() {
            return SchemaGrammar.SG_SchemaNS;
        }
    }

    protected SchemaGrammar() {
        this.fGrammarDescription = null;
        this.fAnnotations = null;
        this.fSymbolTable = null;
        this.fSAXParser = null;
        this.fDOMParser = null;
        this.fIsImmutable = false;
        this.fImported = null;
        this.fCTCount = 0;
        this.fComplexTypeDecls = new XSComplexTypeDecl[16];
        this.fCTLocators = new SimpleLocator[16];
        this.fRGCount = 0;
        this.fRedefinedGroupDecls = new XSGroupDecl[2];
        this.fRGLocators = new SimpleLocator[1];
        this.fFullChecked = false;
        this.fSubGroupCount = 0;
        this.fSubGroups = new XSElementDecl[16];
        this.fComponents = null;
        this.fComponentsExt = null;
        this.fDocuments = null;
        this.fLocations = null;
    }

    public SchemaGrammar(String targetNamespace, XSDDescription grammarDesc, SymbolTable symbolTable) {
        this.fGrammarDescription = null;
        this.fAnnotations = null;
        this.fSymbolTable = null;
        this.fSAXParser = null;
        this.fDOMParser = null;
        this.fIsImmutable = false;
        this.fImported = null;
        this.fCTCount = 0;
        this.fComplexTypeDecls = new XSComplexTypeDecl[16];
        this.fCTLocators = new SimpleLocator[16];
        this.fRGCount = 0;
        this.fRedefinedGroupDecls = new XSGroupDecl[2];
        this.fRGLocators = new SimpleLocator[1];
        this.fFullChecked = false;
        this.fSubGroupCount = 0;
        this.fSubGroups = new XSElementDecl[16];
        this.fComponents = null;
        this.fComponentsExt = null;
        this.fDocuments = null;
        this.fLocations = null;
        this.fTargetNamespace = targetNamespace;
        this.fGrammarDescription = grammarDesc;
        this.fSymbolTable = symbolTable;
        this.fGlobalAttrDecls = new SymbolHash();
        this.fGlobalAttrGrpDecls = new SymbolHash();
        this.fGlobalElemDecls = new SymbolHash();
        this.fGlobalGroupDecls = new SymbolHash();
        this.fGlobalNotationDecls = new SymbolHash();
        this.fGlobalIDConstraintDecls = new SymbolHash();
        this.fGlobalAttrDeclsExt = new SymbolHash();
        this.fGlobalAttrGrpDeclsExt = new SymbolHash();
        this.fGlobalElemDeclsExt = new SymbolHash();
        this.fGlobalGroupDeclsExt = new SymbolHash();
        this.fGlobalNotationDeclsExt = new SymbolHash();
        this.fGlobalIDConstraintDeclsExt = new SymbolHash();
        this.fGlobalTypeDeclsExt = new SymbolHash();
        this.fAllGlobalElemDecls = new SymbolHash();
        if (this.fTargetNamespace == SchemaSymbols.URI_SCHEMAFORSCHEMA) {
            this.fGlobalTypeDecls = SG_SchemaNS.fGlobalTypeDecls.makeClone();
        } else {
            this.fGlobalTypeDecls = new SymbolHash();
        }
    }

    public SchemaGrammar(SchemaGrammar grammar) {
        this.fGrammarDescription = null;
        this.fAnnotations = null;
        this.fSymbolTable = null;
        this.fSAXParser = null;
        this.fDOMParser = null;
        this.fIsImmutable = false;
        this.fImported = null;
        this.fCTCount = 0;
        this.fComplexTypeDecls = new XSComplexTypeDecl[16];
        this.fCTLocators = new SimpleLocator[16];
        this.fRGCount = 0;
        this.fRedefinedGroupDecls = new XSGroupDecl[2];
        this.fRGLocators = new SimpleLocator[1];
        this.fFullChecked = false;
        this.fSubGroupCount = 0;
        this.fSubGroups = new XSElementDecl[16];
        this.fComponents = null;
        this.fComponentsExt = null;
        this.fDocuments = null;
        this.fLocations = null;
        this.fTargetNamespace = grammar.fTargetNamespace;
        this.fGrammarDescription = grammar.fGrammarDescription.makeClone();
        this.fSymbolTable = grammar.fSymbolTable;
        this.fGlobalAttrDecls = grammar.fGlobalAttrDecls.makeClone();
        this.fGlobalAttrGrpDecls = grammar.fGlobalAttrGrpDecls.makeClone();
        this.fGlobalElemDecls = grammar.fGlobalElemDecls.makeClone();
        this.fGlobalGroupDecls = grammar.fGlobalGroupDecls.makeClone();
        this.fGlobalNotationDecls = grammar.fGlobalNotationDecls.makeClone();
        this.fGlobalIDConstraintDecls = grammar.fGlobalIDConstraintDecls.makeClone();
        this.fGlobalTypeDecls = grammar.fGlobalTypeDecls.makeClone();
        this.fGlobalAttrDeclsExt = grammar.fGlobalAttrDeclsExt.makeClone();
        this.fGlobalAttrGrpDeclsExt = grammar.fGlobalAttrGrpDeclsExt.makeClone();
        this.fGlobalElemDeclsExt = grammar.fGlobalElemDeclsExt.makeClone();
        this.fGlobalGroupDeclsExt = grammar.fGlobalGroupDeclsExt.makeClone();
        this.fGlobalNotationDeclsExt = grammar.fGlobalNotationDeclsExt.makeClone();
        this.fGlobalIDConstraintDeclsExt = grammar.fGlobalIDConstraintDeclsExt.makeClone();
        this.fGlobalTypeDeclsExt = grammar.fGlobalTypeDeclsExt.makeClone();
        this.fAllGlobalElemDecls = grammar.fAllGlobalElemDecls.makeClone();
        this.fNumAnnotations = grammar.fNumAnnotations;
        if (this.fNumAnnotations > 0) {
            this.fAnnotations = new XSAnnotationImpl[grammar.fAnnotations.length];
            System.arraycopy(grammar.fAnnotations, 0, this.fAnnotations, 0, this.fNumAnnotations);
        }
        this.fSubGroupCount = grammar.fSubGroupCount;
        if (this.fSubGroupCount > 0) {
            this.fSubGroups = new XSElementDecl[grammar.fSubGroups.length];
            System.arraycopy(grammar.fSubGroups, 0, this.fSubGroups, 0, this.fSubGroupCount);
        }
        this.fCTCount = grammar.fCTCount;
        if (this.fCTCount > 0) {
            this.fComplexTypeDecls = new XSComplexTypeDecl[grammar.fComplexTypeDecls.length];
            this.fCTLocators = new SimpleLocator[grammar.fCTLocators.length];
            System.arraycopy(grammar.fComplexTypeDecls, 0, this.fComplexTypeDecls, 0, this.fCTCount);
            System.arraycopy(grammar.fCTLocators, 0, this.fCTLocators, 0, this.fCTCount);
        }
        this.fRGCount = grammar.fRGCount;
        if (this.fRGCount > 0) {
            this.fRedefinedGroupDecls = new XSGroupDecl[grammar.fRedefinedGroupDecls.length];
            this.fRGLocators = new SimpleLocator[grammar.fRGLocators.length];
            System.arraycopy(grammar.fRedefinedGroupDecls, 0, this.fRedefinedGroupDecls, 0, this.fRGCount);
            System.arraycopy(grammar.fRGLocators, 0, this.fRGLocators, 0, this.fRGCount);
        }
        if (grammar.fImported != null) {
            this.fImported = new Vector();
            for (int i = 0; i < grammar.fImported.size(); i++) {
                this.fImported.add(grammar.fImported.elementAt(i));
            }
        }
        if (grammar.fLocations != null) {
            for (int k = 0; k < grammar.fLocations.size(); k++) {
                addDocument(null, (String) grammar.fLocations.elementAt(k));
            }
        }
    }

    public XMLGrammarDescription getGrammarDescription() {
        return this.fGrammarDescription;
    }

    public boolean isNamespaceAware() {
        return true;
    }

    public void setImportedGrammars(Vector importedGrammars) {
        this.fImported = importedGrammars;
    }

    public Vector getImportedGrammars() {
        return this.fImported;
    }

    public final String getTargetNamespace() {
        return this.fTargetNamespace;
    }

    public void addGlobalAttributeDecl(XSAttributeDecl decl) {
        this.fGlobalAttrDecls.put(decl.fName, decl);
        decl.setNamespaceItem(this);
    }

    public void addGlobalAttributeDecl(XSAttributeDecl decl, String location) {
        SymbolHash symbolHash = this.fGlobalAttrDeclsExt;
        if (location == null) {
            location = "";
        }
        symbolHash.put(new StringBuilder(String.valueOf(location)).append(",").append(decl.fName).toString(), decl);
        if (decl.getNamespaceItem() == null) {
            decl.setNamespaceItem(this);
        }
    }

    public void addGlobalAttributeGroupDecl(XSAttributeGroupDecl decl) {
        this.fGlobalAttrGrpDecls.put(decl.fName, decl);
        decl.setNamespaceItem(this);
    }

    public void addGlobalAttributeGroupDecl(XSAttributeGroupDecl decl, String location) {
        SymbolHash symbolHash = this.fGlobalAttrGrpDeclsExt;
        if (location == null) {
            location = "";
        }
        symbolHash.put(new StringBuilder(String.valueOf(location)).append(",").append(decl.fName).toString(), decl);
        if (decl.getNamespaceItem() == null) {
            decl.setNamespaceItem(this);
        }
    }

    public void addGlobalElementDeclAll(XSElementDecl decl) {
        if (this.fAllGlobalElemDecls.get(decl) == null) {
            this.fAllGlobalElemDecls.put(decl, decl);
            if (decl.fSubGroup != null) {
                if (this.fSubGroupCount == this.fSubGroups.length) {
                    this.fSubGroups = resize(this.fSubGroups, this.fSubGroupCount + 16);
                }
                XSElementDecl[] xSElementDeclArr = this.fSubGroups;
                int i = this.fSubGroupCount;
                this.fSubGroupCount = i + 1;
                xSElementDeclArr[i] = decl;
            }
        }
    }

    public void addGlobalElementDecl(XSElementDecl decl) {
        this.fGlobalElemDecls.put(decl.fName, decl);
        decl.setNamespaceItem(this);
    }

    public void addGlobalElementDecl(XSElementDecl decl, String location) {
        SymbolHash symbolHash = this.fGlobalElemDeclsExt;
        if (location == null) {
            location = "";
        }
        symbolHash.put(new StringBuilder(String.valueOf(location)).append(",").append(decl.fName).toString(), decl);
        if (decl.getNamespaceItem() == null) {
            decl.setNamespaceItem(this);
        }
    }

    public void addGlobalGroupDecl(XSGroupDecl decl) {
        this.fGlobalGroupDecls.put(decl.fName, decl);
        decl.setNamespaceItem(this);
    }

    public void addGlobalGroupDecl(XSGroupDecl decl, String location) {
        SymbolHash symbolHash = this.fGlobalGroupDeclsExt;
        if (location == null) {
            location = "";
        }
        symbolHash.put(new StringBuilder(String.valueOf(location)).append(",").append(decl.fName).toString(), decl);
        if (decl.getNamespaceItem() == null) {
            decl.setNamespaceItem(this);
        }
    }

    public void addGlobalNotationDecl(XSNotationDecl decl) {
        this.fGlobalNotationDecls.put(decl.fName, decl);
        decl.setNamespaceItem(this);
    }

    public void addGlobalNotationDecl(XSNotationDecl decl, String location) {
        SymbolHash symbolHash = this.fGlobalNotationDeclsExt;
        if (location == null) {
            location = "";
        }
        symbolHash.put(new StringBuilder(String.valueOf(location)).append(",").append(decl.fName).toString(), decl);
        if (decl.getNamespaceItem() == null) {
            decl.setNamespaceItem(this);
        }
    }

    public void addGlobalTypeDecl(XSTypeDefinition decl) {
        this.fGlobalTypeDecls.put(decl.getName(), decl);
        if (decl instanceof XSComplexTypeDecl) {
            ((XSComplexTypeDecl) decl).setNamespaceItem(this);
        } else if (decl instanceof XSSimpleTypeDecl) {
            ((XSSimpleTypeDecl) decl).setNamespaceItem(this);
        }
    }

    public void addGlobalTypeDecl(XSTypeDefinition decl, String location) {
        SymbolHash symbolHash = this.fGlobalTypeDeclsExt;
        if (location == null) {
            location = "";
        }
        symbolHash.put(new StringBuilder(String.valueOf(location)).append(",").append(decl.getName()).toString(), decl);
        if (decl.getNamespaceItem() != null) {
            return;
        }
        if (decl instanceof XSComplexTypeDecl) {
            ((XSComplexTypeDecl) decl).setNamespaceItem(this);
        } else if (decl instanceof XSSimpleTypeDecl) {
            ((XSSimpleTypeDecl) decl).setNamespaceItem(this);
        }
    }

    public void addGlobalComplexTypeDecl(XSComplexTypeDecl decl) {
        this.fGlobalTypeDecls.put(decl.getName(), decl);
        decl.setNamespaceItem(this);
    }

    public void addGlobalComplexTypeDecl(XSComplexTypeDecl decl, String location) {
        SymbolHash symbolHash = this.fGlobalTypeDeclsExt;
        if (location == null) {
            location = "";
        }
        symbolHash.put(new StringBuilder(String.valueOf(location)).append(",").append(decl.getName()).toString(), decl);
        if (decl.getNamespaceItem() == null) {
            decl.setNamespaceItem(this);
        }
    }

    public void addGlobalSimpleTypeDecl(XSSimpleType decl) {
        this.fGlobalTypeDecls.put(decl.getName(), decl);
        if (decl instanceof XSSimpleTypeDecl) {
            ((XSSimpleTypeDecl) decl).setNamespaceItem(this);
        }
    }

    public void addGlobalSimpleTypeDecl(XSSimpleType decl, String location) {
        SymbolHash symbolHash = this.fGlobalTypeDeclsExt;
        if (location == null) {
            location = "";
        }
        symbolHash.put(new StringBuilder(String.valueOf(location)).append(",").append(decl.getName()).toString(), decl);
        if (decl.getNamespaceItem() == null && (decl instanceof XSSimpleTypeDecl)) {
            ((XSSimpleTypeDecl) decl).setNamespaceItem(this);
        }
    }

    public final void addIDConstraintDecl(XSElementDecl elmDecl, IdentityConstraint decl) {
        elmDecl.addIDConstraint(decl);
        this.fGlobalIDConstraintDecls.put(decl.getIdentityConstraintName(), decl);
    }

    public final void addIDConstraintDecl(XSElementDecl elmDecl, IdentityConstraint decl, String location) {
        SymbolHash symbolHash = this.fGlobalIDConstraintDeclsExt;
        if (location == null) {
            location = "";
        }
        symbolHash.put(new StringBuilder(String.valueOf(location)).append(",").append(decl.getIdentityConstraintName()).toString(), decl);
    }

    public final XSAttributeDecl getGlobalAttributeDecl(String declName) {
        return (XSAttributeDecl) this.fGlobalAttrDecls.get(declName);
    }

    public final XSAttributeDecl getGlobalAttributeDecl(String declName, String location) {
        SymbolHash symbolHash = this.fGlobalAttrDeclsExt;
        if (location == null) {
            location = "";
        }
        return (XSAttributeDecl) symbolHash.get(new StringBuilder(String.valueOf(location)).append(",").append(declName).toString());
    }

    public final XSAttributeGroupDecl getGlobalAttributeGroupDecl(String declName) {
        return (XSAttributeGroupDecl) this.fGlobalAttrGrpDecls.get(declName);
    }

    public final XSAttributeGroupDecl getGlobalAttributeGroupDecl(String declName, String location) {
        SymbolHash symbolHash = this.fGlobalAttrGrpDeclsExt;
        if (location == null) {
            location = "";
        }
        return (XSAttributeGroupDecl) symbolHash.get(new StringBuilder(String.valueOf(location)).append(",").append(declName).toString());
    }

    public final XSElementDecl getGlobalElementDecl(String declName) {
        return (XSElementDecl) this.fGlobalElemDecls.get(declName);
    }

    public final XSElementDecl getGlobalElementDecl(String declName, String location) {
        SymbolHash symbolHash = this.fGlobalElemDeclsExt;
        if (location == null) {
            location = "";
        }
        return (XSElementDecl) symbolHash.get(new StringBuilder(String.valueOf(location)).append(",").append(declName).toString());
    }

    public final XSGroupDecl getGlobalGroupDecl(String declName) {
        return (XSGroupDecl) this.fGlobalGroupDecls.get(declName);
    }

    public final XSGroupDecl getGlobalGroupDecl(String declName, String location) {
        SymbolHash symbolHash = this.fGlobalGroupDeclsExt;
        if (location == null) {
            location = "";
        }
        return (XSGroupDecl) symbolHash.get(new StringBuilder(String.valueOf(location)).append(",").append(declName).toString());
    }

    public final XSNotationDecl getGlobalNotationDecl(String declName) {
        return (XSNotationDecl) this.fGlobalNotationDecls.get(declName);
    }

    public final XSNotationDecl getGlobalNotationDecl(String declName, String location) {
        SymbolHash symbolHash = this.fGlobalNotationDeclsExt;
        if (location == null) {
            location = "";
        }
        return (XSNotationDecl) symbolHash.get(new StringBuilder(String.valueOf(location)).append(",").append(declName).toString());
    }

    public final XSTypeDefinition getGlobalTypeDecl(String declName) {
        return (XSTypeDefinition) this.fGlobalTypeDecls.get(declName);
    }

    public final XSTypeDefinition getGlobalTypeDecl(String declName, String location) {
        SymbolHash symbolHash = this.fGlobalTypeDeclsExt;
        if (location == null) {
            location = "";
        }
        return (XSTypeDefinition) symbolHash.get(new StringBuilder(String.valueOf(location)).append(",").append(declName).toString());
    }

    public final IdentityConstraint getIDConstraintDecl(String declName) {
        return (IdentityConstraint) this.fGlobalIDConstraintDecls.get(declName);
    }

    public final IdentityConstraint getIDConstraintDecl(String declName, String location) {
        SymbolHash symbolHash = this.fGlobalIDConstraintDeclsExt;
        if (location == null) {
            location = "";
        }
        return (IdentityConstraint) symbolHash.get(new StringBuilder(String.valueOf(location)).append(",").append(declName).toString());
    }

    public final boolean hasIDConstraints() {
        return this.fGlobalIDConstraintDecls.getLength() > 0;
    }

    public void addComplexTypeDecl(XSComplexTypeDecl decl, SimpleLocator locator) {
        if (this.fCTCount == this.fComplexTypeDecls.length) {
            this.fComplexTypeDecls = resize(this.fComplexTypeDecls, this.fCTCount + 16);
            this.fCTLocators = resize(this.fCTLocators, this.fCTCount + 16);
        }
        this.fCTLocators[this.fCTCount] = locator;
        XSComplexTypeDecl[] xSComplexTypeDeclArr = this.fComplexTypeDecls;
        int i = this.fCTCount;
        this.fCTCount = i + 1;
        xSComplexTypeDeclArr[i] = decl;
    }

    public void addRedefinedGroupDecl(XSGroupDecl derived, XSGroupDecl base, SimpleLocator locator) {
        if (this.fRGCount == this.fRedefinedGroupDecls.length) {
            this.fRedefinedGroupDecls = resize(this.fRedefinedGroupDecls, this.fRGCount << 1);
            this.fRGLocators = resize(this.fRGLocators, this.fRGCount);
        }
        this.fRGLocators[this.fRGCount / 2] = locator;
        XSGroupDecl[] xSGroupDeclArr = this.fRedefinedGroupDecls;
        int i = this.fRGCount;
        this.fRGCount = i + 1;
        xSGroupDeclArr[i] = derived;
        xSGroupDeclArr = this.fRedefinedGroupDecls;
        i = this.fRGCount;
        this.fRGCount = i + 1;
        xSGroupDeclArr[i] = base;
    }

    final XSComplexTypeDecl[] getUncheckedComplexTypeDecls() {
        if (this.fCTCount < this.fComplexTypeDecls.length) {
            this.fComplexTypeDecls = resize(this.fComplexTypeDecls, this.fCTCount);
            this.fCTLocators = resize(this.fCTLocators, this.fCTCount);
        }
        return this.fComplexTypeDecls;
    }

    final SimpleLocator[] getUncheckedCTLocators() {
        if (this.fCTCount < this.fCTLocators.length) {
            this.fComplexTypeDecls = resize(this.fComplexTypeDecls, this.fCTCount);
            this.fCTLocators = resize(this.fCTLocators, this.fCTCount);
        }
        return this.fCTLocators;
    }

    final XSGroupDecl[] getRedefinedGroupDecls() {
        if (this.fRGCount < this.fRedefinedGroupDecls.length) {
            this.fRedefinedGroupDecls = resize(this.fRedefinedGroupDecls, this.fRGCount);
            this.fRGLocators = resize(this.fRGLocators, this.fRGCount / 2);
        }
        return this.fRedefinedGroupDecls;
    }

    final SimpleLocator[] getRGLocators() {
        if (this.fRGCount < this.fRedefinedGroupDecls.length) {
            this.fRedefinedGroupDecls = resize(this.fRedefinedGroupDecls, this.fRGCount);
            this.fRGLocators = resize(this.fRGLocators, this.fRGCount / 2);
        }
        return this.fRGLocators;
    }

    final void setUncheckedTypeNum(int newSize) {
        this.fCTCount = newSize;
        this.fComplexTypeDecls = resize(this.fComplexTypeDecls, this.fCTCount);
        this.fCTLocators = resize(this.fCTLocators, this.fCTCount);
    }

    final XSElementDecl[] getSubstitutionGroups() {
        if (this.fSubGroupCount < this.fSubGroups.length) {
            this.fSubGroups = resize(this.fSubGroups, this.fSubGroupCount);
        }
        return this.fSubGroups;
    }

    static {
        boolean[] zArr = new boolean[17];
        zArr[1] = true;
        zArr[2] = true;
        zArr[3] = true;
        zArr[5] = true;
        zArr[6] = true;
        zArr[10] = true;
        zArr[11] = true;
        zArr[15] = true;
        zArr[16] = true;
        GLOBAL_COMP = zArr;
    }

    public static SchemaGrammar getS4SGrammar(short schemaVersion) {
        if (schemaVersion == (short) 1) {
            return SG_SchemaNS;
        }
        return SG_SchemaNSExtended;
    }

    static final XSComplexTypeDecl[] resize(XSComplexTypeDecl[] oldArray, int newSize) {
        XSComplexTypeDecl[] newArray = new XSComplexTypeDecl[newSize];
        System.arraycopy(oldArray, 0, newArray, 0, Math.min(oldArray.length, newSize));
        return newArray;
    }

    static final XSGroupDecl[] resize(XSGroupDecl[] oldArray, int newSize) {
        XSGroupDecl[] newArray = new XSGroupDecl[newSize];
        System.arraycopy(oldArray, 0, newArray, 0, Math.min(oldArray.length, newSize));
        return newArray;
    }

    static final XSElementDecl[] resize(XSElementDecl[] oldArray, int newSize) {
        XSElementDecl[] newArray = new XSElementDecl[newSize];
        System.arraycopy(oldArray, 0, newArray, 0, Math.min(oldArray.length, newSize));
        return newArray;
    }

    static final SimpleLocator[] resize(SimpleLocator[] oldArray, int newSize) {
        SimpleLocator[] newArray = new SimpleLocator[newSize];
        System.arraycopy(oldArray, 0, newArray, 0, Math.min(oldArray.length, newSize));
        return newArray;
    }

    public synchronized void addDocument(Object document, String location) {
        if (this.fDocuments == null) {
            this.fDocuments = new Vector();
            this.fLocations = new Vector();
        }
        this.fDocuments.addElement(document);
        this.fLocations.addElement(location);
    }

    public synchronized void removeDocument(int index) {
        if (this.fDocuments != null && index >= 0 && index < this.fDocuments.size()) {
            this.fDocuments.removeElementAt(index);
            this.fLocations.removeElementAt(index);
        }
    }

    public String getSchemaNamespace() {
        return this.fTargetNamespace;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    synchronized mf.org.apache.xerces.parsers.DOMParser getDOMParser() {
        /*
        r4 = this;
        monitor-enter(r4);
        r2 = r4.fDOMParser;	 Catch:{ all -> 0x003a }
        if (r2 == 0) goto L_0x0011;
    L_0x0005:
        r2 = r4.fDOMParser;	 Catch:{ all -> 0x003a }
        r1 = r2.get();	 Catch:{ all -> 0x003a }
        r1 = (mf.org.apache.xerces.parsers.DOMParser) r1;	 Catch:{ all -> 0x003a }
        if (r1 == 0) goto L_0x0011;
    L_0x000f:
        monitor-exit(r4);
        return r1;
    L_0x0011:
        r0 = new mf.org.apache.xerces.parsers.XML11Configuration;	 Catch:{ all -> 0x003a }
        r2 = r4.fSymbolTable;	 Catch:{ all -> 0x003a }
        r0.<init>(r2);	 Catch:{ all -> 0x003a }
        r2 = "http://xml.org/sax/features/namespaces";
        r3 = 1;
        r0.setFeature(r2, r3);	 Catch:{ all -> 0x003a }
        r2 = "http://xml.org/sax/features/validation";
        r3 = 0;
        r0.setFeature(r2, r3);	 Catch:{ all -> 0x003a }
        r1 = new mf.org.apache.xerces.parsers.DOMParser;	 Catch:{ all -> 0x003a }
        r1.<init>(r0);	 Catch:{ all -> 0x003a }
        r2 = "http://apache.org/xml/features/dom/defer-node-expansion";
        r3 = 0;
        r1.setFeature(r2, r3);	 Catch:{ SAXException -> 0x003d }
    L_0x0032:
        r2 = new java.lang.ref.SoftReference;	 Catch:{ all -> 0x003a }
        r2.<init>(r1);	 Catch:{ all -> 0x003a }
        r4.fDOMParser = r2;	 Catch:{ all -> 0x003a }
        goto L_0x000f;
    L_0x003a:
        r2 = move-exception;
        monitor-exit(r4);
        throw r2;
    L_0x003d:
        r2 = move-exception;
        goto L_0x0032;
        */
        throw new UnsupportedOperationException("Method not decompiled: mf.org.apache.xerces.impl.xs.SchemaGrammar.getDOMParser():mf.org.apache.xerces.parsers.DOMParser");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    synchronized mf.org.apache.xerces.parsers.SAXParser getSAXParser() {
        /*
        r4 = this;
        monitor-enter(r4);
        r2 = r4.fSAXParser;	 Catch:{ all -> 0x0033 }
        if (r2 == 0) goto L_0x0011;
    L_0x0005:
        r2 = r4.fSAXParser;	 Catch:{ all -> 0x0033 }
        r1 = r2.get();	 Catch:{ all -> 0x0033 }
        r1 = (mf.org.apache.xerces.parsers.SAXParser) r1;	 Catch:{ all -> 0x0033 }
        if (r1 == 0) goto L_0x0011;
    L_0x000f:
        monitor-exit(r4);
        return r1;
    L_0x0011:
        r0 = new mf.org.apache.xerces.parsers.XML11Configuration;	 Catch:{ all -> 0x0033 }
        r2 = r4.fSymbolTable;	 Catch:{ all -> 0x0033 }
        r0.<init>(r2);	 Catch:{ all -> 0x0033 }
        r2 = "http://xml.org/sax/features/namespaces";
        r3 = 1;
        r0.setFeature(r2, r3);	 Catch:{ all -> 0x0033 }
        r2 = "http://xml.org/sax/features/validation";
        r3 = 0;
        r0.setFeature(r2, r3);	 Catch:{ all -> 0x0033 }
        r1 = new mf.org.apache.xerces.parsers.SAXParser;	 Catch:{ all -> 0x0033 }
        r1.<init>(r0);	 Catch:{ all -> 0x0033 }
        r2 = new java.lang.ref.SoftReference;	 Catch:{ all -> 0x0033 }
        r2.<init>(r1);	 Catch:{ all -> 0x0033 }
        r4.fSAXParser = r2;	 Catch:{ all -> 0x0033 }
        goto L_0x000f;
    L_0x0033:
        r2 = move-exception;
        monitor-exit(r4);
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: mf.org.apache.xerces.impl.xs.SchemaGrammar.getSAXParser():mf.org.apache.xerces.parsers.SAXParser");
    }

    public synchronized XSNamedMap getComponents(short objectType) {
        XSNamedMap xSNamedMap;
        if (objectType > (short) 0 && objectType <= (short) 16) {
            if (GLOBAL_COMP[objectType]) {
                if (this.fComponents == null) {
                    this.fComponents = new XSNamedMap[17];
                }
                if (this.fComponents[objectType] == null) {
                    SymbolHash table = null;
                    switch (objectType) {
                        case (short) 1:
                            table = this.fGlobalAttrDecls;
                            break;
                        case (short) 2:
                            table = this.fGlobalElemDecls;
                            break;
                        case (short) 3:
                        case (short) 15:
                        case (short) 16:
                            table = this.fGlobalTypeDecls;
                            break;
                        case (short) 5:
                            table = this.fGlobalAttrGrpDecls;
                            break;
                        case (short) 6:
                            table = this.fGlobalGroupDecls;
                            break;
                        case (short) 10:
                            table = this.fGlobalIDConstraintDecls;
                            break;
                        case (short) 11:
                            table = this.fGlobalNotationDecls;
                            break;
                    }
                    if (objectType == (short) 15 || objectType == (short) 16) {
                        this.fComponents[objectType] = new XSNamedMap4Types(this.fTargetNamespace, table, objectType);
                    } else {
                        this.fComponents[objectType] = new XSNamedMapImpl(this.fTargetNamespace, table);
                    }
                }
                xSNamedMap = this.fComponents[objectType];
            }
        }
        xSNamedMap = XSNamedMapImpl.EMPTY_MAP;
        return xSNamedMap;
    }

    public synchronized ObjectList getComponentsExt(short objectType) {
        ObjectList objectList;
        if (objectType > (short) 0 && objectType <= (short) 16) {
            if (GLOBAL_COMP[objectType]) {
                if (this.fComponentsExt == null) {
                    this.fComponentsExt = new ObjectList[17];
                }
                if (this.fComponentsExt[objectType] == null) {
                    SymbolHash table = null;
                    switch (objectType) {
                        case (short) 1:
                            table = this.fGlobalAttrDeclsExt;
                            break;
                        case (short) 2:
                            table = this.fGlobalElemDeclsExt;
                            break;
                        case (short) 3:
                        case (short) 15:
                        case (short) 16:
                            table = this.fGlobalTypeDeclsExt;
                            break;
                        case (short) 5:
                            table = this.fGlobalAttrGrpDeclsExt;
                            break;
                        case (short) 6:
                            table = this.fGlobalGroupDeclsExt;
                            break;
                        case (short) 10:
                            table = this.fGlobalIDConstraintDeclsExt;
                            break;
                        case (short) 11:
                            table = this.fGlobalNotationDeclsExt;
                            break;
                    }
                    Object[] entries = table.getEntries();
                    this.fComponentsExt[objectType] = new ObjectListImpl(entries, entries.length);
                }
                objectList = this.fComponentsExt[objectType];
            }
        }
        objectList = ObjectListImpl.EMPTY_LIST;
        return objectList;
    }

    public synchronized void resetComponents() {
        this.fComponents = null;
        this.fComponentsExt = null;
    }

    public XSTypeDefinition getTypeDefinition(String name) {
        return getGlobalTypeDecl(name);
    }

    public XSAttributeDeclaration getAttributeDeclaration(String name) {
        return getGlobalAttributeDecl(name);
    }

    public XSElementDeclaration getElementDeclaration(String name) {
        return getGlobalElementDecl(name);
    }

    public XSAttributeGroupDefinition getAttributeGroup(String name) {
        return getGlobalAttributeGroupDecl(name);
    }

    public XSModelGroupDefinition getModelGroupDefinition(String name) {
        return getGlobalGroupDecl(name);
    }

    public XSNotationDeclaration getNotationDeclaration(String name) {
        return getGlobalNotationDecl(name);
    }

    public XSIDCDefinition getIDCDefinition(String name) {
        return getIDConstraintDecl(name);
    }

    public StringList getDocumentLocations() {
        return new StringListImpl(this.fLocations);
    }

    public XSModel toXSModel() {
        return new XSModelImpl(new SchemaGrammar[]{this});
    }

    public XSModel toXSModel(XSGrammar[] grammars) {
        if (grammars == null || grammars.length == 0) {
            return toXSModel();
        }
        int i;
        boolean hasSelf = false;
        for (SchemaGrammar schemaGrammar : grammars) {
            if (schemaGrammar == this) {
                hasSelf = true;
                break;
            }
        }
        SchemaGrammar[] gs = new SchemaGrammar[(hasSelf ? len : len + 1)];
        for (i = 0; i < len; i++) {
            gs[i] = (SchemaGrammar) grammars[i];
        }
        if (!hasSelf) {
            gs[len] = this;
        }
        return new XSModelImpl(gs);
    }

    public XSObjectList getAnnotations() {
        if (this.fNumAnnotations == 0) {
            return XSObjectListImpl.EMPTY_LIST;
        }
        return new XSObjectListImpl(this.fAnnotations, this.fNumAnnotations);
    }

    public void addAnnotation(XSAnnotationImpl annotation) {
        if (annotation != null) {
            if (this.fAnnotations == null) {
                this.fAnnotations = new XSAnnotationImpl[2];
            } else if (this.fNumAnnotations == this.fAnnotations.length) {
                XSAnnotationImpl[] newArray = new XSAnnotationImpl[(this.fNumAnnotations << 1)];
                System.arraycopy(this.fAnnotations, 0, newArray, 0, this.fNumAnnotations);
                this.fAnnotations = newArray;
            }
            XSAnnotationImpl[] xSAnnotationImplArr = this.fAnnotations;
            int i = this.fNumAnnotations;
            this.fNumAnnotations = i + 1;
            xSAnnotationImplArr[i] = annotation;
        }
    }

    public void setImmutable(boolean isImmutable) {
        this.fIsImmutable = isImmutable;
    }

    public boolean isImmutable() {
        return this.fIsImmutable;
    }
}
