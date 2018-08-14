package com.altbeacon.beacon.p012b;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.AsyncTask;
import com.altbeacon.beacon.p012b.C0828f.C0825a;
import com.altbeacon.beacon.p013c.C0835d;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
import org.json.JSONArray;
import org.json.JSONObject;

public class C0827e implements C0822c {
    Map<C0821a, C0822c> f1619a;
    private C0821a f1620b;
    private C0822c f1621c;
    private C0821a f1622d;
    private C0821a f1623e;
    private String f1624f;
    private Context f1625g;
    private final ReentrantLock f1626h;

    class C08261 implements C0825a {
        final /* synthetic */ C0827e f1618a;

        C08261(C0827e c0827e) {
            this.f1618a = c0827e;
        }

        public void mo1873a(String str, Exception exception, int i) {
            if (exception != null) {
                C0835d.m1662c("ModelSpecificDistanceCalculator", "Cannot updated distance models from online database at %s", exception, this.f1618a.f1624f);
            } else if (i != 200) {
                C0835d.m1662c("ModelSpecificDistanceCalculator", "Cannot updated distance models from online database at %s due to HTTP status code %s", this.f1618a.f1624f, Integer.valueOf(i));
            } else {
                C0835d.m1657a("ModelSpecificDistanceCalculator", "Successfully downloaded distance models from online database", new Object[0]);
                try {
                    this.f1618a.m1611a(str);
                    if (this.f1618a.m1605b(str)) {
                        this.f1618a.m1603b();
                        this.f1618a.f1621c = this.f1618a.m1610a(this.f1618a.f1623e);
                        C0835d.m1660b("ModelSpecificDistanceCalculator", "Successfully updated distance model with latest from online database", new Object[0]);
                    }
                } catch (Throwable e) {
                    C0835d.m1658a(e, "ModelSpecificDistanceCalculator", "Cannot parse json from downloaded distance model", new Object[0]);
                }
            }
        }
    }

    public C0827e(Context context, String str) {
        this(context, str, C0821a.m1584a());
    }

    public C0827e(Context context, String str, C0821a c0821a) {
        this.f1624f = null;
        this.f1626h = new ReentrantLock();
        this.f1623e = c0821a;
        this.f1624f = str;
        this.f1625g = context;
        m1600a();
        this.f1621c = m1610a(c0821a);
    }

    private void m1600a() {
        if (!(this.f1624f == null || m1603b())) {
            m1607c();
        }
        this.f1621c = m1610a(this.f1623e);
    }

    private C0822c m1602b(C0821a c0821a) {
        C0835d.m1657a("ModelSpecificDistanceCalculator", "Finding best distance calculator for %s, %s, %s, %s", c0821a.m1586b(), c0821a.m1587c(), c0821a.m1588d(), c0821a.m1589e());
        if (this.f1619a == null) {
            C0835d.m1657a("ModelSpecificDistanceCalculator", "Cannot get distance calculator because modelMap was never initialized", new Object[0]);
            return null;
        }
        C0821a c0821a2 = null;
        int i = 0;
        for (C0821a c0821a3 : this.f1619a.keySet()) {
            C0821a c0821a32;
            int a;
            if (c0821a32.m1585a(c0821a) > i) {
                a = c0821a32.m1585a(c0821a);
            } else {
                c0821a32 = c0821a2;
                a = i;
            }
            i = a;
            c0821a2 = c0821a32;
        }
        if (c0821a2 != null) {
            C0835d.m1657a("ModelSpecificDistanceCalculator", "found a match with score %s", Integer.valueOf(i));
            C0835d.m1657a("ModelSpecificDistanceCalculator", "Finding best distance calculator for %s, %s, %s, %s", c0821a2.m1586b(), c0821a2.m1587c(), c0821a2.m1588d(), c0821a2.m1589e());
            this.f1622d = c0821a2;
        } else {
            this.f1622d = this.f1620b;
            C0835d.m1662c("ModelSpecificDistanceCalculator", "Cannot find match for this device.  Using default", new Object[0]);
        }
        return (C0822c) this.f1619a.get(this.f1622d);
    }

    private boolean m1603b() {
        FileInputStream fileInputStream;
        BufferedReader bufferedReader;
        FileInputStream fileInputStream2;
        Throwable e;
        BufferedReader bufferedReader2 = null;
        File file = new File(this.f1625g.getFilesDir(), "model-distance-calculations.json");
        StringBuilder stringBuilder = new StringBuilder();
        try {
            fileInputStream = new FileInputStream(file);
            try {
                BufferedReader bufferedReader3 = new BufferedReader(new InputStreamReader(fileInputStream));
                while (true) {
                    try {
                        String readLine = bufferedReader3.readLine();
                        if (readLine == null) {
                            break;
                        }
                        stringBuilder.append(readLine).append("\n");
                    } catch (FileNotFoundException e2) {
                        bufferedReader = bufferedReader3;
                        fileInputStream2 = fileInputStream;
                    } catch (IOException e3) {
                        e = e3;
                        bufferedReader2 = bufferedReader3;
                    } catch (Throwable th) {
                        e = th;
                        bufferedReader2 = bufferedReader3;
                    }
                }
                if (bufferedReader3 != null) {
                    try {
                        bufferedReader3.close();
                    } catch (Exception e4) {
                    }
                }
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (Exception e5) {
                    }
                }
                try {
                    m1611a(stringBuilder.toString());
                    return true;
                } catch (Throwable e6) {
                    C0835d.m1661b(e6, "ModelSpecificDistanceCalculator", "Cannot update distance models from online database at %s with JSON: %s", this.f1624f, stringBuilder.toString());
                    return false;
                }
            } catch (FileNotFoundException e7) {
                bufferedReader = null;
                fileInputStream2 = fileInputStream;
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (Exception e8) {
                    }
                }
                if (fileInputStream2 != null) {
                    try {
                        fileInputStream2.close();
                    } catch (Exception e9) {
                    }
                }
                return false;
            } catch (IOException e10) {
                e = e10;
                try {
                    C0835d.m1661b(e, "ModelSpecificDistanceCalculator", "Cannot open distance model file %s", file);
                    if (bufferedReader2 != null) {
                        try {
                            bufferedReader2.close();
                        } catch (Exception e11) {
                        }
                    }
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Exception e12) {
                        }
                    }
                    return false;
                } catch (Throwable th2) {
                    e = th2;
                    if (bufferedReader2 != null) {
                        try {
                            bufferedReader2.close();
                        } catch (Exception e13) {
                        }
                    }
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Exception e14) {
                        }
                    }
                    throw e;
                }
            }
        } catch (FileNotFoundException e15) {
            bufferedReader = null;
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (fileInputStream2 != null) {
                fileInputStream2.close();
            }
            return false;
        } catch (IOException e16) {
            e = e16;
            fileInputStream = null;
            C0835d.m1661b(e, "ModelSpecificDistanceCalculator", "Cannot open distance model file %s", file);
            if (bufferedReader2 != null) {
                bufferedReader2.close();
            }
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            return false;
        } catch (Throwable th3) {
            e = th3;
            fileInputStream = null;
            if (bufferedReader2 != null) {
                bufferedReader2.close();
            }
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            throw e;
        }
    }

    private boolean m1605b(String str) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = this.f1625g.openFileOutput("model-distance-calculations.json", 0);
            fileOutputStream.write(str.getBytes());
            fileOutputStream.close();
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (Exception e) {
                }
            }
            C0835d.m1660b("ModelSpecificDistanceCalculator", "Successfully saved new distance model file", new Object[0]);
            return true;
        } catch (Throwable e2) {
            C0835d.m1658a(e2, "ModelSpecificDistanceCalculator", "Cannot write updated distance model to local storage", new Object[0]);
            if (fileOutputStream == null) {
                return false;
            }
            try {
                fileOutputStream.close();
                return false;
            } catch (Exception e3) {
                return false;
            }
        } catch (Throwable th) {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (Exception e4) {
                }
            }
        }
    }

    @TargetApi(11)
    private void m1607c() {
        if (this.f1625g.checkCallingOrSelfPermission("android.permission.INTERNET") != 0) {
            C0835d.m1662c("ModelSpecificDistanceCalculator", "App has no android.permission.INTERNET permission.  Cannot check for distance model updates", new Object[0]);
        } else {
            new C0828f(this.f1625g, this.f1624f, new C08261(this)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        }
    }

    private void m1608c(String str) {
        this.f1619a = new HashMap();
        JSONArray jSONArray = new JSONObject(str).getJSONArray("models");
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            boolean z = jSONObject.has("default") ? jSONObject.getBoolean("default") : false;
            Double valueOf = Double.valueOf(jSONObject.getDouble("coefficient1"));
            Double valueOf2 = Double.valueOf(jSONObject.getDouble("coefficient2"));
            Double valueOf3 = Double.valueOf(jSONObject.getDouble("coefficient3"));
            String string = jSONObject.getString("version");
            String string2 = jSONObject.getString("build_number");
            String string3 = jSONObject.getString("model");
            String string4 = jSONObject.getString("manufacturer");
            C0823b c0823b = new C0823b(valueOf.doubleValue(), valueOf2.doubleValue(), valueOf3.doubleValue());
            C0821a c0821a = new C0821a(string, string2, string3, string4);
            this.f1619a.put(c0821a, c0823b);
            if (z) {
                this.f1620b = c0821a;
            }
        }
    }

    public double mo1872a(int i, double d) {
        if (this.f1621c != null) {
            return this.f1621c.mo1872a(i, d);
        }
        C0835d.m1662c("ModelSpecificDistanceCalculator", "distance calculator has not been set", new Object[0]);
        return -1.0d;
    }

    C0822c m1610a(C0821a c0821a) {
        this.f1626h.lock();
        try {
            C0822c b = m1602b(c0821a);
            return b;
        } finally {
            this.f1626h.unlock();
        }
    }

    void m1611a(String str) {
        this.f1626h.lock();
        try {
            m1608c(str);
        } finally {
            this.f1626h.unlock();
        }
    }
}
