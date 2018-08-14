package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.google.android.gms.ads.formats.NativeAppInstallAd;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.Map.Entry;
import javax.annotation.ParametersAreNonnullByDefault;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.json.JSONException;
import org.json.JSONObject;

@zzadh
@ParametersAreNonnullByDefault
public class zzpd implements zzoz {
    private final Context mContext;
    private final Object mLock = new Object();
    @Nullable
    private final zzacm zzaad;
    @Nullable
    private String zzaae;
    private final zzpa zzbiw;
    private final zzok zzbiz;
    @Nullable
    private final JSONObject zzbja;
    @Nullable
    private final zzpb zzbjb;
    private final zzci zzbjc;
    @VisibleForTesting
    boolean zzbjd;
    @VisibleForTesting
    boolean zzbje;
    private WeakReference<View> zzbjf = null;
    @Nullable
    private final zzang zzyf;
    @Nullable
    private zzaix zzyv;

    public zzpd(Context context, zzpa com_google_android_gms_internal_ads_zzpa, @Nullable zzacm com_google_android_gms_internal_ads_zzacm, zzci com_google_android_gms_internal_ads_zzci, @Nullable JSONObject jSONObject, @Nullable zzpb com_google_android_gms_internal_ads_zzpb, @Nullable zzang com_google_android_gms_internal_ads_zzang, @Nullable String str) {
        this.mContext = context;
        this.zzbiw = com_google_android_gms_internal_ads_zzpa;
        this.zzaad = com_google_android_gms_internal_ads_zzacm;
        this.zzbjc = com_google_android_gms_internal_ads_zzci;
        this.zzbja = jSONObject;
        this.zzbjb = com_google_android_gms_internal_ads_zzpb;
        this.zzyf = com_google_android_gms_internal_ads_zzang;
        this.zzaae = str;
        this.zzbiz = new zzok(this.zzaad);
    }

    private final JSONObject zza(Map<String, WeakReference<View>> map, View view) {
        JSONObject jSONObject = new JSONObject();
        if (map == null || view == null) {
            return jSONObject;
        }
        int[] zzn = zzn(view);
        synchronized (map) {
            for (Entry entry : map.entrySet()) {
                View view2 = (View) ((WeakReference) entry.getValue()).get();
                if (view2 != null) {
                    int[] zzn2 = zzn(view2);
                    JSONObject jSONObject2 = new JSONObject();
                    JSONObject jSONObject3 = new JSONObject();
                    try {
                        Object zzb;
                        jSONObject3.put("width", zzv(view2.getMeasuredWidth()));
                        jSONObject3.put("height", zzv(view2.getMeasuredHeight()));
                        jSONObject3.put("x", zzv(zzn2[0] - zzn[0]));
                        jSONObject3.put("y", zzv(zzn2[1] - zzn[1]));
                        jSONObject3.put("relative_to", "ad_view");
                        jSONObject2.put("frame", jSONObject3);
                        Rect rect = new Rect();
                        if (view2.getLocalVisibleRect(rect)) {
                            zzb = zzb(rect);
                        } else {
                            zzb = new JSONObject();
                            zzb.put("width", 0);
                            zzb.put("height", 0);
                            zzb.put("x", zzv(zzn2[0] - zzn[0]));
                            zzb.put("y", zzv(zzn2[1] - zzn[1]));
                            zzb.put("relative_to", "ad_view");
                        }
                        jSONObject2.put("visible_bounds", zzb);
                        if (view2 instanceof TextView) {
                            TextView textView = (TextView) view2;
                            jSONObject2.put("text_color", textView.getCurrentTextColor());
                            jSONObject2.put("font_size", (double) textView.getTextSize());
                            jSONObject2.put("text", textView.getText());
                        }
                        jSONObject.put((String) entry.getKey(), jSONObject2);
                    } catch (JSONException e) {
                        zzane.zzdk("Unable to get asset views information");
                    }
                }
            }
        }
        return jSONObject;
    }

    private final void zza(View view, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3, JSONObject jSONObject4, String str, JSONObject jSONObject5, JSONObject jSONObject6) {
        Preconditions.checkMainThread("Invalid call from a non-UI thread.");
        try {
            JSONObject jSONObject7 = new JSONObject();
            jSONObject7.put("ad", this.zzbja);
            if (jSONObject2 != null) {
                jSONObject7.put("asset_view_signal", jSONObject2);
            }
            if (jSONObject != null) {
                jSONObject7.put("ad_view_signal", jSONObject);
            }
            if (jSONObject5 != null) {
                jSONObject7.put("click_signal", jSONObject5);
            }
            if (jSONObject3 != null) {
                jSONObject7.put("scroll_view_signal", jSONObject3);
            }
            if (jSONObject4 != null) {
                jSONObject7.put("lock_screen_signal", jSONObject4);
            }
            JSONObject jSONObject8 = new JSONObject();
            jSONObject8.put("asset_id", str);
            jSONObject8.put("template", this.zzbjb.zzkb());
            zzbv.zzem();
            jSONObject8.put("is_privileged_process", zzakq.zzrp());
            if (((Boolean) zzkb.zzik().zzd(zznk.zzbcf)).booleanValue() && this.zzbiz.zzjw() != null && this.zzbja.optBoolean("custom_one_point_five_click_enabled", false)) {
                jSONObject8.put("custom_one_point_five_click_eligible", true);
            }
            jSONObject8.put("timestamp", zzbv.zzer().currentTimeMillis());
            jSONObject8.put("has_custom_click_handler", this.zzbiw.zzr(this.zzbjb.getCustomTemplateId()) != null);
            jSONObject7.put("has_custom_click_handler", this.zzbiw.zzr(this.zzbjb.getCustomTemplateId()) != null);
            try {
                JSONObject optJSONObject = this.zzbja.optJSONObject("tracking_urls_and_actions");
                if (optJSONObject == null) {
                    optJSONObject = new JSONObject();
                }
                jSONObject8.put("click_signals", this.zzbjc.zzaa().zza(this.mContext, optJSONObject.optString("click_string"), view));
            } catch (Throwable e) {
                zzane.zzb("Exception obtaining click signals", e);
            }
            jSONObject7.put("click", jSONObject8);
            if (jSONObject6 != null) {
                jSONObject7.put("provided_signals", jSONObject6);
            }
            jSONObject7.put("ads_id", this.zzaae);
            zzanm.zza(this.zzaad.zzi(jSONObject7), "NativeAdEngineImpl.performClick");
        } catch (Throwable e2) {
            zzane.zzb("Unable to create click JSON.", e2);
        }
    }

    private final boolean zza(JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3, JSONObject jSONObject4, JSONObject jSONObject5) {
        Preconditions.checkMainThread("Invalid call from a non-UI thread.");
        if (this.zzbjd) {
            return true;
        }
        this.zzbjd = true;
        try {
            JSONObject jSONObject6 = new JSONObject();
            jSONObject6.put("ad", this.zzbja);
            jSONObject6.put("ads_id", this.zzaae);
            if (jSONObject2 != null) {
                jSONObject6.put("asset_view_signal", jSONObject2);
            }
            if (jSONObject != null) {
                jSONObject6.put("ad_view_signal", jSONObject);
            }
            if (jSONObject3 != null) {
                jSONObject6.put("scroll_view_signal", jSONObject3);
            }
            if (jSONObject4 != null) {
                jSONObject6.put("lock_screen_signal", jSONObject4);
            }
            if (jSONObject5 != null) {
                jSONObject6.put("provided_signals", jSONObject5);
            }
            zzanm.zza(this.zzaad.zzj(jSONObject6), "NativeAdEngineImpl.recordImpression");
            this.zzbiw.zza(this);
            this.zzbiw.zzbv();
            zzcr();
            return true;
        } catch (Throwable e) {
            zzane.zzb("Unable to create impression JSON.", e);
            return false;
        }
    }

    private final boolean zzaq(String str) {
        JSONObject optJSONObject = this.zzbja == null ? null : this.zzbja.optJSONObject("allow_pub_event_reporting");
        return optJSONObject == null ? false : optJSONObject.optBoolean(str, false);
    }

    private final JSONObject zzb(Rect rect) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("width", zzv(rect.right - rect.left));
        jSONObject.put("height", zzv(rect.bottom - rect.top));
        jSONObject.put("x", zzv(rect.left));
        jSONObject.put("y", zzv(rect.top));
        jSONObject.put("relative_to", "self");
        return jSONObject;
    }

    private static boolean zzm(View view) {
        return view.isShown() && view.getGlobalVisibleRect(new Rect(), null);
    }

    @VisibleForTesting
    private static int[] zzn(View view) {
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        return iArr;
    }

    private final JSONObject zzo(View view) {
        JSONObject jSONObject = new JSONObject();
        if (view != null) {
            try {
                Object zzb;
                int[] zzn = zzn(view);
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("width", zzv(view.getMeasuredWidth()));
                jSONObject2.put("height", zzv(view.getMeasuredHeight()));
                jSONObject2.put("x", zzv(zzn[0]));
                jSONObject2.put("y", zzv(zzn[1]));
                jSONObject2.put("relative_to", "window");
                jSONObject.put("frame", jSONObject2);
                Rect rect = new Rect();
                if (view.getGlobalVisibleRect(rect)) {
                    zzb = zzb(rect);
                } else {
                    zzb = new JSONObject();
                    zzb.put("width", 0);
                    zzb.put("height", 0);
                    zzb.put("x", zzv(zzn[0]));
                    zzb.put("y", zzv(zzn[1]));
                    zzb.put("relative_to", "window");
                }
                jSONObject.put("visible_bounds", zzb);
            } catch (Exception e) {
                zzane.zzdk("Unable to get native ad view bounding box");
            }
        }
        return jSONObject;
    }

    private static JSONObject zzp(View view) {
        JSONObject jSONObject = new JSONObject();
        if (view != null) {
            try {
                zzbv.zzek();
                jSONObject.put("contained_in_scroll_view", zzakk.zzx(view) != -1);
            } catch (Exception e) {
            }
        }
        return jSONObject;
    }

    private final JSONObject zzq(View view) {
        JSONObject jSONObject = new JSONObject();
        if (view != null) {
            try {
                zzbv.zzek();
                jSONObject.put("can_show_on_lock_screen", zzakk.zzw(view));
                zzbv.zzek();
                jSONObject.put("is_keyguard_locked", zzakk.zzau(this.mContext));
            } catch (JSONException e) {
                zzane.zzdk("Unable to get lock screen information");
            }
        }
        return jSONObject;
    }

    @VisibleForTesting
    private final int zzv(int i) {
        zzkb.zzif();
        return zzamu.zzb(this.mContext, i);
    }

    public void cancelUnconfirmedClick() {
        if (!((Boolean) zzkb.zzik().zzd(zznk.zzbcf)).booleanValue()) {
            return;
        }
        if (this.zzbja.optBoolean("custom_one_point_five_click_enabled", false)) {
            this.zzbiz.cancelUnconfirmedClick();
        } else {
            zzane.zzdk("Your account need to be whitelisted to use this feature.\nContact your account manager for more information.");
        }
    }

    public final Context getContext() {
        return this.mContext;
    }

    public final void performClick(Bundle bundle) {
        if (bundle == null) {
            zzane.zzck("Click data is null. No click is reported.");
        } else if (zzaq("click_reporting")) {
            zza(null, null, null, null, null, bundle.getBundle("click_signal").getString("asset_id"), null, zzbv.zzek().zza(bundle, null));
        } else {
            zzane.m3427e("The ad slot cannot handle external click events. You must be whitelisted to be able to report your click events.");
        }
    }

    public final boolean recordImpression(Bundle bundle) {
        if (zzaq("impression_reporting")) {
            return zza(null, null, null, null, zzbv.zzek().zza(bundle, null));
        }
        zzane.m3427e("The ad slot cannot handle external impression events. You must be whitelisted to whitelisted to be able to report your impression events.");
        return false;
    }

    public final void reportTouchEvent(Bundle bundle) {
        if (bundle == null) {
            zzane.zzck("Touch event data is null. No touch event is reported.");
        } else if (zzaq("touch_reporting")) {
            this.zzbjc.zzaa().zza((int) bundle.getFloat("x"), (int) bundle.getFloat("y"), bundle.getInt("duration_ms"));
        } else {
            zzane.m3427e("The ad slot cannot handle external touch events. You must be whitelisted to be able to report your touch events.");
        }
    }

    public void setClickConfirmingView(View view) {
        if (!((Boolean) zzkb.zzik().zzd(zznk.zzbcf)).booleanValue()) {
            return;
        }
        if (this.zzbja.optBoolean("custom_one_point_five_click_enabled", false)) {
            Object obj = this.zzbiz;
            if (view != null) {
                view.setOnClickListener(obj);
                view.setClickable(true);
                obj.zzbhq = new WeakReference(view);
                return;
            }
            return;
        }
        zzane.zzdk("Your account need to be whitelisted to use this feature.\nContact your account manager for more information.");
    }

    @Nullable
    public View zza(OnClickListener onClickListener, boolean z) {
        zzoj zzkc = this.zzbjb.zzkc();
        if (zzkc == null) {
            return null;
        }
        LayoutParams layoutParams = new LayoutParams(-2, -2);
        if (!z) {
            switch (zzkc.zzju()) {
                case 0:
                    layoutParams.addRule(10);
                    layoutParams.addRule(9);
                    break;
                case 2:
                    layoutParams.addRule(12);
                    layoutParams.addRule(11);
                    break;
                case 3:
                    layoutParams.addRule(12);
                    layoutParams.addRule(9);
                    break;
                default:
                    layoutParams.addRule(10);
                    layoutParams.addRule(11);
                    break;
            }
        }
        View com_google_android_gms_internal_ads_zzom = new zzom(this.mContext, zzkc, layoutParams);
        com_google_android_gms_internal_ads_zzom.setOnClickListener(onClickListener);
        com_google_android_gms_internal_ads_zzom.setContentDescription((CharSequence) zzkb.zzik().zzd(zznk.zzbbz));
        return com_google_android_gms_internal_ads_zzom;
    }

    public final void zza(View view, zzox com_google_android_gms_internal_ads_zzox) {
        if (!zzb(view, com_google_android_gms_internal_ads_zzox)) {
            ViewGroup.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
            ((FrameLayout) view).removeAllViews();
            if (this.zzbjb instanceof zzpc) {
                zzpc com_google_android_gms_internal_ads_zzpc = (zzpc) this.zzbjb;
                if (com_google_android_gms_internal_ads_zzpc.getImages() != null && com_google_android_gms_internal_ads_zzpc.getImages().size() > 0) {
                    Object obj = com_google_android_gms_internal_ads_zzpc.getImages().get(0);
                    zzpw zzh = obj instanceof IBinder ? zzpx.zzh((IBinder) obj) : null;
                    if (zzh != null) {
                        try {
                            IObjectWrapper zzjy = zzh.zzjy();
                            if (zzjy != null) {
                                Drawable drawable = (Drawable) ObjectWrapper.unwrap(zzjy);
                                View imageView = new ImageView(this.mContext);
                                imageView.setImageDrawable(drawable);
                                imageView.setScaleType(ScaleType.CENTER_INSIDE);
                                ((FrameLayout) view).addView(imageView, layoutParams);
                            }
                        } catch (RemoteException e) {
                            zzane.zzdk("Could not get drawable from image");
                        }
                    }
                }
            }
        }
    }

    public final void zza(View view, String str, @Nullable Bundle bundle, Map<String, WeakReference<View>> map, View view2) {
        JSONObject jSONObject;
        Throwable e;
        JSONObject zza = zza((Map) map, view2);
        JSONObject zzo = zzo(view2);
        JSONObject zzp = zzp(view2);
        JSONObject zzq = zzq(view2);
        try {
            JSONObject zza2 = zzbv.zzek().zza(bundle, null);
            jSONObject = new JSONObject();
            try {
                jSONObject.put("click_point", zza2);
                jSONObject.put("asset_id", str);
            } catch (Exception e2) {
                e = e2;
                zzane.zzb("Error occurred while grabbing click signals.", e);
                zza(view, zzo, zza, zzp, zzq, str, jSONObject, null);
            }
        } catch (Exception e3) {
            e = e3;
            jSONObject = null;
            zzane.zzb("Error occurred while grabbing click signals.", e);
            zza(view, zzo, zza, zzp, zzq, str, jSONObject, null);
        }
        zza(view, zzo, zza, zzp, zzq, str, jSONObject, null);
    }

    public void zza(View view, Map<String, WeakReference<View>> map) {
        zza(zzo(view), zza((Map) map, view), zzp(view), zzq(view), null);
    }

    public void zza(View view, Map<String, WeakReference<View>> map, Bundle bundle, View view2) {
        Preconditions.checkMainThread("Invalid call from a non-UI thread.");
        if (map != null) {
            synchronized (map) {
                for (Entry entry : map.entrySet()) {
                    if (view.equals((View) ((WeakReference) entry.getValue()).get())) {
                        zza(view, (String) entry.getKey(), bundle, (Map) map, view2);
                        return;
                    }
                }
            }
        }
        if ("6".equals(this.zzbjb.zzkb())) {
            zza(view, "3099", bundle, (Map) map, view2);
        } else if ("2".equals(this.zzbjb.zzkb())) {
            zza(view, "2099", bundle, (Map) map, view2);
        } else if (SchemaSymbols.ATTVAL_TRUE_1.equals(this.zzbjb.zzkb())) {
            zza(view, "1099", bundle, (Map) map, view2);
        }
    }

    public void zza(View view, @Nullable Map<String, WeakReference<View>> map, @Nullable Map<String, WeakReference<View>> map2, OnTouchListener onTouchListener, OnClickListener onClickListener) {
        if (((Boolean) zzkb.zzik().zzd(zznk.zzbbw)).booleanValue()) {
            View view2;
            view.setOnTouchListener(onTouchListener);
            view.setClickable(true);
            view.setOnClickListener(onClickListener);
            if (map != null) {
                synchronized (map) {
                    for (Entry value : map.entrySet()) {
                        view2 = (View) ((WeakReference) value.getValue()).get();
                        if (view2 != null) {
                            view2.setOnTouchListener(onTouchListener);
                            view2.setClickable(true);
                            view2.setOnClickListener(onClickListener);
                        }
                    }
                }
            }
            if (map2 != null) {
                synchronized (map2) {
                    for (Entry value2 : map2.entrySet()) {
                        view2 = (View) ((WeakReference) value2.getValue()).get();
                        if (view2 != null) {
                            view2.setOnTouchListener(onTouchListener);
                        }
                    }
                }
            }
        }
    }

    public void zza(zzro com_google_android_gms_internal_ads_zzro) {
        if (!((Boolean) zzkb.zzik().zzd(zznk.zzbcf)).booleanValue()) {
            return;
        }
        if (this.zzbja.optBoolean("custom_one_point_five_click_enabled", false)) {
            this.zzbiz.zza(com_google_android_gms_internal_ads_zzro);
        } else {
            zzane.zzdk("Your account need to be whitelisted to use this feature.\nContact your account manager for more information.");
        }
    }

    public void zzb(View view, Map<String, WeakReference<View>> map) {
        if (!((Boolean) zzkb.zzik().zzd(zznk.zzbbv)).booleanValue()) {
            view.setOnTouchListener(null);
            view.setClickable(false);
            view.setOnClickListener(null);
            if (map != null) {
                synchronized (map) {
                    for (Entry value : map.entrySet()) {
                        View view2 = (View) ((WeakReference) value.getValue()).get();
                        if (view2 != null) {
                            view2.setOnTouchListener(null);
                            view2.setClickable(false);
                            view2.setOnClickListener(null);
                        }
                    }
                }
            }
        }
    }

    public final boolean zzb(View view, zzox com_google_android_gms_internal_ads_zzox) {
        ViewGroup.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2, 17);
        View zzkd = this.zzbjb.zzkd();
        if (zzkd == null) {
            return false;
        }
        ViewParent parent = zzkd.getParent();
        if (parent instanceof ViewGroup) {
            ((ViewGroup) parent).removeView(zzkd);
        }
        ((FrameLayout) view).removeAllViews();
        ((FrameLayout) view).addView(zzkd, layoutParams);
        this.zzbiw.zza(com_google_android_gms_internal_ads_zzox);
        return true;
    }

    public final void zzc(View view, Map<String, WeakReference<View>> map) {
        synchronized (this.mLock) {
            if (this.zzbjd) {
            } else if (zzm(view)) {
                zza(view, (Map) map);
            } else {
                if (((Boolean) zzkb.zzik().zzd(zznk.zzbce)).booleanValue() && map != null) {
                    synchronized (map) {
                        for (Entry value : map.entrySet()) {
                            View view2 = (View) ((WeakReference) value.getValue()).get();
                            if (view2 != null && zzm(view2)) {
                                zza(view, (Map) map);
                                return;
                            }
                        }
                    }
                }
            }
        }
    }

    public void zzcr() {
        this.zzbiw.zzcr();
    }

    public void zzcs() {
        this.zzbiw.zzcs();
    }

    public final void zzd(MotionEvent motionEvent) {
        this.zzbjc.zza(motionEvent);
    }

    public final void zzf(Map<String, WeakReference<View>> map) {
        if (this.zzbjb.zzkd() == null) {
            return;
        }
        if ("2".equals(this.zzbjb.zzkb())) {
            zzbv.zzeo().zzqh().zza(this.zzbiw.getAdUnitId(), this.zzbjb.zzkb(), map.containsKey(NativeAppInstallAd.ASSET_MEDIA_VIDEO));
        } else if (SchemaSymbols.ATTVAL_TRUE_1.equals(this.zzbjb.zzkb())) {
            zzbv.zzeo().zzqh().zza(this.zzbiw.getAdUnitId(), this.zzbjb.zzkb(), map.containsKey(NativeContentAd.ASSET_MEDIA_VIDEO));
        }
    }

    public final void zzi(View view) {
        this.zzbiw.zzi(view);
    }

    public final void zzj(View view) {
        if (((Boolean) zzkb.zzik().zzd(zznk.zzbat)).booleanValue() && this.zzbjc != null) {
            zzce zzaa = this.zzbjc.zzaa();
            if (zzaa != null) {
                zzaa.zzb(view);
            }
        }
    }

    public boolean zzkj() {
        zzoj zzkc = this.zzbjb.zzkc();
        return zzkc != null && zzkc.zzjv();
    }

    public boolean zzkk() {
        return this.zzbja != null && this.zzbja.optBoolean("allow_pub_owned_ad_view", false);
    }

    public void zzkl() {
        Preconditions.checkMainThread("Invalid call from a non-UI thread.");
        if (!this.zzbje) {
            this.zzbje = true;
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("ad", this.zzbja);
                jSONObject.put("ads_id", this.zzaae);
                zzanm.zza(this.zzaad.zzk(jSONObject), "NativeAdEngineImpl.recordDownloadedImpression");
            } catch (Throwable e) {
                zzane.zzb("", e);
            }
        }
    }

    public zzaqw zzko() throws zzarg {
        zzaqw com_google_android_gms_internal_ads_zzaqw = null;
        if (!(this.zzbja == null || this.zzbja.optJSONObject("overlay") == null)) {
            zzbv.zzel();
            Context context = this.mContext;
            zzjn zzf = zzjn.zzf(this.mContext);
            boolean z = false;
            com_google_android_gms_internal_ads_zzaqw = zzarc.zza(context, zzasi.zzb(zzf), zzf.zzarb, false, z, this.zzbjc, this.zzyf, null, null, null, zzhs.zzhm());
            if (com_google_android_gms_internal_ads_zzaqw != null) {
                com_google_android_gms_internal_ads_zzaqw.getView().setVisibility(8);
                new zzpf(com_google_android_gms_internal_ads_zzaqw).zza(this.zzaad);
            }
        }
        return com_google_android_gms_internal_ads_zzaqw;
    }

    public void zzkp() {
        this.zzaad.zzmc();
    }

    public void zzkq() {
        this.zzbiw.zzct();
    }

    public final View zzkr() {
        return this.zzbjf != null ? (View) this.zzbjf.get() : null;
    }

    @Nullable
    public final zzaix zzks() {
        if (!zzbv.zzfh().zzu(this.mContext)) {
            return null;
        }
        if (this.zzyv == null) {
            this.zzyv = new zzaix(this.mContext, this.zzbiw.getAdUnitId());
        }
        return this.zzyv;
    }

    public final void zzl(View view) {
        this.zzbjf = new WeakReference(view);
    }
}
