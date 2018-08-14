package org.telegram.tgnet;

public class TLRPC$TL_messages_searchGlobal extends TLObject {
    public static int constructor = -1640190800;
    public int limit;
    public int offset_date;
    public int offset_id;
    public TLRPC$InputPeer offset_peer;
    public String f12648q;

    public TLObject deserializeResponse(AbstractSerializedData stream, int constructor, boolean exception) {
        return TLRPC$messages_Messages.TLdeserialize(stream, constructor, exception);
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        stream.writeString(this.f12648q);
        stream.writeInt32(this.offset_date);
        this.offset_peer.serializeToStream(stream);
        stream.writeInt32(this.offset_id);
        stream.writeInt32(this.limit);
    }
}
