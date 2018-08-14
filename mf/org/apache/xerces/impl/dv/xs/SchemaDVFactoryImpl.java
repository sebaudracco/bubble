package mf.org.apache.xerces.impl.dv.xs;

import mf.org.apache.xerces.impl.dv.XSSimpleType;
import mf.org.apache.xerces.util.SymbolHash;

public class SchemaDVFactoryImpl extends BaseSchemaDVFactory {
    static final SymbolHash fBuiltInTypes = new SymbolHash();

    static {
        createBuiltInTypes();
    }

    static void createBuiltInTypes() {
        BaseSchemaDVFactory.createBuiltInTypes(fBuiltInTypes, XSSimpleTypeDecl.fAnySimpleType);
    }

    public XSSimpleType getBuiltInType(String name) {
        return (XSSimpleType) fBuiltInTypes.get(name);
    }

    public SymbolHash getBuiltInTypes() {
        return fBuiltInTypes.makeClone();
    }
}
