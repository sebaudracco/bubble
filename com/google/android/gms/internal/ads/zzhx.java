package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.dynamite.DynamiteModule.LoadingException;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.ads.dynamite.ModuleDescriptor;

@zzadh
public final class zzhx {
    @VisibleForTesting
    zzen zzaln;
    @VisibleForTesting
    boolean zzalo;

    public zzhx(Context context) {
        zznk.initialize(context);
        if (((Boolean) zzkb.zzik().zzd(zznk.zzbeo)).booleanValue()) {
            try {
                this.zzaln = zzeo.zza(DynamiteModule.load(context, DynamiteModule.PREFER_REMOTE, ModuleDescriptor.MODULE_ID).instantiate("com.google.android.gms.ads.clearcut.DynamiteClearcutLogger"));
                ObjectWrapper.wrap(context);
                this.zzaln.zza(ObjectWrapper.wrap(context), "GMA_SDK");
                this.zzalo = true;
                return;
            } catch (LoadingException e) {
            } catch (RemoteException e2) {
            } catch (NullPointerException e3) {
            }
        } else {
            return;
        }
        zzane.zzck("Cannot dynamite load clearcut");
    }

    public zzhx(Context context, String str, String str2) {
        zznk.initialize(context);
        try {
            this.zzaln = zzeo.zza(DynamiteModule.load(context, DynamiteModule.PREFER_REMOTE, ModuleDescriptor.MODULE_ID).instantiate("com.google.android.gms.ads.clearcut.DynamiteClearcutLogger"));
            ObjectWrapper.wrap(context);
            this.zzaln.zza(ObjectWrapper.wrap(context), str, null);
            this.zzalo = true;
            return;
        } catch (LoadingException e) {
        } catch (RemoteException e2) {
        } catch (NullPointerException e3) {
        }
        zzane.zzck("Cannot dynamite load clearcut");
    }

    public final zzhz zzd(byte[] bArr) {
        return new zzhz(this, bArr, null);
    }
}
