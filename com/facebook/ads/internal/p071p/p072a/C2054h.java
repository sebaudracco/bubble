package com.facebook.ads.internal.p071p.p072a;

import android.os.AsyncTask;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class C2054h extends AsyncTask<C2055l, Void, C2060n> implements C2049c {
    private static Executor f4886a = new ThreadPoolExecutor(1, 1, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue());
    private C2048a f4887b;
    private C2035b f4888c;
    private Exception f4889d;

    public C2054h(C2048a c2048a, C2035b c2035b) {
        this.f4887b = c2048a;
        this.f4888c = c2035b;
    }

    protected C2060n m6605a(C2055l... c2055lArr) {
        if (c2055lArr != null) {
            try {
                if (c2055lArr.length > 0) {
                    return this.f4887b.m6575a(c2055lArr[0]);
                }
            } catch (Exception e) {
                this.f4889d = e;
                cancel(true);
                return null;
            }
        }
        throw new IllegalArgumentException("DoHttpRequestTask takes exactly one argument of type HttpRequest");
    }

    public void mo3745a(C2055l c2055l) {
        super.executeOnExecutor(f4886a, new C2055l[]{c2055l});
    }

    protected void m6607a(C2060n c2060n) {
        this.f4888c.mo3730a(c2060n);
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m6605a((C2055l[]) objArr);
    }

    protected void onCancelled() {
        this.f4888c.mo3731a(this.f4889d);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m6607a((C2060n) obj);
    }
}
