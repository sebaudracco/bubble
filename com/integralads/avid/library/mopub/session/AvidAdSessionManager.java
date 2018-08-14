package com.integralads.avid.library.mopub.session;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.integralads.avid.library.mopub.AvidContext;
import com.integralads.avid.library.mopub.AvidManager;
import com.integralads.avid.library.mopub.registration.AvidAdSessionRegistry;
import com.integralads.avid.library.mopub.session.internal.InternalAvidAdSession;
import com.integralads.avid.library.mopub.session.internal.InternalAvidDisplayAdSession;
import com.integralads.avid.library.mopub.session.internal.InternalAvidManagedDisplayAdSession;
import com.integralads.avid.library.mopub.session.internal.InternalAvidManagedVideoAdSession;
import com.integralads.avid.library.mopub.session.internal.InternalAvidVideoAdSession;

public class AvidAdSessionManager {
    public static String getVersion() {
        return AvidContext.getInstance().getAvidVersion();
    }

    public static String getReleaseDate() {
        return AvidContext.getInstance().getAvidReleaseDate();
    }

    public static AvidDisplayAdSession startAvidDisplayAdSession(Context context, ExternalAvidAdSessionContext avidAdSessionContext) {
        AvidManager.getInstance().init(context);
        AvidDisplayAdSession avidAdSession = new AvidDisplayAdSession();
        InternalAvidDisplayAdSession internalAvidAdSession = new InternalAvidDisplayAdSession(context, avidAdSession.getAvidAdSessionId(), avidAdSessionContext);
        internalAvidAdSession.onStart();
        AvidManager.getInstance().registerAvidAdSession(avidAdSession, internalAvidAdSession);
        return avidAdSession;
    }

    public static AvidVideoAdSession startAvidVideoAdSession(Context context, ExternalAvidAdSessionContext avidAdSessionContext) {
        AvidManager.getInstance().init(context);
        AvidVideoAdSession avidAdSession = new AvidVideoAdSession();
        InternalAvidVideoAdSession internalAvidAdSession = new InternalAvidVideoAdSession(context, avidAdSession.getAvidAdSessionId(), avidAdSessionContext);
        internalAvidAdSession.onStart();
        AvidManager.getInstance().registerAvidAdSession(avidAdSession, internalAvidAdSession);
        return avidAdSession;
    }

    public static AvidManagedVideoAdSession startAvidManagedVideoAdSession(Context context, ExternalAvidAdSessionContext avidAdSessionContext) {
        AvidManager.getInstance().init(context);
        AvidManagedVideoAdSession avidAdSession = new AvidManagedVideoAdSession();
        InternalAvidManagedVideoAdSession internalAvidAdSession = new InternalAvidManagedVideoAdSession(context, avidAdSession.getAvidAdSessionId(), avidAdSessionContext);
        internalAvidAdSession.onStart();
        AvidManager.getInstance().registerAvidAdSession(avidAdSession, internalAvidAdSession);
        return avidAdSession;
    }

    public static AvidManagedDisplayAdSession startAvidManagedDisplayAdSession(Context context, ExternalAvidAdSessionContext avidAdSessionContext) {
        AvidManager.getInstance().init(context);
        AvidManagedDisplayAdSession avidAdSession = new AvidManagedDisplayAdSession();
        InternalAvidManagedDisplayAdSession internalAvidAdSession = new InternalAvidManagedDisplayAdSession(context, avidAdSession.getAvidAdSessionId(), avidAdSessionContext);
        internalAvidAdSession.onStart();
        AvidManager.getInstance().registerAvidAdSession(avidAdSession, internalAvidAdSession);
        return avidAdSession;
    }

    public static <T extends AbstractAvidAdSession> T findAvidAdSessionById(String avidAdSessionId) {
        return AvidManager.getInstance().findAvidAdSessionById(avidAdSessionId);
    }

    public static WebView webViewForView(View view) {
        WebView webView = findWebView(view);
        if (webView != null) {
            return webView;
        }
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            webView = webViewForView(viewGroup.getChildAt(i));
            if (webView != null) {
                break;
            }
        }
        return webView;
    }

    public static WebView webViewForSessionId(String sessionId) {
        InternalAvidAdSession session = AvidAdSessionRegistry.getInstance().findInternalAvidAdSessionById(sessionId);
        return session != null ? session.getWebView() : null;
    }

    private static WebView findWebView(View view) {
        InternalAvidAdSession avidAdSession = AvidAdSessionRegistry.getInstance().findInternalAvidAdSessionByView(view);
        return avidAdSession != null ? avidAdSession.getWebView() : null;
    }
}
