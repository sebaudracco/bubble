package mf.org.apache.xerces.jaxp.validation;

import mf.org.apache.xerces.xni.grammars.XMLGrammarPool;

final class XMLSchema extends AbstractXMLSchema {
    private final boolean fFullyComposed;
    private final XMLGrammarPool fGrammarPool;

    public XMLSchema(XMLGrammarPool grammarPool) {
        this(grammarPool, true);
    }

    public XMLSchema(XMLGrammarPool grammarPool, boolean fullyComposed) {
        this.fGrammarPool = grammarPool;
        this.fFullyComposed = fullyComposed;
    }

    public XMLGrammarPool getGrammarPool() {
        return this.fGrammarPool;
    }

    public boolean isFullyComposed() {
        return this.fFullyComposed;
    }
}
