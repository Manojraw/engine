package com.example.myapplication;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
//import androidx.navigation.NavController;
//import androidx.navigation.Navigation;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class aduptor extends RecyclerView.Adapter<aduptor.ViewHolder> {

//
public interface OnChildItemClickListener {
    void onChildItemClick(Attraction model);
}
    private OnChildItemClickListener listener;
        private Context context;
        private List<Attraction> gameCategories;
        private List<GameModel> game;
        private  View view2;
    private Context fragmentManager;
    NavController navController ;
        public aduptor(Context context, List<Attraction> gameCategories , List<GameModel> game, View view) {
            this.context = context;
            this.gameCategories = gameCategories;
            this.game=game;
            this.fragmentManager = fragmentManager;
            view2=view;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.game_card, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
  Log.d("GAME_LIST", "Game: ID = " +game.size() );
            GameModel game1 = game.get(position);
//            Attraction item = gameCategories.get(position);
            holder.titleTextView.setText(game1.getName());
            holder.titleTextView.setSelected(true );
//            holder.titleTextView.setText(game.get(position).getName());
            GameModel.Cover cover = game1.getCover();
            if (cover != null && cover.getImageId() != null) {
                String imageUrl = "https://images.igdb.com/igdb/image/upload/t_1080p/" + cover.getImageId() + ".jpg";
                Glide.with(holder.itemView.getContext())
                        .load(imageUrl)
                        .into(holder.imageView);
            } else {
                holder.imageView.setImageResource(R.drawable.taj_mahal); // fallback image
            }
 holder.itemView.setOnClickListener(v->{
     Bundle bundle = new Bundle();
     detailapi detailApi = new detailapi();
//                    detailApi.fetchGameDetails(game1.getId());
     bundle.putString( "id", game1.getId());
     bundle.putInt( "position", position);
     NavController nav= Navigation.findNavController(view2);
     nav.navigate(R.id.action_mainfragment_to_game_detail,bundle);

 });
    }



    @Override
        public int getItemCount() {

            return game.size();




        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            TextView titleTextView;
            ImageView imageView;


            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                 // Make sure ID matches fragment_game_card.xml
                titleTextView=itemView.findViewById(R.id.text);
                imageView = itemView.findViewById(R.id.imageView);        // Make sure ID matches fragment_game_card.xml
            }
        }
    }
