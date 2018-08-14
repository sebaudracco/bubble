package org.telegram.messenger;

import android.content.Intent;
import com.google.android.gms.iid.InstanceIDListenerService;

public class GcmInstanceIDListenerService extends InstanceIDListenerService {

    class C48801 implements Runnable {
        C48801() {
        }

        public void run() {
            ApplicationLoader.postInitApplication();
            GcmInstanceIDListenerService.this.startService(new Intent(ApplicationLoader.applicationContext, GcmRegistrationIntentService.class));
        }
    }

    public void onTokenRefresh() {
        AndroidUtilities.runOnUIThread(new C48801());
    }
}
