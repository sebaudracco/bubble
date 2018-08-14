package com.appnext.core.callbacks;

import com.appnext.core.ECPM;

public interface OnECPMLoaded {
    void ecpm(ECPM ecpm);

    void error(String str);
}
