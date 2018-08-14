package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.DeviceProperties;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.wrappers.Wrappers;
import com.google.firebase.analytics.FirebaseAnalytics$Param;
import java.util.Locale;

public final class zzagb {
    private float zzagu;
    private int zzcde;
    private int zzcdf;
    private int zzcjk;
    private boolean zzcjl;
    private boolean zzcjm;
    private String zzcjn;
    private String zzcjo;
    private boolean zzcjp;
    private boolean zzcjq;
    private boolean zzcjr;
    private boolean zzcjs;
    private String zzcjt;
    private String zzcju;
    private String zzcjv;
    private int zzcjw;
    private int zzcjx;
    private int zzcjy;
    private int zzcjz;
    private int zzcka;
    private int zzckb;
    private double zzckc;
    private boolean zzckd;
    private boolean zzcke;
    private int zzckf;
    private String zzckg;
    private String zzckh;
    private boolean zzcki;

    public zzagb(Context context) {
        boolean z = true;
        PackageManager packageManager = context.getPackageManager();
        zzn(context);
        zzo(context);
        zzp(context);
        Locale locale = Locale.getDefault();
        this.zzcjl = zza(packageManager, "geo:0,0?q=donuts") != null;
        if (zza(packageManager, "http://www.google.com") == null) {
            z = false;
        }
        this.zzcjm = z;
        this.zzcjo = locale.getCountry();
        zzkb.zzif();
        this.zzcjp = zzamu.zzsg();
        this.zzcjq = DeviceProperties.isSidewinder(context);
        this.zzcjt = locale.getLanguage();
        this.zzcju = zzb(context, packageManager);
        this.zzcjv = zza(context, packageManager);
        Resources resources = context.getResources();
        if (resources != null) {
            DisplayMetrics displayMetrics = resources.getDisplayMetrics();
            if (displayMetrics != null) {
                this.zzagu = displayMetrics.density;
                this.zzcde = displayMetrics.widthPixels;
                this.zzcdf = displayMetrics.heightPixels;
            }
        }
    }

    public zzagb(Context context, zzaga com_google_android_gms_internal_ads_zzaga) {
        context.getPackageManager();
        zzn(context);
        zzo(context);
        zzp(context);
        this.zzckg = Build.FINGERPRINT;
        this.zzckh = Build.DEVICE;
        boolean z = PlatformVersion.isAtLeastIceCreamSandwichMR1() && zzoh.zzh(context);
        this.zzcki = z;
        this.zzcjl = com_google_android_gms_internal_ads_zzaga.zzcjl;
        this.zzcjm = com_google_android_gms_internal_ads_zzaga.zzcjm;
        this.zzcjo = com_google_android_gms_internal_ads_zzaga.zzcjo;
        this.zzcjp = com_google_android_gms_internal_ads_zzaga.zzcjp;
        this.zzcjq = com_google_android_gms_internal_ads_zzaga.zzcjq;
        this.zzcjt = com_google_android_gms_internal_ads_zzaga.zzcjt;
        this.zzcju = com_google_android_gms_internal_ads_zzaga.zzcju;
        this.zzcjv = com_google_android_gms_internal_ads_zzaga.zzcjv;
        this.zzagu = com_google_android_gms_internal_ads_zzaga.zzagu;
        this.zzcde = com_google_android_gms_internal_ads_zzaga.zzcde;
        this.zzcdf = com_google_android_gms_internal_ads_zzaga.zzcdf;
    }

    private static ResolveInfo zza(PackageManager packageManager, String str) {
        try {
            return packageManager.resolveActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)), 65536);
        } catch (Throwable th) {
            zzbv.zzeo().zza(th, "DeviceInfo.getResolveInfo");
            return null;
        }
    }

    private static String zza(Context context, PackageManager packageManager) {
        String str = null;
        try {
            PackageInfo packageInfo = Wrappers.packageManager(context).getPackageInfo("com.android.vending", 128);
            if (packageInfo != null) {
                int i = packageInfo.versionCode;
                String str2 = packageInfo.packageName;
                str = new StringBuilder(String.valueOf(str2).length() + 12).append(i).append(".").append(str2).toString();
            }
        } catch (Exception e) {
        }
        return str;
    }

    private static String zzb(Context context, PackageManager packageManager) {
        String str = null;
        ResolveInfo zza = zza(packageManager, "market://details?id=com.google.android.gms.ads");
        if (zza != null) {
            ActivityInfo activityInfo = zza.activityInfo;
            if (activityInfo != null) {
                try {
                    PackageInfo packageInfo = Wrappers.packageManager(context).getPackageInfo(activityInfo.packageName, 0);
                    if (packageInfo != null) {
                        int i = packageInfo.versionCode;
                        String str2 = activityInfo.packageName;
                        str = new StringBuilder(String.valueOf(str2).length() + 12).append(i).append(".").append(str2).toString();
                    }
                } catch (NameNotFoundException e) {
                }
            }
        }
        return str;
    }

    private final void zzn(Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService("audio");
        if (audioManager != null) {
            try {
                this.zzcjk = audioManager.getMode();
                this.zzcjr = audioManager.isMusicActive();
                this.zzcjs = audioManager.isSpeakerphoneOn();
                this.zzcjw = audioManager.getStreamVolume(3);
                this.zzcka = audioManager.getRingerMode();
                this.zzckb = audioManager.getStreamVolume(2);
                return;
            } catch (Throwable th) {
                zzbv.zzeo().zza(th, "DeviceInfo.gatherAudioInfo");
            }
        }
        this.zzcjk = -2;
        this.zzcjr = false;
        this.zzcjs = false;
        this.zzcjw = 0;
        this.zzcka = 0;
        this.zzckb = 0;
    }

    @TargetApi(16)
    private final void zzo(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        this.zzcjn = telephonyManager.getNetworkOperator();
        this.zzcjy = telephonyManager.getNetworkType();
        this.zzcjz = telephonyManager.getPhoneType();
        this.zzcjx = -2;
        this.zzcke = false;
        this.zzckf = -1;
        zzbv.zzek();
        if (zzakk.zzl(context, "android.permission.ACCESS_NETWORK_STATE")) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                this.zzcjx = activeNetworkInfo.getType();
                this.zzckf = activeNetworkInfo.getDetailedState().ordinal();
            } else {
                this.zzcjx = -1;
            }
            if (VERSION.SDK_INT >= 16) {
                this.zzcke = connectivityManager.isActiveNetworkMetered();
            }
        }
    }

    private final void zzp(Context context) {
        boolean z = false;
        Intent registerReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (registerReceiver != null) {
            int intExtra = registerReceiver.getIntExtra("status", -1);
            this.zzckc = (double) (((float) registerReceiver.getIntExtra(FirebaseAnalytics$Param.LEVEL, -1)) / ((float) registerReceiver.getIntExtra("scale", -1)));
            if (intExtra == 2 || intExtra == 5) {
                z = true;
            }
            this.zzckd = z;
            return;
        }
        this.zzckc = -1.0d;
        this.zzckd = false;
    }

    public final zzaga zzoo() {
        return new zzaga(this.zzcjk, this.zzcjl, this.zzcjm, this.zzcjn, this.zzcjo, this.zzcjp, this.zzcjq, this.zzcjr, this.zzcjs, this.zzcjt, this.zzcju, this.zzcjv, this.zzcjw, this.zzcjx, this.zzcjy, this.zzcjz, this.zzcka, this.zzckb, this.zzagu, this.zzcde, this.zzcdf, this.zzckc, this.zzckd, this.zzcke, this.zzckf, this.zzckg, this.zzcki, this.zzckh);
    }
}
