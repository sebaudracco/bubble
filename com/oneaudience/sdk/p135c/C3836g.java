package com.oneaudience.sdk.p135c;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;

public class C3836g {
    public static String m12265a(InputStream inputStream) {
        try {
            String b = C3836g.m12268b(inputStream);
            return b;
        } finally {
            C3836g.m12266a((Closeable) inputStream);
        }
    }

    public static void m12266a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
            }
        }
    }

    public static void m12267a(InputStream inputStream, OutputStream outputStream) {
        while (true) {
            int read = inputStream.read();
            if (read != -1) {
                outputStream.write(read);
            } else {
                return;
            }
        }
    }

    private static String m12268b(InputStream inputStream) {
        Reader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            int read = bufferedReader.read();
            if (read == -1) {
                return stringBuilder.toString();
            }
            stringBuilder.append((char) read);
        }
    }
}
