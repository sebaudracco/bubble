package com.inmobi.ads;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.inmobi.ads.AdContainer.RenderingProperties.PlacementType;
import com.inmobi.ads.NativeStrandAssetStyle.C2932a;
import com.inmobi.ads.NativeStrandAssetStyle.C2933b;
import com.inmobi.ads.NativeV2Asset.AssetInteractionMode;
import com.inmobi.ads.NativeV2Asset.AssetType;
import com.inmobi.ads.ah.C3000a;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* compiled from: NativeV2VideoAsset */
public class aw extends NativeV2Asset {
    private boolean f7194A;
    private boolean f7195B;
    private Map<String, Object> f7196C;
    private List<NativeV2Asset> f7197x;
    private boolean f7198y;
    private boolean f7199z;

    /* compiled from: NativeV2VideoAsset */
    static class C3023a extends NativeStrandAssetStyle {
        public C3023a(int i, int i2, int i3, int i4) {
            super(i, i2, i3, i4, C2933b.BORDER_STROKE_STYLE_NONE, C2932a.BORDER_CORNER_STYLE_STRAIGHT, "#ff000000", "#00000000");
        }
    }

    public aw(String str, String str2, NativeStrandAssetStyle nativeStrandAssetStyle, bp bpVar, boolean z, boolean z2, boolean z3, boolean z4, List<ah> list, JSONObject jSONObject, Bitmap bitmap) {
        super(str, str2, AssetType.ASSET_TYPE_VIDEO, nativeStrandAssetStyle);
        this.e = bpVar;
        this.i = AssetInteractionMode.ASSET_INTERACTION_MODE_BROWSER;
        this.f7198y = z;
        this.f7199z = z2;
        this.f7194A = z3;
        this.f7195B = z4;
        this.f7197x = new ArrayList();
        if (bpVar != null) {
            this.q = bpVar.mo6216a();
            List<ah> e = bpVar.mo6221e();
            Map map = null;
            if (list != null) {
                for (ah ahVar : list) {
                    Map map2;
                    if (C3000a.TRACKER_EVENT_TYPE_IAS == ahVar.m9297c()) {
                        map = ahVar.m9298d();
                        if (!TextUtils.isEmpty(ahVar.m9295b())) {
                            e.add(ahVar);
                            map2 = map;
                        }
                        map2 = map;
                    } else {
                        e.add(ahVar);
                        map2 = map;
                    }
                    map = map2;
                }
            }
            for (ah ahVar2 : e) {
                if (C3000a.TRACKER_EVENT_TYPE_IAS == ahVar2.m9297c()) {
                    ahVar2.m9296b(map);
                }
            }
            if (!e.isEmpty()) {
                m8999a((List) e);
            }
        }
        if (jSONObject != null) {
            this.f = jSONObject;
        }
        if (bitmap != null) {
            m9003b((Object) bitmap);
        }
        this.u.put("placementType", PlacementType.PLACEMENT_TYPE_INLINE);
        this.u.put("lastVisibleTimestamp", Integer.valueOf(Integer.MIN_VALUE));
        this.u.put("visible", Boolean.valueOf(false));
        this.u.put("seekPosition", Integer.valueOf(0));
        this.u.put("didStartPlaying", Boolean.valueOf(false));
        this.u.put("didPause", Boolean.valueOf(false));
        this.u.put("didCompleteQ1", Boolean.valueOf(false));
        this.u.put("didCompleteQ2", Boolean.valueOf(false));
        this.u.put("didCompleteQ3", Boolean.valueOf(false));
        this.u.put("didCompleteQ4", Boolean.valueOf(false));
        this.u.put("didRequestFullScreen", Boolean.valueOf(false));
        this.u.put("isFullScreen", Boolean.valueOf(false));
        this.u.put("didImpressionFire", Boolean.valueOf(false));
        this.u.put("mapViewabilityParams", new HashMap());
        this.u.put("didSignalVideoCompleted", Boolean.valueOf(false));
        this.u.put("shouldAutoPlay", Boolean.valueOf(false));
        this.u.put("lastMediaVolume", Integer.valueOf(0));
        this.u.put("currentMediaVolume", Integer.valueOf(0));
    }

    public void m9443a(Map<String, Object> map) {
        this.f7196C = new HashMap(map);
    }

    public Map<String, Object> m9447y() {
        return this.f7196C;
    }

    public int m9444b(int i) {
        if (this.f7196C.containsKey("time")) {
            return ((Integer) this.f7196C.get("time")).intValue();
        }
        return i;
    }

    public void m9446c(@NonNull NativeV2Asset nativeV2Asset) {
        this.f7197x.add(nativeV2Asset);
    }

    public void m9445b(boolean z) {
        this.f7198y = z;
    }

    public boolean m9448z() {
        return this.f7198y;
    }

    public boolean m9438A() {
        return this.f7199z;
    }

    public boolean m9439B() {
        return this.f7194A;
    }

    public boolean m9440C() {
        return this.f7195B;
    }

    public bp m9441D() {
        return m9007d() == null ? null : (bp) m9007d();
    }

    public void m9442a(aw awVar) {
        this.u.putAll(awVar.m9027v());
        this.f7196C.putAll(awVar.m9447y());
        this.t = awVar.m9011f();
    }
}
