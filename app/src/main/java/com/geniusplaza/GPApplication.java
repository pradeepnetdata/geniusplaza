package com.geniusplaza;

import android.app.Application;
import android.support.annotation.NonNull;


import com.geniusplaza.datamodel.DataModel;
import com.geniusplaza.datamodel.IDataModel;
import com.geniusplaza.datamodel.MainViewModel;

public class GPApplication extends Application {

    private MainViewModel viewModel;
    private final IDataModel mDataModel;

    public GPApplication(){
        mDataModel = new DataModel();
    }
    @NonNull
    public IDataModel getDataModel() {
        return mDataModel;
    }

}
