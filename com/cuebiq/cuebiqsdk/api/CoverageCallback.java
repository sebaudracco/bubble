package com.cuebiq.cuebiqsdk.api;

import com.cuebiq.cuebiqsdk.model.CoverageManager.CoverageListener;
import com.cuebiq.cuebiqsdk.model.wrapper.ServerResponseV2;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class CoverageCallback implements Callback {
    private final CoverageListener mListener;

    public CoverageCallback(CoverageListener coverageListener) {
        this.mListener = coverageListener;
    }

    public void onFailure(Call call, IOException iOException) {
        iOException.printStackTrace();
    }

    public void onResponse(Call call, Response response) throws IOException {
        if (response.isSuccessful()) {
            ServerResponseV2 fromJSON = ServerResponseV2.fromJSON(response.body().string());
            if (!fromJSON.hasCoverageSettings() || !fromJSON.getCs().hasCountryResponse()) {
                return;
            }
            if (fromJSON.getCs().isCountryOpen()) {
                if (this.mListener != null) {
                    this.mListener.onCountryCovered(fromJSON.getCs());
                }
            } else if (this.mListener != null) {
                this.mListener.onCountryNotCovered((fromJSON.getCs().getD().intValue() * 60) * 1000);
            }
        } else if (this.mListener != null) {
            this.mListener.onError(response.message());
        }
    }
}
