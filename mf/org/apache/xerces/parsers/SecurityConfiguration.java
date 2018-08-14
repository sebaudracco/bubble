package mf.org.apache.xerces.parsers;

import mf.org.apache.xerces.util.SecurityManager;
import mf.org.apache.xerces.util.SymbolTable;
import mf.org.apache.xerces.xni.grammars.XMLGrammarPool;
import mf.org.apache.xerces.xni.parser.XMLComponentManager;

public class SecurityConfiguration extends XIncludeAwareParserConfiguration {
    protected static final String SECURITY_MANAGER_PROPERTY = "http://apache.org/xml/properties/security-manager";

    public SecurityConfiguration() {
        this(null, null, null);
    }

    public SecurityConfiguration(SymbolTable symbolTable) {
        this(symbolTable, null, null);
    }

    public SecurityConfiguration(SymbolTable symbolTable, XMLGrammarPool grammarPool) {
        this(symbolTable, grammarPool, null);
    }

    public SecurityConfiguration(SymbolTable symbolTable, XMLGrammarPool grammarPool, XMLComponentManager parentSettings) {
        super(symbolTable, grammarPool, parentSettings);
        setProperty(SECURITY_MANAGER_PROPERTY, new SecurityManager());
    }
}
