package org.telegram.tgnet;

public class TLRPC$TL_messages_migrateChat extends TLObject {
    public static int constructor = 363051235;
    public int chat_id;

    public TLObject deserializeResponse(AbstractSerializedData stream, int constructor, boolean exception) {
        return TLRPC$Updates.TLdeserialize(stream, constructor, exception);
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        stream.writeInt32(this.chat_id);
    }
}
