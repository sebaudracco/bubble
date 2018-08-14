package cz.msebera.android.httpclient.impl.conn;

import java.net.Proxy.Type;

/* synthetic */ class ProxySelectorRoutePlanner$1 {
    static final /* synthetic */ int[] $SwitchMap$java$net$Proxy$Type = new int[Type.values().length];

    static {
        try {
            $SwitchMap$java$net$Proxy$Type[Type.DIRECT.ordinal()] = 1;
        } catch (NoSuchFieldError e) {
        }
        try {
            $SwitchMap$java$net$Proxy$Type[Type.HTTP.ordinal()] = 2;
        } catch (NoSuchFieldError e2) {
        }
        try {
            $SwitchMap$java$net$Proxy$Type[Type.SOCKS.ordinal()] = 3;
        } catch (NoSuchFieldError e3) {
        }
    }
}
