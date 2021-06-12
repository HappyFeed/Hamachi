package app.moviles.kamachi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class activity_paymethod extends AppCompatActivity implements View.OnClickListener {

    private Button payBtn;
    private Button returnButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paymethod);

        returnButton = findViewById(R.id.returnButton);
        payBtn = findViewById(R.id.payBtn);

        payBtn.setOnClickListener(this);
        returnButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.payBtn:

                break;
            case R.id.returnButton:
                Intent i = new Intent(this, activity_inscription.class);
                startActivity(i);

                break;
        }
    }
}