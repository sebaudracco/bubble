package com.github.lzyzsd.jsbridge;

public class DefaultHandler implements BridgeHandler {
    String TAG = "DefaultHandler";

    public void handler(String data, CallBackFunction function) {
        if (function != null) {
            function.onCallBack("DefaultHandler response data");
        }
    }
}
