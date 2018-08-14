package mf.org.apache.xerces.jaxp.validation;

import java.lang.ref.WeakReference;
import mf.org.apache.xerces.xni.grammars.XMLGrammarPool;

final class WeakReferenceXMLSchema extends AbstractXMLSchema {
    private WeakReference fGrammarPool = new WeakReference(null);

    public synchronized XMLGrammarPool getGrammarPool() {
        XMLGrammarPool grammarPool;
        grammarPool = (XMLGrammarPool) this.fGrammarPool.get();
        if (grammarPool == null) {
            grammarPool = new SoftReferenceGrammarPool();
            this.fGrammarPool = new WeakReference(grammarPool);
        }
        return grammarPool;
    }

    public boolean isFullyComposed() {
        return false;
    }
}
