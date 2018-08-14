package com.integralads.avid.library.inmobi;

import android.text.TextUtils;
import android.webkit.WebView;

/* compiled from: AvidBridge */
public class C3287a {
    private static final String f8422a = "(function () {\n  var avid = window.avid = {},\n    avidbridge = window.avidbridge = {};\n  avid.eventListeners = [];\n  avidbridge.avidAdSessionContext = {};\n  avid.getAvidAdSessionContext = function () {\n    var avidAdSessionContext = avidbridge.avidAdSessionContext;\n    avidAdSessionContext.avidApiLevel = '2';\n    return avidAdSessionContext;\n  };\n  avid.getHtmlVideoAvidAdSessionListener = function () {\n    throw 'The `HtmlVideoAvidAdSessionListener` is not available from the AVID JS stub. Please ensure you listen to the `loaded` event before calling `getHtmlVideoAvidAdSessionListener`.';\n  };\n  avid.addEventListener = function (type, functionToExecute) {\n    avid.eventListeners.push({\n      'type': type,\n      'functionToExecute': functionToExecute\n    });\n  };\n  avidbridge.setAvidAdSessionContext = function (avidAdSessionContext) {\n    avidbridge.avidAdSessionContext = avidAdSessionContext;\n  };\n})();".replaceAll("(?m)^\\s+", "").replaceAll("(?m)^//.*(?=\\n)", "");
    private static String f8423b;

    public static void m11186a(String str) {
        if (!TextUtils.isEmpty(str)) {
            f8423b = str;
        }
    }

    public static boolean m11187a() {
        return !TextUtils.isEmpty(f8423b);
    }

    public static boolean m11188a(WebView webView) {
        if (C3287a.m11187a()) {
            C3287a.m11190b(webView, f8423b);
            return true;
        }
        C3287a.m11190b(webView, f8422a);
        return false;
    }

    public static boolean m11189a(WebView webView, String str) {
        return C3287a.m11190b(webView, "if(window.avidbridge!==undefined){avidbridge." + str + "}");
    }

    public static boolean m11190b(WebView webView, String str) {
        if (webView == null || TextUtils.isEmpty(str)) {
            return false;
        }
        webView.loadUrl("javascript: " + str);
        return true;
    }

    public static void m11191c(WebView webView, String str) {
        if (str != null) {
            C3287a.m11190b(webView, "var script=document.createElement('script');script.setAttribute(\"type\",\"text/javascript\");script.setAttribute(\"src\",\"%SCRIPT_SRC%\");document.body.appendChild(script);".replace("%SCRIPT_SRC%", str));
        }
    }
}
