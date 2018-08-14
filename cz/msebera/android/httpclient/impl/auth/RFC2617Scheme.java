package cz.msebera.android.httpclient.impl.auth;

import cz.msebera.android.httpclient.Consts;
import cz.msebera.android.httpclient.HeaderElement;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.auth.ChallengeState;
import cz.msebera.android.httpclient.auth.MalformedChallengeException;
import cz.msebera.android.httpclient.auth.params.AuthPNames;
import cz.msebera.android.httpclient.message.BasicHeaderValueParser;
import cz.msebera.android.httpclient.message.ParserCursor;
import cz.msebera.android.httpclient.util.CharArrayBuffer;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@NotThreadSafe
public abstract class RFC2617Scheme extends AuthSchemeBase {
    private final Charset credentialsCharset;
    private final Map<String, String> params;

    @Deprecated
    public RFC2617Scheme(ChallengeState challengeState) {
        super(challengeState);
        this.params = new HashMap();
        this.credentialsCharset = Consts.ASCII;
    }

    public RFC2617Scheme(Charset credentialsCharset) {
        this.params = new HashMap();
        if (credentialsCharset == null) {
            credentialsCharset = Consts.ASCII;
        }
        this.credentialsCharset = credentialsCharset;
    }

    public RFC2617Scheme() {
        this(Consts.ASCII);
    }

    public Charset getCredentialsCharset() {
        return this.credentialsCharset;
    }

    String getCredentialsCharset(HttpRequest request) {
        String charset = (String) request.getParams().getParameter(AuthPNames.CREDENTIAL_CHARSET);
        if (charset == null) {
            return getCredentialsCharset().name();
        }
        return charset;
    }

    protected void parseChallenge(CharArrayBuffer buffer, int pos, int len) throws MalformedChallengeException {
        HeaderElement[] elements = BasicHeaderValueParser.INSTANCE.parseElements(buffer, new ParserCursor(pos, buffer.length()));
        if (elements.length == 0) {
            throw new MalformedChallengeException("Authentication challenge is empty");
        }
        this.params.clear();
        for (HeaderElement element : elements) {
            this.params.put(element.getName().toLowerCase(Locale.ENGLISH), element.getValue());
        }
    }

    protected Map<String, String> getParameters() {
        return this.params;
    }

    public String getParameter(String name) {
        if (name == null) {
            return null;
        }
        return (String) this.params.get(name.toLowerCase(Locale.ENGLISH));
    }

    public String getRealm() {
        return getParameter("realm");
    }
}
