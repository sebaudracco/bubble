package org.telegram.tgnet;

import java.util.ArrayList;

public class TLRPC$TL_shippingOption extends TLObject {
    public static int constructor = -1239335713;
    public String id;
    public ArrayList<TLRPC$TL_labeledPrice> prices = new ArrayList();
    public String title;

    public static TLRPC$TL_shippingOption TLdeserialize(AbstractSerializedData stream, int constructor, boolean exception) {
        if (constructor == constructor) {
            TLRPC$TL_shippingOption result = new TLRPC$TL_shippingOption();
            result.readParams(stream, exception);
            return result;
        } else if (!exception) {
            return null;
        } else {
            throw new RuntimeException(String.format("can't parse magic %x in TL_shippingOption", new Object[]{Integer.valueOf(constructor)}));
        }
    }

    public void readParams(AbstractSerializedData stream, boolean exception) {
        this.id = stream.readString(exception);
        this.title = stream.readString(exception);
        if (stream.readInt32(exception) == 481674261) {
            int count = stream.readInt32(exception);
            int a = 0;
            while (a < count) {
                TLRPC$TL_labeledPrice object = TLRPC$TL_labeledPrice.TLdeserialize(stream, stream.readInt32(exception), exception);
                if (object != null) {
                    this.prices.add(object);
                    a++;
                } else {
                    return;
                }
            }
        } else if (exception) {
            throw new RuntimeException(String.format("wrong Vector magic, got %x", new Object[]{Integer.valueOf(magic)}));
        }
    }

    public void serializeToStream(AbstractSerializedData stream) {
        stream.writeInt32(constructor);
        stream.writeString(this.id);
        stream.writeString(this.title);
        stream.writeInt32(481674261);
        int count = this.prices.size();
        stream.writeInt32(count);
        for (int a = 0; a < count; a++) {
            ((TLRPC$TL_labeledPrice) this.prices.get(a)).serializeToStream(stream);
        }
    }
}
