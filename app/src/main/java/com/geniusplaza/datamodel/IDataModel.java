package com.geniusplaza.datamodel;

import android.arch.lifecycle.LiveData;

import com.geniusplaza.model.User;
import com.geniusplaza.model.UserResponse;

import java.util.List;

public interface IDataModel {
    LiveData<List<User>> getUserList();
    LiveData<UserResponse> postUserData(String name, String job);
}
