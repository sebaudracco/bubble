package com.google.android.gms.common.internal;

import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public final class Objects$ToStringHelper {
    private final List<String> zzul;
    private final Object zzum;

    private Objects$ToStringHelper(Object obj) {
        this.zzum = Preconditions.checkNotNull(obj);
        this.zzul = new ArrayList();
    }

    public final Objects$ToStringHelper add(String str, @Nullable Object obj) {
        List list = this.zzul;
        String str2 = (String) Preconditions.checkNotNull(str);
        String valueOf = String.valueOf(obj);
        list.add(new StringBuilder((String.valueOf(str2).length() + 1) + String.valueOf(valueOf).length()).append(str2).append("=").append(valueOf).toString());
        return this;
    }

    public final Objects$ToStringHelper addValue(@Nullable Object obj) {
        this.zzul.add(String.valueOf(obj));
        return this;
    }

    public final String toString() {
        StringBuilder append = new StringBuilder(100).append(this.zzum.getClass().getSimpleName()).append('{');
        int size = this.zzul.size();
        for (int i = 0; i < size; i++) {
            append.append((String) this.zzul.get(i));
            if (i < size - 1) {
                append.append(", ");
            }
        }
        return append.append('}').toString();
    }
}
