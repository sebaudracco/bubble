package org.telegram.messenger;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.appsgeyser.sdk.AppsgeyserSDK;
import org.telegram.ui.LaunchActivity;

public class OpenChatReceiver extends Activity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        if (intent == null) {
            finish();
        }
        if (intent.getAction() == null || !intent.getAction().startsWith("com.tmessages.openchat")) {
            finish();
            return;
        }
        Intent intent2 = new Intent(this, LaunchActivity.class);
        intent2.setAction(intent.getAction());
        intent2.putExtras(intent);
        startActivity(intent2);
        finish();
    }

    protected void onResume() {
        super.onResume();
        AppsgeyserSDK.onResume(this);
    }

    protected void onPause() {
        super.onPause();
        AppsgeyserSDK.onPause(this);
    }
}
