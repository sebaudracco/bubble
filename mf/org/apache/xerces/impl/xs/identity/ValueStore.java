package mf.org.apache.xerces.impl.xs.identity;

import mf.org.apache.xerces.xs.ShortList;

public interface ValueStore {
    void addValue(Field field, boolean z, Object obj, short s, ShortList shortList);

    void reportError(String str, Object[] objArr);
}
