package com.example.schedulab.refactoring;

import android.os.Bundle;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public interface Contract {
    public interface Model{
        //gets string and password from presenter and uses database reference to authenticate then sends back boolean
        boolean checkLogin(String email, String password);




    }

    public interface View{
        void onSuccess();

        ////Sends not successful authentication answer to view
        void onFailure();

        void emailNotValid();
        void emailEmpty();
        void passwordEmpty();


    }

    public interface Presenter{
        //Sends successful authentication answer to view

        //Sends email and password from view to model
        void login(String email, String password);
            //call method from model with parameters email and string
            //call method onSuccess or onFailure depending on what we get back from model

        boolean loginButtonClicked(String email, String password);
        //check fields are valid
        //send back accordingly to view


    }
}
