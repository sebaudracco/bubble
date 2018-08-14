package com.mobfox.sdk.interstitialads;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import com.mobfox.sdk.constants.Constants;
import com.mobfox.sdk.customevents.CustomEventData;
import com.mobfox.sdk.customevents.CustomEventInterstitial;
import com.mobfox.sdk.customevents.CustomEventInterstitialListener;
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
    JSONObject ad;
    JSONObject adResp;
    Context context;
    List<CustomEventData> customDataList = new ArrayList();
    Handler handler;
    protected CustomEventInterstitialListener internalListener;
    InterstitialEvent interstitialEvent;
    Map<String, Object> params;
    String pixel;
    MobFoxWebView wv;

    public EventIterator(Context context, MobFoxWebView wv, JSONObject adResp, Map<String, Object> params) {
        this.context = context;
        this.params = params;
        this.wv = wv;
        this.adResp = adResp;
        this.handler = new Handler(context.getMainLooper());
        try {
            JSONArray customEvents = adResp.getJSONArray("customEvents");
            if (customEvents != null && customEvents.length() > 0) {
                for (int i = 0; i < customEvents.length(); i++) {
                    String clazz = "";
                    try {
                        CustomEventData customData = CustomEventData.parseJSON((JSONObject) customEvents.get(i));
                        Class.forName(Constants.CUSTOM_PACKAGE + customData.className + "Interstitial");
                        this.customDataList.add(customData);
                    } catch (JSONException e) {
                        Log.d(Constants.MOBFOX_INTERSTITIAL, "custom Events JSONException");
                    } catch (ClassNotFoundException e2) {
                        Log.d(Constants.MOBFOX_INTERSTITIAL, "custom Events ClassNotFoundException");
                    } catch (Throwable th) {
                        Log.d(Constants.MOBFOX_INTERSTITIAL, "custom Events Throwable");
                    }
                }
            }
        } catch (JSONException e3) {
            Log.d(Constants.MOBFOX_INTERSTITIAL, "iterator parse error");
        }
    }

    public boolean hasNext() {
        if (this.customDataList.size() != 0 || this.adResp.has("ad") || this.adResp.has("vasts")) {
            return true;
        }
        return false;
    }

    public void callNextEvent(final CustomEventInterstitialListener listener) {
        this.internalListener = new CustomEventInterstitialListener() {
            public void onInterstitialLoaded(CustomEventInterstitial interstitial) {
                listener.onInterstitialLoaded(interstitial);
            }

            public void onInterstitialFailed(CustomEventInterstitial interstitial, Exception e) {
                listener.onInterstitialFailed(interstitial, e);
            }

            public void onInterstitialClosed(CustomEventInterstitial interstitial) {
                Log.d(Constants.MOBFOX_INTERSTITIAL, "interstitial iterator >> onInterstitialClosed");
                listener.onInterstitialClosed(interstitial);
            }

            public void onInterstitialFinished() {
                listener.onInterstitialFinished();
            }

            public void onInterstitialClicked(CustomEventInterstitial interstitial) {
                listener.onInterstitialClicked(interstitial);
            }

            public void onInterstitialShown(CustomEventInterstitial interstitial) {
                if (EventIterator.this.pixel != null) {
                    new MobFoxRequest(EventIterator.this.context, EventIterator.this.pixel).get(null);
                } else {
                    Log.d(Constants.MOBFOX_INTERSTITIAL, "pixel is null");
                }
                listener.onInterstitialShown(interstitial);
            }
        };
        if (this.customDataList.size() > 0) {
            CustomEventData customData = (CustomEventData) this.customDataList.get(0);
            this.customDataList.remove(0);
            try {
                CustomEventInterstitial customInterstitial = (CustomEventInterstitial) Class.forName(Constants.CUSTOM_PACKAGE + customData.className + "Interstitial").getConstructor(new Class[0]).newInstance(new Object[0]);
                this.pixel = customData.pixel;
                customInterstitial.loadInterstitial(this.context, this.internalListener, customData.networkId, this.params);
            } catch (InstantiationException e) {
                Log.d(Constants.MOBFOX_INTERSTITIAL, "custom event InstantiationException");
            } catch (InvocationTargetException e2) {
                Log.d(Constants.MOBFOX_INTERSTITIAL, "custom event InvocationTargetException");
            } catch (NoSuchMethodException e3) {
                Log.d(Constants.MOBFOX_INTERSTITIAL, "custom event NoSuchMethodException");
            } catch (IllegalAccessException e4) {
                Log.d(Constants.MOBFOX_INTERSTITIAL, "custom event IllegalAccessException");
            } catch (ClassNotFoundException e5) {
                Log.d(Constants.MOBFOX_INTERSTITIAL, "custom event ClassNotFoundException");
            } catch (Throwable th) {
                Log.d(Constants.MOBFOX_INTERSTITIAL, "custom event Throwable");
            }
        } else if (this.adResp.has("ad") || this.adResp.has("vasts")) {
            this.interstitialEvent = new InterstitialEvent(this.context, this.adResp);
            this.interstitialEvent.loadInterstitial(this.context, this.internalListener, null, null);
            if (this.adResp.has("ad")) {
                this.adResp.remove("ad");
            }
            if (this.adResp.has("vasts")) {
                this.adResp.remove("vasts");
            }
        } else {
            Log.d(Constants.MOBFOX_INTERSTITIAL, "no adds to show");
        }
    }

    public List<CustomEventData> getCustomDataList() {
        return this.customDataList;
    }

    public JSONObject getAd() {
        return this.ad;
    }

    public InterstitialEvent getInterstitialEvent() {
        return this.interstitialEvent;
    }
}
