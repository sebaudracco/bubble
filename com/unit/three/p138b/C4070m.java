package com.unit.three.p138b;

import com.unit.three.p139a.C4037a;
import java.io.OutputStream;
import java.nio.ByteBuffer;

final class C4070m extends Thread {
    private /* synthetic */ OutputStream f9425a;
    private /* synthetic */ C4067j f9426b;

    C4070m(C4067j c4067j, OutputStream outputStream) {
        this.f9426b = c4067j;
        this.f9425a = outputStream;
    }

    public final void run() {
        while (true) {
            try {
                ByteBuffer byteBuffer = (ByteBuffer) this.f9426b.f9418b.take();
                if (byteBuffer != null && byteBuffer.hasRemaining()) {
                    this.f9425a.write(byteBuffer.array(), byteBuffer.position(), byteBuffer.limit());
                }
                C4051a.m12501a(byteBuffer);
            } catch (InterruptedException e) {
                return;
            } catch (Throwable e2) {
                C4037a.m12449a();
                C4037a.m12453a(e2);
                this.f9426b.f9420d = false;
                return;
            }
        }
    }
}
