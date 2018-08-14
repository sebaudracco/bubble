package org.telegram.tgnet;

import java.util.ArrayList;
import org.telegram.tgnet.TLRPC.User;

public class TLRPC$TL_geochats_located extends TLObject {
    public static int constructor = 1224651367;
    public ArrayList<TLRPC$Chat> chats = new ArrayList();
    public ArrayList<TLRPC$GeoChatMessage> messages = new ArrayList();
    public ArrayList<TLRPC$TL_chatLocated> results = new ArrayList();
    public ArrayList<User> users = new ArrayList();

    public static TLRPC$TL_geochats_located TLdeserialize(AbstractSerializedData stream, int constructor, boolean exception) {
        if (constructor == constructor) {
            TLRPC$TL_geochats_located result = new TLRPC$TL_geochats_located();
            result.readParams(stream, exception);
            return result;
        } else if (!exception) {
            return null;
        } else {
            throw new RuntimeException(String.format("can't parse magic %x in TL_geochats_located", new Object[]{Integer.valueOf(constructor)}));
        }
    }

    public void readParams(AbstractSerializedData stream, boolean exception) {
        if (stream.readInt32(exception) == 481674261) {
            int count = stream.readInt32(exception);
            int a = 0;
            while (a < count) {
                TLRPC$TL_chatLocated object = TLRPC$TL_chatLocated.TLdeserialize(stream, stream.readInt32(exception), exception);
                if (object != null) {
                    this.results.add(object);
                    a++;
                } else {
                    return;
                }
            }
            if (stream.readInt32(exception) == 481674261) {
                count = stream.readInt32(exception);
                a = 0;
                while (a < count) {
                    TLRPC$GeoChatMessage object2 = TLRPC$GeoChatMessage.TLdeserialize(stream, stream.readInt32(exception), exception);
                    if (object2 != null) {
                        this.messages.add(object2);
                        a++;
                    } else {
                        return;
                    }
                }
                if (stream.readInt32(exception) == 481674261) {
                    count = stream.readInt32(exception);
                    a = 0;
                    while (a < count) {
                        TLRPC$Chat object3 = TLRPC$Chat.TLdeserialize(stream, stream.readInt32(exception), exception);
                        if (object3 != null) {
                            this.chats.add(object3);
                            a++;
                        } else {
                            return;
                        }
                    }
                    if (stream.readInt32(exception) == 481674261) {
                        count = stream.readInt32(exception);
                        a = 0;
                        while (a < count) {
                            User object4 = User.TLdeserialize(stream, stream.readInt32(exception), exception);
                            if (object4 != null) {
                                this.users.add(object4);
                                a++;
                            } else {
                                return;
                            }
                        }
                    } else if (exception) {
                        throw new RuntimeException(String.format("wrong Vector magic, got %x", new Object[]{Integer.valueOf(magic)}));
                    }
                } else if (exception) {
                    throw new RuntimeException(String.format("wrong Vector magic, got %x", new Object[]{Integer.valueOf(magic)}));
                }
            } else if (exception) {
                throw new RuntimeException(String.format("wrong Vector magic, got %x", new Object[]{Integer.valueOf(magic)}));
            }
        } else if (exception) {
            throw new RuntimeException(String.format("wrong Vector magic, got %x", new Object[]{Integer.valueOf(magic)}));
        }
    }

    public void serializeToStream(AbstractSerializedData stream) {
        int a;
        stream.writeInt32(constructor);
        stream.writeInt32(481674261);
        int count = this.results.size();
        stream.writeInt32(count);
        for (a = 0; a < count; a++) {
            ((TLRPC$TL_chatLocated) this.results.get(a)).serializeToStream(stream);
        }
        stream.writeInt32(481674261);
        count = this.messages.size();
        stream.writeInt32(count);
        for (a = 0; a < count; a++) {
            ((TLRPC$GeoChatMessage) this.messages.get(a)).serializeToStream(stream);
        }
        stream.writeInt32(481674261);
        count = this.chats.size();
        stream.writeInt32(count);
        for (a = 0; a < count; a++) {
            ((TLRPC$Chat) this.chats.get(a)).serializeToStream(stream);
        }
        stream.writeInt32(481674261);
        count = this.users.size();
        stream.writeInt32(count);
        for (a = 0; a < count; a++) {
            ((User) this.users.get(a)).serializeToStream(stream);
        }
    }
}