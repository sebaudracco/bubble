package com.appnext.base.operations.imp;

import android.os.Bundle;
import com.appnext.base.C1061b;
import com.appnext.base.operations.C1067e;
import com.appnext.base.p019a.C1017a;
import com.appnext.base.p019a.p021b.C1020b;
import com.appnext.base.p019a.p021b.C1021c;
import com.appnext.base.p023b.C1042c;
import com.appnext.base.p023b.C1043d;
import com.appnext.base.p023b.C1048i;
import com.appnext.base.p023b.C1058l;
import com.appnext.base.p023b.C1060n;
import com.appnext.core.C1128g;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import org.json.JSONArray;

public class sals extends C1067e {
    private static final String hi = (C1043d.getContext().getFilesDir().getAbsolutePath() + C1042c.jn);
    private static final String hj = (hi + C1042c.jl);
    private static final String hk = (hi + C1042c.jm);

    public sals(C1021c c1021c, Bundle bundle) {
        super(c1021c, bundle);
    }

    public boolean hasPermission() {
        return true;
    }

    protected boolean bz() {
        return false;
    }

    protected List<C1020b> getData() {
        try {
            if (C1128g.cW() >= 6) {
                File file = new File(hk);
                if (!file.exists()) {
                    if (ak(C1042c.jr)) {
                        File file2 = new File(hj);
                        if (file2.exists()) {
                            new C1060n(file2.toString(), new File(hi).toString()).cJ();
                            file2.delete();
                            C1058l.m2184k("sals", "Delete zip file");
                        }
                    }
                }
                C1017a.aM().aO().delete();
                InputStream fileInputStream = new FileInputStream(hk);
                C1058l.m2184k("sals", "Read from file");
                JSONArray jSONArray = new JSONArray(m2211a(fileInputStream));
                C1058l.m2184k("sals", "Finished read from file");
                C1017a.aM().aO().m2087a(jSONArray);
                C1048i.cy().putBoolean(C1048i.km, true);
                file.delete();
            }
        } catch (Throwable th) {
            C1058l.m2184k("sals", "sals Failed");
            C1061b.m2191a(th);
        }
        return null;
    }

    public static String m2211a(InputStream inputStream) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine != null) {
                stringBuilder.append(readLine);
            } else {
                bufferedReader.close();
                return stringBuilder.toString();
            }
        }
    }

    private static boolean ak(String str) {
        try {
            C1058l.m2184k("sals", "Start downloading");
            File file = new File(hj);
            if (file.exists()) {
                file.setLastModified(System.currentTimeMillis());
                return true;
            }
            new File(hi).mkdirs();
            byte[] a = C1128g.m2342a(str, null, false, 15000);
            if (a == null) {
                return false;
            }
            FileOutputStream fileOutputStream = new FileOutputStream(hj);
            fileOutputStream.write(a);
            fileOutputStream.flush();
            fileOutputStream.close();
            C1058l.m2184k("sals", "Download finished");
            return true;
        } catch (Throwable th) {
        }
        return false;
    }
}
