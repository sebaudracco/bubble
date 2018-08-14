package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.AlertDialog.Builder;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Debug;
import android.os.Debug.MemoryInfo;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.os.PowerManager;
import android.os.Process;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.PopupWindow;
import com.bgjd.ici.p025b.C1408j.C1404b;
import com.google.android.gms.ads.AdActivity;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.CrashUtils;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import com.google.android.gms.common.util.IOUtils;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.wrappers.Wrappers;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.regex.Pattern;
import javax.annotation.concurrent.GuardedBy;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzadh
public final class zzakk {
    public static final Handler zzcrm = new zzakc(Looper.getMainLooper());
    private final Object mLock = new Object();
    @GuardedBy("mLock")
    private String zzcpq;
    private boolean zzcrn = true;
    private boolean zzcro = false;
    private boolean zzcrp = false;
    @GuardedBy("this")
    private Pattern zzcrq;
    @GuardedBy("this")
    private Pattern zzcrr;

    @VisibleForTesting
    public static Bundle zza(zzgk com_google_android_gms_internal_ads_zzgk) {
        if (com_google_android_gms_internal_ads_zzgk == null) {
            return null;
        }
        if (!((Boolean) zzkb.zzik().zzd(zznk.zzawq)).booleanValue()) {
            if (!((Boolean) zzkb.zzik().zzd(zznk.zzaws)).booleanValue()) {
                return null;
            }
        }
        if (zzbv.zzeo().zzqh().zzqu() && zzbv.zzeo().zzqh().zzqw()) {
            return null;
        }
        String signature;
        String zzgo;
        String str;
        if (com_google_android_gms_internal_ads_zzgk.zzha()) {
            com_google_android_gms_internal_ads_zzgk.wakeup();
        }
        zzge zzgy = com_google_android_gms_internal_ads_zzgk.zzgy();
        if (zzgy != null) {
            signature = zzgy.getSignature();
            zzgo = zzgy.zzgo();
            String zzgp = zzgy.zzgp();
            if (signature != null) {
                zzbv.zzeo().zzqh().zzcn(signature);
            }
            if (zzgp != null) {
                zzbv.zzeo().zzqh().zzco(zzgp);
                str = signature;
                signature = zzgo;
                zzgo = zzgp;
            } else {
                str = signature;
                signature = zzgo;
                zzgo = zzgp;
            }
        } else {
            signature = null;
            str = zzbv.zzeo().zzqh().zzqv();
            zzgo = zzbv.zzeo().zzqh().zzqx();
        }
        Bundle bundle = new Bundle(1);
        if (zzgo != null) {
            if (((Boolean) zzkb.zzik().zzd(zznk.zzaws)).booleanValue() && !zzbv.zzeo().zzqh().zzqw()) {
                bundle.putString("v_fp_vertical", zzgo);
            }
        }
        if (str != null) {
            if (((Boolean) zzkb.zzik().zzd(zznk.zzawq)).booleanValue() && !zzbv.zzeo().zzqh().zzqu()) {
                bundle.putString("fingerprint", str);
                if (!str.equals(signature)) {
                    bundle.putString("v_fp", signature);
                }
            }
        }
        return !bundle.isEmpty() ? bundle : null;
    }

    public static DisplayMetrics zza(WindowManager windowManager) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    public static PopupWindow zza(View view, int i, int i2, boolean z) {
        return new PopupWindow(view, i, i2, false);
    }

    public static String zza(Context context, View view, zzjn com_google_android_gms_internal_ads_zzjn) {
        if (!((Boolean) zzkb.zzik().zzd(zznk.zzaxi)).booleanValue()) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("width", com_google_android_gms_internal_ads_zzjn.width);
            jSONObject2.put("height", com_google_android_gms_internal_ads_zzjn.height);
            jSONObject.put("size", jSONObject2);
            jSONObject.put(C1404b.aw, zzap(context));
            if (!com_google_android_gms_internal_ads_zzjn.zzarc) {
                JSONArray jSONArray = new JSONArray();
                while (view != null) {
                    ViewParent parent = view.getParent();
                    if (parent != null) {
                        int i = -1;
                        if (parent instanceof ViewGroup) {
                            i = ((ViewGroup) parent).indexOfChild(view);
                        }
                        JSONObject jSONObject3 = new JSONObject();
                        jSONObject3.put("type", parent.getClass().getName());
                        jSONObject3.put("index_of_child", i);
                        jSONArray.put(jSONObject3);
                    }
                    view = (parent == null || !(parent instanceof View)) ? null : (View) parent;
                }
                if (jSONArray.length() > 0) {
                    jSONObject.put("parents", jSONArray);
                }
            }
            return jSONObject.toString();
        } catch (Throwable e) {
            zzane.zzc("Fail to get view hierarchy json", e);
            return null;
        }
    }

    public static String zza(InputStreamReader inputStreamReader) throws IOException {
        StringBuilder stringBuilder = new StringBuilder(8192);
        char[] cArr = new char[2048];
        while (true) {
            int read = inputStreamReader.read(cArr);
            if (read == -1) {
                return stringBuilder.toString();
            }
            stringBuilder.append(cArr, 0, read);
        }
    }

    private final JSONArray zza(Collection<?> collection) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        for (Object zza : collection) {
            zza(jSONArray, zza);
        }
        return jSONArray;
    }

    public static void zza(Context context, Intent intent) {
        try {
            context.startActivity(intent);
        } catch (Throwable th) {
            intent.addFlags(ErrorDialogData.BINDER_CRASH);
            context.startActivity(intent);
        }
    }

    @TargetApi(18)
    public static void zza(Context context, Uri uri) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW", uri);
            Bundle bundle = new Bundle();
            intent.putExtras(bundle);
            if (((Boolean) zzkb.zzik().zzd(zznk.zzbdy)).booleanValue()) {
                zzb(context, intent);
            }
            bundle.putString("com.android.browser.application_id", context.getPackageName());
            context.startActivity(intent);
            String uri2 = uri.toString();
            zzane.zzck(new StringBuilder(String.valueOf(uri2).length() + 26).append("Opening ").append(uri2).append(" in a new browser.").toString());
        } catch (Throwable e) {
            zzane.zzb("No browser is found.", e);
        }
    }

    public static void zza(Context context, String str, List<String> list) {
        for (String com_google_android_gms_internal_ads_zzami : list) {
            new zzami(context, str, com_google_android_gms_internal_ads_zzami).zznt();
        }
    }

    public static void zza(Context context, Throwable th) {
        if (context != null) {
            boolean booleanValue;
            try {
                booleanValue = ((Boolean) zzkb.zzik().zzd(zznk.zzaui)).booleanValue();
            } catch (IllegalStateException e) {
                booleanValue = false;
            }
            if (booleanValue) {
                CrashUtils.addDynamiteErrorToDropBox(context, th);
            }
        }
    }

    private final void zza(JSONArray jSONArray, Object obj) throws JSONException {
        if (obj instanceof Bundle) {
            jSONArray.put(zzf((Bundle) obj));
        } else if (obj instanceof Map) {
            jSONArray.put(zzn((Map) obj));
        } else if (obj instanceof Collection) {
            jSONArray.put(zza((Collection) obj));
        } else if (obj instanceof Object[]) {
            Object[] objArr = (Object[]) obj;
            JSONArray jSONArray2 = new JSONArray();
            for (Object zza : objArr) {
                zza(jSONArray2, zza);
            }
            jSONArray.put(jSONArray2);
        } else {
            jSONArray.put(obj);
        }
    }

    private final void zza(JSONObject jSONObject, String str, Object obj) throws JSONException {
        if (obj instanceof Bundle) {
            jSONObject.put(str, zzf((Bundle) obj));
        } else if (obj instanceof Map) {
            jSONObject.put(str, zzn((Map) obj));
        } else if (obj instanceof Collection) {
            if (str == null) {
                str = "null";
            }
            jSONObject.put(str, zza((Collection) obj));
        } else if (obj instanceof Object[]) {
            jSONObject.put(str, zza(Arrays.asList((Object[]) obj)));
        } else {
            jSONObject.put(str, obj);
        }
    }

    @TargetApi(24)
    public static boolean zza(Activity activity, Configuration configuration) {
        zzkb.zzif();
        int zza = zzamu.zza((Context) activity, configuration.screenHeightDp);
        int zza2 = zzamu.zza((Context) activity, configuration.screenWidthDp);
        DisplayMetrics zza3 = zza((WindowManager) activity.getApplicationContext().getSystemService("window"));
        int i = zza3.heightPixels;
        int i2 = zza3.widthPixels;
        int identifier = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        int dimensionPixelSize = identifier > 0 ? activity.getResources().getDimensionPixelSize(identifier) : 0;
        identifier = ((Integer) zzkb.zzik().zzd(zznk.zzbek)).intValue() * ((int) Math.round(((double) activity.getResources().getDisplayMetrics().density) + 0.5d));
        return zzb(i, dimensionPixelSize + zza, identifier) && zzb(i2, zza2, identifier);
    }

    public static boolean zza(ClassLoader classLoader, Class<?> cls, String str) {
        boolean z = false;
        try {
            z = cls.isAssignableFrom(Class.forName(str, false, classLoader));
        } catch (Throwable th) {
        }
        return z;
    }

    public static boolean zzaj(Context context) {
        Intent intent = new Intent();
        intent.setClassName(context, AdActivity.CLASS_NAME);
        try {
            ResolveInfo resolveActivity = context.getPackageManager().resolveActivity(intent, 65536);
            if (resolveActivity == null || resolveActivity.activityInfo == null) {
                zzane.zzdk("Could not find com.google.android.gms.ads.AdActivity, please make sure it is declared in AndroidManifest.xml.");
                return false;
            }
            boolean z;
            String str = "com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".";
            if ((resolveActivity.activityInfo.configChanges & 16) == 0) {
                zzane.zzdk(String.format(str, new Object[]{"keyboard"}));
                z = false;
            } else {
                z = true;
            }
            if ((resolveActivity.activityInfo.configChanges & 32) == 0) {
                zzane.zzdk(String.format(str, new Object[]{"keyboardHidden"}));
                z = false;
            }
            if ((resolveActivity.activityInfo.configChanges & 128) == 0) {
                zzane.zzdk(String.format(str, new Object[]{"orientation"}));
                z = false;
            }
            if ((resolveActivity.activityInfo.configChanges & 256) == 0) {
                zzane.zzdk(String.format(str, new Object[]{"screenLayout"}));
                z = false;
            }
            if ((resolveActivity.activityInfo.configChanges & 512) == 0) {
                zzane.zzdk(String.format(str, new Object[]{"uiMode"}));
                z = false;
            }
            if ((resolveActivity.activityInfo.configChanges & 1024) == 0) {
                zzane.zzdk(String.format(str, new Object[]{"screenSize"}));
                z = false;
            }
            if ((resolveActivity.activityInfo.configChanges & 2048) != 0) {
                return z;
            }
            zzane.zzdk(String.format(str, new Object[]{"smallestScreenSize"}));
            return false;
        } catch (Throwable e) {
            zzane.zzc("Could not verify that com.google.android.gms.ads.AdActivity is declared in AndroidManifest.xml", e);
            zzbv.zzeo().zza(e, "AdUtil.hasAdActivity");
            return false;
        }
    }

    @VisibleForTesting
    protected static String zzam(Context context) {
        try {
            return new WebView(context).getSettings().getUserAgentString();
        } catch (Throwable th) {
            return zzrg();
        }
    }

    public static Builder zzan(Context context) {
        return new Builder(context);
    }

    public static zzmw zzao(Context context) {
        return new zzmw(context);
    }

    private static String zzap(Context context) {
        try {
            ActivityManager activityManager = (ActivityManager) context.getSystemService(C1404b.aw);
            if (activityManager == null) {
                return null;
            }
            List runningTasks = activityManager.getRunningTasks(1);
            if (!(runningTasks == null || runningTasks.isEmpty())) {
                RunningTaskInfo runningTaskInfo = (RunningTaskInfo) runningTasks.get(0);
                if (!(runningTaskInfo == null || runningTaskInfo.topActivity == null)) {
                    return runningTaskInfo.topActivity.getClassName();
                }
            }
            return null;
        } catch (Exception e) {
        }
    }

    public static boolean zzaq(Context context) {
        try {
            ActivityManager activityManager = (ActivityManager) context.getSystemService(C1404b.aw);
            KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService("keyguard");
            if (activityManager == null || keyguardManager == null) {
                return false;
            }
            List<RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
            if (runningAppProcesses == null) {
                return false;
            }
            for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (Process.myPid() == runningAppProcessInfo.pid) {
                    if (runningAppProcessInfo.importance == 100 && !keyguardManager.inKeyguardRestrictedInputMode()) {
                        PowerManager powerManager = (PowerManager) context.getSystemService("power");
                        if (powerManager == null ? false : powerManager.isScreenOn()) {
                            return true;
                        }
                    }
                    return false;
                }
            }
            return false;
        } catch (Throwable th) {
            return false;
        }
    }

    public static Bitmap zzar(Context context) {
        if (!(context instanceof Activity)) {
            return null;
        }
        Bitmap zzv;
        try {
            if (((Boolean) zzkb.zzik().zzd(zznk.zzbbh)).booleanValue()) {
                Window window = ((Activity) context).getWindow();
                if (window != null) {
                    zzv = zzv(window.getDecorView().getRootView());
                }
                zzv = null;
            } else {
                zzv = zzu(((Activity) context).getWindow().getDecorView());
            }
        } catch (Throwable e) {
            zzane.zzb("Fail to capture screen shot", e);
        }
        return zzv;
    }

    public static int zzas(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        return applicationInfo == null ? 0 : applicationInfo.targetSdkVersion;
    }

    @Nullable
    private static KeyguardManager zzat(Context context) {
        Object systemService = context.getSystemService("keyguard");
        return (systemService == null || !(systemService instanceof KeyguardManager)) ? null : (KeyguardManager) systemService;
    }

    @TargetApi(16)
    public static boolean zzau(Context context) {
        if (context == null || !PlatformVersion.isAtLeastJellyBean()) {
            return false;
        }
        KeyguardManager zzat = zzat(context);
        return zzat != null && zzat.isKeyguardLocked();
    }

    public static boolean zzav(Context context) {
        try {
            context.getClassLoader().loadClass("com.google.android.gms.ads.internal.ClientApi");
            return false;
        } catch (ClassNotFoundException e) {
            return true;
        } catch (Throwable th) {
            zzane.zzb("Error loading class.", th);
            zzbv.zzeo().zza(th, "AdUtil.isLiteSdk");
            return false;
        }
    }

    public static WebResourceResponse zzb(HttpURLConnection httpURLConnection) throws IOException {
        String trim;
        zzbv.zzek();
        Object contentType = httpURLConnection.getContentType();
        String trim2 = TextUtils.isEmpty(contentType) ? "" : contentType.split(";")[0].trim();
        zzbv.zzek();
        contentType = httpURLConnection.getContentType();
        if (!TextUtils.isEmpty(contentType)) {
            String[] split = contentType.split(";");
            if (split.length != 1) {
                for (int i = 1; i < split.length; i++) {
                    if (split[i].trim().startsWith("charset")) {
                        String[] split2 = split[i].trim().split("=");
                        if (split2.length > 1) {
                            trim = split2[1].trim();
                            break;
                        }
                    }
                }
            }
        }
        trim = "";
        Map headerFields = httpURLConnection.getHeaderFields();
        Map hashMap = new HashMap(headerFields.size());
        for (Entry entry : headerFields.entrySet()) {
            if (!(entry.getKey() == null || entry.getValue() == null || ((List) entry.getValue()).size() <= 0)) {
                hashMap.put((String) entry.getKey(), (String) ((List) entry.getValue()).get(0));
            }
        }
        return zzbv.zzem().zza(trim2, trim, httpURLConnection.getResponseCode(), httpURLConnection.getResponseMessage(), hashMap, httpURLConnection.getInputStream());
    }

    @TargetApi(18)
    public static void zzb(Context context, Intent intent) {
        if (intent != null && PlatformVersion.isAtLeastJellyBeanMR2()) {
            Bundle extras = intent.getExtras() != null ? intent.getExtras() : new Bundle();
            extras.putBinder("android.support.customtabs.extra.SESSION", null);
            extras.putString("com.android.browser.application_id", context.getPackageName());
            intent.putExtras(extras);
        }
    }

    @VisibleForTesting
    private static boolean zzb(int i, int i2, int i3) {
        return Math.abs(i - i2) <= i3;
    }

    public static String zzcu(String str) {
        return Uri.parse(str).buildUpon().query(null).build().toString();
    }

    public static int zzcv(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            String valueOf = String.valueOf(e);
            zzane.zzdk(new StringBuilder(String.valueOf(valueOf).length() + 22).append("Could not parse value:").append(valueOf).toString());
            return 0;
        }
    }

    public static boolean zzcw(String str) {
        return TextUtils.isEmpty(str) ? false : str.matches("([^\\s]+(\\.(?i)(jpg|png|gif|bmp|webp))$)");
    }

    public static void zzd(Context context, String str, String str2) {
        List arrayList = new ArrayList();
        arrayList.add(str2);
        zza(context, str, arrayList);
    }

    public static void zzd(Runnable runnable) {
        if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
            runnable.run();
        } else {
            zzaki.zzb(runnable);
        }
    }

    public static void zze(Context context, String str, String str2) {
        try {
            FileOutputStream openFileOutput = context.openFileOutput(str, 0);
            openFileOutput.write(str2.getBytes("UTF-8"));
            openFileOutput.close();
        } catch (Throwable e) {
            zzane.zzb("Error writing to file in internal storage.", e);
        }
    }

    @Nullable
    public static WebResourceResponse zzf(Context context, String str, String str2) {
        Throwable e;
        try {
            Map hashMap = new HashMap();
            hashMap.put("User-Agent", zzbv.zzek().zzm(context, str));
            hashMap.put("Cache-Control", "max-stale=3600");
            String str3 = (String) new zzalt(context).zzc(str2, hashMap).get(60, TimeUnit.SECONDS);
            if (str3 != null) {
                return new WebResourceResponse("application/javascript", "UTF-8", new ByteArrayInputStream(str3.getBytes("UTF-8")));
            }
        } catch (IOException e2) {
            e = e2;
            zzane.zzc("Could not fetch MRAID JS.", e);
            return null;
        } catch (ExecutionException e3) {
            e = e3;
            zzane.zzc("Could not fetch MRAID JS.", e);
            return null;
        } catch (InterruptedException e4) {
            e = e4;
            zzane.zzc("Could not fetch MRAID JS.", e);
            return null;
        } catch (TimeoutException e5) {
            e = e5;
            zzane.zzc("Could not fetch MRAID JS.", e);
            return null;
        }
        return null;
    }

    private final JSONObject zzf(Bundle bundle) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        for (String str : bundle.keySet()) {
            zza(jSONObject, str, bundle.get(str));
        }
        return jSONObject;
    }

    public static int[] zzf(Activity activity) {
        Window window = activity.getWindow();
        if (window == null || window.findViewById(16908290) == null) {
            return zzrj();
        }
        return new int[]{window.findViewById(16908290).getWidth(), window.findViewById(16908290).getHeight()};
    }

    public static Map<String, String> zzg(Uri uri) {
        if (uri == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        for (String str : zzbv.zzem().zzh(uri)) {
            hashMap.put(str, uri.getQueryParameter(str));
        }
        return hashMap;
    }

    public static boolean zzl(Context context, String str) {
        return Wrappers.packageManager(context).checkPermission(str, context.getPackageName()) == 0;
    }

    public static String zzn(Context context, String str) {
        try {
            return new String(IOUtils.readInputStreamFully(context.openFileInput(str), true), "UTF-8");
        } catch (IOException e) {
            zzane.zzck("Error reading from internal storage.");
            return "";
        }
    }

    private static String zzrg() {
        StringBuilder stringBuilder = new StringBuilder(256);
        stringBuilder.append("Mozilla/5.0 (Linux; U; Android");
        if (VERSION.RELEASE != null) {
            stringBuilder.append(" ").append(VERSION.RELEASE);
        }
        stringBuilder.append("; ").append(Locale.getDefault());
        if (Build.DEVICE != null) {
            stringBuilder.append("; ").append(Build.DEVICE);
            if (Build.DISPLAY != null) {
                stringBuilder.append(" Build/").append(Build.DISPLAY);
            }
        }
        stringBuilder.append(") AppleWebKit/533 Version/4.0 Safari/533");
        return stringBuilder.toString();
    }

    public static String zzrh() {
        return UUID.randomUUID().toString();
    }

    public static String zzri() {
        String str = Build.MANUFACTURER;
        String str2 = Build.MODEL;
        return str2.startsWith(str) ? str2 : new StringBuilder((String.valueOf(str).length() + 1) + String.valueOf(str2).length()).append(str).append(" ").append(str2).toString();
    }

    private static int[] zzrj() {
        return new int[]{0, 0};
    }

    public static Bundle zzrk() {
        Bundle bundle = new Bundle();
        try {
            if (((Boolean) zzkb.zzik().zzd(zznk.zzavm)).booleanValue()) {
                Parcelable memoryInfo = new MemoryInfo();
                Debug.getMemoryInfo(memoryInfo);
                bundle.putParcelable("debug_memory_info", memoryInfo);
            }
            if (((Boolean) zzkb.zzik().zzd(zznk.zzavn)).booleanValue()) {
                Runtime runtime = Runtime.getRuntime();
                bundle.putLong("runtime_free_memory", runtime.freeMemory());
                bundle.putLong("runtime_max_memory", runtime.maxMemory());
                bundle.putLong("runtime_total_memory", runtime.totalMemory());
            }
            bundle.putInt("web_view_count", zzbv.zzeo().zzqg());
        } catch (Throwable e) {
            zzane.zzc("Unable to gather memory stats", e);
        }
        return bundle;
    }

    public static Bitmap zzs(View view) {
        view.setDrawingCacheEnabled(true);
        Bitmap createBitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);
        return createBitmap;
    }

    public static Bitmap zzt(View view) {
        if (view == null) {
            return null;
        }
        Bitmap zzv = zzv(view);
        return zzv == null ? zzu(view) : zzv;
    }

    private static Bitmap zzu(@NonNull View view) {
        try {
            int width = view.getWidth();
            int height = view.getHeight();
            if (width == 0 || height == 0) {
                zzane.zzdk("Width or height of view is zero");
                return null;
            }
            Bitmap createBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Config.RGB_565);
            Canvas canvas = new Canvas(createBitmap);
            view.layout(0, 0, width, height);
            view.draw(canvas);
            return createBitmap;
        } catch (Throwable e) {
            zzane.zzb("Fail to capture the webview", e);
            return null;
        }
    }

    private static Bitmap zzv(@NonNull View view) {
        Bitmap drawingCache;
        Throwable e;
        try {
            boolean isDrawingCacheEnabled = view.isDrawingCacheEnabled();
            view.setDrawingCacheEnabled(true);
            drawingCache = view.getDrawingCache();
            drawingCache = drawingCache != null ? Bitmap.createBitmap(drawingCache) : null;
            try {
                view.setDrawingCacheEnabled(isDrawingCacheEnabled);
            } catch (RuntimeException e2) {
                e = e2;
                zzane.zzb("Fail to capture the web view", e);
                return drawingCache;
            }
        } catch (Throwable e3) {
            Throwable th = e3;
            drawingCache = null;
            e = th;
            zzane.zzb("Fail to capture the web view", e);
            return drawingCache;
        }
        return drawingCache;
    }

    public static boolean zzw(View view) {
        Activity activity;
        View rootView = view.getRootView();
        if (rootView != null) {
            Context context = rootView.getContext();
            if (context instanceof Activity) {
                activity = (Activity) context;
                if (activity == null) {
                    return false;
                }
                Window window = activity.getWindow();
                LayoutParams attributes = window != null ? null : window.getAttributes();
                return attributes == null && (attributes.flags & 524288) != 0;
            }
        }
        activity = null;
        if (activity == null) {
            return false;
        }
        Window window2 = activity.getWindow();
        if (window2 != null) {
        }
        if (attributes == null) {
        }
    }

    public static int zzx(@Nullable View view) {
        if (view == null) {
            return -1;
        }
        ViewParent parent = view.getParent();
        while (parent != null && !(parent instanceof AdapterView)) {
            parent = parent.getParent();
        }
        return parent == null ? -1 : ((AdapterView) parent).getPositionForView(view);
    }

    public final JSONObject zza(@Nullable Bundle bundle, JSONObject jSONObject) {
        JSONObject jSONObject2 = null;
        if (bundle != null) {
            try {
                jSONObject2 = zzf(bundle);
            } catch (Throwable e) {
                zzane.zzb("Error converting Bundle to JSON", e);
            }
        }
        return jSONObject2;
    }

    public final void zza(Context context, String str, WebSettings webSettings) {
        webSettings.setUserAgentString(zzm(context, str));
    }

    public final void zza(Context context, @Nullable String str, String str2, Bundle bundle, boolean z) {
        if (z) {
            zzbv.zzek();
            bundle.putString("device", zzri());
            bundle.putString("eids", TextUtils.join(",", zznk.zzjb()));
        }
        zzkb.zzif();
        zzamu.zza(context, str, str2, bundle, z, new zzakn(this, context, str));
    }

    public final void zza(Context context, String str, boolean z, HttpURLConnection httpURLConnection) {
        httpURLConnection.setConnectTimeout(60000);
        httpURLConnection.setInstanceFollowRedirects(false);
        httpURLConnection.setReadTimeout(60000);
        httpURLConnection.setRequestProperty("User-Agent", zzm(context, str));
        httpURLConnection.setUseCaches(false);
    }

    public final void zza(Context context, List<String> list) {
        if (!(context instanceof Activity) || TextUtils.isEmpty(zzbfw.zzbn((Activity) context))) {
            return;
        }
        if (list == null) {
            zzakb.m3428v("Cannot ping urls: empty list.");
        } else if (zzoh.zzh(context)) {
            zzoh com_google_android_gms_internal_ads_zzoh = new zzoh();
            com_google_android_gms_internal_ads_zzoh.zza(new zzakl(this, list, com_google_android_gms_internal_ads_zzoh, context));
            com_google_android_gms_internal_ads_zzoh.zzd((Activity) context);
        } else {
            zzakb.m3428v("Cannot ping url because custom tabs is not supported");
        }
    }

    public final boolean zza(View view, Context context) {
        PowerManager powerManager = null;
        Context applicationContext = context.getApplicationContext();
        if (applicationContext != null) {
            powerManager = (PowerManager) applicationContext.getSystemService("power");
        }
        return zza(view, powerManager, zzat(context));
    }

    public final boolean zza(View view, PowerManager powerManager, KeyguardManager keyguardManager) {
        boolean z;
        if (!zzbv.zzek().zzcrn) {
            if (keyguardManager == null ? false : keyguardManager.inKeyguardRestrictedInputMode()) {
                if (!(((Boolean) zzkb.zzik().zzd(zznk.zzazu)).booleanValue() && zzw(view))) {
                    z = false;
                    if (view.getVisibility() == 0 && view.isShown()) {
                        boolean z2 = powerManager != null || powerManager.isScreenOn();
                        if (z2 && r0) {
                            if (!((Boolean) zzkb.zzik().zzd(zznk.zzazs)).booleanValue() || view.getLocalVisibleRect(new Rect()) || view.getGlobalVisibleRect(new Rect())) {
                                return true;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        z = true;
        if (powerManager != null) {
        }
        return true;
    }

    public final boolean zzak(Context context) {
        if (this.zzcro) {
            return false;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.USER_PRESENT");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        context.getApplicationContext().registerReceiver(new zzakp(this, null), intentFilter);
        this.zzcro = true;
        return true;
    }

    public final boolean zzal(Context context) {
        if (this.zzcrp) {
            return false;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.google.android.ads.intent.DEBUG_LOGGING_ENABLEMENT_CHANGED");
        context.getApplicationContext().registerReceiver(new zzako(this, null), intentFilter);
        this.zzcrp = true;
        return true;
    }

    public final void zzb(Context context, String str, String str2, Bundle bundle, boolean z) {
        if (((Boolean) zzkb.zzik().zzd(zznk.zzazx)).booleanValue()) {
            zza(context, str, str2, bundle, z);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzcx(java.lang.String r4) {
        /*
        r3 = this;
        r1 = 0;
        r0 = android.text.TextUtils.isEmpty(r4);
        if (r0 == 0) goto L_0x0009;
    L_0x0007:
        r0 = r1;
    L_0x0008:
        return r0;
    L_0x0009:
        monitor-enter(r3);	 Catch:{ PatternSyntaxException -> 0x0047 }
        r0 = r3.zzcrq;	 Catch:{ all -> 0x0044 }
        if (r0 == 0) goto L_0x0026;
    L_0x000e:
        r0 = com.google.android.gms.internal.ads.zznk.zzaxo;	 Catch:{ all -> 0x0044 }
        r2 = com.google.android.gms.internal.ads.zzkb.zzik();	 Catch:{ all -> 0x0044 }
        r0 = r2.zzd(r0);	 Catch:{ all -> 0x0044 }
        r0 = (java.lang.String) r0;	 Catch:{ all -> 0x0044 }
        r2 = r3.zzcrq;	 Catch:{ all -> 0x0044 }
        r2 = r2.pattern();	 Catch:{ all -> 0x0044 }
        r0 = r0.equals(r2);	 Catch:{ all -> 0x0044 }
        if (r0 != 0) goto L_0x0038;
    L_0x0026:
        r0 = com.google.android.gms.internal.ads.zznk.zzaxo;	 Catch:{ all -> 0x0044 }
        r2 = com.google.android.gms.internal.ads.zzkb.zzik();	 Catch:{ all -> 0x0044 }
        r0 = r2.zzd(r0);	 Catch:{ all -> 0x0044 }
        r0 = (java.lang.String) r0;	 Catch:{ all -> 0x0044 }
        r0 = java.util.regex.Pattern.compile(r0);	 Catch:{ all -> 0x0044 }
        r3.zzcrq = r0;	 Catch:{ all -> 0x0044 }
    L_0x0038:
        r0 = r3.zzcrq;	 Catch:{ all -> 0x0044 }
        r0 = r0.matcher(r4);	 Catch:{ all -> 0x0044 }
        r0 = r0.matches();	 Catch:{ all -> 0x0044 }
        monitor-exit(r3);	 Catch:{ all -> 0x0044 }
        goto L_0x0008;
    L_0x0044:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x0044 }
        throw r0;	 Catch:{ PatternSyntaxException -> 0x0047 }
    L_0x0047:
        r0 = move-exception;
        r0 = r1;
        goto L_0x0008;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzakk.zzcx(java.lang.String):boolean");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zzcy(java.lang.String r4) {
        /*
        r3 = this;
        r1 = 0;
        r0 = android.text.TextUtils.isEmpty(r4);
        if (r0 == 0) goto L_0x0009;
    L_0x0007:
        r0 = r1;
    L_0x0008:
        return r0;
    L_0x0009:
        monitor-enter(r3);	 Catch:{ PatternSyntaxException -> 0x0047 }
        r0 = r3.zzcrr;	 Catch:{ all -> 0x0044 }
        if (r0 == 0) goto L_0x0026;
    L_0x000e:
        r0 = com.google.android.gms.internal.ads.zznk.zzaxp;	 Catch:{ all -> 0x0044 }
        r2 = com.google.android.gms.internal.ads.zzkb.zzik();	 Catch:{ all -> 0x0044 }
        r0 = r2.zzd(r0);	 Catch:{ all -> 0x0044 }
        r0 = (java.lang.String) r0;	 Catch:{ all -> 0x0044 }
        r2 = r3.zzcrr;	 Catch:{ all -> 0x0044 }
        r2 = r2.pattern();	 Catch:{ all -> 0x0044 }
        r0 = r0.equals(r2);	 Catch:{ all -> 0x0044 }
        if (r0 != 0) goto L_0x0038;
    L_0x0026:
        r0 = com.google.android.gms.internal.ads.zznk.zzaxp;	 Catch:{ all -> 0x0044 }
        r2 = com.google.android.gms.internal.ads.zzkb.zzik();	 Catch:{ all -> 0x0044 }
        r0 = r2.zzd(r0);	 Catch:{ all -> 0x0044 }
        r0 = (java.lang.String) r0;	 Catch:{ all -> 0x0044 }
        r0 = java.util.regex.Pattern.compile(r0);	 Catch:{ all -> 0x0044 }
        r3.zzcrr = r0;	 Catch:{ all -> 0x0044 }
    L_0x0038:
        r0 = r3.zzcrr;	 Catch:{ all -> 0x0044 }
        r0 = r0.matcher(r4);	 Catch:{ all -> 0x0044 }
        r0 = r0.matches();	 Catch:{ all -> 0x0044 }
        monitor-exit(r3);	 Catch:{ all -> 0x0044 }
        goto L_0x0008;
    L_0x0044:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x0044 }
        throw r0;	 Catch:{ PatternSyntaxException -> 0x0047 }
    L_0x0047:
        r0 = move-exception;
        r0 = r1;
        goto L_0x0008;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzakk.zzcy(java.lang.String):boolean");
    }

    public final int[] zzg(Activity activity) {
        int[] zzf = zzf(activity);
        r1 = new int[2];
        zzkb.zzif();
        r1[0] = zzamu.zzb((Context) activity, zzf[0]);
        zzkb.zzif();
        r1[1] = zzamu.zzb((Context) activity, zzf[1]);
        return r1;
    }

    public final int[] zzh(Activity activity) {
        Window window = activity.getWindow();
        int[] zzrj = (window == null || window.findViewById(16908290) == null) ? zzrj() : new int[]{window.findViewById(16908290).getTop(), window.findViewById(16908290).getBottom()};
        r1 = new int[2];
        zzkb.zzif();
        r1[0] = zzamu.zzb((Context) activity, zzrj[0]);
        zzkb.zzif();
        r1[1] = zzamu.zzb((Context) activity, zzrj[1]);
        return r1;
    }

    public final String zzm(Context context, String str) {
        String str2;
        synchronized (this.mLock) {
            if (this.zzcpq != null) {
                str2 = this.zzcpq;
            } else if (str == null) {
                str2 = zzrg();
            } else {
                try {
                    this.zzcpq = zzbv.zzem().getDefaultUserAgent(context);
                } catch (Exception e) {
                }
                if (TextUtils.isEmpty(this.zzcpq)) {
                    zzkb.zzif();
                    if (zzamu.zzsh()) {
                        this.zzcpq = zzam(context);
                    } else {
                        this.zzcpq = null;
                        zzcrm.post(new zzakm(this, context));
                        while (this.zzcpq == null) {
                            try {
                                this.mLock.wait();
                            } catch (InterruptedException e2) {
                                this.zzcpq = zzrg();
                                String str3 = "Interrupted, use default user agent: ";
                                str2 = String.valueOf(this.zzcpq);
                                zzane.zzdk(str2.length() != 0 ? str3.concat(str2) : new String(str3));
                            }
                        }
                    }
                }
                str2 = String.valueOf(this.zzcpq);
                this.zzcpq = new StringBuilder((String.valueOf(str2).length() + 10) + String.valueOf(str).length()).append(str2).append(" (Mobile; ").append(str).toString();
                try {
                    if (Wrappers.packageManager(context).isCallerInstantApp()) {
                        this.zzcpq = String.valueOf(this.zzcpq).concat(";aia");
                    }
                } catch (Throwable e3) {
                    zzbv.zzeo().zza(e3, "AdUtil.getUserAgent");
                }
                this.zzcpq = String.valueOf(this.zzcpq).concat(")");
                str2 = this.zzcpq;
            }
        }
        return str2;
    }

    public final JSONObject zzn(Map<String, ?> map) throws JSONException {
        String valueOf;
        try {
            JSONObject jSONObject = new JSONObject();
            for (String valueOf2 : map.keySet()) {
                zza(jSONObject, valueOf2, map.get(valueOf2));
            }
            return jSONObject;
        } catch (ClassCastException e) {
            String str = "Could not convert map to JSON: ";
            valueOf2 = String.valueOf(e.getMessage());
            throw new JSONException(valueOf2.length() != 0 ? str.concat(valueOf2) : new String(str));
        }
    }
}
