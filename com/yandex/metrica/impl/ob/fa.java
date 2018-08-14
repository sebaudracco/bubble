package com.yandex.metrica.impl.ob;

import java.io.IOException;
import java.security.GeneralSecurityException;

class fa {
    private static fh f12371a;
    private static ey f12372b;
    private static fs f12373c;

    private static class C4470a implements ey {
        private static final String[] f12368a = new String[]{"LNFe+yc4/NZbJVynpxAeAd+brU3EPwGbtwF6VeUjI/Y=", "PL1/TTDEe9Cm2lb2X0tixyQC7zaPREm/V0IHJscTCmw=", "+B0DgmKB5hWEuHib00m2jvCJWBlOYI0NGTMmVjaVrJA=", "dy/Myn0WRtYGKBNP8ubn9boJWJi+WWmLzp0V+W9pqfM=", "OB84k4abNNzWpMVBdhI+TSgQmCqTKdPPQrwq6j4YdMU=", "AZQG1XXPKFo8LYu/gTPgz65IOcmcwYFb3yREhyWefNI=", "iZEDYF5LpvyxpOX9+x3+qDBXhdByZOUFatBA3JgW7sY=", "IQBnNBEiFuhj+8x6X8XLgh01V9Ic5/V3IRQLNFFc7v4=", "LvRiGEjRqfzurezaWuj8Wie2gyHMrW5Q06LspMnox7A="};
        private final fb f12369b;
        private final fb f12370c;

        private C4470a(fe feVar) throws IOException {
            ev enVar = new en(feVar.m16000b(), "lib");
            this.f12369b = new fb(enVar, "LIB-BLACK");
            this.f12370c = new fb(enVar, "LIB-TRUST", f12368a);
        }

        public fb mo7091a() {
            return this.f12369b;
        }

        public fb mo7092b() {
            throw new UnsupportedOperationException("white list isn't supported in shared container");
        }

        public fb mo7093c() {
            return this.f12370c;
        }
    }

    static synchronized fh m15981a(fe feVar) {
        fh fhVar;
        synchronized (fa.class) {
            if (f12371a == null) {
                f12371a = new fh(feVar, m15982b(feVar), m15983c(feVar), new fd());
            }
            fhVar = f12371a;
        }
        return fhVar;
    }

    static synchronized ey m15982b(fe feVar) {
        ey eyVar;
        synchronized (fa.class) {
            if (f12372b == null) {
                try {
                    f12372b = new C4470a(feVar);
                } catch (IOException e) {
                    f12372b = new et();
                }
            }
            eyVar = f12372b;
        }
        return eyVar;
    }

    static synchronized fs m15983c(fe feVar) {
        fs fsVar;
        synchronized (fa.class) {
            if (f12373c == null) {
                try {
                    f12373c = feVar.m16002d();
                } catch (GeneralSecurityException e) {
                } catch (IOException e2) {
                }
            }
            fsVar = f12373c;
        }
        return fsVar;
    }
}
