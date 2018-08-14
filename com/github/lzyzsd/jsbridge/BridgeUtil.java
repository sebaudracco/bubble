package com.github.lzyzsd.jsbridge;

import android.content.Context;
import android.util.Log;
import android.webkit.WebView;
import com.mobfox.sdk.constants.Constants;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class BridgeUtil {
    public static final String CALLBACK_ID_FORMAT = "JAVA_CB_%s";
    public static final String EMPTY_STR = "";
    public static final String JAVASCRIPT_STR = "javascript:";
    public static final String JS_FETCH_QUEUE_FROM_JAVA = "javascript:WebViewJavascriptBridge._fetchQueue();";
    public static final String JS_HANDLE_MESSAGE_FROM_JAVA = "javascript:WebViewJavascriptBridge._handleMessageFromNative('%s');";
    public static final String SPLIT_MARK = "/";
    public static final String UNDERLINE_STR = "_";
    public static final String YY_FETCH_QUEUE = "yy://return/_fetchQueue/";
    public static final String YY_OVERRIDE_SCHEMA = "yy://";
    public static final String YY_RETURN_DATA = "yy://return/";

    public static String parseFunctionName(String jsUrl) {
        return jsUrl.replace("javascript:WebViewJavascriptBridge.", "").replaceAll("\\(.*\\);", "");
    }

    public static String getDataFromReturnUrl(String url) {
        if (url.startsWith(YY_FETCH_QUEUE)) {
            return url.replace(YY_FETCH_QUEUE, "");
        }
        String[] functionAndData = url.replace(YY_RETURN_DATA, "").split(SPLIT_MARK);
        if (functionAndData.length < 2) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < functionAndData.length; i++) {
            sb.append(functionAndData[i]);
        }
        return sb.toString();
    }

    public static String getFunctionFromReturnUrl(String url) {
        String[] functionAndData = url.replace(YY_RETURN_DATA, "").split(SPLIT_MARK);
        if (functionAndData.length >= 1) {
            return functionAndData[0];
        }
        return null;
    }

    public static void webViewLoadJs(WebView view, String url) {
        view.loadUrl(JAVASCRIPT_STR + (("var newscript = document.createElement(\"script\");" + "newscript.src=\"" + url + "\";") + "document.scripts[0].parentNode.insertBefore(newscript,document.scripts[0]);"));
    }

    public static void webViewLoadLocalJs(WebView view, String path) {
        view.loadUrl(JAVASCRIPT_STR + assetFile2Str(view.getContext(), path));
    }

    public static String assetFile2Str(Context c, String urlStr) {
        InputStream in = null;
        try {
            in = c.getAssets().open(urlStr);
        } catch (IOException e) {
            Log.d(Constants.MOBFOX_WEBVIEW, "webViewLoadLocalJs not in assets, looking in resource loader");
        }
        if (in == null) {
            try {
                in = c.getClass().getClassLoader().getResourceAsStream(urlStr);
            } catch (Exception e2) {
                if (e2.getMessage() != null) {
                    Log.d(Constants.MOBFOX_WEBVIEW, e2.getMessage());
                }
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e3) {
                    }
                }
                return null;
            } catch (Throwable th) {
                if (in != null) {
                    try {
                        in.close();
                    } catch (IOException e4) {
                    }
                }
            }
        }
        if (in == null) {
            in = c.getClassLoader().getResourceAsStream(urlStr);
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        String line;
        do {
            line = bufferedReader.readLine();
            if (!(line == null || line.matches("^\\s*\\/\\/.*"))) {
                sb.append(line);
                continue;
            }
        } while (line != null);
        bufferedReader.close();
        in.close();
        String stringBuilder = sb.toString();
        if (in == null) {
            return stringBuilder;
        }
        try {
            in.close();
            return stringBuilder;
        } catch (IOException e5) {
            return stringBuilder;
        }
    }
}
