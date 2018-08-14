package org.telegram.tgnet;

public class TLRPC$TL_pageBlockEmbed_layer60 extends TLRPC$TL_pageBlockEmbed {
    public static int constructor = -650782469;

    public void readParams(AbstractSerializedData stream, boolean exception) {
        boolean z = true;
        this.flags = stream.readInt32(exception);
        this.full_width = (this.flags & 1) != 0;
        if ((this.flags & 8) == 0) {
            z = false;
        }
        this.allow_scrolling = z;
        if ((this.flags & 2) != 0) {
            this.url = stream.readString(exception);
        }
        if ((this.flags & 4) != 0) {
            this.html = stream.readString(exception);
        }
        this.w = stream.readInt32(exception);
        this.h = stream.readInt32(exception);
        this.caption = TLRPC$RichText.TLdeserialize(stream, stream.readInt32(exception), exception);
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        this.flags = this.full_width ? this.flags | 1 : this.flags & -2;
        this.flags = this.allow_scrolling ? this.flags | 8 : this.flags & -9;
        stream.writeInt32(this.flags);
        if ((this.flags & 2) != 0) {
            stream.writeString(this.url);
        }
        if ((this.flags & 4) != 0) {
            stream.writeString(this.html);
        }
        stream.writeInt32(this.w);
        stream.writeInt32(this.h);
        this.caption.serializeToStream(stream);
    }
}
