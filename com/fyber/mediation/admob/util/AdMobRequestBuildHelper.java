package com.fyber.mediation.admob.util;

import android.location.Location;
import com.fyber.mediation.MediationAdapter;
import com.fyber.mediation.admob.AdMobMediationAdapter;
import com.fyber.mediation.admob.AdMobTestDevicesHelper;
import com.fyber.utils.FyberLogger;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdRequest$Builder;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class AdMobRequestBuildHelper {
    private static final String TAG = AdMobRequestBuildHelper.class.getSimpleName();
    private AdMobMediationAdapter adapter;
    private final Date birthdayDate;
    private final Integer gender;
    private final Location location;
    private final List<String> testDevicesList;

    public AdMobRequestBuildHelper(Map<String, Object> configs, AdMobMediationAdapter adapter) {
        this.adapter = adapter;
        this.gender = getGenderFrom(configs);
        this.location = getLocationFrom(configs);
        this.birthdayDate = getBirthdayDateFrom(configs);
        this.testDevicesList = AdMobTestDevicesHelper.getTestDevices(configs);
        if (FyberLogger.isLogging()) {
            FyberLogger.m8451i(TAG, "Test devices: " + Arrays.toString(this.testDevicesList.toArray()));
        }
    }

    public AdRequest generateRequest() {
        AdRequest$Builder requestBuilder = new AdRequest$Builder();
        requestBuilder.setRequestAgent(AdMobMediationAdapter.REQUEST_AGENT);
        requestBuilder.addTestDevice("B3EEABB8EE11C2BE770B684D95219ECB");
        for (String testDeviceId : this.testDevicesList) {
            requestBuilder.addTestDevice(testDeviceId);
        }
        if (this.gender != null) {
            requestBuilder.setGender(this.gender.intValue());
        }
        if (this.birthdayDate != null) {
            requestBuilder.setBirthday(this.birthdayDate);
        }
        if (this.location != null) {
            requestBuilder.setLocation(this.location);
        }
        Boolean isCoppaCompliant = this.adapter.isCOPPACompliant();
        if (isCoppaCompliant != null) {
            requestBuilder.tagForChildDirectedTreatment(isCoppaCompliant.booleanValue());
        }
        return requestBuilder.build();
    }

    private static Date getBirthdayDateFrom(Map<String, Object> configs) {
        return (Date) MediationAdapter.getConfiguration(configs, AdMobMediationAdapter.BIRTHDAY_KEY, Date.class);
    }

    private static Location getLocationFrom(Map<String, Object> configs) {
        return (Location) MediationAdapter.getConfiguration(configs, "location", Location.class);
    }

    private static Integer getGenderFrom(Map<String, Object> configs) {
        return (Integer) MediationAdapter.getConfiguration(configs, AdMobMediationAdapter.GENDER_KEY, Integer.class);
    }
}
