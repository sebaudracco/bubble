package org.telegram.tgnet;

public class TLRPC$TL_channelAdminLogEventActionToggleSignatures extends TLRPC$ChannelAdminLogEventAction {
    public static int constructor = 648939889;
    public boolean new_value;

    public void readParams(AbstractSerializedData stream, boolean exception) {
        this.new_value = stream.readBool(exception);
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        stream.writeBool(this.new_value);
    }
}
