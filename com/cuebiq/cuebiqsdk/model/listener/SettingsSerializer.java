package com.cuebiq.cuebiqsdk.model.listener;

import com.cuebiq.cuebiqsdk.model.config.SchedulerSleepTime;
import com.cuebiq.cuebiqsdk.model.config.Settings;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.util.Iterator;

public class SettingsSerializer implements JsonDeserializer<Settings>, JsonSerializer<Settings> {
    public Settings deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        int i = 5;
        int i2 = 30;
        int i3 = 10;
        int i4 = 1;
        JsonObject jsonObject = (JsonObject) jsonElement;
        Settings settings = new Settings();
        settings.setMina(jsonObject.get("mina") != null ? jsonObject.get("mina").getAsLong() : 1);
        settings.setAminar(jsonObject.get("aminar") != null ? jsonObject.get("aminar").getAsLong() : 20);
        settings.setDefgeoen(jsonObject.get("defgeoen") != null ? jsonObject.get("defgeoen").getAsInt() : 1);
        settings.setMaxa(jsonObject.get("maxa") != null ? jsonObject.get("maxa").getAsLong() : 30);
        settings.setMinb(jsonObject.get("minb") != null ? jsonObject.get("minb").getAsInt() : 10);
        settings.setMaxb(jsonObject.get("maxb") != null ? jsonObject.get("maxb").getAsInt() : 100);
        settings.setMaxst(jsonObject.get("maxst") != null ? jsonObject.get("maxst").getAsLong() : 60);
        settings.setTr(jsonObject.get("tr") != null ? jsonObject.get("tr").getAsInt() : 30);
        settings.setBtlt(jsonObject.get("btlt") != null ? jsonObject.get("btlt").getAsInt() : 10);
        settings.setIbtr(jsonObject.get("ibtr") != null ? jsonObject.get("ibtr").getAsInt() : 10);
        settings.setCiaa(jsonObject.get("ciaa") != null ? jsonObject.get("ciaa").getAsInt() : 1);
        settings.setIpad(jsonObject.get("ipad") != null ? jsonObject.get("ipad").getAsLong() : 10);
        settings.setTloo(jsonObject.get("tloo") != null ? jsonObject.get("tloo").getAsInt() : 1);
        settings.setTlowo(jsonObject.get("tlowo") != null ? jsonObject.get("tlowo").getAsInt() : 1);
        settings.setAncc(jsonObject.get("ancc") != null ? jsonObject.get("ancc").getAsInt() : Settings.ANALYTICS_COVERAGE_CHECKER_COUNTER);
        settings.setAnao(jsonObject.get("anao") != null ? jsonObject.get("anao").getAsInt() : 30);
        settings.setAlve(jsonObject.get("alve") != null ? jsonObject.get("alve").getAsInt() : Settings.DEFAULT_MIN_VERSION_TO_LOG);
        settings.setAmvs(jsonObject.get("amvs") != null ? jsonObject.get("amvs").getAsInt() : 14);
        settings.setTase(jsonObject.get("tase") != null ? jsonObject.get("tase").getAsInt() : 1);
        settings.setMdyna(jsonObject.get("mdyna") != null ? jsonObject.get("mdyna").getAsInt() : Settings.MAX_DYNAMIC_ACQUISITION);
        settings.setOffda(jsonObject.get("offda") != null ? jsonObject.get("offda").getAsInt() : -10);
        if (jsonObject.get("slopeda") != null) {
            i2 = jsonObject.get("slopeda").getAsInt();
        }
        settings.setSlopeda(i2);
        settings.setWst(jsonObject.get("wst") != null ? jsonObject.get("wst").getAsInt() : 20);
        settings.setAcc(jsonObject.get("acc") != null ? jsonObject.get("acc").getAsString() : "network");
        settings.setV(Integer.valueOf(jsonObject.get("v") != null ? jsonObject.get("v").getAsInt() : 40));
        SchedulerSleepTime schedulerSleepTime = new SchedulerSleepTime();
        if (jsonObject.has("sst")) {
            Iterator it = jsonObject.getAsJsonArray("sst").iterator();
            while (it.hasNext()) {
                schedulerSleepTime.add((Integer) jsonDeserializationContext.deserialize((JsonElement) it.next(), Integer.class));
            }
        }
        settings.setSst(schedulerSleepTime);
        settings.setStbs(jsonObject.get("stbs") != null ? jsonObject.get("stbs").getAsInt() : 5);
        settings.setSddt(jsonObject.get("sddt") != null ? jsonObject.get("sddt").getAsInt() : 50);
        settings.setShdt(jsonObject.get("shdt") != null ? jsonObject.get("shdt").getAsInt() : 200);
        if (jsonObject.get("stt") != null) {
            i = jsonObject.get("stt").getAsInt();
        }
        settings.setStt(i);
        if (jsonObject.get("scen") != null) {
            i4 = jsonObject.get("scen").getAsInt();
        }
        settings.setScen(i4);
        if (jsonObject.get("lri") != null) {
            i3 = jsonObject.get("lri").getAsInt();
        }
        settings.setLri(i3);
        settings.setLrf(jsonObject.get("lrf") != null ? jsonObject.get("lrf").getAsInt() : 4);
        settings.setLrmw(jsonObject.get("lrmw") != null ? jsonObject.get("lrmw").getAsInt() : 60);
        settings.setLrsd(jsonObject.get("lrsd") != null ? jsonObject.get("lrsd").getAsFloat() : Settings.LOCATION_REQUEST_SMALLEST_DISPLACEMENT);
        return settings;
    }

    public JsonElement serialize(Settings settings, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonElement jsonObject = new JsonObject();
        jsonObject.addProperty("mina", Long.valueOf(settings.getMina()));
        jsonObject.addProperty("aminar", Long.valueOf(settings.getAminar()));
        jsonObject.addProperty("defgeoen", Integer.valueOf(settings.getDefgeoen()));
        jsonObject.addProperty("maxa", Long.valueOf(settings.getMaxa()));
        jsonObject.addProperty("minb", Integer.valueOf(settings.getMinb()));
        jsonObject.addProperty("maxb", Integer.valueOf(settings.getMaxb()));
        jsonObject.addProperty("maxst", Long.valueOf(settings.getMaxst()));
        jsonObject.addProperty("tr", Integer.valueOf(settings.getTr()));
        jsonObject.addProperty("btlt", Integer.valueOf(settings.getBtlt()));
        jsonObject.addProperty("ibtr", Integer.valueOf(settings.getIbtr()));
        jsonObject.addProperty("ciaa", Integer.valueOf(settings.getCiaa()));
        jsonObject.addProperty("ipad", Long.valueOf(settings.getIpad()));
        jsonObject.addProperty("tloo", Integer.valueOf(settings.getTloo()));
        jsonObject.addProperty("tlowo", Integer.valueOf(settings.getTlowo()));
        jsonObject.addProperty("ancc", Integer.valueOf(settings.getAncc()));
        jsonObject.addProperty("anao", Integer.valueOf(settings.getAnao()));
        jsonObject.addProperty("alve", Integer.valueOf(settings.getAlve()));
        jsonObject.addProperty("amvs", Integer.valueOf(settings.getAmvs()));
        jsonObject.addProperty("tase", Integer.valueOf(settings.getTase()));
        jsonObject.addProperty("mdyna", Integer.valueOf(settings.getMdyna()));
        jsonObject.addProperty("offda", Integer.valueOf(settings.getOffda()));
        jsonObject.addProperty("slopeda", Integer.valueOf(settings.getSlopeda()));
        jsonObject.addProperty("wst", Integer.valueOf(settings.getWst()));
        jsonObject.addProperty("acc", settings.getAcc());
        jsonObject.addProperty("v", settings.getV());
        jsonObject.add("sst", jsonSerializationContext.serialize(settings.getSst()));
        jsonObject.addProperty("stbs", Integer.valueOf(settings.getStbs()));
        jsonObject.addProperty("sddt", Integer.valueOf(settings.getSddt()));
        jsonObject.addProperty("shdt", Integer.valueOf(settings.getShdt()));
        jsonObject.addProperty("stt", Integer.valueOf(settings.getStt()));
        jsonObject.addProperty("scen", Integer.valueOf(settings.getScen()));
        jsonObject.addProperty("lri", Integer.valueOf(settings.getLri()));
        jsonObject.addProperty("lrf", Integer.valueOf(settings.getLrf()));
        jsonObject.addProperty("lrsd", Float.valueOf(settings.getLrsd()));
        jsonObject.addProperty("lrmw", Integer.valueOf(settings.getLrmw()));
        return jsonObject;
    }
}
