package app.moviles.kamachi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class loginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextPersonName;
    private EditText editTextPassword;
    private Button loginBtn;
    private Button registerBtn;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FirebaseUser fireUser = FirebaseAuth.getInstance().getCurrentUser();
        if(fireUser != null){
            Intent i = new Intent(this, MenuActivity.class);
            startActivity(i);
        }

        auth = FirebaseAuth.getInstance();

        editTextPersonName = findViewById(R.id.editTextPersonName);
        editTextPassword = findViewById(R.id.editTextPassword);
        loginBtn = findViewById(R.id.loginBtn);
        registerBtn = findViewById(R.id.registerBtn);
        loginBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.loginBtn:
                FirebaseAuth.getInstance().signInWithEmailAndPassword(
                        editTextPersonName.getText().toString(),
                        editTextPassword.getText().toString()
                ).addOnSuccessListener(
                        command -> {
                            Intent i = new Intent(this, MenuActivity.class);
                            startActivity(i);
                        }
                ).addOnFailureListener(
                        command -> {
                            Toast.makeText(this,command.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                );
                break;
            case R.id.registerBtn:
                Intent i = new Intent(this, activity_register.class);
                startActivity(i);
        }
    }
}