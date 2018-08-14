package com.inmobi.commons.core.network;

import com.inmobi.commons.core.network.NetworkError.ErrorCode;
import com.inmobi.commons.core.network.NetworkRequest.RequestType;
import com.inmobi.commons.core.p115d.C3135c;
import com.inmobi.commons.core.utilities.C3155d;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import cz.msebera.android.httpclient.client.utils.URLEncodedUtils;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: NetworkConnection */
class C3142b {
    private static final String f7749a = C3142b.class.getName();
    private NetworkRequest f7750b;
    private HttpURLConnection f7751c;

    public C3142b(NetworkRequest networkRequest) {
        this.f7750b = networkRequest;
    }

    public C3143c m10346a() {
        C3143c c3143c;
        this.f7750b.mo6238a();
        if (C3155d.m10406a()) {
            try {
                String k = this.f7750b.m9763k();
                Logger.m10359a(InternalLogLevel.INTERNAL, f7749a, "Url: " + k);
                this.f7751c = m10340a(k);
                if (!this.f7750b.m9766n()) {
                    this.f7751c.setInstanceFollowRedirects(false);
                }
                if (this.f7750b.m9767o() == RequestType.POST) {
                    m10344b(this.f7750b.m9765m());
                }
                return m10343b();
            } catch (IOException e) {
                IOException iOException = e;
                c3143c = new C3143c(this.f7750b);
                c3143c.m10347a(new NetworkError(ErrorCode.NETWORK_IO_ERROR, iOException.getLocalizedMessage()));
                return c3143c;
            } catch (IllegalArgumentException e2) {
                c3143c = new C3143c(this.f7750b);
                c3143c.m10347a(new NetworkError(ErrorCode.BAD_REQUEST, "The URL is malformed:" + ErrorCode.BAD_REQUEST.toString()));
                return c3143c;
            } catch (Exception e3) {
                Exception exception = e3;
                c3143c = new C3143c(this.f7750b);
                c3143c.m10347a(new NetworkError(ErrorCode.UNKNOWN_ERROR, exception.getLocalizedMessage()));
                try {
                    Map hashMap = new HashMap();
                    hashMap.put("type", "GenericException");
                    hashMap.put("message", exception.getMessage() + "");
                    C3135c.m10255a().m10280a("root", "ExceptionCaught", hashMap);
                    return c3143c;
                } catch (Exception e4) {
                    Logger.m10359a(InternalLogLevel.INTERNAL, f7749a, "Error in submitting telemetry event : (" + exception.getMessage() + ")");
                    return c3143c;
                }
            }
        }
        c3143c = new C3143c(this.f7750b);
        c3143c.m10347a(new NetworkError(ErrorCode.NETWORK_UNAVAILABLE_ERROR, "Network not reachable currently. Please try again."));
        return c3143c;
    }

    private HttpURLConnection m10340a(String str) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        m10342a(httpURLConnection);
        return httpURLConnection;
    }

    private void m10342a(HttpURLConnection httpURLConnection) throws ProtocolException {
        httpURLConnection.setConnectTimeout(this.f7750b.m9768p());
        httpURLConnection.setReadTimeout(this.f7750b.m9769q());
        httpURLConnection.setUseCaches(false);
        if (this.f7750b.m9762j() != null) {
            for (String str : this.f7750b.m9762j().keySet()) {
                httpURLConnection.setRequestProperty(str, (String) this.f7750b.m9762j().get(str));
            }
        }
        RequestType o = this.f7750b.m9767o();
        httpURLConnection.setRequestMethod(o.toString());
        if (o != RequestType.GET) {
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
        }
    }

    private void m10344b(String str) throws IOException, SecurityException {
        Throwable th;
        this.f7751c.setRequestProperty("Content-Length", Integer.toString(str.length()));
        this.f7751c.setRequestProperty("Content-Type", URLEncodedUtils.CONTENT_TYPE);
        Closeable bufferedWriter;
        try {
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(this.f7751c.getOutputStream()));
            try {
                bufferedWriter.write(str);
                C3155d.m10404a(bufferedWriter);
            } catch (Throwable th2) {
                th = th2;
                C3155d.m10404a(bufferedWriter);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            bufferedWriter = null;
            C3155d.m10404a(bufferedWriter);
            throw th;
        }
    }

    private C3143c m10343b() {
        C3143c c3143c = new C3143c(this.f7750b);
        try {
            int responseCode = this.f7751c.getResponseCode();
            Logger.m10359a(InternalLogLevel.INTERNAL, f7749a, this.f7750b.m9761i() + "Response code: " + responseCode);
            if (responseCode == 200) {
                m10341a(c3143c, null, false);
            } else {
                ErrorCode fromValue = ErrorCode.fromValue(responseCode);
                if (fromValue == ErrorCode.BAD_REQUEST) {
                    m10341a(c3143c, null, true);
                    c3143c.m10347a(new NetworkError(fromValue, m10345c(c3143c.m10352b())));
                } else {
                    if (fromValue == null) {
                        fromValue = ErrorCode.UNKNOWN_ERROR;
                    }
                    c3143c.m10347a(new NetworkError(fromValue, "HTTP:" + responseCode));
                    c3143c.m10349a(this.f7751c.getHeaderFields());
                }
            }
            C3155d.m10404a(null);
            this.f7751c.disconnect();
        } catch (SocketTimeoutException e) {
            c3143c.m10347a(new NetworkError(ErrorCode.HTTP_GATEWAY_TIMEOUT, ErrorCode.HTTP_GATEWAY_TIMEOUT.toString()));
        } catch (IOException e2) {
            c3143c.m10347a(new NetworkError(ErrorCode.NETWORK_IO_ERROR, ErrorCode.NETWORK_IO_ERROR.toString()));
        } catch (OutOfMemoryError e3) {
            c3143c.m10347a(new NetworkError(ErrorCode.OUT_OF_MEMORY_ERROR, ErrorCode.OUT_OF_MEMORY_ERROR.toString()));
        } catch (Exception e4) {
            c3143c.m10347a(new NetworkError(ErrorCode.UNKNOWN_ERROR, ErrorCode.UNKNOWN_ERROR.toString()));
            try {
                Map hashMap = new HashMap();
                hashMap.put("type", "GenericException");
                hashMap.put("message", e4.getMessage() + "");
                C3135c.m10255a().m10280a("root", "ExceptionCaught", hashMap);
            } catch (Exception e5) {
                Logger.m10359a(InternalLogLevel.INTERNAL, f7749a, "Error in submitting telemetry event : (" + e4.getMessage() + ")");
            }
        } catch (Throwable th) {
            C3155d.m10404a(null);
            this.f7751c.disconnect();
        }
        return c3143c;
    }

    private void m10341a(C3143c c3143c, InputStream inputStream, boolean z) throws IOException {
        if (!this.f7750b.m9760h() || ((long) this.f7751c.getContentLength()) <= this.f7750b.m9759g()) {
            byte[] a = C3155d.m10407a(z ? this.f7751c.getErrorStream() : this.f7751c.getInputStream());
            if (a.length == 0) {
                c3143c.m10348a("");
            } else {
                if (this.f7750b.m9770r()) {
                    a = this.f7750b.m9752a(a);
                    if (a == null) {
                        c3143c.m10347a(new NetworkError(ErrorCode.INVALID_ENCRYPTED_RESPONSE_RECEIVED, "Unable to decrypt the server response."));
                    }
                }
                if (a != null && this.f7750b.m9771s()) {
                    a = C3155d.m10408a(a);
                    if (a == null) {
                        c3143c.m10347a(new NetworkError(ErrorCode.GZIP_DECOMPRESSION_FAILED, "Failed to uncompress gzip response"));
                    }
                }
                if (a != null) {
                    c3143c.m10348a(new String(a, "UTF-8"));
                    c3143c.m10350a(a);
                }
            }
            c3143c.m10349a(this.f7751c.getHeaderFields());
            return;
        }
        c3143c.m10347a(new NetworkError(ErrorCode.RESPONSE_EXCEEDS_SPECIFIED_SIZE_LIMIT, "Response size greater than specified max response size"));
    }

    private String m10345c(String str) {
        String str2 = null;
        if (str != null) {
            String str3 = "errorMessage";
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has("errorMessage")) {
                    str2 = jSONObject.getString("errorMessage");
                }
            } catch (JSONException e) {
            }
        }
        return str2;
    }
}
