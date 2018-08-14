package com.google.android.gms.internal.ads;

import android.net.Uri;
import android.net.Uri.Builder;
import android.text.TextUtils;
import com.appnext.core.Ad;
import com.google.android.gms.ads.internal.zzbv;
import cz.msebera.android.httpclient.cookie.SM;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

@zzadh
public final class zzafx {
    private int mOrientation = -1;
    private String zzbhy;
    private boolean zzbtn = false;
    private final zzaef zzbuc;
    private List<String> zzcab;
    private String zzcae;
    private String zzchw;
    private String zzchx;
    private List<String> zzchy;
    private String zzchz;
    private String zzcia;
    private String zzcib;
    private List<String> zzcic;
    private List<String> zzcid;
    private long zzcie = -1;
    private boolean zzcif = false;
    private final long zzcig = -1;
    private long zzcih = -1;
    private boolean zzcii = false;
    private boolean zzcij = false;
    private boolean zzcik = false;
    private boolean zzcil = true;
    private boolean zzcim = true;
    private String zzcin = "";
    private boolean zzcio = false;
    private zzaig zzcip;
    private List<String> zzciq;
    private List<String> zzcir;
    private boolean zzcis = false;
    private boolean zzcit = false;
    private String zzciu;
    private List<String> zzciv;
    private boolean zzciw;
    private String zzcix;
    private zzaiq zzciy;
    private boolean zzciz;
    private boolean zzcja;
    private boolean zzcjb;
    private boolean zzcjc;
    private zzael zzxe;

    public zzafx(zzaef com_google_android_gms_internal_ads_zzaef, String str) {
        this.zzchx = str;
        this.zzbuc = com_google_android_gms_internal_ads_zzaef;
    }

    private static String zza(Map<String, List<String>> map, String str) {
        List list = (List) map.get(str);
        return (list == null || list.isEmpty()) ? null : (String) list.get(0);
    }

    private static long zzb(Map<String, List<String>> map, String str) {
        List list = (List) map.get(str);
        if (!(list == null || list.isEmpty())) {
            String str2 = (String) list.get(0);
            try {
                return (long) (Float.parseFloat(str2) * 1000.0f);
            } catch (NumberFormatException e) {
                zzane.zzdk(new StringBuilder((String.valueOf(str).length() + 36) + String.valueOf(str2).length()).append("Could not parse float from ").append(str).append(" header: ").append(str2).toString());
            }
        }
        return -1;
    }

    private static List<String> zzc(Map<String, List<String>> map, String str) {
        List list = (List) map.get(str);
        if (!(list == null || list.isEmpty())) {
            String str2 = (String) list.get(0);
            if (str2 != null) {
                return Arrays.asList(str2.trim().split("\\s+"));
            }
        }
        return null;
    }

    private static boolean zzd(Map<String, List<String>> map, String str) {
        List list = (List) map.get(str);
        return (list == null || list.isEmpty()) ? false : Boolean.valueOf((String) list.get(0)).booleanValue();
    }

    public final zzaej zza(long j, boolean z) {
        return new zzaej(this.zzbuc, this.zzchx, this.zzbhy, this.zzchy, this.zzcic, this.zzcie, this.zzcif, -1, this.zzcab, this.zzcih, this.mOrientation, this.zzchw, j, this.zzcia, this.zzcib, this.zzcii, this.zzcij, this.zzcik, this.zzcil, false, this.zzcin, this.zzcio, this.zzbtn, this.zzcip, this.zzciq, this.zzcir, this.zzcis, this.zzxe, this.zzcit, this.zzciu, this.zzciv, this.zzciw, this.zzcix, this.zzciy, this.zzchz, this.zzcim, this.zzciz, this.zzcja, z ? 2 : 1, this.zzcjb, this.zzcid, this.zzcjc, this.zzcae);
    }

    public final void zza(String str, Map<String, List<String>> map, String str2) {
        this.zzbhy = str2;
        zzl(map);
    }

    public final void zzl(Map<String, List<String>> map) {
        String str;
        this.zzchw = zza((Map) map, "X-Afma-Ad-Size");
        this.zzcix = zza((Map) map, "X-Afma-Ad-Slot-Size");
        List zzc = zzc(map, "X-Afma-Click-Tracking-Urls");
        if (zzc != null) {
            this.zzchy = zzc;
        }
        this.zzchz = zza((Map) map, "X-Afma-Debug-Signals");
        zzc = (List) map.get("X-Afma-Debug-Dialog");
        if (!(zzc == null || zzc.isEmpty())) {
            this.zzcia = (String) zzc.get(0);
        }
        zzc = zzc(map, "X-Afma-Tracking-Urls");
        if (zzc != null) {
            this.zzcic = zzc;
        }
        zzc = zzc(map, "X-Afma-Downloaded-Impression-Urls");
        if (zzc != null) {
            this.zzcid = zzc;
        }
        long zzb = zzb(map, "X-Afma-Interstitial-Timeout");
        if (zzb != -1) {
            this.zzcie = zzb;
        }
        this.zzcif |= zzd(map, "X-Afma-Mediation");
        zzc = zzc(map, "X-Afma-Manual-Tracking-Urls");
        if (zzc != null) {
            this.zzcab = zzc;
        }
        zzb = zzb(map, "X-Afma-Refresh-Rate");
        if (zzb != -1) {
            this.zzcih = zzb;
        }
        zzc = (List) map.get("X-Afma-Orientation");
        if (!(zzc == null || zzc.isEmpty())) {
            str = (String) zzc.get(0);
            if (Ad.ORIENTATION_PORTRAIT.equalsIgnoreCase(str)) {
                this.mOrientation = zzbv.zzem().zzrm();
            } else if (Ad.ORIENTATION_LANDSCAPE.equalsIgnoreCase(str)) {
                this.mOrientation = zzbv.zzem().zzrl();
            }
        }
        this.zzcib = zza((Map) map, "X-Afma-ActiveView");
        zzc = (List) map.get("X-Afma-Use-HTTPS");
        if (!(zzc == null || zzc.isEmpty())) {
            this.zzcik = Boolean.valueOf((String) zzc.get(0)).booleanValue();
        }
        this.zzcii |= zzd(map, "X-Afma-Custom-Rendering-Allowed");
        this.zzcij = "native".equals(zza((Map) map, "X-Afma-Ad-Format"));
        zzc = (List) map.get("X-Afma-Content-Url-Opted-Out");
        if (!(zzc == null || zzc.isEmpty())) {
            this.zzcil = Boolean.valueOf((String) zzc.get(0)).booleanValue();
        }
        zzc = (List) map.get("X-Afma-Content-Vertical-Opted-Out");
        if (!(zzc == null || zzc.isEmpty())) {
            this.zzcim = Boolean.valueOf((String) zzc.get(0)).booleanValue();
        }
        zzc = (List) map.get("X-Afma-Gws-Query-Id");
        if (!(zzc == null || zzc.isEmpty())) {
            this.zzcin = (String) zzc.get(0);
        }
        str = zza((Map) map, "X-Afma-Fluid");
        if (str != null && str.equals("height")) {
            this.zzcio = true;
        }
        this.zzbtn = "native_express".equals(zza((Map) map, "X-Afma-Ad-Format"));
        this.zzcip = zzaig.zzce(zza((Map) map, "X-Afma-Rewards"));
        if (this.zzciq == null) {
            this.zzciq = zzc(map, "X-Afma-Reward-Video-Start-Urls");
        }
        if (this.zzcir == null) {
            this.zzcir = zzc(map, "X-Afma-Reward-Video-Complete-Urls");
        }
        this.zzcis |= zzd(map, "X-Afma-Use-Displayed-Impression");
        this.zzcit |= zzd(map, "X-Afma-Auto-Collect-Location");
        this.zzciu = zza((Map) map, SM.SET_COOKIE);
        Object zza = zza((Map) map, "X-Afma-Auto-Protection-Configuration");
        if (zza == null || TextUtils.isEmpty(zza)) {
            Builder buildUpon = Uri.parse("https://pagead2.googlesyndication.com/pagead/gen_204").buildUpon();
            buildUpon.appendQueryParameter("id", "gmob-apps-blocked-navigation");
            if (!TextUtils.isEmpty(this.zzcia)) {
                buildUpon.appendQueryParameter("debugDialog", this.zzcia);
            }
            boolean booleanValue = ((Boolean) zzkb.zzik().zzd(zznk.zzaum)).booleanValue();
            String[] strArr = new String[1];
            String builder = buildUpon.toString();
            strArr[0] = new StringBuilder(String.valueOf(builder).length() + 31).append(builder).append("&navigationURL={NAVIGATION_URL}").toString();
            this.zzxe = new zzael(booleanValue, Arrays.asList(strArr));
        } else {
            try {
                this.zzxe = zzael.zzl(new JSONObject(zza));
            } catch (Throwable e) {
                zzane.zzc("Error parsing configuration JSON", e);
                this.zzxe = new zzael();
            }
        }
        zzc = zzc(map, "X-Afma-Remote-Ping-Urls");
        if (zzc != null) {
            this.zzciv = zzc;
        }
        zza = zza((Map) map, "X-Afma-Safe-Browsing");
        if (!TextUtils.isEmpty(zza)) {
            try {
                this.zzciy = zzaiq.zzo(new JSONObject(zza));
            } catch (Throwable e2) {
                zzane.zzc("Error parsing safe browsing header", e2);
            }
        }
        this.zzciw |= zzd(map, "X-Afma-Render-In-Browser");
        zza = zza((Map) map, "X-Afma-Pool");
        if (!TextUtils.isEmpty(zza)) {
            try {
                this.zzciz = new JSONObject(zza).getBoolean("never_pool");
            } catch (Throwable e22) {
                zzane.zzc("Error parsing interstitial pool header", e22);
            }
        }
        this.zzcja = zzd(map, "X-Afma-Custom-Close-Blocked");
        this.zzcjb = zzd(map, "X-Afma-Enable-Omid");
        this.zzcjc = zzd(map, "X-Afma-Disable-Closable-Area");
        this.zzcae = zza((Map) map, "X-Afma-Omid-Settings");
    }
}
