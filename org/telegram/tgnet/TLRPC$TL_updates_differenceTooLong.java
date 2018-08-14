package org.telegram.tgnet;

public class TLRPC$TL_updates_differenceTooLong extends TLRPC$updates_Difference {
    public static int constructor = 1258196845;

    public void readParams(AbstractSerializedData stream, boolean exception) {
        this.pts = stream.readInt32(exception);
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        stream.writeInt32(this.pts);
    }
}
