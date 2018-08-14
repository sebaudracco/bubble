package mf.org.apache.xerces.impl.dv.xs;

import mf.org.apache.xerces.impl.dv.XSSimpleType;
import mf.org.apache.xerces.util.SymbolHash;

public class ExtendedSchemaDVFactoryImpl extends BaseSchemaDVFactory {
    static SymbolHash fBuiltInTypes = new SymbolHash();

    static {
        createBuiltInTypes();
    }

    static void createBuiltInTypes() {
        String ANYATOMICTYPE = "anyAtomicType";
        String DURATION = "duration";
        String YEARMONTHDURATION = "yearMonthDuration";
        String DAYTIMEDURATION = "dayTimeDuration";
        BaseSchemaDVFactory.createBuiltInTypes(fBuiltInTypes, XSSimpleTypeDecl.fAnyAtomicType);
        fBuiltInTypes.put("anyAtomicType", XSSimpleTypeDecl.fAnyAtomicType);
        XSSimpleTypeDecl durationDV = (XSSimpleTypeDecl) fBuiltInTypes.get("duration");
        fBuiltInTypes.put("yearMonthDuration", new XSSimpleTypeDecl(durationDV, "yearMonthDuration", (short) 27, (short) 1, false, false, false, true, (short) 46));
        fBuiltInTypes.put("dayTimeDuration", new XSSimpleTypeDecl(durationDV, "dayTimeDuration", (short) 28, (short) 1, false, false, false, true, (short) 47));
    }

    public XSSimpleType getBuiltInType(String name) {
        return (XSSimpleType) fBuiltInTypes.get(name);
    }

    public SymbolHash getBuiltInTypes() {
        return fBuiltInTypes.makeClone();
    }
}
