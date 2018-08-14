package cz.msebera.android.httpclient.impl.client.cache;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.HeaderIterator;
import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.HttpResponse;
import cz.msebera.android.httpclient.HttpStatus;
import cz.msebera.android.httpclient.HttpVersion;
import cz.msebera.android.httpclient.ProtocolVersion;
import cz.msebera.android.httpclient.StatusLine;
import cz.msebera.android.httpclient.annotation.Immutable;
import cz.msebera.android.httpclient.message.AbstractHttpMessage;
import cz.msebera.android.httpclient.message.BasicStatusLine;
import cz.msebera.android.httpclient.params.BasicHttpParams;
import cz.msebera.android.httpclient.params.HttpParams;
import java.util.Locale;

@Immutable
final class OptionsHttp11Response extends AbstractHttpMessage implements HttpResponse {
    private final StatusLine statusLine = new BasicStatusLine(HttpVersion.HTTP_1_1, HttpStatus.SC_NOT_IMPLEMENTED, "");
    private final ProtocolVersion version = HttpVersion.HTTP_1_1;

    OptionsHttp11Response() {
    }

    public StatusLine getStatusLine() {
        return this.statusLine;
    }

    public void setStatusLine(StatusLine statusline) {
    }

    public void setStatusLine(ProtocolVersion ver, int code) {
    }

    public void setStatusLine(ProtocolVersion ver, int code, String reason) {
    }

    public void setStatusCode(int code) throws IllegalStateException {
    }

    public void setReasonPhrase(String reason) throws IllegalStateException {
    }

    public HttpEntity getEntity() {
        return null;
    }

    public void setEntity(HttpEntity entity) {
    }

    public Locale getLocale() {
        return null;
    }

    public void setLocale(Locale loc) {
    }

    public ProtocolVersion getProtocolVersion() {
        return this.version;
    }

    public boolean containsHeader(String name) {
        return this.headergroup.containsHeader(name);
    }

    public Header[] getHeaders(String name) {
        return this.headergroup.getHeaders(name);
    }

    public Header getFirstHeader(String name) {
        return this.headergroup.getFirstHeader(name);
    }

    public Header getLastHeader(String name) {
        return this.headergroup.getLastHeader(name);
    }

    public Header[] getAllHeaders() {
        return this.headergroup.getAllHeaders();
    }

    public void addHeader(Header header) {
    }

    public void addHeader(String name, String value) {
    }

    public void setHeader(Header header) {
    }

    public void setHeader(String name, String value) {
    }

    public void setHeaders(Header[] headers) {
    }

    public void removeHeader(Header header) {
    }

    public void removeHeaders(String name) {
    }

    public HeaderIterator headerIterator() {
        return this.headergroup.iterator();
    }

    public HeaderIterator headerIterator(String name) {
        return this.headergroup.iterator(name);
    }

    public HttpParams getParams() {
        if (this.params == null) {
            this.params = new BasicHttpParams();
        }
        return this.params;
    }

    public void setParams(HttpParams params) {
    }
}
