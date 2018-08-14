package org.telegram.messenger;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import java.util.TimeZone;

class LocaleController$TimeZoneChangedReceiver extends BroadcastReceiver {
    final /* synthetic */ LocaleController this$0;

    class C49261 implements Runnable {
        C49261() {
        }

        public void run() {
            if (!LocaleController$TimeZoneChangedReceiver.this.this$0.formatterMonth.getTimeZone().equals(TimeZone.getDefault())) {
                LocaleController.getInstance().recreateFormatters();
            }
        }
    }

    private LocaleController$TimeZoneChangedReceiver(LocaleController localeController) {
        this.this$0 = localeController;
    }

    public void onReceive(Context context, Intent intent) {
        ApplicationLoader.applicationHandler.post(new C49261());
    }
}
