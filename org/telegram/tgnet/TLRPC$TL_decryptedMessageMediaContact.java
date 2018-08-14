package org.telegram.tgnet;

public class TLRPC$TL_decryptedMessageMediaContact extends TLRPC$DecryptedMessageMedia {
    public static int constructor = 1485441687;

    public void readParams(AbstractSerializedData stream, boolean exception) {
        this.phone_number = stream.readString(exception);
        this.first_name = stream.readString(exception);
        this.last_name = stream.readString(exception);
        this.user_id = stream.readInt32(exception);
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        stream.writeString(this.phone_number);
        stream.writeString(this.first_name);
        stream.writeString(this.last_name);
        stream.writeInt32(this.user_id);
    }
}
