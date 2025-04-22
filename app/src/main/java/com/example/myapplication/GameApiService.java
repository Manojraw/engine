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

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public class GameApiService {



    private List<GameModel> similarGamesList;

    public interface ApiService {

        @Headers({
                "Client-ID: yours",
                "Authorization: Bearer <yours>"
        })
        @POST("search")

        Call<List<searchmodel>> getGameDetail(@Body RequestBody query);
    }



    public static ApiService detailapi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.igdb.com/v4/") // Base URL for IGDB API
                .addConverterFactory(CustomGsonConverterFactory.create())
                .build();

        // ApiService object create
        return retrofit.create(ApiService.class);
    }


    void rma (Context context, ProgressBar progressBar, View view1, String titel) {
        progressBar.setVisibility(VISIBLE);
       final String GAME_DETAIL_QUERY = "fields game.cover.image_id, game.name; search \"" + titel + "\"; limit 30;";



        String fullQuery = GAME_DETAIL_QUERY; // Final Query
        RequestBody body = RequestBody.create(fullQuery, MediaType.parse("text/plain"));
        Log.d("rhjssg", "rma: "+body);
  ApiService apiService = detailapi();
        Call<List<searchmodel>> call = apiService.getGameDetail(body);
//    retrofit2 r= new retrofit2.Callback<>()

        call.enqueue(new Callback<List<searchmodel>>() {

            @Override
            public void onResponse(Call < List < searchmodel >> call, Response < List < searchmodel >> response){

                if (response.isSuccessful() && response.body() != null && !response.body().isEmpty()) {
                    progressBar.setVisibility(GONE);
                    List<searchmodel> responseList = response.body();
//                    Gson gson = new Gson();
//                    String json = gson.toJson(response.body());
//                    Log.d("GAME_DETAIL_JSON", json);

                    Log.d("SimilarGames", "similar_games is null for event: " + responseList.size());
                    if (responseList != null && !responseList.isEmpty()) {

                        similarGamesList=new ArrayList<>();
                        Log.d("SimilarGames", "similar_games is null for event: " + responseList.size());
                        for (searchmodel detail : responseList) {
                            if (detail.getGame() != null) { // Pehle game null check
                                GameModel game = detail.getGame();
                                String name = game.getName();
                                String ids = game.getId();
                                String imageId = null;


                                if (game.getCover() != null && game.getCover().getImageId() != null) { // Cover check
                                    imageId = game.getCover().getImageId();
                                }

                                long id = detail.getId();
                                     GameModel b=new GameModel(ids,name,imageId);
                                     similarGamesList.add(b);
                                // Yahan ab safe hai sab
                                Log.d("GameInfo", "id: " + id + ", name: " + name + ", imageId: " + imageId);

                                // Ab apni similarGamesList me add kar sakte ho
                                // Example: similarGamesList.add(new SimilarGame(id, name, imageId));
                            }
                        }

                        RecyclerView recyclerView = view1.findViewById(R.id.search_results_recyclerview);
                        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2);
                        recyclerView.setLayoutManager(gridLayoutManager);

                         GameAdapter gameAdapter = new GameAdapter(similarGamesList,view1);
                        recyclerView.setAdapter(gameAdapter);


                    } else {
                        Log.e("nj", "Response list is null or empty");
                    }


                }
                else {
                    Log.e("nj", "Response list is null or empty");
                }
            }

            @Override
            public void onFailure (Call < List < searchmodel >> call, Throwable t){
                Log.e("GameDetail", "API Call Failed: " + t.getMessage());
                progressBar.setVisibility(GONE);
            }
        });


    }

}
