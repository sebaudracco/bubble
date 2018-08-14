package com.oneaudience.sdk.p135c.p137b;

import java.util.Collection;
import java.util.Map;

public class C3825b {
    public static final boolean m12229a(Collection collection) {
        return collection == null || collection.size() == 0;
    }

    public static final boolean m12230a(Map<?, ?> map) {
        return map == null || map.size() == 0;
    }

    public static final boolean m12231b(Collection collection) {
        return !C3825b.m12229a(collection);
    }

    public static final boolean m12232b(Map<?, ?> map) {
        return !C3825b.m12230a((Map) map);
    }
}
