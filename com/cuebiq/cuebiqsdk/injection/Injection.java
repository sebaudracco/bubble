package com.cuebiq.cuebiqsdk.injection;

import com.cuebiq.cuebiqsdk.api.ApiConfiguration;
import com.cuebiq.cuebiqsdk.api.Environment;
import com.cuebiq.cuebiqsdk.api.GzipRequestInterceptor;
import com.cuebiq.cuebiqsdk.api.HttpLoggingInterceptor;
import com.cuebiq.cuebiqsdk.api.HttpLoggingInterceptor.Level;
import com.cuebiq.cuebiqsdk.model.listener.AuthSerializer;
import com.cuebiq.cuebiqsdk.model.listener.DeviceSerializer;
import com.cuebiq.cuebiqsdk.model.listener.EventSerializer;
import com.cuebiq.cuebiqsdk.model.listener.GeoSerializer;
import com.cuebiq.cuebiqsdk.model.listener.InformationSerializer;
import com.cuebiq.cuebiqsdk.model.listener.RequestSerializer;
import com.cuebiq.cuebiqsdk.model.listener.WifiSerializer;
import com.cuebiq.cuebiqsdk.model.manager.NetworkLayer;
import com.cuebiq.cuebiqsdk.model.wrapper.Auth;
import com.cuebiq.cuebiqsdk.model.wrapper.Device;
import com.cuebiq.cuebiqsdk.model.wrapper.Event;
import com.cuebiq.cuebiqsdk.model.wrapper.Geo;
import com.cuebiq.cuebiqsdk.model.wrapper.Information;
import com.cuebiq.cuebiqsdk.model.wrapper.TrackRequest;
import com.cuebiq.cuebiqsdk.model.wrapper.Wifi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;

public class Injection {
    private static Gson mGson;
    private static NetworkLayer mNetworkLayer;
    private static OkHttpClient mOkHttpClient;

    public static Gson provideGson() {
        if (mGson == null) {
            mGson = new GsonBuilder().serializeSpecialFloatingPointValues().registerTypeAdapter(Auth.class, new AuthSerializer()).registerTypeAdapter(Device.class, new DeviceSerializer()).registerTypeAdapter(Event.class, new EventSerializer()).registerTypeAdapter(Geo.class, new GeoSerializer()).registerTypeAdapter(Information.class, new InformationSerializer()).registerTypeAdapter(TrackRequest.class, new RequestSerializer()).registerTypeAdapter(Wifi.class, new WifiSerializer()).create();
        }
        return mGson;
    }

    public static OkHttpClient provideHttpClient() {
        if (mOkHttpClient == null) {
            Builder builder = new Builder();
            builder.addInterceptor(new GzipRequestInterceptor());
            builder.addInterceptor(new HttpLoggingInterceptor().setLevel(ApiConfiguration.workingEnvironment == Environment.PRODUCTION ? Level.NONE : Level.BODY));
            mOkHttpClient = builder.build();
        }
        return mOkHttpClient;
    }

    public static NetworkLayer provideNetworkLayer() {
        if (mNetworkLayer == null) {
            mNetworkLayer = new NetworkLayer(provideHttpClient());
        }
        return mNetworkLayer;
    }
}
