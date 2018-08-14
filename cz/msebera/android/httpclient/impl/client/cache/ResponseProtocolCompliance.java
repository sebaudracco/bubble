package cz.msebera.android.httpclient.impl.client.cache;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HeaderElement;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpEntityEnclosingRequest;
import cz.msebera.android.httpclient.HttpHeaders;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.HttpStatus;
import cz.msebera.android.httpclient.HttpVersion;
import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.client.ClientProtocolException;
import cz.msebera.android.httpclient.client.methods.HttpRequestWrapper;
import cz.msebera.android.httpclient.client.utils.DateUtils;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.protocol.HTTP;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import mf.org.apache.xerces.impl.xs.SchemaSymbols;

@Immutable
class ResponseProtocolCompliance {
    private static final String UNEXPECTED_100_CONTINUE = "The incoming request did not contain a 100-continue header, but the response was a Status 100, continue.";
    private static final String UNEXPECTED_PARTIAL_CONTENT = "partial content was returned for a request that did not ask for it";

    ResponseProtocolCompliance() {
    }

    public void ensureProtocolCompliance(HttpRequestWrapper request, HttpResponse response) throws IOException {
        if (backendResponseMustNotHaveBody(request, response)) {
            consumeBody(response);
            response.setEntity(null);
        }
        requestDidNotExpect100ContinueButResponseIsOne(request, response);
        transferEncodingIsNotReturnedTo1_0Client(request, response);
        ensurePartialContentIsNotSentToAClientThatDidNotRequestIt(request, response);
        ensure200ForOPTIONSRequestWithNoBodyHasContentLengthZero(request, response);
        ensure206ContainsDateHeader(response);
        ensure304DoesNotContainExtraEntityHeaders(response);
        identityIsNotUsedInContentEncoding(response);
        warningsWithNonMatchingWarnDatesAreRemoved(response);
    }

    private void consumeBody(HttpResponse response) throws IOException {
        HttpEntity body = response.getEntity();
        if (body != null) {
            IOUtils.consume(body);
        }
    }

    private void warningsWithNonMatchingWarnDatesAreRemoved(HttpResponse response) {
        Date responseDate = DateUtils.parseDate(response.getFirstHeader("Date").getValue());
        if (responseDate != null) {
            Header[] warningHeaders = response.getHeaders("Warning");
            if (warningHeaders != null && warningHeaders.length != 0) {
                List<Header> newWarningHeaders = new ArrayList();
                boolean modified = false;
                for (Header h : warningHeaders) {
                    for (WarningValue wv : WarningValue.getWarningValues(h)) {
                        Date warnDate = wv.getWarnDate();
                        if (warnDate == null || warnDate.equals(responseDate)) {
                            newWarningHeaders.add(new BasicHeader("Warning", wv.toString()));
                        } else {
                            modified = true;
                        }
                    }
                }
                if (modified) {
                    response.removeHeaders("Warning");
                    for (Header h2 : newWarningHeaders) {
                        response.addHeader(h2);
                    }
                }
            }
        }
    }

    private void identityIsNotUsedInContentEncoding(HttpResponse response) {
        Header[] hdrs = response.getHeaders("Content-Encoding");
        if (hdrs != null && hdrs.length != 0) {
            List<Header> newHeaders = new ArrayList();
            boolean modified = false;
            for (Header h : hdrs) {
                StringBuilder buf = new StringBuilder();
                boolean first = true;
                for (HeaderElement elt : h.getElements()) {
                    if (HTTP.IDENTITY_CODING.equalsIgnoreCase(elt.getName())) {
                        modified = true;
                    } else {
                        if (!first) {
                            buf.append(",");
                        }
                        buf.append(elt.toString());
                        first = false;
                    }
                }
                String newHeaderValue = buf.toString();
                if (!"".equals(newHeaderValue)) {
                    newHeaders.add(new BasicHeader("Content-Encoding", newHeaderValue));
                }
            }
            if (modified) {
                response.removeHeaders("Content-Encoding");
                for (Header h2 : newHeaders) {
                    response.addHeader(h2);
                }
            }
        }
    }

    private void ensure206ContainsDateHeader(HttpResponse response) {
        if (response.getFirstHeader("Date") == null) {
            response.addHeader("Date", DateUtils.formatDate(new Date()));
        }
    }

    private void ensurePartialContentIsNotSentToAClientThatDidNotRequestIt(HttpRequest request, HttpResponse response) throws IOException {
        if (request.getFirstHeader("Range") == null && response.getStatusLine().getStatusCode() == HttpStatus.SC_PARTIAL_CONTENT) {
            consumeBody(response);
            throw new ClientProtocolException(UNEXPECTED_PARTIAL_CONTENT);
        }
    }

    private void ensure200ForOPTIONSRequestWithNoBodyHasContentLengthZero(HttpRequest request, HttpResponse response) {
        if (request.getRequestLine().getMethod().equalsIgnoreCase("OPTIONS") && response.getStatusLine().getStatusCode() == 200 && response.getFirstHeader("Content-Length") == null) {
            response.addHeader("Content-Length", SchemaSymbols.ATTVAL_FALSE_0);
        }
    }

    private void ensure304DoesNotContainExtraEntityHeaders(HttpResponse response) {
        int i = 0;
        String[] disallowedEntityHeaders = new String[]{"Allow", "Content-Encoding", HttpHeaders.CONTENT_LANGUAGE, "Content-Length", HttpHeaders.CONTENT_MD5, "Content-Range", "Content-Type", "Last-Modified"};
        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_NOT_MODIFIED) {
            int length = disallowedEntityHeaders.length;
            while (i < length) {
                response.removeHeaders(disallowedEntityHeaders[i]);
                i++;
            }
        }
    }

    private boolean backendResponseMustNotHaveBody(HttpRequest request, HttpResponse backendResponse) {
        return "HEAD".equals(request.getRequestLine().getMethod()) || backendResponse.getStatusLine().getStatusCode() == HttpStatus.SC_NO_CONTENT || backendResponse.getStatusLine().getStatusCode() == HttpStatus.SC_RESET_CONTENT || backendResponse.getStatusLine().getStatusCode() == HttpStatus.SC_NOT_MODIFIED;
    }

    private void requestDidNotExpect100ContinueButResponseIsOne(HttpRequestWrapper request, HttpResponse response) throws IOException {
        if (response.getStatusLine().getStatusCode() == 100) {
            HttpRequest originalRequest = request.getOriginal();
            if (!(originalRequest instanceof HttpEntityEnclosingRequest) || !((HttpEntityEnclosingRequest) originalRequest).expectContinue()) {
                consumeBody(response);
                throw new ClientProtocolException(UNEXPECTED_100_CONTINUE);
            }
        }
    }

    private void transferEncodingIsNotReturnedTo1_0Client(HttpRequestWrapper request, HttpResponse response) {
        if (request.getOriginal().getProtocolVersion().compareToVersion(HttpVersion.HTTP_1_1) < 0) {
            removeResponseTransferEncoding(response);
        }
    }

    private void removeResponseTransferEncoding(HttpResponse response) {
        response.removeHeaders(HttpHeaders.TE);
        response.removeHeaders("Transfer-Encoding");
    }
}
