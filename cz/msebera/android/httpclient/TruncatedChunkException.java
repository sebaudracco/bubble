package cz.msebera.android.httpclient;

public class TruncatedChunkException extends MalformedChunkCodingException {
    private static final long serialVersionUID = -23506263930279460L;

    public TruncatedChunkException(String message) {
        super(message);
    }
}
