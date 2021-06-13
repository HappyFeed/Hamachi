package app.moviles.kamachi;

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

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import java.util.zip.Inflater;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import app.moviles.kamachi.model.User;
import app.moviles.kamachi.repository.UserRepositoryInterface;
import app.moviles.kamachi.repository.UserRepositoyImpl;

public class HomeFragment extends Fragment implements View.OnClickListener{

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

    private UserRepositoryInterface uri;

    public HomeFragment() {
        uri = new UserRepositoyImpl();
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

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        btnOptions = root.findViewById(R.id.btnOptions);
        namePtxt = root.findViewById(R.id.namePTxt);
        descriptiontxt = root.findViewById(R.id.descriptionText);
        imgProfile = root.findViewById(R.id.imgProfile);
        eventViewList = root.findViewById(R.id.eventViewList);

        btnOptions.setOnClickListener(this);

        layoutManger = new LinearLayoutManager(getContext());
        eventViewList.setLayoutManager(layoutManger);

        adapter = new EventAdapter();
        eventViewList.setAdapter(adapter);
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
                                                    Glide.with(this).load(url).centerCrop().into(imgProfile);
                                                }
                                        );
                            }else{
                                path =userFind.getProfilePic();
                                storage.getReference().child("postUser").child(path).getDownloadUrl()
                                        .addOnSuccessListener(
                                                command1 -> {
                                                    String url = command1.toString();
                                                    Glide.with(this).load(url).centerCrop().into(imgProfile);
                                                }
                                        );
                            }

                        }
                );
    }
}