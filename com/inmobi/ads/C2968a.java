package com.inmobi.ads;

import android.content.ContentValues;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.inmobi.ads.C3046c.C3043g;
import com.inmobi.ads.InMobiAdRequest.MonetizationContext;
import com.inmobi.commons.core.p115d.C3132b;
import com.inmobi.commons.core.p115d.C3135c;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.mopub.mobileads.BaseVideoPlayerActivity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: Ad */
class C2968a {
    private static final String f6954a = C2968a.class.getSimpleName();
    private final String f6955b;
    private final String f6956c;
    private final String f6957d;
    private final long f6958e;
    private long f6959f;
    private final String f6960g;
    private String f6961h;
    private String f6962i;
    private MonetizationContext f6963j;

    /* compiled from: Ad */
    static final class C2965a {
        @Nullable
        static final C2968a m9111a(JSONObject jSONObject, long j, String str, String str2, String str3, String str4, MonetizationContext monetizationContext) {
            try {
                String string = jSONObject.getString("markupType");
                Object obj = -1;
                switch (string.hashCode()) {
                    case -1084172778:
                        if (string.equals("inmobiJson")) {
                            obj = 3;
                            break;
                        }
                        break;
                    case -236368507:
                        if (string.equals("pubJson")) {
                            obj = 2;
                            break;
                        }
                        break;
                    case 3213227:
                        if (string.equals("html")) {
                            obj = 1;
                            break;
                        }
                        break;
                }
                switch (obj) {
                    case 1:
                        return new C2968a(jSONObject, j, str, str2, str3, str4, monetizationContext);
                    case 2:
                        return new C2968a(jSONObject, j, str, str2, str3, str4, monetizationContext);
                    case 3:
                        JSONObject jSONObject2 = new JSONObject(jSONObject.getString("pubContent"));
                        Map hashMap;
                        if (jSONObject2.isNull("rootContainer")) {
                            Logger.m10359a(InternalLogLevel.INTERNAL, C2968a.f6954a, "Missing key (rootContainer) in the ad markup");
                            try {
                                hashMap = new HashMap();
                                hashMap.put("errorCode", "MISSING rootContainer");
                                hashMap.put("reason", "Missing rootContainer ad markup");
                                C3135c.m10255a().m10280a("ads", "ServerError", hashMap);
                            } catch (Exception e) {
                                Logger.m10359a(InternalLogLevel.INTERNAL, C2968a.f6954a, "Error in submitting telemetry event : (" + e.getMessage() + ")");
                            }
                            return null;
                        }
                        JSONObject jSONObject3 = jSONObject2.getJSONObject("rootContainer");
                        List<String> b = C2965a.m9113b(jSONObject3);
                        JSONArray jSONArray = new JSONArray();
                        for (String put : b) {
                            jSONArray.put(put);
                        }
                        String put2 = C2965a.m9112a(jSONObject3);
                        if (put2.trim().length() == 0) {
                            Logger.m10359a(InternalLogLevel.INTERNAL, C2968a.f6954a, "Missing VAST video XML in the ad markup");
                            hashMap = new HashMap();
                            hashMap.put("errorCode", "MISSING VAST");
                            hashMap.put("reason", "Missing VAST video XML in the ad markup");
                            hashMap.put("latency", SchemaSymbols.ATTVAL_FALSE_0);
                            C3135c.m10255a().m10280a("ads", "ServerError", hashMap);
                            return new C2968a(jSONObject, jSONArray.toString(), j, str, str2, str3, str4, monetizationContext);
                        }
                        bo b2 = new bm(new C3043g()).m9553b(put2);
                        if (b2 == null || b2.m9575c() != VastErrorCode.NO_ERROR) {
                            Logger.m10359a(InternalLogLevel.INTERNAL, C2968a.f6954a, "Processing VAST XML to build a video descriptor failed");
                            hashMap = new HashMap();
                            hashMap.put("errorCode", b2.m9575c().getId() + "");
                            hashMap.put("reason", "Processing VAST XML to build a video descriptor failed");
                            hashMap.put("latency", SchemaSymbols.ATTVAL_FALSE_0);
                            C3135c.m10255a().m10280a("ads", "VastProcessingError", hashMap);
                            return null;
                        }
                        hashMap = new HashMap();
                        hashMap.put("message", "VAST PROCESSING SUCCESS");
                        C3135c.m10255a().m10280a("ads", "VastProcessingSuccess", hashMap);
                        List<ah> e2 = b2.mo6221e();
                        JSONArray jSONArray2 = new JSONArray();
                        for (ah ahVar : e2) {
                            jSONArray2.put(ahVar.toString());
                        }
                        List<bl> f = b2.mo6222f();
                        JSONArray jSONArray3 = new JSONArray();
                        for (bl blVar : f) {
                            jSONArray3.put(blVar.toString());
                        }
                        put2 = b2.mo6219b();
                        if (put2 == null || put2.isEmpty()) {
                            hashMap = new HashMap();
                            hashMap.put("errorCode", "ZERO LENGTH ASSET");
                            hashMap.put("reason", "Asset length is 0");
                            C3135c.m10255a().m10280a("ads", "ServerError", hashMap);
                            Logger.m10359a(InternalLogLevel.INTERNAL, C2968a.f6954a, "No Media URL to download.Returning..");
                            return null;
                        }
                        jSONArray.put(put2);
                        return new bq(jSONObject, jSONArray.toString(), j, str, str2, str3, str4, b2.mo6219b(), b2.m9581h(), b2.mo6216a(), jSONArray2.toString(), jSONArray3.toString(), monetizationContext);
                    default:
                        return null;
                }
            } catch (Throwable e3) {
                Logger.m10359a(InternalLogLevel.INTERNAL, C2968a.f6954a, "Error parsing ad markup; " + e3.getMessage());
                C3135c.m10255a().m10279a(new C3132b(e3));
                return null;
            }
            Logger.m10359a(InternalLogLevel.INTERNAL, C2968a.f6954a, "Error parsing ad markup; " + e3.getMessage());
            C3135c.m10255a().m10279a(new C3132b(e3));
            return null;
        }

        static final C2968a m9110a(ContentValues contentValues) {
            if (!contentValues.containsKey(BaseVideoPlayerActivity.VIDEO_URL) || contentValues.getAsString(BaseVideoPlayerActivity.VIDEO_URL) == null || contentValues.getAsString(BaseVideoPlayerActivity.VIDEO_URL).isEmpty()) {
                return new C2968a(contentValues);
            }
            return new bq(contentValues);
        }

        @NonNull
        static String m9112a(@NonNull JSONObject jSONObject) {
            try {
                JSONArray jSONArray = jSONObject.getJSONArray("assetValue");
                if (jSONArray.length() == 0) {
                    return "";
                }
                String string = jSONObject.getString("assetType");
                if (string.equalsIgnoreCase("video")) {
                    return jSONArray.getString(0);
                }
                if (!string.equalsIgnoreCase("container")) {
                    return "";
                }
                String str = "";
                for (int i = 0; i < jSONArray.length(); i++) {
                    str = C2965a.m9112a(jSONArray.getJSONObject(i));
                    if (str.trim().length() != 0) {
                        return str;
                    }
                }
                return str;
            } catch (Throwable e) {
                Logger.m10359a(InternalLogLevel.INTERNAL, C2968a.f6954a, "Error getting VAST video XML (" + e.getMessage() + ")");
                C3135c.m10255a().m10279a(new C3132b(e));
                return "";
            }
        }

        @NonNull
        static List<String> m9113b(@NonNull JSONObject jSONObject) {
            return new ArrayList();
        }
    }

    C2968a(JSONObject jSONObject, long j, String str, String str2, String str3, String str4, MonetizationContext monetizationContext) {
        this(jSONObject, null, j, str, str2, str3, str4, monetizationContext);
    }

    C2968a(JSONObject jSONObject, String str, long j, String str2, String str3, String str4, String str5, MonetizationContext monetizationContext) {
        this.f6957d = jSONObject.toString();
        this.f6961h = str;
        this.f6958e = j;
        this.f6955b = str2;
        this.f6956c = str3;
        this.f6959f = System.nanoTime();
        this.f6960g = str4;
        this.f6962i = str5;
        this.f6963j = monetizationContext;
    }

    C2968a(ContentValues contentValues) {
        this.f6955b = contentValues.getAsString("ad_type");
        this.f6956c = contentValues.getAsString("ad_size");
        this.f6961h = contentValues.getAsString("asset_urls");
        this.f6957d = contentValues.getAsString("ad_content");
        this.f6958e = contentValues.getAsLong("placement_id").longValue();
        this.f6959f = contentValues.getAsLong("insertion_ts").longValue();
        this.f6960g = contentValues.getAsString("imp_id");
        this.f6962i = contentValues.getAsString("client_request_id");
        this.f6963j = MonetizationContext.m8786a(contentValues.getAsString("m10_context"));
        if (this.f6963j == null) {
            this.f6963j = MonetizationContext.MONETIZATION_CONTEXT_ACTIVITY;
            Logger.m10359a(InternalLogLevel.INTERNAL, f6954a, "Null m10 context");
        }
    }

    public ContentValues mo6224a() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("ad_type", this.f6955b);
        contentValues.put("ad_size", this.f6956c);
        contentValues.put("asset_urls", this.f6961h);
        contentValues.put("ad_content", this.f6957d);
        contentValues.put("placement_id", Long.valueOf(this.f6958e));
        contentValues.put("insertion_ts", Long.valueOf(this.f6959f));
        contentValues.put("imp_id", this.f6960g);
        contentValues.put("client_request_id", this.f6962i);
        contentValues.put("m10_context", this.f6963j.m8787a());
        return contentValues;
    }

    public String m9122b() {
        return this.f6962i;
    }

    public final String m9123c() {
        return this.f6957d;
    }

    public final long m9124d() {
        return this.f6958e;
    }

    public final String m9125e() {
        return this.f6960g;
    }

    public final long m9126f() {
        return this.f6959f;
    }

    public void m9121a(long j) {
        this.f6959f = j;
    }

    public List<String> m9127g() {
        List<String> arrayList = new ArrayList();
        if (!(this.f6961h == null || this.f6961h.length() == 0)) {
            try {
                JSONArray jSONArray = new JSONArray(this.f6961h);
                if (jSONArray.length() != 0) {
                    for (int i = 0; i < jSONArray.length(); i++) {
                        arrayList.add(jSONArray.getString(i));
                    }
                }
            } catch (Throwable e) {
                Logger.m10359a(InternalLogLevel.INTERNAL, f6954a, "Error getting trackers");
                C3135c.m10255a().m10279a(new C3132b(e));
            }
        }
        return arrayList;
    }

    @NonNull
    public final String m9128h() {
        try {
            JSONObject jSONObject = new JSONObject(m9123c());
            if (jSONObject.isNull("markupType")) {
                return "";
            }
            return jSONObject.getString("markupType");
        } catch (Throwable e) {
            C3135c.m10255a().m10279a(new C3132b(e));
            return "";
        }
    }
}
