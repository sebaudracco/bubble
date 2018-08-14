package com.google.android.gms.ads.formats;

import com.google.android.gms.ads.doubleclick.AppEventListener;

public final class PublisherAdViewOptions$Builder {
    private boolean zzvm = false;
    private AppEventListener zzvo;

    public final PublisherAdViewOptions build() {
        return new PublisherAdViewOptions(this, null);
    }

    public final PublisherAdViewOptions$Builder setAppEventListener(AppEventListener appEventListener) {
        this.zzvo = appEventListener;
        return this;
    }

    public final PublisherAdViewOptions$Builder setManualImpressionsEnabled(boolean z) {
        this.zzvm = z;
        return this;
    }
}
