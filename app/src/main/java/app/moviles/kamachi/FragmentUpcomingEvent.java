package app.moviles.kamachi;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class FragmentUpcomingEvent extends Fragment implements View.OnClickListener {

    private RecyclerView listView;
    private LinearLayoutManager layoutManger;
    private EventUpcomingAdapter adapter;

    public FragmentUpcomingEvent() {
        // Required empty public constructor
    }

    public static FragmentUpcomingEvent newInstance() {
        FragmentUpcomingEvent fragment = new FragmentUpcomingEvent();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_upcoming_event, container, false);
        listView = root.findViewById(R.id.listView);

        layoutManger = new LinearLayoutManager(getContext());
        listView.setLayoutManager(layoutManger);

        adapter = new EventUpcomingAdapter();
        listView.setAdapter(adapter);


        return root;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }

    }
}

