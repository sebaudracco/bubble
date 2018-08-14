package com.unit.three.p138b;

import com.unit.three.p139a.C4037a;
import com.unit.three.p139a.C4047d;
import com.unit.three.p140d.C4083a;
import com.unit.three.p141c.C4078f;
import com.unit.three.p143e.C4088b;
import com.unit.three.p143e.C4090d;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class C4067j {
    private static C4067j f9416g;
    private LinkedBlockingQueue f9417a;
    private LinkedBlockingQueue f9418b;
    private HashMap f9419c;
    private volatile boolean f9420d = true;
    private Socket f9421e;
    private C4065a f9422f;

    final class C4065a implements Runnable {
        private String f9413a;
        private int f9414b;
        private /* synthetic */ C4067j f9415c;

        public C4065a(C4067j c4067j, String str, int i) {
            this.f9415c = c4067j;
            this.f9413a = str;
            this.f9414b = i;
        }

        public final void run() {
            try {
                if (this.f9413a.equals(C4078f.m12576l())) {
                    ServerSocket serverSocket = new ServerSocket();
                    serverSocket.setReuseAddress(true);
                    serverSocket.bind(new InetSocketAddress(this.f9414b));
                    C4067j.m12540a(this.f9415c, serverSocket.accept());
                } else {
                    if (this.f9415c.f9421e != null) {
                        this.f9415c.f9421e.close();
                    }
                    this.f9415c.f9421e = new Socket();
                    this.f9415c.f9421e.connect(new InetSocketAddress(this.f9413a, this.f9414b));
                    C4067j.m12544c(this.f9415c, this.f9415c.f9421e);
                    C4067j.m12540a(this.f9415c, this.f9415c.f9421e);
                }
                this.f9415c.f9421e.close();
                C4071n.m12550a();
            } catch (Throwable e) {
                C4037a.m12449a();
                C4037a.m12453a(e);
                C4047d.m12474a().m12490b();
                C4071n.m12550a();
                if (C4083a.m12599a(C4053c.m12503a().m12515b()).m12602a(false)) {
                    C4037a.m12449a();
                    C4037a.m12456b();
                }
            }
        }
    }

    public interface C4066b {
        void mo6917a(C4064i c4064i);
    }

    private C4067j() {
    }

    private static C4064i m12535a(byte[] bArr) {
        try {
            return new C4064i(ByteBuffer.wrap(bArr));
        } catch (Exception e) {
            return null;
        }
    }

    public static C4067j m12536a() {
        if (f9416g == null) {
            synchronized (C4067j.class) {
                if (f9416g == null) {
                    f9416g = new C4067j();
                }
            }
        }
        return f9416g;
    }

    private void m12538a(long j) {
        C4088b.m12615a().m12616b().schedule(this.f9422f, j, TimeUnit.MILLISECONDS);
    }

    static /* synthetic */ void m12540a(C4067j c4067j, Socket socket) {
        try {
            socket.setSendBufferSize(102400);
            socket.setReceiveBufferSize(102400);
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            if (socket.isConnected()) {
                C4037a.m12449a();
                C4037a.m12457c();
                C4037a.m12449a();
                C4037a.m12454a(c4067j.f9418b);
                C4037a.m12449a();
                C4037a.m12455a(c4067j.f9418b, false);
            }
            Thread c4070m = new C4070m(c4067j, outputStream);
            c4070m.start();
            c4067j.f9420d = true;
            while (c4067j.f9420d) {
                int read;
                byte[] bArr = new byte[40];
                int i = 40;
                int i2 = 0;
                do {
                    read = inputStream.read(bArr, i2, i);
                    if (read == -1) {
                        c4067j.f9420d = false;
                        c4070m.interrupt();
                        throw new IOException("服务器写了-1过来，主动断开重连");
                    }
                    i2 += read;
                    i -= read;
                } while (i != 0);
                try {
                    ByteBuffer wrap = ByteBuffer.wrap(bArr);
                    byte b = wrap.get();
                    int i3;
                    if (b == (byte) 106) {
                        i3 = wrap.getInt();
                        boolean z = wrap.get() == (byte) 1;
                        C4037a.m12449a();
                        C4037a.m12450a(System.currentTimeMillis(), i3, z);
                    } else if (b == (byte) 109) {
                        C4078f.m12566a(C4053c.m12503a().m12515b(), "time_server_close", Long.valueOf(System.currentTimeMillis()));
                        C4053c.m12503a();
                        C4053c.m12511d();
                    } else {
                        wrap.position(0);
                        C4064i a = C4067j.m12535a(bArr);
                        if (a.f9407a.f9380a == (byte) 4 && a.f9407a.f9382c == 20) {
                            byte[] bArr2;
                            if (a.f9407a.f9384e > 40) {
                                bArr2 = new byte[a.f9407a.f9384e];
                                System.arraycopy(bArr, 0, bArr2, 0, 40);
                                i3 = a.f9407a.f9384e - 40;
                                i2 = 40;
                                do {
                                    read = inputStream.read(bArr2, i2, i3);
                                    if (read == -1) {
                                        c4067j.f9420d = false;
                                        c4070m.interrupt();
                                        if (socket != null) {
                                            try {
                                                c4067j.f9420d = false;
                                                c4070m.interrupt();
                                                socket.close();
                                                return;
                                            } catch (Exception e) {
                                                return;
                                            }
                                        }
                                        return;
                                    }
                                    i2 += read;
                                    i3 -= read;
                                } while (i3 != 0);
                            } else {
                                bArr2 = bArr;
                            }
                            c4067j.f9417a.offer(new C4064i(ByteBuffer.wrap(bArr2)));
                        }
                    }
                } catch (Throwable e2) {
                    throw new IOException("handle data exception", e2);
                } catch (Throwable th) {
                    if (socket != null) {
                        try {
                            c4067j.f9420d = false;
                            c4070m.interrupt();
                            socket.close();
                        } catch (Exception e3) {
                        }
                    }
                }
            }
            if (socket != null) {
                try {
                    c4067j.f9420d = false;
                    c4070m.interrupt();
                    socket.close();
                } catch (Exception e4) {
                }
            }
        } catch (Throwable e22) {
            throw new IOException("获取socket输入输出流异常", e22);
        }
    }

    static /* synthetic */ void m12544c(C4067j c4067j, Socket socket) {
        int i = 0;
        try {
            OutputStream outputStream = socket.getOutputStream();
            ByteBuffer allocate = ByteBuffer.allocate(40);
            allocate.put((byte) 102);
            allocate.put(C4078f.m12561a(0));
            allocate.put(C4078f.m12561a(0));
            allocate.position(0);
            allocate.limit(40);
            outputStream.write(allocate.array(), 0, 40);
            allocate = ByteBuffer.allocate(200);
            char[] toCharArray = C4090d.m12630b(C4053c.m12503a().m12515b()).toCharArray();
            allocate.put(C4078f.m12561a(toCharArray.length));
            while (i < toCharArray.length && i <= 99) {
                allocate.putChar(toCharArray[i]);
                i++;
            }
            allocate.position(0);
            allocate.limit(200);
            outputStream.write(allocate.array(), 0, 200);
        } catch (Exception e) {
        }
    }

    public final void m12545a(String str, int i) {
        try {
            this.f9419c = new HashMap();
            this.f9417a = new LinkedBlockingQueue();
            this.f9418b = new LinkedBlockingQueue();
            Object c4071n = new C4071n(this.f9419c, this.f9418b);
            new Thread(c4071n).start();
            new Thread(new C4052b(this.f9419c, this.f9417a, new C4069l(this, c4071n))).start();
        } catch (Exception e) {
        }
        this.f9422f = new C4065a(this, str, i);
        C4037a.m12449a();
        C4037a.m12452a(new C4068k(this));
        m12538a(0);
    }

    public final void m12546b() {
        this.f9420d = false;
        if (this.f9421e != null) {
            try {
                this.f9421e.close();
                C4071n.m12550a();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
