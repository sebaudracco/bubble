package org.telegram.tgnet;

public class TLRPC$TL_documentAttributeFilename extends TLRPC$DocumentAttribute {
    public static int constructor = 358154344;

    public void readParams(AbstractSerializedData stream, boolean exception) {
        this.file_name = stream.readString(exception);
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        stream.writeString(this.file_name);
    }
}
