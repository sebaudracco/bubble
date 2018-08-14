package com.fyber.utils.cookies;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.fyber.utils.FyberLogger;
import com.fyber.utils.StringUtils;
import com.fyber.utils.cookies.a.a;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.HttpCookie;
import java.net.URI;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Deprecated
/* compiled from: PersistentHttpCookieStore */
public final class C1564a {
    private final ConcurrentHashMap<URI, List<HttpCookie>> f2639a = new ConcurrentHashMap();

    public C1564a(Context context) {
        int i = 0;
        SharedPreferences sharedPreferences = context.getSharedPreferences("FyberCookiePrefsFile", 0);
        String string = sharedPreferences.getString("names", null);
        if (string != null) {
            String[] split = TextUtils.split(string, ",");
            int length = split.length;
            while (i < length) {
                String str = split[i];
                String string2 = sharedPreferences.getString("cookie_" + str, null);
                if (StringUtils.notNullNorEmpty(string2)) {
                    this.f2639a.put(URI.create(str), m3422a(string2));
                }
                i++;
            }
        }
    }

    private List<HttpCookie> m3422a(String str) {
        List<HttpCookie> linkedList = new LinkedList();
        for (String b : TextUtils.split(str, ",")) {
            HttpCookie b2 = m3423b(b);
            if (!(b2 == null || b2.hasExpired())) {
                linkedList.add(b2);
            }
        }
        return linkedList;
    }

    public final ConcurrentHashMap<URI, List<HttpCookie>> m3424a() {
        return this.f2639a;
    }

    private HttpCookie m3423b(String str) {
        Exception e;
        int length = str.length();
        byte[] bArr = new byte[((length / 2) + 1)];
        for (int i = 0; i < length; i += 2) {
            bArr[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4) + Character.digit(str.charAt(i + 1), 16));
        }
        Creator creator = ParcelableHttpCookie.CREATOR;
        Parcel obtain = Parcel.obtain();
        obtain.unmarshall(bArr, 0, bArr.length);
        obtain.setDataPosition(0);
        HttpCookie a = ((ParcelableHttpCookie) creator.createFromParcel(obtain)).a();
        if (a == null) {
            try {
                return ((b) new a(this, new ByteArrayInputStream(bArr)).readObject()).a();
            } catch (IOException e2) {
                e = e2;
                FyberLogger.d("PersistentHttpCookieStore", "Exception in decodeCookie - " + e.getMessage());
                return a;
            } catch (ClassNotFoundException e3) {
                e = e3;
                FyberLogger.d("PersistentHttpCookieStore", "Exception in decodeCookie - " + e.getMessage());
                return a;
            }
        }
        return a;
    }
}
