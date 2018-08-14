package com.yandex.metrica.impl.ob;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

class es implements ev {
    private Map<String, Set<String>> f12344a = new ConcurrentHashMap();
    private volatile AtomicLong f12345b = new AtomicLong();

    public void mo7097a(String str, String[] strArr) {
        if (!this.f12344a.keySet().contains(str)) {
            this.f12344a.put(str, new HashSet(Arrays.asList(strArr)));
            m15947d();
        }
    }

    public Map<String, Set<String>> m15955c() {
        Map<String, Set<String>> hashMap = new HashMap();
        for (String str : this.f12344a.keySet()) {
            hashMap.put(str, mo7095a(str));
        }
        return hashMap;
    }

    public void m15952a(Map<String, Set<String>> map) {
        this.f12344a = new ConcurrentHashMap(map);
        m15947d();
    }

    public Set<String> mo7095a(String str) {
        Set set = (Set) this.f12344a.get(str);
        return set == null ? null : new HashSet(set);
    }

    public boolean mo7098a(String str, String str2) {
        Set set = (Set) this.f12344a.get(str);
        if (set == null) {
            set = new HashSet();
            this.f12344a.put(str, set);
        }
        m15947d();
        return set.add(str2);
    }

    public void mo7096a(String str, Set<String> set) {
        this.f12344a.put(str, new HashSet(set));
        m15947d();
    }

    public long mo7094a() {
        return this.f12345b.get();
    }

    public void mo7099b() {
        m15947d();
    }

    private void m15947d() {
        this.f12345b.set(System.currentTimeMillis());
    }
}
