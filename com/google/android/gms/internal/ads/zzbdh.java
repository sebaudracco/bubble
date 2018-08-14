package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.List;

final class zzbdh<E> extends zzbab<E> {
    private static final zzbdh<Object> zzdxd;
    private final List<E> zzdvp;

    static {
        zzbab com_google_android_gms_internal_ads_zzbdh = new zzbdh();
        zzdxd = com_google_android_gms_internal_ads_zzbdh;
        com_google_android_gms_internal_ads_zzbdh.zzaaz();
    }

    zzbdh() {
        this(new ArrayList(10));
    }

    private zzbdh(List<E> list) {
        this.zzdvp = list;
    }

    public static <E> zzbdh<E> zzaep() {
        return zzdxd;
    }

    public final void add(int i, E e) {
        zzaba();
        this.zzdvp.add(i, e);
        this.modCount++;
    }

    public final E get(int i) {
        return this.zzdvp.get(i);
    }

    public final E remove(int i) {
        zzaba();
        E remove = this.zzdvp.remove(i);
        this.modCount++;
        return remove;
    }

    public final E set(int i, E e) {
        zzaba();
        E e2 = this.zzdvp.set(i, e);
        this.modCount++;
        return e2;
    }

    public final int size() {
        return this.zzdvp.size();
    }

    public final /* synthetic */ zzbbt zzbm(int i) {
        if (i < size()) {
            throw new IllegalArgumentException();
        }
        List arrayList = new ArrayList(i);
        arrayList.addAll(this.zzdvp);
        return new zzbdh(arrayList);
    }
}
