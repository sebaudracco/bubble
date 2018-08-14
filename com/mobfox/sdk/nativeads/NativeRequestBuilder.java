package com.mobfox.sdk.nativeads;

import android.content.Context;
import android.net.Uri.Builder;
import com.mobfox.sdk.networking.RequestParams;
import com.mobfox.sdk.services.MobFoxAdIdService;
import com.mobfox.sdk.services.MobFoxAdIdService.Listener;
import java.util.Iterator;

public class NativeRequestBuilder {
    Context context;
    ReadyListener listener = null;
    RequestParams params;
    private String protocol = "http";

    public interface ReadyListener {
        void onReady(String str);
    }

    class C35861 implements Listener {
        C35861() {
        }

        public void onFinish(String advId) {
            if (advId.isEmpty()) {
                NativeRequestBuilder.this.params.setParam("dev_dnt", 1);
            }
            NativeRequestBuilder.this.params.setParam("o_andadvid", advId);
            NativeRequestBuilder.this.ready();
        }
    }

    public NativeRequestBuilder(Context context, RequestParams rp, boolean secure, ReadyListener listener) {
        this.context = context;
        this.listener = listener;
        if (secure) {
            this.protocol = "https";
        }
        this.params = new RequestParams(rp);
    }

    public RequestParams getParams() {
        return this.params;
    }

    public void build() {
        new MobFoxAdIdService(new C35861(), this.context).execute();
    }

    void ready() {
        Builder uri = new Builder().scheme(this.protocol).authority(RequestParams.domain).appendPath("request.php");
        Iterator<String> names = this.params.getNames();
        while (names.hasNext()) {
            String n = (String) names.next();
            uri.appendQueryParameter(n, String.valueOf(this.params.getParam(n)));
        }
        this.listener.onReady(uri.toString());
    }
}
