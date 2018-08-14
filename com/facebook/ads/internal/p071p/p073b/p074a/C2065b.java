package com.facebook.ads.internal.p071p.p073b.p074a;

import com.facebook.ads.internal.p071p.p073b.C2064a;
import com.facebook.ads.internal.p071p.p073b.C2090l;
import java.io.File;
import java.io.RandomAccessFile;

public class C2065b implements C2064a {
    public File f4905a;
    private final C2063a f4906b;
    private RandomAccessFile f4907c;

    public C2065b(File file, C2063a c2063a) {
        if (c2063a == null) {
            try {
                throw new NullPointerException();
            } catch (Throwable e) {
                throw new C2090l("Error using file " + file + " as disc cache", e);
            }
        }
        this.f4906b = c2063a;
        C2069d.m6646a(file.getParentFile());
        boolean exists = file.exists();
        this.f4905a = exists ? file : new File(file.getParentFile(), file.getName() + ".download");
        this.f4907c = new RandomAccessFile(this.f4905a, exists ? "r" : "rw");
    }

    private boolean m6636a(File file) {
        return file.getName().endsWith(".download");
    }

    public synchronized int mo3746a() {
        try {
        } catch (Throwable e) {
            throw new C2090l("Error reading length of file " + this.f4905a, e);
        }
        return (int) this.f4907c.length();
    }

    public synchronized int mo3747a(byte[] bArr, long j, int i) {
        try {
            this.f4907c.seek(j);
        } catch (Throwable e) {
            throw new C2090l(String.format("Error reading %d bytes with offset %d from file[%d bytes] to buffer[%d bytes]", new Object[]{Integer.valueOf(i), Long.valueOf(j), Integer.valueOf(mo3746a()), Integer.valueOf(bArr.length)}), e);
        }
        return this.f4907c.read(bArr, 0, i);
    }

    public synchronized void mo3748a(byte[] bArr, int i) {
        try {
            if (mo3751d()) {
                throw new C2090l("Error append cache: cache file " + this.f4905a + " is completed!");
            }
            this.f4907c.seek((long) mo3746a());
            this.f4907c.write(bArr, 0, i);
        } catch (Throwable e) {
            throw new C2090l(String.format("Error writing %d bytes to %s from buffer with size %d", new Object[]{Integer.valueOf(i), this.f4907c, Integer.valueOf(bArr.length)}), e);
        }
    }

    public synchronized void mo3749b() {
        try {
            this.f4907c.close();
            this.f4906b.mo3752a(this.f4905a);
        } catch (Throwable e) {
            throw new C2090l("Error closing file " + this.f4905a, e);
        }
    }

    public synchronized void mo3750c() {
        if (!mo3751d()) {
            mo3749b();
            File file = new File(this.f4905a.getParentFile(), this.f4905a.getName().substring(0, this.f4905a.getName().length() - ".download".length()));
            if (this.f4905a.renameTo(file)) {
                this.f4905a = file;
                try {
                    this.f4907c = new RandomAccessFile(this.f4905a, "r");
                } catch (Throwable e) {
                    throw new C2090l("Error opening " + this.f4905a + " as disc cache", e);
                }
            }
            throw new C2090l("Error renaming file " + this.f4905a + " to " + file + " for completion!");
        }
    }

    public synchronized boolean mo3751d() {
        return !m6636a(this.f4905a);
    }
}
