package com.mopub.mobileads;

import android.content.Context;
import com.mopub.common.AdUrlGenerator;
import com.mopub.common.ClientMetadata;
import com.mopub.common.Constants;
import com.mopub.common.ExternalViewabilitySessionManager.ViewabilityVendor;

public class WebViewAdUrlGenerator extends AdUrlGenerator {
    private final boolean mIsStorePictureSupported;

    public WebViewAdUrlGenerator(Context context, boolean isStorePictureSupported) {
        super(context);
        this.mIsStorePictureSupported = isStorePictureSupported;
    }

    public String generateUrlString(String serverHostname) {
        initUrlString(serverHostname, Constants.AD_HANDLER);
        setApiVersion("6");
        addBaseParams(ClientMetadata.getInstance(this.mContext));
        setMraidFlag(true);
        setExternalStoragePermission(this.mIsStorePictureSupported);
        enableViewability(ViewabilityVendor.getEnabledVendorKey());
        return getFinalUrlString();
    }
}
