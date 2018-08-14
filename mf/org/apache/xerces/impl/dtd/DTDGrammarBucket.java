package mf.org.apache.xerces.impl.dtd;

import java.util.Hashtable;
import mf.org.apache.xerces.xni.grammars.XMLGrammarDescription;

public class DTDGrammarBucket {
    protected DTDGrammar fActiveGrammar;
    protected final Hashtable fGrammars = new Hashtable();
    protected boolean fIsStandalone;

    public void putGrammar(DTDGrammar grammar) {
        this.fGrammars.put((XMLDTDDescription) grammar.getGrammarDescription(), grammar);
    }

    public DTDGrammar getGrammar(XMLGrammarDescription desc) {
        return (DTDGrammar) this.fGrammars.get((XMLDTDDescription) desc);
    }

    public void clear() {
        this.fGrammars.clear();
        this.fActiveGrammar = null;
        this.fIsStandalone = false;
    }

    void setStandalone(boolean standalone) {
        this.fIsStandalone = standalone;
    }

    boolean getStandalone() {
        return this.fIsStandalone;
    }

    void setActiveGrammar(DTDGrammar grammar) {
        this.fActiveGrammar = grammar;
    }

    DTDGrammar getActiveGrammar() {
        return this.fActiveGrammar;
    }
}
