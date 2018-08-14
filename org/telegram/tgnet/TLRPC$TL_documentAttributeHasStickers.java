package org.telegram.tgnet;

public class TLRPC$TL_documentAttributeHasStickers extends TLRPC$DocumentAttribute {
    public static int constructor = -1744710921;

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
    }
}
