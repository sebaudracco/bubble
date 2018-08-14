package com.integralads.avid.library.inmobi;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.integralads.avid.library.inmobi.p126f.C3312a;
import com.integralads.avid.library.inmobi.p126f.C3316e;
import com.stepleaderdigital.reveal.Reveal;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/* compiled from: AvidLoader */
public class C3296c {
    private static C3291a f8429a;
    private static C3292b f8430b;
    private static C3293c f8431c;
    private static C3296c f8432d = new C3296c();
    private static final Runnable f8433e = new C32901();

    /* compiled from: AvidLoader */
    static class C32901 implements Runnable {
        C32901() {
        }

        public void run() {
            if (C3296c.f8429a != null) {
                C3296c.f8429a.sendEmptyMessage(0);
            }
        }
    }

    /* compiled from: AvidLoader */
    private static class C3291a extends Handler {
        private WeakReference<Context> f8428a;

        public C3291a(Context context) {
            this.f8428a = new WeakReference(context);
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            Context context = (Context) this.f8428a.get();
            if (context != null && C3316e.m11302a(context)) {
                C3296c.m11211c(context);
            }
        }
    }

    /* compiled from: AvidLoader */
    public interface C3292b {
        void mo6326a();
    }

    /* compiled from: AvidLoader */
    private static class C3293c extends AsyncTask<String, Void, String> {
        private C3293c() {
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m11202a((String[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m11203a((String) obj);
        }

        protected String m11202a(String... strArr) {
            InputStream bufferedInputStream;
            InputStream inputStream;
            Throwable th;
            IOException e;
            String str = strArr[0];
            if (TextUtils.isEmpty(str)) {
                C3312a.m11278a("AvidLoader: URL is empty");
                return null;
            }
            try {
                URLConnection openConnection = new URL(str).openConnection();
                openConnection.connect();
                bufferedInputStream = new BufferedInputStream(openConnection.getInputStream());
                try {
                    Writer stringWriter = new StringWriter();
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = bufferedInputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        stringWriter.write(new String(bArr, 0, read));
                    }
                    String obj = stringWriter.toString();
                    if (bufferedInputStream != null) {
                        try {
                            bufferedInputStream.close();
                        } catch (Exception e2) {
                            C3312a.m11279a("AvidLoader: can not close Stream", e2);
                            return null;
                        }
                    }
                    return obj;
                } catch (MalformedURLException e3) {
                    inputStream = bufferedInputStream;
                    try {
                        C3312a.m11278a("AvidLoader: something is wrong with the URL '" + str + "'");
                        if (inputStream != null) {
                            return null;
                        }
                        try {
                            inputStream.close();
                            return null;
                        } catch (Exception e22) {
                            C3312a.m11279a("AvidLoader: can not close Stream", e22);
                            return null;
                        }
                    } catch (Throwable th2) {
                        Throwable th3 = th2;
                        bufferedInputStream = inputStream;
                        th = th3;
                        if (bufferedInputStream != null) {
                            try {
                                bufferedInputStream.close();
                            } catch (Exception e222) {
                                C3312a.m11279a("AvidLoader: can not close Stream", e222);
                                return null;
                            }
                        }
                        throw th;
                    }
                } catch (IOException e4) {
                    e = e4;
                    try {
                        C3312a.m11278a("AvidLoader: IO error " + e.getLocalizedMessage());
                        if (bufferedInputStream != null) {
                            return null;
                        }
                        try {
                            bufferedInputStream.close();
                            return null;
                        } catch (Exception e2222) {
                            C3312a.m11279a("AvidLoader: can not close Stream", e2222);
                            return null;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        if (bufferedInputStream != null) {
                            bufferedInputStream.close();
                        }
                        throw th;
                    }
                }
            } catch (MalformedURLException e5) {
                inputStream = null;
                C3312a.m11278a("AvidLoader: something is wrong with the URL '" + str + "'");
                if (inputStream != null) {
                    return null;
                }
                inputStream.close();
                return null;
            } catch (IOException e6) {
                e = e6;
                bufferedInputStream = null;
                C3312a.m11278a("AvidLoader: IO error " + e.getLocalizedMessage());
                if (bufferedInputStream != null) {
                    return null;
                }
                bufferedInputStream.close();
                return null;
            } catch (Throwable th5) {
                th = th5;
                bufferedInputStream = null;
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                throw th;
            }
        }

        protected void m11203a(String str) {
            if (!TextUtils.isEmpty(str)) {
                C3287a.m11186a(str);
                if (C3296c.f8430b != null) {
                    C3296c.f8430b.mo6326a();
                }
            } else if (C3296c.f8429a != null) {
                C3296c.f8429a.postDelayed(C3296c.f8433e, Reveal.CHECK_DELAY);
            }
            C3296c.f8431c = null;
        }

        protected void onCancelled() {
            C3296c.f8431c = null;
        }
    }

    public static C3296c m11205a() {
        return f8432d;
    }

    public static void m11206a(Context context) {
        f8429a = new C3291a(context);
        C3296c.m11211c(context);
    }

    public static void m11207a(C3292b c3292b) {
        f8430b = c3292b;
    }

    private static void m11211c(Context context) {
        if (!C3287a.m11187a() && f8431c == null) {
            f8431c = new C3293c();
            if (VERSION.SDK_INT >= 11) {
                f8431c.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[]{"https://mobile-static.adsafeprotected.com/avid-v2.js"});
                return;
            }
            f8431c.execute(new String[]{"https://mobile-static.adsafeprotected.com/avid-v2.js"});
        }
    }
}
