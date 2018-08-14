package com.google.firebase.internal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

public final class zzb {
    private static final AtomicReference<zzb> zzq = new AtomicReference();

    private zzb(Context context) {
    }

    public static FirebaseOptions zzb(@NonNull String str) {
        return null;
    }

    public static void zzb(@NonNull FirebaseApp firebaseApp) {
    }

    public static zzb zze(Context context) {
        zzq.compareAndSet(null, new zzb(context));
        return (zzb) zzq.get();
    }

    @Nullable
    public static zzb zzq() {
        return (zzb) zzq.get();
    }

    public static Set<String> zzr() {
        return Collections.emptySet();
    }
}
