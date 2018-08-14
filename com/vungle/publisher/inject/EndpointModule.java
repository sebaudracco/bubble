package com.vungle.publisher.inject;

import dagger.Module;
import dagger.Provides;

@Module
/* compiled from: vungle */
public class EndpointModule {
    private String f3014a = "";

    @Provides
    String m4175a() {
        return this.f3014a;
    }

    public EndpointModule setVungleBaseUrl(String vungleBaseUrl) {
        this.f3014a = vungleBaseUrl;
        return this;
    }
}
