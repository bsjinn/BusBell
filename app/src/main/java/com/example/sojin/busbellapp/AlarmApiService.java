package com.example.sojin.busbellapp;

import android.content.res.Resources;

import com.example.sojin.busbellapp.item.BusArrInfoWrapper;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.example.sojin.busbellapp.BusApiService.BASE_URL;

/**
 * Created by sungmokang on 2017. 7. 30..
 */

public interface AlarmApiService {
    final public String SERVER_ADDRESS = Resources.getSystem().getString(R.string.server_port);
    final public String SERVER_PORT = Resources.getSystem().getString(R.string.server_address);

    // TODO : Implement Reserveation Service
    @GET("api/rest/arrive/getArrInfoByRoute")
    Call<BusArrInfoWrapper> getArrInfoByRoute(@Query("busRouteId") String busRouteId, @Query("stId") String stId,
                                              @Query("ord") String ord, @Query(value = "serviceKey", encoded = true) String serviceKey);

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build();
}
