package com.elephant.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import com.elephant.data.p035a.C1730j;

public class ElephantDataProvider extends ContentProvider {
    public int delete(Uri uri, String str, String[] strArr) {
        return C1730j.m4984a().m4987a(uri, str, strArr);
    }

    public String getType(Uri uri) {
        C1730j.m4984a();
        return C1730j.m4985b();
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        return C1730j.m4984a().m4989a(uri, contentValues);
    }

    public boolean onCreate() {
        C1730j.m4984a().m4990a(getContext());
        return true;
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return C1730j.m4984a().m4988a(uri, strArr, str, strArr2, str2);
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return C1730j.m4984a().m4986a(uri, contentValues, str, strArr);
    }
}
