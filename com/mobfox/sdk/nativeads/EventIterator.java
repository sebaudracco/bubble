package com.mobfox.sdk.nativeads;

import android.content.Context;
import android.util.Log;
import com.mobfox.sdk.constants.Constants;
import com.mobfox.sdk.customevents.CustomEventData;
import com.mobfox.sdk.customevents.CustomEventNative;
import com.mobfox.sdk.customevents.CustomEventNativeListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class EventIterator {
    NativeAd ad;
    Context context;
    List<CustomEventData> data;
    Map<String, Object> params;

    public EventIterator(Context c, List<CustomEventData> data, NativeAd ad, Map<String, Object> params) {
        this.data = new ArrayList(data);
        this.ad = ad;
        this.context = c;
        this.params = params;
        List<CustomEventData> nonexitent = new ArrayList();
        for (CustomEventData ced : data) {
            String clazz = Constants.CUSTOM_PACKAGE + ced.className + "mNative";
            try {
                Class.forName(clazz);
            } catch (ClassNotFoundException e) {
                Log.d(Constants.MOBFOX_NATIVE, "Custom Event class does not exist: " + clazz);
                nonexitent.add(ced);
            }
        }
        this.data.removeAll(nonexitent);
    }

    public boolean hasNext() {
        if (this.data.size() == 0 && this.ad == null) {
            return false;
        }
        return true;
    }

    public void callNextEvent(CustomEventNativeListener listener) {
        if (this.data.size() > 0) {
            CustomEventData ced = (CustomEventData) this.data.get(0);
            this.data.remove(0);
            try {
                CustomEventNative cen = (CustomEventNative) Class.forName(Constants.CUSTOM_PACKAGE + ced.className + "mNative").getConstructor(new Class[0]).newInstance(new Object[0]);
                List<Tracker> extraTrackers = new ArrayList();
                extraTrackers.add(new Tracker("impression", ced.pixel));
                cen.load(this.context, listener, ced.networkId, extraTrackers, this.params);
            } catch (Exception e) {
                Log.d(Constants.MOBFOX_NATIVE, "Error creating custom event");
            } catch (Throwable th) {
            }
        } else if (this.ad != null) {
            NativeEvent ne = new NativeEvent(this.ad);
            this.ad = null;
            ne.load(this.context, listener, null, null, null);
        }
    }

    public static EventIterator parse(Context c, JSONObject response, Map<String, List<String>> headers, Map<String, Object> params) {
        NativeAd ad = NativeAd.parse(response);
        List<CustomEventData> customEvents = new ArrayList();
        if (headers != null) {
            for (String header : headers.keySet()) {
                if (header != null && header.indexOf("X-CustomEvent") == 0) {
                    List<String> vals = (List) headers.get(header);
                    if (vals.size() > 0) {
                        try {
                            CustomEventData ced = CustomEventData.parseJSON(new JSONObject((String) vals.get(0)));
                            if (ced != null) {
                                customEvents.add(ced);
                            }
                        } catch (JSONException e) {
                            Log.d(Constants.MOBFOX_NATIVE, "unable to parse custom event");
                        } catch (Throwable th) {
                            Log.d(Constants.MOBFOX_NATIVE, "unable to parse custom event");
                        }
                    }
                }
            }
        }
        return new EventIterator(c, customEvents, ad, params);
    }
}
