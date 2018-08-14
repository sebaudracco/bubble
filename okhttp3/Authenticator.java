package okhttp3;

import java.io.IOException;

public interface Authenticator {
    public static final Authenticator NONE = new C47361();

    static class C47361 implements Authenticator {
        C47361() {
        }

        public Request authenticate(Route route, Response response) {
            return null;
        }
    }

    Request authenticate(Route route, Response response) throws IOException;
}
