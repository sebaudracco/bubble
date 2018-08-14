package com.google.android.gms.internal.ads;

import android.content.Context;
import android.location.Location;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Debug.MemoryInfo;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.appnext.base.p023b.C1042c;
import com.appnext.base.p023b.C1048i;
import com.appnext.core.Ad;
import com.google.android.gms.ads.internal.zzbv;
import com.silvermob.sdk.Const.ClickType;
import com.stepleaderdigital.reveal.Reveal.AdUtils;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzadh
public final class zzafs {
    private static final SimpleDateFormat zzcho = new SimpleDateFormat("yyyyMMdd", Locale.US);

    public static zzaej zza(Context context, zzaef com_google_android_gms_internal_ads_zzaef, String str) {
        String optString;
        try {
            String str2;
            JSONObject jSONObject = new JSONObject(str);
            String optString2 = jSONObject.optString("ad_base_url", null);
            Object optString3 = jSONObject.optString("ad_url", null);
            String optString4 = jSONObject.optString("ad_size", null);
            String optString5 = jSONObject.optString("ad_slot_size", optString4);
            boolean z = (com_google_android_gms_internal_ads_zzaef == null || com_google_android_gms_internal_ads_zzaef.zzcdb == 0) ? false : true;
            CharSequence optString6 = jSONObject.optString("ad_json", null);
            if (optString6 == null) {
                optString6 = jSONObject.optString("ad_html", null);
            }
            if (optString6 == null) {
                optString6 = jSONObject.optString("body", null);
            }
            if (optString6 == null) {
                if (jSONObject.has("ads")) {
                    optString6 = jSONObject.toString();
                }
            }
            long j = -1;
            String optString7 = jSONObject.optString("debug_dialog", null);
            String optString8 = jSONObject.optString("debug_signals", null);
            long j2 = jSONObject.has("interstitial_timeout") ? (long) (jSONObject.getDouble("interstitial_timeout") * 1000.0d) : -1;
            optString = jSONObject.optString("orientation", null);
            int i = -1;
            if (Ad.ORIENTATION_PORTRAIT.equals(optString)) {
                i = zzbv.zzem().zzrm();
            } else if (Ad.ORIENTATION_LANDSCAPE.equals(optString)) {
                i = zzbv.zzem().zzrl();
            }
            zzaej com_google_android_gms_internal_ads_zzaej = null;
            if (!TextUtils.isEmpty(optString6) || TextUtils.isEmpty(optString3)) {
                CharSequence charSequence = optString6;
            } else {
                com_google_android_gms_internal_ads_zzaej = zzafn.zza(com_google_android_gms_internal_ads_zzaef, context, com_google_android_gms_internal_ads_zzaef.zzacr.zzcw, optString3, null, null, null, null, null);
                optString2 = com_google_android_gms_internal_ads_zzaej.zzbyq;
                str2 = com_google_android_gms_internal_ads_zzaej.zzceo;
                j = com_google_android_gms_internal_ads_zzaej.zzceu;
            }
            if (str2 == null) {
                return new zzaej(0);
            }
            long j3;
            String optString9;
            String str3;
            boolean optBoolean;
            JSONArray optJSONArray = jSONObject.optJSONArray("click_urls");
            List list = com_google_android_gms_internal_ads_zzaej == null ? null : com_google_android_gms_internal_ads_zzaej.zzbsn;
            if (optJSONArray != null) {
                list = zza(optJSONArray, list);
            }
            optJSONArray = jSONObject.optJSONArray("impression_urls");
            List list2 = com_google_android_gms_internal_ads_zzaej == null ? null : com_google_android_gms_internal_ads_zzaej.zzbso;
            if (optJSONArray != null) {
                list2 = zza(optJSONArray, list2);
            }
            optJSONArray = jSONObject.optJSONArray("downloaded_impression_urls");
            List list3 = com_google_android_gms_internal_ads_zzaej == null ? null : com_google_android_gms_internal_ads_zzaej.zzbsp;
            if (optJSONArray != null) {
                list3 = zza(optJSONArray, list3);
            }
            optJSONArray = jSONObject.optJSONArray("manual_impression_urls");
            List list4 = com_google_android_gms_internal_ads_zzaej == null ? null : com_google_android_gms_internal_ads_zzaej.zzces;
            if (optJSONArray != null) {
                list4 = zza(optJSONArray, list4);
            }
            if (com_google_android_gms_internal_ads_zzaej != null) {
                if (com_google_android_gms_internal_ads_zzaej.orientation != -1) {
                    i = com_google_android_gms_internal_ads_zzaej.orientation;
                }
                if (com_google_android_gms_internal_ads_zzaej.zzcep > 0) {
                    j3 = com_google_android_gms_internal_ads_zzaej.zzcep;
                    optString9 = jSONObject.optString("active_view");
                    str3 = null;
                    optBoolean = jSONObject.optBoolean("ad_is_javascript", false);
                    if (optBoolean) {
                        str3 = jSONObject.optString("ad_passback_url", null);
                    }
                    return new zzaej(com_google_android_gms_internal_ads_zzaef, optString2, str2, list, list2, j3, jSONObject.optBoolean("mediation", false), jSONObject.optLong("mediation_config_cache_time_milliseconds", -1), list4, jSONObject.optLong("refresh_interval_milliseconds", -1), i, optString4, j, optString7, optBoolean, str3, optString9, jSONObject.optBoolean("custom_render_allowed", false), z, com_google_android_gms_internal_ads_zzaef.zzcdd, jSONObject.optBoolean("content_url_opted_out", true), jSONObject.optBoolean("prefetch", false), jSONObject.optString("gws_query_id", ""), "height".equals(jSONObject.optString("fluid", "")), jSONObject.optBoolean("native_express", false), zzaig.zza(jSONObject.optJSONArray("rewards")), zza(jSONObject.optJSONArray("video_start_urls"), null), zza(jSONObject.optJSONArray("video_complete_urls"), null), jSONObject.optBoolean("use_displayed_impression", false), zzael.zzl(jSONObject.optJSONObject("auto_protection_configuration")), com_google_android_gms_internal_ads_zzaef.zzcdr, jSONObject.optString("set_cookie", ""), zza(jSONObject.optJSONArray("remote_ping_urls"), null), jSONObject.optBoolean("render_in_browser", com_google_android_gms_internal_ads_zzaef.zzbss), optString5, zzaiq.zzo(jSONObject.optJSONObject("safe_browsing")), optString8, jSONObject.optBoolean("content_vertical_opted_out", true), com_google_android_gms_internal_ads_zzaef.zzced, jSONObject.optBoolean("custom_close_blocked"), 0, jSONObject.optBoolean("enable_omid", false), list3, jSONObject.optBoolean("disable_closable_area", false), jSONObject.optString("omid_settings", null));
                }
            }
            j3 = j2;
            optString9 = jSONObject.optString("active_view");
            str3 = null;
            optBoolean = jSONObject.optBoolean("ad_is_javascript", false);
            if (optBoolean) {
                str3 = jSONObject.optString("ad_passback_url", null);
            }
            return new zzaej(com_google_android_gms_internal_ads_zzaef, optString2, str2, list, list2, j3, jSONObject.optBoolean("mediation", false), jSONObject.optLong("mediation_config_cache_time_milliseconds", -1), list4, jSONObject.optLong("refresh_interval_milliseconds", -1), i, optString4, j, optString7, optBoolean, str3, optString9, jSONObject.optBoolean("custom_render_allowed", false), z, com_google_android_gms_internal_ads_zzaef.zzcdd, jSONObject.optBoolean("content_url_opted_out", true), jSONObject.optBoolean("prefetch", false), jSONObject.optString("gws_query_id", ""), "height".equals(jSONObject.optString("fluid", "")), jSONObject.optBoolean("native_express", false), zzaig.zza(jSONObject.optJSONArray("rewards")), zza(jSONObject.optJSONArray("video_start_urls"), null), zza(jSONObject.optJSONArray("video_complete_urls"), null), jSONObject.optBoolean("use_displayed_impression", false), zzael.zzl(jSONObject.optJSONObject("auto_protection_configuration")), com_google_android_gms_internal_ads_zzaef.zzcdr, jSONObject.optString("set_cookie", ""), zza(jSONObject.optJSONArray("remote_ping_urls"), null), jSONObject.optBoolean("render_in_browser", com_google_android_gms_internal_ads_zzaef.zzbss), optString5, zzaiq.zzo(jSONObject.optJSONObject("safe_browsing")), optString8, jSONObject.optBoolean("content_vertical_opted_out", true), com_google_android_gms_internal_ads_zzaef.zzced, jSONObject.optBoolean("custom_close_blocked"), 0, jSONObject.optBoolean("enable_omid", false), list3, jSONObject.optBoolean("disable_closable_area", false), jSONObject.optString("omid_settings", null));
        } catch (JSONException e) {
            String str4 = "Could not parse the inline ad response: ";
            optString = String.valueOf(e.getMessage());
            zzane.zzdk(optString.length() != 0 ? str4.concat(optString) : new String(str4));
            return new zzaej(0);
        }
    }

    @Nullable
    private static List<String> zza(@Nullable JSONArray jSONArray, @Nullable List<String> list) throws JSONException {
        if (jSONArray == null) {
            return null;
        }
        if (list == null) {
            list = new ArrayList();
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            list.add(jSONArray.getString(i));
        }
        return list;
    }

    @Nullable
    public static JSONObject zza(Context context, zzafl com_google_android_gms_internal_ads_zzafl) {
        Object obj;
        Object obj2;
        zzaef com_google_android_gms_internal_ads_zzaef = com_google_android_gms_internal_ads_zzafl.zzcgs;
        Location location = com_google_android_gms_internal_ads_zzafl.zzaqe;
        zzaga com_google_android_gms_internal_ads_zzaga = com_google_android_gms_internal_ads_zzafl.zzcgt;
        Bundle bundle = com_google_android_gms_internal_ads_zzafl.zzcdc;
        JSONObject jSONObject = com_google_android_gms_internal_ads_zzafl.zzcgu;
        HashMap hashMap = new HashMap();
        hashMap.put("extra_caps", zzkb.zzik().zzd(zznk.zzbbk));
        if (com_google_android_gms_internal_ads_zzafl.zzcdj.size() > 0) {
            hashMap.put("eid", TextUtils.join(",", com_google_android_gms_internal_ads_zzafl.zzcdj));
        }
        if (com_google_android_gms_internal_ads_zzaef.zzccu != null) {
            hashMap.put("ad_pos", com_google_android_gms_internal_ads_zzaef.zzccu);
        }
        zzjj com_google_android_gms_internal_ads_zzjj = com_google_android_gms_internal_ads_zzaef.zzccv;
        String zzqn = zzajw.zzqn();
        if (zzqn != null) {
            hashMap.put("abf", zzqn);
        }
        if (com_google_android_gms_internal_ads_zzjj.zzapw != -1) {
            hashMap.put("cust_age", zzcho.format(new Date(com_google_android_gms_internal_ads_zzjj.zzapw)));
        }
        if (com_google_android_gms_internal_ads_zzjj.extras != null) {
            hashMap.put("extras", com_google_android_gms_internal_ads_zzjj.extras);
        }
        if (com_google_android_gms_internal_ads_zzjj.zzapx != -1) {
            hashMap.put("cust_gender", Integer.valueOf(com_google_android_gms_internal_ads_zzjj.zzapx));
        }
        if (com_google_android_gms_internal_ads_zzjj.zzapy != null) {
            hashMap.put("kw", com_google_android_gms_internal_ads_zzjj.zzapy);
        }
        if (com_google_android_gms_internal_ads_zzjj.zzaqa != -1) {
            hashMap.put("tag_for_child_directed_treatment", Integer.valueOf(com_google_android_gms_internal_ads_zzjj.zzaqa));
        }
        if (com_google_android_gms_internal_ads_zzjj.zzapz) {
            if (((Boolean) zzkb.zzik().zzd(zznk.zzbfp)).booleanValue()) {
                hashMap.put("test_request", Boolean.valueOf(true));
            } else {
                hashMap.put("adtest", C1042c.jF);
            }
        }
        if (com_google_android_gms_internal_ads_zzjj.versionCode >= 2) {
            if (com_google_android_gms_internal_ads_zzjj.zzaqb) {
                hashMap.put("d_imp_hdr", Integer.valueOf(1));
            }
            if (!TextUtils.isEmpty(com_google_android_gms_internal_ads_zzjj.zzaqc)) {
                hashMap.put("ppid", com_google_android_gms_internal_ads_zzjj.zzaqc);
            }
        }
        if (com_google_android_gms_internal_ads_zzjj.versionCode >= 3 && com_google_android_gms_internal_ads_zzjj.zzaqf != null) {
            hashMap.put("url", com_google_android_gms_internal_ads_zzjj.zzaqf);
        }
        if (com_google_android_gms_internal_ads_zzjj.versionCode >= 5) {
            if (com_google_android_gms_internal_ads_zzjj.zzaqh != null) {
                hashMap.put("custom_targeting", com_google_android_gms_internal_ads_zzjj.zzaqh);
            }
            if (com_google_android_gms_internal_ads_zzjj.zzaqi != null) {
                hashMap.put("category_exclusions", com_google_android_gms_internal_ads_zzjj.zzaqi);
            }
            if (com_google_android_gms_internal_ads_zzjj.zzaqj != null) {
                hashMap.put("request_agent", com_google_android_gms_internal_ads_zzjj.zzaqj);
            }
        }
        if (com_google_android_gms_internal_ads_zzjj.versionCode >= 6 && com_google_android_gms_internal_ads_zzjj.zzaqk != null) {
            hashMap.put("request_pkg", com_google_android_gms_internal_ads_zzjj.zzaqk);
        }
        if (com_google_android_gms_internal_ads_zzjj.versionCode >= 7) {
            hashMap.put("is_designed_for_families", Boolean.valueOf(com_google_android_gms_internal_ads_zzjj.zzaql));
        }
        if (com_google_android_gms_internal_ads_zzaef.zzacv.zzard != null) {
            obj = null;
            obj2 = null;
            for (zzjn com_google_android_gms_internal_ads_zzjn : com_google_android_gms_internal_ads_zzaef.zzacv.zzard) {
                if (!com_google_android_gms_internal_ads_zzjn.zzarf && r3 == null) {
                    hashMap.put("format", com_google_android_gms_internal_ads_zzjn.zzarb);
                    obj2 = 1;
                }
                if (com_google_android_gms_internal_ads_zzjn.zzarf && r2 == null) {
                    hashMap.put("fluid", "height");
                    obj = 1;
                }
                if (obj2 != null && r2 != null) {
                    break;
                }
            }
        } else {
            hashMap.put("format", com_google_android_gms_internal_ads_zzaef.zzacv.zzarb);
            if (com_google_android_gms_internal_ads_zzaef.zzacv.zzarf) {
                hashMap.put("fluid", "height");
            }
        }
        if (com_google_android_gms_internal_ads_zzaef.zzacv.width == -1) {
            hashMap.put("smart_w", "full");
        }
        if (com_google_android_gms_internal_ads_zzaef.zzacv.height == -2) {
            hashMap.put("smart_h", "auto");
        }
        if (com_google_android_gms_internal_ads_zzaef.zzacv.zzard != null) {
            StringBuilder stringBuilder = new StringBuilder();
            obj = null;
            for (zzjn com_google_android_gms_internal_ads_zzjn2 : com_google_android_gms_internal_ads_zzaef.zzacv.zzard) {
                if (com_google_android_gms_internal_ads_zzjn2.zzarf) {
                    obj = 1;
                } else {
                    if (stringBuilder.length() != 0) {
                        stringBuilder.append("|");
                    }
                    stringBuilder.append(com_google_android_gms_internal_ads_zzjn2.width == -1 ? (int) (((float) com_google_android_gms_internal_ads_zzjn2.widthPixels) / com_google_android_gms_internal_ads_zzaga.zzagu) : com_google_android_gms_internal_ads_zzjn2.width);
                    stringBuilder.append("x");
                    stringBuilder.append(com_google_android_gms_internal_ads_zzjn2.height == -2 ? (int) (((float) com_google_android_gms_internal_ads_zzjn2.heightPixels) / com_google_android_gms_internal_ads_zzaga.zzagu) : com_google_android_gms_internal_ads_zzjn2.height);
                }
            }
            if (obj != null) {
                if (stringBuilder.length() != 0) {
                    stringBuilder.insert(0, "|");
                }
                stringBuilder.insert(0, "320x50");
            }
            hashMap.put("sz", stringBuilder);
        }
        if (com_google_android_gms_internal_ads_zzaef.zzcdb != 0) {
            hashMap.put("native_version", Integer.valueOf(com_google_android_gms_internal_ads_zzaef.zzcdb));
            hashMap.put("native_templates", com_google_android_gms_internal_ads_zzaef.zzads);
            String str = "native_image_orientation";
            zzpl com_google_android_gms_internal_ads_zzpl = com_google_android_gms_internal_ads_zzaef.zzadj;
            if (com_google_android_gms_internal_ads_zzpl != null) {
                switch (com_google_android_gms_internal_ads_zzpl.zzbjo) {
                    case 0:
                        obj = "any";
                        break;
                    case 1:
                        obj = Ad.ORIENTATION_PORTRAIT;
                        break;
                    case 2:
                        obj = Ad.ORIENTATION_LANDSCAPE;
                        break;
                    default:
                        obj = Ad.ORIENTATION_DEFAULT;
                        break;
                }
            }
            obj = "any";
            hashMap.put(str, obj);
            if (!com_google_android_gms_internal_ads_zzaef.zzcdk.isEmpty()) {
                hashMap.put("native_custom_templates", com_google_android_gms_internal_ads_zzaef.zzcdk);
            }
            if (com_google_android_gms_internal_ads_zzaef.versionCode >= 24) {
                hashMap.put("max_num_ads", Integer.valueOf(com_google_android_gms_internal_ads_zzaef.zzceg));
            }
            if (!TextUtils.isEmpty(com_google_android_gms_internal_ads_zzaef.zzcee)) {
                try {
                    hashMap.put("native_advanced_settings", new JSONArray(com_google_android_gms_internal_ads_zzaef.zzcee));
                } catch (Throwable e) {
                    zzane.zzc("Problem creating json from native advanced settings", e);
                }
            }
        }
        try {
            Bundle bundle2;
            if (com_google_android_gms_internal_ads_zzaef.zzadn != null && com_google_android_gms_internal_ads_zzaef.zzadn.size() > 0) {
                for (Integer num : com_google_android_gms_internal_ads_zzaef.zzadn) {
                    if (num.intValue() == 2) {
                        hashMap.put("iba", Boolean.valueOf(true));
                    } else if (num.intValue() == 1) {
                        hashMap.put("ina", Boolean.valueOf(true));
                    }
                }
            }
            if (com_google_android_gms_internal_ads_zzaef.zzacv.zzarg) {
                hashMap.put("ene", Boolean.valueOf(true));
            }
            if (((Boolean) zzkb.zzik().zzd(zznk.zzaxv)).booleanValue()) {
                hashMap.put("xsrve", Boolean.valueOf(true));
            }
            if (com_google_android_gms_internal_ads_zzaef.zzadl != null) {
                hashMap.put("is_icon_ad", Boolean.valueOf(true));
                hashMap.put("icon_ad_expansion_behavior", Integer.valueOf(com_google_android_gms_internal_ads_zzaef.zzadl.zzasj));
            }
            hashMap.put("slotname", com_google_android_gms_internal_ads_zzaef.zzacp);
            hashMap.put("pn", com_google_android_gms_internal_ads_zzaef.applicationInfo.packageName);
            if (com_google_android_gms_internal_ads_zzaef.zzccw != null) {
                hashMap.put("vc", Integer.valueOf(com_google_android_gms_internal_ads_zzaef.zzccw.versionCode));
            }
            hashMap.put("ms", com_google_android_gms_internal_ads_zzafl.zzccx);
            hashMap.put("seq_num", com_google_android_gms_internal_ads_zzaef.zzccy);
            hashMap.put("session_id", com_google_android_gms_internal_ads_zzaef.zzccz);
            hashMap.put("js", com_google_android_gms_internal_ads_zzaef.zzacr.zzcw);
            zzagk com_google_android_gms_internal_ads_zzagk = com_google_android_gms_internal_ads_zzafl.zzcgo;
            Bundle bundle3 = com_google_android_gms_internal_ads_zzaef.zzcdw;
            Bundle bundle4 = com_google_android_gms_internal_ads_zzafl.zzcgn;
            hashMap.put("am", Integer.valueOf(com_google_android_gms_internal_ads_zzaga.zzcjk));
            hashMap.put("cog", zzv(com_google_android_gms_internal_ads_zzaga.zzcjl));
            hashMap.put("coh", zzv(com_google_android_gms_internal_ads_zzaga.zzcjm));
            if (!TextUtils.isEmpty(com_google_android_gms_internal_ads_zzaga.zzcjn)) {
                hashMap.put("carrier", com_google_android_gms_internal_ads_zzaga.zzcjn);
            }
            hashMap.put("gl", com_google_android_gms_internal_ads_zzaga.zzcjo);
            if (com_google_android_gms_internal_ads_zzaga.zzcjp) {
                hashMap.put("simulator", Integer.valueOf(1));
            }
            if (com_google_android_gms_internal_ads_zzaga.zzcjq) {
                hashMap.put("is_sidewinder", Integer.valueOf(1));
            }
            hashMap.put("ma", zzv(com_google_android_gms_internal_ads_zzaga.zzcjr));
            hashMap.put("sp", zzv(com_google_android_gms_internal_ads_zzaga.zzcjs));
            hashMap.put("hl", com_google_android_gms_internal_ads_zzaga.zzcjt);
            if (!TextUtils.isEmpty(com_google_android_gms_internal_ads_zzaga.zzcju)) {
                hashMap.put("mv", com_google_android_gms_internal_ads_zzaga.zzcju);
            }
            hashMap.put("muv", Integer.valueOf(com_google_android_gms_internal_ads_zzaga.zzcjw));
            if (com_google_android_gms_internal_ads_zzaga.zzcjx != -2) {
                hashMap.put("cnt", Integer.valueOf(com_google_android_gms_internal_ads_zzaga.zzcjx));
            }
            hashMap.put("gnt", Integer.valueOf(com_google_android_gms_internal_ads_zzaga.zzcjy));
            hashMap.put("pt", Integer.valueOf(com_google_android_gms_internal_ads_zzaga.zzcjz));
            hashMap.put("rm", Integer.valueOf(com_google_android_gms_internal_ads_zzaga.zzcka));
            hashMap.put("riv", Integer.valueOf(com_google_android_gms_internal_ads_zzaga.zzckb));
            Bundle bundle5 = new Bundle();
            bundle5.putString("build_build", com_google_android_gms_internal_ads_zzaga.zzckg);
            bundle5.putString("build_device", com_google_android_gms_internal_ads_zzaga.zzckh);
            Bundle bundle6 = new Bundle();
            bundle6.putBoolean("is_charging", com_google_android_gms_internal_ads_zzaga.zzckd);
            bundle6.putDouble("battery_level", com_google_android_gms_internal_ads_zzaga.zzckc);
            bundle5.putBundle("battery", bundle6);
            bundle6 = new Bundle();
            bundle6.putInt("active_network_state", com_google_android_gms_internal_ads_zzaga.zzckf);
            bundle6.putBoolean("active_network_metered", com_google_android_gms_internal_ads_zzaga.zzcke);
            if (com_google_android_gms_internal_ads_zzagk != null) {
                bundle2 = new Bundle();
                bundle2.putInt("predicted_latency_micros", com_google_android_gms_internal_ads_zzagk.zzckq);
                bundle2.putLong("predicted_down_throughput_bps", com_google_android_gms_internal_ads_zzagk.zzckr);
                bundle2.putLong("predicted_up_throughput_bps", com_google_android_gms_internal_ads_zzagk.zzcks);
                bundle6.putBundle("predictions", bundle2);
            }
            bundle5.putBundle("network", bundle6);
            Bundle bundle7 = new Bundle();
            bundle7.putBoolean("is_browser_custom_tabs_capable", com_google_android_gms_internal_ads_zzaga.zzcki);
            bundle5.putBundle(ClickType.BROWSER, bundle7);
            if (bundle3 != null) {
                String str2 = "android_mem_info";
                bundle2 = new Bundle();
                bundle2.putString("runtime_free", Long.toString(bundle3.getLong("runtime_free_memory", -1)));
                bundle2.putString("runtime_max", Long.toString(bundle3.getLong("runtime_max_memory", -1)));
                bundle2.putString("runtime_total", Long.toString(bundle3.getLong("runtime_total_memory", -1)));
                bundle2.putString("web_view_count", Integer.toString(bundle3.getInt("web_view_count", 0)));
                MemoryInfo memoryInfo = (MemoryInfo) bundle3.getParcelable("debug_memory_info");
                if (memoryInfo != null) {
                    bundle2.putString("debug_info_dalvik_private_dirty", Integer.toString(memoryInfo.dalvikPrivateDirty));
                    bundle2.putString("debug_info_dalvik_pss", Integer.toString(memoryInfo.dalvikPss));
                    bundle2.putString("debug_info_dalvik_shared_dirty", Integer.toString(memoryInfo.dalvikSharedDirty));
                    bundle2.putString("debug_info_native_private_dirty", Integer.toString(memoryInfo.nativePrivateDirty));
                    bundle2.putString("debug_info_native_pss", Integer.toString(memoryInfo.nativePss));
                    bundle2.putString("debug_info_native_shared_dirty", Integer.toString(memoryInfo.nativeSharedDirty));
                    bundle2.putString("debug_info_other_private_dirty", Integer.toString(memoryInfo.otherPrivateDirty));
                    bundle2.putString("debug_info_other_pss", Integer.toString(memoryInfo.otherPss));
                    bundle2.putString("debug_info_other_shared_dirty", Integer.toString(memoryInfo.otherSharedDirty));
                }
                bundle5.putBundle(str2, bundle2);
            }
            bundle7 = new Bundle();
            bundle7.putBundle("parental_controls", bundle4);
            if (!TextUtils.isEmpty(com_google_android_gms_internal_ads_zzaga.zzcjv)) {
                bundle7.putString("package_version", com_google_android_gms_internal_ads_zzaga.zzcjv);
            }
            bundle5.putBundle("play_store", bundle7);
            hashMap.put("device", bundle5);
            bundle4 = new Bundle();
            bundle4.putString("doritos", com_google_android_gms_internal_ads_zzafl.zzcgp);
            bundle4.putString("doritos_v2", com_google_android_gms_internal_ads_zzafl.zzcgq);
            if (((Boolean) zzkb.zzik().zzd(zznk.zzayj)).booleanValue()) {
                obj2 = null;
                boolean z = false;
                if (com_google_android_gms_internal_ads_zzafl.zzcgr != null) {
                    obj2 = com_google_android_gms_internal_ads_zzafl.zzcgr.getId();
                    z = com_google_android_gms_internal_ads_zzafl.zzcgr.isLimitAdTrackingEnabled();
                }
                if (TextUtils.isEmpty(obj2)) {
                    zzkb.zzif();
                    bundle4.putString("pdid", zzamu.zzbd(context));
                    bundle4.putString("pdidtype", "ssaid");
                } else {
                    bundle4.putString("rdid", obj2);
                    bundle4.putBoolean("is_lat", z);
                    bundle4.putString("idtype", AdUtils.PREFS_NAME);
                }
            }
            hashMap.put("pii", bundle4);
            hashMap.put("platform", Build.MANUFACTURER);
            hashMap.put("submodel", Build.MODEL);
            if (location != null) {
                zza(hashMap, location);
            } else if (com_google_android_gms_internal_ads_zzaef.zzccv.versionCode >= 2 && com_google_android_gms_internal_ads_zzaef.zzccv.zzaqe != null) {
                zza(hashMap, com_google_android_gms_internal_ads_zzaef.zzccv.zzaqe);
            }
            if (com_google_android_gms_internal_ads_zzaef.versionCode >= 2) {
                hashMap.put("quality_signals", com_google_android_gms_internal_ads_zzaef.zzcda);
            }
            if (com_google_android_gms_internal_ads_zzaef.versionCode >= 4 && com_google_android_gms_internal_ads_zzaef.zzcdd) {
                hashMap.put("forceHttps", Boolean.valueOf(com_google_android_gms_internal_ads_zzaef.zzcdd));
            }
            if (bundle != null) {
                hashMap.put("content_info", bundle);
            }
            if (com_google_android_gms_internal_ads_zzaef.versionCode >= 5) {
                hashMap.put("u_sd", Float.valueOf(com_google_android_gms_internal_ads_zzaef.zzagu));
                hashMap.put("sh", Integer.valueOf(com_google_android_gms_internal_ads_zzaef.zzcdf));
                hashMap.put("sw", Integer.valueOf(com_google_android_gms_internal_ads_zzaef.zzcde));
            } else {
                hashMap.put("u_sd", Float.valueOf(com_google_android_gms_internal_ads_zzaga.zzagu));
                hashMap.put("sh", Integer.valueOf(com_google_android_gms_internal_ads_zzaga.zzcdf));
                hashMap.put("sw", Integer.valueOf(com_google_android_gms_internal_ads_zzaga.zzcde));
            }
            if (com_google_android_gms_internal_ads_zzaef.versionCode >= 6) {
                if (!TextUtils.isEmpty(com_google_android_gms_internal_ads_zzaef.zzcdg)) {
                    try {
                        hashMap.put("view_hierarchy", new JSONObject(com_google_android_gms_internal_ads_zzaef.zzcdg));
                    } catch (Throwable e2) {
                        zzane.zzc("Problem serializing view hierarchy to JSON", e2);
                    }
                }
                hashMap.put("correlation_id", Long.valueOf(com_google_android_gms_internal_ads_zzaef.zzcdh));
            }
            if (com_google_android_gms_internal_ads_zzaef.versionCode >= 7) {
                hashMap.put("request_id", com_google_android_gms_internal_ads_zzaef.zzcdi);
            }
            if (com_google_android_gms_internal_ads_zzaef.versionCode >= 12 && !TextUtils.isEmpty(com_google_android_gms_internal_ads_zzaef.zzcdm)) {
                hashMap.put("anchor", com_google_android_gms_internal_ads_zzaef.zzcdm);
            }
            if (com_google_android_gms_internal_ads_zzaef.versionCode >= 13) {
                hashMap.put("android_app_volume", Float.valueOf(com_google_android_gms_internal_ads_zzaef.zzcdn));
            }
            if (com_google_android_gms_internal_ads_zzaef.versionCode >= 18) {
                hashMap.put("android_app_muted", Boolean.valueOf(com_google_android_gms_internal_ads_zzaef.zzcdt));
            }
            if (com_google_android_gms_internal_ads_zzaef.versionCode >= 14 && com_google_android_gms_internal_ads_zzaef.zzcdo > 0) {
                hashMap.put("target_api", Integer.valueOf(com_google_android_gms_internal_ads_zzaef.zzcdo));
            }
            if (com_google_android_gms_internal_ads_zzaef.versionCode >= 15) {
                hashMap.put("scroll_index", Integer.valueOf(com_google_android_gms_internal_ads_zzaef.zzcdp == -1 ? -1 : com_google_android_gms_internal_ads_zzaef.zzcdp));
            }
            if (com_google_android_gms_internal_ads_zzaef.versionCode >= 16) {
                hashMap.put("_activity_context", Boolean.valueOf(com_google_android_gms_internal_ads_zzaef.zzcdq));
            }
            if (com_google_android_gms_internal_ads_zzaef.versionCode >= 18) {
                if (!TextUtils.isEmpty(com_google_android_gms_internal_ads_zzaef.zzcdu)) {
                    try {
                        hashMap.put("app_settings", new JSONObject(com_google_android_gms_internal_ads_zzaef.zzcdu));
                    } catch (Throwable e22) {
                        zzane.zzc("Problem creating json from app settings", e22);
                    }
                }
                hashMap.put("render_in_browser", Boolean.valueOf(com_google_android_gms_internal_ads_zzaef.zzbss));
            }
            if (com_google_android_gms_internal_ads_zzaef.versionCode >= 18) {
                hashMap.put("android_num_video_cache_tasks", Integer.valueOf(com_google_android_gms_internal_ads_zzaef.zzcdv));
            }
            zzang com_google_android_gms_internal_ads_zzang = com_google_android_gms_internal_ads_zzaef.zzacr;
            boolean z2 = com_google_android_gms_internal_ads_zzaef.zzceh;
            boolean z3 = com_google_android_gms_internal_ads_zzafl.zzcgv;
            boolean z4 = com_google_android_gms_internal_ads_zzaef.zzcej;
            bundle = new Bundle();
            bundle7 = new Bundle();
            bundle7.putString("cl", "193400285");
            bundle7.putString("rapid_rc", "dev");
            bundle7.putString("rapid_rollup", "HEAD");
            bundle.putBundle("build_meta", bundle7);
            bundle.putString("mf", Boolean.toString(((Boolean) zzkb.zzik().zzd(zznk.zzbbm)).booleanValue()));
            bundle.putBoolean("instant_app", z2);
            bundle.putBoolean("lite", com_google_android_gms_internal_ads_zzang.zzcvh);
            bundle.putBoolean("local_service", z3);
            bundle.putBoolean("is_privileged_process", z4);
            hashMap.put("sdk_env", bundle);
            hashMap.put("cache_state", jSONObject);
            if (com_google_android_gms_internal_ads_zzaef.versionCode >= 19) {
                hashMap.put("gct", com_google_android_gms_internal_ads_zzaef.zzcdx);
            }
            if (com_google_android_gms_internal_ads_zzaef.versionCode >= 21 && com_google_android_gms_internal_ads_zzaef.zzcdy) {
                hashMap.put("de", SchemaSymbols.ATTVAL_TRUE_1);
            }
            if (((Boolean) zzkb.zzik().zzd(zznk.zzayy)).booleanValue()) {
                zzqn = com_google_android_gms_internal_ads_zzaef.zzacv.zzarb;
                obj2 = (zzqn.equals("interstitial_mb") || zzqn.equals("reward_mb")) ? 1 : null;
                bundle4 = com_google_android_gms_internal_ads_zzaef.zzcdz;
                obj = bundle4 != null ? 1 : null;
                if (!(obj2 == null || obj == null)) {
                    bundle7 = new Bundle();
                    bundle7.putBundle("interstitial_pool", bundle4);
                    hashMap.put("counters", bundle7);
                }
            }
            if (com_google_android_gms_internal_ads_zzaef.zzcea != null) {
                hashMap.put("gmp_app_id", com_google_android_gms_internal_ads_zzaef.zzcea);
            }
            if (com_google_android_gms_internal_ads_zzaef.zzceb == null) {
                hashMap.put("fbs_aiid", "");
            } else if ("TIME_OUT".equals(com_google_android_gms_internal_ads_zzaef.zzceb)) {
                hashMap.put("sai_timeout", zzkb.zzik().zzd(zznk.zzaxt));
            } else {
                hashMap.put("fbs_aiid", com_google_android_gms_internal_ads_zzaef.zzceb);
            }
            if (com_google_android_gms_internal_ads_zzaef.zzcec != null) {
                hashMap.put("fbs_aeid", com_google_android_gms_internal_ads_zzaef.zzcec);
            }
            if (com_google_android_gms_internal_ads_zzaef.versionCode >= 24) {
                hashMap.put("disable_ml", Boolean.valueOf(com_google_android_gms_internal_ads_zzaef.zzcei));
            }
            zzqn = (String) zzkb.zzik().zzd(zznk.zzavo);
            if (!(zzqn == null || zzqn.isEmpty())) {
                if (VERSION.SDK_INT >= ((Integer) zzkb.zzik().zzd(zznk.zzavp)).intValue()) {
                    HashMap hashMap2 = new HashMap();
                    for (String str3 : zzqn.split(",")) {
                        hashMap2.put(str3, zzams.zzdd(str3));
                    }
                    hashMap.put("video_decoders", hashMap2);
                }
            }
            if (((Boolean) zzkb.zzik().zzd(zznk.zzbet)).booleanValue()) {
                hashMap.put("omid_v", zzbv.zzfa().getVersion(context));
            }
            if (!(com_google_android_gms_internal_ads_zzaef.zzcek == null || com_google_android_gms_internal_ads_zzaef.zzcek.isEmpty())) {
                hashMap.put("android_permissions", com_google_android_gms_internal_ads_zzaef.zzcek);
            }
            if (zzane.isLoggable(2)) {
                str = "Ad Request JSON: ";
                zzqn = String.valueOf(zzbv.zzek().zzn(hashMap).toString(2));
                zzakb.m3428v(zzqn.length() != 0 ? str.concat(zzqn) : new String(str));
            }
            return zzbv.zzek().zzn(hashMap);
        } catch (JSONException e3) {
            str = "Problem serializing ad request to JSON: ";
            zzqn = String.valueOf(e3.getMessage());
            zzane.zzdk(zzqn.length() != 0 ? str.concat(zzqn) : new String(str));
            return null;
        }
    }

    private static void zza(HashMap<String, Object> hashMap, Location location) {
        HashMap hashMap2 = new HashMap();
        Float valueOf = Float.valueOf(location.getAccuracy() * 1000.0f);
        Long valueOf2 = Long.valueOf(location.getTime() * 1000);
        Long valueOf3 = Long.valueOf((long) (location.getLatitude() * 1.0E7d));
        Long valueOf4 = Long.valueOf((long) (location.getLongitude() * 1.0E7d));
        hashMap2.put("radius", valueOf);
        hashMap2.put(C1048i.ko, valueOf3);
        hashMap2.put(SchemaSymbols.ATTVAL_LONG, valueOf4);
        hashMap2.put("time", valueOf2);
        hashMap.put("uule", hashMap2);
    }

    public static JSONObject zzb(zzaej com_google_android_gms_internal_ads_zzaej) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (com_google_android_gms_internal_ads_zzaej.zzbyq != null) {
            jSONObject.put("ad_base_url", com_google_android_gms_internal_ads_zzaej.zzbyq);
        }
        if (com_google_android_gms_internal_ads_zzaej.zzcet != null) {
            jSONObject.put("ad_size", com_google_android_gms_internal_ads_zzaej.zzcet);
        }
        jSONObject.put("native", com_google_android_gms_internal_ads_zzaej.zzare);
        if (com_google_android_gms_internal_ads_zzaej.zzare) {
            jSONObject.put("ad_json", com_google_android_gms_internal_ads_zzaej.zzceo);
        } else {
            jSONObject.put("ad_html", com_google_android_gms_internal_ads_zzaej.zzceo);
        }
        if (com_google_android_gms_internal_ads_zzaej.zzcev != null) {
            jSONObject.put("debug_dialog", com_google_android_gms_internal_ads_zzaej.zzcev);
        }
        if (com_google_android_gms_internal_ads_zzaej.zzcfl != null) {
            jSONObject.put("debug_signals", com_google_android_gms_internal_ads_zzaej.zzcfl);
        }
        if (com_google_android_gms_internal_ads_zzaej.zzcep != -1) {
            jSONObject.put("interstitial_timeout", ((double) com_google_android_gms_internal_ads_zzaej.zzcep) / 1000.0d);
        }
        if (com_google_android_gms_internal_ads_zzaej.orientation == zzbv.zzem().zzrm()) {
            jSONObject.put("orientation", Ad.ORIENTATION_PORTRAIT);
        } else if (com_google_android_gms_internal_ads_zzaej.orientation == zzbv.zzem().zzrl()) {
            jSONObject.put("orientation", Ad.ORIENTATION_LANDSCAPE);
        }
        if (com_google_android_gms_internal_ads_zzaej.zzbsn != null) {
            jSONObject.put("click_urls", zzm(com_google_android_gms_internal_ads_zzaej.zzbsn));
        }
        if (com_google_android_gms_internal_ads_zzaej.zzbso != null) {
            jSONObject.put("impression_urls", zzm(com_google_android_gms_internal_ads_zzaej.zzbso));
        }
        if (com_google_android_gms_internal_ads_zzaej.zzbsp != null) {
            jSONObject.put("downloaded_impression_urls", zzm(com_google_android_gms_internal_ads_zzaej.zzbsp));
        }
        if (com_google_android_gms_internal_ads_zzaej.zzces != null) {
            jSONObject.put("manual_impression_urls", zzm(com_google_android_gms_internal_ads_zzaej.zzces));
        }
        if (com_google_android_gms_internal_ads_zzaej.zzcey != null) {
            jSONObject.put("active_view", com_google_android_gms_internal_ads_zzaej.zzcey);
        }
        jSONObject.put("ad_is_javascript", com_google_android_gms_internal_ads_zzaej.zzcew);
        if (com_google_android_gms_internal_ads_zzaej.zzcex != null) {
            jSONObject.put("ad_passback_url", com_google_android_gms_internal_ads_zzaej.zzcex);
        }
        jSONObject.put("mediation", com_google_android_gms_internal_ads_zzaej.zzceq);
        jSONObject.put("custom_render_allowed", com_google_android_gms_internal_ads_zzaej.zzcez);
        jSONObject.put("content_url_opted_out", com_google_android_gms_internal_ads_zzaej.zzcfa);
        jSONObject.put("content_vertical_opted_out", com_google_android_gms_internal_ads_zzaej.zzcfm);
        jSONObject.put("prefetch", com_google_android_gms_internal_ads_zzaej.zzcfb);
        if (com_google_android_gms_internal_ads_zzaej.zzbsu != -1) {
            jSONObject.put("refresh_interval_milliseconds", com_google_android_gms_internal_ads_zzaej.zzbsu);
        }
        if (com_google_android_gms_internal_ads_zzaej.zzcer != -1) {
            jSONObject.put("mediation_config_cache_time_milliseconds", com_google_android_gms_internal_ads_zzaej.zzcer);
        }
        if (!TextUtils.isEmpty(com_google_android_gms_internal_ads_zzaej.zzamj)) {
            jSONObject.put("gws_query_id", com_google_android_gms_internal_ads_zzaej.zzamj);
        }
        jSONObject.put("fluid", com_google_android_gms_internal_ads_zzaej.zzarf ? "height" : "");
        jSONObject.put("native_express", com_google_android_gms_internal_ads_zzaej.zzarg);
        if (com_google_android_gms_internal_ads_zzaej.zzcff != null) {
            jSONObject.put("video_start_urls", zzm(com_google_android_gms_internal_ads_zzaej.zzcff));
        }
        if (com_google_android_gms_internal_ads_zzaej.zzcfg != null) {
            jSONObject.put("video_complete_urls", zzm(com_google_android_gms_internal_ads_zzaej.zzcfg));
        }
        if (com_google_android_gms_internal_ads_zzaej.zzcfe != null) {
            zzaig com_google_android_gms_internal_ads_zzaig = com_google_android_gms_internal_ads_zzaej.zzcfe;
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("rb_type", com_google_android_gms_internal_ads_zzaig.type);
            jSONObject2.put("rb_amount", com_google_android_gms_internal_ads_zzaig.zzcmk);
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(jSONObject2);
            jSONObject.put("rewards", jSONArray);
        }
        jSONObject.put("use_displayed_impression", com_google_android_gms_internal_ads_zzaej.zzcfh);
        jSONObject.put("auto_protection_configuration", com_google_android_gms_internal_ads_zzaej.zzcfi);
        jSONObject.put("render_in_browser", com_google_android_gms_internal_ads_zzaej.zzbss);
        jSONObject.put("disable_closable_area", com_google_android_gms_internal_ads_zzaej.zzzm);
        return jSONObject;
    }

    @Nullable
    private static JSONArray zzm(List<String> list) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        for (String put : list) {
            jSONArray.put(put);
        }
        return jSONArray;
    }

    private static Integer zzv(boolean z) {
        return Integer.valueOf(z ? 1 : 0);
    }
}
