package com.vungle.publisher;

import android.util.Log;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/* compiled from: vungle */
class qw$1 implements Callback<ResponseBody> {
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
    }

    public void onFailure(Call<ResponseBody> call, Throwable throwable) {
        Log.e("Vungle", "Failed to call URL " + call.request().url());
    }
}
