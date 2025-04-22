package com.example.myapplication;

import static android.view.View.VISIBLE;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class Eventdetaile extends Fragment {

private ImageView imag,image;
private    View view1;
private ProgressBar progressBar;
private RecyclerView recyclerView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
     view1= inflater.inflate(R.layout.fragment_eventdetaile, container, false);
     progressBar=view1.findViewById(R.id.progressBar);
        imag=view1.findViewById(R.id.customFab);

      return view1;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imag.setOnClickListener(v -> {
            NavController nav = Navigation.findNavController(view);
            nav.popBackStack();
        });
        Bundle bundle = getArguments();
        String id = bundle.getString("id");
        Log.d("TAGxcv", "onViewCreated: "+id);


        GameDetailCallback t=new GameDetailCallback();
        t.rma(getContext(),progressBar,view,view1,id);
    }
}