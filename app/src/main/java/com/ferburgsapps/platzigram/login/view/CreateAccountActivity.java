package com.ferburgsapps.platzigram.login.view;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.ferburgsapps.platzigram.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CreateAccountActivity extends AppCompatActivity {

    private static final String TAG = "CreateAccountActivity";
    private FirebaseAuth firebasAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private Button btnJoin;
    private TextInputEditText edtCorreo;
    private TextInputEditText edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        showToolbar(getResources().getString(R.string.toolbar_tittle_createaccount), true);

        btnJoin     = (Button) findViewById(R.id.joinUs);
        edtCorreo  = (TextInputEditText) findViewById(R.id.email_createaccount);
        edtPassword = (TextInputEditText) findViewById(R.id.password_createaccount);

        firebasAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebasAuth.getCurrentUser();
                if (firebaseUser != null){
                    Log.w(TAG,"Usuario Logeado" + firebaseUser.getEmail());
                }else{
                    Log.w(TAG, "Usuario no Logeado");
                }
            }
        };

        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAccount();
            }
        });
    }

    private void createAccount() {
        String email    = edtCorreo.getText().toString();
        String password = edtPassword.getText().toString();
        //Toast.makeText(this,"Create "+email+" pass "+password,Toast.LENGTH_LONG).show();
        firebasAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(CreateAccountActivity.this,"Cuenta Creada",Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(CreateAccountActivity.this, "No se creo la cuenta",Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

    public void showToolbar(String tittle, boolean upButton){

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(tittle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);

    }

    @Override
    protected void onStart() {
        super.onStart();
        firebasAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        firebasAuth.removeAuthStateListener(authStateListener);
    }
}
