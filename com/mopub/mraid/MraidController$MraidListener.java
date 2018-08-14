package com.mopub.mraid;

import android.view.View;

public interface MraidController$MraidListener {
    void onClose();

    void onExpand();

    void onFailedToLoad();

    void onLoaded(View view);

    void onOpen();
}
