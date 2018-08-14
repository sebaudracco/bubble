package com.adcolony.sdk;

import android.app.Activity;
import com.adcolony.sdk.aa.C0595a;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ExecutorService;
import org.json.JSONArray;
import org.json.JSONObject;

class ADCVMModule implements ai {
    static boolean f338a = false;
    int f339b;
    ExecutorService f340c;
    private boolean f341d;
    private JSONArray f342e = C0802y.m1469b();
    private Runnable f343f;
    private Runnable f344g;
    private ADCJSVirtualMachine f345h;

    class C05751 implements Runnable {
        final /* synthetic */ ADCVMModule f331a;

        C05751(ADCVMModule aDCVMModule) {
            this.f331a = aDCVMModule;
        }

        public void run() {
            if (!C0594a.m605a().m1278h()) {
                this.f331a.f345h.m519a();
            }
        }
    }

    class C05762 implements Runnable {
        final /* synthetic */ ADCVMModule f332a;

        C05762(ADCVMModule aDCVMModule) {
            this.f332a = aDCVMModule;
        }

        public void run() {
            String str = "";
            synchronized (this.f332a.f342e) {
                if (this.f332a.f342e.length() > 0) {
                    str = this.f332a.f342e.toString();
                    this.f332a.f342e = C0802y.m1469b();
                }
            }
            if (!C0594a.m605a().m1278h()) {
                try {
                    str = new String(this.f332a.f345h.m520a(str.getBytes("UTF-8")), "UTF-8");
                } catch (Exception e) {
                    new C0595a().m622a("VM update failed: ").m622a(az.m872a(e)).m624a(aa.f484h);
                    C0594a.m605a().m1287q().m700a(this.f332a.f339b);
                }
                if (str != null && str.length() > 2) {
                    JSONArray b = C0802y.m1470b(str);
                    if (b != null) {
                        for (int i = 0; i < b.length(); i++) {
                            JSONObject a = C0802y.m1455a(b, i);
                            if (a != null) {
                                C0594a.m605a().m1287q().m705a(a);
                            }
                        }
                        return;
                    }
                    return;
                }
            }
            str = null;
            if (str != null) {
            }
        }
    }

    private class ADCJSVirtualMachine {
        final /* synthetic */ ADCVMModule f335a;
        private long f336b;
        private int f337c;

        class C05771 implements Runnable {
            final /* synthetic */ ADCJSVirtualMachine f333a;

            C05771(ADCJSVirtualMachine aDCJSVirtualMachine) {
                this.f333a = aDCJSVirtualMachine;
            }

            public void run() {
                af afVar = new af("AdSession.finish_fullscreen_ad", 0);
                C0802y.m1472b(afVar.m698c(), "status", 1);
                ((C0589b) C0594a.m613c()).mo1845a(afVar);
            }
        }

        class C0578a extends Exception {
            final /* synthetic */ ADCJSVirtualMachine f334a;

            C0578a(ADCJSVirtualMachine aDCJSVirtualMachine, String str) {
                this.f334a = aDCJSVirtualMachine;
                super(str);
            }
        }

        private native long create(int i, byte[] bArr, byte[] bArr2);

        private native void delete(long j, int i);

        private native byte[] update(long j, int i, byte[] bArr);

        ADCJSVirtualMachine(ADCVMModule aDCVMModule, int moduleId, byte[] script, byte[] options) throws C0578a {
            this.f335a = aDCVMModule;
            this.f336b = create(moduleId, script, options);
            if (this.f336b < 0) {
                throw new C0578a(this, "Virtual machine could not be created.");
            }
            this.f337c = moduleId;
        }

        void m519a() {
            delete(this.f336b, this.f337c);
        }

        byte[] m520a(byte[] bArr) throws C0578a {
            byte[] update = update(this.f336b, this.f337c, bArr);
            if (update != null) {
                return update;
            }
            if (C0594a.m614d() && (C0594a.m613c() instanceof C0589b)) {
                az.m880a(new C05771(this));
            }
            if (this.f335a.f341d) {
                try {
                    new File(C0594a.m605a().m1285o().m787g() + "7bf3a1e7bbd31e612eda3310c2cdb8075c43c6b5").delete();
                } catch (Exception e) {
                }
            }
            throw new C0578a(this, "Virtual machine error.");
        }
    }

    ADCVMModule(Activity activity, int moduleId, String filepath, JSONObject info, ExecutorService es) {
        InputStream open;
        int available;
        byte[] bArr;
        String str;
        byte[] bytes;
        boolean z = false;
        this.f339b = moduleId;
        this.f340c = es;
        C0740l a = C0594a.m605a();
        if (moduleId == 1) {
            z = true;
        }
        this.f341d = z;
        if (moduleId == 1 && f338a) {
            filepath = "ADCController.js";
        }
        new C0595a().m622a("----------------------------------------------------------------------").m624a(aa.f478b);
        new C0595a().m622a("CREATING VM ").m622a(filepath).m624a(aa.f478b);
        if (moduleId == 1) {
            try {
                if (f338a) {
                    open = activity.getAssets().open(filepath);
                    available = open.available();
                    bArr = new byte[available];
                    open.read(bArr, 0, available);
                    open.close();
                    if (a.m1278h()) {
                        bArr = null;
                    }
                    if (bArr != null) {
                        str = "";
                        if (info != null) {
                            str = info.toString();
                        }
                        bytes = str.getBytes("UTF-8");
                        if (moduleId == 1 && !f338a && C0802y.m1449a(a.m1268c(), "item", 0) % 2 == 1) {
                            bArr = ADCGeneratedCrypto.decrypt(bArr);
                            if (bArr == null || bArr.length <= 0) {
                                new C0595a().m622a("Can't instantiate controller VM. Deleting controller, launch response, and disabling AdColony.").m624a(aa.f484h);
                                az.m876a();
                                a.m1259a(true);
                                bArr = null;
                            }
                        }
                        if (bArr == null) {
                            this.f345h = new ADCJSVirtualMachine(this, moduleId, bArr, bytes);
                        } else {
                            new C0595a().m622a("Couldn't create virtual machine for: ").m622a(filepath).m624a(aa.f478b);
                        }
                    }
                    new C0595a().m622a("----------------------------------------------------------------------").m624a(aa.f478b);
                }
            } catch (IOException e) {
                new C0595a().m622a("IOException while loading controller JS: ").m622a(e.toString()).m624a(aa.f484h);
            } catch (Exception e2) {
                new C0595a().m622a("Unable to create virtual machine for: ").m622a(filepath).m624a(aa.f484h);
                if (this.f341d) {
                    new C0595a().m622a("Can't instantiate controller VM. Deleting controller, launch response, and disabling AdColony.").m624a(aa.f484h);
                    az.m876a();
                    a.m1259a(true);
                }
            }
        }
        open = new FileInputStream(new File(filepath).getAbsolutePath());
        available = open.available();
        bArr = new byte[available];
        open.read(bArr, 0, available);
        open.close();
        if (a.m1278h()) {
            bArr = null;
        }
        if (bArr != null) {
            str = "";
            if (info != null) {
                str = info.toString();
            }
            bytes = str.getBytes("UTF-8");
            bArr = ADCGeneratedCrypto.decrypt(bArr);
            new C0595a().m622a("Can't instantiate controller VM. Deleting controller, launch response, and disabling AdColony.").m624a(aa.f484h);
            az.m876a();
            a.m1259a(true);
            bArr = null;
            if (bArr == null) {
                new C0595a().m622a("Couldn't create virtual machine for: ").m622a(filepath).m624a(aa.f478b);
            } else {
                this.f345h = new ADCJSVirtualMachine(this, moduleId, bArr, bytes);
            }
        }
        new C0595a().m622a("----------------------------------------------------------------------").m624a(aa.f478b);
    }

    public int mo1841a() {
        return this.f339b;
    }

    public void mo1842a(JSONObject jSONObject) {
        synchronized (this.f342e) {
            this.f342e.put(jSONObject);
        }
    }

    public void mo1843b() {
        if (this.f344g == null) {
            this.f344g = new C05751(this);
        }
        this.f340c.submit(this.f344g);
        this.f340c.shutdown();
    }

    public void mo1844c() {
        if (this.f343f == null) {
            this.f343f = new C05762(this);
        }
        this.f340c.submit(this.f343f);
    }

    public long m533d() {
        return this.f345h.f336b;
    }

    ExecutorService m534e() {
        return this.f340c;
    }
}
