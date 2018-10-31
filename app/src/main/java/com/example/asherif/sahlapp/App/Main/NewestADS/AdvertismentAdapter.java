package com.example.asherif.sahlapp.App.Main.NewestADS;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.asherif.sahlapp.R;

import java.util.List;

public class AdvertismentAdapter extends RecyclerView.Adapter<AdvertismentAdapter.MyViewHolder> {
    private List<Advertisment> advertismentList;
    OnBottomReachedListener onBottomReachedListener;


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, year, genre;

        public MyViewHolder(View view) {
            super(view);
            title =  view.findViewById(R.id.title);
            genre = view.findViewById(R.id.genre);
            year = view.findViewById(R.id.year);

        }

    }
    public AdvertismentAdapter(List<Advertisment>  advertismentList){
        this.advertismentList=advertismentList;

    }
    public void setOnBottomReachedListener(OnBottomReachedListener onBottomReachedListener){

        this.onBottomReachedListener = onBottomReachedListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.advertisment_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Advertisment advertisment=advertismentList.get(position);
        Log.i("TAG", "onBindViewHolder: "+advertismentList.get(position));
        holder.title.setText(advertisment.getTitle());
        holder.genre.setText(advertisment.getGenre());
        holder.year.setText(advertisment.getYear());
        if (position == advertismentList.size() - 1){

            onBottomReachedListener.onBottomReached(position);

        }



    }

    @Override
    public int getItemCount() {
        return advertismentList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
