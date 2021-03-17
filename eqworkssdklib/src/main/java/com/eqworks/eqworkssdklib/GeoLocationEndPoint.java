package com.eqworks.eqworkssdklib;

import android.nfc.Tag;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Class for Dealing with Backend  end points.
 */
public class GeoLocationEndPoint {

    private static final String URL = "https://httpbin.org/post";

    /**
     * Method to send time, longitude, latitude.
     * @param currentTime
     * @param longitude
     * @param latitude
     * @return
     */
    public boolean run(final String currentTime, final double longitude, double latitude) {
        if (currentTime == null || currentTime.isEmpty()) {
            return false;
        }
        try {
            RequestBody formBody = new FormBody.Builder()
                    .add("time", currentTime)
                    .add("longitude", String.valueOf(longitude))
                    .add("latitude", String.valueOf(latitude))
                    .build();

            executeFormParams(formBody);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Method to send error details when unexpected error occures.
     * @param error
     * @return
     */
    public boolean run(final String error) {
        if (error == null || error.isEmpty()) {
            return false;
        }
        try {
            RequestBody formBody = new FormBody.Builder()
                    .add("error", error)
                    .build();

            executeFormParams(formBody);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Method to send form body consisting of params.
     * @param formBody
     */
    public void executeFormParams(RequestBody formBody) {
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
                System.out.println("onResponse:" + myResponse);
            }
        });
    }


}
