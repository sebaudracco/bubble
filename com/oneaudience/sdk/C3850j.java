package com.oneaudience.sdk;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.ViewGroup.LayoutParams;
import com.bgjd.ici.p025b.C1408j.C1404b;
import com.github.lzyzsd.jsbridge.BridgeUtil;
import com.oneaudience.sdk.C3875l.C3849h;
import com.oneaudience.sdk.p135c.C3828b;
import com.oneaudience.sdk.p135c.C3833d;
import com.oneaudience.sdk.p135c.C3833d.C3831b;
import com.oneaudience.sdk.p135c.p136a.C3821a;
import com.oneaudience.sdk.p135c.p136a.C3822b;
import com.stepleaderdigital.reveal.Reveal;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class C3850j extends Dialog implements C3849h {
    private static final String f9215a = C3850j.class.getSimpleName();
    private static long f9216b = Reveal.CHECK_DELAY;
    private C3875l f9217c;
    private C3831b f9218d = null;
    private String f9219e = null;
    private String f9220f = null;
    private Context f9221g;

    public C3850j(Context context, String str, String str2) {
        super(context);
        try {
            this.f9221g = context;
            setCancelable(false);
            setCanceledOnTouchOutside(false);
            getWindow().setBackgroundDrawable(new ColorDrawable(0));
            this.f9220f = str;
            this.f9219e = str2;
            m12298a();
            this.f9218d = C3833d.m12258f(f9215a, "Getting url.");
            m12302b();
        } catch (Throwable e) {
            C3833d.m12250b(f9215a, "Error loading Activity", e);
        }
    }

    private void m12298a() {
        if (this.f9217c == null) {
            this.f9217c = new C3875l(getContext(), f9216b, 0);
            this.f9217c.m12376a((C3849h) this);
        }
        try {
            requestWindowFeature(1);
        } catch (Exception e) {
            C3833d.m12256e(f9215a, "Couldn't change window settings");
        }
        Resources resources = getContext().getResources();
        float applyDimension = TypedValue.applyDimension(1, 380.0f, resources.getDisplayMetrics());
        setContentView(this.f9217c, new LayoutParams((int) TypedValue.applyDimension(1, 280.0f, resources.getDisplayMetrics()), (int) applyDimension));
    }

    private static void m12299a(Context context) {
        C3833d.m12252c(f9215a, "Deleting Cached Files");
        SharedPreferences sharedPreferences = context.getSharedPreferences("oneAudienceEula", 0);
        Iterator it = C3877m.m12381a(sharedPreferences, "cached_files").iterator();
        while (it.hasNext()) {
            C3833d.m12253c(f9215a, "Deleting Cached File: %s", (String) it.next());
            try {
                new File((String) it.next()).delete();
            } catch (Throwable e) {
                C3833d.m12250b(f9215a, "Error Deleting Cached File: %s", e);
            }
        }
        C3877m.m12382a(sharedPreferences, "cached_files", new ArrayList());
    }

    public static void m12300a(final Context context, final String str) {
        C3833d.m12248a(f9215a, "startWebView was called with url %s", str);
        C3850j.m12299a(context);
        String absolutePath = context.getCacheDir().getAbsolutePath();
        final String str2 = absolutePath + BridgeUtil.SPLIT_MARK + UUID.randomUUID().toString() + ".html";
        SharedPreferences sharedPreferences = context.getSharedPreferences("oneAudienceEula", 0);
        Serializable a = C3877m.m12381a(sharedPreferences, "cached_files");
        a.add(str2);
        C3877m.m12382a(sharedPreferences, "cached_files", a);
        C3822b a2 = new C3820b().m12225a(new C3821a(str, null, null, false));
        File file = new File(str2);
        if (a2.f9187c == null) {
            C3833d.m12254d(f9215a, "Eula response data is null failed to start Eula");
            return;
        }
        String obj = a2.f9187c.toString();
        C3850j.m12301a(sharedPreferences, absolutePath, obj, C1404b.aJ);
        C3850j.m12301a(sharedPreferences, absolutePath, obj, "js");
        C3850j.m12301a(sharedPreferences, absolutePath, obj, "png");
        C3850j.m12301a(sharedPreferences, absolutePath, obj, "bmp");
        C3850j.m12301a(sharedPreferences, absolutePath, obj, "jpg");
        try {
            C3833d.m12253c(f9215a, "Final HTML: %s", obj);
            C3833d.m12253c(f9215a, "Saving html file: %s", str2);
            C3828b.m12239a(obj, file);
        } catch (IOException e) {
            C3833d.m12257e(f9215a, "Couldn't save html file: %s", str2);
        }
        switch (a2.f9185a) {
            case 100000:
                C3833d.m12252c(f9215a, "Finished loading page");
                if (file.exists()) {
                    C3833d.m12252c(f9215a, "Downloading Assets");
                    new Handler(context.getMainLooper()).post(new Runnable() {
                        public void run() {
                            new C3850j(new ContextThemeWrapper(context, 16973835), str2, str).show();
                        }
                    });
                    return;
                }
                return;
            default:
                C3833d.m12252c(f9215a, "Finished loading page - Failed Launching Webview");
                return;
        }
    }

    private static void m12301a(SharedPreferences sharedPreferences, String str, String str2, String str3) {
        Serializable a = C3877m.m12381a(sharedPreferences, "cached_files");
        Matcher matcher = Pattern.compile("http(\\S+)." + str3).matcher(str2);
        while (matcher.find()) {
            String str4 = "http" + matcher.group(1) + "." + str3;
            C3833d.m12253c(f9215a, "Downloading Assets: %s", str4);
            String str5 = str + BridgeUtil.SPLIT_MARK + str4.substring(str4.lastIndexOf(47) + 1);
            a.add(str5);
            try {
                File file = new File(str5);
                InputStream openStream = new URL(str4).openStream();
                OutputStream fileOutputStream = new FileOutputStream(file);
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = openStream.read(bArr);
                    if (read == -1) {
                        break;
                    }
                    fileOutputStream.write(bArr, 0, read);
                }
                fileOutputStream.flush();
                fileOutputStream.close();
                C3833d.m12253c(f9215a, "Done Saving file: %s", str4, str5);
            } catch (IOException e) {
                C3833d.m12257e(f9215a, "Save error file: %s", str5);
            }
        }
        C3877m.m12382a(sharedPreferences, "cached_files", a);
    }

    private void m12302b() {
        if (this.f9218d != null) {
            this.f9218d.mo6808a("Fetching ad.");
        }
        this.f9217c.m12377a(this.f9220f, this.f9219e);
    }

    public void mo6809a(C3875l c3875l) {
    }

    public void mo6810a(C3875l c3875l, boolean z) {
        if (z) {
            C3861k.m12321a(this.f9221g).m12338c();
        } else {
            C3861k.m12321a(this.f9221g).m12339d();
        }
        dismiss();
    }

    public void mo6811b(C3875l c3875l) {
        dismiss();
    }

    public void onBackPressed() {
        this.f9217c.m12375a();
    }
}
