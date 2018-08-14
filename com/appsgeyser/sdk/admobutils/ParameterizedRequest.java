package com.appsgeyser.sdk.admobutils;

import android.location.Location;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdRequest.Builder;

public class ParameterizedRequest {
    private static final String LOCATION_PROVIDER = "Appsgeyser";
    private static final String TAG = ParameterizedRequest.class.getSimpleName();
    private AdRequest adRequest;

    public ParameterizedRequest(AdMobParameters adMobParameters) {
        Builder requestBuilder = new Builder();
        if (adMobParameters == null) {
            adMobParameters = new AdMobParameters();
        }
        if (1 == adMobParameters.getGender() || 2 == adMobParameters.getGender() || adMobParameters.getGender() == 0) {
            requestBuilder.setGender(adMobParameters.getGender());
        }
        if (adMobParameters.getBirthday() != null) {
            requestBuilder.setBirthday(adMobParameters.getBirthday());
        }
        if (adMobParameters.getKeywords() != null) {
            for (String keyword : adMobParameters.getKeywords()) {
                requestBuilder.addKeyword(keyword);
            }
        }
        if (!(adMobParameters.getLatitude() == null || adMobParameters.getLongitude() == null)) {
            Location location = new Location(LOCATION_PROVIDER);
            location.setLatitude(adMobParameters.getLatitude().doubleValue());
            location.setLongitude(adMobParameters.getLongitude().doubleValue());
            requestBuilder.setLocation(location);
        }
        this.adRequest = requestBuilder.addTestDevice("815042A25C9B19F85D2BEE512604974C").build();
    }

    public AdRequest getRequest() {
        return this.adRequest;
    }

    public void setRequest(AdRequest request) {
        this.adRequest = request;
    }
}
