package com.google.android.exoplayer2.drm;

import android.annotation.TargetApi;
import android.os.Looper;

@TargetApi(16)
public interface DrmSessionManager<T extends ExoMediaCrypto> {
    DrmSession<T> acquireSession(Looper looper, DrmInitData drmInitData);

    void releaseSession(DrmSession<T> drmSession);
}
