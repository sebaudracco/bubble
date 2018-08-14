package com.fyber.mediation;

import android.content.Context;
import android.support.annotation.NonNull;
import com.fyber.mediation.p108b.C2580a;

public interface ProviderRequester<R, E extends Exception> {
    void isAdAvailable(@NonNull Context context, @NonNull C2580a c2580a);

    void setProviderRequesterListener(@NonNull ProviderRequesterListener<R, E> providerRequesterListener);
}
