package mf.org.apache.xerces.impl.validation;

import java.util.HashMap;
import java.util.Locale;
import mf.org.apache.xerces.impl.dv.ValidationContext;
import mf.org.apache.xerces.util.SymbolTable;
import mf.org.apache.xerces.xni.NamespaceContext;

public class ValidationState implements ValidationContext {
    private static final Object fNullValue = new Object();
    private EntityState fEntityState = null;
    private boolean fExtraChecking = true;
    private boolean fFacetChecking = true;
    private final HashMap fIdRefTable = new HashMap();
    private final HashMap fIdTable = new HashMap();
    private Locale fLocale = null;
    private NamespaceContext fNamespaceContext = null;
    private boolean fNamespaces = true;
    private boolean fNormalize = true;
    private SymbolTable fSymbolTable = null;

    public void setExtraChecking(boolean newValue) {
        this.fExtraChecking = newValue;
    }

    public void setFacetChecking(boolean newValue) {
        this.fFacetChecking = newValue;
    }

    public void setNormalizationRequired(boolean newValue) {
        this.fNormalize = newValue;
    }

    public void setUsingNamespaces(boolean newValue) {
        this.fNamespaces = newValue;
    }

    public void setEntityState(EntityState state) {
        this.fEntityState = state;
    }

    public void setNamespaceSupport(NamespaceContext namespace) {
        this.fNamespaceContext = namespace;
    }

    public void setSymbolTable(SymbolTable sTable) {
        this.fSymbolTable = sTable;
    }

    public String checkIDRefID() {
        for (String key : this.fIdRefTable.keySet()) {
            if (!this.fIdTable.containsKey(key)) {
                return key;
            }
        }
        return null;
    }

    public void reset() {
        this.fExtraChecking = true;
        this.fFacetChecking = true;
        this.fNamespaces = true;
        this.fIdTable.clear();
        this.fIdRefTable.clear();
        this.fEntityState = null;
        this.fNamespaceContext = null;
        this.fSymbolTable = null;
    }

    public void resetIDTables() {
        this.fIdTable.clear();
        this.fIdRefTable.clear();
    }

    public boolean needExtraChecking() {
        return this.fExtraChecking;
    }

    public boolean needFacetChecking() {
        return this.fFacetChecking;
    }

    public boolean needToNormalize() {
        return this.fNormalize;
    }

    public boolean useNamespaces() {
        return this.fNamespaces;
    }

    public boolean isEntityDeclared(String name) {
        if (this.fEntityState != null) {
            return this.fEntityState.isEntityDeclared(getSymbol(name));
        }
        return false;
    }

    public boolean isEntityUnparsed(String name) {
        if (this.fEntityState != null) {
            return this.fEntityState.isEntityUnparsed(getSymbol(name));
        }
        return false;
    }

    public boolean isIdDeclared(String name) {
        return this.fIdTable.containsKey(name);
    }

    public void addId(String name) {
        this.fIdTable.put(name, fNullValue);
    }

    public void addIdRef(String name) {
        this.fIdRefTable.put(name, fNullValue);
    }

    public String getSymbol(String symbol) {
        if (this.fSymbolTable != null) {
            return this.fSymbolTable.addSymbol(symbol);
        }
        return symbol.intern();
    }

    public String getURI(String prefix) {
        if (this.fNamespaceContext != null) {
            return this.fNamespaceContext.getURI(prefix);
        }
        return null;
    }

    public void setLocale(Locale locale) {
        this.fLocale = locale;
    }

    public Locale getLocale() {
        return this.fLocale;
    }
}
