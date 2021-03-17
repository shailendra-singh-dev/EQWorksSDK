package com.eqworks.eqworkssdklib;

import org.junit.Test;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import okhttp3.RequestBody;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.Silent.class)
public class GeoLocationEndPointTest {
    private static final double LONGITUDE = 77.62978451408715;
    private static final double LATITUDE = 12.882882882882884;

    @Mock
    GeoLocationEndPoint geoLocationEndPoint;

    @Mock
    RequestBody requestBody;

    @BeforeAll
    public void setup() {
        assertNotNull(geoLocationEndPoint);
    }

    @Test
    public void testPostHttpCall() {
        when(geoLocationEndPoint.run(Utils.getTimeFromLong(System.currentTimeMillis()), LONGITUDE, LATITUDE)).thenReturn(true);
        when(geoLocationEndPoint.run(Utils.getTimeFromLong(System.currentTimeMillis()), 0, 0)).thenReturn(true);
        when(geoLocationEndPoint.run("", 0, 0)).thenReturn(false);
        when(geoLocationEndPoint.run(null, 0, 0)).thenReturn(false);
    }

    @Test
    public void testPostHttpErrorCall() {
        when(geoLocationEndPoint.run("UnExpected Error in SDK!!")).thenReturn(true);
        when(geoLocationEndPoint.run(null, 0, 0)).thenReturn(false);
        when(geoLocationEndPoint.run("", 0, 0)).thenReturn(false);
    }

    @Test
    public void testExecuteFormParams() {
        assertNotNull(requestBody);
        geoLocationEndPoint.executeFormParams(requestBody);
    }

    @AfterAll
    public void cleanUp() {
        geoLocationEndPoint = null;
        requestBody = null;
    }
}