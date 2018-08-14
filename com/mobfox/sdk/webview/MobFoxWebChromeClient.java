package com.mobfox.sdk.webview;

import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import com.mobfox.sdk.constants.Constants;

public class MobFoxWebChromeClient extends WebChromeClient {
    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        Log.d(Constants.MOBFOX_CONSOLE, "CONSOLE>>> " + consoleMessage.message() + ", line: " + consoleMessage.lineNumber());
        return super.onConsoleMessage(consoleMessage);
    }

    public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
        result.cancel();
        return true;
    }

    public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
        result.cancel();
        return true;
    }

    public boolean onJsConfirm(WebView view, String url, String message, JsResult result) {
        result.cancel();
        return true;
    }
}
