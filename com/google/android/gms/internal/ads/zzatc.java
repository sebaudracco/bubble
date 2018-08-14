package com.google.android.gms.internal.ads;

import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import com.bgjd.ici.p025b.C1408j.C1404b;
import com.google.android.gms.iid.InstanceID;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
final class zzatc {
    private static final String[] zzdbo = new String[]{C1404b.f2123a, "HOST_LOOKUP", "UNSUPPORTED_AUTH_SCHEME", "AUTHENTICATION", "PROXY_AUTHENTICATION", "CONNECT", "IO", InstanceID.ERROR_TIMEOUT, "REDIRECT_LOOP", "UNSUPPORTED_SCHEME", "FAILED_SSL_HANDSHAKE", "BAD_URL", "FILE", "FILE_NOT_FOUND", "TOO_MANY_REQUESTS"};
    private static final String[] zzdbp = new String[]{"NOT_YET_VALID", "EXPIRED", "ID_MISMATCH", "UNTRUSTED", "DATE_INVALID", "INVALID"};

    zzatc() {
    }

    private static void zzd(String str, String str2, String str3) {
        if (((Boolean) zzkb.zzik().zzd(zznk.zzazy)).booleanValue()) {
            String host;
            Bundle bundle = new Bundle();
            bundle.putString(NotificationCompat.CATEGORY_ERROR, str);
            bundle.putString("code", str2);
            String str4 = "host";
            if (!TextUtils.isEmpty(str3)) {
                Uri parse = Uri.parse(str3);
                if (parse.getHost() != null) {
                    host = parse.getHost();
                    bundle.putString(str4, host);
                }
            }
            host = "";
            bundle.putString(str4, host);
        }
    }

    final void zzb(@Nullable SslError sslError) {
        if (sslError != null) {
            int primaryError = sslError.getPrimaryError();
            String valueOf = (primaryError < 0 || primaryError >= zzdbp.length) ? String.valueOf(primaryError) : zzdbp[primaryError];
            zzd("ssl_err", valueOf, sslError.getUrl());
        }
    }

    final void zze(int i, String str) {
        String valueOf = (i >= 0 || (-i) - 1 >= zzdbo.length) ? String.valueOf(i) : zzdbo[(-i) - 1];
        zzd("http_err", valueOf, str);
    }
}
