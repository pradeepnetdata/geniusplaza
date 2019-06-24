package com.geniusplaza.interfaces;

import com.geniusplaza.model.User;
import com.geniusplaza.model.UserResponse;
import com.geniusplaza.model.UserResult;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiEndpoints {

    @GET("users")
    Call<UserResult> getUserData();

    @FormUrlEncoded
    @POST("users")
    Call<UserResponse> postUserData(@Field("name") String name, @Field("job") String job);

}