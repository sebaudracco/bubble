package com.cuebiq.cuebiqsdk.model.wrapper;

import com.cuebiq.cuebiqsdk.CuebiqSDKImpl;

public class BluetoothDevice {
    private Integer deviceClass;
    private String name;
    private Integer type;

    public Integer getDeviceClass() {
        return this.deviceClass;
    }

    public String getName() {
        return this.name;
    }

    public Integer getType() {
        return this.type;
    }

    public void setDeviceClass(Integer num) {
        this.deviceClass = num;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setType(Integer num) {
        this.type = num;
    }

    public String toString() {
        return CuebiqSDKImpl.GSON.toJson((Object) this);
    }
}
