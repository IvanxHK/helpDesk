package org.utl.helpDesk.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Service {

    private final String url="http://192.168.1.26:8080/helpDeskRest/api/";
    public Retrofit createService(){
        return new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build();
    }
    public Retrofit createScalarService(){
        return new Retrofit.Builder().baseUrl(url).addConverterFactory(ScalarsConverterFactory.create()).build();
    }
}
