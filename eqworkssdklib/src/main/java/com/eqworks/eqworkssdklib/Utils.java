package com.eqworks.eqworkssdklib;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    public static String getTimeFromLong(long timeInMilliseconds) {
        if (timeInMilliseconds == 0)
            timeInMilliseconds = System.currentTimeMillis();
        // Creating date format
        DateFormat simple = new SimpleDateFormat("dd MMM yyyy HH:mm:ss:SSS Z");
        Date result = new Date(timeInMilliseconds);
        return simple.format(result);
    }
}
