package com.facebook.ads.internal.p071p.p073b;

import android.text.TextUtils;
import com.facebook.ads.internal.p071p.p073b.p074a.C2065b;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.net.Socket;

class C2078e extends C2077k {
    private final C2089h f4928a;
    private final C2065b f4929b;
    private C2074b f4930c;

    public C2078e(C2089h c2089h, C2065b c2065b) {
        super(c2089h, c2065b);
        this.f4929b = c2065b;
        this.f4928a = c2089h;
    }

    private void m6681a(OutputStream outputStream, long j) {
        byte[] bArr = new byte[8192];
        while (true) {
            int a = m6676a(bArr, j, bArr.length);
            if (a != -1) {
                outputStream.write(bArr, 0, a);
                j += (long) a;
            } else {
                outputStream.flush();
                return;
            }
        }
    }

    private boolean m6682a(C2076d c2076d) {
        int a = this.f4928a.mo3757a();
        boolean z = a > 0;
        int a2 = this.f4929b.mo3746a();
        if (z && c2076d.f4919c) {
            if (((float) c2076d.f4918b) > (((float) a) * 0.2f) + ((float) a2)) {
                return false;
            }
        }
        return true;
    }

    private String m6683b(C2076d c2076d) {
        int i = !TextUtils.isEmpty(this.f4928a.m6732c()) ? 1 : 0;
        int a = this.f4929b.mo3751d() ? this.f4929b.mo3746a() : this.f4928a.mo3757a();
        int i2 = a >= 0 ? 1 : 0;
        long j = c2076d.f4919c ? ((long) a) - c2076d.f4918b : (long) a;
        int i3 = (i2 == 0 || !c2076d.f4919c) ? 0 : 1;
        return (c2076d.f4919c ? "HTTP/1.1 206 PARTIAL CONTENT\n" : "HTTP/1.1 200 OK\n") + "Accept-Ranges: bytes\n" + (i2 != 0 ? String.format("Content-Length: %d\n", new Object[]{Long.valueOf(j)}) : "") + (i3 != 0 ? String.format("Content-Range: bytes %d-%d/%d\n", new Object[]{Long.valueOf(c2076d.f4918b), Integer.valueOf(a - 1), Integer.valueOf(a)}) : "") + (i != 0 ? String.format("Content-Type: %s\n", new Object[]{r9}) : "") + "\n";
    }

    private void m6684b(OutputStream outputStream, long j) {
        try {
            C2089h c2089h = new C2089h(this.f4928a);
            c2089h.mo3759a((int) j);
            byte[] bArr = new byte[8192];
            while (true) {
                int a = c2089h.mo3758a(bArr);
                if (a == -1) {
                    break;
                }
                outputStream.write(bArr, 0, a);
                j += (long) a;
            }
            outputStream.flush();
        } finally {
            this.f4928a.mo3760b();
        }
    }

    protected void mo3755a(int i) {
        if (this.f4930c != null) {
            this.f4930c.mo3756a(this.f4929b.f4905a, this.f4928a.f4957a, i);
        }
    }

    public void m6686a(C2074b c2074b) {
        this.f4930c = c2074b;
    }

    public void m6687a(C2076d c2076d, Socket socket) {
        OutputStream bufferedOutputStream = new BufferedOutputStream(socket.getOutputStream());
        bufferedOutputStream.write(m6683b(c2076d).getBytes("UTF-8"));
        long j = c2076d.f4918b;
        if (m6682a(c2076d)) {
            m6681a(bufferedOutputStream, j);
        } else {
            m6684b(bufferedOutputStream, j);
        }
    }
}
