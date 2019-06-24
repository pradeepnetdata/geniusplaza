package com.geniusplaza.activities;

import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.geniusplaza.GPApplication;
import com.geniusplaza.R;
import com.geniusplaza.datamodel.MainViewModel;
import com.geniusplaza.datamodel.MyViewModelFactory;
import com.geniusplaza.model.UserResponse;

public class UserActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "UserActivity";

    private EditText nameText;
    private EditText userJob;
    private Button userCreateButton;
    private MainViewModel userViewModel;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        nameText = (EditText) findViewById(R.id.input_name);
        userJob = (EditText) findViewById(R.id.input_user_job);
        userCreateButton = (Button)findViewById(R.id.btn_create_user);
        userCreateButton.setOnClickListener(this::onClick);
        userViewModel = ViewModelProviders.of(this, new MyViewModelFactory( this.getApplication(), ((GPApplication) getApplication()).getDataModel())).get(MainViewModel.class);
    }

    public void postUserInfo() {
        Log.d("autolog", "postUserInfo");

        if (!validate()) {
            Log.d("autolog", "postUserInfo ::  not validated");
            onUserDataFailed();
            return;
        }

        userCreateButton.setEnabled(false);

        progressDialog = new ProgressDialog(UserActivity.this);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Sending...");
        progressDialog.show();

        String name = nameText.getText().toString();
        String job = userJob.getText().toString();
        userViewModel.postUserData(name,job)
                .observe(this, newsResponse -> {
                    if (newsResponse != null) {
                        UserResponse user = newsResponse;
                        if(user!=null ) {
                            Log.v("autolog", "user id :  " + user.getId());
                           onUserCreatedSuccess(user.getId());

                        }
                    }
                });
    }

    public boolean validate() {
        boolean valid = true;

        String name = nameText.getText().toString();
        String job = userJob.getText().toString();

        if (name.isEmpty() || android.util.Patterns.EMAIL_ADDRESS.matcher(name).matches()) {
            nameText.setError("enter a valid user name ");
            valid = false;
        } else {
            nameText.setError(null);
        }

        if (job.isEmpty() || job.length() < 2 ) {
            userJob.setError("enter a valid user job type");
            valid = false;
        } else {
            userJob.setError(null);
        }

        return valid;
    }
    public void onUserDataFailed() {
        Toast.makeText(getBaseContext(), "User info is incorrect", Toast.LENGTH_LONG).show();
        userCreateButton.setEnabled(true);
    }
    public void onUserCreatedSuccess(String userId) {
        progressDialog.dismiss();
        userCreateButton.setEnabled(true);
        Toast.makeText(getBaseContext(), "User :: " + userId + " created successfully", Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void onClick(View view) {
        postUserInfo();
    }
}
