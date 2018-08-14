package cz.msebera.android.httpclient.cookie.params;

import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.params.HttpAbstractParamBean;
import cz.msebera.android.httpclient.params.HttpParams;
import java.util.Collection;

@NotThreadSafe
@Deprecated
public class CookieSpecParamBean extends HttpAbstractParamBean {
    public CookieSpecParamBean(HttpParams params) {
        super(params);
    }

    public void setDatePatterns(Collection<String> patterns) {
        this.params.setParameter(CookieSpecPNames.DATE_PATTERNS, patterns);
    }

    public void setSingleHeader(boolean singleHeader) {
        this.params.setBooleanParameter(CookieSpecPNames.SINGLE_COOKIE_HEADER, singleHeader);
    }
}
