package com.bgjd.ici.json;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;

public class JSONTokener {
    private int character;
    private boolean eof;
    private int index;
    private int line;
    private char previous;
    private final Reader reader;
    private boolean usePrevious;

    public JSONTokener(Reader reader) {
        if (!reader.markSupported()) {
            reader = new BufferedReader(reader);
        }
        this.reader = reader;
        this.eof = false;
        this.usePrevious = false;
        this.previous = '\u0000';
        this.index = 0;
        this.character = 1;
        this.line = 1;
    }

    public JSONTokener(InputStream inputStream) throws JSONException {
        this(new InputStreamReader(inputStream));
    }

    public JSONTokener(String str) {
        this(new StringReader(str));
    }

    public void back() throws JSONException {
        if (this.usePrevious || this.index <= 0) {
            throw new JSONException("Stepping back two steps is not supported");
        }
        this.index--;
        this.character--;
        this.usePrevious = true;
        this.eof = false;
    }

    public boolean end() {
        return this.eof && !this.usePrevious;
    }

    public boolean more() throws JSONException {
        next();
        if (end()) {
            return false;
        }
        back();
        return true;
    }

    public char next() throws JSONException {
        int i;
        int i2 = 0;
        if (this.usePrevious) {
            this.usePrevious = false;
            i = this.previous;
        } else {
            try {
                i = this.reader.read();
                if (i <= 0) {
                    this.eof = true;
                    i = 0;
                }
            } catch (Throwable e) {
                throw new JSONException(e);
            }
        }
        this.index++;
        if (this.previous == '\r') {
            this.line++;
            if (i != 10) {
                i2 = 1;
            }
            this.character = i2;
        } else if (i == 10) {
            this.line++;
            this.character = 0;
        } else {
            this.character++;
        }
        this.previous = (char) i;
        return this.previous;
    }

    public char next(char c) throws JSONException {
        char next = next();
        if (next == c) {
            return next;
        }
        throw syntaxError("Expected '" + c + "' and instead saw '" + next + "'");
    }

    public String next(int i) throws JSONException {
        if (i == 0) {
            return "";
        }
        char[] cArr = new char[i];
        for (int i2 = 0; i2 < i; i2++) {
            cArr[i2] = next();
            if (end()) {
                throw syntaxError("Substring bounds error");
            }
        }
        return new String(cArr);
    }

    public char nextClean() throws JSONException {
        char next;
        do {
            next = next();
            if (next == '\u0000') {
                break;
            }
        } while (next <= ' ');
        return next;
    }

    public String nextString(char c) throws JSONException {
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            char next = next();
            switch (next) {
                case '\u0000':
                case '\n':
                case '\r':
                    throw syntaxError("Unterminated string");
                case '\\':
                    next = next();
                    switch (next) {
                        case '\"':
                        case '\'':
                        case '/':
                        case '\\':
                            stringBuilder.append(next);
                            break;
                        case 'b':
                            stringBuilder.append('\b');
                            break;
                        case 'f':
                            stringBuilder.append('\f');
                            break;
                        case 'n':
                            stringBuilder.append('\n');
                            break;
                        case 'r':
                            stringBuilder.append('\r');
                            break;
                        case 't':
                            stringBuilder.append('\t');
                            break;
                        case 'u':
                            stringBuilder.append((char) Integer.parseInt(next(4), 16));
                            break;
                        default:
                            throw syntaxError("Illegal escape.");
                    }
                default:
                    if (next != c) {
                        stringBuilder.append(next);
                        break;
                    }
                    return stringBuilder.toString();
            }
        }
    }

    public Object nextValue() throws JSONException {
        char nextClean = nextClean();
        switch (nextClean) {
            case '\"':
            case '\'':
                return nextString(nextClean);
            case '[':
                back();
                return new JSONArray(this);
            case '{':
                back();
                return new JSONObject(this);
            default:
                StringBuilder stringBuilder = new StringBuilder();
                while (nextClean >= ' ' && ",:]}/\\\"[{;=#".indexOf(nextClean) < 0) {
                    stringBuilder.append(nextClean);
                    nextClean = next();
                }
                back();
                String trim = stringBuilder.toString().trim();
                if (!trim.equals("")) {
                    return JSONObject.stringToValue(trim);
                }
                throw syntaxError("Missing value");
        }
    }

    public JSONException syntaxError(String str) {
        return new JSONException(str + toString());
    }

    public String toString() {
        return " at " + this.index + " [character " + this.character + " line " + this.line + "]";
    }
}
