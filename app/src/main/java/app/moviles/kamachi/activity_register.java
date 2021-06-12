package app.moviles.kamachi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import app.moviles.kamachi.model.User;
import app.moviles.kamachi.model.UserType;

public class activity_register extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextUserName;
    private EditText editTextEmail;
    private EditText editTextTelephone;
    private EditText editTextPasswordU;
    private EditText editTextPasswordC;
    private Button registrarBtn;

    private FirebaseFirestore db;
    private FirebaseStorage storage;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextUserName = findViewById(R.id.editTextUserName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextTelephone = findViewById(R.id.editTextTelephone);
        editTextPasswordU = findViewById(R.id.editTextPasswordU);
        editTextPasswordC = findViewById(R.id.editTextPasswordC);
        registrarBtn = findViewById(R.id.registrarBtn);

        registrarBtn.setOnClickListener(this);
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.registrarBtn:
                auth.createUserWithEmailAndPassword(
                        editTextEmail.getText().toString(),
                        editTextPasswordU.getText().toString()
                ).addOnSuccessListener(
                        command -> {
                            User u = new User(
                                    editTextUserName.getText().toString(),
                                    editTextEmail.getText().toString(),
                                    editTextTelephone.getText().toString(),
                                    editTextPasswordU.getText().toString(),
                                    UserType.client);

                            db.collection("users").document(u.getUserId()).set(u)
                                    .addOnSuccessListener(
                                            dbaction -> {
                                                Intent i = new Intent(this, MenuActivity.class);
                                                startActivity(i);
                                            }
                                    );
                        }
                ).addOnFailureListener(
                        command -> {
                            Toast.makeText(this,command.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            Log.e(">>>", command.getLocalizedMessage());
                        }
                );
        }
    }
}