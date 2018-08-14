package com.google.android.gms.wallet;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.wallet.wobs.LabelValueRow;
import com.google.android.gms.wallet.wobs.LoyaltyPoints;
import com.google.android.gms.wallet.wobs.TextModuleData;
import com.google.android.gms.wallet.wobs.TimeInterval;
import com.google.android.gms.wallet.wobs.UriData;
import com.google.android.gms.wallet.wobs.WalletObjectMessage;
import java.util.Collection;

public final class LoyaltyWalletObject$Builder {
    private final /* synthetic */ LoyaltyWalletObject zzcz;

    private LoyaltyWalletObject$Builder(LoyaltyWalletObject loyaltyWalletObject) {
        this.zzcz = loyaltyWalletObject;
    }

    public final LoyaltyWalletObject$Builder addImageModuleDataMainImageUri(UriData uriData) {
        this.zzcz.zzcv.add(uriData);
        return this;
    }

    public final LoyaltyWalletObject$Builder addImageModuleDataMainImageUris(Collection<UriData> collection) {
        this.zzcz.zzcv.addAll(collection);
        return this;
    }

    public final LoyaltyWalletObject$Builder addInfoModuleDataLabeValueRow(LabelValueRow labelValueRow) {
        this.zzcz.zzct.add(labelValueRow);
        return this;
    }

    public final LoyaltyWalletObject$Builder addInfoModuleDataLabelValueRows(Collection<LabelValueRow> collection) {
        this.zzcz.zzct.addAll(collection);
        return this;
    }

    public final LoyaltyWalletObject$Builder addLinksModuleDataUri(UriData uriData) {
        this.zzcz.zzcx.add(uriData);
        return this;
    }

    public final LoyaltyWalletObject$Builder addLinksModuleDataUris(Collection<UriData> collection) {
        this.zzcz.zzcx.addAll(collection);
        return this;
    }

    public final LoyaltyWalletObject$Builder addLocation(LatLng latLng) {
        this.zzcz.zzcq.add(latLng);
        return this;
    }

    public final LoyaltyWalletObject$Builder addLocations(Collection<LatLng> collection) {
        this.zzcz.zzcq.addAll(collection);
        return this;
    }

    public final LoyaltyWalletObject$Builder addMessage(WalletObjectMessage walletObjectMessage) {
        this.zzcz.zzco.add(walletObjectMessage);
        return this;
    }

    public final LoyaltyWalletObject$Builder addMessages(Collection<WalletObjectMessage> collection) {
        this.zzcz.zzco.addAll(collection);
        return this;
    }

    public final LoyaltyWalletObject$Builder addTextModuleData(TextModuleData textModuleData) {
        this.zzcz.zzcw.add(textModuleData);
        return this;
    }

    public final LoyaltyWalletObject$Builder addTextModulesData(Collection<TextModuleData> collection) {
        this.zzcz.zzcw.addAll(collection);
        return this;
    }

    public final LoyaltyWalletObject build() {
        return this.zzcz;
    }

    public final LoyaltyWalletObject$Builder setAccountId(String str) {
        this.zzcz.zzcf = str;
        return this;
    }

    public final LoyaltyWalletObject$Builder setAccountName(String str) {
        this.zzcz.zzci = str;
        return this;
    }

    public final LoyaltyWalletObject$Builder setBarcodeAlternateText(String str) {
        this.zzcz.zzcj = str;
        return this;
    }

    public final LoyaltyWalletObject$Builder setBarcodeLabel(String str) {
        this.zzcz.zzcm = str;
        return this;
    }

    public final LoyaltyWalletObject$Builder setBarcodeType(String str) {
        this.zzcz.zzck = str;
        return this;
    }

    public final LoyaltyWalletObject$Builder setBarcodeValue(String str) {
        this.zzcz.zzcl = str;
        return this;
    }

    public final LoyaltyWalletObject$Builder setClassId(String str) {
        this.zzcz.zzcn = str;
        return this;
    }

    public final LoyaltyWalletObject$Builder setId(String str) {
        this.zzcz.zzce = str;
        return this;
    }

    public final LoyaltyWalletObject$Builder setInfoModuleDataHexBackgroundColor(String str) {
        this.zzcz.zzcs = str;
        return this;
    }

    public final LoyaltyWalletObject$Builder setInfoModuleDataHexFontColor(String str) {
        this.zzcz.zzcr = str;
        return this;
    }

    public final LoyaltyWalletObject$Builder setInfoModuleDataShowLastUpdateTime(boolean z) {
        this.zzcz.zzcu = z;
        return this;
    }

    public final LoyaltyWalletObject$Builder setIssuerName(String str) {
        this.zzcz.zzcg = str;
        return this;
    }

    public final LoyaltyWalletObject$Builder setLoyaltyPoints(LoyaltyPoints loyaltyPoints) {
        this.zzcz.zzcy = loyaltyPoints;
        return this;
    }

    public final LoyaltyWalletObject$Builder setProgramName(String str) {
        this.zzcz.zzch = str;
        return this;
    }

    public final LoyaltyWalletObject$Builder setState(int i) {
        this.zzcz.state = i;
        return this;
    }

    public final LoyaltyWalletObject$Builder setValidTimeInterval(TimeInterval timeInterval) {
        this.zzcz.zzcp = timeInterval;
        return this;
    }
}
