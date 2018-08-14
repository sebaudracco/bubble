package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import cz.msebera.android.httpclient.HttpStatus;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@zzadh
public final class zzanf implements zzamx {
    @Nullable
    private final String zzcpq;

    public zzanf() {
        this(null);
    }

    public zzanf(@Nullable String str) {
        this.zzcpq = str;
    }

    @WorkerThread
    public final void zzcz(String str) {
        String valueOf;
        HttpURLConnection httpURLConnection;
        try {
            String str2 = "Pinging URL: ";
            valueOf = String.valueOf(str);
            zzane.zzck(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
            zzkb.zzif();
            zzamu.zza(true, httpURLConnection, this.zzcpq);
            zzamy com_google_android_gms_internal_ads_zzamy = new zzamy();
            com_google_android_gms_internal_ads_zzamy.zza(httpURLConnection, null);
            int responseCode = httpURLConnection.getResponseCode();
            com_google_android_gms_internal_ads_zzamy.zza(httpURLConnection, responseCode);
            if (responseCode < 200 || responseCode >= HttpStatus.SC_MULTIPLE_CHOICES) {
                zzane.zzdk(new StringBuilder(String.valueOf(str).length() + 65).append("Received non-success response code ").append(responseCode).append(" from pinging URL: ").append(str).toString());
            }
            httpURLConnection.disconnect();
        } catch (IndexOutOfBoundsException e) {
            valueOf = e.getMessage();
            zzane.zzdk(new StringBuilder((String.valueOf(str).length() + 32) + String.valueOf(valueOf).length()).append("Error while parsing ping URL: ").append(str).append(". ").append(valueOf).toString());
        } catch (IOException e2) {
            valueOf = e2.getMessage();
            zzane.zzdk(new StringBuilder((String.valueOf(str).length() + 27) + String.valueOf(valueOf).length()).append("Error while pinging URL: ").append(str).append(". ").append(valueOf).toString());
        } catch (RuntimeException e3) {
            valueOf = e3.getMessage();
            zzane.zzdk(new StringBuilder((String.valueOf(str).length() + 27) + String.valueOf(valueOf).length()).append("Error while pinging URL: ").append(str).append(". ").append(valueOf).toString());
        } catch (Throwable th) {
            httpURLConnection.disconnect();
        }
    }
}
