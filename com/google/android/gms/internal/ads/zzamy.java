package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Build.VERSION;
import android.provider.Settings.Global;
import android.support.annotation.GuardedBy;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.JsonWriter;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.wearable.WearableStatusCodes;
import com.google.firebase.analytics.FirebaseAnalytics$Param;
import cz.msebera.android.httpclient.HttpStatus;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

@zzadh
public final class zzamy {
    private static Object sLock = new Object();
    @GuardedBy("sLock")
    private static boolean zzcuv = false;
    @GuardedBy("sLock")
    private static boolean zzcuw = false;
    private static Clock zzcux = DefaultClock.getInstance();
    private static final Set<String> zzcuy = new HashSet(Arrays.asList(new String[0]));
    private final List<String> zzcuz;

    public zzamy() {
        this(null);
    }

    public zzamy(@Nullable String str) {
        List asList;
        if (isEnabled()) {
            String uuid = UUID.randomUUID().toString();
            String[] strArr;
            String str2;
            String valueOf;
            if (str == null) {
                strArr = new String[1];
                str2 = "network_request_";
                valueOf = String.valueOf(uuid);
                strArr[0] = valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2);
                asList = Arrays.asList(strArr);
            } else {
                strArr = new String[2];
                str2 = "ad_request_";
                valueOf = String.valueOf(str);
                strArr[0] = valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2);
                str2 = "network_request_";
                valueOf = String.valueOf(uuid);
                strArr[1] = valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2);
                asList = Arrays.asList(strArr);
            }
        } else {
            asList = new ArrayList();
        }
        this.zzcuz = asList;
    }

    public static boolean isEnabled() {
        boolean z;
        synchronized (sLock) {
            z = zzcuv && zzcuw;
        }
        return z;
    }

    static final /* synthetic */ void zza(int i, Map map, JsonWriter jsonWriter) throws IOException {
        jsonWriter.name("params").beginObject();
        jsonWriter.name("firstline").beginObject();
        jsonWriter.name("code").value((long) i);
        jsonWriter.endObject();
        zza(jsonWriter, map);
        jsonWriter.endObject();
    }

    private static void zza(JsonWriter jsonWriter, @Nullable Map<String, ?> map) throws IOException {
        if (map != null) {
            jsonWriter.name("headers").beginArray();
            for (Entry entry : map.entrySet()) {
                String str = (String) entry.getKey();
                if (!zzcuy.contains(str)) {
                    if (!(entry.getValue() instanceof List)) {
                        if (!(entry.getValue() instanceof String)) {
                            zzane.m3427e("Connection headers should be either Map<String, String> or Map<String, List<String>>");
                            break;
                        }
                        jsonWriter.beginObject();
                        jsonWriter.name("name").value(str);
                        jsonWriter.name(FirebaseAnalytics$Param.VALUE).value((String) entry.getValue());
                        jsonWriter.endObject();
                    } else {
                        for (String str2 : (List) entry.getValue()) {
                            jsonWriter.beginObject();
                            jsonWriter.name("name").value(str);
                            jsonWriter.name(FirebaseAnalytics$Param.VALUE).value(str2);
                            jsonWriter.endObject();
                        }
                    }
                }
            }
            jsonWriter.endArray();
        }
    }

    static final /* synthetic */ void zza(String str, JsonWriter jsonWriter) throws IOException {
        jsonWriter.name("params").beginObject();
        if (str != null) {
            jsonWriter.name("error_description").value(str);
        }
        jsonWriter.endObject();
    }

    private final void zza(String str, zzand com_google_android_gms_internal_ads_zzand) {
        Writer stringWriter = new StringWriter();
        JsonWriter jsonWriter = new JsonWriter(stringWriter);
        try {
            jsonWriter.beginObject();
            jsonWriter.name("timestamp").value(zzcux.currentTimeMillis());
            jsonWriter.name(NotificationCompat.CATEGORY_EVENT).value(str);
            jsonWriter.name("components").beginArray();
            for (String value : this.zzcuz) {
                jsonWriter.value(value);
            }
            jsonWriter.endArray();
            com_google_android_gms_internal_ads_zzand.zza(jsonWriter);
            jsonWriter.endObject();
            jsonWriter.flush();
            jsonWriter.close();
        } catch (Throwable e) {
            zzane.zzb("unable to log", e);
        }
        zzdi(stringWriter.toString());
    }

    static final /* synthetic */ void zza(String str, String str2, Map map, byte[] bArr, JsonWriter jsonWriter) throws IOException {
        jsonWriter.name("params").beginObject();
        jsonWriter.name("firstline").beginObject();
        jsonWriter.name("uri").value(str);
        jsonWriter.name("verb").value(str2);
        jsonWriter.endObject();
        zza(jsonWriter, map);
        if (bArr != null) {
            jsonWriter.name("body").value(Base64Utils.encode(bArr));
        }
        jsonWriter.endObject();
    }

    static final /* synthetic */ void zza(byte[] bArr, JsonWriter jsonWriter) throws IOException {
        jsonWriter.name("params").beginObject();
        int length = bArr.length;
        String encode = Base64Utils.encode(bArr);
        if (length < 10000) {
            jsonWriter.name("body").value(encode);
        } else {
            encode = zzamu.zzde(encode);
            if (encode != null) {
                jsonWriter.name("bodydigest").value(encode);
            }
        }
        jsonWriter.name("bodylength").value((long) length);
        jsonWriter.endObject();
    }

    public static void zzaf(boolean z) {
        synchronized (sLock) {
            zzcuv = true;
            zzcuw = z;
        }
    }

    private final void zzb(String str, String str2, @Nullable Map<String, ?> map, @Nullable byte[] bArr) {
        zza("onNetworkRequest", new zzamz(str, str2, map, bArr));
    }

    private final void zzb(@Nullable Map<String, ?> map, int i) {
        zza("onNetworkResponse", new zzana(i, map));
    }

    public static boolean zzbl(Context context) {
        if (VERSION.SDK_INT < 17) {
            return false;
        }
        if (!((Boolean) zzkb.zzik().zzd(zznk.zzazm)).booleanValue()) {
            return false;
        }
        try {
            return Global.getInt(context.getContentResolver(), "development_settings_enabled", 0) != 0;
        } catch (Throwable e) {
            zzane.zzc("Fail to determine debug setting.", e);
            return false;
        }
    }

    private final void zzdh(@Nullable String str) {
        zza("onNetworkRequestError", new zzanc(str));
    }

    private static synchronized void zzdi(String str) {
        synchronized (zzamy.class) {
            zzane.zzdj("GMA Debug BEGIN");
            for (int i = 0; i < str.length(); i += WearableStatusCodes.TARGET_NODE_NOT_CONNECTED) {
                String str2 = "GMA Debug CONTENT ";
                String valueOf = String.valueOf(str.substring(i, Math.min(i + WearableStatusCodes.TARGET_NODE_NOT_CONNECTED, str.length())));
                zzane.zzdj(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            }
            zzane.zzdj("GMA Debug FINISH");
        }
    }

    public static void zzsj() {
        synchronized (sLock) {
            zzcuv = false;
            zzcuw = false;
            zzane.zzdk("Ad debug logging enablement is out of date.");
        }
    }

    public static boolean zzsk() {
        boolean z;
        synchronized (sLock) {
            z = zzcuv;
        }
        return z;
    }

    public final void zza(String str, String str2, @Nullable Map<String, ?> map, @Nullable byte[] bArr) {
        if (isEnabled()) {
            zzb(str, str2, map, bArr);
        }
    }

    public final void zza(HttpURLConnection httpURLConnection, int i) {
        String str = null;
        if (isEnabled()) {
            zzb(httpURLConnection.getHeaderFields() == null ? str : new HashMap(httpURLConnection.getHeaderFields()), i);
            if (i < 200 || i >= HttpStatus.SC_MULTIPLE_CHOICES) {
                try {
                    str = httpURLConnection.getResponseMessage();
                } catch (IOException e) {
                    String str2 = "Can not get error message from error HttpURLConnection\n";
                    String valueOf = String.valueOf(e.getMessage());
                    zzane.zzdk(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
                }
                zzdh(str);
            }
        }
    }

    public final void zza(HttpURLConnection httpURLConnection, @Nullable byte[] bArr) {
        if (isEnabled()) {
            zzb(new String(httpURLConnection.getURL().toString()), new String(httpURLConnection.getRequestMethod()), httpURLConnection.getRequestProperties() == null ? null : new HashMap(httpURLConnection.getRequestProperties()), bArr);
        }
    }

    public final void zza(@Nullable Map<String, ?> map, int i) {
        if (isEnabled()) {
            zzb(map, i);
            if (i < 200 || i >= HttpStatus.SC_MULTIPLE_CHOICES) {
                zzdh(null);
            }
        }
    }

    public final void zzdg(@Nullable String str) {
        if (isEnabled() && str != null) {
            zzf(str.getBytes());
        }
    }

    public final void zzf(byte[] bArr) {
        zza("onNetworkResponseBody", new zzanb(bArr));
    }
}
