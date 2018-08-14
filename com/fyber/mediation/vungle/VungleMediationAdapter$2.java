package com.fyber.mediation.vungle;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;
import com.vungle.publisher.VunglePub;

class VungleMediationAdapter$2 implements ActivityLifecycleCallbacks {
    final /* synthetic */ VungleMediationAdapter this$0;

    VungleMediationAdapter$2(VungleMediationAdapter this$0) {
        this.this$0 = this$0;
    }

    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
    }

    public void onActivityStarted(Activity activity) {
    }

    public void onActivityResumed(Activity activity) {
        VunglePub.getInstance().onResume();
    }

    public void onActivityPaused(Activity activity) {
        VunglePub.getInstance().onPause();
    }

    public void onActivityStopped(Activity activity) {
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
    }

    public void onActivityDestroyed(Activity activity) {
    }
}
