package com.cuebiq.cuebiqsdk.model.wrapper;

import com.cuebiq.cuebiqsdk.CuebiqSDKImpl;

public class IpAddress {
    private String ipAddr;

    public String getIpAddr() {
        return this.ipAddr;
    }

    public void setIpAddr(String str) {
        this.ipAddr = str;
    }

    public String toString() {
        return CuebiqSDKImpl.GSON.toJson((Object) this);
    }
}
