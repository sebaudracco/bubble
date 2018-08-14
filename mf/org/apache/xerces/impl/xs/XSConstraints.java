package mf.org.apache.xerces.impl.xs;

import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;
import mf.org.apache.xerces.impl.XMLErrorReporter;
import mf.org.apache.xerces.impl.dv.InvalidDatatypeValueException;
import mf.org.apache.xerces.impl.dv.ValidatedInfo;
import mf.org.apache.xerces.impl.dv.ValidationContext;
import mf.org.apache.xerces.impl.dv.XSSimpleType;
import mf.org.apache.xerces.impl.xs.models.CMBuilder;
import mf.org.apache.xerces.impl.xs.models.XSCMValidator;
import mf.org.apache.xerces.impl.xs.util.SimpleLocator;
import mf.org.apache.xerces.impl.xs.util.XSObjectListImpl;
import mf.org.apache.xerces.util.SymbolHash;
import mf.org.apache.xerces.xni.XMLLocator;
import mf.org.apache.xerces.xs.XSObjectList;
import mf.org.apache.xerces.xs.XSTypeDefinition;

public class XSConstraints {
    private static final Comparator ELEMENT_PARTICLE_COMPARATOR = new C46421();
    static final int OCCURRENCE_UNKNOWN = -2;
    static final XSSimpleType STRING_TYPE = ((XSSimpleType) SchemaGrammar.SG_SchemaNS.getGlobalTypeDecl(SchemaSymbols.ATTVAL_STRING));
    private static XSParticleDecl fEmptyParticle = null;

    class C46421 implements Comparator {
        C46421() {
        }

        public int compare(Object o1, Object o2) {
            XSElementDecl decl1 = ((XSParticleDecl) o1).fValue;
            XSElementDecl decl2 = ((XSParticleDecl) o2).fValue;
            String namespace1 = decl1.getNamespace();
            String namespace2 = decl2.getNamespace();
            String name1 = decl1.getName();
            String name2 = decl2.getName();
            int namespaceComparison = 0;
            if (!(namespace1 == namespace2)) {
                if (namespace1 == null) {
                    namespaceComparison = -1;
                } else if (namespace2 != null) {
                    namespaceComparison = namespace1.compareTo(namespace2);
                } else {
                    namespaceComparison = 1;
                }
            }
            return namespaceComparison != 0 ? namespaceComparison : name1.compareTo(name2);
        }
    }

    public static XSParticleDecl getEmptySequence() {
        if (fEmptyParticle == null) {
            XSModelGroupImpl group = new XSModelGroupImpl();
            group.fCompositor = (short) 102;
            group.fParticleCount = 0;
            group.fParticles = null;
            group.fAnnotations = XSObjectListImpl.EMPTY_LIST;
            XSParticleDecl particle = new XSParticleDecl();
            particle.fType = (short) 3;
            particle.fValue = group;
            particle.fAnnotations = XSObjectListImpl.EMPTY_LIST;
            fEmptyParticle = particle;
        }
        return fEmptyParticle;
    }

    public static boolean checkTypeDerivationOk(XSTypeDefinition derived, XSTypeDefinition base, short block) {
        if (derived == SchemaGrammar.fAnyType) {
            if (derived == base) {
                return true;
            }
            return false;
        } else if (derived == SchemaGrammar.fAnySimpleType) {
            if (base == SchemaGrammar.fAnyType || base == SchemaGrammar.fAnySimpleType) {
                return true;
            }
            return false;
        } else if (derived.getTypeCategory() != (short) 16) {
            return checkComplexDerivation((XSComplexTypeDecl) derived, base, block);
        } else {
            if (base.getTypeCategory() == (short) 15) {
                if (base != SchemaGrammar.fAnyType) {
                    return false;
                }
                base = SchemaGrammar.fAnySimpleType;
            }
            return checkSimpleDerivation((XSSimpleType) derived, (XSSimpleType) base, block);
        }
    }

    public static boolean checkSimpleDerivationOk(XSSimpleType derived, XSTypeDefinition base, short block) {
        if (derived != SchemaGrammar.fAnySimpleType) {
            if (base.getTypeCategory() == (short) 15) {
                if (base != SchemaGrammar.fAnyType) {
                    return false;
                }
                base = SchemaGrammar.fAnySimpleType;
            }
            return checkSimpleDerivation(derived, (XSSimpleType) base, block);
        } else if (base == SchemaGrammar.fAnyType || base == SchemaGrammar.fAnySimpleType) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkComplexDerivationOk(XSComplexTypeDecl derived, XSTypeDefinition base, short block) {
        if (derived == SchemaGrammar.fAnyType) {
            return derived == base;
        } else {
            return checkComplexDerivation(derived, base, block);
        }
    }

    private static boolean checkSimpleDerivation(XSSimpleType derived, XSSimpleType base, short block) {
        if (derived == base) {
            return true;
        }
        if ((block & 2) != 0 || (derived.getBaseType().getFinal() & 2) != 0) {
            return false;
        }
        XSSimpleType directBase = (XSSimpleType) derived.getBaseType();
        if (directBase == base) {
            return true;
        }
        if (directBase != SchemaGrammar.fAnySimpleType && checkSimpleDerivation(directBase, base, block)) {
            return true;
        }
        if ((derived.getVariety() == (short) 2 || derived.getVariety() == (short) 3) && base == SchemaGrammar.fAnySimpleType) {
            return true;
        }
        if (base.getVariety() == (short) 3) {
            XSObjectList subUnionMemberDV = base.getMemberTypes();
            int subUnionSize = subUnionMemberDV.getLength();
            for (int i = 0; i < subUnionSize; i++) {
                if (checkSimpleDerivation(derived, (XSSimpleType) subUnionMemberDV.item(i), block)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean checkComplexDerivation(XSComplexTypeDecl derived, XSTypeDefinition base, short block) {
        if (derived == base) {
            return true;
        }
        if ((derived.fDerivedBy & block) != 0) {
            return false;
        }
        XSTypeDefinition directBase = derived.fBaseType;
        if (directBase == base) {
            return true;
        }
        if (directBase == SchemaGrammar.fAnyType || directBase == SchemaGrammar.fAnySimpleType) {
            return false;
        }
        if (directBase.getTypeCategory() == (short) 15) {
            return checkComplexDerivation((XSComplexTypeDecl) directBase, base, block);
        }
        if (directBase.getTypeCategory() != (short) 16) {
            return false;
        }
        if (base.getTypeCategory() == (short) 15) {
            if (base != SchemaGrammar.fAnyType) {
                return false;
            }
            base = SchemaGrammar.fAnySimpleType;
        }
        return checkSimpleDerivation((XSSimpleType) directBase, (XSSimpleType) base, block);
    }

    public static Object ElementDefaultValidImmediate(XSTypeDefinition type, String value, ValidationContext context, ValidatedInfo vinfo) {
        XSSimpleType dv = null;
        if (type.getTypeCategory() == (short) 16) {
            dv = (XSSimpleType) type;
        } else {
            XSComplexTypeDecl ctype = (XSComplexTypeDecl) type;
            if (ctype.fContentType == (short) 1) {
                dv = ctype.fXSSimpleType;
            } else if (ctype.fContentType != (short) 3) {
                return null;
            } else {
                if (!((XSParticleDecl) ctype.getParticle()).emptiable()) {
                    return null;
                }
            }
        }
        if (dv == null) {
            dv = STRING_TYPE;
        }
        try {
            Object actualValue = dv.validate(value, context, vinfo);
            if (vinfo != null) {
                return dv.validate(vinfo.stringValue(), context, vinfo);
            }
            return actualValue;
        } catch (InvalidDatatypeValueException e) {
            return null;
        }
    }

    static void reportSchemaError(XMLErrorReporter errorReporter, SimpleLocator loc, String key, Object[] args) {
        if (loc != null) {
            errorReporter.reportError((XMLLocator) loc, XSMessageFormatter.SCHEMA_DOMAIN, key, args, (short) 1);
            return;
        }
        errorReporter.reportError(XSMessageFormatter.SCHEMA_DOMAIN, key, args, (short) 1);
    }

    public static void fullSchemaChecking(XSGrammarBucket grammarBucket, SubstitutionGroupHandler SGHandler, CMBuilder cmBuilder, XMLErrorReporter errorReporter) {
        int i;
        SchemaGrammar[] grammars = grammarBucket.getGrammars();
        for (i = grammars.length - 1; i >= 0; i--) {
            SGHandler.addSubstitutionGroup(grammars[i].getSubstitutionGroups());
        }
        XSParticleDecl fakeDerived = new XSParticleDecl();
        XSParticleDecl fakeBase = new XSParticleDecl();
        fakeDerived.fType = (short) 3;
        fakeBase.fType = (short) 3;
        for (int g = grammars.length - 1; g >= 0; g--) {
            XSGroupDecl[] redefinedGroups = grammars[g].getRedefinedGroupDecls();
            SimpleLocator[] rgLocators = grammars[g].getRGLocators();
            i = 0;
            while (i < redefinedGroups.length) {
                int i2 = i + 1;
                XSModelGroupImpl derivedMG = redefinedGroups[i].fModelGroup;
                i = i2 + 1;
                XSModelGroupImpl baseMG = redefinedGroups[i2].fModelGroup;
                fakeDerived.fValue = derivedMG;
                fakeBase.fValue = baseMG;
                if (baseMG == null) {
                    if (derivedMG != null) {
                        reportSchemaError(errorReporter, rgLocators[(i / 2) - 1], "src-redefine.6.2.2", new Object[]{derivedGrp.fName, "rcase-Recurse.2"});
                    }
                } else if (derivedMG != null) {
                    try {
                        particleValidRestriction(fakeDerived, SGHandler, fakeBase, SGHandler);
                    } catch (XMLSchemaException e) {
                        String key = e.getKey();
                        reportSchemaError(errorReporter, rgLocators[(i / 2) - 1], key, e.getArgs());
                        reportSchemaError(errorReporter, rgLocators[(i / 2) - 1], "src-redefine.6.2.2", new Object[]{derivedGrp.fName, key});
                    }
                } else if (!fakeBase.emptiable()) {
                    reportSchemaError(errorReporter, rgLocators[(i / 2) - 1], "src-redefine.6.2.2", new Object[]{derivedGrp.fName, "rcase-Recurse.2"});
                }
            }
        }
        SymbolHash elemTable = new SymbolHash();
        for (i = grammars.length - 1; i >= 0; i--) {
            int keepType = 0;
            boolean fullChecked = grammars[i].fFullChecked;
            XSComplexTypeDecl[] types = grammars[i].getUncheckedComplexTypeDecls();
            SimpleLocator[] ctLocators = grammars[i].getUncheckedCTLocators();
            int j = 0;
            while (j < types.length) {
                if (!(fullChecked || types[j].fParticle == null)) {
                    elemTable.clear();
                    try {
                        checkElementDeclsConsistent(types[j], types[j].fParticle, elemTable, SGHandler);
                    } catch (XMLSchemaException e2) {
                        reportSchemaError(errorReporter, ctLocators[j], e2.getKey(), e2.getArgs());
                    }
                }
                if (types[j].fBaseType != null && types[j].fBaseType != SchemaGrammar.fAnyType && types[j].fDerivedBy == (short) 2 && (types[j].fBaseType instanceof XSComplexTypeDecl)) {
                    XSParticleDecl derivedParticle = types[j].fParticle;
                    XSParticleDecl baseParticle = ((XSComplexTypeDecl) types[j].fBaseType).fParticle;
                    if (derivedParticle == null) {
                        if (!(baseParticle == null || baseParticle.emptiable())) {
                            reportSchemaError(errorReporter, ctLocators[j], "derivation-ok-restriction.5.3.2", new Object[]{types[j].fName, types[j].fBaseType.getName()});
                        }
                    } else if (baseParticle != null) {
                        try {
                            particleValidRestriction(types[j].fParticle, SGHandler, ((XSComplexTypeDecl) types[j].fBaseType).fParticle, SGHandler);
                        } catch (XMLSchemaException e22) {
                            reportSchemaError(errorReporter, ctLocators[j], e22.getKey(), e22.getArgs());
                            reportSchemaError(errorReporter, ctLocators[j], "derivation-ok-restriction.5.4.2", new Object[]{types[j].fName});
                        }
                    } else {
                        reportSchemaError(errorReporter, ctLocators[j], "derivation-ok-restriction.5.4.2", new Object[]{types[j].fName});
                    }
                }
                XSCMValidator cm = types[j].getContentModel(cmBuilder, true);
                boolean further = false;
                if (cm != null) {
                    try {
                        further = cm.checkUniqueParticleAttribution(SGHandler);
                    } catch (XMLSchemaException e222) {
                        reportSchemaError(errorReporter, ctLocators[j], e222.getKey(), e222.getArgs());
                    }
                }
                if (!fullChecked && further) {
                    int keepType2 = keepType + 1;
                    types[keepType] = types[j];
                    keepType = keepType2;
                }
                j++;
            }
            if (!fullChecked) {
                grammars[i].setUncheckedTypeNum(keepType);
                grammars[i].fFullChecked = true;
            }
        }
    }

    public static void checkElementDeclsConsistent(XSComplexTypeDecl type, XSParticleDecl particle, SymbolHash elemDeclHash, SubstitutionGroupHandler sgHandler) throws XMLSchemaException {
        int pType = particle.fType;
        if (pType != 2) {
            int i;
            if (pType == 1) {
                XSElementDecl elem = particle.fValue;
                findElemInTable(type, elem, elemDeclHash);
                if (elem.fScope == (short) 1) {
                    XSElementDecl[] subGroup = sgHandler.getSubstitutionGroup(elem);
                    for (XSElementDecl findElemInTable : subGroup) {
                        findElemInTable(type, findElemInTable, elemDeclHash);
                    }
                    return;
                }
                return;
            }
            XSModelGroupImpl group = particle.fValue;
            for (i = 0; i < group.fParticleCount; i++) {
                checkElementDeclsConsistent(type, group.fParticles[i], elemDeclHash, sgHandler);
            }
        }
    }

    public static void findElemInTable(XSComplexTypeDecl type, XSElementDecl elem, SymbolHash elemDeclHash) throws XMLSchemaException {
        String name = elem.fName + "," + elem.fTargetNamespace;
        XSElementDecl existingElem = (XSElementDecl) elemDeclHash.get(name);
        if (existingElem == null) {
            elemDeclHash.put(name, elem);
        } else if (elem != existingElem && elem.fType != existingElem.fType) {
            throw new XMLSchemaException("cos-element-consistent", new Object[]{type.fName, elem.fName});
        }
    }

    private static boolean particleValidRestriction(XSParticleDecl dParticle, SubstitutionGroupHandler dSGHandler, XSParticleDecl bParticle, SubstitutionGroupHandler bSGHandler) throws XMLSchemaException {
        return particleValidRestriction(dParticle, dSGHandler, bParticle, bSGHandler, true);
    }

    private static boolean particleValidRestriction(XSParticleDecl dParticle, SubstitutionGroupHandler dSGHandler, XSParticleDecl bParticle, SubstitutionGroupHandler bSGHandler, boolean checkWCOccurrence) throws XMLSchemaException {
        Vector dChildren = null;
        Vector bChildren = null;
        boolean bExpansionHappened = false;
        if (dParticle.isEmpty() && !bParticle.emptiable()) {
            throw new XMLSchemaException("cos-particle-restrict.a", null);
        } else if (dParticle.isEmpty() || !bParticle.isEmpty()) {
            int dMinEffectiveTotalRange;
            int dMaxEffectiveTotalRange;
            int dMaxEffectiveTotalRange2;
            int dMinEffectiveTotalRange2;
            Vector dChildren2;
            short bType;
            XSParticleDecl btmp;
            int bMinOccurs;
            int bMaxOccurs;
            XSElementDecl bElement;
            XSElementDecl[] bsubGroup;
            int min1;
            int max1;
            short dType = dParticle.fType;
            if (dType == (short) 3) {
                dType = ((XSModelGroupImpl) dParticle.fValue).fCompositor;
                XSParticleDecl dtmp = getNonUnaryGroup(dParticle);
                if (dtmp != dParticle) {
                    dParticle = dtmp;
                    dType = dParticle.fType;
                    if (dType == (short) 3) {
                        dType = ((XSModelGroupImpl) dParticle.fValue).fCompositor;
                    }
                }
                dChildren = removePointlessChildren(dParticle);
            }
            int dMinOccurs = dParticle.fMinOccurs;
            int dMaxOccurs = dParticle.fMaxOccurs;
            if (dSGHandler != null && dType == (short) 1) {
                XSElementDecl dElement = (XSElementDecl) dParticle.fValue;
                if (dElement.fScope == (short) 1) {
                    XSElementDecl[] subGroup = dSGHandler.getSubstitutionGroup(dElement);
                    if (subGroup.length > 0) {
                        dType = (short) 101;
                        dMinEffectiveTotalRange = dMinOccurs;
                        dMaxEffectiveTotalRange = dMaxOccurs;
                        dChildren = new Vector(subGroup.length + 1);
                        for (XSElementDecl addElementToParticleVector : subGroup) {
                            addElementToParticleVector(dChildren, addElementToParticleVector);
                        }
                        addElementToParticleVector(dChildren, dElement);
                        Collections.sort(dChildren, ELEMENT_PARTICLE_COMPARATOR);
                        dSGHandler = null;
                        dMaxEffectiveTotalRange2 = dMaxEffectiveTotalRange;
                        dMinEffectiveTotalRange2 = dMinEffectiveTotalRange;
                        dChildren2 = dChildren;
                        bType = bParticle.fType;
                        if (bType == (short) 3) {
                            bType = ((XSModelGroupImpl) bParticle.fValue).fCompositor;
                            btmp = getNonUnaryGroup(bParticle);
                            if (btmp != bParticle) {
                                bParticle = btmp;
                                bType = bParticle.fType;
                                if (bType == (short) 3) {
                                    bType = ((XSModelGroupImpl) bParticle.fValue).fCompositor;
                                }
                            }
                            bChildren = removePointlessChildren(bParticle);
                        }
                        bMinOccurs = bParticle.fMinOccurs;
                        bMaxOccurs = bParticle.fMaxOccurs;
                        if (bSGHandler != null && bType == (short) 1) {
                            bElement = (XSElementDecl) bParticle.fValue;
                            if (bElement.fScope == (short) 1) {
                                bsubGroup = bSGHandler.getSubstitutionGroup(bElement);
                                if (bsubGroup.length > 0) {
                                    bType = (short) 101;
                                    bChildren = new Vector(bsubGroup.length + 1);
                                    for (XSElementDecl addElementToParticleVector2 : bsubGroup) {
                                        addElementToParticleVector(bChildren, addElementToParticleVector2);
                                    }
                                    addElementToParticleVector(bChildren, bElement);
                                    Collections.sort(bChildren, ELEMENT_PARTICLE_COMPARATOR);
                                    bSGHandler = null;
                                    bExpansionHappened = true;
                                }
                            }
                        }
                        switch (dType) {
                            case (short) 1:
                                switch (bType) {
                                    case (short) 1:
                                        checkNameAndTypeOK((XSElementDecl) dParticle.fValue, dMinOccurs, dMaxOccurs, (XSElementDecl) bParticle.fValue, bMinOccurs, bMaxOccurs);
                                        dMaxEffectiveTotalRange = dMaxEffectiveTotalRange2;
                                        dMinEffectiveTotalRange = dMinEffectiveTotalRange2;
                                        dChildren = dChildren2;
                                        break;
                                    case (short) 2:
                                        checkNSCompat((XSElementDecl) dParticle.fValue, dMinOccurs, dMaxOccurs, (XSWildcardDecl) bParticle.fValue, bMinOccurs, bMaxOccurs, checkWCOccurrence);
                                        dMaxEffectiveTotalRange = dMaxEffectiveTotalRange2;
                                        dMinEffectiveTotalRange = dMinEffectiveTotalRange2;
                                        dChildren = dChildren2;
                                        break;
                                    case (short) 101:
                                        dChildren = new Vector();
                                        dChildren.addElement(dParticle);
                                        checkRecurseLax(dChildren, 1, 1, dSGHandler, bChildren, bMinOccurs, bMaxOccurs, bSGHandler);
                                        dMaxEffectiveTotalRange = dMaxEffectiveTotalRange2;
                                        dMinEffectiveTotalRange = dMinEffectiveTotalRange2;
                                        break;
                                    case (short) 102:
                                    case (short) 103:
                                        dChildren = new Vector();
                                        dChildren.addElement(dParticle);
                                        checkRecurse(dChildren, 1, 1, dSGHandler, bChildren, bMinOccurs, bMaxOccurs, bSGHandler);
                                        dMaxEffectiveTotalRange = dMaxEffectiveTotalRange2;
                                        dMinEffectiveTotalRange = dMinEffectiveTotalRange2;
                                        break;
                                    default:
                                        throw new XMLSchemaException("Internal-Error", new Object[]{"in particleValidRestriction"});
                                }
                            case (short) 2:
                                switch (bType) {
                                    case (short) 1:
                                    case (short) 101:
                                    case (short) 102:
                                    case (short) 103:
                                        throw new XMLSchemaException("cos-particle-restrict.2", new Object[]{"any:choice,sequence,all,elt"});
                                    case (short) 2:
                                        checkNSSubset((XSWildcardDecl) dParticle.fValue, dMinOccurs, dMaxOccurs, (XSWildcardDecl) bParticle.fValue, bMinOccurs, bMaxOccurs);
                                        dMaxEffectiveTotalRange = dMaxEffectiveTotalRange2;
                                        dMinEffectiveTotalRange = dMinEffectiveTotalRange2;
                                        dChildren = dChildren2;
                                        break;
                                    default:
                                        throw new XMLSchemaException("Internal-Error", new Object[]{"in particleValidRestriction"});
                                }
                            case (short) 101:
                                switch (bType) {
                                    case (short) 1:
                                    case (short) 102:
                                    case (short) 103:
                                        throw new XMLSchemaException("cos-particle-restrict.2", new Object[]{"choice:all,sequence,elt"});
                                    case (short) 2:
                                        if (dMinEffectiveTotalRange2 != -2) {
                                            dMinEffectiveTotalRange = dParticle.minEffectiveTotalRange();
                                        } else {
                                            dMinEffectiveTotalRange = dMinEffectiveTotalRange2;
                                        }
                                        if (dMaxEffectiveTotalRange2 != -2) {
                                            dMaxEffectiveTotalRange = dParticle.maxEffectiveTotalRange();
                                        } else {
                                            dMaxEffectiveTotalRange = dMaxEffectiveTotalRange2;
                                        }
                                        checkNSRecurseCheckCardinality(dChildren2, dMinEffectiveTotalRange, dMaxEffectiveTotalRange, dSGHandler, bParticle, bMinOccurs, bMaxOccurs, checkWCOccurrence);
                                        dChildren = dChildren2;
                                        break;
                                    case (short) 101:
                                        checkRecurseLax(dChildren2, dMinOccurs, dMaxOccurs, dSGHandler, bChildren, bMinOccurs, bMaxOccurs, bSGHandler);
                                        dMaxEffectiveTotalRange = dMaxEffectiveTotalRange2;
                                        dMinEffectiveTotalRange = dMinEffectiveTotalRange2;
                                        dChildren = dChildren2;
                                        break;
                                    default:
                                        throw new XMLSchemaException("Internal-Error", new Object[]{"in particleValidRestriction"});
                                }
                            case (short) 102:
                                switch (bType) {
                                    case (short) 1:
                                        throw new XMLSchemaException("cos-particle-restrict.2", new Object[]{"seq:elt"});
                                    case (short) 2:
                                        if (dMinEffectiveTotalRange2 != -2) {
                                            dMinEffectiveTotalRange = dParticle.minEffectiveTotalRange();
                                        } else {
                                            dMinEffectiveTotalRange = dMinEffectiveTotalRange2;
                                        }
                                        if (dMaxEffectiveTotalRange2 != -2) {
                                            dMaxEffectiveTotalRange = dParticle.maxEffectiveTotalRange();
                                        } else {
                                            dMaxEffectiveTotalRange = dMaxEffectiveTotalRange2;
                                        }
                                        checkNSRecurseCheckCardinality(dChildren2, dMinEffectiveTotalRange, dMaxEffectiveTotalRange, dSGHandler, bParticle, bMinOccurs, bMaxOccurs, checkWCOccurrence);
                                        dChildren = dChildren2;
                                        break;
                                    case (short) 101:
                                        min1 = dMinOccurs * dChildren2.size();
                                        if (dMaxOccurs != -1) {
                                            max1 = dMaxOccurs;
                                        } else {
                                            max1 = dMaxOccurs * dChildren2.size();
                                        }
                                        checkMapAndSum(dChildren2, min1, max1, dSGHandler, bChildren, bMinOccurs, bMaxOccurs, bSGHandler);
                                        dMaxEffectiveTotalRange = dMaxEffectiveTotalRange2;
                                        dMinEffectiveTotalRange = dMinEffectiveTotalRange2;
                                        dChildren = dChildren2;
                                        break;
                                    case (short) 102:
                                        checkRecurse(dChildren2, dMinOccurs, dMaxOccurs, dSGHandler, bChildren, bMinOccurs, bMaxOccurs, bSGHandler);
                                        dMaxEffectiveTotalRange = dMaxEffectiveTotalRange2;
                                        dMinEffectiveTotalRange = dMinEffectiveTotalRange2;
                                        dChildren = dChildren2;
                                        break;
                                    case (short) 103:
                                        checkRecurseUnordered(dChildren2, dMinOccurs, dMaxOccurs, dSGHandler, bChildren, bMinOccurs, bMaxOccurs, bSGHandler);
                                        dMaxEffectiveTotalRange = dMaxEffectiveTotalRange2;
                                        dMinEffectiveTotalRange = dMinEffectiveTotalRange2;
                                        dChildren = dChildren2;
                                        break;
                                    default:
                                        throw new XMLSchemaException("Internal-Error", new Object[]{"in particleValidRestriction"});
                                }
                            case (short) 103:
                                switch (bType) {
                                    case (short) 1:
                                    case (short) 101:
                                    case (short) 102:
                                        throw new XMLSchemaException("cos-particle-restrict.2", new Object[]{"all:choice,sequence,elt"});
                                    case (short) 2:
                                        if (dMinEffectiveTotalRange2 != -2) {
                                            dMinEffectiveTotalRange = dParticle.minEffectiveTotalRange();
                                        } else {
                                            dMinEffectiveTotalRange = dMinEffectiveTotalRange2;
                                        }
                                        if (dMaxEffectiveTotalRange2 != -2) {
                                            dMaxEffectiveTotalRange = dParticle.maxEffectiveTotalRange();
                                        } else {
                                            dMaxEffectiveTotalRange = dMaxEffectiveTotalRange2;
                                        }
                                        checkNSRecurseCheckCardinality(dChildren2, dMinEffectiveTotalRange, dMaxEffectiveTotalRange, dSGHandler, bParticle, bMinOccurs, bMaxOccurs, checkWCOccurrence);
                                        dChildren = dChildren2;
                                        break;
                                    case (short) 103:
                                        checkRecurse(dChildren2, dMinOccurs, dMaxOccurs, dSGHandler, bChildren, bMinOccurs, bMaxOccurs, bSGHandler);
                                        dMaxEffectiveTotalRange = dMaxEffectiveTotalRange2;
                                        dMinEffectiveTotalRange = dMinEffectiveTotalRange2;
                                        dChildren = dChildren2;
                                        break;
                                    default:
                                        throw new XMLSchemaException("Internal-Error", new Object[]{"in particleValidRestriction"});
                                }
                            default:
                                dMaxEffectiveTotalRange = dMaxEffectiveTotalRange2;
                                dMinEffectiveTotalRange = dMinEffectiveTotalRange2;
                                dChildren = dChildren2;
                                break;
                        }
                        return bExpansionHappened;
                    }
                }
            }
            dMaxEffectiveTotalRange2 = -2;
            dMinEffectiveTotalRange2 = -2;
            dChildren2 = dChildren;
            bType = bParticle.fType;
            if (bType == (short) 3) {
                bType = ((XSModelGroupImpl) bParticle.fValue).fCompositor;
                btmp = getNonUnaryGroup(bParticle);
                if (btmp != bParticle) {
                    bParticle = btmp;
                    bType = bParticle.fType;
                    if (bType == (short) 3) {
                        bType = ((XSModelGroupImpl) bParticle.fValue).fCompositor;
                    }
                }
                bChildren = removePointlessChildren(bParticle);
            }
            bMinOccurs = bParticle.fMinOccurs;
            bMaxOccurs = bParticle.fMaxOccurs;
            bElement = (XSElementDecl) bParticle.fValue;
            if (bElement.fScope == (short) 1) {
                bsubGroup = bSGHandler.getSubstitutionGroup(bElement);
                if (bsubGroup.length > 0) {
                    bType = (short) 101;
                    bChildren = new Vector(bsubGroup.length + 1);
                    while (i < bsubGroup.length) {
                        addElementToParticleVector(bChildren, addElementToParticleVector2);
                    }
                    addElementToParticleVector(bChildren, bElement);
                    Collections.sort(bChildren, ELEMENT_PARTICLE_COMPARATOR);
                    bSGHandler = null;
                    bExpansionHappened = true;
                }
            }
            switch (dType) {
                case (short) 1:
                    switch (bType) {
                        case (short) 1:
                            checkNameAndTypeOK((XSElementDecl) dParticle.fValue, dMinOccurs, dMaxOccurs, (XSElementDecl) bParticle.fValue, bMinOccurs, bMaxOccurs);
                            dMaxEffectiveTotalRange = dMaxEffectiveTotalRange2;
                            dMinEffectiveTotalRange = dMinEffectiveTotalRange2;
                            dChildren = dChildren2;
                            break;
                        case (short) 2:
                            checkNSCompat((XSElementDecl) dParticle.fValue, dMinOccurs, dMaxOccurs, (XSWildcardDecl) bParticle.fValue, bMinOccurs, bMaxOccurs, checkWCOccurrence);
                            dMaxEffectiveTotalRange = dMaxEffectiveTotalRange2;
                            dMinEffectiveTotalRange = dMinEffectiveTotalRange2;
                            dChildren = dChildren2;
                            break;
                        case (short) 101:
                            dChildren = new Vector();
                            dChildren.addElement(dParticle);
                            checkRecurseLax(dChildren, 1, 1, dSGHandler, bChildren, bMinOccurs, bMaxOccurs, bSGHandler);
                            dMaxEffectiveTotalRange = dMaxEffectiveTotalRange2;
                            dMinEffectiveTotalRange = dMinEffectiveTotalRange2;
                            break;
                        case (short) 102:
                        case (short) 103:
                            dChildren = new Vector();
                            dChildren.addElement(dParticle);
                            checkRecurse(dChildren, 1, 1, dSGHandler, bChildren, bMinOccurs, bMaxOccurs, bSGHandler);
                            dMaxEffectiveTotalRange = dMaxEffectiveTotalRange2;
                            dMinEffectiveTotalRange = dMinEffectiveTotalRange2;
                            break;
                        default:
                            throw new XMLSchemaException("Internal-Error", new Object[]{"in particleValidRestriction"});
                    }
                case (short) 2:
                    switch (bType) {
                        case (short) 1:
                        case (short) 101:
                        case (short) 102:
                        case (short) 103:
                            throw new XMLSchemaException("cos-particle-restrict.2", new Object[]{"any:choice,sequence,all,elt"});
                        case (short) 2:
                            checkNSSubset((XSWildcardDecl) dParticle.fValue, dMinOccurs, dMaxOccurs, (XSWildcardDecl) bParticle.fValue, bMinOccurs, bMaxOccurs);
                            dMaxEffectiveTotalRange = dMaxEffectiveTotalRange2;
                            dMinEffectiveTotalRange = dMinEffectiveTotalRange2;
                            dChildren = dChildren2;
                            break;
                        default:
                            throw new XMLSchemaException("Internal-Error", new Object[]{"in particleValidRestriction"});
                    }
                case (short) 101:
                    switch (bType) {
                        case (short) 1:
                        case (short) 102:
                        case (short) 103:
                            throw new XMLSchemaException("cos-particle-restrict.2", new Object[]{"choice:all,sequence,elt"});
                        case (short) 2:
                            if (dMinEffectiveTotalRange2 != -2) {
                                dMinEffectiveTotalRange = dMinEffectiveTotalRange2;
                            } else {
                                dMinEffectiveTotalRange = dParticle.minEffectiveTotalRange();
                            }
                            if (dMaxEffectiveTotalRange2 != -2) {
                                dMaxEffectiveTotalRange = dMaxEffectiveTotalRange2;
                            } else {
                                dMaxEffectiveTotalRange = dParticle.maxEffectiveTotalRange();
                            }
                            checkNSRecurseCheckCardinality(dChildren2, dMinEffectiveTotalRange, dMaxEffectiveTotalRange, dSGHandler, bParticle, bMinOccurs, bMaxOccurs, checkWCOccurrence);
                            dChildren = dChildren2;
                            break;
                        case (short) 101:
                            checkRecurseLax(dChildren2, dMinOccurs, dMaxOccurs, dSGHandler, bChildren, bMinOccurs, bMaxOccurs, bSGHandler);
                            dMaxEffectiveTotalRange = dMaxEffectiveTotalRange2;
                            dMinEffectiveTotalRange = dMinEffectiveTotalRange2;
                            dChildren = dChildren2;
                            break;
                        default:
                            throw new XMLSchemaException("Internal-Error", new Object[]{"in particleValidRestriction"});
                    }
                case (short) 102:
                    switch (bType) {
                        case (short) 1:
                            throw new XMLSchemaException("cos-particle-restrict.2", new Object[]{"seq:elt"});
                        case (short) 2:
                            if (dMinEffectiveTotalRange2 != -2) {
                                dMinEffectiveTotalRange = dMinEffectiveTotalRange2;
                            } else {
                                dMinEffectiveTotalRange = dParticle.minEffectiveTotalRange();
                            }
                            if (dMaxEffectiveTotalRange2 != -2) {
                                dMaxEffectiveTotalRange = dMaxEffectiveTotalRange2;
                            } else {
                                dMaxEffectiveTotalRange = dParticle.maxEffectiveTotalRange();
                            }
                            checkNSRecurseCheckCardinality(dChildren2, dMinEffectiveTotalRange, dMaxEffectiveTotalRange, dSGHandler, bParticle, bMinOccurs, bMaxOccurs, checkWCOccurrence);
                            dChildren = dChildren2;
                            break;
                        case (short) 101:
                            min1 = dMinOccurs * dChildren2.size();
                            if (dMaxOccurs != -1) {
                                max1 = dMaxOccurs * dChildren2.size();
                            } else {
                                max1 = dMaxOccurs;
                            }
                            checkMapAndSum(dChildren2, min1, max1, dSGHandler, bChildren, bMinOccurs, bMaxOccurs, bSGHandler);
                            dMaxEffectiveTotalRange = dMaxEffectiveTotalRange2;
                            dMinEffectiveTotalRange = dMinEffectiveTotalRange2;
                            dChildren = dChildren2;
                            break;
                        case (short) 102:
                            checkRecurse(dChildren2, dMinOccurs, dMaxOccurs, dSGHandler, bChildren, bMinOccurs, bMaxOccurs, bSGHandler);
                            dMaxEffectiveTotalRange = dMaxEffectiveTotalRange2;
                            dMinEffectiveTotalRange = dMinEffectiveTotalRange2;
                            dChildren = dChildren2;
                            break;
                        case (short) 103:
                            checkRecurseUnordered(dChildren2, dMinOccurs, dMaxOccurs, dSGHandler, bChildren, bMinOccurs, bMaxOccurs, bSGHandler);
                            dMaxEffectiveTotalRange = dMaxEffectiveTotalRange2;
                            dMinEffectiveTotalRange = dMinEffectiveTotalRange2;
                            dChildren = dChildren2;
                            break;
                        default:
                            throw new XMLSchemaException("Internal-Error", new Object[]{"in particleValidRestriction"});
                    }
                case (short) 103:
                    switch (bType) {
                        case (short) 1:
                        case (short) 101:
                        case (short) 102:
                            throw new XMLSchemaException("cos-particle-restrict.2", new Object[]{"all:choice,sequence,elt"});
                        case (short) 2:
                            if (dMinEffectiveTotalRange2 != -2) {
                                dMinEffectiveTotalRange = dMinEffectiveTotalRange2;
                            } else {
                                dMinEffectiveTotalRange = dParticle.minEffectiveTotalRange();
                            }
                            if (dMaxEffectiveTotalRange2 != -2) {
                                dMaxEffectiveTotalRange = dMaxEffectiveTotalRange2;
                            } else {
                                dMaxEffectiveTotalRange = dParticle.maxEffectiveTotalRange();
                            }
                            checkNSRecurseCheckCardinality(dChildren2, dMinEffectiveTotalRange, dMaxEffectiveTotalRange, dSGHandler, bParticle, bMinOccurs, bMaxOccurs, checkWCOccurrence);
                            dChildren = dChildren2;
                            break;
                        case (short) 103:
                            checkRecurse(dChildren2, dMinOccurs, dMaxOccurs, dSGHandler, bChildren, bMinOccurs, bMaxOccurs, bSGHandler);
                            dMaxEffectiveTotalRange = dMaxEffectiveTotalRange2;
                            dMinEffectiveTotalRange = dMinEffectiveTotalRange2;
                            dChildren = dChildren2;
                            break;
                        default:
                            throw new XMLSchemaException("Internal-Error", new Object[]{"in particleValidRestriction"});
                    }
                default:
                    dMaxEffectiveTotalRange = dMaxEffectiveTotalRange2;
                    dMinEffectiveTotalRange = dMinEffectiveTotalRange2;
                    dChildren = dChildren2;
                    break;
            }
            return bExpansionHappened;
        } else {
            throw new XMLSchemaException("cos-particle-restrict.b", null);
        }
    }

    private static void addElementToParticleVector(Vector v, XSElementDecl d) {
        XSParticleDecl p = new XSParticleDecl();
        p.fValue = d;
        p.fType = (short) 1;
        v.addElement(p);
    }

    private static XSParticleDecl getNonUnaryGroup(XSParticleDecl p) {
        if (p.fType == (short) 1 || p.fType == (short) 2 || p.fMinOccurs != 1 || p.fMaxOccurs != 1 || p.fValue == null || ((XSModelGroupImpl) p.fValue).fParticleCount != 1) {
            return p;
        }
        return getNonUnaryGroup(((XSModelGroupImpl) p.fValue).fParticles[0]);
    }

    private static Vector removePointlessChildren(XSParticleDecl p) {
        if (p.fType == (short) 1 || p.fType == (short) 2) {
            return null;
        }
        Vector children = new Vector();
        XSModelGroupImpl group = p.fValue;
        for (int i = 0; i < group.fParticleCount; i++) {
            gatherChildren(group.fCompositor, group.fParticles[i], children);
        }
        return children;
    }

    private static void gatherChildren(int parentType, XSParticleDecl p, Vector children) {
        int min = p.fMinOccurs;
        int max = p.fMaxOccurs;
        int type = p.fType;
        if (type == 3) {
            type = ((XSModelGroupImpl) p.fValue).fCompositor;
        }
        if (type == 1 || type == 2) {
            children.addElement(p);
        } else if (min != 1 || max != 1) {
            children.addElement(p);
        } else if (parentType == type) {
            XSModelGroupImpl group = p.fValue;
            for (int i = 0; i < group.fParticleCount; i++) {
                gatherChildren(type, group.fParticles[i], children);
            }
        } else if (!p.isEmpty()) {
            children.addElement(p);
        }
    }

    private static void checkNameAndTypeOK(XSElementDecl dElement, int dMin, int dMax, XSElementDecl bElement, int bMin, int bMax) throws XMLSchemaException {
        if (dElement.fName != bElement.fName || dElement.fTargetNamespace != bElement.fTargetNamespace) {
            throw new XMLSchemaException("rcase-NameAndTypeOK.1", new Object[]{dElement.fName, dElement.fTargetNamespace, bElement.fName, bElement.fTargetNamespace});
        } else if (!bElement.getNillable() && dElement.getNillable()) {
            throw new XMLSchemaException("rcase-NameAndTypeOK.2", new Object[]{dElement.fName});
        } else if (checkOccurrenceRange(dMin, dMax, bMin, bMax)) {
            if (bElement.getConstraintType() == (short) 2) {
                if (dElement.getConstraintType() != (short) 2) {
                    throw new XMLSchemaException("rcase-NameAndTypeOK.4.a", new Object[]{dElement.fName, bElement.fDefault.stringValue()});
                }
                boolean isSimple = false;
                if (dElement.fType.getTypeCategory() == (short) 16 || ((XSComplexTypeDecl) dElement.fType).fContentType == (short) 1) {
                    isSimple = true;
                }
                if (!(isSimple || bElement.fDefault.normalizedValue.equals(dElement.fDefault.normalizedValue)) || (isSimple && !bElement.fDefault.actualValue.equals(dElement.fDefault.actualValue))) {
                    throw new XMLSchemaException("rcase-NameAndTypeOK.4.b", new Object[]{dElement.fName, dElement.fDefault.stringValue(), bElement.fDefault.stringValue()});
                }
            }
            checkIDConstraintRestriction(dElement, bElement);
            int blockSet1 = dElement.fBlock;
            int blockSet2 = bElement.fBlock;
            if ((blockSet1 & blockSet2) != blockSet2 || (blockSet1 == 0 && blockSet2 != 0)) {
                throw new XMLSchemaException("rcase-NameAndTypeOK.6", new Object[]{dElement.fName});
            } else if (!checkTypeDerivationOk(dElement.fType, bElement.fType, (short) 25)) {
                throw new XMLSchemaException("rcase-NameAndTypeOK.7", new Object[]{dElement.fName, dElement.fType.getName(), bElement.fType.getName()});
            }
        } else {
            String str;
            String str2 = "rcase-NameAndTypeOK.3";
            Object[] objArr = new Object[5];
            objArr[0] = dElement.fName;
            objArr[1] = Integer.toString(dMin);
            objArr[2] = dMax == -1 ? SchemaSymbols.ATTVAL_UNBOUNDED : Integer.toString(dMax);
            objArr[3] = Integer.toString(bMin);
            if (bMax == -1) {
                str = SchemaSymbols.ATTVAL_UNBOUNDED;
            } else {
                str = Integer.toString(bMax);
            }
            objArr[4] = str;
            throw new XMLSchemaException(str2, objArr);
        }
    }

    private static void checkIDConstraintRestriction(XSElementDecl derivedElemDecl, XSElementDecl baseElemDecl) throws XMLSchemaException {
    }

    private static boolean checkOccurrenceRange(int min1, int max1, int min2, int max2) {
        if (min1 < min2 || (max2 != -1 && (max1 == -1 || max1 > max2))) {
            return false;
        }
        return true;
    }

    private static void checkNSCompat(XSElementDecl elem, int min1, int max1, XSWildcardDecl wildcard, int min2, int max2, boolean checkWCOccurrence) throws XMLSchemaException {
        if (checkWCOccurrence && !checkOccurrenceRange(min1, max1, min2, max2)) {
            String str;
            String str2 = "rcase-NSCompat.2";
            Object[] objArr = new Object[5];
            objArr[0] = elem.fName;
            objArr[1] = Integer.toString(min1);
            objArr[2] = max1 == -1 ? SchemaSymbols.ATTVAL_UNBOUNDED : Integer.toString(max1);
            objArr[3] = Integer.toString(min2);
            if (max2 == -1) {
                str = SchemaSymbols.ATTVAL_UNBOUNDED;
            } else {
                str = Integer.toString(max2);
            }
            objArr[4] = str;
            throw new XMLSchemaException(str2, objArr);
        } else if (!wildcard.allowNamespace(elem.fTargetNamespace)) {
            throw new XMLSchemaException("rcase-NSCompat.1", new Object[]{elem.fName, elem.fTargetNamespace});
        }
    }

    private static void checkNSSubset(XSWildcardDecl dWildcard, int min1, int max1, XSWildcardDecl bWildcard, int min2, int max2) throws XMLSchemaException {
        if (!checkOccurrenceRange(min1, max1, min2, max2)) {
            String str = "rcase-NSSubset.2";
            Object[] objArr = new Object[4];
            objArr[0] = Integer.toString(min1);
            objArr[1] = max1 == -1 ? SchemaSymbols.ATTVAL_UNBOUNDED : Integer.toString(max1);
            objArr[2] = Integer.toString(min2);
            objArr[3] = max2 == -1 ? SchemaSymbols.ATTVAL_UNBOUNDED : Integer.toString(max2);
            throw new XMLSchemaException(str, objArr);
        } else if (!dWildcard.isSubsetOf(bWildcard)) {
            throw new XMLSchemaException("rcase-NSSubset.1", null);
        } else if (dWildcard.weakerProcessContents(bWildcard)) {
            throw new XMLSchemaException("rcase-NSSubset.3", new Object[]{dWildcard.getProcessContentsAsString(), bWildcard.getProcessContentsAsString()});
        }
    }

    private static void checkNSRecurseCheckCardinality(Vector children, int min1, int max1, SubstitutionGroupHandler dSGHandler, XSParticleDecl wildcard, int min2, int max2, boolean checkWCOccurrence) throws XMLSchemaException {
        if (!checkWCOccurrence || checkOccurrenceRange(min1, max1, min2, max2)) {
            int count = children.size();
            int i = 0;
            while (i < count) {
                try {
                    particleValidRestriction((XSParticleDecl) children.elementAt(i), dSGHandler, wildcard, null, false);
                    i++;
                } catch (XMLSchemaException e) {
                    throw new XMLSchemaException("rcase-NSRecurseCheckCardinality.1", null);
                }
            }
            return;
        }
        String str = "rcase-NSRecurseCheckCardinality.2";
        Object[] objArr = new Object[4];
        objArr[0] = Integer.toString(min1);
        objArr[1] = max1 == -1 ? SchemaSymbols.ATTVAL_UNBOUNDED : Integer.toString(max1);
        objArr[2] = Integer.toString(min2);
        objArr[3] = max2 == -1 ? SchemaSymbols.ATTVAL_UNBOUNDED : Integer.toString(max2);
        throw new XMLSchemaException(str, objArr);
    }

    private static void checkRecurse(Vector dChildren, int min1, int max1, SubstitutionGroupHandler dSGHandler, Vector bChildren, int min2, int max2, SubstitutionGroupHandler bSGHandler) throws XMLSchemaException {
        int j;
        if (checkOccurrenceRange(min1, max1, min2, max2)) {
            int count1 = dChildren.size();
            int count2 = bChildren.size();
            int current = 0;
            int i = 0;
            while (i < count1) {
                XSParticleDecl particle1 = (XSParticleDecl) dChildren.elementAt(i);
                j = current;
                while (j < count2) {
                    XSParticleDecl particle2 = (XSParticleDecl) bChildren.elementAt(j);
                    current++;
                    try {
                        particleValidRestriction(particle1, dSGHandler, particle2, bSGHandler);
                        i++;
                    } catch (XMLSchemaException e) {
                        if (particle2.emptiable()) {
                            j++;
                        } else {
                            throw new XMLSchemaException("rcase-Recurse.2", null);
                        }
                    }
                }
                throw new XMLSchemaException("rcase-Recurse.2", null);
            }
            j = current;
            while (j < count2) {
                if (((XSParticleDecl) bChildren.elementAt(j)).emptiable()) {
                    j++;
                } else {
                    throw new XMLSchemaException("rcase-Recurse.2", null);
                }
            }
            return;
        }
        String str = "rcase-Recurse.1";
        Object[] objArr = new Object[4];
        objArr[0] = Integer.toString(min1);
        objArr[1] = max1 == -1 ? SchemaSymbols.ATTVAL_UNBOUNDED : Integer.toString(max1);
        objArr[2] = Integer.toString(min2);
        objArr[3] = max2 == -1 ? SchemaSymbols.ATTVAL_UNBOUNDED : Integer.toString(max2);
        throw new XMLSchemaException(str, objArr);
    }

    private static void checkRecurseUnordered(Vector dChildren, int min1, int max1, SubstitutionGroupHandler dSGHandler, Vector bChildren, int min2, int max2, SubstitutionGroupHandler bSGHandler) throws XMLSchemaException {
        if (checkOccurrenceRange(min1, max1, min2, max2)) {
            int j;
            int count1 = dChildren.size();
            int count2 = bChildren.size();
            boolean[] foundIt = new boolean[count2];
            int i = 0;
            while (i < count1) {
                XSParticleDecl particle1 = (XSParticleDecl) dChildren.elementAt(i);
                j = 0;
                while (j < count2) {
                    try {
                        particleValidRestriction(particle1, dSGHandler, (XSParticleDecl) bChildren.elementAt(j), bSGHandler);
                        if (foundIt[j]) {
                            throw new XMLSchemaException("rcase-RecurseUnordered.2", null);
                        }
                        foundIt[j] = true;
                        i++;
                    } catch (XMLSchemaException e) {
                        j++;
                    }
                }
                throw new XMLSchemaException("rcase-RecurseUnordered.2", null);
            }
            j = 0;
            while (j < count2) {
                XSParticleDecl particle2 = (XSParticleDecl) bChildren.elementAt(j);
                if (foundIt[j] || particle2.emptiable()) {
                    j++;
                } else {
                    throw new XMLSchemaException("rcase-RecurseUnordered.2", null);
                }
            }
            return;
        }
        String str = "rcase-RecurseUnordered.1";
        Object[] objArr = new Object[4];
        objArr[0] = Integer.toString(min1);
        objArr[1] = max1 == -1 ? SchemaSymbols.ATTVAL_UNBOUNDED : Integer.toString(max1);
        objArr[2] = Integer.toString(min2);
        objArr[3] = max2 == -1 ? SchemaSymbols.ATTVAL_UNBOUNDED : Integer.toString(max2);
        throw new XMLSchemaException(str, objArr);
    }

    private static void checkRecurseLax(Vector dChildren, int min1, int max1, SubstitutionGroupHandler dSGHandler, Vector bChildren, int min2, int max2, SubstitutionGroupHandler bSGHandler) throws XMLSchemaException {
        if (checkOccurrenceRange(min1, max1, min2, max2)) {
            int count1 = dChildren.size();
            int count2 = bChildren.size();
            int current = 0;
            int i = 0;
            while (i < count1) {
                XSParticleDecl particle1 = (XSParticleDecl) dChildren.elementAt(i);
                int j = current;
                while (j < count2) {
                    current++;
                    try {
                        if (particleValidRestriction(particle1, dSGHandler, (XSParticleDecl) bChildren.elementAt(j), bSGHandler)) {
                            current--;
                        }
                        i++;
                    } catch (XMLSchemaException e) {
                        j++;
                    }
                }
                throw new XMLSchemaException("rcase-RecurseLax.2", null);
            }
            return;
        }
        String str = "rcase-RecurseLax.1";
        Object[] objArr = new Object[4];
        objArr[0] = Integer.toString(min1);
        objArr[1] = max1 == -1 ? SchemaSymbols.ATTVAL_UNBOUNDED : Integer.toString(max1);
        objArr[2] = Integer.toString(min2);
        objArr[3] = max2 == -1 ? SchemaSymbols.ATTVAL_UNBOUNDED : Integer.toString(max2);
        throw new XMLSchemaException(str, objArr);
    }

    private static void checkMapAndSum(Vector dChildren, int min1, int max1, SubstitutionGroupHandler dSGHandler, Vector bChildren, int min2, int max2, SubstitutionGroupHandler bSGHandler) throws XMLSchemaException {
        if (checkOccurrenceRange(min1, max1, min2, max2)) {
            int count1 = dChildren.size();
            int count2 = bChildren.size();
            int i = 0;
            while (i < count1) {
                XSParticleDecl particle1 = (XSParticleDecl) dChildren.elementAt(i);
                int j = 0;
                while (j < count2) {
                    try {
                        particleValidRestriction(particle1, dSGHandler, (XSParticleDecl) bChildren.elementAt(j), bSGHandler);
                        i++;
                    } catch (XMLSchemaException e) {
                        j++;
                    }
                }
                throw new XMLSchemaException("rcase-MapAndSum.1", null);
            }
            return;
        }
        String str = "rcase-MapAndSum.2";
        Object[] objArr = new Object[4];
        objArr[0] = Integer.toString(min1);
        objArr[1] = max1 == -1 ? SchemaSymbols.ATTVAL_UNBOUNDED : Integer.toString(max1);
        objArr[2] = Integer.toString(min2);
        objArr[3] = max2 == -1 ? SchemaSymbols.ATTVAL_UNBOUNDED : Integer.toString(max2);
        throw new XMLSchemaException(str, objArr);
    }

    public static boolean overlapUPA(XSElementDecl element1, XSElementDecl element2, SubstitutionGroupHandler sgHandler) {
        if (element1.fName == element2.fName && element1.fTargetNamespace == element2.fTargetNamespace) {
            return true;
        }
        XSElementDecl[] subGroup = sgHandler.getSubstitutionGroup(element1);
        int i = subGroup.length - 1;
        while (i >= 0) {
            if (subGroup[i].fName == element2.fName && subGroup[i].fTargetNamespace == element2.fTargetNamespace) {
                return true;
            }
            i--;
        }
        subGroup = sgHandler.getSubstitutionGroup(element2);
        i = subGroup.length - 1;
        while (i >= 0) {
            if (subGroup[i].fName == element1.fName && subGroup[i].fTargetNamespace == element1.fTargetNamespace) {
                return true;
            }
            i--;
        }
        return false;
    }

    public static boolean overlapUPA(XSElementDecl element, XSWildcardDecl wildcard, SubstitutionGroupHandler sgHandler) {
        if (wildcard.allowNamespace(element.fTargetNamespace)) {
            return true;
        }
        XSElementDecl[] subGroup = sgHandler.getSubstitutionGroup(element);
        for (int i = subGroup.length - 1; i >= 0; i--) {
            if (wildcard.allowNamespace(subGroup[i].fTargetNamespace)) {
                return true;
            }
        }
        return false;
    }

    public static boolean overlapUPA(XSWildcardDecl wildcard1, XSWildcardDecl wildcard2) {
        XSWildcardDecl intersect = wildcard1.performIntersectionWith(wildcard2, wildcard1.fProcessContents);
        if (intersect != null && intersect.fType == (short) 3 && intersect.fNamespaceList.length == 0) {
            return false;
        }
        return true;
    }

    public static boolean overlapUPA(Object decl1, Object decl2, SubstitutionGroupHandler sgHandler) {
        if (decl1 instanceof XSElementDecl) {
            if (decl2 instanceof XSElementDecl) {
                return overlapUPA((XSElementDecl) decl1, (XSElementDecl) decl2, sgHandler);
            }
            return overlapUPA((XSElementDecl) decl1, (XSWildcardDecl) decl2, sgHandler);
        } else if (decl2 instanceof XSElementDecl) {
            return overlapUPA((XSElementDecl) decl2, (XSWildcardDecl) decl1, sgHandler);
        } else {
            return overlapUPA((XSWildcardDecl) decl1, (XSWildcardDecl) decl2);
        }
    }
}
