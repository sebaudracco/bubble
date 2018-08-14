package mf.org.apache.xerces.jaxp.validation;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import mf.org.apache.xerces.xni.grammars.Grammar;
import mf.org.apache.xerces.xni.grammars.XMLGrammarDescription;
import mf.org.apache.xerces.xni.grammars.XMLGrammarPool;
import mf.org.apache.xerces.xni.grammars.XMLSchemaDescription;

final class SoftReferenceGrammarPool implements XMLGrammarPool {
    protected static final int TABLE_SIZE = 11;
    protected static final Grammar[] ZERO_LENGTH_GRAMMAR_ARRAY = new Grammar[0];
    protected int fGrammarCount;
    protected Entry[] fGrammars;
    protected boolean fPoolIsLocked;
    protected final ReferenceQueue fReferenceQueue;

    static final class Entry {
        public int bucket;
        public XMLGrammarDescription desc;
        public SoftGrammarReference grammar;
        public int hash;
        public Entry next;
        public Entry prev = null;

        protected Entry(int hash, int bucket, XMLGrammarDescription desc, Grammar grammar, Entry next, ReferenceQueue queue) {
            this.hash = hash;
            this.bucket = bucket;
            this.next = next;
            if (next != null) {
                next.prev = this;
            }
            this.desc = desc;
            this.grammar = new SoftGrammarReference(this, grammar, queue);
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

    static final class SoftGrammarReference extends SoftReference {
        public Entry entry;

        protected SoftGrammarReference(Entry entry, Grammar grammar, ReferenceQueue queue) {
            super(grammar, queue);
            this.entry = entry;
        }
    }

    public SoftReferenceGrammarPool() {
        this.fGrammars = null;
        this.fGrammarCount = 0;
        this.fReferenceQueue = new ReferenceQueue();
        this.fGrammars = new Entry[11];
        this.fPoolIsLocked = false;
    }

    public SoftReferenceGrammarPool(int initialCapacity) {
        this.fGrammars = null;
        this.fGrammarCount = 0;
        this.fReferenceQueue = new ReferenceQueue();
        this.fGrammars = new Entry[initialCapacity];
        this.fPoolIsLocked = false;
    }

    public Grammar[] retrieveInitialGrammarSet(String grammarType) {
        Grammar[] grammarArr;
        synchronized (this.fGrammars) {
            clean();
            grammarArr = ZERO_LENGTH_GRAMMAR_ARRAY;
        }
        return grammarArr;
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

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void putGrammar(mf.org.apache.xerces.xni.grammars.Grammar r9) {
        /*
        r8 = this;
        r4 = r8.fPoolIsLocked;
        if (r4 != 0) goto L_0x0038;
    L_0x0004:
        r7 = r8.fGrammars;
        monitor-enter(r7);
        r8.clean();	 Catch:{ all -> 0x0058 }
        r3 = r9.getGrammarDescription();	 Catch:{ all -> 0x0058 }
        r1 = r8.hashCode(r3);	 Catch:{ all -> 0x0058 }
        r4 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
        r4 = r4 & r1;
        r5 = r8.fGrammars;	 Catch:{ all -> 0x0058 }
        r5 = r5.length;	 Catch:{ all -> 0x0058 }
        r2 = r4 % r5;
        r4 = r8.fGrammars;	 Catch:{ all -> 0x0058 }
        r0 = r4[r2];	 Catch:{ all -> 0x0058 }
    L_0x001f:
        if (r0 != 0) goto L_0x0039;
    L_0x0021:
        r0 = new mf.org.apache.xerces.jaxp.validation.SoftReferenceGrammarPool$Entry;	 Catch:{ all -> 0x0058 }
        r4 = r8.fGrammars;	 Catch:{ all -> 0x0058 }
        r5 = r4[r2];	 Catch:{ all -> 0x0058 }
        r6 = r8.fReferenceQueue;	 Catch:{ all -> 0x0058 }
        r4 = r9;
        r0.<init>(r1, r2, r3, r4, r5, r6);	 Catch:{ all -> 0x0058 }
        r4 = r8.fGrammars;	 Catch:{ all -> 0x0058 }
        r4[r2] = r0;	 Catch:{ all -> 0x0058 }
        r4 = r8.fGrammarCount;	 Catch:{ all -> 0x0058 }
        r4 = r4 + 1;
        r8.fGrammarCount = r4;	 Catch:{ all -> 0x0058 }
        monitor-exit(r7);	 Catch:{ all -> 0x0058 }
    L_0x0038:
        return;
    L_0x0039:
        r4 = r0.hash;	 Catch:{ all -> 0x0058 }
        if (r4 != r1) goto L_0x005b;
    L_0x003d:
        r4 = r0.desc;	 Catch:{ all -> 0x0058 }
        r4 = r8.equals(r4, r3);	 Catch:{ all -> 0x0058 }
        if (r4 == 0) goto L_0x005b;
    L_0x0045:
        r4 = r0.grammar;	 Catch:{ all -> 0x0058 }
        r4 = r4.get();	 Catch:{ all -> 0x0058 }
        if (r4 == r9) goto L_0x0056;
    L_0x004d:
        r4 = new mf.org.apache.xerces.jaxp.validation.SoftReferenceGrammarPool$SoftGrammarReference;	 Catch:{ all -> 0x0058 }
        r5 = r8.fReferenceQueue;	 Catch:{ all -> 0x0058 }
        r4.<init>(r0, r9, r5);	 Catch:{ all -> 0x0058 }
        r0.grammar = r4;	 Catch:{ all -> 0x0058 }
    L_0x0056:
        monitor-exit(r7);	 Catch:{ all -> 0x0058 }
        goto L_0x0038;
    L_0x0058:
        r4 = move-exception;
        monitor-exit(r7);	 Catch:{ all -> 0x0058 }
        throw r4;
    L_0x005b:
        r0 = r0.next;	 Catch:{ all -> 0x0058 }
        goto L_0x001f;
        */
        throw new UnsupportedOperationException("Method not decompiled: mf.org.apache.xerces.jaxp.validation.SoftReferenceGrammarPool.putGrammar(mf.org.apache.xerces.xni.grammars.Grammar):void");
    }

    public Grammar getGrammar(XMLGrammarDescription desc) {
        synchronized (this.fGrammars) {
            clean();
            int hash = hashCode(desc);
            Entry entry = this.fGrammars[(Integer.MAX_VALUE & hash) % this.fGrammars.length];
            while (entry != null) {
                Grammar tempGrammar = (Grammar) entry.grammar.get();
                if (tempGrammar == null) {
                    removeEntry(entry);
                } else if (entry.hash == hash && equals(entry.desc, desc)) {
                    return tempGrammar;
                }
                entry = entry.next;
            }
            return null;
        }
    }

    public Grammar removeGrammar(XMLGrammarDescription desc) {
        synchronized (this.fGrammars) {
            clean();
            int hash = hashCode(desc);
            Entry entry = this.fGrammars[(Integer.MAX_VALUE & hash) % this.fGrammars.length];
            while (entry != null) {
                if (entry.hash == hash && equals(entry.desc, desc)) {
                    Grammar removeEntry = removeEntry(entry);
                    return removeEntry;
                }
                entry = entry.next;
            }
            return null;
        }
    }

    public boolean containsGrammar(XMLGrammarDescription desc) {
        synchronized (this.fGrammars) {
            clean();
            int hash = hashCode(desc);
            Entry entry = this.fGrammars[(Integer.MAX_VALUE & hash) % this.fGrammars.length];
            while (entry != null) {
                if (((Grammar) entry.grammar.get()) == null) {
                    removeEntry(entry);
                } else if (entry.hash == hash && equals(entry.desc, desc)) {
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
        if (!(desc1 instanceof XMLSchemaDescription)) {
            return desc1.equals(desc2);
        }
        if (!(desc2 instanceof XMLSchemaDescription)) {
            return false;
        }
        XMLSchemaDescription sd1 = (XMLSchemaDescription) desc1;
        XMLSchemaDescription sd2 = (XMLSchemaDescription) desc2;
        String targetNamespace = sd1.getTargetNamespace();
        if (targetNamespace != null) {
            if (!targetNamespace.equals(sd2.getTargetNamespace())) {
                return false;
            }
        } else if (sd2.getTargetNamespace() != null) {
            return false;
        }
        String expandedSystemId = sd1.getExpandedSystemId();
        if (expandedSystemId != null) {
            if (!expandedSystemId.equals(sd2.getExpandedSystemId())) {
                return false;
            }
        } else if (sd2.getExpandedSystemId() != null) {
            return false;
        }
        return true;
    }

    public int hashCode(XMLGrammarDescription desc) {
        int i = 0;
        if (!(desc instanceof XMLSchemaDescription)) {
            return desc.hashCode();
        }
        int hash;
        XMLSchemaDescription sd = (XMLSchemaDescription) desc;
        String targetNamespace = sd.getTargetNamespace();
        String expandedSystemId = sd.getExpandedSystemId();
        if (targetNamespace != null) {
            hash = targetNamespace.hashCode();
        } else {
            hash = 0;
        }
        if (expandedSystemId != null) {
            i = expandedSystemId.hashCode();
        }
        return hash ^ i;
    }

    private Grammar removeEntry(Entry entry) {
        if (entry.prev != null) {
            entry.prev.next = entry.next;
        } else {
            this.fGrammars[entry.bucket] = entry.next;
        }
        if (entry.next != null) {
            entry.next.prev = entry.prev;
        }
        this.fGrammarCount--;
        entry.grammar.entry = null;
        return (Grammar) entry.grammar.get();
    }

    private void clean() {
        Reference ref = this.fReferenceQueue.poll();
        while (ref != null) {
            Entry entry = ((SoftGrammarReference) ref).entry;
            if (entry != null) {
                removeEntry(entry);
            }
            ref = this.fReferenceQueue.poll();
        }
    }
}
