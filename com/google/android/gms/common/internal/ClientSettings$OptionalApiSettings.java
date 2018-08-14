package com.google.android.gms.common.internal;

import com.google.android.gms.common.api.Scope;
import java.util.Collections;
import java.util.Set;

public final class ClientSettings$OptionalApiSettings {
    public final Set<Scope> mScopes;

    public ClientSettings$OptionalApiSettings(Set<Scope> set) {
        Preconditions.checkNotNull(set);
        this.mScopes = Collections.unmodifiableSet(set);
    }
}
