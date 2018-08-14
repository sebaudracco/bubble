package com.altbeacon.beacon.p012b;

import com.altbeacon.beacon.p013c.C0835d;
import cz.msebera.android.httpclient.HttpHeaders;
import cz.msebera.android.httpclient.HttpStatus;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class C0824d {
    protected String f1613a;
    protected Exception f1614b;
    private int f1615c = -1;
    private String f1616d;
    private String f1617e;

    public C0824d(String str, String str2) {
        this.f1616d = str;
        this.f1617e = str2;
    }

    public int m1592a() {
        return this.f1615c;
    }

    public String m1593b() {
        return this.f1613a;
    }

    public Exception m1594c() {
        return this.f1614b;
    }

    public void m1595d() {
        Throwable th;
        Throwable th2;
        BufferedReader bufferedReader;
        this.f1613a = null;
        String str = this.f1616d;
        StringBuilder stringBuilder = new StringBuilder();
        HttpURLConnection httpURLConnection = null;
        int i = 0;
        String str2 = str;
        while (true) {
            URL url;
            if (i != 0) {
                C0835d.m1657a("DistanceConfigFetcher", "Following redirect from %s to %s", this.f1616d, httpURLConnection.getHeaderField(HttpHeaders.LOCATION));
                str = httpURLConnection.getHeaderField(HttpHeaders.LOCATION);
            } else {
                str = str2;
            }
            i++;
            this.f1615c = -1;
            try {
                url = new URL(str);
            } catch (Exception e) {
                C0835d.m1663d("DistanceConfigFetcher", "Can't construct URL from: %s", this.f1616d);
                this.f1614b = e;
                url = null;
            }
            if (url == null) {
                C0835d.m1657a("DistanceConfigFetcher", "URL is null.  Cannot make request", new Object[0]);
            } else {
                try {
                    HttpURLConnection httpURLConnection2 = (HttpURLConnection) url.openConnection();
                    try {
                        httpURLConnection2.addRequestProperty("User-Agent", this.f1617e);
                        this.f1615c = httpURLConnection2.getResponseCode();
                        C0835d.m1657a("DistanceConfigFetcher", "response code is %s", Integer.valueOf(httpURLConnection2.getResponseCode()));
                        httpURLConnection = httpURLConnection2;
                    } catch (Throwable e2) {
                        th = e2;
                        httpURLConnection = httpURLConnection2;
                        th2 = th;
                        C0835d.m1658a(th2, "DistanceConfigFetcher", "Can't reach sever.  Have you added android.permission.INTERNET to your manifest?", new Object[0]);
                        this.f1614b = th2;
                        if (i < 10) {
                            break;
                        }
                        break;
                        if (this.f1614b != null) {
                            try {
                                bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                                while (true) {
                                    str = bufferedReader.readLine();
                                    if (str != null) {
                                        bufferedReader.close();
                                        this.f1613a = stringBuilder.toString();
                                        return;
                                    }
                                    stringBuilder.append(str);
                                }
                            } catch (Throwable th22) {
                                this.f1614b = th22;
                                C0835d.m1658a(th22, "DistanceConfigFetcher", "error reading beacon data", new Object[0]);
                                return;
                            }
                        }
                    } catch (Throwable e22) {
                        th = e22;
                        httpURLConnection = httpURLConnection2;
                        th22 = th;
                        C0835d.m1658a(th22, "DistanceConfigFetcher", "No data exists at \"+urlString", new Object[0]);
                        this.f1614b = th22;
                        if (i < 10) {
                            break;
                        }
                        break;
                        if (this.f1614b != null) {
                            bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                            while (true) {
                                str = bufferedReader.readLine();
                                if (str != null) {
                                    stringBuilder.append(str);
                                } else {
                                    bufferedReader.close();
                                    this.f1613a = stringBuilder.toString();
                                    return;
                                }
                            }
                        }
                    } catch (Throwable e222) {
                        th = e222;
                        httpURLConnection = httpURLConnection2;
                        th22 = th;
                        C0835d.m1658a(th22, "DistanceConfigFetcher", "Can't reach server", new Object[0]);
                        this.f1614b = th22;
                        if (i < 10) {
                            break;
                        }
                        break;
                        if (this.f1614b != null) {
                            bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                            while (true) {
                                str = bufferedReader.readLine();
                                if (str != null) {
                                    bufferedReader.close();
                                    this.f1613a = stringBuilder.toString();
                                    return;
                                }
                                stringBuilder.append(str);
                            }
                        }
                    }
                } catch (SecurityException e3) {
                    th22 = e3;
                    C0835d.m1658a(th22, "DistanceConfigFetcher", "Can't reach sever.  Have you added android.permission.INTERNET to your manifest?", new Object[0]);
                    this.f1614b = th22;
                    if (i < 10) {
                        break;
                    }
                    break;
                    if (this.f1614b != null) {
                        bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                        while (true) {
                            str = bufferedReader.readLine();
                            if (str != null) {
                                stringBuilder.append(str);
                            } else {
                                bufferedReader.close();
                                this.f1613a = stringBuilder.toString();
                                return;
                            }
                        }
                    }
                } catch (FileNotFoundException e4) {
                    th22 = e4;
                    C0835d.m1658a(th22, "DistanceConfigFetcher", "No data exists at \"+urlString", new Object[0]);
                    this.f1614b = th22;
                    if (i < 10) {
                        break;
                    }
                    break;
                    if (this.f1614b != null) {
                        bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                        while (true) {
                            str = bufferedReader.readLine();
                            if (str != null) {
                                bufferedReader.close();
                                this.f1613a = stringBuilder.toString();
                                return;
                            }
                            stringBuilder.append(str);
                        }
                    }
                } catch (IOException e5) {
                    th22 = e5;
                    C0835d.m1658a(th22, "DistanceConfigFetcher", "Can't reach server", new Object[0]);
                    this.f1614b = th22;
                    if (i < 10) {
                        break;
                    }
                    break;
                    if (this.f1614b != null) {
                        bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                        while (true) {
                            str = bufferedReader.readLine();
                            if (str != null) {
                                stringBuilder.append(str);
                            } else {
                                bufferedReader.close();
                                this.f1613a = stringBuilder.toString();
                                return;
                            }
                        }
                    }
                }
            }
            if (i < 10 && (this.f1615c == HttpStatus.SC_MOVED_TEMPORARILY || this.f1615c == HttpStatus.SC_MOVED_PERMANENTLY || this.f1615c == HttpStatus.SC_SEE_OTHER)) {
                str2 = str;
            }
        }
        if (this.f1614b != null) {
            bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            while (true) {
                str = bufferedReader.readLine();
                if (str != null) {
                    stringBuilder.append(str);
                } else {
                    bufferedReader.close();
                    this.f1613a = stringBuilder.toString();
                    return;
                }
            }
        }
    }
}
