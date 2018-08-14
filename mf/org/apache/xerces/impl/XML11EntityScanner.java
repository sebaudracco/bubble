package mf.org.apache.xerces.impl;

import java.io.IOException;
import mf.org.apache.xerces.impl.XMLEntityManager.ScannedEntity;
import mf.org.apache.xerces.util.XML11Char;
import mf.org.apache.xerces.util.XMLChar;
import mf.org.apache.xerces.util.XMLStringBuffer;
import mf.org.apache.xerces.xni.XMLString;

public class XML11EntityScanner extends XMLEntityScanner {
    public int peekChar() throws IOException {
        if (this.fCurrentEntity.position == this.fCurrentEntity.count) {
            load(0, true);
        }
        int c = this.fCurrentEntity.ch[this.fCurrentEntity.position];
        if (!this.fCurrentEntity.isExternal()) {
            return c;
        }
        if (c == 13 || c == 133 || c == 8232) {
            return 10;
        }
        return c;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int scanChar() throws java.io.IOException {
        /*
        r12 = this;
        r11 = 133; // 0x85 float:1.86E-43 double:6.57E-322;
        r10 = 13;
        r9 = 10;
        r8 = 1;
        r7 = 0;
        r3 = r12.fCurrentEntity;
        r3 = r3.position;
        r4 = r12.fCurrentEntity;
        r4 = r4.count;
        if (r3 != r4) goto L_0x0015;
    L_0x0012:
        r12.load(r7, r8);
    L_0x0015:
        r3 = r12.fCurrentEntity;
        r3 = r3.ch;
        r4 = r12.fCurrentEntity;
        r5 = r4.position;
        r6 = r5 + 1;
        r4.position = r6;
        r0 = r3[r5];
        r2 = 0;
        if (r0 == r9) goto L_0x0036;
    L_0x0026:
        if (r0 == r10) goto L_0x002e;
    L_0x0028:
        if (r0 == r11) goto L_0x002e;
    L_0x002a:
        r3 = 8232; // 0x2028 float:1.1535E-41 double:4.067E-320;
        if (r0 != r3) goto L_0x0076;
    L_0x002e:
        r3 = r12.fCurrentEntity;
        r2 = r3.isExternal();
        if (r2 == 0) goto L_0x0076;
    L_0x0036:
        r3 = r12.fCurrentEntity;
        r4 = r3.lineNumber;
        r4 = r4 + 1;
        r3.lineNumber = r4;
        r3 = r12.fCurrentEntity;
        r3.columnNumber = r8;
        r3 = r12.fCurrentEntity;
        r3 = r3.position;
        r4 = r12.fCurrentEntity;
        r4 = r4.count;
        if (r3 != r4) goto L_0x0056;
    L_0x004c:
        r3 = r12.fCurrentEntity;
        r3 = r3.ch;
        r4 = (char) r0;
        r3[r7] = r4;
        r12.load(r8, r7);
    L_0x0056:
        if (r0 != r10) goto L_0x0074;
    L_0x0058:
        if (r2 == 0) goto L_0x0074;
    L_0x005a:
        r3 = r12.fCurrentEntity;
        r3 = r3.ch;
        r4 = r12.fCurrentEntity;
        r5 = r4.position;
        r6 = r5 + 1;
        r4.position = r6;
        r1 = r3[r5];
        if (r1 == r9) goto L_0x0074;
    L_0x006a:
        if (r1 == r11) goto L_0x0074;
    L_0x006c:
        r3 = r12.fCurrentEntity;
        r4 = r3.position;
        r4 = r4 + -1;
        r3.position = r4;
    L_0x0074:
        r0 = 10;
    L_0x0076:
        r3 = r12.fCurrentEntity;
        r4 = r3.columnNumber;
        r4 = r4 + 1;
        r3.columnNumber = r4;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: mf.org.apache.xerces.impl.XML11EntityScanner.scanChar():int");
    }

    public String scanNmtoken() throws IOException {
        ScannedEntity scannedEntity;
        int length;
        if (this.fCurrentEntity.position == this.fCurrentEntity.count) {
            load(0, true);
        }
        int offset = this.fCurrentEntity.position;
        while (true) {
            char ch = this.fCurrentEntity.ch[this.fCurrentEntity.position];
            int i;
            char[] tmp;
            if (!XML11Char.isXML11Name(ch)) {
                if (!XML11Char.isXML11NameHighSurrogate(ch)) {
                    break;
                }
                scannedEntity = this.fCurrentEntity;
                i = scannedEntity.position + 1;
                scannedEntity.position = i;
                if (i == this.fCurrentEntity.count) {
                    length = this.fCurrentEntity.position - offset;
                    if (length == this.fCurrentEntity.ch.length) {
                        tmp = new char[(this.fCurrentEntity.ch.length << 1)];
                        System.arraycopy(this.fCurrentEntity.ch, offset, tmp, 0, length);
                        this.fCurrentEntity.ch = tmp;
                    } else {
                        System.arraycopy(this.fCurrentEntity.ch, offset, this.fCurrentEntity.ch, 0, length);
                    }
                    offset = 0;
                    if (load(length, false)) {
                        break;
                    }
                }
                char ch2 = this.fCurrentEntity.ch[this.fCurrentEntity.position];
                if (XMLChar.isLowSurrogate(ch2) && XML11Char.isXML11Name(XMLChar.supplemental(ch, ch2))) {
                    scannedEntity = this.fCurrentEntity;
                    i = scannedEntity.position + 1;
                    scannedEntity.position = i;
                    if (i == this.fCurrentEntity.count) {
                        length = this.fCurrentEntity.position - offset;
                        if (length == this.fCurrentEntity.ch.length) {
                            tmp = new char[(this.fCurrentEntity.ch.length << 1)];
                            System.arraycopy(this.fCurrentEntity.ch, offset, tmp, 0, length);
                            this.fCurrentEntity.ch = tmp;
                        } else {
                            System.arraycopy(this.fCurrentEntity.ch, offset, this.fCurrentEntity.ch, 0, length);
                        }
                        offset = 0;
                        if (load(length, false)) {
                            break;
                        }
                    } else {
                        continue;
                    }
                } else {
                    scannedEntity = this.fCurrentEntity;
                    scannedEntity.position--;
                }
            } else {
                scannedEntity = this.fCurrentEntity;
                i = scannedEntity.position + 1;
                scannedEntity.position = i;
                if (i == this.fCurrentEntity.count) {
                    length = this.fCurrentEntity.position - offset;
                    if (length == this.fCurrentEntity.ch.length) {
                        tmp = new char[(this.fCurrentEntity.ch.length << 1)];
                        System.arraycopy(this.fCurrentEntity.ch, offset, tmp, 0, length);
                        this.fCurrentEntity.ch = tmp;
                    } else {
                        System.arraycopy(this.fCurrentEntity.ch, offset, this.fCurrentEntity.ch, 0, length);
                    }
                    offset = 0;
                    if (load(length, false)) {
                        break;
                    }
                } else {
                    continue;
                }
            }
        }
        scannedEntity = this.fCurrentEntity;
        scannedEntity.startPosition--;
        scannedEntity = this.fCurrentEntity;
        scannedEntity.position--;
        length = this.fCurrentEntity.position - offset;
        scannedEntity = this.fCurrentEntity;
        scannedEntity.columnNumber += length;
        if (length > 0) {
            return this.fSymbolTable.addSymbol(this.fCurrentEntity.ch, offset, length);
        }
        return null;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String scanName() throws java.io.IOException {
        /*
        r11 = this;
        r10 = 2;
        r4 = 0;
        r9 = 1;
        r8 = 0;
        r6 = r11.fCurrentEntity;
        r6 = r6.position;
        r7 = r11.fCurrentEntity;
        r7 = r7.count;
        if (r6 != r7) goto L_0x0011;
    L_0x000e:
        r11.load(r8, r9);
    L_0x0011:
        r6 = r11.fCurrentEntity;
        r3 = r6.position;
        r6 = r11.fCurrentEntity;
        r6 = r6.ch;
        r0 = r6[r3];
        r6 = mf.org.apache.xerces.util.XML11Char.isXML11NameStart(r0);
        if (r6 == 0) goto L_0x004f;
    L_0x0021:
        r6 = r11.fCurrentEntity;
        r7 = r6.position;
        r7 = r7 + 1;
        r6.position = r7;
        r6 = r11.fCurrentEntity;
        r6 = r6.count;
        if (r7 != r6) goto L_0x00d9;
    L_0x002f:
        r6 = r11.fCurrentEntity;
        r6 = r6.ch;
        r6[r8] = r0;
        r3 = 0;
        r6 = r11.load(r9, r8);
        if (r6 == 0) goto L_0x00d9;
    L_0x003c:
        r6 = r11.fCurrentEntity;
        r7 = r6.columnNumber;
        r7 = r7 + 1;
        r6.columnNumber = r7;
        r6 = r11.fSymbolTable;
        r7 = r11.fCurrentEntity;
        r7 = r7.ch;
        r4 = r6.addSymbol(r7, r8, r9);
    L_0x004e:
        return r4;
    L_0x004f:
        r6 = mf.org.apache.xerces.util.XML11Char.isXML11NameHighSurrogate(r0);
        if (r6 == 0) goto L_0x004e;
    L_0x0055:
        r6 = r11.fCurrentEntity;
        r7 = r6.position;
        r7 = r7 + 1;
        r6.position = r7;
        r6 = r11.fCurrentEntity;
        r6 = r6.count;
        if (r7 != r6) goto L_0x0081;
    L_0x0063:
        r6 = r11.fCurrentEntity;
        r6 = r6.ch;
        r6[r8] = r0;
        r3 = 0;
        r6 = r11.load(r9, r8);
        if (r6 == 0) goto L_0x0081;
    L_0x0070:
        r6 = r11.fCurrentEntity;
        r7 = r6.position;
        r7 = r7 + -1;
        r6.position = r7;
        r6 = r11.fCurrentEntity;
        r7 = r6.startPosition;
        r7 = r7 + -1;
        r6.startPosition = r7;
        goto L_0x004e;
    L_0x0081:
        r6 = r11.fCurrentEntity;
        r6 = r6.ch;
        r7 = r11.fCurrentEntity;
        r7 = r7.position;
        r1 = r6[r7];
        r6 = mf.org.apache.xerces.util.XMLChar.isLowSurrogate(r1);
        if (r6 == 0) goto L_0x009b;
    L_0x0091:
        r6 = mf.org.apache.xerces.util.XMLChar.supplemental(r0, r1);
        r6 = mf.org.apache.xerces.util.XML11Char.isXML11NameStart(r6);
        if (r6 != 0) goto L_0x00a4;
    L_0x009b:
        r6 = r11.fCurrentEntity;
        r7 = r6.position;
        r7 = r7 + -1;
        r6.position = r7;
        goto L_0x004e;
    L_0x00a4:
        r6 = r11.fCurrentEntity;
        r7 = r6.position;
        r7 = r7 + 1;
        r6.position = r7;
        r6 = r11.fCurrentEntity;
        r6 = r6.count;
        if (r7 != r6) goto L_0x00d9;
    L_0x00b2:
        r6 = r11.fCurrentEntity;
        r6 = r6.ch;
        r6[r8] = r0;
        r6 = r11.fCurrentEntity;
        r6 = r6.ch;
        r6[r9] = r1;
        r3 = 0;
        r6 = r11.load(r10, r8);
        if (r6 == 0) goto L_0x00d9;
    L_0x00c5:
        r6 = r11.fCurrentEntity;
        r7 = r6.columnNumber;
        r7 = r7 + 2;
        r6.columnNumber = r7;
        r6 = r11.fSymbolTable;
        r7 = r11.fCurrentEntity;
        r7 = r7.ch;
        r4 = r6.addSymbol(r7, r8, r10);
        goto L_0x004e;
    L_0x00d9:
        r6 = r11.fCurrentEntity;
        r6 = r6.ch;
        r7 = r11.fCurrentEntity;
        r7 = r7.position;
        r0 = r6[r7];
        r6 = mf.org.apache.xerces.util.XML11Char.isXML11Name(r0);
        if (r6 == 0) goto L_0x0147;
    L_0x00e9:
        r6 = r11.fCurrentEntity;
        r7 = r6.position;
        r7 = r7 + 1;
        r6.position = r7;
        r6 = r11.fCurrentEntity;
        r6 = r6.count;
        if (r7 != r6) goto L_0x00d9;
    L_0x00f7:
        r6 = r11.fCurrentEntity;
        r6 = r6.position;
        r2 = r6 - r3;
        r6 = r11.fCurrentEntity;
        r6 = r6.ch;
        r6 = r6.length;
        if (r2 != r6) goto L_0x013b;
    L_0x0104:
        r6 = r11.fCurrentEntity;
        r6 = r6.ch;
        r6 = r6.length;
        r6 = r6 << 1;
        r5 = new char[r6];
        r6 = r11.fCurrentEntity;
        r6 = r6.ch;
        java.lang.System.arraycopy(r6, r3, r5, r8, r2);
        r6 = r11.fCurrentEntity;
        r6.ch = r5;
    L_0x0118:
        r3 = 0;
        r6 = r11.load(r2, r8);
        if (r6 == 0) goto L_0x00d9;
    L_0x011f:
        r6 = r11.fCurrentEntity;
        r6 = r6.position;
        r2 = r6 - r3;
        r6 = r11.fCurrentEntity;
        r7 = r6.columnNumber;
        r7 = r7 + r2;
        r6.columnNumber = r7;
        r4 = 0;
        if (r2 <= 0) goto L_0x004e;
    L_0x012f:
        r6 = r11.fSymbolTable;
        r7 = r11.fCurrentEntity;
        r7 = r7.ch;
        r4 = r6.addSymbol(r7, r3, r2);
        goto L_0x004e;
    L_0x013b:
        r6 = r11.fCurrentEntity;
        r6 = r6.ch;
        r7 = r11.fCurrentEntity;
        r7 = r7.ch;
        java.lang.System.arraycopy(r6, r3, r7, r8, r2);
        goto L_0x0118;
    L_0x0147:
        r6 = mf.org.apache.xerces.util.XML11Char.isXML11NameHighSurrogate(r0);
        if (r6 == 0) goto L_0x011f;
    L_0x014d:
        r6 = r11.fCurrentEntity;
        r7 = r6.position;
        r7 = r7 + 1;
        r6.position = r7;
        r6 = r11.fCurrentEntity;
        r6 = r6.count;
        if (r7 != r6) goto L_0x01a0;
    L_0x015b:
        r6 = r11.fCurrentEntity;
        r6 = r6.position;
        r2 = r6 - r3;
        r6 = r11.fCurrentEntity;
        r6 = r6.ch;
        r6 = r6.length;
        if (r2 != r6) goto L_0x0194;
    L_0x0168:
        r6 = r11.fCurrentEntity;
        r6 = r6.ch;
        r6 = r6.length;
        r6 = r6 << 1;
        r5 = new char[r6];
        r6 = r11.fCurrentEntity;
        r6 = r6.ch;
        java.lang.System.arraycopy(r6, r3, r5, r8, r2);
        r6 = r11.fCurrentEntity;
        r6.ch = r5;
    L_0x017c:
        r3 = 0;
        r6 = r11.load(r2, r8);
        if (r6 == 0) goto L_0x01a0;
    L_0x0183:
        r6 = r11.fCurrentEntity;
        r7 = r6.position;
        r7 = r7 + -1;
        r6.position = r7;
        r6 = r11.fCurrentEntity;
        r7 = r6.startPosition;
        r7 = r7 + -1;
        r6.startPosition = r7;
        goto L_0x011f;
    L_0x0194:
        r6 = r11.fCurrentEntity;
        r6 = r6.ch;
        r7 = r11.fCurrentEntity;
        r7 = r7.ch;
        java.lang.System.arraycopy(r6, r3, r7, r8, r2);
        goto L_0x017c;
    L_0x01a0:
        r6 = r11.fCurrentEntity;
        r6 = r6.ch;
        r7 = r11.fCurrentEntity;
        r7 = r7.position;
        r1 = r6[r7];
        r6 = mf.org.apache.xerces.util.XMLChar.isLowSurrogate(r1);
        if (r6 == 0) goto L_0x01ba;
    L_0x01b0:
        r6 = mf.org.apache.xerces.util.XMLChar.supplemental(r0, r1);
        r6 = mf.org.apache.xerces.util.XML11Char.isXML11Name(r6);
        if (r6 != 0) goto L_0x01c4;
    L_0x01ba:
        r6 = r11.fCurrentEntity;
        r7 = r6.position;
        r7 = r7 + -1;
        r6.position = r7;
        goto L_0x011f;
    L_0x01c4:
        r6 = r11.fCurrentEntity;
        r7 = r6.position;
        r7 = r7 + 1;
        r6.position = r7;
        r6 = r11.fCurrentEntity;
        r6 = r6.count;
        if (r7 != r6) goto L_0x00d9;
    L_0x01d2:
        r6 = r11.fCurrentEntity;
        r6 = r6.position;
        r2 = r6 - r3;
        r6 = r11.fCurrentEntity;
        r6 = r6.ch;
        r6 = r6.length;
        if (r2 != r6) goto L_0x01fc;
    L_0x01df:
        r6 = r11.fCurrentEntity;
        r6 = r6.ch;
        r6 = r6.length;
        r6 = r6 << 1;
        r5 = new char[r6];
        r6 = r11.fCurrentEntity;
        r6 = r6.ch;
        java.lang.System.arraycopy(r6, r3, r5, r8, r2);
        r6 = r11.fCurrentEntity;
        r6.ch = r5;
    L_0x01f3:
        r3 = 0;
        r6 = r11.load(r2, r8);
        if (r6 == 0) goto L_0x00d9;
    L_0x01fa:
        goto L_0x011f;
    L_0x01fc:
        r6 = r11.fCurrentEntity;
        r6 = r6.ch;
        r7 = r11.fCurrentEntity;
        r7 = r7.ch;
        java.lang.System.arraycopy(r6, r3, r7, r8, r2);
        goto L_0x01f3;
        */
        throw new UnsupportedOperationException("Method not decompiled: mf.org.apache.xerces.impl.XML11EntityScanner.scanName():java.lang.String");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String scanNCName() throws java.io.IOException {
        /*
        r11 = this;
        r10 = 2;
        r4 = 0;
        r9 = 1;
        r8 = 0;
        r6 = r11.fCurrentEntity;
        r6 = r6.position;
        r7 = r11.fCurrentEntity;
        r7 = r7.count;
        if (r6 != r7) goto L_0x0011;
    L_0x000e:
        r11.load(r8, r9);
    L_0x0011:
        r6 = r11.fCurrentEntity;
        r3 = r6.position;
        r6 = r11.fCurrentEntity;
        r6 = r6.ch;
        r0 = r6[r3];
        r6 = mf.org.apache.xerces.util.XML11Char.isXML11NCNameStart(r0);
        if (r6 == 0) goto L_0x004f;
    L_0x0021:
        r6 = r11.fCurrentEntity;
        r7 = r6.position;
        r7 = r7 + 1;
        r6.position = r7;
        r6 = r11.fCurrentEntity;
        r6 = r6.count;
        if (r7 != r6) goto L_0x00d9;
    L_0x002f:
        r6 = r11.fCurrentEntity;
        r6 = r6.ch;
        r6[r8] = r0;
        r3 = 0;
        r6 = r11.load(r9, r8);
        if (r6 == 0) goto L_0x00d9;
    L_0x003c:
        r6 = r11.fCurrentEntity;
        r7 = r6.columnNumber;
        r7 = r7 + 1;
        r6.columnNumber = r7;
        r6 = r11.fSymbolTable;
        r7 = r11.fCurrentEntity;
        r7 = r7.ch;
        r4 = r6.addSymbol(r7, r8, r9);
    L_0x004e:
        return r4;
    L_0x004f:
        r6 = mf.org.apache.xerces.util.XML11Char.isXML11NameHighSurrogate(r0);
        if (r6 == 0) goto L_0x004e;
    L_0x0055:
        r6 = r11.fCurrentEntity;
        r7 = r6.position;
        r7 = r7 + 1;
        r6.position = r7;
        r6 = r11.fCurrentEntity;
        r6 = r6.count;
        if (r7 != r6) goto L_0x0081;
    L_0x0063:
        r6 = r11.fCurrentEntity;
        r6 = r6.ch;
        r6[r8] = r0;
        r3 = 0;
        r6 = r11.load(r9, r8);
        if (r6 == 0) goto L_0x0081;
    L_0x0070:
        r6 = r11.fCurrentEntity;
        r7 = r6.position;
        r7 = r7 + -1;
        r6.position = r7;
        r6 = r11.fCurrentEntity;
        r7 = r6.startPosition;
        r7 = r7 + -1;
        r6.startPosition = r7;
        goto L_0x004e;
    L_0x0081:
        r6 = r11.fCurrentEntity;
        r6 = r6.ch;
        r7 = r11.fCurrentEntity;
        r7 = r7.position;
        r1 = r6[r7];
        r6 = mf.org.apache.xerces.util.XMLChar.isLowSurrogate(r1);
        if (r6 == 0) goto L_0x009b;
    L_0x0091:
        r6 = mf.org.apache.xerces.util.XMLChar.supplemental(r0, r1);
        r6 = mf.org.apache.xerces.util.XML11Char.isXML11NCNameStart(r6);
        if (r6 != 0) goto L_0x00a4;
    L_0x009b:
        r6 = r11.fCurrentEntity;
        r7 = r6.position;
        r7 = r7 + -1;
        r6.position = r7;
        goto L_0x004e;
    L_0x00a4:
        r6 = r11.fCurrentEntity;
        r7 = r6.position;
        r7 = r7 + 1;
        r6.position = r7;
        r6 = r11.fCurrentEntity;
        r6 = r6.count;
        if (r7 != r6) goto L_0x00d9;
    L_0x00b2:
        r6 = r11.fCurrentEntity;
        r6 = r6.ch;
        r6[r8] = r0;
        r6 = r11.fCurrentEntity;
        r6 = r6.ch;
        r6[r9] = r1;
        r3 = 0;
        r6 = r11.load(r10, r8);
        if (r6 == 0) goto L_0x00d9;
    L_0x00c5:
        r6 = r11.fCurrentEntity;
        r7 = r6.columnNumber;
        r7 = r7 + 2;
        r6.columnNumber = r7;
        r6 = r11.fSymbolTable;
        r7 = r11.fCurrentEntity;
        r7 = r7.ch;
        r4 = r6.addSymbol(r7, r8, r10);
        goto L_0x004e;
    L_0x00d9:
        r6 = r11.fCurrentEntity;
        r6 = r6.ch;
        r7 = r11.fCurrentEntity;
        r7 = r7.position;
        r0 = r6[r7];
        r6 = mf.org.apache.xerces.util.XML11Char.isXML11NCName(r0);
        if (r6 == 0) goto L_0x0147;
    L_0x00e9:
        r6 = r11.fCurrentEntity;
        r7 = r6.position;
        r7 = r7 + 1;
        r6.position = r7;
        r6 = r11.fCurrentEntity;
        r6 = r6.count;
        if (r7 != r6) goto L_0x00d9;
    L_0x00f7:
        r6 = r11.fCurrentEntity;
        r6 = r6.position;
        r2 = r6 - r3;
        r6 = r11.fCurrentEntity;
        r6 = r6.ch;
        r6 = r6.length;
        if (r2 != r6) goto L_0x013b;
    L_0x0104:
        r6 = r11.fCurrentEntity;
        r6 = r6.ch;
        r6 = r6.length;
        r6 = r6 << 1;
        r5 = new char[r6];
        r6 = r11.fCurrentEntity;
        r6 = r6.ch;
        java.lang.System.arraycopy(r6, r3, r5, r8, r2);
        r6 = r11.fCurrentEntity;
        r6.ch = r5;
    L_0x0118:
        r3 = 0;
        r6 = r11.load(r2, r8);
        if (r6 == 0) goto L_0x00d9;
    L_0x011f:
        r6 = r11.fCurrentEntity;
        r6 = r6.position;
        r2 = r6 - r3;
        r6 = r11.fCurrentEntity;
        r7 = r6.columnNumber;
        r7 = r7 + r2;
        r6.columnNumber = r7;
        r4 = 0;
        if (r2 <= 0) goto L_0x004e;
    L_0x012f:
        r6 = r11.fSymbolTable;
        r7 = r11.fCurrentEntity;
        r7 = r7.ch;
        r4 = r6.addSymbol(r7, r3, r2);
        goto L_0x004e;
    L_0x013b:
        r6 = r11.fCurrentEntity;
        r6 = r6.ch;
        r7 = r11.fCurrentEntity;
        r7 = r7.ch;
        java.lang.System.arraycopy(r6, r3, r7, r8, r2);
        goto L_0x0118;
    L_0x0147:
        r6 = mf.org.apache.xerces.util.XML11Char.isXML11NameHighSurrogate(r0);
        if (r6 == 0) goto L_0x011f;
    L_0x014d:
        r6 = r11.fCurrentEntity;
        r7 = r6.position;
        r7 = r7 + 1;
        r6.position = r7;
        r6 = r11.fCurrentEntity;
        r6 = r6.count;
        if (r7 != r6) goto L_0x01a0;
    L_0x015b:
        r6 = r11.fCurrentEntity;
        r6 = r6.position;
        r2 = r6 - r3;
        r6 = r11.fCurrentEntity;
        r6 = r6.ch;
        r6 = r6.length;
        if (r2 != r6) goto L_0x0194;
    L_0x0168:
        r6 = r11.fCurrentEntity;
        r6 = r6.ch;
        r6 = r6.length;
        r6 = r6 << 1;
        r5 = new char[r6];
        r6 = r11.fCurrentEntity;
        r6 = r6.ch;
        java.lang.System.arraycopy(r6, r3, r5, r8, r2);
        r6 = r11.fCurrentEntity;
        r6.ch = r5;
    L_0x017c:
        r3 = 0;
        r6 = r11.load(r2, r8);
        if (r6 == 0) goto L_0x01a0;
    L_0x0183:
        r6 = r11.fCurrentEntity;
        r7 = r6.startPosition;
        r7 = r7 + -1;
        r6.startPosition = r7;
        r6 = r11.fCurrentEntity;
        r7 = r6.position;
        r7 = r7 + -1;
        r6.position = r7;
        goto L_0x011f;
    L_0x0194:
        r6 = r11.fCurrentEntity;
        r6 = r6.ch;
        r7 = r11.fCurrentEntity;
        r7 = r7.ch;
        java.lang.System.arraycopy(r6, r3, r7, r8, r2);
        goto L_0x017c;
    L_0x01a0:
        r6 = r11.fCurrentEntity;
        r6 = r6.ch;
        r7 = r11.fCurrentEntity;
        r7 = r7.position;
        r1 = r6[r7];
        r6 = mf.org.apache.xerces.util.XMLChar.isLowSurrogate(r1);
        if (r6 == 0) goto L_0x01ba;
    L_0x01b0:
        r6 = mf.org.apache.xerces.util.XMLChar.supplemental(r0, r1);
        r6 = mf.org.apache.xerces.util.XML11Char.isXML11NCName(r6);
        if (r6 != 0) goto L_0x01c4;
    L_0x01ba:
        r6 = r11.fCurrentEntity;
        r7 = r6.position;
        r7 = r7 + -1;
        r6.position = r7;
        goto L_0x011f;
    L_0x01c4:
        r6 = r11.fCurrentEntity;
        r7 = r6.position;
        r7 = r7 + 1;
        r6.position = r7;
        r6 = r11.fCurrentEntity;
        r6 = r6.count;
        if (r7 != r6) goto L_0x00d9;
    L_0x01d2:
        r6 = r11.fCurrentEntity;
        r6 = r6.position;
        r2 = r6 - r3;
        r6 = r11.fCurrentEntity;
        r6 = r6.ch;
        r6 = r6.length;
        if (r2 != r6) goto L_0x01fc;
    L_0x01df:
        r6 = r11.fCurrentEntity;
        r6 = r6.ch;
        r6 = r6.length;
        r6 = r6 << 1;
        r5 = new char[r6];
        r6 = r11.fCurrentEntity;
        r6 = r6.ch;
        java.lang.System.arraycopy(r6, r3, r5, r8, r2);
        r6 = r11.fCurrentEntity;
        r6.ch = r5;
    L_0x01f3:
        r3 = 0;
        r6 = r11.load(r2, r8);
        if (r6 == 0) goto L_0x00d9;
    L_0x01fa:
        goto L_0x011f;
    L_0x01fc:
        r6 = r11.fCurrentEntity;
        r6 = r6.ch;
        r7 = r11.fCurrentEntity;
        r7 = r7.ch;
        java.lang.System.arraycopy(r6, r3, r7, r8, r2);
        goto L_0x01f3;
        */
        throw new UnsupportedOperationException("Method not decompiled: mf.org.apache.xerces.impl.XML11EntityScanner.scanNCName():java.lang.String");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean scanQName(mf.org.apache.xerces.xni.QName r23) throws java.io.IOException {
        /*
        r22 = this;
        r0 = r22;
        r0 = r0.fCurrentEntity;
        r17 = r0;
        r0 = r17;
        r0 = r0.position;
        r17 = r0;
        r0 = r22;
        r0 = r0.fCurrentEntity;
        r18 = r0;
        r0 = r18;
        r0 = r0.count;
        r18 = r0;
        r0 = r17;
        r1 = r18;
        if (r0 != r1) goto L_0x002b;
    L_0x001e:
        r17 = 0;
        r18 = 1;
        r0 = r22;
        r1 = r17;
        r2 = r18;
        r0.load(r1, r2);
    L_0x002b:
        r0 = r22;
        r0 = r0.fCurrentEntity;
        r17 = r0;
        r0 = r17;
        r10 = r0.position;
        r0 = r22;
        r0 = r0.fCurrentEntity;
        r17 = r0;
        r0 = r17;
        r0 = r0.ch;
        r17 = r0;
        r3 = r17[r10];
        r17 = mf.org.apache.xerces.util.XML11Char.isXML11NCNameStart(r3);
        if (r17 == 0) goto L_0x00ce;
    L_0x0049:
        r0 = r22;
        r0 = r0.fCurrentEntity;
        r17 = r0;
        r0 = r17;
        r0 = r0.position;
        r18 = r0;
        r18 = r18 + 1;
        r0 = r18;
        r1 = r17;
        r1.position = r0;
        r0 = r22;
        r0 = r0.fCurrentEntity;
        r17 = r0;
        r0 = r17;
        r0 = r0.count;
        r17 = r0;
        r0 = r18;
        r1 = r17;
        if (r0 != r1) goto L_0x0222;
    L_0x006f:
        r0 = r22;
        r0 = r0.fCurrentEntity;
        r17 = r0;
        r0 = r17;
        r0 = r0.ch;
        r17 = r0;
        r18 = 0;
        r17[r18] = r3;
        r10 = 0;
        r17 = 1;
        r18 = 0;
        r0 = r22;
        r1 = r17;
        r2 = r18;
        r17 = r0.load(r1, r2);
        if (r17 == 0) goto L_0x0222;
    L_0x0090:
        r0 = r22;
        r0 = r0.fCurrentEntity;
        r17 = r0;
        r0 = r17;
        r0 = r0.columnNumber;
        r18 = r0;
        r18 = r18 + 1;
        r0 = r18;
        r1 = r17;
        r1.columnNumber = r0;
        r0 = r22;
        r0 = r0.fSymbolTable;
        r17 = r0;
        r0 = r22;
        r0 = r0.fCurrentEntity;
        r18 = r0;
        r0 = r18;
        r0 = r0.ch;
        r18 = r0;
        r19 = 0;
        r20 = 1;
        r9 = r17.addSymbol(r18, r19, r20);
        r17 = 0;
        r18 = 0;
        r0 = r23;
        r1 = r17;
        r2 = r18;
        r0.setValues(r1, r9, r9, r2);
        r17 = 1;
    L_0x00cd:
        return r17;
    L_0x00ce:
        r17 = mf.org.apache.xerces.util.XML11Char.isXML11NameHighSurrogate(r3);
        if (r17 == 0) goto L_0x021e;
    L_0x00d4:
        r0 = r22;
        r0 = r0.fCurrentEntity;
        r17 = r0;
        r0 = r17;
        r0 = r0.position;
        r18 = r0;
        r18 = r18 + 1;
        r0 = r18;
        r1 = r17;
        r1.position = r0;
        r0 = r22;
        r0 = r0.fCurrentEntity;
        r17 = r0;
        r0 = r17;
        r0 = r0.count;
        r17 = r0;
        r0 = r18;
        r1 = r17;
        if (r0 != r1) goto L_0x0146;
    L_0x00fa:
        r0 = r22;
        r0 = r0.fCurrentEntity;
        r17 = r0;
        r0 = r17;
        r0 = r0.ch;
        r17 = r0;
        r18 = 0;
        r17[r18] = r3;
        r10 = 0;
        r17 = 1;
        r18 = 0;
        r0 = r22;
        r1 = r17;
        r2 = r18;
        r17 = r0.load(r1, r2);
        if (r17 == 0) goto L_0x0146;
    L_0x011b:
        r0 = r22;
        r0 = r0.fCurrentEntity;
        r17 = r0;
        r0 = r17;
        r0 = r0.startPosition;
        r18 = r0;
        r18 = r18 + -1;
        r0 = r18;
        r1 = r17;
        r1.startPosition = r0;
        r0 = r22;
        r0 = r0.fCurrentEntity;
        r17 = r0;
        r0 = r17;
        r0 = r0.position;
        r18 = r0;
        r18 = r18 + -1;
        r0 = r18;
        r1 = r17;
        r1.position = r0;
        r17 = 0;
        goto L_0x00cd;
    L_0x0146:
        r0 = r22;
        r0 = r0.fCurrentEntity;
        r17 = r0;
        r0 = r17;
        r0 = r0.ch;
        r17 = r0;
        r0 = r22;
        r0 = r0.fCurrentEntity;
        r18 = r0;
        r0 = r18;
        r0 = r0.position;
        r18 = r0;
        r4 = r17[r18];
        r17 = mf.org.apache.xerces.util.XMLChar.isLowSurrogate(r4);
        if (r17 == 0) goto L_0x0170;
    L_0x0166:
        r17 = mf.org.apache.xerces.util.XMLChar.supplemental(r3, r4);
        r17 = mf.org.apache.xerces.util.XML11Char.isXML11NCNameStart(r17);
        if (r17 != 0) goto L_0x0188;
    L_0x0170:
        r0 = r22;
        r0 = r0.fCurrentEntity;
        r17 = r0;
        r0 = r17;
        r0 = r0.position;
        r18 = r0;
        r18 = r18 + -1;
        r0 = r18;
        r1 = r17;
        r1.position = r0;
        r17 = 0;
        goto L_0x00cd;
    L_0x0188:
        r0 = r22;
        r0 = r0.fCurrentEntity;
        r17 = r0;
        r0 = r17;
        r0 = r0.position;
        r18 = r0;
        r18 = r18 + 1;
        r0 = r18;
        r1 = r17;
        r1.position = r0;
        r0 = r22;
        r0 = r0.fCurrentEntity;
        r17 = r0;
        r0 = r17;
        r0 = r0.count;
        r17 = r0;
        r0 = r18;
        r1 = r17;
        if (r0 != r1) goto L_0x0222;
    L_0x01ae:
        r0 = r22;
        r0 = r0.fCurrentEntity;
        r17 = r0;
        r0 = r17;
        r0 = r0.ch;
        r17 = r0;
        r18 = 0;
        r17[r18] = r3;
        r0 = r22;
        r0 = r0.fCurrentEntity;
        r17 = r0;
        r0 = r17;
        r0 = r0.ch;
        r17 = r0;
        r18 = 1;
        r17[r18] = r4;
        r10 = 0;
        r17 = 2;
        r18 = 0;
        r0 = r22;
        r1 = r17;
        r2 = r18;
        r17 = r0.load(r1, r2);
        if (r17 == 0) goto L_0x0222;
    L_0x01df:
        r0 = r22;
        r0 = r0.fCurrentEntity;
        r17 = r0;
        r0 = r17;
        r0 = r0.columnNumber;
        r18 = r0;
        r18 = r18 + 2;
        r0 = r18;
        r1 = r17;
        r1.columnNumber = r0;
        r0 = r22;
        r0 = r0.fSymbolTable;
        r17 = r0;
        r0 = r22;
        r0 = r0.fCurrentEntity;
        r18 = r0;
        r0 = r18;
        r0 = r0.ch;
        r18 = r0;
        r19 = 0;
        r20 = 2;
        r9 = r17.addSymbol(r18, r19, r20);
        r17 = 0;
        r18 = 0;
        r0 = r23;
        r1 = r17;
        r2 = r18;
        r0.setValues(r1, r9, r9, r2);
        r17 = 1;
        goto L_0x00cd;
    L_0x021e:
        r17 = 0;
        goto L_0x00cd;
    L_0x0222:
        r5 = -1;
        r14 = 0;
    L_0x0224:
        r0 = r22;
        r0 = r0.fCurrentEntity;
        r17 = r0;
        r0 = r17;
        r0 = r0.ch;
        r17 = r0;
        r0 = r22;
        r0 = r0.fCurrentEntity;
        r18 = r0;
        r0 = r18;
        r0 = r0.position;
        r18 = r0;
        r3 = r17[r18];
        r17 = mf.org.apache.xerces.util.XML11Char.isXML11Name(r3);
        if (r17 == 0) goto L_0x03e9;
    L_0x0244:
        r17 = 58;
        r0 = r17;
        if (r3 != r0) goto L_0x032a;
    L_0x024a:
        r17 = -1;
        r0 = r17;
        if (r5 == r0) goto L_0x0320;
    L_0x0250:
        r0 = r22;
        r0 = r0.fCurrentEntity;
        r17 = r0;
        r0 = r17;
        r0 = r0.position;
        r17 = r0;
        r7 = r17 - r10;
        r0 = r22;
        r0 = r0.fCurrentEntity;
        r17 = r0;
        r0 = r17;
        r0 = r0.columnNumber;
        r18 = r0;
        r18 = r18 + r7;
        r0 = r18;
        r1 = r17;
        r1.columnNumber = r0;
        if (r7 <= 0) goto L_0x05da;
    L_0x0274:
        r11 = 0;
        r8 = 0;
        r0 = r22;
        r0 = r0.fSymbolTable;
        r17 = r0;
        r0 = r22;
        r0 = r0.fCurrentEntity;
        r18 = r0;
        r0 = r18;
        r0 = r0.ch;
        r18 = r0;
        r0 = r17;
        r1 = r18;
        r13 = r0.addSymbol(r1, r10, r7);
        r17 = -1;
        r0 = r17;
        if (r5 == r0) goto L_0x05d7;
    L_0x0296:
        r12 = r5 - r10;
        r0 = r22;
        r0 = r0.fSymbolTable;
        r17 = r0;
        r0 = r22;
        r0 = r0.fCurrentEntity;
        r18 = r0;
        r0 = r18;
        r0 = r0.ch;
        r18 = r0;
        r0 = r17;
        r1 = r18;
        r11 = r0.addSymbol(r1, r10, r12);
        r17 = r7 - r12;
        r6 = r17 + -1;
        r15 = r5 + 1;
        r0 = r22;
        r0 = r0.fCurrentEntity;
        r17 = r0;
        r0 = r17;
        r0 = r0.ch;
        r17 = r0;
        r17 = r17[r15];
        r17 = mf.org.apache.xerces.util.XML11Char.isXML11NCNameStart(r17);
        if (r17 != 0) goto L_0x02f5;
    L_0x02cc:
        r0 = r22;
        r0 = r0.fCurrentEntity;
        r17 = r0;
        r0 = r17;
        r0 = r0.ch;
        r17 = r0;
        r17 = r17[r15];
        r17 = mf.org.apache.xerces.util.XML11Char.isXML11NameHighSurrogate(r17);
        if (r17 == 0) goto L_0x02e2;
    L_0x02e0:
        if (r14 == 0) goto L_0x02f5;
    L_0x02e2:
        r0 = r22;
        r0 = r0.fErrorReporter;
        r17 = r0;
        r18 = "http://www.w3.org/TR/1998/REC-xml-19980210";
        r19 = "IllegalQName";
        r20 = 0;
        r21 = 2;
        r17.reportError(r18, r19, r20, r21);
    L_0x02f5:
        r0 = r22;
        r0 = r0.fSymbolTable;
        r17 = r0;
        r0 = r22;
        r0 = r0.fCurrentEntity;
        r18 = r0;
        r0 = r18;
        r0 = r0.ch;
        r18 = r0;
        r19 = r5 + 1;
        r0 = r17;
        r1 = r18;
        r2 = r19;
        r8 = r0.addSymbol(r1, r2, r6);
    L_0x0313:
        r17 = 0;
        r0 = r23;
        r1 = r17;
        r0.setValues(r11, r8, r13, r1);
        r17 = 1;
        goto L_0x00cd;
    L_0x0320:
        r0 = r22;
        r0 = r0.fCurrentEntity;
        r17 = r0;
        r0 = r17;
        r5 = r0.position;
    L_0x032a:
        r0 = r22;
        r0 = r0.fCurrentEntity;
        r17 = r0;
        r0 = r17;
        r0 = r0.position;
        r18 = r0;
        r18 = r18 + 1;
        r0 = r18;
        r1 = r17;
        r1.position = r0;
        r0 = r22;
        r0 = r0.fCurrentEntity;
        r17 = r0;
        r0 = r17;
        r0 = r0.count;
        r17 = r0;
        r0 = r18;
        r1 = r17;
        if (r0 != r1) goto L_0x0224;
    L_0x0350:
        r0 = r22;
        r0 = r0.fCurrentEntity;
        r17 = r0;
        r0 = r17;
        r0 = r0.position;
        r17 = r0;
        r7 = r17 - r10;
        r0 = r22;
        r0 = r0.fCurrentEntity;
        r17 = r0;
        r0 = r17;
        r0 = r0.ch;
        r17 = r0;
        r0 = r17;
        r0 = r0.length;
        r17 = r0;
        r0 = r17;
        if (r7 != r0) goto L_0x03c5;
    L_0x0373:
        r0 = r22;
        r0 = r0.fCurrentEntity;
        r17 = r0;
        r0 = r17;
        r0 = r0.ch;
        r17 = r0;
        r0 = r17;
        r0 = r0.length;
        r17 = r0;
        r17 = r17 << 1;
        r0 = r17;
        r0 = new char[r0];
        r16 = r0;
        r0 = r22;
        r0 = r0.fCurrentEntity;
        r17 = r0;
        r0 = r17;
        r0 = r0.ch;
        r17 = r0;
        r18 = 0;
        r0 = r17;
        r1 = r16;
        r2 = r18;
        java.lang.System.arraycopy(r0, r10, r1, r2, r7);
        r0 = r22;
        r0 = r0.fCurrentEntity;
        r17 = r0;
        r0 = r16;
        r1 = r17;
        r1.ch = r0;
    L_0x03af:
        r17 = -1;
        r0 = r17;
        if (r5 == r0) goto L_0x03b6;
    L_0x03b5:
        r5 = r5 - r10;
    L_0x03b6:
        r10 = 0;
        r17 = 0;
        r0 = r22;
        r1 = r17;
        r17 = r0.load(r7, r1);
        if (r17 == 0) goto L_0x0224;
    L_0x03c3:
        goto L_0x0250;
    L_0x03c5:
        r0 = r22;
        r0 = r0.fCurrentEntity;
        r17 = r0;
        r0 = r17;
        r0 = r0.ch;
        r17 = r0;
        r0 = r22;
        r0 = r0.fCurrentEntity;
        r18 = r0;
        r0 = r18;
        r0 = r0.ch;
        r18 = r0;
        r19 = 0;
        r0 = r17;
        r1 = r18;
        r2 = r19;
        java.lang.System.arraycopy(r0, r10, r1, r2, r7);
        goto L_0x03af;
    L_0x03e9:
        r17 = mf.org.apache.xerces.util.XML11Char.isXML11NameHighSurrogate(r3);
        if (r17 == 0) goto L_0x0250;
    L_0x03ef:
        r0 = r22;
        r0 = r0.fCurrentEntity;
        r17 = r0;
        r0 = r17;
        r0 = r0.position;
        r18 = r0;
        r18 = r18 + 1;
        r0 = r18;
        r1 = r17;
        r1.position = r0;
        r0 = r22;
        r0 = r0.fCurrentEntity;
        r17 = r0;
        r0 = r17;
        r0 = r0.count;
        r17 = r0;
        r0 = r18;
        r1 = r17;
        if (r0 != r1) goto L_0x04d7;
    L_0x0415:
        r0 = r22;
        r0 = r0.fCurrentEntity;
        r17 = r0;
        r0 = r17;
        r0 = r0.position;
        r17 = r0;
        r7 = r17 - r10;
        r0 = r22;
        r0 = r0.fCurrentEntity;
        r17 = r0;
        r0 = r17;
        r0 = r0.ch;
        r17 = r0;
        r0 = r17;
        r0 = r0.length;
        r17 = r0;
        r0 = r17;
        if (r7 != r0) goto L_0x04b3;
    L_0x0438:
        r0 = r22;
        r0 = r0.fCurrentEntity;
        r17 = r0;
        r0 = r17;
        r0 = r0.ch;
        r17 = r0;
        r0 = r17;
        r0 = r0.length;
        r17 = r0;
        r17 = r17 << 1;
        r0 = r17;
        r0 = new char[r0];
        r16 = r0;
        r0 = r22;
        r0 = r0.fCurrentEntity;
        r17 = r0;
        r0 = r17;
        r0 = r0.ch;
        r17 = r0;
        r18 = 0;
        r0 = r17;
        r1 = r16;
        r2 = r18;
        java.lang.System.arraycopy(r0, r10, r1, r2, r7);
        r0 = r22;
        r0 = r0.fCurrentEntity;
        r17 = r0;
        r0 = r16;
        r1 = r17;
        r1.ch = r0;
    L_0x0474:
        r17 = -1;
        r0 = r17;
        if (r5 == r0) goto L_0x047b;
    L_0x047a:
        r5 = r5 - r10;
    L_0x047b:
        r10 = 0;
        r17 = 0;
        r0 = r22;
        r1 = r17;
        r17 = r0.load(r7, r1);
        if (r17 == 0) goto L_0x04d7;
    L_0x0488:
        r14 = 1;
        r0 = r22;
        r0 = r0.fCurrentEntity;
        r17 = r0;
        r0 = r17;
        r0 = r0.startPosition;
        r18 = r0;
        r18 = r18 + -1;
        r0 = r18;
        r1 = r17;
        r1.startPosition = r0;
        r0 = r22;
        r0 = r0.fCurrentEntity;
        r17 = r0;
        r0 = r17;
        r0 = r0.position;
        r18 = r0;
        r18 = r18 + -1;
        r0 = r18;
        r1 = r17;
        r1.position = r0;
        goto L_0x0250;
    L_0x04b3:
        r0 = r22;
        r0 = r0.fCurrentEntity;
        r17 = r0;
        r0 = r17;
        r0 = r0.ch;
        r17 = r0;
        r0 = r22;
        r0 = r0.fCurrentEntity;
        r18 = r0;
        r0 = r18;
        r0 = r0.ch;
        r18 = r0;
        r19 = 0;
        r0 = r17;
        r1 = r18;
        r2 = r19;
        java.lang.System.arraycopy(r0, r10, r1, r2, r7);
        goto L_0x0474;
    L_0x04d7:
        r0 = r22;
        r0 = r0.fCurrentEntity;
        r17 = r0;
        r0 = r17;
        r0 = r0.ch;
        r17 = r0;
        r0 = r22;
        r0 = r0.fCurrentEntity;
        r18 = r0;
        r0 = r18;
        r0 = r0.position;
        r18 = r0;
        r4 = r17[r18];
        r17 = mf.org.apache.xerces.util.XMLChar.isLowSurrogate(r4);
        if (r17 == 0) goto L_0x0501;
    L_0x04f7:
        r17 = mf.org.apache.xerces.util.XMLChar.supplemental(r3, r4);
        r17 = mf.org.apache.xerces.util.XML11Char.isXML11Name(r17);
        if (r17 != 0) goto L_0x0518;
    L_0x0501:
        r14 = 1;
        r0 = r22;
        r0 = r0.fCurrentEntity;
        r17 = r0;
        r0 = r17;
        r0 = r0.position;
        r18 = r0;
        r18 = r18 + -1;
        r0 = r18;
        r1 = r17;
        r1.position = r0;
        goto L_0x0250;
    L_0x0518:
        r0 = r22;
        r0 = r0.fCurrentEntity;
        r17 = r0;
        r0 = r17;
        r0 = r0.position;
        r18 = r0;
        r18 = r18 + 1;
        r0 = r18;
        r1 = r17;
        r1.position = r0;
        r0 = r22;
        r0 = r0.fCurrentEntity;
        r17 = r0;
        r0 = r17;
        r0 = r0.count;
        r17 = r0;
        r0 = r18;
        r1 = r17;
        if (r0 != r1) goto L_0x0224;
    L_0x053e:
        r0 = r22;
        r0 = r0.fCurrentEntity;
        r17 = r0;
        r0 = r17;
        r0 = r0.position;
        r17 = r0;
        r7 = r17 - r10;
        r0 = r22;
        r0 = r0.fCurrentEntity;
        r17 = r0;
        r0 = r17;
        r0 = r0.ch;
        r17 = r0;
        r0 = r17;
        r0 = r0.length;
        r17 = r0;
        r0 = r17;
        if (r7 != r0) goto L_0x05b3;
    L_0x0561:
        r0 = r22;
        r0 = r0.fCurrentEntity;
        r17 = r0;
        r0 = r17;
        r0 = r0.ch;
        r17 = r0;
        r0 = r17;
        r0 = r0.length;
        r17 = r0;
        r17 = r17 << 1;
        r0 = r17;
        r0 = new char[r0];
        r16 = r0;
        r0 = r22;
        r0 = r0.fCurrentEntity;
        r17 = r0;
        r0 = r17;
        r0 = r0.ch;
        r17 = r0;
        r18 = 0;
        r0 = r17;
        r1 = r16;
        r2 = r18;
        java.lang.System.arraycopy(r0, r10, r1, r2, r7);
        r0 = r22;
        r0 = r0.fCurrentEntity;
        r17 = r0;
        r0 = r16;
        r1 = r17;
        r1.ch = r0;
    L_0x059d:
        r17 = -1;
        r0 = r17;
        if (r5 == r0) goto L_0x05a4;
    L_0x05a3:
        r5 = r5 - r10;
    L_0x05a4:
        r10 = 0;
        r17 = 0;
        r0 = r22;
        r1 = r17;
        r17 = r0.load(r7, r1);
        if (r17 == 0) goto L_0x0224;
    L_0x05b1:
        goto L_0x0250;
    L_0x05b3:
        r0 = r22;
        r0 = r0.fCurrentEntity;
        r17 = r0;
        r0 = r17;
        r0 = r0.ch;
        r17 = r0;
        r0 = r22;
        r0 = r0.fCurrentEntity;
        r18 = r0;
        r0 = r18;
        r0 = r0.ch;
        r18 = r0;
        r19 = 0;
        r0 = r17;
        r1 = r18;
        r2 = r19;
        java.lang.System.arraycopy(r0, r10, r1, r2, r7);
        goto L_0x059d;
    L_0x05d7:
        r8 = r13;
        goto L_0x0313;
    L_0x05da:
        r17 = 0;
        goto L_0x00cd;
        */
        throw new UnsupportedOperationException("Method not decompiled: mf.org.apache.xerces.impl.XML11EntityScanner.scanQName(mf.org.apache.xerces.xni.QName):boolean");
    }

    public int scanContent(XMLString content) throws IOException {
        ScannedEntity scannedEntity;
        int length;
        if (this.fCurrentEntity.position == this.fCurrentEntity.count) {
            load(0, true);
        } else if (this.fCurrentEntity.position == this.fCurrentEntity.count - 1) {
            this.fCurrentEntity.ch[0] = this.fCurrentEntity.ch[this.fCurrentEntity.count - 1];
            load(1, false);
            this.fCurrentEntity.position = 0;
            this.fCurrentEntity.startPosition = 0;
        }
        int offset = this.fCurrentEntity.position;
        int c = this.fCurrentEntity.ch[offset];
        int newlines = 0;
        boolean external = this.fCurrentEntity.isExternal();
        if (c == 10 || ((c == 13 || c == 133 || c == 8232) && external)) {
            do {
                char[] cArr = this.fCurrentEntity.ch;
                ScannedEntity scannedEntity2 = this.fCurrentEntity;
                int i = scannedEntity2.position;
                scannedEntity2.position = i + 1;
                c = cArr[i];
                if (c != 13 || !external) {
                    if (c != 10 && ((c != 133 && c != 8232) || !external)) {
                        scannedEntity = this.fCurrentEntity;
                        scannedEntity.position--;
                        break;
                    }
                    newlines++;
                    scannedEntity = this.fCurrentEntity;
                    scannedEntity.lineNumber++;
                    this.fCurrentEntity.columnNumber = 1;
                    if (this.fCurrentEntity.position == this.fCurrentEntity.count) {
                        offset = 0;
                        scannedEntity = this.fCurrentEntity;
                        scannedEntity.baseCharOffset += this.fCurrentEntity.position - this.fCurrentEntity.startPosition;
                        this.fCurrentEntity.position = newlines;
                        this.fCurrentEntity.startPosition = newlines;
                        if (load(newlines, false)) {
                            break;
                        }
                    }
                }
                newlines++;
                scannedEntity = this.fCurrentEntity;
                scannedEntity.lineNumber++;
                this.fCurrentEntity.columnNumber = 1;
                if (this.fCurrentEntity.position == this.fCurrentEntity.count) {
                    offset = 0;
                    scannedEntity = this.fCurrentEntity;
                    scannedEntity.baseCharOffset += this.fCurrentEntity.position - this.fCurrentEntity.startPosition;
                    this.fCurrentEntity.position = newlines;
                    this.fCurrentEntity.startPosition = newlines;
                    if (load(newlines, false)) {
                        break;
                    }
                }
                int cc = this.fCurrentEntity.ch[this.fCurrentEntity.position];
                if (cc == 10 || cc == 133) {
                    scannedEntity = this.fCurrentEntity;
                    scannedEntity.position++;
                    offset++;
                } else {
                    newlines++;
                }
            } while (this.fCurrentEntity.position < this.fCurrentEntity.count - 1);
            for (int i2 = offset; i2 < this.fCurrentEntity.position; i2++) {
                this.fCurrentEntity.ch[i2] = '\n';
            }
            length = this.fCurrentEntity.position - offset;
            if (this.fCurrentEntity.position == this.fCurrentEntity.count - 1) {
                content.setValues(this.fCurrentEntity.ch, offset, length);
                return -1;
            }
        }
        if (!external) {
            while (this.fCurrentEntity.position < this.fCurrentEntity.count) {
                cArr = this.fCurrentEntity.ch;
                scannedEntity2 = this.fCurrentEntity;
                i = scannedEntity2.position;
                scannedEntity2.position = i + 1;
                if (!XML11Char.isXML11InternalEntityContent(cArr[i])) {
                    scannedEntity = this.fCurrentEntity;
                    scannedEntity.position--;
                    break;
                }
            }
        }
        while (this.fCurrentEntity.position < this.fCurrentEntity.count) {
            cArr = this.fCurrentEntity.ch;
            scannedEntity2 = this.fCurrentEntity;
            i = scannedEntity2.position;
            scannedEntity2.position = i + 1;
            c = cArr[i];
            if (XML11Char.isXML11Content(c) && c != 133) {
                if (c == 8232) {
                    scannedEntity = this.fCurrentEntity;
                    scannedEntity.position--;
                    break;
                }
            }
            scannedEntity = this.fCurrentEntity;
            scannedEntity.position--;
            break;
        }
        length = this.fCurrentEntity.position - offset;
        scannedEntity = this.fCurrentEntity;
        scannedEntity.columnNumber += length - newlines;
        content.setValues(this.fCurrentEntity.ch, offset, length);
        if (this.fCurrentEntity.position != this.fCurrentEntity.count) {
            c = this.fCurrentEntity.ch[this.fCurrentEntity.position];
            if ((c == 13 || c == 133 || c == 8232) && external) {
                c = 10;
            }
        } else {
            c = -1;
        }
        return c;
    }

    public int scanLiteral(int quote, XMLString content) throws IOException {
        ScannedEntity scannedEntity;
        int length;
        if (this.fCurrentEntity.position == this.fCurrentEntity.count) {
            load(0, true);
        } else if (this.fCurrentEntity.position == this.fCurrentEntity.count - 1) {
            this.fCurrentEntity.ch[0] = this.fCurrentEntity.ch[this.fCurrentEntity.count - 1];
            load(1, false);
            this.fCurrentEntity.startPosition = 0;
            this.fCurrentEntity.position = 0;
        }
        int offset = this.fCurrentEntity.position;
        int c = this.fCurrentEntity.ch[offset];
        int newlines = 0;
        boolean external = this.fCurrentEntity.isExternal();
        if (c == 10 || ((c == 13 || c == 133 || c == 8232) && external)) {
            do {
                char[] cArr = this.fCurrentEntity.ch;
                ScannedEntity scannedEntity2 = this.fCurrentEntity;
                int i = scannedEntity2.position;
                scannedEntity2.position = i + 1;
                c = cArr[i];
                if (c != 13 || !external) {
                    if (c != 10 && ((c != 133 && c != 8232) || !external)) {
                        scannedEntity = this.fCurrentEntity;
                        scannedEntity.position--;
                        break;
                    }
                    newlines++;
                    scannedEntity = this.fCurrentEntity;
                    scannedEntity.lineNumber++;
                    this.fCurrentEntity.columnNumber = 1;
                    if (this.fCurrentEntity.position == this.fCurrentEntity.count) {
                        offset = 0;
                        scannedEntity = this.fCurrentEntity;
                        scannedEntity.baseCharOffset += this.fCurrentEntity.position - this.fCurrentEntity.startPosition;
                        this.fCurrentEntity.position = newlines;
                        this.fCurrentEntity.startPosition = newlines;
                        if (load(newlines, false)) {
                            break;
                        }
                    }
                }
                newlines++;
                scannedEntity = this.fCurrentEntity;
                scannedEntity.lineNumber++;
                this.fCurrentEntity.columnNumber = 1;
                if (this.fCurrentEntity.position == this.fCurrentEntity.count) {
                    offset = 0;
                    scannedEntity = this.fCurrentEntity;
                    scannedEntity.baseCharOffset += this.fCurrentEntity.position - this.fCurrentEntity.startPosition;
                    this.fCurrentEntity.position = newlines;
                    this.fCurrentEntity.startPosition = newlines;
                    if (load(newlines, false)) {
                        break;
                    }
                }
                int cc = this.fCurrentEntity.ch[this.fCurrentEntity.position];
                if (cc == 10 || cc == 133) {
                    scannedEntity = this.fCurrentEntity;
                    scannedEntity.position++;
                    offset++;
                } else {
                    newlines++;
                }
            } while (this.fCurrentEntity.position < this.fCurrentEntity.count - 1);
            for (int i2 = offset; i2 < this.fCurrentEntity.position; i2++) {
                this.fCurrentEntity.ch[i2] = '\n';
            }
            length = this.fCurrentEntity.position - offset;
            if (this.fCurrentEntity.position == this.fCurrentEntity.count - 1) {
                content.setValues(this.fCurrentEntity.ch, offset, length);
                return -1;
            }
        }
        if (!external) {
            while (this.fCurrentEntity.position < this.fCurrentEntity.count) {
                cArr = this.fCurrentEntity.ch;
                scannedEntity2 = this.fCurrentEntity;
                i = scannedEntity2.position;
                scannedEntity2.position = i + 1;
                c = cArr[i];
                if ((c != quote || this.fCurrentEntity.literal) && c != 37) {
                    if (!XML11Char.isXML11InternalEntityContent(c)) {
                        scannedEntity = this.fCurrentEntity;
                        scannedEntity.position--;
                        break;
                    }
                }
                scannedEntity = this.fCurrentEntity;
                scannedEntity.position--;
                break;
            }
        }
        while (this.fCurrentEntity.position < this.fCurrentEntity.count) {
            cArr = this.fCurrentEntity.ch;
            scannedEntity2 = this.fCurrentEntity;
            i = scannedEntity2.position;
            scannedEntity2.position = i + 1;
            c = cArr[i];
            if (c != quote && c != 37 && XML11Char.isXML11Content(c) && c != 133) {
                if (c == 8232) {
                    scannedEntity = this.fCurrentEntity;
                    scannedEntity.position--;
                    break;
                }
            }
            scannedEntity = this.fCurrentEntity;
            scannedEntity.position--;
            break;
        }
        length = this.fCurrentEntity.position - offset;
        scannedEntity = this.fCurrentEntity;
        scannedEntity.columnNumber += length - newlines;
        content.setValues(this.fCurrentEntity.ch, offset, length);
        if (this.fCurrentEntity.position != this.fCurrentEntity.count) {
            c = this.fCurrentEntity.ch[this.fCurrentEntity.position];
            if (c == quote && this.fCurrentEntity.literal) {
                c = -1;
            }
        } else {
            c = -1;
        }
        return c;
    }

    public boolean scanData(String delimiter, XMLStringBuffer buffer) throws IOException {
        boolean done = false;
        int delimLen = delimiter.length();
        char charAt0 = delimiter.charAt(0);
        boolean external = this.fCurrentEntity.isExternal();
        do {
            if (this.fCurrentEntity.position == this.fCurrentEntity.count) {
                load(0, true);
            }
            boolean bNextEntity = false;
            while (this.fCurrentEntity.position >= this.fCurrentEntity.count - delimLen && !bNextEntity) {
                System.arraycopy(this.fCurrentEntity.ch, this.fCurrentEntity.position, this.fCurrentEntity.ch, 0, this.fCurrentEntity.count - this.fCurrentEntity.position);
                bNextEntity = load(this.fCurrentEntity.count - this.fCurrentEntity.position, false);
                this.fCurrentEntity.position = 0;
                this.fCurrentEntity.startPosition = 0;
            }
            if (this.fCurrentEntity.position >= this.fCurrentEntity.count - delimLen) {
                XMLStringBuffer xMLStringBuffer = buffer;
                xMLStringBuffer.append(this.fCurrentEntity.ch, this.fCurrentEntity.position, this.fCurrentEntity.count - this.fCurrentEntity.position);
                ScannedEntity scannedEntity = this.fCurrentEntity;
                scannedEntity.columnNumber += this.fCurrentEntity.count;
                scannedEntity = this.fCurrentEntity;
                scannedEntity.baseCharOffset += this.fCurrentEntity.position - this.fCurrentEntity.startPosition;
                this.fCurrentEntity.position = this.fCurrentEntity.count;
                this.fCurrentEntity.startPosition = this.fCurrentEntity.count;
                load(0, true);
                return false;
            }
            int i;
            int length;
            int offset = this.fCurrentEntity.position;
            int c = this.fCurrentEntity.ch[offset];
            int newlines = 0;
            if (c == 10 || ((c == 13 || c == 133 || c == 8232) && external)) {
                do {
                    char[] cArr = this.fCurrentEntity.ch;
                    ScannedEntity scannedEntity2 = this.fCurrentEntity;
                    int i2 = scannedEntity2.position;
                    scannedEntity2.position = i2 + 1;
                    c = cArr[i2];
                    if (c != 13 || !external) {
                        if (c != 10 && ((c != 133 && c != 8232) || !external)) {
                            scannedEntity = this.fCurrentEntity;
                            scannedEntity.position--;
                            break;
                        }
                        newlines++;
                        scannedEntity = this.fCurrentEntity;
                        scannedEntity.lineNumber++;
                        this.fCurrentEntity.columnNumber = 1;
                        if (this.fCurrentEntity.position == this.fCurrentEntity.count) {
                            offset = 0;
                            scannedEntity = this.fCurrentEntity;
                            scannedEntity.baseCharOffset += this.fCurrentEntity.position - this.fCurrentEntity.startPosition;
                            this.fCurrentEntity.position = newlines;
                            this.fCurrentEntity.startPosition = newlines;
                            this.fCurrentEntity.count = newlines;
                            if (load(newlines, false)) {
                                break;
                            }
                        }
                    }
                    newlines++;
                    scannedEntity = this.fCurrentEntity;
                    scannedEntity.lineNumber++;
                    this.fCurrentEntity.columnNumber = 1;
                    if (this.fCurrentEntity.position == this.fCurrentEntity.count) {
                        offset = 0;
                        scannedEntity = this.fCurrentEntity;
                        scannedEntity.baseCharOffset += this.fCurrentEntity.position - this.fCurrentEntity.startPosition;
                        this.fCurrentEntity.position = newlines;
                        this.fCurrentEntity.startPosition = newlines;
                        if (load(newlines, false)) {
                            break;
                        }
                    }
                    int cc = this.fCurrentEntity.ch[this.fCurrentEntity.position];
                    if (cc == 10 || cc == 133) {
                        scannedEntity = this.fCurrentEntity;
                        scannedEntity.position++;
                        offset++;
                    } else {
                        newlines++;
                    }
                } while (this.fCurrentEntity.position < this.fCurrentEntity.count - 1);
                for (i = offset; i < this.fCurrentEntity.position; i++) {
                    this.fCurrentEntity.ch[i] = '\n';
                }
                length = this.fCurrentEntity.position - offset;
                if (this.fCurrentEntity.position == this.fCurrentEntity.count - 1) {
                    buffer.append(this.fCurrentEntity.ch, offset, length);
                    return true;
                }
            }
            char c2;
            int delimOffset;
            if (external) {
                while (this.fCurrentEntity.position < this.fCurrentEntity.count) {
                    cArr = this.fCurrentEntity.ch;
                    scannedEntity2 = this.fCurrentEntity;
                    i2 = scannedEntity2.position;
                    scannedEntity2.position = i2 + 1;
                    c2 = cArr[i2];
                    if (c2 == charAt0) {
                        delimOffset = this.fCurrentEntity.position - 1;
                        for (i = 1; i < delimLen; i++) {
                            if (this.fCurrentEntity.position == this.fCurrentEntity.count) {
                                scannedEntity = this.fCurrentEntity;
                                scannedEntity.position -= i;
                                break;
                            }
                            cArr = this.fCurrentEntity.ch;
                            scannedEntity2 = this.fCurrentEntity;
                            i2 = scannedEntity2.position;
                            scannedEntity2.position = i2 + 1;
                            if (delimiter.charAt(i) != cArr[i2]) {
                                scannedEntity = this.fCurrentEntity;
                                scannedEntity.position--;
                                break;
                            }
                        }
                        if (this.fCurrentEntity.position == delimOffset + delimLen) {
                            done = true;
                            break;
                        }
                    } else if (c2 == '\n' || c2 == '\r' || c2 == '' || c2 == ' ') {
                        scannedEntity = this.fCurrentEntity;
                        scannedEntity.position--;
                        break;
                    } else if (!XML11Char.isXML11ValidLiteral(c2)) {
                        scannedEntity = this.fCurrentEntity;
                        scannedEntity.position--;
                        length = this.fCurrentEntity.position - offset;
                        scannedEntity = this.fCurrentEntity;
                        scannedEntity.columnNumber += length - newlines;
                        buffer.append(this.fCurrentEntity.ch, offset, length);
                        return true;
                    }
                }
            } else {
                while (this.fCurrentEntity.position < this.fCurrentEntity.count) {
                    cArr = this.fCurrentEntity.ch;
                    scannedEntity2 = this.fCurrentEntity;
                    i2 = scannedEntity2.position;
                    scannedEntity2.position = i2 + 1;
                    c2 = cArr[i2];
                    if (c2 == charAt0) {
                        delimOffset = this.fCurrentEntity.position - 1;
                        for (i = 1; i < delimLen; i++) {
                            if (this.fCurrentEntity.position == this.fCurrentEntity.count) {
                                scannedEntity = this.fCurrentEntity;
                                scannedEntity.position -= i;
                                break;
                            }
                            cArr = this.fCurrentEntity.ch;
                            scannedEntity2 = this.fCurrentEntity;
                            i2 = scannedEntity2.position;
                            scannedEntity2.position = i2 + 1;
                            if (delimiter.charAt(i) != cArr[i2]) {
                                scannedEntity = this.fCurrentEntity;
                                scannedEntity.position--;
                                break;
                            }
                        }
                        if (this.fCurrentEntity.position == delimOffset + delimLen) {
                            done = true;
                            break;
                        }
                    } else if (c2 == '\n') {
                        scannedEntity = this.fCurrentEntity;
                        scannedEntity.position--;
                        break;
                    } else if (!XML11Char.isXML11Valid(c2)) {
                        scannedEntity = this.fCurrentEntity;
                        scannedEntity.position--;
                        length = this.fCurrentEntity.position - offset;
                        scannedEntity = this.fCurrentEntity;
                        scannedEntity.columnNumber += length - newlines;
                        buffer.append(this.fCurrentEntity.ch, offset, length);
                        return true;
                    }
                }
            }
            length = this.fCurrentEntity.position - offset;
            scannedEntity = this.fCurrentEntity;
            scannedEntity.columnNumber += length - newlines;
            if (done) {
                length -= delimLen;
            }
            buffer.append(this.fCurrentEntity.ch, offset, length);
        } while (!done);
        if (done) {
            return false;
        }
        return true;
    }

    public boolean skipChar(int c) throws IOException {
        if (this.fCurrentEntity.position == this.fCurrentEntity.count) {
            load(0, true);
        }
        int cc = this.fCurrentEntity.ch[this.fCurrentEntity.position];
        ScannedEntity scannedEntity;
        if (cc == c) {
            scannedEntity = this.fCurrentEntity;
            scannedEntity.position++;
            if (c == 10) {
                scannedEntity = this.fCurrentEntity;
                scannedEntity.lineNumber++;
                this.fCurrentEntity.columnNumber = 1;
                return true;
            }
            scannedEntity = this.fCurrentEntity;
            scannedEntity.columnNumber++;
            return true;
        } else if (c == 10 && ((cc == 8232 || cc == 133) && this.fCurrentEntity.isExternal())) {
            scannedEntity = this.fCurrentEntity;
            scannedEntity.position++;
            scannedEntity = this.fCurrentEntity;
            scannedEntity.lineNumber++;
            this.fCurrentEntity.columnNumber = 1;
            return true;
        } else if (c != 10 || cc != 13 || !this.fCurrentEntity.isExternal()) {
            return false;
        } else {
            if (this.fCurrentEntity.position == this.fCurrentEntity.count) {
                this.fCurrentEntity.ch[0] = (char) cc;
                load(1, false);
            }
            char[] cArr = this.fCurrentEntity.ch;
            ScannedEntity scannedEntity2 = this.fCurrentEntity;
            int i = scannedEntity2.position + 1;
            scannedEntity2.position = i;
            int ccc = cArr[i];
            if (ccc == 10 || ccc == 133) {
                scannedEntity = this.fCurrentEntity;
                scannedEntity.position++;
            }
            scannedEntity = this.fCurrentEntity;
            scannedEntity.lineNumber++;
            this.fCurrentEntity.columnNumber = 1;
            return true;
        }
    }

    public boolean skipSpaces() throws IOException {
        if (this.fCurrentEntity.position == this.fCurrentEntity.count) {
            load(0, true);
        }
        int c = this.fCurrentEntity.ch[this.fCurrentEntity.position];
        boolean entityChanged;
        ScannedEntity scannedEntity;
        if (this.fCurrentEntity.isExternal()) {
            if (XML11Char.isXML11Space(c)) {
                do {
                    entityChanged = false;
                    if (c == 10 || c == 13 || c == 133 || c == 8232) {
                        scannedEntity = this.fCurrentEntity;
                        scannedEntity.lineNumber++;
                        this.fCurrentEntity.columnNumber = 1;
                        if (this.fCurrentEntity.position == this.fCurrentEntity.count - 1) {
                            this.fCurrentEntity.ch[0] = (char) c;
                            entityChanged = load(1, true);
                            if (!entityChanged) {
                                this.fCurrentEntity.startPosition = 0;
                                this.fCurrentEntity.position = 0;
                            }
                        }
                        if (c == 13) {
                            char[] cArr = this.fCurrentEntity.ch;
                            ScannedEntity scannedEntity2 = this.fCurrentEntity;
                            int i = scannedEntity2.position + 1;
                            scannedEntity2.position = i;
                            int cc = cArr[i];
                            if (!(cc == 10 || cc == 133)) {
                                scannedEntity = this.fCurrentEntity;
                                scannedEntity.position--;
                            }
                        }
                    } else {
                        scannedEntity = this.fCurrentEntity;
                        scannedEntity.columnNumber++;
                    }
                    if (!entityChanged) {
                        scannedEntity = this.fCurrentEntity;
                        scannedEntity.position++;
                    }
                    if (this.fCurrentEntity.position == this.fCurrentEntity.count) {
                        load(0, true);
                    }
                    c = this.fCurrentEntity.ch[this.fCurrentEntity.position];
                } while (XML11Char.isXML11Space(c));
                return true;
            }
        } else if (XMLChar.isSpace(c)) {
            do {
                entityChanged = false;
                if (c == 10) {
                    scannedEntity = this.fCurrentEntity;
                    scannedEntity.lineNumber++;
                    this.fCurrentEntity.columnNumber = 1;
                    if (this.fCurrentEntity.position == this.fCurrentEntity.count - 1) {
                        this.fCurrentEntity.ch[0] = (char) c;
                        entityChanged = load(1, true);
                        if (!entityChanged) {
                            this.fCurrentEntity.startPosition = 0;
                            this.fCurrentEntity.position = 0;
                        }
                    }
                } else {
                    scannedEntity = this.fCurrentEntity;
                    scannedEntity.columnNumber++;
                }
                if (!entityChanged) {
                    scannedEntity = this.fCurrentEntity;
                    scannedEntity.position++;
                }
                if (this.fCurrentEntity.position == this.fCurrentEntity.count) {
                    load(0, true);
                }
                c = this.fCurrentEntity.ch[this.fCurrentEntity.position];
            } while (XMLChar.isSpace(c));
            return true;
        }
        return false;
    }

    public boolean skipString(String s) throws IOException {
        if (this.fCurrentEntity.position == this.fCurrentEntity.count) {
            load(0, true);
        }
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char[] cArr = this.fCurrentEntity.ch;
            ScannedEntity scannedEntity = this.fCurrentEntity;
            int i2 = scannedEntity.position;
            scannedEntity.position = i2 + 1;
            if (cArr[i2] != s.charAt(i)) {
                ScannedEntity scannedEntity2 = this.fCurrentEntity;
                scannedEntity2.position -= i + 1;
                return false;
            }
            if (i < length - 1 && this.fCurrentEntity.position == this.fCurrentEntity.count) {
                System.arraycopy(this.fCurrentEntity.ch, (this.fCurrentEntity.count - i) - 1, this.fCurrentEntity.ch, 0, i + 1);
                if (load(i + 1, false)) {
                    scannedEntity2 = this.fCurrentEntity;
                    scannedEntity2.startPosition -= i + 1;
                    scannedEntity2 = this.fCurrentEntity;
                    scannedEntity2.position -= i + 1;
                    return false;
                }
            }
        }
        ScannedEntity scannedEntity3 = this.fCurrentEntity;
        scannedEntity3.columnNumber += length;
        return true;
    }
}
