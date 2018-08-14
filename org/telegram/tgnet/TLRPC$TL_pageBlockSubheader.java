package org.telegram.tgnet;

public class TLRPC$TL_pageBlockSubheader extends TLRPC$PageBlock {
    public static int constructor = -248793375;

    public void readParams(AbstractSerializedData stream, boolean exception) {
        this.text = TLRPC$RichText.TLdeserialize(stream, stream.readInt32(exception), exception);
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        this.text.serializeToStream(stream);
    }
}
