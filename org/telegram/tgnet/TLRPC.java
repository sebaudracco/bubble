package org.telegram.tgnet;

public class TLRPC {
    public static final int CHAT_FLAG_IS_PUBLIC = 64;
    public static final int LAYER = 73;
    public static final int MESSAGE_FLAG_EDITED = 32768;
    public static final int MESSAGE_FLAG_FWD = 4;
    public static final int MESSAGE_FLAG_HAS_BOT_ID = 2048;
    public static final int MESSAGE_FLAG_HAS_ENTITIES = 128;
    public static final int MESSAGE_FLAG_HAS_FROM_ID = 256;
    public static final int MESSAGE_FLAG_HAS_MARKUP = 64;
    public static final int MESSAGE_FLAG_HAS_MEDIA = 512;
    public static final int MESSAGE_FLAG_HAS_VIEWS = 1024;
    public static final int MESSAGE_FLAG_MEGAGROUP = Integer.MIN_VALUE;
    public static final int MESSAGE_FLAG_REPLY = 8;
    public static final int USER_FLAG_ACCESS_HASH = 1;
    public static final int USER_FLAG_FIRST_NAME = 2;
    public static final int USER_FLAG_LAST_NAME = 4;
    public static final int USER_FLAG_PHONE = 16;
    public static final int USER_FLAG_PHOTO = 32;
    public static final int USER_FLAG_STATUS = 64;
    public static final int USER_FLAG_UNUSED = 128;
    public static final int USER_FLAG_UNUSED2 = 256;
    public static final int USER_FLAG_UNUSED3 = 512;
    public static final int USER_FLAG_USERNAME = 8;

    public static abstract class User extends TLObject {
        public long access_hash;
        public boolean bot;
        public boolean bot_chat_history;
        public int bot_info_version;
        public boolean bot_inline_geo;
        public String bot_inline_placeholder;
        public boolean bot_nochats;
        public boolean contact;
        public boolean deleted;
        public boolean explicit_content;
        public String first_name;
        public int flags;
        public int id;
        public boolean inactive;
        public String lang_code;
        public String last_name;
        public boolean min;
        public boolean mutual_contact;
        public String phone;
        public UserProfilePhoto photo;
        public boolean restricted;
        public String restriction_reason;
        public boolean self;
        public UserStatus status;
        public String username;
        public boolean verified;

        public static User TLdeserialize(AbstractSerializedData stream, int constructor, boolean exception) {
            User result = null;
            switch (constructor) {
                case -1298475060:
                    result = new TL_userDeleted_old();
                    break;
                case -894214632:
                    result = new TL_userContact_old2();
                    break;
                case -787638374:
                    result = new TL_user_layer65();
                    break;
                case -704549510:
                    result = new TL_userDeleted_old2();
                    break;
                case -640891665:
                    result = new TL_userRequest_old2();
                    break;
                case -218397927:
                    result = new TL_userContact_old();
                    break;
                case 123533224:
                    result = new TL_userForeign_old2();
                    break;
                case 476112392:
                    result = new TL_userSelf_old3();
                    break;
                case 537022650:
                    result = new TL_userEmpty();
                    break;
                case 585404530:
                    result = new TL_user_old();
                    break;
                case 585682608:
                    result = new TL_userRequest_old();
                    break;
                case 773059779:
                    result = new TL_user();
                    break;
                case 1377093789:
                    result = new TL_userForeign_old();
                    break;
                case 1879553105:
                    result = new TL_userSelf_old2();
                    break;
                case 1912944108:
                    result = new TL_userSelf_old();
                    break;
            }
            if (result == null && exception) {
                throw new RuntimeException(String.format("can't parse magic %x in User", new Object[]{Integer.valueOf(constructor)}));
            }
            if (result != null) {
                result.readParams(stream, exception);
            }
            return result;
        }
    }
}
