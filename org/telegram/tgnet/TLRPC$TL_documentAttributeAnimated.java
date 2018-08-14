package org.telegram.tgnet;

public class TLRPC$TL_documentAttributeAnimated extends TLRPC$DocumentAttribute {
    public static int constructor = 297109817;

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
    }
}
