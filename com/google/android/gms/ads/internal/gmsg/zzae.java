package com.google.android.gms.ads.internal.gmsg;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import com.bgjd.ici.p025b.C1408j.C1404b;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.zzakk;
import com.google.android.gms.internal.ads.zzci;
import com.google.android.gms.internal.ads.zzkb;
import com.google.android.gms.internal.ads.zznk;
import com.mobfox.sdk.networking.RequestParams;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@VisibleForTesting
public final class zzae {
    private final Context mContext;
    private final View mView;
    private final zzci zzbne;

    public zzae(Context context, zzci com_google_android_gms_internal_ads_zzci, View view) {
        this.mContext = context;
        this.zzbne = com_google_android_gms_internal_ads_zzci;
        this.mView = view;
    }

    private static Intent zza(Intent intent, ResolveInfo resolveInfo) {
        Intent intent2 = new Intent(intent);
        intent2.setClassName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name);
        return intent2;
    }

    @VisibleForTesting
    private final ResolveInfo zza(Intent intent, ArrayList<ResolveInfo> arrayList) {
        ResolveInfo resolveInfo;
        Throwable th;
        try {
            PackageManager packageManager = this.mContext.getPackageManager();
            if (packageManager == null) {
                return null;
            }
            Collection queryIntentActivities = packageManager.queryIntentActivities(intent, 65536);
            ResolveInfo resolveActivity = packageManager.resolveActivity(intent, 65536);
            if (!(queryIntentActivities == null || resolveActivity == null)) {
                for (int i = 0; i < queryIntentActivities.size(); i++) {
                    resolveInfo = (ResolveInfo) queryIntentActivities.get(i);
                    if (resolveActivity != null && resolveActivity.activityInfo.name.equals(resolveInfo.activityInfo.name)) {
                        resolveInfo = resolveActivity;
                        break;
                    }
                }
            }
            resolveInfo = null;
            try {
                arrayList.addAll(queryIntentActivities);
                return resolveInfo;
            } catch (Throwable th2) {
                th = th2;
                zzbv.zzeo().zza(th, "OpenSystemBrowserHandler.getDefaultBrowserResolverForIntent");
                return resolveInfo;
            }
        } catch (Throwable th3) {
            Throwable th4 = th3;
            resolveInfo = null;
            th = th4;
            zzbv.zzeo().zza(th, "OpenSystemBrowserHandler.getDefaultBrowserResolverForIntent");
            return resolveInfo;
        }
    }

    @VisibleForTesting
    private final ResolveInfo zzb(Intent intent) {
        return zza(intent, new ArrayList());
    }

    private static Intent zze(Uri uri) {
        if (uri == null) {
            return null;
        }
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(ErrorDialogData.BINDER_CRASH);
        intent.setData(uri);
        intent.setAction("android.intent.action.VIEW");
        return intent;
    }

    @VisibleForTesting
    public final Intent zzi(Map<String, String> map) {
        Uri uri = null;
        ActivityManager activityManager = (ActivityManager) this.mContext.getSystemService(C1404b.aw);
        String str = (String) map.get(RequestParams.f9038U);
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        int i;
        ArrayList arrayList;
        Intent zze;
        Intent zze2;
        ResolveInfo zza;
        List<RunningAppProcessInfo> runningAppProcesses;
        ArrayList arrayList2;
        int size;
        int i2;
        Uri parse = Uri.parse(zzad.zza(this.mContext, this.zzbne, str, this.mView, null));
        boolean parseBoolean = Boolean.parseBoolean((String) map.get("use_first_package"));
        boolean parseBoolean2 = Boolean.parseBoolean((String) map.get("use_running_process"));
        if (!Boolean.parseBoolean((String) map.get("use_custom_tabs"))) {
            if (!((Boolean) zzkb.zzik().zzd(zznk.zzbdz)).booleanValue()) {
                i = 0;
                if ("http".equalsIgnoreCase(parse.getScheme())) {
                    uri = parse.buildUpon().scheme("https").build();
                } else if ("https".equalsIgnoreCase(parse.getScheme())) {
                    uri = parse.buildUpon().scheme("http").build();
                }
                arrayList = new ArrayList();
                zze = zze(parse);
                zze2 = zze(uri);
                if (i != 0) {
                    zzbv.zzek();
                    zzakk.zzb(this.mContext, zze);
                    zzbv.zzek();
                    zzakk.zzb(this.mContext, zze2);
                }
                zza = zza(zze, arrayList);
                if (zza != null) {
                    return zza(zze, zza);
                }
                if (zze2 != null) {
                    zza = zzb(zze2);
                    if (zza != null) {
                        zze2 = zza(zze, zza);
                        if (zzb(zze2) != null) {
                            return zze2;
                        }
                    }
                }
                if (arrayList.size() == 0) {
                    return zze;
                }
                if (parseBoolean2 && activityManager != null) {
                    runningAppProcesses = activityManager.getRunningAppProcesses();
                    if (runningAppProcesses != null) {
                        arrayList2 = arrayList;
                        size = arrayList2.size();
                        i2 = 0;
                        while (i2 < size) {
                            int i3 = i2 + 1;
                            zza = (ResolveInfo) arrayList2.get(i2);
                            for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                                if (runningAppProcessInfo.processName.equals(zza.activityInfo.packageName)) {
                                    return zza(zze, zza);
                                }
                            }
                            i2 = i3;
                        }
                    }
                }
                return parseBoolean ? zza(zze, (ResolveInfo) arrayList.get(0)) : zze;
            }
        }
        i = 1;
        if ("http".equalsIgnoreCase(parse.getScheme())) {
            uri = parse.buildUpon().scheme("https").build();
        } else if ("https".equalsIgnoreCase(parse.getScheme())) {
            uri = parse.buildUpon().scheme("http").build();
        }
        arrayList = new ArrayList();
        zze = zze(parse);
        zze2 = zze(uri);
        if (i != 0) {
            zzbv.zzek();
            zzakk.zzb(this.mContext, zze);
            zzbv.zzek();
            zzakk.zzb(this.mContext, zze2);
        }
        zza = zza(zze, arrayList);
        if (zza != null) {
            return zza(zze, zza);
        }
        if (zze2 != null) {
            zza = zzb(zze2);
            if (zza != null) {
                zze2 = zza(zze, zza);
                if (zzb(zze2) != null) {
                    return zze2;
                }
            }
        }
        if (arrayList.size() == 0) {
            return zze;
        }
        runningAppProcesses = activityManager.getRunningAppProcesses();
        if (runningAppProcesses != null) {
            arrayList2 = arrayList;
            size = arrayList2.size();
            i2 = 0;
            while (i2 < size) {
                int i32 = i2 + 1;
                zza = (ResolveInfo) arrayList2.get(i2);
                while (r10.hasNext()) {
                    if (runningAppProcessInfo.processName.equals(zza.activityInfo.packageName)) {
                        return zza(zze, zza);
                    }
                }
                i2 = i32;
            }
        }
        if (parseBoolean) {
        }
    }
}
