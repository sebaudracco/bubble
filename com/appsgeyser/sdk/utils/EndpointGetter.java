package com.appsgeyser.sdk.utils;

import android.support.annotation.NonNull;
import java.net.URI;
import java.net.URISyntaxException;

public abstract class EndpointGetter {
    public static String getUrlWithoutArguments(@NonNull String inputUrl) {
        try {
            URI uri = new URI(inputUrl);
            inputUrl = new URI(uri.getScheme(), uri.getAuthority(), uri.getPath(), null, uri.getFragment()).toString();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return inputUrl;
    }
}
