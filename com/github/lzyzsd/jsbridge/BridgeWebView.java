package com.github.lzyzsd.jsbridge;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.webkit.WebView;
import com.mobfox.sdk.constants.Constants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressLint({"SetJavaScriptEnabled"})
public class BridgeWebView extends WebView implements WebViewJavascriptBridge {
    public static final String toLoadJs = "WebViewJavascriptBridge.js";
    private final String TAG = "BridgeWebView";
    BridgeHandler defaultHandler = new DefaultHandler();
    Map<String, BridgeHandler> messageHandlers = new HashMap();
    Map<String, CallBackFunction> responseCallbacks = new HashMap();
    private List<Message> startupMessage = new ArrayList();
    private long uniqueId = 0;

    class C26891 implements CallBackFunction {

        class C26882 implements CallBackFunction {
            C26882() {
            }

            public void onCallBack(String data) {
            }
        }

        C26891() {
        }

        public void onCallBack(String data) {
            try {
                List<Message> list = Message.toArrayList(data);
                if (list != null && list.size() != 0) {
                    for (int i = 0; i < list.size(); i++) {
                        Message m = (Message) list.get(i);
                        String responseId = m.getResponseId();
                        if (TextUtils.isEmpty(responseId)) {
                            CallBackFunction responseFunction;
                            BridgeHandler handler;
                            final String callbackId = m.getCallbackId();
                            if (TextUtils.isEmpty(callbackId)) {
                                responseFunction = new C26882();
                            } else {
                                responseFunction = new CallBackFunction() {
                                    public void onCallBack(String data) {
                                        Message responseMsg = new Message();
                                        responseMsg.setResponseId(callbackId);
                                        responseMsg.setResponseData(data);
                                        BridgeWebView.this.queueMessage(responseMsg);
                                    }
                                };
                            }
                            if (TextUtils.isEmpty(m.getHandlerName())) {
                                handler = BridgeWebView.this.defaultHandler;
                            } else {
                                handler = (BridgeHandler) BridgeWebView.this.messageHandlers.get(m.getHandlerName());
                            }
                            if (handler != null) {
                                handler.handler(m.getData(), responseFunction);
                            }
                        } else {
                            ((CallBackFunction) BridgeWebView.this.responseCallbacks.get(responseId)).onCallBack(m.getResponseData());
                            BridgeWebView.this.responseCallbacks.remove(responseId);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List<Message> getStartupMessage() {
        return this.startupMessage;
    }

    public void setStartupMessage(List<Message> startupMessage) {
        this.startupMessage = startupMessage;
    }

    public BridgeWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BridgeWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public BridgeWebView(Context context) {
        super(context);
        init();
    }

    public void setDefaultHandler(BridgeHandler handler) {
        this.defaultHandler = handler;
    }

    private void init() {
        setVerticalScrollBarEnabled(false);
        setHorizontalScrollBarEnabled(false);
        getSettings().setJavaScriptEnabled(true);
        if (VERSION.SDK_INT >= 19) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        setWebViewClient(generateBridgeWebViewClient());
    }

    protected BridgeWebViewClient generateBridgeWebViewClient() {
        return new BridgeWebViewClient(this);
    }

    void handlerReturnData(String url) {
        String functionName = BridgeUtil.getFunctionFromReturnUrl(url);
        CallBackFunction f = (CallBackFunction) this.responseCallbacks.get(functionName);
        String data = BridgeUtil.getDataFromReturnUrl(url);
        if (f != null) {
            f.onCallBack(data);
            this.responseCallbacks.remove(functionName);
        }
    }

    public void send(String data) {
        send(data, null);
    }

    public void send(String data, CallBackFunction responseCallback) {
        doSend(null, data, responseCallback);
    }

    private void doSend(String handlerName, String data, CallBackFunction responseCallback) {
        try {
            Message m = new Message();
            if (!TextUtils.isEmpty(data)) {
                m.setData(data);
            }
            if (responseCallback != null) {
                String str = BridgeUtil.CALLBACK_ID_FORMAT;
                Object[] objArr = new Object[1];
                StringBuilder stringBuilder = new StringBuilder();
                long j = this.uniqueId + 1;
                this.uniqueId = j;
                objArr[0] = stringBuilder.append(j).append(BridgeUtil.UNDERLINE_STR).append(SystemClock.currentThreadTimeMillis()).toString();
                String callbackStr = String.format(str, objArr);
                this.responseCallbacks.put(callbackStr, responseCallback);
                m.setCallbackId(callbackStr);
            }
            if (!TextUtils.isEmpty(handlerName)) {
                m.setHandlerName(handlerName);
            }
            queueMessage(m);
        } catch (Throwable t) {
            Log.d(Constants.MOBFOX_WEBVIEW, "error on bridge send message", t);
        }
    }

    private void queueMessage(Message m) {
        if (this.startupMessage != null) {
            this.startupMessage.add(m);
        } else {
            dispatchMessage(m);
        }
    }

    void dispatchMessage(Message m) {
        String messageJson = m.toJson().replaceAll("(\\\\)([^utrn])", "\\\\\\\\$1$2").replaceAll("(?<=[^\\\\])(\")", "\\\\\"");
        String javascriptCommand = String.format(BridgeUtil.JS_HANDLE_MESSAGE_FROM_JAVA, new Object[]{messageJson});
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            loadUrl(javascriptCommand);
        }
    }

    void flushMessageQueue() {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            loadUrl(BridgeUtil.JS_FETCH_QUEUE_FROM_JAVA, new C26891());
        }
    }

    public void loadUrl(String jsUrl, CallBackFunction returnCallback) {
        loadUrl(jsUrl);
        this.responseCallbacks.put(BridgeUtil.parseFunctionName(jsUrl), returnCallback);
    }

    public void registerHandler(String handlerName, BridgeHandler handler) {
        if (handler != null) {
            this.messageHandlers.put(handlerName, handler);
        }
    }

    public void callHandler(String handlerName, String data, CallBackFunction callBack) {
        doSend(handlerName, data, callBack);
    }

    public Map<String, BridgeHandler> getMessageHandlers() {
        return this.messageHandlers;
    }
}
