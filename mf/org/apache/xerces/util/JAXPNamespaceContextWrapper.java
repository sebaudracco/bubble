package mf.org.apache.xerces.util;

import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.TreeSet;
import java.util.Vector;
import mf.org.apache.xerces.xni.NamespaceContext;

public final class JAXPNamespaceContextWrapper implements NamespaceContext {
    private final Vector fAllPrefixes = new Vector();
    private int[] fContext = new int[8];
    private int fCurrentContext;
    private mf.javax.xml.namespace.NamespaceContext fNamespaceContext;
    private List fPrefixes;
    private SymbolTable fSymbolTable;

    public JAXPNamespaceContextWrapper(SymbolTable symbolTable) {
        setSymbolTable(symbolTable);
    }

    public void setNamespaceContext(mf.javax.xml.namespace.NamespaceContext context) {
        this.fNamespaceContext = context;
    }

    public mf.javax.xml.namespace.NamespaceContext getNamespaceContext() {
        return this.fNamespaceContext;
    }

    public void setSymbolTable(SymbolTable symbolTable) {
        this.fSymbolTable = symbolTable;
    }

    public SymbolTable getSymbolTable() {
        return this.fSymbolTable;
    }

    public void setDeclaredPrefixes(List prefixes) {
        this.fPrefixes = prefixes;
    }

    public List getDeclaredPrefixes() {
        return this.fPrefixes;
    }

    public String getURI(String prefix) {
        if (this.fNamespaceContext != null) {
            String uri = this.fNamespaceContext.getNamespaceURI(prefix);
            if (!(uri == null || "".equals(uri))) {
                return this.fSymbolTable != null ? this.fSymbolTable.addSymbol(uri) : uri.intern();
            }
        }
        return null;
    }

    public String getPrefix(String uri) {
        if (this.fNamespaceContext == null) {
            return null;
        }
        if (uri == null) {
            uri = "";
        }
        String prefix = this.fNamespaceContext.getPrefix(uri);
        if (prefix == null) {
            prefix = "";
        }
        return this.fSymbolTable != null ? this.fSymbolTable.addSymbol(prefix) : prefix.intern();
    }

    public Enumeration getAllPrefixes() {
        return Collections.enumeration(new TreeSet(this.fAllPrefixes));
    }

    public void pushContext() {
        if (this.fCurrentContext + 1 == this.fContext.length) {
            int[] contextarray = new int[(this.fContext.length * 2)];
            System.arraycopy(this.fContext, 0, contextarray, 0, this.fContext.length);
            this.fContext = contextarray;
        }
        int[] iArr = this.fContext;
        int i = this.fCurrentContext + 1;
        this.fCurrentContext = i;
        iArr[i] = this.fAllPrefixes.size();
        if (this.fPrefixes != null) {
            this.fAllPrefixes.addAll(this.fPrefixes);
        }
    }

    public void popContext() {
        Vector vector = this.fAllPrefixes;
        int[] iArr = this.fContext;
        int i = this.fCurrentContext;
        this.fCurrentContext = i - 1;
        vector.setSize(iArr[i]);
    }

    public boolean declarePrefix(String prefix, String uri) {
        return true;
    }

    public int getDeclaredPrefixCount() {
        return this.fPrefixes != null ? this.fPrefixes.size() : 0;
    }

    public String getDeclaredPrefixAt(int index) {
        return (String) this.fPrefixes.get(index);
    }

    public void reset() {
        this.fCurrentContext = 0;
        this.fContext[this.fCurrentContext] = 0;
        this.fAllPrefixes.clear();
    }
}
