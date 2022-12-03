package com.example.schedulab.refactoring;

import android.util.Patterns;

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
