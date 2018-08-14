package com.google.firebase;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.ArraySet;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.internal.BackgroundDetector;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.util.ProcessUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.components.Component;
import com.google.firebase.components.zzc;
import com.google.firebase.components.zzg;
import com.google.firebase.internal.InternalTokenProvider;
import com.google.firebase.internal.InternalTokenResult;
import com.google.firebase.internal.zzb;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.concurrent.GuardedBy;
import org.slf4j.Marker;

public class FirebaseApp {
    public static final String DEFAULT_APP_NAME = "[DEFAULT]";
    private static final Object sLock = new Object();
    private static final List<String> zza = Arrays.asList(new String[]{"com.google.firebase.auth.FirebaseAuth", "com.google.firebase.iid.FirebaseInstanceId"});
    private static final List<String> zzb = Collections.singletonList("com.google.firebase.crash.FirebaseCrash");
    private static final List<String> zzc = Arrays.asList(new String[]{"com.google.android.gms.measurement.AppMeasurement"});
    private static final List<String> zzd = Arrays.asList(new String[0]);
    private static final Set<String> zze = Collections.emptySet();
    @GuardedBy("sLock")
    static final Map<String, FirebaseApp> zzf = new ArrayMap();
    private final String mName;
    private final Context zzg;
    private final FirebaseOptions zzh;
    private final zzg zzi;
    private final AtomicBoolean zzj = new AtomicBoolean(false);
    private final AtomicBoolean zzk = new AtomicBoolean();
    private final List<IdTokenListener> zzl = new CopyOnWriteArrayList();
    private final List<BackgroundStateChangeListener> zzm = new CopyOnWriteArrayList();
    private final List<Object> zzn = new CopyOnWriteArrayList();
    private InternalTokenProvider zzo;
    private IdTokenListenersCountChangedListener zzp;

    @KeepForSdk
    public interface BackgroundStateChangeListener {
        @KeepForSdk
        void onBackgroundStateChanged(boolean z);
    }

    @KeepForSdk
    public interface IdTokenListener {
        @KeepForSdk
        void onIdTokenChanged(@NonNull InternalTokenResult internalTokenResult);
    }

    @KeepForSdk
    public interface IdTokenListenersCountChangedListener {
        @KeepForSdk
        void onListenerCountChanged(int i);
    }

    @TargetApi(24)
    private static class zza extends BroadcastReceiver {
        private static AtomicReference<zza> zzq = new AtomicReference();
        private final Context zzg;

        private zza(Context context) {
            this.zzg = context;
        }

        private static void zza(Context context) {
            if (zzq.get() == null) {
                BroadcastReceiver com_google_firebase_FirebaseApp_zza = new zza(context);
                if (zzq.compareAndSet(null, com_google_firebase_FirebaseApp_zza)) {
                    context.registerReceiver(com_google_firebase_FirebaseApp_zza, new IntentFilter("android.intent.action.USER_UNLOCKED"));
                }
            }
        }

        public final void onReceive(Context context, Intent intent) {
            synchronized (FirebaseApp.sLock) {
                for (FirebaseApp zza : FirebaseApp.zzf.values()) {
                    zza.zzc();
                }
            }
            this.zzg.unregisterReceiver(this);
        }
    }

    private FirebaseApp(Context context, String str, FirebaseOptions firebaseOptions) {
        this.zzg = (Context) Preconditions.checkNotNull(context);
        this.mName = Preconditions.checkNotEmpty(str);
        this.zzh = (FirebaseOptions) Preconditions.checkNotNull(firebaseOptions);
        this.zzp = new com.google.firebase.internal.zza();
        this.zzi = new zzg(new zzc(this.zzg).zzj(), Component.of(Context.class, this.zzg), Component.of(FirebaseApp.class, this), Component.of(FirebaseOptions.class, this.zzh));
    }

    public static List<FirebaseApp> getApps(Context context) {
        List<FirebaseApp> arrayList;
        zzb.zze(context);
        synchronized (sLock) {
            arrayList = new ArrayList(zzf.values());
            zzb.zzq();
            Set<String> zzr = zzb.zzr();
            zzr.removeAll(zzf.keySet());
            for (String str : zzr) {
                zzb.zzb(str);
                arrayList.add(initializeApp(context, null, str));
            }
        }
        return arrayList;
    }

    @Nullable
    public static FirebaseApp getInstance() {
        FirebaseApp firebaseApp;
        synchronized (sLock) {
            firebaseApp = (FirebaseApp) zzf.get(DEFAULT_APP_NAME);
            if (firebaseApp == null) {
                String myProcessName = ProcessUtils.getMyProcessName();
                throw new IllegalStateException(new StringBuilder(String.valueOf(myProcessName).length() + 116).append("Default FirebaseApp is not initialized in this process ").append(myProcessName).append(". Make sure to call FirebaseApp.initializeApp(Context) first.").toString());
            }
        }
        return firebaseApp;
    }

    public static FirebaseApp getInstance(@NonNull String str) {
        FirebaseApp firebaseApp;
        synchronized (sLock) {
            firebaseApp = (FirebaseApp) zzf.get(str.trim());
            if (firebaseApp != null) {
            } else {
                String str2;
                Iterable zzb = zzb();
                if (zzb.isEmpty()) {
                    str2 = "";
                } else {
                    String str3 = "Available app names: ";
                    str2 = String.valueOf(TextUtils.join(", ", zzb));
                    str2 = str2.length() != 0 ? str3.concat(str2) : new String(str3);
                }
                throw new IllegalStateException(String.format("FirebaseApp with name %s doesn't exist. %s", new Object[]{str, str2}));
            }
        }
        return firebaseApp;
    }

    @Nullable
    public static FirebaseApp initializeApp(Context context) {
        FirebaseApp instance;
        synchronized (sLock) {
            if (zzf.containsKey(DEFAULT_APP_NAME)) {
                instance = getInstance();
            } else {
                FirebaseOptions fromResource = FirebaseOptions.fromResource(context);
                if (fromResource == null) {
                    instance = null;
                } else {
                    instance = initializeApp(context, fromResource);
                }
            }
        }
        return instance;
    }

    public static FirebaseApp initializeApp(Context context, FirebaseOptions firebaseOptions) {
        return initializeApp(context, firebaseOptions, DEFAULT_APP_NAME);
    }

    public static FirebaseApp initializeApp(Context context, FirebaseOptions firebaseOptions, String str) {
        FirebaseApp firebaseApp;
        zzb.zze(context);
        if (PlatformVersion.isAtLeastIceCreamSandwich() && (context.getApplicationContext() instanceof Application)) {
            BackgroundDetector.initialize((Application) context.getApplicationContext());
            BackgroundDetector.getInstance().addListener(new zza());
        }
        String trim = str.trim();
        if (context.getApplicationContext() != null) {
            context = context.getApplicationContext();
        }
        synchronized (sLock) {
            Preconditions.checkState(!zzf.containsKey(trim), new StringBuilder(String.valueOf(trim).length() + 33).append("FirebaseApp name ").append(trim).append(" already exists!").toString());
            Preconditions.checkNotNull(context, "Application context cannot be null.");
            firebaseApp = new FirebaseApp(context, trim, firebaseOptions);
            zzf.put(trim, firebaseApp);
        }
        zzb.zzb(firebaseApp);
        firebaseApp.zzc();
        return firebaseApp;
    }

    @KeepForSdk
    public static void onBackgroundStateChanged(boolean z) {
        synchronized (sLock) {
            ArrayList arrayList = new ArrayList(zzf.values());
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                Object obj = arrayList.get(i);
                i++;
                FirebaseApp firebaseApp = (FirebaseApp) obj;
                if (firebaseApp.zzj.get()) {
                    firebaseApp.zza(z);
                }
            }
        }
    }

    private final void zza() {
        Preconditions.checkState(!this.zzk.get(), "FirebaseApp was deleted");
    }

    private static <T> void zza(Class<T> cls, T t, Iterable<String> iterable, boolean z) {
        String valueOf;
        for (String valueOf2 : iterable) {
            if (z) {
                try {
                    if (!zzd.contains(valueOf2)) {
                    }
                } catch (ClassNotFoundException e) {
                    if (zze.contains(valueOf2)) {
                        throw new IllegalStateException(String.valueOf(valueOf2).concat(" is missing, but is required. Check if it has been removed by Proguard."));
                    }
                    Log.d("FirebaseApp", String.valueOf(valueOf2).concat(" is not linked. Skipping initialization."));
                } catch (NoSuchMethodException e2) {
                    throw new IllegalStateException(String.valueOf(valueOf2).concat("#getInstance has been removed by Proguard. Add keep rule to prevent it."));
                } catch (Throwable e3) {
                    Log.wtf("FirebaseApp", "Firebase API initialization failure.", e3);
                } catch (Throwable e4) {
                    String str = "FirebaseApp";
                    String str2 = "Failed to initialize ";
                    valueOf2 = String.valueOf(valueOf2);
                    Log.wtf(str, valueOf2.length() != 0 ? str2.concat(valueOf2) : new String(str2), e4);
                }
            }
            Method method = Class.forName(valueOf2).getMethod("getInstance", new Class[]{cls});
            int modifiers = method.getModifiers();
            if (Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers)) {
                method.invoke(null, new Object[]{t});
            }
        }
    }

    private final void zza(boolean z) {
        Log.d("FirebaseApp", "Notifying background state change listeners.");
        for (BackgroundStateChangeListener onBackgroundStateChanged : this.zzm) {
            onBackgroundStateChanged.onBackgroundStateChanged(z);
        }
    }

    private static List<String> zzb() {
        Collection arraySet = new ArraySet();
        synchronized (sLock) {
            for (FirebaseApp name : zzf.values()) {
                arraySet.add(name.getName());
            }
            if (zzb.zzq() != null) {
                arraySet.addAll(zzb.zzr());
            }
        }
        List<String> arrayList = new ArrayList(arraySet);
        Collections.sort(arrayList);
        return arrayList;
    }

    private final void zzc() {
        boolean isDeviceProtectedStorage = ContextCompat.isDeviceProtectedStorage(this.zzg);
        if (isDeviceProtectedStorage) {
            zza.zza(this.zzg);
        } else {
            this.zzi.zzb(isDefaultApp());
        }
        zza(FirebaseApp.class, this, zza, isDeviceProtectedStorage);
        if (isDefaultApp()) {
            zza(FirebaseApp.class, this, zzb, isDeviceProtectedStorage);
            zza(Context.class, this.zzg, zzc, isDeviceProtectedStorage);
        }
    }

    @KeepForSdk
    public void addBackgroundStateChangeListener(BackgroundStateChangeListener backgroundStateChangeListener) {
        zza();
        if (this.zzj.get() && BackgroundDetector.getInstance().isInBackground()) {
            backgroundStateChangeListener.onBackgroundStateChanged(true);
        }
        this.zzm.add(backgroundStateChangeListener);
    }

    @KeepForSdk
    public void addIdTokenListener(@NonNull IdTokenListener idTokenListener) {
        zza();
        Preconditions.checkNotNull(idTokenListener);
        this.zzl.add(idTokenListener);
        this.zzp.onListenerCountChanged(this.zzl.size());
    }

    public boolean equals(Object obj) {
        return !(obj instanceof FirebaseApp) ? false : this.mName.equals(((FirebaseApp) obj).getName());
    }

    @KeepForSdk
    public <T> T get(Class<T> cls) {
        zza();
        return this.zzi.get(cls);
    }

    @NonNull
    public Context getApplicationContext() {
        zza();
        return this.zzg;
    }

    @NonNull
    public String getName() {
        zza();
        return this.mName;
    }

    @NonNull
    public FirebaseOptions getOptions() {
        zza();
        return this.zzh;
    }

    @KeepForSdk
    public String getPersistenceKey() {
        String encodeUrlSafeNoPadding = Base64Utils.encodeUrlSafeNoPadding(getName().getBytes());
        String encodeUrlSafeNoPadding2 = Base64Utils.encodeUrlSafeNoPadding(getOptions().getApplicationId().getBytes());
        return new StringBuilder((String.valueOf(encodeUrlSafeNoPadding).length() + 1) + String.valueOf(encodeUrlSafeNoPadding2).length()).append(encodeUrlSafeNoPadding).append(Marker.ANY_NON_NULL_MARKER).append(encodeUrlSafeNoPadding2).toString();
    }

    @KeepForSdk
    public Task<GetTokenResult> getToken(boolean z) {
        zza();
        return this.zzo == null ? Tasks.forException(new FirebaseApiNotAvailableException("firebase-auth is not linked, please fall back to unauthenticated mode.")) : this.zzo.getAccessToken(z);
    }

    @Nullable
    public final String getUid() throws FirebaseApiNotAvailableException {
        zza();
        if (this.zzo != null) {
            return this.zzo.getUid();
        }
        throw new FirebaseApiNotAvailableException("firebase-auth is not linked, please fall back to unauthenticated mode.");
    }

    public int hashCode() {
        return this.mName.hashCode();
    }

    @KeepForSdk
    @VisibleForTesting
    public boolean isDefaultApp() {
        return DEFAULT_APP_NAME.equals(getName());
    }

    @UiThread
    @KeepForSdk
    public void notifyIdTokenListeners(@NonNull InternalTokenResult internalTokenResult) {
        Log.d("FirebaseApp", "Notifying auth state listeners.");
        int i = 0;
        for (IdTokenListener onIdTokenChanged : this.zzl) {
            onIdTokenChanged.onIdTokenChanged(internalTokenResult);
            i++;
        }
        Log.d("FirebaseApp", String.format("Notified %d auth state listeners.", new Object[]{Integer.valueOf(i)}));
    }

    @KeepForSdk
    public void removeBackgroundStateChangeListener(BackgroundStateChangeListener backgroundStateChangeListener) {
        zza();
        this.zzm.remove(backgroundStateChangeListener);
    }

    public void setAutomaticResourceManagementEnabled(boolean z) {
        zza();
        if (this.zzj.compareAndSet(!z, z)) {
            boolean isInBackground = BackgroundDetector.getInstance().isInBackground();
            if (z && isInBackground) {
                zza(true);
            } else if (!z && isInBackground) {
                zza(false);
            }
        }
    }

    @KeepForSdk
    public void setIdTokenListenersCountChangedListener(@NonNull IdTokenListenersCountChangedListener idTokenListenersCountChangedListener) {
        this.zzp = (IdTokenListenersCountChangedListener) Preconditions.checkNotNull(idTokenListenersCountChangedListener);
        this.zzp.onListenerCountChanged(this.zzl.size());
    }

    @KeepForSdk
    public void setTokenProvider(@NonNull InternalTokenProvider internalTokenProvider) {
        this.zzo = (InternalTokenProvider) Preconditions.checkNotNull(internalTokenProvider);
    }

    public String toString() {
        return Objects.toStringHelper(this).add("name", this.mName).add("options", this.zzh).toString();
    }

    public final void zza(@NonNull IdTokenListener idTokenListener) {
        zza();
        Preconditions.checkNotNull(idTokenListener);
        this.zzl.remove(idTokenListener);
        this.zzp.onListenerCountChanged(this.zzl.size());
    }
}
