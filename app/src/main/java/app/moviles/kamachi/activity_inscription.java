package app.moviles.kamachi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import java.util.Date;

import app.moviles.kamachi.model.Event;
import app.moviles.kamachi.model.User;

public class activity_inscription extends AppCompatActivity implements View.OnClickListener{

    private Button btnPay;
    private Button btnReturn;
    private ImageView picColabIv;
    private TextView nameColabTv;
    private TextView nameEventTv;
    private TextView eventDesTv;
    private TextView eventDateTv;
    private TextView urlTv;
    private TextView eventTypeTv;
    private TextView eventPriceTv;

    private FirebaseAuth auth;
    private FirebaseFirestore db;
    private FirebaseStorage storage;

    private Event e;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        getSupportActionBar().hide();
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();

        btnPay = findViewById(R.id.btnPay);
        btnReturn = findViewById(R.id.btnReturn);
        picColabIv = findViewById(R.id.picColabIv);
        nameColabTv = findViewById(R.id.nameColabTv);
        nameEventTv = findViewById(R.id.nameEventTv);
        eventDesTv = findViewById(R.id.eventDesTv);
        eventDateTv = findViewById(R.id.eventDateTv);
        urlTv = findViewById(R.id.urlTv);
        eventTypeTv = findViewById(R.id.eventTypeTv);
        eventPriceTv = findViewById(R.id.eventPriceTv);
        btnReturn.setOnClickListener(this);
        btnPay.setOnClickListener(this);

        e = (Event) getIntent().getSerializableExtra("event");

        chargeEventData();
    }

    @Override
    public void onClick(View v) {
            switch (v.getId()){
                case R.id.btnPay:
                    Intent i = new Intent(this, activity_paymethod.class);
                    startActivity(i);
                    break;
                case R.id.btnReturn:
                    Intent intent = new Intent(this, MenuActivity.class);
                    intent.putExtra("showEvent","2");
                    startActivity(intent);

                    break;

            }
    }

    public void chargeEventData(){
        db.collection("users").document(e.getEventOwnerId()).get()
                .addOnSuccessListener(
                        command -> {
                            User u = command.toObject(User.class);
                            nameColabTv.setText(u.getUserName());
                            String pathP;
                            if(u.getProfilePic() == null){
                                pathP="fotoPerfil.jpg";
                                storage.getReference().child(pathP).getDownloadUrl()
                                        .addOnSuccessListener(
                                                command1 -> {
                                                    String url = command1.toString();
                                                    Glide.with(this).load(url).apply(RequestOptions.circleCropTransform()).into(picColabIv);
                                                }
                                        );
                            }else{
                                pathP =u.getProfilePic();
                                storage.getReference().child("postUser").child(pathP).getDownloadUrl()
                                        .addOnSuccessListener(
                                                command1 -> {
                                                    String url = command1.toString();
                                                    Glide.with(this).load(url).apply(RequestOptions.circleCropTransform()).into(picColabIv);
                                                }
                                        );
                            }

                        }
                );

        nameEventTv.setText("Event name: "+e.getEventName());
        eventDesTv.setText("Description: "+e.getDescription());
        eventTypeTv.setText("Category: "+ e.getType());
        urlTv.setText("Place: zoom");
        eventDateTv.setText("Date: "+new Date(e.getDateEvent()));
        eventPriceTv.setText("Price: "+ e.getPrice());

    }

}