package com.example.schedulab;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import android.telephony.SmsManager;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.schedulab.databinding.ActivityMainBinding;
import com.example.schedulab.refactoring.Contract;
import com.example.schedulab.refactoring.Model;
import com.example.schedulab.refactoring.Presenter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener , Contract.View{

    private Contract.Presenter presenter;
    private TextView register, textView5;
    private EditText editTextEmail, edittextPassword;
    private Button signIn;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        register = (TextView) findViewById(R.id.register);
        register.setOnClickListener(this);

        textView5 = (TextView) findViewById(R.id.textView5);
        textView5.setOnClickListener(this);

        signIn = (Button) findViewById(R.id.signIn);
        signIn.setOnClickListener(this);

        editTextEmail = (EditText) findViewById(R.id.email);
        edittextPassword = (EditText) findViewById(R.id.password);
        progressBar = findViewById(R.id.progressbar);


        presenter = new Presenter(new Model(), this);


    }




    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.register:
                startActivity(new Intent(this, Register.class));
                break;
            case R.id.signIn:
                String email = editTextEmail.getText().toString().trim();
                String password = edittextPassword.getText().toString().trim();
                if(presenter.loginButtonClicked(email, password)) {
                    //progressBar.setVisibility(View.VISIBLE);
                    presenter.login(email, password);
                }
                break;

            case R.id.textView5:
                startActivity(new Intent(this, LoginType.class));
                break;
        }
    }







    @Override
    public void onSuccess() {
        startActivity(new Intent(MainActivity.this, StudentDefault.class));
        //progressBar.setVisibility(View.GONE);


    }



    @Override
    public void onFailure() {
        Toast.makeText(MainActivity.this, "Failed to login, please check your credentials", Toast.LENGTH_LONG).show();
       // progressBar.setVisibility(View.GONE);

    }

    @Override
    public void emailNotValid() {
        editTextEmail.setError("Please enter a valid email");
        editTextEmail.requestFocus();
        return;

    }

    @Override
    public void emailEmpty() {
        editTextEmail.setError("Email is required");
        editTextEmail.requestFocus();
        return;

    }

    @Override
    public void passwordEmpty() {
        edittextPassword.setError("Password is required");
        edittextPassword.requestFocus();
        return;

    }

    public String getEmail(){
        String email = editTextEmail.getText().toString().trim();
        return email;

    }

    public String getPassword(){
        String pass = edittextPassword.getText().toString().trim();
        return pass;

    }


}