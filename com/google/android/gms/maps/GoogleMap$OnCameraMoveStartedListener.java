package com.google.android.gms.maps;

public interface GoogleMap$OnCameraMoveStartedListener {
    public static final int REASON_API_ANIMATION = 2;
    public static final int REASON_DEVELOPER_ANIMATION = 3;
    public static final int REASON_GESTURE = 1;

    void onCameraMoveStarted(int i);
}
