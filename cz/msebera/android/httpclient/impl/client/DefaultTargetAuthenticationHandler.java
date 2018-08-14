package cz.msebera.android.httpclient.impl.client;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.HttpStatus;
import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.auth.MalformedChallengeException;
import cz.msebera.android.httpclient.auth.params.AuthPNames;
import cz.msebera.android.httpclient.protocol.HttpContext;
import cz.msebera.android.httpclient.util.Args;
import java.util.List;
import java.util.Map;

@Immutable
@Deprecated
public class DefaultTargetAuthenticationHandler extends AbstractAuthenticationHandler {
    public boolean isAuthenticationRequested(HttpResponse response, HttpContext context) {
        Args.notNull(response, "HTTP response");
        return response.getStatusLine().getStatusCode() == HttpStatus.SC_UNAUTHORIZED;
    }

    public Map<String, Header> getChallenges(HttpResponse response, HttpContext context) throws MalformedChallengeException {
        Args.notNull(response, "HTTP response");
        return parseChallenges(response.getHeaders("WWW-Authenticate"));
    }

    protected List<String> getAuthPreferences(HttpResponse response, HttpContext context) {
        List<String> authpref = (List) response.getParams().getParameter(AuthPNames.TARGET_AUTH_PREF);
        return authpref != null ? authpref : super.getAuthPreferences(response, context);
    }
}
