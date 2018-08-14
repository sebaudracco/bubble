package com.facebook.ads.internal.p059a;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.ads.internal.p052j.C1998a.C1996a;
import com.facebook.ads.internal.p056q.p076c.C2149g;
import com.facebook.ads.internal.p069m.C2012c;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class C1878e extends C1873a {
    private static final String f4132d = C1878e.class.getSimpleName();
    private final Uri f4133e;
    private final Map<String, String> f4134f;

    public C1878e(Context context, C2012c c2012c, String str, Uri uri, Map<String, String> map) {
        super(context, c2012c, str);
        this.f4133e = uri;
        this.f4134f = map;
    }

    private Intent m5641a(C1879f c1879f) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(ErrorDialogData.BINDER_CRASH);
        if (!(TextUtils.isEmpty(c1879f.m5651a()) || TextUtils.isEmpty(c1879f.m5652b()))) {
            intent.setComponent(new ComponentName(c1879f.m5651a(), c1879f.m5652b()));
        }
        if (!TextUtils.isEmpty(c1879f.m5653c())) {
            intent.setData(Uri.parse(c1879f.m5653c()));
        }
        return intent;
    }

    private Intent m5642b(C1879f c1879f) {
        if (TextUtils.isEmpty(c1879f.m5651a())) {
            return null;
        }
        if (!C1877d.m5640a(this.a, c1879f.m5651a())) {
            return null;
        }
        CharSequence c = c1879f.m5653c();
        if (!TextUtils.isEmpty(c) && (c.startsWith("tel:") || c.startsWith("telprompt:"))) {
            return new Intent("android.intent.action.CALL", Uri.parse(c));
        }
        PackageManager packageManager = this.a.getPackageManager();
        if (TextUtils.isEmpty(c1879f.m5652b()) && TextUtils.isEmpty(c)) {
            return packageManager.getLaunchIntentForPackage(c1879f.m5651a());
        }
        Intent a = m5641a(c1879f);
        List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(a, 65536);
        if (a.getComponent() == null) {
            for (ResolveInfo resolveInfo : queryIntentActivities) {
                if (resolveInfo.activityInfo.packageName.equals(c1879f.m5651a())) {
                    a.setComponent(new ComponentName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name));
                    break;
                }
            }
        }
        return (queryIntentActivities.isEmpty() || a.getComponent() == null) ? null : a;
    }

    private List<C1879f> m5643e() {
        Object queryParameter = this.f4133e.getQueryParameter("appsite_data");
        if (TextUtils.isEmpty(queryParameter) || "[]".equals(queryParameter)) {
            return null;
        }
        List<C1879f> arrayList = new ArrayList();
        try {
            JSONArray optJSONArray = new JSONObject(queryParameter).optJSONArray("android");
            if (optJSONArray == null) {
                return arrayList;
            }
            for (int i = 0; i < optJSONArray.length(); i++) {
                C1879f a = C1879f.m5650a(optJSONArray.optJSONObject(i));
                if (a != null) {
                    arrayList.add(a);
                }
            }
            return arrayList;
        } catch (Throwable e) {
            Log.w(f4132d, "Error parsing appsite_data", e);
            return arrayList;
        }
    }

    private boolean m5644f() {
        List<Intent> d = m5649d();
        if (d == null) {
            return false;
        }
        for (Intent startActivity : d) {
            try {
                this.a.startActivity(startActivity);
                return true;
            } catch (Throwable e) {
                Log.d(f4132d, "Failed to open app intent, falling back", e);
            }
        }
        return false;
    }

    private boolean m5645g() {
        C2149g c2149g = new C2149g();
        try {
            C2149g.m6880a(c2149g, this.a, m5648c(), this.c);
            return true;
        } catch (Throwable e) {
            Log.d(f4132d, "Failed to open market url: " + this.f4133e.toString(), e);
            String queryParameter = this.f4133e.getQueryParameter("store_url_web_fallback");
            if (queryParameter != null && queryParameter.length() > 0) {
                try {
                    C2149g.m6880a(c2149g, this.a, Uri.parse(queryParameter), this.c);
                } catch (Throwable e2) {
                    Log.d(f4132d, "Failed to open fallback url: " + queryParameter, e2);
                }
            }
            return false;
        }
    }

    public C1996a mo3621a() {
        return C1996a.OPEN_STORE;
    }

    public void mo3622b() {
        Object obj = "opened_deeplink";
        if (!m5644f()) {
            obj = m5645g() ? "opened_store_url" : "opened_store_fallback_url";
        }
        this.f4134f.put(obj, String.valueOf(true));
        m5630a(this.f4134f);
    }

    protected Uri m5648c() {
        Object queryParameter = this.f4133e.getQueryParameter("store_url");
        if (!TextUtils.isEmpty(queryParameter)) {
            return Uri.parse(queryParameter);
        }
        String queryParameter2 = this.f4133e.getQueryParameter("store_id");
        return Uri.parse(String.format("market://details?id=%s", new Object[]{queryParameter2}));
    }

    protected List<Intent> m5649d() {
        List<C1879f> e = m5643e();
        List<Intent> arrayList = new ArrayList();
        if (e != null) {
            for (C1879f b : e) {
                Intent b2 = m5642b(b);
                if (b2 != null) {
                    arrayList.add(b2);
                }
            }
        }
        return arrayList;
    }
}
