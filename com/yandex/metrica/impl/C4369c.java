package com.yandex.metrica.impl;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import android.util.Base64;
import com.yandex.metrica.impl.utils.C4525g;
import com.yandex.metrica.impl.utils.C4528i;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

public class C4369c {
    public static final long f11835a = TimeUnit.SECONDS.toMillis(15);
    private final Context f11836b;
    private long f11837c = 0;

    public C4369c(Context context) {
        this.f11836b = context;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized java.util.HashMap<java.lang.String, java.lang.String> m15041a() {
        /*
        r11 = this;
        r5 = 0;
        r4 = 1;
        r0 = 0;
        monitor-enter(r11);
        r1 = r11.f11836b;	 Catch:{ all -> 0x00e4 }
        r2 = "b_meta.dat";
        r1 = r1.getFileStreamPath(r2);	 Catch:{ all -> 0x00e4 }
        r1 = r1.getAbsolutePath();	 Catch:{ all -> 0x00e4 }
        r6 = new java.io.File;	 Catch:{ Exception -> 0x00d6, all -> 0x00e7 }
        r6.<init>(r1);	 Catch:{ Exception -> 0x00d6, all -> 0x00e7 }
        r2 = r6.exists();	 Catch:{ Exception -> 0x00d6, all -> 0x00e7 }
        if (r2 != 0) goto L_0x0040;
    L_0x001c:
        r6.createNewFile();	 Catch:{ Exception -> 0x00d6, all -> 0x00e7 }
        r2 = 1;
        r3 = 0;
        r6.setReadable(r2, r3);	 Catch:{ Exception -> 0x00d6, all -> 0x00e7 }
        r2 = r11.f11836b;	 Catch:{ Exception -> 0x010d, all -> 0x00e7 }
        r3 = "browsers.dat";
        r2 = r2.getFileStreamPath(r3);	 Catch:{ Exception -> 0x010d, all -> 0x00e7 }
        r2 = r2.getAbsoluteFile();	 Catch:{ Exception -> 0x010d, all -> 0x00e7 }
        r3 = r2.exists();	 Catch:{ Exception -> 0x010d, all -> 0x00e7 }
        if (r3 == 0) goto L_0x0040;
    L_0x0037:
        r3 = r2.canWrite();	 Catch:{ Exception -> 0x010d, all -> 0x00e7 }
        if (r3 == 0) goto L_0x0040;
    L_0x003d:
        r2.delete();	 Catch:{ Exception -> 0x010d, all -> 0x00e7 }
    L_0x0040:
        r3 = new java.io.RandomAccessFile;	 Catch:{ Exception -> 0x00d6, all -> 0x00e7 }
        r2 = "rw";
        r3.<init>(r1, r2);	 Catch:{ Exception -> 0x00d6, all -> 0x00e7 }
        r1 = r3.getChannel();	 Catch:{ Exception -> 0x0104, all -> 0x00f7 }
        r2 = r1.lock();	 Catch:{ Exception -> 0x0108, all -> 0x00fd }
        r6 = r6.length();	 Catch:{ Exception -> 0x010b, all -> 0x0102 }
        r6 = (int) r6;	 Catch:{ Exception -> 0x010b, all -> 0x0102 }
        r6 = java.nio.ByteBuffer.allocate(r6);	 Catch:{ Exception -> 0x010b, all -> 0x0102 }
        r1.read(r6);	 Catch:{ Exception -> 0x010b, all -> 0x0102 }
        r6.flip();	 Catch:{ Exception -> 0x010b, all -> 0x0102 }
        r6 = r6.array();	 Catch:{ Exception -> 0x010b, all -> 0x0102 }
        r6 = r11.m15040a(r6);	 Catch:{ Exception -> 0x010b, all -> 0x0102 }
        r0 = r11.m15037b(r6);	 Catch:{ Exception -> 0x010b, all -> 0x0102 }
        r6 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x010b, all -> 0x0102 }
        r8 = r11.f11837c;	 Catch:{ Exception -> 0x010b, all -> 0x0102 }
        r6 = r6 - r8;
        r8 = f11835a;	 Catch:{ Exception -> 0x010b, all -> 0x0102 }
        r6 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
        if (r6 <= 0) goto L_0x00d4;
    L_0x0078:
        if (r4 == 0) goto L_0x00c9;
    L_0x007a:
        r11.m15035a(r0);	 Catch:{ Exception -> 0x010b, all -> 0x0102 }
        r4 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x010b, all -> 0x0102 }
        r11.f11837c = r4;	 Catch:{ Exception -> 0x010b, all -> 0x0102 }
        r4 = new org.json.JSONObject;	 Catch:{ Exception -> 0x010b, all -> 0x0102 }
        r4.<init>();	 Catch:{ Exception -> 0x010b, all -> 0x0102 }
        r5 = "browser_open_times";
        r6 = com.yandex.metrica.impl.utils.C4525g.m16271a(r0);	 Catch:{ Exception -> 0x010b, all -> 0x0102 }
        r4.putOpt(r5, r6);	 Catch:{ Exception -> 0x010b, all -> 0x0102 }
        r5 = "last_sync_time";
        r6 = r11.f11837c;	 Catch:{ Exception -> 0x010b, all -> 0x0102 }
        r6 = java.lang.Long.valueOf(r6);	 Catch:{ Exception -> 0x010b, all -> 0x0102 }
        r4.putOpt(r5, r6);	 Catch:{ Exception -> 0x010b, all -> 0x0102 }
        r4 = r4.toString();	 Catch:{ Exception -> 0x010b, all -> 0x0102 }
        r4 = r11.m15039a(r4);	 Catch:{ Exception -> 0x010b, all -> 0x0102 }
        r5 = "UTF-8";
        r4 = r4.getBytes(r5);	 Catch:{ Exception -> 0x010b, all -> 0x0102 }
        r5 = r4.length;	 Catch:{ Exception -> 0x010b, all -> 0x0102 }
        r5 = java.nio.ByteBuffer.allocate(r5);	 Catch:{ Exception -> 0x010b, all -> 0x0102 }
        r5.put(r4);	 Catch:{ Exception -> 0x010b, all -> 0x0102 }
        r5.flip();	 Catch:{ Exception -> 0x010b, all -> 0x0102 }
        r6 = 0;
        r1.position(r6);	 Catch:{ Exception -> 0x010b, all -> 0x0102 }
        r6 = 0;
        r1.truncate(r6);	 Catch:{ Exception -> 0x010b, all -> 0x0102 }
        r1.write(r5);	 Catch:{ Exception -> 0x010b, all -> 0x0102 }
        r4 = 1;
        r1.force(r4);	 Catch:{ Exception -> 0x010b, all -> 0x0102 }
    L_0x00c9:
        com.yandex.metrica.impl.C4514r.m16220a(r2);	 Catch:{ all -> 0x00e4 }
        com.yandex.metrica.impl.bk.m14980a(r3);	 Catch:{ all -> 0x00e4 }
        com.yandex.metrica.impl.bk.m14980a(r1);	 Catch:{ all -> 0x00e4 }
    L_0x00d2:
        monitor-exit(r11);
        return r0;
    L_0x00d4:
        r4 = r5;
        goto L_0x0078;
    L_0x00d6:
        r1 = move-exception;
        r1 = r0;
        r2 = r0;
        r3 = r0;
    L_0x00da:
        com.yandex.metrica.impl.C4514r.m16220a(r2);	 Catch:{ all -> 0x00e4 }
        com.yandex.metrica.impl.bk.m14980a(r3);	 Catch:{ all -> 0x00e4 }
        com.yandex.metrica.impl.bk.m14980a(r1);	 Catch:{ all -> 0x00e4 }
        goto L_0x00d2;
    L_0x00e4:
        r0 = move-exception;
        monitor-exit(r11);
        throw r0;
    L_0x00e7:
        r1 = move-exception;
        r2 = r0;
        r3 = r0;
        r10 = r1;
        r1 = r0;
        r0 = r10;
    L_0x00ed:
        com.yandex.metrica.impl.C4514r.m16220a(r2);	 Catch:{ all -> 0x00e4 }
        com.yandex.metrica.impl.bk.m14980a(r3);	 Catch:{ all -> 0x00e4 }
        com.yandex.metrica.impl.bk.m14980a(r1);	 Catch:{ all -> 0x00e4 }
        throw r0;	 Catch:{ all -> 0x00e4 }
    L_0x00f7:
        r1 = move-exception;
        r2 = r0;
        r10 = r0;
        r0 = r1;
        r1 = r10;
        goto L_0x00ed;
    L_0x00fd:
        r2 = move-exception;
        r10 = r2;
        r2 = r0;
        r0 = r10;
        goto L_0x00ed;
    L_0x0102:
        r0 = move-exception;
        goto L_0x00ed;
    L_0x0104:
        r1 = move-exception;
        r1 = r0;
        r2 = r0;
        goto L_0x00da;
    L_0x0108:
        r2 = move-exception;
        r2 = r0;
        goto L_0x00da;
    L_0x010b:
        r4 = move-exception;
        goto L_0x00da;
    L_0x010d:
        r2 = move-exception;
        goto L_0x0040;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.c.a():java.util.HashMap<java.lang.String, java.lang.String>");
    }

    private HashMap<String, String> m15037b(String str) {
        HashMap<String, String> hashMap = null;
        try {
            if (!TextUtils.isEmpty(str)) {
                JSONObject jSONObject = new JSONObject(str);
                hashMap = C4525g.m16272a(jSONObject.optString("browser_open_times"));
                this.f11837c = jSONObject.optLong("last_sync_time", 0);
            }
        } catch (JSONException e) {
        }
        return hashMap != null ? hashMap : new HashMap();
    }

    private void m15035a(HashMap<String, String> hashMap) {
        for (String file : m15042b()) {
            C4369c.m15036a(hashMap, m15034a(new File(file)));
        }
    }

    private HashMap<String, String> m15034a(File file) {
        try {
            if (file.exists()) {
                byte[] b = C4514r.m16223b(this.f11836b, file);
                if (b != null) {
                    String a = m15040a(b);
                    file.getName();
                    return m15037b(a);
                }
            }
        } catch (UnsupportedEncodingException e) {
        }
        return new HashMap(0);
    }

    List<String> m15042b() {
        List<ResolveInfo> a = be.m14889a(this.f11836b, be.m14888a(this.f11836b));
        List<String> arrayList = new ArrayList();
        String packageName = this.f11836b.getPackageName();
        for (ResolveInfo resolveInfo : a) {
            String str = resolveInfo.serviceInfo.applicationInfo.packageName;
            if (!packageName.equals(str) && be.m14886a(resolveInfo.serviceInfo) >= 47) {
                try {
                    arrayList.add(this.f11836b.getFileStreamPath("b_meta.dat").getAbsolutePath().replace(this.f11836b.getApplicationInfo().dataDir, this.f11836b.getPackageManager().getApplicationInfo(str, 8192).dataDir));
                } catch (NameNotFoundException e) {
                }
            }
        }
        return arrayList;
    }

    private static boolean m15036a(Map<String, String> map, Map<String, String> map2) {
        boolean z = false;
        for (Entry entry : map2.entrySet()) {
            boolean z2;
            String str = (String) entry.getKey();
            long a = C4528i.m16276a((String) entry.getValue(), 0);
            long a2 = C4528i.m16276a((String) map.get(str), 0);
            if (a <= 0 || a2 >= a) {
                z2 = z;
            } else {
                map.put(str, String.valueOf(a));
                z2 = true;
            }
            z = z2;
        }
        return z;
    }

    String m15039a(String str) throws UnsupportedEncodingException {
        return Base64.encodeToString(m15038b(C4514r.m16221b(str).getBytes("UTF-8")), 0);
    }

    String m15040a(byte[] bArr) throws UnsupportedEncodingException {
        return C4514r.m16225c(new String(m15038b(Base64.decode(bArr, 0)), "UTF-8"));
    }

    private byte[] m15038b(byte[] bArr) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.reset();
            instance.update(this.f11836b.getPackageName().getBytes("UTF-8"));
            byte[] digest = instance.digest();
            byte[] bArr2 = new byte[bArr.length];
            for (int i = 0; i < bArr.length; i++) {
                bArr2[i] = (byte) (bArr[i] ^ digest[i % digest.length]);
            }
            return bArr2;
        } catch (Exception e) {
            return null;
        }
    }
}
