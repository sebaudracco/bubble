package com.mopub.nativeads;

import android.support.annotation.NonNull;
import com.mopub.common.VisibleForTesting;
import com.mopub.nativeads.MoPubNativeAdPositioning.MoPubClientPositioning;
import com.mopub.network.MoPubNetworkError;
import com.mopub.network.MoPubNetworkError.Reason;
import com.mopub.volley.NetworkResponse;
import com.mopub.volley.Response;
import com.mopub.volley.Response.ErrorListener;
import com.mopub.volley.Response.Listener;
import com.mopub.volley.VolleyError;
import com.mopub.volley.toolbox.HttpHeaderParser;
import com.mopub.volley.toolbox.JsonRequest;
import java.io.UnsupportedEncodingException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PositioningRequest extends JsonRequest<MoPubClientPositioning> {
    private static final String FIXED_KEY = "fixed";
    private static final String INTERVAL_KEY = "interval";
    private static final int MAX_VALUE = 65536;
    private static final String POSITION_KEY = "position";
    private static final String REPEATING_KEY = "repeating";
    private static final String SECTION_KEY = "section";

    public PositioningRequest(String url, Listener<MoPubClientPositioning> listener, ErrorListener errorListener) {
        super(0, url, null, listener, errorListener);
    }

    protected void deliverResponse(MoPubClientPositioning response) {
        super.deliverResponse(response);
    }

    protected Response<MoPubClientPositioning> parseNetworkResponse(NetworkResponse response) {
        if (response.statusCode != 200) {
            return Response.error(new VolleyError(response));
        }
        if (response.data.length == 0) {
            return Response.error(new VolleyError("Empty positioning response", new JSONException("Empty response")));
        }
        try {
            return Response.success(parseJson(new String(response.data, HttpHeaderParser.parseCharset(response.headers))), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new VolleyError("Couldn't parse JSON from Charset", e));
        } catch (JSONException e2) {
            return Response.error(new VolleyError("JSON Parsing Error", e2));
        } catch (MoPubNetworkError e3) {
            return Response.error(e3);
        }
    }

    @NonNull
    @VisibleForTesting
    MoPubClientPositioning parseJson(@NonNull String jsonString) throws JSONException, MoPubNetworkError {
        JSONObject jsonObject = new JSONObject(jsonString);
        String error = jsonObject.optString("error", null);
        if (error == null) {
            JSONArray fixed = jsonObject.optJSONArray(FIXED_KEY);
            JSONObject repeating = jsonObject.optJSONObject(REPEATING_KEY);
            if (fixed == null && repeating == null) {
                throw new JSONException("Must contain fixed or repeating positions");
            }
            MoPubClientPositioning positioning = new MoPubClientPositioning();
            if (fixed != null) {
                parseFixedJson(fixed, positioning);
            }
            if (repeating != null) {
                parseRepeatingJson(repeating, positioning);
            }
            return positioning;
        } else if (error.equalsIgnoreCase("WARMING_UP")) {
            throw new MoPubNetworkError(Reason.WARMING_UP);
        } else {
            throw new JSONException(error);
        }
    }

    private void parseFixedJson(@NonNull JSONArray fixed, @NonNull MoPubClientPositioning positioning) throws JSONException {
        for (int i = 0; i < fixed.length(); i++) {
            JSONObject positionObject = fixed.getJSONObject(i);
            int section = positionObject.optInt(SECTION_KEY, 0);
            if (section < 0) {
                throw new JSONException("Invalid section " + section + " in JSON response");
            }
            if (section <= 0) {
                int position = positionObject.getInt(POSITION_KEY);
                if (position < 0 || position > 65536) {
                    throw new JSONException("Invalid position " + position + " in JSON response");
                }
                positioning.addFixedPosition(position);
            }
        }
    }

    private void parseRepeatingJson(@NonNull JSONObject repeatingObject, @NonNull MoPubClientPositioning positioning) throws JSONException {
        int interval = repeatingObject.getInt("interval");
        if (interval < 2 || interval > 65536) {
            throw new JSONException("Invalid interval " + interval + " in JSON response");
        }
        positioning.enableRepeatingPositions(interval);
    }
}
