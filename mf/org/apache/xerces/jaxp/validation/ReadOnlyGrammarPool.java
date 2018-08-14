package mf.org.apache.xerces.jaxp.validation;

import mf.org.apache.xerces.xni.grammars.Grammar;
import mf.org.apache.xerces.xni.grammars.XMLGrammarDescription;
import mf.org.apache.xerces.xni.grammars.XMLGrammarPool;

final class ReadOnlyGrammarPool implements XMLGrammarPool {
    private final XMLGrammarPool core;

    public ReadOnlyGrammarPool(XMLGrammarPool pool) {
        this.core = pool;
    }

    public void cacheGrammars(String grammarType, Grammar[] grammars) {
    }

    public void clear() {
    }

    public void lockPool() {
    }

    public Grammar retrieveGrammar(XMLGrammarDescription desc) {
        return this.core.retrieveGrammar(desc);
    }

    public Grammar[] retrieveInitialGrammarSet(String grammarType) {
        return this.core.retrieveInitialGrammarSet(grammarType);
    }

    public void unlockPool() {
    }
}
