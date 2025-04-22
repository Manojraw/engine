package com.example.myapplication;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TopAdapter extends RecyclerView.Adapter<TopAdapter.TopViewHolder> {

    private List<raju> topItemList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(raju item);
    }

    public TopAdapter(List<raju> topItemList, OnItemClickListener listener) {
        this.topItemList = topItemList;
        this.listener = listener;
    }

    @Override
    public TopViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event, parent, false);
        return new TopViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TopViewHolder holder, int position) {
        holder.bind(topItemList.get(position), listener);

        String game = topItemList.get(position).getEvent_logo().getImage_id();

        String imageUrl = "https://images.igdb.com/igdb/image/upload/t_1080p/" +game + ".jpg";
        Glide.with(holder.itemView.getContext())
                .load(imageUrl)
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        Log.d("dsdfsdfd", "getItemCount: "+topItemList.size());
        return topItemList.size();


    }

    public static class TopViewHolder extends RecyclerView.ViewHolder {
        TextView textView,textView2 ,textve;
        ImageView imageView;
        public TopViewHolder(View itemView) {
            super(itemView);
            textve=itemView.findViewById(R.id.eventStartTimeTextVie);
            textView = itemView.findViewById(R.id.eventStartTimeTextView);
            textView2=itemView.findViewById(R.id.eventNameTextView);
            imageView=itemView.findViewById(R.id.eventLogoImageView);

        }

        public void bind(final raju item, final OnItemClickListener listener) {
            textView2.setText(item.getName());

           long time= item.getStart_time();
            if (time < 1000000000000L) {
                time *= 1000;
            }
            long now = System.currentTimeMillis();
            if (time > now) {
                Date date = new Date(time);
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy", Locale.getDefault());
                textView.setText(sdf.format(date));
                textve.setText("Upcoming");
            } else {
                Date date = new Date(time);
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy", Locale.getDefault());
                textView.setText(sdf.format(date));

            }

            textView.setSelected(true );
           textView2.setSelected(true );
            itemView.setOnClickListener(v -> listener.onItemClick(item)
            );
            Log.d("ghjjjjjj", "bind: "+item.getId()+item.getName());

        }



    }
}
