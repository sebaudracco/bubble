package mf.org.apache.xerces.util;

import mf.org.apache.xerces.xni.grammars.Grammar;
import mf.org.apache.xerces.xni.grammars.XMLGrammarDescription;
import mf.org.apache.xerces.xni.grammars.XMLGrammarPool;

public class XMLGrammarPoolImpl implements XMLGrammarPool {
    private static final boolean DEBUG = false;
    protected static final int TABLE_SIZE = 11;
    protected int fGrammarCount;
    protected Entry[] fGrammars;
    protected boolean fPoolIsLocked;

    protected static final class Entry {
        public XMLGrammarDescription desc;
        public Grammar grammar;
        public int hash;
        public Entry next;

        protected Entry(int hash, XMLGrammarDescription desc, Grammar grammar, Entry next) {
            this.hash = hash;
            this.desc = desc;
            this.grammar = grammar;
            this.next = next;
        }

        protected void clear() {
            this.desc = null;
            this.grammar = null;
            if (this.next != null) {
                this.next.clear();
                this.next = null;
            }
        }
    }

    public XMLGrammarPoolImpl() {
        this.fGrammars = null;
        this.fGrammarCount = 0;
        this.fGrammars = new Entry[11];
        this.fPoolIsLocked = false;
    }

    public XMLGrammarPoolImpl(int initialCapacity) {
        this.fGrammars = null;
        this.fGrammarCount = 0;
        this.fGrammars = new Entry[initialCapacity];
        this.fPoolIsLocked = false;
    }

    public Grammar[] retrieveInitialGrammarSet(String grammarType) {
        Grammar[] toReturn;
        synchronized (this.fGrammars) {
            int grammarSize = this.fGrammars.length;
            Grammar[] tempGrammars = new Grammar[this.fGrammarCount];
            int pos = 0;
            int i = 0;
            while (i < grammarSize) {
                Entry e = this.fGrammars[i];
                int pos2 = pos;
                while (e != null) {
                    if (e.desc.getGrammarType().equals(grammarType)) {
                        pos = pos2 + 1;
                        tempGrammars[pos2] = e.grammar;
                    } else {
                        pos = pos2;
                    }
                    e = e.next;
                    pos2 = pos;
                }
                i++;
                pos = pos2;
            }
            toReturn = new Grammar[pos];
            System.arraycopy(tempGrammars, 0, toReturn, 0, pos);
        }
        return toReturn;
    }

    public void cacheGrammars(String grammarType, Grammar[] grammars) {
        if (!this.fPoolIsLocked) {
            for (Grammar putGrammar : grammars) {
                putGrammar(putGrammar);
            }
        }
    }

    public Grammar retrieveGrammar(XMLGrammarDescription desc) {
        return getGrammar(desc);
    }

    public void putGrammar(Grammar grammar) {
        if (!this.fPoolIsLocked) {
            synchronized (this.fGrammars) {
                XMLGrammarDescription desc = grammar.getGrammarDescription();
                int hash = hashCode(desc);
                int index = (Integer.MAX_VALUE & hash) % this.fGrammars.length;
                Entry entry = this.fGrammars[index];
                while (entry != null) {
                    if (entry.hash == hash && equals(entry.desc, desc)) {
                        entry.grammar = grammar;
                        return;
                    }
                    entry = entry.next;
                }
                this.fGrammars[index] = new Entry(hash, desc, grammar, this.fGrammars[index]);
                this.fGrammarCount++;
            }
        }
    }

    public Grammar getGrammar(XMLGrammarDescription desc) {
        synchronized (this.fGrammars) {
            int hash = hashCode(desc);
            Entry entry = this.fGrammars[(Integer.MAX_VALUE & hash) % this.fGrammars.length];
            while (entry != null) {
                if (entry.hash == hash && equals(entry.desc, desc)) {
                    Grammar grammar = entry.grammar;
                    return grammar;
                }
                entry = entry.next;
            }
            return null;
        }
    }

    public Grammar removeGrammar(XMLGrammarDescription desc) {
        Grammar grammar = null;
        synchronized (this.fGrammars) {
            int hash = hashCode(desc);
            int index = (Integer.MAX_VALUE & hash) % this.fGrammars.length;
            Entry entry = this.fGrammars[index];
            Entry prev = null;
            while (entry != null) {
                if (entry.hash == hash && equals(entry.desc, desc)) {
                    if (prev != null) {
                        prev.next = entry.next;
                    } else {
                        this.fGrammars[index] = entry.next;
                    }
                    grammar = entry.grammar;
                    entry.grammar = null;
                    this.fGrammarCount--;
                } else {
                    prev = entry;
                    entry = entry.next;
                }
            }
        }
        return grammar;
    }

    public boolean containsGrammar(XMLGrammarDescription desc) {
        synchronized (this.fGrammars) {
            int hash = hashCode(desc);
            Entry entry = this.fGrammars[(Integer.MAX_VALUE & hash) % this.fGrammars.length];
            while (entry != null) {
                if (entry.hash == hash && equals(entry.desc, desc)) {
                    return true;
                }
                entry = entry.next;
            }
            return false;
        }
    }

    public void lockPool() {
        this.fPoolIsLocked = true;
    }

    public void unlockPool() {
        this.fPoolIsLocked = false;
    }

    public void clear() {
        for (int i = 0; i < this.fGrammars.length; i++) {
            if (this.fGrammars[i] != null) {
                this.fGrammars[i].clear();
                this.fGrammars[i] = null;
            }
        }
        this.fGrammarCount = 0;
    }

    public boolean equals(XMLGrammarDescription desc1, XMLGrammarDescription desc2) {
        return desc1.equals(desc2);
    }

    public int hashCode(XMLGrammarDescription desc) {
        return desc.hashCode();
    }
}
