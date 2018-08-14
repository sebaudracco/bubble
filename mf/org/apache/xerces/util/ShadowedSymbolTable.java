package mf.org.apache.xerces.util;

public final class ShadowedSymbolTable extends SymbolTable {
    protected SymbolTable fSymbolTable;

    public ShadowedSymbolTable(SymbolTable symbolTable) {
        this.fSymbolTable = symbolTable;
    }

    public String addSymbol(String symbol) {
        if (this.fSymbolTable.containsSymbol(symbol)) {
            return this.fSymbolTable.addSymbol(symbol);
        }
        return super.addSymbol(symbol);
    }

    public String addSymbol(char[] buffer, int offset, int length) {
        if (this.fSymbolTable.containsSymbol(buffer, offset, length)) {
            return this.fSymbolTable.addSymbol(buffer, offset, length);
        }
        return super.addSymbol(buffer, offset, length);
    }

    public int hash(String symbol) {
        return this.fSymbolTable.hash(symbol);
    }

    public int hash(char[] buffer, int offset, int length) {
        return this.fSymbolTable.hash(buffer, offset, length);
    }
}
