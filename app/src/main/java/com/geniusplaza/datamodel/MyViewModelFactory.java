package com.geniusplaza.datamodel;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

public class MyViewModelFactory implements ViewModelProvider.Factory {
    private Application mApplication;
    private IDataModel mDataModel;


    public MyViewModelFactory(Application application, IDataModel dataModel) {
        mApplication = application;
        mDataModel = dataModel;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T)new MainViewModel(mDataModel,mApplication);
    }
}
