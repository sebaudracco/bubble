package mf.org.apache.xerces.parsers;

import mf.org.apache.xerces.util.SymbolTable;
import mf.org.apache.xerces.xni.grammars.XMLGrammarPool;
import mf.org.apache.xerces.xni.parser.XMLParserConfiguration;

public class SAXParser extends AbstractSAXParser {
    protected static final String NOTIFY_BUILTIN_REFS = "http://apache.org/xml/features/scanner/notify-builtin-refs";
    private static final String[] RECOGNIZED_FEATURES = new String[]{NOTIFY_BUILTIN_REFS};
    private static final String[] RECOGNIZED_PROPERTIES = new String[]{"http://apache.org/xml/properties/internal/symbol-table", "http://apache.org/xml/properties/internal/grammar-pool"};
    protected static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
    protected static final String XMLGRAMMAR_POOL = "http://apache.org/xml/properties/internal/grammar-pool";

    public SAXParser(XMLParserConfiguration config) {
        super(config);
    }

    public SAXParser() {
        this(null, null);
    }

    public SAXParser(SymbolTable symbolTable) {
        this(symbolTable, null);
    }

    public SAXParser(SymbolTable symbolTable, XMLGrammarPool grammarPool) {
        super((XMLParserConfiguration) ObjectFactory.createObject("mf.org.apache.xerces.xni.parser.XMLParserConfiguration", "mf.org.apache.xerces.parsers.XIncludeAwareParserConfiguration"));
        this.fConfiguration.addRecognizedFeatures(RECOGNIZED_FEATURES);
        this.fConfiguration.setFeature(NOTIFY_BUILTIN_REFS, true);
        this.fConfiguration.addRecognizedProperties(RECOGNIZED_PROPERTIES);
        if (symbolTable != null) {
            this.fConfiguration.setProperty("http://apache.org/xml/properties/internal/symbol-table", symbolTable);
        }
        if (grammarPool != null) {
            this.fConfiguration.setProperty("http://apache.org/xml/properties/internal/grammar-pool", grammarPool);
        }
    }
}
