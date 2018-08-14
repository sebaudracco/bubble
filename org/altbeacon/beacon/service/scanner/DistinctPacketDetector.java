package org.altbeacon.beacon.service.scanner;

import android.support.annotation.NonNull;
import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.Set;

public class DistinctPacketDetector {
    private static final int MAX_PACKETS_TO_TRACK = 1000;
    @NonNull
    private final Set<ByteBuffer> mDistinctPacketsDetected = new HashSet();

    public void clearDetections() {
        this.mDistinctPacketsDetected.clear();
    }

    public boolean isPacketDistinct(@NonNull String originMacAddress, @NonNull byte[] scanRecord) {
        byte[] macBytes = originMacAddress.getBytes();
        ByteBuffer buffer = ByteBuffer.allocate(macBytes.length + scanRecord.length);
        buffer.put(macBytes);
        buffer.put(scanRecord);
        buffer.rewind();
        if (this.mDistinctPacketsDetected.size() == 1000) {
            return this.mDistinctPacketsDetected.contains(buffer);
        }
        return this.mDistinctPacketsDetected.add(buffer);
    }
}
