package com.google.android.gms.internal.ads;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.ParcelFileDescriptor.AutoCloseInputStream;
import android.os.ParcelFileDescriptor.AutoCloseOutputStream;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.common.util.IOUtils;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.IOException;

@zzadh
@Class(creator = "LargeParcelTeleporterCreator")
@Reserved({1})
public final class zzaev extends AbstractSafeParcelable {
    public static final Creator<zzaev> CREATOR = new zzaex();
    @Field(id = 2)
    private ParcelFileDescriptor zzcft;
    private Parcelable zzcfu;
    private boolean zzcfv;

    @Constructor
    public zzaev(@Param(id = 2) ParcelFileDescriptor parcelFileDescriptor) {
        this.zzcft = parcelFileDescriptor;
        this.zzcfu = null;
        this.zzcfv = true;
    }

    public zzaev(SafeParcelable safeParcelable) {
        this.zzcft = null;
        this.zzcfu = safeParcelable;
        this.zzcfv = false;
    }

    private final <T> ParcelFileDescriptor zze(byte[] bArr) {
        Throwable e;
        ParcelFileDescriptor parcelFileDescriptor = null;
        Closeable autoCloseOutputStream;
        try {
            ParcelFileDescriptor[] createPipe = ParcelFileDescriptor.createPipe();
            autoCloseOutputStream = new AutoCloseOutputStream(createPipe[1]);
            try {
                new Thread(new zzaew(this, autoCloseOutputStream, bArr)).start();
                return createPipe[0];
            } catch (IOException e2) {
                e = e2;
            }
        } catch (IOException e3) {
            e = e3;
            autoCloseOutputStream = parcelFileDescriptor;
            zzane.zzb("Error transporting the ad response", e);
            zzbv.zzeo().zza(e, "LargeParcelTeleporter.pipeData.2");
            IOUtils.closeQuietly(autoCloseOutputStream);
            return parcelFileDescriptor;
        }
    }

    private final ParcelFileDescriptor zzoc() {
        if (this.zzcft == null) {
            Parcel obtain = Parcel.obtain();
            try {
                this.zzcfu.writeToParcel(obtain, 0);
                byte[] marshall = obtain.marshall();
                this.zzcft = zze(marshall);
            } finally {
                obtain.recycle();
            }
        }
        return this.zzcft;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        zzoc();
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzcft, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final <T extends SafeParcelable> T zza(Creator<T> creator) {
        if (this.zzcfv) {
            if (this.zzcft == null) {
                zzane.m3427e("File descriptor is empty, returning null.");
                return null;
            }
            Closeable dataInputStream = new DataInputStream(new AutoCloseInputStream(this.zzcft));
            byte[] bArr;
            try {
                bArr = new byte[dataInputStream.readInt()];
                dataInputStream.readFully(bArr, 0, bArr.length);
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.unmarshall(bArr, 0, bArr.length);
                    obtain.setDataPosition(0);
                    this.zzcfu = (SafeParcelable) creator.createFromParcel(obtain);
                    this.zzcfv = false;
                } finally {
                    obtain.recycle();
                }
            } catch (IOException e) {
                bArr = e;
                zzane.zzb("Could not read from parcel file descriptor", bArr);
                return null;
            } finally {
                IOUtils.closeQuietly(dataInputStream);
            }
        }
        return (SafeParcelable) this.zzcfu;
    }
}
