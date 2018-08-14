package com.facebook.ads.internal.p066i;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.util.Base64OutputStream;
import com.facebook.ads.internal.p056q.p057a.C2109c;
import com.facebook.ads.internal.p056q.p057a.C2115f;
import com.facebook.ads.internal.p056q.p057a.C2115f.C2114a;
import com.facebook.ads.internal.p056q.p057a.C2116g;
import com.facebook.ads.internal.p056q.p057a.C2117h;
import com.facebook.ads.internal.p056q.p057a.C2119j;
import com.facebook.ads.internal.p056q.p057a.C2122m;
import com.facebook.ads.internal.p056q.p057a.C2129r;
import com.facebook.ads.internal.p056q.p057a.C2133v;
import com.facebook.ads.internal.p056q.p076c.C2145d;
import com.facebook.ads.internal.p061c.C1951b;
import com.facebook.ads.internal.p063f.C1981e;
import com.facebook.ads.internal.p064g.C1985a;
import com.facebook.ads.internal.p064g.C1986b;
import com.facebook.ads.internal.settings.AdInternalSettings;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.zip.DeflaterOutputStream;
import org.json.JSONObject;

public class C1995c {
    private static final AtomicInteger f4680a = new AtomicInteger(0);
    private static String f4681b = null;
    private static final C2114a f4682c = C2115f.m6782a();
    private final Context f4683d;
    private final C1986b f4684e;

    public C1995c(Context context, boolean z) {
        this.f4683d = context;
        this.f4684e = new C1986b(context);
        C1995c.m6310a(context, z);
    }

    private static void m6310a(final Context context, boolean z) {
        if (f4680a.compareAndSet(0, 1)) {
            try {
                C2122m.m6804a();
                final SharedPreferences sharedPreferences = context.getSharedPreferences("FBAdPrefs", 0);
                final String str = "AFP;" + new C1986b(context).m6276g();
                f4681b = sharedPreferences.getString(str, null);
                Object futureTask = new FutureTask(new Callable<Boolean>() {
                    public Boolean m6307a() {
                        C1995c.f4681b = C1995c.m6311b(context, context.getPackageName());
                        sharedPreferences.edit().putString(str, C1995c.f4681b).apply();
                        C1995c.f4680a.set(2);
                        return Boolean.valueOf(true);
                    }

                    public /* synthetic */ Object call() {
                        return m6307a();
                    }
                });
                Executors.newSingleThreadExecutor().submit(futureTask);
                if (z) {
                    futureTask.get();
                }
            } catch (Exception e) {
                f4680a.set(0);
            }
        }
    }

    @Nullable
    private static String m6311b(Context context, String str) {
        try {
            return C2117h.m6793a(context.getPackageManager().getApplicationInfo(str, 0).sourceDir);
        } catch (Exception e) {
            C1981e.m6245a(e, context, new C1995c(context, false).m6315b());
            return null;
        }
    }

    public String m6314a() {
        ByteArrayOutputStream byteArrayOutputStream;
        Base64OutputStream base64OutputStream;
        Throwable e;
        DeflaterOutputStream deflaterOutputStream;
        ByteArrayOutputStream byteArrayOutputStream2;
        Base64OutputStream base64OutputStream2 = null;
        C1995c.m6310a(this.f4683d, true);
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                base64OutputStream = new Base64OutputStream(byteArrayOutputStream, 0);
            } catch (IOException e2) {
                e = e2;
                deflaterOutputStream = null;
                byteArrayOutputStream2 = byteArrayOutputStream;
                try {
                    throw new RuntimeException("Failed to build user token", e);
                } catch (Throwable th) {
                    e = th;
                    byteArrayOutputStream = byteArrayOutputStream2;
                    base64OutputStream = base64OutputStream2;
                    if (deflaterOutputStream != null) {
                        try {
                            deflaterOutputStream.close();
                        } catch (IOException e3) {
                            throw e;
                        }
                    }
                    if (base64OutputStream != null) {
                        base64OutputStream.close();
                    }
                    if (byteArrayOutputStream != null) {
                        byteArrayOutputStream.close();
                    }
                    throw e;
                }
            } catch (Throwable th2) {
                e = th2;
                deflaterOutputStream = null;
                base64OutputStream = null;
                if (deflaterOutputStream != null) {
                    deflaterOutputStream.close();
                }
                if (base64OutputStream != null) {
                    base64OutputStream.close();
                }
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                throw e;
            }
            try {
                deflaterOutputStream = new DeflaterOutputStream(base64OutputStream);
                try {
                    deflaterOutputStream.write(new JSONObject(m6315b()).toString().getBytes());
                    deflaterOutputStream.close();
                    String byteArrayOutputStream3 = byteArrayOutputStream.toString();
                    if (deflaterOutputStream != null) {
                        try {
                            deflaterOutputStream.close();
                        } catch (IOException e4) {
                        }
                    }
                    if (base64OutputStream != null) {
                        base64OutputStream.close();
                    }
                    if (byteArrayOutputStream != null) {
                        byteArrayOutputStream.close();
                    }
                    return byteArrayOutputStream3;
                } catch (IOException e5) {
                    e = e5;
                    base64OutputStream2 = base64OutputStream;
                    byteArrayOutputStream2 = byteArrayOutputStream;
                    throw new RuntimeException("Failed to build user token", e);
                } catch (Throwable th3) {
                    e = th3;
                    if (deflaterOutputStream != null) {
                        deflaterOutputStream.close();
                    }
                    if (base64OutputStream != null) {
                        base64OutputStream.close();
                    }
                    if (byteArrayOutputStream != null) {
                        byteArrayOutputStream.close();
                    }
                    throw e;
                }
            } catch (IOException e6) {
                e = e6;
                deflaterOutputStream = null;
                base64OutputStream2 = base64OutputStream;
                byteArrayOutputStream2 = byteArrayOutputStream;
                throw new RuntimeException("Failed to build user token", e);
            } catch (Throwable th4) {
                e = th4;
                deflaterOutputStream = null;
                if (deflaterOutputStream != null) {
                    deflaterOutputStream.close();
                }
                if (base64OutputStream != null) {
                    base64OutputStream.close();
                }
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                throw e;
            }
        } catch (IOException e7) {
            e = e7;
            deflaterOutputStream = null;
            byteArrayOutputStream2 = null;
            throw new RuntimeException("Failed to build user token", e);
        } catch (Throwable th5) {
            e = th5;
            deflaterOutputStream = null;
            base64OutputStream = null;
            byteArrayOutputStream = null;
            if (deflaterOutputStream != null) {
                deflaterOutputStream.close();
            }
            if (base64OutputStream != null) {
                base64OutputStream.close();
            }
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            throw e;
        }
    }

    public Map<String, String> m6315b() {
        C1995c.m6310a(this.f4683d, false);
        C1985a.m6259a(this.f4683d);
        Map<String, String> hashMap = new HashMap();
        hashMap.put("SDK", "android");
        hashMap.put("SDK_VERSION", "4.28.1");
        hashMap.put("LOCALE", Locale.getDefault().toString());
        float f = C2133v.f5083b;
        int i = this.f4683d.getResources().getDisplayMetrics().widthPixels;
        int i2 = this.f4683d.getResources().getDisplayMetrics().heightPixels;
        hashMap.put("DENSITY", String.valueOf(f));
        hashMap.put("SCREEN_WIDTH", String.valueOf((int) (((float) i) / f)));
        hashMap.put("SCREEN_HEIGHT", String.valueOf((int) (((float) i2) / f)));
        hashMap.put("ATTRIBUTION_ID", C1951b.f4525a);
        hashMap.put("ID_SOURCE", C1951b.f4528d);
        hashMap.put("OS", "Android");
        hashMap.put("OSVERS", C1986b.f4639a);
        hashMap.put("BUNDLE", this.f4684e.m6275f());
        hashMap.put("APPNAME", this.f4684e.m6273d());
        hashMap.put("APPVERS", this.f4684e.m6276g());
        hashMap.put("APPBUILD", String.valueOf(this.f4684e.m6277h()));
        hashMap.put("CARRIER", this.f4684e.m6272c());
        hashMap.put("MAKE", this.f4684e.m6270a());
        hashMap.put("MODEL", this.f4684e.m6271b());
        hashMap.put("ROOTED", String.valueOf(f4682c.f5026d));
        hashMap.put("INSTALLER", this.f4684e.m6274e());
        hashMap.put("SDK_CAPABILITY", C2109c.m6768b());
        hashMap.put("NETWORK_TYPE", String.valueOf(C2145d.m6867c(this.f4683d).f5125g));
        hashMap.put("SESSION_TIME", C2129r.m6818a(C2122m.m6805b()));
        hashMap.put("SESSION_ID", C2122m.m6806c());
        if (f4681b != null) {
            hashMap.put("AFP", f4681b);
        }
        String a = C2115f.m6783a(this.f4683d);
        if (a != null) {
            hashMap.put("ASHAS", a);
        }
        hashMap.put("UNITY", String.valueOf(C2116g.m6790a(this.f4683d)));
        a = AdInternalSettings.getMediationService();
        if (a != null) {
            hashMap.put("MEDIATION_SERVICE", a);
        }
        hashMap.put("ACCESSIBILITY_ENABLED", String.valueOf(this.f4684e.m6278i()));
        if (this.f4684e.m6279j() != -1) {
            hashMap.put("APP_MIN_SDK_VERSION", String.valueOf(this.f4684e.m6279j()));
        }
        hashMap.put("VALPARAMS", C1993b.m6304a());
        hashMap.put("ANALOG", C2119j.m6798a(C1985a.m6258a()));
        return hashMap;
    }
}
