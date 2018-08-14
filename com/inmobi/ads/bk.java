package com.inmobi.ads;

import android.graphics.Point;
import android.support.annotation.Nullable;
import com.inmobi.ads.bl.C3033a.C3032a;
import com.inmobi.commons.core.utilities.info.DisplayInfo;
import java.util.List;

/* compiled from: VastBestFitCompanionFinder */
public class bk {
    private boolean f7269a = false;

    @Nullable
    public bl m9519a(aw awVar, NativeV2Asset nativeV2Asset) {
        bp D = awVar.m9441D();
        Point a = nativeV2Asset.m9001b().m8849a();
        List<bl> f = D.mo6222f();
        float c = DisplayInfo.m10420a().m10440c();
        double d = (double) (((float) a.y) / c);
        double d2 = (double) (((float) a.x) / c);
        double d3 = d2 / d;
        double d4 = d2 * d;
        double d5 = -1.0d;
        double d6 = 0.0d;
        bl blVar = null;
        for (bl blVar2 : f) {
            bl blVar22;
            if (!blVar22.m9528a(C3032a.CREATIVE_TYPE_STATIC).isEmpty()) {
                double d7;
                double d8;
                this.f7269a = true;
                int b = blVar22.m9531b();
                int a2 = blVar22.m9526a();
                if (d3 > ((double) a2) / ((double) b)) {
                    d7 = ((double) a2) * (d / ((double) b));
                    d8 = d;
                } else {
                    d8 = ((double) b) * (d2 / ((double) a2));
                    d7 = d2;
                }
                if (((double) b) >= 0.33d * d8 && ((double) a2) >= 0.33d * d7) {
                    d7 *= d8;
                    if (d7 > 0.5d * d4) {
                        if (d7 > d5) {
                            d8 = ((double) b) / d8;
                            d6 = d7;
                        } else {
                            if (d7 == d5) {
                                float c2 = DisplayInfo.m10420a().m10440c();
                                d8 = ((double) b) / d8;
                                if ((d8 > d6 && d6 < ((double) c2)) || (d6 > ((double) c2) && d8 < d6 && d8 > ((double) c2))) {
                                    d6 = d5;
                                }
                            }
                            blVar22 = blVar;
                            d8 = d6;
                            d6 = d5;
                        }
                        blVar = blVar22;
                        d5 = d6;
                        d6 = d8;
                    }
                }
            }
        }
        return blVar;
    }

    public boolean m9520a() {
        return this.f7269a;
    }
}
