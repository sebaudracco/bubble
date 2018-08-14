package com.elephant.data.p044e.p045a;

import android.os.Process;
import java.util.HashSet;

final class C1792p extends Thread {
    private /* synthetic */ C1791o f3778a;

    C1792p(C1791o c1791o) {
        this.f3778a = c1791o;
    }

    public final void run() {
        Process.setThreadPriority(11);
        HashSet hashSet = new HashSet();
        while (!this.f3778a.f3777a.f3754d) {
            try {
                C1783g a = this.f3778a.f3777a.f3755e.m5008a();
                if (a != null) {
                    hashSet.add(String.valueOf(a.f3735a));
                    if (a.f3739e < 3) {
                        this.f3778a.f3777a.m5140a(a);
                    }
                    if (a.f3738d == 3) {
                        this.f3778a.f3777a.f3756f.m5005a(a);
                    } else {
                        a.f3739e++;
                        if (a.f3739e < 3) {
                            this.f3778a.f3777a.f3755e.m5009a(a);
                        } else {
                            a.f3743i = true;
                            this.f3778a.f3777a.f3756f.m5007b(a);
                            this.f3778a.f3777a.f3754d = true;
                            return;
                        }
                    }
                } else if (!this.f3778a.f3777a.m5148a(hashSet)) {
                    this.f3778a.f3777a.f3754d = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        C1785i.m5136a(this.f3778a.f3777a, System.currentTimeMillis());
        this.f3778a.f3777a.m5159f();
    }
}
