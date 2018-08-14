package com.appsgeyser.sdk.datasdk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.webkit.WebView;
import android.widget.TextView;
import com.appsgeyser.sdk.AppsgeyserSDK;
import com.appsgeyser.sdk.C1195R;
import com.appsgeyser.sdk.InternalEntryPoint;
import com.appsgeyser.sdk.ads.fastTrack.FastTrackAdsController;
import com.appsgeyser.sdk.configuration.PreferencesCoder;
import com.appsgeyser.sdk.configuration.models.ConfigPhp;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;

public class DataSdkActivity extends AppCompatActivity {
    private static final String CONFIG_PHP_KEY = "com.appsgeyser.sdk.permission.DataSdkActivity.configPhp";
    private static final String DATA_SDK_DIALOG_ACCEPT = "Accept";
    private static final String DATA_SDK_DIALOG_DECLINE = "Decline";
    private static final String ENCODING = "UTF-8";
    private static final String MIME_TYPE = "text/html";
    static final int PERMISSIONS_CALLBACK_CODE = 78;
    private static final String PERMISSION_ACTIVITY = "com.appsgeyser.sdk.permission.DataSdkActivity";
    private static final String TEXT_OF_POLICY_KEY = "com.appsgeyser.sdk.permission.DataSdkActivity.textOfPolicy";
    private ConfigPhp configPhp;
    private AppCompatActivity dataActivity;

    public static void startRequestPermissions(@NonNull Context context, @NonNull ConfigPhp configPhp, @Nullable String textOfPolicy) {
        if (PermissionsRequester.isPermissionsRequired(configPhp, context) || !(DataSdkController.isSdkAccepted(context) || TextUtils.isEmpty(textOfPolicy))) {
            Intent intent = new Intent(context, DataSdkActivity.class);
            intent.setFlags(ErrorDialogData.BINDER_CRASH);
            if (!TextUtils.isEmpty(textOfPolicy)) {
                intent.putExtra(TEXT_OF_POLICY_KEY, textOfPolicy);
            }
            intent.putExtra(CONFIG_PHP_KEY, configPhp);
            context.startActivity(intent);
            return;
        }
        InternalEntryPoint.getInstance().setConsentRequestProcessActive(false);
        DataSdkController.initSdk(configPhp, context);
    }

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.dataActivity = this;
        Intent intent = getIntent();
        if (intent != null) {
            this.configPhp = (ConfigPhp) intent.getParcelableExtra(CONFIG_PHP_KEY);
            String textOfPolicy;
            if (VERSION.SDK_INT >= 23) {
                textOfPolicy = intent.getStringExtra(TEXT_OF_POLICY_KEY);
                if (TextUtils.isEmpty(textOfPolicy)) {
                    if (PermissionsRequester.isPermissionsRequired(this.configPhp, this)) {
                        PermissionsRequester.requestAllActivePermissions(this, this.configPhp, 78);
                        return;
                    }
                    return;
                } else if (this.configPhp != null) {
                    showEulaDialog(textOfPolicy, this.configPhp);
                    return;
                } else {
                    return;
                }
            }
            textOfPolicy = intent.getStringExtra(TEXT_OF_POLICY_KEY);
            if (!TextUtils.isEmpty(textOfPolicy) && this.configPhp != null) {
                showEulaDialog(textOfPolicy, this.configPhp);
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onRequestPermissionsResult(int r8, @android.support.annotation.NonNull java.lang.String[] r9, @android.support.annotation.NonNull int[] r10) {
        /*
        r7 = this;
        r6 = -1;
        r5 = 78;
        if (r5 != r8) goto L_0x00ee;
    L_0x0005:
        r1 = 0;
        r3 = r9.length;	 Catch:{ all -> 0x006a }
    L_0x0007:
        if (r1 >= r3) goto L_0x00e2;
    L_0x0009:
        r2 = r9[r1];	 Catch:{ all -> 0x006a }
        r0 = r10[r1];	 Catch:{ all -> 0x006a }
        r4 = com.appsgeyser.sdk.server.StatController.getInstance();	 Catch:{ all -> 0x006a }
        r5 = r2.hashCode();	 Catch:{ all -> 0x006a }
        switch(r5) {
            case -1888586689: goto L_0x001f;
            case -406040016: goto L_0x0056;
            case -63024214: goto L_0x0040;
            case -5573545: goto L_0x0035;
            case 1271781903: goto L_0x002a;
            case 1365911975: goto L_0x004b;
            default: goto L_0x0018;
        };	 Catch:{ all -> 0x006a }
    L_0x0018:
        r5 = r6;
    L_0x0019:
        switch(r5) {
            case 0: goto L_0x0061;
            case 1: goto L_0x0081;
            case 2: goto L_0x0093;
            case 3: goto L_0x00a6;
            case 4: goto L_0x00ba;
            case 5: goto L_0x00ce;
            default: goto L_0x001c;
        };	 Catch:{ all -> 0x006a }
    L_0x001c:
        r1 = r1 + 1;
        goto L_0x0007;
    L_0x001f:
        r5 = "android.permission.ACCESS_FINE_LOCATION";
        r5 = r2.equals(r5);	 Catch:{ all -> 0x006a }
        if (r5 == 0) goto L_0x0018;
    L_0x0028:
        r5 = 0;
        goto L_0x0019;
    L_0x002a:
        r5 = "android.permission.GET_ACCOUNTS";
        r5 = r2.equals(r5);	 Catch:{ all -> 0x006a }
        if (r5 == 0) goto L_0x0018;
    L_0x0033:
        r5 = 1;
        goto L_0x0019;
    L_0x0035:
        r5 = "android.permission.READ_PHONE_STATE";
        r5 = r2.equals(r5);	 Catch:{ all -> 0x006a }
        if (r5 == 0) goto L_0x0018;
    L_0x003e:
        r5 = 2;
        goto L_0x0019;
    L_0x0040:
        r5 = "android.permission.ACCESS_COARSE_LOCATION";
        r5 = r2.equals(r5);	 Catch:{ all -> 0x006a }
        if (r5 == 0) goto L_0x0018;
    L_0x0049:
        r5 = 3;
        goto L_0x0019;
    L_0x004b:
        r5 = "android.permission.WRITE_EXTERNAL_STORAGE";
        r5 = r2.equals(r5);	 Catch:{ all -> 0x006a }
        if (r5 == 0) goto L_0x0018;
    L_0x0054:
        r5 = 4;
        goto L_0x0019;
    L_0x0056:
        r5 = "android.permission.READ_EXTERNAL_STORAGE";
        r5 = r2.equals(r5);	 Catch:{ all -> 0x006a }
        if (r5 == 0) goto L_0x0018;
    L_0x005f:
        r5 = 5;
        goto L_0x0019;
    L_0x0061:
        if (r0 != 0) goto L_0x0078;
    L_0x0063:
        r5 = "click_accept_permission_access_fine_location";
        r4.sendRequestAsyncByKey(r5);	 Catch:{ all -> 0x006a }
        goto L_0x001c;
    L_0x006a:
        r5 = move-exception;
        r6 = r7.configPhp;
        if (r6 == 0) goto L_0x0074;
    L_0x006f:
        r6 = r7.configPhp;
        com.appsgeyser.sdk.datasdk.DataSdkController.initSdk(r6, r7);
    L_0x0074:
        r7.finish();
        throw r5;
    L_0x0078:
        if (r0 != r6) goto L_0x001c;
    L_0x007a:
        r5 = "click_decline_permission_access_fine_location";
        r4.sendRequestAsyncByKey(r5);	 Catch:{ all -> 0x006a }
        goto L_0x001c;
    L_0x0081:
        if (r0 != 0) goto L_0x008a;
    L_0x0083:
        r5 = "click_accept_permission_get_accounts";
        r4.sendRequestAsyncByKey(r5);	 Catch:{ all -> 0x006a }
        goto L_0x001c;
    L_0x008a:
        if (r0 != r6) goto L_0x001c;
    L_0x008c:
        r5 = "click_decline_permission_get_accounts";
        r4.sendRequestAsyncByKey(r5);	 Catch:{ all -> 0x006a }
        goto L_0x001c;
    L_0x0093:
        if (r0 != 0) goto L_0x009c;
    L_0x0095:
        r5 = "click_accept_permission_read_phone_state";
        r4.sendRequestAsyncByKey(r5);	 Catch:{ all -> 0x006a }
        goto L_0x001c;
    L_0x009c:
        if (r0 != r6) goto L_0x001c;
    L_0x009e:
        r5 = "click_decline_permission_read_phone_state";
        r4.sendRequestAsyncByKey(r5);	 Catch:{ all -> 0x006a }
        goto L_0x001c;
    L_0x00a6:
        if (r0 != 0) goto L_0x00b0;
    L_0x00a8:
        r5 = "click_accept_permission_access_coarse_location";
        r4.sendRequestAsyncByKey(r5);	 Catch:{ all -> 0x006a }
        goto L_0x001c;
    L_0x00b0:
        if (r0 != r6) goto L_0x001c;
    L_0x00b2:
        r5 = "click_decline_permission_access_coarse_location";
        r4.sendRequestAsyncByKey(r5);	 Catch:{ all -> 0x006a }
        goto L_0x001c;
    L_0x00ba:
        if (r0 != 0) goto L_0x00c4;
    L_0x00bc:
        r5 = "click_accept_permission_write_external_storage";
        r4.sendRequestAsyncByKey(r5);	 Catch:{ all -> 0x006a }
        goto L_0x001c;
    L_0x00c4:
        if (r0 != r6) goto L_0x001c;
    L_0x00c6:
        r5 = "click_decline_permission_write_external_storage";
        r4.sendRequestAsyncByKey(r5);	 Catch:{ all -> 0x006a }
        goto L_0x001c;
    L_0x00ce:
        if (r0 != 0) goto L_0x00d8;
    L_0x00d0:
        r5 = "click_accept_permission_read_external_storage";
        r4.sendRequestAsyncByKey(r5);	 Catch:{ all -> 0x006a }
        goto L_0x001c;
    L_0x00d8:
        if (r0 != r6) goto L_0x001c;
    L_0x00da:
        r5 = "click_decline_permission_read_external_storage";
        r4.sendRequestAsyncByKey(r5);	 Catch:{ all -> 0x006a }
        goto L_0x001c;
    L_0x00e2:
        r5 = r7.configPhp;
        if (r5 == 0) goto L_0x00eb;
    L_0x00e6:
        r5 = r7.configPhp;
        com.appsgeyser.sdk.datasdk.DataSdkController.initSdk(r5, r7);
    L_0x00eb:
        r7.finish();
    L_0x00ee:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.appsgeyser.sdk.datasdk.DataSdkActivity.onRequestPermissionsResult(int, java.lang.String[], int[]):void");
    }

    public void showEulaDialog(@NonNull String textOfPolicy, @NonNull ConfigPhp configPhp) {
        View view = getLayoutInflater().inflate(C1195R.layout.appsgeysersdk_datasdk_dialog, null);
        ((TextView) view.findViewById(C1195R.id.appsgeysersdk_datasdk_dialog_title)).setText(getString(C1195R.string.appsgeysersdk_eula_title));
        Builder alertBuilder = new Builder(new ContextThemeWrapper(this, C1195R.style.MaterialAlertDialog));
        alertBuilder.setCancelable(false);
        TextView declineTextView = (TextView) view.findViewById(C1195R.id.appsgeysersdk_datasdk_dialog_decline);
        ((TextView) view.findViewById(C1195R.id.appsgeysersdk_datasdk_dialog_accept)).setOnClickListener(DataSdkActivity$$Lambda$1.lambdaFactory$(this, configPhp, this));
        declineTextView.setOnClickListener(DataSdkActivity$$Lambda$2.lambdaFactory$(this, this, configPhp, textOfPolicy));
        AlertDialog dialog = alertBuilder.create();
        WebView webView = (WebView) view.findViewById(C1195R.id.appsgeysersdk_datasdk_dialog_web_view);
        webView.loadData(0, "text/html", "UTF-8");
        webView.setScrollbarFadingEnabled(false);
        new PreferencesCoder(activity).savePrefLong(DataSdkController.PREFS_ELAPSED_TIME, 0);
        dialog.setView(view);
        dialog.show();
        LayoutParams layoutParams = new LayoutParams();
        if (dialog.getWindow() != null) {
            layoutParams.copyFrom(dialog.getWindow().getAttributes());
            layoutParams.width = -1;
            layoutParams.height = -1;
            dialog.getWindow().setAttributes(layoutParams);
        }
    }

    static /* synthetic */ void lambda$showEulaDialog$0(@NonNull DataSdkActivity this_, ConfigPhp configPhp, Activity activity, View view1) {
        if (VERSION.SDK_INT < 23 || !PermissionsRequester.isPermissionsRequired(configPhp, activity)) {
            DataSdkController.acceptAllActiveSdk(activity, configPhp);
            DataSdkController.initSdk(configPhp, activity);
            this_.finish();
            return;
        }
        DataSdkController.acceptAllActiveSdk(activity, configPhp);
        PermissionsRequester.requestAllActivePermissions(activity, configPhp, 78);
    }

    protected void onDestroy() {
        super.onDestroy();
        InternalEntryPoint.getInstance().setConsentRequestProcessActive(false);
        FastTrackAdsController fastTrackAdsController = AppsgeyserSDK.getFastTrackAdsController();
        if (fastTrackAdsController != null && fastTrackAdsController.getFullscreenPendingRequestTag() != null) {
            fastTrackAdsController.showFullscreen(fastTrackAdsController.getFullscreenPendingRequestTag(), null);
        }
    }
}
