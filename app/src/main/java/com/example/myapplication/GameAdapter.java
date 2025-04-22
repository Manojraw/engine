package com.example.myapplication;

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

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.GameViewHolder> {
private View view;
    private List<GameModel> gameList;

    public GameAdapter(List<GameModel> gameList,View view) {
        this.gameList = gameList;
        this.view=view;
    }

    @NonNull
    @Override
    public GameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.game_card, parent, false);
        return new GameViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GameViewHolder holder, int position) {
        GameModel model = gameList.get(position);
        holder.textGameName.setText(model.getName());
        holder.textGameName.setSelected(true );
        if (model.getCover() != null) {


            // Load image
            if (model.getCover().getImageId() != null ) {
                String imageUrl = "https://images.igdb.com/igdb/image/upload/t_1080p/"
                        + model.getCover().getImageId() + ".jpg";

                Glide.with(holder.itemView.getContext())
                        .load(imageUrl)
                        .placeholder(R.drawable.taj_mahal) // Add a placeholder image in your drawable
                        .into(holder.imageCover);
            } else {
                holder.imageCover.setImageResource(R.drawable.taj_mahal); // No image case
            }
        }
        holder.itemView.setOnClickListener(V->
        {
            Bundle bundle = new Bundle();

            bundle.putString( "id", model.getId());

            NavController nav= Navigation.findNavController(view);
            nav.navigate(R.id.action_fragmentsearch_to_game_detail,bundle);
        });
    }

    @Override
    public int getItemCount() {
        return gameList != null ? gameList.size() : 0;
    }

    static class GameViewHolder extends RecyclerView.ViewHolder {
        ImageView imageCover;
        TextView textGameName;

        public GameViewHolder(@NonNull View itemView) {
            super(itemView);
            imageCover = itemView.findViewById(R.id.imageView);
            textGameName = itemView.findViewById(R.id.text);

        }
    }
}
