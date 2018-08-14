package com.mopub.mobileads.dfp.adapters;

import android.graphics.drawable.Drawable;
import java.util.HashMap;

public interface DrawableDownloadListener {
    void onDownloadFailure();

    void onDownloadSuccess(HashMap<String, Drawable> hashMap);
}
