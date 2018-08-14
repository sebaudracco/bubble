package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.Color;
import android.os.Build.VERSION;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.MotionEvent;
import com.appnext.base.p023b.C1042c;
import com.google.android.gms.ads.internal.gmsg.zzv;
import com.googlecode.mp4parser.boxes.apple.TrackLoadSettingsAtom;
import com.mobfox.sdk.networking.RequestParams;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

@zzadh
public final class zzaqc implements zzv<zzapw> {
    private boolean zzdau;

    private static int zza(Context context, Map<String, String> map, String str, int i) {
        String str2 = (String) map.get(str);
        if (str2 != null) {
            try {
                zzkb.zzif();
                i = zzamu.zza(context, Integer.parseInt(str2));
            } catch (NumberFormatException e) {
                zzane.zzdk(new StringBuilder((String.valueOf(str).length() + 34) + String.valueOf(str2).length()).append("Could not parse ").append(str).append(" in a video GMSG: ").append(str2).toString());
            }
        }
        return i;
    }

    private static void zza(zzapi com_google_android_gms_internal_ads_zzapi, Map<String, String> map) {
        String str = (String) map.get("minBufferMs");
        String str2 = (String) map.get("maxBufferMs");
        String str3 = (String) map.get("bufferForPlaybackMs");
        String str4 = (String) map.get("bufferForPlaybackAfterRebufferMs");
        if (str != null) {
            try {
                Integer.parseInt(str);
            } catch (NumberFormatException e) {
                zzane.zzdk(String.format("Could not parse buffer parameters in loadControl video GMSG: (%s, %s)", new Object[]{str, str2}));
                return;
            }
        }
        if (str2 != null) {
            Integer.parseInt(str2);
        }
        if (str3 != null) {
            Integer.parseInt(str3);
        }
        if (str4 != null) {
            Integer.parseInt(str4);
        }
    }

    public final /* synthetic */ void zza(Object obj, Map map) {
        int i = 0;
        zzapw com_google_android_gms_internal_ads_zzapw = (zzapw) obj;
        String str = (String) map.get(C1042c.jL);
        if (str == null) {
            zzane.zzdk("Action missing from video GMSG.");
            return;
        }
        if (zzane.isLoggable(3)) {
            JSONObject jSONObject = new JSONObject(map);
            jSONObject.remove("google.afma.Notify_dt");
            String jSONObject2 = jSONObject.toString();
            zzane.zzck(new StringBuilder((String.valueOf(str).length() + 13) + String.valueOf(jSONObject2).length()).append("Video GMSG: ").append(str).append(" ").append(jSONObject2).toString());
        }
        if ("background".equals(str)) {
            jSONObject2 = (String) map.get("color");
            if (TextUtils.isEmpty(jSONObject2)) {
                zzane.zzdk("Color parameter missing from color video GMSG.");
                return;
            }
            try {
                com_google_android_gms_internal_ads_zzapw.setBackgroundColor(Color.parseColor(jSONObject2));
            } catch (IllegalArgumentException e) {
                zzane.zzdk("Invalid color parameter in video GMSG.");
            }
        } else if ("decoderProps".equals(str)) {
            jSONObject2 = (String) map.get("mimeTypes");
            if (jSONObject2 == null) {
                zzane.zzdk("No MIME types specified for decoder properties inspection.");
                zzapi.zza(com_google_android_gms_internal_ads_zzapw, "missingMimeTypes");
            } else if (VERSION.SDK_INT < 16) {
                zzane.zzdk("Video decoder properties available on API versions >= 16.");
                zzapi.zza(com_google_android_gms_internal_ads_zzapw, "deficientApiVersion");
            } else {
                Map hashMap = new HashMap();
                String[] split = jSONObject2.split(",");
                r2 = split.length;
                while (i < r2) {
                    String str2 = split[i];
                    hashMap.put(str2, zzams.zzdd(str2.trim()));
                    i++;
                }
                zzapi.zza(com_google_android_gms_internal_ads_zzapw, hashMap);
            }
        } else {
            zzapn zztl = com_google_android_gms_internal_ads_zzapw.zztl();
            if (zztl == null) {
                zzane.zzdk("Could not get underlay container for a video GMSG.");
                return;
            }
            boolean equals = "new".equals(str);
            boolean equals2 = "position".equals(str);
            int min;
            if (equals || equals2) {
                Context context = com_google_android_gms_internal_ads_zzapw.getContext();
                int zza = zza(context, map, "x", 0);
                r2 = zza(context, map, "y", 0);
                int zza2 = zza(context, map, "w", -1);
                int zza3 = zza(context, map, RequestParams.f9035H, -1);
                if (((Boolean) zzkb.zzik().zzd(zznk.zzbca)).booleanValue()) {
                    min = Math.min(zza2, com_google_android_gms_internal_ads_zzapw.zzts() - zza);
                    zza3 = Math.min(zza3, com_google_android_gms_internal_ads_zzapw.zztr() - r2);
                } else {
                    min = zza2;
                }
                try {
                    zza2 = Integer.parseInt((String) map.get("player"));
                } catch (NumberFormatException e2) {
                    zza2 = 0;
                }
                boolean parseBoolean = Boolean.parseBoolean((String) map.get("spherical"));
                if (equals && zztl.zzth() == null) {
                    zztl.zza(zza, r2, min, zza3, zza2, parseBoolean, new zzapv((String) map.get("flags")));
                    zzapi zzth = zztl.zzth();
                    if (zzth != null) {
                        zza(zzth, map);
                        return;
                    }
                    return;
                }
                zztl.zze(zza, r2, min, zza3);
                return;
            }
            zzapi zzth2 = zztl.zzth();
            if (zzth2 == null) {
                zzapi.zza(com_google_android_gms_internal_ads_zzapw);
            } else if ("click".equals(str)) {
                r0 = com_google_android_gms_internal_ads_zzapw.getContext();
                r2 = zza(r0, map, "x", 0);
                min = zza(r0, map, "y", 0);
                long uptimeMillis = SystemClock.uptimeMillis();
                MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 0, (float) r2, (float) min, 0);
                zzth2.zzf(obtain);
                obtain.recycle();
            } else if ("currentTime".equals(str)) {
                jSONObject2 = (String) map.get("time");
                if (jSONObject2 == null) {
                    zzane.zzdk("Time parameter missing from currentTime video GMSG.");
                    return;
                }
                try {
                    zzth2.seekTo((int) (Float.parseFloat(jSONObject2) * 1000.0f));
                } catch (NumberFormatException e3) {
                    str = "Could not parse time parameter from currentTime video GMSG: ";
                    jSONObject2 = String.valueOf(jSONObject2);
                    zzane.zzdk(jSONObject2.length() != 0 ? str.concat(jSONObject2) : new String(str));
                }
            } else if ("hide".equals(str)) {
                zzth2.setVisibility(4);
            } else if (TrackLoadSettingsAtom.TYPE.equals(str)) {
                zzth2.zzta();
            } else if ("loadControl".equals(str)) {
                zza(zzth2, map);
            } else if ("muted".equals(str)) {
                if (Boolean.parseBoolean((String) map.get("muted"))) {
                    zzth2.zztb();
                } else {
                    zzth2.zztc();
                }
            } else if ("pause".equals(str)) {
                zzth2.pause();
            } else if ("play".equals(str)) {
                zzth2.play();
            } else if ("show".equals(str)) {
                zzth2.setVisibility(0);
            } else if ("src".equals(str)) {
                zzth2.zzdn((String) map.get("src"));
            } else if ("touchMove".equals(str)) {
                r0 = com_google_android_gms_internal_ads_zzapw.getContext();
                zzth2.zza((float) zza(r0, map, "dx", 0), (float) zza(r0, map, "dy", 0));
                if (!this.zzdau) {
                    com_google_android_gms_internal_ads_zzapw.zznp();
                    this.zzdau = true;
                }
            } else if ("volume".equals(str)) {
                jSONObject2 = (String) map.get("volume");
                if (jSONObject2 == null) {
                    zzane.zzdk("Level parameter missing from volume video GMSG.");
                    return;
                }
                try {
                    zzth2.setVolume(Float.parseFloat(jSONObject2));
                } catch (NumberFormatException e4) {
                    str = "Could not parse volume parameter from volume video GMSG: ";
                    jSONObject2 = String.valueOf(jSONObject2);
                    zzane.zzdk(jSONObject2.length() != 0 ? str.concat(jSONObject2) : new String(str));
                }
            } else if ("watermark".equals(str)) {
                zzth2.zztd();
            } else {
                String str3 = "Unknown video action: ";
                jSONObject2 = String.valueOf(str);
                zzane.zzdk(jSONObject2.length() != 0 ? str3.concat(jSONObject2) : new String(str3));
            }
        }
    }
}
