package com.cuebiq.cuebiqsdk.model.wrapper;

import com.cuebiq.cuebiqsdk.CuebiqSDKImpl;
import com.cuebiq.cuebiqsdk.utils.InformationList;
import java.util.List;

public class TrackRequest {
    private Auth auth;
    private Device device;
    private InformationList information;
    private List<BluetoothDevice> pairedDevices;
    private Integer settingsVersion;

    public Auth getAuth() {
        return this.auth;
    }

    public Device getDevice() {
        return this.device;
    }

    public InformationList getInformation() {
        return this.information;
    }

    public List<BluetoothDevice> getPairedDevices() {
        return this.pairedDevices;
    }

    public Integer getSettingsVersion() {
        return this.settingsVersion;
    }

    public void setAuth(Auth auth) {
        this.auth = auth;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public void setInformation(InformationList informationList) {
        this.information = informationList;
    }

    public void setPairedDevices(List<BluetoothDevice> list) {
        this.pairedDevices = list;
    }

    public void setSettingsVersion(Integer num) {
        this.settingsVersion = num;
    }

    public String toString() {
        return CuebiqSDKImpl.GSON.toJson((Object) this);
    }
}
