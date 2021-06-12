package app.moviles.kamachi;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.zip.Inflater;

public class HomeFragment extends Fragment implements View.OnClickListener{
    private Button btnOptions;
    private TextView namePtxt;
    private TextView descriptiontxt;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        btnOptions = root.findViewById(R.id.btnOptions);
        namePtxt = root.findViewById(R.id.namePTxt);
        descriptiontxt = root.findViewById(R.id.descriptionText);

        btnOptions.setOnClickListener(this);
        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnOptions:
                Intent i = new Intent(getContext(), activity_options.class);
                startActivity(i);
                break;
        }
    }
}