package mf.org.apache.xerces.util;

import mf.javax.xml.XMLConstants;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

public class XMLSymbols {
    public static final String EMPTY_STRING = "".intern();
    public static final String PREFIX_XML = "xml".intern();
    public static final String PREFIX_XMLNS = XMLConstants.XMLNS_ATTRIBUTE.intern();
    public static final String fANYSymbol = "ANY".intern();
    public static final String fCDATASymbol = "CDATA".intern();
    public static final String fENTITIESSymbol = SchemaSymbols.ATTVAL_ENTITIES.intern();
    public static final String fENTITYSymbol = SchemaSymbols.ATTVAL_ENTITY.intern();
    public static final String fENUMERATIONSymbol = "ENUMERATION".intern();
    public static final String fFIXEDSymbol = "#FIXED".intern();
    public static final String fIDREFSSymbol = SchemaSymbols.ATTVAL_IDREFS.intern();
    public static final String fIDREFSymbol = SchemaSymbols.ATTVAL_IDREF.intern();
    public static final String fIDSymbol = SchemaSymbols.ATTVAL_ID.intern();
    public static final String fIMPLIEDSymbol = "#IMPLIED".intern();
    public static final String fNMTOKENSSymbol = SchemaSymbols.ATTVAL_NMTOKENS.intern();
    public static final String fNMTOKENSymbol = SchemaSymbols.ATTVAL_NMTOKEN.intern();
    public static final String fNOTATIONSymbol = SchemaSymbols.ATTVAL_NOTATION.intern();
    public static final String fREQUIREDSymbol = "#REQUIRED".intern();
}
