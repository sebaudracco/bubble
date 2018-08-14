package cz.msebera.android.httpclient.conn.params;

import cz.msebera.android.httpclient.params.HttpAbstractParamBean;
import cz.msebera.android.httpclient.params.HttpParams;

@Deprecated
public class ConnConnectionParamBean extends HttpAbstractParamBean {
    public ConnConnectionParamBean(HttpParams params) {
        super(params);
    }

    @Deprecated
    public void setMaxStatusLineGarbage(int maxStatusLineGarbage) {
        this.params.setIntParameter(ConnConnectionPNames.MAX_STATUS_LINE_GARBAGE, maxStatusLineGarbage);
    }
}
