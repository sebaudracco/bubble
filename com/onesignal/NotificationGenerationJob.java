package com.onesignal;

import android.content.Context;
import com.onesignal.NotificationExtenderService.OverrideSettings;
import java.util.Random;
import org.json.JSONObject;

class NotificationGenerationJob {
    Context context;
    JSONObject jsonPayload;
    OverrideSettings overrideSettings;
    boolean restoring;
    boolean showAsAlert;
    Long shownTimeStamp;

    NotificationGenerationJob(Context context) {
        this.context = context;
    }

    Integer getAndroidId() {
        if (this.overrideSettings == null) {
            this.overrideSettings = new OverrideSettings();
        }
        if (this.overrideSettings.androidNotificationId == null) {
            this.overrideSettings.androidNotificationId = Integer.valueOf(new Random().nextInt());
        }
        return this.overrideSettings.androidNotificationId;
    }

    void setAndroidIdWithOutOverriding(Integer id) {
        if (id != null) {
            if (this.overrideSettings == null || this.overrideSettings.androidNotificationId == null) {
                if (this.overrideSettings == null) {
                    this.overrideSettings = new OverrideSettings();
                }
                this.overrideSettings.androidNotificationId = id;
            }
        }
    }
}
