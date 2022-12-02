package com.example.schedulab.refactoring;

import android.util.Patterns;


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
        if (!(model.checkLogin(email, password))) {
            view.onFailure();
        }
    }





    @Override
    public boolean loginButtonClicked(String email, String password) {

       /* if(email.isEmpty() && password.isEmpty()){
            view.passwordEmpty();
            view.emailEmpty();
            return false;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.isEmpty()){
            view.passwordEmpty();
            view.emailNotValid();
            return false;
        }
        */

        if (email.isEmpty()) {
            view.emailEmpty();
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            view.emailNotValid();
            return false;
        }

       if (password.isEmpty()) {
            view.passwordEmpty();
            return false;

        }



       else
           return true;


    }
}
