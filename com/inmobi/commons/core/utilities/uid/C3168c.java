package com.inmobi.commons.core.utilities.uid;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.support.annotation.Nullable;
import com.bgjd.ici.p025b.C1408j.C1404b;
import com.google.android.gms.common.GoogleApiAvailability;
import com.inmobi.commons.core.p115d.C3135c;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.core.utilities.info.C3163e;
import com.inmobi.commons.p112a.C3105a;
import com.mopub.common.GpsHelper;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: UidHelper */
public class C3168c {
    private static final String f7834a = C3168c.class.getSimpleName();
    private static final Object f7835b = new Object();
    private static C3168c f7836c;
    private static C3165a f7837d;
    private static String f7838e;

    public static C3168c m10513a() {
        C3168c c3168c = f7836c;
        if (c3168c == null) {
            synchronized (f7835b) {
                c3168c = f7836c;
                if (c3168c == null) {
                    c3168c = new C3168c();
                    f7836c = c3168c;
                }
            }
        }
        return c3168c;
    }

    private C3168c() {
    }

    public void m10523b() {
        try {
            m10526e();
            m10525d();
            m10516q();
            m10535n();
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7834a, "SDK encountered an unexpected error while initializing the UID helper component; " + e.getMessage());
        }
    }

    public String m10524c() {
        return f7838e;
    }

    private void m10516q() {
        C3166b c3166b = new C3166b();
        String c = c3166b.m10507c();
        if (c == null) {
            c = UUID.randomUUID().toString();
            c3166b.m10506b(c);
        }
        f7838e = c;
    }

    public void m10525d() {
        m10532k();
    }

    public void m10526e() {
        try {
            if (m10533l()) {
                C3165a j = m10531j();
                if (j != null) {
                    String b = j.m10500b();
                    if (b != null) {
                        Logger.m10359a(InternalLogLevel.DEBUG, f7834a, "Publisher device Id is " + b);
                        return;
                    }
                    return;
                }
                return;
            }
            Logger.m10359a(InternalLogLevel.DEBUG, f7834a, "Publisher device Id is " + m10518a(m10530i()));
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7834a, "SDK encountered an unexpected error attempting to print the publisher test ID; " + e.getMessage());
        }
    }

    String m10518a(String str) {
        String str2 = "SHA-1";
        return m10519a(str, "SHA-1");
    }

    String m10527f() {
        return SchemaSymbols.ATTVAL_TRUE_1;
    }

    String m10519a(String str, String str2) {
        if (str != null) {
            try {
                if (!"".equals(str.trim())) {
                    MessageDigest instance = MessageDigest.getInstance(str2);
                    instance.update(str.getBytes());
                    byte[] digest = instance.digest();
                    StringBuffer stringBuffer = new StringBuffer();
                    for (byte b : digest) {
                        stringBuffer.append(Integer.toString((b & 255) + 256, 16).substring(1));
                    }
                    return stringBuffer.toString();
                }
            } catch (Exception e) {
                Logger.m10359a(InternalLogLevel.INTERNAL, f7834a, "SDK encountered an unexpected error attempting to get digested UID; " + e.getMessage());
                return null;
            }
        }
        return "TEST_EMULATOR";
    }

    String m10522b(String str) {
        String str2 = "MD5";
        return m10519a(str, "MD5");
    }

    String m10528g() {
        String str = "";
        try {
            str = C3163e.m10456d();
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7834a, "SDK encountered unexpected error in getting the login ID; " + e.getMessage());
        }
        return str;
    }

    String m10529h() {
        String str = "";
        try {
            str = C3163e.m10458e();
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7834a, "SDK encountered unexpected error in getting the session ID; " + e.getMessage());
        }
        return str;
    }

    String m10530i() {
        String str = "";
        Context b = C3105a.m10078b();
        if (b == null) {
            return str;
        }
        try {
            str = Secure.getString(b.getContentResolver(), "android_id");
            if (str == null) {
                return System.getString(b.getContentResolver(), "android_id");
            }
            return str;
        } catch (Throwable e) {
            Throwable th = e;
            str = "";
            Logger.m10360a(InternalLogLevel.INTERNAL, f7834a, "SDK encountered unexpected error in getting the Platform Id; ", th);
            return str;
        }
    }

    C3165a m10531j() {
        return f7837d;
    }

    void m10532k() {
        try {
            String str = "com.google.android.gms.ads.identifier.AdvertisingIdClient$Info";
            str = "getId";
            str = GpsHelper.IS_LIMIT_AD_TRACKING_ENABLED_KEY;
            str = "com.google.android.gms.ads.identifier.AdvertisingIdClient";
            str = "getAdvertisingIdInfo";
            final C3166b c3166b = new C3166b();
            f7837d = new C3165a();
            f7837d.m10499a(c3166b.m10501a());
            f7837d.m10498a(c3166b.m10505b());
            new Thread(new Runnable(this) {
                final /* synthetic */ C3168c f7833b;

                public void run() {
                    try {
                        Class cls = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient");
                        Class cls2 = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient$Info");
                        Object invoke = cls.getDeclaredMethod("getAdvertisingIdInfo", new Class[]{Context.class}).invoke(null, new Object[]{C3105a.m10078b()});
                        String str = (String) cls2.getDeclaredMethod("getId", (Class[]) null).invoke(invoke, (Object[]) null);
                        C3168c.f7837d.m10499a(str);
                        c3166b.m10503a(str);
                        Boolean bool = (Boolean) cls2.getDeclaredMethod(GpsHelper.IS_LIMIT_AD_TRACKING_ENABLED_KEY, (Class[]) null).invoke(invoke, (Object[]) null);
                        C3168c.f7837d.m10498a(bool);
                        c3166b.m10504a(bool.booleanValue());
                    } catch (Exception e) {
                        Logger.m10359a(InternalLogLevel.INTERNAL, C3168c.f7834a, "SDK encountered unexpected error in trying to set the advertising ID " + e.getMessage());
                    }
                }
            }).start();
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7834a, "SDK encountered unexpected error in setting the advertising ID; " + e.getMessage());
        }
    }

    boolean m10533l() {
        if (!C3105a.m10076a()) {
            return false;
        }
        try {
            if (GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(C3105a.m10078b()) == 0) {
                return true;
            }
            return false;
        } catch (Error e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7834a, "Google Play Services is not installed!");
            return false;
        } catch (Exception e2) {
            try {
                Map hashMap = new HashMap();
                hashMap.put("type", "RuntimeException");
                hashMap.put("message", e2.getMessage() + "");
                C3135c.m10255a().m10280a("root", "ExceptionCaught", hashMap);
                return false;
            } catch (Exception e3) {
                Logger.m10359a(InternalLogLevel.INTERNAL, f7834a, "Error in submitting telemetry event : (" + e2.getMessage() + ")");
                return false;
            }
        }
    }

    @Nullable
    public Boolean m10534m() {
        C3165a j = C3168c.m10513a().m10531j();
        return j == null ? null : j.m10497a();
    }

    protected void m10535n() {
        try {
            C3166b c3166b = new C3166b();
            String d = c3166b.m10509d();
            if (c3166b.m10511e() == 0) {
                c3166b.m10502a(System.currentTimeMillis());
            }
            if (d == null) {
                d = UUID.randomUUID().toString();
                c3166b.m10508c(d);
                c3166b.m10506b(m10524c());
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(m10524c(), System.currentTimeMillis());
                c3166b.m10510d(jSONObject.toString());
            }
            String a = m10520a(new JSONObject(c3166b.m10512f()));
            Intent intent = new Intent();
            intent.setAction("com.inmobi.share.id");
            intent.putExtra("imid", d);
            intent.putExtra("appendedid", a);
            intent.putExtra("imidts", c3166b.m10511e());
            intent.putExtra(C1404b.f2147y, c3166b.m10507c());
            C3105a.m10078b().sendBroadcast(intent);
            Logger.m10359a(InternalLogLevel.INTERNAL, f7834a, "Generating and broadcasting IDs. ID:" + d + " AID:" + a);
        } catch (Exception e) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f7834a, "SDK encountered unexpected error in generating and broadcasting IMID");
        }
    }

    String m10517a(Context context) {
        return new C3166b().m10509d();
    }

    @Nullable
    String m10521b(Context context) {
        String str = null;
        C3166b c3166b = new C3166b();
        try {
            JSONObject jSONObject = new JSONObject();
            String c = c3166b.m10507c();
            if (c != null) {
                jSONObject.put("p", c);
            }
            String f = c3166b.m10512f();
            if (!(f == null || c == null)) {
                JSONObject jSONObject2 = new JSONObject(f);
                jSONObject2.remove(c);
                if (jSONObject2.length() > 0) {
                    f = m10520a(jSONObject2);
                    JSONArray jSONArray = new JSONArray();
                    jSONArray.put(f);
                    jSONObject.put("s", jSONArray);
                }
            }
            str = jSONObject.toString();
        } catch (JSONException e) {
        } catch (Exception e2) {
        }
        return str;
    }

    public String m10520a(JSONObject jSONObject) {
        StringBuilder stringBuilder = new StringBuilder();
        if (jSONObject.length() > 0) {
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                stringBuilder.append((String) keys.next()).append(',');
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
        return stringBuilder.toString();
    }
}
