package mf.org.apache.xerces.impl.xs;

import mf.org.apache.xerces.impl.dv.XSSimpleType;
import mf.org.apache.xerces.impl.dv.xs.XSSimpleTypeDecl;
import mf.org.apache.xerces.impl.xs.models.CMBuilder;
import mf.org.apache.xerces.impl.xs.models.XSCMValidator;
import mf.org.apache.xerces.impl.xs.util.XSObjectListImpl;
import mf.org.apache.xerces.xs.XSAttributeUse;
import mf.org.apache.xerces.xs.XSComplexTypeDefinition;
import mf.org.apache.xerces.xs.XSNamespaceItem;
import mf.org.apache.xerces.xs.XSObjectList;
import mf.org.apache.xerces.xs.XSParticle;
import mf.org.apache.xerces.xs.XSSimpleTypeDefinition;
import mf.org.apache.xerces.xs.XSTypeDefinition;
import mf.org.apache.xerces.xs.XSWildcard;
import mf.org.w3c.dom.TypeInfo;

public class XSComplexTypeDecl implements XSComplexTypeDefinition, TypeInfo {
    private static final short CT_HAS_TYPE_ID = (short) 2;
    private static final short CT_IS_ABSTRACT = (short) 1;
    private static final short CT_IS_ANONYMOUS = (short) 4;
    static final int DERIVATION_ANY = 0;
    static final int DERIVATION_EXTENSION = 2;
    static final int DERIVATION_LIST = 8;
    static final int DERIVATION_RESTRICTION = 1;
    static final int DERIVATION_UNION = 4;
    XSObjectListImpl fAnnotations = null;
    XSAttributeGroupDecl fAttrGrp = null;
    XSTypeDefinition fBaseType = null;
    short fBlock = (short) 0;
    XSCMValidator fCMValidator = null;
    short fContentType = (short) 0;
    short fDerivedBy = (short) 2;
    short fFinal = (short) 0;
    short fMiscFlags = (short) 0;
    String fName = null;
    private XSNamespaceItem fNamespaceItem = null;
    XSParticleDecl fParticle = null;
    String fTargetNamespace = null;
    XSCMValidator fUPACMValidator = null;
    XSSimpleType fXSSimpleType = null;

    public void setValues(String name, String targetNamespace, XSTypeDefinition baseType, short derivedBy, short schemaFinal, short block, short contentType, boolean isAbstract, XSAttributeGroupDecl attrGrp, XSSimpleType simpleType, XSParticleDecl particle, XSObjectListImpl annotations) {
        this.fTargetNamespace = targetNamespace;
        this.fBaseType = baseType;
        this.fDerivedBy = derivedBy;
        this.fFinal = schemaFinal;
        this.fBlock = block;
        this.fContentType = contentType;
        if (isAbstract) {
            this.fMiscFlags = (short) (this.fMiscFlags | 1);
        }
        this.fAttrGrp = attrGrp;
        this.fXSSimpleType = simpleType;
        this.fParticle = particle;
        this.fAnnotations = annotations;
    }

    public void setName(String name) {
        this.fName = name;
    }

    public short getTypeCategory() {
        return (short) 15;
    }

    public String getTypeName() {
        return this.fName;
    }

    public short getFinalSet() {
        return this.fFinal;
    }

    public String getTargetNamespace() {
        return this.fTargetNamespace;
    }

    public boolean containsTypeID() {
        return (this.fMiscFlags & 2) != 0;
    }

    public void setIsAbstractType() {
        this.fMiscFlags = (short) (this.fMiscFlags | 1);
    }

    public void setContainsTypeID() {
        this.fMiscFlags = (short) (this.fMiscFlags | 2);
    }

    public void setIsAnonymous() {
        this.fMiscFlags = (short) (this.fMiscFlags | 4);
    }

    public XSCMValidator getContentModel(CMBuilder cmBuilder) {
        return getContentModel(cmBuilder, false);
    }

    public synchronized XSCMValidator getContentModel(CMBuilder cmBuilder, boolean forUPA) {
        XSCMValidator xSCMValidator;
        if (this.fCMValidator == null) {
            if (forUPA) {
                if (this.fUPACMValidator == null) {
                    this.fUPACMValidator = cmBuilder.getContentModel(this, true);
                    if (!(this.fUPACMValidator == null || this.fUPACMValidator.isCompactedForUPA())) {
                        this.fCMValidator = this.fUPACMValidator;
                    }
                }
                xSCMValidator = this.fUPACMValidator;
            } else {
                this.fCMValidator = cmBuilder.getContentModel(this, false);
            }
        }
        xSCMValidator = this.fCMValidator;
        return xSCMValidator;
    }

    public XSAttributeGroupDecl getAttrGrp() {
        return this.fAttrGrp;
    }

    public String toString() {
        StringBuffer str = new StringBuffer();
        appendTypeInfo(str);
        return str.toString();
    }

    void appendTypeInfo(StringBuffer str) {
        String[] contentType = new String[]{"EMPTY", "SIMPLE", "ELEMENT", "MIXED"};
        String[] derivedBy = new String[]{"EMPTY", "EXTENSION", "RESTRICTION"};
        str.append("Complex type name='").append(this.fTargetNamespace).append(',').append(getTypeName()).append("', ");
        if (this.fBaseType != null) {
            str.append(" base type name='").append(this.fBaseType.getName()).append("', ");
        }
        str.append(" content type='").append(contentType[this.fContentType]).append("', ");
        str.append(" isAbstract='").append(getAbstract()).append("', ");
        str.append(" hasTypeId='").append(containsTypeID()).append("', ");
        str.append(" final='").append(this.fFinal).append("', ");
        str.append(" block='").append(this.fBlock).append("', ");
        if (this.fParticle != null) {
            str.append(" particle='").append(this.fParticle.toString()).append("', ");
        }
        str.append(" derivedBy='").append(derivedBy[this.fDerivedBy]).append("'. ");
    }

    public boolean derivedFromType(XSTypeDefinition ancestor, short derivationMethod) {
        if (ancestor == null) {
            return false;
        }
        if (ancestor == SchemaGrammar.fAnyType) {
            return true;
        }
        XSTypeDefinition type = this;
        while (type != ancestor && type != SchemaGrammar.fAnySimpleType && type != SchemaGrammar.fAnyType) {
            type = type.getBaseType();
        }
        if (type == ancestor) {
            return true;
        }
        return false;
    }

    public boolean derivedFrom(String ancestorNS, String ancestorName, short derivationMethod) {
        if (ancestorName == null) {
            return false;
        }
        if (ancestorNS != null && ancestorNS.equals(SchemaSymbols.URI_SCHEMAFORSCHEMA) && ancestorName.equals(SchemaSymbols.ATTVAL_ANYTYPE)) {
            return true;
        }
        XSTypeDefinition type = this;
        while (true) {
            if (!((ancestorName.equals(type.getName()) && ((ancestorNS == null && type.getNamespace() == null) || (ancestorNS != null && ancestorNS.equals(type.getNamespace())))) || type == SchemaGrammar.fAnySimpleType || type == SchemaGrammar.fAnyType)) {
                type = type.getBaseType();
            }
        }
        if (type == SchemaGrammar.fAnySimpleType || type == SchemaGrammar.fAnyType) {
            return false;
        }
        return true;
    }

    public boolean isDOMDerivedFrom(String ancestorNS, String ancestorName, int derivationMethod) {
        if (ancestorName == null) {
            return false;
        }
        if (ancestorNS != null && ancestorNS.equals(SchemaSymbols.URI_SCHEMAFORSCHEMA) && ancestorName.equals(SchemaSymbols.ATTVAL_ANYTYPE) && derivationMethod == 1 && derivationMethod == 2) {
            return true;
        }
        if ((derivationMethod & 1) != 0 && isDerivedByRestriction(ancestorNS, ancestorName, derivationMethod, this)) {
            return true;
        }
        if ((derivationMethod & 2) != 0 && isDerivedByExtension(ancestorNS, ancestorName, derivationMethod, this)) {
            return true;
        }
        if (!((derivationMethod & 8) == 0 && (derivationMethod & 4) == 0) && (derivationMethod & 1) == 0 && (derivationMethod & 2) == 0) {
            if (ancestorNS.equals(SchemaSymbols.URI_SCHEMAFORSCHEMA) && ancestorName.equals(SchemaSymbols.ATTVAL_ANYTYPE)) {
                ancestorName = SchemaSymbols.ATTVAL_ANYSIMPLETYPE;
            }
            if (!(this.fName.equals(SchemaSymbols.ATTVAL_ANYTYPE) && this.fTargetNamespace.equals(SchemaSymbols.URI_SCHEMAFORSCHEMA))) {
                if (this.fBaseType != null && (this.fBaseType instanceof XSSimpleTypeDecl)) {
                    return ((XSSimpleTypeDecl) this.fBaseType).isDOMDerivedFrom(ancestorNS, ancestorName, derivationMethod);
                }
                if (this.fBaseType != null && (this.fBaseType instanceof XSComplexTypeDecl)) {
                    return ((XSComplexTypeDecl) this.fBaseType).isDOMDerivedFrom(ancestorNS, ancestorName, derivationMethod);
                }
            }
        }
        if ((derivationMethod & 2) == 0 && (derivationMethod & 1) == 0 && (derivationMethod & 8) == 0 && (derivationMethod & 4) == 0) {
            return isDerivedByAny(ancestorNS, ancestorName, derivationMethod, this);
        }
        return false;
    }

    private boolean isDerivedByAny(String ancestorNS, String ancestorName, int derivationMethod, XSTypeDefinition type) {
        XSTypeDefinition oldType = null;
        boolean derivedFrom = false;
        while (type != null && type != oldType) {
            if (ancestorName.equals(type.getName()) && ((ancestorNS == null && type.getNamespace() == null) || (ancestorNS != null && ancestorNS.equals(type.getNamespace())))) {
                derivedFrom = true;
                break;
            } else if (isDerivedByRestriction(ancestorNS, ancestorName, derivationMethod, type) || !isDerivedByExtension(ancestorNS, ancestorName, derivationMethod, type)) {
                return true;
            } else {
                oldType = type;
                type = type.getBaseType();
            }
        }
        return derivedFrom;
    }

    private boolean isDerivedByRestriction(String ancestorNS, String ancestorName, int derivationMethod, XSTypeDefinition type) {
        XSTypeDefinition oldType = null;
        while (type != null && type != oldType) {
            if (ancestorNS != null && ancestorNS.equals(SchemaSymbols.URI_SCHEMAFORSCHEMA) && ancestorName.equals(SchemaSymbols.ATTVAL_ANYSIMPLETYPE)) {
                return false;
            }
            if ((ancestorName.equals(type.getName()) && ancestorNS != null && ancestorNS.equals(type.getNamespace())) || (type.getNamespace() == null && ancestorNS == null)) {
                return true;
            }
            if (type instanceof XSSimpleTypeDecl) {
                if (ancestorNS.equals(SchemaSymbols.URI_SCHEMAFORSCHEMA) && ancestorName.equals(SchemaSymbols.ATTVAL_ANYTYPE)) {
                    ancestorName = SchemaSymbols.ATTVAL_ANYSIMPLETYPE;
                }
                return ((XSSimpleTypeDecl) type).isDOMDerivedFrom(ancestorNS, ancestorName, derivationMethod);
            } else if (((XSComplexTypeDecl) type).getDerivationMethod() != (short) 2) {
                return false;
            } else {
                oldType = type;
                type = type.getBaseType();
            }
        }
        return false;
    }

    private boolean isDerivedByExtension(String ancestorNS, String ancestorName, int derivationMethod, XSTypeDefinition type) {
        boolean extension = false;
        XSTypeDefinition oldType = null;
        while (type != null && type != oldType && (ancestorNS == null || !ancestorNS.equals(SchemaSymbols.URI_SCHEMAFORSCHEMA) || !ancestorName.equals(SchemaSymbols.ATTVAL_ANYSIMPLETYPE) || !SchemaSymbols.URI_SCHEMAFORSCHEMA.equals(type.getNamespace()) || !SchemaSymbols.ATTVAL_ANYTYPE.equals(type.getName()))) {
            if (ancestorName.equals(type.getName())) {
                if (ancestorNS == null && type.getNamespace() == null) {
                    return extension;
                }
                if (ancestorNS != null && ancestorNS.equals(type.getNamespace())) {
                    return extension;
                }
            }
            if (type instanceof XSSimpleTypeDecl) {
                if (ancestorNS.equals(SchemaSymbols.URI_SCHEMAFORSCHEMA) && ancestorName.equals(SchemaSymbols.ATTVAL_ANYTYPE)) {
                    ancestorName = SchemaSymbols.ATTVAL_ANYSIMPLETYPE;
                }
                if ((derivationMethod & 2) != 0) {
                    return extension & ((XSSimpleTypeDecl) type).isDOMDerivedFrom(ancestorNS, ancestorName, derivationMethod & 1);
                }
                return extension & ((XSSimpleTypeDecl) type).isDOMDerivedFrom(ancestorNS, ancestorName, derivationMethod);
            }
            if (((XSComplexTypeDecl) type).getDerivationMethod() == (short) 1) {
                extension |= 1;
            }
            oldType = type;
            type = type.getBaseType();
        }
        return false;
    }

    public void reset() {
        this.fName = null;
        this.fTargetNamespace = null;
        this.fBaseType = null;
        this.fDerivedBy = (short) 2;
        this.fFinal = (short) 0;
        this.fBlock = (short) 0;
        this.fMiscFlags = (short) 0;
        this.fAttrGrp.reset();
        this.fContentType = (short) 0;
        this.fXSSimpleType = null;
        this.fParticle = null;
        this.fCMValidator = null;
        this.fUPACMValidator = null;
        if (this.fAnnotations != null) {
            this.fAnnotations.clearXSObjectList();
        }
        this.fAnnotations = null;
    }

    public short getType() {
        return (short) 3;
    }

    public String getName() {
        return getAnonymous() ? null : this.fName;
    }

    public boolean getAnonymous() {
        return (this.fMiscFlags & 4) != 0;
    }

    public String getNamespace() {
        return this.fTargetNamespace;
    }

    public XSTypeDefinition getBaseType() {
        return this.fBaseType;
    }

    public short getDerivationMethod() {
        return this.fDerivedBy;
    }

    public boolean isFinal(short derivation) {
        return (this.fFinal & derivation) != 0;
    }

    public short getFinal() {
        return this.fFinal;
    }

    public boolean getAbstract() {
        return (this.fMiscFlags & 1) != 0;
    }

    public XSObjectList getAttributeUses() {
        return this.fAttrGrp.getAttributeUses();
    }

    public XSWildcard getAttributeWildcard() {
        return this.fAttrGrp.getAttributeWildcard();
    }

    public short getContentType() {
        return this.fContentType;
    }

    public XSSimpleTypeDefinition getSimpleType() {
        return this.fXSSimpleType;
    }

    public XSParticle getParticle() {
        return this.fParticle;
    }

    public boolean isProhibitedSubstitution(short prohibited) {
        return (this.fBlock & prohibited) != 0;
    }

    public short getProhibitedSubstitutions() {
        return this.fBlock;
    }

    public XSObjectList getAnnotations() {
        return this.fAnnotations != null ? this.fAnnotations : XSObjectListImpl.EMPTY_LIST;
    }

    public XSNamespaceItem getNamespaceItem() {
        return this.fNamespaceItem;
    }

    void setNamespaceItem(XSNamespaceItem namespaceItem) {
        this.fNamespaceItem = namespaceItem;
    }

    public XSAttributeUse getAttributeUse(String namespace, String name) {
        return this.fAttrGrp.getAttributeUse(namespace, name);
    }

    public String getTypeNamespace() {
        return getNamespace();
    }

    public boolean isDerivedFrom(String typeNamespaceArg, String typeNameArg, int derivationMethod) {
        return isDOMDerivedFrom(typeNamespaceArg, typeNameArg, derivationMethod);
    }
}
