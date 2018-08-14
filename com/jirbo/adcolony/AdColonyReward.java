package com.jirbo.adcolony;

import com.google.android.gms.ads.reward.RewardItem;

public class AdColonyReward implements RewardItem {
    private int _amount;
    private String _name;

    public AdColonyReward(String name, int amount) {
        this._name = name;
        this._amount = amount;
    }

    public String getType() {
        return this._name;
    }

    public int getAmount() {
        return this._amount;
    }
}
