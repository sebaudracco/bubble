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
import com.google.android.gms.vision.barcode.Barcode;
import java.nio.ByteBuffer;

public final class zze extends zzj<zzf> {
    private final zzc zzbb;

    public zze(Context context, zzc com_google_android_gms_internal_vision_zzc) {
        super(context, "BarcodeNativeHandle");
        this.zzbb = com_google_android_gms_internal_vision_zzc;
        zzh();
    }

    protected final /* synthetic */ Object zza(DynamiteModule dynamiteModule, Context context) throws RemoteException, LoadingException {
        zzh com_google_android_gms_internal_vision_zzh;
        IBinder instantiate = dynamiteModule.instantiate("com.google.android.gms.vision.barcode.ChimeraNativeBarcodeDetectorCreator");
        if (instantiate == null) {
            com_google_android_gms_internal_vision_zzh = null;
        } else {
            IInterface queryLocalInterface = instantiate.queryLocalInterface("com.google.android.gms.vision.barcode.internal.client.INativeBarcodeDetectorCreator");
            com_google_android_gms_internal_vision_zzh = queryLocalInterface instanceof zzh ? (zzh) queryLocalInterface : new zzi(instantiate);
        }
        return com_google_android_gms_internal_vision_zzh == null ? null : com_google_android_gms_internal_vision_zzh.zza(ObjectWrapper.wrap(context), this.zzbb);
    }

    public final Barcode[] zza(Bitmap bitmap, zzk com_google_android_gms_internal_vision_zzk) {
        if (!isOperational()) {
            return new Barcode[0];
        }
        try {
            return ((zzf) zzh()).zzb(ObjectWrapper.wrap(bitmap), com_google_android_gms_internal_vision_zzk);
        } catch (Throwable e) {
            Log.e("BarcodeNativeHandle", "Error calling native barcode detector", e);
            return new Barcode[0];
        }
    }

    public final Barcode[] zza(ByteBuffer byteBuffer, zzk com_google_android_gms_internal_vision_zzk) {
        if (!isOperational()) {
            return new Barcode[0];
        }
        try {
            return ((zzf) zzh()).zza(ObjectWrapper.wrap(byteBuffer), com_google_android_gms_internal_vision_zzk);
        } catch (Throwable e) {
            Log.e("BarcodeNativeHandle", "Error calling native barcode detector", e);
            return new Barcode[0];
        }
    }

    protected final void zze() throws RemoteException {
        if (isOperational()) {
            ((zzf) zzh()).zzf();
        }
    }
}
