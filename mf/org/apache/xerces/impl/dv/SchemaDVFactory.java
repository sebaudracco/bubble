package mf.org.apache.xerces.impl.dv;

import mf.org.apache.xerces.impl.dv.xs.SchemaDVFactoryImpl;
import mf.org.apache.xerces.util.SymbolHash;
import mf.org.apache.xerces.xs.XSObjectList;

public abstract class SchemaDVFactory {
    private static final String DEFAULT_FACTORY_CLASS = "mf.org.apache.xerces.impl.dv.xs.SchemaDVFactoryImpl";

    public abstract XSSimpleType createTypeList(String str, String str2, short s, XSSimpleType xSSimpleType, XSObjectList xSObjectList);

    public abstract XSSimpleType createTypeRestriction(String str, String str2, short s, XSSimpleType xSSimpleType, XSObjectList xSObjectList);

    public abstract XSSimpleType createTypeUnion(String str, String str2, short s, XSSimpleType[] xSSimpleTypeArr, XSObjectList xSObjectList);

    public abstract XSSimpleType getBuiltInType(String str);

    public abstract SymbolHash getBuiltInTypes();

    public static final SchemaDVFactory getInstance() throws DVFactoryException {
        return getInstance(DEFAULT_FACTORY_CLASS);
    }

    public static final SchemaDVFactory getInstance(String factoryClass) throws DVFactoryException {
        try {
            return new SchemaDVFactoryImpl();
        } catch (ClassCastException e) {
            throw new DVFactoryException("Schema factory class " + factoryClass + " does not extend from SchemaDVFactory.");
        }
    }

    protected SchemaDVFactory() {
    }
}
