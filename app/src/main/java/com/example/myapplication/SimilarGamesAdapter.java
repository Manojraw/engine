package com.example.myapplication;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class SimilarGamesAdapter extends RecyclerView.Adapter<SimilarGamesAdapter.SimilarGameViewHolder> {
    private  View view2;
    public interface OnGameClickListener {
        void onGameClick(GameModel game);
    }

    private List<GameModel> similarGames;
    private OnGameClickListener listener;
    Context context;

    public SimilarGamesAdapter(Context context, List<GameModel> similarGames,View view) {
        this.similarGames = similarGames;
        this.listener = listener;
        view2=view;
        this.context=context;
    }

    @NonNull
    @Override
    public SimilarGameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.game_card, parent, false);
        return new SimilarGameViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SimilarGameViewHolder holder, int position) {
        GameModel game = similarGames.get(position);
        holder.gameTitleTextView.setText(game.getName());
        holder.gameTitleTextView.setSelected(true );
        GameModel.Cover cover = game.getCover();
        if (cover != null && cover.getImageId() != null) {
            String imageUrl = "https://images.igdb.com/igdb/image/upload/t_1080p/" + cover.getImageId() + ".jpg";
            Glide.with(holder.itemView.getContext())
                    .load(imageUrl)
                    .into(holder.imageView);
        } else {
            holder.imageView.setImageResource(R.drawable.taj_mahal); // fallback image
        }
//        holder.itemView.setOnClickListener(v -> {
//            if (listener != null) {
//                listener.onGameClick(game);
//            }
//        });
        holder.itemView.setOnClickListener(v->{
            Bundle bundle = new Bundle();
            detailapi detailApi = new detailapi();
//                    detailApi.fetchGameDetails(game1.getId());
            bundle.putString( "id", game.getId());
            bundle.putInt( "position", position);
            NavController nav= Navigation.findNavController(view2);
            nav.navigate(R.id.action_game_detail_self,bundle);

        });

    }

    @Override
    public int getItemCount() {
        return similarGames != null ? similarGames.size() : 0;
    }

    public class SimilarGameViewHolder extends RecyclerView.ViewHolder {

        private TextView gameTitleTextView;
private ImageView imageView;
        public SimilarGameViewHolder(@NonNull View itemView) {
            super(itemView);
            gameTitleTextView = itemView.findViewById(R.id.text);

            imageView = itemView.findViewById(R.id.imageView);
        }


    }
}
