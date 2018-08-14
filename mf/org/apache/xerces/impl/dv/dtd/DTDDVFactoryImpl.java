package mf.org.apache.xerces.impl.dv.dtd;

import java.util.Hashtable;
import mf.org.apache.xerces.impl.dv.DTDDVFactory;
import mf.org.apache.xerces.impl.dv.DatatypeValidator;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

public class DTDDVFactoryImpl extends DTDDVFactory {
    static final Hashtable fBuiltInTypes = new Hashtable();

    static {
        createBuiltInTypes();
    }

    public DatatypeValidator getBuiltInDV(String name) {
        return (DatatypeValidator) fBuiltInTypes.get(name);
    }

    public Hashtable getBuiltInTypes() {
        return (Hashtable) fBuiltInTypes.clone();
    }

    static void createBuiltInTypes() {
        fBuiltInTypes.put(SchemaSymbols.ATTVAL_STRING, new StringDatatypeValidator());
        fBuiltInTypes.put(SchemaSymbols.ATTVAL_ID, new IDDatatypeValidator());
        DatatypeValidator dvTemp = new IDREFDatatypeValidator();
        fBuiltInTypes.put(SchemaSymbols.ATTVAL_IDREF, dvTemp);
        fBuiltInTypes.put(SchemaSymbols.ATTVAL_IDREFS, new ListDatatypeValidator(dvTemp));
        dvTemp = new ENTITYDatatypeValidator();
        fBuiltInTypes.put(SchemaSymbols.ATTVAL_ENTITY, new ENTITYDatatypeValidator());
        fBuiltInTypes.put(SchemaSymbols.ATTVAL_ENTITIES, new ListDatatypeValidator(dvTemp));
        fBuiltInTypes.put(SchemaSymbols.ATTVAL_NOTATION, new NOTATIONDatatypeValidator());
        dvTemp = new NMTOKENDatatypeValidator();
        fBuiltInTypes.put(SchemaSymbols.ATTVAL_NMTOKEN, dvTemp);
        fBuiltInTypes.put(SchemaSymbols.ATTVAL_NMTOKENS, new ListDatatypeValidator(dvTemp));
    }
}
