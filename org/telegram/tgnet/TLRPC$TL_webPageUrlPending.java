package org.telegram.tgnet;

public class TLRPC$TL_webPageUrlPending extends TLRPC$WebPage {
    public static int constructor = -736472729;

    public void readParams(AbstractSerializedData stream, boolean exception) {
        this.url = stream.readString(exception);
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        stream.writeString(this.url);
    }
}
