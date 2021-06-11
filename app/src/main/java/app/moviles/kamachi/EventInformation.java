package app.moviles.kamachi;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.firestore.FirebaseFirestore;

import app.moviles.kamachi.comunication.ServiceEventInfo;

public class EventInformation extends Fragment implements ServiceEventInfo {

    private FirebaseFirestore db;

    public EventInformation() {
    }

    // TODO: Rename and change types and number of parameters
    public static EventInformation newInstance() {
        EventInformation fragment = new EventInformation();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event_information, container, false);
    }

    @Override
    public void enviarDatos(String eventId, String ownerId) {

    }
}