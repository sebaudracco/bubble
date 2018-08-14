package com.appsgeyser.sdk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.webkit.WebView;
import android.widget.FrameLayout;
import com.appsgeyser.sdk.ads.FullScreenBanner;
import com.appsgeyser.sdk.ads.FullScreenBanner.FullScreenEventListener;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;

public class AdActivity extends Activity implements FullScreenEventListener {
    public static void startActivity(Context context) {
        Intent intent = new Intent(context, AdActivity.class);
        intent.setFlags(ErrorDialogData.BINDER_CRASH);
        context.startActivity(intent);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        FullScreenBanner banner = InternalEntryPoint.getInstance().getFullScreenBanner(this);
        banner.setEventListener(this);
        WebView bannerView = banner.getWebView();
        removeView(bannerView);
        View view = getLayoutInflater().inflate(C1195R.layout.appsgeysersdk_adactivity, null);
        FrameLayout frameLayout = (FrameLayout) view.findViewById(C1195R.id.appsgeysersdk_adactivity_view_layout);
        bannerView.setLayoutParams(new LayoutParams(-1, -1));
        frameLayout.addView(bannerView);
        setContentView(view);
    }

    public void onBackPressed() {
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }

    private void removeView(View bannerView) {
        ViewParent parent = bannerView.getParent();
        if (parent != null) {
            ((ViewGroup) parent).removeView(bannerView);
        }
    }

    public void bannerClosed() {
        finish();
    }
}
