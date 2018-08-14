package org.altbeacon.beacon;

import android.annotation.TargetApi;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.LongBuffer;
import java.util.Arrays;
import java.util.UUID;
import java.util.regex.Pattern;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

public class Identifier implements Comparable<Identifier>, Serializable {
    private static final Pattern DECIMAL_PATTERN = Pattern.compile("^0|[1-9][0-9]*$");
    private static final char[] HEX_DIGITS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static final Pattern HEX_PATTERN = Pattern.compile("^0x[0-9A-Fa-f]*$");
    private static final Pattern HEX_PATTERN_NO_PREFIX = Pattern.compile("^[0-9A-Fa-f]*$");
    private static final int MAX_INTEGER = 65535;
    private static final Pattern UUID_PATTERN = Pattern.compile("^[0-9A-Fa-f]{8}-?[0-9A-Fa-f]{4}-?[0-9A-Fa-f]{4}-?[0-9A-Fa-f]{4}-?[0-9A-Fa-f]{12}$");
    private final byte[] mValue;

    public static Identifier parse(String stringValue) {
        return parse(stringValue, -1);
    }

    public static Identifier parse(String stringValue, int desiredByteLength) {
        if (stringValue == null) {
            throw new NullPointerException("Identifiers cannot be constructed from null pointers but \"stringValue\" is null.");
        } else if (HEX_PATTERN.matcher(stringValue).matches()) {
            return parseHex(stringValue.substring(2), desiredByteLength);
        } else {
            if (UUID_PATTERN.matcher(stringValue).matches()) {
                return parseHex(stringValue.replace("-", ""), desiredByteLength);
            }
            if (DECIMAL_PATTERN.matcher(stringValue).matches()) {
                try {
                    int value = Integer.valueOf(stringValue).intValue();
                    if (desiredByteLength <= 0 || desiredByteLength == 2) {
                        return fromInt(value);
                    }
                    return fromLong((long) value, desiredByteLength);
                } catch (Throwable t) {
                    IllegalArgumentException illegalArgumentException = new IllegalArgumentException("Unable to parse Identifier in decimal format.", t);
                }
            } else if (HEX_PATTERN_NO_PREFIX.matcher(stringValue).matches()) {
                return parseHex(stringValue, desiredByteLength);
            } else {
                throw new IllegalArgumentException("Unable to parse Identifier.");
            }
        }
    }

    private static Identifier parseHex(String identifierString, int desiredByteLength) {
        String str = (identifierString.length() % 2 == 0 ? "" : SchemaSymbols.ATTVAL_FALSE_0) + identifierString.toUpperCase();
        if (desiredByteLength > 0 && desiredByteLength < str.length() / 2) {
            str = str.substring(str.length() - (desiredByteLength * 2));
        }
        if (desiredByteLength > 0 && desiredByteLength > str.length() / 2) {
            int extraCharsToAdd = (desiredByteLength * 2) - str.length();
            StringBuilder sb = new StringBuilder();
            while (sb.length() < extraCharsToAdd) {
                sb.append(SchemaSymbols.ATTVAL_FALSE_0);
            }
            str = sb.toString() + str;
        }
        byte[] result = new byte[(str.length() / 2)];
        for (int i = 0; i < result.length; i++) {
            result[i] = (byte) (Integer.parseInt(str.substring(i * 2, (i * 2) + 2), 16) & 255);
        }
        return new Identifier(result);
    }

    public static Identifier fromLong(long longValue, int desiredByteLength) {
        if (desiredByteLength < 0) {
            throw new IllegalArgumentException("Identifier length must be > 0.");
        }
        byte[] newValue = new byte[desiredByteLength];
        for (int i = desiredByteLength - 1; i >= 0; i--) {
            newValue[i] = (byte) ((int) (255 & longValue));
            longValue >>= 8;
        }
        return new Identifier(newValue);
    }

    public static Identifier fromInt(int intValue) {
        if (intValue < 0 || intValue > 65535) {
            throw new IllegalArgumentException("Identifiers can only be constructed from integers between 0 and 65535 (inclusive).");
        }
        return new Identifier(new byte[]{(byte) (intValue >> 8), (byte) intValue});
    }

    @TargetApi(9)
    public static Identifier fromBytes(byte[] bytes, int start, int end, boolean littleEndian) {
        if (bytes == null) {
            throw new NullPointerException("Identifiers cannot be constructed from null pointers but \"bytes\" is null.");
        } else if (start < 0 || start > bytes.length) {
            throw new ArrayIndexOutOfBoundsException("start < 0 || start > bytes.length");
        } else if (end > bytes.length) {
            throw new ArrayIndexOutOfBoundsException("end > bytes.length");
        } else if (start > end) {
            throw new IllegalArgumentException("start > end");
        } else {
            byte[] byteRange = Arrays.copyOfRange(bytes, start, end);
            if (littleEndian) {
                reverseArray(byteRange);
            }
            return new Identifier(byteRange);
        }
    }

    public static Identifier fromUuid(UUID uuid) {
        ByteBuffer buf = ByteBuffer.allocate(16);
        buf.putLong(uuid.getMostSignificantBits());
        buf.putLong(uuid.getLeastSignificantBits());
        return new Identifier(buf.array());
    }

    @Deprecated
    public Identifier(Identifier identifier) {
        if (identifier == null) {
            throw new NullPointerException("Identifiers cannot be constructed from null pointers but \"identifier\" is null.");
        }
        this.mValue = identifier.mValue;
    }

    protected Identifier(byte[] value) {
        if (value == null) {
            throw new NullPointerException("Identifiers cannot be constructed from null pointers but \"value\" is null.");
        }
        this.mValue = value;
    }

    public String toString() {
        if (this.mValue.length == 2) {
            return Integer.toString(toInt());
        }
        if (this.mValue.length == 16) {
            return toUuid().toString();
        }
        return toHexString();
    }

    public int toInt() {
        if (this.mValue.length > 2) {
            throw new UnsupportedOperationException("Only supported for Identifiers with max byte length of 2");
        }
        int result = 0;
        for (int i = 0; i < this.mValue.length; i++) {
            result |= (this.mValue[i] & 255) << (((this.mValue.length - i) - 1) * 8);
        }
        return result;
    }

    @TargetApi(9)
    public byte[] toByteArrayOfSpecifiedEndianness(boolean bigEndian) {
        byte[] copy = Arrays.copyOf(this.mValue, this.mValue.length);
        if (!bigEndian) {
            reverseArray(copy);
        }
        return copy;
    }

    private static void reverseArray(byte[] bytes) {
        for (int i = 0; i < bytes.length / 2; i++) {
            int mirroredIndex = (bytes.length - i) - 1;
            byte tmp = bytes[i];
            bytes[i] = bytes[mirroredIndex];
            bytes[mirroredIndex] = tmp;
        }
    }

    public int getByteCount() {
        return this.mValue.length;
    }

    public int hashCode() {
        return Arrays.hashCode(this.mValue);
    }

    public boolean equals(Object that) {
        if (!(that instanceof Identifier)) {
            return false;
        }
        return Arrays.equals(this.mValue, ((Identifier) that).mValue);
    }

    public String toHexString() {
        int l = this.mValue.length;
        char[] out = new char[((l * 2) + 2)];
        out[0] = '0';
        out[1] = 'x';
        int j = 2;
        for (int i = 0; i < l; i++) {
            int i2 = j + 1;
            out[j] = HEX_DIGITS[(this.mValue[i] & 240) >>> 4];
            j = i2 + 1;
            out[i2] = HEX_DIGITS[this.mValue[i] & 15];
        }
        return new String(out);
    }

    @Deprecated
    public String toUuidString() {
        return toUuid().toString();
    }

    public UUID toUuid() {
        if (this.mValue.length != 16) {
            throw new UnsupportedOperationException("Only Identifiers backed by a byte array with length of exactly 16 can be UUIDs.");
        }
        LongBuffer buf = ByteBuffer.wrap(this.mValue).asLongBuffer();
        return new UUID(buf.get(), buf.get());
    }

    public byte[] toByteArray() {
        return (byte[]) this.mValue.clone();
    }

    public int compareTo(Identifier that) {
        if (this.mValue.length == that.mValue.length) {
            int i = 0;
            while (i < this.mValue.length) {
                if (this.mValue[i] == that.mValue[i]) {
                    i++;
                } else if (this.mValue[i] >= that.mValue[i]) {
                    return 1;
                } else {
                    return -1;
                }
            }
            return 0;
        } else if (this.mValue.length < that.mValue.length) {
            return -1;
        } else {
            return 1;
        }
    }
}
