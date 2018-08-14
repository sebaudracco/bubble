package mf.org.apache.xerces.impl;

import java.io.EOFException;
import java.io.IOException;
import java.util.Locale;
import mf.org.apache.xerces.impl.XMLEntityManager.ScannedEntity;
import mf.org.apache.xerces.impl.io.UCSReader;
import mf.org.apache.xerces.util.SymbolTable;
import mf.org.apache.xerces.util.XMLChar;
import mf.org.apache.xerces.util.XMLStringBuffer;
import mf.org.apache.xerces.xni.QName;
import mf.org.apache.xerces.xni.XMLLocator;
import mf.org.apache.xerces.xni.XMLString;

public class XMLEntityScanner implements XMLLocator {
    private static final boolean DEBUG_BUFFER = false;
    private static final boolean DEBUG_ENCODINGS = false;
    private static final EOFException END_OF_DOCUMENT_ENTITY = new C46271();
    protected int fBufferSize = 2048;
    protected ScannedEntity fCurrentEntity = null;
    private XMLEntityManager fEntityManager = null;
    protected XMLErrorReporter fErrorReporter;
    protected SymbolTable fSymbolTable = null;

    class C46271 extends EOFException {
        private static final long serialVersionUID = 980337771224675268L;

        C46271() {
        }

        public Throwable fillInStackTrace() {
            return this;
        }
    }

    public final String getBaseSystemId() {
        return (this.fCurrentEntity == null || this.fCurrentEntity.entityLocation == null) ? null : this.fCurrentEntity.entityLocation.getExpandedSystemId();
    }

    public final void setEncoding(String encoding) throws IOException {
        if (this.fCurrentEntity.stream == null) {
            return;
        }
        if (this.fCurrentEntity.encoding == null || !this.fCurrentEntity.encoding.equals(encoding)) {
            if (this.fCurrentEntity.encoding != null && this.fCurrentEntity.encoding.startsWith("UTF-16")) {
                String ENCODING = encoding.toUpperCase(Locale.ENGLISH);
                if (!ENCODING.equals("UTF-16")) {
                    if (ENCODING.equals("ISO-10646-UCS-4")) {
                        if (this.fCurrentEntity.encoding.equals("UTF-16BE")) {
                            this.fCurrentEntity.reader = new UCSReader(this.fCurrentEntity.stream, (short) 8);
                            return;
                        }
                        this.fCurrentEntity.reader = new UCSReader(this.fCurrentEntity.stream, (short) 4);
                        return;
                    } else if (ENCODING.equals("ISO-10646-UCS-2")) {
                        if (this.fCurrentEntity.encoding.equals("UTF-16BE")) {
                            this.fCurrentEntity.reader = new UCSReader(this.fCurrentEntity.stream, (short) 2);
                            return;
                        }
                        this.fCurrentEntity.reader = new UCSReader(this.fCurrentEntity.stream, (short) 1);
                        return;
                    }
                }
                return;
            }
            this.fCurrentEntity.setReader(this.fCurrentEntity.stream, encoding, null);
            this.fCurrentEntity.encoding = encoding;
        }
    }

    public final void setXMLVersion(String xmlVersion) {
        this.fCurrentEntity.xmlVersion = xmlVersion;
    }

    public final boolean isExternal() {
        return this.fCurrentEntity.isExternal();
    }

    public int peekChar() throws IOException {
        if (this.fCurrentEntity.position == this.fCurrentEntity.count) {
            load(0, true);
        }
        int c = this.fCurrentEntity.ch[this.fCurrentEntity.position];
        if (this.fCurrentEntity.isExternal() && c == 13) {
            return 10;
        }
        return c;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int scanChar() throws java.io.IOException {
        /*
        r10 = this;
        r9 = 13;
        r8 = 10;
        r7 = 1;
        r6 = 0;
        r2 = r10.fCurrentEntity;
        r2 = r2.position;
        r3 = r10.fCurrentEntity;
        r3 = r3.count;
        if (r2 != r3) goto L_0x0013;
    L_0x0010:
        r10.load(r6, r7);
    L_0x0013:
        r2 = r10.fCurrentEntity;
        r2 = r2.ch;
        r3 = r10.fCurrentEntity;
        r4 = r3.position;
        r5 = r4 + 1;
        r3.position = r5;
        r0 = r2[r4];
        r1 = 0;
        if (r0 == r8) goto L_0x002e;
    L_0x0024:
        if (r0 != r9) goto L_0x006c;
    L_0x0026:
        r2 = r10.fCurrentEntity;
        r1 = r2.isExternal();
        if (r1 == 0) goto L_0x006c;
    L_0x002e:
        r2 = r10.fCurrentEntity;
        r3 = r2.lineNumber;
        r3 = r3 + 1;
        r2.lineNumber = r3;
        r2 = r10.fCurrentEntity;
        r2.columnNumber = r7;
        r2 = r10.fCurrentEntity;
        r2 = r2.position;
        r3 = r10.fCurrentEntity;
        r3 = r3.count;
        if (r2 != r3) goto L_0x004e;
    L_0x0044:
        r2 = r10.fCurrentEntity;
        r2 = r2.ch;
        r3 = (char) r0;
        r2[r6] = r3;
        r10.load(r7, r6);
    L_0x004e:
        if (r0 != r9) goto L_0x006c;
    L_0x0050:
        if (r1 == 0) goto L_0x006c;
    L_0x0052:
        r2 = r10.fCurrentEntity;
        r2 = r2.ch;
        r3 = r10.fCurrentEntity;
        r4 = r3.position;
        r5 = r4 + 1;
        r3.position = r5;
        r2 = r2[r4];
        if (r2 == r8) goto L_0x006a;
    L_0x0062:
        r2 = r10.fCurrentEntity;
        r3 = r2.position;
        r3 = r3 + -1;
        r2.position = r3;
    L_0x006a:
        r0 = 10;
    L_0x006c:
        r2 = r10.fCurrentEntity;
        r3 = r2.columnNumber;
        r3 = r3 + 1;
        r2.columnNumber = r3;
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: mf.org.apache.xerces.impl.XMLEntityScanner.scanChar():int");
    }

    public String scanNmtoken() throws IOException {
        ScannedEntity scannedEntity;
        int length;
        if (this.fCurrentEntity.position == this.fCurrentEntity.count) {
            load(0, true);
        }
        int offset = this.fCurrentEntity.position;
        while (XMLChar.isName(this.fCurrentEntity.ch[this.fCurrentEntity.position])) {
            scannedEntity = this.fCurrentEntity;
            int i = scannedEntity.position + 1;
            scannedEntity.position = i;
            if (i == this.fCurrentEntity.count) {
                length = this.fCurrentEntity.position - offset;
                if (length == this.fCurrentEntity.ch.length) {
                    char[] tmp = new char[(this.fCurrentEntity.ch.length << 1)];
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
        }
        length = this.fCurrentEntity.position - offset;
        scannedEntity = this.fCurrentEntity;
        scannedEntity.columnNumber += length;
        if (length > 0) {
            return this.fSymbolTable.addSymbol(this.fCurrentEntity.ch, offset, length);
        }
        return null;
    }

    public String scanName() throws IOException {
        ScannedEntity scannedEntity;
        int length;
        if (this.fCurrentEntity.position == this.fCurrentEntity.count) {
            load(0, true);
        }
        int offset = this.fCurrentEntity.position;
        if (XMLChar.isNameStart(this.fCurrentEntity.ch[offset])) {
            scannedEntity = this.fCurrentEntity;
            int i = scannedEntity.position + 1;
            scannedEntity.position = i;
            if (i == this.fCurrentEntity.count) {
                this.fCurrentEntity.ch[0] = this.fCurrentEntity.ch[offset];
                offset = 0;
                if (load(1, false)) {
                    scannedEntity = this.fCurrentEntity;
                    scannedEntity.columnNumber++;
                    return this.fSymbolTable.addSymbol(this.fCurrentEntity.ch, 0, 1);
                }
            }
            while (XMLChar.isName(this.fCurrentEntity.ch[this.fCurrentEntity.position])) {
                scannedEntity = this.fCurrentEntity;
                i = scannedEntity.position + 1;
                scannedEntity.position = i;
                if (i == this.fCurrentEntity.count) {
                    length = this.fCurrentEntity.position - offset;
                    if (length == this.fCurrentEntity.ch.length) {
                        char[] tmp = new char[(this.fCurrentEntity.ch.length << 1)];
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
            }
        }
        length = this.fCurrentEntity.position - offset;
        scannedEntity = this.fCurrentEntity;
        scannedEntity.columnNumber += length;
        if (length > 0) {
            return this.fSymbolTable.addSymbol(this.fCurrentEntity.ch, offset, length);
        }
        return null;
    }

    public String scanNCName() throws IOException {
        ScannedEntity scannedEntity;
        int length;
        if (this.fCurrentEntity.position == this.fCurrentEntity.count) {
            load(0, true);
        }
        int offset = this.fCurrentEntity.position;
        if (XMLChar.isNCNameStart(this.fCurrentEntity.ch[offset])) {
            scannedEntity = this.fCurrentEntity;
            int i = scannedEntity.position + 1;
            scannedEntity.position = i;
            if (i == this.fCurrentEntity.count) {
                this.fCurrentEntity.ch[0] = this.fCurrentEntity.ch[offset];
                offset = 0;
                if (load(1, false)) {
                    scannedEntity = this.fCurrentEntity;
                    scannedEntity.columnNumber++;
                    return this.fSymbolTable.addSymbol(this.fCurrentEntity.ch, 0, 1);
                }
            }
            while (XMLChar.isNCName(this.fCurrentEntity.ch[this.fCurrentEntity.position])) {
                scannedEntity = this.fCurrentEntity;
                i = scannedEntity.position + 1;
                scannedEntity.position = i;
                if (i == this.fCurrentEntity.count) {
                    length = this.fCurrentEntity.position - offset;
                    if (length == this.fCurrentEntity.ch.length) {
                        char[] tmp = new char[(this.fCurrentEntity.ch.length << 1)];
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
            }
        }
        length = this.fCurrentEntity.position - offset;
        scannedEntity = this.fCurrentEntity;
        scannedEntity.columnNumber += length;
        if (length > 0) {
            return this.fSymbolTable.addSymbol(this.fCurrentEntity.ch, offset, length);
        }
        return null;
    }

    public boolean scanQName(QName qname) throws IOException {
        if (this.fCurrentEntity.position == this.fCurrentEntity.count) {
            load(0, true);
        }
        int offset = this.fCurrentEntity.position;
        if (XMLChar.isNCNameStart(this.fCurrentEntity.ch[offset])) {
            int length;
            ScannedEntity scannedEntity = this.fCurrentEntity;
            int i = scannedEntity.position + 1;
            scannedEntity.position = i;
            if (i == this.fCurrentEntity.count) {
                this.fCurrentEntity.ch[0] = this.fCurrentEntity.ch[offset];
                offset = 0;
                if (load(1, false)) {
                    scannedEntity = this.fCurrentEntity;
                    scannedEntity.columnNumber++;
                    String name = this.fSymbolTable.addSymbol(this.fCurrentEntity.ch, 0, 1);
                    qname.setValues(null, name, name, null);
                    return true;
                }
            }
            int index = -1;
            while (XMLChar.isName(this.fCurrentEntity.ch[this.fCurrentEntity.position])) {
                if (this.fCurrentEntity.ch[this.fCurrentEntity.position] == ':') {
                    if (index != -1) {
                        break;
                    }
                    index = this.fCurrentEntity.position;
                }
                scannedEntity = this.fCurrentEntity;
                i = scannedEntity.position + 1;
                scannedEntity.position = i;
                if (i == this.fCurrentEntity.count) {
                    length = this.fCurrentEntity.position - offset;
                    if (length == this.fCurrentEntity.ch.length) {
                        char[] tmp = new char[(this.fCurrentEntity.ch.length << 1)];
                        System.arraycopy(this.fCurrentEntity.ch, offset, tmp, 0, length);
                        this.fCurrentEntity.ch = tmp;
                    } else {
                        System.arraycopy(this.fCurrentEntity.ch, offset, this.fCurrentEntity.ch, 0, length);
                    }
                    if (index != -1) {
                        index -= offset;
                    }
                    offset = 0;
                    if (load(length, false)) {
                        break;
                    }
                }
            }
            length = this.fCurrentEntity.position - offset;
            scannedEntity = this.fCurrentEntity;
            scannedEntity.columnNumber += length;
            if (length > 0) {
                String localpart;
                String prefix = null;
                String rawname = this.fSymbolTable.addSymbol(this.fCurrentEntity.ch, offset, length);
                if (index != -1) {
                    int prefixLength = index - offset;
                    prefix = this.fSymbolTable.addSymbol(this.fCurrentEntity.ch, offset, prefixLength);
                    int len = (length - prefixLength) - 1;
                    int startLocal = index + 1;
                    if (!XMLChar.isNCNameStart(this.fCurrentEntity.ch[startLocal])) {
                        this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "IllegalQName", null, (short) 2);
                    }
                    localpart = this.fSymbolTable.addSymbol(this.fCurrentEntity.ch, startLocal, len);
                } else {
                    localpart = rawname;
                }
                qname.setValues(prefix, localpart, rawname, null);
                return true;
            }
        }
        return false;
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
        if (c == 10 || (c == 13 && external)) {
            do {
                char[] cArr = this.fCurrentEntity.ch;
                ScannedEntity scannedEntity2 = this.fCurrentEntity;
                int i = scannedEntity2.position;
                scannedEntity2.position = i + 1;
                c = cArr[i];
                if (c != 13 || !external) {
                    if (c != 10) {
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
                if (this.fCurrentEntity.ch[this.fCurrentEntity.position] == '\n') {
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
        while (this.fCurrentEntity.position < this.fCurrentEntity.count) {
            cArr = this.fCurrentEntity.ch;
            scannedEntity2 = this.fCurrentEntity;
            i = scannedEntity2.position;
            scannedEntity2.position = i + 1;
            if (!XMLChar.isContent(cArr[i])) {
                scannedEntity = this.fCurrentEntity;
                scannedEntity.position--;
                break;
            }
        }
        length = this.fCurrentEntity.position - offset;
        scannedEntity = this.fCurrentEntity;
        scannedEntity.columnNumber += length - newlines;
        content.setValues(this.fCurrentEntity.ch, offset, length);
        if (this.fCurrentEntity.position != this.fCurrentEntity.count) {
            c = this.fCurrentEntity.ch[this.fCurrentEntity.position];
            if (c == 13 && external) {
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
            this.fCurrentEntity.position = 0;
            this.fCurrentEntity.startPosition = 0;
        }
        int offset = this.fCurrentEntity.position;
        int c = this.fCurrentEntity.ch[offset];
        int newlines = 0;
        boolean external = this.fCurrentEntity.isExternal();
        if (c == 10 || (c == 13 && external)) {
            do {
                char[] cArr = this.fCurrentEntity.ch;
                ScannedEntity scannedEntity2 = this.fCurrentEntity;
                int i = scannedEntity2.position;
                scannedEntity2.position = i + 1;
                c = cArr[i];
                if (c != 13 || !external) {
                    if (c != 10) {
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
                if (this.fCurrentEntity.ch[this.fCurrentEntity.position] == '\n') {
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
        while (this.fCurrentEntity.position < this.fCurrentEntity.count) {
            cArr = this.fCurrentEntity.ch;
            scannedEntity2 = this.fCurrentEntity;
            i = scannedEntity2.position;
            scannedEntity2.position = i + 1;
            c = cArr[i];
            if ((c != quote || (this.fCurrentEntity.literal && !external)) && c != 37) {
                if (!XMLChar.isContent(c)) {
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
        boolean found = false;
        int delimLen = delimiter.length();
        char charAt0 = delimiter.charAt(0);
        boolean external = this.fCurrentEntity.isExternal();
        if (this.fCurrentEntity.position == this.fCurrentEntity.count) {
            load(0, true);
        }
        boolean bNextEntity = false;
        while (this.fCurrentEntity.position > this.fCurrentEntity.count - delimLen && !bNextEntity) {
            System.arraycopy(this.fCurrentEntity.ch, this.fCurrentEntity.position, this.fCurrentEntity.ch, 0, this.fCurrentEntity.count - this.fCurrentEntity.position);
            bNextEntity = load(this.fCurrentEntity.count - this.fCurrentEntity.position, false);
            this.fCurrentEntity.position = 0;
            this.fCurrentEntity.startPosition = 0;
        }
        if (this.fCurrentEntity.position > this.fCurrentEntity.count - delimLen) {
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
        if (c == 10 || (c == 13 && external)) {
            do {
                char[] cArr = this.fCurrentEntity.ch;
                ScannedEntity scannedEntity2 = this.fCurrentEntity;
                int i2 = scannedEntity2.position;
                scannedEntity2.position = i2 + 1;
                c = cArr[i2];
                if (c != 13 || !external) {
                    if (c != 10) {
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
                if (this.fCurrentEntity.ch[this.fCurrentEntity.position] == '\n') {
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
        loop4:
        while (this.fCurrentEntity.position < this.fCurrentEntity.count) {
            cArr = this.fCurrentEntity.ch;
            scannedEntity2 = this.fCurrentEntity;
            i2 = scannedEntity2.position;
            scannedEntity2.position = i2 + 1;
            char c2 = cArr[i2];
            if (c2 == charAt0) {
                int delimOffset = this.fCurrentEntity.position - 1;
                for (i = 1; i < delimLen; i++) {
                    if (this.fCurrentEntity.position == this.fCurrentEntity.count) {
                        scannedEntity = this.fCurrentEntity;
                        scannedEntity.position -= i;
                        break loop4;
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
                    found = true;
                    break;
                }
            } else if (c2 == '\n' || (external && c2 == '\r')) {
                scannedEntity = this.fCurrentEntity;
                scannedEntity.position--;
                break;
            } else if (XMLChar.isInvalid(c2)) {
                scannedEntity = this.fCurrentEntity;
                scannedEntity.position--;
                length = this.fCurrentEntity.position - offset;
                scannedEntity = this.fCurrentEntity;
                scannedEntity.columnNumber += length - newlines;
                buffer.append(this.fCurrentEntity.ch, offset, length);
                return true;
            }
        }
        length = this.fCurrentEntity.position - offset;
        scannedEntity = this.fCurrentEntity;
        scannedEntity.columnNumber += length - newlines;
        if (found) {
            length -= delimLen;
        }
        buffer.append(this.fCurrentEntity.ch, offset, length);
        if (found) {
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
        } else if (c != 10 || cc != 13 || !this.fCurrentEntity.isExternal()) {
            return false;
        } else {
            if (this.fCurrentEntity.position == this.fCurrentEntity.count) {
                this.fCurrentEntity.ch[0] = (char) cc;
                load(1, false);
            }
            scannedEntity = this.fCurrentEntity;
            scannedEntity.position++;
            if (this.fCurrentEntity.ch[this.fCurrentEntity.position] == '\n') {
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
        if (!XMLChar.isSpace(c)) {
            return false;
        }
        boolean external = this.fCurrentEntity.isExternal();
        do {
            ScannedEntity scannedEntity;
            boolean entityChanged = false;
            if (c == 10 || (external && c == 13)) {
                scannedEntity = this.fCurrentEntity;
                scannedEntity.lineNumber++;
                this.fCurrentEntity.columnNumber = 1;
                if (this.fCurrentEntity.position == this.fCurrentEntity.count - 1) {
                    this.fCurrentEntity.ch[0] = (char) c;
                    entityChanged = load(1, true);
                    if (!entityChanged) {
                        this.fCurrentEntity.position = 0;
                        this.fCurrentEntity.startPosition = 0;
                    }
                }
                if (c == 13 && external) {
                    char[] cArr = this.fCurrentEntity.ch;
                    ScannedEntity scannedEntity2 = this.fCurrentEntity;
                    int i = scannedEntity2.position + 1;
                    scannedEntity2.position = i;
                    if (cArr[i] != '\n') {
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
        } while (XMLChar.isSpace(c));
        return true;
    }

    public final boolean skipDeclSpaces() throws IOException {
        if (this.fCurrentEntity.position == this.fCurrentEntity.count) {
            load(0, true);
        }
        int c = this.fCurrentEntity.ch[this.fCurrentEntity.position];
        if (!XMLChar.isSpace(c)) {
            return false;
        }
        boolean external = this.fCurrentEntity.isExternal();
        do {
            ScannedEntity scannedEntity;
            boolean entityChanged = false;
            if (c == 10 || (external && c == 13)) {
                scannedEntity = this.fCurrentEntity;
                scannedEntity.lineNumber++;
                this.fCurrentEntity.columnNumber = 1;
                if (this.fCurrentEntity.position == this.fCurrentEntity.count - 1) {
                    this.fCurrentEntity.ch[0] = (char) c;
                    entityChanged = load(1, true);
                    if (!entityChanged) {
                        this.fCurrentEntity.position = 0;
                        this.fCurrentEntity.startPosition = 0;
                    }
                }
                if (c == 13 && external) {
                    char[] cArr = this.fCurrentEntity.ch;
                    ScannedEntity scannedEntity2 = this.fCurrentEntity;
                    int i = scannedEntity2.position + 1;
                    scannedEntity2.position = i;
                    if (cArr[i] != '\n') {
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
        } while (XMLChar.isSpace(c));
        return true;
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

    public final String getPublicId() {
        return (this.fCurrentEntity == null || this.fCurrentEntity.entityLocation == null) ? null : this.fCurrentEntity.entityLocation.getPublicId();
    }

    public final String getExpandedSystemId() {
        if (this.fCurrentEntity == null) {
            return null;
        }
        if (this.fCurrentEntity.entityLocation == null || this.fCurrentEntity.entityLocation.getExpandedSystemId() == null) {
            return this.fCurrentEntity.getExpandedSystemId();
        }
        return this.fCurrentEntity.entityLocation.getExpandedSystemId();
    }

    public final String getLiteralSystemId() {
        if (this.fCurrentEntity == null) {
            return null;
        }
        if (this.fCurrentEntity.entityLocation == null || this.fCurrentEntity.entityLocation.getLiteralSystemId() == null) {
            return this.fCurrentEntity.getLiteralSystemId();
        }
        return this.fCurrentEntity.entityLocation.getLiteralSystemId();
    }

    public final int getLineNumber() {
        if (this.fCurrentEntity == null) {
            return -1;
        }
        if (this.fCurrentEntity.isExternal()) {
            return this.fCurrentEntity.lineNumber;
        }
        return this.fCurrentEntity.getLineNumber();
    }

    public final int getColumnNumber() {
        if (this.fCurrentEntity == null) {
            return -1;
        }
        if (this.fCurrentEntity.isExternal()) {
            return this.fCurrentEntity.columnNumber;
        }
        return this.fCurrentEntity.getColumnNumber();
    }

    public final int getCharacterOffset() {
        if (this.fCurrentEntity == null) {
            return -1;
        }
        if (this.fCurrentEntity.isExternal()) {
            return this.fCurrentEntity.baseCharOffset + (this.fCurrentEntity.position - this.fCurrentEntity.startPosition);
        }
        return this.fCurrentEntity.getCharacterOffset();
    }

    public final String getEncoding() {
        if (this.fCurrentEntity == null) {
            return null;
        }
        if (this.fCurrentEntity.isExternal()) {
            return this.fCurrentEntity.encoding;
        }
        return this.fCurrentEntity.getEncoding();
    }

    public final String getXMLVersion() {
        if (this.fCurrentEntity == null) {
            return null;
        }
        if (this.fCurrentEntity.isExternal()) {
            return this.fCurrentEntity.xmlVersion;
        }
        return this.fCurrentEntity.getXMLVersion();
    }

    public final void setCurrentEntity(ScannedEntity ent) {
        this.fCurrentEntity = ent;
    }

    public final void setBufferSize(int size) {
        this.fBufferSize = size;
    }

    public final void reset(SymbolTable symbolTable, XMLEntityManager entityManager, XMLErrorReporter reporter) {
        this.fCurrentEntity = null;
        this.fSymbolTable = symbolTable;
        this.fEntityManager = entityManager;
        this.fErrorReporter = reporter;
    }

    final boolean load(int offset, boolean changeEntity) throws IOException {
        ScannedEntity scannedEntity = this.fCurrentEntity;
        scannedEntity.baseCharOffset += this.fCurrentEntity.position - this.fCurrentEntity.startPosition;
        int length = this.fCurrentEntity.ch.length - offset;
        if (!this.fCurrentEntity.mayReadChunks && length > 64) {
            length = 64;
        }
        int count = this.fCurrentEntity.reader.read(this.fCurrentEntity.ch, offset, length);
        boolean entityChanged = false;
        if (count == -1) {
            this.fCurrentEntity.count = offset;
            this.fCurrentEntity.position = offset;
            this.fCurrentEntity.startPosition = offset;
            entityChanged = true;
            if (changeEntity) {
                this.fEntityManager.endEntity();
                if (this.fCurrentEntity == null) {
                    throw END_OF_DOCUMENT_ENTITY;
                } else if (this.fCurrentEntity.position == this.fCurrentEntity.count) {
                    load(0, true);
                }
            }
        } else if (count != 0) {
            this.fCurrentEntity.count = count + offset;
            this.fCurrentEntity.position = offset;
            this.fCurrentEntity.startPosition = offset;
        }
        return entityChanged;
    }
}
