package com.elephant.data.p044e.p045a;

import android.content.Context;
import com.elephant.data.p048g.C1814b;
import com.elephant.data.p048g.C1816d;
import com.elephant.data.p048g.p049a.C1810b;
import java.io.DataOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public final class C1778b extends C1777a {
    private static String f3719c = C1814b.eQ;

    static {
        String str = C1814b.eP;
    }

    public C1778b(Context context) {
        super(context);
    }

    public final void mo3538b(C1783g c1783g) {
        c1783g.f3738d = 1;
        StringBuilder stringBuilder = null;
        if (c1783g != null) {
            stringBuilder = new StringBuilder(c1783g.f3740f);
            C1783g c1783g2 = c1783g.f3742h;
            while (c1783g2 != null && c1783g2.f3740f != null) {
                stringBuilder.append(C1814b.eY);
                stringBuilder.append(c1783g2.f3740f);
                c1783g2 = c1783g2.f3742h;
            }
        }
        if (stringBuilder != null) {
            try {
                String a = C1816d.m5284a(C1810b.m5232a(URLEncoder.encode(stringBuilder.toString(), C1777a.f3716b), f3719c).getBytes());
                if (a != null) {
                    DataOutputStream dataOutputStream = new DataOutputStream(this.a.getOutputStream());
                    dataOutputStream.write(a.getBytes());
                    dataOutputStream.flush();
                    dataOutputStream.close();
                }
                if (this.a.getResponseCode() == 200) {
                    c1783g.f3738d = 3;
                } else {
                    c1783g.f3738d = 2;
                }
                this.a.disconnect();
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e2) {
                e2.printStackTrace();
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
    }
}
