package com.google.android.exoplayer2.metadata.emsg;

import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.Metadata$Entry;
import com.google.android.exoplayer2.metadata.MetadataDecoder;
import com.google.android.exoplayer2.metadata.MetadataInputBuffer;
import com.google.android.exoplayer2.util.ParsableByteArray;
import java.nio.ByteBuffer;
import java.util.Arrays;

public final class EventMessageDecoder implements MetadataDecoder {
    public Metadata decode(MetadataInputBuffer inputBuffer) {
        ByteBuffer buffer = inputBuffer.data;
        ParsableByteArray emsgData = new ParsableByteArray(buffer.array(), buffer.limit());
        String schemeIdUri = emsgData.readNullTerminatedString();
        String value = emsgData.readNullTerminatedString();
        long timescale = emsgData.readUnsignedInt();
        emsgData.skipBytes(4);
        return new Metadata(new Metadata$Entry[]{new EventMessage(schemeIdUri, value, (emsgData.readUnsignedInt() * 1000) / timescale, emsgData.readUnsignedInt(), Arrays.copyOfRange(data, emsgData.getPosition(), size))});
    }
}
