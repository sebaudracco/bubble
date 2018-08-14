package com.yandex.metrica.impl.ob;

import com.yandex.metrica.impl.ob.fr.C4476a;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import org.apache.http.HttpEntity;

public class fq {
    protected final fm f12410a;

    public fq(fm fmVar) {
        this.f12410a = fmVar;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.yandex.metrica.impl.ob.ft m16068a(com.yandex.metrica.impl.ob.fu<?> r12) throws com.yandex.metrica.impl.ob.fr {
        /*
        r11 = this;
        r3 = 0;
        r5 = 0;
    L_0x0002:
        r1 = java.util.Collections.emptyMap();
        r0 = r11.f12410a;	 Catch:{ SocketTimeoutException -> 0x0051, ConnectTimeoutException -> 0x0068, MalformedURLException -> 0x0074, IOException -> 0x008f }
        r4 = r0.mo7107a(r12);	 Catch:{ SocketTimeoutException -> 0x0051, ConnectTimeoutException -> 0x0068, MalformedURLException -> 0x0074, IOException -> 0x008f }
        r0 = r4.getStatusLine();	 Catch:{ SocketTimeoutException -> 0x0051, ConnectTimeoutException -> 0x0068, MalformedURLException -> 0x0074, IOException -> 0x00cf }
        r6 = r0.getStatusCode();	 Catch:{ SocketTimeoutException -> 0x0051, ConnectTimeoutException -> 0x0068, MalformedURLException -> 0x0074, IOException -> 0x00cf }
        r7 = r4.getAllHeaders();	 Catch:{ SocketTimeoutException -> 0x0051, ConnectTimeoutException -> 0x0068, MalformedURLException -> 0x0074, IOException -> 0x00cf }
        r2 = new java.util.TreeMap;	 Catch:{ SocketTimeoutException -> 0x0051, ConnectTimeoutException -> 0x0068, MalformedURLException -> 0x0074, IOException -> 0x00cf }
        r0 = java.lang.String.CASE_INSENSITIVE_ORDER;	 Catch:{ SocketTimeoutException -> 0x0051, ConnectTimeoutException -> 0x0068, MalformedURLException -> 0x0074, IOException -> 0x00cf }
        r2.<init>(r0);	 Catch:{ SocketTimeoutException -> 0x0051, ConnectTimeoutException -> 0x0068, MalformedURLException -> 0x0074, IOException -> 0x00cf }
        r0 = r5;
    L_0x0020:
        r8 = r7.length;	 Catch:{ SocketTimeoutException -> 0x0051, ConnectTimeoutException -> 0x0068, MalformedURLException -> 0x0074, IOException -> 0x00cf }
        if (r0 >= r8) goto L_0x0035;
    L_0x0023:
        r8 = r7[r0];	 Catch:{ SocketTimeoutException -> 0x0051, ConnectTimeoutException -> 0x0068, MalformedURLException -> 0x0074, IOException -> 0x00cf }
        r8 = r8.getName();	 Catch:{ SocketTimeoutException -> 0x0051, ConnectTimeoutException -> 0x0068, MalformedURLException -> 0x0074, IOException -> 0x00cf }
        r9 = r7[r0];	 Catch:{ SocketTimeoutException -> 0x0051, ConnectTimeoutException -> 0x0068, MalformedURLException -> 0x0074, IOException -> 0x00cf }
        r9 = r9.getValue();	 Catch:{ SocketTimeoutException -> 0x0051, ConnectTimeoutException -> 0x0068, MalformedURLException -> 0x0074, IOException -> 0x00cf }
        r2.put(r8, r9);	 Catch:{ SocketTimeoutException -> 0x0051, ConnectTimeoutException -> 0x0068, MalformedURLException -> 0x0074, IOException -> 0x00cf }
        r0 = r0 + 1;
        goto L_0x0020;
    L_0x0035:
        r0 = r4.getEntity();	 Catch:{ SocketTimeoutException -> 0x0051, ConnectTimeoutException -> 0x0068, MalformedURLException -> 0x0074, IOException -> 0x00d2 }
        if (r0 == 0) goto L_0x005d;
    L_0x003b:
        r0 = r4.getEntity();	 Catch:{ SocketTimeoutException -> 0x0051, ConnectTimeoutException -> 0x0068, MalformedURLException -> 0x0074, IOException -> 0x00d2 }
        r1 = m16067a(r0);	 Catch:{ SocketTimeoutException -> 0x0051, ConnectTimeoutException -> 0x0068, MalformedURLException -> 0x0074, IOException -> 0x00d2 }
    L_0x0043:
        r0 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r6 < r0) goto L_0x004b;
    L_0x0047:
        r0 = 299; // 0x12b float:4.19E-43 double:1.477E-321;
        if (r6 <= r0) goto L_0x0061;
    L_0x004b:
        r0 = new java.io.IOException;	 Catch:{ SocketTimeoutException -> 0x0051, ConnectTimeoutException -> 0x0068, MalformedURLException -> 0x0074, IOException -> 0x00d6 }
        r0.<init>();	 Catch:{ SocketTimeoutException -> 0x0051, ConnectTimeoutException -> 0x0068, MalformedURLException -> 0x0074, IOException -> 0x00d6 }
        throw r0;	 Catch:{ SocketTimeoutException -> 0x0051, ConnectTimeoutException -> 0x0068, MalformedURLException -> 0x0074, IOException -> 0x00d6 }
    L_0x0051:
        r0 = move-exception;
        r0 = new com.yandex.metrica.impl.ob.fr;
        r1 = com.yandex.metrica.impl.ob.fr.C4476a.f12417g;
        r0.<init>();
        m16066a(r12, r0);
        goto L_0x0002;
    L_0x005d:
        r0 = 0;
        r1 = new byte[r0];	 Catch:{ SocketTimeoutException -> 0x0051, ConnectTimeoutException -> 0x0068, MalformedURLException -> 0x0074, IOException -> 0x00d2 }
        goto L_0x0043;
    L_0x0061:
        r0 = new com.yandex.metrica.impl.ob.ft;	 Catch:{ SocketTimeoutException -> 0x0051, ConnectTimeoutException -> 0x0068, MalformedURLException -> 0x0074, IOException -> 0x00d6 }
        r6 = 0;
        r0.<init>(r1, r2, r6);	 Catch:{ SocketTimeoutException -> 0x0051, ConnectTimeoutException -> 0x0068, MalformedURLException -> 0x0074, IOException -> 0x00d6 }
        return r0;
    L_0x0068:
        r0 = move-exception;
        r0 = new com.yandex.metrica.impl.ob.fr;
        r1 = com.yandex.metrica.impl.ob.fr.C4476a.NO_CONNECTION;
        r0.<init>();
        m16066a(r12, r0);
        goto L_0x0002;
    L_0x0074:
        r0 = move-exception;
        r1 = new java.lang.RuntimeException;
        r2 = new java.lang.StringBuilder;
        r3 = "Bad URL ";
        r2.<init>(r3);
        r3 = r12.mo7105a();
        r2 = r2.append(r3);
        r2 = r2.toString();
        r1.<init>(r2, r0);
        throw r1;
    L_0x008f:
        r0 = move-exception;
        r2 = r3;
        r4 = r3;
    L_0x0092:
        if (r4 == 0) goto L_0x00b7;
    L_0x0094:
        r0 = r4.getStatusLine();
        r0 = r0.getStatusCode();
        if (r2 == 0) goto L_0x00c7;
    L_0x009e:
        r4 = new com.yandex.metrica.impl.ob.ft;
        r4.<init>(r2, r1, r5);
        r1 = 401; // 0x191 float:5.62E-43 double:1.98E-321;
        if (r0 == r1) goto L_0x00ab;
    L_0x00a7:
        r1 = 403; // 0x193 float:5.65E-43 double:1.99E-321;
        if (r0 != r1) goto L_0x00bf;
    L_0x00ab:
        r0 = new com.yandex.metrica.impl.ob.fr;
        r1 = com.yandex.metrica.impl.ob.fr.C4476a.AUTH;
        r0.<init>(r5);
        m16066a(r12, r0);
        goto L_0x0002;
    L_0x00b7:
        r1 = new com.yandex.metrica.impl.ob.fr;
        r2 = com.yandex.metrica.impl.ob.fr.C4476a.f12411a;
        r1.<init>(r0);
        throw r1;
    L_0x00bf:
        r0 = new com.yandex.metrica.impl.ob.fr;
        r1 = com.yandex.metrica.impl.ob.fr.C4476a.SERVER;
        r0.<init>(r5);
        throw r0;
    L_0x00c7:
        r0 = new com.yandex.metrica.impl.ob.fr;
        r1 = com.yandex.metrica.impl.ob.fr.C4476a.NETWORK;
        r0.<init>(r5);
        throw r0;
    L_0x00cf:
        r0 = move-exception;
        r2 = r3;
        goto L_0x0092;
    L_0x00d2:
        r0 = move-exception;
        r1 = r2;
        r2 = r3;
        goto L_0x0092;
    L_0x00d6:
        r0 = move-exception;
        r10 = r2;
        r2 = r1;
        r1 = r10;
        goto L_0x0092;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.yandex.metrica.impl.ob.fq.a(com.yandex.metrica.impl.ob.fu):com.yandex.metrica.impl.ob.ft");
    }

    private static void m16066a(fu<?> fuVar, fr frVar) throws fr {
        try {
            fuVar.m16050o().m16079a(frVar);
        } catch (fr e) {
            throw e;
        }
    }

    private static byte[] m16067a(HttpEntity httpEntity) throws IOException, fr {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(Math.max((int) httpEntity.getContentLength(), 256));
        try {
            InputStream content = httpEntity.getContent();
            if (content == null) {
                C4476a c4476a = C4476a.SERVER;
                throw new fr();
            }
            byte[] bArr = new byte[1024];
            while (true) {
                int read = content.read(bArr);
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
            byte[] toByteArray = byteArrayOutputStream.toByteArray();
            return toByteArray;
        } finally {
            try {
                httpEntity.consumeContent();
            } catch (IOException e) {
            }
            byteArrayOutputStream.close();
        }
    }

    public static String m16065a(Map<String, String> map, String str) {
        String str2 = (String) map.get("Content-Type");
        if (str2 == null) {
            return str;
        }
        String[] split = str2.split(";");
        for (int i = 1; i < split.length; i++) {
            String[] split2 = split[i].trim().split("=");
            if (split2.length == 2 && split2[0].equals("charset")) {
                return split2[1];
            }
        }
        return str;
    }
}
