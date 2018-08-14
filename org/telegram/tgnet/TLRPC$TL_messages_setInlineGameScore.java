package org.telegram.tgnet;

public class TLRPC$TL_messages_setInlineGameScore extends TLObject {
    public static int constructor = 363700068;
    public boolean edit_message;
    public int flags;
    public boolean force;
    public TLRPC$TL_inputBotInlineMessageID id;
    public int score;
    public TLRPC$InputUser user_id;

    public TLObject deserializeResponse(AbstractSerializedData stream, int constructor, boolean exception) {
        return TLRPC$Bool.TLdeserialize(stream, constructor, exception);
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        this.flags = this.edit_message ? this.flags | 1 : this.flags & -2;
        this.flags = this.force ? this.flags | 2 : this.flags & -3;
        stream.writeInt32(this.flags);
        this.id.serializeToStream(stream);
        this.user_id.serializeToStream(stream);
        stream.writeInt32(this.score);
    }
}
