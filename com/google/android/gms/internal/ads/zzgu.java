package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.MurmurHash3;
import java.io.UnsupportedEncodingException;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzgu {
    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @android.support.annotation.Nullable
    public static java.lang.String[] zzb(@android.support.annotation.Nullable java.lang.String r13, boolean r14) {
        /*
        r3 = 1;
        r2 = 0;
        if (r13 != 0) goto L_0x0006;
    L_0x0004:
        r0 = 0;
    L_0x0005:
        return r0;
    L_0x0006:
        r6 = new java.util.ArrayList;
        r6.<init>();
        r7 = r13.toCharArray();
        r8 = r13.length();
        r4 = r2;
        r0 = r2;
        r1 = r2;
    L_0x0016:
        if (r1 >= r8) goto L_0x00e3;
    L_0x0018:
        r9 = java.lang.Character.codePointAt(r7, r1);
        r10 = java.lang.Character.charCount(r9);
        r5 = java.lang.Character.isLetter(r9);
        if (r5 == 0) goto L_0x009f;
    L_0x0026:
        r5 = java.lang.Character.UnicodeBlock.of(r9);
        r11 = java.lang.Character.UnicodeBlock.BOPOMOFO;
        if (r5 == r11) goto L_0x0062;
    L_0x002e:
        r11 = java.lang.Character.UnicodeBlock.BOPOMOFO_EXTENDED;
        if (r5 == r11) goto L_0x0062;
    L_0x0032:
        r11 = java.lang.Character.UnicodeBlock.CJK_COMPATIBILITY;
        if (r5 == r11) goto L_0x0062;
    L_0x0036:
        r11 = java.lang.Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS;
        if (r5 == r11) goto L_0x0062;
    L_0x003a:
        r11 = java.lang.Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT;
        if (r5 == r11) goto L_0x0062;
    L_0x003e:
        r11 = java.lang.Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS;
        if (r5 == r11) goto L_0x0062;
    L_0x0042:
        r11 = java.lang.Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A;
        if (r5 == r11) goto L_0x0062;
    L_0x0046:
        r11 = java.lang.Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B;
        if (r5 == r11) goto L_0x0062;
    L_0x004a:
        r11 = java.lang.Character.UnicodeBlock.ENCLOSED_CJK_LETTERS_AND_MONTHS;
        if (r5 == r11) goto L_0x0062;
    L_0x004e:
        r11 = java.lang.Character.UnicodeBlock.HANGUL_JAMO;
        if (r5 == r11) goto L_0x0062;
    L_0x0052:
        r11 = java.lang.Character.UnicodeBlock.HANGUL_SYLLABLES;
        if (r5 == r11) goto L_0x0062;
    L_0x0056:
        r11 = java.lang.Character.UnicodeBlock.HIRAGANA;
        if (r5 == r11) goto L_0x0062;
    L_0x005a:
        r11 = java.lang.Character.UnicodeBlock.KATAKANA;
        if (r5 == r11) goto L_0x0062;
    L_0x005e:
        r11 = java.lang.Character.UnicodeBlock.KATAKANA_PHONETIC_EXTENSIONS;
        if (r5 != r11) goto L_0x009b;
    L_0x0062:
        r5 = r3;
    L_0x0063:
        if (r5 != 0) goto L_0x007c;
    L_0x0065:
        r5 = 65382; // 0xff66 float:9.162E-41 double:3.2303E-319;
        if (r9 < r5) goto L_0x006f;
    L_0x006a:
        r5 = 65437; // 0xff9d float:9.1697E-41 double:3.233E-319;
        if (r9 <= r5) goto L_0x0079;
    L_0x006f:
        r5 = 65441; // 0xffa1 float:9.1702E-41 double:3.2332E-319;
        if (r9 < r5) goto L_0x009d;
    L_0x0074:
        r5 = 65500; // 0xffdc float:9.1785E-41 double:3.23613E-319;
        if (r9 > r5) goto L_0x009d;
    L_0x0079:
        r5 = r3;
    L_0x007a:
        if (r5 == 0) goto L_0x009f;
    L_0x007c:
        r5 = r3;
    L_0x007d:
        if (r5 == 0) goto L_0x00a1;
    L_0x007f:
        if (r4 == 0) goto L_0x008b;
    L_0x0081:
        r4 = new java.lang.String;
        r5 = r1 - r0;
        r4.<init>(r7, r0, r5);
        r6.add(r4);
    L_0x008b:
        r4 = new java.lang.String;
        r4.<init>(r7, r1, r10);
        r6.add(r4);
        r4 = r0;
        r0 = r2;
    L_0x0095:
        r1 = r1 + r10;
        r12 = r0;
        r0 = r4;
        r4 = r12;
        goto L_0x0016;
    L_0x009b:
        r5 = r2;
        goto L_0x0063;
    L_0x009d:
        r5 = r2;
        goto L_0x007a;
    L_0x009f:
        r5 = r2;
        goto L_0x007d;
    L_0x00a1:
        r5 = java.lang.Character.isLetterOrDigit(r9);
        if (r5 != 0) goto L_0x00b6;
    L_0x00a7:
        r5 = java.lang.Character.getType(r9);
        r11 = 6;
        if (r5 == r11) goto L_0x00b6;
    L_0x00ae:
        r5 = java.lang.Character.getType(r9);
        r11 = 8;
        if (r5 != r11) goto L_0x00bc;
    L_0x00b6:
        if (r4 != 0) goto L_0x00b9;
    L_0x00b8:
        r0 = r1;
    L_0x00b9:
        r4 = r0;
        r0 = r3;
        goto L_0x0095;
    L_0x00bc:
        if (r14 == 0) goto L_0x00d4;
    L_0x00be:
        r5 = java.lang.Character.charCount(r9);
        if (r5 != r3) goto L_0x00d4;
    L_0x00c4:
        r5 = java.lang.Character.toChars(r9);
        r5 = r5[r2];
        r9 = 39;
        if (r5 != r9) goto L_0x00d4;
    L_0x00ce:
        if (r4 != 0) goto L_0x00d1;
    L_0x00d0:
        r0 = r1;
    L_0x00d1:
        r4 = r0;
        r0 = r3;
        goto L_0x0095;
    L_0x00d4:
        if (r4 == 0) goto L_0x00fc;
    L_0x00d6:
        r4 = new java.lang.String;
        r5 = r1 - r0;
        r4.<init>(r7, r0, r5);
        r6.add(r4);
        r4 = r0;
        r0 = r2;
        goto L_0x0095;
    L_0x00e3:
        if (r4 == 0) goto L_0x00ee;
    L_0x00e5:
        r2 = new java.lang.String;
        r1 = r1 - r0;
        r2.<init>(r7, r0, r1);
        r6.add(r2);
    L_0x00ee:
        r0 = r6.size();
        r0 = new java.lang.String[r0];
        r0 = r6.toArray(r0);
        r0 = (java.lang.String[]) r0;
        goto L_0x0005;
    L_0x00fc:
        r12 = r4;
        r4 = r0;
        r0 = r12;
        goto L_0x0095;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzgu.zzb(java.lang.String, boolean):java.lang.String[]");
    }

    public static int zzz(String str) {
        byte[] bytes;
        try {
            bytes = str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            bytes = str.getBytes();
        }
        return MurmurHash3.murmurhash3_x86_32(bytes, 0, bytes.length, 0);
    }
}
