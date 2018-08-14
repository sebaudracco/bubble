package com.yandex.metrica.impl.ob;

import android.text.TextUtils;
import android.util.Log;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class en implements ev {
    private static final String f12335a = en.class.getSimpleName();
    private final es f12336b = new es();
    private File f12337c;

    public en(String str, String str2) throws IOException {
        m15928b(str, str2);
    }

    public synchronized Set<String> mo7095a(String str) {
        return this.f12336b.mo7095a(str);
    }

    public synchronized void mo7097a(String str, String[] strArr) {
        if (this.f12336b.mo7095a(str) == null) {
            long lastModified = this.f12337c.lastModified();
            mo7096a(str, new HashSet(Arrays.asList(strArr)));
            this.f12337c.setLastModified(lastModified);
        }
    }

    public synchronized boolean mo7098a(String str, String str2) {
        boolean a;
        a = this.f12336b.mo7098a(str, str2);
        m15930d();
        return a;
    }

    public synchronized void mo7096a(String str, Set<String> set) {
        this.f12336b.mo7096a(str, (Set) set);
        m15930d();
    }

    public synchronized long mo7094a() {
        return this.f12337c.lastModified();
    }

    public void mo7099b() {
        this.f12337c.setLastModified(System.currentTimeMillis());
    }

    private synchronized void m15928b(String str, String str2) throws IOException {
        Map hashMap;
        this.f12337c = new File(str, "sslpinningv1-" + str2);
        if (this.f12337c.createNewFile()) {
            hashMap = new HashMap();
            m15927a(hashMap);
            this.f12337c.setLastModified(0);
        } else {
            hashMap = m15929c();
        }
        this.f12336b.m15952a(hashMap);
    }

    private synchronized Map<String, Set<String>> m15929c() throws IOException {
        Map<String, Set<String>> hashMap;
        BufferedReader bufferedReader;
        Throwable th;
        Set set = null;
        synchronized (this) {
            hashMap = new HashMap();
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(this.f12337c)));
                try {
                    for (CharSequence readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                        if (readLine.contains("type-")) {
                            String substring = readLine.substring(5);
                            set = new HashSet();
                            hashMap.put(substring, set);
                        } else if (!TextUtils.isEmpty(readLine)) {
                            set.add(readLine);
                        }
                    }
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        Log.e(f12335a, e.getMessage());
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e2) {
                            Log.e(f12335a, e2.getMessage());
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
        }
        return hashMap;
    }

    private synchronized void m15927a(Map<String, Set<String>> map) {
        IOException e;
        Throwable th;
        BufferedWriter bufferedWriter;
        try {
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.f12337c)));
            try {
                for (String str : map.keySet()) {
                    bufferedWriter.write("type-" + str);
                    bufferedWriter.newLine();
                    for (String str2 : (Set) map.get(str2)) {
                        bufferedWriter.write(str2);
                        bufferedWriter.newLine();
                    }
                }
                try {
                    bufferedWriter.close();
                } catch (IOException e2) {
                    Log.e(f12335a, e2.getMessage());
                }
            } catch (IOException e3) {
                e2 = e3;
                try {
                    Log.e(f12335a, e2.getMessage());
                    if (bufferedWriter != null) {
                        try {
                            bufferedWriter.close();
                        } catch (IOException e22) {
                            Log.e(f12335a, e22.getMessage());
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (bufferedWriter != null) {
                        try {
                            bufferedWriter.close();
                        } catch (IOException e4) {
                            Log.e(f12335a, e4.getMessage());
                        }
                    }
                    throw th;
                }
            }
        } catch (IOException e5) {
            e22 = e5;
            bufferedWriter = null;
            Log.e(f12335a, e22.getMessage());
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
        } catch (Throwable th3) {
            th = th3;
            bufferedWriter = null;
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            throw th;
        }
    }

    private synchronized void m15930d() {
        m15927a(this.f12336b.m15955c());
    }
}
