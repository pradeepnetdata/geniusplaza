package com.geniusplaza.activities;

import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.geniusplaza.adapter.RecyclerViewAdapter;
import com.geniusplaza.datamodel.MainViewModel;
import com.geniusplaza.GPApplication;
import com.geniusplaza.R;
import com.geniusplaza.datamodel.MyViewModelFactory;
import com.geniusplaza.model.User;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private MainViewModel userViewModel;
    private final String TAG = getClass().getSimpleName();
    private Button btn_create_user;
    private TextView tv_data ;
    private LinearLayoutManager layoutManager;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_create_user = (Button)findViewById(R.id.btn_create_user);
        userViewModel = ViewModelProviders.of(this, new MyViewModelFactory( this.getApplication(), ((GPApplication) getApplication()).getDataModel())).get(MainViewModel.class);
        showDialog();
        getUserList();
        btn_create_user.setOnClickListener(this::onClick);
    }
   private void showDialog(){
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("loading...");
        progressDialog.show();
   }
    private void dismissDialog(){
        if(progressDialog!=null){
            progressDialog.dismiss();
        }
    }
    private void getUserList() {
        Log.i("autolog", "getUserList");
        userViewModel.getUserList()
                .observe(this, newsResponse -> {
                    if (newsResponse != null) {
                        List<User> userList = newsResponse;
                        if(userList.size() > 0 ) {
                            Log.v(TAG, "List item 11 :  " + userList.size());
                            updateUserInformation(userList);
                            dismissDialog();
                        }
                    } else {
                        dismissDialog();
                    }
                });
    }

    public void updateUserInformation(List<User> userList){
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler);
        //recyclerView.setHasFixedSize(true);
        Log.i("autolog", "RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recycler);");

        layoutManager = new LinearLayoutManager(MainActivity.this);
        Log.i("autolog", "layoutManager = new LinearLayoutManager(MainActivity.this);");
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        Log.i("autolog", "recyclerView.setLayoutManager(layoutManager);");

        RecyclerViewAdapter recyclerViewAdapter =new RecyclerViewAdapter(getApplicationContext(), userList);
        Log.i("autolog", "RecyclerViewAdapter recyclerViewAdapter =new RecyclerViewAdapter(getApplicationContext(), userList);");
        recyclerView.setAdapter(recyclerViewAdapter);
        Log.i("autolog", "recyclerView.setAdapter(recyclerViewAdapter);");
    }

    @Override
    public void onClick(View view) {
        starUserInfoActivity();
    }
    private void starUserInfoActivity(){
        Intent intent = new Intent(this,UserActivity.class);
        startActivity(intent);
    }
}
