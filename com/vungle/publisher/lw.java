package com.vungle.publisher;

import android.webkit.JsPromptResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import com.vungle.publisher.ji.a;
import com.vungle.publisher.log.Logger;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONObject;

@Singleton
/* compiled from: vungle */
public class lw extends WebChromeClient {
    @Inject
    qg f3094a;

    public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
        Logger.v(Logger.AD_TAG, "js prompt: " + message);
        boolean startsWith = message.startsWith("vungle:");
        if (startsWith) {
            String str = null;
            try {
                str = message.substring("vungle:".length());
                JSONObject jSONObject = new JSONObject(str);
                String string = jSONObject.getString("method");
                jSONObject.getString("params");
                m4353a(string);
            } catch (IndexOutOfBoundsException e) {
                Logger.w(Logger.AD_TAG, "no javascript method call");
            } catch (Throwable e2) {
                Logger.w(Logger.AD_TAG, "invalid json " + str, e2);
            } catch (Throwable e22) {
                Logger.e(Logger.AD_TAG, e22);
            }
            result.cancel();
        }
        return startsWith;
    }

    boolean m4353a(String str) {
        if ("close".equalsIgnoreCase(str)) {
            this.f3094a.m4568a(new ao());
            return true;
        } else if ("download".equalsIgnoreCase(str)) {
            this.f3094a.m4568a(new aa(a.j));
            return true;
        } else if ("privacy".equalsIgnoreCase(str)) {
            this.f3094a.m4568a(new aq());
            return true;
        } else {
            Logger.w(Logger.AD_TAG, "unknown javascript method: " + str);
            return false;
        }
    }
}
