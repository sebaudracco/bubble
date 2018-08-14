package com.facebook.ads.internal.view.p053e;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.facebook.ads.internal.p052j.C1839f;
import com.facebook.ads.internal.p060b.C1909b;
import com.facebook.ads.internal.p069m.C2012c;
import com.facebook.ads.internal.view.p053e.C2323c;
import com.facebook.ads.internal.view.p053e.p054b.C1840m;
import com.facebook.ads.internal.view.p053e.p054b.C1845w;
import com.facebook.ads.internal.view.p053e.p054b.C2229b;
import com.facebook.ads.internal.view.p053e.p054b.C2233h;
import com.facebook.ads.internal.view.p053e.p054b.C2234j;
import com.facebook.ads.internal.view.p053e.p054b.C2235l;
import com.facebook.ads.internal.view.p053e.p054b.C2236n;
import com.facebook.ads.internal.view.p053e.p054b.C2238p;
import com.facebook.ads.internal.view.p053e.p054b.C2239r;
import com.facebook.ads.internal.view.p053e.p054b.C2240s;
import com.facebook.ads.internal.view.p053e.p054b.C2243v;
import com.facebook.ads.internal.view.p053e.p054b.C2244x;
import com.facebook.ads.internal.view.p053e.p054b.C2245y;
import cz.msebera.android.httpclient.HttpStatus;
import java.util.ArrayList;
import java.util.List;

public class C2323c extends C2322d {
    public int f5627a;
    private final C1845w f5628b;
    private final C1839f<C2239r> f5629c;
    private final C1839f<C2233h> f5630d;
    private final C1839f<C2234j> f5631e;
    private final C1839f<C2236n> f5632f;
    private final C1839f<C2229b> f5633g;
    private final C1839f<C2238p> f5634h;
    private final C1839f<C2244x> f5635i;
    private final C1839f<C2245y> f5636j;
    private final C1839f<C2240s> f5637k;
    private final C1840m f5638l;
    private final C2249b f5639m;
    private boolean f5640n;

    class C22501 extends C1845w {
        static final /* synthetic */ boolean f5458a = (!C2323c.class.desiredAssertionStatus());
        final /* synthetic */ C2323c f5459b;

        C22501(C2323c c2323c) {
            this.f5459b = c2323c;
        }

        public void m7124a(C2243v c2243v) {
            if (!f5458a && this.f5459b == null) {
                throw new AssertionError();
            } else if (this.f5459b != null) {
                this.f5459b.m7330e();
            }
        }
    }

    class C22512 extends C1839f<C2240s> {
        final /* synthetic */ C2323c f5460a;

        C22512(C2323c c2323c) {
            this.f5460a = c2323c;
        }

        public Class<C2240s> mo3580a() {
            return C2240s.class;
        }

        public void m7127a(C2240s c2240s) {
            this.f5460a.m7325a(this.f5460a.m7337l(), this.f5460a.m7337l());
        }
    }

    class C22523 extends C1840m {
        final /* synthetic */ C2323c f5461a;

        C22523(C2323c c2323c) {
            this.f5461a = c2323c;
        }

        public void m7129a(C2235l c2235l) {
            this.f5461a.f5627a = this.f5461a.f5639m.getDuration();
        }
    }

    class C22534 extends C1839f<C2239r> {
        static final /* synthetic */ boolean f5462a = (!C2323c.class.desiredAssertionStatus());
        final /* synthetic */ C2323c f5463b;

        C22534(C2323c c2323c) {
            this.f5463b = c2323c;
        }

        public Class<C2239r> mo3580a() {
            return C2239r.class;
        }

        public void m7132a(C2239r c2239r) {
            if (!f5462a && this.f5463b == null) {
                throw new AssertionError();
            } else if (this.f5463b != null) {
                this.f5463b.m7334i();
            }
        }
    }

    class C22545 extends C1839f<C2233h> {
        static final /* synthetic */ boolean f5464a = (!C2323c.class.desiredAssertionStatus());
        final /* synthetic */ C2323c f5465b;

        C22545(C2323c c2323c) {
            this.f5465b = c2323c;
        }

        public Class<C2233h> mo3580a() {
            return C2233h.class;
        }

        public void m7135a(C2233h c2233h) {
            if (!f5464a && this.f5465b == null) {
                throw new AssertionError();
            } else if (this.f5465b != null) {
                this.f5465b.m7335j();
            }
        }
    }

    class C22556 extends C1839f<C2234j> {
        static final /* synthetic */ boolean f5466a = (!C2323c.class.desiredAssertionStatus());
        final /* synthetic */ C2323c f5467b;

        C22556(C2323c c2323c) {
            this.f5467b = c2323c;
        }

        public Class<C2234j> mo3580a() {
            return C2234j.class;
        }

        public void m7138a(C2234j c2234j) {
            if (!f5466a && this.f5467b == null) {
                throw new AssertionError();
            } else if (this.f5467b != null) {
                if (this.f5467b.f5640n) {
                    this.f5467b.m7336k();
                } else {
                    this.f5467b.f5640n = true;
                }
            }
        }
    }

    class C22567 extends C1839f<C2236n> {
        final /* synthetic */ C2323c f5468a;

        C22567(C2323c c2323c) {
            this.f5468a = c2323c;
        }

        public Class<C2236n> mo3580a() {
            return C2236n.class;
        }

        public void m7141a(C2236n c2236n) {
            if (this.f5468a.f5627a <= 0 || this.f5468a.f5639m.getCurrentPosition() != this.f5468a.f5639m.getDuration() || this.f5468a.f5639m.getDuration() <= this.f5468a.f5627a) {
                this.f5468a.m7324a(this.f5468a.f5639m.getCurrentPosition());
            }
        }
    }

    class C22578 extends C1839f<C2229b> {
        final /* synthetic */ C2323c f5469a;

        C22578(C2323c c2323c) {
            this.f5469a = c2323c;
        }

        public Class<C2229b> mo3580a() {
            return C2229b.class;
        }

        public void m7144a(C2229b c2229b) {
            int currentPosition = this.f5469a.f5639m.getCurrentPosition();
            if (this.f5469a.f5627a > 0 && currentPosition == this.f5469a.f5639m.getDuration() && this.f5469a.f5639m.getDuration() > this.f5469a.f5627a) {
                return;
            }
            if (!(currentPosition == 0 && this.f5469a.f5639m.mo3781g()) && this.f5469a.f5639m.getDuration() >= currentPosition + HttpStatus.SC_INTERNAL_SERVER_ERROR) {
                this.f5469a.m7327b(currentPosition);
            } else if (this.f5469a.f5639m.getDuration() == 0) {
                this.f5469a.m7327b(this.f5469a.f5627a);
            } else {
                this.f5469a.m7327b(this.f5469a.f5639m.getDuration());
            }
        }
    }

    class C22589 extends C1839f<C2238p> {
        final /* synthetic */ C2323c f5470a;

        C22589(C2323c c2323c) {
            this.f5470a = c2323c;
        }

        public Class<C2238p> mo3580a() {
            return C2238p.class;
        }

        public void m7147a(C2238p c2238p) {
            this.f5470a.m7325a(c2238p.m7086a(), c2238p.m7087b());
        }
    }

    public C2323c(Context context, C2012c c2012c, C2249b c2249b, String str) {
        this(context, c2012c, c2249b, new ArrayList(), str);
    }

    public C2323c(Context context, C2012c c2012c, C2249b c2249b, String str, @Nullable Bundle bundle) {
        this(context, c2012c, c2249b, new ArrayList(), str, bundle);
    }

    public C2323c(Context context, C2012c c2012c, C2249b c2249b, List<C1909b> list, String str) {
        super(context, c2012c, c2249b, list, str);
        this.f5628b = new C22501(this);
        this.f5629c = new C22534(this);
        this.f5630d = new C22545(this);
        this.f5631e = new C22556(this);
        this.f5632f = new C22567(this);
        this.f5633g = new C22578(this);
        this.f5634h = new C22589(this);
        this.f5635i = new C1839f<C2244x>(this) {
            final /* synthetic */ C2323c f5456a;

            {
                this.f5456a = r1;
            }

            public Class<C2244x> mo3580a() {
                return C2244x.class;
            }

            public void m7119a(C2244x c2244x) {
                this.f5456a.m7326b();
            }
        };
        this.f5636j = new C1839f<C2245y>(this) {
            final /* synthetic */ C2323c f5457a;

            {
                this.f5457a = r1;
            }

            public Class<C2245y> mo3580a() {
                return C2245y.class;
            }

            public void m7122a(C2245y c2245y) {
                this.f5457a.m7328c();
            }
        };
        this.f5637k = new C22512(this);
        this.f5638l = new C22523(this);
        this.f5640n = false;
        this.f5639m = c2249b;
        this.f5639m.getEventBus().m6328a(this.f5628b, this.f5632f, this.f5629c, this.f5631e, this.f5630d, this.f5633g, this.f5634h, this.f5635i, this.f5636j, this.f5638l, this.f5637k);
    }

    public C2323c(Context context, C2012c c2012c, C2249b c2249b, List<C1909b> list, String str, @Nullable Bundle bundle) {
        super(context, c2012c, c2249b, list, str, bundle);
        this.f5628b = new C22501(this);
        this.f5629c = new C22534(this);
        this.f5630d = new C22545(this);
        this.f5631e = new C22556(this);
        this.f5632f = new C22567(this);
        this.f5633g = new C22578(this);
        this.f5634h = new C22589(this);
        this.f5635i = /* anonymous class already generated */;
        this.f5636j = /* anonymous class already generated */;
        this.f5637k = new C22512(this);
        this.f5638l = new C22523(this);
        this.f5640n = false;
        this.f5639m = c2249b;
        this.f5639m.getEventBus().m6328a(this.f5628b, this.f5632f, this.f5629c, this.f5631e, this.f5630d, this.f5633g, this.f5635i, this.f5636j, this.f5637k);
    }

    public void m7341a() {
        this.f5639m.getEventBus().m6330b(this.f5628b, this.f5632f, this.f5629c, this.f5631e, this.f5630d, this.f5633g, this.f5635i, this.f5636j, this.f5638l, this.f5637k);
    }
}
