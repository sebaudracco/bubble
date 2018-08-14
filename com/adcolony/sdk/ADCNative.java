package com.adcolony.sdk;

import com.adcolony.sdk.aa.C0595a;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutorService;
import org.json.JSONException;
import org.json.JSONObject;

class ADCNative {
    static ADCNative lock = new ADCNative();

    private static class C0574a {
        static int f326a = 4;
        static int f327b = 0;
        static int f328c = 1;
        static int f329d = 2;
        static int f330e = 3;

        private C0574a() {
        }

        private static void m516b(String[] strArr) {
            if (strArr != null && strArr.length == f326a) {
                ac.m654a(Integer.parseInt(strArr[f327b]), Integer.parseInt(strArr[f328c]), strArr[f329d], Boolean.parseBoolean(strArr[f330e]));
            }
        }
    }

    private static native void EventTracker__logEvent(long j, byte[] bArr);

    static native void nativeCreateSceneController(int i, int i2);

    static native void nativeCreateTexture(int i, int i2, int i3, String str, ByteBuffer byteBuffer, int i4, int i5, int i6, int i7);

    static native void nativeDeleteSceneController(int i, int i2);

    static native void nativeHelloWorld();

    static native boolean nativeIsArm();

    static native boolean nativeNeonSupported();

    static native void nativeRender(int i, int i2, int i3, int i4);

    ADCNative() {
    }

    static void Logger__logNative(String[] parameters) {
        C0574a.m516b(parameters);
    }

    static void EventTracker__logEvent(final JSONObject payload) {
        final ADCVMModule aDCVMModule = (ADCVMModule) C0594a.m605a().m1287q().m710e().get(Integer.valueOf(1));
        if (aDCVMModule == null || C0740l.m1211C().equals("")) {
            AdColonyEventTracker.m557a(payload);
            return;
        }
        ExecutorService e = aDCVMModule.m534e();
        if (e != null) {
            e.submit(new Runnable() {
                public void run() {
                    long d = aDCVMModule.m533d();
                    ADCNative.m517a(payload);
                    ADCNative.EventTracker__logEvent(d, ("ADC3__EventTracker__logEvent(" + payload.toString() + ")").getBytes(Charset.forName("UTF-8")));
                }
            });
        } else {
            new C0595a().m622a("ExecutorService is null.").m624a(aa.f483g);
        }
    }

    private static void m517a(JSONObject jSONObject) {
        JSONObject f = C0802y.m1480f(jSONObject, "payload");
        if (ADCVMModule.f338a) {
            C0802y.m1462a(f, "api_key", "bb2cf0647ba654d7228dd3f9405bbc6a");
        } else {
            C0802y.m1462a(f, "api_key", C0740l.m1211C());
        }
        try {
            jSONObject.remove("payload");
            jSONObject.put("payload", f);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
