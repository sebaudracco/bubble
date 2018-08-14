package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.wallet.wobs.CommonWalletObject;
import com.google.android.gms.wallet.wobs.LabelValueRow;
import com.google.android.gms.wallet.wobs.TextModuleData;
import com.google.android.gms.wallet.wobs.TimeInterval;
import com.google.android.gms.wallet.wobs.UriData;
import com.google.android.gms.wallet.wobs.WalletObjectMessage;
import java.util.ArrayList;

@Class(creator = "OfferWalletObjectCreator")
public final class OfferWalletObject extends AbstractSafeParcelable {
    public static final Creator<OfferWalletObject> CREATOR = new zzab();
    @VersionField(getter = "getVersionCode", id = 1)
    private final int versionCode;
    @Field(id = 4)
    CommonWalletObject zzbj;
    @Field(id = 2)
    String zzce;
    @Field(id = 3)
    String zzdq;

    OfferWalletObject() {
        this.versionCode = 3;
    }

    @Constructor
    OfferWalletObject(@Param(id = 1) int i, @Param(id = 2) String str, @Param(id = 3) String str2, @Param(id = 4) CommonWalletObject commonWalletObject) {
        this.versionCode = i;
        this.zzdq = str2;
        if (i < 3) {
            this.zzbj = CommonWalletObject.zze().zza(str).zzf();
        } else {
            this.zzbj = commonWalletObject;
        }
    }

    public static Builder newBuilder() {
        return new Builder(new OfferWalletObject(), null);
    }

    public final String getBarcodeAlternateText() {
        return this.zzbj.getBarcodeAlternateText();
    }

    public final String getBarcodeLabel() {
        return this.zzbj.getBarcodeLabel();
    }

    public final String getBarcodeType() {
        return this.zzbj.getBarcodeType();
    }

    public final String getBarcodeValue() {
        return this.zzbj.getBarcodeValue();
    }

    public final String getClassId() {
        return this.zzbj.getClassId();
    }

    public final String getId() {
        return this.zzbj.getId();
    }

    public final ArrayList<UriData> getImageModuleDataMainImageUris() {
        return this.zzbj.getImageModuleDataMainImageUris();
    }

    public final String getInfoModuleDataHexBackgroundColor() {
        return this.zzbj.getInfoModuleDataHexBackgroundColor();
    }

    public final String getInfoModuleDataHexFontColor() {
        return this.zzbj.getInfoModuleDataHexFontColor();
    }

    public final ArrayList<LabelValueRow> getInfoModuleDataLabelValueRows() {
        return this.zzbj.getInfoModuleDataLabelValueRows();
    }

    public final boolean getInfoModuleDataShowLastUpdateTime() {
        return this.zzbj.getInfoModuleDataShowLastUpdateTime();
    }

    public final String getIssuerName() {
        return this.zzbj.getIssuerName();
    }

    public final ArrayList<UriData> getLinksModuleDataUris() {
        return this.zzbj.getLinksModuleDataUris();
    }

    public final ArrayList<LatLng> getLocations() {
        return this.zzbj.getLocations();
    }

    public final ArrayList<WalletObjectMessage> getMessages() {
        return this.zzbj.getMessages();
    }

    public final String getRedemptionCode() {
        return this.zzdq;
    }

    public final int getState() {
        return this.zzbj.getState();
    }

    public final ArrayList<TextModuleData> getTextModulesData() {
        return this.zzbj.getTextModulesData();
    }

    public final String getTitle() {
        return this.zzbj.getName();
    }

    public final TimeInterval getValidTimeInterval() {
        return this.zzbj.getValidTimeInterval();
    }

    public final int getVersionCode() {
        return this.versionCode;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, getVersionCode());
        SafeParcelWriter.writeString(parcel, 2, this.zzce, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzdq, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzbj, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
