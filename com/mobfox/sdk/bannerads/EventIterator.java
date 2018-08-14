package com.mobfox.sdk.bannerads;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import com.mobfox.sdk.constants.Constants;
import com.mobfox.sdk.customevents.CustomEventBanner;
import com.mobfox.sdk.customevents.CustomEventBannerListener;
import com.mobfox.sdk.customevents.CustomEventData;
import com.mobfox.sdk.networking.MobFoxRequest;
import com.mobfox.sdk.webview.MobFoxWebView;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class EventIterator {
    BannerEvent bannerEvent = null;
    Context context;
    List<CustomEventData> customDataList = new ArrayList();
    Handler mainHandler;
    MobFoxWebView mobFoxWebView = null;
    Map<String, Object> params;
    String pixel;
    JSONObject respObj;

    public EventIterator(Context context, MobFoxWebView mobFoxWebView, JSONObject respObj, Map<String, Object> params) {
        this.context = context;
        this.mainHandler = new Handler(context.getMainLooper());
        this.params = params;
        this.mobFoxWebView = mobFoxWebView;
        this.respObj = respObj;
        try {
            JSONArray customEvents = respObj.getJSONArray("customEvents");
            this.customDataList = new ArrayList();
            if (customEvents != null && customEvents.length() > 0) {
                for (int i = 0; i < customEvents.length(); i++) {
                    try {
                        CustomEventData customData = CustomEventData.parseJSON((JSONObject) customEvents.get(i));
                        Class.forName(Constants.CUSTOM_PACKAGE + customData.className);
                        this.customDataList.add(customData);
                    } catch (JSONException e) {
                        Log.d(Constants.MOBFOX_BANNER, "iterator json exception");
                    } catch (ClassNotFoundException e2) {
                        Log.d(Constants.MOBFOX_BANNER, "iterator class not found exception");
                    } catch (Throwable th) {
                        Log.d(Constants.MOBFOX_BANNER, "class not found throwable");
                    }
                }
            }
        } catch (JSONException e3) {
            Log.d(Constants.MOBFOX_BANNER, "iterator json exception");
        }
    }

    public boolean hasNext() {
        if (this.customDataList.size() != 0 || this.respObj.has("ad") || this.respObj.has("vasts")) {
            return true;
        }
        return false;
    }

    public void callNextEvent(final CustomEventBannerListener listener) {
        CustomEventBannerListener customListener = new CustomEventBannerListener() {

            class C35543 implements Runnable {
                C35543() {
                }

                public void run() {
                    listener.onBannerFinished();
                }
            }

            public void onBannerLoaded(final View banner) {
                EventIterator.this.mainHandler.post(new Runnable() {
                    public void run() {
                        listener.onBannerLoaded(banner);
                    }
                });
                if (EventIterator.this.pixel != null) {
                    new MobFoxRequest(EventIterator.this.context, EventIterator.this.pixel).get(null);
                }
            }

            public void onBannerClosed(final View banner) {
                EventIterator.this.mainHandler.post(new Runnable() {
                    public void run() {
                        listener.onBannerClosed(banner);
                    }
                });
            }

            public void onBannerFinished() {
                EventIterator.this.mainHandler.post(new C35543());
            }

            public void onBannerClicked(final View banner) {
                EventIterator.this.mainHandler.post(new Runnable() {
                    public void run() {
                        listener.onBannerClicked(banner);
                    }
                });
            }

            public void onBannerError(final View banner, final Exception e) {
                EventIterator.this.mainHandler.post(new Runnable() {
                    public void run() {
                        listener.onBannerError(banner, e);
                    }
                });
            }
        };
        if (this.customDataList.size() > 0) {
            CustomEventData customData = (CustomEventData) this.customDataList.get(0);
            this.customDataList.remove(0);
            try {
                CustomEventBanner customBanner = (CustomEventBanner) Class.forName(Constants.CUSTOM_PACKAGE + customData.className).getConstructor(new Class[0]).newInstance(new Object[0]);
                this.pixel = customData.pixel;
                customBanner.loadAd(this.context, customListener, customData.networkId, this.params);
                return;
            } catch (ClassNotFoundException e) {
                Log.d(Constants.MOBFOX_BANNER, "ce ClassNotFoundException");
                return;
            } catch (InvocationTargetException e2) {
                Log.d(Constants.MOBFOX_BANNER, "ce InvocationTargetException");
                return;
            } catch (NoSuchMethodException e3) {
                Log.d(Constants.MOBFOX_BANNER, "ce NoSuchMethodException");
                return;
            } catch (InstantiationException e4) {
                Log.d(Constants.MOBFOX_BANNER, "ce InstantiationException");
                return;
            } catch (IllegalAccessException e5) {
                Log.d(Constants.MOBFOX_BANNER, "ce IllegalAccessException");
                return;
            } catch (Throwable th) {
                Log.d(Constants.MOBFOX_BANNER, "banner iterator error");
                return;
            }
        }
        this.pixel = null;
        this.bannerEvent = new BannerEvent(this.mobFoxWebView, this.respObj);
        this.bannerEvent.loadAd(this.context, customListener, null, null);
        if (this.respObj.has("ad")) {
            this.respObj.remove("ad");
        } else if (this.respObj.has("vasts")) {
            this.respObj.remove("vasts");
        }
    }
}
