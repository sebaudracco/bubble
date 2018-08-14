package com.google.android.gms.internal.ads;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build.VERSION;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.stepleaderdigital.reveal.Reveal;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class zzcz {
    private static final String TAG = zzcz.class.getSimpleName();
    private volatile boolean zzqt = false;
    protected Context zzrt;
    private ExecutorService zzru;
    private DexClassLoader zzrv;
    private zzck zzrw;
    private byte[] zzrx;
    private volatile AdvertisingIdClient zzry = null;
    private Future zzrz = null;
    private boolean zzsa;
    private volatile zzba zzsb = null;
    private Future zzsc = null;
    private zzcc zzsd;
    private boolean zzse = false;
    private boolean zzsf = false;
    private Map<Pair<String, String>, zzeg> zzsg;
    private boolean zzsh = false;
    private boolean zzsi = true;
    private boolean zzsj = false;

    final class zza extends BroadcastReceiver {
        private final /* synthetic */ zzcz zzsl;

        private zza(zzcz com_google_android_gms_internal_ads_zzcz) {
            this.zzsl = com_google_android_gms_internal_ads_zzcz;
        }

        public final void onReceive(Context context, Intent intent) {
            if ("android.intent.action.USER_PRESENT".equals(intent.getAction())) {
                this.zzsl.zzsi = true;
            } else if ("android.intent.action.SCREEN_OFF".equals(intent.getAction())) {
                this.zzsl.zzsi = false;
            }
        }
    }

    private zzcz(Context context) {
        boolean z = true;
        Context applicationContext = context.getApplicationContext();
        if (applicationContext == null) {
            z = false;
        }
        this.zzsa = z;
        if (this.zzsa) {
            context = applicationContext;
        }
        this.zzrt = context;
        this.zzsg = new HashMap();
    }

    public static zzcz zza(Context context, String str, String str2, boolean z) {
        File file;
        boolean z2 = true;
        zzcz com_google_android_gms_internal_ads_zzcz = new zzcz(context);
        try {
            com_google_android_gms_internal_ads_zzcz.zzru = Executors.newCachedThreadPool(new zzda());
            com_google_android_gms_internal_ads_zzcz.zzqt = z;
            if (z) {
                com_google_android_gms_internal_ads_zzcz.zzrz = com_google_android_gms_internal_ads_zzcz.zzru.submit(new zzdb(com_google_android_gms_internal_ads_zzcz));
            }
            com_google_android_gms_internal_ads_zzcz.zzru.execute(new zzdd(com_google_android_gms_internal_ads_zzcz));
            try {
                GoogleApiAvailabilityLight instance = GoogleApiAvailabilityLight.getInstance();
                com_google_android_gms_internal_ads_zzcz.zzse = instance.getApkVersion(com_google_android_gms_internal_ads_zzcz.zzrt) > 0;
                if (instance.isGooglePlayServicesAvailable(com_google_android_gms_internal_ads_zzcz.zzrt) != 0) {
                    z2 = false;
                }
                com_google_android_gms_internal_ads_zzcz.zzsf = z2;
            } catch (Throwable th) {
            }
            com_google_android_gms_internal_ads_zzcz.zza(0, true);
            if (zzdg.isMainThread()) {
                if (((Boolean) zzkb.zzik().zzd(zznk.zzbaz)).booleanValue()) {
                    throw new IllegalStateException("Task Context initialization must not be called from the UI thread.");
                }
            }
            com_google_android_gms_internal_ads_zzcz.zzrw = new zzck(null);
            com_google_android_gms_internal_ads_zzcz.zzrx = com_google_android_gms_internal_ads_zzcz.zzrw.zzl(str);
            File cacheDir = com_google_android_gms_internal_ads_zzcz.zzrt.getCacheDir();
            if (cacheDir == null) {
                cacheDir = com_google_android_gms_internal_ads_zzcz.zzrt.getDir("dex", 0);
                if (cacheDir == null) {
                    throw new zzcw();
                }
            }
            File file2 = cacheDir;
            String str3 = "1521499837408";
            file = new File(String.format("%s/%s.jar", new Object[]{file2, str3}));
            if (!file.exists()) {
                byte[] zza = com_google_android_gms_internal_ads_zzcz.zzrw.zza(com_google_android_gms_internal_ads_zzcz.zzrx, str2);
                file.createNewFile();
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(zza, 0, zza.length);
                fileOutputStream.close();
            }
            com_google_android_gms_internal_ads_zzcz.zzb(file2, str3);
            com_google_android_gms_internal_ads_zzcz.zzrv = new DexClassLoader(file.getAbsolutePath(), file2.getAbsolutePath(), null, com_google_android_gms_internal_ads_zzcz.zzrt.getClassLoader());
            zzb(file);
            com_google_android_gms_internal_ads_zzcz.zza(file2, str3);
            zzm(String.format("%s/%s.dex", new Object[]{file2, str3}));
            if (!com_google_android_gms_internal_ads_zzcz.zzsj) {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction("android.intent.action.USER_PRESENT");
                intentFilter.addAction("android.intent.action.SCREEN_OFF");
                com_google_android_gms_internal_ads_zzcz.zzrt.registerReceiver(new zza(), intentFilter);
                com_google_android_gms_internal_ads_zzcz.zzsj = true;
            }
            com_google_android_gms_internal_ads_zzcz.zzsd = new zzcc(com_google_android_gms_internal_ads_zzcz);
            com_google_android_gms_internal_ads_zzcz.zzsh = true;
        } catch (Throwable e) {
            throw new zzcw(e);
        } catch (Throwable e2) {
            throw new zzcw(e2);
        } catch (Throwable e22) {
            throw new zzcw(e22);
        } catch (Throwable e222) {
            throw new zzcw(e222);
        } catch (Throwable e2222) {
            throw new zzcw(e2222);
        } catch (zzcw e3) {
        } catch (Throwable th2) {
            zzb(file);
            com_google_android_gms_internal_ads_zzcz.zza(file2, str3);
            zzm(String.format("%s/%s.dex", new Object[]{file2, str3}));
        }
        return com_google_android_gms_internal_ads_zzcz;
    }

    private final void zza(File file, String str) {
        FileOutputStream fileOutputStream;
        FileInputStream fileInputStream;
        Throwable th;
        FileOutputStream fileOutputStream2 = null;
        File file2 = new File(String.format("%s/%s.tmp", new Object[]{file, str}));
        if (!file2.exists()) {
            File file3 = new File(String.format("%s/%s.dex", new Object[]{file, str}));
            if (file3.exists()) {
                long length = file3.length();
                if (length > 0) {
                    byte[] bArr = new byte[((int) length)];
                    FileInputStream fileInputStream2;
                    try {
                        fileInputStream2 = new FileInputStream(file3);
                        try {
                            if (fileInputStream2.read(bArr) <= 0) {
                                try {
                                    fileInputStream2.close();
                                } catch (IOException e) {
                                }
                                zzb(file3);
                                return;
                            }
                            zzbfi com_google_android_gms_internal_ads_zzbe = new zzbe();
                            com_google_android_gms_internal_ads_zzbe.zzgs = VERSION.SDK.getBytes();
                            com_google_android_gms_internal_ads_zzbe.zzgr = str.getBytes();
                            bArr = this.zzrw.zzb(this.zzrx, bArr).getBytes();
                            com_google_android_gms_internal_ads_zzbe.data = bArr;
                            com_google_android_gms_internal_ads_zzbe.zzgq = zzbk.zzb(bArr);
                            file2.createNewFile();
                            fileOutputStream = new FileOutputStream(file2);
                            try {
                                byte[] zzb = zzbfi.zzb(com_google_android_gms_internal_ads_zzbe);
                                fileOutputStream.write(zzb, 0, zzb.length);
                                fileOutputStream.close();
                                try {
                                    fileInputStream2.close();
                                } catch (IOException e2) {
                                }
                                try {
                                    fileOutputStream.close();
                                } catch (IOException e3) {
                                }
                                zzb(file3);
                            } catch (IOException e4) {
                                fileInputStream = fileInputStream2;
                                if (fileInputStream != null) {
                                    try {
                                        fileInputStream.close();
                                    } catch (IOException e5) {
                                    }
                                }
                                if (fileOutputStream != null) {
                                    try {
                                        fileOutputStream.close();
                                    } catch (IOException e6) {
                                    }
                                }
                                zzb(file3);
                            } catch (NoSuchAlgorithmException e7) {
                                fileInputStream = fileInputStream2;
                                if (fileInputStream != null) {
                                    fileInputStream.close();
                                }
                                if (fileOutputStream != null) {
                                    fileOutputStream.close();
                                }
                                zzb(file3);
                            } catch (zzcl e8) {
                                fileInputStream = fileInputStream2;
                                if (fileInputStream != null) {
                                    fileInputStream.close();
                                }
                                if (fileOutputStream != null) {
                                    fileOutputStream.close();
                                }
                                zzb(file3);
                            } catch (Throwable th2) {
                                Throwable th3 = th2;
                                fileOutputStream2 = fileOutputStream;
                                th = th3;
                                if (fileInputStream2 != null) {
                                    try {
                                        fileInputStream2.close();
                                    } catch (IOException e9) {
                                    }
                                }
                                if (fileOutputStream2 != null) {
                                    try {
                                        fileOutputStream2.close();
                                    } catch (IOException e10) {
                                    }
                                }
                                zzb(file3);
                                throw th;
                            }
                        } catch (IOException e11) {
                            fileOutputStream = null;
                            fileInputStream = fileInputStream2;
                            if (fileInputStream != null) {
                                fileInputStream.close();
                            }
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                            zzb(file3);
                        } catch (NoSuchAlgorithmException e12) {
                            fileOutputStream = null;
                            fileInputStream = fileInputStream2;
                            if (fileInputStream != null) {
                                fileInputStream.close();
                            }
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                            zzb(file3);
                        } catch (zzcl e13) {
                            fileOutputStream = null;
                            fileInputStream = fileInputStream2;
                            if (fileInputStream != null) {
                                fileInputStream.close();
                            }
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                            zzb(file3);
                        } catch (Throwable th4) {
                            th = th4;
                            if (fileInputStream2 != null) {
                                fileInputStream2.close();
                            }
                            if (fileOutputStream2 != null) {
                                fileOutputStream2.close();
                            }
                            zzb(file3);
                            throw th;
                        }
                    } catch (IOException e14) {
                        fileOutputStream = null;
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        zzb(file3);
                    } catch (NoSuchAlgorithmException e15) {
                        fileOutputStream = null;
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        zzb(file3);
                    } catch (zzcl e16) {
                        fileOutputStream = null;
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        zzb(file3);
                    } catch (Throwable th5) {
                        th = th5;
                        fileInputStream2 = null;
                        if (fileInputStream2 != null) {
                            fileInputStream2.close();
                        }
                        if (fileOutputStream2 != null) {
                            fileOutputStream2.close();
                        }
                        zzb(file3);
                        throw th;
                    }
                }
            }
        }
    }

    private static boolean zza(int i, zzba com_google_android_gms_internal_ads_zzba) {
        if (i < 4) {
            if (com_google_android_gms_internal_ads_zzba == null) {
                return true;
            }
            if (((Boolean) zzkb.zzik().zzd(zznk.zzbbc)).booleanValue() && (com_google_android_gms_internal_ads_zzba.zzcx == null || com_google_android_gms_internal_ads_zzba.zzcx.equals("0000000000000000000000000000000000000000000000000000000000000000"))) {
                return true;
            }
            if (((Boolean) zzkb.zzik().zzd(zznk.zzbbd)).booleanValue() && (com_google_android_gms_internal_ads_zzba.zzfn == null || com_google_android_gms_internal_ads_zzba.zzfn.zzgl == null || com_google_android_gms_internal_ads_zzba.zzfn.zzgl.longValue() == -2)) {
                return true;
            }
        }
        return false;
    }

    private final void zzal() {
        try {
            if (this.zzry == null && this.zzsa) {
                AdvertisingIdClient advertisingIdClient = new AdvertisingIdClient(this.zzrt);
                advertisingIdClient.start();
                this.zzry = advertisingIdClient;
            }
        } catch (GooglePlayServicesNotAvailableException e) {
            this.zzry = null;
        } catch (IOException e2) {
            this.zzry = null;
        } catch (GooglePlayServicesRepairableException e3) {
            this.zzry = null;
        }
    }

    @VisibleForTesting
    private final zzba zzam() {
        zzba com_google_android_gms_internal_ads_zzba = null;
        try {
            com_google_android_gms_internal_ads_zzba = zzatq.zzl(this.zzrt, this.zzrt.getPackageName(), Integer.toString(this.zzrt.getPackageManager().getPackageInfo(this.zzrt.getPackageName(), 0).versionCode));
        } catch (Throwable th) {
        }
        return com_google_android_gms_internal_ads_zzba;
    }

    private static void zzb(File file) {
        if (file.exists()) {
            file.delete();
            return;
        }
        Log.d(TAG, String.format("File %s not found. No need for deletion", new Object[]{file.getAbsolutePath()}));
    }

    private final boolean zzb(File file, String str) {
        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream;
        FileInputStream fileInputStream2;
        Throwable th;
        FileOutputStream fileOutputStream2 = null;
        File file2 = new File(String.format("%s/%s.tmp", new Object[]{file, str}));
        if (!file2.exists()) {
            return false;
        }
        File file3 = new File(String.format("%s/%s.dex", new Object[]{file, str}));
        if (file3.exists()) {
            return false;
        }
        try {
            long length = file2.length();
            if (length <= 0) {
                zzb(file2);
                return false;
            }
            byte[] bArr = new byte[((int) length)];
            fileInputStream = new FileInputStream(file2);
            try {
                if (fileInputStream.read(bArr) <= 0) {
                    Log.d(TAG, "Cannot read the cache data.");
                    zzb(file2);
                    try {
                        fileInputStream.close();
                    } catch (IOException e) {
                    }
                    return false;
                }
                zzbe com_google_android_gms_internal_ads_zzbe = (zzbe) zzbfi.zza(new zzbe(), bArr);
                if (str.equals(new String(com_google_android_gms_internal_ads_zzbe.zzgr)) && Arrays.equals(com_google_android_gms_internal_ads_zzbe.zzgq, zzbk.zzb(com_google_android_gms_internal_ads_zzbe.data)) && Arrays.equals(com_google_android_gms_internal_ads_zzbe.zzgs, VERSION.SDK.getBytes())) {
                    byte[] zza = this.zzrw.zza(this.zzrx, new String(com_google_android_gms_internal_ads_zzbe.data));
                    file3.createNewFile();
                    fileOutputStream = new FileOutputStream(file3);
                    try {
                        fileOutputStream.write(zza, 0, zza.length);
                        try {
                            fileInputStream.close();
                        } catch (IOException e2) {
                        }
                        try {
                            fileOutputStream.close();
                        } catch (IOException e3) {
                        }
                        return true;
                    } catch (IOException e4) {
                        fileInputStream2 = fileInputStream;
                        if (fileInputStream2 != null) {
                            try {
                                fileInputStream2.close();
                            } catch (IOException e5) {
                            }
                        }
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e6) {
                            }
                        }
                        return false;
                    } catch (NoSuchAlgorithmException e7) {
                        fileInputStream2 = fileInputStream;
                        if (fileInputStream2 != null) {
                            fileInputStream2.close();
                        }
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        return false;
                    } catch (zzcl e8) {
                        fileInputStream2 = fileInputStream;
                        if (fileInputStream2 != null) {
                            fileInputStream2.close();
                        }
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        return false;
                    } catch (Throwable th2) {
                        fileOutputStream2 = fileOutputStream;
                        th = th2;
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException e9) {
                            }
                        }
                        if (fileOutputStream2 != null) {
                            try {
                                fileOutputStream2.close();
                            } catch (IOException e10) {
                            }
                        }
                        throw th;
                    }
                }
                zzb(file2);
                try {
                    fileInputStream.close();
                } catch (IOException e11) {
                }
                return false;
            } catch (IOException e12) {
                fileOutputStream = null;
                fileInputStream2 = fileInputStream;
                if (fileInputStream2 != null) {
                    fileInputStream2.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                return false;
            } catch (NoSuchAlgorithmException e13) {
                fileOutputStream = null;
                fileInputStream2 = fileInputStream;
                if (fileInputStream2 != null) {
                    fileInputStream2.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                return false;
            } catch (zzcl e14) {
                fileOutputStream = null;
                fileInputStream2 = fileInputStream;
                if (fileInputStream2 != null) {
                    fileInputStream2.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                return false;
            } catch (Throwable th3) {
                th = th3;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (fileOutputStream2 != null) {
                    fileOutputStream2.close();
                }
                throw th;
            }
        } catch (IOException e15) {
            fileOutputStream = null;
            fileInputStream2 = null;
            if (fileInputStream2 != null) {
                fileInputStream2.close();
            }
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            return false;
        } catch (NoSuchAlgorithmException e16) {
            fileOutputStream = null;
            fileInputStream2 = null;
            if (fileInputStream2 != null) {
                fileInputStream2.close();
            }
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            return false;
        } catch (zzcl e17) {
            fileOutputStream = null;
            fileInputStream2 = null;
            if (fileInputStream2 != null) {
                fileInputStream2.close();
            }
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            return false;
        } catch (Throwable th4) {
            th = th4;
            fileInputStream = null;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            if (fileOutputStream2 != null) {
                fileOutputStream2.close();
            }
            throw th;
        }
    }

    private static void zzm(String str) {
        zzb(new File(str));
    }

    public final Context getContext() {
        return this.zzrt;
    }

    public final boolean isInitialized() {
        return this.zzsh;
    }

    public final Method zza(String str, String str2) {
        zzeg com_google_android_gms_internal_ads_zzeg = (zzeg) this.zzsg.get(new Pair(str, str2));
        return com_google_android_gms_internal_ads_zzeg == null ? null : com_google_android_gms_internal_ads_zzeg.zzaw();
    }

    @VisibleForTesting
    final void zza(int i, boolean z) {
        if (this.zzsf) {
            Future submit = this.zzru.submit(new zzdc(this, i, z));
            if (i == 0) {
                this.zzsc = submit;
            }
        }
    }

    public final boolean zza(String str, String str2, Class<?>... clsArr) {
        if (this.zzsg.containsKey(new Pair(str, str2))) {
            return false;
        }
        this.zzsg.put(new Pair(str, str2), new zzeg(this, str, str2, clsArr));
        return true;
    }

    public final ExecutorService zzab() {
        return this.zzru;
    }

    public final DexClassLoader zzac() {
        return this.zzrv;
    }

    public final zzck zzad() {
        return this.zzrw;
    }

    public final byte[] zzae() {
        return this.zzrx;
    }

    public final boolean zzaf() {
        return this.zzse;
    }

    public final zzcc zzag() {
        return this.zzsd;
    }

    public final boolean zzah() {
        return this.zzsf;
    }

    public final boolean zzai() {
        return this.zzsi;
    }

    public final zzba zzaj() {
        return this.zzsb;
    }

    public final Future zzak() {
        return this.zzsc;
    }

    public final AdvertisingIdClient zzan() {
        if (!this.zzqt) {
            return null;
        }
        if (this.zzry != null) {
            return this.zzry;
        }
        if (this.zzrz != null) {
            try {
                this.zzrz.get(Reveal.CHECK_DELAY, TimeUnit.MILLISECONDS);
                this.zzrz = null;
            } catch (InterruptedException e) {
            } catch (ExecutionException e2) {
            } catch (TimeoutException e3) {
                this.zzrz.cancel(true);
            }
        }
        return this.zzry;
    }

    @VisibleForTesting
    final zzba zzb(int i, boolean z) {
        if (i > 0 && z) {
            try {
                Thread.sleep((long) (i * 1000));
            } catch (InterruptedException e) {
            }
        }
        return zzam();
    }

    public final int zzx() {
        return this.zzsd != null ? zzcc.zzx() : Integer.MIN_VALUE;
    }
}
