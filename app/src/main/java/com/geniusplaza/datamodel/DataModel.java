package com.geniusplaza.datamodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.geniusplaza.interfaces.ApiEndpoints;
import com.geniusplaza.model.User;
import com.geniusplaza.model.UserResponse;
import com.geniusplaza.model.UserResult;
import com.geniusplaza.network.ApiClient;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataModel implements IDataModel{
    private final String TAG = getClass().getSimpleName();

    public LiveData<List<User>> getUserList() {
        Log.i("autolog", "getUserList");
        final MutableLiveData<List<User>> data = new MutableLiveData<>();
        ApiEndpoints service = ApiClient.getApiClient();
        Log.i("autolog", " ApiEndpoints service = retrofit.create(ApiEndpoints.class);");


        Call<UserResult> call = service.getUserData();
        Log.i("autolog", "Call<List<User>> call = service.getUserData();");

        call.enqueue(new Callback<UserResult>() {
            @Override
            public void onResponse(Call<UserResult> call, Response<UserResult> response) {
                //Log.i("onResponse", response.message());
                Log.i("autolog", "onResponse");

                List<User> userList = response.body().getUsers();
                Log.i("autolog", "List<User> userList = "+userList);
                data.setValue(userList);

            }

            @Override
            public void onFailure(Call<UserResult> call, Throwable t) {
                Log.i("autolog", t.getMessage());
            }
        });
        return data;
    }

    @Override
    public LiveData<UserResponse> postUserData(String name, String job) {
        Log.i("autolog", "postUserInfo");
        final MutableLiveData<UserResponse> data = new MutableLiveData<>();
        ApiEndpoints service = ApiClient.getApiClient();

        Call<UserResponse> call = service.postUserData(name, job);
        Log.i("autolog", "Call<List<User>> call = service.postUserData();");

        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                //Log.i("onResponse", response.message());
                Log.i("autolog", "onResponse");

                UserResponse user = response.body();
                Log.i("autolog", "user = "+user);
                data.setValue(user);

            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.i("autolog", t.getMessage());
            }
        });
        return data;
    }


}
