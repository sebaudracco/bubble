package com.appsgeyser.sdk.datasdk;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import com.appsgeyser.sdk.configuration.models.ConfigPhp;

final /* synthetic */ class DataSdkActivity$$Lambda$2 implements OnClickListener {
    private final DataSdkActivity arg$1;
    private final Activity arg$2;
    private final ConfigPhp arg$3;
    private final String arg$4;

    private DataSdkActivity$$Lambda$2(DataSdkActivity dataSdkActivity, Activity activity, ConfigPhp configPhp, String str) {
        this.arg$1 = dataSdkActivity;
        this.arg$2 = activity;
        this.arg$3 = configPhp;
        this.arg$4 = str;
    }

    public static OnClickListener lambdaFactory$(DataSdkActivity dataSdkActivity, Activity activity, ConfigPhp configPhp, String str) {
        return new DataSdkActivity$$Lambda$2(dataSdkActivity, activity, configPhp, str);
    }

    public void onClick(View view) {
        DataSdkController.declineAllActiveSdk(this.arg$2, this.arg$3, this.arg$1.dataActivity, this.arg$4);
    }
}
