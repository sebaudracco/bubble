package org.telegram.tgnet;

public class TLRPC$TL_account_registerDevice extends TLObject {
    public static int constructor = 1669245048;
    public String token;
    public int token_type;

    public TLObject deserializeResponse(AbstractSerializedData stream, int constructor, boolean exception) {
        return TLRPC$Bool.TLdeserialize(stream, constructor, exception);
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        stream.writeInt32(this.token_type);
        stream.writeString(this.token);
    }
}
