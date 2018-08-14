package com.facebook.ads;

import com.facebook.ads.internal.p033n.C2021d;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public enum NativeAd$MediaCacheFlag {
    NONE(C2021d.NONE),
    ICON(C2021d.ICON),
    IMAGE(C2021d.IMAGE),
    VIDEO(C2021d.VIDEO);
    
    public static final EnumSet<NativeAd$MediaCacheFlag> ALL = null;
    private final C2021d f4017a;

    static {
        ALL = EnumSet.allOf(NativeAd$MediaCacheFlag.class);
    }

    private NativeAd$MediaCacheFlag(C2021d c2021d) {
        this.f4017a = c2021d;
    }

    public static Set<C2021d> setToInternalSet(EnumSet<NativeAd$MediaCacheFlag> enumSet) {
        Set<C2021d> hashSet = new HashSet();
        Iterator it = enumSet.iterator();
        while (it.hasNext()) {
            hashSet.add(((NativeAd$MediaCacheFlag) it.next()).m5494a());
        }
        return hashSet;
    }

    C2021d m5494a() {
        return this.f4017a;
    }

    public long getCacheFlagValue() {
        return this.f4017a.m6446a();
    }
}
