package com.fyber.utils;

import android.support.annotation.NonNull;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/* compiled from: ViewStringMap */
public final class C2684x implements Map<String, String> {
    private final Map<String, Object> f6672a;

    public final /* bridge */ /* synthetic */ Object put(Object obj, Object obj2) {
        return null;
    }

    public final /* bridge */ /* synthetic */ Object remove(Object obj) {
        return null;
    }

    public C2684x(Map<String, Object> map) {
        this.f6672a = map;
    }

    public final void clear() {
    }

    public final boolean containsKey(Object obj) {
        return this.f6672a.containsKey(obj);
    }

    public final boolean containsValue(Object obj) {
        return false;
    }

    @NonNull
    public final Set<Entry<String, String>> entrySet() {
        return null;
    }

    public final boolean isEmpty() {
        return this.f6672a.isEmpty();
    }

    @NonNull
    public final Set<String> keySet() {
        return this.f6672a.keySet();
    }

    public final void putAll(Map<? extends String, ? extends String> map) {
    }

    public final int size() {
        return this.f6672a.size();
    }

    @NonNull
    public final Collection<String> values() {
        return Collections.emptyList();
    }

    public final /* synthetic */ Object get(Object obj) {
        Object obj2 = this.f6672a.get(obj);
        return obj2 != null ? obj2.toString() : null;
    }
}
