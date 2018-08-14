package org.telegram.tgnet;

public class TLRPC$TL_updates_getState extends TLObject {
    public static int constructor = -304838614;

    public TLObject deserializeResponse(AbstractSerializedData stream, int constructor, boolean exception) {
        return TLRPC$TL_updates_state.TLdeserialize(stream, constructor, exception);
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
    }
}
