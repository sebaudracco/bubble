package com.google.android.gms.internal.ads;

import android.net.Uri;
import android.support.annotation.GuardedBy;
import com.google.android.gms.ads.internal.gmsg.zzv;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.Predicate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public abstract class zzus<ReferenceT> {
    @GuardedBy("this")
    private final Map<String, CopyOnWriteArrayList<zzv<? super ReferenceT>>> zzbpn = new HashMap();

    private final synchronized void zzb(String str, Map<String, String> map) {
        if (zzane.isLoggable(2)) {
            String str2 = "Received GMSG: ";
            String valueOf = String.valueOf(str);
            zzakb.m3428v(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            for (String valueOf2 : map.keySet()) {
                str2 = (String) map.get(valueOf2);
                zzakb.m3428v(new StringBuilder((String.valueOf(valueOf2).length() + 4) + String.valueOf(str2).length()).append("  ").append(valueOf2).append(": ").append(str2).toString());
            }
        }
        CopyOnWriteArrayList copyOnWriteArrayList = (CopyOnWriteArrayList) this.zzbpn.get(str);
        if (copyOnWriteArrayList != null) {
            Iterator it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                zzaoe.zzcvy.execute(new zzut(this, (zzv) it.next(), map));
            }
        }
    }

    public abstract ReferenceT getReference();

    public synchronized void reset() {
        this.zzbpn.clear();
    }

    public final synchronized void zza(String str, zzv<? super ReferenceT> com_google_android_gms_ads_internal_gmsg_zzv__super_ReferenceT) {
        CopyOnWriteArrayList copyOnWriteArrayList = (CopyOnWriteArrayList) this.zzbpn.get(str);
        if (copyOnWriteArrayList == null) {
            copyOnWriteArrayList = new CopyOnWriteArrayList();
            this.zzbpn.put(str, copyOnWriteArrayList);
        }
        copyOnWriteArrayList.add(com_google_android_gms_ads_internal_gmsg_zzv__super_ReferenceT);
    }

    public final synchronized void zza(String str, Predicate<zzv<? super ReferenceT>> predicate) {
        CopyOnWriteArrayList copyOnWriteArrayList = (CopyOnWriteArrayList) this.zzbpn.get(str);
        if (copyOnWriteArrayList != null) {
            Collection arrayList = new ArrayList();
            Iterator it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                zzv com_google_android_gms_ads_internal_gmsg_zzv = (zzv) it.next();
                if (predicate.apply(com_google_android_gms_ads_internal_gmsg_zzv)) {
                    arrayList.add(com_google_android_gms_ads_internal_gmsg_zzv);
                }
            }
            copyOnWriteArrayList.removeAll(arrayList);
        }
    }

    public final synchronized void zzb(String str, zzv<? super ReferenceT> com_google_android_gms_ads_internal_gmsg_zzv__super_ReferenceT) {
        CopyOnWriteArrayList copyOnWriteArrayList = (CopyOnWriteArrayList) this.zzbpn.get(str);
        if (copyOnWriteArrayList != null) {
            copyOnWriteArrayList.remove(com_google_android_gms_ads_internal_gmsg_zzv__super_ReferenceT);
        }
    }

    public final boolean zzf(Uri uri) {
        if (!"gmsg".equalsIgnoreCase(uri.getScheme()) || !"mobileads.google.com".equalsIgnoreCase(uri.getHost())) {
            return false;
        }
        String path = uri.getPath();
        zzbv.zzek();
        zzb(path, zzakk.zzg(uri));
        return true;
    }
}
