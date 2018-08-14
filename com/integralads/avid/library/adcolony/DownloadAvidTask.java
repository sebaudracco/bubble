package com.integralads.avid.library.adcolony;

import android.os.AsyncTask;
import android.support.annotation.VisibleForTesting;
import android.text.TextUtils;
import com.integralads.avid.library.adcolony.utils.AvidLogs;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class DownloadAvidTask extends AsyncTask<String, Void, String> {
    private static final int f8326a = 1024;
    private DownloadAvidTaskListener f8327b;

    public interface DownloadAvidTaskListener {
        void failedToLoadAvid();

        void onLoadAvid(String str);
    }

    public DownloadAvidTaskListener getListener() {
        return this.f8327b;
    }

    public void setListener(DownloadAvidTaskListener listener) {
        this.f8327b = listener;
    }

    protected String doInBackground(String... params) {
        InputStream bufferedInputStream;
        InputStream inputStream;
        Throwable th;
        IOException e;
        String str = params[0];
        if (TextUtils.isEmpty(str)) {
            AvidLogs.m11161e("AvidLoader: URL is empty");
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
                        AvidLogs.m11162e("AvidLoader: can not close Stream", e2);
                        return null;
                    }
                }
                return obj;
            } catch (MalformedURLException e3) {
                inputStream = bufferedInputStream;
                try {
                    AvidLogs.m11161e("AvidLoader: something is wrong with the URL '" + str + "'");
                    if (inputStream != null) {
                        return null;
                    }
                    try {
                        inputStream.close();
                        return null;
                    } catch (Exception e22) {
                        AvidLogs.m11162e("AvidLoader: can not close Stream", e22);
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
                            AvidLogs.m11162e("AvidLoader: can not close Stream", e222);
                            return null;
                        }
                    }
                    throw th;
                }
            } catch (IOException e4) {
                e = e4;
                try {
                    AvidLogs.m11161e("AvidLoader: IO error " + e.getLocalizedMessage());
                    if (bufferedInputStream != null) {
                        return null;
                    }
                    try {
                        bufferedInputStream.close();
                        return null;
                    } catch (Exception e2222) {
                        AvidLogs.m11162e("AvidLoader: can not close Stream", e2222);
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
            AvidLogs.m11161e("AvidLoader: something is wrong with the URL '" + str + "'");
            if (inputStream != null) {
                return null;
            }
            inputStream.close();
            return null;
        } catch (IOException e6) {
            e = e6;
            bufferedInputStream = null;
            AvidLogs.m11161e("AvidLoader: IO error " + e.getLocalizedMessage());
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

    protected void onPostExecute(String result) {
        if (this.f8327b == null) {
            return;
        }
        if (TextUtils.isEmpty(result)) {
            this.f8327b.failedToLoadAvid();
        } else {
            this.f8327b.onLoadAvid(result);
        }
    }

    protected void onCancelled() {
        if (this.f8327b != null) {
            this.f8327b.failedToLoadAvid();
        }
    }

    @VisibleForTesting
    void m11130a(String str) {
        onPostExecute(str);
    }

    @VisibleForTesting
    void m11129a() {
        onCancelled();
    }
}
