package com.vungle.publisher.log;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.vungle.publisher.env.C1614r;
import com.vungle.publisher.env.n;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class C1652a {
    @Inject
    C1614r f3078a;
    @Inject
    n f3079b;

    public JsonObject m4345a(File file) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("batch_id", Long.valueOf(this.f3078a.m3966p()));
        jsonObject.addProperty("device_guid", this.f3078a.m3967q());
        JsonElement jsonArray = new JsonArray();
        if (file != null && file.exists()) {
            m4343a(jsonArray, file);
        }
        jsonObject.add("payload", jsonArray);
        return jsonObject;
    }

    private void m4343a(JsonArray jsonArray, File file) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine != null) {
                    String[] a = m4344a(readLine);
                    JsonElement jsonObject = new JsonObject();
                    jsonObject.addProperty("device_timestamp", a[0]);
                    jsonObject.addProperty("time_zone", a[1]);
                    jsonObject.addProperty("context", a[2]);
                    jsonObject.addProperty("log_level", a[3]);
                    jsonObject.addProperty("event_id", a[4]);
                    jsonObject.addProperty("sdk_user_agent", a[5]);
                    jsonObject.addProperty("log_message", a[6]);
                    jsonObject.addProperty("bundle_id", this.f3079b.a());
                    JsonElement jsonObject2 = new JsonObject();
                    jsonObject2.add("metadata", jsonObject);
                    jsonObject2.addProperty("raw_log", readLine);
                    jsonArray.add(jsonObject2);
                } else {
                    return;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    private String[] m4344a(String str) {
        return str.split(";");
    }
}
