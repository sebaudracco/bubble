package com.oneaudience.sdk;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.oneaudience.sdk.C3861k.C3858b;
import java.io.Serializable;

class C3882o {
    private static final String f9302a = C3882o.class.getSimpleName();

    public interface C3856b {
    }

    public interface C3857a extends C3856b {
        void mo6813a(C3854n c3854n, Exception exception);
    }

    private static final class C3880c<Params, Progress, Result> extends C3809a<Params, Progress, Result> {
        private final Handler f9295d;
        private final C3854n<Params, Progress, Result> f9296e;
        private final C3856b f9297f;
        private final Context f9298g;

        class C38791 extends Handler {
            final /* synthetic */ C3880c f9294a;

            C38791(C3880c c3880c) {
                this.f9294a = c3880c;
            }

            public void handleMessage(Message message) {
                try {
                    if (message.what == 6 && this.f9294a.f9297f != null && (this.f9294a.f9297f instanceof C3857a)) {
                        Serializable serializable = message.getData().getSerializable("message_extra_exception");
                        if (serializable != null) {
                            ((C3857a) this.f9294a.f9297f).mo6813a(this.f9294a.f9296e, (Exception) serializable);
                        }
                    }
                } catch (Exception e) {
                    C3843e.m12284a(this.f9294a.f9298g, this.f9294a.f9298g.getSharedPreferences("oneaudience", 0), "Failed on ExceptionTaskWrapper, handleMessage: " + e);
                }
            }
        }

        private C3880c(C3854n<Params, Progress, Result> c3854n, C3856b c3856b) {
            this.f9295d = new C38791(this);
            this.f9296e = c3854n;
            this.f9297f = c3856b;
            this.f9298g = ((C3858b) c3856b).f9235a;
        }

        private void m12384a(Exception exception) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("message_extra_exception", exception);
            Message obtain = Message.obtain();
            obtain.what = 6;
            obtain.setData(bundle);
            this.f9295d.sendMessage(obtain);
        }

        protected Result mo6814a(Params... paramsArr) {
            try {
                return this.f9296e.mo6812a((Object[]) paramsArr);
            } catch (Exception e) {
                m12384a(e);
                return null;
            }
        }

        protected void mo6815a() {
            try {
                this.f9296e.m12307a();
            } catch (Exception e) {
                m12384a(e);
            }
        }

        protected void mo6816a(Result result) {
            try {
                this.f9296e.m12308a((Object) result);
            } catch (Exception e) {
                m12384a(e);
            }
        }

        protected void mo6817b() {
            try {
                this.f9296e.m12309b();
            } catch (Exception e) {
                m12384a(e);
            }
        }

        protected void mo6818b(Result result) {
            try {
                this.f9296e.m12310b(result);
            } catch (Exception e) {
                m12384a(e);
            }
        }
    }

    public static final class C3881d<Params, Progress, Result> {
        private final C3854n<Params, Progress, Result> f9299a;
        private C3856b f9300b = null;
        private Params[] f9301c = null;

        public C3881d(C3854n<Params, Progress, Result> c3854n) {
            this.f9299a = c3854n;
        }

        public C3809a m12392a() {
            return new C3880c(this.f9299a, this.f9300b).m12193a(C3809a.f9148c, this.f9301c);
        }

        public C3881d m12393a(C3856b c3856b) {
            this.f9300b = c3856b;
            return this;
        }

        public C3881d m12394a(Params... paramsArr) {
            this.f9301c = paramsArr;
            return this;
        }
    }

    C3882o() {
    }
}
