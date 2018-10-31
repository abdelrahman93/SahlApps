package com.example.asherif.sahlapp.Region.Main.NewestADS;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.asherif.sahlapp.R;

import java.util.ArrayList;
import java.util.List;


public class Fragment_NewestADS extends Fragment {
    private List<Advertisment> advertismentList = new ArrayList<>();
    private RecyclerView recyclerView;
    private AdvertismentAdapter mAdapter;
    private View view;
    public Fragment_NewestADS(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //comment
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_newest_ad, container, false);
        recyclerView =  view.findViewById(R.id.recycler_view);
        final FragmentActivity c = getActivity();
        Context context = view.getContext();
        mAdapter = new AdvertismentAdapter(advertismentList);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(context);

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(view.getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnBottomReachedListener(new OnBottomReachedListener() {
            @Override
            public void onBottomReached(int position) {
                //your code goes here
            }
        });
        // row click listener
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Advertisment movie = advertismentList.get(position);
                Toast.makeText(getActivity(), movie.getTitle() + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
        prepareMovieData();

        return view;
    }
    private void prepareMovieData() {
        Advertisment advertisment = new Advertisment("Mad Max: Fury Road", "Action & Adventure", "2015");
        advertismentList.add(advertisment);

        advertisment = new Advertisment("Inside Out", "Animation, Kids & Family", "2015");
        advertismentList.add(advertisment);

        advertisment = new Advertisment("Star Wars: Episode VII - The Force Awakens", "Action", "2015");
        advertismentList.add(advertisment);

        advertisment = new Advertisment("Shaun the Sheep", "Animation", "2015");
        advertismentList.add(advertisment);

        advertisment = new Advertisment("The Martian", "Science Fiction & Fantasy", "2015");
        advertismentList.add(advertisment);

        advertisment = new Advertisment("Mission: Impossible Rogue Nation", "Action", "2015");
        advertismentList.add(advertisment);

        advertisment = new Advertisment("Up", "Animation", "2009");
        advertismentList.add(advertisment);

        advertisment = new Advertisment("Star Trek", "Science Fiction", "2009");
        advertismentList.add(advertisment);

        advertisment = new Advertisment("The LEGO Movie", "Animation", "2014");
        advertismentList.add(advertisment);

        advertisment = new Advertisment("Iron Man", "Action & Adventure", "2008");
        advertismentList.add(advertisment);

        advertisment = new Advertisment("Aliens", "Science Fiction", "1986");
        advertismentList.add(advertisment);

        advertisment = new Advertisment("Chicken Run", "Animation", "2000");
        advertismentList.add(advertisment);

        advertisment = new Advertisment("Back to the Future", "Science Fiction", "1985");
        advertismentList.add(advertisment);

        advertisment = new Advertisment("Raiders of the Lost Ark", "Action & Adventure", "1981");
        advertismentList.add(advertisment);

        advertisment = new Advertisment("Goldfinger", "Action & Adventure", "1965");
        advertismentList.add(advertisment);

        advertisment = new Advertisment("Guardians of the Galaxy", "Science Fiction & Fantasy", "2014");
        advertismentList.add(advertisment);

        mAdapter.notifyDataSetChanged();
    }

}
