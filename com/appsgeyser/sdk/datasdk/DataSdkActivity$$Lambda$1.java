package com.appsgeyser.sdk.datasdk;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;
import com.appsgeyser.sdk.configuration.models.ConfigPhp;

final /* synthetic */ class DataSdkActivity$$Lambda$1 implements OnClickListener {
    private final DataSdkActivity arg$1;
    private final ConfigPhp arg$2;
    private final Activity arg$3;

    private DataSdkActivity$$Lambda$1(DataSdkActivity dataSdkActivity, ConfigPhp configPhp, Activity activity) {
        this.arg$1 = dataSdkActivity;
        this.arg$2 = configPhp;
        this.arg$3 = activity;
    }

    public static OnClickListener lambdaFactory$(DataSdkActivity dataSdkActivity, ConfigPhp configPhp, Activity activity) {
        return new DataSdkActivity$$Lambda$1(dataSdkActivity, configPhp, activity);
    }

    public void onClick(View view) {
        DataSdkActivity.lambda$showEulaDialog$0(this.arg$1, this.arg$2, this.arg$3, view);
    }
}
