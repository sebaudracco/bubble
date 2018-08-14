package com.adcolony.sdk;

import org.json.JSONObject;

public class AdColonyReward {
    private int f443a;
    private String f444b;
    private String f445c;
    private boolean f446d;

    AdColonyReward(af message) {
        JSONObject c = message.m698c();
        this.f443a = C0802y.m1473c(c, "reward_amount");
        this.f444b = C0802y.m1468b(c, "reward_name");
        this.f446d = C0802y.m1477d(c, "success");
        this.f445c = C0802y.m1468b(c, "zone_id");
    }

    public int getRewardAmount() {
        return this.f443a;
    }

    public String getRewardName() {
        return this.f444b;
    }

    public String getZoneID() {
        return this.f445c;
    }

    public boolean success() {
        return this.f446d;
    }
}
