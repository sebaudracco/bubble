package mf.org.apache.xerces.util;

public final class SynchronizedSymbolTable extends SymbolTable {
    protected SymbolTable fSymbolTable;

    public SynchronizedSymbolTable(SymbolTable symbolTable) {
        this.fSymbolTable = symbolTable;
    }

    public SynchronizedSymbolTable() {
        this.fSymbolTable = new SymbolTable();
    }

    public SynchronizedSymbolTable(int size) {
        this.fSymbolTable = new SymbolTable(size);
    }

    public String addSymbol(String symbol) {
        String addSymbol;
        synchronized (this.fSymbolTable) {
            addSymbol = this.fSymbolTable.addSymbol(symbol);
        }
        return addSymbol;
    }

    public String addSymbol(char[] buffer, int offset, int length) {
        String addSymbol;
        synchronized (this.fSymbolTable) {
            addSymbol = this.fSymbolTable.addSymbol(buffer, offset, length);
        }
        return addSymbol;
    }

    public boolean containsSymbol(String symbol) {
        boolean containsSymbol;
        synchronized (this.fSymbolTable) {
            containsSymbol = this.fSymbolTable.containsSymbol(symbol);
        }
        return containsSymbol;
    }

    public boolean containsSymbol(char[] buffer, int offset, int length) {
        boolean containsSymbol;
        synchronized (this.fSymbolTable) {
            containsSymbol = this.fSymbolTable.containsSymbol(buffer, offset, length);
        }
        return containsSymbol;
    }
}
