package com.google.android.gms.common.internal;

import android.support.annotation.Nullable;
import java.util.Arrays;

public final class Objects {
    private Objects() {
        throw new AssertionError("Uninstantiable");
    }

    public static boolean equal(@Nullable Object obj, @Nullable Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static int hashCode(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    public static ToStringHelper toStringHelper(Object obj) {
        return new ToStringHelper(obj, null);
    }
}
