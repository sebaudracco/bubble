package com.google.android.gms.ads.internal;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.view.View.OnClickListener;
import com.facebook.ads.AudienceNetworkActivity;
import com.google.android.gms.ads.internal.gmsg.zzv;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzajh;
import com.google.android.gms.internal.ads.zzane;
import com.google.android.gms.internal.ads.zzaqw;
import com.google.android.gms.internal.ads.zzoo;
import com.google.android.gms.internal.ads.zzoq;
import com.google.android.gms.internal.ads.zzpw;
import com.google.android.gms.internal.ads.zzpx;
import com.google.android.gms.internal.ads.zzxe;
import com.google.android.gms.internal.ads.zzxz;
import com.google.android.gms.internal.ads.zzyc;
import com.google.firebase.analytics.FirebaseAnalytics$Param;
import com.silvermob.sdk.Const.BannerType;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import javax.annotation.ParametersAreNonnullByDefault;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzadh
@ParametersAreNonnullByDefault
public final class zzas {
    @VisibleForTesting
    static zzv<zzaqw> zza(@Nullable zzxz com_google_android_gms_internal_ads_zzxz, @Nullable zzyc com_google_android_gms_internal_ads_zzyc, zzac com_google_android_gms_ads_internal_zzac) {
        return new zzax(com_google_android_gms_internal_ads_zzxz, com_google_android_gms_ads_internal_zzac, com_google_android_gms_internal_ads_zzyc);
    }

    private static String zza(@Nullable Bitmap bitmap) {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (bitmap == null) {
            zzane.zzdk("Bitmap is null. Returning empty string");
            return "";
        }
        bitmap.compress(CompressFormat.PNG, 100, byteArrayOutputStream);
        String encodeToString = Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
        String valueOf = String.valueOf("data:image/png;base64,");
        encodeToString = String.valueOf(encodeToString);
        return encodeToString.length() != 0 ? valueOf.concat(encodeToString) : new String(valueOf);
    }

    @VisibleForTesting
    private static String zza(@Nullable zzpw com_google_android_gms_internal_ads_zzpw) {
        if (com_google_android_gms_internal_ads_zzpw == null) {
            zzane.zzdk("Image is null. Returning empty string");
            return "";
        }
        try {
            Uri uri = com_google_android_gms_internal_ads_zzpw.getUri();
            if (uri != null) {
                return uri.toString();
            }
        } catch (RemoteException e) {
            zzane.zzdk("Unable to get image uri. Trying data uri next");
        }
        return zzb(com_google_android_gms_internal_ads_zzpw);
    }

    private static JSONObject zza(@Nullable Bundle bundle, String str) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (bundle == null || TextUtils.isEmpty(str)) {
            return jSONObject;
        }
        JSONObject jSONObject2 = new JSONObject(str);
        Iterator keys = jSONObject2.keys();
        while (keys.hasNext()) {
            String str2 = (String) keys.next();
            if (bundle.containsKey(str2)) {
                if (BannerType.IMAGE.equals(jSONObject2.getString(str2))) {
                    Object obj = bundle.get(str2);
                    if (obj instanceof Bitmap) {
                        jSONObject.put(str2, zza((Bitmap) obj));
                    } else {
                        zzane.zzdk("Invalid type. An image type extra should return a bitmap");
                    }
                } else if (bundle.get(str2) instanceof Bitmap) {
                    zzane.zzdk("Invalid asset type. Bitmap should be returned only for image type");
                } else {
                    jSONObject.put(str2, String.valueOf(bundle.get(str2)));
                }
            }
        }
        return jSONObject;
    }

    static final /* synthetic */ void zza(zzoo com_google_android_gms_internal_ads_zzoo, String str, zzaqw com_google_android_gms_internal_ads_zzaqw, boolean z) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("headline", com_google_android_gms_internal_ads_zzoo.getHeadline());
            jSONObject.put("body", com_google_android_gms_internal_ads_zzoo.getBody());
            jSONObject.put("call_to_action", com_google_android_gms_internal_ads_zzoo.getCallToAction());
            jSONObject.put(FirebaseAnalytics$Param.PRICE, com_google_android_gms_internal_ads_zzoo.getPrice());
            jSONObject.put("star_rating", String.valueOf(com_google_android_gms_internal_ads_zzoo.getStarRating()));
            jSONObject.put("store", com_google_android_gms_internal_ads_zzoo.getStore());
            jSONObject.put("icon", zza(com_google_android_gms_internal_ads_zzoo.zzjz()));
            JSONArray jSONArray = new JSONArray();
            List<Object> images = com_google_android_gms_internal_ads_zzoo.getImages();
            if (images != null) {
                for (Object zzd : images) {
                    jSONArray.put(zza(zzd(zzd)));
                }
            }
            jSONObject.put("images", jSONArray);
            jSONObject.put("extras", zza(com_google_android_gms_internal_ads_zzoo.getExtras(), str));
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("assets", jSONObject);
            jSONObject2.put("template_id", "2");
            com_google_android_gms_internal_ads_zzaqw.zzb("google.afma.nativeExpressAds.loadAssets", jSONObject2);
        } catch (Throwable e) {
            zzane.zzc("Exception occurred when loading assets", e);
        }
    }

    static final /* synthetic */ void zza(zzoq com_google_android_gms_internal_ads_zzoq, String str, zzaqw com_google_android_gms_internal_ads_zzaqw, boolean z) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("headline", com_google_android_gms_internal_ads_zzoq.getHeadline());
            jSONObject.put("body", com_google_android_gms_internal_ads_zzoq.getBody());
            jSONObject.put("call_to_action", com_google_android_gms_internal_ads_zzoq.getCallToAction());
            jSONObject.put("advertiser", com_google_android_gms_internal_ads_zzoq.getAdvertiser());
            jSONObject.put("logo", zza(com_google_android_gms_internal_ads_zzoq.zzkg()));
            JSONArray jSONArray = new JSONArray();
            List<Object> images = com_google_android_gms_internal_ads_zzoq.getImages();
            if (images != null) {
                for (Object zzd : images) {
                    jSONArray.put(zza(zzd(zzd)));
                }
            }
            jSONObject.put("images", jSONArray);
            jSONObject.put("extras", zza(com_google_android_gms_internal_ads_zzoq.getExtras(), str));
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("assets", jSONObject);
            jSONObject2.put("template_id", SchemaSymbols.ATTVAL_TRUE_1);
            com_google_android_gms_internal_ads_zzaqw.zzb("google.afma.nativeExpressAds.loadAssets", jSONObject2);
        } catch (Throwable e) {
            zzane.zzc("Exception occurred when loading assets", e);
        }
    }

    public static boolean zza(zzaqw com_google_android_gms_internal_ads_zzaqw, zzxe com_google_android_gms_internal_ads_zzxe, CountDownLatch countDownLatch) {
        boolean z;
        try {
            View view = com_google_android_gms_internal_ads_zzaqw.getView();
            if (view == null) {
                zzane.zzdk("AdWebView is null");
                z = false;
            } else {
                view.setVisibility(4);
                List list = com_google_android_gms_internal_ads_zzxe.zzbtw.zzbsi;
                if (list == null || list.isEmpty()) {
                    zzane.zzdk("No template ids present in mediation response");
                    z = false;
                } else {
                    com_google_android_gms_internal_ads_zzaqw.zza("/nativeExpressAssetsLoaded", new zzav(countDownLatch));
                    com_google_android_gms_internal_ads_zzaqw.zza("/nativeExpressAssetsLoadingFailed", new zzaw(countDownLatch));
                    zzxz zzmo = com_google_android_gms_internal_ads_zzxe.zzbtx.zzmo();
                    zzyc zzmp = com_google_android_gms_internal_ads_zzxe.zzbtx.zzmp();
                    if (list.contains("2") && zzmo != null) {
                        com_google_android_gms_internal_ads_zzaqw.zzuf().zza(new zzat(new zzoo(zzmo.getHeadline(), zzmo.getImages(), zzmo.getBody(), zzmo.zzjz(), zzmo.getCallToAction(), zzmo.getStarRating(), zzmo.getStore(), zzmo.getPrice(), null, zzmo.getExtras(), null, zzmo.zzmw() != null ? (View) ObjectWrapper.unwrap(zzmo.zzmw()) : null, zzmo.zzke(), null), com_google_android_gms_internal_ads_zzxe.zzbtw.zzbsh, com_google_android_gms_internal_ads_zzaqw));
                    } else if (!list.contains(SchemaSymbols.ATTVAL_TRUE_1) || zzmp == null) {
                        zzane.zzdk("No matching template id and mapper");
                        z = false;
                    } else {
                        com_google_android_gms_internal_ads_zzaqw.zzuf().zza(new zzau(new zzoq(zzmp.getHeadline(), zzmp.getImages(), zzmp.getBody(), zzmp.zzkg(), zzmp.getCallToAction(), zzmp.getAdvertiser(), null, zzmp.getExtras(), null, zzmp.zzmw() != null ? (View) ObjectWrapper.unwrap(zzmp.zzmw()) : null, zzmp.zzke(), null), com_google_android_gms_internal_ads_zzxe.zzbtw.zzbsh, com_google_android_gms_internal_ads_zzaqw));
                    }
                    String str = com_google_android_gms_internal_ads_zzxe.zzbtw.zzbsf;
                    String str2 = com_google_android_gms_internal_ads_zzxe.zzbtw.zzbsg;
                    if (str2 != null) {
                        com_google_android_gms_internal_ads_zzaqw.loadDataWithBaseURL(str2, str, AudienceNetworkActivity.WEBVIEW_MIME_TYPE, "UTF-8", null);
                    } else {
                        com_google_android_gms_internal_ads_zzaqw.loadData(str, AudienceNetworkActivity.WEBVIEW_MIME_TYPE, "UTF-8");
                    }
                    z = true;
                }
            }
        } catch (Throwable e) {
            zzane.zzc("Unable to invoke load assets", e);
            z = false;
        } catch (RuntimeException e2) {
            countDownLatch.countDown();
            throw e2;
        }
        if (!z) {
            countDownLatch.countDown();
        }
        return z;
    }

    private static String zzb(zzpw com_google_android_gms_internal_ads_zzpw) {
        try {
            IObjectWrapper zzjy = com_google_android_gms_internal_ads_zzpw.zzjy();
            if (zzjy == null) {
                zzane.zzdk("Drawable is null. Returning empty string");
                return "";
            }
            Drawable drawable = (Drawable) ObjectWrapper.unwrap(zzjy);
            if (drawable instanceof BitmapDrawable) {
                return zza(((BitmapDrawable) drawable).getBitmap());
            }
            zzane.zzdk("Drawable is not an instance of BitmapDrawable. Returning empty string");
            return "";
        } catch (RemoteException e) {
            zzane.zzdk("Unable to get drawable. Returning empty string");
            return "";
        }
    }

    @Nullable
    private static zzpw zzd(Object obj) {
        return obj instanceof IBinder ? zzpx.zzh((IBinder) obj) : null;
    }

    private static void zzd(zzaqw com_google_android_gms_internal_ads_zzaqw) {
        OnClickListener onClickListener = com_google_android_gms_internal_ads_zzaqw.getOnClickListener();
        if (onClickListener != null) {
            onClickListener.onClick(com_google_android_gms_internal_ads_zzaqw.getView());
        }
    }

    @Nullable
    public static View zze(@Nullable zzajh com_google_android_gms_internal_ads_zzajh) {
        if (com_google_android_gms_internal_ads_zzajh == null) {
            zzane.m3427e("AdState is null");
            return null;
        } else if (zzf(com_google_android_gms_internal_ads_zzajh) && com_google_android_gms_internal_ads_zzajh.zzbyo != null) {
            return com_google_android_gms_internal_ads_zzajh.zzbyo.getView();
        } else {
            try {
                IObjectWrapper view = com_google_android_gms_internal_ads_zzajh.zzbtx != null ? com_google_android_gms_internal_ads_zzajh.zzbtx.getView() : null;
                if (view != null) {
                    return (View) ObjectWrapper.unwrap(view);
                }
                zzane.zzdk("View in mediation adapter is null.");
                return null;
            } catch (Throwable e) {
                zzane.zzc("Could not get View from mediation adapter.", e);
                return null;
            }
        }
    }

    public static boolean zzf(@Nullable zzajh com_google_android_gms_internal_ads_zzajh) {
        return (com_google_android_gms_internal_ads_zzajh == null || !com_google_android_gms_internal_ads_zzajh.zzceq || com_google_android_gms_internal_ads_zzajh.zzbtw == null || com_google_android_gms_internal_ads_zzajh.zzbtw.zzbsf == null) ? false : true;
    }
}
