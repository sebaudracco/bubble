package com.google.android.gms.internal.ads;

import android.os.SystemClock;
import android.text.TextUtils;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public final class zzam implements zzb {
    private final Map<String, zzan> zzbw;
    private long zzbx;
    private final File zzby;
    private final int zzbz;

    public zzam(File file) {
        this(file, 5242880);
    }

    private zzam(File file, int i) {
        this.zzbw = new LinkedHashMap(16, 0.75f, true);
        this.zzbx = 0;
        this.zzby = file;
        this.zzbz = 5242880;
    }

    private final synchronized void remove(String str) {
        boolean delete = zze(str).delete();
        removeEntry(str);
        if (!delete) {
            zzaf.m8608d("Could not delete cache entry for key=%s, filename=%s", str, zzd(str));
        }
    }

    private final void removeEntry(String str) {
        zzan com_google_android_gms_internal_ads_zzan = (zzan) this.zzbw.remove(str);
        if (com_google_android_gms_internal_ads_zzan != null) {
            this.zzbx -= com_google_android_gms_internal_ads_zzan.zzca;
        }
    }

    private static int zza(InputStream inputStream) throws IOException {
        int read = inputStream.read();
        if (read != -1) {
            return read;
        }
        throw new EOFException();
    }

    private static InputStream zza(File file) throws FileNotFoundException {
        return new FileInputStream(file);
    }

    static String zza(zzao com_google_android_gms_internal_ads_zzao) throws IOException {
        return new String(zza(com_google_android_gms_internal_ads_zzao, zzc(com_google_android_gms_internal_ads_zzao)), "UTF-8");
    }

    static void zza(OutputStream outputStream, int i) throws IOException {
        outputStream.write(i & 255);
        outputStream.write((i >> 8) & 255);
        outputStream.write((i >> 16) & 255);
        outputStream.write(i >>> 24);
    }

    static void zza(OutputStream outputStream, long j) throws IOException {
        outputStream.write((byte) ((int) j));
        outputStream.write((byte) ((int) (j >>> 8)));
        outputStream.write((byte) ((int) (j >>> 16)));
        outputStream.write((byte) ((int) (j >>> 24)));
        outputStream.write((byte) ((int) (j >>> 32)));
        outputStream.write((byte) ((int) (j >>> 40)));
        outputStream.write((byte) ((int) (j >>> 48)));
        outputStream.write((byte) ((int) (j >>> 56)));
    }

    static void zza(OutputStream outputStream, String str) throws IOException {
        byte[] bytes = str.getBytes("UTF-8");
        zza(outputStream, (long) bytes.length);
        outputStream.write(bytes, 0, bytes.length);
    }

    private final void zza(String str, zzan com_google_android_gms_internal_ads_zzan) {
        if (this.zzbw.containsKey(str)) {
            zzan com_google_android_gms_internal_ads_zzan2 = (zzan) this.zzbw.get(str);
            this.zzbx = (com_google_android_gms_internal_ads_zzan.zzca - com_google_android_gms_internal_ads_zzan2.zzca) + this.zzbx;
        } else {
            this.zzbx += com_google_android_gms_internal_ads_zzan.zzca;
        }
        this.zzbw.put(str, com_google_android_gms_internal_ads_zzan);
    }

    private static byte[] zza(zzao com_google_android_gms_internal_ads_zzao, long j) throws IOException {
        long zzo = com_google_android_gms_internal_ads_zzao.zzo();
        if (j < 0 || j > zzo || ((long) ((int) j)) != j) {
            throw new IOException("streamToBytes length=" + j + ", maxLength=" + zzo);
        }
        byte[] bArr = new byte[((int) j)];
        new DataInputStream(com_google_android_gms_internal_ads_zzao).readFully(bArr);
        return bArr;
    }

    static int zzb(InputStream inputStream) throws IOException {
        return (((zza(inputStream) | 0) | (zza(inputStream) << 8)) | (zza(inputStream) << 16)) | (zza(inputStream) << 24);
    }

    static List<zzl> zzb(zzao com_google_android_gms_internal_ads_zzao) throws IOException {
        int zzb = zzb((InputStream) com_google_android_gms_internal_ads_zzao);
        List<zzl> emptyList = zzb == 0 ? Collections.emptyList() : new ArrayList(zzb);
        for (int i = 0; i < zzb; i++) {
            emptyList.add(new zzl(zza(com_google_android_gms_internal_ads_zzao).intern(), zza(com_google_android_gms_internal_ads_zzao).intern()));
        }
        return emptyList;
    }

    static long zzc(InputStream inputStream) throws IOException {
        return (((((((0 | (((long) zza(inputStream)) & 255)) | ((((long) zza(inputStream)) & 255) << 8)) | ((((long) zza(inputStream)) & 255) << 16)) | ((((long) zza(inputStream)) & 255) << 24)) | ((((long) zza(inputStream)) & 255) << 32)) | ((((long) zza(inputStream)) & 255) << 40)) | ((((long) zza(inputStream)) & 255) << 48)) | ((((long) zza(inputStream)) & 255) << 56);
    }

    private static String zzd(String str) {
        int length = str.length() / 2;
        String valueOf = String.valueOf(String.valueOf(str.substring(0, length).hashCode()));
        String valueOf2 = String.valueOf(String.valueOf(str.substring(length).hashCode()));
        return valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
    }

    private final File zze(String str) {
        return new File(this.zzby, zzd(str));
    }

    public final synchronized zzc zza(String str) {
        zzc com_google_android_gms_internal_ads_zzc;
        zzan com_google_android_gms_internal_ads_zzan = (zzan) this.zzbw.get(str);
        if (com_google_android_gms_internal_ads_zzan == null) {
            com_google_android_gms_internal_ads_zzc = null;
        } else {
            File zze = zze(str);
            zzao com_google_android_gms_internal_ads_zzao;
            try {
                com_google_android_gms_internal_ads_zzao = new zzao(new BufferedInputStream(zza(zze)), zze.length());
                if (TextUtils.equals(str, zzan.zzc(com_google_android_gms_internal_ads_zzao).zzcb)) {
                    byte[] zza = zza(com_google_android_gms_internal_ads_zzao, com_google_android_gms_internal_ads_zzao.zzo());
                    zzc com_google_android_gms_internal_ads_zzc2 = new zzc();
                    com_google_android_gms_internal_ads_zzc2.data = zza;
                    com_google_android_gms_internal_ads_zzc2.zza = com_google_android_gms_internal_ads_zzan.zza;
                    com_google_android_gms_internal_ads_zzc2.zzb = com_google_android_gms_internal_ads_zzan.zzb;
                    com_google_android_gms_internal_ads_zzc2.zzc = com_google_android_gms_internal_ads_zzan.zzc;
                    com_google_android_gms_internal_ads_zzc2.zzd = com_google_android_gms_internal_ads_zzan.zzd;
                    com_google_android_gms_internal_ads_zzc2.zze = com_google_android_gms_internal_ads_zzan.zze;
                    List<zzl> list = com_google_android_gms_internal_ads_zzan.zzg;
                    Map treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
                    for (zzl com_google_android_gms_internal_ads_zzl : list) {
                        treeMap.put(com_google_android_gms_internal_ads_zzl.getName(), com_google_android_gms_internal_ads_zzl.getValue());
                    }
                    com_google_android_gms_internal_ads_zzc2.zzf = treeMap;
                    com_google_android_gms_internal_ads_zzc2.zzg = Collections.unmodifiableList(com_google_android_gms_internal_ads_zzan.zzg);
                    com_google_android_gms_internal_ads_zzao.close();
                    com_google_android_gms_internal_ads_zzc = com_google_android_gms_internal_ads_zzc2;
                } else {
                    zzaf.m8608d("%s: key=%s, found=%s", zze.getAbsolutePath(), str, zzan.zzc(com_google_android_gms_internal_ads_zzao).zzcb);
                    removeEntry(str);
                    com_google_android_gms_internal_ads_zzao.close();
                    com_google_android_gms_internal_ads_zzc = null;
                }
            } catch (IOException e) {
                zzaf.m8608d("%s: %s", zze.getAbsolutePath(), e.toString());
                remove(str);
                com_google_android_gms_internal_ads_zzc = null;
            } catch (Throwable th) {
                com_google_android_gms_internal_ads_zzao.close();
            }
        }
        return com_google_android_gms_internal_ads_zzc;
    }

    public final synchronized void zza() {
        zzao com_google_android_gms_internal_ads_zzao;
        if (this.zzby.exists()) {
            File[] listFiles = this.zzby.listFiles();
            if (listFiles != null) {
                for (File file : listFiles) {
                    try {
                        long length = file.length();
                        com_google_android_gms_internal_ads_zzao = new zzao(new BufferedInputStream(zza(file)), length);
                        zzan zzc = zzan.zzc(com_google_android_gms_internal_ads_zzao);
                        zzc.zzca = length;
                        zza(zzc.zzcb, zzc);
                        com_google_android_gms_internal_ads_zzao.close();
                    } catch (IOException e) {
                        file.delete();
                    } catch (Throwable th) {
                        com_google_android_gms_internal_ads_zzao.close();
                    }
                }
            }
        } else if (!this.zzby.mkdirs()) {
            zzaf.m8609e("Unable to create cache dir %s", this.zzby.getAbsolutePath());
        }
    }

    public final synchronized void zza(String str, zzc com_google_android_gms_internal_ads_zzc) {
        int i = 0;
        synchronized (this) {
            int length = com_google_android_gms_internal_ads_zzc.data.length;
            if (this.zzbx + ((long) length) >= ((long) this.zzbz)) {
                int i2;
                if (zzaf.DEBUG) {
                    zzaf.m8610v("Pruning old cache entries.", new Object[0]);
                }
                long j = this.zzbx;
                long elapsedRealtime = SystemClock.elapsedRealtime();
                Iterator it = this.zzbw.entrySet().iterator();
                while (it.hasNext()) {
                    zzan com_google_android_gms_internal_ads_zzan = (zzan) ((Entry) it.next()).getValue();
                    if (zze(com_google_android_gms_internal_ads_zzan.zzcb).delete()) {
                        this.zzbx -= com_google_android_gms_internal_ads_zzan.zzca;
                    } else {
                        zzaf.m8608d("Could not delete cache entry for key=%s, filename=%s", com_google_android_gms_internal_ads_zzan.zzcb, zzd(com_google_android_gms_internal_ads_zzan.zzcb));
                    }
                    it.remove();
                    i2 = i + 1;
                    if (((float) (this.zzbx + ((long) length))) < ((float) this.zzbz) * 0.9f) {
                        break;
                    }
                    i = i2;
                }
                i2 = i;
                if (zzaf.DEBUG) {
                    zzaf.m8610v("pruned %d files, %d bytes, %d ms", Integer.valueOf(i2), Long.valueOf(this.zzbx - j), Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
                }
            }
            File zze = zze(str);
            try {
                OutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(zze));
                zzan com_google_android_gms_internal_ads_zzan2 = new zzan(str, com_google_android_gms_internal_ads_zzc);
                if (com_google_android_gms_internal_ads_zzan2.zza(bufferedOutputStream)) {
                    bufferedOutputStream.write(com_google_android_gms_internal_ads_zzc.data);
                    bufferedOutputStream.close();
                    zza(str, com_google_android_gms_internal_ads_zzan2);
                } else {
                    bufferedOutputStream.close();
                    zzaf.m8608d("Failed to write header for %s", zze.getAbsolutePath());
                    throw new IOException();
                }
            } catch (IOException e) {
                if (!zze.delete()) {
                    zzaf.m8608d("Could not clean up file %s", zze.getAbsolutePath());
                }
            }
        }
    }
}
