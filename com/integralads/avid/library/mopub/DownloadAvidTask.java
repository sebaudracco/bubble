package com.integralads.avid.library.mopub;

import android.os.AsyncTask;
import android.support.annotation.VisibleForTesting;
import android.text.TextUtils;
import com.integralads.avid.library.mopub.utils.AvidLogs;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class DownloadAvidTask extends AsyncTask<String, Void, String> {
    private static final int BSIZE = 1024;
    private DownloadAvidTaskListener listener;

    public interface DownloadAvidTaskListener {
        void failedToLoadAvid();

        void onLoadAvid(String str);
    }

    public DownloadAvidTaskListener getListener() {
        return this.listener;
    }

    public void setListener(DownloadAvidTaskListener listener) {
        this.listener = listener;
    }

    protected String doInBackground(String... params) {
        IOException e;
        Throwable th;
        String urlString = params[0];
        if (TextUtils.isEmpty(urlString)) {
            AvidLogs.m11437e("AvidLoader: URL is empty");
            return null;
        }
        InputStream inputStream = null;
        try {
            URLConnection urlConnection = new URL(urlString).openConnection();
            urlConnection.connect();
            InputStream inputStream2 = new BufferedInputStream(urlConnection.getInputStream());
            try {
                Writer writer = new StringWriter();
                byte[] buf = new byte[1024];
                while (true) {
                    int data = inputStream2.read(buf);
                    if (data == -1) {
                        break;
                    }
                    writer.write(new String(buf, 0, data));
                }
                String obj = writer.toString();
                if (inputStream2 != null) {
                    try {
                        inputStream2.close();
                    } catch (IOException e2) {
                        AvidLogs.m11438e("AvidLoader: can not close Stream", e2);
                        return null;
                    }
                }
                return obj;
            } catch (MalformedURLException e3) {
                inputStream = inputStream2;
                try {
                    AvidLogs.m11437e("AvidLoader: something is wrong with the URL '" + urlString + "'");
                    if (inputStream != null) {
                        return null;
                    }
                    try {
                        inputStream.close();
                        return null;
                    } catch (IOException e22) {
                        AvidLogs.m11438e("AvidLoader: can not close Stream", e22);
                        return null;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e222) {
                            AvidLogs.m11438e("AvidLoader: can not close Stream", e222);
                            return null;
                        }
                    }
                    throw th;
                }
            } catch (IOException e4) {
                e222 = e4;
                inputStream = inputStream2;
                AvidLogs.m11437e("AvidLoader: IO error " + e222.getLocalizedMessage());
                if (inputStream != null) {
                    return null;
                }
                try {
                    inputStream.close();
                    return null;
                } catch (IOException e2222) {
                    AvidLogs.m11438e("AvidLoader: can not close Stream", e2222);
                    return null;
                }
            } catch (Throwable th3) {
                th = th3;
                inputStream = inputStream2;
                if (inputStream != null) {
                    inputStream.close();
                }
                throw th;
            }
        } catch (MalformedURLException e5) {
            AvidLogs.m11437e("AvidLoader: something is wrong with the URL '" + urlString + "'");
            if (inputStream != null) {
                return null;
            }
            inputStream.close();
            return null;
        } catch (IOException e6) {
            e2222 = e6;
            AvidLogs.m11437e("AvidLoader: IO error " + e2222.getLocalizedMessage());
            if (inputStream != null) {
                return null;
            }
            inputStream.close();
            return null;
        }
    }

    protected void onPostExecute(String result) {
        if (this.listener == null) {
            return;
        }
        if (TextUtils.isEmpty(result)) {
            this.listener.failedToLoadAvid();
        } else {
            this.listener.onLoadAvid(result);
        }
    }

    protected void onCancelled() {
        if (this.listener != null) {
            this.listener.failedToLoadAvid();
        }
    }

    @VisibleForTesting
    void invokeOnPostExecute(String result) {
        onPostExecute(result);
    }

    @VisibleForTesting
    void invokeOnCancelled() {
        onCancelled();
    }
}
