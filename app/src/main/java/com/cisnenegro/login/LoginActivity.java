package com.cisnenegro.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText mEditTextEmail, mEditTextPassword;
    private Button mButtonLogin, mbtnResetPassword;

    private String email = "", password = "";

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEditTextEmail = findViewById(R.id.email);
        mEditTextPassword = findViewById(R.id.password);
        mButtonLogin = findViewById(R.id.btnLogin);
        mAuth = FirebaseAuth.getInstance();
        mbtnResetPassword = findViewById(R.id.btnSendToResetPassword);

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = mEditTextEmail.getText().toString();
                password = mEditTextPassword.getText().toString();

                if (!email.isEmpty() && !password.isEmpty()){
                    loginUser();
                }
                else{
                    Toast.makeText(LoginActivity.this, "Complete los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mbtnResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(LoginActivity.this, ResetPassword.class));
            }
        });
    }
    private void loginUser(){
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
               if (task.isSuccessful()){
                   startActivity(new Intent(LoginActivity.this, inicio.class));
                   finish();
               }
               else{
                   Toast.makeText(LoginActivity.this, "No se pudo iniciar sesion, verifique los datos", Toast.LENGTH_SHORT).show();
               }
            }
        });
    }
}