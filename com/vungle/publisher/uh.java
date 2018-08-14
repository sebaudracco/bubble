package com.vungle.publisher;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class uh {
    @Inject
    uh() {
    }

    public HttpURLConnection m4710a(String str) throws IOException {
        return (HttpURLConnection) new URL(str).openConnection();
    }
}
