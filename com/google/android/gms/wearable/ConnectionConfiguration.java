package com.google.android.gms.wearable;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;

@Class(creator = "ConnectionConfigurationCreator")
@Reserved({1})
public class ConnectionConfiguration extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Creator<ConnectionConfiguration> CREATOR = new zzg();
    @Field(getter = "getName", id = 2)
    private final String name;
    @Field(getter = "getType", id = 4)
    private final int type;
    @Field(getter = "getAddress", id = 3)
    private final String zzi;
    @Field(getter = "getRole", id = 5)
    private final int zzj;
    @Field(getter = "isEnabled", id = 6)
    private final boolean zzk;
    @Field(getter = "isConnected", id = 7)
    private volatile boolean zzl;
    @Field(getter = "getPeerNodeId", id = 8)
    private volatile String zzm;
    @Field(getter = "getBtlePriority", id = 9)
    private boolean zzn;
    @Field(getter = "getNodeId", id = 10)
    private String zzo;

    @Constructor
    ConnectionConfiguration(@Param(id = 2) String str, @Param(id = 3) String str2, @Param(id = 4) int i, @Param(id = 5) int i2, @Param(id = 6) boolean z, @Param(id = 7) boolean z2, @Param(id = 8) String str3, @Param(id = 9) boolean z3, @Param(id = 10) String str4) {
        this.name = str;
        this.zzi = str2;
        this.type = i;
        this.zzj = i2;
        this.zzk = z;
        this.zzl = z2;
        this.zzm = str3;
        this.zzn = z3;
        this.zzo = str4;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ConnectionConfiguration)) {
            return false;
        }
        ConnectionConfiguration connectionConfiguration = (ConnectionConfiguration) obj;
        return Objects.equal(this.name, connectionConfiguration.name) && Objects.equal(this.zzi, connectionConfiguration.zzi) && Objects.equal(Integer.valueOf(this.type), Integer.valueOf(connectionConfiguration.type)) && Objects.equal(Integer.valueOf(this.zzj), Integer.valueOf(connectionConfiguration.zzj)) && Objects.equal(Boolean.valueOf(this.zzk), Boolean.valueOf(connectionConfiguration.zzk)) && Objects.equal(Boolean.valueOf(this.zzn), Boolean.valueOf(connectionConfiguration.zzn));
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{this.name, this.zzi, Integer.valueOf(this.type), Integer.valueOf(this.zzj), Boolean.valueOf(this.zzk), Boolean.valueOf(this.zzn)});
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("ConnectionConfiguration[ ");
        String str = "mName=";
        String valueOf = String.valueOf(this.name);
        stringBuilder.append(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        str = ", mAddress=";
        valueOf = String.valueOf(this.zzi);
        stringBuilder.append(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        stringBuilder.append(", mType=" + this.type);
        stringBuilder.append(", mRole=" + this.zzj);
        stringBuilder.append(", mEnabled=" + this.zzk);
        stringBuilder.append(", mIsConnected=" + this.zzl);
        str = ", mPeerNodeId=";
        valueOf = String.valueOf(this.zzm);
        stringBuilder.append(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        stringBuilder.append(", mBtlePriority=" + this.zzn);
        str = ", mNodeId=";
        valueOf = String.valueOf(this.zzo);
        stringBuilder.append(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.name, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzi, false);
        SafeParcelWriter.writeInt(parcel, 4, this.type);
        SafeParcelWriter.writeInt(parcel, 5, this.zzj);
        SafeParcelWriter.writeBoolean(parcel, 6, this.zzk);
        SafeParcelWriter.writeBoolean(parcel, 7, this.zzl);
        SafeParcelWriter.writeString(parcel, 8, this.zzm, false);
        SafeParcelWriter.writeBoolean(parcel, 9, this.zzn);
        SafeParcelWriter.writeString(parcel, 10, this.zzo, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
