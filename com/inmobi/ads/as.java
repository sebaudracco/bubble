package com.inmobi.ads;

import android.support.annotation.NonNull;
import com.inmobi.ads.NativeStrandAssetStyle.C2932a;
import com.inmobi.ads.NativeStrandAssetStyle.C2933b;
import com.inmobi.ads.NativeV2Asset.AssetType;
import java.util.Locale;

/* compiled from: NativeV2TextAsset */
public class as extends NativeV2Asset {

    /* compiled from: NativeV2TextAsset */
    static class C3006a extends NativeStrandAssetStyle {
        protected int f7142g;
        protected String f7143h;
        protected int f7144i;
        protected C3014b[] f7145j;
        private C3013a f7146k;

        /* compiled from: NativeV2TextAsset */
        enum C3013a {
            TEXT_LEFT_ALIGNED,
            TEXT_RIGHT_ALIGNED,
            TEXT_CENTER_ALIGNED
        }

        /* compiled from: NativeV2TextAsset */
        enum C3014b {
            TEXT_STYLE_NONE("none"),
            TEXT_STYLE_BOLD("bold"),
            TEXT_STYLE_ITALICISED("italic"),
            TEXT_STYLE_STRIKE_THRU("strike"),
            TEXT_STYLE_UNDERLINE("underline");
            
            private final String f7169f;

            private C3014b(String str) {
                this.f7169f = str;
            }
        }

        public C3006a(int i, int i2, int i3, int i4, @NonNull C2933b c2933b, @NonNull C2932a c2932a, @NonNull String str, @NonNull String str2) {
            this(i, i2, i3, i4, c2933b, c2932a, str, str2, 12, C3013a.TEXT_LEFT_ALIGNED);
        }

        public C3006a(int i, int i2, int i3, int i4, @NonNull C2933b c2933b, @NonNull C2932a c2932a, @NonNull String str, @NonNull String str2, int i5, C3013a c3013a) {
            this(i, i2, i3, i4, c2933b, c2932a, str, str2, i5, c3013a, "#ff000000");
        }

        public C3006a(int i, int i2, int i3, int i4, @NonNull C2933b c2933b, @NonNull C2932a c2932a, @NonNull String str, @NonNull String str2, int i5, C3013a c3013a, String str3) {
            this(i, i2, i3, i4, c2933b, c2932a, str, str2, i5, c3013a, str3, new C3014b[]{C3014b.TEXT_STYLE_NONE});
        }

        public C3006a(int i, int i2, int i3, int i4, @NonNull C2933b c2933b, @NonNull C2932a c2932a, @NonNull String str, @NonNull String str2, int i5, C3013a c3013a, String str3, C3014b[] c3014bArr) {
            this(i, i2, i3, i4, c2933b, c2932a, str, str2, i5, c3013a, Integer.MAX_VALUE, str3, c3014bArr);
        }

        public C3006a(int i, int i2, int i3, int i4, @NonNull C2933b c2933b, @NonNull C2932a c2932a, @NonNull String str, @NonNull String str2, int i5, C3013a c3013a, int i6, @NonNull String str3, C3014b[] c3014bArr) {
            super(i, i2, i3, i4, c2933b, c2932a, str, str2);
            this.f7142g = i5;
            if (str3.length() == 0) {
                str3 = "#ff000000";
            }
            this.f7143h = str3;
            this.f7144i = i6;
            int min = Math.min(c3014bArr.length, 4);
            this.f7145j = new C3014b[min];
            this.f7146k = c3013a;
            System.arraycopy(c3014bArr, 0, this.f7145j, 0, min);
        }

        public int m9367i() {
            return this.f7142g;
        }

        public String m9368j() {
            return this.f7143h.toLowerCase(Locale.US);
        }

        public C3014b[] m9369k() {
            return this.f7145j;
        }

        public String mo6187g() {
            return this.f.toLowerCase(Locale.US);
        }

        public C3013a m9370l() {
            return this.f7146k;
        }
    }

    public as(String str, String str2, NativeStrandAssetStyle nativeStrandAssetStyle, String str3) {
        super(str, str2, AssetType.ASSET_TYPE_TEXT, nativeStrandAssetStyle);
        this.e = str3;
    }

    public as(String str, String str2, AssetType assetType, NativeStrandAssetStyle nativeStrandAssetStyle, String str3) {
        super(str, str2, assetType, nativeStrandAssetStyle);
        this.e = str3;
    }
}
