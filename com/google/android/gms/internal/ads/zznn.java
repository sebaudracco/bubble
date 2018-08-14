package com.google.android.gms.internal.ads;

import android.content.Context;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.appnext.base.p023b.C1042c;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zznn {
    @VisibleForTesting
    private Context mContext;
    @VisibleForTesting
    private String zzaej;
    @VisibleForTesting
    private String zzbfx;
    @VisibleForTesting
    private BlockingQueue<zznx> zzbfz = new ArrayBlockingQueue(100);
    @VisibleForTesting
    private ExecutorService zzbga;
    @VisibleForTesting
    private LinkedHashMap<String, String> zzbgb = new LinkedHashMap();
    @VisibleForTesting
    private Map<String, zznr> zzbgc = new HashMap();
    private AtomicBoolean zzbgd;
    private File zzbge;

    private final void zzjf() {
        FileOutputStream fileOutputStream;
        Throwable e;
        while (true) {
            try {
                zznx com_google_android_gms_internal_ads_zznx = (zznx) this.zzbfz.take();
                Object zzjk = com_google_android_gms_internal_ads_zznx.zzjk();
                if (!TextUtils.isEmpty(zzjk)) {
                    Map zza = zza(this.zzbgb, com_google_android_gms_internal_ads_zznx.zzjl());
                    Builder buildUpon = Uri.parse(this.zzbfx).buildUpon();
                    for (Entry entry : zza.entrySet()) {
                        buildUpon.appendQueryParameter((String) entry.getKey(), (String) entry.getValue());
                    }
                    StringBuilder stringBuilder = new StringBuilder(buildUpon.build().toString());
                    stringBuilder.append("&it=").append(zzjk);
                    String stringBuilder2 = stringBuilder.toString();
                    if (this.zzbgd.get()) {
                        File file = this.zzbge;
                        if (file != null) {
                            try {
                                fileOutputStream = new FileOutputStream(file, true);
                                try {
                                    fileOutputStream.write(stringBuilder2.getBytes());
                                    fileOutputStream.write(10);
                                    try {
                                        fileOutputStream.close();
                                    } catch (Throwable e2) {
                                        zzane.zzc("CsiReporter: Cannot close file: sdk_csi_data.txt.", e2);
                                    }
                                } catch (IOException e3) {
                                    e2 = e3;
                                    try {
                                        zzane.zzc("CsiReporter: Cannot write to file: sdk_csi_data.txt.", e2);
                                        if (fileOutputStream != null) {
                                            try {
                                                fileOutputStream.close();
                                            } catch (Throwable e22) {
                                                zzane.zzc("CsiReporter: Cannot close file: sdk_csi_data.txt.", e22);
                                            }
                                        }
                                    } catch (Throwable th) {
                                        e22 = th;
                                    }
                                }
                            } catch (IOException e4) {
                                e22 = e4;
                                fileOutputStream = null;
                                zzane.zzc("CsiReporter: Cannot write to file: sdk_csi_data.txt.", e22);
                                if (fileOutputStream != null) {
                                    fileOutputStream.close();
                                }
                            } catch (Throwable th2) {
                                e22 = th2;
                                fileOutputStream = null;
                            }
                        } else {
                            zzane.zzdk("CsiReporter: File doesn't exists. Cannot write CSI data to file.");
                        }
                    } else {
                        zzbv.zzek();
                        zzakk.zzd(this.mContext, this.zzaej, stringBuilder2);
                    }
                }
            } catch (Throwable e222) {
                zzane.zzc("CsiReporter:reporter interrupted", e222);
                return;
            }
        }
        if (fileOutputStream != null) {
            try {
                fileOutputStream.close();
            } catch (Throwable e5) {
                zzane.zzc("CsiReporter: Cannot close file: sdk_csi_data.txt.", e5);
            }
        }
        throw e222;
        throw e222;
    }

    final Map<String, String> zza(Map<String, String> map, @Nullable Map<String, String> map2) {
        Map<String, String> linkedHashMap = new LinkedHashMap(map);
        if (map2 == null) {
            return linkedHashMap;
        }
        for (Entry entry : map2.entrySet()) {
            String str = (String) entry.getKey();
            String str2 = (String) linkedHashMap.get(str);
            linkedHashMap.put(str, zzal(str).zzd(str2, (String) entry.getValue()));
        }
        return linkedHashMap;
    }

    public final void zza(Context context, String str, String str2, Map<String, String> map) {
        this.mContext = context;
        this.zzaej = str;
        this.zzbfx = str2;
        this.zzbgd = new AtomicBoolean(false);
        this.zzbgd.set(((Boolean) zzkb.zzik().zzd(zznk.zzawj)).booleanValue());
        if (this.zzbgd.get()) {
            File externalStorageDirectory = Environment.getExternalStorageDirectory();
            if (externalStorageDirectory != null) {
                this.zzbge = new File(externalStorageDirectory, "sdk_csi_data.txt");
            }
        }
        for (Entry entry : map.entrySet()) {
            this.zzbgb.put((String) entry.getKey(), (String) entry.getValue());
        }
        this.zzbga = Executors.newSingleThreadExecutor();
        this.zzbga.execute(new zzno(this));
        this.zzbgc.put(C1042c.jL, zznr.zzbgh);
        this.zzbgc.put("ad_format", zznr.zzbgh);
        this.zzbgc.put("e", zznr.zzbgi);
    }

    public final boolean zza(zznx com_google_android_gms_internal_ads_zznx) {
        return this.zzbfz.offer(com_google_android_gms_internal_ads_zznx);
    }

    public final zznr zzal(String str) {
        zznr com_google_android_gms_internal_ads_zznr = (zznr) this.zzbgc.get(str);
        return com_google_android_gms_internal_ads_zznr != null ? com_google_android_gms_internal_ads_zznr : zznr.zzbgg;
    }

    public final void zzg(@Nullable List<String> list) {
        if (list != null && !list.isEmpty()) {
            this.zzbgb.put("e", TextUtils.join(",", list));
        }
    }
}
