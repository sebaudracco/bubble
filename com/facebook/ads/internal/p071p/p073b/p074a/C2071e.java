package com.facebook.ads.internal.p071p.p073b.p074a;

import android.util.Log;
import java.io.File;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

abstract class C2071e implements C2063a {
    private final ExecutorService f4910a = Executors.newSingleThreadExecutor();

    private class C2070a implements Callable<Void> {
        final /* synthetic */ C2071e f4908a;
        private final File f4909b;

        public C2070a(C2071e c2071e, File file) {
            this.f4908a = c2071e;
            this.f4909b = file;
        }

        public Void m6651a() {
            this.f4908a.m6655b(this.f4909b);
            return null;
        }

        public /* synthetic */ Object call() {
            return m6651a();
        }
    }

    C2071e() {
    }

    private void m6653a(List<File> list) {
        long b = m6654b((List) list);
        int size = list.size();
        int i = size;
        for (File file : list) {
            if (!mo3754a(file, b, i)) {
                long length = file.length();
                if (file.delete()) {
                    i--;
                    b -= length;
                    Log.i("ProxyCache", "Cache file " + file + " is deleted because it exceeds cache limit");
                    size = i;
                    i = size;
                } else {
                    Log.e("ProxyCache", "Error deleting file " + file + " for trimming cache");
                }
            }
            size = i;
            i = size;
        }
    }

    private long m6654b(List<File> list) {
        long j = 0;
        for (File length : list) {
            j = length.length() + j;
        }
        return j;
    }

    private void m6655b(File file) {
        C2069d.m6648c(file);
        m6653a(C2069d.m6647b(file.getParentFile()));
    }

    public void mo3752a(File file) {
        this.f4910a.submit(new C2070a(this, file));
    }

    protected abstract boolean mo3754a(File file, long j, int i);
}
