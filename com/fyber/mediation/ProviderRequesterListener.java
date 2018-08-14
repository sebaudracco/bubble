package com.fyber.mediation;

import android.support.annotation.NonNull;
import com.fyber.mediation.p108b.C2580a;

public interface ProviderRequesterListener<R, E extends Exception> {
    void setAdAvailable(@NonNull R r, @NonNull C2580a c2580a);

    void setAdError(@NonNull E e, @NonNull C2580a c2580a);

    void setAdNotAvailable(@NonNull C2580a c2580a);
}
