package com.github.lzyzsd.jsbridge;

public interface WebViewJavascriptBridge {
    void send(String str);

    void send(String str, CallBackFunction callBackFunction);
}
