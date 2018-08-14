package com.adcolony.sdk;

import com.adcolony.sdk.aa.C0595a;
import com.mopub.mobileads.VastIconXmlManager;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.GZIPInputStream;
import mf.javax.xml.transform.OutputKeys;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class C0789t {
    private LinkedList<Runnable> f1451a = new LinkedList();
    private boolean f1452b;

    class C07721 implements ah {
        final /* synthetic */ C0789t f1426a;

        C07721(C0789t c0789t) {
            this.f1426a = c0789t;
        }

        public void mo1863a(final af afVar) {
            this.f1426a.m1403a(new Runnable(this) {
                final /* synthetic */ C07721 f1425b;

                public void run() {
                    this.f1425b.f1426a.m1405a(afVar);
                    this.f1425b.f1426a.m1410b();
                }
            });
        }
    }

    class C07742 implements ah {
        final /* synthetic */ C0789t f1429a;

        C07742(C0789t c0789t) {
            this.f1429a = c0789t;
        }

        public void mo1863a(final af afVar) {
            this.f1429a.m1403a(new Runnable(this) {
                final /* synthetic */ C07742 f1428b;

                public void run() {
                    this.f1428b.f1429a.m1406a(afVar, new File(C0802y.m1468b(afVar.m698c(), "filepath")));
                    this.f1428b.f1429a.m1410b();
                }
            });
        }
    }

    class C07763 implements ah {
        final /* synthetic */ C0789t f1432a;

        C07763(C0789t c0789t) {
            this.f1432a = c0789t;
        }

        public void mo1863a(final af afVar) {
            this.f1432a.m1403a(new Runnable(this) {
                final /* synthetic */ C07763 f1431b;

                public void run() {
                    this.f1431b.f1432a.m1411b(afVar);
                    this.f1431b.f1432a.m1410b();
                }
            });
        }
    }

    class C07784 implements ah {
        final /* synthetic */ C0789t f1435a;

        C07784(C0789t c0789t) {
            this.f1435a = c0789t;
        }

        public void mo1863a(final af afVar) {
            this.f1435a.m1403a(new Runnable(this) {
                final /* synthetic */ C07784 f1434b;

                public void run() {
                    this.f1434b.f1435a.m1412c(afVar);
                    this.f1434b.f1435a.m1410b();
                }
            });
        }
    }

    class C07805 implements ah {
        final /* synthetic */ C0789t f1438a;

        C07805(C0789t c0789t) {
            this.f1438a = c0789t;
        }

        public void mo1863a(final af afVar) {
            this.f1438a.m1403a(new Runnable(this) {
                final /* synthetic */ C07805 f1437b;

                public void run() {
                    this.f1437b.f1438a.m1413d(afVar);
                    this.f1437b.f1438a.m1410b();
                }
            });
        }
    }

    class C07826 implements ah {
        final /* synthetic */ C0789t f1441a;

        C07826(C0789t c0789t) {
            this.f1441a = c0789t;
        }

        public void mo1863a(final af afVar) {
            this.f1441a.m1403a(new Runnable(this) {
                final /* synthetic */ C07826 f1440b;

                public void run() {
                    this.f1440b.f1441a.m1414e(afVar);
                    this.f1440b.f1441a.m1410b();
                }
            });
        }
    }

    class C07847 implements ah {
        final /* synthetic */ C0789t f1444a;

        C07847(C0789t c0789t) {
            this.f1444a = c0789t;
        }

        public void mo1863a(final af afVar) {
            this.f1444a.m1403a(new Runnable(this) {
                final /* synthetic */ C07847 f1443b;

                public void run() {
                    this.f1443b.f1444a.m1398f(afVar);
                    this.f1443b.f1444a.m1410b();
                }
            });
        }
    }

    class C07868 implements ah {
        final /* synthetic */ C0789t f1447a;

        C07868(C0789t c0789t) {
            this.f1447a = c0789t;
        }

        public void mo1863a(final af afVar) {
            this.f1447a.m1403a(new Runnable(this) {
                final /* synthetic */ C07868 f1446b;

                public void run() {
                    this.f1446b.f1447a.m1399g(afVar);
                    this.f1446b.f1447a.m1410b();
                }
            });
        }
    }

    class C07889 implements ah {
        final /* synthetic */ C0789t f1450a;

        C07889(C0789t c0789t) {
            this.f1450a = c0789t;
        }

        public void mo1863a(final af afVar) {
            this.f1450a.m1403a(new Runnable(this) {
                final /* synthetic */ C07889 f1449b;

                public void run() {
                    this.f1449b.f1450a.m1400h(afVar);
                    this.f1449b.f1450a.m1410b();
                }
            });
        }
    }

    C0789t() {
    }

    private boolean m1398f(af afVar) {
        JSONObject c = afVar.m698c();
        String b = C0802y.m1468b(c, "filepath");
        C0594a.m605a().m1285o().m782b();
        JSONObject a = C0802y.m1453a();
        try {
            int c2 = C0802y.m1473c(c, VastIconXmlManager.OFFSET);
            int c3 = C0802y.m1473c(c, "size");
            boolean d = C0802y.m1477d(c, "gunzip");
            String b2 = C0802y.m1468b(c, "output_filepath");
            InputStream asVar = new as(new FileInputStream(b), c2, c3);
            if (d) {
                asVar = new GZIPInputStream(asVar, 1024);
            }
            if (b2.equals("")) {
                StringBuilder stringBuilder = new StringBuilder(asVar.available());
                byte[] bArr = new byte[1024];
                while (true) {
                    c2 = asVar.read(bArr, 0, 1024);
                    if (c2 < 0) {
                        break;
                    }
                    stringBuilder.append(new String(bArr, 0, c2, "ISO-8859-1"));
                }
                C0802y.m1472b(a, "size", stringBuilder.length());
                C0802y.m1462a(a, "data", stringBuilder.toString());
            } else {
                FileOutputStream fileOutputStream = new FileOutputStream(b2);
                byte[] bArr2 = new byte[1024];
                int i = 0;
                while (true) {
                    c3 = asVar.read(bArr2, 0, 1024);
                    if (c3 < 0) {
                        break;
                    }
                    fileOutputStream.write(bArr2, 0, c3);
                    i += c3;
                }
                fileOutputStream.close();
                C0802y.m1472b(a, "size", i);
            }
            asVar.close();
            C0802y.m1465a(a, "success", true);
            afVar.m694a(a).m695b();
            return true;
        } catch (IOException e) {
            C0802y.m1465a(a, "success", false);
            afVar.m694a(a).m695b();
            return false;
        } catch (OutOfMemoryError e2) {
            new C0595a().m622a("Out of memory error - disabling AdColony.").m624a(aa.f483g);
            C0594a.m605a().m1259a(true);
            C0802y.m1465a(a, "success", false);
            afVar.m694a(a).m695b();
            return false;
        }
    }

    private boolean m1399g(af afVar) {
        JSONObject c = afVar.m698c();
        String b = C0802y.m1468b(c, "filepath");
        String b2 = C0802y.m1468b(c, "bundle_path");
        JSONArray g = C0802y.m1481g(c, "bundle_filenames");
        C0594a.m605a().m1285o().m782b();
        JSONObject a = C0802y.m1453a();
        try {
            File file = new File(b2);
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r");
            byte[] bArr = new byte[4];
            byte[] bArr2 = new byte[32];
            randomAccessFile.readInt();
            int readInt = randomAccessFile.readInt();
            JSONArray jSONArray = new JSONArray();
            byte[] bArr3 = new byte[1024];
            int i = 0;
            while (i < readInt) {
                randomAccessFile.seek((long) ((i * 44) + 8));
                randomAccessFile.read(bArr2);
                String str = new String(bArr2);
                randomAccessFile.readInt();
                int readInt2 = randomAccessFile.readInt();
                int readInt3 = randomAccessFile.readInt();
                jSONArray.put(readInt3);
                String str2 = "";
                try {
                    str2 = b + g.get(i);
                    randomAccessFile.seek((long) readInt2);
                    FileOutputStream fileOutputStream = new FileOutputStream(str2);
                    int i2 = readInt3 / 1024;
                    readInt3 %= 1024;
                    for (readInt2 = 0; readInt2 < i2; readInt2++) {
                        randomAccessFile.read(bArr3, 0, 1024);
                        fileOutputStream.write(bArr3, 0, 1024);
                    }
                    randomAccessFile.read(bArr3, 0, readInt3);
                    fileOutputStream.write(bArr3, 0, readInt3);
                    fileOutputStream.close();
                    i++;
                } catch (JSONException e) {
                    new C0595a().m622a("Could extract file name at index ").m620a(i).m622a(" unpacking ad unit bundle at ").m622a(b2).m624a(aa.f483g);
                    C0802y.m1465a(a, "success", false);
                    afVar.m694a(a).m695b();
                    return false;
                }
            }
            randomAccessFile.close();
            file.delete();
            C0802y.m1465a(a, "success", true);
            C0802y.m1463a(a, "file_sizes", jSONArray);
            afVar.m694a(a).m695b();
            return true;
        } catch (IOException e2) {
            new C0595a().m622a("Failed to find or open ad unit bundle at path: ").m622a(b2).m624a(aa.f484h);
            C0802y.m1465a(a, "success", false);
            afVar.m694a(a).m695b();
            return false;
        } catch (OutOfMemoryError e3) {
            new C0595a().m622a("Out of memory error - disabling AdColony.").m624a(aa.f483g);
            C0594a.m605a().m1259a(true);
            C0802y.m1465a(a, "success", false);
            afVar.m694a(a).m695b();
            return false;
        }
    }

    void m1402a() {
        C0594a.m609a("FileSystem.save", new C07721(this));
        C0594a.m609a("FileSystem.delete", new C07742(this));
        C0594a.m609a("FileSystem.listing", new C07763(this));
        C0594a.m609a("FileSystem.load", new C07784(this));
        C0594a.m609a("FileSystem.rename", new C07805(this));
        C0594a.m609a("FileSystem.exists", new C07826(this));
        C0594a.m609a("FileSystem.extract", new C07847(this));
        C0594a.m609a("FileSystem.unpack_bundle", new C07868(this));
        C0594a.m609a("FileSystem.create_directory", new C07889(this));
    }

    boolean m1405a(af afVar) {
        JSONObject c = afVar.m698c();
        String b = C0802y.m1468b(c, "filepath");
        String b2 = C0802y.m1468b(c, "data");
        String b3 = C0802y.m1468b(c, OutputKeys.ENCODING);
        boolean z = b3 != null && b3.equals("utf8");
        C0594a.m605a().m1285o().m782b();
        JSONObject a = C0802y.m1453a();
        try {
            m1404a(b, b2, z);
            C0802y.m1465a(a, "success", true);
            afVar.m694a(a).m695b();
            return true;
        } catch (IOException e) {
            C0802y.m1465a(a, "success", false);
            afVar.m694a(a).m695b();
            return false;
        }
    }

    void m1404a(String str, String str2, boolean z) throws IOException {
        BufferedWriter bufferedWriter = z ? new BufferedWriter(new OutputStreamWriter(new FileOutputStream(str), "UTF-8")) : new BufferedWriter(new OutputStreamWriter(new FileOutputStream(str)));
        bufferedWriter.write(str2);
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    boolean m1406a(af afVar, File file) {
        C0594a.m605a().m1285o().m782b();
        JSONObject a = C0802y.m1453a();
        if (m1407a(file)) {
            C0802y.m1465a(a, "success", true);
            afVar.m694a(a).m695b();
            return true;
        }
        C0802y.m1465a(a, "success", false);
        afVar.m694a(a).m695b();
        return false;
    }

    boolean m1407a(File file) {
        try {
            if (!file.isDirectory()) {
                return file.delete();
            }
            if (file.list().length == 0) {
                return file.delete();
            }
            String[] list = file.list();
            if (0 < list.length) {
                return m1407a(new File(file, list[0]));
            }
            if (file.list().length == 0) {
                return file.delete();
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    boolean m1411b(af afVar) {
        String b = C0802y.m1468b(afVar.m698c(), "filepath");
        C0594a.m605a().m1285o().m782b();
        JSONObject a = C0802y.m1453a();
        String[] list = new File(b).list();
        if (list != null) {
            JSONArray b2 = C0802y.m1469b();
            for (String str : list) {
                JSONObject a2 = C0802y.m1453a();
                C0802y.m1462a(a2, "filename", str);
                if (new File(b + str).isDirectory()) {
                    C0802y.m1465a(a2, "is_folder", true);
                } else {
                    C0802y.m1465a(a2, "is_folder", false);
                }
                C0802y.m1457a(b2, (Object) a2);
            }
            C0802y.m1465a(a, "success", true);
            C0802y.m1463a(a, "entries", b2);
            afVar.m694a(a).m695b();
            return true;
        }
        C0802y.m1465a(a, "success", false);
        afVar.m694a(a).m695b();
        return false;
    }

    String m1412c(af afVar) {
        boolean z = true;
        JSONObject c = afVar.m698c();
        String b = C0802y.m1468b(c, "filepath");
        String b2 = C0802y.m1468b(c, OutputKeys.ENCODING);
        if (b2 == null || !b2.equals("utf8")) {
            z = false;
        }
        C0594a.m605a().m1285o().m782b();
        c = C0802y.m1453a();
        try {
            StringBuilder a = m1401a(b, z);
            C0802y.m1465a(c, "success", true);
            C0802y.m1462a(c, "data", a.toString());
            afVar.m694a(c).m695b();
            return a.toString();
        } catch (IOException e) {
            C0802y.m1465a(c, "success", false);
            afVar.m694a(c).m695b();
            return "";
        }
    }

    StringBuilder m1401a(String str, boolean z) throws IOException {
        File file = new File(str);
        StringBuilder stringBuilder = new StringBuilder((int) file.length());
        BufferedReader bufferedReader = z ? new BufferedReader(new InputStreamReader(new FileInputStream(file.getAbsolutePath()), "UTF-8")) : new BufferedReader(new InputStreamReader(new FileInputStream(file.getAbsolutePath())));
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine != null) {
                stringBuilder.append(readLine);
                stringBuilder.append("\n");
            } else {
                bufferedReader.close();
                return stringBuilder;
            }
        }
    }

    List<String> m1409b(String str, boolean z) throws IOException {
        File file = new File(str);
        int length = (int) file.length();
        List<String> arrayList = new ArrayList();
        BufferedReader bufferedReader = z ? new BufferedReader(new InputStreamReader(new FileInputStream(file.getAbsolutePath()), "UTF-8")) : new BufferedReader(new InputStreamReader(new FileInputStream(file.getAbsolutePath())));
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine != null) {
                arrayList.add(readLine);
            } else {
                bufferedReader.close();
                return arrayList;
            }
        }
    }

    boolean m1413d(af afVar) {
        JSONObject c = afVar.m698c();
        String b = C0802y.m1468b(c, "filepath");
        String b2 = C0802y.m1468b(c, "new_filepath");
        C0594a.m605a().m1285o().m782b();
        JSONObject a = C0802y.m1453a();
        try {
            if (new File(b).renameTo(new File(b2))) {
                C0802y.m1465a(a, "success", true);
                afVar.m694a(a).m695b();
                return true;
            }
            C0802y.m1465a(a, "success", false);
            afVar.m694a(a).m695b();
            return false;
        } catch (Exception e) {
            C0802y.m1465a(a, "success", false);
            afVar.m694a(a).m695b();
            return false;
        }
    }

    boolean m1414e(af afVar) {
        String b = C0802y.m1468b(afVar.m698c(), "filepath");
        C0594a.m605a().m1285o().m782b();
        JSONObject a = C0802y.m1453a();
        try {
            boolean a2 = m1408a(b);
            C0802y.m1465a(a, "result", a2);
            C0802y.m1465a(a, "success", true);
            afVar.m694a(a).m695b();
            return a2;
        } catch (Exception e) {
            C0802y.m1465a(a, "result", false);
            C0802y.m1465a(a, "success", false);
            afVar.m694a(a).m695b();
            e.printStackTrace();
            return false;
        }
    }

    boolean m1408a(String str) throws Exception {
        return new File(str).exists();
    }

    private boolean m1400h(af afVar) {
        String b = C0802y.m1468b(afVar.m698c(), "filepath");
        C0594a.m605a().m1285o().m782b();
        JSONObject a = C0802y.m1453a();
        try {
            if (new File(b).mkdir()) {
                C0802y.m1465a(a, "success", true);
                afVar.m694a(a).m695b();
                return true;
            }
            C0802y.m1465a(a, "success", false);
            return false;
        } catch (Exception e) {
            C0802y.m1465a(a, "success", false);
            afVar.m694a(a).m695b();
            return false;
        }
    }

    void m1403a(Runnable runnable) {
        if (!this.f1451a.isEmpty() || this.f1452b) {
            this.f1451a.push(runnable);
            return;
        }
        this.f1452b = true;
        runnable.run();
    }

    void m1410b() {
        this.f1452b = false;
        if (!this.f1451a.isEmpty()) {
            this.f1452b = true;
            ((Runnable) this.f1451a.removeLast()).run();
        }
    }
}
