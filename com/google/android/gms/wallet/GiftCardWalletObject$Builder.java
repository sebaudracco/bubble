package com.google.android.gms.wallet;

import android.text.TextUtils;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.wallet.wobs.CommonWalletObject;
import com.google.android.gms.wallet.wobs.CommonWalletObject.zza;
import com.google.android.gms.wallet.wobs.LabelValueRow;
import com.google.android.gms.wallet.wobs.TextModuleData;
import com.google.android.gms.wallet.wobs.TimeInterval;
import com.google.android.gms.wallet.wobs.UriData;
import com.google.android.gms.wallet.wobs.WalletObjectMessage;
import java.util.Collection;

public final class GiftCardWalletObject$Builder {
    private zza zzbq;
    private final /* synthetic */ GiftCardWalletObject zzbr;

    private GiftCardWalletObject$Builder(GiftCardWalletObject giftCardWalletObject) {
        this.zzbr = giftCardWalletObject;
        this.zzbq = CommonWalletObject.zze();
    }

    public final GiftCardWalletObject$Builder addImageModuleDataMainImageUri(UriData uriData) {
        this.zzbq.zza(uriData);
        return this;
    }

    public final GiftCardWalletObject$Builder addImageModuleDataMainImageUris(Collection<UriData> collection) {
        this.zzbq.zzd((Collection) collection);
        return this;
    }

    public final GiftCardWalletObject$Builder addInfoModuleDataLabelValueRow(LabelValueRow labelValueRow) {
        this.zzbq.zza(labelValueRow);
        return this;
    }

    public final GiftCardWalletObject$Builder addInfoModuleDataLabelValueRows(Collection<LabelValueRow> collection) {
        this.zzbq.zzc((Collection) collection);
        return this;
    }

    public final GiftCardWalletObject$Builder addLinksModuleDataUri(UriData uriData) {
        this.zzbq.zzb(uriData);
        return this;
    }

    public final GiftCardWalletObject$Builder addLinksModuleDataUris(Collection<UriData> collection) {
        this.zzbq.zzf((Collection) collection);
        return this;
    }

    public final GiftCardWalletObject$Builder addLocation(LatLng latLng) {
        this.zzbq.zza(latLng);
        return this;
    }

    public final GiftCardWalletObject$Builder addLocations(Collection<LatLng> collection) {
        this.zzbq.zzb((Collection) collection);
        return this;
    }

    public final GiftCardWalletObject$Builder addMessage(WalletObjectMessage walletObjectMessage) {
        this.zzbq.zza(walletObjectMessage);
        return this;
    }

    public final GiftCardWalletObject$Builder addMessages(Collection<WalletObjectMessage> collection) {
        this.zzbq.zza((Collection) collection);
        return this;
    }

    public final GiftCardWalletObject$Builder addTextModuleData(TextModuleData textModuleData) {
        this.zzbq.zza(textModuleData);
        return this;
    }

    public final GiftCardWalletObject$Builder addTextModulesData(Collection<TextModuleData> collection) {
        this.zzbq.zze((Collection) collection);
        return this;
    }

    public final GiftCardWalletObject build() {
        boolean z = true;
        Preconditions.checkArgument(!TextUtils.isEmpty(this.zzbr.zzbk), "Card number is required.");
        this.zzbr.zzbj = this.zzbq.zzf();
        Preconditions.checkArgument(!TextUtils.isEmpty(this.zzbr.zzbj.getName()), "Card name is required.");
        if (TextUtils.isEmpty(this.zzbr.zzbj.getIssuerName())) {
            z = false;
        }
        Preconditions.checkArgument(z, "Card issuer name is required.");
        return this.zzbr;
    }

    public final GiftCardWalletObject$Builder setBalanceCurrencyCode(String str) {
        this.zzbr.zzbn = str;
        return this;
    }

    public final GiftCardWalletObject$Builder setBalanceMicros(long j) {
        this.zzbr.zzbm = j;
        return this;
    }

    public final GiftCardWalletObject$Builder setBalanceUpdateTime(long j) {
        this.zzbr.zzbo = j;
        return this;
    }

    public final GiftCardWalletObject$Builder setBarcodeAlternateText(String str) {
        this.zzbq.zze(str);
        return this;
    }

    public final GiftCardWalletObject$Builder setBarcodeLabel(String str) {
        this.zzbq.zzh(str);
        return this;
    }

    public final GiftCardWalletObject$Builder setBarcodeType(String str) {
        this.zzbq.zzf(str);
        return this;
    }

    public final GiftCardWalletObject$Builder setBarcodeValue(String str) {
        this.zzbq.zzg(str);
        return this;
    }

    public final GiftCardWalletObject$Builder setCardIdentifier(String str) {
        this.zzbr.zzbl = str;
        return this;
    }

    public final GiftCardWalletObject$Builder setCardNumber(String str) {
        this.zzbr.zzbk = str;
        return this;
    }

    public final GiftCardWalletObject$Builder setClassId(String str) {
        this.zzbq.zzb(str);
        return this;
    }

    public final GiftCardWalletObject$Builder setEventNumber(String str) {
        this.zzbr.zzbp = str;
        return this;
    }

    public final GiftCardWalletObject$Builder setId(String str) {
        this.zzbq.zza(str);
        return this;
    }

    public final GiftCardWalletObject$Builder setInfoModuleDataHexBackgroundColor(String str) {
        this.zzbq.zzj(str);
        return this;
    }

    public final GiftCardWalletObject$Builder setInfoModuleDataHexFontColor(String str) {
        this.zzbq.zzi(str);
        return this;
    }

    public final GiftCardWalletObject$Builder setInfoModuleDataShowLastUpdateTime(boolean z) {
        this.zzbq.zza(z);
        return this;
    }

    public final GiftCardWalletObject$Builder setIssuerName(String str) {
        this.zzbq.zzd(str);
        return this;
    }

    public final GiftCardWalletObject$Builder setPin(String str) {
        this.zzbr.pin = str;
        return this;
    }

    public final GiftCardWalletObject$Builder setState(int i) {
        this.zzbq.zzc(i);
        return this;
    }

    public final GiftCardWalletObject$Builder setTitle(String str) {
        this.zzbq.zzc(str);
        return this;
    }

    public final GiftCardWalletObject$Builder setValidTimeInterval(TimeInterval timeInterval) {
        this.zzbq.zza(timeInterval);
        return this;
    }
}
