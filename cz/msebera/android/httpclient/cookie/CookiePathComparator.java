package cz.msebera.android.httpclient.cookie;

import com.github.lzyzsd.jsbridge.BridgeUtil;
import cz.msebera.android.httpclient.annotation.Immutable;
import java.io.Serializable;
import java.util.Comparator;

@Immutable
public class CookiePathComparator implements Serializable, Comparator<Cookie> {
    private static final long serialVersionUID = 7523645369616405818L;

    private String normalizePath(Cookie cookie) {
        String path = cookie.getPath();
        if (path == null) {
            path = BridgeUtil.SPLIT_MARK;
        }
        if (path.endsWith(BridgeUtil.SPLIT_MARK)) {
            return path;
        }
        return path + '/';
    }

    public int compare(Cookie c1, Cookie c2) {
        String path1 = normalizePath(c1);
        String path2 = normalizePath(c2);
        if (path1.equals(path2)) {
            return 0;
        }
        if (path1.startsWith(path2)) {
            return -1;
        }
        if (path2.startsWith(path1)) {
            return 1;
        }
        return 0;
    }
}
