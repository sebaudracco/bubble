package com.mobfox.sdk.customevents;

import com.bgjd.ici.p030e.C1485h.C1484a;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CustomEventData {
    public String className;
    public String networkId;
    public String pixel;

    public static CustomEventData parseJSON(JSONObject o) {
        CustomEventData ce = new CustomEventData();
        try {
            ce.pixel = o.getString("pixel");
            ce.className = o.getString(C1484a.f2398e);
            ce.networkId = o.getString(MediationRewardedVideoAdAdapter.CUSTOM_EVENT_SERVER_PARAMETER_FIELD);
            return ce;
        } catch (JSONException e) {
            return null;
        }
    }

    public static List<CustomEventData> parseJSONArray(JSONArray arr) {
        List<CustomEventData> list = new ArrayList();
        for (int i = 0; i < arr.length(); i++) {
            try {
                CustomEventData ce = parseJSON(arr.getJSONObject(i));
                if (ce != null) {
                    list.add(ce);
                }
            } catch (JSONException e) {
            }
        }
        return list;
    }
}
