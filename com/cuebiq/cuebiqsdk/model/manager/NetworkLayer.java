package com.cuebiq.cuebiqsdk.model.manager;

import com.cuebiq.cuebiqsdk.api.CuebiqRequest;
import java.io.IOException;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;

public class NetworkLayer {
    private final OkHttpClient mOkHttpClient;

    public NetworkLayer(OkHttpClient okHttpClient) {
        this.mOkHttpClient = okHttpClient;
    }

    public void callAsync(CuebiqRequest cuebiqRequest, Callback callback) {
        this.mOkHttpClient.newCall(cuebiqRequest.request()).enqueue(callback);
    }

    public Response callSync(CuebiqRequest cuebiqRequest) throws IOException {
        return this.mOkHttpClient.newCall(cuebiqRequest.request()).execute();
    }
}
