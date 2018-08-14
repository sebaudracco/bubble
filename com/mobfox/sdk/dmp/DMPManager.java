package com.mobfox.sdk.dmp;

import android.content.Context;
import com.mobfox.sdk.networking.AsyncCallback;
import com.mobfox.sdk.networking.MobFoxRequest;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public class DMPManager {
    static DMP dmpInst = DMP.getInstance();
    public static String dmpURL = "https://dmp.starbolt.io/logger.json";

    public interface DMPCallback {
        void onPostCompleted();

        void onPostError();
    }

    public static void updateDMP(Context context, String IPAddress, String ua) {
        dmpInst.update(context, IPAddress, ua);
    }

    public static void postDMP(Context context, final DMPCallback cb) {
        JSONObject u = dmpInst.getPost(context);
        if (u == null) {
            cb.onPostCompleted();
            return;
        }
        MobFoxRequest req = new MobFoxRequest(context, dmpURL);
        req.setTestMode(true);
        req.setData(u);
        req.post(new AsyncCallback() {
            public void onComplete(int code, Object response, Map<String, List<String>> map) {
                if (cb != null) {
                    cb.onPostCompleted();
                }
            }

            public void onError(Exception e) {
                if (cb != null) {
                    cb.onPostError();
                }
            }
        });
    }
}
