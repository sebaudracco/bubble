package com.appsgeyser.sdk.deviceidparser;

import android.content.Context;

public interface IDeviceIdParserListener {
    void onDeviceIdParametersObtained(Context context, DeviceIdParameters deviceIdParameters);
}
