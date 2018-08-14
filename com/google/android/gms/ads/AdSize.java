package com.google.android.gms.ads;

import android.content.Context;
import com.cuebiq.cuebiqsdk.model.config.Settings;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.zzamu;
import com.google.android.gms.internal.ads.zzjn;
import com.google.android.gms.internal.ads.zzkb;
import cz.msebera.android.httpclient.HttpStatus;

@VisibleForTesting
public final class AdSize {
    public static final int AUTO_HEIGHT = -2;
    public static final AdSize BANNER = new AdSize(320, 50, "320x50_mb");
    public static final AdSize FLUID = new AdSize(-3, -4, "fluid");
    public static final AdSize FULL_BANNER = new AdSize(468, 60, "468x60_as");
    public static final int FULL_WIDTH = -1;
    public static final AdSize LARGE_BANNER = new AdSize(320, 100, "320x100_as");
    public static final AdSize LEADERBOARD = new AdSize(728, 90, "728x90_as");
    public static final AdSize MEDIUM_RECTANGLE = new AdSize(HttpStatus.SC_MULTIPLE_CHOICES, 250, "300x250_as");
    public static final AdSize SEARCH = new AdSize(-3, 0, "search_v2");
    public static final AdSize SMART_BANNER = new AdSize(-1, -2, "smart_banner");
    public static final AdSize WIDE_SKYSCRAPER = new AdSize(160, Settings.MAX_DYNAMIC_ACQUISITION, "160x600_as");
    public static final AdSize zzup = new AdSize(50, 50, "50x50_mb");
    private final int zzuq;
    private final int zzur;
    private final String zzus;

    public AdSize(int i, int i2) {
        String valueOf = i == -1 ? "FULL" : String.valueOf(i);
        String valueOf2 = i2 == -2 ? "AUTO" : String.valueOf(i2);
        this(i, i2, new StringBuilder((String.valueOf(valueOf).length() + 4) + String.valueOf(valueOf2).length()).append(valueOf).append("x").append(valueOf2).append("_as").toString());
    }

    AdSize(int i, int i2, String str) {
        if (i < 0 && i != -1 && i != -3) {
            throw new IllegalArgumentException("Invalid width for AdSize: " + i);
        } else if (i2 >= 0 || i2 == -2 || i2 == -4) {
            this.zzuq = i;
            this.zzur = i2;
            this.zzus = str;
        } else {
            throw new IllegalArgumentException("Invalid height for AdSize: " + i2);
        }
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AdSize)) {
            return false;
        }
        AdSize adSize = (AdSize) obj;
        return this.zzuq == adSize.zzuq && this.zzur == adSize.zzur && this.zzus.equals(adSize.zzus);
    }

    public final int getHeight() {
        return this.zzur;
    }

    public final int getHeightInPixels(Context context) {
        switch (this.zzur) {
            case -4:
            case -3:
                return -1;
            case -2:
                return zzjn.zzc(context.getResources().getDisplayMetrics());
            default:
                zzkb.zzif();
                return zzamu.zza(context, this.zzur);
        }
    }

    public final int getWidth() {
        return this.zzuq;
    }

    public final int getWidthInPixels(Context context) {
        switch (this.zzuq) {
            case -4:
            case -3:
                return -1;
            case -1:
                return zzjn.zzb(context.getResources().getDisplayMetrics());
            default:
                zzkb.zzif();
                return zzamu.zza(context, this.zzuq);
        }
    }

    public final int hashCode() {
        return this.zzus.hashCode();
    }

    public final boolean isAutoHeight() {
        return this.zzur == -2;
    }

    public final boolean isFluid() {
        return this.zzuq == -3 && this.zzur == -4;
    }

    public final boolean isFullWidth() {
        return this.zzuq == -1;
    }

    public final String toString() {
        return this.zzus;
    }
}
