package com.integralads.avid.library.mopub.registration;

import android.view.View;
import com.integralads.avid.library.mopub.session.AbstractAvidAdSession;
import com.integralads.avid.library.mopub.session.internal.InternalAvidAdSession;
import com.integralads.avid.library.mopub.session.internal.InternalAvidAdSessionListener;
import java.util.Collection;
import java.util.HashMap;

public class AvidAdSessionRegistry implements InternalAvidAdSessionListener {
    private static AvidAdSessionRegistry instance = new AvidAdSessionRegistry();
    private int activeSessionCount = 0;
    private final HashMap<String, AbstractAvidAdSession> avidAdSessions = new HashMap();
    private final HashMap<String, InternalAvidAdSession> internalAvidAdSessions = new HashMap();
    private AvidAdSessionRegistryListener listener;

    public static AvidAdSessionRegistry getInstance() {
        return instance;
    }

    public AvidAdSessionRegistryListener getListener() {
        return this.listener;
    }

    public void setListener(AvidAdSessionRegistryListener listener) {
        this.listener = listener;
    }

    public void registerAvidAdSession(AbstractAvidAdSession avidAdSession, InternalAvidAdSession internalAvidAdSession) {
        this.avidAdSessions.put(avidAdSession.getAvidAdSessionId(), avidAdSession);
        this.internalAvidAdSessions.put(avidAdSession.getAvidAdSessionId(), internalAvidAdSession);
        internalAvidAdSession.setListener(this);
        if (this.avidAdSessions.size() == 1 && this.listener != null) {
            this.listener.registryHasSessionsChanged(this);
        }
    }

    public Collection<InternalAvidAdSession> getInternalAvidAdSessions() {
        return this.internalAvidAdSessions.values();
    }

    public Collection<AbstractAvidAdSession> getAvidAdSessions() {
        return this.avidAdSessions.values();
    }

    public boolean isEmpty() {
        return this.avidAdSessions.isEmpty();
    }

    public boolean hasActiveSessions() {
        return this.activeSessionCount > 0;
    }

    public AbstractAvidAdSession findAvidAdSessionById(String avidAdSessionId) {
        return (AbstractAvidAdSession) this.avidAdSessions.get(avidAdSessionId);
    }

    public InternalAvidAdSession findInternalAvidAdSessionById(String avidAdSessionId) {
        return (InternalAvidAdSession) this.internalAvidAdSessions.get(avidAdSessionId);
    }

    public InternalAvidAdSession findInternalAvidAdSessionByView(View view) {
        for (InternalAvidAdSession internalAvidAdSession : this.internalAvidAdSessions.values()) {
            if (internalAvidAdSession.doesManageView(view)) {
                return internalAvidAdSession;
            }
        }
        return null;
    }

    public void sessionDidEnd(InternalAvidAdSession session) {
        this.avidAdSessions.remove(session.getAvidAdSessionId());
        this.internalAvidAdSessions.remove(session.getAvidAdSessionId());
        session.setListener(null);
        if (this.avidAdSessions.size() == 0 && this.listener != null) {
            this.listener.registryHasSessionsChanged(this);
        }
    }

    public void sessionHasBecomeActive(InternalAvidAdSession session) {
        this.activeSessionCount++;
        if (this.activeSessionCount == 1 && this.listener != null) {
            this.listener.registryHasActiveSessionsChanged(this);
        }
    }

    public void sessionHasResignedActive(InternalAvidAdSession session) {
        this.activeSessionCount--;
        if (this.activeSessionCount == 0 && this.listener != null) {
            this.listener.registryHasActiveSessionsChanged(this);
        }
    }
}
