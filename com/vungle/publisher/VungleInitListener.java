package com.vungle.publisher;

/* compiled from: vungle */
public interface VungleInitListener {
    void onFailure(Throwable th);

    void onSuccess();
}
