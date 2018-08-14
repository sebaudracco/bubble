package cz.msebera.android.httpclient.auth.params;

import cz.msebera.android.httpclient.params.HttpAbstractParamBean;
import cz.msebera.android.httpclient.params.HttpParams;

@Deprecated
public class AuthParamBean extends HttpAbstractParamBean {
    public AuthParamBean(HttpParams params) {
        super(params);
    }

    public void setCredentialCharset(String charset) {
        AuthParams.setCredentialCharset(this.params, charset);
    }
}
