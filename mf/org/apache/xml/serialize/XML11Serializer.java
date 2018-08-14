package mf.org.apache.xml.serialize;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import mf.org.apache.xerces.dom.DOMMessageFormatter;
import mf.org.apache.xerces.util.NamespaceSupport;
import mf.org.apache.xerces.util.SymbolTable;
import mf.org.apache.xerces.util.XML11Char;
import mf.org.apache.xerces.util.XMLChar;
import org.xml.sax.SAXException;

public class XML11Serializer extends XMLSerializer {
    protected static final boolean DEBUG = false;
    protected static final String PREFIX = "NS";
    protected boolean fDOML1;
    protected NamespaceSupport fLocalNSBinder;
    protected NamespaceSupport fNSBinder;
    protected int fNamespaceCounter;
    protected boolean fNamespaces;
    protected SymbolTable fSymbolTable;

    public XML11Serializer() {
        this.fDOML1 = false;
        this.fNamespaceCounter = 1;
        this.fNamespaces = false;
        this._format.setVersion("1.1");
    }

    public XML11Serializer(OutputFormat format) {
        super(format);
        this.fDOML1 = false;
        this.fNamespaceCounter = 1;
        this.fNamespaces = false;
        this._format.setVersion("1.1");
    }

    public XML11Serializer(Writer writer, OutputFormat format) {
        super(writer, format);
        this.fDOML1 = false;
        this.fNamespaceCounter = 1;
        this.fNamespaces = false;
        this._format.setVersion("1.1");
    }

    public XML11Serializer(OutputStream output, OutputFormat format) {
        if (format == null) {
            format = new OutputFormat("xml", null, false);
        }
        super(output, format);
        this.fDOML1 = false;
        this.fNamespaceCounter = 1;
        this.fNamespaces = false;
        this._format.setVersion("1.1");
    }

    public void characters(char[] chars, int start, int length) throws SAXException {
        try {
            ElementState state = content();
            int saveIndent;
            if (state.inCData || state.doCData) {
                if (!state.inCData) {
                    this._printer.printText("<![CDATA[");
                    state.inCData = true;
                }
                saveIndent = this._printer.getNextIndent();
                this._printer.setNextIndent(0);
                int end = start + length;
                int index = start;
                while (index < end) {
                    char ch = chars[index];
                    if (ch == ']' && index + 2 < end && chars[index + 1] == ']' && chars[index + 2] == '>') {
                        this._printer.printText("]]]]><![CDATA[>");
                        index += 2;
                    } else if (!XML11Char.isXML11Valid(ch)) {
                        index++;
                        if (index < end) {
                            surrogates(ch, chars[index], true);
                        } else {
                            fatalError("The character '" + ch + "' is an invalid XML character");
                        }
                    } else if (this._encodingInfo.isPrintable(ch) && XML11Char.isXML11ValidLiteral(ch)) {
                        this._printer.printText(ch);
                    } else {
                        this._printer.printText("]]>&#x");
                        this._printer.printText(Integer.toHexString(ch));
                        this._printer.printText(";<![CDATA[");
                    }
                    index++;
                }
                this._printer.setNextIndent(saveIndent);
            } else if (state.preserveSpace) {
                saveIndent = this._printer.getNextIndent();
                this._printer.setNextIndent(0);
                printText(chars, start, length, true, state.unescaped);
                this._printer.setNextIndent(saveIndent);
            } else {
                printText(chars, start, length, false, state.unescaped);
            }
        } catch (IOException except) {
            throw new SAXException(except);
        }
    }

    protected void printEscaped(String source) throws IOException {
        int length = source.length();
        int i = 0;
        while (i < length) {
            int ch = source.charAt(i);
            if (!XML11Char.isXML11Valid(ch)) {
                i++;
                if (i < length) {
                    surrogates(ch, source.charAt(i), false);
                } else {
                    fatalError("The character '" + ((char) ch) + "' is an invalid XML character");
                }
            } else if (ch == 10 || ch == 13 || ch == 9 || ch == 133 || ch == 8232) {
                printHex(ch);
            } else if (ch == 60) {
                this._printer.printText("&lt;");
            } else if (ch == 38) {
                this._printer.printText("&amp;");
            } else if (ch == 34) {
                this._printer.printText("&quot;");
            } else if (ch < 32 || !this._encodingInfo.isPrintable((char) ch)) {
                printHex(ch);
            } else {
                this._printer.printText((char) ch);
            }
            i++;
        }
    }

    protected final void printCDATAText(String text) throws IOException {
        int length = text.length();
        int index = 0;
        while (index < length) {
            char ch = text.charAt(index);
            if (ch == ']' && index + 2 < length && text.charAt(index + 1) == ']' && text.charAt(index + 2) == '>') {
                if (this.fDOMErrorHandler != null) {
                    if ((this.features & 16) == 0 && (this.features & 2) == 0) {
                        modifyDOMError(DOMMessageFormatter.formatMessage(DOMMessageFormatter.SERIALIZER_DOMAIN, "EndingCDATA", null), (short) 3, null, this.fCurrentNode);
                        if (!this.fDOMErrorHandler.handleError(this.fDOMError)) {
                            throw new IOException();
                        }
                    }
                    modifyDOMError(DOMMessageFormatter.formatMessage(DOMMessageFormatter.SERIALIZER_DOMAIN, "SplittingCDATA", null), (short) 1, null, this.fCurrentNode);
                    this.fDOMErrorHandler.handleError(this.fDOMError);
                }
                this._printer.printText("]]]]><![CDATA[>");
                index += 2;
            } else if (!XML11Char.isXML11Valid(ch)) {
                index++;
                if (index < length) {
                    surrogates(ch, text.charAt(index), true);
                } else {
                    fatalError("The character '" + ch + "' is an invalid XML character");
                }
            } else if (this._encodingInfo.isPrintable(ch) && XML11Char.isXML11ValidLiteral(ch)) {
                this._printer.printText(ch);
            } else {
                this._printer.printText("]]>&#x");
                this._printer.printText(Integer.toHexString(ch));
                this._printer.printText(";<![CDATA[");
            }
            index++;
        }
    }

    protected final void printXMLChar(int ch) throws IOException {
        if (ch == 13 || ch == 133 || ch == 8232) {
            printHex(ch);
        } else if (ch == 60) {
            this._printer.printText("&lt;");
        } else if (ch == 38) {
            this._printer.printText("&amp;");
        } else if (ch == 62) {
            this._printer.printText("&gt;");
        } else if (this._encodingInfo.isPrintable((char) ch) && XML11Char.isXML11ValidLiteral(ch)) {
            this._printer.printText((char) ch);
        } else {
            printHex(ch);
        }
    }

    protected final void surrogates(int high, int low, boolean inContent) throws IOException {
        if (!XMLChar.isHighSurrogate(high)) {
            fatalError("The character '" + ((char) high) + "' is an invalid XML character");
        } else if (XMLChar.isLowSurrogate(low)) {
            int supplemental = XMLChar.supplemental((char) high, (char) low);
            if (!XML11Char.isXML11Valid(supplemental)) {
                fatalError("The character '" + ((char) supplemental) + "' is an invalid XML character");
            } else if (inContent && content().inCData) {
                this._printer.printText("]]>&#x");
                this._printer.printText(Integer.toHexString(supplemental));
                this._printer.printText(";<![CDATA[");
            } else {
                printHex(supplemental);
            }
        } else {
            fatalError("The character '" + ((char) low) + "' is an invalid XML character");
        }
    }

    protected void printText(String text, boolean preserveSpace, boolean unescaped) throws IOException {
        int length = text.length();
        int index;
        char ch;
        if (preserveSpace) {
            index = 0;
            while (index < length) {
                ch = text.charAt(index);
                if (!XML11Char.isXML11Valid(ch)) {
                    index++;
                    if (index < length) {
                        surrogates(ch, text.charAt(index), true);
                    } else {
                        fatalError("The character '" + ch + "' is an invalid XML character");
                    }
                } else if (unescaped && XML11Char.isXML11ValidLiteral(ch)) {
                    this._printer.printText(ch);
                } else {
                    printXMLChar(ch);
                }
                index++;
            }
            return;
        }
        index = 0;
        while (index < length) {
            ch = text.charAt(index);
            if (!XML11Char.isXML11Valid(ch)) {
                index++;
                if (index < length) {
                    surrogates(ch, text.charAt(index), true);
                } else {
                    fatalError("The character '" + ch + "' is an invalid XML character");
                }
            } else if (unescaped && XML11Char.isXML11ValidLiteral(ch)) {
                this._printer.printText(ch);
            } else {
                printXMLChar(ch);
            }
            index++;
        }
    }

    protected void printText(char[] chars, int start, int length, boolean preserveSpace, boolean unescaped) throws IOException {
        int length2;
        int start2;
        char ch;
        if (preserveSpace) {
            length2 = length;
            start2 = start;
            while (true) {
                length = length2 - 1;
                if (length2 <= 0) {
                    start = start2;
                    return;
                }
                start = start2 + 1;
                ch = chars[start2];
                if (!XML11Char.isXML11Valid(ch)) {
                    length2 = length - 1;
                    if (length > 0) {
                        start2 = start + 1;
                        surrogates(ch, chars[start], true);
                    } else {
                        fatalError("The character '" + ch + "' is an invalid XML character");
                        start2 = start;
                    }
                } else if (unescaped && XML11Char.isXML11ValidLiteral(ch)) {
                    this._printer.printText(ch);
                    length2 = length;
                    start2 = start;
                } else {
                    printXMLChar(ch);
                    length2 = length;
                    start2 = start;
                }
            }
        } else {
            while (true) {
                length2 = length;
                start2 = start;
                while (true) {
                    length = length2 - 1;
                    if (length2 <= 0) {
                        start = start2;
                        return;
                    }
                    start = start2 + 1;
                    ch = chars[start2];
                    if (!XML11Char.isXML11Valid(ch)) {
                        length2 = length - 1;
                        if (length > 0) {
                            start2 = start + 1;
                            surrogates(ch, chars[start], true);
                        } else {
                            fatalError("The character '" + ch + "' is an invalid XML character");
                            start2 = start;
                        }
                    } else if (unescaped && XML11Char.isXML11ValidLiteral(ch)) {
                        this._printer.printText(ch);
                        length2 = length;
                        start2 = start;
                    } else {
                        printXMLChar(ch);
                    }
                }
                printXMLChar(ch);
            }
        }
    }

    public boolean reset() {
        super.reset();
        return true;
    }
}
