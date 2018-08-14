package mf.org.apache.xerces.xni.grammars;

import mf.org.apache.xerces.xs.XSModel;

public interface XSGrammar extends Grammar {
    XSModel toXSModel();

    XSModel toXSModel(XSGrammar[] xSGrammarArr);
}
