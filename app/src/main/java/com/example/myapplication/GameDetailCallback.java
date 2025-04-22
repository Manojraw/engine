package com.example.myapplication;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public class GameDetailCallback {




        List<raju> gameList;

        private static Retrofit retrofit;
        private List<GameModel> similarGamesList;
        private RecyclerView recyclerVie1;
        public interface ApiService {

            @Headers({
                    "Client-ID: yours",
                    "Authorization: Bearer <yours>"
            })
            @POST("events")

            Call<List<EventModel>> getGameDetail(@Body RequestBody query);
        }


        public static final String GAME_DETAIL_QUERY = "fields id, name, event_logo.image_id, start_time; sort start_time desc;limit 10;   fields id, name, description, event_logo.image_id, start_time, games.cover.image_id, games.name;limit 10; where id = ";


        public static ApiService detailapi() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.igdb.com/v4/") // Base URL for IGDB API
                    .addConverterFactory(CustomGsonConverterFactory.create())
                    .build();

            // ApiService object create
            return retrofit.create(ApiService.class);
        }


        void rma (Context context, ProgressBar progressBar, View view, View view1,String id) {
            progressBar.setVisibility(VISIBLE);
            String fullQuery = GAME_DETAIL_QUERY+ id + ";"; // Final Query
            RequestBody body = RequestBody.create(fullQuery, okhttp3.MediaType.parse("text/plain"));
            Log.d("cdsfd", "rma: "+body);
            ApiService apiService = detailapi();
            Call<List<EventModel>> call = apiService.getGameDetail(body);
//    retrofit2 r= new retrofit2.Callback<>()

            call.enqueue(new retrofit2.Callback<List<EventModel>>()


            {


                @Override
                public void onResponse
                        (Call < List < EventModel >> call, retrofit2.Response < List < EventModel >> response){
                    if (response.isSuccessful() && response.body() != null && !response.body().isEmpty()) {
                progressBar.setVisibility(GONE);
                        List<EventModel> responseList = response.body();
                        if (responseList != null && !responseList.isEmpty()) {

                            similarGamesList=new ArrayList<>();

                            for (EventModel detail : responseList) {
                                if (detail.getEvent_logo() != null && detail.getEvent_logo().getImage_id() != null) {
                                    String id = detail.getEvent_logo().getImage_id();
                                    String nam = detail.getName();

                                    long time= detail.getStart_time();
                                    String t=detail.getDescription();
                                    List<GameModel> similar_games=detail.getGames();

                                    if (time < 1000000000000L) {
                                        time *= 1000;
                                    }
                                    Date date = new Date(time);
                                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy", Locale.getDefault());

                                    String imageUrl = "https://images.igdb.com/igdb/image/upload/t_1080p/" + id + ".jpg";
                                    Log.d("dxcf", "onResponse: "+nam+id+time+imageUrl);
                                    ImageView imageView1=view1.findViewById(R.id.imageView2);
                                    TextView textView=view1.findViewById(R.id.textView);
                                    TextView textView1=view1.findViewById(R.id.textView3);
 TextView textView2=view1.findViewById(R.id.textView4);
                                    textView.setText(nam);
                                    textView1.setText(sdf.format(date));
                                    textView2.setText(t);
                                     recyclerVie1=view1.findViewById(R.id.similarGamesRecyclerView);
                                    boolean[] isExpanded = {false}; // Array banaya kyunki inner class mein use karna hai

                                    textView2.setOnClickListener(v -> {
                                        if (isExpanded[0]) {
                                            // Collapse karo
                                            textView2.setMaxLines(3);
                                            textView2.setEllipsize(TextUtils.TruncateAt.END);
                                            isExpanded[0] = false;

                                        } else {
                                            // Expand karo
                                            textView2.setMaxLines(20);
                                            textView2.setEllipsize(null);
                                            isExpanded[0] = true;
                                        }
                                    });
                                    Glide.with(view.getContext())
                                            .load(imageUrl)
                                            .into(imageView1);
                                    Log.d("adsffer", "onResponse: "+nam);
                                    if (similar_games != null) {
                                        for (GameModel similar_game : similar_games) {
                                            if(    similar_game.getCover()!=null){
                                            Log.d("SimilarGames", "similar_games is null for event: " + similar_game.getName());
                                            GameModel game = new GameModel(
                                                    String.valueOf(similar_game.getId()),
                                                    similar_game.getName(),
                                                    similar_game.getCover().getImageId()
                                            );

                                            similarGamesList.add(game);}
                                        }
                                    } else {
                                        Log.d("SimilarGames", "similar_games is null for event: " +nam);
                                    }

                                }
                            }


                            recyclerVie1.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));

                           GamesAdapter adapter = new GamesAdapter(context,similarGamesList,view);
                            recyclerVie1.setAdapter(adapter);

                        } else {
                            Log.e("nj", "Response list is null or empty");
                        }


                    }
                }

                @Override
                public void onFailure (Call < List < EventModel >> call, Throwable t){
                    Log.e("GameDetail", "API Call Failed: " + t.getMessage());
            progressBar.setVisibility(GONE);
                }
            });


        }


    }


//        for (GameModel similar_game : similar_games) {
//


//                                }
//
////                            Log.d("CompanyInfo", "screen: " +    dates);
//                                        }
//
////
//
//RecyclerView similarGamesRecyclerView = view.findViewById(R.id.similarGamesRecyclerView);
//SimilarGamesAdapter adapter = new SimilarGamesAdapter(getContext(),similarGamesList,view);
//                        similarGamesRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
//
//
//        similarGamesRecyclerView.setAdapter(adapter);
//







//
//
//                        Log.d("CompanyInfo", "Release Dates: " + releaseDates.get(releaseDates.size()-1).getHuman());
//String date1=releaseDates.get(releaseDates.size()-1).getHuman();
//                            date.setText(date1);
//
//
//
////                        else {
////