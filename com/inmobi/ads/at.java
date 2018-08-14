package com.inmobi.ads;

import android.media.MediaMetadataRetriever;
import com.inmobi.ads.NativeV2Asset.AssetType;
import com.mopub.mobileads.dfp.adapters.MoPubAdapter;

/* compiled from: NativeV2TimerAsset */
public class at extends NativeV2Asset {
    private C3015a f7173x;
    private C3015a f7174y;
    private boolean f7175z;

    /* compiled from: NativeV2TimerAsset */
    static class C3015a {
        private long f7170a;
        private long f7171b;
        private NativeV2Asset f7172c;

        public C3015a(long j, long j2, NativeV2Asset nativeV2Asset) {
            this.f7170a = j;
            this.f7171b = j2;
            this.f7172c = nativeV2Asset;
        }

        public long m9399a() {
            long j = this.f7170a;
            if (this.f7172c != null && (this.f7172c instanceof aw)) {
                aw awVar = (aw) this.f7172c;
                if (awVar != null) {
                    String b = awVar.m9441D().mo6219b();
                    if (b != null) {
                        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
                        mediaMetadataRetriever.setDataSource(b);
                        long intValue = (long) ((((double) (((long) Integer.valueOf(mediaMetadataRetriever.extractMetadata(9)).intValue()) / 1000)) * ((((double) this.f7171b) * MoPubAdapter.DEFAULT_MOPUB_IMAGE_SCALE) / 100.0d)) + ((double) j));
                        mediaMetadataRetriever.release();
                        return intValue;
                    }
                }
            }
            return j;
        }
    }

    public at(String str, String str2, NativeStrandAssetStyle nativeStrandAssetStyle, C3015a c3015a, C3015a c3015a2) {
        super(str, str2, AssetType.ASSET_TYPE_TIMER, nativeStrandAssetStyle);
        this.f7173x = c3015a;
        this.f7174y = c3015a2;
    }

    public void m9401b(boolean z) {
        this.f7175z = z;
    }

    public boolean m9402y() {
        return this.f7175z;
    }

    public C3015a m9403z() {
        return this.f7173x;
    }

    public C3015a m9400A() {
        return this.f7174y;
    }
}
