package com.areametrics.areametricssdk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Handler;
import com.appnext.base.p023b.C1048i;
import com.areametrics.areametricssdk.C1338a.C1337a;
import com.areametrics.areametricssdk.C1372e.C1370a;
import com.areametrics.areametricssdk.C1378g.C1352c;
import com.areametrics.areametricssdk.C1378g.C1376a;
import com.areametrics.areametricssdk.C1381h.C1380a;
import com.coremedia.iso.boxes.UserBox;
import com.google.android.gms.common.internal.ImagesContract;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.MySSLSocketFactory;
import com.loopj.android.http.RequestParams;
import com.mopub.mobileads.resource.DrawableConstants.CloseButton;
import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpStatus;
import cz.msebera.android.httpclient.conn.ssl.SSLSocketFactory;
import cz.msebera.android.httpclient.entity.StringEntity;
import java.security.KeyStore;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;
import org.altbeacon.beacon.Region;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class C1367d implements C1337a {
    private static final String f1905a = ("AMS-" + C1367d.class.getSimpleName());
    private SSLSocketFactory f1906A = null;
    private SSLSocketFactory f1907B = null;
    private final String f1908b;
    private final SimpleDateFormat f1909c = new SimpleDateFormat("yyyyMMdd'T'HHmmss'Z'", Locale.US);
    private int f1910d = 200;
    private List<Map<String, Object>> f1911e = null;
    private int f1912f;
    private AsyncHttpClient f1913g = null;
    private BroadcastReceiver f1914h = null;
    private Handler f1915i = null;
    private C1338a f1916j;
    private C1361a f1917k;
    private C1378g f1918l;
    private Context f1919m;
    private boolean f1920n = false;
    private boolean f1921o = false;
    private boolean f1922p = false;
    private boolean f1923q = false;
    private int f1924r = 0;
    private boolean f1925s = false;
    private boolean f1926t = false;
    private int f1927u = 0;
    private int f1928v = 0;
    private int f1929w = 0;
    private int f1930x = 0;
    private int f1931y = 0;
    private SSLSocketFactory f1932z = null;

    class C13586 extends BroadcastReceiver {
        final /* synthetic */ C1367d f1894a;

        C13586(C1367d c1367d) {
            this.f1894a = c1367d;
        }

        public final void onReceive(Context context, Intent intent) {
            if (this.f1894a.m2530d()) {
                this.f1894a.m2511q();
            }
        }
    }

    class C13607 extends AsyncHttpResponseHandler {
        final /* synthetic */ C1367d f1896a;

        class C13591 implements Runnable {
            final /* synthetic */ C13607 f1895a;

            C13591(C13607 c13607) {
                this.f1895a = c13607;
            }

            public final void run() {
                this.f1895a.f1896a.f1920n = false;
                this.f1895a.f1896a.m2536j();
            }
        }

        C13607(C1367d c1367d) {
            this.f1896a = c1367d;
        }

        public final void onFailure(int i, Header[] headerArr, byte[] bArr, Throwable th) {
            this.f1896a.f1912f = this.f1896a.f1912f + 1;
            if (this.f1896a.f1912f < 6) {
                this.f1896a.f1915i.postDelayed(new C13591(this), (long) (this.f1896a.f1912f * 2000));
                return;
            }
            this.f1896a.f1920n = false;
            this.f1896a.f1912f = 0;
        }

        public final void onSuccess(int i, Header[] headerArr, byte[] bArr) {
            int i2 = 0;
            try {
                JSONObject jSONObject = new JSONObject(new String(bArr));
                this.f1896a.m2525a(jSONObject.getString("access_token"), jSONObject.getLong("expires_in") + jSONObject.getLong("created_at"));
                this.f1896a.f1920n = false;
                this.f1896a.f1912f = 0;
                this.f1896a.m2511q();
            } catch (JSONException e) {
                this.f1896a.f1920n = false;
                C1367d c1367d = this.f1896a;
                if (this.f1896a.f1912f < 6) {
                    i2 = this.f1896a.f1912f + 1;
                }
                c1367d.f1912f = i2;
            }
        }

        public final void setUsePoolThread(boolean z) {
            super.setUsePoolThread(true);
        }
    }

    interface C1361a {
        void mo2165a(Set<String> set);
    }

    class C1366b extends AsyncHttpResponseHandler {
        final /* synthetic */ C1367d f1901a;
        private String f1902b;
        private Map<String, Object> f1903c;
        private int f1904d;

        class C13621 implements Runnable {
            final /* synthetic */ C1366b f1897a;

            C13621(C1366b c1366b) {
                this.f1897a = c1366b;
            }

            public final void run() {
                this.f1897a.f1901a.m2511q();
            }
        }

        class C13632 implements Runnable {
            final /* synthetic */ C1366b f1898a;

            C13632(C1366b c1366b) {
                this.f1898a = c1366b;
            }

            public final void run() {
                this.f1898a.f1901a.f1922p = false;
                this.f1898a.f1901a.m2531e();
            }
        }

        class C13643 implements Runnable {
            final /* synthetic */ C1366b f1899a;

            C13643(C1366b c1366b) {
                this.f1899a = c1366b;
            }

            public final void run() {
                this.f1899a.f1901a.f1922p = false;
                this.f1899a.f1901a.m2531e();
            }
        }

        class C13654 implements Runnable {
            final /* synthetic */ C1366b f1900a;

            C13654(C1366b c1366b) {
                this.f1900a = c1366b;
            }

            public final void run() {
                this.f1900a.f1901a.f1922p = false;
                this.f1900a.f1901a.m2531e();
            }
        }

        C1366b(C1367d c1367d, String str, Map<String, Object> map, int i) {
            this.f1901a = c1367d;
            this.f1902b = str;
            this.f1903c = map;
            this.f1904d = i;
        }

        private void m2470a(int i, Header[] headerArr, String str) {
            SharedPreferences sharedPreferences = this.f1901a.m2528b().m2629n().getSharedPreferences("AMS_FC_PARSED_PERSONA_METADATA", 0);
            int i2 = sharedPreferences.getInt(String.valueOf(i), 0);
            Editor edit = sharedPreferences.edit();
            edit.putInt(String.valueOf(i), i2 + 1);
            edit.apply();
            String str2 = this.f1902b.contains(".com/v2/person.json?email=") ? "email" : this.f1902b.contains(".com/v2/person.json?emailMD5=") ? "emailMD5" : this.f1902b.contains(".com/v2/person.json?emailSHA256=") ? "emailSHA256" : this.f1902b.contains(".com/v2/person.json?twitter=") ? "twitter" : "phone";
            int i3;
            long j;
            if (i == 200) {
                C1367d.m2499k(this.f1901a);
                this.f1901a.m2528b().m2618e(str2);
                this.f1901a.f1924r = 0;
                this.f1901a.f1922p = false;
                this.f1901a.m2528b().m2606b("Successfully collected persona data");
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    long currentTimeMillis = (long) (((double) System.currentTimeMillis()) / 1000.0d);
                    long j2 = currentTimeMillis;
                    i3 = 0;
                    int i4 = 0;
                    j = j2;
                    for (Header header : headerArr) {
                        if (header.getName().equals("Date")) {
                            try {
                                j = (long) (((double) new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US).parse(header.getValue()).getTime()) / 1000.0d);
                            } catch (ParseException e) {
                            }
                        } else if (header.getName().equals("X-Rate-Limit-Remaining")) {
                            i4 = Integer.valueOf(header.getValue()).intValue();
                        } else if (header.getName().equals("X-Rate-Limit-Reset")) {
                            i3 = Integer.valueOf(header.getValue()).intValue();
                        }
                    }
                    C1378g a = this.f1901a.m2528b();
                    a.f1989a = 0;
                    a.f1990b = 0;
                    try {
                        a.m2602a(new JSONObject(jSONObject.toString()));
                    } catch (JSONException e2) {
                    }
                    jSONObject = a.m2594a(jSONObject, false);
                    Editor edit2 = a.m2629n().getSharedPreferences("AMS_FC_PARSED_PERSONA", 0).edit();
                    edit2.putString("parsed_persona", jSONObject.toString());
                    edit2.apply();
                    Editor edit3 = a.m2629n().getSharedPreferences("AMS_FC_PARSED_PERSONA_METADATA", 0).edit();
                    edit3.putLong("date", j);
                    edit3.putInt("remaining", i4);
                    edit3.putInt("reset", i3);
                    edit3.putInt("redactions", a.f1989a);
                    edit3.putInt("obfuscations", a.f1990b);
                    edit3.apply();
                    if (a.m2630o() != null) {
                        C1381h o = a.m2630o();
                        if (o.m2641c() != null) {
                            o.m2641c().m2532f();
                        }
                    }
                } catch (JSONException e3) {
                    this.f1901a.m2528b().m2606b("Error serializing persona data to JSONObject: " + e3.getMessage());
                    this.f1901a.f1924r = this.f1901a.f1924r + 1;
                    if (this.f1901a.f1924r < 6) {
                        this.f1901a.m2528b().m2606b("Reattempting persona generation after " + (this.f1901a.f1924r * 2) + " seconds");
                        this.f1901a.f1915i.postDelayed(new C13632(this), (long) (this.f1901a.f1924r * 2000));
                        return;
                    }
                    this.f1901a.f1924r = 0;
                    this.f1901a.f1922p = false;
                    this.f1901a.m2528b().m2606b("Reattempt limit reached, will retry on next app wake opportunity");
                }
            } else if (i == 202) {
                this.f1901a.m2528b().m2596a(System.currentTimeMillis() + 120000);
                this.f1901a.m2528b().m2618e(str2);
                this.f1901a.f1924r = 0;
                this.f1901a.f1922p = false;
                this.f1901a.m2528b().m2606b("Data collection in progress, will reattempt on next app wake opportunity after 2 minutes");
            } else if (i == 404) {
                this.f1901a.m2528b().m2596a(System.currentTimeMillis() + 86400000);
                this.f1901a.m2528b().m2618e(str2);
                this.f1901a.f1924r = 0;
                this.f1901a.f1922p = false;
                this.f1901a.m2528b().m2606b("Data not available, will reattempt on next app wake opportunity after 24 hours");
            } else if (i == 403) {
                int i5 = 0;
                for (Header header2 : headerArr) {
                    if (header2.getName().equals("X-Rate-Limit-Reset")) {
                        i5 = Integer.valueOf(header2.getValue()).intValue();
                    }
                }
                j = (long) (i5 + new Random().nextInt(this.f1901a.m2528b().m2629n().getSharedPreferences("AMS_SDK_CONFIG", 0).getInt("fc_random_delay_max", 5) + 1));
                this.f1901a.m2528b().m2596a(System.currentTimeMillis() + j);
                this.f1901a.f1924r = this.f1901a.f1924r + 1;
                if (this.f1901a.f1924r < 6) {
                    this.f1901a.m2528b().m2606b("Rate window reached, will reattempt after " + (500 + j) + " seconds");
                    this.f1901a.f1915i.postDelayed(new C13643(this), j + 500);
                    return;
                }
                this.f1901a.f1924r = 0;
                this.f1901a.f1922p = false;
                this.f1901a.m2528b().m2606b("Rate limit and reattempt limit reached, will retry on next app wake opportunity");
            } else {
                this.f1901a.f1924r = this.f1901a.f1924r + 1;
                if (this.f1901a.f1924r < 6) {
                    this.f1901a.m2528b().m2606b("Reattempting persona generation after " + (this.f1901a.f1924r * 2) + " seconds");
                    this.f1901a.f1915i.postDelayed(new C13654(this), (long) (this.f1901a.f1924r * 2000));
                    return;
                }
                this.f1901a.f1924r = 0;
                this.f1901a.f1922p = false;
                this.f1901a.m2528b().m2606b("Reattempt limit reached, will retry on next app wake opportunity");
            }
        }

        private boolean m2471a() {
            if (this.f1901a.f1927u > 0) {
                this.f1901a.f1927u = this.f1901a.f1927u - 1;
            } else {
                this.f1901a.f1927u = 0;
            }
            if (this.f1901a.f1927u != 0) {
                return false;
            }
            this.f1901a.f1926t = false;
            return true;
        }

        public final void onFailure(int i, Header[] headerArr, byte[] bArr, Throwable th) {
            if (this.f1902b.contains(".com/v2/person.json?")) {
                String str = null;
                if (bArr != null) {
                    str = new String(bArr);
                }
                m2470a(i, headerArr, str);
            } else if (this.f1904d < 6) {
                this.f1904d++;
                this.f1903c.put("myurl", this.f1902b);
                this.f1903c.put("submitCount", Integer.valueOf(this.f1904d));
                this.f1903c.put("retry", Integer.toString(this.f1904d));
                C1367d.m2489b(this.f1901a, this.f1903c);
                if (i == HttpStatus.SC_UNAUTHORIZED || i == HttpStatus.SC_FORBIDDEN) {
                    this.f1901a.m2536j();
                } else {
                    this.f1901a.f1915i.postDelayed(new C13621(this), (long) (this.f1904d * 2000));
                }
            } else {
                if (this.f1903c.containsKey("jsonData")) {
                    this.f1901a.f1923q = false;
                }
                if (this.f1902b.contains("v3/sdk_configs")) {
                    this.f1901a.f1921o = false;
                }
                if (this.f1902b.contains("v3/loc_batch")) {
                    m2471a();
                }
            }
        }

        public final void onSuccess(int i, Header[] headerArr, byte[] bArr) {
            String str = new String(bArr);
            if (!this.f1901a.f1925s) {
                this.f1901a.f1925s = true;
                this.f1901a.m2528b().m2598a("AreaMetricsSDK Successfully Contacted Server!");
            }
            JSONObject jSONObject;
            if (this.f1902b.contains("v3/sdk_configs")) {
                this.f1901a.f1921o = false;
                try {
                    int optInt;
                    C1378g a;
                    Editor edit;
                    double optDouble;
                    C1378g a2;
                    Editor edit2;
                    String optString;
                    boolean optBoolean;
                    jSONObject = new JSONObject(str);
                    C1367d.m2497j(this.f1901a);
                    if (jSONObject.has("refresh_date") && !jSONObject.isNull("refresh_date")) {
                        this.f1901a.m2528b().m2605b(jSONObject.optLong("refresh_date"));
                    }
                    if (jSONObject.has("join_duration") && !jSONObject.isNull("join_duration")) {
                        optInt = jSONObject.optInt("join_duration");
                        a = this.f1901a.m2528b();
                        if (optInt >= 0) {
                            edit = a.m2629n().getSharedPreferences("AMS_SDK_CONFIG", 0).edit();
                            edit.putInt("join_duration", optInt);
                            edit.apply();
                            a.m2612c("join_duration", Integer.toString(optInt));
                        }
                    }
                    if (jSONObject.has("fc_random_delay_max") && !jSONObject.isNull("fc_random_delay_max")) {
                        optInt = jSONObject.optInt("fc_random_delay_max");
                        a = this.f1901a.m2528b();
                        if (optInt >= 0 && optInt <= HttpStatus.SC_MULTIPLE_CHOICES) {
                            edit = a.m2629n().getSharedPreferences("AMS_SDK_CONFIG", 0).edit();
                            edit.putInt("fc_random_delay_max", optInt);
                            edit.apply();
                            a.m2612c("fc_random_delay_max", Integer.toString(optInt));
                        }
                    }
                    if (jSONObject.has("us_time_filter") && !jSONObject.isNull("us_time_filter")) {
                        optInt = jSONObject.optInt("us_time_filter");
                        a = this.f1901a.m2528b();
                        if (optInt >= 0 && optInt <= 3600) {
                            edit = a.m2629n().getSharedPreferences("AMS_SDK_CONFIG", 0).edit();
                            edit.putInt("us_time_filter", optInt);
                            edit.apply();
                            a.m2612c("us_time_filter", Integer.toString(optInt));
                        }
                    }
                    if (jSONObject.has("us_locdiff_filter") && !jSONObject.isNull("us_locdiff_filter")) {
                        optDouble = jSONObject.optDouble("us_locdiff_filter");
                        a2 = this.f1901a.m2528b();
                        if (optDouble >= 0.0d && optDouble <= 30.0d) {
                            edit2 = a2.m2629n().getSharedPreferences("AMS_SDK_CONFIG", 0).edit();
                            edit2.putFloat("us_locdiff_filter", (float) optDouble);
                            edit2.apply();
                            a2.m2612c("us_locdiff_filter", Double.toString(optDouble));
                        }
                    }
                    if (jSONObject.has("us_accthresh_filter") && !jSONObject.isNull("us_accthresh_filter")) {
                        optDouble = jSONObject.optDouble("us_accthresh_filter");
                        a2 = this.f1901a.m2528b();
                        if (optDouble >= 0.0d && optDouble <= 30.0d) {
                            edit2 = a2.m2629n().getSharedPreferences("AMS_SDK_CONFIG", 0).edit();
                            edit2.putFloat("us_accthresh_filter", (float) optDouble);
                            edit2.apply();
                            a2.m2612c("us_accthresh_filter", Double.toString(optDouble));
                        }
                    }
                    if (jSONObject.has("bs_time_filter") && !jSONObject.isNull("bs_time_filter")) {
                        optInt = jSONObject.optInt("bs_time_filter");
                        a = this.f1901a.m2528b();
                        if (optInt >= 0 && optInt <= 3600) {
                            edit = a.m2629n().getSharedPreferences("AMS_SDK_CONFIG", 0).edit();
                            edit.putInt("bs_time_filter", optInt);
                            edit.apply();
                            a.m2612c("bs_time_filter", Integer.toString(optInt));
                        }
                    }
                    if (jSONObject.has("bs_locdiff_filter") && !jSONObject.isNull("bs_locdiff_filter")) {
                        optDouble = jSONObject.optDouble("bs_locdiff_filter");
                        a2 = this.f1901a.m2528b();
                        if (optDouble >= 0.0d && optDouble <= 30.0d) {
                            edit2 = a2.m2629n().getSharedPreferences("AMS_SDK_CONFIG", 0).edit();
                            edit2.putFloat("bs_locdiff_filter", (float) optDouble);
                            edit2.apply();
                            a2.m2612c("bs_locdiff_filter", Double.toString(optDouble));
                        }
                    }
                    if (jSONObject.has("bs_accthresh_filter") && !jSONObject.isNull("bs_accthresh_filter")) {
                        optDouble = jSONObject.optDouble("bs_accthresh_filter");
                        a2 = this.f1901a.m2528b();
                        if (optDouble >= 0.0d && optDouble <= 30.0d) {
                            edit2 = a2.m2629n().getSharedPreferences("AMS_SDK_CONFIG", 0).edit();
                            edit2.putFloat("bs_accthresh_filter", (float) optDouble);
                            edit2.apply();
                            a2.m2612c("bs_accthresh_filter", Double.toString(optDouble));
                        }
                    }
                    if (jSONObject.has("bgloc_time_filter") && !jSONObject.isNull("bgloc_time_filter")) {
                        optInt = jSONObject.optInt("bgloc_time_filter");
                        a = this.f1901a.m2528b();
                        if (optInt >= 0 && optInt <= 86400) {
                            edit = a.m2629n().getSharedPreferences("AMS_SDK_CONFIG", 0).edit();
                            edit.putInt("bgloc_time_filter", optInt);
                            edit.apply();
                            a.m2612c("bgloc_time_filter", Integer.toString(optInt));
                        }
                    }
                    if (jSONObject.has("geo_cache_dist") && !jSONObject.isNull("geo_cache_dist")) {
                        optDouble = jSONObject.optDouble("geo_cache_dist");
                        a2 = this.f1901a.m2528b();
                        if (optDouble >= 0.0d && optDouble <= 500000.0d) {
                            edit2 = a2.m2629n().getSharedPreferences("AMS_SDK_CONFIG", 0).edit();
                            edit2.putFloat("geo_cache_dist", (float) optDouble);
                            edit2.apply();
                            a2.m2612c("geo_cache_dist", Double.toString(optDouble));
                        }
                    }
                    if (jSONObject.has("persona_sharing") && !jSONObject.isNull("persona_sharing")) {
                        optString = jSONObject.optString("persona_sharing");
                        a = this.f1901a.m2528b();
                        if (optString != null && (optString.equals("none") || optString.equals("limited") || optString.equals("full"))) {
                            edit = a.m2629n().getSharedPreferences("AMS_SDK_CONFIG", 0).edit();
                            edit.putString("persona_sharing", optString);
                            edit.apply();
                            a.m2612c("persona_sharing", optString);
                        }
                    }
                    if (jSONObject.has("lc_priority") && !jSONObject.isNull("lc_priority")) {
                        optString = jSONObject.optString("lc_priority");
                        a = this.f1901a.m2528b();
                        if (optString != null && optString.length() > 0) {
                            edit = a.m2629n().getSharedPreferences("AMS_SDK_CONFIG", 0).edit();
                            edit.putString("lc_priority", optString);
                            edit.apply();
                            a.m2612c("lc_priority", optString);
                        }
                    }
                    if (jSONObject.has("lc_smallest_displacement") && !jSONObject.isNull("lc_smallest_displacement")) {
                        optDouble = jSONObject.optDouble("lc_smallest_displacement");
                        a2 = this.f1901a.m2528b();
                        if (optDouble >= 0.0d && optDouble <= 100000.0d) {
                            edit2 = a2.m2629n().getSharedPreferences("AMS_SDK_CONFIG", 0).edit();
                            edit2.putFloat("lc_smallest_displacement", (float) optDouble);
                            edit2.apply();
                            a2.m2612c("lc_smallest_displacement", Double.toString(optDouble));
                        }
                    }
                    if (jSONObject.has("lc_fastest_interval") && !jSONObject.isNull("lc_fastest_interval")) {
                        optInt = jSONObject.optInt("lc_fastest_interval");
                        a = this.f1901a.m2528b();
                        if (optInt >= 0 && optInt <= 3600) {
                            edit = a.m2629n().getSharedPreferences("AMS_SDK_CONFIG", 0).edit();
                            edit.putInt("lc_fastest_interval", optInt * 1000);
                            edit.apply();
                            a.m2612c("lc_fastest_interval", Integer.toString(optInt));
                        }
                    }
                    if (jSONObject.has("lc_interval") && !jSONObject.isNull("lc_interval")) {
                        optInt = jSONObject.optInt("lc_interval");
                        a = this.f1901a.m2528b();
                        if (optInt >= 0 && optInt <= 86400) {
                            edit = a.m2629n().getSharedPreferences("AMS_SDK_CONFIG", 0).edit();
                            edit.putInt("lc_interval", optInt * 1000);
                            edit.apply();
                            a.m2612c("lc_interval", Integer.toString(optInt));
                        }
                    }
                    if (jSONObject.has("lc_max_wait") && !jSONObject.isNull("lc_max_wait")) {
                        optInt = jSONObject.optInt("lc_max_wait");
                        a = this.f1901a.m2528b();
                        if (optInt >= 0 && optInt <= 86400) {
                            edit = a.m2629n().getSharedPreferences("AMS_SDK_CONFIG", 0).edit();
                            edit.putInt("lc_max_wait", optInt * 1000);
                            edit.apply();
                            a.m2612c("lc_max_wait", Integer.toString(optInt));
                        }
                    }
                    if (jSONObject.has("loc_batch_time_window") && !jSONObject.isNull("loc_batch_time_window")) {
                        optInt = jSONObject.optInt("loc_batch_time_window");
                        a = this.f1901a.m2528b();
                        if (optInt >= 0 && optInt <= 86400) {
                            edit = a.m2629n().getSharedPreferences("AMS_SDK_CONFIG", 0).edit();
                            edit.putInt("loc_batch_time_window", optInt);
                            edit.apply();
                            a.m2612c("loc_batch_time_window", Integer.toString(optInt));
                        }
                    }
                    if (jSONObject.has("loc_batch_size_thresh") && !jSONObject.isNull("loc_batch_size_thresh")) {
                        optInt = jSONObject.optInt("loc_batch_size_thresh");
                        a = this.f1901a.m2528b();
                        if (optInt > 0 && optInt <= 200) {
                            edit = a.m2629n().getSharedPreferences("AMS_SDK_CONFIG", 0).edit();
                            edit.putInt("loc_batch_size_thresh", optInt);
                            edit.apply();
                            a.m2612c("loc_batch_size_thresh", Integer.toString(optInt));
                        }
                    }
                    if (jSONObject.has("loc_batch_size_limit") && !jSONObject.isNull("loc_batch_size_limit")) {
                        optInt = jSONObject.optInt("loc_batch_size_limit");
                        a = this.f1901a.m2528b();
                        if (optInt > 0 && optInt <= 1000) {
                            edit = a.m2629n().getSharedPreferences("AMS_SDK_CONFIG", 0).edit();
                            edit.putInt("loc_batch_size_limit", optInt);
                            edit.apply();
                            a.m2612c("loc_batch_size_limit", Integer.toString(optInt));
                        }
                    }
                    if (jSONObject.has("loc_batch_acc_filter") && !jSONObject.isNull("loc_batch_acc_filter")) {
                        optInt = jSONObject.optInt("loc_batch_acc_filter");
                        a = this.f1901a.m2528b();
                        if (optInt >= 0 && optInt <= 5000) {
                            edit = a.m2629n().getSharedPreferences("AMS_SDK_CONFIG", 0).edit();
                            edit.putInt("loc_batch_acc_filter", optInt);
                            edit.apply();
                            a.m2612c("loc_batch_acc_filter", Integer.toString(optInt));
                        }
                    }
                    if (jSONObject.has("loc_batch_acc_filter_activation_count") && !jSONObject.isNull("loc_batch_acc_filter_activation_count")) {
                        optInt = jSONObject.optInt("loc_batch_acc_filter_activation_count");
                        a = this.f1901a.m2528b();
                        if (optInt >= 0 && optInt <= 200) {
                            edit = a.m2629n().getSharedPreferences("AMS_SDK_CONFIG", 0).edit();
                            edit.putInt("loc_batch_acc_filter_activation_count", optInt);
                            edit.apply();
                            a.m2612c("loc_batch_acc_filter_activation_count", Integer.toString(optInt));
                        }
                    }
                    if (jSONObject.has("loc_batch_days_expiry") && !jSONObject.isNull("loc_batch_days_expiry")) {
                        optInt = jSONObject.optInt("loc_batch_days_expiry");
                        a = this.f1901a.m2528b();
                        if (optInt > 0 && optInt <= 365) {
                            edit = a.m2629n().getSharedPreferences("AMS_SDK_CONFIG", 0).edit();
                            edit.putInt("loc_batch_days_expiry", optInt);
                            edit.apply();
                            a.m2612c("loc_batch_days_expiry", Integer.toString(optInt));
                        }
                    }
                    if (jSONObject.has("loc_batch_priority_windows") && !jSONObject.isNull("loc_batch_priority_windows")) {
                        this.f1901a.m2528b().m2601a(jSONObject.optJSONArray("loc_batch_priority_windows"));
                    }
                    if (jSONObject.has("blt_block_unit") && !jSONObject.isNull("blt_block_unit")) {
                        optInt = jSONObject.optInt("blt_block_unit");
                        a = this.f1901a.m2528b();
                        if (optInt >= 0 && optInt <= 86400) {
                            edit = a.m2629n().getSharedPreferences("AMS_SDK_CONFIG", 0).edit();
                            edit.putInt("blt_block_unit", optInt * 1000);
                            edit.apply();
                            a.m2612c("blt_block_unit", Integer.toString(optInt));
                        }
                    }
                    if (jSONObject.has("blt_start_counter") && !jSONObject.isNull("blt_start_counter")) {
                        optInt = jSONObject.optInt("blt_start_counter");
                        a = this.f1901a.m2528b();
                        if (optInt > 0 && optInt <= 200) {
                            edit = a.m2629n().getSharedPreferences("AMS_SDK_CONFIG", 0).edit();
                            edit.putInt("blt_start_counter", optInt);
                            edit.apply();
                            a.m2612c("blt_start_counter", Integer.toString(optInt));
                        }
                    }
                    if (jSONObject.has("fg_bscan_period") && !jSONObject.isNull("fg_bscan_period")) {
                        this.f1901a.m2528b().m2595a(jSONObject.optInt("fg_bscan_period"));
                    }
                    if (jSONObject.has("fg_bscan_wait") && !jSONObject.isNull("fg_bscan_wait")) {
                        this.f1901a.m2528b().m2604b(jSONObject.optInt("fg_bscan_wait"));
                    }
                    if (jSONObject.has("bg_bscan_period") && !jSONObject.isNull("bg_bscan_period")) {
                        this.f1901a.m2528b().m2610c(jSONObject.optInt("bg_bscan_period"));
                    }
                    if (jSONObject.has("bg_bscan_wait") && !jSONObject.isNull("bg_bscan_wait")) {
                        this.f1901a.m2528b().m2614d(jSONObject.optInt("bg_bscan_wait"));
                    }
                    if (jSONObject.has("fc_token") && !jSONObject.isNull("fc_token")) {
                        optString = jSONObject.optString("fc_token");
                        if (optString != null && optString.length() > 0) {
                            C1367d.m2481a(this.f1901a, optString);
                        } else if (optString == null || optString.isEmpty()) {
                            C1367d.m2499k(this.f1901a);
                        }
                    }
                    if (jSONObject.has("papi_url") && !jSONObject.isNull("papi_url")) {
                        this.f1901a.m2528b().m2620f(jSONObject.optString("papi_url"));
                    }
                    if (jSONObject.has("mm_word") && !jSONObject.isNull("mm_word")) {
                        this.f1901a.m2528b().m2622g(jSONObject.optString("mm_word"));
                    }
                    if (jSONObject.has("ampin_enabled") && !jSONObject.isNull("ampin_enabled")) {
                        optBoolean = jSONObject.optBoolean("ampin_enabled");
                        a = this.f1901a.m2528b();
                        edit = a.m2629n().getSharedPreferences("AMS_SDK_CONFIG", 0).edit();
                        edit.putBoolean("ampin_enabled", optBoolean);
                        edit.apply();
                        a.m2612c("ampin_enabled", Boolean.toString(optBoolean));
                    }
                    if (jSONObject.has("fcpin_enabled") && !jSONObject.isNull("fcpin_enabled")) {
                        optBoolean = jSONObject.optBoolean("fcpin_enabled");
                        a = this.f1901a.m2528b();
                        edit = a.m2629n().getSharedPreferences("AMS_SDK_CONFIG", 0).edit();
                        edit.putBoolean("fcpin_enabled", optBoolean);
                        edit.apply();
                        a.m2612c("fcpin_enabled", Boolean.toString(optBoolean));
                    }
                    if (jSONObject.has("gdpr_date") && !jSONObject.isNull("gdpr_date")) {
                        long optLong = jSONObject.optLong("gdpr_date");
                        a2 = this.f1901a.m2528b();
                        edit2 = a2.m2629n().getSharedPreferences("AMS_SDK_CONFIG", 0).edit();
                        edit2.putLong("gdpr_date", optLong);
                        edit2.apply();
                        a2.m2612c("gdpr_date", Long.toString(optLong));
                    }
                    if (jSONObject.has("gdpr_countries") && !jSONObject.isNull("gdpr_countries")) {
                        this.f1901a.m2528b().m2608b(jSONObject.optJSONArray("gdpr_countries"));
                    }
                    if (jSONObject.has("resend_user_data") && !jSONObject.isNull("resend_user_data") && jSONObject.optBoolean("resend_user_data")) {
                        this.f1901a.m2528b().m2627l();
                    }
                    this.f1901a.m2511q();
                } catch (JSONException e) {
                    onFailure(i, headerArr, str.getBytes(), new Throwable("Bad JSON in sdk_config response"));
                }
            } else if (this.f1902b.contains("v3/user_stats")) {
                Set set = (Set) this.f1903c.get("multistat[]");
                if (set != null) {
                    this.f1901a.f1917k.mo2165a(set);
                }
                this.f1901a.m2511q();
            } else if (this.f1902b.contains("v3/loc_batch")) {
                if (m2471a()) {
                    this.f1901a.m2507o();
                    C1367d.m2480a(this.f1901a, this.f1901a.f1928v, this.f1901a.f1930x, this.f1901a.f1929w, this.f1901a.f1931y);
                    this.f1901a.f1928v = 0;
                    this.f1901a.f1930x = 0;
                    this.f1901a.f1929w = 0;
                    this.f1901a.f1931y = 0;
                }
                this.f1901a.m2511q();
            } else if (this.f1903c.containsKey("jsonData")) {
                this.f1901a.f1923q = false;
                C1378g a3 = this.f1901a.m2528b();
                a3.f1989a = 0;
                a3.f1990b = 0;
                a3.m2629n().getSharedPreferences("AMS_FC_PARSED_PERSONA", 0).edit().clear().apply();
                a3.m2629n().getSharedPreferences("AMS_FC_PARSED_PERSONA_METADATA", 0).edit().clear().apply();
                if (str.length() > 0) {
                    try {
                        jSONObject = new JSONObject(str);
                        if (jSONObject.has("sdk_config_refresh_date") && !jSONObject.isNull("sdk_config_refresh_date")) {
                            this.f1901a.m2528b().m2605b(jSONObject.getLong("sdk_config_refresh_date"));
                        }
                    } catch (JSONException e2) {
                    }
                }
                this.f1901a.m2511q();
            } else {
                if (this.f1902b.contains(".com/v2/person.json?")) {
                    m2470a(i, headerArr, str);
                }
                this.f1901a.m2511q();
            }
        }

        public final void setUsePoolThread(boolean z) {
            super.setUsePoolThread(true);
        }
    }

    C1367d(C1361a c1361a) {
        this.f1917k = c1361a;
        String str = "release".equals("debug") ? "release".equals(ImagesContract.LOCAL) ? "http://localhost:3000/" : "https://stageapi.areametrics.com/" : "https://api.areametrics.com/";
        this.f1908b = str;
        this.f1911e = Collections.synchronizedList(new ArrayList());
        this.f1915i = new Handler();
        this.f1913g = new AsyncHttpClient();
        this.f1909c.setTimeZone(TimeZone.getTimeZone("UTC"));
        this.f1912f = 0;
        this.f1916j = new C1338a(this);
    }

    private String m2478a(long j) {
        return this.f1909c.format(new Date(j));
    }

    private Map<String, Object> m2479a(Set<String> set) {
        JSONObject jSONObject;
        List arrayList = new ArrayList();
        for (String jSONObject2 : set) {
            try {
                arrayList.add(new JSONObject(jSONObject2));
            } catch (JSONException e) {
            }
        }
        int intValue = ((Integer) m2505n().get("LOCS_SINCE_UTC")).intValue();
        int i = m2528b().m2629n().getSharedPreferences("AMS_SDK_CONFIG", 0).getInt("loc_batch_acc_filter_activation_count", "release".equals("debug") ? 10 : 60);
        C1378g b = m2528b();
        "release".equals("debug");
        int i2 = b.m2629n().getSharedPreferences("AMS_SDK_CONFIG", 0).getInt("loc_batch_acc_filter", 0);
        Object obj = (intValue < i || i2 <= 0) ? null : 1;
        int i3 = 0;
        int i4 = 0;
        Set hashSet = new HashSet();
        Set<Set> hashSet2 = new HashSet();
        Set hashSet3 = new HashSet();
        long currentTimeMillis = System.currentTimeMillis() - ((((long) m2528b().m2619f()) * 86400) * 1000);
        int i5 = 0;
        while (i5 < arrayList.size()) {
            if (!hashSet3.contains(Integer.valueOf(i5))) {
                jSONObject = (JSONObject) arrayList.get(i5);
                if (obj == null || (jSONObject.has("acc") && jSONObject.optDouble("acc") <= ((double) i2))) {
                    long optLong = jSONObject.optLong("time");
                    if (1000 * optLong < currentTimeMillis) {
                        i = i4 + 1;
                        intValue = i3;
                    } else {
                        Set hashSet4 = new HashSet();
                        for (int i6 = i5 + 1; i6 < arrayList.size(); i6++) {
                            if (!hashSet3.contains(Integer.valueOf(i6))) {
                                JSONObject jSONObject3 = (JSONObject) arrayList.get(i6);
                                if (optLong == jSONObject3.optLong("time")) {
                                    hashSet4.add(jSONObject3);
                                    hashSet3.add(Integer.valueOf(i6));
                                }
                            }
                        }
                        if (hashSet4.size() > 0) {
                            hashSet4.add(jSONObject);
                            hashSet2.add(hashSet4);
                            i = i4;
                            intValue = i3;
                        } else {
                            hashSet.add(jSONObject.toString());
                        }
                    }
                    i5++;
                    i4 = i;
                    i3 = intValue;
                } else {
                    intValue = i3 + 1;
                    i = i4;
                    i5++;
                    i4 = i;
                    i3 = intValue;
                }
            }
            i = i4;
            intValue = i3;
            i5++;
            i4 = i;
            i3 = intValue;
        }
        int i7 = 0;
        for (Set<JSONObject> set2 : hashSet2) {
            JSONObject jSONObject4 = null;
            for (JSONObject jSONObject5 : set2) {
                String optString = jSONObject5.optString("type");
                if (optString.equals("bp")) {
                    break;
                }
                if (!(jSONObject4 == null || optString.equals("ao"))) {
                    jSONObject5 = jSONObject4;
                }
                jSONObject4 = jSONObject5;
            }
            jSONObject5 = jSONObject4;
            if (jSONObject5 != null) {
                hashSet.add(jSONObject5.toString());
                i = (set2.size() - 1) + i7;
            } else {
                i = i7;
            }
            i7 = i;
        }
        Map<String, Object> hashMap = new HashMap();
        hashMap.put("dupeDropped", Integer.valueOf(i7));
        hashMap.put("accFiltered", Integer.valueOf(i3));
        hashMap.put("ageDropped", Integer.valueOf(i4));
        hashMap.put("stringSet", hashSet);
        return hashMap;
    }

    static /* synthetic */ void m2480a(C1367d c1367d, int i, int i2, int i3, int i4) {
        Map n = c1367d.m2505n();
        int intValue = ((Integer) n.get("LOCS_SINCE_UTC")).intValue();
        int intValue2 = ((Integer) n.get("DUPES_DROPPED_SINCE_UTC")).intValue();
        int intValue3 = ((Integer) n.get("ACC_FILTERED_SINCE_UTC")).intValue();
        int i5 = intValue + i;
        intValue = intValue2 + i2;
        intValue2 = intValue3 + i3;
        int intValue4 = ((Integer) n.get("AGE_DROPPED_SINCE_UTC")).intValue() + i4;
        Editor edit = c1367d.m2529c().getSharedPreferences("AMS_LOC_BATCH", 0).edit();
        edit.putInt("LOCS_SINCE_UTC", i5);
        edit.putInt("DUPES_DROPPED_SINCE_UTC", intValue);
        edit.putInt("ACC_FILTERED_SINCE_UTC", intValue2);
        edit.putInt("AGE_DROPPED_SINCE_UTC", intValue4);
        edit.putLong("PREV_UPLOAD", System.currentTimeMillis());
        edit.apply();
    }

    static /* synthetic */ void m2481a(C1367d c1367d, String str) {
        Editor edit = c1367d.m2529c().getSharedPreferences("AMS_TOKEN_DATA", 0).edit();
        edit.putString("FC_TOKEN", str);
        edit.apply();
    }

    private void m2483a(String str, RequestParams requestParams, int i, Map<String, Object> map) {
        this.f1913g.setMaxRetriesAndTimeout(3, 12000);
        this.f1913g.removeAllHeaders();
        SSLSocketFactory k = m2498k();
        if (k != null) {
            this.f1913g.setSSLSocketFactory(k);
            if (map.containsKey("jsonData")) {
                HttpEntity httpEntity = null;
                String str2 = str + "?access_token=" + m2513r();
                String str3 = str2;
                for (Entry entry : map.entrySet()) {
                    String str4 = (String) entry.getKey();
                    if (str4.equals("jsonData")) {
                        httpEntity = (StringEntity) entry.getValue();
                    } else {
                        str3 = !str4.equals("access_token") ? str3 + "&" + str4 + "=" + Uri.encode((String) entry.getValue()) : str3;
                    }
                }
                this.f1913g.post(m2529c(), this.f1908b + str3, httpEntity, RequestParams.APPLICATION_JSON, new C1366b(this, str, map, i));
            } else if (str.contains("v3/user_stats/new")) {
                this.f1913g.post(this.f1908b + str, requestParams, new C1366b(this, str, map, i));
            } else if (str.contains("v3/loc_batch")) {
                this.f1913g.addHeader("Token", m2513r());
                this.f1913g.post(m2529c(), this.f1908b + (i > 0 ? str + "?retry=" + i : str), (StringEntity) map.get("fullLocBatchJSON"), RequestParams.APPLICATION_JSON, new C1366b(this, str, map, i));
            } else {
                this.f1913g.get(this.f1908b + str, requestParams, new C1366b(this, str, map, i));
            }
        } else if (this.f1921o) {
            this.f1921o = false;
        }
    }

    private void m2484a(Map<String, Object> map) {
        if (this.f1911e.size() >= this.f1910d) {
            this.f1911e.remove(0);
        }
        this.f1911e.add(map);
        if (m2530d()) {
            m2511q();
        } else if (!m2503m()) {
            this.f1914h = new C13586(this);
            m2529c().registerReceiver(this.f1914h, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        }
    }

    private void m2485a(Map<String, Object> map, Location location) {
        map.put("timestamp", m2478a(location.getTime()));
        map.put("latitude", String.valueOf(location.getLatitude()));
        map.put("longitude", String.valueOf(location.getLongitude()));
        if (location.hasAccuracy()) {
            map.put("accuracy", String.valueOf(location.getAccuracy()));
        }
        if (location.hasAltitude()) {
            map.put("altitude", String.valueOf(location.getAltitude()));
        }
        if (location.hasBearing()) {
            map.put("bearing", String.valueOf(location.getBearing()));
        }
        if (location.hasSpeed()) {
            map.put("speed", String.valueOf(location.getSpeed()));
        }
    }

    private void m2486a(Set<String> set, boolean z) {
        if (set != null && !this.f1926t) {
            this.f1926t = true;
            this.f1927u = 0;
            try {
                JSONObject jSONObject = new JSONObject();
                Map a = m2479a((Set) set);
                Set<String> set2 = (Set) a.get("stringSet");
                if (set2.size() == 0) {
                    this.f1926t = false;
                    return;
                }
                String string;
                JSONArray jSONArray = new JSONArray();
                for (String string2 : set2) {
                    jSONArray.put(new JSONObject(string2));
                }
                jSONObject.put("batch", jSONArray);
                Map n = m2505n();
                int intValue = ((Integer) a.get("accFiltered")).intValue();
                int intValue2 = ((Integer) a.get("dupeDropped")).intValue();
                int intValue3 = ((Integer) a.get("ageDropped")).intValue();
                int intValue4 = intValue + ((Integer) n.get("ACC_FILTERED_SINCE_UTC")).intValue();
                int intValue5 = intValue2 + ((Integer) n.get("DUPES_DROPPED_SINCE_UTC")).intValue();
                int intValue6 = intValue3 + ((Integer) n.get("AGE_DROPPED_SINCE_UTC")).intValue();
                int intValue7 = ((Integer) n.get("LOCS_SINCE_UTC")).intValue() + jSONArray.length();
                this.f1928v = jSONArray.length();
                this.f1929w = intValue;
                this.f1930x = intValue2;
                this.f1931y = intValue3;
                if (intValue7 > 0) {
                    jSONObject.put("locs_utc", intValue7);
                }
                if (intValue > 0) {
                    jSONObject.put("acc_filtered_batch", intValue);
                }
                if (intValue4 > 0) {
                    jSONObject.put("acc_filtered_utc", intValue4);
                }
                if (intValue2 > 0) {
                    jSONObject.put("dupes_dropped_batch", intValue2);
                }
                if (intValue5 > 0) {
                    jSONObject.put("dupes_dropped_utc", intValue5);
                }
                if (intValue3 > 0) {
                    jSONObject.put("age_dropped_batch", intValue3);
                }
                if (intValue6 > 0) {
                    jSONObject.put("age_dropped_utc", intValue6);
                }
                jSONObject.put("app_id", AreaMetricsSDK.INSTANCE.getAppID());
                jSONObject.put("user_uuid", m2528b().m2625i());
                jSONObject.put("sdk_ver", C1373f.m2567a());
                if (z) {
                    jSONObject.put("blankData", 1);
                } else {
                    C1378g b = m2528b();
                    SharedPreferences sharedPreferences = b.m2629n().getSharedPreferences("AMS_User_Data", 0);
                    Object string3 = sharedPreferences.getString(C1378g.m2578e("usr_settings", "ad_id"), null);
                    String string4 = sharedPreferences.getString(C1378g.m2579f("usr_settings", "ad_id"), null);
                    if (string4 != null && string4.equals("AMS_VOID_VALUE")) {
                        string4 = null;
                    }
                    if (string3 == null && string4 == null) {
                        string2 = sharedPreferences.getString(C1378g.m2578e("usr_settings", "advertiser_id"), null);
                        string4 = sharedPreferences.getString(C1378g.m2579f("usr_settings", "advertiser_id"), null);
                        if (string4 != null && string4.equals("AMS_VOID_VALUE")) {
                            string4 = null;
                        }
                        if (string2 == null && string4 == null) {
                            string3 = null;
                        } else {
                            if (string2 != null) {
                                b.m2611c(string2);
                            } else {
                                b.m2611c(string4);
                            }
                            if (string2 == null) {
                                string2 = string4;
                            }
                        }
                    } else if (string3 == null) {
                        string2 = string4;
                    }
                    if (string3 != null) {
                        jSONObject.put("ad_id", string3);
                    }
                }
                jSONObject.put("os_ver", VERSION.RELEASE);
                m2528b();
                jSONObject.put("model", C1378g.m2582j());
                jSONObject.put("agent", System.getProperty("http.agent"));
                JSONArray jSONArray2 = jSONObject.getJSONArray("batch");
                intValue3 = m2528b().m2629n().getSharedPreferences("AMS_SDK_CONFIG", 0).getInt("loc_batch_size_limit", "release".equals("debug") ? 2 : 25);
                StringEntity stringEntity;
                if (jSONArray2.length() > intValue3) {
                    List<JSONArray> arrayList = new ArrayList();
                    int length = jSONArray2.length();
                    int i = 0;
                    while (length > 0) {
                        intValue7 = length < intValue3 ? length : intValue3;
                        JSONArray jSONArray3 = new JSONArray();
                        for (int i2 = i; i2 < i + intValue7; i2++) {
                            jSONArray3.put(jSONArray2.getJSONObject(i2));
                        }
                        intValue7 = jSONArray3.length() + i;
                        length -= jSONArray3.length();
                        arrayList.add(jSONArray3);
                        i = intValue7;
                    }
                    for (JSONArray jSONArray4 : arrayList) {
                        JSONObject jSONObject2 = new JSONObject(jSONObject.toString());
                        jSONObject2.put("batch", jSONArray4);
                        stringEntity = new StringEntity(jSONObject2.toString());
                        a = new HashMap();
                        a.put("myurl", "v3/loc_batch");
                        a.put("fullLocBatchJSON", stringEntity);
                        this.f1927u++;
                        m2484a(a);
                    }
                    return;
                }
                stringEntity = new StringEntity(jSONObject.toString());
                Map hashMap = new HashMap();
                hashMap.put("myurl", "v3/loc_batch");
                hashMap.put("fullLocBatchJSON", stringEntity);
                this.f1927u++;
                m2484a(hashMap);
            } catch (Exception e) {
                this.f1927u = 0;
                this.f1926t = false;
            }
        }
    }

    private boolean m2487a(Date date) {
        C1378g b = m2528b();
        Set hashSet = new HashSet();
        if ("release".equals("debug")) {
            hashSet.add("0-3600");
            hashSet.add("28800-32400");
            hashSet.add("57600-61200");
            hashSet.add("82800-86400");
        } else {
            hashSet.add("0-3600");
            hashSet.add("28800-32400");
            hashSet.add("57600-61200");
            hashSet.add("82800-86400");
        }
        Set<String> stringSet = b.m2629n().getSharedPreferences("AMS_SDK_CONFIG", 0).getStringSet("loc_batch_priority_windows", hashSet);
        long j = m2529c().getSharedPreferences("AMS_LOC_BATCH", 0).getLong("PREV_UPLOAD", 0);
        Date date2 = j > 0 ? new Date(j) : null;
        Calendar instance = Calendar.getInstance(TimeZone.getTimeZone("UTC"), Locale.US);
        instance.setTime(date);
        instance.set(11, 0);
        instance.set(12, 0);
        instance.set(13, 0);
        instance.set(14, 0);
        j = instance.getTimeInMillis();
        for (String split : stringSet) {
            String[] split2 = split.split("-");
            long longValue = Long.valueOf(split2[1]).longValue() * 1000;
            Date date3 = new Date((Long.valueOf(split2[0]).longValue() * 1000) + j);
            Date date4 = new Date(j + longValue);
            if (!date.before(date3) && !date.after(date4)) {
                return date2 != null ? date2.before(date3) : true;
            }
        }
        return false;
    }

    static /* synthetic */ void m2489b(C1367d c1367d, Map map) {
        if (c1367d.f1911e.size() >= c1367d.f1910d) {
            c1367d.f1911e.remove(0);
        }
        c1367d.f1911e.add(0, map);
    }

    static /* synthetic */ void m2497j(C1367d c1367d) {
        Editor edit = c1367d.m2529c().getSharedPreferences("AMS_TOKEN_DATA", 0).edit();
        edit.putBoolean("first_config_received", true);
        edit.apply();
    }

    private SSLSocketFactory m2498k() {
        SSLSocketFactory sSLSocketFactory = null;
        if (m2528b().m2629n().getSharedPreferences("AMS_SDK_CONFIG", 0).getBoolean("ampin_enabled", true)) {
            if (this.f1932z != null) {
                return this.f1932z;
            }
            try {
                KeyStore instance = KeyStore.getInstance("BKS");
                instance.load(m2529c().getResources().openRawResource(C1335R.raw.ams), "atleastsix".toCharArray());
                this.f1932z = new SSLSocketFactory(instance);
                this.f1932z.setHostnameVerifier(SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
                return this.f1932z;
            } catch (Exception e) {
                this.f1932z = sSLSocketFactory;
                return sSLSocketFactory;
            }
        } else if (this.f1907B != null) {
            return this.f1907B;
        } else {
            this.f1907B = MySSLSocketFactory.getFixedSocketFactory();
            return this.f1907B;
        }
    }

    static /* synthetic */ void m2499k(C1367d c1367d) {
        Editor edit = c1367d.m2529c().getSharedPreferences("AMS_TOKEN_DATA", 0).edit();
        edit.remove("FC_TOKEN");
        edit.apply();
        c1367d.m2528b().m2620f(null);
    }

    private SSLSocketFactory m2501l() {
        SSLSocketFactory sSLSocketFactory = null;
        if (m2528b().m2629n().getSharedPreferences("AMS_SDK_CONFIG", 0).getBoolean("fcpin_enabled", true)) {
            if (this.f1906A != null) {
                return this.f1906A;
            }
            try {
                KeyStore instance = KeyStore.getInstance("BKS");
                instance.load(m2529c().getResources().openRawResource(C1335R.raw.amsfc), "atleastsix".toCharArray());
                this.f1906A = new SSLSocketFactory(instance);
                this.f1906A.setHostnameVerifier(SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
                return this.f1906A;
            } catch (Exception e) {
                this.f1906A = sSLSocketFactory;
                return sSLSocketFactory;
            }
        } else if (this.f1907B != null) {
            return this.f1907B;
        } else {
            this.f1907B = MySSLSocketFactory.getFixedSocketFactory();
            return this.f1907B;
        }
    }

    private boolean m2503m() {
        return this.f1914h != null;
    }

    private Map<String, Integer> m2505n() {
        int i;
        int i2;
        int i3;
        int i4 = 0;
        SharedPreferences sharedPreferences = m2529c().getSharedPreferences("AMS_LOC_BATCH", 0);
        long j = sharedPreferences.getLong("UTC_START", 0);
        Map<String, Integer> hashMap = new HashMap();
        Calendar instance = Calendar.getInstance(TimeZone.getTimeZone("UTC"), Locale.US);
        instance.set(11, 0);
        instance.set(12, 0);
        instance.set(13, 0);
        instance.set(14, 0);
        long timeInMillis = instance.getTimeInMillis();
        if (j < timeInMillis) {
            Editor edit = sharedPreferences.edit();
            edit.remove("LOCS_SINCE_UTC");
            edit.remove("ACC_FILTERED_SINCE_UTC");
            edit.remove("DUPES_DROPPED_SINCE_UTC");
            edit.remove("AGE_DROPPED_SINCE_UTC");
            edit.putLong("UTC_START", timeInMillis);
            edit.apply();
            i = 0;
            i2 = 0;
            i3 = 0;
        } else {
            i3 = sharedPreferences.getInt("ACC_FILTERED_SINCE_UTC", 0);
            i2 = sharedPreferences.getInt("DUPES_DROPPED_SINCE_UTC", 0);
            i = sharedPreferences.getInt("AGE_DROPPED_SINCE_UTC", 0);
            i4 = sharedPreferences.getInt("LOCS_SINCE_UTC", 0);
        }
        hashMap.put("ACC_FILTERED_SINCE_UTC", Integer.valueOf(i3));
        hashMap.put("DUPES_DROPPED_SINCE_UTC", Integer.valueOf(i2));
        hashMap.put("AGE_DROPPED_SINCE_UTC", Integer.valueOf(i));
        hashMap.put("LOCS_SINCE_UTC", Integer.valueOf(i4));
        return hashMap;
    }

    private void m2507o() {
        Editor edit = m2529c().getSharedPreferences("AMS_LOC_BATCH", 0).edit();
        edit.remove("AMS_LOC_BATCH");
        edit.apply();
    }

    private void m2509p() {
        SharedPreferences sharedPreferences = m2529c().getSharedPreferences("AMS_LOC_BATCH", 0);
        Set<String> stringSet = sharedPreferences.getStringSet("AMS_LOC_BATCH", new HashSet());
        List<JSONObject> arrayList = new ArrayList();
        for (String jSONObject : stringSet) {
            try {
                arrayList.add(new JSONObject(jSONObject));
            } catch (JSONException e) {
                m2507o();
                return;
            }
        }
        Set hashSet = new HashSet();
        long currentTimeMillis = System.currentTimeMillis() - ((((long) m2528b().m2619f()) * 86400) * 1000);
        for (JSONObject jSONObject2 : arrayList) {
            if (jSONObject2.optLong("time") > currentTimeMillis) {
                hashSet.add(jSONObject2.toString());
            }
        }
        Editor edit = sharedPreferences.edit();
        edit.putStringSet("AMS_LOC_BATCH", hashSet);
        edit.apply();
    }

    private void m2511q() {
        while (!this.f1920n && m2530d()) {
            if (m2503m()) {
                try {
                    if (this.f1914h != null) {
                        m2529c().unregisterReceiver(this.f1914h);
                    }
                } catch (IllegalArgumentException e) {
                }
                this.f1914h = null;
            }
            int i = (((double) m2529c().getSharedPreferences("AMS_TOKEN_DATA", 0).getLong("TOKEN_EXPIRY", 0)) <= ((double) System.currentTimeMillis()) / 1000.0d || m2513r().length() == 0) ? 1 : 0;
            if (i != 0) {
                m2536j();
                return;
            } else if (this.f1911e.size() > 0) {
                Map map = (Map) this.f1911e.remove(0);
                if (map != null) {
                    String str = (String) map.remove("myurl");
                    if (str != null) {
                        Integer num = (Integer) map.remove("submitCount");
                        int intValue = num != null ? num.intValue() : 0;
                        String r = m2513r();
                        RequestParams requestParams = new RequestParams();
                        if (!map.containsKey("fullLocBatchJSON")) {
                            requestParams.put("access_token", r);
                        }
                        for (Entry entry : map.entrySet()) {
                            if (!(entry.getValue() == null || ((String) entry.getKey()).equals("jsonData") || ((String) entry.getKey()).equals("fullLocBatchJSON"))) {
                                requestParams.put((String) entry.getKey(), entry.getValue());
                            }
                        }
                        m2483a(str, requestParams, intValue, map);
                        return;
                    }
                    return;
                }
            } else {
                return;
            }
        }
    }

    private String m2513r() {
        return m2529c().getSharedPreferences("AMS_TOKEN_DATA", 0).getString("TOKEN_STRING", "");
    }

    public final void mo2160a() {
        SharedPreferences sharedPreferences = m2529c().getSharedPreferences("AMS_LOC_BATCH", 0);
        Set stringSet = sharedPreferences.getStringSet("AMS_LOC_BATCH", new HashSet());
        boolean z = sharedPreferences.getBoolean("AMS_LOC_BATCH_GDPR", false);
        m2509p();
        if (stringSet.size() > 0) {
            m2486a(stringSet, z);
        }
    }

    final void m2523a(final Location location, final String str) {
        m2528b().m2597a(location, new C1352c(this) {
            final /* synthetic */ C1367d f1893c;

            public final void mo2159a(Map<String, String> map) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("type", str);
                    jSONObject.put("time", (long) (((double) location.getTime()) / 1000.0d));
                    jSONObject.put(C1048i.ko, location.getLatitude());
                    jSONObject.put("lng", location.getLongitude());
                    if (location.hasAccuracy()) {
                        jSONObject.put("acc", (double) location.getAccuracy());
                    }
                    if (location.hasSpeed()) {
                        jSONObject.put("speed", (double) location.getSpeed());
                    }
                    if (location.hasAltitude()) {
                        jSONObject.put("altitude", location.getAltitude());
                    }
                    if (location.hasBearing()) {
                        jSONObject.put("bearing", (double) location.getBearing());
                    }
                    if (map.containsKey("zip")) {
                        jSONObject.put("zip", map.get("zip"));
                    }
                    if (map.containsKey("city")) {
                        jSONObject.put("city", map.get("city"));
                    }
                    if (map.containsKey("state")) {
                        jSONObject.put("state", map.get("state"));
                    }
                    String str = null;
                    if (map.containsKey("country")) {
                        jSONObject.put("country", map.get("country"));
                        str = (String) map.get("country");
                    }
                    boolean h = this.f1893c.m2528b().m2624h(str);
                    SharedPreferences sharedPreferences = this.f1893c.m2529c().getSharedPreferences("AMS_LOC_BATCH", 0);
                    Set hashSet = new HashSet();
                    Collection stringSet = sharedPreferences.getStringSet("AMS_LOC_BATCH", hashSet);
                    if (stringSet.size() > 0) {
                        hashSet.addAll(stringSet);
                    }
                    hashSet.add(jSONObject.toString());
                    Editor edit = sharedPreferences.edit();
                    edit.putStringSet("AMS_LOC_BATCH", hashSet);
                    if (h) {
                        edit.putBoolean("AMS_LOC_BATCH_GDPR", h);
                    }
                    edit.apply();
                    this.f1893c.m2535i();
                } catch (JSONException e) {
                }
            }
        });
    }

    final void m2524a(C1370a c1370a) {
        final Map hashMap = new HashMap();
        hashMap.put("myurl", "v3/beacon_events/new");
        hashMap.put("app_id", AreaMetricsSDK.INSTANCE.getAppID());
        hashMap.put("user_uuid", m2528b().m2625i());
        hashMap.put("sdk_version", C1373f.m2567a());
        hashMap.put("os_ver", VERSION.RELEASE);
        m2528b();
        hashMap.put("model", C1378g.m2582j());
        hashMap.put("agent", System.getProperty("http.agent"));
        hashMap.put("beacon_uuid", c1370a.f1935a.getId1().toString());
        hashMap.put("major", c1370a.f1935a.getId2().toString());
        hashMap.put("minor", c1370a.f1935a.getId3().toString());
        if (c1370a.f1947m != null) {
            hashMap.put("btype", c1370a.f1947m);
        }
        hashMap.put("enter_time", m2478a(c1370a.f1936b));
        hashMap.put("exit_time", m2478a(c1370a.f1937c));
        hashMap.put("dwell_duration", String.valueOf((long) (((double) Math.abs(c1370a.f1937c - c1370a.f1936b)) / 1000.0d)));
        hashMap.put("num_enters", String.valueOf(c1370a.f1938d));
        hashMap.put("num_exits", String.valueOf(c1370a.f1939e));
        hashMap.put("num_ranges", String.valueOf(c1370a.f1940f));
        hashMap.put("join_duration", String.valueOf((long) (((double) c1370a.f1941g) / 1000.0d)));
        hashMap.put("num_rssi_samples", String.valueOf(c1370a.f1945k));
        hashMap.put("max_rssi", String.valueOf(c1370a.f1942h));
        hashMap.put("min_rssi", String.valueOf(c1370a.f1943i));
        hashMap.put("avg_rssi", String.valueOf(c1370a.f1944j));
        hashMap.put("measured_power", String.valueOf(c1370a.f1946l));
        hashMap.put("num_coords", String.valueOf(c1370a.f1952r.size()));
        hashMap.put("exit_reason", "se");
        Location location = c1370a.f1950p;
        if (location != null) {
            m2485a(hashMap, location);
            m2523a(location, "bp");
        } else {
            location = null;
        }
        Location location2 = c1370a.f1951q;
        if (location2 != null) {
            hashMap.put("best_timestamp", m2478a(location2.getTime()));
            hashMap.put("best_latitude", String.valueOf(location2.getLatitude()));
            hashMap.put("best_longitude", String.valueOf(location2.getLongitude()));
            if (location2.hasAccuracy()) {
                hashMap.put("best_accuracy", String.valueOf(location2.getAccuracy()));
            }
            if (location2.hasAltitude()) {
                hashMap.put("best_altitude", String.valueOf(location2.getAltitude()));
            }
            if (location2.hasBearing()) {
                hashMap.put("best_bearing", String.valueOf(location2.getBearing()));
            }
            if (location2.hasSpeed()) {
                hashMap.put("best_speed", String.valueOf(location2.getSpeed()));
            }
            m2523a(location2, "bp");
            location = location2;
        }
        location2 = c1370a.m2538a();
        if (location2 != null) {
            hashMap.put("avg_latitude", String.valueOf(location2.getLatitude()));
            hashMap.put("avg_longitude", String.valueOf(location2.getLongitude()));
            if (location2.hasAccuracy()) {
                hashMap.put("avg_accuracy", String.valueOf(location2.getAccuracy()));
            }
            location = location2;
        }
        m2528b().m2597a(location, new C1352c(this) {
            final /* synthetic */ C1367d f1888b;

            public final void mo2159a(Map<String, String> map) {
                hashMap.putAll(map);
                this.f1888b.m2484a(hashMap);
            }
        });
    }

    final void m2525a(String str, long j) {
        Editor edit = m2529c().getSharedPreferences("AMS_TOKEN_DATA", 0).edit();
        edit.putString("TOKEN_STRING", str);
        edit.putLong("TOKEN_EXPIRY", j);
        edit.apply();
    }

    final void m2526a(Set<String> set, Location location, int i) {
        Location location2 = null;
        int i2 = 1;
        if (set != null && set.size() == 0) {
            if (System.currentTimeMillis() - m2529c().getSharedPreferences("AMS_USER_STATS", 0).getLong("date", 0) < ((long) m2528b().m2629n().getSharedPreferences("AMS_SDK_CONFIG", 0).getInt("us_time_filter", "release".equals("debug") ? 15 : HttpStatus.SC_MULTIPLE_CHOICES)) * 1000) {
                SharedPreferences sharedPreferences = m2529c().getSharedPreferences("AMS_USER_STATS", 0);
                String string = sharedPreferences.getString("latitude", null);
                if (string != null) {
                    Location location3 = new Location("cache");
                    location3.setLatitude(Double.parseDouble(string));
                    location3.setLongitude(Double.parseDouble(sharedPreferences.getString("longitude", null)));
                    float f = sharedPreferences.getFloat("accuracy", -1.0f);
                    if (f >= 0.0f) {
                        location3.setAccuracy(f);
                    }
                    location2 = location3;
                }
                if (location == null || location2 != null) {
                    int i3;
                    if (location != null) {
                        i3 = location.distanceTo(location2) >= m2528b().m2629n().getSharedPreferences("AMS_SDK_CONFIG", 0).getFloat("us_locdiff_filter", "release".equals("debug") ? 5.0f : BitmapDescriptorFactory.HUE_ORANGE) ? 1 : 0;
                        float f2 = m2528b().m2629n().getSharedPreferences("AMS_SDK_CONFIG", 0).getFloat("us_accthresh_filter", BitmapDescriptorFactory.HUE_ORANGE);
                        if (location2.hasAccuracy() && location2.getAccuracy() > f2 && location.hasAccuracy() && location.getAccuracy() <= f2) {
                            i3 = 1;
                        }
                    } else {
                        i3 = 0;
                    }
                    i2 = i3;
                }
            }
        }
        if (i2 != 0) {
            Editor edit = m2529c().getSharedPreferences("AMS_USER_STATS", 0).edit();
            edit.putLong("date", System.currentTimeMillis());
            if (location != null) {
                edit.putString("latitude", String.valueOf(location.getLatitude()));
                edit.putString("longitude", String.valueOf(location.getLongitude()));
                if (location.hasAccuracy()) {
                    edit.putFloat("accuracy", location.getAccuracy());
                } else {
                    edit.remove("accuracy");
                }
            } else {
                edit.remove("latitude");
                edit.remove("longitude");
                edit.remove("accuracy");
            }
            edit.apply();
            final Map hashMap = new HashMap();
            hashMap.put("myurl", "v3/user_stats/new");
            hashMap.put("app_id", AreaMetricsSDK.INSTANCE.getAppID());
            hashMap.put("user_uuid", m2528b().m2625i());
            hashMap.put("sdk_version", C1373f.m2567a());
            hashMap.put("os_ver", VERSION.RELEASE);
            m2528b();
            hashMap.put("model", C1378g.m2582j());
            hashMap.put("agent", System.getProperty("http.agent"));
            if (i == C1380a.f1999d) {
                hashMap.put("type", "ud");
                if (location != null) {
                    m2523a(location, "ud");
                }
            } else {
                hashMap.put("type", "ao");
                if (location != null) {
                    m2523a(location, "ao");
                }
            }
            if (location != null) {
                m2485a(hashMap, location);
            } else {
                hashMap.put("timestamp", m2478a(System.currentTimeMillis()));
            }
            hashMap.put("multistat[]", set);
            m2528b().m2597a(location, new C1352c(this) {
                final /* synthetic */ C1367d f1890b;

                public final void mo2159a(Map<String, String> map) {
                    hashMap.putAll(map);
                    this.f1890b.m2484a(hashMap);
                }
            });
        }
    }

    final void m2527a(Region region, long j, Location location, boolean z, String str) {
        Object obj;
        Editor edit;
        String str2;
        final Map hashMap;
        double d;
        double d2;
        float f;
        long currentTimeMillis;
        if (System.currentTimeMillis() < m2529c().getSharedPreferences("AMS_BEACON_STATS", 0).getLong((region.getUniqueId() + (z ? "ENTER" : "EXIT")) + "_time", 0) + (((long) m2528b().m2629n().getSharedPreferences("AMS_SDK_CONFIG", 0).getInt("bs_time_filter", "release".equals("debug") ? 15 : 60)) * 1000)) {
            Location location2;
            SharedPreferences sharedPreferences = m2529c().getSharedPreferences("AMS_BEACON_STATS", 0);
            String str3 = region.getUniqueId() + (z ? "ENTER" : "EXIT");
            String string = sharedPreferences.getString(str3 + "_latitude", null);
            if (string != null) {
                Location location3 = new Location("cache");
                location3.setLatitude(Double.parseDouble(string));
                location3.setLongitude(Double.parseDouble(sharedPreferences.getString(str3 + "_longitude", null)));
                float f2 = sharedPreferences.getFloat(str3 + "_accuracy", -1.0f);
                if (f2 >= 0.0f) {
                    location3.setAccuracy(f2);
                }
                location2 = location3;
            } else {
                location2 = null;
            }
            if (location == null || location2 != null) {
                if (location != null) {
                    obj = location.distanceTo(location2) >= m2528b().m2629n().getSharedPreferences("AMS_SDK_CONFIG", 0).getFloat("bs_locdiff_filter", "release".equals("debug") ? 5.0f : CloseButton.TEXT_SIZE_SP) ? 1 : null;
                    float f3 = m2528b().m2629n().getSharedPreferences("AMS_SDK_CONFIG", 0).getFloat("bs_accthresh_filter", BitmapDescriptorFactory.HUE_ORANGE);
                    if (location2.hasAccuracy() && location2.getAccuracy() > f3 && location.hasAccuracy() && location.getAccuracy() <= f3) {
                        obj = 1;
                    }
                } else {
                    obj = null;
                }
                if (obj != null) {
                    edit = m2529c().getSharedPreferences("AMS_BEACON_STATS", 0).edit();
                    str2 = region.getUniqueId() + (z ? "ENTER" : "EXIT");
                    edit.putLong(str2 + "_time", j);
                    if (location == null) {
                        edit.putString(str2 + "_latitude", String.valueOf(location.getLatitude()));
                        edit.putString(str2 + "_longitude", String.valueOf(location.getLongitude()));
                        if (location.hasAccuracy()) {
                            edit.remove(str2 + "_accuracy");
                        } else {
                            edit.putFloat(str2 + "_accuracy", location.getAccuracy());
                        }
                    } else {
                        edit.remove(str2 + "_latitude");
                        edit.remove(str2 + "_longitude");
                        edit.remove(str2 + "_accuracy");
                    }
                    edit.apply();
                    hashMap = new HashMap();
                    hashMap.put("myurl", "v3/beacon_stats/new");
                    hashMap.put("app_id", AreaMetricsSDK.INSTANCE.getAppID());
                    hashMap.put("user_uuid", m2528b().m2625i());
                    hashMap.put("sdk_version", C1373f.m2567a());
                    hashMap.put("os_ver", VERSION.RELEASE);
                    m2528b();
                    hashMap.put("model", C1378g.m2582j());
                    hashMap.put("agent", System.getProperty("http.agent"));
                    hashMap.put("direction", z ? "enter" : "exit");
                    hashMap.put(UserBox.TYPE, region.getId1().toString());
                    hashMap.put("major", region.getId2().toString());
                    hashMap.put("minor", region.getId3().toString());
                    if (str != null) {
                        hashMap.put("btype", str);
                    }
                    hashMap.put("beacon_timestamp", m2478a(j));
                    d = 0.0d;
                    d2 = 0.0d;
                    f = 0.0f;
                    currentTimeMillis = System.currentTimeMillis();
                    if (location != null) {
                        d = location.getLatitude();
                        d2 = location.getLongitude();
                        currentTimeMillis = location.getTime();
                        if (location.hasAccuracy()) {
                            f = location.getAccuracy();
                        }
                        m2523a(location, "bp");
                    }
                    long j2 = currentTimeMillis;
                    double d3 = d2;
                    d2 = d;
                    float f4 = f;
                    long j3 = j2;
                    hashMap.put("latitude", String.valueOf(d2));
                    hashMap.put("longitude", String.valueOf(d3));
                    hashMap.put("accuracy", String.valueOf(f4));
                    hashMap.put("timestamp", m2478a(j3));
                    m2528b().m2597a(location, new C1352c(this) {
                        final /* synthetic */ C1367d f1886b;

                        public final void mo2159a(Map<String, String> map) {
                            hashMap.putAll(map);
                            this.f1886b.m2484a(hashMap);
                        }
                    });
                }
            }
        }
        obj = 1;
        if (obj != null) {
            edit = m2529c().getSharedPreferences("AMS_BEACON_STATS", 0).edit();
            if (z) {
            }
            str2 = region.getUniqueId() + (z ? "ENTER" : "EXIT");
            edit.putLong(str2 + "_time", j);
            if (location == null) {
                edit.remove(str2 + "_latitude");
                edit.remove(str2 + "_longitude");
                edit.remove(str2 + "_accuracy");
            } else {
                edit.putString(str2 + "_latitude", String.valueOf(location.getLatitude()));
                edit.putString(str2 + "_longitude", String.valueOf(location.getLongitude()));
                if (location.hasAccuracy()) {
                    edit.remove(str2 + "_accuracy");
                } else {
                    edit.putFloat(str2 + "_accuracy", location.getAccuracy());
                }
            }
            edit.apply();
            hashMap = new HashMap();
            hashMap.put("myurl", "v3/beacon_stats/new");
            hashMap.put("app_id", AreaMetricsSDK.INSTANCE.getAppID());
            hashMap.put("user_uuid", m2528b().m2625i());
            hashMap.put("sdk_version", C1373f.m2567a());
            hashMap.put("os_ver", VERSION.RELEASE);
            m2528b();
            hashMap.put("model", C1378g.m2582j());
            hashMap.put("agent", System.getProperty("http.agent"));
            if (z) {
            }
            hashMap.put("direction", z ? "enter" : "exit");
            hashMap.put(UserBox.TYPE, region.getId1().toString());
            hashMap.put("major", region.getId2().toString());
            hashMap.put("minor", region.getId3().toString());
            if (str != null) {
                hashMap.put("btype", str);
            }
            hashMap.put("beacon_timestamp", m2478a(j));
            d = 0.0d;
            d2 = 0.0d;
            f = 0.0f;
            currentTimeMillis = System.currentTimeMillis();
            if (location != null) {
                d = location.getLatitude();
                d2 = location.getLongitude();
                currentTimeMillis = location.getTime();
                if (location.hasAccuracy()) {
                    f = location.getAccuracy();
                }
                m2523a(location, "bp");
            }
            long j22 = currentTimeMillis;
            double d32 = d2;
            d2 = d;
            float f42 = f;
            long j32 = j22;
            hashMap.put("latitude", String.valueOf(d2));
            hashMap.put("longitude", String.valueOf(d32));
            hashMap.put("accuracy", String.valueOf(f42));
            hashMap.put("timestamp", m2478a(j32));
            m2528b().m2597a(location, /* anonymous class already generated */);
        }
    }

    final C1378g m2528b() {
        return this.f1918l != null ? this.f1918l : AreaMetricsSDK.INSTANCE.getUserData();
    }

    protected final Context m2529c() {
        return this.f1919m != null ? this.f1919m : AreaMetricsSDK.INSTANCE.getContext();
    }

    final boolean m2530d() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) m2529c().getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    final void m2531e() {
        String string = m2529c().getSharedPreferences("AMS_TOKEN_DATA", 0).getString("FC_TOKEN", null);
        String string2 = m2528b().m2629n().getSharedPreferences("AMS_SDK_CONFIG", 0).getString("papi_url", null);
        String g = m2528b().m2621g();
        C1378g b = m2528b();
        b.m2615d("email");
        String string3 = b.m2629n().getSharedPreferences("AMS_FC_PARAM_STORE", 0).getString("email", null);
        int i = b.m2629n().getSharedPreferences("AMS_FC_CONSECUTIVE_404", 0).getInt("email", 0);
        if (string3 == null || i > 3) {
            string3 = null;
        }
        C1378g b2 = m2528b();
        b2.m2615d("emailMD5");
        String string4 = b2.m2629n().getSharedPreferences("AMS_FC_PARAM_STORE", 0).getString("emailMD5", null);
        int i2 = b2.m2629n().getSharedPreferences("AMS_FC_CONSECUTIVE_404", 0).getInt("emailMD5", 0);
        if (string4 == null || i2 > 3) {
            string4 = null;
        }
        C1378g b3 = m2528b();
        b3.m2615d("emailSHA256");
        String string5 = b3.m2629n().getSharedPreferences("AMS_FC_PARAM_STORE", 0).getString("emailSHA256", null);
        int i3 = b3.m2629n().getSharedPreferences("AMS_FC_CONSECUTIVE_404", 0).getInt("emailSHA256", 0);
        if (string5 == null || i3 > 3) {
            string5 = null;
        }
        C1378g b4 = m2528b();
        b4.m2615d("twitter");
        String string6 = b4.m2629n().getSharedPreferences("AMS_FC_PARAM_STORE", 0).getString("twitter", null);
        int i4 = b4.m2629n().getSharedPreferences("AMS_FC_CONSECUTIVE_404", 0).getInt("twitter", 0);
        if (string6 == null || i4 > 3) {
            string6 = null;
        }
        C1378g b5 = m2528b();
        b5.m2615d("phone");
        String string7 = b5.m2629n().getSharedPreferences("AMS_FC_PARAM_STORE", 0).getString("phone", null);
        int i5 = b5.m2629n().getSharedPreferences("AMS_FC_CONSECUTIVE_404", 0).getInt("phone", 0);
        if (string7 == null || i5 > 3) {
            string7 = null;
        }
        Object obj = System.currentTimeMillis() < m2528b().m2629n().getSharedPreferences("AMS_FC_PERSONA_WAIT", 0).getLong("wait", 0) ? 1 : null;
        if (obj != null) {
            m2528b().m2606b("Still on cooldown waiting to retry persona generation");
        }
        if (string == null && m2528b().m2593a() != null) {
            m2528b().m2606b("This install generated a persona recently and is not yet allowed to generate another. You may retrieve the previously generated persona by calling AreaMetricsSDK.INSTANCE.getCachedPersona(). You may uninstall and reinstall to circumvent this built-in rationing and generate a different persona");
        }
        if (!this.f1922p && obj == null && string != null && string2 != null && g != null) {
            if (string3 != null || string4 != null || string5 != null || string6 != null || string7 != null) {
                String str = string2 + "&apiKey=" + string;
                if (string3 != null) {
                    string3 = str + "&email=" + Uri.encode(string3);
                    m2528b().m2606b("Generating persona for entered email address");
                } else if (string4 != null) {
                    string3 = str + "&emailMD5=" + Uri.encode(string4);
                    m2528b().m2606b("Generating persona for entered emailMD5 address");
                } else if (string5 != null) {
                    string3 = str + "&emailSHA256=" + Uri.encode(string5);
                    m2528b().m2606b("Generating persona for entered emailSHA256 address");
                } else if (string6 != null) {
                    string3 = str + "&twitter=" + Uri.encode(string6);
                    m2528b().m2606b("Generating persona for entered twitter username");
                } else {
                    string3 = str + "&phone=" + Uri.encode(string7);
                    m2528b().m2606b("Generating persona for entered phone number");
                }
                Map hashMap = new HashMap();
                SSLSocketFactory l = m2501l();
                if (l != null) {
                    this.f1922p = true;
                    this.f1913g.removeAllHeaders();
                    this.f1913g.setSSLSocketFactory(l);
                    if (m2528b().m2617e() == C1376a.f1979a) {
                        m2528b().m2598a("Persona sharing is disabled. Please contact AreaMetrics if you are interested in receiving a copy of persona data.");
                    }
                    this.f1913g.get(string3, null, new C1366b(this, string3, hashMap, 0));
                }
            }
        }
    }

    final void m2532f() {
        JSONObject b = m2528b().m2603b();
        if (b != null && !this.f1923q) {
            this.f1923q = true;
            Map hashMap = new HashMap();
            hashMap.put("myurl", "v3/personas/new");
            hashMap.put("app_id", AreaMetricsSDK.INSTANCE.getAppID());
            hashMap.put("user_uuid", m2528b().m2625i());
            hashMap.put("sdk_version", C1373f.m2567a());
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("type", "fc");
                jSONObject.put("persona", b);
                jSONObject.put("metadata", m2528b().m2609c());
                hashMap.put("jsonData", new StringEntity(jSONObject.toString()));
                m2484a(hashMap);
            } catch (Exception e) {
                this.f1923q = false;
            }
        }
    }

    final void m2533g() {
        if (!this.f1921o && m2528b().m2629n().getSharedPreferences("AMS_SDK_CONFIG", 0).getLong("refresh_date", 0) * 1000 < System.currentTimeMillis()) {
            m2534h();
        }
    }

    final void m2534h() {
        if (!this.f1921o) {
            this.f1921o = true;
            final Map hashMap = new HashMap();
            hashMap.put("myurl", "v3/sdk_configs");
            hashMap.put("app_id", AreaMetricsSDK.INSTANCE.getAppID());
            hashMap.put("user_uuid", m2528b().m2625i());
            hashMap.put("sdk_version", C1373f.m2567a());
            if (!m2529c().getSharedPreferences("AMS_TOKEN_DATA", 0).getBoolean("first_config_received", false)) {
                hashMap.put("fresh", SchemaSymbols.ATTVAL_TRUE);
            }
            m2528b().m2597a(null, new C1352c(this) {
                final /* synthetic */ C1367d f1884b;

                public final void mo2159a(Map<String, String> map) {
                    hashMap.putAll(map);
                    this.f1884b.m2484a(hashMap);
                }
            });
        }
    }

    final void m2535i() {
        Set<String> stringSet = m2529c().getSharedPreferences("AMS_LOC_BATCH", 0).getStringSet("AMS_LOC_BATCH", new HashSet());
        if (!stringSet.isEmpty()) {
            long j;
            Object obj;
            List<JSONObject> arrayList = new ArrayList();
            for (String jSONObject : stringSet) {
                try {
                    arrayList.add(new JSONObject(jSONObject));
                } catch (JSONException e) {
                }
            }
            Calendar instance = Calendar.getInstance(TimeZone.getTimeZone("UTC"), Locale.US);
            instance.set(11, 0);
            instance.set(12, 0);
            instance.set(13, 0);
            instance.set(14, 0);
            long timeInMillis = instance.getTimeInMillis();
            long j2 = 0;
            for (JSONObject optLong : arrayList) {
                long optLong2 = optLong.optLong("time");
                j = (j2 == 0 || optLong2 < j2) ? optLong2 : j2;
                if (timeInMillis > 1000 * optLong2) {
                    obj = 1;
                    break;
                }
                j2 = j;
            }
            j = j2;
            obj = null;
            if (j < (System.currentTimeMillis() / 1000) - ((long) m2528b().m2629n().getSharedPreferences("AMS_SDK_CONFIG", 0).getInt("loc_batch_time_window", "release".equals("debug") ? 90 : 14400))) {
                obj = 1;
            }
            if (m2487a(new Date())) {
                obj = 1;
            }
            if (arrayList.size() >= m2528b().m2629n().getSharedPreferences("AMS_SDK_CONFIG", 0).getInt("loc_batch_size_thresh", "release".equals("debug") ? 4 : 100)) {
                obj = 1;
            }
            if (obj != null) {
                Set stringSet2 = m2529c().getSharedPreferences("AMS_LOC_BATCH", 0).getStringSet("AMS_LOC_BATCH", new HashSet());
                if (stringSet2.size() > 0 && !this.f1926t) {
                    if (stringSet2.size() < 10) {
                        this.f1916j.m2434a(3000);
                    } else {
                        this.f1916j.m2434a(100);
                    }
                }
            }
        }
    }

    final void m2536j() {
        if (!this.f1920n) {
            this.f1920n = true;
            String str = "oauth/token";
            RequestParams requestParams = new RequestParams();
            requestParams.put("grant_type", "client_credentials");
            requestParams.put("client_id", AreaMetricsSDK.INSTANCE.getAppID());
            requestParams.put("client_secret", AreaMetricsSDK.INSTANCE.getApiKey());
            requestParams.put("scope", "sdk_write");
            if (this.f1912f > 0) {
                requestParams.put("retry", Integer.toString(this.f1912f));
            }
            SSLSocketFactory k = m2498k();
            if (k == null) {
                this.f1920n = false;
                return;
            }
            this.f1913g.removeAllHeaders();
            this.f1913g.setSSLSocketFactory(k);
            this.f1913g.post(this.f1908b + str, requestParams, new C13607(this));
        }
    }
}
