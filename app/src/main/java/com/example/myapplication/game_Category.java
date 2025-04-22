package com.example.myapplication;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static java.security.AccessController.getContext;

import android.content.Context;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public class game_Category {


    List<raju> gameList;

    private static Retrofit retrofit;
private List<raju> topItemList;
    public interface ApiService {

        @Headers({
                "Client-ID: yours",
                "Authorization: Bearer <yours>"
        })
        @POST("events")
            // 👈 IGDB 'games' endpoint
        Call<List<raju>> getGameDetail(@Body RequestBody query);
    }


    public static final String GAME_DETAIL_QUERY = "fields id, name, event_logo.image_id, start_time;sort start_time desc;limit 10;";


    public static ApiService detailapi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.igdb.com/v4/") // Base URL for IGDB API
                .addConverterFactory(CustomGsonConverterFactory.create())
                .build();

        // ApiService object create
        return retrofit.create(ApiService.class);
    }

    void rma (Context context, RecyclerView recyclerVie1, ProgressBar progressBar, View view) {
                progressBar.setVisibility(VISIBLE);
        String fullQuery = GAME_DETAIL_QUERY; // Final Query
        RequestBody body = RequestBody.create(fullQuery, okhttp3.MediaType.parse("text/plain"));
        ApiService apiService = detailapi();
        Call<List<raju>> call = apiService.getGameDetail(body);
//    retrofit2 r= new retrofit2.Callback<>()

        call.enqueue(new retrofit2.Callback<List<raju>>()


        {

        @Override
        public void onResponse
        (Call < List < raju >> call, retrofit2.Response < List < raju >> response){
            if (response.isSuccessful() && response.body() != null && !response.body().isEmpty()) {
                progressBar.setVisibility(GONE);
                List<raju> responseList = response.body();
                if (responseList != null && !responseList.isEmpty()) {

                    topItemList = new ArrayList<>();

                    for (raju detail : responseList) {
                        if (detail.getEvent_logo() != null && detail.getEvent_logo().getImage_id() != null) {
                            int id = detail.getId();
                            String nam = detail.getName();
                            Long tim = detail.getStart_time();
                            raju.EventLogo imageId = detail.getEvent_logo();

                            raju g = new raju(id, nam, imageId, tim);
                            topItemList.add(g);
                        }
                    }

                    recyclerVie1.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                    TopAdapter.OnItemClickListener listener = new TopAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(raju item) {
                            long time= item.getStart_time();

                            if (time < 1000000000000L) {
                                time *= 1000;
                            }
                            long now = System.currentTimeMillis();
                            if (time < now) {
                                Bundle bundle = new Bundle();
                                detailapi detailApi = new detailapi();

                                bundle.putString( "id", String.valueOf(item.getId()));
//                            bundle.putInt( "position", position);
                                Log.d("ghjjjjjj", "bind: "+item.getId()+item.getName());
                                NavController nav= Navigation.findNavController(view);
                                nav.navigate(R.id.action_mainfragment_to_eventdetaile,bundle);
                            }


                        }
                    };
                    TopAdapter adapter = new TopAdapter(topItemList, listener);
                    recyclerVie1.setAdapter(adapter);

                } else {
                    Log.e("nj", "Response list is null or empty");
                }


            }
        }

        @Override
        public void onFailure (Call < List < raju >> call, Throwable t){
            Log.e("GameDetail", "API Call Failed: " + t.getMessage());
            progressBar.setVisibility(GONE);
        }
    });


    }


}