package com.altbeacon.beacon.service.p009a;

import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.Set;

public class C0858e {
    private final Set<ByteBuffer> f1719a = new HashSet();

    public void m1745a() {
        this.f1719a.clear();
    }

    public boolean m1746a(String str, byte[] bArr) {
        byte[] bytes = str.getBytes();
        ByteBuffer allocate = ByteBuffer.allocate(bytes.length + bArr.length);
        allocate.put(bytes);
        allocate.put(bArr);
        allocate.rewind();
        return this.f1719a.size() == 1000 ? this.f1719a.contains(allocate) : this.f1719a.add(allocate);
    }
}
