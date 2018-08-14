package com.cuebiq.cuebiqsdk.model.wrapper;

import com.cuebiq.cuebiqsdk.CuebiqSDKImpl;
import com.cuebiq.cuebiqsdk.api.ApiConfiguration;
import com.cuebiq.cuebiqsdk.api.Environment;
import com.cuebiq.cuebiqsdk.utils.WifiList;

public class Information {
    private Float batteryLevel;
    private String dataConnectionType;
    private Boolean debugMode;
    private Event event;
    private Geo geo;
    private String ipAddressV4;
    private String ipAddressV6;
    private Boolean isGeofence;
    private Boolean isRoaming;
    private Long lastSeen;
    private Long timestamp;
    private WifiList wifis;

    public Information() {
        this.debugMode = Boolean.valueOf(ApiConfiguration.workingEnvironment != Environment.PRODUCTION);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Information information = (Information) obj;
        if (this.event != null) {
            if (this.event.equals(information.event)) {
                return true;
            }
        } else if (information.event == null) {
            return true;
        }
        return false;
    }

    public Float getBatteryLevel() {
        return this.batteryLevel;
    }

    public String getDataConnectionType() {
        return this.dataConnectionType;
    }

    public Boolean getDebugMode() {
        return this.debugMode;
    }

    public Event getEvent() {
        return this.event;
    }

    public Geo getGeo() {
        return this.geo;
    }

    public Boolean getGeofence() {
        return this.isGeofence;
    }

    public String getIpAddressV4() {
        return this.ipAddressV4;
    }

    public String getIpAddressV6() {
        return this.ipAddressV6;
    }

    public Boolean getIsRoaming() {
        return this.isRoaming;
    }

    public Long getLastSeen() {
        return this.lastSeen;
    }

    public Boolean getRoaming() {
        return this.isRoaming;
    }

    public Long getTimestamp() {
        return this.timestamp;
    }

    public WifiList getWifis() {
        return this.wifis;
    }

    public int hashCode() {
        return this.event != null ? this.event.hashCode() : 0;
    }

    public void setBatteryLevel(Float f) {
        this.batteryLevel = f;
    }

    public void setDataConnectionType(String str) {
        this.dataConnectionType = str;
    }

    public void setDebugMode(Boolean bool) {
        this.debugMode = bool;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public void setGeo(Geo geo) {
        this.geo = geo;
    }

    public void setGeofence(Boolean bool) {
        this.isGeofence = bool;
    }

    public void setIpAddressV4(String str) {
        this.ipAddressV4 = str;
    }

    public void setIpAddressV6(String str) {
        this.ipAddressV6 = str;
    }

    public void setIsRoaming(Boolean bool) {
        this.isRoaming = bool;
    }

    public void setLastSeen(Long l) {
        this.lastSeen = l;
    }

    public void setRoaming(Boolean bool) {
        this.isRoaming = bool;
    }

    public void setTimestamp(Long l) {
        this.timestamp = l;
    }

    public void setWifis(WifiList wifiList) {
        this.wifis = wifiList;
    }

    public String toString() {
        return CuebiqSDKImpl.GSON.toJson((Object) this);
    }
}
