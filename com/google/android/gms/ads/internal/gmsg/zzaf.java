package com.google.android.gms.ads.internal.gmsg;

import android.text.TextUtils;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzane;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.GuardedBy;
import org.json.JSONException;
import org.json.JSONObject;

@zzadh
@ParametersAreNonnullByDefault
public final class zzaf implements zzv<Object> {
    private final Object mLock = new Object();
    @GuardedBy("mLock")
    private final Map<String, zzag> zzbnf = new HashMap();

    public final void zza(Object obj, Map<String, String> map) {
        String str = (String) map.get("id");
        String str2 = (String) map.get("fail");
        String str3 = (String) map.get("fail_reason");
        String str4 = (String) map.get("fail_stack");
        String str5 = (String) map.get("result");
        if (TextUtils.isEmpty(str4)) {
            Object obj2 = "Unknown Fail Reason.";
        } else {
            String str6 = str3;
        }
        if (TextUtils.isEmpty(str4)) {
            Object obj3 = "";
        } else {
            String str7 = "\n";
            str3 = String.valueOf(str4);
            str4 = str3.length() != 0 ? str7.concat(str3) : new String(str7);
        }
        synchronized (this.mLock) {
            zzag com_google_android_gms_ads_internal_gmsg_zzag = (zzag) this.zzbnf.remove(str);
            if (com_google_android_gms_ads_internal_gmsg_zzag == null) {
                str2 = "Received result for unexpected method invocation: ";
                str = String.valueOf(str);
                zzane.zzdk(str.length() != 0 ? str2.concat(str) : new String(str2));
            } else if (!TextUtils.isEmpty(str2)) {
                str2 = String.valueOf(obj2);
                str = String.valueOf(obj3);
                com_google_android_gms_ads_internal_gmsg_zzag.zzau(str.length() != 0 ? str2.concat(str) : new String(str2));
            } else if (str5 == null) {
                com_google_android_gms_ads_internal_gmsg_zzag.zzd(null);
            } else {
                try {
                    JSONObject jSONObject = new JSONObject(str5);
                    if (zzakb.zzqp()) {
                        str4 = "Result GMSG: ";
                        str = String.valueOf(jSONObject.toString(2));
                        zzakb.m3428v(str.length() != 0 ? str4.concat(str) : new String(str4));
                    }
                    com_google_android_gms_ads_internal_gmsg_zzag.zzd(jSONObject);
                } catch (JSONException e) {
                    com_google_android_gms_ads_internal_gmsg_zzag.zzau(e.getMessage());
                }
            }
        }
    }

    public final void zza(String str, zzag com_google_android_gms_ads_internal_gmsg_zzag) {
        synchronized (this.mLock) {
            this.zzbnf.put(str, com_google_android_gms_ads_internal_gmsg_zzag);
        }
    }
}
