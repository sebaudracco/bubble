package com.google.android.gms.ads.search;

import android.os.Bundle;
import com.appnext.base.p023b.C1042c;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;
import com.google.android.gms.ads.search.SearchAdRequest.Builder;

public final class DynamicHeightSearchAdRequest$Builder {
    private final Builder zzdgx = new Builder();
    private final Bundle zzdgy = new Bundle();

    public final DynamicHeightSearchAdRequest$Builder addCustomEventExtrasBundle(Class<? extends CustomEvent> cls, Bundle bundle) {
        this.zzdgx.addCustomEventExtrasBundle(cls, bundle);
        return this;
    }

    public final DynamicHeightSearchAdRequest$Builder addNetworkExtras(NetworkExtras networkExtras) {
        this.zzdgx.addNetworkExtras(networkExtras);
        return this;
    }

    public final DynamicHeightSearchAdRequest$Builder addNetworkExtrasBundle(Class<? extends MediationAdapter> cls, Bundle bundle) {
        this.zzdgx.addNetworkExtrasBundle(cls, bundle);
        return this;
    }

    public final DynamicHeightSearchAdRequest build() {
        this.zzdgx.addNetworkExtrasBundle(AdMobAdapter.class, this.zzdgy);
        return new DynamicHeightSearchAdRequest(this, null);
    }

    public final DynamicHeightSearchAdRequest$Builder setAdBorderSelectors(String str) {
        this.zzdgy.putString("csa_adBorderSelectors", str);
        return this;
    }

    public final DynamicHeightSearchAdRequest$Builder setAdTest(boolean z) {
        this.zzdgy.putString("csa_adtest", z ? C1042c.jF : C1042c.jG);
        return this;
    }

    public final DynamicHeightSearchAdRequest$Builder setAdjustableLineHeight(int i) {
        this.zzdgy.putString("csa_adjustableLineHeight", Integer.toString(i));
        return this;
    }

    public final DynamicHeightSearchAdRequest$Builder setAdvancedOptionValue(String str, String str2) {
        this.zzdgy.putString(str, str2);
        return this;
    }

    public final DynamicHeightSearchAdRequest$Builder setAttributionSpacingBelow(int i) {
        this.zzdgy.putString("csa_attributionSpacingBelow", Integer.toString(i));
        return this;
    }

    public final DynamicHeightSearchAdRequest$Builder setBorderSelections(String str) {
        this.zzdgy.putString("csa_borderSelections", str);
        return this;
    }

    public final DynamicHeightSearchAdRequest$Builder setChannel(String str) {
        this.zzdgy.putString("csa_channel", str);
        return this;
    }

    public final DynamicHeightSearchAdRequest$Builder setColorAdBorder(String str) {
        this.zzdgy.putString("csa_colorAdBorder", str);
        return this;
    }

    public final DynamicHeightSearchAdRequest$Builder setColorAdSeparator(String str) {
        this.zzdgy.putString("csa_colorAdSeparator", str);
        return this;
    }

    public final DynamicHeightSearchAdRequest$Builder setColorAnnotation(String str) {
        this.zzdgy.putString("csa_colorAnnotation", str);
        return this;
    }

    public final DynamicHeightSearchAdRequest$Builder setColorAttribution(String str) {
        this.zzdgy.putString("csa_colorAttribution", str);
        return this;
    }

    public final DynamicHeightSearchAdRequest$Builder setColorBackground(String str) {
        this.zzdgy.putString("csa_colorBackground", str);
        return this;
    }

    public final DynamicHeightSearchAdRequest$Builder setColorBorder(String str) {
        this.zzdgy.putString("csa_colorBorder", str);
        return this;
    }

    public final DynamicHeightSearchAdRequest$Builder setColorDomainLink(String str) {
        this.zzdgy.putString("csa_colorDomainLink", str);
        return this;
    }

    public final DynamicHeightSearchAdRequest$Builder setColorText(String str) {
        this.zzdgy.putString("csa_colorText", str);
        return this;
    }

    public final DynamicHeightSearchAdRequest$Builder setColorTitleLink(String str) {
        this.zzdgy.putString("csa_colorTitleLink", str);
        return this;
    }

    public final DynamicHeightSearchAdRequest$Builder setCssWidth(int i) {
        this.zzdgy.putString("csa_width", Integer.toString(i));
        return this;
    }

    public final DynamicHeightSearchAdRequest$Builder setDetailedAttribution(boolean z) {
        this.zzdgy.putString("csa_detailedAttribution", Boolean.toString(z));
        return this;
    }

    public final DynamicHeightSearchAdRequest$Builder setFontFamily(String str) {
        this.zzdgy.putString("csa_fontFamily", str);
        return this;
    }

    public final DynamicHeightSearchAdRequest$Builder setFontFamilyAttribution(String str) {
        this.zzdgy.putString("csa_fontFamilyAttribution", str);
        return this;
    }

    public final DynamicHeightSearchAdRequest$Builder setFontSizeAnnotation(int i) {
        this.zzdgy.putString("csa_fontSizeAnnotation", Integer.toString(i));
        return this;
    }

    public final DynamicHeightSearchAdRequest$Builder setFontSizeAttribution(int i) {
        this.zzdgy.putString("csa_fontSizeAttribution", Integer.toString(i));
        return this;
    }

    public final DynamicHeightSearchAdRequest$Builder setFontSizeDescription(int i) {
        this.zzdgy.putString("csa_fontSizeDescription", Integer.toString(i));
        return this;
    }

    public final DynamicHeightSearchAdRequest$Builder setFontSizeDomainLink(int i) {
        this.zzdgy.putString("csa_fontSizeDomainLink", Integer.toString(i));
        return this;
    }

    public final DynamicHeightSearchAdRequest$Builder setFontSizeTitle(int i) {
        this.zzdgy.putString("csa_fontSizeTitle", Integer.toString(i));
        return this;
    }

    public final DynamicHeightSearchAdRequest$Builder setHostLanguage(String str) {
        this.zzdgy.putString("csa_hl", str);
        return this;
    }

    public final DynamicHeightSearchAdRequest$Builder setIsClickToCallEnabled(boolean z) {
        this.zzdgy.putString("csa_clickToCall", Boolean.toString(z));
        return this;
    }

    public final DynamicHeightSearchAdRequest$Builder setIsLocationEnabled(boolean z) {
        this.zzdgy.putString("csa_location", Boolean.toString(z));
        return this;
    }

    public final DynamicHeightSearchAdRequest$Builder setIsPlusOnesEnabled(boolean z) {
        this.zzdgy.putString("csa_plusOnes", Boolean.toString(z));
        return this;
    }

    public final DynamicHeightSearchAdRequest$Builder setIsSellerRatingsEnabled(boolean z) {
        this.zzdgy.putString("csa_sellerRatings", Boolean.toString(z));
        return this;
    }

    public final DynamicHeightSearchAdRequest$Builder setIsSiteLinksEnabled(boolean z) {
        this.zzdgy.putString("csa_siteLinks", Boolean.toString(z));
        return this;
    }

    public final DynamicHeightSearchAdRequest$Builder setIsTitleBold(boolean z) {
        this.zzdgy.putString("csa_titleBold", Boolean.toString(z));
        return this;
    }

    public final DynamicHeightSearchAdRequest$Builder setIsTitleUnderlined(boolean z) {
        this.zzdgy.putString("csa_noTitleUnderline", Boolean.toString(!z));
        return this;
    }

    public final DynamicHeightSearchAdRequest$Builder setLocationColor(String str) {
        this.zzdgy.putString("csa_colorLocation", str);
        return this;
    }

    public final DynamicHeightSearchAdRequest$Builder setLocationFontSize(int i) {
        this.zzdgy.putString("csa_fontSizeLocation", Integer.toString(i));
        return this;
    }

    public final DynamicHeightSearchAdRequest$Builder setLongerHeadlines(boolean z) {
        this.zzdgy.putString("csa_longerHeadlines", Boolean.toString(z));
        return this;
    }

    public final DynamicHeightSearchAdRequest$Builder setNumber(int i) {
        this.zzdgy.putString("csa_number", Integer.toString(i));
        return this;
    }

    public final DynamicHeightSearchAdRequest$Builder setPage(int i) {
        this.zzdgy.putString("csa_adPage", Integer.toString(i));
        return this;
    }

    public final DynamicHeightSearchAdRequest$Builder setQuery(String str) {
        this.zzdgx.setQuery(str);
        return this;
    }

    public final DynamicHeightSearchAdRequest$Builder setVerticalSpacing(int i) {
        this.zzdgy.putString("csa_verticalSpacing", Integer.toString(i));
        return this;
    }
}
