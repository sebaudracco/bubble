package com.mopub.network;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressUtils {
    @NonNull
    public static InetAddress getInetAddressByName(@Nullable String host) throws UnknownHostException {
        return InetAddress.getByName(host);
    }

    private InetAddressUtils() {
    }
}
