package com.Decoder;

public class BASE64Decoder extends CharacterDecoder {
    private static final char[] pem_array = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
    private static final byte[] pem_convert_array = new byte[256];
    byte[] decode_buffer = new byte[4];

    protected int bytesPerAtom() {
        return 4;
    }

    protected int bytesPerLine() {
        return 72;
    }

    static {
        int i;
        for (i = 0; i < 255; i++) {
            pem_convert_array[i] = (byte) -1;
        }
        for (i = 0; i < pem_array.length; i++) {
            pem_convert_array[pem_array[i]] = (byte) i;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected void decodeAtom(java.io.PushbackInputStream r13, java.io.OutputStream r14, int r15) throws java.io.IOException {
        /*
        r12 = this;
        r11 = 1;
        r10 = 0;
        r9 = -1;
        r8 = 3;
        r7 = 2;
        r0 = -1;
        r1 = -1;
        r2 = -1;
        r3 = -1;
        if (r15 >= r7) goto L_0x0014;
    L_0x000b:
        r5 = new com.Decoder.CEFormatException;
        r6 = "BASE64Decoder: Not enough bytes for an atom.";
        r5.<init>(r6);
        throw r5;
    L_0x0014:
        r4 = r13.read();
        if (r4 != r9) goto L_0x0020;
    L_0x001a:
        r5 = new com.Decoder.CEStreamExhausted;
        r5.<init>();
        throw r5;
    L_0x0020:
        r5 = 10;
        if (r4 == r5) goto L_0x0014;
    L_0x0024:
        r5 = 13;
        if (r4 == r5) goto L_0x0014;
    L_0x0028:
        r5 = r12.decode_buffer;
        r6 = (byte) r4;
        r5[r10] = r6;
        r5 = r12.decode_buffer;
        r6 = r15 + -1;
        r4 = r12.readFully(r13, r5, r11, r6);
        if (r4 != r9) goto L_0x003d;
    L_0x0037:
        r5 = new com.Decoder.CEStreamExhausted;
        r5.<init>();
        throw r5;
    L_0x003d:
        if (r15 <= r8) goto L_0x0048;
    L_0x003f:
        r5 = r12.decode_buffer;
        r5 = r5[r8];
        r6 = 61;
        if (r5 != r6) goto L_0x0048;
    L_0x0047:
        r15 = 3;
    L_0x0048:
        if (r15 <= r7) goto L_0x0053;
    L_0x004a:
        r5 = r12.decode_buffer;
        r5 = r5[r7];
        r6 = 61;
        if (r5 != r6) goto L_0x0053;
    L_0x0052:
        r15 = 2;
    L_0x0053:
        switch(r15) {
            case 2: goto L_0x006e;
            case 3: goto L_0x0064;
            case 4: goto L_0x005a;
            default: goto L_0x0056;
        };
    L_0x0056:
        switch(r15) {
            case 2: goto L_0x0083;
            case 3: goto L_0x0091;
            case 4: goto L_0x00ac;
            default: goto L_0x0059;
        };
    L_0x0059:
        return;
    L_0x005a:
        r5 = pem_convert_array;
        r6 = r12.decode_buffer;
        r6 = r6[r8];
        r6 = r6 & 255;
        r3 = r5[r6];
    L_0x0064:
        r5 = pem_convert_array;
        r6 = r12.decode_buffer;
        r6 = r6[r7];
        r6 = r6 & 255;
        r2 = r5[r6];
    L_0x006e:
        r5 = pem_convert_array;
        r6 = r12.decode_buffer;
        r6 = r6[r11];
        r6 = r6 & 255;
        r1 = r5[r6];
        r5 = pem_convert_array;
        r6 = r12.decode_buffer;
        r6 = r6[r10];
        r6 = r6 & 255;
        r0 = r5[r6];
        goto L_0x0056;
    L_0x0083:
        r5 = r0 << 2;
        r5 = r5 & 252;
        r6 = r1 >>> 4;
        r6 = r6 & 3;
        r5 = r5 | r6;
        r5 = (byte) r5;
        r14.write(r5);
        goto L_0x0059;
    L_0x0091:
        r5 = r0 << 2;
        r5 = r5 & 252;
        r6 = r1 >>> 4;
        r6 = r6 & 3;
        r5 = r5 | r6;
        r5 = (byte) r5;
        r14.write(r5);
        r5 = r1 << 4;
        r5 = r5 & 240;
        r6 = r2 >>> 2;
        r6 = r6 & 15;
        r5 = r5 | r6;
        r5 = (byte) r5;
        r14.write(r5);
        goto L_0x0059;
    L_0x00ac:
        r5 = r0 << 2;
        r5 = r5 & 252;
        r6 = r1 >>> 4;
        r6 = r6 & 3;
        r5 = r5 | r6;
        r5 = (byte) r5;
        r14.write(r5);
        r5 = r1 << 4;
        r5 = r5 & 240;
        r6 = r2 >>> 2;
        r6 = r6 & 15;
        r5 = r5 | r6;
        r5 = (byte) r5;
        r14.write(r5);
        r5 = r2 << 6;
        r5 = r5 & 192;
        r6 = r3 & 63;
        r5 = r5 | r6;
        r5 = (byte) r5;
        r14.write(r5);
        goto L_0x0059;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.Decoder.BASE64Decoder.decodeAtom(java.io.PushbackInputStream, java.io.OutputStream, int):void");
    }
}
