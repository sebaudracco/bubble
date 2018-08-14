package com.danikula.videocache.sourcestorage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.danikula.videocache.Preconditions;
import com.danikula.videocache.SourceInfo;

class DatabaseSourceInfoStorage extends SQLiteOpenHelper implements SourceInfoStorage {
    private static final String[] ALL_COLUMNS = new String[]{COLUMN_ID, "url", COLUMN_LENGTH, COLUMN_MIME};
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_LENGTH = "length";
    private static final String COLUMN_MIME = "mime";
    private static final String COLUMN_URL = "url";
    private static final String CREATE_SQL = "CREATE TABLE SourceInfo (_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,url TEXT NOT NULL,mime TEXT,length INTEGER);";
    private static final String TABLE = "SourceInfo";

    DatabaseSourceInfoStorage(Context context) {
        super(context, "AndroidVideoCache.db", null, 1);
        Preconditions.checkNotNull(context);
    }

    public void onCreate(SQLiteDatabase db) {
        Preconditions.checkNotNull(db);
        db.execSQL(CREATE_SQL);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        throw new IllegalStateException("Should not be called. There is no any migration");
    }

    public SourceInfo get(String url) {
        Preconditions.checkNotNull(url);
        Cursor cursor = null;
        try {
            cursor = getReadableDatabase().query(TABLE, ALL_COLUMNS, "url=?", new String[]{url}, null, null, null);
            SourceInfo convert = (cursor == null || !cursor.moveToFirst()) ? null : convert(cursor);
            if (cursor != null) {
                cursor.close();
            }
            return convert;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public void put(String url, SourceInfo sourceInfo) {
        boolean exist;
        Preconditions.checkAllNotNull(url, sourceInfo);
        if (get(url) != null) {
            exist = true;
        } else {
            exist = false;
        }
        ContentValues contentValues = convert(sourceInfo);
        if (exist) {
            getWritableDatabase().update(TABLE, contentValues, "url=?", new String[]{url});
            return;
        }
        getWritableDatabase().insert(TABLE, null, contentValues);
    }

    public void release() {
        close();
    }

    private SourceInfo convert(Cursor cursor) {
        return new SourceInfo(cursor.getString(cursor.getColumnIndexOrThrow("url")), cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_LENGTH)), cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MIME)));
    }

    private ContentValues convert(SourceInfo sourceInfo) {
        ContentValues values = new ContentValues();
        values.put("url", sourceInfo.url);
        values.put(COLUMN_LENGTH, Long.valueOf(sourceInfo.length));
        values.put(COLUMN_MIME, sourceInfo.mime);
        return values;
    }
}
