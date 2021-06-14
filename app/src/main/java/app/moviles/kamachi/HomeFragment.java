package app.moviles.kamachi;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import java.util.Date;
import java.util.zip.Inflater;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import app.moviles.kamachi.model.Event;
import app.moviles.kamachi.model.EventType;
import app.moviles.kamachi.model.Participant;
import app.moviles.kamachi.model.User;
import app.moviles.kamachi.model.UserType;
import app.moviles.kamachi.repository.UserRepositoryInterface;
import app.moviles.kamachi.repository.UserRepositoyImpl;

public class HomeFragment extends Fragment implements View.OnClickListener, EventAdapter.OnItemClick {

    private Button btnOptions;
    private TextView namePtxt;
    private TextView descriptiontxt;
    private ImageView imgProfile;
    private RecyclerView eventViewList;
    private LinearLayoutManager layoutManger;
    private EventAdapter adapter;

    private FirebaseAuth auth;
    private FirebaseStorage storage;
    private FirebaseFirestore db;

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

        auth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
        db = FirebaseFirestore.getInstance();

        prueba();

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        btnOptions = root.findViewById(R.id.btnOptions);
        namePtxt = root.findViewById(R.id.namePTxt);
        descriptiontxt = root.findViewById(R.id.descriptiontxt);
        imgProfile = root.findViewById(R.id.imgProfile);
        eventViewList = root.findViewById(R.id.eventViewList);

        btnOptions.setOnClickListener(this);

        layoutManger = new LinearLayoutManager(getContext());
        eventViewList.setLayoutManager(layoutManger);

        adapter = new EventAdapter();
        adapter.setListener(this);
        eventViewList.setAdapter(adapter);

        cargarEventos("*");

        eventViewList.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled( RecyclerView recyclerView, int dx, int dy) {
                if(!recyclerView.canScrollVertically(-1)&& dy<0){
                    Log.e(">>>", "TOP");
                }else if(!recyclerView.canScrollVertically(1)&& dy>0){
                    Log.e(">>>", "BOTTOM");
                    String ultimo = adapter.lastEvent();
                    cargarEventos(ultimo);

                }
            }
        });


        loadUserInfo();


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

    public void prueba(){
        System.out.println("entro a la prueba");
        User u = new User("asdasdasd", "Juanito", "vojabes2@gmail.com","123","123456", UserType.collaborator);
        Event e = new Event("Mental health", "prueba2 vojabes", EventType.MENTAL_HEALTH,"zoom://",1000,10);
        e.setEventOwnerId(u.getUserId());
        db.collection("users").document(u.getUserId()).set(u);
        db.collection("events").document(e.getIdEvent()).set(e);
    }

    public void loadUserInfo(){
        db.collection("users").document(FirebaseAuth.getInstance().getUid()).get()
                .addOnSuccessListener(
                        command -> {
                            User userFind = command.toObject(User.class);
                            namePtxt.setText(userFind.getUserName());
                            if(userFind.getDescripion() != null){
                                descriptiontxt.setText(userFind.getDescripion());
                            }
                            String path ="";
                            if(userFind.getProfilePic() == null){
                                path="fotoPerfil.jpg";
                                storage.getReference().child(path).getDownloadUrl()
                                        .addOnSuccessListener(
                                                command1 -> {
                                                    String url = command1.toString();
                                                    Glide.with(this).load(url).apply(RequestOptions.circleCropTransform()).into(imgProfile);
                                                }
                                        );
                            }else{
                                path =userFind.getProfilePic();
                                storage.getReference().child("postUser").child(path).getDownloadUrl()
                                        .addOnSuccessListener(
                                                command1 -> {
                                                    String url = command1.toString();
                                                    Glide.with(this).load(url).apply(RequestOptions.circleCropTransform()).into(imgProfile);
                                                }
                                        );
                            }

                        }
                );
    }

    public void cargarEventos(String start){
        db.collection("users").document(FirebaseAuth.getInstance().getUid()).get()
                .addOnSuccessListener(
                        command -> {
                            User u = command.toObject(User.class);
                            db.collection("events").get().addOnSuccessListener(
                                    command1 -> {
                                        for(DocumentSnapshot doc: command1.getDocuments()){
                                            Event event = doc.toObject(Event.class);
                                            db.collection("events").document(event.getIdEvent()).collection("eventParticipants").whereEqualTo("id",u.getUserId()).get()
                                                    .addOnSuccessListener(
                                                            command2 -> {
                                                                for(DocumentSnapshot doc1: command2.getDocuments()){
                                                                    Participant participant = doc1.toObject(Participant.class);
                                                                    if(participant != null){
                                                                        adapter.addEvent(event);
                                                                        System.out.println(adapter.getItemCount());
                                                                    }
                                                                }
                                                            }
                                                    ).addOnFailureListener(
                                                    command3 -> {
                                                        Toast.makeText(getContext(), "No tienes eventos registrados", Toast.LENGTH_SHORT).show();
                                                    }
                                            );
                                        }

                                    }
                            );
                        }
                );

    }

    @Override
    public void OnEventItemClick(Event e) {
        final CharSequence[] info = { "Regresar"};
       final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
       builder.setTitle("Informacion");
        db.collection("users").document(e.getEventOwnerId()).get()
                .addOnSuccessListener(
                    command -> {
                        User u = command.toObject(User.class);
                        builder.setMessage("Instructor: "+ u.getUserName()+"\n"
                                + "Nombre: "+e.getEventName()+"\n"
                                + "Descripcion: "+e.getDescription()+"\n"
                                + "Categoria: "+e.getType()+"\n"
                                + "Fecha: "+new Date(e.getDateEvent())+"\n"
                                + "Url: "+e.getUrl());
                        builder.show();
                    }
                );

    }
}