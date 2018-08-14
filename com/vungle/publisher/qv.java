package com.vungle.publisher;

import com.google.gson.JsonObject;
import java.util.Map;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import rx.Observable;

/* compiled from: vungle */
public interface qv {
    @POST("{config}")
    Observable<JsonObject> m4575a(@HeaderMap Map<String, String> map, @Path(encoded = true, value = "config") String str, @Body JsonObject jsonObject);

    @GET("{new}")
    Observable<JsonObject> m4576a(@HeaderMap Map<String, String> map, @Path(encoded = true, value = "new") String str, @QueryMap Map<String, String> map2);

    @POST("{ads}")
    Observable<JsonObject> m4577b(@HeaderMap Map<String, String> map, @Path(encoded = true, value = "ads") String str, @Body JsonObject jsonObject);

    @POST("{will_play_ad}")
    Observable<JsonObject> m4578c(@HeaderMap Map<String, String> map, @Path(encoded = true, value = "will_play_ad") String str, @Body JsonObject jsonObject);

    @POST("{report_ad}")
    Observable<JsonObject> m4579d(@HeaderMap Map<String, String> map, @Path(encoded = true, value = "report_ad") String str, @Body JsonObject jsonObject);

    @POST("{log}")
    Observable<JsonObject> m4580e(@HeaderMap Map<String, String> map, @Path(encoded = true, value = "log") String str, @Body JsonObject jsonObject);
}
