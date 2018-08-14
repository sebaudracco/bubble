package com.google.android.gms.wallet;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.wallet.wobs.CommonWalletObject;
import com.google.android.gms.wallet.wobs.LabelValueRow;
import com.google.android.gms.wallet.wobs.TextModuleData;
import com.google.android.gms.wallet.wobs.TimeInterval;
import com.google.android.gms.wallet.wobs.UriData;
import com.google.android.gms.wallet.wobs.WalletObjectMessage;
import java.util.ArrayList;

@Class(creator = "GiftCardWalletObjectCreator")
@Reserved({1})
public final class GiftCardWalletObject extends AbstractSafeParcelable {
    public static final Creator<GiftCardWalletObject> CREATOR = new zzo();
    @Field(id = 4)
    String pin;
    @Field(id = 2)
    CommonWalletObject zzbj = CommonWalletObject.zze().zzf();
    @Field(id = 3)
    String zzbk;
    @Field(id = 5)
    String zzbl;
    @Field(id = 6)
    long zzbm;
    @Field(id = 7)
    String zzbn;
    @Field(id = 8)
    long zzbo;
    @Field(id = 9)
    String zzbp;

    GiftCardWalletObject() {
    }

    @Constructor
    GiftCardWalletObject(@Param(id = 2) CommonWalletObject commonWalletObject, @Param(id = 3) String str, @Param(id = 4) String str2, @Param(id = 5) String str3, @Param(id = 6) long j, @Param(id = 7) String str4, @Param(id = 8) long j2, @Param(id = 9) String str5) {
        this.zzbj = commonWalletObject;
        this.zzbk = str;
        this.pin = str2;
        this.zzbm = j;
        this.zzbn = str4;
        this.zzbo = j2;
        this.zzbp = str5;
        this.zzbl = str3;
    }

    public static Builder newBuilder() {
        return new Builder(new GiftCardWalletObject(), null);
    }

    public final String getBalanceCurrencyCode() {
        return this.zzbn;
    }

    public final long getBalanceMicros() {
        return this.zzbm;
    }

    public final long getBalanceUpdateTime() {
        return this.zzbo;
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

    public final String getCardIdentifier() {
        return this.zzbl;
    }

    public final String getCardNumber() {
        return this.zzbk;
    }

    public final String getClassId() {
        return this.zzbj.getClassId();
    }

    public final String getEventNumber() {
        return this.zzbp;
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

    public final String getPin() {
        return this.pin;
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

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzbj, i, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzbk, false);
        SafeParcelWriter.writeString(parcel, 4, this.pin, false);
        SafeParcelWriter.writeString(parcel, 5, this.zzbl, false);
        SafeParcelWriter.writeLong(parcel, 6, this.zzbm);
        SafeParcelWriter.writeString(parcel, 7, this.zzbn, false);
        SafeParcelWriter.writeLong(parcel, 8, this.zzbo);
        SafeParcelWriter.writeString(parcel, 9, this.zzbp, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
