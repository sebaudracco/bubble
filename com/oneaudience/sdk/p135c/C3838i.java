package com.oneaudience.sdk.p135c;

import android.net.Uri;
import android.net.Uri.Builder;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

public class C3838i {
    public static final Builder m12270a(Builder builder, Map<String, Object> map) {
        Set<Entry> entrySet = map.entrySet();
        builder.appendQueryParameter("randval", UUID.randomUUID().toString());
        for (Entry entry : entrySet) {
            if (entry.getValue() != null) {
                builder.appendQueryParameter((String) entry.getKey(), entry.getValue().toString());
            }
        }
        return builder;
    }

    public static final Uri m12271a(Uri uri, Map<String, Object> map) {
        return C3838i.m12270a(uri.buildUpon(), (Map) map).build();
    }
}
