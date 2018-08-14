package com.inmobi.ads;

import android.support.annotation.NonNull;
import com.inmobi.ads.NativeStrandAssetStyle.C2932a;
import com.inmobi.ads.NativeStrandAssetStyle.C2933b;
import com.inmobi.ads.NativeV2Asset.AssetInteractionMode;
import com.inmobi.ads.NativeV2Asset.AssetType;
import com.inmobi.ads.as.C3006a;
import com.inmobi.ads.as.C3006a.C3014b;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONObject;

/* compiled from: NativeV2CtaAsset */
public class al extends as {

    /* compiled from: NativeV2CtaAsset */
    static class C3007a extends C3006a {
        public C3007a(int i, int i2, int i3, int i4, @NonNull C2933b c2933b, @NonNull C2932a c2932a, @NonNull String str, @NonNull String str2, int i5, String str3, C3014b[] c3014bArr) {
            super(i, i2, i3, i4, c2933b, c2932a, str, str2);
            this.g = i5;
            this.i = Integer.MAX_VALUE;
            if (str3.length() == 0) {
                str3 = "#ff000000";
            }
            this.h = str3;
            int min = Math.min(c3014bArr.length, 1);
            this.j = new C3014b[min];
            System.arraycopy(c3014bArr, 0, this.j, 0, min);
        }
    }

    public al(String str, String str2, NativeStrandAssetStyle nativeStrandAssetStyle, String str3, AssetInteractionMode assetInteractionMode, JSONObject jSONObject) {
        this(str, str2, nativeStrandAssetStyle, str3, new LinkedList(), assetInteractionMode, jSONObject);
    }

    public al(String str, String str2, NativeStrandAssetStyle nativeStrandAssetStyle, String str3, List<ah> list, AssetInteractionMode assetInteractionMode, JSONObject jSONObject) {
        super(str, str2, AssetType.ASSET_TYPE_CTA, nativeStrandAssetStyle, str3);
        m8999a((List) list);
        if (jSONObject != null) {
            this.i = assetInteractionMode;
            this.f = jSONObject;
        }
    }
}
