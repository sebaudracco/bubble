package org.telegram.tgnet;

public class TLRPC$TL_message_old4 extends TLRPC$TL_message {
    public static int constructor = -1023016155;

    public void readParams(AbstractSerializedData stream, boolean exception) {
        boolean z;
        boolean z2 = true;
        this.flags = (stream.readInt32(exception) | 256) | 512;
        this.unread = (this.flags & 1) != 0;
        if ((this.flags & 2) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.out = z;
        if ((this.flags & 16) != 0) {
            z = true;
        } else {
            z = false;
        }
        this.mentioned = z;
        if ((this.flags & 32) == 0) {
            z2 = false;
        }
        this.media_unread = z2;
        this.id = stream.readInt32(exception);
        this.from_id = stream.readInt32(exception);
        this.to_id = TLRPC$Peer.TLdeserialize(stream, stream.readInt32(exception), exception);
        if ((this.flags & 4) != 0) {
            this.fwd_from = new TLRPC$TL_messageFwdHeader();
            this.fwd_from.from_id = stream.readInt32(exception);
            TLRPC$MessageFwdHeader tLRPC$MessageFwdHeader = this.fwd_from;
            tLRPC$MessageFwdHeader.flags |= 1;
            this.fwd_from.date = stream.readInt32(exception);
        }
        if ((this.flags & 8) != 0) {
            this.reply_to_msg_id = stream.readInt32(exception);
        }
        this.date = stream.readInt32(exception);
        this.message = stream.readString(exception);
        this.media = TLRPC$MessageMedia.TLdeserialize(stream, stream.readInt32(exception), exception);
        if ((this.flags & 64) != 0) {
            this.reply_markup = TLRPC$ReplyMarkup.TLdeserialize(stream, stream.readInt32(exception), exception);
        }
        if (this.id < 0 || !(this.media == null || (this.media instanceof TLRPC$TL_messageMediaEmpty) || (this.media instanceof TLRPC$TL_messageMediaWebPage) || this.message == null || this.message.length() == 0 || !this.message.startsWith("-1"))) {
            this.attachPath = stream.readString(exception);
        }
        if ((this.flags & 4) != 0 && this.id < 0) {
            this.fwd_msg_id = stream.readInt32(exception);
        }
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        this.flags = this.unread ? this.flags | 1 : this.flags & -2;
        this.flags = this.out ? this.flags | 2 : this.flags & -3;
        this.flags = this.mentioned ? this.flags | 16 : this.flags & -17;
        this.flags = this.media_unread ? this.flags | 32 : this.flags & -33;
        stream.writeInt32(this.flags);
        stream.writeInt32(this.id);
        stream.writeInt32(this.from_id);
        this.to_id.serializeToStream(stream);
        if ((this.flags & 4) != 0) {
            stream.writeInt32(this.fwd_from.from_id);
            stream.writeInt32(this.fwd_from.date);
        }
        if ((this.flags & 8) != 0) {
            stream.writeInt32(this.reply_to_msg_id);
        }
        stream.writeInt32(this.date);
        stream.writeString(this.message);
        this.media.serializeToStream(stream);
        if ((this.flags & 64) != 0) {
            this.reply_markup.serializeToStream(stream);
        }
        stream.writeString(this.attachPath);
        if ((this.flags & 4) != 0 && this.id < 0) {
            stream.writeInt32(this.fwd_msg_id);
        }
    }
}
