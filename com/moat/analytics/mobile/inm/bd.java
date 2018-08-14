package com.moat.analytics.mobile.inm;

import android.content.Context;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import com.google.android.gms.common.server.FavaDiagnosticsEntity;
import com.moat.analytics.mobile.inm.base.asserts.C3375a;
import com.moat.analytics.mobile.inm.base.exception.C3376a;
import com.moat.analytics.mobile.inm.base.functional.C3378a;
import com.mobfox.sdk.networking.RequestParams;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

class bd implements bc, C3379m {
    private View f8577a;
    private final WebView f8578b;
    private boolean f8579c;
    private final C3387l f8580d;
    private final C3371a f8581e;
    private final ao f8582f;
    private C3378a<String> f8583g;

    bd(View view, WebView webView, boolean z, C3371a c3371a, ao aoVar) {
        this(view, webView, z, new C3388n(webView.getContext(), aoVar), c3371a, aoVar);
    }

    bd(View view, WebView webView, boolean z, C3387l c3387l, C3371a c3371a, ao aoVar) {
        C3375a.m11556a(view);
        C3375a.m11556a(webView);
        C3375a.m11556a(c3371a);
        C3375a.m11556a(c3387l);
        if (aoVar.mo6482b()) {
            Log.d("MoatViewTracker", "In initialization method.");
        }
        this.f8581e = c3371a;
        this.f8577a = view;
        this.f8578b = webView;
        this.f8579c = z;
        this.f8580d = c3387l;
        this.f8582f = aoVar;
        this.f8583g = C3378a.m11558a();
    }

    private static String m11570a(Rect rect) {
        int i = rect.left;
        int i2 = rect.right - rect.left;
        int i3 = rect.bottom - rect.top;
        return new StringBuilder("{").append('\"').append("x").append('\"').append(":").append(i).append(',').append('\"').append("y").append('\"').append(":").append(rect.top).append(',').append('\"').append("w").append('\"').append(":").append(i2).append(',').append('\"').append(RequestParams.f9035H).append('\"').append(":").append(i3).append('}') + "";
    }

    private static String m11571a(Map<String, String> map, boolean z) {
        StringBuilder stringBuilder = new StringBuilder("{");
        for (Entry entry : map.entrySet()) {
            String str = (String) entry.getKey();
            String str2 = (String) entry.getValue();
            if (stringBuilder.length() > 1) {
                stringBuilder.append(',');
            }
            stringBuilder.append('\"').append(str).append('\"').append(':');
            if (z) {
                stringBuilder.append('\"').append(str2).append('\"');
            } else {
                stringBuilder.append(str2);
            }
        }
        stringBuilder.append("}");
        return "" + stringBuilder;
    }

    private void m11572a(Map<String, String> map, String str, Rect rect) {
        map.put(str, m11570a(m11573b(rect)));
    }

    private Rect m11573b(Rect rect) {
        float f = m11578j().density;
        if (f == 0.0f) {
            return rect;
        }
        return new Rect(Math.round(((float) rect.left) / f), Math.round(((float) rect.top) / f), Math.round(((float) rect.right) / f), Math.round(((float) rect.bottom) / f));
    }

    private Rect m11574c(Rect rect) {
        Rect k = m11579k();
        if (!this.f8577a.getGlobalVisibleRect(k)) {
            k = m11579k();
        }
        k.left = Math.min(Math.max(0, k.left), rect.right);
        k.right = Math.min(Math.max(0, k.right), rect.right);
        k.top = Math.min(Math.max(0, k.top), rect.bottom);
        k.bottom = Math.min(Math.max(0, k.bottom), rect.bottom);
        return k;
    }

    private String m11575g() {
        Exception e;
        if (this.f8583g.m11563c()) {
            return (String) this.f8583g.m11561b();
        }
        String str = "_unknown_";
        String charSequence;
        try {
            Context context = this.f8578b.getContext();
            charSequence = context.getPackageManager().getApplicationLabel(context.getApplicationContext().getApplicationInfo()).toString();
            try {
                this.f8583g = C3378a.m11559a(charSequence);
                return charSequence;
            } catch (Exception e2) {
                e = e2;
                C3376a.m11557a(e);
                return charSequence;
            }
        } catch (Exception e3) {
            Exception exception = e3;
            charSequence = str;
            e = exception;
            C3376a.m11557a(e);
            return charSequence;
        }
    }

    private boolean m11576h() {
        return this.f8577a.isShown() && !this.f8581e.mo6497a();
    }

    private Rect m11577i() {
        DisplayMetrics j = m11578j();
        return new Rect(0, 0, j.widthPixels, j.heightPixels);
    }

    private DisplayMetrics m11578j() {
        return this.f8577a.getContext().getResources().getDisplayMetrics();
    }

    private Rect m11579k() {
        return new Rect(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE);
    }

    public String mo6490a() {
        int i = 0;
        Map hashMap = new HashMap();
        try {
            Rect i2 = m11577i();
            Rect c = m11574c(i2);
            Rect e = mo6495e();
            m11572a(hashMap, "screen", i2);
            m11572a(hashMap, "visible", c);
            m11572a(hashMap, "maybe", c);
            m11572a(hashMap, "view", e);
            if (m11576h()) {
                i = 1;
            }
            hashMap.put("inFocus", i + "");
            hashMap.put("dr", "" + m11578j().density);
            return m11571a(hashMap, false);
        } catch (Exception e2) {
            return "{}";
        }
    }

    public void mo6491a(View view) {
        if (this.f8582f.mo6482b()) {
            Log.d("MoatViewTracker", "changing view to " + (view != null ? view.getClass().getSimpleName() + "@" + view.hashCode() : "null"));
        }
        this.f8577a = view;
    }

    public String mo6492b() {
        try {
            return m11571a(m11586f(), true);
        } catch (Exception e) {
            return "{}";
        }
    }

    public boolean mo6493c() {
        if (this.f8582f.mo6482b()) {
            Log.d("MoatViewTracker", "Attempting bridge installation.");
        }
        boolean a = this.f8580d.mo6501a(this.f8578b, this);
        if (this.f8582f.mo6482b()) {
            Log.d("MoatViewTracker", "Bridge " + (a ? "" : "not ") + "installed.");
        }
        return a;
    }

    public void mo6494d() {
        this.f8580d.mo6500a();
    }

    public Rect mo6495e() {
        int[] iArr = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
        this.f8577a.getLocationInWindow(iArr);
        int i = iArr[0];
        int i2 = iArr[1];
        return new Rect(i, i2, this.f8577a.getWidth() + i, this.f8577a.getHeight() + i2);
    }

    public Map<String, String> m11586f() {
        Map<String, String> hashMap = new HashMap();
        String str = "f379b01b219fb72670923cc96dc29bbe34213365";
        str = "INM";
        str = "1.7.11";
        String g = m11575g();
        String num = Integer.toString(VERSION.SDK_INT);
        Object obj = this.f8579c ? SchemaSymbols.ATTVAL_TRUE_1 : SchemaSymbols.ATTVAL_FALSE_0;
        hashMap.put("versionHash", "f379b01b219fb72670923cc96dc29bbe34213365");
        hashMap.put("appName", g);
        hashMap.put(FavaDiagnosticsEntity.EXTRA_NAMESPACE, "INM");
        hashMap.put("version", "1.7.11");
        hashMap.put("deviceOS", num);
        hashMap.put("isNative", obj);
        return hashMap;
    }
}
