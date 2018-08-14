package mf.org.apache.xerces.xni.grammars;

public interface XMLGrammarPool {
    void cacheGrammars(String str, Grammar[] grammarArr);

    void clear();

    void lockPool();

    Grammar retrieveGrammar(XMLGrammarDescription xMLGrammarDescription);

    Grammar[] retrieveInitialGrammarSet(String str);

    void unlockPool();
}
