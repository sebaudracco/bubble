package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri.Builder;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings.Secure;
import android.support.annotation.Nullable;
import android.support.v4.internal.view.SupportMenu;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;
import com.bgjd.ici.p025b.C1408j.C1404b;
import com.cuebiq.cuebiqsdk.model.config.Settings;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;
import com.google.android.gms.ads.search.SearchAdView;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.ProviderConstants;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.ads.dynamite.ModuleDescriptor;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.UUID;

@zzadh
public final class zzamu {
    private static final String zzcup = AdView.class.getName();
    private static final String zzcuq = InterstitialAd.class.getName();
    private static final String zzcur = PublisherAdView.class.getName();
    private static final String zzcus = PublisherInterstitialAd.class.getName();
    private static final String zzcut = SearchAdView.class.getName();
    private static final String zzcuu = AdLoader.class.getName();
    public static final Handler zzsy = new Handler(Looper.getMainLooper());

    public static int zza(Context context, int i) {
        return zza(context.getResources().getDisplayMetrics(), i);
    }

    public static int zza(DisplayMetrics displayMetrics, int i) {
        return (int) TypedValue.applyDimension(1, (float) i, displayMetrics);
    }

    @Nullable
    @VisibleForTesting
    public static String zza(StackTraceElement[] stackTraceElementArr, String str) {
        String className;
        for (int i = 0; i + 1 < stackTraceElementArr.length; i++) {
            StackTraceElement stackTraceElement = stackTraceElementArr[i];
            String className2 = stackTraceElement.getClassName();
            if ("loadAd".equalsIgnoreCase(stackTraceElement.getMethodName()) && (zzcup.equalsIgnoreCase(className2) || zzcuq.equalsIgnoreCase(className2) || zzcur.equalsIgnoreCase(className2) || zzcus.equalsIgnoreCase(className2) || zzcut.equalsIgnoreCase(className2) || zzcuu.equalsIgnoreCase(className2))) {
                className = stackTraceElementArr[i + 1].getClassName();
                break;
            }
        }
        className = null;
        if (str != null) {
            StringTokenizer stringTokenizer = new StringTokenizer(str, ".");
            StringBuilder stringBuilder = new StringBuilder();
            int i2 = 2;
            if (stringTokenizer.hasMoreElements()) {
                CharSequence stringBuilder2;
                stringBuilder.append(stringTokenizer.nextToken());
                while (true) {
                    int i3 = i2 - 1;
                    if (i2 <= 0 || !stringTokenizer.hasMoreElements()) {
                        stringBuilder2 = stringBuilder.toString();
                    } else {
                        stringBuilder.append(".").append(stringTokenizer.nextToken());
                        i2 = i3;
                    }
                }
                stringBuilder2 = stringBuilder.toString();
            }
            if (!(className == null || className.contains(r8))) {
                return className;
            }
        }
        return null;
    }

    public static void zza(Context context, @Nullable String str, String str2, Bundle bundle, boolean z, zzamx com_google_android_gms_internal_ads_zzamx) {
        if (z) {
            Context applicationContext = context.getApplicationContext();
            if (applicationContext == null) {
                applicationContext = context;
            }
            bundle.putString("os", VERSION.RELEASE);
            bundle.putString(ProviderConstants.API_PATH, String.valueOf(VERSION.SDK_INT));
            bundle.putString(C1404b.f2147y, applicationContext.getPackageName());
            if (str == null) {
                str = GoogleApiAvailabilityLight.getInstance().getApkVersion(context) + ".12451000";
            }
            bundle.putString("js", str);
        }
        Builder appendQueryParameter = new Builder().scheme("https").path("//pagead2.googlesyndication.com/pagead/gen_204").appendQueryParameter("id", str2);
        for (String str3 : bundle.keySet()) {
            appendQueryParameter.appendQueryParameter(str3, bundle.getString(str3));
        }
        com_google_android_gms_internal_ads_zzamx.zzcz(appendQueryParameter.toString());
    }

    private final void zza(ViewGroup viewGroup, zzjn com_google_android_gms_internal_ads_zzjn, String str, int i, int i2) {
        if (viewGroup.getChildCount() == 0) {
            Context context = viewGroup.getContext();
            View textView = new TextView(context);
            textView.setGravity(17);
            textView.setText(str);
            textView.setTextColor(i);
            textView.setBackgroundColor(i2);
            View frameLayout = new FrameLayout(context);
            frameLayout.setBackgroundColor(i);
            int zza = zza(context, 3);
            frameLayout.addView(textView, new LayoutParams(com_google_android_gms_internal_ads_zzjn.widthPixels - zza, com_google_android_gms_internal_ads_zzjn.heightPixels - zza, 17));
            viewGroup.addView(frameLayout, com_google_android_gms_internal_ads_zzjn.widthPixels, com_google_android_gms_internal_ads_zzjn.heightPixels);
        }
    }

    public static void zza(boolean z, HttpURLConnection httpURLConnection, @Nullable String str) {
        httpURLConnection.setConnectTimeout(60000);
        httpURLConnection.setInstanceFollowRedirects(true);
        httpURLConnection.setReadTimeout(60000);
        if (str != null) {
            httpURLConnection.setRequestProperty("User-Agent", str);
        }
        httpURLConnection.setUseCaches(false);
    }

    public static int zzb(Context context, int i) {
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        return zzb(displayMetrics, i);
    }

    public static int zzb(DisplayMetrics displayMetrics, int i) {
        return Math.round(((float) i) / displayMetrics.density);
    }

    public static String zzbc(Context context) {
        ContentResolver contentResolver = context.getContentResolver();
        String string = contentResolver == null ? null : Secure.getString(contentResolver, "android_id");
        if (string == null || zzsg()) {
            string = "emulator";
        }
        return zzde(string);
    }

    @Nullable
    public static String zzbd(Context context) {
        ContentResolver contentResolver = context.getContentResolver();
        return contentResolver == null ? null : Secure.getString(contentResolver, "android_id");
    }

    public static boolean zzbe(Context context) {
        return GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(context, 12451000) == 0;
    }

    public static int zzbf(Context context) {
        return DynamiteModule.getRemoteVersion(context, ModuleDescriptor.MODULE_ID);
    }

    public static int zzbg(Context context) {
        return DynamiteModule.getLocalVersion(context, ModuleDescriptor.MODULE_ID);
    }

    public static boolean zzbh(Context context) {
        int isGooglePlayServicesAvailable = GoogleApiAvailabilityLight.getInstance().isGooglePlayServicesAvailable(context, 12451000);
        return isGooglePlayServicesAvailable == 0 || isGooglePlayServicesAvailable == 2;
    }

    public static boolean zzbi(Context context) {
        if (context.getResources().getConfiguration().orientation != 2) {
            return false;
        }
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return ((int) (((float) displayMetrics.heightPixels) / displayMetrics.density)) < Settings.MAX_DYNAMIC_ACQUISITION;
    }

    @TargetApi(17)
    public static boolean zzbj(Context context) {
        int i;
        int i2;
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        if (PlatformVersion.isAtLeastJellyBeanMR1()) {
            defaultDisplay.getRealMetrics(displayMetrics);
            i = displayMetrics.heightPixels;
            i2 = displayMetrics.widthPixels;
        } else {
            try {
                i = ((Integer) Display.class.getMethod("getRawHeight", new Class[0]).invoke(defaultDisplay, new Object[0])).intValue();
                i2 = ((Integer) Display.class.getMethod("getRawWidth", new Class[0]).invoke(defaultDisplay, new Object[0])).intValue();
            } catch (Exception e) {
                return false;
            }
        }
        defaultDisplay.getMetrics(displayMetrics);
        return displayMetrics.heightPixels == i && displayMetrics.widthPixels == i2;
    }

    public static int zzbk(Context context) {
        int identifier = context.getResources().getIdentifier("navigation_bar_width", "dimen", "android");
        return identifier > 0 ? context.getResources().getDimensionPixelSize(identifier) : 0;
    }

    public static Throwable zzc(Throwable th) {
        if (((Boolean) zzkb.zzik().zzd(zznk.zzauj)).booleanValue()) {
            return th;
        }
        LinkedList linkedList = new LinkedList();
        while (th != null) {
            linkedList.push(th);
            th = th.getCause();
        }
        Throwable th2 = null;
        while (!linkedList.isEmpty()) {
            Throwable th3;
            Throwable th4 = (Throwable) linkedList.pop();
            StackTraceElement[] stackTrace = th4.getStackTrace();
            ArrayList arrayList = new ArrayList();
            arrayList.add(new StackTraceElement(th4.getClass().getName(), "<filtered>", "<filtered>", 1));
            int i = 0;
            for (StackTraceElement stackTraceElement : stackTrace) {
                if (zzdf(stackTraceElement.getClassName())) {
                    arrayList.add(stackTraceElement);
                    i = 1;
                } else {
                    String className = stackTraceElement.getClassName();
                    int i2 = (TextUtils.isEmpty(className) || !(className.startsWith("android.") || className.startsWith("java."))) ? 0 : 1;
                    if (i2 != 0) {
                        arrayList.add(stackTraceElement);
                    } else {
                        arrayList.add(new StackTraceElement("<filtered>", "<filtered>", "<filtered>", 1));
                    }
                }
            }
            if (i != 0) {
                th3 = th2 == null ? new Throwable(th4.getMessage()) : new Throwable(th4.getMessage(), th2);
                th3.setStackTrace((StackTraceElement[]) arrayList.toArray(new StackTraceElement[0]));
            } else {
                th3 = th2;
            }
            th2 = th3;
        }
        return th2;
    }

    public static String zzde(String str) {
        String str2 = null;
        int i = 0;
        while (i < 2) {
            try {
                MessageDigest.getInstance("MD5").update(str.getBytes());
                str2 = String.format(Locale.US, "%032X", new Object[]{new BigInteger(1, r2.digest())});
                break;
            } catch (NoSuchAlgorithmException e) {
                i++;
            } catch (ArithmeticException e2) {
            }
        }
        return str2;
    }

    @VisibleForTesting
    public static boolean zzdf(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (str.startsWith((String) zzkb.zzik().zzd(zznk.zzauk))) {
            return true;
        }
        try {
            return Class.forName(str).isAnnotationPresent(zzadh.class);
        } catch (Throwable e) {
            Throwable th = e;
            String str2 = "Fail to check class type for class ";
            String valueOf = String.valueOf(str);
            zzane.zza(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2), th);
            return false;
        }
    }

    public static boolean zzsg() {
        return Build.DEVICE.startsWith("generic");
    }

    public static boolean zzsh() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public static String zzsi() {
        UUID randomUUID = UUID.randomUUID();
        byte[] toByteArray = BigInteger.valueOf(randomUUID.getLeastSignificantBits()).toByteArray();
        byte[] toByteArray2 = BigInteger.valueOf(randomUUID.getMostSignificantBits()).toByteArray();
        String bigInteger = new BigInteger(1, toByteArray).toString();
        for (int i = 0; i < 2; i++) {
            try {
                MessageDigest instance = MessageDigest.getInstance("MD5");
                instance.update(toByteArray);
                instance.update(toByteArray2);
                Object obj = new byte[8];
                System.arraycopy(instance.digest(), 0, obj, 0, 8);
                bigInteger = new BigInteger(1, obj).toString();
            } catch (NoSuchAlgorithmException e) {
            }
        }
        return bigInteger;
    }

    public final void zza(Context context, @Nullable String str, String str2, Bundle bundle, boolean z) {
        zza(context, null, str2, bundle, true, new zzamv(this));
    }

    public final void zza(ViewGroup viewGroup, zzjn com_google_android_gms_internal_ads_zzjn, String str) {
        zza(viewGroup, com_google_android_gms_internal_ads_zzjn, str, -16777216, -1);
    }

    public final void zza(ViewGroup viewGroup, zzjn com_google_android_gms_internal_ads_zzjn, String str, String str2) {
        zzane.zzdk(str2);
        zza(viewGroup, com_google_android_gms_internal_ads_zzjn, str, (int) SupportMenu.CATEGORY_MASK, -16777216);
    }
}
