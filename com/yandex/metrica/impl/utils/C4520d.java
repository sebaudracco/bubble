package com.yandex.metrica.impl.utils;

import java.util.Collection;
import java.util.HashSet;

public class C4520d {
    public static boolean m16253a(Collection<?> collection, Collection<?> collection2) {
        if (collection == null && collection2 == null) {
            return true;
        }
        if (collection == null || collection2 == null) {
            return false;
        }
        if (collection.size() != collection2.size()) {
            return false;
        }
        if (collection instanceof HashSet) {
            collection = (HashSet) collection;
        } else if (collection2 instanceof HashSet) {
            Collection<?> collection3 = collection;
            r5 = (HashSet) collection2;
            collection2 = collection3;
        } else {
            r5 = new HashSet(collection);
        }
        for (Object contains : r6) {
            if (!collection.contains(contains)) {
                return false;
            }
        }
        return true;
    }
}
