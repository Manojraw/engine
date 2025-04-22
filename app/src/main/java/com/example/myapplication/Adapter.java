package com.example.myapplication;

import static android.content.ContentValues.TAG;
import static android.view.View.VISIBLE;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.IGDBApi;
import com.example.myapplication.RetrofitClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

//



   private View view1;
        private Context context;
        private List<GameCategory> gameCategories;
    private ProgressBar progressBar;
    private RecyclerView  recyclerView;

// 1. API call start hone se pehle dikhana

    List<GameModel> gameList;
    private List<GameApiHelper> game;
    View view;
    List<Attraction> tr;
        public Adapter(Context context, List<GameCategory> gameCategories, List<GameApiHelper> game, ProgressBar progressBar,View view,RecyclerView recyclerView) {
            this.context = context;
            this.gameCategories = gameCategories;
            this.game=game;
            this.progressBar=progressBar;

            view1=view;
       this.recyclerView=recyclerView;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
           view= LayoutInflater.from(context).inflate(R.layout.gamecategory, parent, false);
                  return new ViewHolder(view);
        }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        List<Attraction> tr = new ArrayList<>();
        tr.add(new Attraction("game", R.drawable.taj_mahal));
        tr.add(new Attraction( "game ", R.drawable.taj_mahal));
        tr.add(new Attraction("game", R.drawable.taj_mahal));
        tr.add(new Attraction( "game", R.drawable.taj_mahal));


        Log.d("TAG2", "getItemCount:5 "+ game.get(position).getQuery());
        progressBar.setVisibility(VISIBLE);
        String query = game.get(position).getQuery();
        RequestBody body = RequestBody.create(
                MediaType.parse("text/plain"), query
        );
        gameList=new ArrayList<>();
        // Step 3: Call the API
        IGDBApi api = RetrofitClient.getApi();
        Call<ResponseBody> call = api.getGames(body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful() && response.body() != null) {
                    progressBar.setVisibility(View.GONE);
//                    recyclerView.setVisibility(VISIBLE);
//                    recyclerView.setAlpha(0f); // Invisible start
//                    recyclerView.animate()
//                            .alpha(1f)              // Full visible
//                            .setDuration(500)       // 500 milliseconds
//                            .start();
                    try {

                        String json = response.body().string();

                        JSONArray jsonArray = new JSONArray(json);


                        // Step 3d: Notify adapter

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject obj = jsonArray.getJSONObject(i);

                            String id = String.valueOf(obj.getInt("id"));
                            String name = obj.getString("name");

                            JSONObject coverObj = obj.optJSONObject("cover"); // may be null
                            String imageId = "";

                            if (coverObj != null) {
                                imageId = coverObj.getString("image_id");

                            }

                            GameModel game = new GameModel(id, name, imageId);

                            gameList.add(game);

                            aduptor childAdapter = new aduptor(context, tr,gameList,view);

//                             detailapi detailApi = new detailapi();
//                            detailApi.fetchGameDetails(id);// यहाँ पर जो game id चाहिए वो डालो

                        holder.childRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                            holder.childRecyclerView.setAdapter(childAdapter);

//                            for (GameModel g : gameList) {
//                                Log.d("GAME_LIST", "Game: ID = " + g.getId() + ", Name = " + g.getName() );
//                            }
//                            Log.d("GAME_LIST", "Game: ID = " +        gameList.size() );

                        }


//                        Log.d(TAG, json); // Print the raw JSON
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.e(TAG, "Response not successful");
                }

            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(context, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        holder.title.setText(game.get(position).getTitle());
//        Log.d("GAME_LIST", "Game: ID = " +        gameList.size() );

    }

        @Override
        public int getItemCount() {
            int r=game.size();
            Log.d("TAG", "getItemCount: "+r);
            return r;
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            TextView title;
            RecyclerView childRecyclerView;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                title = itemView.findViewById(R.id.text);
                childRecyclerView = itemView.findViewById(R.id.recyclerView);
        }}
    }

