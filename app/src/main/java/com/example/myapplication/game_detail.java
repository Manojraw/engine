package com.example.myapplication;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static androidx.navigation.fragment.FragmentKt.findNavController;

import android.annotation.SuppressLint;
import android.media.Image;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;

import okhttp3.RequestBody;
import okhttp3.internal.platform.Platform;
import retrofit2.Call;
import retrofit2.Retrofit;


public class game_detail extends Fragment {
    private detailapi.ApiService apiService;
    ImageButton imag;
    ImageView  image;
    ProgressBar progressBar;
    List<GameModel> similarGamesList;
    ChipGroup platformChipGroup,platformChipGrou;
    RecyclerView recyclerView;
    View view;
    TextView textView,rating,compny,date,summry,seeMoreText ,seeMoreText2 ,seeMoreText3 ;
    List<String> gameList;

    public game_detail() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
     view = inflater.inflate(R.layout.fragment_game_detail, container, false);
        imag=view.findViewById(R.id.customFab);
        image=view.findViewById(R.id.imageView2);
        textView=view.findViewById(R.id.textView);
        compny=view.findViewById(R.id.textView2);
rating=view.findViewById(R.id.ratingBar);
date=view.findViewById(R.id.textView3);
summry=view.findViewById(R.id.textView4);
        platformChipGrou=view.findViewById(R.id.platformChipGrou);
//seeMoreText=view.findViewById(R.id.seeMoreText2);
//seeMoreText2=view.findViewById(R.id.seeMoreText3);
//seeMoreText3=view.findViewById(R.id.seeMoreText4);
        recyclerView  = view.findViewById(R.id.recyclerView);
        gameList= new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        GameDetailAdapter adapter = new GameDetailAdapter(getContext(), gameList);
        recyclerView.setAdapter(adapter);
        platformChipGroup = view.findViewById(R.id.platformChipGroup);
        progressBar = view.findViewById(R.id.progressBar);
       return view;

    }
//    private void fetchGameDetails(int gameId) {
//
//    }

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
        progressBar.setVisibility(VISIBLE);
        String fullQuery = detailapi.Constants.GAME_DETAIL_QUERY + id + ";"; // Final Query
        RequestBody body = RequestBody.create(fullQuery, okhttp3.MediaType.parse("text/plain"));
        apiService = detailapi();
        Call<List<detailmodel>> call = apiService.getGameDetail(body);

        call.enqueue(new retrofit2.Callback<List<detailmodel>>() {
            @Override
            public void onResponse(Call<List<detailmodel>> call, retrofit2.Response<List<detailmodel>> response) {
                if (response.isSuccessful() && response.body() != null && !response.body().isEmpty()) {
                    progressBar.setVisibility(GONE);
                    detailmodel detail = response.body().get(0);

                    if (detail.getCover() != null && detail.getCover().getImage_id() != null) {
                        String imageId = detail.getCover().getImage_id();
                        Log.d("vgjgh", "onResponse: "+imageId);
                        String imageUrl = "https://images.igdb.com/igdb/image/upload/t_1080p/" + imageId + ".jpg";

                        Glide.with(view.getContext())
                                .load(imageUrl)
                                .into(image);

                        Log.d("nj", "Image loaded successfully: " + imageUrl);
                    } else {
                        Log.e("nj", "Cover or ImageId is null");
                    }

                    Log.d("GameDetail", "Name: " + detail.getName());
                    textView.setText(detail.getName());
                    Log.d("GameDetail", "Summary: " + detail.getSummary());
                    boolean[] isExpanded = {false}; // Array banaya kyunki inner class mein use karna hai
                    summry.setText(detail.getSummary());
                    summry.setOnClickListener(v -> {
                        if (isExpanded[0]) {
                            // Collapse karo
                            summry.setMaxLines(3);
                            summry.setEllipsize(TextUtils.TruncateAt.END);
                            isExpanded[0] = false;

                        } else {
                            // Expand karo
                            summry.setMaxLines(20);
                            summry.setEllipsize(null);
                            isExpanded[0] = true;
                        }
                    });


                    Log.d("GameDetail", "Summary: " + detail.getRating());
                    Double rating1 = detail.getRating();  // assuming rating is Double type
                    if (rating1 != null) {

                        String t=String.format("%.1f", detail.getRating() / 10);

                    rating.setText(t);
                        List<detailmodel.InvolvedCompany> involvedCompanies = detail.getInvolved_companies();

                        if (involvedCompanies != null) {
//                            for (detailmodel.InvolvedCompany involvedCompany : involvedCompanies) {
//                                String companyName = involvedCompany.getCompany().getName();
                                String companyName = involvedCompanies.get(0).getCompany().getName();
//                                boolean isDeveloper = involvedCompany.isDeveloper();

                                Log.d("CompanyInfo", "Company: " + companyName );
                            compny.setText(companyName);
                        }
                        List<detailmodel.ReleaseDate> releaseDates = detail.getRelease_dates();
                        List<detailmodel.Genre> releaseDate = detail.getGenres();


                        platformChipGrou.removeAllViews();
                        List<detailmodel.Genre> platforms1 = detail.getGenres();
                        if (platforms1 != null && !platforms1.isEmpty()) {
                            for (detailmodel.Genre platform : platforms1) {
//                                Log.d("platform", "platfar: " +    platform.getName()           );

                                Chip chip = new Chip(getContext());
                                chip.setText(platform.getName()); // Platform ka naam set karo
                                chip.setChipBackgroundColorResource(R.color.white); // background color (gray ya jo tum chaho)
                                chip.setTextColor(ContextCompat.getColor(getContext(), android.R.color.black)); // text color
                                chip.setClickable(false); // Optional: Click disable
                                chip.setCheckable(false); // Optional: Select disable
                                chip.setElevation(4f); // Optional: thoda shadow
                                platformChipGrou.addView(chip);
                            }
                        }



//                        seeMoreText.setText( releaseDate.get(0).getName());
//                        seeMoreText2.setText( releaseDate.get(1).getName());
////                        seeMoreText3.setText( releaseDate.get(2).getName());
                        List<detailmodel.Screenshot> releaeDate = detail.getScreenshots();
//
//                        if (releaeDate != null && !releaeDate.isEmpty()) {
//
//                            StringBuilder dates = new StringBuilder();
//
//                            for (int i = 0; i < releaseDate.size(); i++) {
//                                detailmodel.Screenshot rd = releaeDate.get(i);
//                                if (rd != null && rd.getImage_id() != null) {
//                                    dates.append(rd.getImage_id());
//                                    if (i != releaseDate.size() - 1) {
//                                        dates.append(", "); // Last item ke baad comma nahi lagayenge
//                                    }
//                                }
//                            }
//
//                            Log.d("CompanyInfo", "screen: " +    dates);
//                        }
                        platformChipGroup.removeAllViews();
                        List<detailmodel.Platform> platforms = detail.getPlatforms();
                        if (platforms != null && !platforms.isEmpty()) {
                            for (detailmodel.Platform platform : platforms) {
//                                Log.d("platform", "platfar: " +               );

                                Chip chip = new Chip(getContext());
                                chip.setText(platform.getName()); // Platform ka naam set karo
                                chip.setChipBackgroundColorResource(R.color.white); // background color (gray ya jo tum chaho)
                                chip.setTextColor(ContextCompat.getColor(getContext(), android.R.color.black)); // text color
                                chip.setClickable(false); // Optional: Click disable
                                chip.setCheckable(false); // Optional: Select disable
                                chip.setElevation(4f); // Optional: thoda shadow
                                platformChipGroup.addView(chip);
                            }
                        }
                        for (detailmodel.Screenshot screenshot : releaeDate) {

                            if (screenshot != null && screenshot.getImage_id() != null) {
                                Log.d("CompanyInfo", "screen: " +   screenshot.getImage_id());
                                gameList.add(screenshot.getImage_id());
                            }
                        }

// gameList.add(relaseDate.get(0).getImage_id());
                        List<detailmodel.SimilarGame> similar_games=detail.getSimilar_games();
                        if (similar_games != null && !similar_games.isEmpty()) {

//                            StringBuilder dates = new StringBuilder();

                            similarGamesList=new ArrayList<>();
                                for (detailmodel.SimilarGame similar_game : similar_games) {
                                Log.d("platform", "platfsimilerar: " +              similar_game.getId()+similar_game.getName()+similar_game.getCover().getImage_id()      );

                                    GameModel game = new GameModel(
                                            String.valueOf(similar_game.getId()),
                                            similar_game.getName(),
                                            similar_game.getCover().getImage_id()
                                    );
                                    similarGamesList.add(game);
                                }

//                            Log.d("CompanyInfo", "screen: " +    dates);
                        }

//

                        RecyclerView similarGamesRecyclerView = view.findViewById(R.id.similarGamesRecyclerView);
                        SimilarGamesAdapter adapter = new SimilarGamesAdapter(getContext(),similarGamesList,view);
                        similarGamesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));


                        similarGamesRecyclerView.setAdapter(adapter);










                        Log.d("CompanyInfo", "Release Dates: " + releaseDates.get(releaseDates.size()-1).getHuman());
                            String date1=releaseDates.get(releaseDates.size()-1).getHuman();
                            date.setText(date1);



//                        else {
//                            Log.d("GameDetail", "Release Dates is not available");}
                    } else {
                        Log.d("GameDetail", "Rating is not available");
                    }

//                    String t=detail.getRating().toString();
//
//                    rating.setText(t);
                } else {
                    Log.e("GameDetail", "Response Failed: " + response.errorBody());
                }

            }

            @Override
            public void onFailure(Call<List<detailmodel>> call, Throwable t) {
                Log.e("GameDetail", "API Call Failed: " + t.getMessage());
                progressBar.setVisibility(GONE);
            }
        });


    }



//        detailapi api = new detailapi();



//                Log.d("nj", "onGameDetailFetched: "+gameList.size());
//                if (gameList != null && !gameList.isEmpty()) {
//                    detailmodel detail = gameList.get(0);

                    // ✅ Yaha ab Glide use karke image la




    public static detailapi.ApiService detailapi(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.igdb.com/v4/") // Base URL for IGDB API
                .addConverterFactory(CustomGsonConverterFactory.create())
                .build();

        // ApiService object create
        return retrofit.create(detailapi.ApiService.class);}



    }


    // Ab Glide se ya Picasso se image load kar sakte hai

