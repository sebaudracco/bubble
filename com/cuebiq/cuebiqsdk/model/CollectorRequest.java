package com.cuebiq.cuebiqsdk.model;

import android.content.Context;
import android.location.Location;
import com.cuebiq.cuebiqsdk.CuebiqSDKImpl;
import com.cuebiq.cuebiqsdk.api.EchoRequest;
import com.cuebiq.cuebiqsdk.injection.Injection;
import com.cuebiq.cuebiqsdk.model.config.Settings;
import com.cuebiq.cuebiqsdk.model.helper.ResourcesHelper;
import com.cuebiq.cuebiqsdk.model.persistence.PersistenceManagerFactory;
import com.cuebiq.cuebiqsdk.model.processor.InformationProcessor;
import com.cuebiq.cuebiqsdk.model.processor.WifiProcessor;
import com.cuebiq.cuebiqsdk.model.wrapper.Auth;
import com.cuebiq.cuebiqsdk.model.wrapper.Device;
import com.cuebiq.cuebiqsdk.model.wrapper.Geo;
import com.cuebiq.cuebiqsdk.model.wrapper.Information;
import com.cuebiq.cuebiqsdk.model.wrapper.IpAddress;
import com.cuebiq.cuebiqsdk.model.wrapper.TrackRequest;
import com.cuebiq.cuebiqsdk.receiver.CoverageReceiver.TRIGGER_TYPE;
import com.cuebiq.cuebiqsdk.utils.InformationList;
import com.cuebiq.cuebiqsdk.utils.Utils;
import com.cuebiq.cuebiqsdk.utils.WifiList;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class CollectorRequest {
    private Context mContext;
    private Information mInformation;
    private CollectorCallback mListener;
    private TrackRequest mTrackRequest;
    private TRIGGER_TYPE mType;

    public interface CollectorCallback {
        void onCollectorFinished();
    }

    private void reportAllOperationsCompleted() {
        Settings beAudienceConfiguration = CuebiqSDKImpl.getBeAudienceConfiguration(this.mContext);
        InformationList informationList = new InformationList();
        informationList.add(this.mInformation);
        this.mTrackRequest.setInformation(informationList);
        CuebiqSDKImpl.log("Collector -> Saving request... ");
        try {
            if (beAudienceConfiguration.getTlowo() == 1) {
                Geo geo = this.mTrackRequest.getInformation().getFirst().getGeo();
                WifiList wifis = this.mTrackRequest.getInformation().getFirst().getWifis();
                if (geo == null && wifis == null) {
                    CuebiqSDKImpl.log("Collector -> Geo information is missing. Wifis information is missing. Skip request...");
                    if (this.mListener != null) {
                        this.mListener.onCollectorFinished();
                        return;
                    }
                    return;
                } else if (geo == null && wifis.size() == 0) {
                    CuebiqSDKImpl.log("Collector -> Geo information is missing. Wifis information are empty. Skip request...");
                    if (this.mListener != null) {
                        this.mListener.onCollectorFinished();
                        return;
                    }
                    return;
                }
            }
        } catch (Throwable th) {
        }
        final InfoAnalysisResult analyzeRequest = BatchManager.analyzeRequest(PersistenceManagerFactory.get().retrieveRequest(this.mContext), this.mTrackRequest, (int) Utils.getBatteryLevel(this.mContext), beAudienceConfiguration);
        if (analyzeRequest.getTrackRequest() == null) {
            PersistenceManagerFactory.get().persistRequest(this.mContext, null);
            if (this.mListener != null) {
                this.mListener.onCollectorFinished();
            }
        } else if (callIpAddressAPI(analyzeRequest.getTrackRequest(), beAudienceConfiguration.getCiaa(), beAudienceConfiguration.getIpad() * 60)) {
            Injection.provideNetworkLayer().callAsync(new EchoRequest(PersistenceManagerFactory.get().retrieveAppKey(this.mContext)), new Callback() {
                public void onFailure(Call call, IOException iOException) {
                    CollectorRequest.this.saveAndFlushRequest(analyzeRequest);
                    if (CollectorRequest.this.mListener != null) {
                        CollectorRequest.this.mListener.onCollectorFinished();
                    }
                }

                public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()) {
                        try {
                            IpAddress ipAddress = (IpAddress) CuebiqSDKImpl.GSON.fromJson(response.body().string(), IpAddress.class);
                            if (Utils.isIPv4(ipAddress.getIpAddr())) {
                                analyzeRequest.getTrackRequest().getInformation().getLast().setIpAddressV4(ipAddress.getIpAddr());
                            } else if (Utils.isIPv6(ipAddress.getIpAddr())) {
                                analyzeRequest.getTrackRequest().getInformation().getLast().setIpAddressV6(ipAddress.getIpAddr());
                            }
                        } catch (Throwable th) {
                            th.printStackTrace();
                        }
                        CollectorRequest.this.saveAndFlushRequest(analyzeRequest);
                        if (CollectorRequest.this.mListener != null) {
                            CollectorRequest.this.mListener.onCollectorFinished();
                            return;
                        }
                        return;
                    }
                    throw new IOException(response.message());
                }
            });
        } else {
            saveAndFlushRequest(analyzeRequest);
            if (this.mListener != null) {
                this.mListener.onCollectorFinished();
            }
        }
    }

    private void saveAndFlushRequest(InfoAnalysisResult infoAnalysisResult) {
        PersistenceManagerFactory.get().persistRequest(this.mContext, infoAnalysisResult.getTrackRequest());
        if (!Utils.isFlushCounterActive(this.mContext) && BatchManager.flush(infoAnalysisResult.getTrackRequest(), CuebiqSDKImpl.getBeAudienceConfiguration(this.mContext))) {
            CuebiqSDKImpl.log("Collector -> Flushing request...");
            PersistenceManagerFactory.get().persistRequest(this.mContext, null);
            ResourcesHelper.get().bea(this.mContext.getApplicationContext(), infoAnalysisResult.getTrackRequest());
        }
    }

    public boolean callIpAddressAPI(TrackRequest trackRequest, int i, long j) {
        if (i == 0) {
            return false;
        }
        Information last = trackRequest.getInformation().getLast();
        return (last == null || last.getIpAddressV4() != null || last.getIpAddressV6() != null || last.getLastSeen() == null || last.getLastSeen().longValue() == 0 || last.getTimestamp() == null || last.getTimestamp().longValue() == 0 || last.getLastSeen().longValue() - last.getTimestamp().longValue() < j) ? false : true;
    }

    public void collect(Context context, Auth auth, Device device, Location location, boolean z, boolean z2, CollectorCallback collectorCallback, TRIGGER_TYPE trigger_type) {
        this.mContext = context;
        this.mListener = collectorCallback;
        this.mType = trigger_type;
        Settings beAudienceConfiguration = CuebiqSDKImpl.getBeAudienceConfiguration(this.mContext);
        this.mTrackRequest = new TrackRequest();
        this.mTrackRequest.setDevice(device);
        this.mTrackRequest.setAuth(auth);
        this.mInformation = new Information();
        this.mInformation.setGeofence(Boolean.valueOf(z));
        if (z2 && beAudienceConfiguration.getWfe() == 1) {
            new WifiProcessor().gather(this.mContext, this.mInformation, null);
        }
        new InformationProcessor().gather(this.mContext, this.mInformation, null);
        this.mInformation.setTimestamp(Long.valueOf(location.getTime() / 1000));
        this.mInformation.setGeo(Geo.build(location));
        reportAllOperationsCompleted();
    }
}
