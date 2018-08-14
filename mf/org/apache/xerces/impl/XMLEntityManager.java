package mf.org.apache.xerces.impl;

import com.github.lzyzsd.jsbridge.BridgeUtil;
import cz.msebera.android.httpclient.protocol.HTTP;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map.Entry;
import java.util.Stack;
import java.util.StringTokenizer;
import mf.org.apache.xerces.impl.io.ASCIIReader;
import mf.org.apache.xerces.impl.io.Latin1Reader;
import mf.org.apache.xerces.impl.io.UCSReader;
import mf.org.apache.xerces.impl.io.UTF16Reader;
import mf.org.apache.xerces.impl.io.UTF8Reader;
import mf.org.apache.xerces.impl.validation.ValidationManager;
import mf.org.apache.xerces.util.AugmentationsImpl;
import mf.org.apache.xerces.util.EncodingMap;
import mf.org.apache.xerces.util.HTTPInputSource;
import mf.org.apache.xerces.util.SecurityManager;
import mf.org.apache.xerces.util.SymbolTable;
import mf.org.apache.xerces.util.URI;
import mf.org.apache.xerces.util.URI.MalformedURIException;
import mf.org.apache.xerces.util.XMLChar;
import mf.org.apache.xerces.util.XMLEntityDescriptionImpl;
import mf.org.apache.xerces.util.XMLResourceIdentifierImpl;
import mf.org.apache.xerces.xni.Augmentations;
import mf.org.apache.xerces.xni.XMLResourceIdentifier;
import mf.org.apache.xerces.xni.XNIException;
import mf.org.apache.xerces.xni.parser.XMLComponent;
import mf.org.apache.xerces.xni.parser.XMLComponentManager;
import mf.org.apache.xerces.xni.parser.XMLConfigurationException;
import mf.org.apache.xerces.xni.parser.XMLEntityResolver;
import mf.org.apache.xerces.xni.parser.XMLInputSource;

public class XMLEntityManager implements XMLComponent, XMLEntityResolver {
    protected static final String ALLOW_JAVA_ENCODINGS = "http://apache.org/xml/features/allow-java-encodings";
    protected static final String BUFFER_SIZE = "http://apache.org/xml/properties/input-buffer-size";
    private static final boolean DEBUG_BUFFER = false;
    private static final boolean DEBUG_ENCODINGS = false;
    private static final boolean DEBUG_ENTITIES = false;
    private static final boolean DEBUG_RESOLVER = false;
    public static final int DEFAULT_BUFFER_SIZE = 2048;
    public static final int DEFAULT_INTERNAL_BUFFER_SIZE = 512;
    public static final int DEFAULT_XMLDECL_BUFFER_SIZE = 64;
    private static final String DTDEntity = "[dtd]".intern();
    protected static final String ENTITY_RESOLVER = "http://apache.org/xml/properties/internal/entity-resolver";
    protected static final String ERROR_REPORTER = "http://apache.org/xml/properties/internal/error-reporter";
    protected static final String EXTERNAL_GENERAL_ENTITIES = "http://xml.org/sax/features/external-general-entities";
    protected static final String EXTERNAL_PARAMETER_ENTITIES = "http://xml.org/sax/features/external-parameter-entities";
    private static final Boolean[] FEATURE_DEFAULTS;
    private static PrivilegedAction GET_USER_DIR_SYSTEM_PROPERTY = new C46261();
    protected static final String PARSER_SETTINGS = "http://apache.org/xml/features/internal/parser-settings";
    private static final Object[] PROPERTY_DEFAULTS;
    private static final String[] RECOGNIZED_FEATURES = new String[]{VALIDATION, EXTERNAL_GENERAL_ENTITIES, EXTERNAL_PARAMETER_ENTITIES, ALLOW_JAVA_ENCODINGS, WARN_ON_DUPLICATE_ENTITYDEF, STANDARD_URI_CONFORMANT};
    private static final String[] RECOGNIZED_PROPERTIES = new String[]{"http://apache.org/xml/properties/internal/symbol-table", "http://apache.org/xml/properties/internal/error-reporter", "http://apache.org/xml/properties/internal/entity-resolver", VALIDATION_MANAGER, BUFFER_SIZE, SECURITY_MANAGER};
    protected static final String SECURITY_MANAGER = "http://apache.org/xml/properties/security-manager";
    protected static final String STANDARD_URI_CONFORMANT = "http://apache.org/xml/features/standard-uri-conformant";
    protected static final String SYMBOL_TABLE = "http://apache.org/xml/properties/internal/symbol-table";
    protected static final String VALIDATION = "http://xml.org/sax/features/validation";
    protected static final String VALIDATION_MANAGER = "http://apache.org/xml/properties/internal/validation-manager";
    protected static final String WARN_ON_DUPLICATE_ENTITYDEF = "http://apache.org/xml/features/warn-on-duplicate-entitydef";
    private static final String XMLEntity = "[xml]".intern();
    private static final char[] gAfterEscaping1 = new char[128];
    private static final char[] gAfterEscaping2 = new char[128];
    private static final char[] gHexChs = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private static final boolean[] gNeedEscaping = new boolean[128];
    private static String gUserDir;
    private static URI gUserDirURI;
    protected boolean fAllowJavaEncodings;
    protected int fBufferSize;
    private final CharacterBufferPool fCharacterBufferPool;
    protected ScannedEntity fCurrentEntity;
    protected Hashtable fDeclaredEntities;
    protected final Hashtable fEntities;
    private final Augmentations fEntityAugs;
    protected int fEntityExpansionCount;
    protected int fEntityExpansionLimit;
    protected XMLEntityHandler fEntityHandler;
    protected XMLEntityResolver fEntityResolver;
    protected XMLEntityScanner fEntityScanner;
    protected final Stack fEntityStack;
    protected XMLErrorReporter fErrorReporter;
    protected boolean fExternalGeneralEntities;
    protected boolean fExternalParameterEntities;
    protected boolean fHasPEReferences;
    protected boolean fInExternalSubset;
    private final ByteBufferPool fLargeByteBufferPool;
    protected Stack fReaderStack;
    private final XMLResourceIdentifierImpl fResourceIdentifier;
    protected SecurityManager fSecurityManager;
    private final ByteBufferPool fSmallByteBufferPool;
    protected boolean fStandalone;
    protected boolean fStrictURI;
    protected SymbolTable fSymbolTable;
    private byte[] fTempByteBuffer;
    protected boolean fValidation;
    protected ValidationManager fValidationManager;
    protected boolean fWarnDuplicateEntityDef;
    protected XMLEntityScanner fXML10EntityScanner;
    protected XMLEntityScanner fXML11EntityScanner;

    class C46261 implements PrivilegedAction {
        C46261() {
        }

        public Object run() {
            return System.getProperty("user.dir");
        }
    }

    private static final class ByteBufferPool {
        private static final int DEFAULT_POOL_SIZE = 3;
        private int fBufferSize;
        private byte[][] fByteBufferPool;
        private int fDepth;
        private int fPoolSize;

        public ByteBufferPool(int bufferSize) {
            this(3, bufferSize);
        }

        public ByteBufferPool(int poolSize, int bufferSize) {
            this.fPoolSize = poolSize;
            this.fBufferSize = bufferSize;
            this.fByteBufferPool = new byte[this.fPoolSize][];
            this.fDepth = 0;
        }

        public byte[] getBuffer() {
            if (this.fDepth <= 0) {
                return new byte[this.fBufferSize];
            }
            byte[][] bArr = this.fByteBufferPool;
            int i = this.fDepth - 1;
            this.fDepth = i;
            return bArr[i];
        }

        public void returnBuffer(byte[] buffer) {
            if (this.fDepth < this.fByteBufferPool.length) {
                byte[][] bArr = this.fByteBufferPool;
                int i = this.fDepth;
                this.fDepth = i + 1;
                bArr[i] = buffer;
            }
        }

        public void setBufferSize(int bufferSize) {
            this.fBufferSize = bufferSize;
            this.fByteBufferPool = new byte[this.fPoolSize][];
            this.fDepth = 0;
        }
    }

    private static final class CharacterBuffer {
        private final char[] ch;
        private final boolean isExternal;

        public CharacterBuffer(boolean isExternal, int size) {
            this.isExternal = isExternal;
            this.ch = new char[size];
        }
    }

    private static final class CharacterBufferPool {
        private static final int DEFAULT_POOL_SIZE = 3;
        private CharacterBuffer[] fExternalBufferPool;
        private int fExternalBufferSize;
        private int fExternalTop;
        private CharacterBuffer[] fInternalBufferPool;
        private int fInternalBufferSize;
        private int fInternalTop;
        private int fPoolSize;

        public CharacterBufferPool(int externalBufferSize, int internalBufferSize) {
            this(3, externalBufferSize, internalBufferSize);
        }

        public CharacterBufferPool(int poolSize, int externalBufferSize, int internalBufferSize) {
            this.fExternalBufferSize = externalBufferSize;
            this.fInternalBufferSize = internalBufferSize;
            this.fPoolSize = poolSize;
            init();
        }

        private void init() {
            this.fInternalBufferPool = new CharacterBuffer[this.fPoolSize];
            this.fExternalBufferPool = new CharacterBuffer[this.fPoolSize];
            this.fInternalTop = -1;
            this.fExternalTop = -1;
        }

        public CharacterBuffer getBuffer(boolean external) {
            CharacterBuffer[] characterBufferArr;
            int i;
            if (external) {
                if (this.fExternalTop <= -1) {
                    return new CharacterBuffer(true, this.fExternalBufferSize);
                }
                characterBufferArr = this.fExternalBufferPool;
                i = this.fExternalTop;
                this.fExternalTop = i - 1;
                return characterBufferArr[i];
            } else if (this.fInternalTop <= -1) {
                return new CharacterBuffer(false, this.fInternalBufferSize);
            } else {
                characterBufferArr = this.fInternalBufferPool;
                i = this.fInternalTop;
                this.fInternalTop = i - 1;
                return characterBufferArr[i];
            }
        }

        public void returnBuffer(CharacterBuffer buffer) {
            CharacterBuffer[] characterBufferArr;
            int i;
            if (buffer.isExternal) {
                if (this.fExternalTop < this.fExternalBufferPool.length - 1) {
                    characterBufferArr = this.fExternalBufferPool;
                    i = this.fExternalTop + 1;
                    this.fExternalTop = i;
                    characterBufferArr[i] = buffer;
                }
            } else if (this.fInternalTop < this.fInternalBufferPool.length - 1) {
                characterBufferArr = this.fInternalBufferPool;
                i = this.fInternalTop + 1;
                this.fInternalTop = i;
                characterBufferArr[i] = buffer;
            }
        }

        public void setExternalBufferSize(int bufferSize) {
            this.fExternalBufferSize = bufferSize;
            this.fExternalBufferPool = new CharacterBuffer[this.fPoolSize];
            this.fExternalTop = -1;
        }
    }

    private static class EncodingInfo {
        public static final EncodingInfo EBCDIC = new EncodingInfo("CP037", null, false);
        public static final EncodingInfo UCS_4_BIG_ENDIAN = new EncodingInfo("ISO-10646-UCS-4", Boolean.TRUE, false);
        public static final EncodingInfo UCS_4_LITTLE_ENDIAN = new EncodingInfo("ISO-10646-UCS-4", Boolean.FALSE, false);
        public static final EncodingInfo UCS_4_UNUSUAL_BYTE_ORDER = new EncodingInfo("ISO-10646-UCS-4", null, false);
        public static final EncodingInfo UTF_16_BIG_ENDIAN = new EncodingInfo("UTF-16", Boolean.TRUE, false);
        public static final EncodingInfo UTF_16_BIG_ENDIAN_WITH_BOM = new EncodingInfo("UTF-16", Boolean.TRUE, true);
        public static final EncodingInfo UTF_16_LITTLE_ENDIAN = new EncodingInfo("UTF-16", Boolean.FALSE, false);
        public static final EncodingInfo UTF_16_LITTLE_ENDIAN_WITH_BOM = new EncodingInfo("UTF-16", Boolean.FALSE, true);
        public static final EncodingInfo UTF_8 = new EncodingInfo("UTF-8", null, false);
        public static final EncodingInfo UTF_8_WITH_BOM = new EncodingInfo("UTF-8", null, true);
        public final String encoding;
        public final boolean hasBOM;
        public final Boolean isBigEndian;

        private EncodingInfo(String encoding, Boolean isBigEndian, boolean hasBOM) {
            this.encoding = encoding;
            this.isBigEndian = isBigEndian;
            this.hasBOM = hasBOM;
        }
    }

    public static abstract class Entity {
        public boolean inExternalSubset;
        public String name;

        public abstract boolean isExternal();

        public abstract boolean isUnparsed();

        public Entity() {
            clear();
        }

        public Entity(String name, boolean inExternalSubset) {
            this.name = name;
            this.inExternalSubset = inExternalSubset;
        }

        public boolean isEntityDeclInExternalSubset() {
            return this.inExternalSubset;
        }

        public void clear() {
            this.name = null;
            this.inExternalSubset = false;
        }

        public void setValues(Entity entity) {
            this.name = entity.name;
            this.inExternalSubset = entity.inExternalSubset;
        }
    }

    protected static class ExternalEntity extends Entity {
        public XMLResourceIdentifier entityLocation;
        public String notation;

        public ExternalEntity() {
            clear();
        }

        public ExternalEntity(String name, XMLResourceIdentifier entityLocation, String notation, boolean inExternalSubset) {
            super(name, inExternalSubset);
            this.entityLocation = entityLocation;
            this.notation = notation;
        }

        public final boolean isExternal() {
            return true;
        }

        public final boolean isUnparsed() {
            return this.notation != null;
        }

        public void clear() {
            super.clear();
            this.entityLocation = null;
            this.notation = null;
        }

        public void setValues(Entity entity) {
            super.setValues(entity);
            this.entityLocation = null;
            this.notation = null;
        }

        public void setValues(ExternalEntity entity) {
            super.setValues(entity);
            this.entityLocation = entity.entityLocation;
            this.notation = entity.notation;
        }
    }

    protected static class InternalEntity extends Entity {
        public String text;

        public InternalEntity() {
            clear();
        }

        public InternalEntity(String name, String text, boolean inExternalSubset) {
            super(name, inExternalSubset);
            this.text = text;
        }

        public final boolean isExternal() {
            return false;
        }

        public final boolean isUnparsed() {
            return false;
        }

        public void clear() {
            super.clear();
            this.text = null;
        }

        public void setValues(Entity entity) {
            super.setValues(entity);
            this.text = null;
        }

        public void setValues(InternalEntity entity) {
            super.setValues(entity);
            this.text = entity.text;
        }
    }

    protected final class RewindableInputStream extends InputStream {
        private byte[] fData = new byte[64];
        private int fEndOffset;
        private InputStream fInputStream;
        private int fLength;
        private int fMark;
        private int fOffset;
        private int fStartOffset;

        public RewindableInputStream(InputStream is) {
            this.fInputStream = is;
            this.fStartOffset = 0;
            this.fEndOffset = -1;
            this.fOffset = 0;
            this.fLength = 0;
            this.fMark = 0;
        }

        public void setStartOffset(int offset) {
            this.fStartOffset = offset;
        }

        public void rewind() {
            this.fOffset = this.fStartOffset;
        }

        public int readAndBuffer() throws IOException {
            if (this.fOffset == this.fData.length) {
                byte[] newData = new byte[(this.fOffset << 1)];
                System.arraycopy(this.fData, 0, newData, 0, this.fOffset);
                this.fData = newData;
            }
            int b = this.fInputStream.read();
            if (b == -1) {
                this.fEndOffset = this.fOffset;
                return -1;
            }
            byte[] bArr = this.fData;
            int i = this.fLength;
            this.fLength = i + 1;
            bArr[i] = (byte) b;
            this.fOffset++;
            return b & 255;
        }

        public int read() throws IOException {
            if (this.fOffset < this.fLength) {
                byte[] bArr = this.fData;
                int i = this.fOffset;
                this.fOffset = i + 1;
                return bArr[i] & 255;
            } else if (this.fOffset == this.fEndOffset) {
                return -1;
            } else {
                if (XMLEntityManager.this.fCurrentEntity.mayReadChunks) {
                    return this.fInputStream.read();
                }
                return readAndBuffer();
            }
        }

        public int read(byte[] b, int off, int len) throws IOException {
            int bytesLeft = this.fLength - this.fOffset;
            if (bytesLeft != 0) {
                if (len >= bytesLeft) {
                    len = bytesLeft;
                } else if (len <= 0) {
                    return 0;
                }
                if (b != null) {
                    System.arraycopy(this.fData, this.fOffset, b, off, len);
                }
                this.fOffset += len;
                return len;
            } else if (this.fOffset == this.fEndOffset) {
                return -1;
            } else {
                if (XMLEntityManager.this.fCurrentEntity.mayReadChunks) {
                    return this.fInputStream.read(b, off, len);
                }
                int returnedVal = readAndBuffer();
                if (returnedVal == -1) {
                    this.fEndOffset = this.fOffset;
                    return -1;
                }
                b[off] = (byte) returnedVal;
                return 1;
            }
        }

        public long skip(long n) throws IOException {
            if (n <= 0) {
                return 0;
            }
            int bytesLeft = this.fLength - this.fOffset;
            if (bytesLeft == 0) {
                if (this.fOffset != this.fEndOffset) {
                    return this.fInputStream.skip(n);
                }
                return 0;
            } else if (n <= ((long) bytesLeft)) {
                this.fOffset = (int) (((long) this.fOffset) + n);
                return n;
            } else {
                this.fOffset += bytesLeft;
                if (this.fOffset == this.fEndOffset) {
                    return (long) bytesLeft;
                }
                return this.fInputStream.skip(n - ((long) bytesLeft)) + ((long) bytesLeft);
            }
        }

        public int available() throws IOException {
            int bytesLeft = this.fLength - this.fOffset;
            if (bytesLeft != 0) {
                return bytesLeft;
            }
            if (this.fOffset == this.fEndOffset) {
                return -1;
            }
            if (XMLEntityManager.this.fCurrentEntity.mayReadChunks) {
                return this.fInputStream.available();
            }
            return 0;
        }

        public void mark(int howMuch) {
            this.fMark = this.fOffset;
        }

        public void reset() {
            this.fOffset = this.fMark;
        }

        public boolean markSupported() {
            return true;
        }

        public void close() throws IOException {
            if (this.fInputStream != null) {
                this.fInputStream.close();
                this.fInputStream = null;
            }
        }
    }

    public class ScannedEntity extends Entity {
        public int baseCharOffset;
        public char[] ch = null;
        public int columnNumber = 1;
        public int count;
        public String encoding;
        public XMLResourceIdentifier entityLocation;
        boolean externallySpecifiedEncoding = false;
        private byte[] fByteBuffer;
        private CharacterBuffer fCharacterBuffer;
        public boolean isExternal;
        public int lineNumber = 1;
        public boolean literal;
        public boolean mayReadChunks;
        public int position;
        public Reader reader;
        public int startPosition;
        public InputStream stream;
        public String xmlVersion = "1.0";

        public ScannedEntity(String name, XMLResourceIdentifier entityLocation, InputStream stream, Reader reader, byte[] byteBuffer, String encoding, boolean literal, boolean mayReadChunks, boolean isExternal) {
            super(name, XMLEntityManager.this.fInExternalSubset);
            this.entityLocation = entityLocation;
            this.stream = stream;
            this.reader = reader;
            this.encoding = encoding;
            this.literal = literal;
            this.mayReadChunks = mayReadChunks;
            this.isExternal = isExternal;
            this.fCharacterBuffer = XMLEntityManager.this.fCharacterBufferPool.getBuffer(isExternal);
            this.ch = this.fCharacterBuffer.ch;
            this.fByteBuffer = byteBuffer;
        }

        public final boolean isExternal() {
            return this.isExternal;
        }

        public final boolean isUnparsed() {
            return false;
        }

        public void setReader(InputStream stream, String encoding, Boolean isBigEndian) throws IOException {
            XMLEntityManager.this.fTempByteBuffer = this.fByteBuffer;
            this.reader = XMLEntityManager.this.createReader(stream, encoding, isBigEndian);
            this.fByteBuffer = XMLEntityManager.this.fTempByteBuffer;
        }

        public String getExpandedSystemId() {
            for (int i = XMLEntityManager.this.fEntityStack.size() - 1; i >= 0; i--) {
                ScannedEntity externalEntity = (ScannedEntity) XMLEntityManager.this.fEntityStack.elementAt(i);
                if (externalEntity.entityLocation != null && externalEntity.entityLocation.getExpandedSystemId() != null) {
                    return externalEntity.entityLocation.getExpandedSystemId();
                }
            }
            return null;
        }

        public String getLiteralSystemId() {
            for (int i = XMLEntityManager.this.fEntityStack.size() - 1; i >= 0; i--) {
                ScannedEntity externalEntity = (ScannedEntity) XMLEntityManager.this.fEntityStack.elementAt(i);
                if (externalEntity.entityLocation != null && externalEntity.entityLocation.getLiteralSystemId() != null) {
                    return externalEntity.entityLocation.getLiteralSystemId();
                }
            }
            return null;
        }

        public int getLineNumber() {
            for (int i = XMLEntityManager.this.fEntityStack.size() - 1; i >= 0; i--) {
                ScannedEntity firstExternalEntity = (ScannedEntity) XMLEntityManager.this.fEntityStack.elementAt(i);
                if (firstExternalEntity.isExternal()) {
                    return firstExternalEntity.lineNumber;
                }
            }
            return -1;
        }

        public int getColumnNumber() {
            for (int i = XMLEntityManager.this.fEntityStack.size() - 1; i >= 0; i--) {
                ScannedEntity firstExternalEntity = (ScannedEntity) XMLEntityManager.this.fEntityStack.elementAt(i);
                if (firstExternalEntity.isExternal()) {
                    return firstExternalEntity.columnNumber;
                }
            }
            return -1;
        }

        public int getCharacterOffset() {
            for (int i = XMLEntityManager.this.fEntityStack.size() - 1; i >= 0; i--) {
                ScannedEntity firstExternalEntity = (ScannedEntity) XMLEntityManager.this.fEntityStack.elementAt(i);
                if (firstExternalEntity.isExternal()) {
                    return firstExternalEntity.baseCharOffset + (firstExternalEntity.position - firstExternalEntity.startPosition);
                }
            }
            return -1;
        }

        public String getEncoding() {
            for (int i = XMLEntityManager.this.fEntityStack.size() - 1; i >= 0; i--) {
                ScannedEntity firstExternalEntity = (ScannedEntity) XMLEntityManager.this.fEntityStack.elementAt(i);
                if (firstExternalEntity.isExternal()) {
                    return firstExternalEntity.encoding;
                }
            }
            return null;
        }

        public String getXMLVersion() {
            for (int i = XMLEntityManager.this.fEntityStack.size() - 1; i >= 0; i--) {
                ScannedEntity firstExternalEntity = (ScannedEntity) XMLEntityManager.this.fEntityStack.elementAt(i);
                if (firstExternalEntity.isExternal()) {
                    return firstExternalEntity.xmlVersion;
                }
            }
            return null;
        }

        public boolean isEncodingExternallySpecified() {
            return this.externallySpecifiedEncoding;
        }

        public void setEncodingExternallySpecified(boolean value) {
            this.externallySpecifiedEncoding = value;
        }

        public String toString() {
            StringBuffer str = new StringBuffer();
            str.append("name=\"").append(this.name).append('\"');
            str.append(",ch=");
            str.append(this.ch);
            str.append(",position=").append(this.position);
            str.append(",count=").append(this.count);
            str.append(",baseCharOffset=").append(this.baseCharOffset);
            str.append(",startPosition=").append(this.startPosition);
            return str.toString();
        }
    }

    static {
        Boolean[] boolArr = new Boolean[6];
        boolArr[1] = Boolean.TRUE;
        boolArr[2] = Boolean.TRUE;
        boolArr[3] = Boolean.FALSE;
        boolArr[4] = Boolean.FALSE;
        boolArr[5] = Boolean.FALSE;
        FEATURE_DEFAULTS = boolArr;
        Object[] objArr = new Object[6];
        objArr[4] = new Integer(2048);
        PROPERTY_DEFAULTS = objArr;
        for (int i = 0; i <= 31; i++) {
            gNeedEscaping[i] = true;
            gAfterEscaping1[i] = gHexChs[i >> 4];
            gAfterEscaping2[i] = gHexChs[i & 15];
        }
        gNeedEscaping[127] = true;
        gAfterEscaping1[127] = '7';
        gAfterEscaping2[127] = 'F';
        for (char ch : new char[]{' ', '<', '>', '#', '%', '\"', '{', '}', '|', '\\', '^', '~', '[', ']', '`'}) {
            gNeedEscaping[ch] = true;
            gAfterEscaping1[ch] = gHexChs[ch >> 4];
            gAfterEscaping2[ch] = gHexChs[ch & 15];
        }
    }

    public XMLEntityManager() {
        this(null);
    }

    public XMLEntityManager(XMLEntityManager entityManager) {
        Hashtable hashtable = null;
        this.fExternalGeneralEntities = true;
        this.fExternalParameterEntities = true;
        this.fBufferSize = 2048;
        this.fSecurityManager = null;
        this.fInExternalSubset = false;
        this.fEntityExpansionLimit = 0;
        this.fEntityExpansionCount = 0;
        this.fEntities = new Hashtable();
        this.fEntityStack = new Stack();
        this.fResourceIdentifier = new XMLResourceIdentifierImpl();
        this.fEntityAugs = new AugmentationsImpl();
        this.fSmallByteBufferPool = new ByteBufferPool(this.fBufferSize);
        this.fLargeByteBufferPool = new ByteBufferPool(this.fBufferSize << 1);
        this.fTempByteBuffer = null;
        this.fCharacterBufferPool = new CharacterBufferPool(this.fBufferSize, 512);
        this.fReaderStack = new Stack();
        if (entityManager != null) {
            hashtable = entityManager.getDeclaredEntities();
        }
        this.fDeclaredEntities = hashtable;
        setScannerVersion((short) 1);
    }

    public void setStandalone(boolean standalone) {
        this.fStandalone = standalone;
    }

    public boolean isStandalone() {
        return this.fStandalone;
    }

    final void notifyHasPEReferences() {
        this.fHasPEReferences = true;
    }

    final boolean hasPEReferences() {
        return this.fHasPEReferences;
    }

    public void setEntityHandler(XMLEntityHandler entityHandler) {
        this.fEntityHandler = entityHandler;
    }

    public XMLResourceIdentifier getCurrentResourceIdentifier() {
        return this.fResourceIdentifier;
    }

    public ScannedEntity getCurrentEntity() {
        return this.fCurrentEntity;
    }

    public void addInternalEntity(String name, String text) {
        if (!this.fEntities.containsKey(name)) {
            this.fEntities.put(name, new InternalEntity(name, text, this.fInExternalSubset));
        } else if (this.fWarnDuplicateEntityDef) {
            this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_DUPLICATE_ENTITY_DEFINITION", new Object[]{name}, (short) 0);
        }
    }

    public void addExternalEntity(String name, String publicId, String literalSystemId, String baseSystemId) throws IOException {
        if (!this.fEntities.containsKey(name)) {
            if (baseSystemId == null) {
                int size = this.fEntityStack.size();
                if (!(size != 0 || this.fCurrentEntity == null || this.fCurrentEntity.entityLocation == null)) {
                    baseSystemId = this.fCurrentEntity.entityLocation.getExpandedSystemId();
                }
                for (int i = size - 1; i >= 0; i--) {
                    ScannedEntity externalEntity = (ScannedEntity) this.fEntityStack.elementAt(i);
                    if (externalEntity.entityLocation != null && externalEntity.entityLocation.getExpandedSystemId() != null) {
                        baseSystemId = externalEntity.entityLocation.getExpandedSystemId();
                        break;
                    }
                }
            }
            this.fEntities.put(name, new ExternalEntity(name, new XMLEntityDescriptionImpl(name, publicId, literalSystemId, baseSystemId, expandSystemId(literalSystemId, baseSystemId, false)), null, this.fInExternalSubset));
        } else if (this.fWarnDuplicateEntityDef) {
            this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_DUPLICATE_ENTITY_DEFINITION", new Object[]{name}, (short) 0);
        }
    }

    public boolean isExternalEntity(String entityName) {
        Entity entity = (Entity) this.fEntities.get(entityName);
        if (entity == null) {
            return false;
        }
        return entity.isExternal();
    }

    public boolean isEntityDeclInExternalSubset(String entityName) {
        Entity entity = (Entity) this.fEntities.get(entityName);
        if (entity == null) {
            return false;
        }
        return entity.isEntityDeclInExternalSubset();
    }

    public void addUnparsedEntity(String name, String publicId, String systemId, String baseSystemId, String notation) {
        if (!this.fEntities.containsKey(name)) {
            this.fEntities.put(name, new ExternalEntity(name, new XMLEntityDescriptionImpl(name, publicId, systemId, baseSystemId, null), notation, this.fInExternalSubset));
        } else if (this.fWarnDuplicateEntityDef) {
            this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "MSG_DUPLICATE_ENTITY_DEFINITION", new Object[]{name}, (short) 0);
        }
    }

    public boolean isUnparsedEntity(String entityName) {
        Entity entity = (Entity) this.fEntities.get(entityName);
        if (entity == null) {
            return false;
        }
        return entity.isUnparsed();
    }

    public boolean isDeclaredEntity(String entityName) {
        return ((Entity) this.fEntities.get(entityName)) != null;
    }

    public XMLInputSource resolveEntity(XMLResourceIdentifier resourceIdentifier) throws IOException, XNIException {
        if (resourceIdentifier == null) {
            return null;
        }
        boolean needExpand;
        String publicId = resourceIdentifier.getPublicId();
        String literalSystemId = resourceIdentifier.getLiteralSystemId();
        String baseSystemId = resourceIdentifier.getBaseSystemId();
        String expandedSystemId = resourceIdentifier.getExpandedSystemId();
        if (expandedSystemId == null) {
            needExpand = true;
        } else {
            needExpand = false;
        }
        if (!(baseSystemId != null || this.fCurrentEntity == null || this.fCurrentEntity.entityLocation == null)) {
            baseSystemId = this.fCurrentEntity.entityLocation.getExpandedSystemId();
            if (baseSystemId != null) {
                needExpand = true;
            }
        }
        XMLInputSource xmlInputSource = null;
        if (this.fEntityResolver != null) {
            if (needExpand) {
                expandedSystemId = expandSystemId(literalSystemId, baseSystemId, false);
            }
            resourceIdentifier.setBaseSystemId(baseSystemId);
            resourceIdentifier.setExpandedSystemId(expandedSystemId);
            xmlInputSource = this.fEntityResolver.resolveEntity(resourceIdentifier);
        }
        if (xmlInputSource == null) {
            return new XMLInputSource(publicId, literalSystemId, baseSystemId);
        }
        return xmlInputSource;
    }

    public void startEntity(String entityName, boolean literal) throws IOException, XNIException {
        Entity entity = (Entity) this.fEntities.get(entityName);
        if (entity != null) {
            ExternalEntity externalEntity;
            String extLitSysId;
            String extBaseSysId;
            XMLInputSource xmlInputSource;
            boolean external = entity.isExternal();
            if (external && (this.fValidationManager == null || !this.fValidationManager.isCachedDTD())) {
                boolean unparsed = entity.isUnparsed();
                boolean parameter = entityName.startsWith("%");
                boolean general = !parameter;
                if (unparsed || ((general && !this.fExternalGeneralEntities) || (parameter && !this.fExternalParameterEntities))) {
                    if (this.fEntityHandler != null) {
                        this.fResourceIdentifier.clear();
                        externalEntity = (ExternalEntity) entity;
                        extLitSysId = externalEntity.entityLocation != null ? externalEntity.entityLocation.getLiteralSystemId() : null;
                        extBaseSysId = externalEntity.entityLocation != null ? externalEntity.entityLocation.getBaseSystemId() : null;
                        this.fResourceIdentifier.setValues(externalEntity.entityLocation != null ? externalEntity.entityLocation.getPublicId() : null, extLitSysId, extBaseSysId, expandSystemId(extLitSysId, extBaseSysId, false));
                        this.fEntityAugs.removeAllItems();
                        this.fEntityAugs.putItem(Constants.ENTITY_SKIPPED, Boolean.TRUE);
                        this.fEntityHandler.startEntity(entityName, this.fResourceIdentifier, null, this.fEntityAugs);
                        this.fEntityAugs.removeAllItems();
                        this.fEntityAugs.putItem(Constants.ENTITY_SKIPPED, Boolean.TRUE);
                        this.fEntityHandler.endEntity(entityName, this.fEntityAugs);
                        return;
                    }
                    return;
                }
            }
            int size = this.fEntityStack.size();
            for (int i = size; i >= 0; i--) {
                Entity activeEntity;
                if (i == size) {
                    activeEntity = this.fCurrentEntity;
                } else {
                    activeEntity = (Entity) this.fEntityStack.elementAt(i);
                }
                if (activeEntity.name == entityName) {
                    StringBuffer stringBuffer = new StringBuffer(entityName);
                    for (int j = i + 1; j < size; j++) {
                        activeEntity = (Entity) this.fEntityStack.elementAt(j);
                        stringBuffer.append(" -> ");
                        stringBuffer.append(activeEntity.name);
                    }
                    stringBuffer.append(" -> ");
                    stringBuffer.append(this.fCurrentEntity.name);
                    stringBuffer.append(" -> ");
                    stringBuffer.append(entityName);
                    this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "RecursiveReference", new Object[]{entityName, stringBuffer.toString()}, (short) 2);
                    if (this.fEntityHandler != null) {
                        this.fResourceIdentifier.clear();
                        if (external) {
                            externalEntity = (ExternalEntity) entity;
                            extLitSysId = externalEntity.entityLocation != null ? externalEntity.entityLocation.getLiteralSystemId() : null;
                            extBaseSysId = externalEntity.entityLocation != null ? externalEntity.entityLocation.getBaseSystemId() : null;
                            this.fResourceIdentifier.setValues(externalEntity.entityLocation != null ? externalEntity.entityLocation.getPublicId() : null, extLitSysId, extBaseSysId, expandSystemId(extLitSysId, extBaseSysId, false));
                        }
                        this.fEntityAugs.removeAllItems();
                        this.fEntityAugs.putItem(Constants.ENTITY_SKIPPED, Boolean.TRUE);
                        this.fEntityHandler.startEntity(entityName, this.fResourceIdentifier, null, this.fEntityAugs);
                        this.fEntityAugs.removeAllItems();
                        this.fEntityAugs.putItem(Constants.ENTITY_SKIPPED, Boolean.TRUE);
                        this.fEntityHandler.endEntity(entityName, this.fEntityAugs);
                        return;
                    }
                    return;
                }
            }
            if (external) {
                xmlInputSource = resolveEntity(((ExternalEntity) entity).entityLocation);
            } else {
                xmlInputSource = new XMLInputSource(null, null, null, new StringReader(((InternalEntity) entity).text), null);
            }
            startEntity(entityName, xmlInputSource, literal, external);
        } else if (this.fEntityHandler != null) {
            this.fResourceIdentifier.clear();
            this.fEntityAugs.removeAllItems();
            this.fEntityAugs.putItem(Constants.ENTITY_SKIPPED, Boolean.TRUE);
            this.fEntityHandler.startEntity(entityName, this.fResourceIdentifier, null, this.fEntityAugs);
            this.fEntityAugs.removeAllItems();
            this.fEntityAugs.putItem(Constants.ENTITY_SKIPPED, Boolean.TRUE);
            this.fEntityHandler.endEntity(entityName, this.fEntityAugs);
        }
    }

    public void startDocumentEntity(XMLInputSource xmlInputSource) throws IOException, XNIException {
        startEntity(XMLEntity, xmlInputSource, false, true);
    }

    public void startDTDEntity(XMLInputSource xmlInputSource) throws IOException, XNIException {
        startEntity(DTDEntity, xmlInputSource, false, true);
    }

    public void startExternalSubset() {
        this.fInExternalSubset = true;
    }

    public void endExternalSubset() {
        this.fInExternalSubset = false;
    }

    public void startEntity(String name, XMLInputSource xmlInputSource, boolean literal, boolean isExternal) throws IOException, XNIException {
        String encoding = setupCurrentEntity(name, xmlInputSource, literal, isExternal);
        if (this.fSecurityManager != null) {
            int i = this.fEntityExpansionCount;
            this.fEntityExpansionCount = i + 1;
            if (i > this.fEntityExpansionLimit) {
                this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "EntityExpansionLimitExceeded", new Object[]{new Integer(this.fEntityExpansionLimit)}, (short) 2);
                this.fEntityExpansionCount = 0;
            }
        }
        if (this.fEntityHandler != null) {
            this.fEntityHandler.startEntity(name, this.fResourceIdentifier, encoding, null);
        }
    }

    public String setupCurrentEntity(String name, XMLInputSource xmlInputSource, boolean literal, boolean isExternal) throws IOException, XNIException {
        String publicId = xmlInputSource.getPublicId();
        String literalSystemId = xmlInputSource.getSystemId();
        String baseSystemId = xmlInputSource.getBaseSystemId();
        String encoding = xmlInputSource.getEncoding();
        boolean encodingExternallySpecified = encoding != null;
        Boolean isBigEndian = null;
        this.fTempByteBuffer = null;
        InputStream stream = null;
        Reader reader = xmlInputSource.getCharacterStream();
        String expandedSystemId = expandSystemId(literalSystemId, baseSystemId, this.fStrictURI);
        if (baseSystemId == null) {
            baseSystemId = expandedSystemId;
        }
        if (reader == null) {
            stream = xmlInputSource.getByteStream();
            if (stream == null) {
                URLConnection connect = new URL(expandedSystemId).openConnection();
                if (connect instanceof HttpURLConnection) {
                    boolean followRedirects = true;
                    if (xmlInputSource instanceof HTTPInputSource) {
                        HttpURLConnection urlConnection = (HttpURLConnection) connect;
                        HTTPInputSource httpInputSource = (HTTPInputSource) xmlInputSource;
                        Iterator propIter = httpInputSource.getHTTPRequestProperties();
                        while (propIter.hasNext()) {
                            Entry entry = (Entry) propIter.next();
                            urlConnection.setRequestProperty((String) entry.getKey(), (String) entry.getValue());
                        }
                        followRedirects = httpInputSource.getFollowHTTPRedirects();
                        if (!followRedirects) {
                            urlConnection.setInstanceFollowRedirects(followRedirects);
                        }
                    }
                    stream = connect.getInputStream();
                    if (followRedirects) {
                        String redirect = connect.getURL().toString();
                        if (!redirect.equals(expandedSystemId)) {
                            literalSystemId = redirect;
                            expandedSystemId = redirect;
                        }
                    }
                } else {
                    stream = connect.getInputStream();
                }
            }
            InputStream rewindableInputStream = new RewindableInputStream(stream);
            stream = rewindableInputStream;
            int count;
            if (encoding == null) {
                byte[] b4 = new byte[4];
                count = 0;
                while (count < 4) {
                    b4[count] = (byte) rewindableInputStream.readAndBuffer();
                    count++;
                }
                if (count == 4) {
                    EncodingInfo info = getEncodingInfo(b4, count);
                    encoding = info.encoding;
                    isBigEndian = info.isBigEndian;
                    stream.reset();
                    if (info.hasBOM) {
                        if (encoding == "UTF-8") {
                            stream.skip(3);
                        } else if (encoding == "UTF-16") {
                            stream.skip(2);
                        }
                    }
                    reader = createReader(stream, encoding, isBigEndian);
                } else {
                    reader = createReader(stream, encoding, null);
                }
            } else {
                encoding = encoding.toUpperCase(Locale.ENGLISH);
                if (encoding.equals("UTF-8")) {
                    int[] b3 = new int[3];
                    count = 0;
                    while (count < 3) {
                        b3[count] = rewindableInputStream.readAndBuffer();
                        if (b3[count] == -1) {
                            break;
                        }
                        count++;
                    }
                    if (count != 3) {
                        stream.reset();
                    } else if (!(b3[0] == 239 && b3[1] == 187 && b3[2] == 191)) {
                        stream.reset();
                    }
                    reader = createReader(stream, "UTF-8", null);
                } else if (encoding.equals("UTF-16")) {
                    b4 = new int[4];
                    count = 0;
                    while (count < 4) {
                        b4[count] = rewindableInputStream.readAndBuffer();
                        if (b4[count] == -1) {
                            break;
                        }
                        count++;
                    }
                    stream.reset();
                    if (count >= 2) {
                        int b0 = b4[0];
                        int b1 = b4[1];
                        if (b0 == 254 && b1 == 255) {
                            isBigEndian = Boolean.TRUE;
                            stream.skip(2);
                        } else if (b0 == 255 && b1 == 254) {
                            isBigEndian = Boolean.FALSE;
                            stream.skip(2);
                        } else if (count == 4) {
                            int b2 = b4[2];
                            int b32 = b4[3];
                            if (b0 == 0 && b1 == 60 && b2 == 0 && b32 == 63) {
                                isBigEndian = Boolean.TRUE;
                            }
                            if (b0 == 60 && b1 == 0 && b2 == 63 && b32 == 0) {
                                isBigEndian = Boolean.FALSE;
                            }
                        }
                    }
                    reader = createReader(stream, "UTF-16", isBigEndian);
                } else if (encoding.equals("ISO-10646-UCS-4")) {
                    b4 = new int[4];
                    count = 0;
                    while (count < 4) {
                        b4[count] = rewindableInputStream.readAndBuffer();
                        if (b4[count] == -1) {
                            break;
                        }
                        count++;
                    }
                    stream.reset();
                    if (count == 4) {
                        if (b4[0] == 0 && b4[1] == 0 && b4[2] == 0 && b4[3] == 60) {
                            isBigEndian = Boolean.TRUE;
                        } else if (b4[0] == 60 && b4[1] == 0 && b4[2] == 0 && b4[3] == 0) {
                            isBigEndian = Boolean.FALSE;
                        }
                    }
                    reader = createReader(stream, encoding, isBigEndian);
                } else if (encoding.equals("ISO-10646-UCS-2")) {
                    b4 = new int[4];
                    count = 0;
                    while (count < 4) {
                        b4[count] = rewindableInputStream.readAndBuffer();
                        if (b4[count] == -1) {
                            break;
                        }
                        count++;
                    }
                    stream.reset();
                    if (count == 4) {
                        if (b4[0] == 0 && b4[1] == 60 && b4[2] == 0 && b4[3] == 63) {
                            isBigEndian = Boolean.TRUE;
                        } else if (b4[0] == 60 && b4[1] == 0 && b4[2] == 63 && b4[3] == 0) {
                            isBigEndian = Boolean.FALSE;
                        }
                    }
                    reader = createReader(stream, encoding, isBigEndian);
                } else {
                    reader = createReader(stream, encoding, null);
                }
            }
        }
        this.fReaderStack.push(reader);
        if (this.fCurrentEntity != null) {
            this.fEntityStack.push(this.fCurrentEntity);
        }
        this.fCurrentEntity = new ScannedEntity(name, new XMLResourceIdentifierImpl(publicId, literalSystemId, baseSystemId, expandedSystemId), stream, reader, this.fTempByteBuffer, encoding, literal, false, isExternal);
        this.fCurrentEntity.setEncodingExternallySpecified(encodingExternallySpecified);
        this.fEntityScanner.setCurrentEntity(this.fCurrentEntity);
        this.fResourceIdentifier.setValues(publicId, literalSystemId, baseSystemId, expandedSystemId);
        return encoding;
    }

    public void setScannerVersion(short version) {
        if (version == (short) 1) {
            if (this.fXML10EntityScanner == null) {
                this.fXML10EntityScanner = new XMLEntityScanner();
            }
            this.fXML10EntityScanner.reset(this.fSymbolTable, this, this.fErrorReporter);
            this.fEntityScanner = this.fXML10EntityScanner;
            this.fEntityScanner.setCurrentEntity(this.fCurrentEntity);
            return;
        }
        if (this.fXML11EntityScanner == null) {
            this.fXML11EntityScanner = new XML11EntityScanner();
        }
        this.fXML11EntityScanner.reset(this.fSymbolTable, this, this.fErrorReporter);
        this.fEntityScanner = this.fXML11EntityScanner;
        this.fEntityScanner.setCurrentEntity(this.fCurrentEntity);
    }

    public XMLEntityScanner getEntityScanner() {
        if (this.fEntityScanner == null) {
            if (this.fXML10EntityScanner == null) {
                this.fXML10EntityScanner = new XMLEntityScanner();
            }
            this.fXML10EntityScanner.reset(this.fSymbolTable, this, this.fErrorReporter);
            this.fEntityScanner = this.fXML10EntityScanner;
        }
        return this.fEntityScanner;
    }

    public void closeReaders() {
        for (int i = this.fReaderStack.size() - 1; i >= 0; i--) {
            try {
                ((Reader) this.fReaderStack.pop()).close();
            } catch (IOException e) {
            }
        }
    }

    public void reset(XMLComponentManager componentManager) throws XMLConfigurationException {
        boolean parser_settings;
        try {
            parser_settings = componentManager.getFeature(PARSER_SETTINGS);
        } catch (XMLConfigurationException e) {
            parser_settings = true;
        }
        if (parser_settings) {
            try {
                this.fValidation = componentManager.getFeature(VALIDATION);
            } catch (XMLConfigurationException e2) {
                this.fValidation = false;
            }
            try {
                this.fExternalGeneralEntities = componentManager.getFeature(EXTERNAL_GENERAL_ENTITIES);
            } catch (XMLConfigurationException e3) {
                this.fExternalGeneralEntities = true;
            }
            try {
                this.fExternalParameterEntities = componentManager.getFeature(EXTERNAL_PARAMETER_ENTITIES);
            } catch (XMLConfigurationException e4) {
                this.fExternalParameterEntities = true;
            }
            try {
                this.fAllowJavaEncodings = componentManager.getFeature(ALLOW_JAVA_ENCODINGS);
            } catch (XMLConfigurationException e5) {
                this.fAllowJavaEncodings = false;
            }
            try {
                this.fWarnDuplicateEntityDef = componentManager.getFeature(WARN_ON_DUPLICATE_ENTITYDEF);
            } catch (XMLConfigurationException e6) {
                this.fWarnDuplicateEntityDef = false;
            }
            try {
                this.fStrictURI = componentManager.getFeature(STANDARD_URI_CONFORMANT);
            } catch (XMLConfigurationException e7) {
                this.fStrictURI = false;
            }
            this.fSymbolTable = (SymbolTable) componentManager.getProperty("http://apache.org/xml/properties/internal/symbol-table");
            this.fErrorReporter = (XMLErrorReporter) componentManager.getProperty("http://apache.org/xml/properties/internal/error-reporter");
            try {
                this.fEntityResolver = (XMLEntityResolver) componentManager.getProperty("http://apache.org/xml/properties/internal/entity-resolver");
            } catch (XMLConfigurationException e8) {
                this.fEntityResolver = null;
            }
            try {
                this.fValidationManager = (ValidationManager) componentManager.getProperty(VALIDATION_MANAGER);
            } catch (XMLConfigurationException e9) {
                this.fValidationManager = null;
            }
            try {
                this.fSecurityManager = (SecurityManager) componentManager.getProperty(SECURITY_MANAGER);
            } catch (XMLConfigurationException e10) {
                this.fSecurityManager = null;
            }
            reset();
            return;
        }
        reset();
    }

    public void reset() {
        this.fEntityExpansionLimit = this.fSecurityManager != null ? this.fSecurityManager.getEntityExpansionLimit() : 0;
        this.fStandalone = false;
        this.fHasPEReferences = false;
        this.fEntities.clear();
        this.fEntityStack.removeAllElements();
        this.fEntityExpansionCount = 0;
        this.fCurrentEntity = null;
        if (this.fXML10EntityScanner != null) {
            this.fXML10EntityScanner.reset(this.fSymbolTable, this, this.fErrorReporter);
        }
        if (this.fXML11EntityScanner != null) {
            this.fXML11EntityScanner.reset(this.fSymbolTable, this, this.fErrorReporter);
        }
        if (this.fDeclaredEntities != null) {
            for (Entry entry : this.fDeclaredEntities.entrySet()) {
                this.fEntities.put(entry.getKey(), entry.getValue());
            }
        }
        this.fEntityHandler = null;
    }

    public String[] getRecognizedFeatures() {
        return (String[]) RECOGNIZED_FEATURES.clone();
    }

    public void setFeature(String featureId, boolean state) throws XMLConfigurationException {
        if (featureId.startsWith(Constants.XERCES_FEATURE_PREFIX) && featureId.length() - Constants.XERCES_FEATURE_PREFIX.length() == Constants.ALLOW_JAVA_ENCODINGS_FEATURE.length() && featureId.endsWith(Constants.ALLOW_JAVA_ENCODINGS_FEATURE)) {
            this.fAllowJavaEncodings = state;
        }
    }

    public String[] getRecognizedProperties() {
        return (String[]) RECOGNIZED_PROPERTIES.clone();
    }

    public void setProperty(String propertyId, Object value) throws XMLConfigurationException {
        if (propertyId.startsWith(Constants.XERCES_PROPERTY_PREFIX)) {
            int suffixLength = propertyId.length() - Constants.XERCES_PROPERTY_PREFIX.length();
            if (suffixLength == Constants.SYMBOL_TABLE_PROPERTY.length() && propertyId.endsWith(Constants.SYMBOL_TABLE_PROPERTY)) {
                this.fSymbolTable = (SymbolTable) value;
            } else if (suffixLength == Constants.ERROR_REPORTER_PROPERTY.length() && propertyId.endsWith(Constants.ERROR_REPORTER_PROPERTY)) {
                this.fErrorReporter = (XMLErrorReporter) value;
            } else if (suffixLength == Constants.ENTITY_RESOLVER_PROPERTY.length() && propertyId.endsWith(Constants.ENTITY_RESOLVER_PROPERTY)) {
                this.fEntityResolver = (XMLEntityResolver) value;
            } else {
                if (suffixLength == Constants.BUFFER_SIZE_PROPERTY.length() && propertyId.endsWith(Constants.BUFFER_SIZE_PROPERTY)) {
                    Integer bufferSize = (Integer) value;
                    if (bufferSize != null && bufferSize.intValue() > 64) {
                        this.fBufferSize = bufferSize.intValue();
                        this.fEntityScanner.setBufferSize(this.fBufferSize);
                        this.fSmallByteBufferPool.setBufferSize(this.fBufferSize);
                        this.fLargeByteBufferPool.setBufferSize(this.fBufferSize << 1);
                        this.fCharacterBufferPool.setExternalBufferSize(this.fBufferSize);
                    }
                }
                if (suffixLength == Constants.SECURITY_MANAGER_PROPERTY.length() && propertyId.endsWith(Constants.SECURITY_MANAGER_PROPERTY)) {
                    this.fSecurityManager = (SecurityManager) value;
                    this.fEntityExpansionLimit = this.fSecurityManager != null ? this.fSecurityManager.getEntityExpansionLimit() : 0;
                }
            }
        }
    }

    public Boolean getFeatureDefault(String featureId) {
        for (int i = 0; i < RECOGNIZED_FEATURES.length; i++) {
            if (RECOGNIZED_FEATURES[i].equals(featureId)) {
                return FEATURE_DEFAULTS[i];
            }
        }
        return null;
    }

    public Object getPropertyDefault(String propertyId) {
        for (int i = 0; i < RECOGNIZED_PROPERTIES.length; i++) {
            if (RECOGNIZED_PROPERTIES[i].equals(propertyId)) {
                return PROPERTY_DEFAULTS[i];
            }
        }
        return null;
    }

    private static synchronized URI getUserDir() throws MalformedURIException {
        URI uri;
        synchronized (XMLEntityManager.class) {
            String userDir = "";
            try {
                userDir = (String) AccessController.doPrivileged(GET_USER_DIR_SYSTEM_PROPERTY);
            } catch (SecurityException e) {
            }
            if (userDir.length() == 0) {
                uri = new URI("file", "", "", null, null);
            } else if (gUserDirURI == null || !userDir.equals(gUserDir)) {
                int ch;
                gUserDir = userDir;
                userDir = userDir.replace(File.separatorChar, '/');
                int len = userDir.length();
                StringBuffer buffer = new StringBuffer(len * 3);
                if (len >= 2 && userDir.charAt(1) == ':') {
                    ch = Character.toUpperCase(userDir.charAt(0));
                    if (ch >= 65 && ch <= 90) {
                        buffer.append('/');
                    }
                }
                int i = 0;
                while (i < len) {
                    ch = userDir.charAt(i);
                    if (ch >= 128) {
                        break;
                    }
                    if (gNeedEscaping[ch]) {
                        buffer.append('%');
                        buffer.append(gAfterEscaping1[ch]);
                        buffer.append(gAfterEscaping2[ch]);
                    } else {
                        buffer.append((char) ch);
                    }
                    i++;
                }
                if (i < len) {
                    try {
                        for (byte b : userDir.substring(i).getBytes("UTF-8")) {
                            if (b < (byte) 0) {
                                ch = b + 256;
                                buffer.append('%');
                                buffer.append(gHexChs[ch >> 4]);
                                buffer.append(gHexChs[ch & 15]);
                            } else if (gNeedEscaping[b]) {
                                buffer.append('%');
                                buffer.append(gAfterEscaping1[b]);
                                buffer.append(gAfterEscaping2[b]);
                            } else {
                                buffer.append((char) b);
                            }
                        }
                    } catch (UnsupportedEncodingException e2) {
                        uri = new URI("file", "", userDir, null, null);
                    }
                }
                if (!userDir.endsWith(BridgeUtil.SPLIT_MARK)) {
                    buffer.append('/');
                }
                gUserDirURI = new URI("file", "", buffer.toString(), null, null);
                uri = gUserDirURI;
            } else {
                uri = gUserDirURI;
            }
        }
        return uri;
    }

    public static void absolutizeAgainstUserDir(URI uri) throws MalformedURIException {
        uri.absolutize(getUserDir());
    }

    public static String expandSystemId(String systemId, String baseSystemId, boolean strict) throws MalformedURIException {
        if (systemId == null) {
            return null;
        }
        if (strict) {
            return expandSystemIdStrictOn(systemId, baseSystemId);
        }
        try {
            return expandSystemIdStrictOff(systemId, baseSystemId);
        } catch (MalformedURIException e) {
            if (systemId.length() == 0) {
                return systemId;
            }
            URI base;
            String id = fixURI(systemId);
            URI uri = null;
            if (baseSystemId != null) {
                try {
                    if (!(baseSystemId.length() == 0 || baseSystemId.equals(systemId))) {
                        try {
                            base = new URI(fixURI(baseSystemId).trim());
                        } catch (MalformedURIException e2) {
                            if (baseSystemId.indexOf(58) != -1) {
                                base = new URI("file", "", fixURI(baseSystemId).trim(), null, null);
                            } else {
                                base = new URI(getUserDir(), fixURI(baseSystemId));
                            }
                        }
                        uri = new URI(base, id.trim());
                        return uri != null ? systemId : uri.toString();
                    }
                } catch (Exception e3) {
                    base = null;
                    if (uri != null) {
                    }
                }
            }
            base = getUserDir();
            try {
                uri = new URI(base, id.trim());
            } catch (Exception e4) {
            }
            if (uri != null) {
            }
        }
    }

    private static String expandSystemIdStrictOn(String systemId, String baseSystemId) throws MalformedURIException {
        URI systemURI = new URI(systemId, true);
        if (systemURI.isAbsoluteURI()) {
            return systemId;
        }
        URI baseURI;
        if (baseSystemId == null || baseSystemId.length() == 0) {
            baseURI = getUserDir();
        } else {
            baseURI = new URI(baseSystemId, true);
            if (!baseURI.isAbsoluteURI()) {
                baseURI.absolutize(getUserDir());
            }
        }
        systemURI.absolutize(baseURI);
        return systemURI.toString();
    }

    private static String expandSystemIdStrictOff(String systemId, String baseSystemId) throws MalformedURIException {
        URI systemURI = new URI(systemId, true);
        if (!systemURI.isAbsoluteURI()) {
            URI baseURI;
            if (baseSystemId == null || baseSystemId.length() == 0) {
                baseURI = getUserDir();
            } else {
                baseURI = new URI(baseSystemId, true);
                if (!baseURI.isAbsoluteURI()) {
                    baseURI.absolutize(getUserDir());
                }
            }
            systemURI.absolutize(baseURI);
            return systemURI.toString();
        } else if (systemURI.getScheme().length() > 1) {
            return systemId;
        } else {
            throw new MalformedURIException();
        }
    }

    public static OutputStream createOutputStream(String uri) throws IOException {
        String expanded = expandSystemId(uri, null, true);
        if (expanded == null) {
            expanded = uri;
        }
        URL url = new URL(expanded);
        String protocol = url.getProtocol();
        String host = url.getHost();
        if (protocol.equals("file") && (host == null || host.length() == 0 || host.equals("localhost"))) {
            File file = new File(getPathWithoutEscapes(url.getPath()));
            if (!file.exists()) {
                File parent = file.getParentFile();
                if (!(parent == null || parent.exists())) {
                    parent.mkdirs();
                }
            }
            return new FileOutputStream(file);
        }
        URLConnection urlCon = url.openConnection();
        urlCon.setDoInput(false);
        urlCon.setDoOutput(true);
        urlCon.setUseCaches(false);
        if (urlCon instanceof HttpURLConnection) {
            ((HttpURLConnection) urlCon).setRequestMethod("PUT");
        }
        return urlCon.getOutputStream();
    }

    private static String getPathWithoutEscapes(String origPath) {
        if (origPath == null || origPath.length() == 0 || origPath.indexOf(37) == -1) {
            return origPath;
        }
        StringTokenizer tokenizer = new StringTokenizer(origPath, "%");
        StringBuffer result = new StringBuffer(origPath.length());
        int size = tokenizer.countTokens();
        result.append(tokenizer.nextToken());
        for (int i = 1; i < size; i++) {
            String token = tokenizer.nextToken();
            result.append((char) Integer.valueOf(token.substring(0, 2), 16).intValue());
            result.append(token.substring(2));
        }
        return result.toString();
    }

    void endEntity() throws XNIException {
        ScannedEntity scannedEntity = null;
        if (this.fEntityHandler != null) {
            this.fEntityHandler.endEntity(this.fCurrentEntity.name, null);
        }
        try {
            this.fCurrentEntity.reader.close();
        } catch (IOException e) {
        }
        if (!this.fReaderStack.isEmpty()) {
            this.fReaderStack.pop();
        }
        this.fCharacterBufferPool.returnBuffer(this.fCurrentEntity.fCharacterBuffer);
        if (this.fCurrentEntity.fByteBuffer != null) {
            if (this.fCurrentEntity.fByteBuffer.length == this.fBufferSize) {
                this.fSmallByteBufferPool.returnBuffer(this.fCurrentEntity.fByteBuffer);
            } else {
                this.fLargeByteBufferPool.returnBuffer(this.fCurrentEntity.fByteBuffer);
            }
        }
        if (this.fEntityStack.size() > 0) {
            scannedEntity = (ScannedEntity) this.fEntityStack.pop();
        }
        this.fCurrentEntity = scannedEntity;
        this.fEntityScanner.setCurrentEntity(this.fCurrentEntity);
    }

    protected EncodingInfo getEncodingInfo(byte[] b4, int count) {
        if (count < 2) {
            return EncodingInfo.UTF_8;
        }
        int b0 = b4[0] & 255;
        int b1 = b4[1] & 255;
        if (b0 == 254 && b1 == 255) {
            return EncodingInfo.UTF_16_BIG_ENDIAN_WITH_BOM;
        }
        if (b0 == 255 && b1 == 254) {
            return EncodingInfo.UTF_16_LITTLE_ENDIAN_WITH_BOM;
        }
        if (count < 3) {
            return EncodingInfo.UTF_8;
        }
        int b2 = b4[2] & 255;
        if (b0 == 239 && b1 == 187 && b2 == 191) {
            return EncodingInfo.UTF_8_WITH_BOM;
        }
        if (count < 4) {
            return EncodingInfo.UTF_8;
        }
        int b3 = b4[3] & 255;
        if (b0 == 0 && b1 == 0 && b2 == 0 && b3 == 60) {
            return EncodingInfo.UCS_4_BIG_ENDIAN;
        }
        if (b0 == 60 && b1 == 0 && b2 == 0 && b3 == 0) {
            return EncodingInfo.UCS_4_LITTLE_ENDIAN;
        }
        if (b0 == 0 && b1 == 0 && b2 == 60 && b3 == 0) {
            return EncodingInfo.UCS_4_UNUSUAL_BYTE_ORDER;
        }
        if (b0 == 0 && b1 == 60 && b2 == 0 && b3 == 0) {
            return EncodingInfo.UCS_4_UNUSUAL_BYTE_ORDER;
        }
        if (b0 == 0 && b1 == 60 && b2 == 0 && b3 == 63) {
            return EncodingInfo.UTF_16_BIG_ENDIAN;
        }
        if (b0 == 60 && b1 == 0 && b2 == 63 && b3 == 0) {
            return EncodingInfo.UTF_16_LITTLE_ENDIAN;
        }
        if (b0 == 76 && b1 == 111 && b2 == 167 && b3 == 148) {
            return EncodingInfo.EBCDIC;
        }
        return EncodingInfo.UTF_8;
    }

    protected Reader createReader(InputStream inputStream, String encoding, Boolean isBigEndian) throws IOException {
        if (encoding == "UTF-8" || encoding == null) {
            return createUTF8Reader(inputStream);
        }
        if (encoding == "UTF-16" && isBigEndian != null) {
            return createUTF16Reader(inputStream, isBigEndian.booleanValue());
        }
        String ENCODING = encoding.toUpperCase(Locale.ENGLISH);
        if (ENCODING.equals("UTF-8")) {
            return createUTF8Reader(inputStream);
        }
        if (ENCODING.equals("UTF-16BE")) {
            return createUTF16Reader(inputStream, true);
        }
        if (ENCODING.equals("UTF-16LE")) {
            return createUTF16Reader(inputStream, false);
        }
        if (ENCODING.equals("ISO-10646-UCS-4")) {
            if (isBigEndian == null) {
                this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "EncodingByteOrderUnsupported", new Object[]{encoding}, (short) 2);
            } else if (isBigEndian.booleanValue()) {
                return new UCSReader(inputStream, (short) 8);
            } else {
                return new UCSReader(inputStream, (short) 4);
            }
        }
        if (ENCODING.equals("ISO-10646-UCS-2")) {
            if (isBigEndian == null) {
                this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "EncodingByteOrderUnsupported", new Object[]{encoding}, (short) 2);
            } else if (isBigEndian.booleanValue()) {
                return new UCSReader(inputStream, (short) 2);
            } else {
                return new UCSReader(inputStream, (short) 1);
            }
        }
        boolean validIANA = XMLChar.isValidIANAEncoding(encoding);
        boolean validJava = XMLChar.isValidJavaEncoding(encoding);
        if (!validIANA || (this.fAllowJavaEncodings && !validJava)) {
            this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "EncodingDeclInvalid", new Object[]{encoding}, (short) 2);
            return createLatin1Reader(inputStream);
        }
        String javaEncoding = EncodingMap.getIANA2JavaMapping(ENCODING);
        if (javaEncoding == null) {
            if (this.fAllowJavaEncodings) {
                javaEncoding = encoding;
            } else {
                this.fErrorReporter.reportError("http://www.w3.org/TR/1998/REC-xml-19980210", "EncodingDeclInvalid", new Object[]{encoding}, (short) 2);
                return createLatin1Reader(inputStream);
            }
        } else if (javaEncoding.equals(HTTP.ASCII)) {
            return createASCIIReader(inputStream);
        } else {
            if (javaEncoding.equals("ISO8859_1")) {
                return createLatin1Reader(inputStream);
            }
        }
        return new InputStreamReader(inputStream, javaEncoding);
    }

    private Reader createUTF8Reader(InputStream stream) {
        if (this.fTempByteBuffer == null) {
            this.fTempByteBuffer = this.fSmallByteBufferPool.getBuffer();
        }
        return new UTF8Reader(stream, this.fTempByteBuffer, this.fErrorReporter.getMessageFormatter("http://www.w3.org/TR/1998/REC-xml-19980210"), this.fErrorReporter.getLocale());
    }

    private Reader createUTF16Reader(InputStream stream, boolean isBigEndian) {
        if (this.fTempByteBuffer == null) {
            this.fTempByteBuffer = this.fLargeByteBufferPool.getBuffer();
        } else if (this.fTempByteBuffer.length == this.fBufferSize) {
            this.fSmallByteBufferPool.returnBuffer(this.fTempByteBuffer);
            this.fTempByteBuffer = this.fLargeByteBufferPool.getBuffer();
        }
        return new UTF16Reader(stream, this.fTempByteBuffer, isBigEndian, this.fErrorReporter.getMessageFormatter("http://www.w3.org/TR/1998/REC-xml-19980210"), this.fErrorReporter.getLocale());
    }

    private Reader createASCIIReader(InputStream stream) {
        if (this.fTempByteBuffer == null) {
            this.fTempByteBuffer = this.fSmallByteBufferPool.getBuffer();
        }
        return new ASCIIReader(stream, this.fTempByteBuffer, this.fErrorReporter.getMessageFormatter("http://www.w3.org/TR/1998/REC-xml-19980210"), this.fErrorReporter.getLocale());
    }

    private Reader createLatin1Reader(InputStream stream) {
        if (this.fTempByteBuffer == null) {
            this.fTempByteBuffer = this.fSmallByteBufferPool.getBuffer();
        }
        return new Latin1Reader(stream, this.fTempByteBuffer);
    }

    protected static String fixURI(String str) {
        str = str.replace(File.separatorChar, '/');
        StringBuffer stringBuffer = null;
        if (str.length() >= 2) {
            char ch1 = str.charAt(1);
            if (ch1 == ':') {
                char ch0 = Character.toUpperCase(str.charAt(0));
                if (ch0 >= 'A' && ch0 <= 'Z') {
                    stringBuffer = new StringBuffer(str.length() + 8);
                    stringBuffer.append("file:///");
                }
            } else if (ch1 == '/' && str.charAt(0) == '/') {
                stringBuffer = new StringBuffer(str.length() + 5);
                stringBuffer.append("file:");
            }
        }
        int pos = str.indexOf(32);
        if (pos >= 0) {
            int i;
            if (stringBuffer == null) {
                stringBuffer = new StringBuffer(str.length());
            }
            for (i = 0; i < pos; i++) {
                stringBuffer.append(str.charAt(i));
            }
            stringBuffer.append("%20");
            for (i = pos + 1; i < str.length(); i++) {
                if (str.charAt(i) == ' ') {
                    stringBuffer.append("%20");
                } else {
                    stringBuffer.append(str.charAt(i));
                }
            }
            return stringBuffer.toString();
        } else if (stringBuffer == null) {
            return str;
        } else {
            stringBuffer.append(str);
            return stringBuffer.toString();
        }
    }

    Hashtable getDeclaredEntities() {
        return this.fEntities;
    }

    static final void print(ScannedEntity currentEntity) {
    }
}
