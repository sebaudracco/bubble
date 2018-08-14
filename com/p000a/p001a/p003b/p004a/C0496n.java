package com.p000a.p001a.p003b.p004a;

import com.a.a.b.a.n.AnonymousClass19;
import com.a.a.b.a.n.AnonymousClass28;
import com.appnext.base.p023b.C1042c;
import com.github.lzyzsd.jsbridge.BridgeUtil;
import com.p000a.p001a.C0449u;
import com.p000a.p001a.C0452t;
import com.p000a.p001a.C0552e;
import com.p000a.p001a.C0554j;
import com.p000a.p001a.C0555g;
import com.p000a.p001a.C0558k;
import com.p000a.p001a.C0559l;
import com.p000a.p001a.C0560m;
import com.p000a.p001a.C0561o;
import com.p000a.p001a.C0563r;
import com.p000a.p001a.p002a.C0445c;
import com.p000a.p001a.p003b.C0516f;
import com.p000a.p001a.p003b.p004a.C0496n.C0495a;
import com.p000a.p001a.p006d.C0460a;
import com.p000a.p001a.p006d.C0463c;
import com.p000a.p001a.p006d.C0544b;
import com.p000a.p001a.p007c.C0542a;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.URI;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import org.slf4j.Marker;

public final class C0496n {
    public static final C0452t<String> f122A = new C04938();
    public static final C0452t<BigDecimal> f123B = new C04949();
    public static final C0452t<BigInteger> f124C = new C0452t<BigInteger>() {
        public BigInteger m176a(C0460a c0460a) {
            if (c0460a.mo1801f() == C0544b.NULL) {
                c0460a.mo1805j();
                return null;
            }
            try {
                return new BigInteger(c0460a.mo1803h());
            } catch (Throwable e) {
                throw new C0563r(e);
            }
        }

        public void m178a(C0463c c0463c, BigInteger bigInteger) {
            c0463c.mo1815a((Number) bigInteger);
        }

        public /* synthetic */ Object mo1794b(C0460a c0460a) {
            return m176a(c0460a);
        }
    };
    public static final C0449u f125D = C0496n.m304a(String.class, f122A);
    public static final C0452t<StringBuilder> f126E = new C0452t<StringBuilder>() {
        public StringBuilder m180a(C0460a c0460a) {
            if (c0460a.mo1801f() != C0544b.NULL) {
                return new StringBuilder(c0460a.mo1803h());
            }
            c0460a.mo1805j();
            return null;
        }

        public void m182a(C0463c c0463c, StringBuilder stringBuilder) {
            c0463c.mo1820b(stringBuilder == null ? null : stringBuilder.toString());
        }

        public /* synthetic */ Object mo1794b(C0460a c0460a) {
            return m180a(c0460a);
        }
    };
    public static final C0449u f127F = C0496n.m304a(StringBuilder.class, f126E);
    public static final C0452t<StringBuffer> f128G = new C0452t<StringBuffer>() {
        public StringBuffer m188a(C0460a c0460a) {
            if (c0460a.mo1801f() != C0544b.NULL) {
                return new StringBuffer(c0460a.mo1803h());
            }
            c0460a.mo1805j();
            return null;
        }

        public void m190a(C0463c c0463c, StringBuffer stringBuffer) {
            c0463c.mo1820b(stringBuffer == null ? null : stringBuffer.toString());
        }

        public /* synthetic */ Object mo1794b(C0460a c0460a) {
            return m188a(c0460a);
        }
    };
    public static final C0449u f129H = C0496n.m304a(StringBuffer.class, f128G);
    public static final C0452t<URL> f130I = new C0452t<URL>() {
        public URL m192a(C0460a c0460a) {
            if (c0460a.mo1801f() == C0544b.NULL) {
                c0460a.mo1805j();
                return null;
            }
            String h = c0460a.mo1803h();
            return !"null".equals(h) ? new URL(h) : null;
        }

        public void m194a(C0463c c0463c, URL url) {
            c0463c.mo1820b(url == null ? null : url.toExternalForm());
        }

        public /* synthetic */ Object mo1794b(C0460a c0460a) {
            return m192a(c0460a);
        }
    };
    public static final C0449u f131J = C0496n.m304a(URL.class, f130I);
    public static final C0452t<URI> f132K = new C0452t<URI>() {
        public URI m196a(C0460a c0460a) {
            URI uri = null;
            if (c0460a.mo1801f() == C0544b.NULL) {
                c0460a.mo1805j();
            } else {
                try {
                    String h = c0460a.mo1803h();
                    if (!"null".equals(h)) {
                        uri = new URI(h);
                    }
                } catch (Throwable e) {
                    throw new C0558k(e);
                }
            }
            return uri;
        }

        public void m198a(C0463c c0463c, URI uri) {
            c0463c.mo1820b(uri == null ? null : uri.toASCIIString());
        }

        public /* synthetic */ Object mo1794b(C0460a c0460a) {
            return m196a(c0460a);
        }
    };
    public static final C0449u f133L = C0496n.m304a(URI.class, f132K);
    public static final C0452t<InetAddress> f134M = new C0452t<InetAddress>() {
        public InetAddress m200a(C0460a c0460a) {
            if (c0460a.mo1801f() != C0544b.NULL) {
                return InetAddress.getByName(c0460a.mo1803h());
            }
            c0460a.mo1805j();
            return null;
        }

        public void m202a(C0463c c0463c, InetAddress inetAddress) {
            c0463c.mo1820b(inetAddress == null ? null : inetAddress.getHostAddress());
        }

        public /* synthetic */ Object mo1794b(C0460a c0460a) {
            return m200a(c0460a);
        }
    };
    public static final C0449u f135N = C0496n.m306b(InetAddress.class, f134M);
    public static final C0452t<UUID> f136O = new C0452t<UUID>() {
        public UUID m204a(C0460a c0460a) {
            if (c0460a.mo1801f() != C0544b.NULL) {
                return UUID.fromString(c0460a.mo1803h());
            }
            c0460a.mo1805j();
            return null;
        }

        public void m206a(C0463c c0463c, UUID uuid) {
            c0463c.mo1820b(uuid == null ? null : uuid.toString());
        }

        public /* synthetic */ Object mo1794b(C0460a c0460a) {
            return m204a(c0460a);
        }
    };
    public static final C0449u f137P = C0496n.m304a(UUID.class, f136O);
    public static final C0452t<Currency> f138Q = new C0452t<Currency>() {
        public Currency m208a(C0460a c0460a) {
            return Currency.getInstance(c0460a.mo1803h());
        }

        public void m210a(C0463c c0463c, Currency currency) {
            c0463c.mo1820b(currency.getCurrencyCode());
        }

        public /* synthetic */ Object mo1794b(C0460a c0460a) {
            return m208a(c0460a);
        }
    }.m20a();
    public static final C0449u f139R = C0496n.m304a(Currency.class, f138Q);
    public static final C0449u f140S = new C0449u() {
        public <T> C0452t<T> mo1792a(C0552e c0552e, C0542a<T> c0542a) {
            if (c0542a.m403a() != Timestamp.class) {
                return null;
            }
            final C0452t a = c0552e.m440a(Date.class);
            return new C0452t<Timestamp>(this) {
                final /* synthetic */ AnonymousClass19 f106b;

                public Timestamp m212a(C0460a c0460a) {
                    Date date = (Date) a.mo1794b(c0460a);
                    return date != null ? new Timestamp(date.getTime()) : null;
                }

                public void m214a(C0463c c0463c, Timestamp timestamp) {
                    a.mo1793a(c0463c, timestamp);
                }

                public /* synthetic */ Object mo1794b(C0460a c0460a) {
                    return m212a(c0460a);
                }
            };
        }
    };
    public static final C0452t<Calendar> f141T = new C0452t<Calendar>() {
        public Calendar m221a(C0460a c0460a) {
            int i = 0;
            if (c0460a.mo1801f() == C0544b.NULL) {
                c0460a.mo1805j();
                return null;
            }
            c0460a.mo1797c();
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            while (c0460a.mo1801f() != C0544b.END_OBJECT) {
                String g = c0460a.mo1802g();
                int m = c0460a.mo1808m();
                if ("year".equals(g)) {
                    i6 = m;
                } else if (C1042c.jA.equals(g)) {
                    i5 = m;
                } else if ("dayOfMonth".equals(g)) {
                    i4 = m;
                } else if ("hourOfDay".equals(g)) {
                    i3 = m;
                } else if (C1042c.jx.equals(g)) {
                    i2 = m;
                } else if (C1042c.jw.equals(g)) {
                    i = m;
                }
            }
            c0460a.mo1799d();
            return new GregorianCalendar(i6, i5, i4, i3, i2, i);
        }

        public void m223a(C0463c c0463c, Calendar calendar) {
            if (calendar == null) {
                c0463c.mo1825f();
                return;
            }
            c0463c.mo1823d();
            c0463c.mo1816a("year");
            c0463c.mo1813a((long) calendar.get(1));
            c0463c.mo1816a(C1042c.jA);
            c0463c.mo1813a((long) calendar.get(2));
            c0463c.mo1816a("dayOfMonth");
            c0463c.mo1813a((long) calendar.get(5));
            c0463c.mo1816a("hourOfDay");
            c0463c.mo1813a((long) calendar.get(11));
            c0463c.mo1816a(C1042c.jx);
            c0463c.mo1813a((long) calendar.get(12));
            c0463c.mo1816a(C1042c.jw);
            c0463c.mo1813a((long) calendar.get(13));
            c0463c.mo1824e();
        }

        public /* synthetic */ Object mo1794b(C0460a c0460a) {
            return m221a(c0460a);
        }
    };
    public static final C0449u f142U = C0496n.m307b(Calendar.class, GregorianCalendar.class, f141T);
    public static final C0452t<Locale> f143V = new C0452t<Locale>() {
        public Locale m225a(C0460a c0460a) {
            if (c0460a.mo1801f() == C0544b.NULL) {
                c0460a.mo1805j();
                return null;
            }
            StringTokenizer stringTokenizer = new StringTokenizer(c0460a.mo1803h(), BridgeUtil.UNDERLINE_STR);
            String nextToken = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
            String nextToken2 = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
            String nextToken3 = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
            return (nextToken2 == null && nextToken3 == null) ? new Locale(nextToken) : nextToken3 == null ? new Locale(nextToken, nextToken2) : new Locale(nextToken, nextToken2, nextToken3);
        }

        public void m227a(C0463c c0463c, Locale locale) {
            c0463c.mo1820b(locale == null ? null : locale.toString());
        }

        public /* synthetic */ Object mo1794b(C0460a c0460a) {
            return m225a(c0460a);
        }
    };
    public static final C0449u f144W = C0496n.m304a(Locale.class, f143V);
    public static final C0452t<C0554j> f145X = new C0452t<C0554j>() {
        public C0554j m229a(C0460a c0460a) {
            C0554j c0555g;
            switch (c0460a.mo1801f()) {
                case NUMBER:
                    return new C0561o(new C0516f(c0460a.mo1803h()));
                case BOOLEAN:
                    return new C0561o(Boolean.valueOf(c0460a.mo1804i()));
                case STRING:
                    return new C0561o(c0460a.mo1803h());
                case NULL:
                    c0460a.mo1805j();
                    return C0559l.f295a;
                case BEGIN_ARRAY:
                    c0555g = new C0555g();
                    c0460a.mo1795a();
                    while (c0460a.mo1800e()) {
                        c0555g.m468a(m229a(c0460a));
                    }
                    c0460a.mo1796b();
                    return c0555g;
                case BEGIN_OBJECT:
                    c0555g = new C0560m();
                    c0460a.mo1797c();
                    while (c0460a.mo1800e()) {
                        c0555g.m475a(c0460a.mo1802g(), m229a(c0460a));
                    }
                    c0460a.mo1799d();
                    return c0555g;
                default:
                    throw new IllegalArgumentException();
            }
        }

        public void m230a(C0463c c0463c, C0554j c0554j) {
            if (c0554j == null || c0554j.m462j()) {
                c0463c.mo1825f();
            } else if (c0554j.m461i()) {
                C0561o m = c0554j.m465m();
                if (m.m488p()) {
                    c0463c.mo1815a(m.mo1834a());
                } else if (m.m487o()) {
                    c0463c.mo1817a(m.mo1839f());
                } else {
                    c0463c.mo1820b(m.mo1835b());
                }
            } else if (c0554j.m459g()) {
                c0463c.mo1819b();
                Iterator it = c0554j.m464l().iterator();
                while (it.hasNext()) {
                    m230a(c0463c, (C0554j) it.next());
                }
                c0463c.mo1821c();
            } else if (c0554j.m460h()) {
                c0463c.mo1823d();
                for (Entry entry : c0554j.m463k().m476o()) {
                    c0463c.mo1816a((String) entry.getKey());
                    m230a(c0463c, (C0554j) entry.getValue());
                }
                c0463c.mo1824e();
            } else {
                throw new IllegalArgumentException("Couldn't write " + c0554j.getClass());
            }
        }

        public /* synthetic */ Object mo1794b(C0460a c0460a) {
            return m229a(c0460a);
        }
    };
    public static final C0449u f146Y = C0496n.m306b(C0554j.class, f145X);
    public static final C0449u f147Z = new C0449u() {
        public <T> C0452t<T> mo1792a(C0552e c0552e, C0542a<T> c0542a) {
            Class a = c0542a.m403a();
            if (!Enum.class.isAssignableFrom(a) || a == Enum.class) {
                return null;
            }
            if (!a.isEnum()) {
                a = a.getSuperclass();
            }
            return new C0495a(a);
        }
    };
    public static final C0452t<Class> f148a = new C04851().m20a();
    public static final C0449u f149b = C0496n.m304a(Class.class, f148a);
    public static final C0452t<BitSet> f150c = new C0452t<BitSet>() {
        public BitSet m184a(C0460a c0460a) {
            BitSet bitSet = new BitSet();
            c0460a.mo1795a();
            C0544b f = c0460a.mo1801f();
            int i = 0;
            while (f != C0544b.END_ARRAY) {
                boolean z;
                switch (f) {
                    case NUMBER:
                        if (c0460a.mo1808m() == 0) {
                            z = false;
                            break;
                        }
                        z = true;
                        break;
                    case BOOLEAN:
                        z = c0460a.mo1804i();
                        break;
                    case STRING:
                        String h = c0460a.mo1803h();
                        try {
                            if (Integer.parseInt(h) == 0) {
                                z = false;
                                break;
                            }
                            z = true;
                            break;
                        } catch (NumberFormatException e) {
                            throw new C0563r("Error: Expecting: bitset number value (1, 0), Found: " + h);
                        }
                    default:
                        throw new C0563r("Invalid bitset value type: " + f);
                }
                if (z) {
                    bitSet.set(i);
                }
                i++;
                f = c0460a.mo1801f();
            }
            c0460a.mo1796b();
            return bitSet;
        }

        public void m186a(C0463c c0463c, BitSet bitSet) {
            c0463c.mo1819b();
            int length = bitSet.length();
            for (int i = 0; i < length; i++) {
                c0463c.mo1813a((long) (bitSet.get(i) ? 1 : 0));
            }
            c0463c.mo1821c();
        }

        public /* synthetic */ Object mo1794b(C0460a c0460a) {
            return m184a(c0460a);
        }
    }.m20a();
    public static final C0449u f151d = C0496n.m304a(BitSet.class, f150c);
    public static final C0452t<Boolean> f152e = new C0452t<Boolean>() {
        public Boolean m233a(C0460a c0460a) {
            if (c0460a.mo1801f() != C0544b.NULL) {
                return c0460a.mo1801f() == C0544b.STRING ? Boolean.valueOf(Boolean.parseBoolean(c0460a.mo1803h())) : Boolean.valueOf(c0460a.mo1804i());
            } else {
                c0460a.mo1805j();
                return null;
            }
        }

        public void m234a(C0463c c0463c, Boolean bool) {
            c0463c.mo1814a(bool);
        }

        public /* synthetic */ Object mo1794b(C0460a c0460a) {
            return m233a(c0460a);
        }
    };
    public static final C0452t<Boolean> f153f = new C0452t<Boolean>() {
        public Boolean m248a(C0460a c0460a) {
            if (c0460a.mo1801f() != C0544b.NULL) {
                return Boolean.valueOf(c0460a.mo1803h());
            }
            c0460a.mo1805j();
            return null;
        }

        public void m249a(C0463c c0463c, Boolean bool) {
            c0463c.mo1820b(bool == null ? "null" : bool.toString());
        }

        public /* synthetic */ Object mo1794b(C0460a c0460a) {
            return m248a(c0460a);
        }
    };
    public static final C0449u f154g = C0496n.m305a(Boolean.TYPE, Boolean.class, f152e);
    public static final C0452t<Number> f155h = new C0452t<Number>() {
        public Number m252a(C0460a c0460a) {
            if (c0460a.mo1801f() == C0544b.NULL) {
                c0460a.mo1805j();
                return null;
            }
            try {
                return Byte.valueOf((byte) c0460a.mo1808m());
            } catch (Throwable e) {
                throw new C0563r(e);
            }
        }

        public void m253a(C0463c c0463c, Number number) {
            c0463c.mo1815a(number);
        }

        public /* synthetic */ Object mo1794b(C0460a c0460a) {
            return m252a(c0460a);
        }
    };
    public static final C0449u f156i = C0496n.m305a(Byte.TYPE, Byte.class, f155h);
    public static final C0452t<Number> f157j = new C0452t<Number>() {
        public Number m256a(C0460a c0460a) {
            if (c0460a.mo1801f() == C0544b.NULL) {
                c0460a.mo1805j();
                return null;
            }
            try {
                return Short.valueOf((short) c0460a.mo1808m());
            } catch (Throwable e) {
                throw new C0563r(e);
            }
        }

        public void m257a(C0463c c0463c, Number number) {
            c0463c.mo1815a(number);
        }

        public /* synthetic */ Object mo1794b(C0460a c0460a) {
            return m256a(c0460a);
        }
    };
    public static final C0449u f158k = C0496n.m305a(Short.TYPE, Short.class, f157j);
    public static final C0452t<Number> f159l = new C0452t<Number>() {
        public Number m260a(C0460a c0460a) {
            if (c0460a.mo1801f() == C0544b.NULL) {
                c0460a.mo1805j();
                return null;
            }
            try {
                return Integer.valueOf(c0460a.mo1808m());
            } catch (Throwable e) {
                throw new C0563r(e);
            }
        }

        public void m261a(C0463c c0463c, Number number) {
            c0463c.mo1815a(number);
        }

        public /* synthetic */ Object mo1794b(C0460a c0460a) {
            return m260a(c0460a);
        }
    };
    public static final C0449u f160m = C0496n.m305a(Integer.TYPE, Integer.class, f159l);
    public static final C0452t<AtomicInteger> f161n = new C0452t<AtomicInteger>() {
        public AtomicInteger m264a(C0460a c0460a) {
            try {
                return new AtomicInteger(c0460a.mo1808m());
            } catch (Throwable e) {
                throw new C0563r(e);
            }
        }

        public void m266a(C0463c c0463c, AtomicInteger atomicInteger) {
            c0463c.mo1813a((long) atomicInteger.get());
        }

        public /* synthetic */ Object mo1794b(C0460a c0460a) {
            return m264a(c0460a);
        }
    }.m20a();
    public static final C0449u f162o = C0496n.m304a(AtomicInteger.class, f161n);
    public static final C0452t<AtomicBoolean> f163p = new C0452t<AtomicBoolean>() {
        public AtomicBoolean m268a(C0460a c0460a) {
            return new AtomicBoolean(c0460a.mo1804i());
        }

        public void m270a(C0463c c0463c, AtomicBoolean atomicBoolean) {
            c0463c.mo1817a(atomicBoolean.get());
        }

        public /* synthetic */ Object mo1794b(C0460a c0460a) {
            return m268a(c0460a);
        }
    }.m20a();
    public static final C0449u f164q = C0496n.m304a(AtomicBoolean.class, f163p);
    public static final C0452t<AtomicIntegerArray> f165r = new C04872().m20a();
    public static final C0449u f166s = C0496n.m304a(AtomicIntegerArray.class, f165r);
    public static final C0452t<Number> f167t = new C04883();
    public static final C0452t<Number> f168u = new C04894();
    public static final C0452t<Number> f169v = new C04905();
    public static final C0452t<Number> f170w = new C04916();
    public static final C0449u f171x = C0496n.m304a(Number.class, f170w);
    public static final C0452t<Character> f172y = new C04927();
    public static final C0449u f173z = C0496n.m305a(Character.TYPE, Character.class, f172y);

    static class C04851 extends C0452t<Class> {
        C04851() {
        }

        public Class m217a(C0460a c0460a) {
            throw new UnsupportedOperationException("Attempted to deserialize a java.lang.Class. Forgot to register a type adapter?");
        }

        public void m218a(C0463c c0463c, Class cls) {
            throw new UnsupportedOperationException("Attempted to serialize java.lang.Class: " + cls.getName() + ". Forgot to register a type adapter?");
        }

        public /* synthetic */ Object mo1794b(C0460a c0460a) {
            return m217a(c0460a);
        }
    }

    static class C04872 extends C0452t<AtomicIntegerArray> {
        C04872() {
        }

        public AtomicIntegerArray m244a(C0460a c0460a) {
            List arrayList = new ArrayList();
            c0460a.mo1795a();
            while (c0460a.mo1800e()) {
                try {
                    arrayList.add(Integer.valueOf(c0460a.mo1808m()));
                } catch (Throwable e) {
                    throw new C0563r(e);
                }
            }
            c0460a.mo1796b();
            int size = arrayList.size();
            AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(size);
            for (int i = 0; i < size; i++) {
                atomicIntegerArray.set(i, ((Integer) arrayList.get(i)).intValue());
            }
            return atomicIntegerArray;
        }

        public void m246a(C0463c c0463c, AtomicIntegerArray atomicIntegerArray) {
            c0463c.mo1819b();
            int length = atomicIntegerArray.length();
            for (int i = 0; i < length; i++) {
                c0463c.mo1813a((long) atomicIntegerArray.get(i));
            }
            c0463c.mo1821c();
        }

        public /* synthetic */ Object mo1794b(C0460a c0460a) {
            return m244a(c0460a);
        }
    }

    static class C04883 extends C0452t<Number> {
        C04883() {
        }

        public Number m272a(C0460a c0460a) {
            if (c0460a.mo1801f() == C0544b.NULL) {
                c0460a.mo1805j();
                return null;
            }
            try {
                return Long.valueOf(c0460a.mo1807l());
            } catch (Throwable e) {
                throw new C0563r(e);
            }
        }

        public void m273a(C0463c c0463c, Number number) {
            c0463c.mo1815a(number);
        }

        public /* synthetic */ Object mo1794b(C0460a c0460a) {
            return m272a(c0460a);
        }
    }

    static class C04894 extends C0452t<Number> {
        C04894() {
        }

        public Number m276a(C0460a c0460a) {
            if (c0460a.mo1801f() != C0544b.NULL) {
                return Float.valueOf((float) c0460a.mo1806k());
            }
            c0460a.mo1805j();
            return null;
        }

        public void m277a(C0463c c0463c, Number number) {
            c0463c.mo1815a(number);
        }

        public /* synthetic */ Object mo1794b(C0460a c0460a) {
            return m276a(c0460a);
        }
    }

    static class C04905 extends C0452t<Number> {
        C04905() {
        }

        public Number m280a(C0460a c0460a) {
            if (c0460a.mo1801f() != C0544b.NULL) {
                return Double.valueOf(c0460a.mo1806k());
            }
            c0460a.mo1805j();
            return null;
        }

        public void m281a(C0463c c0463c, Number number) {
            c0463c.mo1815a(number);
        }

        public /* synthetic */ Object mo1794b(C0460a c0460a) {
            return m280a(c0460a);
        }
    }

    static class C04916 extends C0452t<Number> {
        C04916() {
        }

        public Number m284a(C0460a c0460a) {
            C0544b f = c0460a.mo1801f();
            switch (f) {
                case NUMBER:
                case STRING:
                    return new C0516f(c0460a.mo1803h());
                case NULL:
                    c0460a.mo1805j();
                    return null;
                default:
                    throw new C0563r("Expecting number, got: " + f);
            }
        }

        public void m285a(C0463c c0463c, Number number) {
            c0463c.mo1815a(number);
        }

        public /* synthetic */ Object mo1794b(C0460a c0460a) {
            return m284a(c0460a);
        }
    }

    static class C04927 extends C0452t<Character> {
        C04927() {
        }

        public Character m288a(C0460a c0460a) {
            if (c0460a.mo1801f() == C0544b.NULL) {
                c0460a.mo1805j();
                return null;
            }
            String h = c0460a.mo1803h();
            if (h.length() == 1) {
                return Character.valueOf(h.charAt(0));
            }
            throw new C0563r("Expecting character, got: " + h);
        }

        public void m289a(C0463c c0463c, Character ch) {
            c0463c.mo1820b(ch == null ? null : String.valueOf(ch));
        }

        public /* synthetic */ Object mo1794b(C0460a c0460a) {
            return m288a(c0460a);
        }
    }

    static class C04938 extends C0452t<String> {
        C04938() {
        }

        public String m292a(C0460a c0460a) {
            C0544b f = c0460a.mo1801f();
            if (f != C0544b.NULL) {
                return f == C0544b.BOOLEAN ? Boolean.toString(c0460a.mo1804i()) : c0460a.mo1803h();
            } else {
                c0460a.mo1805j();
                return null;
            }
        }

        public void m294a(C0463c c0463c, String str) {
            c0463c.mo1820b(str);
        }

        public /* synthetic */ Object mo1794b(C0460a c0460a) {
            return m292a(c0460a);
        }
    }

    static class C04949 extends C0452t<BigDecimal> {
        C04949() {
        }

        public BigDecimal m296a(C0460a c0460a) {
            if (c0460a.mo1801f() == C0544b.NULL) {
                c0460a.mo1805j();
                return null;
            }
            try {
                return new BigDecimal(c0460a.mo1803h());
            } catch (Throwable e) {
                throw new C0563r(e);
            }
        }

        public void m298a(C0463c c0463c, BigDecimal bigDecimal) {
            c0463c.mo1815a((Number) bigDecimal);
        }

        public /* synthetic */ Object mo1794b(C0460a c0460a) {
            return m296a(c0460a);
        }
    }

    private static final class C0495a<T extends Enum<T>> extends C0452t<T> {
        private final Map<String, T> f120a = new HashMap();
        private final Map<T, String> f121b = new HashMap();

        public C0495a(Class<T> cls) {
            try {
                for (Enum enumR : (Enum[]) cls.getEnumConstants()) {
                    String name = enumR.name();
                    C0445c c0445c = (C0445c) cls.getField(name).getAnnotation(C0445c.class);
                    if (c0445c != null) {
                        name = c0445c.m7a();
                        for (Object put : c0445c.m8b()) {
                            this.f120a.put(put, enumR);
                        }
                    }
                    String str = name;
                    this.f120a.put(str, enumR);
                    this.f121b.put(enumR, str);
                }
            } catch (NoSuchFieldException e) {
                throw new AssertionError(e);
            }
        }

        public T m300a(C0460a c0460a) {
            if (c0460a.mo1801f() != C0544b.NULL) {
                return (Enum) this.f120a.get(c0460a.mo1803h());
            }
            c0460a.mo1805j();
            return null;
        }

        public void m301a(C0463c c0463c, T t) {
            c0463c.mo1820b(t == null ? null : (String) this.f121b.get(t));
        }

        public /* synthetic */ Object mo1794b(C0460a c0460a) {
            return m300a(c0460a);
        }
    }

    public static <TT> C0449u m304a(final Class<TT> cls, final C0452t<TT> c0452t) {
        return new C0449u() {
            public <T> C0452t<T> mo1792a(C0552e c0552e, C0542a<T> c0542a) {
                return c0542a.m403a() == cls ? c0452t : null;
            }

            public String toString() {
                return "Factory[type=" + cls.getName() + ",adapter=" + c0452t + "]";
            }
        };
    }

    public static <TT> C0449u m305a(final Class<TT> cls, final Class<TT> cls2, final C0452t<? super TT> c0452t) {
        return new C0449u() {
            public <T> C0452t<T> mo1792a(C0552e c0552e, C0542a<T> c0542a) {
                Class a = c0542a.m403a();
                return (a == cls || a == cls2) ? c0452t : null;
            }

            public String toString() {
                return "Factory[type=" + cls2.getName() + Marker.ANY_NON_NULL_MARKER + cls.getName() + ",adapter=" + c0452t + "]";
            }
        };
    }

    public static <T1> C0449u m306b(final Class<T1> cls, final C0452t<T1> c0452t) {
        return new C0449u() {
            public <T2> C0452t<T2> mo1792a(C0552e c0552e, C0542a<T2> c0542a) {
                final Class a = c0542a.m403a();
                return !cls.isAssignableFrom(a) ? null : new C0452t<T1>(this) {
                    final /* synthetic */ AnonymousClass28 f116b;

                    public void mo1793a(C0463c c0463c, T1 t1) {
                        c0452t.mo1793a(c0463c, t1);
                    }

                    public T1 mo1794b(C0460a c0460a) {
                        T1 b = c0452t.mo1794b(c0460a);
                        if (b == null || a.isInstance(b)) {
                            return b;
                        }
                        throw new C0563r("Expected a " + a.getName() + " but was " + b.getClass().getName());
                    }
                };
            }

            public String toString() {
                return "Factory[typeHierarchy=" + cls.getName() + ",adapter=" + c0452t + "]";
            }
        };
    }

    public static <TT> C0449u m307b(final Class<TT> cls, final Class<? extends TT> cls2, final C0452t<? super TT> c0452t) {
        return new C0449u() {
            public <T> C0452t<T> mo1792a(C0552e c0552e, C0542a<T> c0542a) {
                Class a = c0542a.m403a();
                return (a == cls || a == cls2) ? c0452t : null;
            }

            public String toString() {
                return "Factory[type=" + cls.getName() + Marker.ANY_NON_NULL_MARKER + cls2.getName() + ",adapter=" + c0452t + "]";
            }
        };
    }
}
