package com.eqworks.eqworkssdklib;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class GeoLocationEndPoint {

    private static final String URL = "https://httpbin.org/post";

    public static void run(final String currentTime, final double longitude, double latitude)  {
        RequestBody formBody = new FormBody.Builder()
                .add("time", currentTime)
                .add("longitude", String.valueOf(longitude))
                .add("latitude", String.valueOf(latitude))
                .build();

        executeFormParams(formBody);
    }

    public static void run(final String error)  {
        RequestBody formBody = new FormBody.Builder()
                .add("error", error)
                .build();

        executeFormParams(formBody);
    }

    private static void executeFormParams(RequestBody formBody) {
        Request request = new Request.Builder()
                .url(URL)
                .post(formBody)
                .build();

        OkHttpClient client = new OkHttpClient();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
                System.out.println("onFailure");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String myResponse = response.body().string();
                System.out.println("onResponse:"+ myResponse);
            }
        });
    }


}
