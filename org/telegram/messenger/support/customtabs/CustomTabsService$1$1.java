package org.telegram.messenger.support.customtabs;

import android.os.IBinder.DeathRecipient;
import org.telegram.messenger.support.customtabs.CustomTabsService.1;

class CustomTabsService$1$1 implements DeathRecipient {
    final /* synthetic */ 1 this$1;
    final /* synthetic */ CustomTabsSessionToken val$sessionToken;

    CustomTabsService$1$1(1 this$1, CustomTabsSessionToken customTabsSessionToken) {
        this.this$1 = this$1;
        this.val$sessionToken = customTabsSessionToken;
    }

    public void binderDied() {
        this.this$1.this$0.cleanUpSession(this.val$sessionToken);
    }
}
