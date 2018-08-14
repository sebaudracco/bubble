package cz.msebera.android.httpclient.impl.client.cache;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HeaderElement;
import cz.msebera.android.httpclient.HttpEntityEnclosingRequest;
import cz.msebera.android.httpclient.HttpRequest;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.HttpStatus;
import cz.msebera.android.httpclient.HttpVersion;
import cz.msebera.android.httpclient.ProtocolVersion;
import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.client.ClientProtocolException;
import cz.msebera.android.httpclient.client.cache.HeaderConstants;
import cz.msebera.android.httpclient.client.methods.HttpRequestWrapper;
import cz.msebera.android.httpclient.entity.AbstractHttpEntity;
import cz.msebera.android.httpclient.entity.ContentType;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.message.BasicHttpResponse;
import cz.msebera.android.httpclient.message.BasicStatusLine;
import cz.msebera.android.httpclient.protocol.HTTP;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Immutable
class RequestProtocolCompliance {
    private static final List<String> disallowedWithNoCache = Arrays.asList(new String[]{HeaderConstants.CACHE_CONTROL_MIN_FRESH, HeaderConstants.CACHE_CONTROL_MAX_STALE, "max-age"});
    private final boolean weakETagOnPutDeleteAllowed;

    public RequestProtocolCompliance() {
        this.weakETagOnPutDeleteAllowed = false;
    }

    public RequestProtocolCompliance(boolean weakETagOnPutDeleteAllowed) {
        this.weakETagOnPutDeleteAllowed = weakETagOnPutDeleteAllowed;
    }

    public List<RequestProtocolError> requestIsFatallyNonCompliant(HttpRequest request) {
        List<RequestProtocolError> theErrors = new ArrayList();
        RequestProtocolError anError = requestHasWeakETagAndRange(request);
        if (anError != null) {
            theErrors.add(anError);
        }
        if (!this.weakETagOnPutDeleteAllowed) {
            anError = requestHasWeekETagForPUTOrDELETEIfMatch(request);
            if (anError != null) {
                theErrors.add(anError);
            }
        }
        anError = requestContainsNoCacheDirectiveWithFieldName(request);
        if (anError != null) {
            theErrors.add(anError);
        }
        return theErrors;
    }

    public void makeRequestCompliant(HttpRequestWrapper request) throws ClientProtocolException {
        if (requestMustNotHaveEntity(request)) {
            ((HttpEntityEnclosingRequest) request).setEntity(null);
        }
        verifyRequestWithExpectContinueFlagHas100continueHeader(request);
        verifyOPTIONSRequestWithBodyHasContentType(request);
        decrementOPTIONSMaxForwardsIfGreaterThen0(request);
        stripOtherFreshnessDirectivesWithNoCache(request);
        if (requestVersionIsTooLow(request) || requestMinorVersionIsTooHighMajorVersionsMatch(request)) {
            request.setProtocolVersion(HttpVersion.HTTP_1_1);
        }
    }

    private void stripOtherFreshnessDirectivesWithNoCache(HttpRequest request) {
        List<HeaderElement> outElts = new ArrayList();
        boolean shouldStrip = false;
        for (Header h : request.getHeaders("Cache-Control")) {
            for (HeaderElement elt : h.getElements()) {
                if (!disallowedWithNoCache.contains(elt.getName())) {
                    outElts.add(elt);
                }
                if (HeaderConstants.CACHE_CONTROL_NO_CACHE.equals(elt.getName())) {
                    shouldStrip = true;
                }
            }
        }
        if (shouldStrip) {
            request.removeHeaders("Cache-Control");
            request.setHeader("Cache-Control", buildHeaderFromElements(outElts));
        }
    }

    private String buildHeaderFromElements(List<HeaderElement> outElts) {
        StringBuilder newHdr = new StringBuilder("");
        boolean first = true;
        for (HeaderElement elt : outElts) {
            if (first) {
                first = false;
            } else {
                newHdr.append(",");
            }
            newHdr.append(elt.toString());
        }
        return newHdr.toString();
    }

    private boolean requestMustNotHaveEntity(HttpRequest request) {
        return "TRACE".equals(request.getRequestLine().getMethod()) && (request instanceof HttpEntityEnclosingRequest);
    }

    private void decrementOPTIONSMaxForwardsIfGreaterThen0(HttpRequest request) {
        if ("OPTIONS".equals(request.getRequestLine().getMethod())) {
            Header maxForwards = request.getFirstHeader("Max-Forwards");
            if (maxForwards != null) {
                request.removeHeaders("Max-Forwards");
                request.setHeader("Max-Forwards", Integer.toString(Integer.parseInt(maxForwards.getValue()) - 1));
            }
        }
    }

    private void verifyOPTIONSRequestWithBodyHasContentType(HttpRequest request) {
        if ("OPTIONS".equals(request.getRequestLine().getMethod()) && (request instanceof HttpEntityEnclosingRequest)) {
            addContentTypeHeaderIfMissing((HttpEntityEnclosingRequest) request);
        }
    }

    private void addContentTypeHeaderIfMissing(HttpEntityEnclosingRequest request) {
        if (request.getEntity().getContentType() == null) {
            ((AbstractHttpEntity) request.getEntity()).setContentType(ContentType.APPLICATION_OCTET_STREAM.getMimeType());
        }
    }

    private void verifyRequestWithExpectContinueFlagHas100continueHeader(HttpRequest request) {
        if (!(request instanceof HttpEntityEnclosingRequest)) {
            remove100ContinueHeaderIfExists(request);
        } else if (!((HttpEntityEnclosingRequest) request).expectContinue() || ((HttpEntityEnclosingRequest) request).getEntity() == null) {
            remove100ContinueHeaderIfExists(request);
        } else {
            add100ContinueHeaderIfMissing(request);
        }
    }

    private void remove100ContinueHeaderIfExists(HttpRequest request) {
        boolean hasHeader = false;
        Header[] expectHeaders = request.getHeaders("Expect");
        List<HeaderElement> expectElementsThatAreNot100Continue = new ArrayList();
        for (Header h : expectHeaders) {
            for (HeaderElement elt : h.getElements()) {
                if (HTTP.EXPECT_CONTINUE.equalsIgnoreCase(elt.getName())) {
                    hasHeader = true;
                } else {
                    expectElementsThatAreNot100Continue.add(elt);
                }
            }
            if (hasHeader) {
                request.removeHeader(h);
                for (HeaderElement elt2 : expectElementsThatAreNot100Continue) {
                    request.addHeader(new BasicHeader("Expect", elt2.getName()));
                }
                return;
            }
            expectElementsThatAreNot100Continue = new ArrayList();
        }
    }

    private void add100ContinueHeaderIfMissing(HttpRequest request) {
        boolean hasHeader = false;
        for (Header h : request.getHeaders("Expect")) {
            for (HeaderElement elt : h.getElements()) {
                if (HTTP.EXPECT_CONTINUE.equalsIgnoreCase(elt.getName())) {
                    hasHeader = true;
                }
            }
        }
        if (!hasHeader) {
            request.addHeader("Expect", HTTP.EXPECT_CONTINUE);
        }
    }

    protected boolean requestMinorVersionIsTooHighMajorVersionsMatch(HttpRequest request) {
        ProtocolVersion requestProtocol = request.getProtocolVersion();
        if (requestProtocol.getMajor() == HttpVersion.HTTP_1_1.getMajor() && requestProtocol.getMinor() > HttpVersion.HTTP_1_1.getMinor()) {
            return true;
        }
        return false;
    }

    protected boolean requestVersionIsTooLow(HttpRequest request) {
        return request.getProtocolVersion().compareToVersion(HttpVersion.HTTP_1_1) < 0;
    }

    public HttpResponse getErrorForRequest(RequestProtocolError errorCheck) {
        switch (errorCheck) {
            case BODY_BUT_NO_LENGTH_ERROR:
                return new BasicHttpResponse(new BasicStatusLine(HttpVersion.HTTP_1_1, 411, ""));
            case WEAK_ETAG_AND_RANGE_ERROR:
                return new BasicHttpResponse(new BasicStatusLine(HttpVersion.HTTP_1_1, HttpStatus.SC_BAD_REQUEST, "Weak eTag not compatible with byte range"));
            case WEAK_ETAG_ON_PUTDELETE_METHOD_ERROR:
                return new BasicHttpResponse(new BasicStatusLine(HttpVersion.HTTP_1_1, HttpStatus.SC_BAD_REQUEST, "Weak eTag not compatible with PUT or DELETE requests"));
            case NO_CACHE_DIRECTIVE_WITH_FIELD_NAME:
                return new BasicHttpResponse(new BasicStatusLine(HttpVersion.HTTP_1_1, HttpStatus.SC_BAD_REQUEST, "No-Cache directive MUST NOT include a field name"));
            default:
                throw new IllegalStateException("The request was compliant, therefore no error can be generated for it.");
        }
    }

    private RequestProtocolError requestHasWeakETagAndRange(HttpRequest request) {
        if (!"GET".equals(request.getRequestLine().getMethod()) || request.getFirstHeader("Range") == null) {
            return null;
        }
        Header ifRange = request.getFirstHeader("If-Range");
        if (ifRange == null || !ifRange.getValue().startsWith("W/")) {
            return null;
        }
        return RequestProtocolError.WEAK_ETAG_AND_RANGE_ERROR;
    }

    private RequestProtocolError requestHasWeekETagForPUTOrDELETEIfMatch(HttpRequest request) {
        String method = request.getRequestLine().getMethod();
        if (!"PUT".equals(method) && !"DELETE".equals(method)) {
            return null;
        }
        Header ifMatch = request.getFirstHeader("If-Match");
        if (ifMatch == null) {
            Header ifNoneMatch = request.getFirstHeader("If-None-Match");
            if (ifNoneMatch == null || !ifNoneMatch.getValue().startsWith("W/")) {
                return null;
            }
            return RequestProtocolError.WEAK_ETAG_ON_PUTDELETE_METHOD_ERROR;
        } else if (ifMatch.getValue().startsWith("W/")) {
            return RequestProtocolError.WEAK_ETAG_ON_PUTDELETE_METHOD_ERROR;
        } else {
            return null;
        }
    }

    private RequestProtocolError requestContainsNoCacheDirectiveWithFieldName(HttpRequest request) {
        for (Header h : request.getHeaders("Cache-Control")) {
            for (HeaderElement elt : h.getElements()) {
                if (HeaderConstants.CACHE_CONTROL_NO_CACHE.equalsIgnoreCase(elt.getName()) && elt.getValue() != null) {
                    return RequestProtocolError.NO_CACHE_DIRECTIVE_WITH_FIELD_NAME;
                }
            }
        }
        return null;
    }
}
