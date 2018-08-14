package org.telegram.tgnet;

public class TLRPC$TL_maskCoords extends TLObject {
    public static int constructor = -1361650766;
    public int f12641n;
    public double f12642x;
    public double f12643y;
    public double zoom;

    public static TLRPC$TL_maskCoords TLdeserialize(AbstractSerializedData stream, int constructor, boolean exception) {
        if (constructor == constructor) {
            TLRPC$TL_maskCoords result = new TLRPC$TL_maskCoords();
            result.readParams(stream, exception);
            return result;
        } else if (!exception) {
            return null;
        } else {
            throw new RuntimeException(String.format("can't parse magic %x in TL_maskCoords", new Object[]{Integer.valueOf(constructor)}));
        }
    }

    public void readParams(AbstractSerializedData stream, boolean exception) {
        this.f12641n = stream.readInt32(exception);
        this.f12642x = stream.readDouble(exception);
        this.f12643y = stream.readDouble(exception);
        this.zoom = stream.readDouble(exception);
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        stream.writeInt32(this.f12641n);
        stream.writeDouble(this.f12642x);
        stream.writeDouble(this.f12643y);
        stream.writeDouble(this.zoom);
    }
}
