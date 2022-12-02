package com.example.schedulab;


import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.schedulab.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

    Activity to_admin_h;

    private FragmentFirstBinding binding;
    Activity context;



    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {


        to_admin_h = getActivity();


        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }


    public void onStart(){
        super.onStart();
        Button bt_admin = (Button) to_admin_h.findViewById(R.id.to_admin_home);
        bt_admin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent ah_intent = new Intent(to_admin_h , AllCourses.class);
                startActivity(ah_intent);
            }
        });*/
    }

    public void onStart(){
        super.onStart();
        Button btn = (Button) context.findViewById(R.id.button3);
        btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, AdminDelete.class); //change to AdminEdit.class if want to go there
                startActivity(intent);

            }
        });

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }



}