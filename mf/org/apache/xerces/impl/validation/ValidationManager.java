package mf.org.apache.xerces.impl.validation;

import java.util.ArrayList;

public class ValidationManager {
    protected boolean fCachedDTD = false;
    protected boolean fGrammarFound = false;
    protected final ArrayList fVSs = new ArrayList();

    public final void addValidationState(ValidationState vs) {
        this.fVSs.add(vs);
    }

    public final void setEntityState(EntityState state) {
        for (int i = this.fVSs.size() - 1; i >= 0; i--) {
            ((ValidationState) this.fVSs.get(i)).setEntityState(state);
        }
    }

    public final void setGrammarFound(boolean grammar) {
        this.fGrammarFound = grammar;
    }

    public final boolean isGrammarFound() {
        return this.fGrammarFound;
    }

    public final void setCachedDTD(boolean cachedDTD) {
        this.fCachedDTD = cachedDTD;
    }

    public final boolean isCachedDTD() {
        return this.fCachedDTD;
    }

    public final void reset() {
        this.fVSs.clear();
        this.fGrammarFound = false;
        this.fCachedDTD = false;
    }
}
