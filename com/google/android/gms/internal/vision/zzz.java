package com.google.android.gms.internal.vision;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.DynamiteModule.LoadingException;

public final class zzz extends zzj<zzp> {
    private final zzaa zzdb;

    public zzz(Context context, zzaa com_google_android_gms_internal_vision_zzaa) {
        super(context, "TextNativeHandle");
        this.zzdb = com_google_android_gms_internal_vision_zzaa;
        zzh();
    }

    protected final /* synthetic */ Object zza(DynamiteModule dynamiteModule, Context context) throws RemoteException, LoadingException {
        zzr com_google_android_gms_internal_vision_zzr;
        IBinder instantiate = dynamiteModule.instantiate("com.google.android.gms.vision.text.ChimeraNativeTextRecognizerCreator");
        if (instantiate == null) {
            com_google_android_gms_internal_vision_zzr = null;
        } else {
            IInterface queryLocalInterface = instantiate.queryLocalInterface("com.google.android.gms.vision.text.internal.client.INativeTextRecognizerCreator");
            com_google_android_gms_internal_vision_zzr = queryLocalInterface instanceof zzr ? (zzr) queryLocalInterface : new zzs(instantiate);
        }
        return com_google_android_gms_internal_vision_zzr == null ? null : com_google_android_gms_internal_vision_zzr.zza(ObjectWrapper.wrap(context), this.zzdb);
    }

    public final zzt[] zza(Bitmap bitmap, zzk com_google_android_gms_internal_vision_zzk, zzv com_google_android_gms_internal_vision_zzv) {
        if (!isOperational()) {
            return new zzt[0];
        }
        try {
            return ((zzp) zzh()).zza(ObjectWrapper.wrap(bitmap), com_google_android_gms_internal_vision_zzk, com_google_android_gms_internal_vision_zzv);
        } catch (Throwable e) {
            Log.e("TextNativeHandle", "Error calling native text recognizer", e);
            return new zzt[0];
        }
    }

    protected final void zze() throws RemoteException {
        ((zzp) zzh()).zzi();
    }
}
