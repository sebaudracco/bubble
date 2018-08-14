package com.google.android.gms.wallet;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.wallet.wobs.CommonWalletObject;
import com.google.android.gms.wallet.wobs.CommonWalletObject.zza;
import com.google.android.gms.wallet.wobs.LabelValueRow;
import com.google.android.gms.wallet.wobs.TextModuleData;
import com.google.android.gms.wallet.wobs.TimeInterval;
import com.google.android.gms.wallet.wobs.UriData;
import com.google.android.gms.wallet.wobs.WalletObjectMessage;
import java.util.Collection;

public final class OfferWalletObject$Builder {
    private zza zzbq;
    private final /* synthetic */ OfferWalletObject zzdr;

    private OfferWalletObject$Builder(OfferWalletObject offerWalletObject) {
        this.zzdr = offerWalletObject;
        this.zzbq = CommonWalletObject.zze();
    }

    public final OfferWalletObject$Builder addImageModuleDataMainImageUri(UriData uriData) {
        this.zzbq.zza(uriData);
        return this;
    }

    public final OfferWalletObject$Builder addImageModuleDataMainImageUris(Collection<UriData> collection) {
        this.zzbq.zzd((Collection) collection);
        return this;
    }

    public final OfferWalletObject$Builder addInfoModuleDataLabelValueRow(LabelValueRow labelValueRow) {
        this.zzbq.zza(labelValueRow);
        return this;
    }

    public final OfferWalletObject$Builder addInfoModuleDataLabelValueRows(Collection<LabelValueRow> collection) {
        this.zzbq.zzc((Collection) collection);
        return this;
    }

    public final OfferWalletObject$Builder addLinksModuleDataUri(UriData uriData) {
        this.zzbq.zzb(uriData);
        return this;
    }

    public final OfferWalletObject$Builder addLinksModuleDataUris(Collection<UriData> collection) {
        this.zzbq.zzf((Collection) collection);
        return this;
    }

    public final OfferWalletObject$Builder addLocation(LatLng latLng) {
        this.zzbq.zza(latLng);
        return this;
    }

    public final OfferWalletObject$Builder addLocations(Collection<LatLng> collection) {
        this.zzbq.zzb((Collection) collection);
        return this;
    }

    public final OfferWalletObject$Builder addMessage(WalletObjectMessage walletObjectMessage) {
        this.zzbq.zza(walletObjectMessage);
        return this;
    }

    public final OfferWalletObject$Builder addMessages(Collection<WalletObjectMessage> collection) {
        this.zzbq.zza((Collection) collection);
        return this;
    }

    public final OfferWalletObject$Builder addTextModuleData(TextModuleData textModuleData) {
        this.zzbq.zza(textModuleData);
        return this;
    }

    public final OfferWalletObject$Builder addTextModulesData(Collection<TextModuleData> collection) {
        this.zzbq.zze((Collection) collection);
        return this;
    }

    public final OfferWalletObject build() {
        this.zzdr.zzbj = this.zzbq.zzf();
        return this.zzdr;
    }

    public final OfferWalletObject$Builder setBarcodeAlternateText(String str) {
        this.zzbq.zze(str);
        return this;
    }

    public final OfferWalletObject$Builder setBarcodeLabel(String str) {
        this.zzbq.zzh(str);
        return this;
    }

    public final OfferWalletObject$Builder setBarcodeType(String str) {
        this.zzbq.zzf(str);
        return this;
    }

    public final OfferWalletObject$Builder setBarcodeValue(String str) {
        this.zzbq.zzg(str);
        return this;
    }

    public final OfferWalletObject$Builder setClassId(String str) {
        this.zzbq.zzb(str);
        return this;
    }

    public final OfferWalletObject$Builder setId(String str) {
        this.zzbq.zza(str);
        this.zzdr.zzce = str;
        return this;
    }

    public final OfferWalletObject$Builder setInfoModuleDataHexBackgroundColor(String str) {
        this.zzbq.zzj(str);
        return this;
    }

    public final OfferWalletObject$Builder setInfoModuleDataHexFontColor(String str) {
        this.zzbq.zzi(str);
        return this;
    }

    public final OfferWalletObject$Builder setInfoModuleDataShowLastUpdateTime(boolean z) {
        this.zzbq.zza(z);
        return this;
    }

    public final OfferWalletObject$Builder setIssuerName(String str) {
        this.zzbq.zzd(str);
        return this;
    }

    public final OfferWalletObject$Builder setRedemptionCode(String str) {
        this.zzdr.zzdq = str;
        return this;
    }

    public final OfferWalletObject$Builder setState(int i) {
        this.zzbq.zzc(i);
        return this;
    }

    public final OfferWalletObject$Builder setTitle(String str) {
        this.zzbq.zzc(str);
        return this;
    }

    public final OfferWalletObject$Builder setValidTimeInterval(TimeInterval timeInterval) {
        this.zzbq.zza(timeInterval);
        return this;
    }
}
