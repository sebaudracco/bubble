package com.cuebiq.cuebiqsdk.model.wrapper;

import android.location.Location;
import com.cuebiq.cuebiqsdk.CuebiqSDKImpl;
import com.mopub.mobileads.dfp.adapters.MoPubAdapter;

public class Geo {
    private static final int EARTH_RADIUS_IN_METERS = 6378137;
    private Double altitude;
    private Float haccuracy;
    private Double latitude;
    private Double longitude;

    public static Geo build(Location location) {
        Geo geo = new Geo();
        try {
            geo.setLatitude(Double.valueOf(location.getLatitude()));
            geo.setLongitude(Double.valueOf(location.getLongitude()));
            if (location.getAltitude() == 0.0d) {
                geo.setAltitude(null);
            } else {
                geo.setAltitude(Double.valueOf(location.getAltitude()));
            }
            geo.setHaccuracy(Float.valueOf(location.getAccuracy()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return geo;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Geo geo = (Geo) obj;
        if (this.latitude != null) {
            return this.latitude.equals(geo.latitude);
        }
        if (geo.latitude == null) {
            if (this.longitude != null) {
                if (this.longitude.equals(geo.longitude)) {
                    return true;
                }
            } else if (geo.longitude == null) {
                return true;
            }
        }
        return false;
    }

    public Double getAltitude() {
        return this.altitude;
    }

    public Float getHaccuracy() {
        return this.haccuracy;
    }

    public Double getLatitude() {
        return this.latitude;
    }

    public Double getLongitude() {
        return this.longitude;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (this.latitude != null ? this.latitude.hashCode() : 0) * 31;
        if (this.longitude != null) {
            i = this.longitude.hashCode();
        }
        return hashCode + i;
    }

    public double haversineDistance(Geo geo) {
        double toRadians = Math.toRadians(geo.latitude.doubleValue() - this.latitude.doubleValue());
        double toRadians2 = Math.toRadians(geo.longitude.doubleValue() - this.longitude.doubleValue());
        toRadians = (Math.sin(toRadians / 2.0d) * Math.sin(toRadians / 2.0d)) + (Math.sin(toRadians2 / 2.0d) * ((Math.cos(Math.toRadians(this.latitude.doubleValue())) * Math.cos(Math.toRadians(geo.latitude.doubleValue()))) * Math.sin(toRadians2 / 2.0d)));
        return (Math.atan2(Math.sqrt(toRadians), Math.sqrt(MoPubAdapter.DEFAULT_MOPUB_IMAGE_SCALE - toRadians)) * 2.0d) * 6378137.0d;
    }

    public void setAltitude(Double d) {
        this.altitude = d;
    }

    public void setHaccuracy(Float f) {
        this.haccuracy = f;
    }

    public void setLatitude(Double d) {
        this.latitude = d;
    }

    public void setLongitude(Double d) {
        this.longitude = d;
    }

    public Location toLocation(String str) {
        Location location = new Location(str);
        location.setLatitude(this.latitude.doubleValue());
        location.setLongitude(this.longitude.doubleValue());
        location.setAccuracy(this.haccuracy.floatValue());
        return location;
    }

    public String toString() {
        return CuebiqSDKImpl.GSON.toJson((Object) this);
    }
}
