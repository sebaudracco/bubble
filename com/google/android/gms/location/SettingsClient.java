package com.google.android.gms.location;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Api$ApiOptions$NoOptions;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.ApiExceptionMapper;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.tasks.Task;

public class SettingsClient extends GoogleApi<Api$ApiOptions$NoOptions> {
    public SettingsClient(@NonNull Activity activity) {
        super(activity, LocationServices.API, null, new ApiExceptionMapper());
    }

    public SettingsClient(@NonNull Context context) {
        super(context, LocationServices.API, null, new ApiExceptionMapper());
    }

    public Task<LocationSettingsResponse> checkLocationSettings(LocationSettingsRequest locationSettingsRequest) {
        return PendingResultUtil.toResponseTask(LocationServices.SettingsApi.checkLocationSettings(asGoogleApiClient(), locationSettingsRequest), new LocationSettingsResponse());
    }
}
