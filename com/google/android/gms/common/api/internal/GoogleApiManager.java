package com.google.android.gms.common.api.internal;

import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.ArraySet;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.Api.Client;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.UnsupportedApiCallException;
import com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl;
import com.google.android.gms.common.api.internal.ListenerHolder.ListenerKey;
import com.google.android.gms.common.internal.GoogleApiAvailabilityCache;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.SimpleClientAdapter;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.signin.SignInClient;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.concurrent.GuardedBy;

@KeepForSdk
public class GoogleApiManager implements Callback {
    private static final Object lock = new Object();
    public static final Status zzjj = new Status(4, "Sign-out occurred while this API call was in progress.");
    private static final Status zzjk = new Status(4, "The user must be signed in to make this API call.");
    @GuardedBy("lock")
    private static GoogleApiManager zzjo;
    private final Handler handler;
    private long zzjl = 5000;
    private long zzjm = 120000;
    private long zzjn = 10000;
    private final Context zzjp;
    private final GoogleApiAvailability zzjq;
    private final GoogleApiAvailabilityCache zzjr;
    private final AtomicInteger zzjs = new AtomicInteger(1);
    private final AtomicInteger zzjt = new AtomicInteger(0);
    private final Map<zzh<?>, zza<?>> zzju = new ConcurrentHashMap(5, 0.75f, 1);
    @GuardedBy("lock")
    private zzad zzjv = null;
    @GuardedBy("lock")
    private final Set<zzh<?>> zzjw = new ArraySet();
    private final Set<zzh<?>> zzjx = new ArraySet();

    public class zza<O extends ApiOptions> implements ConnectionCallbacks, OnConnectionFailedListener, zzq {
        private final zzh<O> zzhc;
        final /* synthetic */ GoogleApiManager zzjy;
        private final Queue<zzb> zzjz = new LinkedList();
        private final Client zzka;
        private final AnyClient zzkb;
        private final zzaa zzkc;
        private final Set<zzj> zzkd = new HashSet();
        private final Map<ListenerKey<?>, zzbv> zzke = new HashMap();
        private final int zzkf;
        private final zzby zzkg;
        private boolean zzkh;
        private final List<zzb> zzki = new ArrayList();
        private ConnectionResult zzkj = null;

        @WorkerThread
        public zza(GoogleApiManager googleApiManager, GoogleApi<O> googleApi) {
            this.zzjy = googleApiManager;
            this.zzka = googleApi.zza(googleApiManager.handler.getLooper(), this);
            if (this.zzka instanceof SimpleClientAdapter) {
                this.zzkb = ((SimpleClientAdapter) this.zzka).getClient();
            } else {
                this.zzkb = this.zzka;
            }
            this.zzhc = googleApi.zzm();
            this.zzkc = new zzaa();
            this.zzkf = googleApi.getInstanceId();
            if (this.zzka.requiresSignIn()) {
                this.zzkg = googleApi.zza(googleApiManager.zzjp, googleApiManager.handler);
            } else {
                this.zzkg = null;
            }
        }

        @WorkerThread
        private final void zza(zzb com_google_android_gms_common_api_internal_GoogleApiManager_zzb) {
            if (!this.zzki.contains(com_google_android_gms_common_api_internal_GoogleApiManager_zzb) || this.zzkh) {
                return;
            }
            if (this.zzka.isConnected()) {
                zzbl();
            } else {
                connect();
            }
        }

        @WorkerThread
        private final void zzb(zzb com_google_android_gms_common_api_internal_GoogleApiManager_zzb) {
            if (this.zzki.remove(com_google_android_gms_common_api_internal_GoogleApiManager_zzb)) {
                this.zzjy.handler.removeMessages(15, com_google_android_gms_common_api_internal_GoogleApiManager_zzb);
                this.zzjy.handler.removeMessages(16, com_google_android_gms_common_api_internal_GoogleApiManager_zzb);
                Feature zzd = zzb.zzd(com_google_android_gms_common_api_internal_GoogleApiManager_zzb);
                ArrayList arrayList = new ArrayList(this.zzjz.size());
                for (zzb com_google_android_gms_common_api_internal_zzb : this.zzjz) {
                    if (com_google_android_gms_common_api_internal_zzb instanceof zzf) {
                        Feature[] requiredFeatures = ((zzf) com_google_android_gms_common_api_internal_zzb).getRequiredFeatures();
                        if (requiredFeatures != null && ArrayUtils.contains(requiredFeatures, zzd)) {
                            arrayList.add(com_google_android_gms_common_api_internal_zzb);
                        }
                    }
                }
                ArrayList arrayList2 = arrayList;
                int size = arrayList2.size();
                int i = 0;
                while (i < size) {
                    Object obj = arrayList2.get(i);
                    i++;
                    zzb com_google_android_gms_common_api_internal_zzb2 = (zzb) obj;
                    this.zzjz.remove(com_google_android_gms_common_api_internal_zzb2);
                    com_google_android_gms_common_api_internal_zzb2.zza(new UnsupportedApiCallException(zzd));
                }
            }
        }

        @WorkerThread
        private final boolean zzb(zzb com_google_android_gms_common_api_internal_zzb) {
            if (com_google_android_gms_common_api_internal_zzb instanceof zzf) {
                zzf com_google_android_gms_common_api_internal_zzf = (zzf) com_google_android_gms_common_api_internal_zzb;
                Feature[] requiredFeatures = com_google_android_gms_common_api_internal_zzf.getRequiredFeatures();
                if (requiredFeatures == null || requiredFeatures.length == 0) {
                    zzc(com_google_android_gms_common_api_internal_zzb);
                    return true;
                }
                Feature[] availableFeatures = this.zzka.getAvailableFeatures();
                if (availableFeatures == null) {
                    availableFeatures = new Feature[0];
                }
                Map arrayMap = new ArrayMap(availableFeatures.length);
                for (Feature feature : availableFeatures) {
                    arrayMap.put(feature.getName(), Long.valueOf(feature.getVersion()));
                }
                for (Feature feature2 : requiredFeatures) {
                    if (!arrayMap.containsKey(feature2.getName()) || ((Long) arrayMap.get(feature2.getName())).longValue() < feature2.getVersion()) {
                        if (com_google_android_gms_common_api_internal_zzf.shouldAutoResolveMissingFeatures()) {
                            zzb com_google_android_gms_common_api_internal_GoogleApiManager_zzb = new zzb(this.zzhc, feature2, null);
                            int indexOf = this.zzki.indexOf(com_google_android_gms_common_api_internal_GoogleApiManager_zzb);
                            if (indexOf >= 0) {
                                com_google_android_gms_common_api_internal_GoogleApiManager_zzb = (zzb) this.zzki.get(indexOf);
                                this.zzjy.handler.removeMessages(15, com_google_android_gms_common_api_internal_GoogleApiManager_zzb);
                                this.zzjy.handler.sendMessageDelayed(Message.obtain(this.zzjy.handler, 15, com_google_android_gms_common_api_internal_GoogleApiManager_zzb), this.zzjy.zzjl);
                            } else {
                                this.zzki.add(com_google_android_gms_common_api_internal_GoogleApiManager_zzb);
                                this.zzjy.handler.sendMessageDelayed(Message.obtain(this.zzjy.handler, 15, com_google_android_gms_common_api_internal_GoogleApiManager_zzb), this.zzjy.zzjl);
                                this.zzjy.handler.sendMessageDelayed(Message.obtain(this.zzjy.handler, 16, com_google_android_gms_common_api_internal_GoogleApiManager_zzb), this.zzjy.zzjm);
                                ConnectionResult connectionResult = new ConnectionResult(2, null);
                                if (!zzh(connectionResult)) {
                                    this.zzjy.zzc(connectionResult, this.zzkf);
                                }
                            }
                        } else {
                            com_google_android_gms_common_api_internal_zzf.zza(new UnsupportedApiCallException(feature2));
                        }
                        return false;
                    }
                    this.zzki.remove(new zzb(this.zzhc, feature2, null));
                }
                zzc(com_google_android_gms_common_api_internal_zzb);
                return true;
            }
            zzc(com_google_android_gms_common_api_internal_zzb);
            return true;
        }

        @WorkerThread
        private final boolean zzb(boolean z) {
            Preconditions.checkHandlerThread(this.zzjy.handler);
            if (!this.zzka.isConnected() || this.zzke.size() != 0) {
                return false;
            }
            if (!this.zzkc.zzaj()) {
                this.zzka.disconnect();
                return true;
            } else if (!z) {
                return false;
            } else {
                zzbr();
                return false;
            }
        }

        @WorkerThread
        private final void zzbj() {
            zzbo();
            zzi(ConnectionResult.RESULT_SUCCESS);
            zzbq();
            for (zzbv com_google_android_gms_common_api_internal_zzbv : this.zzke.values()) {
                try {
                    com_google_android_gms_common_api_internal_zzbv.zzlt.registerListener(this.zzkb, new TaskCompletionSource());
                } catch (DeadObjectException e) {
                    onConnectionSuspended(1);
                    this.zzka.disconnect();
                } catch (RemoteException e2) {
                }
            }
            zzbl();
            zzbr();
        }

        @WorkerThread
        private final void zzbk() {
            zzbo();
            this.zzkh = true;
            this.zzkc.zzal();
            this.zzjy.handler.sendMessageDelayed(Message.obtain(this.zzjy.handler, 9, this.zzhc), this.zzjy.zzjl);
            this.zzjy.handler.sendMessageDelayed(Message.obtain(this.zzjy.handler, 11, this.zzhc), this.zzjy.zzjm);
            this.zzjy.zzjr.flush();
        }

        @WorkerThread
        private final void zzbl() {
            ArrayList arrayList = new ArrayList(this.zzjz);
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                Object obj = arrayList.get(i);
                i++;
                zzb com_google_android_gms_common_api_internal_zzb = (zzb) obj;
                if (!this.zzka.isConnected()) {
                    return;
                }
                if (zzb(com_google_android_gms_common_api_internal_zzb)) {
                    this.zzjz.remove(com_google_android_gms_common_api_internal_zzb);
                }
            }
        }

        @WorkerThread
        private final void zzbq() {
            if (this.zzkh) {
                this.zzjy.handler.removeMessages(11, this.zzhc);
                this.zzjy.handler.removeMessages(9, this.zzhc);
                this.zzkh = false;
            }
        }

        private final void zzbr() {
            this.zzjy.handler.removeMessages(12, this.zzhc);
            this.zzjy.handler.sendMessageDelayed(this.zzjy.handler.obtainMessage(12, this.zzhc), this.zzjy.zzjn);
        }

        @WorkerThread
        private final void zzc(zzb com_google_android_gms_common_api_internal_zzb) {
            com_google_android_gms_common_api_internal_zzb.zza(this.zzkc, requiresSignIn());
            try {
                com_google_android_gms_common_api_internal_zzb.zza(this);
            } catch (DeadObjectException e) {
                onConnectionSuspended(1);
                this.zzka.disconnect();
            }
        }

        @WorkerThread
        private final boolean zzh(@NonNull ConnectionResult connectionResult) {
            boolean z;
            synchronized (GoogleApiManager.lock) {
                if (this.zzjy.zzjv == null || !this.zzjy.zzjw.contains(this.zzhc)) {
                    z = false;
                } else {
                    this.zzjy.zzjv.zzb(connectionResult, this.zzkf);
                    z = true;
                }
            }
            return z;
        }

        @WorkerThread
        private final void zzi(ConnectionResult connectionResult) {
            for (zzj com_google_android_gms_common_api_internal_zzj : this.zzkd) {
                String str = null;
                if (Objects.equal(connectionResult, ConnectionResult.RESULT_SUCCESS)) {
                    str = this.zzka.getEndpointPackageName();
                }
                com_google_android_gms_common_api_internal_zzj.zza(this.zzhc, connectionResult, str);
            }
            this.zzkd.clear();
        }

        @WorkerThread
        public final void connect() {
            Preconditions.checkHandlerThread(this.zzjy.handler);
            if (!this.zzka.isConnected() && !this.zzka.isConnecting()) {
                int clientAvailability = this.zzjy.zzjr.getClientAvailability(this.zzjy.zzjp, this.zzka);
                if (clientAvailability != 0) {
                    onConnectionFailed(new ConnectionResult(clientAvailability, null));
                    return;
                }
                zzcb com_google_android_gms_common_api_internal_GoogleApiManager_zzc = new zzc(this.zzjy, this.zzka, this.zzhc);
                if (this.zzka.requiresSignIn()) {
                    this.zzkg.zza(com_google_android_gms_common_api_internal_GoogleApiManager_zzc);
                }
                this.zzka.connect(com_google_android_gms_common_api_internal_GoogleApiManager_zzc);
            }
        }

        public final int getInstanceId() {
            return this.zzkf;
        }

        final boolean isConnected() {
            return this.zzka.isConnected();
        }

        public final void onConnected(@Nullable Bundle bundle) {
            if (Looper.myLooper() == this.zzjy.handler.getLooper()) {
                zzbj();
            } else {
                this.zzjy.handler.post(new zzbi(this));
            }
        }

        @WorkerThread
        public final void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
            Preconditions.checkHandlerThread(this.zzjy.handler);
            if (this.zzkg != null) {
                this.zzkg.zzbz();
            }
            zzbo();
            this.zzjy.zzjr.flush();
            zzi(connectionResult);
            if (connectionResult.getErrorCode() == 4) {
                zzc(GoogleApiManager.zzjk);
            } else if (this.zzjz.isEmpty()) {
                this.zzkj = connectionResult;
            } else if (!zzh(connectionResult) && !this.zzjy.zzc(connectionResult, this.zzkf)) {
                if (connectionResult.getErrorCode() == 18) {
                    this.zzkh = true;
                }
                if (this.zzkh) {
                    this.zzjy.handler.sendMessageDelayed(Message.obtain(this.zzjy.handler, 9, this.zzhc), this.zzjy.zzjl);
                    return;
                }
                String zzq = this.zzhc.zzq();
                zzc(new Status(17, new StringBuilder(String.valueOf(zzq).length() + 38).append("API: ").append(zzq).append(" is not available on this device.").toString()));
            }
        }

        public final void onConnectionSuspended(int i) {
            if (Looper.myLooper() == this.zzjy.handler.getLooper()) {
                zzbk();
            } else {
                this.zzjy.handler.post(new zzbj(this));
            }
        }

        public final boolean requiresSignIn() {
            return this.zzka.requiresSignIn();
        }

        @WorkerThread
        public final void resume() {
            Preconditions.checkHandlerThread(this.zzjy.handler);
            if (this.zzkh) {
                connect();
            }
        }

        public final void zza(ConnectionResult connectionResult, Api<?> api, boolean z) {
            if (Looper.myLooper() == this.zzjy.handler.getLooper()) {
                onConnectionFailed(connectionResult);
            } else {
                this.zzjy.handler.post(new zzbk(this, connectionResult));
            }
        }

        @WorkerThread
        public final void zza(zzb com_google_android_gms_common_api_internal_zzb) {
            Preconditions.checkHandlerThread(this.zzjy.handler);
            if (!this.zzka.isConnected()) {
                this.zzjz.add(com_google_android_gms_common_api_internal_zzb);
                if (this.zzkj == null || !this.zzkj.hasResolution()) {
                    connect();
                } else {
                    onConnectionFailed(this.zzkj);
                }
            } else if (zzb(com_google_android_gms_common_api_internal_zzb)) {
                zzbr();
            } else {
                this.zzjz.add(com_google_android_gms_common_api_internal_zzb);
            }
        }

        @WorkerThread
        public final void zza(zzj com_google_android_gms_common_api_internal_zzj) {
            Preconditions.checkHandlerThread(this.zzjy.handler);
            this.zzkd.add(com_google_android_gms_common_api_internal_zzj);
        }

        public final Client zzae() {
            return this.zzka;
        }

        @WorkerThread
        public final void zzay() {
            Preconditions.checkHandlerThread(this.zzjy.handler);
            if (this.zzkh) {
                zzbq();
                zzc(this.zzjy.zzjq.isGooglePlayServicesAvailable(this.zzjy.zzjp) == 18 ? new Status(8, "Connection timed out while waiting for Google Play services update to complete.") : new Status(8, "API failed to connect while resuming due to an unknown error."));
                this.zzka.disconnect();
            }
        }

        @WorkerThread
        public final void zzbm() {
            Preconditions.checkHandlerThread(this.zzjy.handler);
            zzc(GoogleApiManager.zzjj);
            this.zzkc.zzak();
            for (ListenerKey com_google_android_gms_common_api_internal_zzg : (ListenerKey[]) this.zzke.keySet().toArray(new ListenerKey[this.zzke.size()])) {
                zza(new zzg(com_google_android_gms_common_api_internal_zzg, new TaskCompletionSource()));
            }
            zzi(new ConnectionResult(4));
            if (this.zzka.isConnected()) {
                this.zzka.onUserSignOut(new zzbl(this));
            }
        }

        public final Map<ListenerKey<?>, zzbv> zzbn() {
            return this.zzke;
        }

        @WorkerThread
        public final void zzbo() {
            Preconditions.checkHandlerThread(this.zzjy.handler);
            this.zzkj = null;
        }

        @WorkerThread
        public final ConnectionResult zzbp() {
            Preconditions.checkHandlerThread(this.zzjy.handler);
            return this.zzkj;
        }

        @WorkerThread
        public final boolean zzbs() {
            return zzb(true);
        }

        final SignInClient zzbt() {
            return this.zzkg == null ? null : this.zzkg.zzbt();
        }

        @WorkerThread
        public final void zzc(Status status) {
            Preconditions.checkHandlerThread(this.zzjy.handler);
            for (zzb zza : this.zzjz) {
                zza.zza(status);
            }
            this.zzjz.clear();
        }

        @WorkerThread
        public final void zzg(@NonNull ConnectionResult connectionResult) {
            Preconditions.checkHandlerThread(this.zzjy.handler);
            this.zzka.disconnect();
            onConnectionFailed(connectionResult);
        }
    }

    @KeepForSdk
    private GoogleApiManager(Context context, Looper looper, GoogleApiAvailability googleApiAvailability) {
        this.zzjp = context;
        this.handler = new Handler(looper, this);
        this.zzjq = googleApiAvailability;
        this.zzjr = new GoogleApiAvailabilityCache(googleApiAvailability);
        this.handler.sendMessage(this.handler.obtainMessage(6));
    }

    @KeepForSdk
    public static void reportSignOut() {
        synchronized (lock) {
            if (zzjo != null) {
                GoogleApiManager googleApiManager = zzjo;
                googleApiManager.zzjt.incrementAndGet();
                googleApiManager.handler.sendMessageAtFrontOfQueue(googleApiManager.handler.obtainMessage(10));
            }
        }
    }

    public static GoogleApiManager zzb(Context context) {
        GoogleApiManager googleApiManager;
        synchronized (lock) {
            if (zzjo == null) {
                HandlerThread handlerThread = new HandlerThread("GoogleApiHandler", 9);
                handlerThread.start();
                zzjo = new GoogleApiManager(context.getApplicationContext(), handlerThread.getLooper(), GoogleApiAvailability.getInstance());
            }
            googleApiManager = zzjo;
        }
        return googleApiManager;
    }

    @WorkerThread
    private final void zzb(GoogleApi<?> googleApi) {
        zzh zzm = googleApi.zzm();
        zza com_google_android_gms_common_api_internal_GoogleApiManager_zza = (zza) this.zzju.get(zzm);
        if (com_google_android_gms_common_api_internal_GoogleApiManager_zza == null) {
            com_google_android_gms_common_api_internal_GoogleApiManager_zza = new zza(this, googleApi);
            this.zzju.put(zzm, com_google_android_gms_common_api_internal_GoogleApiManager_zza);
        }
        if (com_google_android_gms_common_api_internal_GoogleApiManager_zza.requiresSignIn()) {
            this.zzjx.add(zzm);
        }
        com_google_android_gms_common_api_internal_GoogleApiManager_zza.connect();
    }

    public static GoogleApiManager zzbf() {
        GoogleApiManager googleApiManager;
        synchronized (lock) {
            Preconditions.checkNotNull(zzjo, "Must guarantee manager is non-null before using getInstance");
            googleApiManager = zzjo;
        }
        return googleApiManager;
    }

    @WorkerThread
    public boolean handleMessage(Message message) {
        zzh zzm;
        zza com_google_android_gms_common_api_internal_GoogleApiManager_zza;
        zzb com_google_android_gms_common_api_internal_GoogleApiManager_zzb;
        switch (message.what) {
            case 1:
                this.zzjn = ((Boolean) message.obj).booleanValue() ? 10000 : 300000;
                this.handler.removeMessages(12);
                for (zzh obtainMessage : this.zzju.keySet()) {
                    this.handler.sendMessageDelayed(this.handler.obtainMessage(12, obtainMessage), this.zzjn);
                }
                break;
            case 2:
                zzj com_google_android_gms_common_api_internal_zzj = (zzj) message.obj;
                for (zzh zzm2 : com_google_android_gms_common_api_internal_zzj.zzs()) {
                    zza com_google_android_gms_common_api_internal_GoogleApiManager_zza2 = (zza) this.zzju.get(zzm2);
                    if (com_google_android_gms_common_api_internal_GoogleApiManager_zza2 == null) {
                        com_google_android_gms_common_api_internal_zzj.zza(zzm2, new ConnectionResult(13), null);
                        break;
                    } else if (com_google_android_gms_common_api_internal_GoogleApiManager_zza2.isConnected()) {
                        com_google_android_gms_common_api_internal_zzj.zza(zzm2, ConnectionResult.RESULT_SUCCESS, com_google_android_gms_common_api_internal_GoogleApiManager_zza2.zzae().getEndpointPackageName());
                    } else if (com_google_android_gms_common_api_internal_GoogleApiManager_zza2.zzbp() != null) {
                        com_google_android_gms_common_api_internal_zzj.zza(zzm2, com_google_android_gms_common_api_internal_GoogleApiManager_zza2.zzbp(), null);
                    } else {
                        com_google_android_gms_common_api_internal_GoogleApiManager_zza2.zza(com_google_android_gms_common_api_internal_zzj);
                    }
                }
                break;
            case 3:
                for (zza com_google_android_gms_common_api_internal_GoogleApiManager_zza3 : this.zzju.values()) {
                    com_google_android_gms_common_api_internal_GoogleApiManager_zza3.zzbo();
                    com_google_android_gms_common_api_internal_GoogleApiManager_zza3.connect();
                }
                break;
            case 4:
            case 8:
            case 13:
                zzbu com_google_android_gms_common_api_internal_zzbu = (zzbu) message.obj;
                com_google_android_gms_common_api_internal_GoogleApiManager_zza = (zza) this.zzju.get(com_google_android_gms_common_api_internal_zzbu.zzlr.zzm());
                if (com_google_android_gms_common_api_internal_GoogleApiManager_zza == null) {
                    zzb(com_google_android_gms_common_api_internal_zzbu.zzlr);
                    com_google_android_gms_common_api_internal_GoogleApiManager_zza = (zza) this.zzju.get(com_google_android_gms_common_api_internal_zzbu.zzlr.zzm());
                }
                if (com_google_android_gms_common_api_internal_GoogleApiManager_zza.requiresSignIn() && this.zzjt.get() != com_google_android_gms_common_api_internal_zzbu.zzlq) {
                    com_google_android_gms_common_api_internal_zzbu.zzlp.zza(zzjj);
                    com_google_android_gms_common_api_internal_GoogleApiManager_zza.zzbm();
                    break;
                }
                com_google_android_gms_common_api_internal_GoogleApiManager_zza.zza(com_google_android_gms_common_api_internal_zzbu.zzlp);
                break;
                break;
            case 5:
                String errorString;
                String errorMessage;
                int i = message.arg1;
                ConnectionResult connectionResult = (ConnectionResult) message.obj;
                for (zza com_google_android_gms_common_api_internal_GoogleApiManager_zza4 : this.zzju.values()) {
                    if (com_google_android_gms_common_api_internal_GoogleApiManager_zza4.getInstanceId() == i) {
                        if (com_google_android_gms_common_api_internal_GoogleApiManager_zza4 != null) {
                            Log.wtf("GoogleApiManager", "Could not find API instance " + i + " while trying to fail enqueued calls.", new Exception());
                            break;
                        }
                        errorString = this.zzjq.getErrorString(connectionResult.getErrorCode());
                        errorMessage = connectionResult.getErrorMessage();
                        com_google_android_gms_common_api_internal_GoogleApiManager_zza4.zzc(new Status(17, new StringBuilder((String.valueOf(errorString).length() + 69) + String.valueOf(errorMessage).length()).append("Error resolution was canceled by the user, original error message: ").append(errorString).append(": ").append(errorMessage).toString()));
                        break;
                    }
                }
                com_google_android_gms_common_api_internal_GoogleApiManager_zza4 = null;
                if (com_google_android_gms_common_api_internal_GoogleApiManager_zza4 != null) {
                    Log.wtf("GoogleApiManager", "Could not find API instance " + i + " while trying to fail enqueued calls.", new Exception());
                } else {
                    errorString = this.zzjq.getErrorString(connectionResult.getErrorCode());
                    errorMessage = connectionResult.getErrorMessage();
                    com_google_android_gms_common_api_internal_GoogleApiManager_zza4.zzc(new Status(17, new StringBuilder((String.valueOf(errorString).length() + 69) + String.valueOf(errorMessage).length()).append("Error resolution was canceled by the user, original error message: ").append(errorString).append(": ").append(errorMessage).toString()));
                }
            case 6:
                if (PlatformVersion.isAtLeastIceCreamSandwich() && (this.zzjp.getApplicationContext() instanceof Application)) {
                    BackgroundDetector.initialize((Application) this.zzjp.getApplicationContext());
                    BackgroundDetector.getInstance().addListener(new zzbh(this));
                    if (!BackgroundDetector.getInstance().readCurrentStateIfPossible(true)) {
                        this.zzjn = 300000;
                        break;
                    }
                }
                break;
            case 7:
                zzb((GoogleApi) message.obj);
                break;
            case 9:
                if (this.zzju.containsKey(message.obj)) {
                    ((zza) this.zzju.get(message.obj)).resume();
                    break;
                }
                break;
            case 10:
                for (zzh obtainMessage2 : this.zzjx) {
                    ((zza) this.zzju.remove(obtainMessage2)).zzbm();
                }
                this.zzjx.clear();
                break;
            case 11:
                if (this.zzju.containsKey(message.obj)) {
                    ((zza) this.zzju.get(message.obj)).zzay();
                    break;
                }
                break;
            case 12:
                if (this.zzju.containsKey(message.obj)) {
                    ((zza) this.zzju.get(message.obj)).zzbs();
                    break;
                }
                break;
            case 14:
                zzae com_google_android_gms_common_api_internal_zzae = (zzae) message.obj;
                zzm2 = com_google_android_gms_common_api_internal_zzae.zzm();
                if (!this.zzju.containsKey(zzm2)) {
                    com_google_android_gms_common_api_internal_zzae.zzao().setResult(Boolean.valueOf(false));
                    break;
                }
                com_google_android_gms_common_api_internal_zzae.zzao().setResult(Boolean.valueOf(((zza) this.zzju.get(zzm2)).zzb(false)));
                break;
            case 15:
                com_google_android_gms_common_api_internal_GoogleApiManager_zzb = (zzb) message.obj;
                if (this.zzju.containsKey(zzb.zzc(com_google_android_gms_common_api_internal_GoogleApiManager_zzb))) {
                    ((zza) this.zzju.get(zzb.zzc(com_google_android_gms_common_api_internal_GoogleApiManager_zzb))).zza(com_google_android_gms_common_api_internal_GoogleApiManager_zzb);
                    break;
                }
                break;
            case 16:
                com_google_android_gms_common_api_internal_GoogleApiManager_zzb = (zzb) message.obj;
                if (this.zzju.containsKey(zzb.zzc(com_google_android_gms_common_api_internal_GoogleApiManager_zzb))) {
                    ((zza) this.zzju.get(zzb.zzc(com_google_android_gms_common_api_internal_GoogleApiManager_zzb))).zzb(com_google_android_gms_common_api_internal_GoogleApiManager_zzb);
                    break;
                }
                break;
            default:
                Log.w("GoogleApiManager", "Unknown message id: " + message.what);
                return false;
        }
        return true;
    }

    final void maybeSignOut() {
        this.zzjt.incrementAndGet();
        this.handler.sendMessage(this.handler.obtainMessage(10));
    }

    final PendingIntent zza(zzh<?> com_google_android_gms_common_api_internal_zzh_, int i) {
        zza com_google_android_gms_common_api_internal_GoogleApiManager_zza = (zza) this.zzju.get(com_google_android_gms_common_api_internal_zzh_);
        if (com_google_android_gms_common_api_internal_GoogleApiManager_zza == null) {
            return null;
        }
        SignInClient zzbt = com_google_android_gms_common_api_internal_GoogleApiManager_zza.zzbt();
        return zzbt == null ? null : PendingIntent.getActivity(this.zzjp, i, zzbt.getSignInIntent(), 134217728);
    }

    public final <O extends ApiOptions> Task<Boolean> zza(@NonNull GoogleApi<O> googleApi, @NonNull ListenerKey<?> listenerKey) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.handler.sendMessage(this.handler.obtainMessage(13, new zzbu(new zzg(listenerKey, taskCompletionSource), this.zzjt.get(), googleApi)));
        return taskCompletionSource.getTask();
    }

    public final <O extends ApiOptions> Task<Void> zza(@NonNull GoogleApi<O> googleApi, @NonNull RegisterListenerMethod<AnyClient, ?> registerListenerMethod, @NonNull UnregisterListenerMethod<AnyClient, ?> unregisterListenerMethod) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.handler.sendMessage(this.handler.obtainMessage(8, new zzbu(new zze(new zzbv(registerListenerMethod, unregisterListenerMethod), taskCompletionSource), this.zzjt.get(), googleApi)));
        return taskCompletionSource.getTask();
    }

    public final Task<Map<zzh<?>, String>> zza(Iterable<? extends GoogleApi<?>> iterable) {
        zzj com_google_android_gms_common_api_internal_zzj = new zzj(iterable);
        this.handler.sendMessage(this.handler.obtainMessage(2, com_google_android_gms_common_api_internal_zzj));
        return com_google_android_gms_common_api_internal_zzj.getTask();
    }

    public final void zza(ConnectionResult connectionResult, int i) {
        if (!zzc(connectionResult, i)) {
            this.handler.sendMessage(this.handler.obtainMessage(5, i, 0, connectionResult));
        }
    }

    public final void zza(GoogleApi<?> googleApi) {
        this.handler.sendMessage(this.handler.obtainMessage(7, googleApi));
    }

    public final <O extends ApiOptions> void zza(GoogleApi<O> googleApi, int i, ApiMethodImpl<? extends Result, AnyClient> apiMethodImpl) {
        this.handler.sendMessage(this.handler.obtainMessage(4, new zzbu(new zzd(i, apiMethodImpl), this.zzjt.get(), googleApi)));
    }

    public final <O extends ApiOptions, ResultT> void zza(GoogleApi<O> googleApi, int i, TaskApiCall<AnyClient, ResultT> taskApiCall, TaskCompletionSource<ResultT> taskCompletionSource, StatusExceptionMapper statusExceptionMapper) {
        this.handler.sendMessage(this.handler.obtainMessage(4, new zzbu(new zzf(i, taskApiCall, taskCompletionSource, statusExceptionMapper), this.zzjt.get(), googleApi)));
    }

    public final void zza(@NonNull zzad com_google_android_gms_common_api_internal_zzad) {
        synchronized (lock) {
            if (this.zzjv != com_google_android_gms_common_api_internal_zzad) {
                this.zzjv = com_google_android_gms_common_api_internal_zzad;
                this.zzjw.clear();
            }
            this.zzjw.addAll(com_google_android_gms_common_api_internal_zzad.zzam());
        }
    }

    final void zzb(@NonNull zzad com_google_android_gms_common_api_internal_zzad) {
        synchronized (lock) {
            if (this.zzjv == com_google_android_gms_common_api_internal_zzad) {
                this.zzjv = null;
                this.zzjw.clear();
            }
        }
    }

    public final int zzbg() {
        return this.zzjs.getAndIncrement();
    }

    public final Task<Boolean> zzc(GoogleApi<?> googleApi) {
        zzae com_google_android_gms_common_api_internal_zzae = new zzae(googleApi.zzm());
        this.handler.sendMessage(this.handler.obtainMessage(14, com_google_android_gms_common_api_internal_zzae));
        return com_google_android_gms_common_api_internal_zzae.zzao().getTask();
    }

    final boolean zzc(ConnectionResult connectionResult, int i) {
        return this.zzjq.showWrappedErrorNotification(this.zzjp, connectionResult, i);
    }

    public final void zzr() {
        this.handler.sendMessage(this.handler.obtainMessage(3));
    }
}
