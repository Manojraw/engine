package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class GameDetailAdapter(private val context: Context?, private val gameList: List<String?>) :
    RecyclerView.Adapter<GameDetailAdapter.GameViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.itam_id, parent, false)
        return GameViewHolder(view)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val game = gameList[position]

        Log.d("vgjgh", "onResponse: $game")

        //        String imageUrl = "https://images.igdb.com/igdb/image/upload/t_1080p/" + imageId + ".jpg";

        // Load image using Glide
        val imageUrl = "https://images.igdb.com/igdb/image/upload/t_1080p/$game.jpg"
        Glide.with(context!!)
            .load(imageUrl)
            .placeholder(R.drawable.taj_mahal) // Jab tak image load ho rahi hai
            .error(R.drawable.ic_action_calendar_today) // Agar image nahi mili
            .into(holder.nameiew)
    }

    override fun getItemCount(): Int {
        return gameList.size
    }

    class GameViewHolder @SuppressLint("WrongViewCast") constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var nameiew: ImageView =
            itemView.findViewById(R.id.imageView5)
    }
}
