package com.mobfox.sdk.interstitialads;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import com.danikula.videocache.HttpProxyCacheServer;
import com.google.android.gms.common.util.CrashUtils.ErrorDialogData;
import com.mobfox.sdk.cache.CacheCounter;
import com.mobfox.sdk.cache.CacheCounterCB;
import com.mobfox.sdk.constants.Constants;
import com.mobfox.sdk.customevents.CustomEventInterstitial;
import com.mobfox.sdk.customevents.CustomEventInterstitialListener;
import com.mobfox.sdk.utils.ProxyFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class InterstitialEvent implements CustomEventInterstitial {
    public static final String CACHEABLES_FIELD = "cacheables";
    public static final String INTERSTITIAL_ACTIVITY_CLASSNAME = ".interstitialads.InterstitialActivity";
    static long VIDEO_TIMEOUT = 10000;
    JSONObject adResp;
    boolean closed = false;
    Context context;
    Intent interstitialActivityIntent;
    CustomEventInterstitialListener listener;
    protected InterstitialBroadcastReceiver mMessageReceiver;
    Handler mainHandler;

    protected class InterstitialBroadcastReceiver extends BroadcastReceiver {
        InterstitialEvent event;

        class C35691 implements Runnable {
            C35691() {
            }

            public void run() {
                InterstitialEvent.this.listener.onInterstitialClicked(InterstitialBroadcastReceiver.this.event);
            }
        }

        class C35702 implements Runnable {
            C35702() {
            }

            public void run() {
                InterstitialEvent.this.listener.onInterstitialFinished();
            }
        }

        class C35713 implements Runnable {
            C35713() {
            }

            public void run() {
                InterstitialEvent.this.listener.onInterstitialClosed(InterstitialBroadcastReceiver.this.event);
            }
        }

        class C35735 implements Runnable {
            C35735() {
            }

            public void run() {
                InterstitialEvent.this.listener.onInterstitialFailed(InterstitialBroadcastReceiver.this.event, new Exception("onAutoRedirect"));
            }
        }

        class C35746 implements Runnable {
            C35746() {
            }

            public void run() {
                InterstitialEvent.this.listener.onInterstitialShown(InterstitialBroadcastReceiver.this.event);
            }
        }

        InterstitialBroadcastReceiver(InterstitialEvent e) {
            this.event = e;
        }

        public void onReceive(Context context, Intent intent) {
            if (intent != null) {
                String message = intent.getStringExtra("message");
                Log.d(Constants.MOBFOX_INTERSTITIAL, "Got message: " + message);
                String data = intent.getStringExtra("data");
                boolean z = true;
                switch (message.hashCode()) {
                    case -2019859532:
                        if (message.equals("onRendered")) {
                            z = true;
                            break;
                        }
                        break;
                    case -1349867671:
                        if (message.equals("onError")) {
                            z = true;
                            break;
                        }
                        break;
                    case 157935686:
                        if (message.equals("onAdClick")) {
                            z = false;
                            break;
                        }
                        break;
                    case 601233006:
                        if (message.equals("onAdClosed")) {
                            z = true;
                            break;
                        }
                        break;
                    case 1017292330:
                        if (message.equals("onAutoRedirect")) {
                            z = true;
                            break;
                        }
                        break;
                    case 1250197681:
                        if (message.equals("onVideoAdFinished")) {
                            z = true;
                            break;
                        }
                        break;
                }
                switch (z) {
                    case false:
                        InterstitialEvent.this.mainHandler.post(new C35691());
                        return;
                    case true:
                        InterstitialEvent.this.mainHandler.post(new C35702());
                        return;
                    case true:
                        Log.d(Constants.MOBFOX_INTERSTITIAL, "interstitial event >> onAdClosed");
                        if (!InterstitialEvent.this.closed) {
                            InterstitialEvent.this.closed = true;
                            if (InterstitialEvent.this.mMessageReceiver != null) {
                                LocalBroadcastManager.getInstance(context).unregisterReceiver(InterstitialEvent.this.mMessageReceiver);
                            }
                            InterstitialEvent.this.mainHandler.post(new C35713());
                            return;
                        }
                        return;
                    case true:
                        try {
                            final Exception e = new Exception(new JSONObject(data).getString("data"));
                            InterstitialEvent.this.mainHandler.post(new Runnable() {
                                public void run() {
                                    InterstitialEvent.this.listener.onInterstitialFailed(InterstitialBroadcastReceiver.this.event, e);
                                }
                            });
                            return;
                        } catch (JSONException e2) {
                            Log.d(Constants.MOBFOX_INTERSTITIAL, "interstitial event JSONException");
                            return;
                        }
                    case true:
                        Log.d(Constants.MOBFOX_INTERSTITIAL, "onAutoRedirect, url: " + data);
                        InterstitialEvent.this.mainHandler.post(new C35735());
                        return;
                    case true:
                        Log.d(Constants.MOBFOX_INTERSTITIAL, "onRendered");
                        InterstitialEvent.this.mainHandler.post(new C35746());
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public InterstitialEvent(Context context, JSONObject adResp) {
        this.context = context;
        this.mainHandler = new Handler(context.getMainLooper());
        this.adResp = adResp;
        this.mMessageReceiver = new InterstitialBroadcastReceiver(this);
        this.interstitialActivityIntent = new Intent();
        this.interstitialActivityIntent.setFlags(ErrorDialogData.BINDER_CRASH);
        this.interstitialActivityIntent.setClassName(context.getPackageName(), "com.mobfox.sdk.interstitialads.InterstitialActivity");
        this.interstitialActivityIntent.putExtra("adResp", adResp.toString());
        this.interstitialActivityIntent.putExtra("orientation", context.getResources().getConfiguration().orientation);
        LocalBroadcastManager.getInstance(context).registerReceiver(this.mMessageReceiver, new IntentFilter("interstitialEvent"));
    }

    public void loadInterstitial(Context context, final CustomEventInterstitialListener listener, String networkID, Map<String, Object> map) {
        final InterstitialEvent self = this;
        this.listener = listener;
        List<String> cachedURLs = new ArrayList();
        HttpProxyCacheServer proxy = ProxyFactory.getProxy(this.context);
        if (this.adResp.has(CACHEABLES_FIELD)) {
            try {
                JSONArray cacheables = this.adResp.getJSONArray(CACHEABLES_FIELD);
                for (int i = 0; i < cacheables.length(); i++) {
                    cachedURLs.add(proxy.getProxyUrl(cacheables.getString(i), false));
                }
            } catch (JSONException e) {
            }
        }
        new CacheCounter(cachedURLs, this.context).cache(new CacheCounterCB() {

            class C35671 implements Runnable {
                C35671() {
                }

                public void run() {
                    listener.onInterstitialLoaded(self);
                }
            }

            public void onDone() {
                InterstitialEvent.this.mainHandler.post(new C35671());
            }
        });
    }

    public void showInterstitial() {
        this.closed = false;
        this.context.startActivity(this.interstitialActivityIntent);
    }
}
