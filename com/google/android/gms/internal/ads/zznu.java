package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import android.text.TextUtils;

final class zznu extends zznr {
    zznu() {
    }

    @Nullable
    private static String zzam(@Nullable String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        int i = 0;
        int length = str.length();
        while (i < str.length() && str.charAt(i) == ',') {
            i++;
        }
        while (length > 0 && str.charAt(length - 1) == ',') {
            length--;
        }
        return length < i ? null : (i == 0 && length == str.length()) ? str : str.substring(i, length);
    }

    public final String zzd(@Nullable String str, String str2) {
        String zzam = zzam(str);
        String zzam2 = zzam(str2);
        return TextUtils.isEmpty(zzam) ? zzam2 : TextUtils.isEmpty(zzam2) ? zzam : new StringBuilder((String.valueOf(zzam).length() + 1) + String.valueOf(zzam2).length()).append(zzam).append(",").append(zzam2).toString();
    }
}
