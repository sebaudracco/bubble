package com.appsgeyser.sdk.push;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.appsgeyser.sdk.C1195R;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import org.telegram.tgnet.ConnectionsManager;

public class MessageViewer extends Activity {
    private static final String ENCODING = "utf-8";
    private static final String MESSAGE_KEY = "com.appsgeyser.sdk.push.MessageViewer.message";
    private static final String MESSAGE_VIEWER = "com.appsgeyser.sdk.push.MessageViewer";
    private static final String MIME_TYPE = "text/html";
    private static final String TITLE_KEY = "com.appsgeyser.sdk.push.MessageViewer.title";

    public static void launchWithContent(@NonNull Context context, @NonNull String title, @NonNull String message) {
        Intent intent = new Intent(context, MessageViewer.class);
        intent.addFlags(ConnectionsManager.FileTypeFile);
        intent.addFlags(ErrorDialogData.BINDER_CRASH);
        intent.addFlags(2097152);
        intent.putExtra(TITLE_KEY, title);
        intent.putExtra(MESSAGE_KEY, message);
        context.startActivity(intent);
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(C1195R.layout.appsgeysersdk_message_viewer);
        Intent intent = getIntent();
        if (intent != null) {
            if (VERSION.SDK_INT >= 11) {
                ActionBar actionBar = getActionBar();
                if (actionBar != null) {
                    actionBar.hide();
                }
            }
            TextView textView = (TextView) findViewById(C1195R.id.message_viewer_app_name_text_view);
            PackageManager packageManager = getPackageManager();
            CharSequence label = packageManager.getApplicationLabel(getApplicationInfo());
            if (label != null) {
                textView.setText(label);
            } else {
                textView.setVisibility(8);
            }
            ImageView imageView = (ImageView) findViewById(C1195R.id.message_viewer_imageView_icon);
            Drawable applicationIcon = packageManager.getApplicationIcon(getApplicationInfo());
            if (applicationIcon != null) {
                Resources resources = getResources();
                int pixels = (int) (0.5f + (resources.getDisplayMetrics().density * 36.0f));
                imageView.setImageDrawable(new BitmapDrawable(resources, Bitmap.createScaledBitmap(((BitmapDrawable) applicationIcon).getBitmap(), pixels, pixels, true)));
            } else {
                imageView.setVisibility(8);
            }
            if (imageView.getVisibility() == 8 && textView.getVisibility() == 8) {
                ((RelativeLayout) findViewById(C1195R.id.message_viewer_app_bar_fake)).setVisibility(8);
            }
            WebView webView = (WebView) findViewById(C1195R.id.message_viewer_web_view);
            String title = intent.getStringExtra(TITLE_KEY);
            String message = intent.getStringExtra(MESSAGE_KEY);
            if (!(title == null || message == null)) {
                webView.loadDataWithBaseURL(null, createHtml(title, message), "text/html", "utf-8", null);
                WebSettings settings = webView.getSettings();
                settings.setJavaScriptEnabled(true);
                settings.setJavaScriptCanOpenWindowsAutomatically(true);
                settings.setAllowFileAccess(true);
                settings.setGeolocationEnabled(true);
                settings.setCacheMode(-1);
                webView.setVerticalScrollBarEnabled(false);
                webView.setHorizontalScrollBarEnabled(false);
                settings.setLoadWithOverviewMode(true);
                settings.setUseWideViewPort(true);
                webView.setInitialScale(0);
            }
            final Context activity = this;
            findViewById(C1195R.id.message_viewer_button).setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    Intent intent = activity.getPackageManager().getLaunchIntentForPackage(activity.getPackageName());
                    intent.addFlags(ConnectionsManager.FileTypeFile);
                    intent.addFlags(ErrorDialogData.BINDER_CRASH);
                    intent.addFlags(2097152);
                    activity.startActivity(intent);
                }
            });
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode != 4) {
            return super.onKeyDown(keyCode, event);
        }
        finish();
        return true;
    }

    protected void onPause() {
        super.onPause();
        if (isFinishing()) {
            finish();
        }
    }

    private String createHtml(@NonNull String title, @NonNull String message) {
        return "<!DOCTYPE html><html><head><title></title><meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"><style>body {background-color:#ffffff;background-repeat:no-repeat;background-position:top left;background-attachment:fixed;}h1{font-family:Arial, sans-serif;font-size:16px;color:#000000;background-color:#ffffff;}p {font-family:Georgia, serif;font-size:14px;font-style:normal;font-weight:normal;color:#000000;background-color:#ffffff;}</style></head><body><h1>" + title + "</h1>" + message + "</body></html>";
    }
}
