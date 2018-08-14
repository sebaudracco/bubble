package com.integralads.avid.library.adcolony.utils;

import org.json.JSONObject;

public class AvidCommand {
    public static String setNativeViewState(String viewState) {
        return callAvidbridge("setNativeViewState(" + viewState + ")");
    }

    public static String setAppState(String appState) {
        return callAvidbridge("setAppState(" + JSONObject.quote(appState) + ")");
    }

    public static String publishReadyEventForDeferredAdSession() {
        return callAvidbridge("publishReadyEventForDeferredAdSession()");
    }

    public static String publishVideoEvent(String event) {
        return callAvidbridge("publishVideoEvent(" + JSONObject.quote(event) + ")");
    }

    public static String publishVideoEvent(String event, String data) {
        return callAvidbridge("publishVideoEvent(" + JSONObject.quote(event) + "," + data + ")");
    }

    public static String setAvidAdSessionContext(String avidAdSessionContext) {
        return callAvidbridge("setAvidAdSessionContext(" + avidAdSessionContext + ")");
    }

    public static String callAvidbridge(String command) {
        return "javascript: if(window.avidbridge!==undefined){avidbridge." + command + "}";
    }

    public static String formatJavaScript(String javaScript) {
        return "javascript: " + javaScript;
    }
}
