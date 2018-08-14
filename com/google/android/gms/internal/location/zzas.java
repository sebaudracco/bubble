package com.google.android.gms.internal.location;

import android.app.PendingIntent;
import android.content.ContentProviderClient;
import android.content.Context;
import android.location.Location;
import android.os.RemoteException;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolder.ListenerKey;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.location.LocationAvailability;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.zzu;
import com.google.android.gms.location.zzx;
import java.util.HashMap;
import java.util.Map;

public final class zzas {
    private final zzbj<zzao> zzcb;
    private final Context zzcu;
    private ContentProviderClient zzcv = null;
    private boolean zzcw = false;
    private final Map<ListenerKey<LocationListener>, zzax> zzcx = new HashMap();
    private final Map<ListenerKey<Object>, zzaw> zzcy = new HashMap();
    private final Map<ListenerKey<LocationCallback>, zzat> zzcz = new HashMap();

    public zzas(Context context, zzbj<zzao> com_google_android_gms_internal_location_zzbj_com_google_android_gms_internal_location_zzao) {
        this.zzcu = context;
        this.zzcb = com_google_android_gms_internal_location_zzbj_com_google_android_gms_internal_location_zzao;
    }

    private final zzax zza(ListenerHolder<LocationListener> listenerHolder) {
        zzax com_google_android_gms_internal_location_zzax;
        synchronized (this.zzcx) {
            com_google_android_gms_internal_location_zzax = (zzax) this.zzcx.get(listenerHolder.getListenerKey());
            if (com_google_android_gms_internal_location_zzax == null) {
                com_google_android_gms_internal_location_zzax = new zzax(listenerHolder);
            }
            this.zzcx.put(listenerHolder.getListenerKey(), com_google_android_gms_internal_location_zzax);
        }
        return com_google_android_gms_internal_location_zzax;
    }

    private final zzat zzb(ListenerHolder<LocationCallback> listenerHolder) {
        zzat com_google_android_gms_internal_location_zzat;
        synchronized (this.zzcz) {
            com_google_android_gms_internal_location_zzat = (zzat) this.zzcz.get(listenerHolder.getListenerKey());
            if (com_google_android_gms_internal_location_zzat == null) {
                com_google_android_gms_internal_location_zzat = new zzat(listenerHolder);
            }
            this.zzcz.put(listenerHolder.getListenerKey(), com_google_android_gms_internal_location_zzat);
        }
        return com_google_android_gms_internal_location_zzat;
    }

    public final Location getLastLocation() throws RemoteException {
        this.zzcb.checkConnected();
        return ((zzao) this.zzcb.getService()).zza(this.zzcu.getPackageName());
    }

    public final void removeAllListeners() throws RemoteException {
        synchronized (this.zzcx) {
            for (zzx com_google_android_gms_location_zzx : this.zzcx.values()) {
                if (com_google_android_gms_location_zzx != null) {
                    ((zzao) this.zzcb.getService()).zza(zzbf.zza(com_google_android_gms_location_zzx, null));
                }
            }
            this.zzcx.clear();
        }
        synchronized (this.zzcz) {
            for (zzu com_google_android_gms_location_zzu : this.zzcz.values()) {
                if (com_google_android_gms_location_zzu != null) {
                    ((zzao) this.zzcb.getService()).zza(zzbf.zza(com_google_android_gms_location_zzu, null));
                }
            }
            this.zzcz.clear();
        }
        synchronized (this.zzcy) {
            for (zzaw com_google_android_gms_internal_location_zzaw : this.zzcy.values()) {
                if (com_google_android_gms_internal_location_zzaw != null) {
                    ((zzao) this.zzcb.getService()).zza(new zzo(2, null, com_google_android_gms_internal_location_zzaw.asBinder(), null));
                }
            }
            this.zzcy.clear();
        }
    }

    public final LocationAvailability zza() throws RemoteException {
        this.zzcb.checkConnected();
        return ((zzao) this.zzcb.getService()).zzb(this.zzcu.getPackageName());
    }

    public final void zza(PendingIntent pendingIntent, zzaj com_google_android_gms_internal_location_zzaj) throws RemoteException {
        this.zzcb.checkConnected();
        ((zzao) this.zzcb.getService()).zza(new zzbf(2, null, null, pendingIntent, null, com_google_android_gms_internal_location_zzaj != null ? com_google_android_gms_internal_location_zzaj.asBinder() : null));
    }

    public final void zza(Location location) throws RemoteException {
        this.zzcb.checkConnected();
        ((zzao) this.zzcb.getService()).zza(location);
    }

    public final void zza(ListenerKey<LocationListener> listenerKey, zzaj com_google_android_gms_internal_location_zzaj) throws RemoteException {
        this.zzcb.checkConnected();
        Preconditions.checkNotNull(listenerKey, "Invalid null listener key");
        synchronized (this.zzcx) {
            zzx com_google_android_gms_location_zzx = (zzax) this.zzcx.remove(listenerKey);
            if (com_google_android_gms_location_zzx != null) {
                com_google_android_gms_location_zzx.release();
                ((zzao) this.zzcb.getService()).zza(zzbf.zza(com_google_android_gms_location_zzx, com_google_android_gms_internal_location_zzaj));
            }
        }
    }

    public final void zza(zzaj com_google_android_gms_internal_location_zzaj) throws RemoteException {
        this.zzcb.checkConnected();
        ((zzao) this.zzcb.getService()).zza(com_google_android_gms_internal_location_zzaj);
    }

    public final void zza(zzbd com_google_android_gms_internal_location_zzbd, ListenerHolder<LocationCallback> listenerHolder, zzaj com_google_android_gms_internal_location_zzaj) throws RemoteException {
        this.zzcb.checkConnected();
        ((zzao) this.zzcb.getService()).zza(new zzbf(1, com_google_android_gms_internal_location_zzbd, null, null, zzb(listenerHolder).asBinder(), com_google_android_gms_internal_location_zzaj != null ? com_google_android_gms_internal_location_zzaj.asBinder() : null));
    }

    public final void zza(LocationRequest locationRequest, PendingIntent pendingIntent, zzaj com_google_android_gms_internal_location_zzaj) throws RemoteException {
        this.zzcb.checkConnected();
        ((zzao) this.zzcb.getService()).zza(new zzbf(1, zzbd.zza(locationRequest), null, pendingIntent, null, com_google_android_gms_internal_location_zzaj != null ? com_google_android_gms_internal_location_zzaj.asBinder() : null));
    }

    public final void zza(LocationRequest locationRequest, ListenerHolder<LocationListener> listenerHolder, zzaj com_google_android_gms_internal_location_zzaj) throws RemoteException {
        this.zzcb.checkConnected();
        ((zzao) this.zzcb.getService()).zza(new zzbf(1, zzbd.zza(locationRequest), zza((ListenerHolder) listenerHolder).asBinder(), null, null, com_google_android_gms_internal_location_zzaj != null ? com_google_android_gms_internal_location_zzaj.asBinder() : null));
    }

    public final void zza(boolean z) throws RemoteException {
        this.zzcb.checkConnected();
        ((zzao) this.zzcb.getService()).zza(z);
        this.zzcw = z;
    }

    public final void zzb() throws RemoteException {
        if (this.zzcw) {
            zza(false);
        }
    }

    public final void zzb(ListenerKey<LocationCallback> listenerKey, zzaj com_google_android_gms_internal_location_zzaj) throws RemoteException {
        this.zzcb.checkConnected();
        Preconditions.checkNotNull(listenerKey, "Invalid null listener key");
        synchronized (this.zzcz) {
            zzu com_google_android_gms_location_zzu = (zzat) this.zzcz.remove(listenerKey);
            if (com_google_android_gms_location_zzu != null) {
                com_google_android_gms_location_zzu.release();
                ((zzao) this.zzcb.getService()).zza(zzbf.zza(com_google_android_gms_location_zzu, com_google_android_gms_internal_location_zzaj));
            }
        }
    }
}
