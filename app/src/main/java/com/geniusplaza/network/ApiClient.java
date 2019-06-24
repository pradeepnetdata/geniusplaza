package com.geniusplaza.network;

import com.geniusplaza.constants.APIConstants;
import com.geniusplaza.interfaces.ApiEndpoints;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit retrofit;
    private static ApiEndpoints apiEndpoints;
    public static ApiEndpoints getApiClient(){

        if(apiEndpoints!=null){
            return apiEndpoints;
        }

        // Implementing HTTP Logger
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient
                .Builder()
                .addNetworkInterceptor(interceptor)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(APIConstants.API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        apiEndpoints = retrofit.create(ApiEndpoints.class);
        return apiEndpoints;
    }

}
