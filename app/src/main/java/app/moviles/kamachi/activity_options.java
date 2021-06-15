package app.moviles.kamachi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class activity_options extends AppCompatActivity implements View.OnClickListener {

    private Button buttonReturn;
    private Button homeBtn;
    private Button eventBtn;
    private Button editProfileBtn;
    private Button paymentBtn;
    private Button closeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        getSupportActionBar().hide();
        buttonReturn = findViewById(R.id.buttonReturn);
        homeBtn = findViewById(R.id.homeBtn);
        eventBtn = findViewById(R.id.eventBtn);
        paymentBtn = findViewById(R.id.paymentBtn);
        editProfileBtn = findViewById(R.id.editProfileBtn);
        closeBtn = findViewById(R.id.closeBtn);

        buttonReturn.setOnClickListener(this);
        homeBtn.setOnClickListener(this);
        eventBtn.setOnClickListener(this);
        paymentBtn.setOnClickListener(this);
        editProfileBtn.setOnClickListener(this);
        closeBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonReturn:
                Intent intent2 = new Intent(this, MenuActivity.class);
                startActivity(intent2);
                break;
            case R.id.homeBtn:
                Intent intent3 = new Intent(this, MenuActivity.class);
                startActivity(intent3);
                break;
            case R.id.eventBtn:
                Intent intent4 = new Intent(this, MenuActivity.class);
                intent4.putExtra("showEvent", "2");
                startActivity(intent4);
                break;
            case R.id.editProfileBtn:
                Intent i = new Intent(this, activity_editprofile.class);
                startActivity(i);
                break;
            case R.id.paymentBtn:
                Intent intent = new Intent(this, activity_paymethod.class);
                startActivity(intent);
                break;
            case R.id.closeBtn:
                FirebaseAuth.getInstance().signOut();
                Intent intent1 = new Intent(this, loginActivity.class);
                startActivity(intent1);
                break;
        }
    }


}