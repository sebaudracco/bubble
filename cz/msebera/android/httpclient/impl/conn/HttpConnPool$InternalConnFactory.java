package cz.msebera.android.httpclient.impl.conn;

import cz.msebera.android.httpclient.conn.ClientConnectionOperator;
import cz.msebera.android.httpclient.conn.OperatedClientConnection;
import cz.msebera.android.httpclient.conn.routing.HttpRoute;
import cz.msebera.android.httpclient.pool.ConnFactory;
import java.io.IOException;

class HttpConnPool$InternalConnFactory implements ConnFactory<HttpRoute, OperatedClientConnection> {
    private final ClientConnectionOperator connOperator;

    HttpConnPool$InternalConnFactory(ClientConnectionOperator connOperator) {
        this.connOperator = connOperator;
    }

    public OperatedClientConnection create(HttpRoute route) throws IOException {
        return this.connOperator.createConnection();
    }
}
