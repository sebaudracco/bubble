package com.inmobi.ads.p110a;

import android.os.SystemClock;
import android.support.annotation.NonNull;
import com.inmobi.ads.C3029b;
import com.inmobi.commons.core.network.C3143c;
import com.inmobi.commons.core.network.C3144d;
import com.inmobi.commons.core.network.NetworkRequest;
import com.inmobi.commons.core.network.NetworkRequest.RequestType;
import com.inmobi.commons.core.p115d.C3132b;
import com.inmobi.commons.core.p115d.C3135c;
import com.inmobi.commons.core.utilities.Logger;
import com.inmobi.commons.core.utilities.Logger.InternalLogLevel;
import com.inmobi.commons.p112a.C3105a;
import com.inmobi.signals.C3276n;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/* compiled from: AdAssetFetcher */
public final class C2967a {
    private static final String f6951a = C2967a.class.getSimpleName();
    private C2966a f6952b;
    private String f6953c;

    /* compiled from: AdAssetFetcher */
    public interface C2966a {
        void mo6243a(C3029b c3029b, String str);

        void mo6244a(C3143c c3143c, String str, C3029b c3029b, String str2);
    }

    public C2967a(C2966a c2966a, String str) {
        this.f6952b = c2966a;
        this.f6953c = str;
    }

    public void m9118a(@NonNull C3029b c3029b) {
        Throwable e;
        BufferedOutputStream bufferedOutputStream;
        Object obj;
        BufferedOutputStream bufferedOutputStream2 = null;
        Logger.m10359a(InternalLogLevel.INTERNAL, f6951a, "Fetching asset (" + c3029b.m9467b() + ")");
        long elapsedRealtime = SystemClock.elapsedRealtime();
        NetworkRequest networkRequest = new NetworkRequest(RequestType.GET, c3029b.m9467b(), false, null);
        networkRequest.m9751a(false);
        C3143c a = new C3144d(networkRequest).m10357a();
        elapsedRealtime = SystemClock.elapsedRealtime() - elapsedRealtime;
        c3029b.m9466a(elapsedRealtime);
        try {
            C3276n.m10977a().m10979a(networkRequest.m9772t());
            C3276n.m10977a().m10981b(a.m10356f());
            C3276n.m10977a().m10988g(elapsedRealtime);
        } catch (Throwable e2) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f6951a, "Error in setting request-response data size. " + e2.getMessage());
            C3135c.m10255a().m10279a(new C3132b(e2));
        }
        try {
            if (!a.m10351a()) {
                File a2 = m9116a(c3029b.m9467b());
                try {
                    bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(a2));
                    try {
                        bufferedOutputStream.write(a.m10353c());
                        if (bufferedOutputStream != null) {
                            try {
                                bufferedOutputStream.close();
                            } catch (IOException e3) {
                                Logger.m10359a(InternalLogLevel.INTERNAL, f6951a, e3.getMessage());
                            }
                        }
                        if (this.f6952b != null) {
                            Logger.m10359a(InternalLogLevel.INTERNAL, f6951a, "onAssetFetchSucceeded; location on disk: " + a2.getAbsolutePath());
                            this.f6952b.mo6244a(a, a2.getAbsolutePath(), c3029b, this.f6953c);
                        }
                    } catch (IOException e4) {
                        e2 = e4;
                        try {
                            Logger.m10359a(InternalLogLevel.INTERNAL, f6951a, e2.getMessage());
                            C3135c.m10255a().m10279a(new C3132b(e2));
                            if (bufferedOutputStream != null) {
                                try {
                                    bufferedOutputStream.close();
                                } catch (IOException e32) {
                                    Logger.m10359a(InternalLogLevel.INTERNAL, f6951a, e32.getMessage());
                                }
                            }
                            if (this.f6952b != null) {
                                Logger.m10359a(InternalLogLevel.INTERNAL, f6951a, "onAssetFetchFailed");
                                this.f6952b.mo6243a(c3029b, this.f6953c);
                            }
                        } catch (Throwable th) {
                            e2 = th;
                            bufferedOutputStream2 = bufferedOutputStream;
                            int i = 1;
                            if (bufferedOutputStream2 != null) {
                                try {
                                    bufferedOutputStream2.close();
                                } catch (IOException e5) {
                                    Logger.m10359a(InternalLogLevel.INTERNAL, f6951a, e5.getMessage());
                                }
                            }
                            if (this.f6952b != null) {
                                if (obj != null) {
                                    Logger.m10359a(InternalLogLevel.INTERNAL, f6951a, "onAssetFetchFailed");
                                    this.f6952b.mo6243a(c3029b, this.f6953c);
                                } else {
                                    Logger.m10359a(InternalLogLevel.INTERNAL, f6951a, "onAssetFetchSucceeded; location on disk: " + a2.getAbsolutePath());
                                    this.f6952b.mo6244a(a, a2.getAbsolutePath(), c3029b, this.f6953c);
                                }
                            }
                            throw e2;
                        }
                    } catch (Throwable th2) {
                        e2 = th2;
                        bufferedOutputStream2 = bufferedOutputStream;
                        obj = null;
                        if (bufferedOutputStream2 != null) {
                            bufferedOutputStream2.close();
                        }
                        if (this.f6952b != null) {
                            if (obj != null) {
                                Logger.m10359a(InternalLogLevel.INTERNAL, f6951a, "onAssetFetchFailed");
                                this.f6952b.mo6243a(c3029b, this.f6953c);
                            } else {
                                Logger.m10359a(InternalLogLevel.INTERNAL, f6951a, "onAssetFetchSucceeded; location on disk: " + a2.getAbsolutePath());
                                this.f6952b.mo6244a(a, a2.getAbsolutePath(), c3029b, this.f6953c);
                            }
                        }
                        throw e2;
                    }
                } catch (IOException e6) {
                    e2 = e6;
                    bufferedOutputStream = null;
                    Logger.m10359a(InternalLogLevel.INTERNAL, f6951a, e2.getMessage());
                    C3135c.m10255a().m10279a(new C3132b(e2));
                    if (bufferedOutputStream != null) {
                        bufferedOutputStream.close();
                    }
                    if (this.f6952b != null) {
                        Logger.m10359a(InternalLogLevel.INTERNAL, f6951a, "onAssetFetchFailed");
                        this.f6952b.mo6243a(c3029b, this.f6953c);
                    }
                } catch (Throwable th3) {
                    e2 = th3;
                    obj = null;
                    if (bufferedOutputStream2 != null) {
                        bufferedOutputStream2.close();
                    }
                    if (this.f6952b != null) {
                        if (obj != null) {
                            Logger.m10359a(InternalLogLevel.INTERNAL, f6951a, "onAssetFetchSucceeded; location on disk: " + a2.getAbsolutePath());
                            this.f6952b.mo6244a(a, a2.getAbsolutePath(), c3029b, this.f6953c);
                        } else {
                            Logger.m10359a(InternalLogLevel.INTERNAL, f6951a, "onAssetFetchFailed");
                            this.f6952b.mo6243a(c3029b, this.f6953c);
                        }
                    }
                    throw e2;
                }
            } else if (this.f6952b != null) {
                this.f6952b.mo6243a(c3029b, this.f6953c);
            }
        } catch (Throwable e22) {
            Logger.m10359a(InternalLogLevel.INTERNAL, f6951a, "Encountered unexpected error in fetching asset: " + e22.getMessage());
            this.f6952b.mo6243a(c3029b, this.f6953c);
            C3135c.m10255a().m10279a(new C3132b(e22));
        }
    }

    private File m9116a(String str) {
        C3105a.m10089f();
        return new File(C3105a.m10079b(C3105a.m10078b()), m9117b(str));
    }

    private String m9117b(String str) {
        int length = str.length() / 2;
        return String.valueOf(str.substring(0, length).hashCode() & Integer.MAX_VALUE) + String.valueOf(str.substring(length).hashCode() & Integer.MAX_VALUE);
    }
}
