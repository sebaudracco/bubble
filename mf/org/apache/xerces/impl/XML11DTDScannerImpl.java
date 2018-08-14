package mf.org.apache.xerces.impl;

import java.io.IOException;
import mf.org.apache.xerces.util.SymbolTable;
import mf.org.apache.xerces.util.XML11Char;
import mf.org.apache.xerces.util.XMLChar;
import mf.org.apache.xerces.util.XMLStringBuffer;
import mf.org.apache.xerces.xni.XMLString;
import mf.org.apache.xerces.xni.XNIException;

public class XML11DTDScannerImpl extends XMLDTDScannerImpl {
    private final XMLStringBuffer fStringBuffer = new XMLStringBuffer();

    public XML11DTDScannerImpl(SymbolTable symbolTable, XMLErrorReporter errorReporter, XMLEntityManager entityManager) {
        super(symbolTable, errorReporter, entityManager);
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
        return !XML11Char.isXML11Valid(value);
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
