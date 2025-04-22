package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class mainfragment extends Fragment {
  private ImageButton search_button;
    private RecyclerView recyclerView,recyclerVie1;
    private List<GameCategory> gameCategories;
    private List<GameApiHelper> game;

    ProgressBar progressBar;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_mainfragment, container, false);
        progressBar = view.findViewById(R.id.progressBar);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerVie1=view.findViewById(R.id.recyclerVie1);
        search_button=view.findViewById(R.id.search_button);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        gameCategories = new ArrayList<>();
        game = new ArrayList<>();




        List<Attraction> childLis = new ArrayList<>();
        long currentTimestamp = System.currentTimeMillis() / 1000;

        game.add(new GameApiHelper("1","Most Anticipated", " fields id, name, cover.image_id, first_release_date, hypes;where hypes > 0 & first_release_date > "+  currentTimestamp+ ";"+"sort hypes desc;limit 20;"));

        game.add(new GameApiHelper("2","Recently Released", " fields id, name, cover.image_id, first_release_date, hypes;where hypes > 0 & first_release_date > "+  (currentTimestamp - (60 * 60 * 24 * 30 * 3))+";"+"sort hypes desc;limit 20;"));

        game.add(new GameApiHelper(  "3",
                "Currently Popular",
                "fields id, name, cover.image_id, first_release_date, hypes;where hypes > 0 & first_release_date > "+ (currentTimestamp - (60 * 60 * 24 * 365))+";"+" & rating_count > 50;" +"sort hypes desc;limit 20;"));

        game.add(new GameApiHelper("4",
                "Top 20",
                "fields id, name, cover.image_id, rating, rating_count;where rating >= 90 & rating_count > 50;sort rating_count desc;limit 20;"));
        search_button.setOnClickListener(v->{
            NavController nav= Navigation.findNavController(view);
            nav.navigate(R.id.action_mainfragment_to_fragmentsearch);
        });

//        game= getGameCategories();
        game_Category t=new game_Category();
        t.rma(getContext(),recyclerVie1,progressBar,view);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        Adapter adapter = new Adapter(getContext(),gameCategories,game,progressBar,view,recyclerView);
        recyclerView.setAdapter(adapter);
    }
    }
