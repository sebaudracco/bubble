package org.telegram.tgnet;

public class TLRPC$TL_account_resetNotifySettings extends TLObject {
    public static int constructor = -612493497;

    public TLObject deserializeResponse(AbstractSerializedData stream, int constructor, boolean exception) {
        return TLRPC$Bool.TLdeserialize(stream, constructor, exception);
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
    }
}
