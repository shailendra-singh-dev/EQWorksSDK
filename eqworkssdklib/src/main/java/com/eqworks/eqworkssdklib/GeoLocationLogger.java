package com.eqworks.eqworkssdklib;

import android.content.Context;
import android.location.Location;
import android.util.Log;

import androidx.annotation.NonNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Class for logging geo location to Backend server.
 */
public class GeoLocationLogger {

    //Make it null for verifying Unexpected error happened in SDK.
    private static GeoLocationTaskManager mGeoLocationTaskManager = GeoLocationTaskManager.getManager();
    private static String TAG = "GeoLocationLogger";
    private static GeoLocationEndPoint mGeoLocationEndPoint = new GeoLocationEndPoint();

    /**
     * init method for setting default exception handler when un-expected error occures.
     */
    private static void initDefaultExceptionHandler(){
        Log.i(TAG, "initDefaultExceptionHandler()");
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
                    @Override
                    public void uncaughtException(Thread paramThread, Throwable paramThrowable) {
                        Log.i(TAG, "uncaughtException(),Sending unexpected error details to server:" + paramThrowable);
                        mGeoLocationEndPoint.run(paramThrowable.toString());
                    }
                });
    }

    /**
     * Method for logging when developer chooses not to specify time.
     * @param context
     */
    public static void log(final Context context) {
        initDefaultExceptionHandler();
        log(context, System.currentTimeMillis());
    }

    /**
     * Method for logging when developer chooses to specify time.
     * @param context
     * @param currentTime
     */
    public static void log(final Context context, long currentTime) {
        initDefaultExceptionHandler();
        Log.i(TAG, "log(),currentTime:" + currentTime);
        mGeoLocationTaskManager.run(new Task(context, currentTime));
    }

    /**
     * Thread to run backend API call in background.
     */
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
            Log.i(TAG, "run()");
            while (location.getLongitude() == 0 && location.getLatitude() == 0) {
                try {
                    wait(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Log.i(TAG, "run(),location: " + location);
            if (location.getLongitude() != 0 && location.getLatitude() != 0) {
                mGeoLocationEndPoint.run(Utils.getTimeFromLong(mCurrentTime), location.getLongitude(), location.getLatitude());
            } else {
                mGeoLocationEndPoint.run(Utils.getTimeFromLong(mCurrentTime), 0, 0);
            }
        }
    }

}
