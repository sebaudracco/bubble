package mf.org.apache.xerces.parsers;

import mf.org.apache.xerces.impl.dv.DTDDVFactory;
import mf.org.apache.xerces.util.SymbolTable;
import mf.org.apache.xerces.xni.parser.XMLParserConfiguration;

public abstract class XMLGrammarParser extends XMLParser {
    protected DTDDVFactory fDatatypeValidatorFactory;

    protected XMLGrammarParser(SymbolTable symbolTable) {
        super((XMLParserConfiguration) ObjectFactory.createObject("mf.org.apache.xerces.xni.parser.XMLParserConfiguration", "mf.org.apache.xerces.parsers.XIncludeAwareParserConfiguration"));
        this.fConfiguration.setProperty("http://apache.org/xml/properties/internal/symbol-table", symbolTable);
    }
}
