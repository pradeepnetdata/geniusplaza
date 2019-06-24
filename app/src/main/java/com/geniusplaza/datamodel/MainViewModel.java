package com.geniusplaza.datamodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;


import com.geniusplaza.model.User;
import com.geniusplaza.model.UserResponse;

import java.util.List;



/**
 * View model for the main activity.
 */
public class MainViewModel extends AndroidViewModel {

    private IDataModel dataModel;

    private static final MutableLiveData MUTABLE_LIVE_DATA = new MutableLiveData();
    {
        MUTABLE_LIVE_DATA.setValue(null);
    }


    public MainViewModel(IDataModel dataModel,@NonNull Application application){
        super(application);
        this.dataModel = dataModel;
    }


    public LiveData<List<User>> getUserList(){
        return dataModel.getUserList();
    }
    public LiveData<UserResponse> postUserData(String name, String job){
        return dataModel.postUserData(name,job);
    }
}

