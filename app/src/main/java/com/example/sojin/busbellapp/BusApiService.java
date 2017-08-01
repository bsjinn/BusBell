package com.example.sojin.busbellapp;

import com.example.sojin.busbellapp.item.BusArrInfoWrapper;
import com.example.sojin.busbellapp.item.BusPosInfoWrapper;
import com.example.sojin.busbellapp.item.BusRouteInfoWrapper;
import com.example.sojin.busbellapp.item.BusStationInfoWrapper;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by KSM on 2017-07-21.
 */

public interface BusApiService {
    public final String BASE_URL = "http://ws.bus.go.kr";

    @GET("/api/rest/busRouteInfo/getBusRouteList")
    Call<BusRouteInfoWrapper> getBusRouteList(@Query("strSrch") String strSrch,
                                              @Query(value = "serviceKey", encoded = true) String serviceKey);

    @GET("api/rest/busRouteInfo/getStaionByRoute")
    Call<BusStationInfoWrapper> getBusStationByRoute(@Query("busRouteId") String busRouteId,
                                                     @Query(value = "serviceKey", encoded = true) String serviceKey);
    @GET("api/rest/buspos/getBusPosByRtid")
    Call<BusPosInfoWrapper> getBusPosByRtid(@Query("busRouteId") String busRouteId,
                                            @Query(value = "serviceKey", encoded = true) String serviceKey);

    @GET("api/rest/arrive/getArrInfoByRoute")
    Call<BusArrInfoWrapper> getArrInfoByRoute(@Query("busRouteId") String busRouteId, @Query("stId") String stId,
                                              @Query("ord") String ord, @Query(value = "serviceKey", encoded = true) String serviceKey);

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build();
}
