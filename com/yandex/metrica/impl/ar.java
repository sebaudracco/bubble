package com.yandex.metrica.impl;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.yandex.metrica.DeferredDeeplinkParametersListener;
import com.yandex.metrica.DeferredDeeplinkParametersListener.Error;
import com.yandex.metrica.impl.ob.bz;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class ar {
    private final boolean f11665a;
    private final ay f11666b;
    private final bz f11667c;
    private String f11668d;
    private Map<String, String> f11669e;
    private DeferredDeeplinkParametersListener f11670f;
    private Handler f11671g;

    class C43351 implements Runnable {
        final /* synthetic */ ar f11664a;

        C43351(ar arVar) {
            this.f11664a = arVar;
        }

        public void run() {
            this.f11664a.m14651a();
        }
    }

    public ar(ay ayVar, bz bzVar) {
        this.f11666b = ayVar;
        this.f11667c = bzVar;
        this.f11668d = bzVar.m15435c();
        this.f11665a = bzVar.m15439d();
        if (this.f11665a) {
            this.f11667c.m15454p(null);
            this.f11668d = null;
            return;
        }
        m14654d(m14658b(this.f11668d));
    }

    public void m14657a(String str) {
        this.f11666b.m14760b(str);
        if (!this.f11665a) {
            synchronized (this) {
                this.f11668d = str;
                this.f11667c.m15454p(this.f11668d);
                m14654d(m14658b(str));
                if (this.f11671g == null) {
                    this.f11671g = new Handler(Looper.getMainLooper());
                }
                this.f11671g.post(new C43351(this));
            }
        }
    }

    private void m14654d(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.f11669e = m14659c(str);
        }
    }

    String m14658b(String str) {
        return (String) m14655e(str).get("appmetrica_deep_link");
    }

    Map<String, String> m14659c(String str) {
        Map e = m14655e(Uri.decode(str));
        Map hashMap = new HashMap(e.size());
        for (Entry entry : e.entrySet()) {
            hashMap.put(Uri.decode((String) entry.getKey()), Uri.decode((String) entry.getValue()));
        }
        return hashMap;
    }

    private static Map<String, String> m14655e(String str) {
        Map hashMap = new HashMap();
        if (str != null) {
            int lastIndexOf = str.lastIndexOf(63);
            if (lastIndexOf >= 0) {
                str = str.substring(lastIndexOf + 1);
            }
            if (str.contains("=")) {
                for (String str2 : str.split("&")) {
                    int indexOf = str2.indexOf("=");
                    if (indexOf >= 0) {
                        hashMap.put(str2.substring(0, indexOf), str2.substring(indexOf + 1));
                    } else {
                        hashMap.put(str2, "");
                    }
                }
            }
        }
        return hashMap;
    }

    private void m14651a() {
        if (bk.m14988a(this.f11669e)) {
            if (this.f11668d != null) {
                m14652a(Error.PARSE_ERROR);
            }
        } else if (this.f11670f != null) {
            this.f11670f.onParametersLoaded(this.f11669e);
            this.f11670f = null;
        }
    }

    private void m14652a(Error error) {
        if (this.f11670f != null) {
            this.f11670f.onError(error, this.f11668d);
            this.f11670f = null;
        }
    }

    public synchronized void m14656a(DeferredDeeplinkParametersListener deferredDeeplinkParametersListener) {
        try {
            this.f11670f = deferredDeeplinkParametersListener;
            if (this.f11665a) {
                m14652a(Error.NOT_A_FIRST_LAUNCH);
            } else {
                m14651a();
            }
            this.f11667c.m15440e();
        } catch (Throwable th) {
            this.f11667c.m15440e();
        }
    }
}
