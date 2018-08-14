package mf.org.apache.xerces.impl;

import java.io.EOFException;
import java.io.IOException;
import mf.org.apache.xerces.impl.XMLEntityManager.ScannedEntity;
import mf.org.apache.xerces.util.SymbolTable;
import mf.org.apache.xerces.xni.parser.XMLComponentManager;
import mf.org.apache.xerces.xni.parser.XMLConfigurationException;
import mf.org.apache.xerces.xni.parser.XMLInputSource;

public class XMLVersionDetector {
    protected static final String ENTITY_MANAGER = "http://apache.org/xml/properties/internal/entity-manager";
    protected static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
    protected static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
    private static final char[] XML11_VERSION = new char[]{'1', '.', '1'};
    protected static final String fVersionSymbol = "version".intern();
    protected static final String fXMLSymbol = "[xml]".intern();
    protected String fEncoding = null;
    protected XMLEntityManager fEntityManager;
    protected XMLErrorReporter fErrorReporter;
    private final char[] fExpectedVersionString = new char[]{'<', '?', 'x', 'm', 'l', ' ', 'v', 'e', 'r', 's', 'i', 'o', 'n', '=', ' ', ' ', ' ', ' ', ' '};
    protected SymbolTable fSymbolTable;

    public void reset(XMLComponentManager componentManager) throws XMLConfigurationException {
        this.fSymbolTable = (SymbolTable) componentManager.getProperty("http://apache.org/xml/properties/internal/symbol-table");
        this.fErrorReporter = (XMLErrorReporter) componentManager.getProperty("http://apache.org/xml/properties/internal/error-reporter");
        this.fEntityManager = (XMLEntityManager) componentManager.getProperty(ENTITY_MANAGER);
        for (int i = 14; i < this.fExpectedVersionString.length; i++) {
            this.fExpectedVersionString[i] = ' ';
        }
    }

    public void startDocumentParsing(XMLEntityHandler scanner, short version) {
        if (version == (short) 1) {
            this.fEntityManager.setScannerVersion((short) 1);
        } else {
            this.fEntityManager.setScannerVersion((short) 2);
        }
        this.fErrorReporter.setDocumentLocator(this.fEntityManager.getEntityScanner());
        this.fEntityManager.setEntityHandler(scanner);
        scanner.startEntity(fXMLSymbol, this.fEntityManager.getCurrentResourceIdentifier(), this.fEncoding, null);
    }

    public short determineDocVersion(XMLInputSource inputSource) throws IOException {
        short s = (short) 2;
        this.fEncoding = this.fEntityManager.setupCurrentEntity(fXMLSymbol, inputSource, false, true);
        this.fEntityManager.setScannerVersion((short) 1);
        XMLEntityScanner scanner = this.fEntityManager.getEntityScanner();
        try {
            if (!scanner.skipString("<?xml")) {
                return (short) 1;
            }
            if (!scanner.skipDeclSpaces()) {
                fixupCurrentEntity(this.fEntityManager, this.fExpectedVersionString, 5);
                return (short) 1;
            } else if (scanner.skipString("version")) {
                scanner.skipDeclSpaces();
                if (scanner.peekChar() != 61) {
                    fixupCurrentEntity(this.fEntityManager, this.fExpectedVersionString, 13);
                    return (short) 1;
                }
                scanner.scanChar();
                scanner.skipDeclSpaces();
                this.fExpectedVersionString[14] = (char) scanner.scanChar();
                for (int versionPos = 0; versionPos < XML11_VERSION.length; versionPos++) {
                    this.fExpectedVersionString[versionPos + 15] = (char) scanner.scanChar();
                }
                this.fExpectedVersionString[18] = (char) scanner.scanChar();
                fixupCurrentEntity(this.fEntityManager, this.fExpectedVersionString, 19);
                int matched = 0;
                while (matched < XML11_VERSION.length && this.fExpectedVersionString[matched + 15] == XML11_VERSION[matched]) {
                    matched++;
                }
                if (matched != XML11_VERSION.length) {
                    s = (short) 1;
                }
                return s;
            } else {
                fixupCurrentEntity(this.fEntityManager, this.fExpectedVersionString, 6);
                return (short) 1;
            }
        } catch (Exception e) {
            this.fErrorReporter.reportError(e.getDomain(), e.getKey(), e.getArguments(), (short) 2, e);
            return (short) -1;
        } catch (Exception e2) {
            this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "CharConversionFailure", null, (short) 2, e2);
            return (short) -1;
        } catch (EOFException e3) {
            this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "PrematureEOF", null, (short) 2);
            return (short) -1;
        }
    }

    private void fixupCurrentEntity(XMLEntityManager manager, char[] scannedChars, int length) {
        ScannedEntity currentEntity = manager.getCurrentEntity();
        if ((currentEntity.count - currentEntity.position) + length > currentEntity.ch.length) {
            char[] tempCh = currentEntity.ch;
            currentEntity.ch = new char[(((currentEntity.count + length) - currentEntity.position) + 1)];
            System.arraycopy(tempCh, 0, currentEntity.ch, 0, tempCh.length);
        }
        if (currentEntity.position < length) {
            System.arraycopy(currentEntity.ch, currentEntity.position, currentEntity.ch, length, currentEntity.count - currentEntity.position);
            currentEntity.count += length - currentEntity.position;
        } else {
            for (int i = length; i < currentEntity.position; i++) {
                currentEntity.ch[i] = ' ';
            }
        }
        System.arraycopy(scannedChars, 0, currentEntity.ch, 0, length);
        currentEntity.position = 0;
        currentEntity.baseCharOffset = 0;
        currentEntity.startPosition = 0;
        currentEntity.lineNumber = 1;
        currentEntity.columnNumber = 1;
    }
}
