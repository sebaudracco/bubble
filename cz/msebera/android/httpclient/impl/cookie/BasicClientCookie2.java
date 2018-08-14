package cz.msebera.android.httpclient.impl.cookie;

import cz.msebera.android.httpclient.annotation.NotThreadSafe;
import cz.msebera.android.httpclient.cookie.SetCookie2;
import java.util.Date;

@NotThreadSafe
public class BasicClientCookie2 extends BasicClientCookie implements SetCookie2 {
    private static final long serialVersionUID = -7744598295706617057L;
    private String commentURL;
    private boolean discard;
    private int[] ports;

    public BasicClientCookie2(String name, String value) {
        super(name, value);
    }

    public int[] getPorts() {
        return this.ports;
    }

    public void setPorts(int[] ports) {
        this.ports = ports;
    }

    public String getCommentURL() {
        return this.commentURL;
    }

    public void setCommentURL(String commentURL) {
        this.commentURL = commentURL;
    }

    public void setDiscard(boolean discard) {
        this.discard = discard;
    }

    public boolean isPersistent() {
        return !this.discard && super.isPersistent();
    }

    public boolean isExpired(Date date) {
        return this.discard || super.isExpired(date);
    }

    public Object clone() throws CloneNotSupportedException {
        BasicClientCookie2 clone = (BasicClientCookie2) super.clone();
        if (this.ports != null) {
            clone.ports = (int[]) this.ports.clone();
        }
        return clone;
    }
}
