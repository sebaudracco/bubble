package mf.org.apache.xerces.parsers;

import mf.org.apache.xerces.util.SoftReferenceSymbolTable;
import mf.org.apache.xerces.util.SymbolTable;
import mf.org.apache.xerces.xni.grammars.XMLGrammarPool;
import mf.org.apache.xerces.xni.parser.XMLComponentManager;

public class SoftReferenceSymbolTableConfiguration extends XIncludeAwareParserConfiguration {
    public SoftReferenceSymbolTableConfiguration() {
        this(new SoftReferenceSymbolTable(), null, null);
    }

    public SoftReferenceSymbolTableConfiguration(SymbolTable symbolTable) {
        this(symbolTable, null, null);
    }

    public SoftReferenceSymbolTableConfiguration(SymbolTable symbolTable, XMLGrammarPool grammarPool) {
        this(symbolTable, grammarPool, null);
    }

    public SoftReferenceSymbolTableConfiguration(SymbolTable symbolTable, XMLGrammarPool grammarPool, XMLComponentManager parentSettings) {
        super(symbolTable, grammarPool, parentSettings);
    }
}
