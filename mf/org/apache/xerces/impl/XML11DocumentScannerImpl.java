package mf.org.apache.xerces.impl;

import java.io.IOException;
import mf.org.apache.xerces.util.XML11Char;
import mf.org.apache.xerces.util.XMLChar;
import mf.org.apache.xerces.util.XMLStringBuffer;
import mf.org.apache.xerces.xni.XMLString;
import mf.org.apache.xerces.xni.XNIException;

public class XML11DocumentScannerImpl extends XMLDocumentScannerImpl {
    private final XMLString fString = new XMLString();
    private final XMLStringBuffer fStringBuffer = new XMLStringBuffer();
    private final XMLStringBuffer fStringBuffer2 = new XMLStringBuffer();
    private final XMLStringBuffer fStringBuffer3 = new XMLStringBuffer();

    protected int scanContent() throws IOException, XNIException {
        XMLString content = this.fString;
        int c = this.fEntityScanner.scanContent(content);
        if (c == 13 || c == 133 || c == 8232) {
            this.fEntityScanner.scanChar();
            this.fStringBuffer.clear();
            this.fStringBuffer.append(this.fString);
            this.fStringBuffer.append((char) c);
            content = this.fStringBuffer;
            c = -1;
        }
        if (this.fDocumentHandler != null && content.length > 0) {
            this.fDocumentHandler.characters(content, null);
        }
        if (c != 93 || this.fString.length != 0) {
            return c;
        }
        this.fStringBuffer.clear();
        this.fStringBuffer.append((char) this.fEntityScanner.scanChar());
        this.fInScanContent = true;
        if (this.fEntityScanner.skipChar(93)) {
            this.fStringBuffer.append(']');
            while (this.fEntityScanner.skipChar(93)) {
                this.fStringBuffer.append(']');
            }
            if (this.fEntityScanner.skipChar(62)) {
                reportFatalError("CDEndInContent", null);
            }
        }
        if (!(this.fDocumentHandler == null || this.fStringBuffer.length == 0)) {
            this.fDocumentHandler.characters(this.fStringBuffer, null);
        }
        this.fInScanContent = false;
        return -1;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected boolean scanAttributeValue(mf.org.apache.xerces.xni.XMLString r13, mf.org.apache.xerces.xni.XMLString r14, java.lang.String r15, boolean r16, java.lang.String r17) throws java.io.IOException, mf.org.apache.xerces.xni.XNIException {
        /*
        r12 = this;
        r7 = r12.fEntityScanner;
        r6 = r7.peekChar();
        r7 = 39;
        if (r6 == r7) goto L_0x001d;
    L_0x000a:
        r7 = 34;
        if (r6 == r7) goto L_0x001d;
    L_0x000e:
        r7 = "OpenQuoteExpected";
        r8 = 2;
        r8 = new java.lang.Object[r8];
        r9 = 0;
        r8[r9] = r17;
        r9 = 1;
        r8[r9] = r15;
        r12.reportFatalError(r7, r8);
    L_0x001d:
        r7 = r12.fEntityScanner;
        r7.scanChar();
        r3 = r12.fEntityDepth;
        r7 = r12.fEntityScanner;
        r0 = r7.scanLiteral(r6, r13);
        r5 = 0;
        if (r0 != r6) goto L_0x0050;
    L_0x002d:
        r5 = r12.isUnchangedByNormalization(r13);
        r7 = -1;
        if (r5 != r7) goto L_0x0050;
    L_0x0034:
        r14.setValues(r13);
        r7 = r12.fEntityScanner;
        r2 = r7.scanChar();
        if (r2 == r6) goto L_0x004e;
    L_0x003f:
        r7 = "CloseQuoteExpected";
        r8 = 2;
        r8 = new java.lang.Object[r8];
        r9 = 0;
        r8[r9] = r17;
        r9 = 1;
        r8[r9] = r15;
        r12.reportFatalError(r7, r8);
    L_0x004e:
        r7 = 1;
    L_0x004f:
        return r7;
    L_0x0050:
        r7 = r12.fStringBuffer2;
        r7.clear();
        r7 = r12.fStringBuffer2;
        r7.append(r13);
        r12.normalizeWhitespace(r13, r5);
        if (r0 == r6) goto L_0x00c7;
    L_0x005f:
        r7 = 1;
        r12.fScanningAttribute = r7;
        r7 = r12.fStringBuffer;
        r7.clear();
    L_0x0067:
        r7 = r12.fStringBuffer;
        r7.append(r13);
        r7 = 38;
        if (r0 != r7) goto L_0x01b5;
    L_0x0070:
        r7 = r12.fEntityScanner;
        r8 = 38;
        r7.skipChar(r8);
        r7 = r12.fEntityDepth;
        if (r3 != r7) goto L_0x0082;
    L_0x007b:
        r7 = r12.fStringBuffer2;
        r8 = 38;
        r7.append(r8);
    L_0x0082:
        r7 = r12.fEntityScanner;
        r8 = 35;
        r7 = r7.skipChar(r8);
        if (r7 == 0) goto L_0x00ef;
    L_0x008c:
        r7 = r12.fEntityDepth;
        if (r3 != r7) goto L_0x0097;
    L_0x0090:
        r7 = r12.fStringBuffer2;
        r8 = 35;
        r7.append(r8);
    L_0x0097:
        r7 = r12.fStringBuffer;
        r8 = r12.fStringBuffer2;
        r1 = r12.scanCharReferenceValue(r7, r8);
        r7 = -1;
        if (r1 == r7) goto L_0x00a2;
    L_0x00a2:
        r7 = r12.fEntityScanner;
        r0 = r7.scanLiteral(r6, r13);
        r7 = r12.fEntityDepth;
        if (r3 != r7) goto L_0x00b1;
    L_0x00ac:
        r7 = r12.fStringBuffer2;
        r7.append(r13);
    L_0x00b1:
        r12.normalizeWhitespace(r13);
        if (r0 != r6) goto L_0x0067;
    L_0x00b6:
        r7 = r12.fEntityDepth;
        if (r3 != r7) goto L_0x0067;
    L_0x00ba:
        r7 = r12.fStringBuffer;
        r7.append(r13);
        r7 = r12.fStringBuffer;
        r13.setValues(r7);
        r7 = 0;
        r12.fScanningAttribute = r7;
    L_0x00c7:
        r7 = r12.fStringBuffer2;
        r14.setValues(r7);
        r7 = r12.fEntityScanner;
        r2 = r7.scanChar();
        if (r2 == r6) goto L_0x00e3;
    L_0x00d4:
        r7 = "CloseQuoteExpected";
        r8 = 2;
        r8 = new java.lang.Object[r8];
        r9 = 0;
        r8[r9] = r17;
        r9 = 1;
        r8[r9] = r15;
        r12.reportFatalError(r7, r8);
    L_0x00e3:
        r7 = r13.ch;
        r8 = r13.offset;
        r9 = r13.length;
        r7 = r14.equals(r7, r8, r9);
        goto L_0x004f;
    L_0x00ef:
        r7 = r12.fEntityScanner;
        r4 = r7.scanName();
        if (r4 != 0) goto L_0x0120;
    L_0x00f7:
        r7 = "NameRequiredInReference";
        r8 = 0;
        r12.reportFatalError(r7, r8);
    L_0x00fe:
        r7 = r12.fEntityScanner;
        r8 = 59;
        r7 = r7.skipChar(r8);
        if (r7 != 0) goto L_0x012a;
    L_0x0108:
        r7 = "SemicolonRequiredInReference";
        r8 = 1;
        r8 = new java.lang.Object[r8];
        r9 = 0;
        r8[r9] = r4;
        r12.reportFatalError(r7, r8);
    L_0x0114:
        r7 = fAmpSymbol;
        if (r4 != r7) goto L_0x0136;
    L_0x0118:
        r7 = r12.fStringBuffer;
        r8 = 38;
        r7.append(r8);
        goto L_0x00a2;
    L_0x0120:
        r7 = r12.fEntityDepth;
        if (r3 != r7) goto L_0x00fe;
    L_0x0124:
        r7 = r12.fStringBuffer2;
        r7.append(r4);
        goto L_0x00fe;
    L_0x012a:
        r7 = r12.fEntityDepth;
        if (r3 != r7) goto L_0x0114;
    L_0x012e:
        r7 = r12.fStringBuffer2;
        r8 = 59;
        r7.append(r8);
        goto L_0x0114;
    L_0x0136:
        r7 = fAposSymbol;
        if (r4 != r7) goto L_0x0143;
    L_0x013a:
        r7 = r12.fStringBuffer;
        r8 = 39;
        r7.append(r8);
        goto L_0x00a2;
    L_0x0143:
        r7 = fLtSymbol;
        if (r4 != r7) goto L_0x0150;
    L_0x0147:
        r7 = r12.fStringBuffer;
        r8 = 60;
        r7.append(r8);
        goto L_0x00a2;
    L_0x0150:
        r7 = fGtSymbol;
        if (r4 != r7) goto L_0x015d;
    L_0x0154:
        r7 = r12.fStringBuffer;
        r8 = 62;
        r7.append(r8);
        goto L_0x00a2;
    L_0x015d:
        r7 = fQuotSymbol;
        if (r4 != r7) goto L_0x016a;
    L_0x0161:
        r7 = r12.fStringBuffer;
        r8 = 34;
        r7.append(r8);
        goto L_0x00a2;
    L_0x016a:
        r7 = r12.fEntityManager;
        r7 = r7.isExternalEntity(r4);
        if (r7 == 0) goto L_0x0180;
    L_0x0172:
        r7 = "ReferenceToExternalEntity";
        r8 = 1;
        r8 = new java.lang.Object[r8];
        r9 = 0;
        r8[r9] = r4;
        r12.reportFatalError(r7, r8);
        goto L_0x00a2;
    L_0x0180:
        r7 = r12.fEntityManager;
        r7 = r7.isDeclaredEntity(r4);
        if (r7 != 0) goto L_0x01a0;
    L_0x0188:
        if (r16 == 0) goto L_0x01a8;
    L_0x018a:
        r7 = r12.fValidation;
        if (r7 == 0) goto L_0x01a0;
    L_0x018e:
        r7 = r12.fErrorReporter;
        r8 = "http://www.w3.org/TR/1998/REC-xml-19980210";
        r9 = "EntityNotDeclared";
        r10 = 1;
        r10 = new java.lang.Object[r10];
        r11 = 0;
        r10[r11] = r4;
        r11 = 1;
        r7.reportError(r8, r9, r10, r11);
    L_0x01a0:
        r7 = r12.fEntityManager;
        r8 = 1;
        r7.startEntity(r4, r8);
        goto L_0x00a2;
    L_0x01a8:
        r7 = "EntityNotDeclared";
        r8 = 1;
        r8 = new java.lang.Object[r8];
        r9 = 0;
        r8[r9] = r4;
        r12.reportFatalError(r7, r8);
        goto L_0x01a0;
    L_0x01b5:
        r7 = 60;
        if (r0 != r7) goto L_0x01d9;
    L_0x01b9:
        r7 = "LessthanInAttValue";
        r8 = 2;
        r8 = new java.lang.Object[r8];
        r9 = 0;
        r8[r9] = r17;
        r9 = 1;
        r8[r9] = r15;
        r12.reportFatalError(r7, r8);
        r7 = r12.fEntityScanner;
        r7.scanChar();
        r7 = r12.fEntityDepth;
        if (r3 != r7) goto L_0x00a2;
    L_0x01d1:
        r7 = r12.fStringBuffer2;
        r8 = (char) r0;
        r7.append(r8);
        goto L_0x00a2;
    L_0x01d9:
        r7 = 37;
        if (r0 == r7) goto L_0x01e1;
    L_0x01dd:
        r7 = 93;
        if (r0 != r7) goto L_0x01f8;
    L_0x01e1:
        r7 = r12.fEntityScanner;
        r7.scanChar();
        r7 = r12.fStringBuffer;
        r8 = (char) r0;
        r7.append(r8);
        r7 = r12.fEntityDepth;
        if (r3 != r7) goto L_0x00a2;
    L_0x01f0:
        r7 = r12.fStringBuffer2;
        r8 = (char) r0;
        r7.append(r8);
        goto L_0x00a2;
    L_0x01f8:
        r7 = 10;
        if (r0 == r7) goto L_0x0208;
    L_0x01fc:
        r7 = 13;
        if (r0 == r7) goto L_0x0208;
    L_0x0200:
        r7 = 133; // 0x85 float:1.86E-43 double:6.57E-322;
        if (r0 == r7) goto L_0x0208;
    L_0x0204:
        r7 = 8232; // 0x2028 float:1.1535E-41 double:4.067E-320;
        if (r0 != r7) goto L_0x0221;
    L_0x0208:
        r7 = r12.fEntityScanner;
        r7.scanChar();
        r7 = r12.fStringBuffer;
        r8 = 32;
        r7.append(r8);
        r7 = r12.fEntityDepth;
        if (r3 != r7) goto L_0x00a2;
    L_0x0218:
        r7 = r12.fStringBuffer2;
        r8 = 10;
        r7.append(r8);
        goto L_0x00a2;
    L_0x0221:
        r7 = -1;
        if (r0 == r7) goto L_0x024b;
    L_0x0224:
        r7 = mf.org.apache.xerces.util.XMLChar.isHighSurrogate(r0);
        if (r7 == 0) goto L_0x024b;
    L_0x022a:
        r7 = r12.fStringBuffer3;
        r7.clear();
        r7 = r12.fStringBuffer3;
        r7 = r12.scanSurrogates(r7);
        if (r7 == 0) goto L_0x00a2;
    L_0x0237:
        r7 = r12.fStringBuffer;
        r8 = r12.fStringBuffer3;
        r7.append(r8);
        r7 = r12.fEntityDepth;
        if (r3 != r7) goto L_0x00a2;
    L_0x0242:
        r7 = r12.fStringBuffer2;
        r8 = r12.fStringBuffer3;
        r7.append(r8);
        goto L_0x00a2;
    L_0x024b:
        r7 = -1;
        if (r0 == r7) goto L_0x00a2;
    L_0x024e:
        r7 = r12.isInvalidLiteral(r0);
        if (r7 == 0) goto L_0x00a2;
    L_0x0254:
        r7 = "InvalidCharInAttValue";
        r8 = 3;
        r8 = new java.lang.Object[r8];
        r9 = 0;
        r8[r9] = r17;
        r9 = 1;
        r8[r9] = r15;
        r9 = 2;
        r10 = 16;
        r10 = java.lang.Integer.toString(r0, r10);
        r8[r9] = r10;
        r12.reportFatalError(r7, r8);
        r7 = r12.fEntityScanner;
        r7.scanChar();
        r7 = r12.fEntityDepth;
        if (r3 != r7) goto L_0x00a2;
    L_0x0275:
        r7 = r12.fStringBuffer2;
        r8 = (char) r0;
        r7.append(r8);
        goto L_0x00a2;
        */
        throw new UnsupportedOperationException("Method not decompiled: mf.org.apache.xerces.impl.XML11DocumentScannerImpl.scanAttributeValue(mf.org.apache.xerces.xni.XMLString, mf.org.apache.xerces.xni.XMLString, java.lang.String, boolean, java.lang.String):boolean");
    }

    protected boolean scanPubidLiteral(XMLString literal) throws IOException, XNIException {
        int quote = this.fEntityScanner.scanChar();
        if (quote == 39 || quote == 34) {
            this.fStringBuffer.clear();
            boolean skipSpace = true;
            boolean dataok = true;
            while (true) {
                int c = this.fEntityScanner.scanChar();
                if (c == 32 || c == 10 || c == 13 || c == 133 || c == 8232) {
                    if (!skipSpace) {
                        this.fStringBuffer.append(' ');
                        skipSpace = true;
                    }
                } else if (c == quote) {
                    break;
                } else if (XMLChar.isPubid(c)) {
                    this.fStringBuffer.append((char) c);
                    skipSpace = false;
                } else if (c == -1) {
                    reportFatalError("PublicIDUnterminated", null);
                    return false;
                } else {
                    dataok = false;
                    reportFatalError("InvalidCharInPublicID", new Object[]{Integer.toHexString(c)});
                }
            }
            if (skipSpace) {
                XMLString xMLString = this.fStringBuffer;
                xMLString.length = xMLString.length - 1;
            }
            literal.setValues(this.fStringBuffer);
            return dataok;
        }
        reportFatalError("QuoteRequiredInPublicID", null);
        return false;
    }

    protected void normalizeWhitespace(XMLString value) {
        int end = value.offset + value.length;
        for (int i = value.offset; i < end; i++) {
            if (XMLChar.isSpace(value.ch[i])) {
                value.ch[i] = ' ';
            }
        }
    }

    protected void normalizeWhitespace(XMLString value, int fromIndex) {
        int end = value.offset + value.length;
        for (int i = value.offset + fromIndex; i < end; i++) {
            if (XMLChar.isSpace(value.ch[i])) {
                value.ch[i] = ' ';
            }
        }
    }

    protected int isUnchangedByNormalization(XMLString value) {
        int end = value.offset + value.length;
        for (int i = value.offset; i < end; i++) {
            if (XMLChar.isSpace(value.ch[i])) {
                return i - value.offset;
            }
        }
        return -1;
    }

    protected boolean isInvalid(int value) {
        return XML11Char.isXML11Invalid(value);
    }

    protected boolean isInvalidLiteral(int value) {
        return !XML11Char.isXML11ValidLiteral(value);
    }

    protected boolean isValidNameChar(int value) {
        return XML11Char.isXML11Name(value);
    }

    protected boolean isValidNameStartChar(int value) {
        return XML11Char.isXML11NameStart(value);
    }

    protected boolean isValidNCName(int value) {
        return XML11Char.isXML11NCName(value);
    }

    protected boolean isValidNameStartHighSurrogate(int value) {
        return XML11Char.isXML11NameHighSurrogate(value);
    }

    protected boolean versionSupported(String version) {
        return version.equals("1.1") || version.equals("1.0");
    }

    protected String getVersionNotSupportedKey() {
        return "VersionNotSupported11";
    }
}
