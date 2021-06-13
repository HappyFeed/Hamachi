package app.moviles.kamachi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.time.Month;
import java.util.Date;

import app.moviles.kamachi.model.Event;
import app.moviles.kamachi.model.EventType;
import app.moviles.kamachi.model.User;
import app.moviles.kamachi.model.UserType;
import app.moviles.kamachi.repository.EventRepositoryImpl;
import app.moviles.kamachi.repository.EventRepositoryInterface;
import app.moviles.kamachi.repository.UserRepositoryInterface;
import app.moviles.kamachi.repository.UserRepositoyImpl;

public class MainActivity extends AppCompatActivity {

    private UserRepositoryInterface uri;
    private EventRepositoryInterface eri;

    public MainActivity(){
        uri = new UserRepositoyImpl();
        eri = new EventRepositoryImpl();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


}