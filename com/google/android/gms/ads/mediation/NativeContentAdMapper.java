package com.google.android.gms.ads.mediation;

import com.google.android.gms.ads.formats.NativeAd.Image;
import java.util.List;

public class NativeContentAdMapper extends NativeAdMapper {
    private String zzbhw;
    private List<Image> zzbhx;
    private String zzbhy;
    private String zzbia;
    private String zzbim;
    private Image zzdfp;

    public final String getAdvertiser() {
        return this.zzbim;
    }

    public final String getBody() {
        return this.zzbhy;
    }

    public final String getCallToAction() {
        return this.zzbia;
    }

    public final String getHeadline() {
        return this.zzbhw;
    }

    public final List<Image> getImages() {
        return this.zzbhx;
    }

    public final Image getLogo() {
        return this.zzdfp;
    }

    public final void setAdvertiser(String str) {
        this.zzbim = str;
    }

    public final void setBody(String str) {
        this.zzbhy = str;
    }

    public final void setCallToAction(String str) {
        this.zzbia = str;
    }

    public final void setHeadline(String str) {
        this.zzbhw = str;
    }

    public final void setImages(List<Image> list) {
        this.zzbhx = list;
    }

    public final void setLogo(Image image) {
        this.zzdfp = image;
    }
}
