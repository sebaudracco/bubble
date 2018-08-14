package com.mopub.common;

import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import com.mopub.network.AdResponse;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

public class AdReport implements Serializable {
    private static final String DATE_FORMAT_PATTERN = "M/d/yy hh:mm:ss a z";
    private static final long serialVersionUID = 1;
    private final AdResponse mAdResponse;
    private final String mAdUnitId;
    private final Locale mDeviceLocale;
    private final String mDeviceModel;
    private final String mSdkVersion;
    private final String mUdid;

    public AdReport(@NonNull String adUnitId, @NonNull ClientMetadata clientMetadata, @NonNull AdResponse adResponse) {
        this.mAdUnitId = adUnitId;
        this.mSdkVersion = clientMetadata.getSdkVersion();
        this.mDeviceModel = clientMetadata.getDeviceModel();
        this.mDeviceLocale = clientMetadata.getDeviceLocale();
        this.mUdid = clientMetadata.getDeviceId();
        this.mAdResponse = adResponse;
    }

    public String toString() {
        String str;
        StringBuilder parameters = new StringBuilder();
        appendKeyValue(parameters, "sdk_version", this.mSdkVersion);
        appendKeyValue(parameters, "creative_id", this.mAdResponse.getDspCreativeId());
        appendKeyValue(parameters, "platform_version", Integer.toString(VERSION.SDK_INT));
        appendKeyValue(parameters, "device_model", this.mDeviceModel);
        appendKeyValue(parameters, "ad_unit_id", this.mAdUnitId);
        String str2 = "device_locale";
        if (this.mDeviceLocale == null) {
            str = null;
        } else {
            str = this.mDeviceLocale.toString();
        }
        appendKeyValue(parameters, str2, str);
        appendKeyValue(parameters, "device_id", this.mUdid);
        appendKeyValue(parameters, "network_type", this.mAdResponse.getNetworkType());
        appendKeyValue(parameters, "platform", "android");
        appendKeyValue(parameters, "timestamp", getFormattedTimeStamp(this.mAdResponse.getTimestamp()));
        appendKeyValue(parameters, "ad_type", this.mAdResponse.getAdType());
        Integer width = this.mAdResponse.getWidth();
        Integer height = this.mAdResponse.getHeight();
        str = "ad_size";
        StringBuilder append = new StringBuilder().append("{");
        if (width == null) {
            width = SchemaSymbols.ATTVAL_FALSE_0;
        }
        append = append.append(width).append(", ");
        if (height == null) {
            height = SchemaSymbols.ATTVAL_FALSE_0;
        }
        appendKeyValue(parameters, str, append.append(height).append("}").toString());
        return parameters.toString();
    }

    public String getResponseString() {
        return this.mAdResponse.getStringBody();
    }

    public String getDspCreativeId() {
        return this.mAdResponse.getDspCreativeId();
    }

    private void appendKeyValue(StringBuilder parameters, String key, String value) {
        parameters.append(key);
        parameters.append(" : ");
        parameters.append(value);
        parameters.append("\n");
    }

    private String getFormattedTimeStamp(long timeStamp) {
        if (timeStamp != -1) {
            return new SimpleDateFormat(DATE_FORMAT_PATTERN, Locale.US).format(new Date(timeStamp));
        }
        return null;
    }
}
