package com.unit.three.p138b;

import java.nio.ByteBuffer;
import java.util.concurrent.ConcurrentLinkedQueue;

public final class C4051a {
    private static ConcurrentLinkedQueue f9360a = new ConcurrentLinkedQueue();

    public static ByteBuffer m12500a() {
        ByteBuffer byteBuffer = (ByteBuffer) f9360a.poll();
        return byteBuffer == null ? ByteBuffer.allocateDirect(16384) : byteBuffer;
    }

    public static void m12501a(ByteBuffer byteBuffer) {
        try {
            byteBuffer.clear();
            f9360a.offer(byteBuffer);
        } catch (Throwable th) {
        }
    }

    public static void m12502b() {
        f9360a.clear();
    }
}
