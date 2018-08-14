package mf.org.apache.xerces.jaxp.validation;

import mf.org.apache.xerces.xni.grammars.XMLGrammarPool;

public interface XSGrammarPoolContainer {
    Boolean getFeature(String str);

    XMLGrammarPool getGrammarPool();

    boolean isFullyComposed();
}
