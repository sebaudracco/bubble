package com.mobfox.sdk.webview;

import android.content.Context;
import android.util.Log;
import com.github.lzyzsd.jsbridge.BridgeHandler;
import com.github.lzyzsd.jsbridge.CallBackFunction;
import com.mobfox.sdk.constants.Constants;
import com.mobfox.sdk.runnables.WebViewRunnable;
import org.json.JSONException;
import org.json.JSONObject;

class MobFoxWebView$7 implements BridgeHandler {
    final /* synthetic */ MobFoxWebView this$0;
    final /* synthetic */ Context val$context;
    final /* synthetic */ MobFoxWebView val$self;

    MobFoxWebView$7(MobFoxWebView this$0, Context context, MobFoxWebView mobFoxWebView) {
        this.this$0 = this$0;
        this.val$context = context;
        this.val$self = mobFoxWebView;
    }

    public void handler(String data, CallBackFunction function) {
        Log.d(Constants.MOBFOX_WEBVIEW, "data >>> :" + data);
        try {
            final JSONObject respObj = new JSONObject(data);
            if (respObj.has("customEvents") || respObj.has("vasts")) {
                this.this$0.mainHandler.post(new WebViewRunnable(this.val$context, this.val$self, MobFoxWebView.LOAD_AD_LISTENER) {
                    public void mobFoxRun() {
                        MobFoxWebView$7.this.val$self.loadAdListener.onAdResponse(MobFoxWebView$7.this.val$self, respObj);
                    }
                });
                return;
            }
            final Object error = respObj.get("error");
            if (error != null && (error instanceof JSONObject)) {
                if (((JSONObject) error).has("noAd")) {
                    this.this$0.mainHandler.post(new WebViewRunnable(this.val$context, this.val$self, MobFoxWebView.LOAD_AD_LISTENER) {
                        public void mobFoxRun() {
                            MobFoxWebView$7.this.val$self.loadAdListener.onNoAd(MobFoxWebView$7.this.val$self);
                        }
                    });
                    return;
                }
            }
            this.this$0.mainHandler.post(new WebViewRunnable(this.val$context, this.val$self, MobFoxWebView.LOAD_AD_LISTENER) {
                public void mobFoxRun() {
                    MobFoxWebView$7.this.val$self.loadAdListener.onError(MobFoxWebView$7.this.val$self, new Exception(error.toString()));
                }
            });
        } catch (final JSONException e) {
            this.this$0.mainHandler.post(new WebViewRunnable(this.val$context, this.val$self, MobFoxWebView.LOAD_AD_LISTENER) {
                public void mobFoxRun() {
                    MobFoxWebView$7.this.val$self.loadAdListener.onError(MobFoxWebView$7.this.val$self, e);
                }
            });
        } catch (final Throwable t) {
            this.this$0.mainHandler.post(new WebViewRunnable(this.val$context, this.val$self, MobFoxWebView.LOAD_AD_LISTENER) {
                public void mobFoxRun() {
                    MobFoxWebView$7.this.val$self.loadAdListener.onError(MobFoxWebView$7.this.val$self, new Exception(t.getMessage()));
                }
            });
        }
    }
}
