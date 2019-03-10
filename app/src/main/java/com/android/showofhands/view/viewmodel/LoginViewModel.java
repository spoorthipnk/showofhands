package com.android.showofhands.view.viewmodel;

import android.view.View;
import android.widget.Toast;

import com.android.showofhands.model.LoginUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {

    private LiveData<String> email = new MutableLiveData<>();
    private LiveData<String> password = new MutableLiveData<>();

    private MutableLiveData<LoginUser> userLiveData;



    public  MutableLiveData<LoginUser> getUser(){
        if(userLiveData == null){
            userLiveData = new MutableLiveData<>();
        }

        return userLiveData;
    }

   /* public void onClick(View view){
        LoginUser loginUser = new LoginUser(email.getValue(),password.getValue());
        userLiveData.setValue(loginUser);

    }*/



}
