package com.unit.three.p138b;

import com.unit.three.p139a.C4037a;
import com.unit.three.p139a.C4050g;
import com.unit.three.p141c.C4078f;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.CancelledKeyException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;

public final class C4071n implements Runnable {
    private static Selector f9427b;
    private LinkedBlockingQueue f9428a;
    private HashMap f9429c;

    public C4071n(HashMap hashMap, LinkedBlockingQueue linkedBlockingQueue) {
        this.f9429c = hashMap;
        this.f9428a = linkedBlockingQueue;
        try {
            f9427b = Selector.open();
        } catch (Throwable e) {
            C4037a.m12449a();
            C4037a.m12453a(e);
        }
    }

    public static SocketChannel m12549a(C4064i c4064i) {
        String a = C4078f.m12563a(c4064i);
        try {
            SocketChannel open = SocketChannel.open();
            open.configureBlocking(false);
            open.connect(new InetSocketAddress(c4064i.f9407a.f9390k, c4064i.f9408b.f9393b));
            f9427b.wakeup();
            C4050g.m12495a().m12496a(a);
            open.register(f9427b, 8, c4064i);
            return open;
        } catch (Throwable e) {
            C4037a.m12449a();
            C4037a.m12453a(e);
            return null;
        }
    }

    public static void m12550a() {
        try {
            C4051a.m12502b();
            f9427b.close();
            f9427b = Selector.open();
        } catch (Exception e) {
        }
    }

    private void m12551a(C4064i c4064i, SelectionKey selectionKey, String str) {
        if (c4064i != null && c4064i.f9408b != null) {
            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
            if (c4064i.f9408b.m12531b() || c4064i.f9408b.m12530a()) {
                C4050g.m12495a().m12498b(str);
                m12552a(str, selectionKey);
                return;
            }
            try {
                socketChannel.write(c4064i.f9409c);
            } catch (Throwable e) {
                C4037a.m12449a();
                C4037a.m12453a(e);
            }
        }
    }

    private void m12552a(String str, SelectionKey selectionKey) {
        this.f9429c.remove(str);
        try {
            selectionKey.channel().close();
            selectionKey.cancel();
        } catch (Exception e) {
        }
    }

    public final void run() {
        while (true) {
            try {
                if (f9427b.select() == 0) {
                    Thread.sleep(10);
                } else {
                    Iterator it = f9427b.selectedKeys().iterator();
                    while (it.hasNext()) {
                        SelectionKey selectionKey = (SelectionKey) it.next();
                        C4064i c4064i;
                        if (selectionKey.isReadable()) {
                            try {
                                it.remove();
                            } catch (Exception e) {
                            }
                            ByteBuffer a = C4051a.m12500a();
                            a.position(40);
                            c4064i = (C4064i) selectionKey.attachment();
                            String a2 = C4078f.m12563a(c4064i);
                            try {
                                int read = ((SocketChannel) selectionKey.channel()).read(a);
                                if (read == -1) {
                                    m12552a(a2, selectionKey);
                                    C4051a.m12501a(a);
                                } else {
                                    a.flip();
                                    c4064i.f9407a.f9384e = ((short) read) + 40;
                                    c4064i.m12533a(a);
                                    a.position(0);
                                    this.f9428a.offer(a);
                                }
                            } catch (Throwable e2) {
                                try {
                                    m12552a(a2, selectionKey);
                                    C4051a.m12501a(a);
                                    C4037a.m12449a();
                                    C4037a.m12453a(e2);
                                } catch (Exception e3) {
                                }
                            }
                        } else if (selectionKey.isWritable()) {
                            try {
                                it.remove();
                            } catch (Exception e4) {
                            }
                            try {
                                String a3 = C4078f.m12563a((C4064i) selectionKey.attachment());
                                LinkedBlockingQueue linkedBlockingQueue = (LinkedBlockingQueue) this.f9429c.get(a3);
                                c4064i = linkedBlockingQueue == null ? null : (C4064i) linkedBlockingQueue.poll();
                                if (c4064i == null) {
                                    try {
                                        Thread.sleep(10);
                                    } catch (Exception e5) {
                                    }
                                } else {
                                    m12551a(c4064i, selectionKey, a3);
                                }
                            } catch (CancelledKeyException e6) {
                                selectionKey.cancel();
                            }
                        } else if (selectionKey.isConnectable()) {
                            try {
                                if (((SocketChannel) selectionKey.channel()).finishConnect()) {
                                    selectionKey.interestOps(5);
                                    it.remove();
                                }
                            } catch (Throwable e22) {
                                e22.printStackTrace();
                                C4037a.m12449a();
                                C4037a.m12453a(e22);
                            }
                        }
                    }
                }
            } catch (Throwable e7) {
                C4037a.m12449a();
                C4037a.m12453a(e7);
            }
        }
    }
}
