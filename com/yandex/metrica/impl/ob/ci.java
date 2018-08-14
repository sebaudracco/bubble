package com.yandex.metrica.impl.ob;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.LocalServerSocket;
import android.net.Uri;
import android.text.TextUtils;
import com.yandex.metrica.YandexMetrica;
import com.yandex.metrica.impl.C4514r;
import com.yandex.metrica.impl.be;
import com.yandex.metrica.impl.bi;
import com.yandex.metrica.impl.bk;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public class ci {
    private final Object f12100a;
    private final C4425a f12101b;
    private final ck f12102c;
    private ch f12103d;

    static class C4425a {
        ci f12097a;
        private LocalServerSocket f12098b;

        private C4425a(ci ciVar) {
            this.f12097a = ciVar;
        }

        ci m15563a() {
            return this.f12097a;
        }

        boolean m15566b() {
            try {
                this.f12098b = new LocalServerSocket("com.yandex.metrica.synchronization.deviceid");
                return true;
            } catch (IOException e) {
                return false;
            }
        }

        public String m15564a(Context context, String str) {
            String str2 = null;
            TextUtils.isEmpty(str);
            m15563a().m15584f().m15593a(context);
            dn dnVar = new dn(12);
            do {
                if (m15566b()) {
                    str2 = m15565a(context, str, m15563a().m15584f().m15593a(context));
                    if (this.f12098b != null) {
                        try {
                            this.f12098b.close();
                            this.f12098b = null;
                        } catch (IOException e) {
                        }
                    }
                } else {
                    dnVar.mo7074a();
                    dnVar.m15736c();
                }
            } while (dnVar.m15735b());
            return str2;
        }

        String m15565a(Context context, String str, String str2) {
            if (TextUtils.isEmpty(str)) {
                if (TextUtils.isEmpty(str2)) {
                    return null;
                }
                ci.m15570a(m15563a(), context, str2);
                return str2;
            } else if (str.equals(str2)) {
                ci.m15570a(m15563a(), context, str);
                YandexMetrica.getReporter(context, "20799a27-fa80-4b36-b2db-0f8141f24180").reportEvent("update_snapshot", new C4427c(context, str2, str));
                return str;
            } else if (TextUtils.isEmpty(str2)) {
                ci.m15570a(m15563a(), context, str);
                YandexMetrica.getReporter(context, "20799a27-fa80-4b36-b2db-0f8141f24180").reportEvent("wtf_situation. App has id and elector hasn't", new C4427c(context, str2, str));
                return str;
            } else {
                ci.m15570a(m15563a(), context, str2);
                YandexMetrica.getReporter(context, "20799a27-fa80-4b36-b2db-0f8141f24180").reportEvent("overlapping_device_id", new C4427c(context, str2, str));
                return str2;
            }
        }
    }

    private static class C4426b {
        private static final ci f12099a = new ci();
    }

    private static class C4427c extends HashMap<String, Object> {
        public C4427c(Context context, String str) {
            String packageName = context.getPackageName();
            put("passed_id", str);
            put("package_name", packageName);
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
                put("version_code", Integer.valueOf(packageInfo.versionCode));
                put("version_name", packageInfo.versionName);
            } catch (NameNotFoundException e) {
            }
        }

        public C4427c(Context context, String str, String str2) {
            this(context, str);
            put("stored_device_id", str2);
        }
    }

    public static ci m15569a() {
        return C4426b.f12099a;
    }

    private ci() {
        this.f12100a = new Object();
        this.f12101b = new C4425a();
        this.f12102c = new ck(this);
    }

    C4425a m15577b() {
        return this.f12101b;
    }

    ch m15578c() {
        return this.f12103d;
    }

    public String m15580d() {
        ch c = m15578c();
        if (c == null) {
            return null;
        }
        return c.m15560c();
    }

    ch m15574a(Context context, String str) {
        return m15568a(context, str, context.getFileStreamPath("credentials.dat"));
    }

    ch m15576b(Context context, String str) {
        return m15568a(context, str, new File(context.getNoBackupFilesDir(), "credentials.dat"));
    }

    private ch m15568a(Context context, String str, File file) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(str, 8192);
            if (applicationInfo != null) {
                return m15572h(context, file.getAbsolutePath().replace(context.getApplicationInfo().dataDir, applicationInfo.dataDir));
            }
        } catch (NameNotFoundException e) {
        }
        return null;
    }

    private ch m15572h(Context context, String str) {
        try {
            File file = new File(str);
            if (!file.exists()) {
                return null;
            }
            String a;
            synchronized (this.f12100a) {
                a = C4514r.m16215a(context, file);
            }
            if (a == null) {
                return null;
            }
            return new ch(new JSONObject(a), file.lastModified());
        } catch (JSONException e) {
            return null;
        } catch (Exception e2) {
            return null;
        }
    }

    public String m15579c(Context context, String str) {
        return m15573i(context, str);
    }

    boolean m15583e() {
        return bk.m14985a(21);
    }

    void m15581d(Context context, String str) {
        try {
            synchronized (this.f12100a) {
                this.f12103d = new ch(str, new cj(context), System.currentTimeMillis());
                String a = this.f12103d.m15557a();
                if (m15583e()) {
                    m15582e(context, a);
                }
                String str2 = "credentials.dat";
                synchronized (this.f12100a) {
                    C4514r.m16218a(context, str2, a);
                }
            }
        } catch (JSONException e) {
        }
    }

    void m15582e(Context context, String str) {
        synchronized (this.f12100a) {
            C4514r.m16222b(context, "credentials.dat", str);
        }
    }

    public String m15575a(Context context) {
        return m15573i(context, null);
    }

    private String m15573i(Context context, String str) {
        String a;
        synchronized (this.f12100a) {
            if (m15578c() == null) {
                ch a2 = m15574a(context, context.getPackageName());
                if (a2 == null) {
                    a = m15577b().m15564a(context, str);
                } else if (m15583e()) {
                    ch b = m15576b(context, context.getPackageName());
                    if (a2.m15558a(b) && b.m15562e()) {
                        this.f12103d = a2;
                        a = b.m15560c();
                    } else {
                        a = m15577b().m15564a(context, a2.m15560c());
                    }
                } else if (a2.m15562e()) {
                    this.f12103d = a2;
                    a = a2.m15560c();
                } else {
                    a = m15577b().m15564a(context, a2.m15560c());
                }
            } else {
                a = m15578c().m15560c();
            }
        }
        return a;
    }

    String m15585f(Context context, String str) {
        Cursor query;
        Cursor cursor;
        Throwable th;
        ProviderInfo resolveContentProvider = context.getPackageManager().resolveContentProvider(str + ".MetricaContentProvider", 0);
        if (resolveContentProvider == null || !resolveContentProvider.enabled) {
            return null;
        }
        try {
            String string;
            query = context.getContentResolver().query(Uri.parse(String.format(Locale.US, "content://%s.MetricaContentProvider/DEVICE_ID", new Object[]{str})), null, null, null, null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        string = query.getString(query.getColumnIndex("DEVICE_ID"));
                        bk.m14978a(query);
                        return string;
                    }
                } catch (Exception e) {
                    cursor = query;
                    bk.m14978a(cursor);
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    bk.m14978a(query);
                    throw th;
                }
            }
            string = null;
            bk.m14978a(query);
            return string;
        } catch (Exception e2) {
            cursor = null;
            bk.m14978a(cursor);
            return null;
        } catch (Throwable th3) {
            th = th3;
            query = null;
            bk.m14978a(query);
            throw th;
        }
    }

    ck m15584f() {
        return this.f12102c;
    }

    public static String m15571g(Context context, String str) {
        String a = C4426b.f12099a.m15575a(context);
        if (!bi.m14957a(a)) {
            Intent a2 = be.m14888a(context);
            a2.setPackage(str);
            for (ResolveInfo resolveInfo : be.m14889a(context, a2)) {
                int a3 = be.m14886a(resolveInfo.serviceInfo);
                if (a3 > 0 && a3 < 29) {
                    try {
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("DEVICE_ID", a);
                        if (!bi.m14957a(a)) {
                            context.getContentResolver().update(Uri.parse(String.format(Locale.US, "content://%s.MetricaContentProvider/DEVICE_ID", new Object[]{str})), contentValues, null, null);
                        }
                    } catch (Exception e) {
                    }
                }
            }
        }
        return a;
    }

    static /* synthetic */ void m15570a(ci ciVar, Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            YandexMetrica.getReporter(context, "20799a27-fa80-4b36-b2db-0f8141f24180").reportEvent("saving_empty_device_id", new C4427c(context, str));
        } else {
            ciVar.m15581d(context, str);
        }
    }
}
