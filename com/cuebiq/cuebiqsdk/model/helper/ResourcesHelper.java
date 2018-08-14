package com.cuebiq.cuebiqsdk.model.helper;

import android.content.Context;
import com.cuebiq.cuebiqsdk.CuebiqSDKImpl;
import com.cuebiq.cuebiqsdk.api.BeaRequest;
import com.cuebiq.cuebiqsdk.injection.Injection;
import com.cuebiq.cuebiqsdk.model.CoverageManager;
import com.cuebiq.cuebiqsdk.model.persistence.PersistenceManagerFactory;
import com.cuebiq.cuebiqsdk.model.wrapper.Auth;
import com.cuebiq.cuebiqsdk.model.wrapper.CoverageSettings;
import com.cuebiq.cuebiqsdk.model.wrapper.Device;
import com.cuebiq.cuebiqsdk.model.wrapper.ServerResponseV2;
import com.cuebiq.cuebiqsdk.model.wrapper.TrackRequest;
import com.cuebiq.cuebiqsdk.task.GAIDRunnable;
import com.cuebiq.cuebiqsdk.task.GAIDRunnable.OnGAIDListener;
import com.cuebiq.cuebiqsdk.utils.Utils;
import cz.msebera.android.httpclient.HttpStatus;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Executors;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class ResourcesHelper {
    private static ResourcesHelper instance;

    public static ResourcesHelper get() {
        if (instance == null) {
            instance = new ResourcesHelper();
        }
        return instance;
    }

    private void sendRequest(final Context context, final TrackRequest trackRequest, String str, boolean z) {
        Auth auth = trackRequest.getAuth();
        Device device = trackRequest.getDevice();
        auth.setGoogleAdvertiserID(str);
        device.setIsGoogleAdvIDDisabled(Boolean.valueOf(z));
        if (new Random().nextInt(100) < CuebiqSDKImpl.getBeAudienceConfiguration(context).getIbtr()) {
            trackRequest.setPairedDevices(Utils.getPairedDevices());
        }
        trackRequest.setSettingsVersion(CuebiqSDKImpl.getBeAudienceConfiguration(context).getV());
        try {
            Injection.provideNetworkLayer().callAsync(new BeaRequest(trackRequest, PersistenceManagerFactory.get().retrieveAppKey(context)), new Callback() {
                public void onFailure(Call call, IOException iOException) {
                    PersistenceManagerFactory.get().persistRequest(context, trackRequest);
                }

                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        try {
                            ServerResponseV2 fromJSON = ServerResponseV2.fromJSON(response.body().string());
                            if (fromJSON.hasSettings()) {
                                try {
                                    CuebiqSDKImpl.log("ResourcesHelper -> Update configuration from server. OLD Version: " + CuebiqSDKImpl.getBeAudienceConfiguration(context).getV() + " NEW Version: " + fromJSON.getGs().getV());
                                    PersistenceManagerFactory.get().saveBeAudienceConfiguration(context, fromJSON.getGs());
                                } catch (Throwable th) {
                                }
                            }
                            if (fromJSON.hasCoverageSettings()) {
                                CoverageSettings cs = fromJSON.getCs();
                                CoverageManager.get().updateCoverageSettings(context, cs);
                                if (cs.hasCountryResponse() && !cs.isCountryOpen()) {
                                    CoverageManager.get().scheduleCheckCoverage(context, (long) ((fromJSON.getCs().getD().intValue() * 60) * 1000));
                                    return;
                                }
                                return;
                            }
                            return;
                        } catch (Throwable th2) {
                            return;
                        }
                    }
                    switch (response.networkResponse().code()) {
                        case HttpStatus.SC_BAD_REQUEST /*400*/:
                            CuebiqSDKImpl.log("ResourcesHelper -> Bad-formed json. Clear cache.");
                            PersistenceManagerFactory.get().persistRequest(context, null);
                            return;
                        case HttpStatus.SC_FORBIDDEN /*403*/:
                            CuebiqSDKImpl.log("ResourcesHelper -> Permission denied.");
                            PersistenceManagerFactory.get().persistRequest(context, trackRequest);
                            return;
                        case HttpStatus.SC_INTERNAL_SERVER_ERROR /*500*/:
                        case HttpStatus.SC_NOT_IMPLEMENTED /*501*/:
                        case HttpStatus.SC_BAD_GATEWAY /*502*/:
                        case HttpStatus.SC_SERVICE_UNAVAILABLE /*503*/:
                            CuebiqSDKImpl.log("ResourcesHelper -> Server Down");
                            PersistenceManagerFactory.get().persistRequest(context, trackRequest);
                            PersistenceManagerFactory.get().setNextFlushingContent(context, new Random().nextInt(100) + 1);
                            return;
                        default:
                            return;
                    }
                }
            });
        } catch (Throwable th) {
        }
    }

    public void bea(final Context context, final TrackRequest trackRequest) {
        Executors.newSingleThreadExecutor().submit(new GAIDRunnable(context.getApplicationContext(), new OnGAIDListener() {
            public void onGoogleAdvID(String str, boolean z) {
                if (z && CuebiqSDKImpl.getBeAudienceConfiguration(context).getTase() == 0) {
                    CuebiqSDKImpl.log("ResourcesHelper -> Device is OptedOut, abort request.");
                } else if (str == null || "".equals(str)) {
                    CuebiqSDKImpl.log("ResourcesHelper -> Failed to retrieve GoogleAdvertisingID.");
                    PersistenceManagerFactory.get().persistRequest(context, trackRequest);
                } else {
                    ResourcesHelper.this.sendRequest(context, trackRequest, str, z);
                }
            }
        }));
    }
}
