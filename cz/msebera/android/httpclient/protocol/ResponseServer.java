package cz.msebera.android.httpclient.protocol;

import cz.msebera.android.httpclient.HttpException;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.HttpResponseInterceptor;
import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.util.Args;
import java.io.IOException;

@Immutable
public class ResponseServer implements HttpResponseInterceptor {
    private final String originServer;

    public ResponseServer(String originServer) {
        this.originServer = originServer;
    }

    public ResponseServer() {
        this(null);
    }

    public void process(HttpResponse response, HttpContext context) throws HttpException, IOException {
        Args.notNull(response, "HTTP response");
        if (!response.containsHeader("Server") && this.originServer != null) {
            response.addHeader("Server", this.originServer);
        }
    }
}
