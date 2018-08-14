package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import java.lang.ref.WeakReference;
import java.util.Map;

public interface zzoz {
    void cancelUnconfirmedClick();

    Context getContext();

    void performClick(Bundle bundle);

    boolean recordImpression(Bundle bundle);

    void reportTouchEvent(Bundle bundle);

    void setClickConfirmingView(View view);

    View zza(OnClickListener onClickListener, boolean z);

    void zza(View view, zzox com_google_android_gms_internal_ads_zzox);

    void zza(View view, String str, Bundle bundle, Map<String, WeakReference<View>> map, View view2);

    void zza(View view, Map<String, WeakReference<View>> map);

    void zza(View view, Map<String, WeakReference<View>> map, Bundle bundle, View view2);

    void zza(zzro com_google_android_gms_internal_ads_zzro);

    void zzb(View view, Map<String, WeakReference<View>> map);

    void zzc(View view, Map<String, WeakReference<View>> map);

    void zzcr();

    void zzcs();

    void zzd(MotionEvent motionEvent);

    void zzj(View view);

    boolean zzkj();

    boolean zzkk();

    void zzkl();

    void zzkp();

    void zzkq();

    View zzkr();

    void zzl(View view);
}
