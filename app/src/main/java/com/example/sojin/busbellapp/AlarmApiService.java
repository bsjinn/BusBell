package com.example.sojin.busbellapp;

import com.example.sojin.busbellapp.item.DeleteItem;
import com.example.sojin.busbellapp.item.RequestItem;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by sungmokang on 2017. 7. 30..
 */

public interface AlarmApiService {
    //final public String SERVER_ADDRESS = Resources.getSystem().getString(R.string.server_port);
    //final public String SERVER_PORT = Resources.getSystem().getString(R.string.server_address);

    public final String SERVER_ADDRESS = "http://54.214.224.43";
    public final String SERVER_PORT = "3000";

    @FormUrlEncoded
    @POST("/request")
    Call<RequestItem> request(@Field(value = "deviceID") String deviceID, @Field(value = "busID") String busID,
                              @Field(value = "preStnID") String preStnID, @Field(value = "destStnID") String destStnID);

    @GET("/request/delete/{reqID}")
    Call<DeleteItem> delete(@Path("reqID") String reqID);

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(SERVER_ADDRESS + ":" + SERVER_PORT)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
}
