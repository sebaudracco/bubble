package org.telegram.tgnet;

public class TLRPC$TL_webPage extends TLRPC$WebPage {
    public static int constructor = 1594340540;

    public void readParams(AbstractSerializedData stream, boolean exception) {
        this.flags = stream.readInt32(exception);
        this.id = stream.readInt64(exception);
        this.url = stream.readString(exception);
        this.display_url = stream.readString(exception);
        this.hash = stream.readInt32(exception);
        if ((this.flags & 1) != 0) {
            this.type = stream.readString(exception);
        }
        if ((this.flags & 2) != 0) {
            this.site_name = stream.readString(exception);
        }
        if ((this.flags & 4) != 0) {
            this.title = stream.readString(exception);
        }
        if ((this.flags & 8) != 0) {
            this.description = stream.readString(exception);
        }
        if ((this.flags & 16) != 0) {
            this.photo = TLRPC$Photo.TLdeserialize(stream, stream.readInt32(exception), exception);
        }
        if ((this.flags & 32) != 0) {
            this.embed_url = stream.readString(exception);
        }
        if ((this.flags & 32) != 0) {
            this.embed_type = stream.readString(exception);
        }
        if ((this.flags & 64) != 0) {
            this.embed_width = stream.readInt32(exception);
        }
        if ((this.flags & 64) != 0) {
            this.embed_height = stream.readInt32(exception);
        }
        if ((this.flags & 128) != 0) {
            this.duration = stream.readInt32(exception);
        }
        if ((this.flags & 256) != 0) {
            this.author = stream.readString(exception);
        }
        if ((this.flags & 512) != 0) {
            this.document = TLRPC$Document.TLdeserialize(stream, stream.readInt32(exception), exception);
        }
        if ((this.flags & 1024) != 0) {
            this.cached_page = TLRPC$Page.TLdeserialize(stream, stream.readInt32(exception), exception);
        }
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        stream.writeInt32(this.flags);
        stream.writeInt64(this.id);
        stream.writeString(this.url);
        stream.writeString(this.display_url);
        stream.writeInt32(this.hash);
        if ((this.flags & 1) != 0) {
            stream.writeString(this.type);
        }
        if ((this.flags & 2) != 0) {
            stream.writeString(this.site_name);
        }
        if ((this.flags & 4) != 0) {
            stream.writeString(this.title);
        }
        if ((this.flags & 8) != 0) {
            stream.writeString(this.description);
        }
        if ((this.flags & 16) != 0) {
            this.photo.serializeToStream(stream);
        }
        if ((this.flags & 32) != 0) {
            stream.writeString(this.embed_url);
        }
        if ((this.flags & 32) != 0) {
            stream.writeString(this.embed_type);
        }
        if ((this.flags & 64) != 0) {
            stream.writeInt32(this.embed_width);
        }
        if ((this.flags & 64) != 0) {
            stream.writeInt32(this.embed_height);
        }
        if ((this.flags & 128) != 0) {
            stream.writeInt32(this.duration);
        }
        if ((this.flags & 256) != 0) {
            stream.writeString(this.author);
        }
        if ((this.flags & 512) != 0) {
            this.document.serializeToStream(stream);
        }
        if ((this.flags & 1024) != 0) {
            this.cached_page.serializeToStream(stream);
        }
    }
}
