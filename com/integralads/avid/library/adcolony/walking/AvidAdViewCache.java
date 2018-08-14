package com.integralads.avid.library.adcolony.walking;

import android.support.annotation.VisibleForTesting;
import android.view.View;
import android.view.ViewParent;
import com.integralads.avid.library.adcolony.registration.AvidAdSessionRegistry;
import com.integralads.avid.library.adcolony.session.internal.InternalAvidAdSession;
import com.integralads.avid.library.adcolony.utils.AvidViewUtil;
import com.integralads.avid.library.adcolony.weakreference.AvidView;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

public class AvidAdViewCache {
    private final AvidAdSessionRegistry f8402a;
    private final HashMap<View, String> f8403b = new HashMap();
    private final HashMap<View, ArrayList<String>> f8404c = new HashMap();
    private final HashSet<View> f8405d = new HashSet();
    private final HashSet<String> f8406e = new HashSet();
    private final HashSet<String> f8407f = new HashSet();
    private boolean f8408g;

    public AvidAdViewCache(AvidAdSessionRegistry adSessionRegistry) {
        this.f8402a = adSessionRegistry;
    }

    public HashSet<String> getVisibleSessionIds() {
        return this.f8406e;
    }

    public HashSet<String> getHiddenSessionIds() {
        return this.f8407f;
    }

    public void prepare() {
        for (InternalAvidAdSession internalAvidAdSession : this.f8402a.getInternalAvidAdSessions()) {
            View view = internalAvidAdSession.getView();
            if (internalAvidAdSession.isActive() && view != null) {
                if (m11169a(view)) {
                    this.f8406e.add(internalAvidAdSession.getAvidAdSessionId());
                    this.f8403b.put(view, internalAvidAdSession.getAvidAdSessionId());
                    m11168a(internalAvidAdSession);
                } else {
                    this.f8407f.add(internalAvidAdSession.getAvidAdSessionId());
                }
            }
        }
    }

    private boolean m11169a(View view) {
        if (!view.hasWindowFocus()) {
            return false;
        }
        Collection hashSet = new HashSet();
        while (view != null) {
            if (!AvidViewUtil.isViewVisible(view)) {
                return false;
            }
            hashSet.add(view);
            ViewParent parent = view.getParent();
            view = parent instanceof View ? (View) parent : null;
        }
        this.f8405d.addAll(hashSet);
        return true;
    }

    private void m11168a(InternalAvidAdSession internalAvidAdSession) {
        Iterator it = internalAvidAdSession.getObstructionsWhiteList().getWhiteList().iterator();
        while (it.hasNext()) {
            AvidView avidView = (AvidView) it.next();
            if (!avidView.isEmpty()) {
                m11167a((View) avidView.get(), internalAvidAdSession);
            }
        }
    }

    private void m11167a(View view, InternalAvidAdSession internalAvidAdSession) {
        ArrayList arrayList = (ArrayList) this.f8404c.get(view);
        if (arrayList == null) {
            arrayList = new ArrayList();
            this.f8404c.put(view, arrayList);
        }
        arrayList.add(internalAvidAdSession.getAvidAdSessionId());
    }

    public void cleanup() {
        this.f8403b.clear();
        this.f8404c.clear();
        this.f8405d.clear();
        this.f8406e.clear();
        this.f8407f.clear();
        this.f8408g = false;
    }

    public void onAdViewProcessed() {
        this.f8408g = true;
    }

    public String getSessionId(View view) {
        if (this.f8403b.size() == 0) {
            return null;
        }
        String str = (String) this.f8403b.get(view);
        if (str == null) {
            return str;
        }
        this.f8403b.remove(view);
        return str;
    }

    public ArrayList<String> getFriendlySessionIds(View view) {
        if (this.f8404c.size() == 0) {
            return null;
        }
        ArrayList<String> arrayList = (ArrayList) this.f8404c.get(view);
        if (arrayList == null) {
            return arrayList;
        }
        this.f8404c.remove(view);
        Collections.sort(arrayList);
        return arrayList;
    }

    public ViewType getViewType(View view) {
        if (this.f8405d.contains(view)) {
            return ViewType.ROOT_VIEW;
        }
        return this.f8408g ? ViewType.OBSTRUCTION_VIEW : ViewType.UNDERLYING_VIEW;
    }

    @VisibleForTesting
    HashMap<View, String> m11170a() {
        return this.f8403b;
    }

    @VisibleForTesting
    HashMap<View, ArrayList<String>> m11171b() {
        return this.f8404c;
    }

    @VisibleForTesting
    HashSet<View> m11172c() {
        return this.f8405d;
    }
}
