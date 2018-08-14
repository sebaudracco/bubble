package org.telegram.tgnet;

public class TLRPC$TL_auth_checkedPhone extends TLObject {
    public static int constructor = -2128698738;
    public boolean phone_registered;

    public static TLRPC$TL_auth_checkedPhone TLdeserialize(AbstractSerializedData stream, int constructor, boolean exception) {
        if (constructor == constructor) {
            TLRPC$TL_auth_checkedPhone result = new TLRPC$TL_auth_checkedPhone();
            result.readParams(stream, exception);
            return result;
        } else if (!exception) {
            return null;
        } else {
            throw new RuntimeException(String.format("can't parse magic %x in TL_auth_checkedPhone", new Object[]{Integer.valueOf(constructor)}));
        }
    }

    public void readParams(AbstractSerializedData stream, boolean exception) {
        this.phone_registered = stream.readBool(exception);
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        stream.writeBool(this.phone_registered);
    }
}
