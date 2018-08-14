package com.facebook.ads.internal.adapters;

import android.content.Context;
import android.os.Bundle;
import com.facebook.ads.CacheFlag;
import com.facebook.ads.internal.p056q.p057a.C1912o;
import com.facebook.ads.internal.p069m.C2012c;
import com.facebook.ads.internal.protocol.AdPlacementType;
import com.facebook.ads.p051a.C1835a;
import java.util.EnumSet;
import java.util.Map;

public abstract class C1913u implements AdAdapter, C1912o<Bundle> {
    public abstract void mo3676a(Context context, C1835a c1835a, Map<String, Object> map, C2012c c2012c, EnumSet<CacheFlag> enumSet);

    public abstract boolean mo3677e();

    public AdPlacementType getPlacementType() {
        return AdPlacementType.INSTREAM;
    }
}
