package com.inmobi.rendering.mraid;

import android.os.AsyncTask;
import android.os.Environment;
import android.webkit.URLUtil;
import com.inmobi.commons.core.utilities.C3155d;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.rendering.RenderView;
import cz.msebera.android.httpclient.HttpStatus;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.List;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: DownloadTask */
public final class C3221b extends AsyncTask<Void, Void, String> {
    private static final String f8071a = C3221b.class.getSimpleName();
    private int f8072b;
    private File f8073c;
    private String f8074d;
    private String f8075e;
    private String f8076f;
    private WeakReference<RenderView> f8077g;
    private C3220a f8078h;
    private List<String> f8079i;
    private long f8080j;
    private String f8081k;

    /* compiled from: DownloadTask */
    public interface C3220a {
        void m10747a();

        void m10748a(int i);
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m10750a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m10751a((String) obj);
    }

    public C3221b(String str, File file, String str2, String str3, RenderView renderView) {
        this.f8081k = str;
        this.f8073c = file;
        this.f8074d = str2;
        this.f8075e = str3;
        this.f8079i = renderView.getRenderingConfig().m9674h();
        this.f8080j = renderView.getRenderingConfig().m9673g();
        this.f8077g = new WeakReference(renderView);
    }

    protected String m10750a(Void... voidArr) {
        if (!C3155d.m10406a()) {
            this.f8072b = 8;
            return "fail";
        } else if (!this.f8075e.matches("[A-Za-z0-9]+") || this.f8075e.equals("")) {
            this.f8072b = 2;
            return "fail";
        } else if (this.f8074d.equals("") || !URLUtil.isValidUrl(this.f8074d)) {
            this.f8072b = 3;
            return "fail";
        } else if (Environment.getExternalStorageState().equals("mounted")) {
            String[] strArr = (String[]) this.f8079i.toArray(new String[this.f8079i.size()]);
            try {
                long currentTimeMillis = System.currentTimeMillis();
                HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(this.f8074d).openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.setConnectTimeout(5000);
                int responseCode = httpURLConnection.getResponseCode();
                Logger.m10359a(InternalLogLevel.INTERNAL, f8071a, "Response code: " + responseCode);
                if (responseCode < HttpStatus.SC_BAD_REQUEST) {
                    Object obj;
                    String contentType = httpURLConnection.getContentType();
                    Logger.m10359a(InternalLogLevel.INTERNAL, f8071a, "Content Type: " + contentType);
                    for (String str : strArr) {
                        Logger.m10359a(InternalLogLevel.INTERNAL, f8071a, "Allowed Type: " + str);
                        if (contentType != null && str.toLowerCase(Locale.ENGLISH).equals(contentType.toLowerCase(Locale.ENGLISH))) {
                            obj = 1;
                            break;
                        }
                    }
                    obj = null;
                    if (obj == null) {
                        this.f8072b = 6;
                        return "fail";
                    }
                }
                long contentLength = (long) httpURLConnection.getContentLength();
                if (contentLength >= 0) {
                    Logger.m10359a(InternalLogLevel.INTERNAL, f8071a, "ContentSize: " + contentLength + " max size: " + this.f8080j);
                    if (contentLength > this.f8080j) {
                        this.f8072b = 7;
                        return "fail";
                    }
                }
                httpURLConnection.connect();
                FileOutputStream fileOutputStream = new FileOutputStream(this.f8073c);
                InputStream inputStream = httpURLConnection.getInputStream();
                byte[] bArr = new byte[1024];
                long j = 0;
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read <= 0 || isCancelled()) {
                        fileOutputStream.close();
                        j = System.currentTimeMillis();
                    } else {
                        j += (long) read;
                        if (j > this.f8080j) {
                            this.f8072b = 7;
                            return "fail";
                        }
                        fileOutputStream.write(bArr, 0, read);
                    }
                }
                fileOutputStream.close();
                j = System.currentTimeMillis();
                if (isCancelled()) {
                    Logger.m10359a(InternalLogLevel.INTERNAL, f8071a, "cancelSaveContent called.File: " + this.f8073c.getAbsolutePath() + " deleted: " + this.f8073c.delete());
                } else {
                    String str2 = "file://" + this.f8073c.getAbsolutePath();
                    Logger.m10359a(InternalLogLevel.INTERNAL, f8071a, "file path of video: " + str2);
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("url", this.f8074d);
                    jSONObject.put("saved_url", str2);
                    jSONObject.put("size_in_bytes", this.f8073c.length());
                    jSONObject.put("download_started_at", currentTimeMillis);
                    jSONObject.put("download_ended_at", j);
                    this.f8076f = jSONObject.toString().replace("\"", "\\\"");
                }
                return "success";
            } catch (SocketTimeoutException e) {
                Logger.m10359a(InternalLogLevel.INTERNAL, f8071a, "SocketTimeoutException");
                this.f8072b = 4;
                return "fail";
            } catch (FileNotFoundException e2) {
                Logger.m10359a(InternalLogLevel.INTERNAL, f8071a, "FileNotFoundException");
                this.f8072b = 4;
                return "fail";
            } catch (MalformedURLException e3) {
                Logger.m10359a(InternalLogLevel.INTERNAL, f8071a, "MalformedURLException");
                this.f8072b = 3;
                return "fail";
            } catch (ProtocolException e4) {
                Logger.m10359a(InternalLogLevel.INTERNAL, f8071a, "ProtocolException");
                this.f8072b = 8;
                return "fail";
            } catch (IOException e5) {
                Logger.m10359a(InternalLogLevel.INTERNAL, f8071a, "IOException");
                this.f8072b = 8;
                return "fail";
            } catch (JSONException e6) {
                Logger.m10359a(InternalLogLevel.INTERNAL, f8071a, "JSONException");
                this.f8072b = 0;
                return "fail";
            }
        } else {
            this.f8072b = 10;
            return "fail";
        }
    }

    protected void onCancelled() {
        super.onCancelled();
    }

    protected void m10751a(String str) {
        if (str.equals("success")) {
            if (this.f8077g.get() != null) {
                ((RenderView) this.f8077g.get()).m10630a(this.f8081k, "sendSaveContentResult(\"saveContent_" + this.f8075e + "\", 'success', \"" + this.f8076f + "\");");
            }
            if (this.f8078h != null) {
                this.f8078h.m10747a();
            }
        } else {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("url", this.f8074d);
                jSONObject.put("reason", this.f8072b);
                String replace = jSONObject.toString().replace("\"", "\\\"");
                if (this.f8077g.get() != null) {
                    ((RenderView) this.f8077g.get()).m10630a(this.f8081k, "sendSaveContentResult(\"saveContent_" + this.f8075e + "\", 'failed', \"" + replace + "\");");
                }
                if (this.f8078h != null) {
                    this.f8078h.m10748a(this.f8072b);
                }
            } catch (JSONException e) {
                if (this.f8077g.get() != null) {
                    ((RenderView) this.f8077g.get()).m10630a(this.f8081k, "sendSaveContentResult(\"saveContent_" + this.f8075e + "\", 'failed', \"JSONException\");");
                }
            }
        }
        super.onPostExecute(str);
    }

    public String m10749a() {
        return this.f8075e;
    }
}
