package com.google.android.gms.internal.ads;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

class zzbdp<K extends Comparable<K>, V> extends AbstractMap<K, V> {
    private boolean zzdqy;
    private final int zzdyj;
    private List<zzbdw> zzdyk;
    private Map<K, V> zzdyl;
    private volatile zzbdy zzdym;
    private Map<K, V> zzdyn;
    private volatile zzbds zzdyo;

    private zzbdp(int i) {
        this.zzdyj = i;
        this.zzdyk = Collections.emptyList();
        this.zzdyl = Collections.emptyMap();
        this.zzdyn = Collections.emptyMap();
    }

    private final int zza(K k) {
        int compareTo;
        int size = this.zzdyk.size() - 1;
        if (size >= 0) {
            compareTo = k.compareTo((Comparable) ((zzbdw) this.zzdyk.get(size)).getKey());
            if (compareTo > 0) {
                return -(size + 2);
            }
            if (compareTo == 0) {
                return size;
            }
        }
        int i = 0;
        int i2 = size;
        while (i <= i2) {
            size = (i + i2) / 2;
            compareTo = k.compareTo((Comparable) ((zzbdw) this.zzdyk.get(size)).getKey());
            if (compareTo < 0) {
                i2 = size - 1;
            } else if (compareTo <= 0) {
                return size;
            } else {
                i = size + 1;
            }
        }
        return -(i + 1);
    }

    private final void zzafv() {
        if (this.zzdqy) {
            throw new UnsupportedOperationException();
        }
    }

    private final SortedMap<K, V> zzafw() {
        zzafv();
        if (this.zzdyl.isEmpty() && !(this.zzdyl instanceof TreeMap)) {
            this.zzdyl = new TreeMap();
            this.zzdyn = ((TreeMap) this.zzdyl).descendingMap();
        }
        return (SortedMap) this.zzdyl;
    }

    static <FieldDescriptorType extends zzbbi<FieldDescriptorType>> zzbdp<FieldDescriptorType, Object> zzcx(int i) {
        return new zzbdq(i);
    }

    private final V zzcz(int i) {
        zzafv();
        V value = ((zzbdw) this.zzdyk.remove(i)).getValue();
        if (!this.zzdyl.isEmpty()) {
            Iterator it = zzafw().entrySet().iterator();
            this.zzdyk.add(new zzbdw(this, (Entry) it.next()));
            it.remove();
        }
        return value;
    }

    public void clear() {
        zzafv();
        if (!this.zzdyk.isEmpty()) {
            this.zzdyk.clear();
        }
        if (!this.zzdyl.isEmpty()) {
            this.zzdyl.clear();
        }
    }

    public boolean containsKey(Object obj) {
        Comparable comparable = (Comparable) obj;
        return zza(comparable) >= 0 || this.zzdyl.containsKey(comparable);
    }

    public Set<Entry<K, V>> entrySet() {
        if (this.zzdym == null) {
            this.zzdym = new zzbdy();
        }
        return this.zzdym;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzbdp)) {
            return super.equals(obj);
        }
        zzbdp com_google_android_gms_internal_ads_zzbdp = (zzbdp) obj;
        int size = size();
        if (size != com_google_android_gms_internal_ads_zzbdp.size()) {
            return false;
        }
        int zzafs = zzafs();
        if (zzafs != com_google_android_gms_internal_ads_zzbdp.zzafs()) {
            return entrySet().equals(com_google_android_gms_internal_ads_zzbdp.entrySet());
        }
        for (int i = 0; i < zzafs; i++) {
            if (!zzcy(i).equals(com_google_android_gms_internal_ads_zzbdp.zzcy(i))) {
                return false;
            }
        }
        return zzafs != size ? this.zzdyl.equals(com_google_android_gms_internal_ads_zzbdp.zzdyl) : true;
    }

    public V get(Object obj) {
        Comparable comparable = (Comparable) obj;
        int zza = zza(comparable);
        return zza >= 0 ? ((zzbdw) this.zzdyk.get(zza)).getValue() : this.zzdyl.get(comparable);
    }

    public int hashCode() {
        int i = 0;
        for (int i2 = 0; i2 < zzafs(); i2++) {
            i += ((zzbdw) this.zzdyk.get(i2)).hashCode();
        }
        return this.zzdyl.size() > 0 ? this.zzdyl.hashCode() + i : i;
    }

    public final boolean isImmutable() {
        return this.zzdqy;
    }

    public /* synthetic */ Object put(Object obj, Object obj2) {
        return zza((Comparable) obj, obj2);
    }

    public V remove(Object obj) {
        zzafv();
        Comparable comparable = (Comparable) obj;
        int zza = zza(comparable);
        return zza >= 0 ? zzcz(zza) : this.zzdyl.isEmpty() ? null : this.zzdyl.remove(comparable);
    }

    public int size() {
        return this.zzdyk.size() + this.zzdyl.size();
    }

    public final V zza(K k, V v) {
        zzafv();
        int zza = zza((Comparable) k);
        if (zza >= 0) {
            return ((zzbdw) this.zzdyk.get(zza)).setValue(v);
        }
        zzafv();
        if (this.zzdyk.isEmpty() && !(this.zzdyk instanceof ArrayList)) {
            this.zzdyk = new ArrayList(this.zzdyj);
        }
        int i = -(zza + 1);
        if (i >= this.zzdyj) {
            return zzafw().put(k, v);
        }
        if (this.zzdyk.size() == this.zzdyj) {
            zzbdw com_google_android_gms_internal_ads_zzbdw = (zzbdw) this.zzdyk.remove(this.zzdyj - 1);
            zzafw().put((Comparable) com_google_android_gms_internal_ads_zzbdw.getKey(), com_google_android_gms_internal_ads_zzbdw.getValue());
        }
        this.zzdyk.add(i, new zzbdw(this, k, v));
        return null;
    }

    public void zzaaz() {
        if (!this.zzdqy) {
            this.zzdyl = this.zzdyl.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(this.zzdyl);
            this.zzdyn = this.zzdyn.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(this.zzdyn);
            this.zzdqy = true;
        }
    }

    public final int zzafs() {
        return this.zzdyk.size();
    }

    public final Iterable<Entry<K, V>> zzaft() {
        return this.zzdyl.isEmpty() ? zzbdt.zzafy() : this.zzdyl.entrySet();
    }

    final Set<Entry<K, V>> zzafu() {
        if (this.zzdyo == null) {
            this.zzdyo = new zzbds();
        }
        return this.zzdyo;
    }

    public final Entry<K, V> zzcy(int i) {
        return (Entry) this.zzdyk.get(i);
    }
}
