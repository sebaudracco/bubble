package com.inmobi.ads;

import com.inmobi.ads.NativeV2Asset.AssetInteractionMode;
import com.inmobi.ads.NativeV2Asset.AssetType;
import java.util.LinkedList;
import java.util.List;
import org.json.JSONObject;

/* compiled from: NativeV2ImageAsset */
public class ao extends NativeV2Asset {
    public ao(String str, String str2, NativeStrandAssetStyle nativeStrandAssetStyle, String str3, AssetInteractionMode assetInteractionMode, JSONObject jSONObject) {
        this(str, str2, nativeStrandAssetStyle, str3, new LinkedList(), assetInteractionMode, jSONObject);
    }

    public ao(String str, String str2, NativeStrandAssetStyle nativeStrandAssetStyle, String str3, List<ah> list, AssetInteractionMode assetInteractionMode, JSONObject jSONObject) {
        super(str, str2, AssetType.ASSET_TYPE_IMAGE, nativeStrandAssetStyle, list);
        this.e = str3;
        if (jSONObject != null) {
            this.i = assetInteractionMode;
            this.f = jSONObject;
        }
    }
}
