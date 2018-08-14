package mf.org.apache.xerces.impl.dtd;

import mf.org.apache.xerces.impl.XML11DTDScannerImpl;
import mf.org.apache.xerces.impl.XMLDTDScannerImpl;
import mf.org.apache.xerces.impl.XMLEntityManager;
import mf.org.apache.xerces.impl.XMLErrorReporter;
import mf.org.apache.xerces.util.SymbolTable;
import mf.org.apache.xerces.util.XML11Char;
import mf.org.apache.xerces.xni.grammars.XMLGrammarPool;
import mf.org.apache.xerces.xni.parser.XMLEntityResolver;

public class XML11DTDProcessor extends XMLDTDLoader {
    public XML11DTDProcessor(SymbolTable symbolTable) {
        super(symbolTable);
    }

    public XML11DTDProcessor(SymbolTable symbolTable, XMLGrammarPool grammarPool) {
        super(symbolTable, grammarPool);
    }

    XML11DTDProcessor(SymbolTable symbolTable, XMLGrammarPool grammarPool, XMLErrorReporter errorReporter, XMLEntityResolver entityResolver) {
        super(symbolTable, grammarPool, errorReporter, entityResolver);
    }

    protected boolean isValidNmtoken(String nmtoken) {
        return XML11Char.isXML11ValidNmtoken(nmtoken);
    }

    protected boolean isValidName(String name) {
        return XML11Char.isXML11ValidName(name);
    }

    protected XMLDTDScannerImpl createDTDScanner(SymbolTable symbolTable, XMLErrorReporter errorReporter, XMLEntityManager entityManager) {
        return new XML11DTDScannerImpl(symbolTable, errorReporter, entityManager);
    }

    protected short getScannerVersion() {
        return (short) 2;
    }
}
