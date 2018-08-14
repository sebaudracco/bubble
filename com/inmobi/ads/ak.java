package com.inmobi.ads;

import com.inmobi.ads.NativeV2Asset.AssetInteractionMode;
import com.inmobi.ads.NativeV2Asset.AssetType;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONObject;

/* compiled from: NativeV2ContainerAsset */
public class ak extends NativeV2Asset implements Iterable<NativeV2Asset> {
    private int f7138A;
    private long f7139x;
    private C3004a f7140y;
    private NativeV2Asset[] f7141z;

    /* compiled from: NativeV2ContainerAsset */
    enum C3004a {
        CARD_SCROLLABLE_TYPE_PAGED,
        CARD_SCROLLABLE_TYPE_FREE
    }

    /* compiled from: NativeV2ContainerAsset */
    private class C3005b implements Iterator<NativeV2Asset> {
        final /* synthetic */ ak f7136a;
        private int f7137b = 0;

        public /* synthetic */ Object next() {
            return m9354a();
        }

        public C3005b(ak akVar) {
            this.f7136a = akVar;
        }

        public boolean hasNext() {
            return this.f7137b < this.f7136a.f7138A;
        }

        public NativeV2Asset m9354a() {
            NativeV2Asset[] b = this.f7136a.f7141z;
            int i = this.f7137b;
            this.f7137b = i + 1;
            return b[i];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public Iterator<NativeV2Asset> iterator() {
        return new C3005b(this);
    }

    public void m9361a(long j) {
        this.f7139x = j;
    }

    public long m9364y() {
        return this.f7139x;
    }

    public ak(String str, String str2, NativeStrandAssetStyle nativeStrandAssetStyle, AssetInteractionMode assetInteractionMode, JSONObject jSONObject, C3004a c3004a) {
        this(str, str2, nativeStrandAssetStyle, new LinkedList(), assetInteractionMode, jSONObject, c3004a);
    }

    public ak(String str, String str2, NativeStrandAssetStyle nativeStrandAssetStyle, List<ah> list, AssetInteractionMode assetInteractionMode, JSONObject jSONObject, C3004a c3004a) {
        super(str, str2, AssetType.ASSET_TYPE_CONTAINER, nativeStrandAssetStyle, list);
        this.f7139x = 0;
        this.f = jSONObject;
        this.f7141z = new NativeV2Asset[1];
        this.i = assetInteractionMode;
        this.f7138A = 0;
        this.f7140y = c3004a;
    }

    public boolean m9363c(NativeV2Asset nativeV2Asset) {
        if (this.f7138A >= 16) {
            return false;
        }
        if (this.f7138A == this.f7141z.length) {
            m9357c(this.f7141z.length * 2);
        }
        NativeV2Asset[] nativeV2AssetArr = this.f7141z;
        int i = this.f7138A;
        this.f7138A = i + 1;
        nativeV2AssetArr[i] = nativeV2Asset;
        return true;
    }

    public NativeV2Asset m9362b(int i) {
        return (i < 0 || i >= m9358A()) ? null : this.f7141z[i];
    }

    public C3004a m9365z() {
        return this.f7140y;
    }

    public int m9358A() {
        return this.f7138A;
    }

    public boolean m9359B() {
        return "root".equalsIgnoreCase(this.d);
    }

    public boolean m9360C() {
        return "card_scrollable".equalsIgnoreCase(this.d);
    }

    private void m9357c(int i) {
        Object obj = new NativeV2Asset[i];
        System.arraycopy(this.f7141z, 0, obj, 0, this.f7138A);
        this.f7141z = obj;
    }
}
