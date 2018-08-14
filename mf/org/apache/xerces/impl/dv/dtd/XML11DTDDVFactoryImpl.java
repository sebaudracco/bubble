package mf.org.apache.xerces.impl.dv.dtd;

import java.util.Hashtable;
import java.util.Map.Entry;
import mf.org.apache.xerces.impl.dv.DatatypeValidator;

public class XML11DTDDVFactoryImpl extends DTDDVFactoryImpl {
    static final Hashtable fXML11BuiltInTypes = new Hashtable();

    static {
        fXML11BuiltInTypes.put("XML11ID", new XML11IDDatatypeValidator());
        DatatypeValidator dvTemp = new XML11IDREFDatatypeValidator();
        fXML11BuiltInTypes.put("XML11IDREF", dvTemp);
        fXML11BuiltInTypes.put("XML11IDREFS", new ListDatatypeValidator(dvTemp));
        dvTemp = new XML11NMTOKENDatatypeValidator();
        fXML11BuiltInTypes.put("XML11NMTOKEN", dvTemp);
        fXML11BuiltInTypes.put("XML11NMTOKENS", new ListDatatypeValidator(dvTemp));
    }

    public DatatypeValidator getBuiltInDV(String name) {
        if (fXML11BuiltInTypes.get(name) != null) {
            return (DatatypeValidator) fXML11BuiltInTypes.get(name);
        }
        return (DatatypeValidator) fBuiltInTypes.get(name);
    }

    public Hashtable getBuiltInTypes() {
        Hashtable toReturn = (Hashtable) fBuiltInTypes.clone();
        for (Entry entry : fXML11BuiltInTypes.entrySet()) {
            toReturn.put(entry.getKey(), entry.getValue());
        }
        return toReturn;
    }
}
