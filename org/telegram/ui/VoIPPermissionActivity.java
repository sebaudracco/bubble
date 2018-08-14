package org.telegram.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import com.appsgeyser.sdk.AppsgeyserSDK;
import org.telegram.messenger.voip.VoIPService;
import org.telegram.ui.Components.voip.VoIPHelper;

public class VoIPPermissionActivity extends Activity {

    class C69541 implements Runnable {
        C69541() {
        }

        public void run() {
            VoIPPermissionActivity.this.finish();
        }
    }

    @RequiresApi(23)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestPermissions(new String[]{"android.permission.RECORD_AUDIO"}, 101);
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode != 101) {
            return;
        }
        if (grantResults.length > 0 && grantResults[0] == 0) {
            if (VoIPService.getSharedInstance() != null) {
                VoIPService.getSharedInstance().acceptIncomingCall();
            }
            finish();
            startActivity(new Intent(this, VoIPActivity.class));
        } else if (VERSION.SDK_INT < 23 || shouldShowRequestPermissionRationale("android.permission.RECORD_AUDIO")) {
            finish();
        } else {
            if (VoIPService.getSharedInstance() != null) {
                VoIPService.getSharedInstance().declineIncomingCall();
            }
            VoIPHelper.permissionDenied(this, new C69541());
        }
    }

    protected void onPause() {
        super.onPause();
        AppsgeyserSDK.onPause(this);
    }

    protected void onResume() {
        super.onResume();
        AppsgeyserSDK.onResume(this);
    }
}
