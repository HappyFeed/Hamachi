package app.moviles.kamachi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class activity_inscription extends AppCompatActivity implements View.OnClickListener{

    private Button btnPay;
    private Button btnReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        btnPay = findViewById(R.id.btnPay);
        btnReturn = findViewById(R.id.btnReturn);

        btnReturn.setOnClickListener(this);
        btnPay.setOnClickListener(this);
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
                    startActivity(intent);

                    break;

            }
    }

}