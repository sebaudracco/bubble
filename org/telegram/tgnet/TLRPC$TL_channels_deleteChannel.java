package org.telegram.tgnet;

public class TLRPC$TL_channels_deleteChannel extends TLObject {
    public static int constructor = -1072619549;
    public TLRPC$InputChannel channel;

    public TLObject deserializeResponse(AbstractSerializedData stream, int constructor, boolean exception) {
        return TLRPC$Updates.TLdeserialize(stream, constructor, exception);
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        this.channel.serializeToStream(stream);
    }
}
