package cz.msebera.android.httpclient.impl.conn;

import java.util.concurrent.TimeUnit;

class IdleConnectionHandler$TimeValues {
    private final long timeAdded;
    private final long timeExpires;

    IdleConnectionHandler$TimeValues(long now, long validDuration, TimeUnit validUnit) {
        this.timeAdded = now;
        if (validDuration > 0) {
            this.timeExpires = validUnit.toMillis(validDuration) + now;
        } else {
            this.timeExpires = Long.MAX_VALUE;
        }
    }
}
