package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public final class zzp {
    public final List<zzl> allHeaders;
    public final byte[] data;
    public final int statusCode;
    public final Map<String, String> zzab;
    public final boolean zzac;
    private final long zzad;

    private zzp(int i, byte[] bArr, Map<String, String> map, List<zzl> list, boolean z, long j) {
        this.statusCode = i;
        this.data = bArr;
        this.zzab = map;
        if (list == null) {
            this.allHeaders = null;
        } else {
            this.allHeaders = Collections.unmodifiableList(list);
        }
        this.zzac = z;
        this.zzad = j;
    }

    @Deprecated
    public zzp(int i, byte[] bArr, Map<String, String> map, boolean z, long j) {
        List list;
        if (map == null) {
            list = null;
        } else if (map.isEmpty()) {
            list = Collections.emptyList();
        } else {
            list = new ArrayList(map.size());
            for (Entry entry : map.entrySet()) {
                list.add(new zzl((String) entry.getKey(), (String) entry.getValue()));
            }
        }
        this(i, bArr, map, list, z, j);
    }

    public zzp(int i, byte[] bArr, boolean z, long j, List<zzl> list) {
        Map map;
        if (list == null) {
            map = null;
        } else if (list.isEmpty()) {
            map = Collections.emptyMap();
        } else {
            map = new TreeMap(String.CASE_INSENSITIVE_ORDER);
            for (zzl com_google_android_gms_internal_ads_zzl : list) {
                map.put(com_google_android_gms_internal_ads_zzl.getName(), com_google_android_gms_internal_ads_zzl.getValue());
            }
        }
        this(i, bArr, map, list, z, j);
    }

    @Deprecated
    public zzp(byte[] bArr, Map<String, String> map) {
        this(200, bArr, (Map) map, false, 0);
    }
}
