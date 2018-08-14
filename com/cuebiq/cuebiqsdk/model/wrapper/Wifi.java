package com.cuebiq.cuebiqsdk.model.wrapper;

import com.cuebiq.cuebiqsdk.CuebiqSDKImpl;

public class Wifi {
    private String bssid;
    private String capabilities;
    private Integer linkSpeed;
    private Integer rssi;
    private String ssid;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Wifi wifi = (Wifi) obj;
        if (this.ssid == null ? wifi.ssid != null : !this.ssid.equals(wifi.ssid)) {
            if (this.bssid != null) {
                if (this.bssid.equals(wifi.bssid)) {
                    return true;
                }
            } else if (wifi.bssid == null) {
                return true;
            }
        }
        return false;
    }

    public String getBssid() {
        return this.bssid;
    }

    public String getCapabilities() {
        return this.capabilities;
    }

    public Integer getLinkSpeed() {
        return this.linkSpeed;
    }

    public Integer getRssi() {
        return this.rssi;
    }

    public String getSsid() {
        return this.ssid;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (this.ssid != null ? this.ssid.hashCode() : 0) * 31;
        if (this.bssid != null) {
            i = this.bssid.hashCode();
        }
        return hashCode + i;
    }

    public void setBssid(String str) {
        this.bssid = str;
    }

    public void setCapabilities(String str) {
        this.capabilities = str;
    }

    public void setLinkSpeed(Integer num) {
        this.linkSpeed = num;
    }

    public void setRssi(Integer num) {
        this.rssi = num;
    }

    public void setSsid(String str) {
        this.ssid = str;
    }

    public String toString() {
        return CuebiqSDKImpl.GSON.toJson((Object) this);
    }
}
