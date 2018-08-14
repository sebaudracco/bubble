package com.mopub.common;

import android.graphics.Point;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.mopub.network.Networking;
import com.mopub.network.PlayServicesUrlRewriter;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

public abstract class BaseUrlGenerator {
    private static final String HEIGHT_KEY = "h";
    private static final String WIDTH_KEY = "w";
    private boolean mFirstParam;
    private StringBuilder mStringBuilder;

    public abstract String generateUrlString(String str);

    protected void initUrlString(String serverHostname, String handlerType) {
        this.mStringBuilder = new StringBuilder(Networking.getScheme()).append("://").append(serverHostname).append(handlerType);
        this.mFirstParam = true;
    }

    protected String getFinalUrlString() {
        return this.mStringBuilder.toString();
    }

    protected void addParam(String key, String value) {
        if (!TextUtils.isEmpty(value)) {
            this.mStringBuilder.append(getParamDelimiter());
            this.mStringBuilder.append(key);
            this.mStringBuilder.append("=");
            this.mStringBuilder.append(Uri.encode(value));
        }
    }

    private String getParamDelimiter() {
        if (!this.mFirstParam) {
            return "&";
        }
        this.mFirstParam = false;
        return "?";
    }

    protected void setApiVersion(String apiVersion) {
        addParam("v", apiVersion);
    }

    protected void setAppVersion(String appVersion) {
        addParam("av", appVersion);
    }

    protected void setExternalStoragePermission(boolean isExternalStoragePermissionGranted) {
        addParam("android_perms_ext_storage", isExternalStoragePermissionGranted ? SchemaSymbols.ATTVAL_TRUE_1 : SchemaSymbols.ATTVAL_FALSE_0);
    }

    protected void setDeviceInfo(String... info) {
        StringBuilder result = new StringBuilder();
        if (info != null && info.length >= 1) {
            for (int i = 0; i < info.length - 1; i++) {
                result.append(info[i]).append(",");
            }
            result.append(info[info.length - 1]);
            addParam("dn", result.toString());
        }
    }

    protected void setDoNotTrack(boolean dnt) {
        if (dnt) {
            addParam("dnt", SchemaSymbols.ATTVAL_TRUE_1);
        }
    }

    protected void setUdid(String udid) {
        addParam("udid", udid);
    }

    protected void appendAdvertisingInfoTemplates() {
        addParam("udid", PlayServicesUrlRewriter.UDID_TEMPLATE);
        addParam("dnt", PlayServicesUrlRewriter.DO_NOT_TRACK_TEMPLATE);
    }

    protected void setDeviceDimensions(@NonNull Point dimensions) {
        addParam(WIDTH_KEY, "" + dimensions.x);
        addParam("h", "" + dimensions.y);
    }
}
