package com.github.lzyzsd.jsbridge;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Message {
    private static final String CALLBACK_ID_STR = "callbackId";
    private static final String DATA_STR = "data";
    private static final String HANDLER_NAME_STR = "handlerName";
    private static final String RESPONSE_DATA_STR = "responseData";
    private static final String RESPONSE_ID_STR = "responseId";
    private String callbackId;
    private String data;
    private String handlerName;
    private String responseData;
    private String responseId;

    public String getResponseId() {
        return this.responseId;
    }

    public void setResponseId(String responseId) {
        this.responseId = responseId;
    }

    public String getResponseData() {
        return this.responseData;
    }

    public void setResponseData(String responseData) {
        this.responseData = responseData;
    }

    public String getCallbackId() {
        return this.callbackId;
    }

    public void setCallbackId(String callbackId) {
        this.callbackId = callbackId;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHandlerName() {
        return this.handlerName;
    }

    public void setHandlerName(String handlerName) {
        this.handlerName = handlerName;
    }

    public String toJson() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put(CALLBACK_ID_STR, getCallbackId());
            jsonObject.put("data", getData());
            jsonObject.put(HANDLER_NAME_STR, getHandlerName());
            jsonObject.put(RESPONSE_DATA_STR, getResponseData());
            jsonObject.put(RESPONSE_ID_STR, getResponseId());
            return jsonObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Message toObject(String jsonStr) {
        String str = null;
        Message m = new Message();
        try {
            String string;
            JSONObject jsonObject = new JSONObject(jsonStr);
            if (jsonObject.has(HANDLER_NAME_STR)) {
                string = jsonObject.getString(HANDLER_NAME_STR);
            } else {
                string = null;
            }
            m.setHandlerName(string);
            if (jsonObject.has(CALLBACK_ID_STR)) {
                string = jsonObject.getString(CALLBACK_ID_STR);
            } else {
                string = null;
            }
            m.setCallbackId(string);
            if (jsonObject.has(RESPONSE_DATA_STR)) {
                string = jsonObject.getString(RESPONSE_DATA_STR);
            } else {
                string = null;
            }
            m.setResponseData(string);
            if (jsonObject.has(RESPONSE_ID_STR)) {
                string = jsonObject.getString(RESPONSE_ID_STR);
            } else {
                string = null;
            }
            m.setResponseId(string);
            if (jsonObject.has("data")) {
                str = jsonObject.getString("data");
            }
            m.setData(str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return m;
    }

    public static List<Message> toArrayList(String jsonStr) {
        List<Message> list = new ArrayList();
        try {
            JSONArray jsonArray = new JSONArray(jsonStr);
            for (int i = 0; i < jsonArray.length(); i++) {
                String string;
                Message m = new Message();
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if (jsonObject.has(HANDLER_NAME_STR)) {
                    string = jsonObject.getString(HANDLER_NAME_STR);
                } else {
                    string = null;
                }
                m.setHandlerName(string);
                if (jsonObject.has(CALLBACK_ID_STR)) {
                    string = jsonObject.getString(CALLBACK_ID_STR);
                } else {
                    string = null;
                }
                m.setCallbackId(string);
                if (jsonObject.has(RESPONSE_DATA_STR)) {
                    string = jsonObject.getString(RESPONSE_DATA_STR);
                } else {
                    string = null;
                }
                m.setResponseData(string);
                if (jsonObject.has(RESPONSE_ID_STR)) {
                    string = jsonObject.getString(RESPONSE_ID_STR);
                } else {
                    string = null;
                }
                m.setResponseId(string);
                if (jsonObject.has("data")) {
                    string = jsonObject.getString("data");
                } else {
                    string = null;
                }
                m.setData(string);
                list.add(m);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }
}
