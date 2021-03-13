package com.eqworks.eqworkssdklib;

import android.content.Context;
import android.location.Location;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GeoLocationLogger {

    private GeoLocationTaskManager mGeoLocationTaskManager;

    private GeoLocationLogger() {
        mGeoLocationTaskManager = GeoLocationTaskManager.getManager();
    }

    public void log(final Context context) {
        log(context, System.currentTimeMillis());
    }

    public void log(final Context context, long currentTime) {
        mGeoLocationTaskManager.run(new Task(context, currentTime));
    }

    private static class Task implements Runnable {
        private final long mCurrentTime;
        private Location location;

        Task(final Context context, long currentTime) {
            GeoLocation geoLocation = new GeoLocation(context);
            location = geoLocation.getLocation();
            mCurrentTime = currentTime;
        }

        @Override
        public void run() {
            while (location.getLongitude() == 0 && location.getLatitude() == 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            GeoLocationEndPoint.run(getTimeFromLong(mCurrentTime), location.getLongitude(), location.getLatitude());
        }

        public String getTimeFromLong(long timeInMilliseconds) {
            if (timeInMilliseconds == 0)
                timeInMilliseconds = System.currentTimeMillis();
            // Creating date format
            DateFormat simple = new SimpleDateFormat("dd MMM yyyy HH:mm:ss:SSS Z");
            Date result = new Date(timeInMilliseconds);
            return simple.format(result);
        }
    }


}
