package com.inmobi.ads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import com.inmobi.ads.ah.C3000a;
import com.inmobi.commons.core.utilities.C3155d;
import com.inmobi.rendering.p118a.C3213c;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public class NativeV2Asset {
    private static final String f6897x = NativeV2Asset.class.getSimpleName();
    protected String f6898a;
    protected AssetType f6899b;
    protected NativeStrandAssetStyle f6900c;
    protected String f6901d;
    protected Object f6902e;
    protected JSONObject f6903f;
    protected String f6904g;
    protected boolean f6905h;
    protected AssetInteractionMode f6906i;
    protected String f6907j;
    protected AssetActionOnFinish f6908k;
    protected AssetActionOnClick f6909l;
    protected AssetReferencedCreative f6910m;
    protected AssetDisplayOnType f6911n;
    protected int f6912o;
    protected String f6913p;
    protected String f6914q;
    protected String f6915r;
    protected NativeV2Asset f6916s;
    protected List<ah> f6917t;
    protected Map<String, Object> f6918u;
    protected Object f6919v;
    protected AssetVisibility f6920w;
    private NativeV2Asset f6921y;

    public enum AssetActionOnClick {
        ASSET_ACTION_ON_CLICK_NONE,
        ASSET_ACTION_ON_CLICK_EXIT,
        ASSET_ACTION_ON_CLICK_SKIP,
        ASSET_ACTION_ON_CLICK_REPLAY,
        ASSET_ACTION_ON_CLICK_FULLSCREEN
    }

    public enum AssetActionOnFinish {
        ASSET_ACTION_ON_FINISH_NONE,
        ASSET_ACTION_ON_FINISH_EXIT
    }

    public enum AssetDisplayOnType {
        ASSET_DISPLAY_ON_TYPE_UNKNOWN,
        ASSET_DISPLAY_ON_TYPE_ALWAYS,
        ASSET_DISPLAY_ON_TYPE_ABSOLUTE,
        ASSET_DISPLAY_ON_TYPE_PERCENTAGE
    }

    public enum AssetInteractionMode {
        ASSET_INTERACTION_MODE_NO_ACTION,
        ASSET_INTERACTION_MODE_IN_APP,
        ASSET_INTERACTION_MODE_BROWSER,
        ASSET_INTERACTION_MODE_DEEP_LINK
    }

    public enum AssetReferencedCreative {
        ASSET_REFERENCED_CREATIVE_NONE,
        ASSET_REFERENCED_CREATIVE_LINEAR,
        ASSET_REFERENCED_CREATIVE_COMPANION
    }

    public enum AssetType {
        ASSET_TYPE_CONTAINER("CONTAINER"),
        ASSET_TYPE_TEXT("TEXT"),
        ASSET_TYPE_CTA("CTA"),
        ASSET_TYPE_IMAGE("IMAGE"),
        ASSET_TYPE_ICON("ICON"),
        ASSET_TYPE_RATING("RATING"),
        ASSET_TYPE_VIDEO("VIDEO"),
        ASSET_TYPE_TIMER("TIMER");
        
        private final String f6895a;

        private AssetType(String str) {
            this.f6895a = str;
        }
    }

    public enum AssetValueType {
        ASSET_VALUE_ABSOLUTE,
        ASSET_VALUE_REFERENCE
    }

    public enum AssetVisibility {
        VISIBLE(Integer.valueOf(0)),
        INVISIBLE(Integer.valueOf(4)),
        GONE(Integer.valueOf(8));
        
        private Integer f6896a;

        private AssetVisibility(Integer num) {
            this.f6896a = num;
        }

        public Integer getId() {
            return this.f6896a;
        }
    }

    @VisibleForTesting
    public NativeV2Asset() {
        this("", "root", AssetType.ASSET_TYPE_CONTAINER);
    }

    public NativeV2Asset(String str, String str2, AssetType assetType) {
        this(str, str2, assetType, new NativeStrandAssetStyle());
    }

    public NativeV2Asset(String str, String str2, AssetType assetType, NativeStrandAssetStyle nativeStrandAssetStyle) {
        this(str, str2, assetType, nativeStrandAssetStyle, new LinkedList());
    }

    public NativeV2Asset(String str, String str2, AssetType assetType, NativeStrandAssetStyle nativeStrandAssetStyle, List<ah> list) {
        this.f6898a = str;
        this.f6901d = str2;
        this.f6899b = assetType;
        this.f6900c = nativeStrandAssetStyle;
        this.f6902e = null;
        this.f6904g = "";
        this.f6905h = false;
        this.f6906i = AssetInteractionMode.ASSET_INTERACTION_MODE_NO_ACTION;
        this.f6907j = "";
        this.f6909l = AssetActionOnClick.ASSET_ACTION_ON_CLICK_NONE;
        this.f6908k = AssetActionOnFinish.ASSET_ACTION_ON_FINISH_NONE;
        this.f6910m = AssetReferencedCreative.ASSET_REFERENCED_CREATIVE_NONE;
        this.f6911n = AssetDisplayOnType.ASSET_DISPLAY_ON_TYPE_ALWAYS;
        this.f6920w = AssetVisibility.VISIBLE;
        this.f6912o = -1;
        this.f6913p = "";
        this.f6914q = "";
        this.f6903f = new JSONObject();
        this.f6917t = new LinkedList();
        this.f6917t.addAll(list);
        this.f6918u = new HashMap();
    }

    public void m8996a(@NonNull ah ahVar, @Nullable Map<String, String> map) {
        m9029x().m10712a(C3155d.m10402a(ahVar.m9295b(), (Map) map), ahVar.m9298d(), true);
    }

    public void m8995a(C3000a c3000a, @Nullable Map<String, String> map) {
        if (this.f6917t.size() != 0) {
            for (ah ahVar : this.f6917t) {
                if (c3000a == ahVar.m9297c()) {
                    m8996a(ahVar, (Map) map);
                }
            }
        }
    }

    public AssetType m8986a() {
        return this.f6899b;
    }

    public NativeStrandAssetStyle m9001b() {
        return this.f6900c;
    }

    public String m9005c() {
        return this.f6901d;
    }

    public Object m9007d() {
        return this.f6902e;
    }

    public void m8997a(Object obj) {
        this.f6902e = obj;
    }

    public JSONObject m9009e() {
        return this.f6903f;
    }

    public void m8999a(List<ah> list) {
        this.f6917t.addAll(list);
    }

    public List<ah> m9011f() {
        return this.f6917t;
    }

    public void m8998a(String str) {
        this.f6904g = str;
    }

    public String m9012g() {
        return this.f6904g;
    }

    public boolean m9013h() {
        return this.f6905h;
    }

    public void m9000a(boolean z) {
        this.f6905h = z;
    }

    public void m8994a(NativeV2Asset nativeV2Asset) {
        this.f6921y = nativeV2Asset;
    }

    public NativeV2Asset m9014i() {
        return this.f6921y;
    }

    public AssetInteractionMode m9015j() {
        return this.f6906i;
    }

    public void m8991a(AssetInteractionMode assetInteractionMode) {
        this.f6906i = assetInteractionMode;
    }

    public void m8993a(AssetVisibility assetVisibility) {
        this.f6920w = assetVisibility;
    }

    public AssetVisibility m9016k() {
        return this.f6920w;
    }

    public AssetActionOnClick m9017l() {
        return this.f6909l;
    }

    public void m8988a(AssetActionOnClick assetActionOnClick) {
        this.f6909l = assetActionOnClick;
    }

    public AssetActionOnFinish m9018m() {
        return this.f6908k;
    }

    public void m8989a(AssetActionOnFinish assetActionOnFinish) {
        this.f6908k = assetActionOnFinish;
    }

    public AssetReferencedCreative m9019n() {
        return this.f6910m;
    }

    public void m8992a(AssetReferencedCreative assetReferencedCreative) {
        this.f6910m = assetReferencedCreative;
    }

    public AssetDisplayOnType m9020o() {
        return this.f6911n;
    }

    public void m8990a(AssetDisplayOnType assetDisplayOnType) {
        this.f6911n = assetDisplayOnType;
    }

    public int m9021p() {
        return this.f6912o;
    }

    public void m8987a(int i) {
        this.f6912o = i;
    }

    public void m9004b(String str) {
        this.f6913p = str;
    }

    public String m9022q() {
        return this.f6907j;
    }

    public void m9006c(String str) {
        this.f6907j = str;
    }

    public void m9008d(String str) {
        this.f6914q = str.trim();
    }

    public void m9010e(@NonNull String str) {
        this.f6915r = str.trim();
    }

    public String m9023r() {
        return this.f6914q;
    }

    public String m9024s() {
        return this.f6915r;
    }

    public String m9025t() {
        return this.f6898a;
    }

    public void m9002b(@NonNull NativeV2Asset nativeV2Asset) {
        this.f6916s = nativeV2Asset;
    }

    public NativeV2Asset m9026u() {
        return this.f6916s;
    }

    @NonNull
    public Map<String, Object> m9027v() {
        return this.f6918u;
    }

    @Nullable
    public Object m9028w() {
        return this.f6919v;
    }

    public void m9003b(Object obj) {
        this.f6919v = obj;
    }

    @VisibleForTesting
    C3213c m9029x() {
        return C3213c.m10698a();
    }
}
