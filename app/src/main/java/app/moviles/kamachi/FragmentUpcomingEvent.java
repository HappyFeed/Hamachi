package app.moviles.kamachi;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class FragmentUpcomingEvent extends Fragment implements View.OnClickListener {

    private Button btnInscrition;

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
        btnInscrition = root.findViewById(R.id.btnInscrition);
        btnInscrition.setOnClickListener(this);
        return root;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnInscrition:
                Intent i = new Intent(getContext(), activity_inscription.class);
                startActivity(i);
                break;
        }

    }
}

