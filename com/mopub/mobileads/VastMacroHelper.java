package com.mopub.mobileads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.mopub.common.Preconditions;
import com.mopub.common.logging.MoPubLog;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class VastMacroHelper {
    @NonNull
    private final Map<VastMacro, String> mMacroDataMap = new HashMap();
    @NonNull
    private final List<String> mOriginalUris;

    public VastMacroHelper(@NonNull List<String> uris) {
        Preconditions.checkNotNull(uris, "uris cannot be null");
        this.mOriginalUris = uris;
        this.mMacroDataMap.put(VastMacro.CACHEBUSTING, getCachebustingString());
    }

    @NonNull
    public List<String> getUris() {
        List<String> modifiedUris = new ArrayList();
        for (String modifiedUri : this.mOriginalUris) {
            String modifiedUri2;
            if (!TextUtils.isEmpty(modifiedUri2)) {
                for (VastMacro vastMacro : VastMacro.values()) {
                    String value = (String) this.mMacroDataMap.get(vastMacro);
                    if (value == null) {
                        value = "";
                    }
                    modifiedUri2 = modifiedUri2.replaceAll("\\[" + vastMacro.name() + "\\]", value);
                }
                modifiedUris.add(modifiedUri2);
            }
        }
        return modifiedUris;
    }

    @NonNull
    public VastMacroHelper withErrorCode(@Nullable VastErrorCode errorCode) {
        if (errorCode != null) {
            this.mMacroDataMap.put(VastMacro.ERRORCODE, errorCode.getErrorCode());
        }
        return this;
    }

    @NonNull
    public VastMacroHelper withContentPlayHead(@Nullable Integer contentPlayHeadMS) {
        if (contentPlayHeadMS != null) {
            String contentPlayHeadMSStr = formatContentPlayHead(contentPlayHeadMS.intValue());
            if (!TextUtils.isEmpty(contentPlayHeadMSStr)) {
                this.mMacroDataMap.put(VastMacro.CONTENTPLAYHEAD, contentPlayHeadMSStr);
            }
        }
        return this;
    }

    @NonNull
    public VastMacroHelper withAssetUri(@Nullable String assetUri) {
        if (!TextUtils.isEmpty(assetUri)) {
            try {
                assetUri = URLEncoder.encode(assetUri, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                MoPubLog.m12070w("Failed to encode url", e);
            }
            this.mMacroDataMap.put(VastMacro.ASSETURI, assetUri);
        }
        return this;
    }

    @NonNull
    private String getCachebustingString() {
        return String.format(Locale.US, "%08d", new Object[]{Long.valueOf(Math.round(Math.random() * 1.0E8d))});
    }

    @NonNull
    private String formatContentPlayHead(int contentPlayHeadMS) {
        return String.format("%02d:%02d:%02d.%03d", new Object[]{Long.valueOf(TimeUnit.MILLISECONDS.toHours((long) contentPlayHeadMS)), Long.valueOf(TimeUnit.MILLISECONDS.toMinutes((long) contentPlayHeadMS) % TimeUnit.HOURS.toMinutes(1)), Long.valueOf(TimeUnit.MILLISECONDS.toSeconds((long) contentPlayHeadMS) % TimeUnit.MINUTES.toSeconds(1)), Integer.valueOf(contentPlayHeadMS % 1000)});
    }
}
