package com.google.android.gms.ads.internal.gmsg;

import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzakk;
import com.google.android.gms.internal.ads.zzaqw;
import java.util.HashMap;
import java.util.Map;

final class zzm implements zzv<zzaqw> {
    zzm() {
    }

    public final /* synthetic */ void zza(Object obj, Map map) {
        zzaqw com_google_android_gms_internal_ads_zzaqw = (zzaqw) obj;
        WindowManager windowManager = (WindowManager) com_google_android_gms_internal_ads_zzaqw.getContext().getSystemService("window");
        zzbv.zzek();
        View view = (View) com_google_android_gms_internal_ads_zzaqw;
        DisplayMetrics zza = zzakk.zza(windowManager);
        int i = zza.widthPixels;
        int i2 = zza.heightPixels;
        int[] iArr = new int[2];
        Map hashMap = new HashMap();
        view.getLocationInWindow(iArr);
        hashMap.put("xInPixels", Integer.valueOf(iArr[0]));
        hashMap.put("yInPixels", Integer.valueOf(iArr[1]));
        hashMap.put("windowWidthInPixels", Integer.valueOf(i));
        hashMap.put("windowHeightInPixels", Integer.valueOf(i2));
        com_google_android_gms_internal_ads_zzaqw.zza("locationReady", hashMap);
        zzakb.zzdk("GET LOCATION COMPILED");
    }
}
