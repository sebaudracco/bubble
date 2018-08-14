package com.google.android.gms.ads.internal.gmsg;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import com.google.android.gms.ads.internal.overlay.zzc;
import com.google.android.gms.ads.internal.overlay.zzn;
import com.google.android.gms.ads.internal.overlay.zzt;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.ads.internal.zzx;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.zzaab;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzajb;
import com.google.android.gms.internal.ads.zzane;
import com.google.android.gms.internal.ads.zzang;
import com.google.android.gms.internal.ads.zzaqw;
import com.google.android.gms.internal.ads.zzarr;
import com.google.android.gms.internal.ads.zzars;
import com.google.android.gms.internal.ads.zzarw;
import com.google.android.gms.internal.ads.zzarz;
import com.google.android.gms.internal.ads.zzasb;
import com.google.android.gms.internal.ads.zzci;
import com.google.android.gms.internal.ads.zzcj;
import com.google.android.gms.internal.ads.zzjd;
import com.mobfox.sdk.networking.RequestParams;
import java.util.Map;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

@zzadh
public final class zzad<T extends zzarr & zzars & zzarw & zzarz & zzasb> implements zzv<T> {
    private final Context mContext;
    private final zzjd zzapt;
    private final zzb zzbll;
    private final zzd zzblm;
    private final zzx zzbmw;
    private final zzaab zzbmx;
    private final zzci zzbna;
    private final zzt zzbnb;
    private final zzn zzbnc;
    private final zzaqw zzbnd = null;
    private final zzang zzzw;

    public zzad(Context context, zzang com_google_android_gms_internal_ads_zzang, zzci com_google_android_gms_internal_ads_zzci, zzt com_google_android_gms_ads_internal_overlay_zzt, zzjd com_google_android_gms_internal_ads_zzjd, zzb com_google_android_gms_ads_internal_gmsg_zzb, zzd com_google_android_gms_ads_internal_gmsg_zzd, zzn com_google_android_gms_ads_internal_overlay_zzn, zzx com_google_android_gms_ads_internal_zzx, zzaab com_google_android_gms_internal_ads_zzaab) {
        this.mContext = context;
        this.zzzw = com_google_android_gms_internal_ads_zzang;
        this.zzbna = com_google_android_gms_internal_ads_zzci;
        this.zzbnb = com_google_android_gms_ads_internal_overlay_zzt;
        this.zzapt = com_google_android_gms_internal_ads_zzjd;
        this.zzbll = com_google_android_gms_ads_internal_gmsg_zzb;
        this.zzblm = com_google_android_gms_ads_internal_gmsg_zzd;
        this.zzbmw = com_google_android_gms_ads_internal_zzx;
        this.zzbmx = com_google_android_gms_internal_ads_zzaab;
        this.zzbnc = com_google_android_gms_ads_internal_overlay_zzn;
    }

    @VisibleForTesting
    static String zza(Context context, zzci com_google_android_gms_internal_ads_zzci, String str, View view, @Nullable Activity activity) {
        if (com_google_android_gms_internal_ads_zzci != null) {
            try {
                Uri parse = Uri.parse(str);
                if (com_google_android_gms_internal_ads_zzci.zzc(parse)) {
                    parse = com_google_android_gms_internal_ads_zzci.zza(parse, context, view, activity);
                }
                str = parse.toString();
            } catch (zzcj e) {
            } catch (Throwable e2) {
                zzbv.zzeo().zza(e2, "OpenGmsgHandler.maybeAddClickSignalsToUrl");
            }
        }
        return str;
    }

    private static boolean zzg(Map<String, String> map) {
        return SchemaSymbols.ATTVAL_TRUE_1.equals(map.get("custom_close"));
    }

    private static int zzh(Map<String, String> map) {
        String str = (String) map.get("o");
        if (str != null) {
            if ("p".equalsIgnoreCase(str)) {
                return zzbv.zzem().zzrm();
            }
            if ("l".equalsIgnoreCase(str)) {
                return zzbv.zzem().zzrl();
            }
            if ("c".equalsIgnoreCase(str)) {
                return zzbv.zzem().zzrn();
            }
        }
        return -1;
    }

    private final void zzl(boolean z) {
        if (this.zzbmx != null) {
            this.zzbmx.zzm(z);
        }
    }

    public final /* synthetic */ void zza(Object obj, Map map) {
        Object zza;
        zzarr com_google_android_gms_internal_ads_zzarr = (zzarr) obj;
        String zzb = zzajb.zzb((String) map.get(RequestParams.f9038U), com_google_android_gms_internal_ads_zzarr.getContext());
        String str = (String) map.get("a");
        if (str == null) {
            zzane.zzdk("Action missing from an open GMSG.");
        } else if (this.zzbmw != null && !this.zzbmw.zzcy()) {
            this.zzbmw.zzs(zzb);
        } else if ("expand".equalsIgnoreCase(str)) {
            if (((zzars) com_google_android_gms_internal_ads_zzarr).zzuj()) {
                zzane.zzdk("Cannot expand WebView that is already expanded.");
                return;
            }
            zzl(false);
            ((zzarw) com_google_android_gms_internal_ads_zzarr).zza(zzg(map), zzh(map));
        } else if ("webapp".equalsIgnoreCase(str)) {
            zzl(false);
            if (zzb != null) {
                ((zzarw) com_google_android_gms_internal_ads_zzarr).zza(zzg(map), zzh(map), zzb);
            } else {
                ((zzarw) com_google_android_gms_internal_ads_zzarr).zza(zzg(map), zzh(map), (String) map.get("html"), (String) map.get("baseurl"));
            }
        } else if ("app".equalsIgnoreCase(str) && SchemaSymbols.ATTVAL_TRUE.equalsIgnoreCase((String) map.get("system_browser"))) {
            zzl(true);
            com_google_android_gms_internal_ads_zzarr.getContext();
            if (TextUtils.isEmpty(zzb)) {
                zzane.zzdk("Destination url cannot be empty.");
                return;
            }
            try {
                ((zzarw) com_google_android_gms_internal_ads_zzarr).zza(new zzc(new zzae(com_google_android_gms_internal_ads_zzarr.getContext(), ((zzarz) com_google_android_gms_internal_ads_zzarr).zzui(), ((zzasb) com_google_android_gms_internal_ads_zzarr).getView()).zzi(map)));
            } catch (ActivityNotFoundException e) {
                zzane.zzdk(e.getMessage());
            }
        } else {
            Intent parseUri;
            Uri data;
            Object uri;
            Uri parse;
            zzl(true);
            str = (String) map.get("intent_url");
            if (!TextUtils.isEmpty(str)) {
                try {
                    parseUri = Intent.parseUri(str, 0);
                } catch (Throwable e2) {
                    String str2 = "Error parsing the url: ";
                    str = String.valueOf(str);
                    zzane.zzb(str.length() != 0 ? str2.concat(str) : new String(str2), e2);
                }
                if (!(parseUri == null || parseUri.getData() == null)) {
                    data = parseUri.getData();
                    uri = data.toString();
                    if (!TextUtils.isEmpty(uri)) {
                        try {
                            zza = zza(com_google_android_gms_internal_ads_zzarr.getContext(), ((zzarz) com_google_android_gms_internal_ads_zzarr).zzui(), uri, ((zzasb) com_google_android_gms_internal_ads_zzarr).getView(), com_google_android_gms_internal_ads_zzarr.zzto());
                        } catch (Throwable e3) {
                            zzane.zzb("Error occurred while adding signals.", e3);
                            zzbv.zzeo().zza(e3, "OpenGmsgHandler.onGmsg");
                            zza = uri;
                        }
                        try {
                            parse = Uri.parse(zza);
                        } catch (Throwable e22) {
                            String str3 = "Error parsing the uri: ";
                            str = String.valueOf(zza);
                            zzane.zzb(str.length() != 0 ? str3.concat(str) : new String(str3), e22);
                            zzbv.zzeo().zza(e22, "OpenGmsgHandler.onGmsg");
                        }
                        parseUri.setData(parse);
                    }
                    parse = data;
                    parseUri.setData(parse);
                }
                if (parseUri != null) {
                    ((zzarw) com_google_android_gms_internal_ads_zzarr).zza(new zzc(parseUri));
                    return;
                }
                if (!TextUtils.isEmpty(zzb)) {
                    zzb = zza(com_google_android_gms_internal_ads_zzarr.getContext(), ((zzarz) com_google_android_gms_internal_ads_zzarr).zzui(), zzb, ((zzasb) com_google_android_gms_internal_ads_zzarr).getView(), com_google_android_gms_internal_ads_zzarr.zzto());
                }
                ((zzarw) com_google_android_gms_internal_ads_zzarr).zza(new zzc((String) map.get(RequestParams.IP), zzb, (String) map.get(RequestParams.f9036M), (String) map.get("p"), (String) map.get("c"), (String) map.get("f"), (String) map.get("e")));
            }
            parseUri = null;
            data = parseUri.getData();
            uri = data.toString();
            if (TextUtils.isEmpty(uri)) {
                zza = zza(com_google_android_gms_internal_ads_zzarr.getContext(), ((zzarz) com_google_android_gms_internal_ads_zzarr).zzui(), uri, ((zzasb) com_google_android_gms_internal_ads_zzarr).getView(), com_google_android_gms_internal_ads_zzarr.zzto());
                parse = Uri.parse(zza);
                parseUri.setData(parse);
                if (parseUri != null) {
                    if (TextUtils.isEmpty(zzb)) {
                        zzb = zza(com_google_android_gms_internal_ads_zzarr.getContext(), ((zzarz) com_google_android_gms_internal_ads_zzarr).zzui(), zzb, ((zzasb) com_google_android_gms_internal_ads_zzarr).getView(), com_google_android_gms_internal_ads_zzarr.zzto());
                    }
                    ((zzarw) com_google_android_gms_internal_ads_zzarr).zza(new zzc((String) map.get(RequestParams.IP), zzb, (String) map.get(RequestParams.f9036M), (String) map.get("p"), (String) map.get("c"), (String) map.get("f"), (String) map.get("e")));
                }
                ((zzarw) com_google_android_gms_internal_ads_zzarr).zza(new zzc(parseUri));
                return;
            }
            parse = data;
            parseUri.setData(parse);
            if (parseUri != null) {
                ((zzarw) com_google_android_gms_internal_ads_zzarr).zza(new zzc(parseUri));
                return;
            }
            if (TextUtils.isEmpty(zzb)) {
                zzb = zza(com_google_android_gms_internal_ads_zzarr.getContext(), ((zzarz) com_google_android_gms_internal_ads_zzarr).zzui(), zzb, ((zzasb) com_google_android_gms_internal_ads_zzarr).getView(), com_google_android_gms_internal_ads_zzarr.zzto());
            }
            ((zzarw) com_google_android_gms_internal_ads_zzarr).zza(new zzc((String) map.get(RequestParams.IP), zzb, (String) map.get(RequestParams.f9036M), (String) map.get("p"), (String) map.get("c"), (String) map.get("f"), (String) map.get("e")));
        }
    }
}
