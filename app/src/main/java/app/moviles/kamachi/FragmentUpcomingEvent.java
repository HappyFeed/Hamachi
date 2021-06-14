package app.moviles.kamachi;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import java.util.Date;

import app.moviles.kamachi.model.Event;
import app.moviles.kamachi.model.User;


public class FragmentUpcomingEvent extends Fragment implements EventUpcomingAdapter.OnRegistClick, SearchView.OnQueryTextListener {

    private RecyclerView listView;
    private SearchView searchView;
    private LinearLayoutManager layoutManger;
    private EventUpcomingAdapter adapter;

    private FirebaseAuth auth;
    private FirebaseStorage storage;
    private FirebaseFirestore db;

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
        searchView = root.findViewById(R.id.searchView);

        auth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
        db = FirebaseFirestore.getInstance();


        layoutManger = new LinearLayoutManager(getContext());
        listView.setLayoutManager(layoutManger);

        adapter = new EventUpcomingAdapter();
        adapter.setListener(this);
        listView.setAdapter(adapter);

        searchView.setOnQueryTextListener(this);

        cargarEventos("*");

        listView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled( RecyclerView recyclerView, int dx, int dy) {
                if(!recyclerView.canScrollVertically(-1)&& dy<0){
                    Log.e(">>>", "TOP");
                }else if(!recyclerView.canScrollVertically(1)&& dy>0){
                    Log.e(">>>", "BOTTOM");
                    String ultimo = adapter.lastOne();
                    cargarEventos(ultimo);

                }
            }
        });

        return root;
    }




    public void cargarEventos(String start){
        db.collection("events").orderBy("eventName").limit(2).startAfter(start).get()
                .addOnSuccessListener(
                        command -> {
                            for(DocumentSnapshot doc2: command.getDocuments()) {
                                Event event2 = doc2.toObject(Event.class);
                                adapter.addEvent(event2);

                            }
                        }
                ).addOnFailureListener(
                command3 -> {
                    Toast.makeText(getContext(), "No tienes eventos CERCA", Toast.LENGTH_SHORT).show();
                }
        );
    }

    @Override
    public void OnEventRegisItemClick(Event e) {
        Intent i = new Intent(getActivity(), activity_inscription.class);
        i.putExtra("event", e);
        startActivity(i);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.filter(newText);
        return false;
    }

}

