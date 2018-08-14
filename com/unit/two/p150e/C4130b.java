package com.unit.two.p150e;

import android.content.Context;
import com.unit.two.p145a.C4092a;
import com.unit.two.p145a.C4093b;
import com.unit.two.p147c.C4096a;
import com.unit.two.p147c.C4112o;
import java.util.List;

final class C4130b implements C4129d {
    final /* synthetic */ Context f9654a;

    C4130b(Context context) {
        this.f9654a = context;
    }

    public final void mo6926a(String str) {
        try {
            C4093b c4093b = new C4093b(str);
            if (c4093b.m12663c() != null && c4093b.m12661a() == 200 && c4093b.m12662b().equals(C4096a.eW)) {
                List c = c4093b.m12663c();
                int size = c.size();
                if (size > 0) {
                    int i = 0;
                    int i2 = 0;
                    while (i < size) {
                        int i3;
                        C4092a c4092a = (C4092a) c.get(i);
                        if (c4092a == null || c4092a.m12657b() != 2) {
                            i3 = i2;
                        } else {
                            boolean z = i == size + -1;
                            if (i == 0) {
                                C4112o.m12699a(this.f9654a, c4092a, z);
                            } else if (C4128a.f9628e != null) {
                                C4128a.f9628e.postDelayed(new C4131c(this, c4092a, z), (long) (i2 * 1000));
                            }
                            i3 = c4092a.m12659d() + i2;
                        }
                        i++;
                        i2 = i3;
                    }
                }
            }
        } catch (Exception e) {
        }
    }
}
