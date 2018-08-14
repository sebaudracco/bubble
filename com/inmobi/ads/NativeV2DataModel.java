package com.inmobi.ads;

import android.annotation.TargetApi;
import android.graphics.Point;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.webkit.URLUtil;
import com.appnext.base.p023b.C1042c;
import com.appnext.core.Ad;
import com.coremedia.iso.boxes.FreeBox;
import com.inmobi.ads.AdContainer.RenderingProperties.PlacementType;
import com.inmobi.ads.C3046c.C3043g;
import com.inmobi.ads.NativeStrandAssetStyle.C2932a;
import com.inmobi.ads.NativeStrandAssetStyle.C2933b;
import com.inmobi.ads.NativeStrandAssetStyle.ContentMode;
import com.inmobi.ads.NativeV2Asset.AssetActionOnFinish;
import com.inmobi.ads.NativeV2Asset.AssetDisplayOnType;
import com.inmobi.ads.NativeV2Asset.AssetInteractionMode;
import com.inmobi.ads.NativeV2Asset.AssetReferencedCreative;
import com.inmobi.ads.NativeV2Asset.AssetType;
import com.inmobi.ads.NativeV2Asset.AssetValueType;
import com.inmobi.ads.ah.C3000a;
import com.inmobi.ads.ah.C3001b;
import com.inmobi.ads.ak.C3004a;
import com.inmobi.ads.al.C3007a;
import com.inmobi.ads.as.C3006a;
import com.inmobi.ads.as.C3006a.C3013a;
import com.inmobi.ads.as.C3006a.C3014b;
import com.inmobi.ads.at.C3015a;
import com.inmobi.ads.aw.C3023a;
import com.inmobi.commons.core.p115d.C3132b;
import com.inmobi.commons.core.p115d.C3135c;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.info.DisplayInfo;
import com.mopub.common.Constants;
import com.silvermob.sdk.Const.BannerType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@VisibleForTesting
public class NativeV2DataModel {
    private static final String f6923a = NativeV2DataModel.class.getSimpleName();
    private JSONObject f6924b;
    private String f6925c;
    private Orientation f6926d;
    private boolean f6927e;
    private JSONObject f6928f;
    private ak f6929g;
    private JSONArray f6930h;
    private final NativeV2DataModel f6931i;
    @Nullable
    private Map<String, String> f6932j;
    private Map<AssetType, List<NativeV2Asset>> f6933k;
    private Map<String, NativeV2Asset> f6934l;
    private Map<String, String> f6935m;
    @Nullable
    private bo f6936n;
    private C3043g f6937o;
    private PlacementType f6938p;

    public enum Orientation {
        ORIENTATION_UNSPECIFIED,
        ORIENTATION_PORTRAIT,
        ORIENTATION_LANDSCAPE
    }

    @VisibleForTesting
    NativeV2DataModel() {
        this.f6931i = null;
    }

    public NativeV2DataModel(@NonNull PlacementType placementType, @NonNull JSONObject jSONObject, @Nullable C3043g c3043g, @Nullable bo boVar) {
        this(placementType, jSONObject, null, c3043g, boVar);
    }

    public NativeV2DataModel(@NonNull PlacementType placementType, @NonNull JSONObject jSONObject, @Nullable NativeV2DataModel nativeV2DataModel, @Nullable C3043g c3043g, @Nullable bo boVar) {
        this.f6938p = placementType;
        this.f6931i = nativeV2DataModel;
        if (c3043g == null) {
            c3043g = new C3043g();
        }
        this.f6937o = c3043g;
        this.f6924b = jSONObject;
        this.f6926d = Orientation.ORIENTATION_UNSPECIFIED;
        this.f6927e = false;
        this.f6936n = boVar;
        this.f6934l = new HashMap();
        this.f6935m = new HashMap();
        this.f6933k = new HashMap();
        m9093m();
    }

    @NonNull
    public JSONObject m9074a() {
        return this.f6924b == null ? new JSONObject() : this.f6924b;
    }

    public ak m9079b() {
        return this.f6929g;
    }

    public Orientation m9083c() {
        return this.f6926d;
    }

    public boolean m9084d() {
        return this.f6927e;
    }

    public NativeV2DataModel m9085e() {
        return this.f6931i;
    }

    public JSONArray m9086f() {
        return this.f6930h;
    }

    @Nullable
    Map<String, String> m9087g() {
        return this.f6932j;
    }

    public JSONObject m9075a(int i) {
        try {
            return this.f6930h.getJSONObject(i);
        } catch (Throwable e) {
            C3135c.m10255a().m10279a(new C3132b(e));
            return null;
        }
    }

    @Nullable
    public JSONObject m9088h() {
        return this.f6928f;
    }

    public ak m9089i() {
        Iterator it = this.f6929g.iterator();
        while (it.hasNext()) {
            NativeV2Asset nativeV2Asset = (NativeV2Asset) it.next();
            if (nativeV2Asset.m9005c().equalsIgnoreCase("card_scrollable")) {
                return (ak) nativeV2Asset;
            }
        }
        return null;
    }

    public int m9090j() {
        if (this.f6929g == null) {
            return 0;
        }
        Iterator it = this.f6929g.iterator();
        while (it.hasNext()) {
            NativeV2Asset nativeV2Asset = (NativeV2Asset) it.next();
            if (nativeV2Asset.m9005c().equalsIgnoreCase("card_scrollable")) {
                return ((ak) nativeV2Asset).m9358A();
            }
        }
        return 0;
    }

    public ak m9080b(int i) {
        Iterator it = this.f6929g.iterator();
        while (it.hasNext()) {
            NativeV2Asset nativeV2Asset = (NativeV2Asset) it.next();
            if (nativeV2Asset.m9005c().equalsIgnoreCase("card_scrollable")) {
                if (i >= ((ak) nativeV2Asset).m9358A()) {
                    return null;
                }
                return (ak) ((ak) nativeV2Asset).m9362b(i);
            }
        }
        return null;
    }

    public int m9091k() {
        return this.f6929g == null ? 0 : this.f6929g.m9001b().m8849a().x;
    }

    public int m9092l() {
        return this.f6929g == null ? 0 : this.f6929g.m9001b().m8849a().y;
    }

    public ak m9071a(@NonNull NativeV2Asset nativeV2Asset) {
        if ((nativeV2Asset instanceof ak) && m9035a((ak) nativeV2Asset)) {
            return (ak) nativeV2Asset;
        }
        for (ak akVar = (ak) nativeV2Asset.m9026u(); akVar != null; akVar = (ak) akVar.m9026u()) {
            if (m9035a(akVar)) {
                return akVar;
            }
        }
        return null;
    }

    private boolean m9035a(@NonNull ak akVar) {
        return "card_scrollable".equalsIgnoreCase(akVar.m9005c());
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @android.support.annotation.VisibleForTesting
    void m9093m() {
        /*
        r10 = this;
        r5 = 0;
        r2 = r10.f6924b;	 Catch:{ JSONException -> 0x008e }
        r3 = "version";
        r2 = r2.getDouble(r3);	 Catch:{ JSONException -> 0x008e }
        r2 = java.lang.String.valueOf(r2);	 Catch:{ JSONException -> 0x008e }
        r10.f6925c = r2;	 Catch:{ JSONException -> 0x008e }
        r2 = r10.f6924b;	 Catch:{ JSONException -> 0x008e }
        r3 = "styleRefs";
        r2 = r2.optJSONObject(r3);	 Catch:{ JSONException -> 0x008e }
        r10.f6928f = r2;	 Catch:{ JSONException -> 0x008e }
        r2 = r10.f6924b;	 Catch:{ JSONException -> 0x008e }
        r3 = "orientation";
        r2 = r2.isNull(r3);	 Catch:{ JSONException -> 0x008e }
        if (r2 == 0) goto L_0x009c;
    L_0x0026:
        r2 = com.inmobi.ads.NativeV2DataModel.Orientation.ORIENTATION_UNSPECIFIED;	 Catch:{ JSONException -> 0x008e }
        r10.f6926d = r2;	 Catch:{ JSONException -> 0x008e }
    L_0x002a:
        r2 = r10.f6924b;	 Catch:{ JSONException -> 0x008e }
        r3 = "disableBackButton";
        r4 = 0;
        r2 = r2.optBoolean(r3, r4);	 Catch:{ JSONException -> 0x008e }
        r10.f6927e = r2;	 Catch:{ JSONException -> 0x008e }
        r2 = r10.f6924b;	 Catch:{ JSONException -> 0x008e }
        r3 = "rootContainer";
        r2 = r2.getJSONObject(r3);	 Catch:{ JSONException -> 0x008e }
        r3 = com.inmobi.ads.NativeV2Asset.AssetType.ASSET_TYPE_CONTAINER;	 Catch:{ JSONException -> 0x008e }
        r4 = "/rootContainer";
        r2 = r10.m9031a(r2, r3, r4);	 Catch:{ JSONException -> 0x008e }
        r2 = (com.inmobi.ads.ak) r2;	 Catch:{ JSONException -> 0x008e }
        r10.f6929g = r2;	 Catch:{ JSONException -> 0x008e }
        r2 = r10.f6924b;	 Catch:{ JSONException -> 0x008e }
        r3 = "rewards";
        r2 = r2.has(r3);	 Catch:{ JSONException -> 0x008e }
        if (r2 == 0) goto L_0x005e;
    L_0x0057:
        r2 = new java.util.HashMap;	 Catch:{ JSONException -> 0x008e }
        r2.<init>();	 Catch:{ JSONException -> 0x008e }
        r10.f6932j = r2;	 Catch:{ JSONException -> 0x008e }
    L_0x005e:
        r2 = r10.f6924b;	 Catch:{ JSONException -> 0x008e }
        r3 = "rewards";
        r2 = r2.isNull(r3);	 Catch:{ JSONException -> 0x008e }
        if (r2 != 0) goto L_0x00ad;
    L_0x0069:
        r2 = r10.f6924b;	 Catch:{ JSONException -> 0x008e }
        r3 = "rewards";
        r3 = r2.getJSONObject(r3);	 Catch:{ JSONException -> 0x008e }
        if (r3 == 0) goto L_0x00ad;
    L_0x0074:
        r4 = r3.keys();	 Catch:{ JSONException -> 0x008e }
    L_0x0078:
        r2 = r4.hasNext();	 Catch:{ JSONException -> 0x008e }
        if (r2 == 0) goto L_0x00ad;
    L_0x007e:
        r2 = r4.next();	 Catch:{ JSONException -> 0x008e }
        r2 = (java.lang.String) r2;	 Catch:{ JSONException -> 0x008e }
        r6 = r3.getString(r2);	 Catch:{ JSONException -> 0x008e }
        r7 = r10.f6932j;	 Catch:{ JSONException -> 0x008e }
        r7.put(r2, r6);	 Catch:{ JSONException -> 0x008e }
        goto L_0x0078;
    L_0x008e:
        r2 = move-exception;
        r3 = com.inmobi.commons.core.p115d.C3135c.m10255a();
        r4 = new com.inmobi.commons.core.d.b;
        r4.<init>(r2);
        r3.m10279a(r4);
    L_0x009b:
        return;
    L_0x009c:
        r2 = r10.f6924b;	 Catch:{ JSONException -> 0x008e }
        r3 = "orientation";
        r2 = r2.getString(r3);	 Catch:{ JSONException -> 0x008e }
        r2 = r10.m9050i(r2);	 Catch:{ JSONException -> 0x008e }
        r10.f6926d = r2;	 Catch:{ JSONException -> 0x008e }
        goto L_0x002a;
    L_0x00ad:
        r2 = com.inmobi.ads.NativeV2Asset.AssetType.ASSET_TYPE_IMAGE;	 Catch:{ JSONException -> 0x008e }
        r2 = r10.m9072a(r2);	 Catch:{ JSONException -> 0x008e }
        r4 = r2.iterator();	 Catch:{ JSONException -> 0x008e }
    L_0x00b7:
        r2 = r4.hasNext();	 Catch:{ JSONException -> 0x008e }
        if (r2 == 0) goto L_0x01d7;
    L_0x00bd:
        r2 = r4.next();	 Catch:{ JSONException -> 0x008e }
        r2 = (com.inmobi.ads.NativeV2Asset) r2;	 Catch:{ JSONException -> 0x008e }
        r3 = r2.m9007d();	 Catch:{ JSONException -> 0x008e }
        r3 = (java.lang.String) r3;	 Catch:{ JSONException -> 0x008e }
        r6 = "http://";
        r6 = r3.startsWith(r6);	 Catch:{ JSONException -> 0x008e }
        if (r6 != 0) goto L_0x00b7;
    L_0x00d2:
        r6 = "https://";
        r3 = r3.startsWith(r6);	 Catch:{ JSONException -> 0x008e }
        if (r3 != 0) goto L_0x00b7;
    L_0x00db:
        r3 = r10.m9070a(r10, r2);	 Catch:{ JSONException -> 0x008e }
        if (r3 != 0) goto L_0x0108;
    L_0x00e1:
        r3 = com.inmobi.commons.core.utilities.Logger.InternalLogLevel.INTERNAL;	 Catch:{ JSONException -> 0x008e }
        r6 = f6923a;	 Catch:{ JSONException -> 0x008e }
        r7 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x008e }
        r7.<init>();	 Catch:{ JSONException -> 0x008e }
        r8 = "Could not find referenced asset for asset (";
        r7 = r7.append(r8);	 Catch:{ JSONException -> 0x008e }
        r2 = r2.m9005c();	 Catch:{ JSONException -> 0x008e }
        r2 = r7.append(r2);	 Catch:{ JSONException -> 0x008e }
        r7 = ")";
        r2 = r2.append(r7);	 Catch:{ JSONException -> 0x008e }
        r2 = r2.toString();	 Catch:{ JSONException -> 0x008e }
        com.inmobi.commons.core.utilities.Logger.m10359a(r3, r6, r2);	 Catch:{ JSONException -> 0x008e }
        goto L_0x00b7;
    L_0x0108:
        r6 = r3.m8986a();	 Catch:{ JSONException -> 0x008e }
        r7 = r2.m8986a();	 Catch:{ JSONException -> 0x008e }
        if (r6 != r7) goto L_0x011a;
    L_0x0112:
        r3 = r3.m9007d();	 Catch:{ JSONException -> 0x008e }
        r2.m8997a(r3);	 Catch:{ JSONException -> 0x008e }
        goto L_0x00b7;
    L_0x011a:
        r6 = com.inmobi.ads.NativeV2Asset.AssetType.ASSET_TYPE_VIDEO;	 Catch:{ JSONException -> 0x008e }
        r7 = r3.m8986a();	 Catch:{ JSONException -> 0x008e }
        if (r6 != r7) goto L_0x00b7;
    L_0x0122:
        r6 = com.inmobi.ads.NativeV2Asset.AssetReferencedCreative.ASSET_REFERENCED_CREATIVE_LINEAR;	 Catch:{ JSONException -> 0x008e }
        r7 = r3.m9019n();	 Catch:{ JSONException -> 0x008e }
        if (r6 != r7) goto L_0x0135;
    L_0x012a:
        r2 = com.inmobi.commons.core.utilities.Logger.InternalLogLevel.INTERNAL;	 Catch:{ JSONException -> 0x008e }
        r3 = f6923a;	 Catch:{ JSONException -> 0x008e }
        r6 = "Image asset cannot reference a linear creative in a video element!";
        com.inmobi.commons.core.utilities.Logger.m10359a(r2, r3, r6);	 Catch:{ JSONException -> 0x008e }
        goto L_0x00b7;
    L_0x0135:
        r6 = com.inmobi.ads.NativeV2Asset.AssetReferencedCreative.ASSET_REFERENCED_CREATIVE_COMPANION;	 Catch:{ JSONException -> 0x008e }
        r7 = r3.m9019n();	 Catch:{ JSONException -> 0x008e }
        if (r6 != r7) goto L_0x01cb;
    L_0x013d:
        r3 = (com.inmobi.ads.aw) r3;	 Catch:{ JSONException -> 0x008e }
        r6 = r3.m9441D();	 Catch:{ JSONException -> 0x008e }
        r7 = new com.inmobi.ads.bk;	 Catch:{ JSONException -> 0x008e }
        r7.<init>();	 Catch:{ JSONException -> 0x008e }
        r8 = r7.m9519a(r3, r2);	 Catch:{ JSONException -> 0x008e }
        if (r8 != 0) goto L_0x0190;
    L_0x014e:
        r2 = com.inmobi.ads.NativeV2Asset.AssetVisibility.GONE;	 Catch:{ JSONException -> 0x008e }
        r3.m8993a(r2);	 Catch:{ JSONException -> 0x008e }
        r2 = new java.util.HashMap;	 Catch:{ JSONException -> 0x008e }
        r2.<init>();	 Catch:{ JSONException -> 0x008e }
        r6 = r7.m9520a();	 Catch:{ JSONException -> 0x008e }
        if (r6 != 0) goto L_0x017f;
    L_0x015e:
        r6 = "[ERRORCODE]";
        r7 = com.inmobi.ads.VastErrorCode.MISSING_SUPPORTED_TYPE_COMPANION;	 Catch:{ JSONException -> 0x008e }
        r7 = r7.getId();	 Catch:{ JSONException -> 0x008e }
        r7 = r7.toString();	 Catch:{ JSONException -> 0x008e }
        r2.put(r6, r7);	 Catch:{ JSONException -> 0x008e }
    L_0x016e:
        r6 = com.inmobi.ads.ah.C3000a.TRACKER_EVENT_TYPE_ERROR;	 Catch:{ JSONException -> 0x008e }
        r3.m8995a(r6, r2);	 Catch:{ JSONException -> 0x008e }
        r2 = com.inmobi.commons.core.utilities.Logger.InternalLogLevel.INTERNAL;	 Catch:{ JSONException -> 0x008e }
        r3 = f6923a;	 Catch:{ JSONException -> 0x008e }
        r6 = "Unable to find the best-fit companion ad! Returning ...";
        com.inmobi.commons.core.utilities.Logger.m10359a(r2, r3, r6);	 Catch:{ JSONException -> 0x008e }
        goto L_0x00b7;
    L_0x017f:
        r6 = "[ERRORCODE]";
        r7 = com.inmobi.ads.VastErrorCode.NO_BEST_FIT_COMPANION;	 Catch:{ JSONException -> 0x008e }
        r7 = r7.getId();	 Catch:{ JSONException -> 0x008e }
        r7 = r7.toString();	 Catch:{ JSONException -> 0x008e }
        r2.put(r6, r7);	 Catch:{ JSONException -> 0x008e }
        goto L_0x016e;
    L_0x0190:
        r3 = com.inmobi.ads.bl.C3033a.C3032a.CREATIVE_TYPE_STATIC;	 Catch:{ JSONException -> 0x008e }
        r7 = r8.m9528a(r3);	 Catch:{ JSONException -> 0x008e }
        r6.mo6217a(r8);	 Catch:{ JSONException -> 0x008e }
        r6 = com.inmobi.commons.core.utilities.Logger.InternalLogLevel.INTERNAL;	 Catch:{ JSONException -> 0x008e }
        r8 = f6923a;	 Catch:{ JSONException -> 0x008e }
        r3 = new java.lang.StringBuilder;	 Catch:{ JSONException -> 0x008e }
        r3.<init>();	 Catch:{ JSONException -> 0x008e }
        r9 = "Setting asset value: ";
        r9 = r3.append(r9);	 Catch:{ JSONException -> 0x008e }
        r3 = 0;
        r3 = r7.get(r3);	 Catch:{ JSONException -> 0x008e }
        r3 = (com.inmobi.ads.bl.C3033a) r3;	 Catch:{ JSONException -> 0x008e }
        r3 = r3.f7277b;	 Catch:{ JSONException -> 0x008e }
        r3 = r9.append(r3);	 Catch:{ JSONException -> 0x008e }
        r3 = r3.toString();	 Catch:{ JSONException -> 0x008e }
        com.inmobi.commons.core.utilities.Logger.m10359a(r6, r8, r3);	 Catch:{ JSONException -> 0x008e }
        r3 = 0;
        r3 = r7.get(r3);	 Catch:{ JSONException -> 0x008e }
        r3 = (com.inmobi.ads.bl.C3033a) r3;	 Catch:{ JSONException -> 0x008e }
        r3 = r3.f7277b;	 Catch:{ JSONException -> 0x008e }
        r2.m8997a(r3);	 Catch:{ JSONException -> 0x008e }
        goto L_0x00b7;
    L_0x01cb:
        r2 = com.inmobi.commons.core.utilities.Logger.InternalLogLevel.INTERNAL;	 Catch:{ JSONException -> 0x008e }
        r3 = f6923a;	 Catch:{ JSONException -> 0x008e }
        r6 = "Unknown creative type reference for image asset! Returning ...";
        com.inmobi.commons.core.utilities.Logger.m10359a(r2, r3, r6);	 Catch:{ JSONException -> 0x008e }
        goto L_0x00b7;
    L_0x01d7:
        r2 = r10.f6935m;	 Catch:{ JSONException -> 0x008e }
        r2 = r2.entrySet();	 Catch:{ JSONException -> 0x008e }
        r6 = r2.iterator();	 Catch:{ JSONException -> 0x008e }
    L_0x01e1:
        r2 = r6.hasNext();	 Catch:{ JSONException -> 0x008e }
        if (r2 == 0) goto L_0x0278;
    L_0x01e7:
        r2 = r6.next();	 Catch:{ JSONException -> 0x008e }
        r2 = (java.util.Map.Entry) r2;	 Catch:{ JSONException -> 0x008e }
        r3 = r2.getValue();	 Catch:{ JSONException -> 0x008e }
        r3 = (java.lang.String) r3;	 Catch:{ JSONException -> 0x008e }
        r4 = r10.f6934l;	 Catch:{ JSONException -> 0x008e }
        r2 = r2.getKey();	 Catch:{ JSONException -> 0x008e }
        r2 = r4.get(r2);	 Catch:{ JSONException -> 0x008e }
        r2 = (com.inmobi.ads.NativeV2Asset) r2;	 Catch:{ JSONException -> 0x008e }
        r4 = com.inmobi.ads.NativeV2Asset.AssetDisplayOnType.ASSET_DISPLAY_ON_TYPE_PERCENTAGE;	 Catch:{ JSONException -> 0x008e }
        r7 = r2.m9020o();	 Catch:{ JSONException -> 0x008e }
        if (r4 != r7) goto L_0x01e1;
    L_0x0207:
        r4 = r10.f6934l;	 Catch:{ JSONException -> 0x008e }
        r3 = r4.get(r3);	 Catch:{ JSONException -> 0x008e }
        r3 = (com.inmobi.ads.NativeV2Asset) r3;	 Catch:{ JSONException -> 0x008e }
        r4 = com.inmobi.ads.NativeV2Asset.AssetType.ASSET_TYPE_VIDEO;	 Catch:{ JSONException -> 0x008e }
        r7 = r3.m8986a();	 Catch:{ JSONException -> 0x008e }
        if (r4 != r7) goto L_0x01e1;
    L_0x0217:
        r0 = r3;
        r0 = (com.inmobi.ads.aw) r0;	 Catch:{ JSONException -> 0x008e }
        r4 = r0;
        r4 = r4.m9441D();	 Catch:{ JSONException -> 0x008e }
        r4 = (com.inmobi.ads.bo) r4;	 Catch:{ JSONException -> 0x008e }
        r4 = r4.m9581h();	 Catch:{ JSONException -> 0x008e }
        r7 = ":";
        r4 = r4.split(r7);	 Catch:{ JSONException -> 0x008e }
        r7 = 1;
        r7 = r4[r7];	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024a }
        r7 = java.lang.Integer.parseInt(r7);	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024a }
        r7 = r7 * 60;
        r8 = 2;
        r4 = r4[r8];	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024a }
        r4 = java.lang.Integer.parseInt(r4);	 Catch:{ ArrayIndexOutOfBoundsException -> 0x024a }
        r4 = r4 + r7;
    L_0x023d:
        if (r4 != 0) goto L_0x0259;
    L_0x023f:
        r4 = r4 / 4;
        r2.m8987a(r4);	 Catch:{ JSONException -> 0x008e }
    L_0x0244:
        r3 = (com.inmobi.ads.aw) r3;	 Catch:{ JSONException -> 0x008e }
        r3.m9446c(r2);	 Catch:{ JSONException -> 0x008e }
        goto L_0x01e1;
    L_0x024a:
        r4 = move-exception;
        r7 = com.inmobi.commons.core.p115d.C3135c.m10255a();	 Catch:{ JSONException -> 0x008e }
        r8 = new com.inmobi.commons.core.d.b;	 Catch:{ JSONException -> 0x008e }
        r8.<init>(r4);	 Catch:{ JSONException -> 0x008e }
        r7.m10279a(r8);	 Catch:{ JSONException -> 0x008e }
        r4 = r5;
        goto L_0x023d;
    L_0x0259:
        r7 = r2.m9021p();	 Catch:{ JSONException -> 0x008e }
        switch(r7) {
            case 50: goto L_0x0266;
            case 75: goto L_0x026c;
            case 100: goto L_0x0274;
            default: goto L_0x0260;
        };	 Catch:{ JSONException -> 0x008e }
    L_0x0260:
        r4 = r4 / 4;
        r2.m8987a(r4);	 Catch:{ JSONException -> 0x008e }
        goto L_0x0244;
    L_0x0266:
        r4 = r4 / 2;
        r2.m8987a(r4);	 Catch:{ JSONException -> 0x008e }
        goto L_0x0244;
    L_0x026c:
        r4 = r4 * 3;
        r4 = r4 / 4;
        r2.m8987a(r4);	 Catch:{ JSONException -> 0x008e }
        goto L_0x0244;
    L_0x0274:
        r2.m8987a(r4);	 Catch:{ JSONException -> 0x008e }
        goto L_0x0244;
    L_0x0278:
        r2 = r10.f6924b;	 Catch:{ JSONException -> 0x008e }
        r3 = "pages";
        r2 = r2.isNull(r3);	 Catch:{ JSONException -> 0x008e }
        if (r2 == 0) goto L_0x028c;
    L_0x0283:
        r2 = new org.json.JSONArray;	 Catch:{ JSONException -> 0x008e }
        r2.<init>();	 Catch:{ JSONException -> 0x008e }
        r10.f6930h = r2;	 Catch:{ JSONException -> 0x008e }
        goto L_0x009b;
    L_0x028c:
        r2 = r10.f6924b;	 Catch:{ JSONException -> 0x008e }
        r3 = "pages";
        r2 = r2.getJSONArray(r3);	 Catch:{ JSONException -> 0x008e }
        r10.f6930h = r2;	 Catch:{ JSONException -> 0x008e }
        goto L_0x009b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.NativeV2DataModel.m():void");
    }

    protected NativeV2Asset m9070a(@NonNull NativeV2DataModel nativeV2DataModel, @NonNull NativeV2Asset nativeV2Asset) {
        if (nativeV2DataModel == null) {
            return null;
        }
        String str = (String) nativeV2Asset.m9007d();
        if (str == null || str.length() == 0) {
            return null;
        }
        String[] split = str.split("\\|");
        NativeV2Asset b = nativeV2DataModel.m9078b(split[0]);
        if (b == null) {
            return m9070a(m9085e(), nativeV2Asset);
        }
        if (b.equals(nativeV2Asset)) {
            return null;
        }
        if (1 == split.length) {
            b.m8992a(AssetReferencedCreative.ASSET_REFERENCED_CREATIVE_LINEAR);
            return b;
        }
        b.m8992a(m9069a(split[1]));
        Logger.m10359a(InternalLogLevel.INTERNAL, f6923a, "Referenced asset (" + b.m9005c() + ")");
        return b;
    }

    public AssetReferencedCreative m9069a(String str) {
        String trim = str.toLowerCase(Locale.US).trim();
        Object obj = -1;
        switch (trim.hashCode()) {
            case -1412832500:
                if (trim.equals("companion")) {
                    obj = 3;
                    break;
                }
                break;
            case 0:
                if (trim.equals("")) {
                    obj = 1;
                    break;
                }
                break;
            case 112202875:
                if (trim.equals("video")) {
                    obj = 2;
                    break;
                }
                break;
        }
        switch (obj) {
            case 1:
            case 2:
                return AssetReferencedCreative.ASSET_REFERENCED_CREATIVE_LINEAR;
            case 3:
                return AssetReferencedCreative.ASSET_REFERENCED_CREATIVE_COMPANION;
            default:
                return AssetReferencedCreative.ASSET_REFERENCED_CREATIVE_NONE;
        }
    }

    @VisibleForTesting
    boolean m9094n() {
        if (m9079b() == null) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f6923a, "Invalid Data Model: No Root Container");
            return false;
        } else if (m9089i() == null) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f6923a, "No Card Scrollable in the data model");
            return m9066p();
        } else if (m9090j() > 0) {
            return m9066p();
        } else {
            Logger.m10359a(InternalLogLevel.INTERNAL, f6923a, "Invalid Data Model: No Cards in Card Scrollable");
            return false;
        }
    }

    private boolean m9066p() {
        List<NativeV2Asset> a = m9072a(AssetType.ASSET_TYPE_VIDEO);
        if (a == null || a.size() <= 0) {
            return true;
        }
        for (NativeV2Asset nativeV2Asset : a) {
            if (nativeV2Asset.m9025t().length() == 0) {
                Logger.m10359a(InternalLogLevel.INTERNAL, f6923a, "Video asset has invalid ID! CTA link resolution may not work");
            }
            aw awVar = (aw) nativeV2Asset;
            if (awVar.m9441D() == null) {
                Logger.m10359a(InternalLogLevel.INTERNAL, f6923a, "No Vast XML. Discarding DataModel");
                return false;
            }
            List d = awVar.m9441D().mo6220d();
            if (d == null || d.size() == 0) {
                Logger.m10359a(InternalLogLevel.INTERNAL, f6923a, "No Media files. Discarding DataModel");
                return false;
            }
            String b = awVar.m9441D().mo6219b();
            if (b != null) {
                if (b.length() == 0) {
                }
            }
            Logger.m10359a(InternalLogLevel.INTERNAL, f6923a, "Invalid Media URL.Discarding the model");
            Map hashMap = new HashMap();
            hashMap.put("[ERRORCODE]", VastErrorCode.NO_SUPPORTED_MEDIA.getId().toString());
            awVar.m8995a(C3000a.TRACKER_EVENT_TYPE_ERROR, hashMap);
            return false;
        }
        return true;
    }

    @TargetApi(15)
    private NativeV2Asset m9031a(@NonNull JSONObject jSONObject, AssetType assetType, String str) {
        NativeV2Asset nativeV2Asset;
        Throwable th;
        String str2;
        NativeV2Asset nativeV2Asset2;
        Throwable th2;
        String str3;
        AssetInteractionMode assetInteractionMode;
        String o;
        C3004a c3004a;
        JSONArray jSONArray;
        int i;
        NativeV2Asset a;
        C3015a c3015a;
        C3015a c3015a2;
        boolean optBoolean;
        JSONObject jSONObject2;
        String o2;
        NativeStrandAssetStyle c3023a;
        bp a2;
        Object obj;
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        Map hashMap;
        Iterator keys;
        String str4;
        NativeV2Asset awVar;
        NativeV2Asset nativeV2Asset3;
        List arrayList;
        String e = m9043e(jSONObject);
        String f = m9045f(jSONObject);
        JSONObject i2 = m9051i(jSONObject);
        if (m9077a(i2, assetType)) {
            NativeStrandAssetStyle a3;
            AssetInteractionMode assetInteractionMode2;
            Point j = m9052j(jSONObject);
            Point k = m9054k(jSONObject);
            List a4 = m9073a(jSONObject);
            AssetDisplayOnType l = m9057l(jSONObject);
            int m = m9058m(jSONObject);
            String b = m9081b(jSONObject);
            NativeV2Asset nativeV2Asset4 = null;
            String str5 = "";
            AssetValueType p = m9064p(m9049h(jSONObject));
            JSONArray p2 = m9065p(jSONObject);
            if (p2 == null || p2.length() == 0) {
                nativeV2Asset = null;
            } else {
                try {
                    str5 = p2.getString(0);
                    if (p == AssetValueType.ASSET_VALUE_REFERENCE) {
                        nativeV2Asset4 = m9078b(str5);
                        if (nativeV2Asset4 == null) {
                            try {
                                if (this.f6931i != null) {
                                    nativeV2Asset4 = this.f6931i.m9078b(str5);
                                }
                            } catch (Throwable e2) {
                                th = e2;
                                str2 = str5;
                                nativeV2Asset2 = nativeV2Asset4;
                                th2 = th;
                                C3135c.m10255a().m10279a(new C3132b(th2));
                                str3 = str2;
                                nativeV2Asset = nativeV2Asset2;
                                str5 = str3;
                                switch (assetType) {
                                    case ASSET_TYPE_CONTAINER:
                                        a3 = m9030a(j, k, i2);
                                        assetInteractionMode = AssetInteractionMode.ASSET_INTERACTION_MODE_NO_ACTION;
                                        if (m9067q(jSONObject)) {
                                            assetInteractionMode = AssetInteractionMode.ASSET_INTERACTION_MODE_BROWSER;
                                            if (!jSONObject.getJSONObject("assetOnclick").isNull("openMode")) {
                                                assetInteractionMode = m9044f(jSONObject.getJSONObject("assetOnclick").getString("openMode"));
                                                o = m9063o(jSONObject.getJSONObject("assetOnclick"));
                                                c3004a = C3004a.CARD_SCROLLABLE_TYPE_PAGED;
                                                if (i2.has("transitionEffect")) {
                                                    c3004a = m9062o(i2.getString("transitionEffect"));
                                                }
                                                if (a4 != null) {
                                                    break;
                                                }
                                                nativeV2Asset4 = new ak(e, f, a3, assetInteractionMode, jSONObject, c3004a);
                                                nativeV2Asset4.m8998a(str);
                                                if (o != null) {
                                                    nativeV2Asset4.m9010e(o);
                                                }
                                                m9034a(nativeV2Asset4, jSONObject);
                                                jSONArray = jSONObject.getJSONArray("assetValue");
                                                for (i = 0; i < jSONArray.length(); i++) {
                                                    str5 = str + "." + "assetValue" + "[" + i + "]";
                                                    i2 = jSONArray.getJSONObject(i);
                                                    a = m9031a(i2, m9048h(m9047g(i2)), str5);
                                                    if (a != null) {
                                                        Logger.m10359a(InternalLogLevel.INTERNAL, f6923a, "Cannot build asset from JSON: " + i2);
                                                    } else {
                                                        a.m8998a(str5);
                                                        a.m9002b(nativeV2Asset4);
                                                        nativeV2Asset4.m9363c(a);
                                                    }
                                                }
                                                break;
                                            }
                                        }
                                        o = null;
                                        c3004a = C3004a.CARD_SCROLLABLE_TYPE_PAGED;
                                        if (i2.has("transitionEffect")) {
                                            c3004a = m9062o(i2.getString("transitionEffect"));
                                        }
                                        if (a4 != null) {
                                        }
                                        nativeV2Asset4 = new ak(e, f, a3, assetInteractionMode, jSONObject, c3004a);
                                        nativeV2Asset4.m8998a(str);
                                        if (o != null) {
                                            nativeV2Asset4.m9010e(o);
                                        }
                                        m9034a(nativeV2Asset4, jSONObject);
                                        jSONArray = jSONObject.getJSONArray("assetValue");
                                        for (i = 0; i < jSONArray.length(); i++) {
                                            str5 = str + "." + "assetValue" + "[" + i + "]";
                                            i2 = jSONArray.getJSONObject(i);
                                            a = m9031a(i2, m9048h(m9047g(i2)), str5);
                                            if (a != null) {
                                                a.m8998a(str5);
                                                a.m9002b(nativeV2Asset4);
                                                nativeV2Asset4.m9363c(a);
                                            } else {
                                                Logger.m10359a(InternalLogLevel.INTERNAL, f6923a, "Cannot build asset from JSON: " + i2);
                                            }
                                        }
                                    case ASSET_TYPE_TEXT:
                                        nativeV2Asset4 = new as(e, f, m9036b(j, k, i2), str5);
                                        nativeV2Asset4.m8998a(str);
                                        break;
                                    case ASSET_TYPE_ICON:
                                        nativeV2Asset4 = new an(e, f, m9030a(j, k, i2), m9041d(jSONObject));
                                        nativeV2Asset4.m8998a(str);
                                        break;
                                    case ASSET_TYPE_TIMER:
                                        a3 = m9030a(j, k, i2);
                                        c3015a = null;
                                        if (jSONObject.has("startOffset")) {
                                            c3015a = m9068r(jSONObject.getJSONObject("startOffset"));
                                        }
                                        c3015a2 = null;
                                        if (jSONObject.has("timerDuration")) {
                                            c3015a2 = m9068r(jSONObject.getJSONObject("timerDuration"));
                                        }
                                        optBoolean = jSONObject.optBoolean("displayTimer", true);
                                        nativeV2Asset4 = new at(e, f, a3, c3015a, c3015a2);
                                        nativeV2Asset4.m9401b(optBoolean);
                                        if (jSONObject.has("assetOnFinish")) {
                                            jSONObject2 = (JSONObject) jSONObject.get("assetOnFinish");
                                            if (jSONObject2.has(C1042c.jL)) {
                                                nativeV2Asset4.m8989a(m9046g(jSONObject2.getString(C1042c.jL)));
                                            }
                                        }
                                        nativeV2Asset4.m8998a(str);
                                        break;
                                    case ASSET_TYPE_IMAGE:
                                        a3 = m9030a(j, k, i2);
                                        assetInteractionMode2 = AssetInteractionMode.ASSET_INTERACTION_MODE_NO_ACTION;
                                        if (m9067q(jSONObject)) {
                                            assetInteractionMode2 = AssetInteractionMode.ASSET_INTERACTION_MODE_BROWSER;
                                            if (!jSONObject.getJSONObject("assetOnclick").isNull("openMode")) {
                                                assetInteractionMode2 = m9044f(jSONObject.getJSONObject("assetOnclick").getString("openMode"));
                                                o2 = m9063o(jSONObject.getJSONObject("assetOnclick"));
                                                if (a4 != null) {
                                                    break;
                                                }
                                                nativeV2Asset4 = new ao(e, f, a3, m9041d(jSONObject), assetInteractionMode2, jSONObject);
                                                nativeV2Asset4.m8998a(str);
                                                m9034a(nativeV2Asset4, jSONObject);
                                                if (o2 != null) {
                                                    nativeV2Asset4.m9010e(o2);
                                                    break;
                                                }
                                            }
                                        }
                                        o2 = null;
                                        if (a4 != null) {
                                        }
                                        nativeV2Asset4 = new ao(e, f, a3, m9041d(jSONObject), assetInteractionMode2, jSONObject);
                                        nativeV2Asset4.m8998a(str);
                                        m9034a(nativeV2Asset4, jSONObject);
                                        if (o2 != null) {
                                            nativeV2Asset4.m9010e(o2);
                                        }
                                        break;
                                    case ASSET_TYPE_VIDEO:
                                        if (VERSION.SDK_INT >= 15) {
                                            if (this.f6933k.get(AssetType.ASSET_TYPE_VIDEO) != null) {
                                                Logger.m10359a(InternalLogLevel.INTERNAL, f6923a, "One video asset already present! I will add this to the data model (for now) but ideally consider skipping this asset");
                                            }
                                            c3023a = new C3023a(j.x, j.y, k.x, k.y);
                                            if (this.f6936n == null) {
                                                a2 = m9033a(jSONObject, str5, nativeV2Asset);
                                            } else {
                                                obj = this.f6936n;
                                            }
                                            z = true;
                                            z2 = true;
                                            z3 = false;
                                            z4 = true;
                                            if (PlacementType.PLACEMENT_TYPE_INLINE == this.f6938p) {
                                                z3 = jSONObject.optBoolean("loop", false);
                                                z4 = jSONObject.optBoolean("showProgress", true);
                                                z = jSONObject.optBoolean("soundOn", true);
                                                z2 = jSONObject.optBoolean("showMute", true);
                                            } else if (nativeV2Asset != null) {
                                                z3 = jSONObject.optBoolean("loop", true);
                                                z4 = jSONObject.optBoolean("showProgress", false);
                                                z = jSONObject.optBoolean("soundOn", false);
                                                z2 = jSONObject.optBoolean("showMute", false);
                                            } else if (((Boolean) nativeV2Asset.m9027v().get("didRequestFullScreen")).booleanValue()) {
                                                z3 = jSONObject.optBoolean("loop", false);
                                                z4 = jSONObject.optBoolean("showProgress", true);
                                                z = jSONObject.optBoolean("soundOn", true);
                                                z2 = jSONObject.optBoolean("showMute", true);
                                            }
                                            hashMap = new HashMap();
                                            if (!jSONObject.isNull("videoViewabilityConfig")) {
                                                i2 = jSONObject.getJSONObject("videoViewabilityConfig");
                                                keys = i2.keys();
                                                while (keys.hasNext()) {
                                                    str4 = (String) keys.next();
                                                    hashMap.put(str4, i2.get(str4));
                                                }
                                            }
                                            awVar = new aw(e, f, c3023a, a2, z, z2, z3, z4, a4, jSONObject, null);
                                            ((aw) awVar).m9443a(hashMap);
                                            awVar.m8998a(str);
                                            awVar.m8994a(nativeV2Asset);
                                            if (nativeV2Asset != null) {
                                                nativeV2Asset.m8994a(awVar);
                                            }
                                            nativeV2Asset4 = awVar;
                                            break;
                                        }
                                        nativeV2Asset4 = null;
                                        break;
                                    case ASSET_TYPE_RATING:
                                        nativeV2Asset4 = null;
                                        break;
                                    case ASSET_TYPE_CTA:
                                        if (!m9067q(jSONObject)) {
                                            a3 = m9038c(j, k, i2);
                                            assetInteractionMode2 = AssetInteractionMode.ASSET_INTERACTION_MODE_BROWSER;
                                            if (jSONObject.getJSONObject("assetOnclick").isNull("openMode")) {
                                                o2 = null;
                                            } else {
                                                assetInteractionMode2 = m9044f(jSONObject.getJSONObject("assetOnclick").getString("openMode"));
                                                o2 = m9063o(jSONObject.getJSONObject("assetOnclick"));
                                            }
                                            if (a4 != null) {
                                                break;
                                            }
                                            nativeV2Asset4 = new al(e, f, a3, str5, assetInteractionMode2, jSONObject);
                                            nativeV2Asset4.m8998a(str);
                                            m9034a(nativeV2Asset4, jSONObject);
                                            if (o2 != null) {
                                                nativeV2Asset4.m9010e(o2);
                                                break;
                                            }
                                        }
                                        return null;
                                        break;
                                    default:
                                        nativeV2Asset4 = null;
                                        break;
                                }
                                nativeV2Asset3 = nativeV2Asset4;
                                if (nativeV2Asset3 != null) {
                                    nativeV2Asset3.m8990a(l);
                                    nativeV2Asset3.m8987a(m);
                                    nativeV2Asset3.m9004b(b);
                                    this.f6935m.put(e, b);
                                    this.f6934l.put(e, nativeV2Asset3);
                                    if (this.f6933k.containsKey(assetType)) {
                                        ((List) this.f6933k.get(assetType)).add(nativeV2Asset3);
                                    } else {
                                        arrayList = new ArrayList();
                                        arrayList.add(nativeV2Asset3);
                                        this.f6933k.put(assetType, arrayList);
                                    }
                                }
                                return nativeV2Asset3;
                            }
                        }
                    }
                    nativeV2Asset = nativeV2Asset4;
                } catch (Throwable e22) {
                    th = e22;
                    str2 = str5;
                    nativeV2Asset2 = null;
                    th2 = th;
                    C3135c.m10255a().m10279a(new C3132b(th2));
                    str3 = str2;
                    nativeV2Asset = nativeV2Asset2;
                    str5 = str3;
                    switch (assetType) {
                        case ASSET_TYPE_CONTAINER:
                            a3 = m9030a(j, k, i2);
                            assetInteractionMode = AssetInteractionMode.ASSET_INTERACTION_MODE_NO_ACTION;
                            if (m9067q(jSONObject)) {
                                assetInteractionMode = AssetInteractionMode.ASSET_INTERACTION_MODE_BROWSER;
                                if (jSONObject.getJSONObject("assetOnclick").isNull("openMode")) {
                                    assetInteractionMode = m9044f(jSONObject.getJSONObject("assetOnclick").getString("openMode"));
                                    o = m9063o(jSONObject.getJSONObject("assetOnclick"));
                                    c3004a = C3004a.CARD_SCROLLABLE_TYPE_PAGED;
                                    if (i2.has("transitionEffect")) {
                                        c3004a = m9062o(i2.getString("transitionEffect"));
                                    }
                                    if (a4 != null) {
                                        break;
                                    }
                                    nativeV2Asset4 = new ak(e, f, a3, assetInteractionMode, jSONObject, c3004a);
                                    nativeV2Asset4.m8998a(str);
                                    if (o != null) {
                                        nativeV2Asset4.m9010e(o);
                                    }
                                    m9034a(nativeV2Asset4, jSONObject);
                                    jSONArray = jSONObject.getJSONArray("assetValue");
                                    for (i = 0; i < jSONArray.length(); i++) {
                                        str5 = str + "." + "assetValue" + "[" + i + "]";
                                        i2 = jSONArray.getJSONObject(i);
                                        a = m9031a(i2, m9048h(m9047g(i2)), str5);
                                        if (a != null) {
                                            Logger.m10359a(InternalLogLevel.INTERNAL, f6923a, "Cannot build asset from JSON: " + i2);
                                        } else {
                                            a.m8998a(str5);
                                            a.m9002b(nativeV2Asset4);
                                            nativeV2Asset4.m9363c(a);
                                        }
                                    }
                                    break;
                                }
                            }
                            o = null;
                            c3004a = C3004a.CARD_SCROLLABLE_TYPE_PAGED;
                            if (i2.has("transitionEffect")) {
                                c3004a = m9062o(i2.getString("transitionEffect"));
                            }
                            if (a4 != null) {
                            }
                            nativeV2Asset4 = new ak(e, f, a3, assetInteractionMode, jSONObject, c3004a);
                            nativeV2Asset4.m8998a(str);
                            if (o != null) {
                                nativeV2Asset4.m9010e(o);
                            }
                            m9034a(nativeV2Asset4, jSONObject);
                            jSONArray = jSONObject.getJSONArray("assetValue");
                            for (i = 0; i < jSONArray.length(); i++) {
                                str5 = str + "." + "assetValue" + "[" + i + "]";
                                i2 = jSONArray.getJSONObject(i);
                                a = m9031a(i2, m9048h(m9047g(i2)), str5);
                                if (a != null) {
                                    a.m8998a(str5);
                                    a.m9002b(nativeV2Asset4);
                                    nativeV2Asset4.m9363c(a);
                                } else {
                                    Logger.m10359a(InternalLogLevel.INTERNAL, f6923a, "Cannot build asset from JSON: " + i2);
                                }
                            }
                        case ASSET_TYPE_TEXT:
                            nativeV2Asset4 = new as(e, f, m9036b(j, k, i2), str5);
                            nativeV2Asset4.m8998a(str);
                            break;
                        case ASSET_TYPE_ICON:
                            nativeV2Asset4 = new an(e, f, m9030a(j, k, i2), m9041d(jSONObject));
                            nativeV2Asset4.m8998a(str);
                            break;
                        case ASSET_TYPE_TIMER:
                            a3 = m9030a(j, k, i2);
                            c3015a = null;
                            if (jSONObject.has("startOffset")) {
                                c3015a = m9068r(jSONObject.getJSONObject("startOffset"));
                            }
                            c3015a2 = null;
                            if (jSONObject.has("timerDuration")) {
                                c3015a2 = m9068r(jSONObject.getJSONObject("timerDuration"));
                            }
                            optBoolean = jSONObject.optBoolean("displayTimer", true);
                            nativeV2Asset4 = new at(e, f, a3, c3015a, c3015a2);
                            nativeV2Asset4.m9401b(optBoolean);
                            if (jSONObject.has("assetOnFinish")) {
                                jSONObject2 = (JSONObject) jSONObject.get("assetOnFinish");
                                if (jSONObject2.has(C1042c.jL)) {
                                    nativeV2Asset4.m8989a(m9046g(jSONObject2.getString(C1042c.jL)));
                                }
                            }
                            nativeV2Asset4.m8998a(str);
                            break;
                        case ASSET_TYPE_IMAGE:
                            a3 = m9030a(j, k, i2);
                            assetInteractionMode2 = AssetInteractionMode.ASSET_INTERACTION_MODE_NO_ACTION;
                            if (m9067q(jSONObject)) {
                                assetInteractionMode2 = AssetInteractionMode.ASSET_INTERACTION_MODE_BROWSER;
                                if (jSONObject.getJSONObject("assetOnclick").isNull("openMode")) {
                                    assetInteractionMode2 = m9044f(jSONObject.getJSONObject("assetOnclick").getString("openMode"));
                                    o2 = m9063o(jSONObject.getJSONObject("assetOnclick"));
                                    if (a4 != null) {
                                        break;
                                    }
                                    nativeV2Asset4 = new ao(e, f, a3, m9041d(jSONObject), assetInteractionMode2, jSONObject);
                                    nativeV2Asset4.m8998a(str);
                                    m9034a(nativeV2Asset4, jSONObject);
                                    if (o2 != null) {
                                        nativeV2Asset4.m9010e(o2);
                                        break;
                                    }
                                }
                            }
                            o2 = null;
                            if (a4 != null) {
                            }
                            nativeV2Asset4 = new ao(e, f, a3, m9041d(jSONObject), assetInteractionMode2, jSONObject);
                            nativeV2Asset4.m8998a(str);
                            m9034a(nativeV2Asset4, jSONObject);
                            if (o2 != null) {
                                nativeV2Asset4.m9010e(o2);
                            }
                            break;
                        case ASSET_TYPE_VIDEO:
                            if (VERSION.SDK_INT >= 15) {
                                nativeV2Asset4 = null;
                                break;
                            }
                            if (this.f6933k.get(AssetType.ASSET_TYPE_VIDEO) != null) {
                                Logger.m10359a(InternalLogLevel.INTERNAL, f6923a, "One video asset already present! I will add this to the data model (for now) but ideally consider skipping this asset");
                            }
                            c3023a = new C3023a(j.x, j.y, k.x, k.y);
                            if (this.f6936n == null) {
                                obj = this.f6936n;
                            } else {
                                a2 = m9033a(jSONObject, str5, nativeV2Asset);
                            }
                            z = true;
                            z2 = true;
                            z3 = false;
                            z4 = true;
                            if (PlacementType.PLACEMENT_TYPE_INLINE == this.f6938p) {
                                z3 = jSONObject.optBoolean("loop", false);
                                z4 = jSONObject.optBoolean("showProgress", true);
                                z = jSONObject.optBoolean("soundOn", true);
                                z2 = jSONObject.optBoolean("showMute", true);
                            } else if (nativeV2Asset != null) {
                                z3 = jSONObject.optBoolean("loop", true);
                                z4 = jSONObject.optBoolean("showProgress", false);
                                z = jSONObject.optBoolean("soundOn", false);
                                z2 = jSONObject.optBoolean("showMute", false);
                            } else if (((Boolean) nativeV2Asset.m9027v().get("didRequestFullScreen")).booleanValue()) {
                                z3 = jSONObject.optBoolean("loop", false);
                                z4 = jSONObject.optBoolean("showProgress", true);
                                z = jSONObject.optBoolean("soundOn", true);
                                z2 = jSONObject.optBoolean("showMute", true);
                            }
                            hashMap = new HashMap();
                            if (jSONObject.isNull("videoViewabilityConfig")) {
                                i2 = jSONObject.getJSONObject("videoViewabilityConfig");
                                keys = i2.keys();
                                while (keys.hasNext()) {
                                    str4 = (String) keys.next();
                                    hashMap.put(str4, i2.get(str4));
                                }
                            }
                            awVar = new aw(e, f, c3023a, a2, z, z2, z3, z4, a4, jSONObject, null);
                            ((aw) awVar).m9443a(hashMap);
                            awVar.m8998a(str);
                            awVar.m8994a(nativeV2Asset);
                            if (nativeV2Asset != null) {
                                nativeV2Asset.m8994a(awVar);
                            }
                            nativeV2Asset4 = awVar;
                            break;
                        case ASSET_TYPE_RATING:
                            nativeV2Asset4 = null;
                            break;
                        case ASSET_TYPE_CTA:
                            if (!m9067q(jSONObject)) {
                                a3 = m9038c(j, k, i2);
                                assetInteractionMode2 = AssetInteractionMode.ASSET_INTERACTION_MODE_BROWSER;
                                if (jSONObject.getJSONObject("assetOnclick").isNull("openMode")) {
                                    o2 = null;
                                } else {
                                    assetInteractionMode2 = m9044f(jSONObject.getJSONObject("assetOnclick").getString("openMode"));
                                    o2 = m9063o(jSONObject.getJSONObject("assetOnclick"));
                                }
                                if (a4 != null) {
                                    break;
                                }
                                nativeV2Asset4 = new al(e, f, a3, str5, assetInteractionMode2, jSONObject);
                                nativeV2Asset4.m8998a(str);
                                m9034a(nativeV2Asset4, jSONObject);
                                if (o2 != null) {
                                    nativeV2Asset4.m9010e(o2);
                                    break;
                                }
                            }
                            return null;
                            break;
                        default:
                            nativeV2Asset4 = null;
                            break;
                    }
                    nativeV2Asset3 = nativeV2Asset4;
                    if (nativeV2Asset3 != null) {
                        nativeV2Asset3.m8990a(l);
                        nativeV2Asset3.m8987a(m);
                        nativeV2Asset3.m9004b(b);
                        this.f6935m.put(e, b);
                        this.f6934l.put(e, nativeV2Asset3);
                        if (this.f6933k.containsKey(assetType)) {
                            arrayList = new ArrayList();
                            arrayList.add(nativeV2Asset3);
                            this.f6933k.put(assetType, arrayList);
                        } else {
                            ((List) this.f6933k.get(assetType)).add(nativeV2Asset3);
                        }
                    }
                    return nativeV2Asset3;
                }
            }
            try {
                switch (assetType) {
                    case ASSET_TYPE_CONTAINER:
                        a3 = m9030a(j, k, i2);
                        assetInteractionMode = AssetInteractionMode.ASSET_INTERACTION_MODE_NO_ACTION;
                        if (m9067q(jSONObject)) {
                            assetInteractionMode = AssetInteractionMode.ASSET_INTERACTION_MODE_BROWSER;
                            if (jSONObject.getJSONObject("assetOnclick").isNull("openMode")) {
                                assetInteractionMode = m9044f(jSONObject.getJSONObject("assetOnclick").getString("openMode"));
                                o = m9063o(jSONObject.getJSONObject("assetOnclick"));
                                c3004a = C3004a.CARD_SCROLLABLE_TYPE_PAGED;
                                if (i2.has("transitionEffect")) {
                                    c3004a = m9062o(i2.getString("transitionEffect"));
                                }
                                if (a4 != null || a4.size() == 0) {
                                    nativeV2Asset4 = new ak(e, f, a3, assetInteractionMode, jSONObject, c3004a);
                                } else {
                                    nativeV2Asset4 = new ak(e, f, a3, a4, assetInteractionMode, jSONObject, c3004a);
                                }
                                nativeV2Asset4.m8998a(str);
                                if (o != null) {
                                    nativeV2Asset4.m9010e(o);
                                }
                                m9034a(nativeV2Asset4, jSONObject);
                                jSONArray = jSONObject.getJSONArray("assetValue");
                                for (i = 0; i < jSONArray.length(); i++) {
                                    str5 = str + "." + "assetValue" + "[" + i + "]";
                                    i2 = jSONArray.getJSONObject(i);
                                    a = m9031a(i2, m9048h(m9047g(i2)), str5);
                                    if (a != null) {
                                        Logger.m10359a(InternalLogLevel.INTERNAL, f6923a, "Cannot build asset from JSON: " + i2);
                                    } else {
                                        a.m8998a(str5);
                                        a.m9002b(nativeV2Asset4);
                                        nativeV2Asset4.m9363c(a);
                                    }
                                }
                                break;
                            }
                        }
                        o = null;
                        c3004a = C3004a.CARD_SCROLLABLE_TYPE_PAGED;
                        if (i2.has("transitionEffect")) {
                            c3004a = m9062o(i2.getString("transitionEffect"));
                        }
                        if (a4 != null) {
                            break;
                        }
                        nativeV2Asset4 = new ak(e, f, a3, assetInteractionMode, jSONObject, c3004a);
                        nativeV2Asset4.m8998a(str);
                        if (o != null) {
                            nativeV2Asset4.m9010e(o);
                        }
                        m9034a(nativeV2Asset4, jSONObject);
                        jSONArray = jSONObject.getJSONArray("assetValue");
                        for (i = 0; i < jSONArray.length(); i++) {
                            str5 = str + "." + "assetValue" + "[" + i + "]";
                            i2 = jSONArray.getJSONObject(i);
                            a = m9031a(i2, m9048h(m9047g(i2)), str5);
                            if (a != null) {
                                a.m8998a(str5);
                                a.m9002b(nativeV2Asset4);
                                nativeV2Asset4.m9363c(a);
                            } else {
                                Logger.m10359a(InternalLogLevel.INTERNAL, f6923a, "Cannot build asset from JSON: " + i2);
                            }
                        }
                    case ASSET_TYPE_TEXT:
                        nativeV2Asset4 = new as(e, f, m9036b(j, k, i2), str5);
                        nativeV2Asset4.m8998a(str);
                        break;
                    case ASSET_TYPE_ICON:
                        nativeV2Asset4 = new an(e, f, m9030a(j, k, i2), m9041d(jSONObject));
                        nativeV2Asset4.m8998a(str);
                        break;
                    case ASSET_TYPE_TIMER:
                        a3 = m9030a(j, k, i2);
                        c3015a = null;
                        if (jSONObject.has("startOffset")) {
                            c3015a = m9068r(jSONObject.getJSONObject("startOffset"));
                        }
                        c3015a2 = null;
                        if (jSONObject.has("timerDuration")) {
                            c3015a2 = m9068r(jSONObject.getJSONObject("timerDuration"));
                        }
                        optBoolean = jSONObject.optBoolean("displayTimer", true);
                        nativeV2Asset4 = new at(e, f, a3, c3015a, c3015a2);
                        nativeV2Asset4.m9401b(optBoolean);
                        if (jSONObject.has("assetOnFinish")) {
                            jSONObject2 = (JSONObject) jSONObject.get("assetOnFinish");
                            if (jSONObject2.has(C1042c.jL)) {
                                nativeV2Asset4.m8989a(m9046g(jSONObject2.getString(C1042c.jL)));
                            }
                        }
                        nativeV2Asset4.m8998a(str);
                        break;
                    case ASSET_TYPE_IMAGE:
                        a3 = m9030a(j, k, i2);
                        assetInteractionMode2 = AssetInteractionMode.ASSET_INTERACTION_MODE_NO_ACTION;
                        if (m9067q(jSONObject)) {
                            assetInteractionMode2 = AssetInteractionMode.ASSET_INTERACTION_MODE_BROWSER;
                            if (jSONObject.getJSONObject("assetOnclick").isNull("openMode")) {
                                assetInteractionMode2 = m9044f(jSONObject.getJSONObject("assetOnclick").getString("openMode"));
                                o2 = m9063o(jSONObject.getJSONObject("assetOnclick"));
                                if (a4 != null || a4.size() == 0) {
                                    nativeV2Asset4 = new ao(e, f, a3, m9041d(jSONObject), assetInteractionMode2, jSONObject);
                                } else {
                                    nativeV2Asset4 = new ao(e, f, a3, m9041d(jSONObject), a4, assetInteractionMode2, jSONObject);
                                }
                                nativeV2Asset4.m8998a(str);
                                m9034a(nativeV2Asset4, jSONObject);
                                if (o2 != null) {
                                    nativeV2Asset4.m9010e(o2);
                                    break;
                                }
                            }
                        }
                        o2 = null;
                        if (a4 != null) {
                            break;
                        }
                        nativeV2Asset4 = new ao(e, f, a3, m9041d(jSONObject), assetInteractionMode2, jSONObject);
                        nativeV2Asset4.m8998a(str);
                        m9034a(nativeV2Asset4, jSONObject);
                        if (o2 != null) {
                            nativeV2Asset4.m9010e(o2);
                        }
                        break;
                    case ASSET_TYPE_VIDEO:
                        if (VERSION.SDK_INT >= 15) {
                            if (this.f6933k.get(AssetType.ASSET_TYPE_VIDEO) != null) {
                                Logger.m10359a(InternalLogLevel.INTERNAL, f6923a, "One video asset already present! I will add this to the data model (for now) but ideally consider skipping this asset");
                            }
                            c3023a = new C3023a(j.x, j.y, k.x, k.y);
                            if (this.f6936n == null) {
                                a2 = m9033a(jSONObject, str5, nativeV2Asset);
                            } else {
                                obj = this.f6936n;
                            }
                            z = true;
                            z2 = true;
                            z3 = false;
                            z4 = true;
                            if (PlacementType.PLACEMENT_TYPE_INLINE == this.f6938p) {
                                z3 = jSONObject.optBoolean("loop", false);
                                z4 = jSONObject.optBoolean("showProgress", true);
                                z = jSONObject.optBoolean("soundOn", true);
                                z2 = jSONObject.optBoolean("showMute", true);
                            } else if (nativeV2Asset != null) {
                                z3 = jSONObject.optBoolean("loop", true);
                                z4 = jSONObject.optBoolean("showProgress", false);
                                z = jSONObject.optBoolean("soundOn", false);
                                z2 = jSONObject.optBoolean("showMute", false);
                            } else if (((Boolean) nativeV2Asset.m9027v().get("didRequestFullScreen")).booleanValue()) {
                                z3 = jSONObject.optBoolean("loop", false);
                                z4 = jSONObject.optBoolean("showProgress", true);
                                z = jSONObject.optBoolean("soundOn", true);
                                z2 = jSONObject.optBoolean("showMute", true);
                            }
                            hashMap = new HashMap();
                            if (jSONObject.isNull("videoViewabilityConfig")) {
                                i2 = jSONObject.getJSONObject("videoViewabilityConfig");
                                keys = i2.keys();
                                while (keys.hasNext()) {
                                    str4 = (String) keys.next();
                                    hashMap.put(str4, i2.get(str4));
                                }
                            }
                            awVar = new aw(e, f, c3023a, a2, z, z2, z3, z4, a4, jSONObject, null);
                            ((aw) awVar).m9443a(hashMap);
                            awVar.m8998a(str);
                            awVar.m8994a(nativeV2Asset);
                            if (nativeV2Asset != null) {
                                nativeV2Asset.m8994a(awVar);
                            }
                            nativeV2Asset4 = awVar;
                            break;
                        }
                        nativeV2Asset4 = null;
                        break;
                    case ASSET_TYPE_RATING:
                        nativeV2Asset4 = null;
                        break;
                    case ASSET_TYPE_CTA:
                        if (!m9067q(jSONObject)) {
                            a3 = m9038c(j, k, i2);
                            assetInteractionMode2 = AssetInteractionMode.ASSET_INTERACTION_MODE_BROWSER;
                            if (jSONObject.getJSONObject("assetOnclick").isNull("openMode")) {
                                assetInteractionMode2 = m9044f(jSONObject.getJSONObject("assetOnclick").getString("openMode"));
                                o2 = m9063o(jSONObject.getJSONObject("assetOnclick"));
                            } else {
                                o2 = null;
                            }
                            if (a4 != null || a4.size() == 0) {
                                nativeV2Asset4 = new al(e, f, a3, str5, assetInteractionMode2, jSONObject);
                            } else {
                                nativeV2Asset4 = new al(e, f, a3, str5, a4, assetInteractionMode2, jSONObject);
                            }
                            nativeV2Asset4.m8998a(str);
                            m9034a(nativeV2Asset4, jSONObject);
                            if (o2 != null) {
                                nativeV2Asset4.m9010e(o2);
                                break;
                            }
                        }
                        return null;
                        break;
                    default:
                        nativeV2Asset4 = null;
                        break;
                }
                nativeV2Asset3 = nativeV2Asset4;
            } catch (Throwable th22) {
                C3135c.m10255a().m10279a(new C3132b(th22));
                nativeV2Asset3 = null;
            }
            if (nativeV2Asset3 != null) {
                nativeV2Asset3.m8990a(l);
                nativeV2Asset3.m8987a(m);
                nativeV2Asset3.m9004b(b);
                if (!(b == null || b.length() == 0)) {
                    this.f6935m.put(e, b);
                }
                if (!(e.length() == 0 || this.f6934l.containsKey(e))) {
                    this.f6934l.put(e, nativeV2Asset3);
                }
                if (this.f6933k.containsKey(assetType)) {
                    ((List) this.f6933k.get(assetType)).add(nativeV2Asset3);
                } else {
                    arrayList = new ArrayList();
                    arrayList.add(nativeV2Asset3);
                    this.f6933k.put(assetType, arrayList);
                }
            }
            return nativeV2Asset3;
        }
        Logger.m10359a(InternalLogLevel.INTERNAL, f6923a, "Invalid asset style for asset: " + f);
        Logger.m10359a(InternalLogLevel.INTERNAL, f6923a, "Asset style JSON: " + i2);
        return null;
    }

    private void m9034a(@NonNull NativeV2Asset nativeV2Asset, @NonNull JSONObject jSONObject) throws JSONException {
        String string;
        String str = "";
        String str2 = "";
        boolean z = false;
        if (m9067q(jSONObject)) {
            if (jSONObject.getJSONObject("assetOnclick").isNull("itemUrl")) {
                Logger.m10359a(InternalLogLevel.INTERNAL, f6923a, "Missing itemUrl on asset " + jSONObject.toString());
            } else {
                str = jSONObject.getJSONObject("assetOnclick").getString("itemUrl");
                z = true;
            }
            if (!jSONObject.getJSONObject("assetOnclick").isNull(C1042c.jL)) {
                string = jSONObject.getJSONObject("assetOnclick").getString(C1042c.jL);
                z = true;
                nativeV2Asset.m9008d(str);
                nativeV2Asset.m9006c(string);
                nativeV2Asset.m9000a(z);
            }
        }
        string = str2;
        nativeV2Asset.m9008d(str);
        nativeV2Asset.m9006c(string);
        nativeV2Asset.m9000a(z);
    }

    @Nullable
    NativeV2Asset m9078b(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        if (this.f6934l.get(str) != null) {
            return (NativeV2Asset) this.f6934l.get(str);
        }
        if (this.f6931i != null) {
            return (NativeV2Asset) this.f6931i.f6934l.get(str);
        }
        return null;
    }

    List<AssetType> m9095o() {
        return new ArrayList(this.f6933k.keySet());
    }

    List<NativeV2Asset> m9072a(AssetType assetType) {
        if (this.f6933k.containsKey(assetType)) {
            return (List) this.f6933k.get(assetType);
        }
        return Collections.emptyList();
    }

    @VisibleForTesting
    boolean m9077a(JSONObject jSONObject, AssetType assetType) {
        boolean z = true;
        if (jSONObject.isNull("geometry")) {
            return false;
        }
        try {
            if (!m9076a(jSONObject.getJSONArray("geometry"))) {
                return false;
            }
            switch (assetType) {
                case ASSET_TYPE_CONTAINER:
                case ASSET_TYPE_ICON:
                case ASSET_TYPE_TIMER:
                case ASSET_TYPE_IMAGE:
                case ASSET_TYPE_VIDEO:
                    return true;
                case ASSET_TYPE_TEXT:
                case ASSET_TYPE_CTA:
                    if (jSONObject.isNull("text")) {
                        return false;
                    }
                    try {
                        if (((int) Double.parseDouble(jSONObject.getJSONObject("text").getString("size"))) <= 0) {
                            z = false;
                        }
                        return z;
                    } catch (Throwable e) {
                        Logger.m10359a(InternalLogLevel.INTERNAL, f6923a, "Failure in validating text asset! Text size should be an integer");
                        C3135c.m10255a().m10279a(new C3132b(e));
                        return false;
                    }
                default:
                    return false;
            }
            C3135c.m10255a().m10279a(new C3132b(e));
            return false;
        } catch (Throwable e2) {
            C3135c.m10255a().m10279a(new C3132b(e2));
            return false;
        }
    }

    @VisibleForTesting
    boolean m9076a(JSONArray jSONArray) {
        boolean z = true;
        try {
            boolean z2 = jSONArray.getInt(0) >= 0 && jSONArray.getInt(1) >= 0;
            if (!z2) {
                return false;
            }
            int i = jSONArray.getInt(2);
            int i2 = jSONArray.getInt(3);
            if (i <= 0 || i2 <= 0) {
                z = false;
            }
            return z;
        } catch (Throwable e) {
            C3135c.m10255a().m10279a(new C3132b(e));
            return false;
        }
    }

    private ah m9032a(int i, C3000a c3000a, JSONObject jSONObject) throws JSONException {
        String trim = jSONObject.isNull("url") ? "" : jSONObject.getString("url").trim();
        Map hashMap = new HashMap();
        if (C3000a.TRACKER_EVENT_TYPE_VIDEO_RENDER == c3000a) {
            JSONArray optJSONArray = jSONObject.optJSONArray(Constants.VIDEO_TRACKING_EVENTS_KEY);
            if ((trim.length() == 0 || ((trim.startsWith("http") && !URLUtil.isValidUrl(trim)) || !trim.startsWith("http"))) && optJSONArray == null) {
                return null;
            }
            List arrayList = new ArrayList();
            if (optJSONArray != null) {
                for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                    C3000a a = ah.m9288a(optJSONArray.getString(i2));
                    if (a == C3000a.TRACKER_EVENT_TYPE_CREATIVE_VIEW || a == C3000a.TRACKER_EVENT_TYPE_PLAY || a == C3000a.TRACKER_EVENT_TYPE_RENDER) {
                        arrayList.add(a);
                    }
                }
            }
            hashMap.put("referencedEvents", arrayList);
        } else if (trim.length() == 0 || !URLUtil.isValidUrl(trim)) {
            return null;
        }
        Map hashMap2 = new HashMap();
        try {
            if (!jSONObject.isNull("params")) {
                JSONObject jSONObject2 = jSONObject.getJSONObject("params");
                Iterator keys = jSONObject2.keys();
                while (keys.hasNext()) {
                    String str = (String) keys.next();
                    hashMap2.put(str, jSONObject2.getString(str));
                }
            }
        } catch (Throwable e) {
            Logger.m10360a(InternalLogLevel.INTERNAL, f6923a, "Failed to parser tracker.params", e);
            C3135c.m10255a().m10279a(new C3132b(e));
        }
        ah ahVar = new ah(trim, i, c3000a, hashMap2);
        ahVar.m9294a(hashMap);
        return ahVar;
    }

    private List<ah> m9039c(JSONObject jSONObject) {
        List<ah> linkedList = new LinkedList();
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject("passThroughJson");
            Map hashMap = new HashMap();
            if (!jSONObject2.isNull("macros")) {
                JSONObject jSONObject3 = jSONObject2.getJSONObject("macros");
                Iterator keys = jSONObject3.keys();
                while (keys.hasNext()) {
                    String str = (String) keys.next();
                    hashMap.put(str, jSONObject3.getString(str));
                }
            }
            if (!jSONObject2.isNull(Constants.VIDEO_TRACKING_URLS_KEY)) {
                JSONArray jSONArray = jSONObject2.getJSONArray(Constants.VIDEO_TRACKING_URLS_KEY);
                int length = jSONArray.length();
                for (int i = 0; i < length; i++) {
                    linkedList.add(new ah(jSONArray.getString(i), 0, C3000a.TRACKER_EVENT_TYPE_IAS, hashMap));
                }
            }
            if (linkedList.isEmpty()) {
                linkedList.add(new ah("", 0, C3000a.TRACKER_EVENT_TYPE_IAS, hashMap));
            }
        } catch (Throwable e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f6923a, "Failed to parse IAS tracker : " + e.getMessage());
            C3135c.m10255a().m10279a(new C3132b(e));
        }
        return linkedList;
    }

    @VisibleForTesting
    List<ah> m9073a(@NonNull JSONObject jSONObject) {
        if (jSONObject.isNull("trackers")) {
            return null;
        }
        List<ah> linkedList = new LinkedList();
        try {
            JSONArray jSONArray = jSONObject.getJSONArray("trackers");
            int length = jSONArray.length();
            if (length == 0) {
                return linkedList;
            }
            for (int i = 0; i < length; i++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                if (!jSONObject2.isNull("trackerType") && C3001b.TRACKER_TYPE_URL_PING == m9040d(jSONObject2.getString("trackerType"))) {
                    int optInt = jSONObject2.optInt("eventId", 0);
                    if (jSONObject2.isNull("uiEvent")) {
                        continue;
                    } else {
                        C3000a e = m9042e(jSONObject2.getString("uiEvent"));
                        if (C3000a.TRACKER_EVENT_TYPE_UNKNOWN == e) {
                            continue;
                        } else if (C3000a.TRACKER_EVENT_TYPE_IAS != e) {
                            linkedList.add(m9032a(optInt, e, jSONObject2));
                        } else {
                            linkedList.addAll(m9039c(jSONObject2));
                        }
                    }
                }
            }
            return linkedList;
        } catch (Throwable e2) {
            C3135c.m10255a().m10279a(new C3132b(e2));
            return linkedList;
        }
    }

    @Nullable
    private bp m9033a(@NonNull JSONObject jSONObject, @NonNull String str, NativeV2Asset nativeV2Asset) {
        if (m9047g(jSONObject).equalsIgnoreCase("VIDEO")) {
            try {
                JSONArray jSONArray = jSONObject.getJSONArray("assetValue");
                if (jSONArray == null || jSONArray.length() == 0) {
                    return null;
                }
                if (nativeV2Asset == null || !(nativeV2Asset instanceof aw)) {
                    return new bm(this.f6937o).m9553b(str);
                }
                return (bp) nativeV2Asset.m9007d();
            } catch (Throwable e) {
                C3135c.m10255a().m10279a(new C3132b(e));
            }
        }
        return null;
    }

    private String m9041d(@NonNull JSONObject jSONObject) {
        try {
            if ((m9047g(jSONObject).equalsIgnoreCase("ICON") || m9047g(jSONObject).equalsIgnoreCase("IMAGE")) && jSONObject.getJSONArray("assetValue").getString(0).length() != 0) {
                return jSONObject.getJSONArray("assetValue").getString(0);
            }
        } catch (Throwable e) {
            C3135c.m10255a().m10279a(new C3132b(e));
        }
        return "";
    }

    private String m9043e(@NonNull JSONObject jSONObject) {
        try {
            return jSONObject.getString("assetId");
        } catch (Throwable e) {
            C3135c.m10255a().m10279a(new C3132b(e));
            return Integer.toString(jSONObject.hashCode());
        }
    }

    private String m9045f(@NonNull JSONObject jSONObject) {
        try {
            return jSONObject.getString("assetName");
        } catch (Throwable e) {
            C3135c.m10255a().m10279a(new C3132b(e));
            return "";
        }
    }

    private String m9047g(@NonNull JSONObject jSONObject) {
        try {
            return jSONObject.getString("assetType");
        } catch (Throwable e) {
            C3135c.m10255a().m10279a(new C3132b(e));
            return "";
        }
    }

    private String m9049h(@NonNull JSONObject jSONObject) {
        try {
            return jSONObject.getString("valueType");
        } catch (Throwable e) {
            C3135c.m10255a().m10279a(new C3132b(e));
            return "";
        }
    }

    @NonNull
    private JSONObject m9051i(@NonNull JSONObject jSONObject) {
        try {
            JSONObject jSONObject2;
            if (jSONObject.isNull("assetStyle")) {
                jSONObject2 = null;
            } else {
                jSONObject2 = jSONObject.getJSONObject("assetStyle");
            }
            if (jSONObject2 != null) {
                return jSONObject2;
            }
            if (jSONObject.isNull("assetStyleRef")) {
                return new JSONObject();
            }
            String string = jSONObject.getString("assetStyleRef");
            if (m9088h() == null) {
                return new JSONObject();
            }
            return m9088h().getJSONObject(string);
        } catch (Throwable e) {
            C3135c.m10255a().m10279a(new C3132b(e));
            return new JSONObject();
        }
    }

    private Point m9052j(@NonNull JSONObject jSONObject) {
        Point point = new Point();
        try {
            JSONObject i = m9051i(jSONObject);
            if (!i.isNull("geometry")) {
                JSONArray jSONArray = i.getJSONArray("geometry");
                point.x = m9082c(jSONArray.getInt(0));
                point.y = m9082c(jSONArray.getInt(1));
            }
        } catch (Throwable e) {
            C3135c.m10255a().m10279a(new C3132b(e));
        }
        return point;
    }

    private Point m9054k(@NonNull JSONObject jSONObject) {
        Point point = new Point();
        try {
            JSONObject i = m9051i(jSONObject);
            if (!i.isNull("geometry")) {
                JSONArray jSONArray = i.getJSONArray("geometry");
                point.x = m9082c(jSONArray.getInt(2));
                point.y = m9082c(jSONArray.getInt(3));
            }
        } catch (Throwable e) {
            C3135c.m10255a().m10279a(new C3132b(e));
        }
        return point;
    }

    private AssetDisplayOnType m9057l(@NonNull JSONObject jSONObject) {
        AssetDisplayOnType assetDisplayOnType = AssetDisplayOnType.ASSET_DISPLAY_ON_TYPE_ALWAYS;
        try {
            JSONObject n = m9061n(jSONObject);
            if (!n.isNull("type")) {
                assetDisplayOnType = m9037c(n.getString("type"));
            }
        } catch (Throwable e) {
            C3135c.m10255a().m10279a(new C3132b(e));
        }
        return assetDisplayOnType;
    }

    private int m9058m(@NonNull JSONObject jSONObject) {
        try {
            JSONObject n = m9061n(jSONObject);
            if (n.isNull("delay")) {
                return -1;
            }
            int i = n.getInt("delay");
            if (AssetDisplayOnType.ASSET_DISPLAY_ON_TYPE_ABSOLUTE == m9057l(jSONObject)) {
                return i;
            }
            if (AssetDisplayOnType.ASSET_DISPLAY_ON_TYPE_PERCENTAGE != m9057l(jSONObject)) {
                return -1;
            }
            if (i <= 0 || i > 100) {
                return -1;
            }
            int[] iArr = new int[]{25, 50, 75, 100};
            double d = Double.MAX_VALUE;
            int i2 = 0;
            int i3 = -1;
            while (i2 < iArr.length) {
                int i4;
                int i5 = iArr[i2];
                double d2 = (double) ((i - i5) * (i - i5));
                if (d2 < d) {
                    i4 = i2;
                } else {
                    d2 = d;
                    i4 = i3;
                }
                i2++;
                i3 = i4;
                d = d2;
            }
            return iArr[i3];
        } catch (Throwable e) {
            C3135c.m10255a().m10279a(new C3132b(e));
            return -1;
        }
    }

    public String m9081b(@NonNull JSONObject jSONObject) {
        try {
            JSONObject n = m9061n(jSONObject);
            if (n.isNull("reference")) {
                return "";
            }
            return n.getString("reference");
        } catch (Throwable e) {
            C3135c.m10255a().m10279a(new C3132b(e));
            return "";
        }
    }

    private JSONObject m9061n(@NonNull JSONObject jSONObject) {
        if (jSONObject.isNull("display")) {
            return new JSONObject();
        }
        try {
            return jSONObject.getJSONObject("display");
        } catch (Throwable e) {
            C3135c.m10255a().m10279a(new C3132b(e));
            return new JSONObject();
        }
    }

    private AssetDisplayOnType m9037c(@NonNull String str) {
        String toLowerCase = str.trim().toLowerCase(Locale.US);
        Object obj = -1;
        switch (toLowerCase.hashCode()) {
            case -921832806:
                if (toLowerCase.equals("percentage")) {
                    obj = 3;
                    break;
                }
                break;
            case -284840886:
                if (toLowerCase.equals("unknown")) {
                    obj = 1;
                    break;
                }
                break;
            case 1728122231:
                if (toLowerCase.equals("absolute")) {
                    obj = 2;
                    break;
                }
                break;
        }
        switch (obj) {
            case 2:
                return AssetDisplayOnType.ASSET_DISPLAY_ON_TYPE_ABSOLUTE;
            case 3:
                return AssetDisplayOnType.ASSET_DISPLAY_ON_TYPE_PERCENTAGE;
            default:
                return AssetDisplayOnType.ASSET_DISPLAY_ON_TYPE_UNKNOWN;
        }
    }

    private String m9063o(@NonNull JSONObject jSONObject) throws JSONException {
        return jSONObject.optString("fallbackUrl");
    }

    @VisibleForTesting
    int m9082c(int i) {
        return DisplayInfo.m10419a(i);
    }

    private JSONArray m9065p(@NonNull JSONObject jSONObject) {
        try {
            return jSONObject.getJSONArray("assetValue");
        } catch (Throwable e) {
            C3135c.m10255a().m10279a(new C3132b(e));
            return new JSONArray();
        }
    }

    private boolean m9067q(@NonNull JSONObject jSONObject) {
        return !jSONObject.isNull("assetOnclick");
    }

    private C3001b m9040d(@NonNull String str) {
        String trim = str.toUpperCase(Locale.US).trim();
        Object obj = -1;
        switch (trim.hashCode()) {
            case -1430070305:
                if (trim.equals("HTML_SCRIPT")) {
                    obj = 3;
                    break;
                }
                break;
            case -158113182:
                if (trim.equals("URL_PING")) {
                    obj = 1;
                    break;
                }
                break;
            case 1110926088:
                if (trim.equals("URL_WEBVIEW_PING")) {
                    obj = 2;
                    break;
                }
                break;
        }
        switch (obj) {
            case 1:
                return C3001b.TRACKER_TYPE_URL_PING;
            case 2:
                return C3001b.TRACKER_TYPE_URL_WEBVIEW_PING;
            case 3:
                return C3001b.TRACKER_TYPE_HTML_SCRIPT;
            default:
                return C3001b.TRACKER_TYPE_UNKNOWN_OR_UNSUPPORTED;
        }
    }

    private C3000a m9042e(@NonNull String str) {
        String trim = str.toUpperCase(Locale.US).trim();
        Object obj = -1;
        switch (trim.hashCode()) {
            case -1881262698:
                if (trim.equals("RENDER")) {
                    obj = 3;
                    break;
                }
                break;
            case -45894975:
                if (trim.equals("IAS_VIEWABILITY")) {
                    obj = 7;
                    break;
                }
                break;
            case 2342118:
                if (trim.equals("LOAD")) {
                    obj = 1;
                    break;
                }
                break;
            case 2634405:
                if (trim.equals("VIEW")) {
                    obj = 4;
                    break;
                }
                break;
            case 64212328:
                if (trim.equals("CLICK")) {
                    obj = 5;
                    break;
                }
                break;
            case 1963885793:
                if (trim.equals("VIDEO_VIEWABILITY")) {
                    obj = 6;
                    break;
                }
                break;
            case 2008409463:
                if (trim.equals("CLIENT_FILL")) {
                    obj = 2;
                    break;
                }
                break;
        }
        switch (obj) {
            case 1:
                return C3000a.TRACKER_EVENT_TYPE_LOAD;
            case 2:
                return C3000a.TRACKER_EVENT_TYPE_CLIENT_FILL;
            case 3:
                return C3000a.TRACKER_EVENT_TYPE_RENDER;
            case 4:
                return C3000a.TRACKER_EVENT_TYPE_PAGE_VIEW;
            case 5:
                return C3000a.TRACKER_EVENT_TYPE_CLICK;
            case 6:
                return C3000a.TRACKER_EVENT_TYPE_VIDEO_RENDER;
            case 7:
                return C3000a.TRACKER_EVENT_TYPE_IAS;
            default:
                return C3000a.TRACKER_EVENT_TYPE_UNKNOWN;
        }
    }

    private AssetInteractionMode m9044f(@NonNull String str) {
        String trim = str.toUpperCase(Locale.US).trim();
        Object obj = -1;
        switch (trim.hashCode()) {
            case -1038134325:
                if (trim.equals("EXTERNAL")) {
                    obj = 1;
                    break;
                }
                break;
            case 69805756:
                if (trim.equals("INAPP")) {
                    obj = 2;
                    break;
                }
                break;
            case 1411860198:
                if (trim.equals("DEEPLINK")) {
                    obj = 3;
                    break;
                }
                break;
        }
        switch (obj) {
            case 2:
                return AssetInteractionMode.ASSET_INTERACTION_MODE_IN_APP;
            case 3:
                return AssetInteractionMode.ASSET_INTERACTION_MODE_DEEP_LINK;
            default:
                return AssetInteractionMode.ASSET_INTERACTION_MODE_BROWSER;
        }
    }

    private AssetActionOnFinish m9046g(@NonNull String str) {
        String trim = str.toUpperCase(Locale.US).trim();
        Object obj = -1;
        switch (trim.hashCode()) {
            case 2142494:
                if (trim.equals("EXIT")) {
                    obj = 2;
                    break;
                }
                break;
            case 2402104:
                if (trim.equals("NONE")) {
                    obj = 1;
                    break;
                }
                break;
        }
        switch (obj) {
            case 2:
                return AssetActionOnFinish.ASSET_ACTION_ON_FINISH_EXIT;
            default:
                return AssetActionOnFinish.ASSET_ACTION_ON_FINISH_NONE;
        }
    }

    private AssetType m9048h(@NonNull String str) {
        String trim = str.toLowerCase(Locale.US).trim();
        Object obj = -1;
        switch (trim.hashCode()) {
            case -938102371:
                if (trim.equals("rating")) {
                    obj = 7;
                    break;
                }
                break;
            case -410956671:
                if (trim.equals("container")) {
                    obj = 1;
                    break;
                }
                break;
            case 98832:
                if (trim.equals("cta")) {
                    obj = 6;
                    break;
                }
                break;
            case 3226745:
                if (trim.equals("icon")) {
                    obj = 2;
                    break;
                }
                break;
            case 3556653:
                if (trim.equals("text")) {
                    obj = 5;
                    break;
                }
                break;
            case 100313435:
                if (trim.equals(BannerType.IMAGE)) {
                    obj = 3;
                    break;
                }
                break;
            case 110364485:
                if (trim.equals("timer")) {
                    obj = 8;
                    break;
                }
                break;
            case 112202875:
                if (trim.equals("video")) {
                    obj = 4;
                    break;
                }
                break;
        }
        switch (obj) {
            case 2:
                return AssetType.ASSET_TYPE_ICON;
            case 3:
                return AssetType.ASSET_TYPE_IMAGE;
            case 4:
                return AssetType.ASSET_TYPE_VIDEO;
            case 5:
                return AssetType.ASSET_TYPE_TEXT;
            case 6:
                return AssetType.ASSET_TYPE_CTA;
            case 7:
                return AssetType.ASSET_TYPE_RATING;
            case 8:
                return AssetType.ASSET_TYPE_TIMER;
            default:
                return AssetType.ASSET_TYPE_CONTAINER;
        }
    }

    private Orientation m9050i(@NonNull String str) {
        String trim = str.toLowerCase(Locale.US).trim();
        Object obj = -1;
        switch (trim.hashCode()) {
            case -1626174665:
                if (trim.equals("unspecified")) {
                    obj = 1;
                    break;
                }
                break;
            case 729267099:
                if (trim.equals(Ad.ORIENTATION_PORTRAIT)) {
                    obj = 2;
                    break;
                }
                break;
            case 1430647483:
                if (trim.equals(Ad.ORIENTATION_LANDSCAPE)) {
                    obj = 3;
                    break;
                }
                break;
        }
        switch (obj) {
            case 2:
                return Orientation.ORIENTATION_PORTRAIT;
            case 3:
                return Orientation.ORIENTATION_LANDSCAPE;
            default:
                return Orientation.ORIENTATION_UNSPECIFIED;
        }
    }

    private C3014b m9053j(@NonNull String str) {
        String trim = str.toLowerCase(Locale.US).trim();
        Object obj = -1;
        switch (trim.hashCode()) {
            case -1178781136:
                if (trim.equals("italic")) {
                    obj = 3;
                    break;
                }
                break;
            case -1026963764:
                if (trim.equals("underline")) {
                    obj = 5;
                    break;
                }
                break;
            case -891985998:
                if (trim.equals("strike")) {
                    obj = 4;
                    break;
                }
                break;
            case 3029637:
                if (trim.equals("bold")) {
                    obj = 2;
                    break;
                }
                break;
            case 3387192:
                if (trim.equals("none")) {
                    obj = 1;
                    break;
                }
                break;
        }
        switch (obj) {
            case 2:
                return C3014b.TEXT_STYLE_BOLD;
            case 3:
                return C3014b.TEXT_STYLE_ITALICISED;
            case 4:
                return C3014b.TEXT_STYLE_STRIKE_THRU;
            case 5:
                return C3014b.TEXT_STYLE_UNDERLINE;
            default:
                return C3014b.TEXT_STYLE_NONE;
        }
    }

    private C3013a m9055k(@NonNull String str) {
        String trim = str.trim();
        Object obj = -1;
        switch (trim.hashCode()) {
            case -1364013605:
                if (trim.equals("centre")) {
                    obj = 3;
                    break;
                }
                break;
            case 3317767:
                if (trim.equals("left")) {
                    obj = 1;
                    break;
                }
                break;
            case 108511772:
                if (trim.equals("right")) {
                    obj = 2;
                    break;
                }
                break;
        }
        switch (obj) {
            case 2:
                return C3013a.TEXT_RIGHT_ALIGNED;
            case 3:
                return C3013a.TEXT_CENTER_ALIGNED;
            default:
                return C3013a.TEXT_LEFT_ALIGNED;
        }
    }

    private C2933b m9056l(@NonNull String str) {
        String trim = str.toLowerCase(Locale.US).trim();
        Object obj = -1;
        switch (trim.hashCode()) {
            case 3321844:
                if (trim.equals("line")) {
                    obj = 2;
                    break;
                }
                break;
            case 3387192:
                if (trim.equals("none")) {
                    obj = 1;
                    break;
                }
                break;
        }
        switch (obj) {
            case 2:
                return C2933b.BORDER_STROKE_STYLE_LINE;
            default:
                return C2933b.BORDER_STROKE_STYLE_NONE;
        }
    }

    private C2932a m9059m(@NonNull String str) {
        String trim = str.toLowerCase(Locale.US).trim();
        Object obj = -1;
        switch (trim.hashCode()) {
            case -1349116587:
                if (trim.equals("curved")) {
                    obj = 2;
                    break;
                }
                break;
            case 1787472634:
                if (trim.equals("straight")) {
                    obj = 1;
                    break;
                }
                break;
        }
        switch (obj) {
            case 2:
                return C2932a.BORDER_CORNER_STYLE_CURVED;
            default:
                return C2932a.BORDER_CORNER_STYLE_STRAIGHT;
        }
    }

    private ContentMode m9060n(@NonNull String str) {
        String trim = str.trim();
        Object obj = -1;
        switch (trim.hashCode()) {
            case -1626174665:
                if (trim.equals("unspecified")) {
                    obj = 1;
                    break;
                }
                break;
            case -1362001767:
                if (trim.equals("aspectFit")) {
                    obj = 4;
                    break;
                }
                break;
            case 3143043:
                if (trim.equals("fill")) {
                    obj = 2;
                    break;
                }
                break;
            case 727618043:
                if (trim.equals("aspectFill")) {
                    obj = 3;
                    break;
                }
                break;
        }
        switch (obj) {
            case 2:
                return ContentMode.CONTENT_MODE_FILL;
            case 3:
                return ContentMode.CONTENT_MODE_ASPECT_FILL;
            case 4:
                return ContentMode.CONTENT_MODE_ASPECT_FIT;
            default:
                return ContentMode.CONTENT_MODE_UNSPECIFIED;
        }
    }

    private C3015a m9068r(JSONObject jSONObject) {
        return new C3015a(jSONObject.optLong("absolute"), jSONObject.optLong("percentage"), m9078b(jSONObject.optString("reference")));
    }

    private C3004a m9062o(@NonNull String str) {
        String trim = str.trim();
        Object obj = -1;
        switch (trim.hashCode()) {
            case 3151468:
                if (trim.equals(FreeBox.TYPE)) {
                    obj = 2;
                    break;
                }
                break;
            case 106426293:
                if (trim.equals("paged")) {
                    obj = 1;
                    break;
                }
                break;
        }
        switch (obj) {
            case 2:
                return C3004a.CARD_SCROLLABLE_TYPE_FREE;
            default:
                return C3004a.CARD_SCROLLABLE_TYPE_PAGED;
        }
    }

    private AssetValueType m9064p(@NonNull String str) {
        String trim = str.trim();
        Object obj = -1;
        switch (trim.hashCode()) {
            case -925155509:
                if (trim.equals("reference")) {
                    obj = 2;
                    break;
                }
                break;
            case 1728122231:
                if (trim.equals("absolute")) {
                    obj = 1;
                    break;
                }
                break;
        }
        switch (obj) {
            case 2:
                return AssetValueType.ASSET_VALUE_REFERENCE;
            default:
                return AssetValueType.ASSET_VALUE_ABSOLUTE;
        }
    }

    private NativeStrandAssetStyle m9030a(@NonNull Point point, @NonNull Point point2, @NonNull JSONObject jSONObject) throws JSONException {
        C2933b c2933b;
        String str;
        C2932a c2932a;
        String str2;
        if (jSONObject.isNull("border")) {
            c2933b = C2933b.BORDER_STROKE_STYLE_NONE;
            str = "#ff000000";
            c2932a = C2932a.BORDER_CORNER_STYLE_STRAIGHT;
        } else {
            JSONObject jSONObject2 = jSONObject.getJSONObject("border");
            if (jSONObject2.isNull("style")) {
                c2933b = C2933b.BORDER_STROKE_STYLE_NONE;
                str = "#ff000000";
                c2932a = C2932a.BORDER_CORNER_STYLE_STRAIGHT;
            } else {
                C2932a c2932a2;
                c2933b = m9056l(jSONObject2.getString("style"));
                if (jSONObject2.isNull("corner")) {
                    c2932a2 = C2932a.BORDER_CORNER_STYLE_STRAIGHT;
                } else {
                    c2932a2 = m9059m(jSONObject2.getString("corner"));
                }
                if (jSONObject2.isNull("color")) {
                    str = "#ff000000";
                    c2932a = c2932a2;
                } else {
                    str = jSONObject2.getString("color").trim();
                    c2932a = c2932a2;
                }
            }
        }
        if (jSONObject.isNull("backgroundColor")) {
            str2 = "#00000000";
        } else {
            str2 = jSONObject.getString("backgroundColor").trim();
        }
        ContentMode contentMode = ContentMode.CONTENT_MODE_FILL;
        if (!jSONObject.isNull("contentMode")) {
            contentMode = m9060n(jSONObject.getString("contentMode"));
        }
        return new NativeStrandAssetStyle(point.x, point.y, point2.x, point2.y, contentMode, c2933b, c2932a, str, str2);
    }

    private C3006a m9036b(@NonNull Point point, @NonNull Point point2, @NonNull JSONObject jSONObject) throws JSONException {
        C2933b c2933b;
        String str;
        C2932a c2932a;
        JSONObject jSONObject2;
        String str2;
        if (jSONObject.isNull("border")) {
            c2933b = C2933b.BORDER_STROKE_STYLE_NONE;
            str = "#ff000000";
            c2932a = C2932a.BORDER_CORNER_STYLE_STRAIGHT;
        } else {
            jSONObject2 = jSONObject.getJSONObject("border");
            if (jSONObject2.isNull("style")) {
                c2933b = C2933b.BORDER_STROKE_STYLE_NONE;
                str = "#ff000000";
                c2932a = C2932a.BORDER_CORNER_STYLE_STRAIGHT;
            } else {
                C2932a c2932a2;
                c2933b = m9056l(jSONObject2.getString("style"));
                if (jSONObject2.isNull("corner")) {
                    c2932a2 = C2932a.BORDER_CORNER_STYLE_STRAIGHT;
                } else {
                    c2932a2 = m9059m(jSONObject2.getString("corner"));
                }
                if (jSONObject2.isNull("color")) {
                    str = "#ff000000";
                    c2932a = c2932a2;
                } else {
                    str = jSONObject2.getString("color").trim();
                    c2932a = c2932a2;
                }
            }
        }
        if (jSONObject.isNull("backgroundColor")) {
            str2 = "#00000000";
        } else {
            str2 = jSONObject.getString("backgroundColor").trim();
        }
        jSONObject2 = jSONObject.getJSONObject("text");
        try {
            int i;
            String str3;
            C3014b[] c3014bArr;
            int parseDouble = (int) Double.parseDouble(jSONObject2.getString("size"));
            if (jSONObject2.isNull("length")) {
                i = Integer.MAX_VALUE;
            } else {
                i = Integer.parseInt(jSONObject2.getString("length"));
            }
            if (jSONObject2.isNull("color")) {
                str3 = "#ff000000";
            } else {
                str3 = jSONObject2.getString("color").trim();
            }
            if (jSONObject2.isNull("style")) {
                c3014bArr = new C3014b[]{C3014b.TEXT_STYLE_NONE};
            } else {
                int length = jSONObject2.getJSONArray("style").length();
                if (length == 0) {
                    c3014bArr = new C3014b[]{C3014b.TEXT_STYLE_NONE};
                } else {
                    c3014bArr = new C3014b[length];
                    for (int i2 = 0; i2 < length; i2++) {
                        c3014bArr[i2] = m9053j(jSONObject2.getJSONArray("style").getString(i2));
                    }
                }
            }
            C3013a c3013a = C3013a.TEXT_LEFT_ALIGNED;
            if (!jSONObject2.isNull("align")) {
                c3013a = m9055k(jSONObject2.getString("align"));
            }
            return new C3006a(point.x, point.y, point2.x, point2.y, c2933b, c2932a, str, str2, parseDouble, c3013a, i, str3, c3014bArr);
        } catch (Throwable e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f6923a, "Failure in building text asset! Text size should be an integer");
            JSONException jSONException = new JSONException(e.getMessage());
            jSONException.initCause(e);
            C3135c.m10255a().m10279a(new C3132b(e));
            throw jSONException;
        }
    }

    private C3006a m9038c(@NonNull Point point, @NonNull Point point2, @NonNull JSONObject jSONObject) throws JSONException {
        C2933b c2933b;
        String str;
        C2932a c2932a;
        JSONObject jSONObject2;
        String str2;
        if (jSONObject.isNull("border")) {
            c2933b = C2933b.BORDER_STROKE_STYLE_NONE;
            str = "#ff000000";
            c2932a = C2932a.BORDER_CORNER_STYLE_STRAIGHT;
        } else {
            jSONObject2 = jSONObject.getJSONObject("border");
            if (jSONObject2.isNull("style")) {
                c2933b = C2933b.BORDER_STROKE_STYLE_NONE;
                str = "#ff000000";
                c2932a = C2932a.BORDER_CORNER_STYLE_STRAIGHT;
            } else {
                C2932a c2932a2;
                c2933b = m9056l(jSONObject2.getString("style"));
                if (jSONObject2.isNull("corner")) {
                    c2932a2 = C2932a.BORDER_CORNER_STYLE_STRAIGHT;
                } else {
                    c2932a2 = m9059m(jSONObject2.getString("corner"));
                }
                if (jSONObject2.isNull("color")) {
                    str = "#ff000000";
                    c2932a = c2932a2;
                } else {
                    str = jSONObject2.getString("color").trim();
                    c2932a = c2932a2;
                }
            }
        }
        if (jSONObject.isNull("backgroundColor")) {
            str2 = "#00000000";
        } else {
            str2 = jSONObject.getString("backgroundColor").trim();
        }
        jSONObject2 = jSONObject.getJSONObject("text");
        try {
            String str3;
            C3014b[] c3014bArr;
            int parseDouble = (int) Double.parseDouble(jSONObject2.getString("size"));
            if (jSONObject2.isNull("color")) {
                str3 = "#ff000000";
            } else {
                str3 = jSONObject2.getString("color").trim();
            }
            if (jSONObject2.isNull("style")) {
                c3014bArr = new C3014b[]{C3014b.TEXT_STYLE_NONE};
            } else {
                int length = jSONObject2.getJSONArray("style").length();
                if (length == 0) {
                    c3014bArr = new C3014b[]{C3014b.TEXT_STYLE_NONE};
                } else {
                    c3014bArr = new C3014b[length];
                    for (int i = 0; i < length; i++) {
                        c3014bArr[i] = m9053j(jSONObject2.getJSONArray("style").getString(i));
                    }
                }
            }
            return new C3007a(point.x, point.y, point2.x, point2.y, c2933b, c2932a, str, str2, parseDouble, str3, c3014bArr);
        } catch (Throwable e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f6923a, "Failure in building text asset! Text size should be an integer");
            JSONException jSONException = new JSONException(e.getMessage());
            jSONException.initCause(e);
            C3135c.m10255a().m10279a(new C3132b(e));
            throw jSONException;
        }
    }
}
