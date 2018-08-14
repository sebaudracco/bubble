package com.yandex.metrica;

import com.yandex.metrica.impl.ob.C4277d;
import com.yandex.metrica.impl.ob.C4392b;
import com.yandex.metrica.impl.ob.C4422c;
import com.yandex.metrica.impl.ob.C4469f;
import java.io.IOException;
import java.util.Arrays;

public interface C4292c {

    public static final class C4291a extends C4277d {
        public C4290f f11515b;
        public C4288d[] f11516c;
        public C4278a[] f11517d;
        public C4280c[] f11518e;
        public String[] f11519f;
        public C4289e[] f11520g;

        public static final class C4278a extends C4277d {
            private static volatile C4278a[] f11448d;
            public String f11449b;
            public String f11450c;

            public static C4278a[] m14320d() {
                if (f11448d == null) {
                    synchronized (C4422c.f12015a) {
                        if (f11448d == null) {
                            f11448d = new C4278a[0];
                        }
                    }
                }
                return f11448d;
            }

            public C4278a() {
                m14323e();
            }

            public C4278a m14323e() {
                this.f11449b = "";
                this.f11450c = "";
                this.a = -1;
                return this;
            }

            public void mo6966a(C4392b c4392b) throws IOException {
                c4392b.m15200a(1, this.f11449b);
                c4392b.m15200a(2, this.f11450c);
                super.mo6966a(c4392b);
            }

            protected int mo6967c() {
                return (super.mo6967c() + C4392b.m15177b(1, this.f11449b)) + C4392b.m15177b(2, this.f11450c);
            }
        }

        public static final class C4279b extends C4277d {
            public double f11451b;
            public double f11452c;
            public long f11453d;
            public int f11454e;
            public int f11455f;
            public int f11456g;
            public int f11457h;
            public int f11458i;

            public C4279b() {
                m14326d();
            }

            public C4279b m14326d() {
                this.f11451b = 0.0d;
                this.f11452c = 0.0d;
                this.f11453d = 0;
                this.f11454e = 0;
                this.f11455f = 0;
                this.f11456g = 0;
                this.f11457h = 0;
                this.f11458i = 0;
                this.a = -1;
                return this;
            }

            public void mo6966a(C4392b c4392b) throws IOException {
                c4392b.m15196a(1, this.f11451b);
                c4392b.m15196a(2, this.f11452c);
                if (this.f11453d != 0) {
                    c4392b.m15198a(3, this.f11453d);
                }
                if (this.f11454e != 0) {
                    c4392b.m15210b(4, this.f11454e);
                }
                if (this.f11455f != 0) {
                    c4392b.m15210b(5, this.f11455f);
                }
                if (this.f11456g != 0) {
                    c4392b.m15210b(6, this.f11456g);
                }
                if (this.f11457h != 0) {
                    c4392b.m15197a(7, this.f11457h);
                }
                if (this.f11458i != 0) {
                    c4392b.m15197a(8, this.f11458i);
                }
                super.mo6966a(c4392b);
            }

            protected int mo6967c() {
                int c = (super.mo6967c() + C4392b.m15182d(1)) + C4392b.m15182d(2);
                if (this.f11453d != 0) {
                    c += C4392b.m15181c(3, this.f11453d);
                }
                if (this.f11454e != 0) {
                    c += C4392b.m15187e(4, this.f11454e);
                }
                if (this.f11455f != 0) {
                    c += C4392b.m15187e(5, this.f11455f);
                }
                if (this.f11456g != 0) {
                    c += C4392b.m15187e(6, this.f11456g);
                }
                if (this.f11457h != 0) {
                    c += C4392b.m15183d(7, this.f11457h);
                }
                if (this.f11458i != 0) {
                    return c + C4392b.m15183d(8, this.f11458i);
                }
                return c;
            }
        }

        public static final class C4280c extends C4277d {
            private static volatile C4280c[] f11459d;
            public String f11460b;
            public String f11461c;

            public static C4280c[] m14327d() {
                if (f11459d == null) {
                    synchronized (C4422c.f12015a) {
                        if (f11459d == null) {
                            f11459d = new C4280c[0];
                        }
                    }
                }
                return f11459d;
            }

            public C4280c() {
                m14330e();
            }

            public C4280c m14330e() {
                this.f11460b = "";
                this.f11461c = "";
                this.a = -1;
                return this;
            }

            public void mo6966a(C4392b c4392b) throws IOException {
                c4392b.m15200a(1, this.f11460b);
                c4392b.m15200a(2, this.f11461c);
                super.mo6966a(c4392b);
            }

            protected int mo6967c() {
                return (super.mo6967c() + C4392b.m15177b(1, this.f11460b)) + C4392b.m15177b(2, this.f11461c);
            }
        }

        public static final class C4288d extends C4277d {
            private static volatile C4288d[] f11502e;
            public long f11503b;
            public C4286b f11504c;
            public C4285a[] f11505d;

            public static final class C4285a extends C4277d {
                private static volatile C4285a[] f11482m;
                public long f11483b;
                public long f11484c;
                public int f11485d;
                public String f11486e;
                public byte[] f11487f;
                public C4279b f11488g;
                public C4284b f11489h;
                public String f11490i;
                public C4281a f11491j;
                public int f11492k;
                public int f11493l;

                public static final class C4281a extends C4277d {
                    public String f11462b;
                    public String f11463c;
                    public String f11464d;

                    public C4281a() {
                        m14333d();
                    }

                    public C4281a m14333d() {
                        this.f11462b = "";
                        this.f11463c = "";
                        this.f11464d = "";
                        this.a = -1;
                        return this;
                    }

                    public void mo6966a(C4392b c4392b) throws IOException {
                        c4392b.m15200a(1, this.f11462b);
                        if (!this.f11463c.equals("")) {
                            c4392b.m15200a(2, this.f11463c);
                        }
                        if (!this.f11464d.equals("")) {
                            c4392b.m15200a(3, this.f11464d);
                        }
                        super.mo6966a(c4392b);
                    }

                    protected int mo6967c() {
                        int c = super.mo6967c() + C4392b.m15177b(1, this.f11462b);
                        if (!this.f11463c.equals("")) {
                            c += C4392b.m15177b(2, this.f11463c);
                        }
                        if (this.f11464d.equals("")) {
                            return c;
                        }
                        return c + C4392b.m15177b(3, this.f11464d);
                    }
                }

                public static final class C4284b extends C4277d {
                    public C4282a[] f11477b;
                    public C4287c[] f11478c;
                    public int f11479d;
                    public String f11480e;
                    public C4283b f11481f;

                    public static final class C4282a extends C4277d {
                        private static volatile C4282a[] f11465k;
                        public int f11466b;
                        public int f11467c;
                        public int f11468d;
                        public int f11469e;
                        public int f11470f;
                        public String f11471g;
                        public boolean f11472h;
                        public int f11473i;
                        public int f11474j;

                        public static C4282a[] m14334d() {
                            if (f11465k == null) {
                                synchronized (C4422c.f12015a) {
                                    if (f11465k == null) {
                                        f11465k = new C4282a[0];
                                    }
                                }
                            }
                            return f11465k;
                        }

                        public C4282a() {
                            m14337e();
                        }

                        public C4282a m14337e() {
                            this.f11466b = -1;
                            this.f11467c = 0;
                            this.f11468d = -1;
                            this.f11469e = -1;
                            this.f11470f = -1;
                            this.f11471g = "";
                            this.f11472h = false;
                            this.f11473i = 0;
                            this.f11474j = -1;
                            this.a = -1;
                            return this;
                        }

                        public void mo6966a(C4392b c4392b) throws IOException {
                            if (this.f11466b != -1) {
                                c4392b.m15210b(1, this.f11466b);
                            }
                            if (this.f11467c != 0) {
                                c4392b.m15216c(2, this.f11467c);
                            }
                            if (this.f11468d != -1) {
                                c4392b.m15210b(3, this.f11468d);
                            }
                            if (this.f11469e != -1) {
                                c4392b.m15210b(4, this.f11469e);
                            }
                            if (this.f11470f != -1) {
                                c4392b.m15210b(5, this.f11470f);
                            }
                            if (!this.f11471g.equals("")) {
                                c4392b.m15200a(6, this.f11471g);
                            }
                            if (this.f11472h) {
                                c4392b.m15201a(7, this.f11472h);
                            }
                            if (this.f11473i != 0) {
                                c4392b.m15197a(8, this.f11473i);
                            }
                            if (this.f11474j != -1) {
                                c4392b.m15210b(9, this.f11474j);
                            }
                            super.mo6966a(c4392b);
                        }

                        protected int mo6967c() {
                            int c = super.mo6967c();
                            if (this.f11466b != -1) {
                                c += C4392b.m15187e(1, this.f11466b);
                            }
                            if (this.f11467c != 0) {
                                c += C4392b.m15188f(2, this.f11467c);
                            }
                            if (this.f11468d != -1) {
                                c += C4392b.m15187e(3, this.f11468d);
                            }
                            if (this.f11469e != -1) {
                                c += C4392b.m15187e(4, this.f11469e);
                            }
                            if (this.f11470f != -1) {
                                c += C4392b.m15187e(5, this.f11470f);
                            }
                            if (!this.f11471g.equals("")) {
                                c += C4392b.m15177b(6, this.f11471g);
                            }
                            if (this.f11472h) {
                                c += C4392b.m15186e(7);
                            }
                            if (this.f11473i != 0) {
                                c += C4392b.m15183d(8, this.f11473i);
                            }
                            if (this.f11474j != -1) {
                                return c + C4392b.m15187e(9, this.f11474j);
                            }
                            return c;
                        }
                    }

                    public static final class C4283b extends C4277d {
                        public String f11475b;
                        public int f11476c;

                        public C4283b() {
                            m14340d();
                        }

                        public C4283b m14340d() {
                            this.f11475b = "";
                            this.f11476c = 0;
                            this.a = -1;
                            return this;
                        }

                        public void mo6966a(C4392b c4392b) throws IOException {
                            c4392b.m15200a(1, this.f11475b);
                            if (this.f11476c != 0) {
                                c4392b.m15197a(2, this.f11476c);
                            }
                            super.mo6966a(c4392b);
                        }

                        protected int mo6967c() {
                            int c = super.mo6967c() + C4392b.m15177b(1, this.f11475b);
                            if (this.f11476c != 0) {
                                return c + C4392b.m15183d(2, this.f11476c);
                            }
                            return c;
                        }
                    }

                    public C4284b() {
                        m14343d();
                    }

                    public C4284b m14343d() {
                        this.f11477b = C4282a.m14334d();
                        this.f11478c = C4287c.m14351d();
                        this.f11479d = 2;
                        this.f11480e = "";
                        this.f11481f = null;
                        this.a = -1;
                        return this;
                    }

                    public void mo6966a(C4392b c4392b) throws IOException {
                        int i = 0;
                        if (this.f11477b != null && this.f11477b.length > 0) {
                            for (C4277d c4277d : this.f11477b) {
                                if (c4277d != null) {
                                    c4392b.m15199a(1, c4277d);
                                }
                            }
                        }
                        if (this.f11478c != null && this.f11478c.length > 0) {
                            while (i < this.f11478c.length) {
                                C4277d c4277d2 = this.f11478c[i];
                                if (c4277d2 != null) {
                                    c4392b.m15199a(2, c4277d2);
                                }
                                i++;
                            }
                        }
                        if (this.f11479d != 2) {
                            c4392b.m15197a(3, this.f11479d);
                        }
                        if (!this.f11480e.equals("")) {
                            c4392b.m15200a(4, this.f11480e);
                        }
                        if (this.f11481f != null) {
                            c4392b.m15199a(5, this.f11481f);
                        }
                        super.mo6966a(c4392b);
                    }

                    protected int mo6967c() {
                        int i = 0;
                        int c = super.mo6967c();
                        if (this.f11477b != null && this.f11477b.length > 0) {
                            int i2 = c;
                            for (C4277d c4277d : this.f11477b) {
                                if (c4277d != null) {
                                    i2 += C4392b.m15176b(1, c4277d);
                                }
                            }
                            c = i2;
                        }
                        if (this.f11478c != null && this.f11478c.length > 0) {
                            while (i < this.f11478c.length) {
                                C4277d c4277d2 = this.f11478c[i];
                                if (c4277d2 != null) {
                                    c += C4392b.m15176b(2, c4277d2);
                                }
                                i++;
                            }
                        }
                        if (this.f11479d != 2) {
                            c += C4392b.m15183d(3, this.f11479d);
                        }
                        if (!this.f11480e.equals("")) {
                            c += C4392b.m15177b(4, this.f11480e);
                        }
                        if (this.f11481f != null) {
                            return c + C4392b.m15176b(5, this.f11481f);
                        }
                        return c;
                    }
                }

                public static C4285a[] m14344d() {
                    if (f11482m == null) {
                        synchronized (C4422c.f12015a) {
                            if (f11482m == null) {
                                f11482m = new C4285a[0];
                            }
                        }
                    }
                    return f11482m;
                }

                public C4285a() {
                    m14347e();
                }

                public C4285a m14347e() {
                    this.f11483b = 0;
                    this.f11484c = 0;
                    this.f11485d = 0;
                    this.f11486e = "";
                    this.f11487f = C4469f.f12367b;
                    this.f11488g = null;
                    this.f11489h = null;
                    this.f11490i = "";
                    this.f11491j = null;
                    this.f11492k = 0;
                    this.f11493l = 0;
                    this.a = -1;
                    return this;
                }

                public void mo6966a(C4392b c4392b) throws IOException {
                    c4392b.m15198a(1, this.f11483b);
                    c4392b.m15198a(2, this.f11484c);
                    c4392b.m15210b(3, this.f11485d);
                    if (!this.f11486e.equals("")) {
                        c4392b.m15200a(4, this.f11486e);
                    }
                    if (!Arrays.equals(this.f11487f, C4469f.f12367b)) {
                        c4392b.m15202a(5, this.f11487f);
                    }
                    if (this.f11488g != null) {
                        c4392b.m15199a(6, this.f11488g);
                    }
                    if (this.f11489h != null) {
                        c4392b.m15199a(7, this.f11489h);
                    }
                    if (!this.f11490i.equals("")) {
                        c4392b.m15200a(8, this.f11490i);
                    }
                    if (this.f11491j != null) {
                        c4392b.m15199a(9, this.f11491j);
                    }
                    if (this.f11492k != 0) {
                        c4392b.m15210b(10, this.f11492k);
                    }
                    if (this.f11493l != 0) {
                        c4392b.m15197a(12, this.f11493l);
                    }
                    super.mo6966a(c4392b);
                }

                protected int mo6967c() {
                    int c = ((super.mo6967c() + C4392b.m15181c(1, this.f11483b)) + C4392b.m15181c(2, this.f11484c)) + C4392b.m15187e(3, this.f11485d);
                    if (!this.f11486e.equals("")) {
                        c += C4392b.m15177b(4, this.f11486e);
                    }
                    if (!Arrays.equals(this.f11487f, C4469f.f12367b)) {
                        c += C4392b.m15178b(5, this.f11487f);
                    }
                    if (this.f11488g != null) {
                        c += C4392b.m15176b(6, this.f11488g);
                    }
                    if (this.f11489h != null) {
                        c += C4392b.m15176b(7, this.f11489h);
                    }
                    if (!this.f11490i.equals("")) {
                        c += C4392b.m15177b(8, this.f11490i);
                    }
                    if (this.f11491j != null) {
                        c += C4392b.m15176b(9, this.f11491j);
                    }
                    if (this.f11492k != 0) {
                        c += C4392b.m15187e(10, this.f11492k);
                    }
                    if (this.f11493l != 0) {
                        return c + C4392b.m15183d(12, this.f11493l);
                    }
                    return c;
                }
            }

            public static final class C4286b extends C4277d {
                public C4290f f11494b;
                public String f11495c;
                public int f11496d;

                public C4286b() {
                    m14350d();
                }

                public C4286b m14350d() {
                    this.f11494b = null;
                    this.f11495c = "";
                    this.f11496d = 0;
                    this.a = -1;
                    return this;
                }

                public void mo6966a(C4392b c4392b) throws IOException {
                    if (this.f11494b != null) {
                        c4392b.m15199a(1, this.f11494b);
                    }
                    c4392b.m15200a(2, this.f11495c);
                    if (this.f11496d != 0) {
                        c4392b.m15197a(5, this.f11496d);
                    }
                    super.mo6966a(c4392b);
                }

                protected int mo6967c() {
                    int c = super.mo6967c();
                    if (this.f11494b != null) {
                        c += C4392b.m15176b(1, this.f11494b);
                    }
                    c += C4392b.m15177b(2, this.f11495c);
                    if (this.f11496d != 0) {
                        return c + C4392b.m15183d(5, this.f11496d);
                    }
                    return c;
                }
            }

            public static final class C4287c extends C4277d {
                private static volatile C4287c[] f11497f;
                public String f11498b;
                public int f11499c;
                public String f11500d;
                public boolean f11501e;

                public static C4287c[] m14351d() {
                    if (f11497f == null) {
                        synchronized (C4422c.f12015a) {
                            if (f11497f == null) {
                                f11497f = new C4287c[0];
                            }
                        }
                    }
                    return f11497f;
                }

                public C4287c() {
                    m14354e();
                }

                public C4287c m14354e() {
                    this.f11498b = "";
                    this.f11499c = 0;
                    this.f11500d = "";
                    this.f11501e = false;
                    this.a = -1;
                    return this;
                }

                public void mo6966a(C4392b c4392b) throws IOException {
                    c4392b.m15200a(1, this.f11498b);
                    if (this.f11499c != 0) {
                        c4392b.m15216c(2, this.f11499c);
                    }
                    if (!this.f11500d.equals("")) {
                        c4392b.m15200a(3, this.f11500d);
                    }
                    if (this.f11501e) {
                        c4392b.m15201a(4, this.f11501e);
                    }
                    super.mo6966a(c4392b);
                }

                protected int mo6967c() {
                    int c = super.mo6967c() + C4392b.m15177b(1, this.f11498b);
                    if (this.f11499c != 0) {
                        c += C4392b.m15188f(2, this.f11499c);
                    }
                    if (!this.f11500d.equals("")) {
                        c += C4392b.m15177b(3, this.f11500d);
                    }
                    if (this.f11501e) {
                        return c + C4392b.m15186e(4);
                    }
                    return c;
                }
            }

            public static C4288d[] m14355d() {
                if (f11502e == null) {
                    synchronized (C4422c.f12015a) {
                        if (f11502e == null) {
                            f11502e = new C4288d[0];
                        }
                    }
                }
                return f11502e;
            }

            public C4288d() {
                m14358e();
            }

            public C4288d m14358e() {
                this.f11503b = 0;
                this.f11504c = null;
                this.f11505d = C4285a.m14344d();
                this.a = -1;
                return this;
            }

            public void mo6966a(C4392b c4392b) throws IOException {
                c4392b.m15198a(1, this.f11503b);
                if (this.f11504c != null) {
                    c4392b.m15199a(2, this.f11504c);
                }
                if (this.f11505d != null && this.f11505d.length > 0) {
                    for (C4277d c4277d : this.f11505d) {
                        if (c4277d != null) {
                            c4392b.m15199a(3, c4277d);
                        }
                    }
                }
                super.mo6966a(c4392b);
            }

            protected int mo6967c() {
                int c = super.mo6967c() + C4392b.m15181c(1, this.f11503b);
                if (this.f11504c != null) {
                    c += C4392b.m15176b(2, this.f11504c);
                }
                if (this.f11505d == null || this.f11505d.length <= 0) {
                    return c;
                }
                int i = c;
                for (C4277d c4277d : this.f11505d) {
                    if (c4277d != null) {
                        i += C4392b.m15176b(3, c4277d);
                    }
                }
                return i;
            }
        }

        public static final class C4289e extends C4277d {
            private static volatile C4289e[] f11506g;
            public int f11507b;
            public int f11508c;
            public String f11509d;
            public boolean f11510e;
            public String f11511f;

            public static C4289e[] m14359d() {
                if (f11506g == null) {
                    synchronized (C4422c.f12015a) {
                        if (f11506g == null) {
                            f11506g = new C4289e[0];
                        }
                    }
                }
                return f11506g;
            }

            public C4289e() {
                m14362e();
            }

            public C4289e m14362e() {
                this.f11507b = 0;
                this.f11508c = 0;
                this.f11509d = "";
                this.f11510e = false;
                this.f11511f = "";
                this.a = -1;
                return this;
            }

            public void mo6966a(C4392b c4392b) throws IOException {
                if (this.f11507b != 0) {
                    c4392b.m15210b(1, this.f11507b);
                }
                if (this.f11508c != 0) {
                    c4392b.m15210b(2, this.f11508c);
                }
                if (!this.f11509d.equals("")) {
                    c4392b.m15200a(3, this.f11509d);
                }
                if (this.f11510e) {
                    c4392b.m15201a(4, this.f11510e);
                }
                if (!this.f11511f.equals("")) {
                    c4392b.m15200a(5, this.f11511f);
                }
                super.mo6966a(c4392b);
            }

            protected int mo6967c() {
                int c = super.mo6967c();
                if (this.f11507b != 0) {
                    c += C4392b.m15187e(1, this.f11507b);
                }
                if (this.f11508c != 0) {
                    c += C4392b.m15187e(2, this.f11508c);
                }
                if (!this.f11509d.equals("")) {
                    c += C4392b.m15177b(3, this.f11509d);
                }
                if (this.f11510e) {
                    c += C4392b.m15186e(4);
                }
                if (this.f11511f.equals("")) {
                    return c;
                }
                return c + C4392b.m15177b(5, this.f11511f);
            }
        }

        public static final class C4290f extends C4277d {
            public long f11512b;
            public int f11513c;
            public long f11514d;

            public C4290f() {
                m14365d();
            }

            public C4290f m14365d() {
                this.f11512b = 0;
                this.f11513c = 0;
                this.f11514d = 0;
                this.a = -1;
                return this;
            }

            public void mo6966a(C4392b c4392b) throws IOException {
                c4392b.m15198a(1, this.f11512b);
                c4392b.m15216c(2, this.f11513c);
                if (this.f11514d != 0) {
                    c4392b.m15211b(3, this.f11514d);
                }
                super.mo6966a(c4392b);
            }

            protected int mo6967c() {
                int c = (super.mo6967c() + C4392b.m15181c(1, this.f11512b)) + C4392b.m15188f(2, this.f11513c);
                if (this.f11514d != 0) {
                    return c + C4392b.m15184d(3, this.f11514d);
                }
                return c;
            }
        }

        public C4291a() {
            m14368d();
        }

        public C4291a m14368d() {
            this.f11515b = null;
            this.f11516c = C4288d.m14355d();
            this.f11517d = C4278a.m14320d();
            this.f11518e = C4280c.m14327d();
            this.f11519f = C4469f.f12366a;
            this.f11520g = C4289e.m14359d();
            this.a = -1;
            return this;
        }

        public void mo6966a(C4392b c4392b) throws IOException {
            int i = 0;
            if (this.f11515b != null) {
                c4392b.m15199a(1, this.f11515b);
            }
            if (this.f11516c != null && this.f11516c.length > 0) {
                for (C4277d c4277d : this.f11516c) {
                    if (c4277d != null) {
                        c4392b.m15199a(3, c4277d);
                    }
                }
            }
            if (this.f11517d != null && this.f11517d.length > 0) {
                for (C4277d c4277d2 : this.f11517d) {
                    if (c4277d2 != null) {
                        c4392b.m15199a(7, c4277d2);
                    }
                }
            }
            if (this.f11518e != null && this.f11518e.length > 0) {
                for (C4277d c4277d22 : this.f11518e) {
                    if (c4277d22 != null) {
                        c4392b.m15199a(8, c4277d22);
                    }
                }
            }
            if (this.f11519f != null && this.f11519f.length > 0) {
                for (String str : this.f11519f) {
                    if (str != null) {
                        c4392b.m15200a(9, str);
                    }
                }
            }
            if (this.f11520g != null && this.f11520g.length > 0) {
                while (i < this.f11520g.length) {
                    C4277d c4277d3 = this.f11520g[i];
                    if (c4277d3 != null) {
                        c4392b.m15199a(10, c4277d3);
                    }
                    i++;
                }
            }
            super.mo6966a(c4392b);
        }

        protected int mo6967c() {
            int i;
            int i2 = 0;
            int c = super.mo6967c();
            if (this.f11515b != null) {
                c += C4392b.m15176b(1, this.f11515b);
            }
            if (this.f11516c != null && this.f11516c.length > 0) {
                i = c;
                for (C4277d c4277d : this.f11516c) {
                    if (c4277d != null) {
                        i += C4392b.m15176b(3, c4277d);
                    }
                }
                c = i;
            }
            if (this.f11517d != null && this.f11517d.length > 0) {
                i = c;
                for (C4277d c4277d2 : this.f11517d) {
                    if (c4277d2 != null) {
                        i += C4392b.m15176b(7, c4277d2);
                    }
                }
                c = i;
            }
            if (this.f11518e != null && this.f11518e.length > 0) {
                i = c;
                for (C4277d c4277d22 : this.f11518e) {
                    if (c4277d22 != null) {
                        i += C4392b.m15176b(8, c4277d22);
                    }
                }
                c = i;
            }
            if (this.f11519f != null && this.f11519f.length > 0) {
                int i3 = 0;
                int i4 = 0;
                for (String str : this.f11519f) {
                    if (str != null) {
                        i4++;
                        i3 += C4392b.m15180b(str);
                    }
                }
                c = (c + i3) + (i4 * 1);
            }
            if (this.f11520g != null && this.f11520g.length > 0) {
                while (i2 < this.f11520g.length) {
                    C4277d c4277d3 = this.f11520g[i2];
                    if (c4277d3 != null) {
                        c += C4392b.m15176b(10, c4277d3);
                    }
                    i2++;
                }
            }
            return c;
        }
    }
}
