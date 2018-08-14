package com.mobfox.sdk.nativeads;

import android.content.Context;
import android.location.Location;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import com.mobfox.sdk.constants.Constants;
import com.mobfox.sdk.customevents.CustomEventNative;
import com.mobfox.sdk.customevents.CustomEventNativeListener;
import com.mobfox.sdk.nativeads.NativeRequestBuilder.ReadyListener;
import com.mobfox.sdk.networking.AsyncCallbackJSON;
import com.mobfox.sdk.networking.MobFoxRequest;
import com.mobfox.sdk.networking.RequestParams;
import com.mobfox.sdk.runnables.NativeRunnable;
import com.mobfox.sdk.services.MobFoxLocationService;
import com.mobfox.sdk.services.MobFoxLocationService.Listener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class Native {
    private static final int MY_PERMISSIONS_REQUEST_FINE_LOCATION = 1;
    static boolean debug = false;
    public static boolean loc = false;
    NativeAd ad = null;
    Context context;
    CustomEventNativeListener customEventNativeListener;
    Handler handler = null;
    EventIterator iter;
    NativeListener listener = null;
    Location location;
    MobFoxLocationService locationService;
    public RequestParams params = new RequestParams();
    ReadyListener readyListener;
    AsyncCallbackJSON respHandler;
    boolean secure = false;
    Native self;

    class C35791 implements CustomEventNativeListener {
        C35791() {
        }

        public void onNativeReady(CustomEventNative customEventNative, NativeAd ad) {
            if (Native.this.listener != null) {
                final CustomEventNative customEventNative2 = customEventNative;
                final NativeAd nativeAd = ad;
                Native.this.handler.post(new NativeRunnable(Native.this.context, Native.this.self) {
                    public void mobFoxRun() {
                        Native.this.listener.onNativeReady(Native.this.self, customEventNative2, nativeAd);
                    }
                });
            }
        }

        public void onNativeError(final Exception e) {
            if (Native.this.iter.hasNext()) {
                Native.this.iter.callNextEvent(this);
                return;
            }
            Log.d(Constants.MOBFOX_NATIVE, "no more custom events");
            Native.this.handler.post(new NativeRunnable(Native.this.context, Native.this.self) {
                public void mobFoxRun() {
                    Native.this.listener.onNativeError(e);
                }
            });
        }

        public void onNativeClicked(CustomEventNative customEventNative) {
            Native.this.handler.post(new NativeRunnable(Native.this.context, Native.this.self) {
                public void mobFoxRun() {
                    Native.this.listener.onNativeClick(Native.this.ad);
                }
            });
        }
    }

    class C35802 implements AsyncCallbackJSON {
        C35802() {
        }

        public void onComplete(int code, JSONObject response, Map<String, List<String>> headers) {
            if (response.has("error")) {
                try {
                    Exception e;
                    if (response.getString("error").equals("No Ad Available")) {
                        e = new Exception("no ad");
                        if (Native.this.listener != null) {
                            Native.this.listener.onNativeError(e);
                            return;
                        }
                        return;
                    }
                    e = new Exception(response.getString("error"));
                    if (Native.this.listener != null) {
                        Native.this.listener.onNativeError(e);
                        return;
                    }
                    return;
                } catch (JSONException e2) {
                }
            }
            Native.this.iterate(Native.this.context, response, headers, new HashMap());
        }

        public void onError(Exception e) {
            if (e.getMessage() == null || e.getMessage().equals("empty json response.")) {
                e = new Exception("no ad");
            }
            if (Native.this.listener != null) {
                Native.this.listener.onNativeError(e);
            }
        }
    }

    class C35813 implements ReadyListener {
        C35813() {
        }

        public void onReady(String url) {
            Log.d(Constants.MOBFOX_NATIVE, "url");
            if (Native.this.location != null) {
                new MobFoxRequest(Native.this.context, Uri.parse(url).buildUpon().appendQueryParameter("latitude", String.valueOf(Native.this.location.getLatitude())).appendQueryParameter("longitude", String.valueOf(Native.this.location.getLongitude())).build().toString()).getJSON(Native.this.respHandler);
                return;
            }
            new MobFoxRequest(Native.this.context, url).getJSON(Native.this.respHandler);
        }
    }

    class C35824 implements Listener {
        C35824() {
        }

        public void onLocation(Location location) {
            Native.this.setLocation(location);
        }

        public void onError(String err) {
            Log.d(Constants.MOBFOX_BANNER, NotificationCompat.CATEGORY_ERROR);
        }
    }

    public Native(Context context) {
        this.context = context;
        this.self = this;
        this.handler = new Handler(context.getMainLooper());
        init();
    }

    public void init() {
        getLocation();
        this.customEventNativeListener = new C35791();
        this.respHandler = new C35802();
        this.readyListener = new C35813();
    }

    public void load(String invh) {
        if (invh == null || invh.isEmpty()) {
            Log.d(Constants.MOBFOX_NATIVE, "no invh provided, aborting.");
            if (this.listener != null) {
                this.listener.onNativeError(new Exception("inventory hash is not available"));
                return;
            }
            return;
        }
        this.params.setParam("s", invh);
        if (debug) {
            this.params.setParam(RequestParams.f9036M, "test");
        }
        makeRequest(this.context, this.params, this.secure, this.readyListener);
    }

    void makeRequest(Context context, RequestParams params, boolean secure, ReadyListener readyListener) {
        new NativeRequestBuilder(context, params, secure, readyListener).build();
    }

    void iterate(Context context, JSONObject response, Map<String, List<String>> headers, HashMap<String, Object> hashMap) {
        this.iter = EventIterator.parse(context, response, headers, new HashMap());
        handleEvents();
    }

    protected void getLocation() {
        if (loc) {
            this.locationService = new MobFoxLocationService(new C35824(), this.context);
            this.locationService.execute();
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (this.locationService != null) {
            this.locationService.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    void handleEvents() {
        Log.d(Constants.MOBFOX_NATIVE, "handle custom events");
        if (this.iter.hasNext()) {
            this.iter.callNextEvent(this.customEventNativeListener);
        } else {
            this.listener.onNativeError(new Exception("no native ad returned"));
        }
    }

    public NativeListener getListener() {
        return this.listener;
    }

    public AsyncCallbackJSON getRespHandler() {
        return this.respHandler;
    }

    public void setRespHandler(AsyncCallbackJSON respHandler) {
        this.respHandler = respHandler;
    }

    public static void setDebug(boolean debug) {
        debug = debug;
    }

    public void setParams(RequestParams params) {
        this.params = params;
    }

    public void setListener(NativeListener listener) {
        this.listener = listener;
    }

    public void setSecure(boolean secure) {
        this.secure = secure;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setReadyListener(ReadyListener readyListener) {
        this.readyListener = readyListener;
    }

    public static void setLoc(boolean loc) {
        loc = loc;
    }
}
