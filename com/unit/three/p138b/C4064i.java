package com.unit.three.p138b;

import android.support.v4.internal.view.SupportMenu;
import java.net.InetAddress;
import java.nio.ByteBuffer;

public final class C4064i {
    public C4061a f9407a;
    public C4062b f9408b;
    public ByteBuffer f9409c;
    private C4063c f9410d;
    private boolean f9411e;
    private boolean f9412f;

    public final class C4061a {
        public byte f9380a;
        public byte f9381b;
        public int f9382c;
        public short f9383d;
        public int f9384e;
        public int f9385f;
        public short f9386g;
        public C4060a f9387h;
        public int f9388i;
        public InetAddress f9389j;
        public InetAddress f9390k;
        private short f9391l;

        enum C4060a {
            TCP(6),
            UDP(17),
            Other(255);
            
            private int f9379d;

            static {
                TCP = new C4060a("TCP", 0, 6);
                UDP = new C4060a("UDP", 1, 17);
                Other = new C4060a("Other", 2, 255);
                C4060a[] c4060aArr = new C4060a[]{TCP, UDP, Other};
            }

            private C4060a(int i) {
                this.f9379d = i;
            }

            public final int m12528a() {
                return this.f9379d;
            }
        }

        private C4061a(ByteBuffer byteBuffer) {
            byte b = byteBuffer.get();
            this.f9380a = (byte) (b >> 4);
            this.f9381b = (byte) (b & 15);
            this.f9382c = this.f9381b << 2;
            this.f9383d = ((short) (byteBuffer.get() & 255));
            this.f9384e = (SupportMenu.USER_MASK & byteBuffer.getShort());
            this.f9385f = byteBuffer.getInt();
            this.f9386g = ((short) (byteBuffer.get() & 255));
            this.f9391l = ((short) (byteBuffer.get() & 255));
            this.f9387h = C4060a.m12527a(this.f9391l);
            this.f9388i = (SupportMenu.USER_MASK & byteBuffer.getShort());
            byte[] bArr = new byte[4];
            byteBuffer.get(bArr, 0, 4);
            this.f9389j = InetAddress.getByAddress(bArr);
            byteBuffer.get(bArr, 0, 4);
            this.f9390k = InetAddress.getByAddress(bArr);
        }

        public final String toString() {
            StringBuilder stringBuilder = new StringBuilder("IP4Header{");
            stringBuilder.append("version=").append(this.f9380a);
            stringBuilder.append(", IHL=").append(this.f9381b);
            stringBuilder.append(", typeOfService=").append(this.f9383d);
            stringBuilder.append(", totalLength=").append(this.f9384e);
            stringBuilder.append(", identificationAndFlagsAndFragmentOffset=").append(this.f9385f);
            stringBuilder.append(", TTL=").append(this.f9386g);
            stringBuilder.append(", protocol=").append(this.f9391l).append(":").append(this.f9387h);
            stringBuilder.append(", headerChecksum=").append(this.f9388i);
            stringBuilder.append(", sourceAddress=").append(this.f9389j.getHostAddress());
            stringBuilder.append(", destinationAddress=").append(this.f9390k.getHostAddress());
            stringBuilder.append('}');
            return stringBuilder.toString();
        }
    }

    public final class C4062b {
        public int f9392a;
        public int f9393b;
        private long f9394c;
        private long f9395d;
        private byte f9396e;
        private int f9397f;
        private byte f9398g;
        private int f9399h;
        private int f9400i;
        private int f9401j;
        private byte[] f9402k;

        private C4062b(ByteBuffer byteBuffer) {
            this.f9392a = (SupportMenu.USER_MASK & byteBuffer.getShort());
            this.f9393b = (SupportMenu.USER_MASK & byteBuffer.getShort());
            this.f9394c = (((long) byteBuffer.getInt()) & 4294967295L);
            this.f9395d = (((long) byteBuffer.getInt()) & 4294967295L);
            this.f9396e = byteBuffer.get();
            this.f9397f = (this.f9396e & 240) >> 2;
            this.f9398g = byteBuffer.get();
            this.f9399h = (SupportMenu.USER_MASK & byteBuffer.getShort());
            this.f9400i = (SupportMenu.USER_MASK & byteBuffer.getShort());
            this.f9401j = (SupportMenu.USER_MASK & byteBuffer.getShort());
            int i = this.f9397f - 20;
            if (i > 0) {
                this.f9402k = new byte[i];
                byteBuffer.get(this.f9402k, 0, i);
            }
        }

        static /* synthetic */ void m12529a(C4062b c4062b, ByteBuffer byteBuffer) {
            byteBuffer.putShort((short) c4062b.f9392a);
            byteBuffer.putShort((short) c4062b.f9393b);
            byteBuffer.putInt((int) c4062b.f9394c);
            byteBuffer.putInt((int) c4062b.f9395d);
            byteBuffer.put(c4062b.f9396e);
            byteBuffer.put(c4062b.f9398g);
            byteBuffer.putShort((short) c4062b.f9399h);
            byteBuffer.putShort((short) c4062b.f9400i);
            byteBuffer.putShort((short) c4062b.f9401j);
        }

        public final boolean m12530a() {
            return (this.f9398g & 1) == 1;
        }

        public final boolean m12531b() {
            return (this.f9398g & 4) == 4;
        }

        public final String toString() {
            Object obj = 1;
            StringBuilder stringBuilder = new StringBuilder("TCPHeader{");
            stringBuilder.append("sourcePort=").append(this.f9392a);
            stringBuilder.append(", destinationPort=").append(this.f9393b);
            stringBuilder.append(", sequenceNumber=").append(this.f9394c);
            stringBuilder.append(", acknowledgementNumber=").append(this.f9395d);
            stringBuilder.append(", headerLength=").append(this.f9397f);
            stringBuilder.append(", window=").append(this.f9399h);
            stringBuilder.append(", checksum=").append(this.f9400i);
            stringBuilder.append(", flags=");
            if (m12530a()) {
                stringBuilder.append(" FIN");
            }
            if (((this.f9398g & 2) == 2 ? 1 : null) != null) {
                stringBuilder.append(" SYN");
            }
            if (m12531b()) {
                stringBuilder.append(" RST");
            }
            if (((this.f9398g & 8) == 8 ? 1 : null) != null) {
                stringBuilder.append(" PSH");
            }
            if (((this.f9398g & 16) == 16 ? 1 : null) != null) {
                stringBuilder.append(" ACK");
            }
            if ((this.f9398g & 32) != 32) {
                obj = null;
            }
            if (obj != null) {
                stringBuilder.append(" URG");
            }
            stringBuilder.append('}');
            return stringBuilder.toString();
        }
    }

    public final class C4063c {
        private int f9403a;
        private int f9404b;
        private int f9405c;
        private int f9406d;

        private C4063c(ByteBuffer byteBuffer) {
            this.f9403a = (SupportMenu.USER_MASK & byteBuffer.getShort());
            this.f9404b = (SupportMenu.USER_MASK & byteBuffer.getShort());
            this.f9405c = (SupportMenu.USER_MASK & byteBuffer.getShort());
            this.f9406d = (SupportMenu.USER_MASK & byteBuffer.getShort());
        }

        static /* synthetic */ void m12532a(C4063c c4063c, ByteBuffer byteBuffer) {
            byteBuffer.putShort((short) c4063c.f9403a);
            byteBuffer.putShort((short) c4063c.f9404b);
            byteBuffer.putShort((short) c4063c.f9405c);
            byteBuffer.putShort((short) c4063c.f9406d);
        }

        public final String toString() {
            StringBuilder stringBuilder = new StringBuilder("UDPHeader{");
            stringBuilder.append("sourcePort=").append(this.f9403a);
            stringBuilder.append(", destinationPort=").append(this.f9404b);
            stringBuilder.append(", length=").append(this.f9405c);
            stringBuilder.append(", checksum=").append(this.f9406d);
            stringBuilder.append('}');
            return stringBuilder.toString();
        }
    }

    public C4064i(ByteBuffer byteBuffer) {
        this.f9407a = new C4061a(byteBuffer);
        if (this.f9407a.f9387h == C4060a.TCP) {
            this.f9408b = new C4062b(byteBuffer);
            this.f9411e = true;
        } else if (this.f9407a.f9387h == C4060a.UDP) {
            this.f9410d = new C4063c(byteBuffer);
            this.f9412f = true;
        }
        this.f9409c = byteBuffer;
    }

    public final void m12533a(ByteBuffer byteBuffer) {
        C4061a c4061a = this.f9407a;
        byteBuffer.put((byte) ((c4061a.f9380a << 4) | c4061a.f9381b));
        byteBuffer.put((byte) c4061a.f9383d);
        byteBuffer.putShort((short) c4061a.f9384e);
        byteBuffer.putInt(c4061a.f9385f);
        byteBuffer.put((byte) c4061a.f9386g);
        byteBuffer.put((byte) c4061a.f9387h.m12528a());
        byteBuffer.putShort((short) c4061a.f9388i);
        byteBuffer.put(c4061a.f9389j.getAddress());
        byteBuffer.put(c4061a.f9390k.getAddress());
        if (this.f9412f) {
            C4063c.m12532a(this.f9410d, byteBuffer);
        } else if (this.f9411e) {
            C4062b.m12529a(this.f9408b, byteBuffer);
        }
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder("Packet{");
        stringBuilder.append("ip4Header=").append(this.f9407a);
        if (this.f9411e) {
            stringBuilder.append(", tcpHeader=").append(this.f9408b);
        } else if (this.f9412f) {
            stringBuilder.append(", udpHeader=").append(this.f9410d);
        }
        stringBuilder.append(", payloadSize=").append(this.f9409c.limit() - this.f9409c.position());
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
