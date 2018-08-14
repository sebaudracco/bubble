package com.integralads.avid.library.adcolony.registration;

import android.view.View;
import com.integralads.avid.library.adcolony.session.AbstractAvidAdSession;
import com.integralads.avid.library.adcolony.session.internal.InternalAvidAdSession;
import com.integralads.avid.library.adcolony.session.internal.InternalAvidAdSessionListener;
import java.util.Collection;
import java.util.HashMap;

public class AvidAdSessionRegistry implements InternalAvidAdSessionListener {
    private static AvidAdSessionRegistry f8336a = new AvidAdSessionRegistry();
    private final HashMap<String, InternalAvidAdSession> f8337b = new HashMap();
    private final HashMap<String, AbstractAvidAdSession> f8338c = new HashMap();
    private AvidAdSessionRegistryListener f8339d;
    private int f8340e = 0;

    public static AvidAdSessionRegistry getInstance() {
        return f8336a;
    }

    public AvidAdSessionRegistryListener getListener() {
        return this.f8339d;
    }

    public void setListener(AvidAdSessionRegistryListener listener) {
        this.f8339d = listener;
    }

    public void registerAvidAdSession(AbstractAvidAdSession avidAdSession, InternalAvidAdSession internalAvidAdSession) {
        this.f8338c.put(avidAdSession.getAvidAdSessionId(), avidAdSession);
        this.f8337b.put(avidAdSession.getAvidAdSessionId(), internalAvidAdSession);
        internalAvidAdSession.setListener(this);
        if (this.f8338c.size() == 1 && this.f8339d != null) {
            this.f8339d.registryHasSessionsChanged(this);
        }
    }

    public Collection<InternalAvidAdSession> getInternalAvidAdSessions() {
        return this.f8337b.values();
    }

    public Collection<AbstractAvidAdSession> getAvidAdSessions() {
        return this.f8338c.values();
    }

    public boolean isEmpty() {
        return this.f8338c.isEmpty();
    }

    public boolean hasActiveSessions() {
        return this.f8340e > 0;
    }

    public AbstractAvidAdSession findAvidAdSessionById(String avidAdSessionId) {
        return (AbstractAvidAdSession) this.f8338c.get(avidAdSessionId);
    }

    public InternalAvidAdSession findInternalAvidAdSessionById(String avidAdSessionId) {
        return (InternalAvidAdSession) this.f8337b.get(avidAdSessionId);
    }

    public InternalAvidAdSession findInternalAvidAdSessionByView(View view) {
        for (InternalAvidAdSession internalAvidAdSession : this.f8337b.values()) {
            if (internalAvidAdSession.doesManageView(view)) {
                return internalAvidAdSession;
            }
        }
        return null;
    }

    public void sessionDidEnd(InternalAvidAdSession session) {
        this.f8338c.remove(session.getAvidAdSessionId());
        this.f8337b.remove(session.getAvidAdSessionId());
        session.setListener(null);
        if (this.f8338c.size() == 0 && this.f8339d != null) {
            this.f8339d.registryHasSessionsChanged(this);
        }
    }

    public void sessionHasBecomeActive(InternalAvidAdSession session) {
        this.f8340e++;
        if (this.f8340e == 1 && this.f8339d != null) {
            this.f8339d.registryHasActiveSessionsChanged(this);
        }
    }

    public void sessionHasResignedActive(InternalAvidAdSession session) {
        this.f8340e--;
        if (this.f8340e == 0 && this.f8339d != null) {
            this.f8339d.registryHasActiveSessionsChanged(this);
        }
    }
}
