package app.moviles.kamachi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class activity_editprofile extends AppCompatActivity implements View.OnClickListener{

    private TextView editNameTxt;
    private EditText editDescriptionTxt;
    private Button saveChangesBtn;
    private Button imgButton;
    private Button optionsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editprofile);

        imgButton = findViewById(R.id.imgButton);
        editNameTxt = findViewById(R.id.editNameTxt);
        editDescriptionTxt = findViewById(R.id.editDescriptionTxt);
        saveChangesBtn = findViewById(R.id.saveChangesBtn);
        optionsBtn = findViewById(R.id.optionsBtn);

        imgButton.setOnClickListener(this);
        saveChangesBtn.setOnClickListener(this);
        optionsBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgButton:
                break;
            case R.id.saveChangesBtn:
                break;
            case R.id.optionsBtn:
                Intent i = new Intent(this, activity_options.class);
                startActivity(i);
                break;
        }
    }
}