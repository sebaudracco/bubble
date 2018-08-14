package com.unit.two.p151f;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Looper;
import com.unit.two.p147c.C4096a;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class C4134a {
    public String f9660a;
    public List f9661b = new ArrayList();
    public List f9662c = new ArrayList();
    public String f9663d;
    public String f9664e;

    public C4134a(JSONObject jSONObject) {
        int i = 0;
        try {
            this.f9660a = jSONObject.optString(C4096a.cY);
            jSONObject.optString(C4096a.cZ);
            jSONObject.optString(C4096a.da);
            jSONObject.optString(C4096a.db);
            JSONArray optJSONArray = jSONObject.optJSONArray(C4096a.dc);
            if (optJSONArray != null) {
                for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                    this.f9661b.add(optJSONArray.optString(i2));
                }
            }
            JSONArray optJSONArray2 = jSONObject.optJSONArray(C4096a.dd);
            if (optJSONArray2 != null) {
                while (i < optJSONArray2.length()) {
                    this.f9662c.add(optJSONArray2.optString(i));
                    i++;
                }
            }
            jSONObject.optString(C4096a.de);
            jSONObject.optString(C4096a.df);
            jSONObject.optString(C4096a.dg);
            jSONObject.optString(C4096a.dh);
            jSONObject.optDouble(C4096a.di);
            jSONObject.optDouble(C4096a.dj);
            jSONObject.optString(C4096a.dk);
            jSONObject.optString(C4096a.dl);
            jSONObject.optInt(C4096a.dm);
            jSONObject.optString(C4096a.dn);
            this.f9663d = jSONObject.optString(C4096a.f9520do);
            this.f9664e = jSONObject.optString(C4096a.dp);
            jSONObject.optString(C4096a.dq);
        } catch (Exception e) {
        }
    }

    public static C4135b m12764a(Context context) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalStateException(C4096a.bo);
        }
        try {
            context.getPackageManager().getPackageInfo(C4096a.bp, 0);
            ServiceConnection c4136c = new C4136c();
            Intent intent = new Intent(C4096a.bq);
            intent.setPackage(C4096a.br);
            if (context.bindService(intent, c4136c, 1)) {
                try {
                    C4137d c4137d = new C4137d(c4136c.m12766a());
                    C4135b c4135b = new C4135b(c4137d.m12767a(), c4137d.m12768a(true));
                    context.unbindService(c4136c);
                    return c4135b;
                } catch (Exception e) {
                    throw e;
                } catch (Throwable th) {
                    context.unbindService(c4136c);
                }
            } else {
                throw new IOException(C4096a.bs);
            }
        } catch (Exception e2) {
            throw e2;
        }
    }
}
