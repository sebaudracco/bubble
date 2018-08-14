package com.facebook.ads.internal.p063f;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.facebook.ads.internal.p056q.p057a.C2122m;
import com.facebook.ads.internal.p056q.p057a.C2124p;
import com.facebook.ads.internal.p056q.p057a.C2129r;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class C1981e {
    private static final String f4626a = C1981e.class.getName();
    private static final Object f4627b = new Object();
    private static final Set<String> f4628c = Collections.synchronizedSet(new HashSet());
    private static final Map<String, Integer> f4629d = Collections.synchronizedMap(new HashMap());

    public static C1977d m6245a(Exception exception, Context context, Map<String, String> map) {
        try {
            C1977d c1977d = new C1977d(C2122m.m6805b(), C2122m.m6806c(), new C1979b(C2124p.m6810a((Throwable) exception), map, true).m6243a());
            try {
                C1981e.m6249a(c1977d, context);
                return c1977d;
            } catch (Exception e) {
                return c1977d;
            }
        } catch (Exception e2) {
            return null;
        }
    }

    @WorkerThread
    public static JSONArray m6246a(Context context) {
        return C1981e.m6247a(context, -1);
    }

    @WorkerThread
    public static JSONArray m6247a(Context context, int i) {
        FileInputStream openFileInput;
        InputStreamReader inputStreamReader;
        BufferedReader bufferedReader;
        Throwable e;
        FileInputStream fileInputStream;
        InputStreamReader inputStreamReader2 = null;
        JSONArray jSONArray = new JSONArray();
        synchronized (f4627b) {
            try {
                if (new File(context.getFilesDir(), "debuglogs").exists()) {
                    openFileInput = context.openFileInput("debuglogs");
                    try {
                        inputStreamReader = new InputStreamReader(openFileInput);
                        try {
                            bufferedReader = new BufferedReader(inputStreamReader);
                            int i2 = i;
                            while (true) {
                                try {
                                    String readLine = bufferedReader.readLine();
                                    if (readLine == null || i2 == 0) {
                                        break;
                                    }
                                    JSONObject jSONObject = new JSONObject(readLine);
                                    if (!jSONObject.has("attempt")) {
                                        jSONObject.put("attempt", 0);
                                    }
                                    readLine = jSONObject.getString("id");
                                    if (!f4628c.contains(readLine)) {
                                        int i3 = jSONObject.getInt("attempt");
                                        if (f4629d.containsKey(readLine)) {
                                            jSONObject.put("attempt", f4629d.get(readLine));
                                        } else {
                                            C1981e.m6251a(readLine, i3);
                                        }
                                        jSONArray.put(jSONObject);
                                        if (i2 > 0) {
                                            i2--;
                                        }
                                    }
                                } catch (IOException e2) {
                                    e = e2;
                                    inputStreamReader2 = inputStreamReader;
                                    fileInputStream = openFileInput;
                                } catch (JSONException e3) {
                                    e = e3;
                                }
                            }
                        } catch (IOException e4) {
                            e = e4;
                            bufferedReader = null;
                            inputStreamReader2 = inputStreamReader;
                            fileInputStream = openFileInput;
                            openFileInput = fileInputStream;
                            inputStreamReader = inputStreamReader2;
                            try {
                                Log.e(f4626a, "Failed to read crashes", e);
                                if (bufferedReader != null) {
                                    try {
                                        bufferedReader.close();
                                    } catch (Throwable e5) {
                                        Log.e(f4626a, "Failed to close buffers", e5);
                                    }
                                }
                                if (inputStreamReader != null) {
                                    inputStreamReader.close();
                                }
                                if (openFileInput != null) {
                                    openFileInput.close();
                                }
                                return jSONArray;
                            } catch (Throwable th) {
                                e5 = th;
                                if (bufferedReader != null) {
                                    try {
                                        bufferedReader.close();
                                    } catch (Throwable e6) {
                                        Log.e(f4626a, "Failed to close buffers", e6);
                                        throw e5;
                                    }
                                }
                                if (inputStreamReader != null) {
                                    inputStreamReader.close();
                                }
                                if (openFileInput != null) {
                                    openFileInput.close();
                                }
                                throw e5;
                            }
                        } catch (JSONException e7) {
                            e5 = e7;
                            bufferedReader = null;
                            Log.e(f4626a, "Failed to read crashes", e5);
                            if (bufferedReader != null) {
                                bufferedReader.close();
                            }
                            if (inputStreamReader != null) {
                                inputStreamReader.close();
                            }
                            if (openFileInput != null) {
                                openFileInput.close();
                            }
                            return jSONArray;
                        } catch (Throwable th2) {
                            e5 = th2;
                            bufferedReader = null;
                            if (bufferedReader != null) {
                                bufferedReader.close();
                            }
                            if (inputStreamReader != null) {
                                inputStreamReader.close();
                            }
                            if (openFileInput != null) {
                                openFileInput.close();
                            }
                            throw e5;
                        }
                    } catch (IOException e8) {
                        e5 = e8;
                        bufferedReader = null;
                        fileInputStream = openFileInput;
                        openFileInput = fileInputStream;
                        inputStreamReader = inputStreamReader2;
                        Log.e(f4626a, "Failed to read crashes", e5);
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                        if (inputStreamReader != null) {
                            inputStreamReader.close();
                        }
                        if (openFileInput != null) {
                            openFileInput.close();
                        }
                        return jSONArray;
                    } catch (JSONException e9) {
                        e5 = e9;
                        bufferedReader = null;
                        inputStreamReader = null;
                        Log.e(f4626a, "Failed to read crashes", e5);
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                        if (inputStreamReader != null) {
                            inputStreamReader.close();
                        }
                        if (openFileInput != null) {
                            openFileInput.close();
                        }
                        return jSONArray;
                    } catch (Throwable th3) {
                        e5 = th3;
                        bufferedReader = null;
                        inputStreamReader = null;
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                        if (inputStreamReader != null) {
                            inputStreamReader.close();
                        }
                        if (openFileInput != null) {
                            openFileInput.close();
                        }
                        throw e5;
                    }
                }
                bufferedReader = null;
                inputStreamReader = null;
                openFileInput = null;
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (Throwable e52) {
                        Log.e(f4626a, "Failed to close buffers", e52);
                    }
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (openFileInput != null) {
                    openFileInput.close();
                }
            } catch (IOException e10) {
                e52 = e10;
                bufferedReader = null;
                fileInputStream = null;
                openFileInput = fileInputStream;
                inputStreamReader = inputStreamReader2;
                Log.e(f4626a, "Failed to read crashes", e52);
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (openFileInput != null) {
                    openFileInput.close();
                }
                return jSONArray;
            } catch (JSONException e11) {
                e52 = e11;
                bufferedReader = null;
                inputStreamReader = null;
                openFileInput = null;
                Log.e(f4626a, "Failed to read crashes", e52);
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (openFileInput != null) {
                    openFileInput.close();
                }
                return jSONArray;
            } catch (Throwable th4) {
                e52 = th4;
                bufferedReader = null;
                inputStreamReader = null;
                openFileInput = null;
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (openFileInput != null) {
                    openFileInput.close();
                }
                throw e52;
            }
        }
        return jSONArray;
    }

    private static JSONObject m6248a(C1977d c1977d) {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("id", UUID.randomUUID().toString());
        jSONObject.put("type", c1977d.mo3705a());
        jSONObject.put("time", C2129r.m6818a(c1977d.m6237b()));
        jSONObject.put("session_time", C2129r.m6818a(c1977d.m6238c()));
        jSONObject.put("session_id", c1977d.m6239d());
        jSONObject.put("data", c1977d.m6240e() != null ? new JSONObject(c1977d.m6240e()) : new JSONObject());
        jSONObject.put("attempt", 0);
        return jSONObject;
    }

    public static void m6249a(C1977d c1977d, Context context) {
        if (c1977d != null && context != null) {
            synchronized (f4627b) {
                try {
                    JSONObject a = C1981e.m6248a(c1977d);
                    FileOutputStream openFileOutput = context.openFileOutput("debuglogs", 32768);
                    openFileOutput.write((a.toString() + "\n").getBytes());
                    openFileOutput.close();
                    C1981e.m6257d(context);
                } catch (Throwable e) {
                    Log.e(f4626a, "Failed to store crash", e);
                }
            }
        }
    }

    public static void m6250a(String str) {
        Integer num = (Integer) f4629d.get(str);
        if (num == null) {
            num = Integer.valueOf(0);
        } else {
            f4629d.remove(str);
        }
        f4629d.put(str, Integer.valueOf(num.intValue() + 1));
    }

    private static void m6251a(String str, int i) {
        if (f4628c.contains(str)) {
            throw new RuntimeException("finished event should not be updated to OngoingEvent.");
        }
        if (f4629d.containsKey(str)) {
            f4629d.remove(str);
        }
        f4629d.put(str, Integer.valueOf(i));
    }

    public static int m6252b(Context context) {
        return context.getApplicationContext().getSharedPreferences("DEBUG_PREF", 0).getInt("EventCount", 0) - f4628c.size();
    }

    private static void m6253b(Context context, int i) {
        Editor edit = context.getApplicationContext().getSharedPreferences("DEBUG_PREF", 0).edit();
        String str = "EventCount";
        if (i < 0) {
            i = 0;
        }
        edit.putInt(str, i).apply();
    }

    public static void m6254b(String str) {
        if (f4629d.containsKey(str)) {
            f4629d.remove(str);
        }
        f4628c.add(str);
    }

    @WorkerThread
    public static boolean m6255c(Context context) {
        FileInputStream openFileInput;
        BufferedReader bufferedReader;
        Throwable e;
        Object obj;
        Throwable th;
        Object obj2;
        boolean z = false;
        FileOutputStream fileOutputStream = null;
        JSONArray jSONArray = new JSONArray();
        synchronized (f4627b) {
            InputStreamReader inputStreamReader;
            Object obj3;
            try {
                if (new File(context.getFilesDir(), "debuglogs").exists()) {
                    openFileInput = context.openFileInput("debuglogs");
                    try {
                        inputStreamReader = new InputStreamReader(openFileInput);
                        try {
                            bufferedReader = new BufferedReader(inputStreamReader);
                            while (true) {
                                try {
                                    String readLine = bufferedReader.readLine();
                                    if (readLine == null) {
                                        break;
                                    }
                                    JSONObject jSONObject = new JSONObject(readLine);
                                    readLine = jSONObject.getString("id");
                                    if (!f4628c.contains(readLine)) {
                                        if (f4629d.containsKey(readLine)) {
                                            jSONObject.put("attempt", f4629d.get(readLine));
                                        }
                                        jSONArray.put(jSONObject);
                                    }
                                } catch (IOException e2) {
                                    e = e2;
                                } catch (JSONException e3) {
                                    e = e3;
                                }
                            }
                            StringBuilder stringBuilder = new StringBuilder();
                            int length = jSONArray.length();
                            for (int i = 0; i < length; i++) {
                                stringBuilder.append(jSONArray.getJSONObject(i).toString()).append('\n');
                            }
                            fileOutputStream = context.openFileOutput("debuglogs", 0);
                            fileOutputStream.write(stringBuilder.toString().getBytes());
                        } catch (IOException e4) {
                            e = e4;
                            obj = fileOutputStream;
                            try {
                                Log.e(f4626a, "Failed to rewrite File.", e);
                                if (bufferedReader != null) {
                                    try {
                                        bufferedReader.close();
                                    } catch (Throwable e5) {
                                        Log.e(f4626a, "Failed to close buffers", e5);
                                        f4628c.clear();
                                        f4629d.clear();
                                        return z;
                                    }
                                }
                                if (inputStreamReader != null) {
                                    inputStreamReader.close();
                                }
                                if (openFileInput != null) {
                                    openFileInput.close();
                                }
                                if (fileOutputStream != null) {
                                    fileOutputStream.close();
                                }
                                f4628c.clear();
                                f4629d.clear();
                                return z;
                            } catch (Throwable th2) {
                                th = th2;
                                if (bufferedReader != null) {
                                    try {
                                        bufferedReader.close();
                                    } catch (Throwable e52) {
                                        Log.e(f4626a, "Failed to close buffers", e52);
                                        f4628c.clear();
                                        f4629d.clear();
                                        throw th;
                                    }
                                }
                                if (inputStreamReader != null) {
                                    inputStreamReader.close();
                                }
                                if (openFileInput != null) {
                                    openFileInput.close();
                                }
                                if (fileOutputStream != null) {
                                    fileOutputStream.close();
                                }
                                f4628c.clear();
                                f4629d.clear();
                                throw th;
                            }
                        } catch (JSONException e6) {
                            e52 = e6;
                            obj = fileOutputStream;
                            Log.e(f4626a, "Failed to rewrite File.", e52);
                            if (bufferedReader != null) {
                                bufferedReader.close();
                            }
                            if (inputStreamReader != null) {
                                inputStreamReader.close();
                            }
                            if (openFileInput != null) {
                                openFileInput.close();
                            }
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                            f4628c.clear();
                            f4629d.clear();
                            return z;
                        } catch (Throwable th3) {
                            th = th3;
                            obj = fileOutputStream;
                            if (bufferedReader != null) {
                                bufferedReader.close();
                            }
                            if (inputStreamReader != null) {
                                inputStreamReader.close();
                            }
                            if (openFileInput != null) {
                                openFileInput.close();
                            }
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                            f4628c.clear();
                            f4629d.clear();
                            throw th;
                        }
                    } catch (IOException e7) {
                        e52 = e7;
                        obj = fileOutputStream;
                        obj2 = fileOutputStream;
                        Log.e(f4626a, "Failed to rewrite File.", e52);
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                        if (inputStreamReader != null) {
                            inputStreamReader.close();
                        }
                        if (openFileInput != null) {
                            openFileInput.close();
                        }
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        f4628c.clear();
                        f4629d.clear();
                        return z;
                    } catch (JSONException e8) {
                        e52 = e8;
                        obj = fileOutputStream;
                        obj2 = fileOutputStream;
                        Log.e(f4626a, "Failed to rewrite File.", e52);
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                        if (inputStreamReader != null) {
                            inputStreamReader.close();
                        }
                        if (openFileInput != null) {
                            openFileInput.close();
                        }
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        f4628c.clear();
                        f4629d.clear();
                        return z;
                    } catch (Throwable th4) {
                        th = th4;
                        obj = fileOutputStream;
                        obj2 = fileOutputStream;
                        if (bufferedReader != null) {
                            bufferedReader.close();
                        }
                        if (inputStreamReader != null) {
                            inputStreamReader.close();
                        }
                        if (openFileInput != null) {
                            openFileInput.close();
                        }
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        f4628c.clear();
                        f4629d.clear();
                        throw th;
                    }
                }
                obj = fileOutputStream;
                obj2 = fileOutputStream;
                obj3 = fileOutputStream;
                C1981e.m6253b(context, C1981e.m6252b(context));
                z = true;
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (Throwable e522) {
                        Log.e(f4626a, "Failed to close buffers", e522);
                    }
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (openFileInput != null) {
                    openFileInput.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                f4628c.clear();
                f4629d.clear();
            } catch (IOException e9) {
                e522 = e9;
                obj = fileOutputStream;
                obj2 = fileOutputStream;
                obj3 = fileOutputStream;
                Log.e(f4626a, "Failed to rewrite File.", e522);
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (openFileInput != null) {
                    openFileInput.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                f4628c.clear();
                f4629d.clear();
                return z;
            } catch (JSONException e10) {
                e522 = e10;
                obj = fileOutputStream;
                obj2 = fileOutputStream;
                obj3 = fileOutputStream;
                Log.e(f4626a, "Failed to rewrite File.", e522);
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (openFileInput != null) {
                    openFileInput.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                f4628c.clear();
                f4629d.clear();
                return z;
            } catch (Throwable th5) {
                th = th5;
                bufferedReader = fileOutputStream;
                inputStreamReader = fileOutputStream;
                openFileInput = fileOutputStream;
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
                if (openFileInput != null) {
                    openFileInput.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                f4628c.clear();
                f4629d.clear();
                throw th;
            }
        }
        return z;
    }

    public static boolean m6256c(String str) {
        return f4628c.contains(str) || f4629d.containsKey(str);
    }

    private static void m6257d(Context context) {
        C1981e.m6253b(context, context.getApplicationContext().getSharedPreferences("DEBUG_PREF", 0).getInt("EventCount", 0) + 1);
    }
}
