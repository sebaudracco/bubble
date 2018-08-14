package org.telegram.tgnet;

public class TLRPC$TL_contacts_search extends TLObject {
    public static int constructor = 301470424;
    public int limit;
    public String f12639q;

    public TLObject deserializeResponse(AbstractSerializedData stream, int constructor, boolean exception) {
        return TLRPC$TL_contacts_found.TLdeserialize(stream, constructor, exception);
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        stream.writeString(this.f12639q);
        stream.writeInt32(this.limit);
    }
}
