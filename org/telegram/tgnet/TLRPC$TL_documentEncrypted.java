package org.telegram.tgnet;

public class TLRPC$TL_documentEncrypted extends TLRPC$Document {
    public static int constructor = 1431655768;

    public void readParams(AbstractSerializedData stream, boolean exception) {
        this.id = stream.readInt64(exception);
        this.access_hash = stream.readInt64(exception);
        this.date = stream.readInt32(exception);
        this.mime_type = stream.readString(exception);
        this.size = stream.readInt32(exception);
        this.thumb = TLRPC$PhotoSize.TLdeserialize(stream, stream.readInt32(exception), exception);
        this.dc_id = stream.readInt32(exception);
        if (stream.readInt32(exception) == 481674261) {
            int count = stream.readInt32(exception);
            int a = 0;
            while (a < count) {
                TLRPC$DocumentAttribute object = TLRPC$DocumentAttribute.TLdeserialize(stream, stream.readInt32(exception), exception);
                if (object != null) {
                    this.attributes.add(object);
                    a++;
                } else {
                    return;
                }
            }
            this.key = stream.readByteArray(exception);
            this.iv = stream.readByteArray(exception);
        } else if (exception) {
            throw new RuntimeException(String.format("wrong Vector magic, got %x", new Object[]{Integer.valueOf(magic)}));
        }
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        stream.writeInt64(this.id);
        stream.writeInt64(this.access_hash);
        stream.writeInt32(this.date);
        stream.writeString(this.mime_type);
        stream.writeInt32(this.size);
        this.thumb.serializeToStream(stream);
        stream.writeInt32(this.dc_id);
        stream.writeInt32(481674261);
        int count = this.attributes.size();
        stream.writeInt32(count);
        for (int a = 0; a < count; a++) {
            ((TLRPC$DocumentAttribute) this.attributes.get(a)).serializeToStream(stream);
        }
        stream.writeByteArray(this.key);
        stream.writeByteArray(this.iv);
    }
}
