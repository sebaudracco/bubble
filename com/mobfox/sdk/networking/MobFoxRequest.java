package com.mobfox.sdk.networking;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import com.facebook.ads.AudienceNetworkActivity;
import com.mobfox.sdk.constants.Constants;
import cz.msebera.android.httpclient.client.methods.HttpPost;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class MobFoxRequest {
    Object data;
    Map<String, String> headers;
    Map<String, String> parameters;
    boolean testMode = false;
    int timeout = 0;
    String url;

    private interface ResponseFormatter {
        Object format(InputStream inputStream);
    }

    class C35893 implements ResponseFormatter {
        C35893() {
        }

        public Object format(InputStream response) {
            return BitmapFactory.decodeStream(response);
        }
    }

    class C35915 implements ResponseFormatter {
        C35915() {
        }

        public Object format(InputStream response) {
            return Drawable.createFromStream(response, null);
        }
    }

    private class DefaultResponseFormatter implements ResponseFormatter {
        private DefaultResponseFormatter() {
        }

        public Object format(InputStream response) {
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(response));
            while (true) {
                try {
                    String line = reader.readLine();
                    if (line == null) {
                        break;
                    }
                    sb.append(line + "\n");
                } catch (IOException e) {
                    Log.d(Constants.MOBFOX_NETWORK, "IOException error reading response");
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e2) {
                            Log.d(Constants.MOBFOX_NETWORK, "IOException error closing response inp stream");
                        }
                    }
                } catch (Throwable th) {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e3) {
                            Log.d(Constants.MOBFOX_NETWORK, "IOException error closing response inp stream");
                        }
                    }
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e4) {
                    Log.d(Constants.MOBFOX_NETWORK, "IOException error closing response inp stream");
                }
            }
            return sb.toString();
        }
    }

    public void setTestMode(boolean testMode) {
        this.testMode = testMode;
    }

    public MobFoxRequest(Context c, String url) {
        this.url = url;
        this.parameters = new HashMap();
        this.headers = new HashMap();
    }

    public MobFoxRequest setTimeout(int timeout) {
        this.timeout = timeout;
        return this;
    }

    public MobFoxRequest setParam(String key, String value) {
        this.parameters.put(key, value);
        return this;
    }

    public MobFoxRequest setData(Object data) {
        this.data = data;
        return this;
    }

    public MobFoxRequest setHeader(String key, String value) {
        this.headers.put(key, value);
        return this;
    }

    private static void _call(String url, String method, Object data, Map<String, String> reqHeaders, int timeout, boolean testMode, ResponseFormatter formatter, AsyncCallback cb) {
        StringBuilder query = new StringBuilder();
        if (method.equals("GET")) {
            Map<String, String> parameters = (Map) data;
            for (String k : parameters.keySet()) {
                try {
                    query.append(k + "=" + URLEncoder.encode((String) parameters.get(k), AudienceNetworkActivity.WEBVIEW_ENCODING));
                } catch (UnsupportedEncodingException e) {
                } catch (Throwable th) {
                }
            }
        }
        String fullURL = url;
        if (query.length() > 0) {
            if (url.indexOf("?") > 0) {
                fullURL = url + "&" + query.toString();
            } else {
                fullURL = url + "?" + query.toString();
            }
        }
        final String finalURL = fullURL;
        final Map<String, String> map = reqHeaders;
        final String str = method;
        final int i = timeout;
        final Object obj = data;
        final ResponseFormatter responseFormatter = formatter;
        final AsyncCallback asyncCallback = cb;
        AsyncTask<String, Void, String> t = new AsyncTask<String, Void, String>() {
            Exception err;
            Object response;
            Map<String, List<String>> responseHeaders;
            int status;

            protected String doInBackground(String... params) {
                Exception e;
                Throwable th;
                Throwable e2;
                HttpURLConnection con = null;
                try {
                    con = (HttpURLConnection) new URL(finalURL).openConnection();
                    con.setUseCaches(true);
                    for (String h : map.keySet()) {
                        con.setRequestProperty(h, (String) map.get(h));
                    }
                    con.setRequestMethod(str);
                    con.setRequestProperty("User-Agent", System.getProperty("http.agent"));
                    if (i > 0) {
                        con.setConnectTimeout(i);
                        con.setReadTimeout(i);
                    }
                    if (str.equals(HttpPost.METHOD_NAME)) {
                        con.setDoInput(true);
                        con.setDoOutput(true);
                        OutputStream os = con.getOutputStream();
                        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                        BufferedWriter bufferedWriter;
                        try {
                            writer.write(obj.toString());
                            writer.flush();
                            writer.close();
                            os.close();
                            bufferedWriter = writer;
                        } catch (Exception e3) {
                            e = e3;
                            bufferedWriter = writer;
                            try {
                                this.err = e;
                                if (con != null) {
                                    try {
                                        con.disconnect();
                                    } catch (Exception e4) {
                                    }
                                }
                                return null;
                            } catch (Throwable th2) {
                                th = th2;
                                if (con != null) {
                                    try {
                                        con.disconnect();
                                    } catch (Exception e5) {
                                    }
                                }
                                throw th;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            bufferedWriter = writer;
                            if (con != null) {
                                con.disconnect();
                            }
                            throw th;
                        }
                    }
                    this.status = con.getResponseCode();
                    this.response = responseFormatter.format(con.getInputStream());
                    this.responseHeaders = con.getHeaderFields();
                    String str = "success";
                    if (con == null) {
                        return str;
                    }
                    try {
                        con.disconnect();
                        return str;
                    } catch (Exception e6) {
                        return str;
                    }
                } catch (Exception e7) {
                    e = e7;
                    this.err = e;
                    if (con != null) {
                        con.disconnect();
                    }
                    return null;
                } catch (Throwable th4) {
                    e2 = th4;
                    if (e2.getMessage() != null) {
                        this.err = new Exception("Error in sending request to server");
                    } else {
                        this.err = new Exception(e2.getMessage());
                    }
                    if (con != null) {
                        con.disconnect();
                    }
                    return null;
                }
            }

            protected void onPostExecute(String code) {
                if (asyncCallback != null) {
                    if (code == null) {
                        asyncCallback.onError(this.err);
                    } else {
                        asyncCallback.onComplete(this.status, this.response, this.responseHeaders);
                    }
                }
            }
        };
        if (testMode) {
            try {
                t.execute(new String[0]);
                return;
            } catch (Throwable err) {
                Log.d(Constants.MOBFOX_NETWORK, "Unable to complete request!", err);
                cb.onError(new Exception(err.toString()));
                return;
            }
        }
        t.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new String[0]);
    }

    public void get(AsyncCallback cb) {
        _call(this.url, "GET", this.parameters, this.headers, this.timeout, this.testMode, new DefaultResponseFormatter(), cb);
    }

    public void post(AsyncCallback cb) {
        _call(this.url, HttpPost.METHOD_NAME, this.data, this.headers, this.timeout, this.testMode, new DefaultResponseFormatter(), cb);
    }

    public void getJSON(final AsyncCallbackJSON cb) {
        get(new AsyncCallback() {
            public void onComplete(int code, Object response, Map<String, List<String>> headers) {
                String jsonStr = (String) response;
                if (jsonStr == null || jsonStr.length() == 0) {
                    cb.onError(new Exception("empty json response."));
                    return;
                }
                try {
                    cb.onComplete(code, new JSONObject((String) response), headers);
                } catch (JSONException e) {
                    Log.d(Constants.MOBFOX_NATIVE, "error parsing JSON response: " + response);
                    cb.onError(e);
                }
            }

            public void onError(Exception e) {
                cb.onError(e);
            }
        });
    }

    public void getBitmap(final AsyncCallbackBitmap cb) {
        _call(this.url, "GET", this.parameters, this.headers, this.timeout, this.testMode, new C35893(), new AsyncCallback() {
            public void onComplete(int code, Object response, Map<String, List<String>> headers) {
                cb.onComplete(code, (Bitmap) response, headers);
            }

            public void onError(Exception e) {
                cb.onError(e);
            }
        });
    }

    public void getDrawable(final AsyncCallbackDrawable cb) {
        _call(this.url, "GET", this.parameters, this.headers, this.timeout, this.testMode, new C35915(), new AsyncCallback() {
            public void onComplete(int code, Object response, Map<String, List<String>> headers) {
                cb.onComplete(code, (Drawable) response, headers);
            }

            public void onError(Exception e) {
                cb.onError(e);
            }
        });
    }
}
