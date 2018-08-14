package com.google.android.gms.common;

import android.util.Log;
import com.google.android.gms.common.internal.ICertData;
import com.google.android.gms.common.internal.ICertData.Stub;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

abstract class GoogleCertificates$CertData extends Stub {
    private int zzbc;

    protected GoogleCertificates$CertData(byte[] bArr) {
        Preconditions.checkArgument(bArr.length == 25);
        this.zzbc = Arrays.hashCode(bArr);
    }

    protected static byte[] zzd(String str) {
        try {
            return str.getBytes("ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof ICertData)) {
            return false;
        }
        try {
            ICertData iCertData = (ICertData) obj;
            if (iCertData.getHashCode() != hashCode()) {
                return false;
            }
            IObjectWrapper bytesWrapped = iCertData.getBytesWrapped();
            if (bytesWrapped == null) {
                return false;
            }
            return Arrays.equals(getBytes(), (byte[]) ObjectWrapper.unwrap(bytesWrapped));
        } catch (Throwable e) {
            Log.e("GoogleCertificates", "Failed to get Google certificates from remote", e);
            return false;
        }
    }

    abstract byte[] getBytes();

    public IObjectWrapper getBytesWrapped() {
        return ObjectWrapper.wrap(getBytes());
    }

    public int getHashCode() {
        return hashCode();
    }

    public int hashCode() {
        return this.zzbc;
    }
}
