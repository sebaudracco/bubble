package com.bgjd.ici.p029d;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.support.v4.app.NotificationCompat;
import android.webkit.MimeTypeMap;
import com.bgjd.ici.json.JSONArray;
import com.bgjd.ici.json.JSONException;
import com.bgjd.ici.json.JSONObject;
import com.bgjd.ici.p025b.C1395h;
import com.bgjd.ici.p025b.C1402i;
import com.bgjd.ici.p025b.C1408j.C1407c.C1406b;
import com.bgjd.ici.p025b.C1409k;
import com.bgjd.ici.p025b.C1410m;
import com.bgjd.ici.p028c.C1434d;
import com.bgjd.ici.p028c.C1435a;
import com.bgjd.ici.p028c.C1436e;
import com.bgjd.ici.p030e.C1481f;
import com.bgjd.ici.p031f.C1491d;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class C1459j extends C1409k<JSONObject> {
    private static final String f2299f = "PKG";
    private long f2300g;
    private int f2301h;

    public /* synthetic */ Object mo2325d() {
        return m3021f();
    }

    public C1459j(C1395h c1395h) {
        super(c1395h);
        this.f2300g = 0;
        this.f2301h = 0;
        this.b = "package";
        this.e = C1406b.f2151a;
    }

    public JSONObject m3021f() {
        BufferedReader bufferedReader;
        int i;
        int i2;
        Exception e;
        Object jSONObject;
        JSONObject jSONObject2;
        Throwable th;
        Object g;
        int a;
        BufferedReader bufferedReader2;
        Exception e2;
        boolean IsSandbox = this.a.IsSandbox();
        boolean isAccepted = this.a.isAccepted();
        JSONArray jSONArray = new JSONArray();
        PackageManager packageManager = this.a.getContext().getPackageManager();
        C1402i.m2901b(f2299f, "Started...");
        String packageFilter;
        String readLine;
        if (IsSandbox || !isAccepted) {
            int declinedTransaction;
            int declinedTransaction2;
            packageFilter = this.a.getPackageFilter();
            String systemFilter = this.a.getSystemFilter();
            try {
                Process exec = Runtime.getRuntime().exec("pm list packages");
                bufferedReader = new BufferedReader(new InputStreamReader(exec.getInputStream()));
                i = 0;
                i2 = 0;
                while (true) {
                    try {
                        readLine = bufferedReader.readLine();
                        if (readLine == null) {
                            break;
                        }
                        readLine = readLine.substring(readLine.indexOf(58) + 1);
                        try {
                            PackageInfo packageInfo = packageManager.getPackageInfo(readLine, 128);
                            if (readLine.matches(packageFilter)) {
                                if (new C1462m(packageManager, packageInfo, systemFilter).m3029d() == 0) {
                                    i++;
                                } else {
                                    i2++;
                                }
                                this.c++;
                            }
                        } catch (NameNotFoundException e3) {
                        }
                    } catch (Exception e4) {
                        e = e4;
                    }
                }
                exec.waitFor();
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException e5) {
                    }
                }
            } catch (Exception e6) {
                bufferedReader = null;
                i2 = 0;
                e = e6;
                i = 0;
                try {
                    e.printStackTrace();
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e7) {
                        }
                    }
                    jSONObject = new JSONObject();
                    this.f2300g = System.currentTimeMillis();
                    declinedTransaction = this.a.getDeclinedTransaction(NotificationCompat.CATEGORY_SYSTEM);
                    declinedTransaction2 = this.a.getDeclinedTransaction("nonsys");
                    i2 = 0;
                    i = 0;
                    jSONObject.put("a", (Object) "com.system");
                    jSONObject.put("b", (Object) "System apps");
                    jSONObject.put("c", 1);
                    jSONObject.put("d", this.f2300g);
                    jSONObject.put("e", i2);
                    jSONArray.put(jSONObject);
                    jSONObject = new JSONObject();
                    jSONObject.put("a", (Object) "com.nonsystem");
                    jSONObject.put("b", (Object) "Non System Apps");
                    jSONObject.put("c", 1);
                    jSONObject.put("d", this.f2300g);
                    jSONObject.put("e", i);
                    jSONArray.put(jSONObject);
                    if (i2 > 0) {
                        this.a.setDeclinedTransaction(NotificationCompat.CATEGORY_SYSTEM, i2);
                    }
                    if (i > 0) {
                        this.a.setDeclinedTransaction("nonsys", i);
                    }
                    C1402i.m2901b(f2299f, "Completed Total count : " + this.c);
                    jSONObject2 = new JSONObject();
                    jSONObject2.put("a", this.f2300g > this.d ? jSONArray : new JSONArray());
                    jSONObject2.put("b", jSONArray.length());
                    jSONObject2.put("c", this.f2300g > this.d ? this.f2300g : this.d);
                    i = this.a.getPackageMaxId();
                    this.a.setPackageMaxId(this.f2301h);
                    this.a.setTransactionHistory(mo2298a(), Long.valueOf(mo2300c()));
                    return jSONObject2;
                } catch (Throwable th2) {
                    th = th2;
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e8) {
                        }
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                bufferedReader = null;
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                throw th;
            }
            jSONObject = new JSONObject();
            this.f2300g = System.currentTimeMillis();
            try {
                declinedTransaction = this.a.getDeclinedTransaction(NotificationCompat.CATEGORY_SYSTEM);
                declinedTransaction2 = this.a.getDeclinedTransaction("nonsys");
                if (declinedTransaction > 0 && declinedTransaction >= r1) {
                    i2 = 0;
                }
                if (declinedTransaction2 > 0 && declinedTransaction2 >= r0) {
                    i = 0;
                }
                jSONObject.put("a", (Object) "com.system");
                jSONObject.put("b", (Object) "System apps");
                jSONObject.put("c", 1);
                jSONObject.put("d", this.f2300g);
                jSONObject.put("e", i2);
                jSONArray.put(jSONObject);
                jSONObject = new JSONObject();
                jSONObject.put("a", (Object) "com.nonsystem");
                jSONObject.put("b", (Object) "Non System Apps");
                jSONObject.put("c", 1);
                jSONObject.put("d", this.f2300g);
                jSONObject.put("e", i);
                jSONArray.put(jSONObject);
                if (i2 > 0) {
                    this.a.setDeclinedTransaction(NotificationCompat.CATEGORY_SYSTEM, i2);
                }
                if (i > 0) {
                    this.a.setDeclinedTransaction("nonsys", i);
                }
            } catch (JSONException e9) {
            }
        } else {
            C1434d c1435a = new C1435a(new C1410m(this.a));
            c1435a.open();
            if (c1435a.IsConnected()) {
                C1481f c1481f;
                C1491d c1491d = (C1491d) c1435a.getMapper(C1491d.class, C1481f.class);
                C1436e a2 = c1491d.m3181a();
                while (a2.read()) {
                    c1481f = (C1481f) a2.fetch();
                    g = c1481f.m3097g();
                    readLine = "";
                    try {
                        if (c1481f.m3094d() == 0) {
                            readLine = m3019a(c1481f.m3092c(), packageManager);
                        }
                        g.put("f", readLine.toString());
                    } catch (JSONException e10) {
                    }
                    jSONArray.put(g);
                    this.c++;
                    a = c1481f.m3085a();
                    if (a > this.f2301h) {
                        this.f2301h = a;
                    }
                    this.f2300g = c1481f.m3096f();
                    if (this.c >= 200) {
                        break;
                    }
                }
                a2.close();
                if (this.c == 0) {
                    String packageFilter2 = this.a.getPackageFilter();
                    packageFilter = this.a.getSystemFilter();
                    try {
                        Process exec2 = Runtime.getRuntime().exec("pm list packages");
                        bufferedReader2 = new BufferedReader(new InputStreamReader(exec2.getInputStream()));
                        while (true) {
                            try {
                                String readLine2 = bufferedReader2.readLine();
                                if (readLine2 == null) {
                                    break;
                                }
                                readLine2 = readLine2.substring(readLine2.indexOf(58) + 1);
                                try {
                                    PackageInfo packageInfo2 = packageManager.getPackageInfo(readLine2, 128);
                                    if (readLine2.matches(packageFilter2)) {
                                        c1491d.m3183a(new C1462m(packageManager, packageInfo2, packageFilter));
                                    }
                                } catch (NameNotFoundException e11) {
                                }
                            } catch (Exception e12) {
                                e2 = e12;
                            }
                        }
                        exec2.waitFor();
                        if (bufferedReader2 != null) {
                            try {
                                bufferedReader2.close();
                            } catch (IOException e13) {
                            }
                        }
                    } catch (Exception e14) {
                        e2 = e14;
                        bufferedReader2 = null;
                        try {
                            e2.printStackTrace();
                            if (bufferedReader2 != null) {
                                try {
                                    bufferedReader2.close();
                                } catch (IOException e15) {
                                }
                            }
                            if (this.c < 200) {
                                a2 = c1491d.m3182a(this.d, this.a.getContext().getPackageName(), 200 - this.c);
                                while (a2.read()) {
                                    c1481f = (C1481f) a2.fetch();
                                    g = c1481f.m3097g();
                                    readLine = "";
                                    try {
                                        if (c1481f.m3094d() == 0) {
                                            readLine = m3019a(c1481f.m3092c(), packageManager);
                                        }
                                        g.put("f", readLine.toString());
                                    } catch (JSONException e16) {
                                    }
                                    a = c1481f.m3085a();
                                    if (a > this.f2301h) {
                                        this.f2301h = a;
                                    }
                                    if (c1491d.m3179a(a) > 0) {
                                        jSONArray.put(g);
                                        this.c++;
                                        this.f2300g = c1481f.m3096f();
                                        if (this.c >= 200) {
                                            break;
                                        }
                                    }
                                }
                                a2.close();
                            }
                            c1435a.close();
                            C1402i.m2901b(f2299f, "Completed Total count : " + this.c);
                            jSONObject2 = new JSONObject();
                            if (this.f2300g > this.d) {
                            }
                            jSONObject2.put("a", this.f2300g > this.d ? jSONArray : new JSONArray());
                            jSONObject2.put("b", jSONArray.length());
                            if (this.f2300g > this.d) {
                            }
                            jSONObject2.put("c", this.f2300g > this.d ? this.f2300g : this.d);
                            i = this.a.getPackageMaxId();
                            this.a.setPackageMaxId(this.f2301h);
                            this.a.setTransactionHistory(mo2298a(), Long.valueOf(mo2300c()));
                            return jSONObject2;
                        } catch (Throwable th4) {
                            th = th4;
                            if (bufferedReader2 != null) {
                                try {
                                    bufferedReader2.close();
                                } catch (IOException e17) {
                                }
                            }
                            throw th;
                        }
                    } catch (Throwable th5) {
                        th = th5;
                        bufferedReader2 = null;
                        if (bufferedReader2 != null) {
                            bufferedReader2.close();
                        }
                        throw th;
                    }
                }
                if (this.c < 200) {
                    a2 = c1491d.m3182a(this.d, this.a.getContext().getPackageName(), 200 - this.c);
                    while (a2.read()) {
                        c1481f = (C1481f) a2.fetch();
                        g = c1481f.m3097g();
                        readLine = "";
                        if (c1481f.m3094d() == 0) {
                            readLine = m3019a(c1481f.m3092c(), packageManager);
                        }
                        g.put("f", readLine.toString());
                        a = c1481f.m3085a();
                        if (a > this.f2301h) {
                            this.f2301h = a;
                        }
                        if (c1491d.m3179a(a) > 0) {
                            jSONArray.put(g);
                            this.c++;
                            this.f2300g = c1481f.m3096f();
                            if (this.c >= 200) {
                                break;
                            }
                        }
                    }
                    a2.close();
                }
            }
            c1435a.close();
        }
        C1402i.m2901b(f2299f, "Completed Total count : " + this.c);
        jSONObject2 = new JSONObject();
        try {
            if (this.f2300g > this.d) {
            }
            jSONObject2.put("a", this.f2300g > this.d ? jSONArray : new JSONArray());
            jSONObject2.put("b", jSONArray.length());
            if (this.f2300g > this.d) {
            }
            jSONObject2.put("c", this.f2300g > this.d ? this.f2300g : this.d);
            i = this.a.getPackageMaxId();
            if (this.f2301h > 0 && this.f2301h > i) {
                this.a.setPackageMaxId(this.f2301h);
            }
            this.a.setTransactionHistory(mo2298a(), Long.valueOf(mo2300c()));
        } catch (JSONException e18) {
        }
        return jSONObject2;
    }

    private String m3019a(String str, PackageManager packageManager) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            String[] strArr = packageManager.getPackageInfo(str, 4096).requestedPermissions;
            if (strArr != null && strArr.length > 0) {
                int length = strArr.length;
                Object obj = 1;
                int i = 0;
                while (i < length) {
                    String fileExtensionFromUrl = MimeTypeMap.getFileExtensionFromUrl(strArr[i]);
                    if (obj == null) {
                        stringBuilder.append("|");
                    }
                    stringBuilder.append(fileExtensionFromUrl);
                    i++;
                    obj = null;
                }
            }
        } catch (NameNotFoundException e) {
        } catch (Exception e2) {
        }
        return stringBuilder.toString();
    }
}
