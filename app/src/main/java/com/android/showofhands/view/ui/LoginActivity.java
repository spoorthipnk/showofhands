package com.android.showofhands.view.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.showofhands.R;
import com.android.showofhands.model.LoginUser;
import com.android.showofhands.view.MainActivity;
import com.android.showofhands.view.viewmodel.LoginViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.email)
    TextInputEditText email_text;
    @BindView(R.id.password)
    TextInputEditText password_text;
    @BindView(R.id.signup_button)
    Button signupButton;
    @BindView(R.id.login_button)
    Button loginButton;

    private LoginViewModel loginViewModel;
    private  FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        sunscribeUI();
    }

    private void sunscribeUI() {

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        loginViewModel.getUser().observe(this, new Observer<LoginUser>() {
            @Override
            public void onChanged(LoginUser loginUser) {
                if (TextUtils.isEmpty(Objects.requireNonNull(loginUser).getEmail())) {
                    email_text.setError("Enter an E-Mail Address");
                    email_text.requestFocus();
                }
                else if (TextUtils.isEmpty(Objects.requireNonNull(loginUser).getPassword())) {
                    password_text.setError("Enter a Password");
                    password_text.requestFocus();
                }
                else {
                    email_text.setText(loginUser.getEmail());
                    password_text.setText(loginUser.getPassword());
                }
            }
        });
    }

    @OnClick({R.id.signup_button, R.id.login_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.signup_button:
                firebaseAuth.getInstance().createUserWithEmailAndPassword(email_text.getText().toString(),password_text.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                    Toast.makeText(LoginActivity.this,"success",Toast.LENGTH_SHORT).show();
                                    startActivity(intent);
                                }
                                else{
                                    Toast.makeText(LoginActivity.this,task.getException().toString(),Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
                break;
            case R.id.login_button:
                firebaseAuth.getInstance().signInWithEmailAndPassword(email_text.getText().toString(),password_text.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                                    Toast.makeText(LoginActivity.this,"success",Toast.LENGTH_SHORT).show();
                                    startActivity(intent);
                                }
                                else{
                                    Toast.makeText(LoginActivity.this,task.getException().toString(),Toast.LENGTH_SHORT).show();

                                }
                            }
                        });

                break;
        }
    }
}
