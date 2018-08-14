package com.integralads.avid.library.adcolony.session;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.integralads.avid.library.adcolony.AvidContext;
import com.integralads.avid.library.adcolony.AvidManager;
import com.integralads.avid.library.adcolony.registration.AvidAdSessionRegistry;
import com.integralads.avid.library.adcolony.session.internal.InternalAvidAdSession;
import com.integralads.avid.library.adcolony.session.internal.InternalAvidDisplayAdSession;
import com.integralads.avid.library.adcolony.session.internal.InternalAvidManagedDisplayAdSession;
import com.integralads.avid.library.adcolony.session.internal.InternalAvidManagedVideoAdSession;
import com.integralads.avid.library.adcolony.session.internal.InternalAvidVideoAdSession;

public class AvidAdSessionManager {
    public static String getVersion() {
        return AvidContext.getInstance().getAvidVersion();
    }

    public static String getReleaseDate() {
        return AvidContext.getInstance().getAvidReleaseDate();
    }

    public static AvidDisplayAdSession startAvidDisplayAdSession(Context context, ExternalAvidAdSessionContext avidAdSessionContext) {
        AvidManager.getInstance().init(context);
        AbstractAvidAdSession avidDisplayAdSession = new AvidDisplayAdSession();
        InternalAvidAdSession internalAvidDisplayAdSession = new InternalAvidDisplayAdSession(context, avidDisplayAdSession.getAvidAdSessionId(), avidAdSessionContext);
        internalAvidDisplayAdSession.onStart();
        AvidManager.getInstance().registerAvidAdSession(avidDisplayAdSession, internalAvidDisplayAdSession);
        return avidDisplayAdSession;
    }

    public static AvidVideoAdSession startAvidVideoAdSession(Context context, ExternalAvidAdSessionContext avidAdSessionContext) {
        AvidManager.getInstance().init(context);
        AbstractAvidAdSession avidVideoAdSession = new AvidVideoAdSession();
        InternalAvidAdSession internalAvidVideoAdSession = new InternalAvidVideoAdSession(context, avidVideoAdSession.getAvidAdSessionId(), avidAdSessionContext);
        internalAvidVideoAdSession.onStart();
        AvidManager.getInstance().registerAvidAdSession(avidVideoAdSession, internalAvidVideoAdSession);
        return avidVideoAdSession;
    }

    public static AvidManagedVideoAdSession startAvidManagedVideoAdSession(Context context, ExternalAvidAdSessionContext avidAdSessionContext) {
        AvidManager.getInstance().init(context);
        AbstractAvidAdSession avidManagedVideoAdSession = new AvidManagedVideoAdSession();
        InternalAvidAdSession internalAvidManagedVideoAdSession = new InternalAvidManagedVideoAdSession(context, avidManagedVideoAdSession.getAvidAdSessionId(), avidAdSessionContext);
        internalAvidManagedVideoAdSession.onStart();
        AvidManager.getInstance().registerAvidAdSession(avidManagedVideoAdSession, internalAvidManagedVideoAdSession);
        return avidManagedVideoAdSession;
    }

    public static AvidManagedDisplayAdSession startAvidManagedDisplayAdSession(Context context, ExternalAvidAdSessionContext avidAdSessionContext) {
        AvidManager.getInstance().init(context);
        AbstractAvidAdSession avidManagedDisplayAdSession = new AvidManagedDisplayAdSession();
        InternalAvidAdSession internalAvidManagedDisplayAdSession = new InternalAvidManagedDisplayAdSession(context, avidManagedDisplayAdSession.getAvidAdSessionId(), avidAdSessionContext);
        internalAvidManagedDisplayAdSession.onStart();
        AvidManager.getInstance().registerAvidAdSession(avidManagedDisplayAdSession, internalAvidManagedDisplayAdSession);
        return avidManagedDisplayAdSession;
    }

    public static <T extends AbstractAvidAdSession> T findAvidAdSessionById(String avidAdSessionId) {
        return AvidManager.getInstance().findAvidAdSessionById(avidAdSessionId);
    }

    public static WebView webViewForView(View view) {
        WebView a = m11138a(view);
        if (a != null) {
            return a;
        }
        if (!(view instanceof ViewGroup)) {
            return null;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            a = webViewForView(viewGroup.getChildAt(i));
            if (a != null) {
                return a;
            }
        }
        return a;
    }

    public static WebView webViewForSessionId(String sessionId) {
        InternalAvidAdSession findInternalAvidAdSessionById = AvidAdSessionRegistry.getInstance().findInternalAvidAdSessionById(sessionId);
        return findInternalAvidAdSessionById != null ? findInternalAvidAdSessionById.getWebView() : null;
    }

    private static WebView m11138a(View view) {
        InternalAvidAdSession findInternalAvidAdSessionByView = AvidAdSessionRegistry.getInstance().findInternalAvidAdSessionByView(view);
        return findInternalAvidAdSessionByView != null ? findInternalAvidAdSessionByView.getWebView() : null;
    }
}
