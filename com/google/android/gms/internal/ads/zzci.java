package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.MotionEvent;
import android.view.View;

public final class zzci {
    private static final String[] zzrc = new String[]{"/aclk", "/pcs/click"};
    private String zzqy = "googleads.g.doubleclick.net";
    private String zzqz = "/pagead/ads";
    private String zzra = "ad.doubleclick.net";
    private String[] zzrb = new String[]{".doubleclick.net", ".googleadservices.com", ".googlesyndication.com"};
    private zzce zzrd;

    public zzci(zzce com_google_android_gms_internal_ads_zzce) {
        this.zzrd = com_google_android_gms_internal_ads_zzce;
    }

    private final Uri zza(Uri uri, Context context, String str, boolean z, View view, Activity activity) throws zzcj {
        try {
            boolean zza = zza(uri);
            if (zza) {
                if (uri.toString().contains("dc_ms=")) {
                    throw new zzcj("Parameter already exists: dc_ms");
                }
            } else if (uri.getQueryParameter("ms") != null) {
                throw new zzcj("Query parameter already exists: ms");
            }
            String zza2 = z ? this.zzrd.zza(context, str, view, activity) : this.zzrd.zza(context);
            String uri2;
            String encodedPath;
            if (zza) {
                String str2 = "dc_ms";
                uri2 = uri.toString();
                int indexOf = uri2.indexOf(";adurl");
                if (indexOf != -1) {
                    return Uri.parse(new StringBuilder(uri2.substring(0, indexOf + 1)).append(str2).append("=").append(zza2).append(";").append(uri2.substring(indexOf + 1)).toString());
                }
                encodedPath = uri.getEncodedPath();
                int indexOf2 = uri2.indexOf(encodedPath);
                return Uri.parse(new StringBuilder(uri2.substring(0, encodedPath.length() + indexOf2)).append(";").append(str2).append("=").append(zza2).append(";").append(uri2.substring(encodedPath.length() + indexOf2)).toString());
            }
            uri2 = "ms";
            encodedPath = uri.toString();
            int indexOf3 = encodedPath.indexOf("&adurl");
            if (indexOf3 == -1) {
                indexOf3 = encodedPath.indexOf("?adurl");
            }
            return indexOf3 != -1 ? Uri.parse(new StringBuilder(encodedPath.substring(0, indexOf3 + 1)).append(uri2).append("=").append(zza2).append("&").append(encodedPath.substring(indexOf3 + 1)).toString()) : uri.buildUpon().appendQueryParameter(uri2, zza2).build();
        } catch (UnsupportedOperationException e) {
            throw new zzcj("Provided Uri is not in a valid state");
        }
    }

    private final boolean zza(Uri uri) {
        if (uri == null) {
            throw new NullPointerException();
        }
        try {
            return uri.getHost().equals(this.zzra);
        } catch (NullPointerException e) {
            return false;
        }
    }

    public final Uri zza(Uri uri, Context context) throws zzcj {
        return zza(uri, context, null, false, null, null);
    }

    public final Uri zza(Uri uri, Context context, View view, Activity activity) throws zzcj {
        try {
            return zza(uri, context, uri.getQueryParameter("ai"), true, view, activity);
        } catch (UnsupportedOperationException e) {
            throw new zzcj("Provided Uri is not in a valid state");
        }
    }

    public final void zza(MotionEvent motionEvent) {
        this.zzrd.zza(motionEvent);
    }

    public final zzce zzaa() {
        return this.zzrd;
    }

    public final boolean zzb(Uri uri) {
        if (uri == null) {
            throw new NullPointerException();
        }
        try {
            String host = uri.getHost();
            for (String endsWith : this.zzrb) {
                if (host.endsWith(endsWith)) {
                    return true;
                }
            }
            return false;
        } catch (NullPointerException e) {
            return false;
        }
    }

    public final boolean zzc(Uri uri) {
        if (!zzb(uri)) {
            return false;
        }
        for (String endsWith : zzrc) {
            if (uri.getPath().endsWith(endsWith)) {
                return true;
            }
        }
        return false;
    }
}
