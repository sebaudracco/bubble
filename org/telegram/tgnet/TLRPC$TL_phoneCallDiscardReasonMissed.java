package org.telegram.tgnet;

public class TLRPC$TL_phoneCallDiscardReasonMissed extends TLRPC$PhoneCallDiscardReason {
    public static int constructor = -2048646399;

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
    }
}
