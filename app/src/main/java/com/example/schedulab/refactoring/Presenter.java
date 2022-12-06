package com.example.schedulab.refactoring;

import android.util.Patterns;

import androidx.annotation.NonNull;

import com.example.schedulab.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Presenter implements Contract.Presenter {
    private Contract.Model model;
    private Contract.View view;



    public Presenter(Contract.Model model, Contract.View view) {
        this.model = model;
        this.view = view;
    }



    @Override
    public void login(String email, String password) {
        if (model.checkLogin(email, password)) {

            view.onSuccess();
        }
        else {

            view.onFailure();

        }
    }





    @Override
    public boolean loginButtonClicked(String email, String password) {


        if (email.isEmpty()) {
            view.emailEmpty();
            return false;
        }
        Pattern pattern = Pattern.compile("[a-z]+@[a-z]+\\.com");
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            view.emailNotValid();
            return false;
        }



       if (password.equals("")) {
            view.passwordEmpty();
            return false;

        }



       else
           return true;


    }
}
