package com.elephant.data.p037d.p043c;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import com.bgjd.ici.p025b.C1408j.C1404b;
import com.elephant.data.p048g.C1814b;
import com.elephant.data.p048g.C1816d;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Timer;

public class C1757d {
    private static final String f3643a = C1814b.jC;
    private static C1757d f3644c;
    private Context f3645b;
    private C1759f f3646d;
    private Timer f3647e;
    private C1753a f3648f;
    private BroadcastReceiver f3649g = new C1758e(this);

    private C1757d(Context context) {
        this.f3645b = context.getApplicationContext();
        this.f3645b.getSystemService(C1404b.aw);
        try {
            IntentFilter intentFilter = new IntentFilter("android.intent.action.SCREEN_OFF");
            intentFilter.addAction("android.intent.action.USER_PRESENT");
            intentFilter.addAction("android.intent.action.SCREEN_ON");
            this.f3645b.registerReceiver(this.f3649g, intentFilter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static C1757d m5063a(Context context) {
        if (f3644c != null) {
            return f3644c;
        }
        synchronized (C1757d.class) {
            if (f3644c == null) {
                f3644c = new C1757d(context);
            }
        }
        return f3644c;
    }

    private static String m5064a(String str) {
        InputStream inputStream;
        ByteArrayOutputStream byteArrayOutputStream;
        Throwable th;
        try {
            try {
                inputStream = Runtime.getRuntime().exec(str).getInputStream();
                try {
                    byteArrayOutputStream = new ByteArrayOutputStream();
                } catch (Throwable th2) {
                    th = th2;
                    byteArrayOutputStream = null;
                    inputStream.close();
                    byteArrayOutputStream.close();
                    throw th;
                }
                try {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = inputStream.read(bArr);
                        if (read != -1) {
                            byteArrayOutputStream.write(bArr, 0, read);
                        } else {
                            String str2 = new String(byteArrayOutputStream.toByteArray());
                            inputStream.close();
                            byteArrayOutputStream.close();
                            return str2;
                        }
                    }
                } catch (Throwable th3) {
                    th = th3;
                    inputStream.close();
                    byteArrayOutputStream.close();
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
                byteArrayOutputStream = null;
                inputStream = null;
                inputStream.close();
                byteArrayOutputStream.close();
                throw th;
            }
        } catch (Throwable th5) {
            th5.printStackTrace();
            return null;
        }
    }

    static /* synthetic */ C1756c m5065b(C1757d c1757d) {
        HashSet c = c1757d.m5066c();
        C1756c c1756c = new C1756c();
        if (c == null || c.size() == 0) {
            return c1756c;
        }
        if (c.contains(C1760g.f3653a)) {
            c1756c.f3641a = C1760g.f3653a;
            c1756c.f3642b = 3;
        } else {
            List<String> arrayList = new ArrayList();
            if (!(c1757d.f3648f == null || c1757d.f3648f.m5058c() == null || c1757d.f3648f.m5058c().size() <= 0)) {
                arrayList.addAll(c1757d.f3648f.m5058c());
            }
            if (C1816d.m5331v(c1757d.f3645b) != null) {
                arrayList.addAll(C1816d.m5331v(c1757d.f3645b));
            }
            if (arrayList.size() > 0) {
                for (String str : arrayList) {
                    if (c.contains(str)) {
                        c1756c.f3641a = str;
                        c1756c.f3642b = 2;
                        break;
                    }
                }
            }
        }
        return c1756c;
    }

    private HashSet m5066c() {
        HashSet hashSet = new HashSet();
        try {
            for (String str : C1757d.m5064a(C1814b.jD).split(C1814b.jE)) {
                int lastIndexOf = str.trim().lastIndexOf(32);
                if (lastIndexOf > 0 && lastIndexOf < str.length()) {
                    String trim = str.substring(lastIndexOf).trim();
                    if (str.contains(C1814b.jF) && str.startsWith(C1814b.jG) && !trim.contains(C1814b.jH) && !trim.contains(C1814b.jI)) {
                        hashSet.add(trim);
                    }
                }
            }
            return hashSet;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    public final void m5067a() {
        if (this.f3648f == null || !this.f3648f.m5059d()) {
            m5068b();
            this.f3648f = new C1753a(this.f3645b);
            if (this.f3648f.m5056a()) {
                if (this.f3646d == null) {
                    this.f3646d = new C1759f(this);
                }
                if (this.f3647e == null) {
                    this.f3647e = new Timer(f3643a);
                }
                if (this.f3647e != null && this.f3646d != null) {
                    synchronized (this.f3646d) {
                        if (!this.f3646d.f3651a) {
                            this.f3646d.f3651a = true;
                            this.f3647e.schedule(this.f3646d, 0, (long) this.f3648f.m5057b());
                        }
                    }
                }
            }
        }
    }

    public final void m5068b() {
        if (this.f3646d != null) {
            synchronized (this.f3646d) {
                this.f3646d.cancel();
                this.f3646d.f3651a = false;
                this.f3646d = null;
            }
        }
        if (this.f3647e != null) {
            this.f3647e.cancel();
            this.f3647e = null;
        }
        if (this.f3648f != null) {
            this.f3648f.m5055a(false);
            this.f3648f = null;
        }
    }
}
