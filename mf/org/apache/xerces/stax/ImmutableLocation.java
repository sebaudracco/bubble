package mf.org.apache.xerces.stax;

import mf.javax.xml.stream.Location;

public class ImmutableLocation implements Location {
    private final int fCharacterOffset;
    private final int fColumnNumber;
    private final int fLineNumber;
    private final String fPublicId;
    private final String fSystemId;

    public ImmutableLocation(Location location) {
        this(location.getCharacterOffset(), location.getColumnNumber(), location.getLineNumber(), location.getPublicId(), location.getSystemId());
    }

    public ImmutableLocation(int characterOffset, int columnNumber, int lineNumber, String publicId, String systemId) {
        this.fCharacterOffset = characterOffset;
        this.fColumnNumber = columnNumber;
        this.fLineNumber = lineNumber;
        this.fPublicId = publicId;
        this.fSystemId = systemId;
    }

    public int getCharacterOffset() {
        return this.fCharacterOffset;
    }

    public int getColumnNumber() {
        return this.fColumnNumber;
    }

    public int getLineNumber() {
        return this.fLineNumber;
    }

    public String getPublicId() {
        return this.fPublicId;
    }

    public String getSystemId() {
        return this.fSystemId;
    }
}
