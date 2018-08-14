package com.google.android.gms.internal.measurement;

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

class zzaay<K extends Comparable<K>, V> extends AbstractMap<K, V> {
    private boolean zzbls;
    private final int zzbtx;
    private List<zzabd> zzbty;
    private Map<K, V> zzbtz;
    private volatile zzabf zzbua;
    private Map<K, V> zzbub;

    private zzaay(int i) {
        this.zzbtx = i;
        this.zzbty = Collections.emptyList();
        this.zzbtz = Collections.emptyMap();
        this.zzbub = Collections.emptyMap();
    }

    private final int zza(K k) {
        int compareTo;
        int size = this.zzbty.size() - 1;
        if (size >= 0) {
            compareTo = k.compareTo((Comparable) ((zzabd) this.zzbty.get(size)).getKey());
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
            compareTo = k.compareTo((Comparable) ((zzabd) this.zzbty.get(size)).getKey());
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

    static <FieldDescriptorType extends zzzo<FieldDescriptorType>> zzaay<FieldDescriptorType, Object> zzag(int i) {
        return new zzaaz(i);
    }

    private final V zzai(int i) {
        zzul();
        V value = ((zzabd) this.zzbty.remove(i)).getValue();
        if (!this.zzbtz.isEmpty()) {
            Iterator it = zzum().entrySet().iterator();
            this.zzbty.add(new zzabd(this, (Entry) it.next()));
            it.remove();
        }
        return value;
    }

    private final void zzul() {
        if (this.zzbls) {
            throw new UnsupportedOperationException();
        }
    }

    private final SortedMap<K, V> zzum() {
        zzul();
        if (this.zzbtz.isEmpty() && !(this.zzbtz instanceof TreeMap)) {
            this.zzbtz = new TreeMap();
            this.zzbub = ((TreeMap) this.zzbtz).descendingMap();
        }
        return (SortedMap) this.zzbtz;
    }

    public void clear() {
        zzul();
        if (!this.zzbty.isEmpty()) {
            this.zzbty.clear();
        }
        if (!this.zzbtz.isEmpty()) {
            this.zzbtz.clear();
        }
    }

    public boolean containsKey(Object obj) {
        Comparable comparable = (Comparable) obj;
        return zza(comparable) >= 0 || this.zzbtz.containsKey(comparable);
    }

    public Set<Entry<K, V>> entrySet() {
        if (this.zzbua == null) {
            this.zzbua = new zzabf();
        }
        return this.zzbua;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzaay)) {
            return super.equals(obj);
        }
        zzaay com_google_android_gms_internal_measurement_zzaay = (zzaay) obj;
        int size = size();
        if (size != com_google_android_gms_internal_measurement_zzaay.size()) {
            return false;
        }
        int zzuj = zzuj();
        if (zzuj != com_google_android_gms_internal_measurement_zzaay.zzuj()) {
            return entrySet().equals(com_google_android_gms_internal_measurement_zzaay.entrySet());
        }
        for (int i = 0; i < zzuj; i++) {
            if (!zzah(i).equals(com_google_android_gms_internal_measurement_zzaay.zzah(i))) {
                return false;
            }
        }
        return zzuj != size ? this.zzbtz.equals(com_google_android_gms_internal_measurement_zzaay.zzbtz) : true;
    }

    public V get(Object obj) {
        Comparable comparable = (Comparable) obj;
        int zza = zza(comparable);
        return zza >= 0 ? ((zzabd) this.zzbty.get(zza)).getValue() : this.zzbtz.get(comparable);
    }

    public int hashCode() {
        int i = 0;
        for (int i2 = 0; i2 < zzuj(); i2++) {
            i += ((zzabd) this.zzbty.get(i2)).hashCode();
        }
        return this.zzbtz.size() > 0 ? this.zzbtz.hashCode() + i : i;
    }

    public final boolean isImmutable() {
        return this.zzbls;
    }

    public /* synthetic */ Object put(Object obj, Object obj2) {
        return zza((Comparable) obj, obj2);
    }

    public V remove(Object obj) {
        zzul();
        Comparable comparable = (Comparable) obj;
        int zza = zza(comparable);
        return zza >= 0 ? zzai(zza) : this.zzbtz.isEmpty() ? null : this.zzbtz.remove(comparable);
    }

    public int size() {
        return this.zzbty.size() + this.zzbtz.size();
    }

    public final V zza(K k, V v) {
        zzul();
        int zza = zza((Comparable) k);
        if (zza >= 0) {
            return ((zzabd) this.zzbty.get(zza)).setValue(v);
        }
        zzul();
        if (this.zzbty.isEmpty() && !(this.zzbty instanceof ArrayList)) {
            this.zzbty = new ArrayList(this.zzbtx);
        }
        int i = -(zza + 1);
        if (i >= this.zzbtx) {
            return zzum().put(k, v);
        }
        if (this.zzbty.size() == this.zzbtx) {
            zzabd com_google_android_gms_internal_measurement_zzabd = (zzabd) this.zzbty.remove(this.zzbtx - 1);
            zzum().put((Comparable) com_google_android_gms_internal_measurement_zzabd.getKey(), com_google_android_gms_internal_measurement_zzabd.getValue());
        }
        this.zzbty.add(i, new zzabd(this, k, v));
        return null;
    }

    public final Entry<K, V> zzah(int i) {
        return (Entry) this.zzbty.get(i);
    }

    public void zzrg() {
        if (!this.zzbls) {
            this.zzbtz = this.zzbtz.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(this.zzbtz);
            this.zzbub = this.zzbub.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(this.zzbub);
            this.zzbls = true;
        }
    }

    public final int zzuj() {
        return this.zzbty.size();
    }

    public final Iterable<Entry<K, V>> zzuk() {
        return this.zzbtz.isEmpty() ? zzaba.zzun() : this.zzbtz.entrySet();
    }
}
