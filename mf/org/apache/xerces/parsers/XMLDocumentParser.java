package mf.org.apache.xerces.parsers;

import mf.org.apache.xerces.util.SymbolTable;
import mf.org.apache.xerces.xni.grammars.XMLGrammarPool;
import mf.org.apache.xerces.xni.parser.XMLParserConfiguration;

public class XMLDocumentParser extends AbstractXMLDocumentParser {
    public XMLDocumentParser() {
        super((XMLParserConfiguration) ObjectFactory.createObject("mf.org.apache.xerces.xni.parser.XMLParserConfiguration", "mf.org.apache.xerces.parsers.XIncludeAwareParserConfiguration"));
    }

    public XMLDocumentParser(XMLParserConfiguration config) {
        super(config);
    }

    public XMLDocumentParser(SymbolTable symbolTable) {
        super((XMLParserConfiguration) ObjectFactory.createObject("mf.org.apache.xerces.xni.parser.XMLParserConfiguration", "mf.org.apache.xerces.parsers.XIncludeAwareParserConfiguration"));
        this.fConfiguration.setProperty("http://apache.org/xml/properties/internal/symbol-table", symbolTable);
    }

    public XMLDocumentParser(SymbolTable symbolTable, XMLGrammarPool grammarPool) {
        super((XMLParserConfiguration) ObjectFactory.createObject("mf.org.apache.xerces.xni.parser.XMLParserConfiguration", "mf.org.apache.xerces.parsers.XIncludeAwareParserConfiguration"));
        this.fConfiguration.setProperty("http://apache.org/xml/properties/internal/symbol-table", symbolTable);
        this.fConfiguration.setProperty("http://apache.org/xml/properties/internal/grammar-pool", grammarPool);
    }
}
