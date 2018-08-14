package com.elephant.data.p037d;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.net.Uri.Builder;
import android.text.TextUtils;
import com.elephant.data.p035a.p036a.C1712b;
import com.elephant.data.p046f.C1804d;
import com.elephant.data.p046f.C1806f;
import com.elephant.data.p048g.C1814b;
import com.elephant.data.p048g.C1816d;
import com.elephant.data.p048g.p049a.C1810b;
import com.elephant.data.p048g.p050b.C1812a;
import cz.msebera.android.httpclient.HttpStatus;
import cz.msebera.android.httpclient.client.params.ClientPNames;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

public final class C1767d extends C1712b implements C1766h {
    private static final Pattern f3674a = Pattern.compile(C1814b.gz, 2);
    private static final Pattern f3675b = Pattern.compile(C1814b.gA, 2);
    private static final Pattern f3676c = Pattern.compile(C1814b.gB, 2);
    private static final Pattern f3677d = Pattern.compile(C1814b.gC, 2);
    private static final Pattern f3678e = Pattern.compile(C1814b.gD);
    private static String f3679f = C1814b.gE;
    private static final String f3680g = C1814b.gF;
    private static final Map f3681p = new ConcurrentHashMap();
    private int f3682h = 30;
    private C1743a f3683i;
    private C1714e f3684j;
    private Context f3685k;
    private boolean f3686l;
    private String f3687m = System.getProperty(C1814b.gG);
    private boolean f3688n = false;
    private boolean f3689o = true;
    private boolean f3690q = false;
    private String f3691r;
    private boolean f3692s = false;
    private C1804d f3693t;
    private int f3694u = 1;

    static {
        String str = C1814b.gy;
    }

    private C1767d(Context context, C1743a c1743a, C1714e c1714e, boolean z) {
        this.f3683i = c1743a;
        this.f3684j = c1714e;
        this.f3686l = z;
        this.f3685k = context;
        this.f3693t = C1806f.m5221a(context).m5223a();
        if (this.f3693t.m5193a()) {
            this.f3694u = 4;
        }
    }

    public C1767d(Context context, String str) {
        this.f3691r = str;
        this.f3685k = context;
        this.f3690q = true;
        this.f3683i = new C1743a(null);
    }

    private static C1743a m5092a(Context context, C1743a c1743a) {
        if (c1743a == null) {
            return c1743a;
        }
        C1743a c1743a2 = (C1743a) f3681p.get(c1743a.m5017a());
        if (c1743a2 == null) {
            Object b = C1810b.m5236b(C1812a.m5249c(context).getString(c1743a.m5017a(), null), C1814b.hb);
            if (!TextUtils.isEmpty(b)) {
                c1743a2 = new C1743a(b);
                c1743a2.m5018a(c1743a.m5027h());
                f3681p.put(c1743a2.m5017a(), c1743a2);
                return c1743a2;
            }
        }
        return c1743a2;
    }

    private String m5093a(String str, String str2) {
        if (!(this.f3685k == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2))) {
            try {
                str = Uri.parse(str).buildUpon().appendQueryParameter(C1814b.gI, String.valueOf(C1816d.m5292b(this.f3685k, str2))).toString();
            } catch (Exception e) {
            }
        }
        return str;
    }

    public static void m5094a(Context context, C1743a c1743a, C1714e c1714e, boolean z, String str) {
        if ((c1743a == null || context == null || !C1816d.m5323n(context)) && c1714e != null) {
            c1714e.mo3530a(c1743a);
        }
        C1743a a = C1767d.m5092a(context, c1743a);
        if (C1767d.m5095a(a, false, c1743a.m5026g())) {
            C1767d c1767d = new C1767d(context, c1743a, c1714e, false);
            if (!TextUtils.isEmpty(str)) {
                c1767d.f3687m = str;
            }
            if (c1743a.m5026g()) {
                c1767d.f3694u = 4;
            }
            c1767d.m4951c();
        } else if (c1714e != null) {
            c1714e.mo3530a(a);
        }
    }

    private static boolean m5095a(C1743a c1743a, boolean z, boolean z2) {
        return z2 || c1743a == null || c1743a.m5025f() || (z && TextUtils.isEmpty(c1743a.m5022c()));
    }

    public static String m5096b(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String[] split = str.split(C1814b.gJ);
        return (split.length < 2 || split[1] == null) ? null : split[1].replace(C1814b.gK, C1814b.gL).replace(C1814b.gM, C1814b.gN);
    }

    public static C1743a m5097c(String str) {
        if (str == null) {
            return null;
        }
        for (C1743a c1743a : f3681p.values()) {
            if (str.equals(c1743a.m5021b()) && !c1743a.m5025f()) {
                return c1743a;
            }
        }
        return null;
    }

    private void m5098d(String str) {
        try {
            m5100e(str);
        } catch (Exception e) {
            m5102f(str);
        }
    }

    private String m5099e() {
        if (!this.f3690q) {
            return m5101f();
        }
        try {
            String k = m5107k(this.f3691r);
            if (this.f3684j == null) {
                return k;
            }
            this.f3684j.mo3530a(null);
            return k;
        } catch (Exception e) {
            if (this.f3684j == null) {
                return null;
            }
            this.f3684j.mo3530a(null);
            return null;
        } catch (Throwable th) {
            if (this.f3684j != null) {
                this.f3684j.mo3530a(null);
            }
        }
    }

    private void m5100e(String str) {
        Object k = m5107k(str);
        if (TextUtils.isEmpty(k)) {
            throw new Exception(f3679f);
        }
        m5103g(k);
    }

    private String m5101f() {
        String a;
        synchronized (this.f3683i.m5017a().intern()) {
            C1743a a2 = C1767d.m5092a(this.f3685k, this.f3683i);
            String d = this.f3683i.m5023d();
            if (C1767d.m5095a(a2, this.f3686l, this.f3683i.m5026g())) {
                a = m5093a(d, this.f3683i.m5021b());
                try {
                    switch (this.f3694u) {
                        case 1:
                            m5100e(a);
                            break;
                        case 2:
                            m5102f(a);
                            break;
                        case 3:
                            this.f3688n = true;
                            m5102f(a);
                            break;
                        case 4:
                            m5098d(a);
                            break;
                        default:
                            m5100e(a);
                            break;
                    }
                    if (!TextUtils.isEmpty(this.f3683i.m5022c()) && C1816d.m5304e(this.f3683i.m5022c())) {
                        C1767d.m5096b(this.f3683i.m5022c());
                    }
                } catch (Throwable th) {
                    m5103g(null);
                }
            } else {
                this.f3683i.m5019a(a2.m5022c());
            }
            a = this.f3683i.m5022c();
        }
        return a;
    }

    private void m5102f(String str) {
        try {
            C1768f.m5114a(this.f3685k, new C1769g(str, this));
            while (this.f3689o) {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (this.f3688n && TextUtils.isEmpty(this.f3683i.m5022c())) {
                m5100e(str);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void m5103g(String str) {
        C1743a c1743a = this.f3683i;
        if (!TextUtils.isEmpty(str)) {
            CharSequence charSequence = C1814b.hc;
            String str2 = C1814b.hd;
            if (str.indexOf(charSequence) >= 0) {
                int indexOf = str.indexOf(str2);
                if (indexOf >= 0) {
                    str2 = str.substring(indexOf + 1);
                    int indexOf2 = str2.indexOf(C1814b.he);
                    if (indexOf2 >= 0) {
                        str2 = str2.substring(0, indexOf2);
                    }
                    str = str.replace(C1814b.hf + str2, "").replace(charSequence, charSequence + str2 + C1814b.hf);
                }
            }
        }
        c1743a.m5019a(str);
        this.f3683i.m5016a(this.f3683i.m5024e() + System.currentTimeMillis());
        if (this.f3683i.m5024e() > 0) {
            f3681p.put(this.f3683i.m5017a(), this.f3683i);
            Editor edit = C1812a.m5249c(this.f3685k).edit();
            edit.putString(this.f3683i.m5017a(), C1810b.m5232a(this.f3683i.m5028i(), C1814b.gH));
            C1812a.m5246a(edit);
        }
    }

    private HttpGet m5104h(String str) {
        try {
            return new HttpGet(str);
        } catch (Exception e) {
            return new HttpGet(C1767d.m5105i(str));
        }
    }

    private static String m5105i(String str) {
        int indexOf = str.indexOf(C1814b.gO);
        if (indexOf <= 0) {
            return str;
        }
        String[] split = str.substring(indexOf + 1).split(C1814b.gP);
        if (split == null || split.length == 0) {
            return str;
        }
        Builder buildUpon = Uri.parse(str.substring(0, indexOf)).buildUpon();
        for (String str2 : split) {
            String str22;
            int indexOf2 = str22.indexOf(C1814b.gQ);
            if (indexOf2 > 0) {
                String substring = str22.substring(0, indexOf2);
                str22 = str22.substring(indexOf2 + 1);
                try {
                    str22 = URLDecoder.decode(str22, C1814b.gR);
                } catch (Exception e) {
                }
                buildUpon.appendQueryParameter(substring, str22);
            }
        }
        return f3678e.matcher(buildUpon.toString()).replaceAll("");
    }

    private static boolean m5106j(String str) {
        int indexOf = str.indexOf(f3680g);
        return indexOf >= 0 && indexOf < 3;
    }

    private String m5107k(String str) {
        while (this.f3682h > 0) {
            this.f3682h--;
            CharSequence charSequence = C1814b.gS;
            String str2 = C1814b.gT;
            if (C1767d.m5106j(str)) {
                return str.substring(str.indexOf(f3680g));
            }
            URL url = new URL(str);
            if (url.getHost().contains(charSequence) || url.getPath().endsWith(str2)) {
                return str;
            }
            HttpParams basicHttpParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(basicHttpParams, 600000);
            HttpConnectionParams.setSoTimeout(basicHttpParams, 600000);
            DefaultHttpClient defaultHttpClient = new DefaultHttpClient(basicHttpParams);
            defaultHttpClient.getParams().setParameter(ClientPNames.HANDLE_REDIRECTS, Boolean.valueOf(false));
            HttpUriRequest h = m5104h(str);
            if (this.f3687m != null && this.f3687m.length() > 0) {
                h.setHeader(C1814b.gU, this.f3687m);
            }
            HttpResponse execute = defaultHttpClient.execute(h);
            int statusCode = execute.getStatusLine().getStatusCode();
            if (this.f3683i != null) {
                this.f3683i.m5017a();
            }
            if (HttpStatus.SC_MULTIPLE_CHOICES >= statusCode || statusCode >= HttpStatus.SC_BAD_REQUEST) {
                charSequence = EntityUtils.toString(execute.getEntity(), C1814b.ha);
                if (statusCode == 200) {
                    Matcher matcher = f3674a.matcher(charSequence);
                    if (matcher.find()) {
                        str = matcher.group(1);
                    } else {
                        matcher = f3675b.matcher(charSequence);
                        if (matcher.find()) {
                            str = matcher.group(1);
                        } else {
                            matcher = f3676c.matcher(charSequence);
                            if (matcher.find()) {
                                str = matcher.group(1);
                            } else {
                                Matcher matcher2 = f3677d.matcher(charSequence);
                                if (matcher2.find()) {
                                    str = matcher2.group(1);
                                }
                            }
                        }
                    }
                }
            } else {
                Header[] allHeaders = execute.getAllHeaders();
                int length = allHeaders.length;
                int i = 0;
                while (i < length) {
                    Header header = allHeaders[i];
                    if (header.getName().equalsIgnoreCase(C1814b.gV)) {
                        str = header.getValue();
                        if (!(C1767d.m5106j(str) || str.startsWith(C1814b.gW))) {
                            i = -1;
                            if (url.getPort() > 0) {
                                i = url.getPort();
                            }
                            str = i > 0 ? url.getProtocol() + C1814b.gX + url.getHost() + C1814b.gY + i + str : url.getProtocol() + C1814b.gZ + url.getHost() + str;
                        }
                    } else {
                        i++;
                    }
                }
            }
            return null;
        }
        return null;
    }

    protected final /* synthetic */ Object mo3531a() {
        return m5099e();
    }

    public final void mo3535a(String str) {
        m5103g(str);
        this.f3689o = false;
    }

    public final void mo3536d() {
        if (!this.f3688n) {
            m5103g(null);
        }
        this.f3689o = false;
    }
}
