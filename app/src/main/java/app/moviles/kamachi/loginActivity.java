package app.moviles.kamachi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import app.moviles.kamachi.model.Event;
import app.moviles.kamachi.model.EventType;
import app.moviles.kamachi.model.Participant;
import app.moviles.kamachi.model.User;
import app.moviles.kamachi.model.UserType;

public class loginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextPersonName;
    private EditText editTextPassword;
    private Button loginBtn;
    private Button registerBtn;

    private FirebaseAuth auth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
        }, 1);

        FirebaseUser fireUser = FirebaseAuth.getInstance().getCurrentUser();
        if(fireUser != null){
            Intent i = new Intent(this, MenuActivity.class);
            i.putExtra("showEvent", "1");

            startActivity(i);
        }


        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();


        editTextPersonName = findViewById(R.id.editTextPersonName);
        editTextPassword = findViewById(R.id.editTextPassword);
        loginBtn = findViewById(R.id.loginBtn);
        registerBtn = findViewById(R.id.registerBtn);
        loginBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        try {


            switch (v.getId()) {

                case R.id.loginBtn:
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(
                            editTextPersonName.getText().toString(),
                            editTextPassword.getText().toString()
                    ).addOnSuccessListener(
                            command -> {
                                Intent i = new Intent(this, MenuActivity.class);
                                i.putExtra("showEvent", "1");
                                startActivity(i);
                            }
                    ).addOnFailureListener(
                            command -> {
                                Toast.makeText(this, command.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                            }
                    );
                    break;
                case R.id.registerBtn:
                    Intent i = new Intent(this, activity_register.class);
                    startActivity(i);


            }
        }catch (Exception e){
            Log.e(">>>", "empty");
        }
    }


}