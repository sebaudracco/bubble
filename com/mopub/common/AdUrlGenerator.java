package com.mopub.common;

import android.content.Context;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.mopub.common.ClientMetadata.MoPubNetworkType;
import com.mopub.common.util.DateAndTime;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

public abstract class AdUrlGenerator extends BaseUrlGenerator {
    private static final String AD_UNIT_ID_KEY = "id";
    private static final String BUNDLE_ID_KEY = "bundle";
    private static final String CARRIER_NAME_KEY = "cn";
    private static final String CARRIER_TYPE_KEY = "ct";
    private static final String COUNTRY_CODE_KEY = "iso";
    private static final String IS_MRAID_KEY = "mr";
    private static final String KEYWORDS_KEY = "q";
    private static final String LAT_LONG_ACCURACY_KEY = "lla";
    private static final String LAT_LONG_FRESHNESS_KEY = "llf";
    private static final String LAT_LONG_FROM_SDK_KEY = "llsdk";
    private static final String LAT_LONG_KEY = "ll";
    private static final String MOBILE_COUNTRY_CODE_KEY = "mcc";
    private static final String MOBILE_NETWORK_CODE_KEY = "mnc";
    private static final String ORIENTATION_KEY = "o";
    private static final String SCREEN_SCALE_KEY = "sc_a";
    private static final String SDK_VERSION_KEY = "nv";
    private static final String TIMEZONE_OFFSET_KEY = "z";
    private static final String VIEWABILITY_KEY = "vv";
    protected String mAdUnitId;
    protected Context mContext;
    protected String mKeywords;
    protected Location mLocation;

    public AdUrlGenerator(Context context) {
        this.mContext = context;
    }

    public AdUrlGenerator withAdUnitId(String adUnitId) {
        this.mAdUnitId = adUnitId;
        return this;
    }

    public AdUrlGenerator withKeywords(String keywords) {
        this.mKeywords = keywords;
        return this;
    }

    public AdUrlGenerator withLocation(Location location) {
        this.mLocation = location;
        return this;
    }

    protected void setAdUnitId(String adUnitId) {
        addParam("id", adUnitId);
    }

    protected void setSdkVersion(String sdkVersion) {
        addParam(SDK_VERSION_KEY, sdkVersion);
    }

    protected void setKeywords(String keywords) {
        addParam(KEYWORDS_KEY, keywords);
    }

    protected void setLocation(@Nullable Location location) {
        Location bestLocation = location;
        Location locationFromLocationService = LocationService.getLastKnownLocation(this.mContext, MoPub.getLocationPrecision(), MoPub.getLocationAwareness());
        if (locationFromLocationService != null && (location == null || locationFromLocationService.getTime() >= location.getTime())) {
            bestLocation = locationFromLocationService;
        }
        if (bestLocation != null) {
            addParam(LAT_LONG_KEY, bestLocation.getLatitude() + "," + bestLocation.getLongitude());
            addParam(LAT_LONG_ACCURACY_KEY, String.valueOf((int) bestLocation.getAccuracy()));
            addParam(LAT_LONG_FRESHNESS_KEY, String.valueOf(calculateLocationStalenessInMilliseconds(bestLocation)));
            if (bestLocation == locationFromLocationService) {
                addParam(LAT_LONG_FROM_SDK_KEY, SchemaSymbols.ATTVAL_TRUE_1);
            }
        }
    }

    protected void setTimezone(String timeZoneOffsetString) {
        addParam(TIMEZONE_OFFSET_KEY, timeZoneOffsetString);
    }

    protected void setOrientation(String orientation) {
        addParam(ORIENTATION_KEY, orientation);
    }

    protected void setDensity(float density) {
        addParam(SCREEN_SCALE_KEY, "" + density);
    }

    protected void setMraidFlag(boolean mraid) {
        if (mraid) {
            addParam(IS_MRAID_KEY, SchemaSymbols.ATTVAL_TRUE_1);
        }
    }

    protected void setMccCode(String networkOperator) {
        addParam(MOBILE_COUNTRY_CODE_KEY, networkOperator == null ? "" : networkOperator.substring(0, mncPortionLength(networkOperator)));
    }

    protected void setMncCode(String networkOperator) {
        addParam(MOBILE_NETWORK_CODE_KEY, networkOperator == null ? "" : networkOperator.substring(mncPortionLength(networkOperator)));
    }

    protected void setIsoCountryCode(String networkCountryIso) {
        addParam(COUNTRY_CODE_KEY, networkCountryIso);
    }

    protected void setCarrierName(String networkOperatorName) {
        addParam(CARRIER_NAME_KEY, networkOperatorName);
    }

    protected void setNetworkType(MoPubNetworkType networkType) {
        addParam(CARRIER_TYPE_KEY, networkType);
    }

    protected void setBundleId(String bundleId) {
        if (!TextUtils.isEmpty(bundleId)) {
            addParam(BUNDLE_ID_KEY, bundleId);
        }
    }

    protected void enableViewability(@NonNull String vendorKey) {
        Preconditions.checkNotNull(vendorKey);
        addParam(VIEWABILITY_KEY, vendorKey);
    }

    protected void addBaseParams(ClientMetadata clientMetadata) {
        setAdUnitId(this.mAdUnitId);
        setSdkVersion(clientMetadata.getSdkVersion());
        setDeviceInfo(new String[]{clientMetadata.getDeviceManufacturer(), clientMetadata.getDeviceModel(), clientMetadata.getDeviceProduct()});
        setBundleId(clientMetadata.getAppPackageName());
        setKeywords(this.mKeywords);
        setLocation(this.mLocation);
        setTimezone(DateAndTime.getTimeZoneOffsetString());
        setOrientation(clientMetadata.getOrientationString());
        setDeviceDimensions(clientMetadata.getDeviceDimensions());
        setDensity(clientMetadata.getDensity());
        String networkOperator = clientMetadata.getNetworkOperatorForUrl();
        setMccCode(networkOperator);
        setMncCode(networkOperator);
        setIsoCountryCode(clientMetadata.getIsoCountryCode());
        setCarrierName(clientMetadata.getNetworkOperatorName());
        setNetworkType(clientMetadata.getActiveNetworkType());
        setAppVersion(clientMetadata.getAppVersion());
        appendAdvertisingInfoTemplates();
    }

    private void addParam(String key, MoPubNetworkType value) {
        addParam(key, value.toString());
    }

    private int mncPortionLength(String networkOperator) {
        return Math.min(3, networkOperator.length());
    }

    private static int calculateLocationStalenessInMilliseconds(Location location) {
        Preconditions.checkNotNull(location);
        return (int) (System.currentTimeMillis() - location.getTime());
    }

    @Deprecated
    public AdUrlGenerator withFacebookSupported(boolean enabled) {
        return this;
    }
}
