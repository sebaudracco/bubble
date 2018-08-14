package com.elephant.data.p044e.p045a;

import android.content.Context;
import android.os.Process;
import com.elephant.data.p048g.C1817e;
import com.elephant.data.p048g.C1820h;
import com.elephant.data.p048g.p050b.C1813b;

final class C1782f extends Thread {
    private /* synthetic */ Context f3730a;
    private /* synthetic */ String f3731b;
    private /* synthetic */ String f3732c;
    private /* synthetic */ String f3733d;
    private /* synthetic */ String f3734e;

    C1782f(Context context, String str, String str2, String str3, String str4) {
        this.f3730a = context;
        this.f3731b = str;
        this.f3732c = str2;
        this.f3733d = str3;
        this.f3734e = str4;
    }

    public final void run() {
        Process.setThreadPriority(10);
        if (!C1817e.m5333a(this.f3730a).m5335a()) {
            StringBuffer stringBuffer = new StringBuffer();
            C1784h.m5135a(this.f3730a, stringBuffer, 76, 3, C1813b.m5252a(this.f3730a), C1813b.m5258b(this.f3730a), this.f3731b, this.f3732c, this.f3733d, "", "", "", this.f3734e);
            C1781e.m5132a(this.f3730a, true, true, this.f3731b, C1820h.m5344a(stringBuffer));
        }
    }
}
