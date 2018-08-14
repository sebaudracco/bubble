package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.util.IOUtils;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

final class zzaew implements Runnable {
    private final /* synthetic */ OutputStream zzcfw;
    private final /* synthetic */ byte[] zzcfx;

    zzaew(zzaev com_google_android_gms_internal_ads_zzaev, OutputStream outputStream, byte[] bArr) {
        this.zzcfw = outputStream;
        this.zzcfx = bArr;
    }

    public final void run() {
        Closeable dataOutputStream;
        Throwable e;
        try {
            dataOutputStream = new DataOutputStream(this.zzcfw);
            try {
                dataOutputStream.writeInt(this.zzcfx.length);
                dataOutputStream.write(this.zzcfx);
                IOUtils.closeQuietly(dataOutputStream);
            } catch (IOException e2) {
                e = e2;
                try {
                    zzakb.zzb("Error transporting the ad response", e);
                    zzbv.zzeo().zza(e, "LargeParcelTeleporter.pipeData.1");
                    if (dataOutputStream != null) {
                        IOUtils.closeQuietly(dataOutputStream);
                    } else {
                        IOUtils.closeQuietly(this.zzcfw);
                    }
                } catch (Throwable th) {
                    e = th;
                    if (dataOutputStream != null) {
                        IOUtils.closeQuietly(this.zzcfw);
                    } else {
                        IOUtils.closeQuietly(dataOutputStream);
                    }
                    throw e;
                }
            }
        } catch (IOException e3) {
            e = e3;
            dataOutputStream = null;
            zzakb.zzb("Error transporting the ad response", e);
            zzbv.zzeo().zza(e, "LargeParcelTeleporter.pipeData.1");
            if (dataOutputStream != null) {
                IOUtils.closeQuietly(this.zzcfw);
            } else {
                IOUtils.closeQuietly(dataOutputStream);
            }
        } catch (Throwable th2) {
            e = th2;
            dataOutputStream = null;
            if (dataOutputStream != null) {
                IOUtils.closeQuietly(dataOutputStream);
            } else {
                IOUtils.closeQuietly(this.zzcfw);
            }
            throw e;
        }
    }
}
