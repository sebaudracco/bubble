package com.facebook.ads.internal.adapters;

import android.content.Context;
import com.facebook.ads.RewardData;
import com.facebook.ads.internal.protocol.AdPlacementType;
import java.util.Map;

public abstract class ae implements AdAdapter {
    RewardData f4196a;
    int f4197b;

    public abstract int mo3693a();

    public void m5744a(int i) {
        this.f4197b = i;
    }

    public abstract void mo3694a(Context context, af afVar, Map<String, Object> map, boolean z);

    public void m5746a(RewardData rewardData) {
        this.f4196a = rewardData;
    }

    public abstract boolean mo3695b();

    public AdPlacementType getPlacementType() {
        return AdPlacementType.REWARDED_VIDEO;
    }
}
