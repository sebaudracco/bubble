package com.mobfox.sdk.interstitialads;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import com.mobfox.sdk.constants.Constants;
import com.mobfox.sdk.webview.MobFoxWebView;
import com.mobfox.sdk.webview.MobFoxWebViewRenderAdListener;
import org.json.JSONException;
import org.json.JSONObject;

public class InterstitialActivity extends Activity {
    JSONObject adResp = null;
    String adRespString;
    int callerOrientation;
    boolean ready = false;
    MobFoxWebViewRenderAdListener renderAdListener;
    Activity self;
    MobFoxWebView webView;

    class C35621 implements MobFoxWebViewRenderAdListener {
        C35621() {
        }

        public void onError(MobFoxWebView wv, Exception e) {
            if (e.getMessage() != null) {
                Log.d(Constants.MOBFOX_INTERSTITIAL, "on webView error " + e.getMessage());
            }
            JSONObject data = new JSONObject();
            try {
                data.put("data", e.toString());
            } catch (JSONException e2) {
            }
            InterstitialActivity.this.sendMessage("onError", data.toString());
        }

        public void onAdClick(MobFoxWebView wv, String clickURL) {
            Log.d(Constants.MOBFOX_INTERSTITIAL, "on ad clicked");
            if (clickURL == null) {
                Log.d(Constants.MOBFOX_INTERSTITIAL, "click Url null");
            } else if (clickURL.length() == 0) {
                Log.d(Constants.MOBFOX_INTERSTITIAL, "click Url empty");
            } else {
                try {
                    Intent launchBrowser = new Intent("android.intent.action.VIEW");
                    launchBrowser.setData(Uri.parse(clickURL));
                    launchBrowser.setFlags(ErrorDialogData.BINDER_CRASH);
                    InterstitialActivity.this.startActivity(launchBrowser);
                } catch (Exception e) {
                    onError(wv, e);
                } catch (Throwable e2) {
                    onError(wv, new Exception(e2.getMessage()));
                }
                JSONObject data = new JSONObject();
                try {
                    data.put("data", clickURL);
                } catch (JSONException e3) {
                }
                InterstitialActivity.this.sendMessage("onAdClick", data.toString());
            }
        }

        public void onVideoAdFinished(MobFoxWebView wv) {
            InterstitialActivity.this.sendMessage("onVideoAdFinished", "");
        }

        public void onAdClosed(MobFoxWebView wv) {
            Log.d(Constants.MOBFOX_INTERSTITIAL, "interstitial activity >> onAdClosed");
            InterstitialActivity.this.finish();
        }

        public void onAutoRedirect(MobFoxWebView wv, String url) {
            InterstitialActivity.this.sendMessage("onAutoRedirect", url);
            InterstitialActivity.this.finish();
        }

        public void onRendered(MobFoxWebView wv, String data) {
            if (data.isEmpty()) {
                InterstitialActivity.this.sendMessage("onRendered", "");
            } else {
                InterstitialActivity.this.sendMessage("onError", data);
            }
        }
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        boolean isLandscapeVideo = isLandscapeVideo();
        if (!isLandscapeVideo || getResources().getConfiguration().orientation == 2) {
            if (isLandscapeVideo) {
                setRequestedOrientation(0);
            }
            this.callerOrientation = getIntent().getIntExtra("orientation", 1);
            if (isLandscapeVideo || this.callerOrientation == getResources().getConfiguration().orientation) {
                if (!isLandscapeVideo) {
                    if (getResources().getConfiguration().orientation == 2) {
                        setRequestedOrientation(0);
                    }
                    if (getResources().getConfiguration().orientation == 1) {
                        setRequestedOrientation(1);
                    }
                }
                hideBar();
                setupWebView();
                return;
            }
            if (this.callerOrientation == 2) {
                setRequestedOrientation(0);
            }
            if (this.callerOrientation == 1) {
                setRequestedOrientation(1);
                return;
            }
            return;
        }
        setRequestedOrientation(0);
    }

    void hideBar() {
        if (VERSION.SDK_INT < 16) {
            getWindow().setFlags(1024, 1024);
            return;
        }
        getWindow().getDecorView().setSystemUiVisibility(4);
        ActionBar actionBar = getActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    boolean isLandscapeVideo() {
        try {
            this.adRespString = getIntent().getStringExtra("adResp");
            this.adResp = new JSONObject(this.adRespString);
            if (this.adResp.get("type").equals("video")) {
                return true;
            }
            return false;
        } catch (Exception e) {
            Log.d(Constants.MOBFOX_INTERSTITIAL, "adResp json exception " + e.getMessage());
            JSONObject data = new JSONObject();
            try {
                data.put("data", e.toString());
            } catch (JSONException e2) {
            }
            sendMessage("onError", data.toString());
            finish();
            return false;
        }
    }

    void setupWebView() {
        this.self = this;
        this.renderAdListener = new C35621();
        this.webView = new MobFoxWebView(this, null);
        this.webView.setBackgroundColor(-16777216);
        this.webView.setRenderAdListener(this.renderAdListener);
        this.webView.renderAd(this.adResp);
        setContentView(this.webView);
    }

    protected void sendMessage(String message, String data) {
        Log.d(Constants.MOBFOX_INTERSTITIAL, "inter activity >>> Broadcasting message: " + message);
        Intent interstitialEventIntent = new Intent("interstitialEvent");
        interstitialEventIntent.putExtra("message", message);
        if (data != null && data.length() > 0) {
            interstitialEventIntent.putExtra("data", data);
        }
        LocalBroadcastManager.getInstance(this).sendBroadcast(interstitialEventIntent);
    }

    protected void onPause() {
        super.onPause();
        if (this.webView != null) {
            this.webView.onPause();
        }
    }

    protected void onResume() {
        super.onResume();
        if (this.webView != null) {
            this.webView.onResume();
        }
    }

    public void finish() {
        super.finish();
        sendMessage("onAdClosed", "");
        unlock();
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setRequestedOrientation(0);
    }

    void lock() {
        try {
            int orientation = this.callerOrientation;
            if (orientation == 1 || orientation == 0) {
                setRequestedOrientation(1);
            }
            if (orientation == 2) {
                setRequestedOrientation(0);
            }
        } catch (Exception e) {
            Log.d(Constants.MOBFOX_INTERSTITIAL, "interstitial activity lock orientation exception");
            setRequestedOrientation(1);
        }
    }

    void unlock() {
        setRequestedOrientation(-1);
    }

    public void setWebView(MobFoxWebView webView) {
        this.webView = webView;
    }

    protected void onStop() {
        super.onStop();
    }
}
