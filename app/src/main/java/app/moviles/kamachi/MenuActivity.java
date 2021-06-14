package app.moviles.kamachi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MenuActivity extends AppCompatActivity {

    private FragmentUpcomingEvent fragmentEvent;
    private HomeFragment homeFragment;
    private BottomNavigationView navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        fragmentEvent = fragmentEvent.newInstance();
        homeFragment = homeFragment.newInstance();
        navigator = findViewById(R.id.navigator);

        String show = getIntent().getStringExtra("showEvent");
        System.out.println(show);
        if(show.equals("2")){
            showFragment(fragmentEvent);
        }else{
            showFragment(homeFragment);
        }

        navigator.setOnNavigationItemSelectedListener(
                (menuItem)->{

                    switch (menuItem.getItemId()){
                        case R.id.profile:
                            showFragment(homeFragment);
                            break;

                        case R.id.listSearch:
                            showFragment(fragmentEvent);
                            break;
                    }
                  return true;
                }
        );
    }

    public void showFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragmentContainer, fragment);
        transaction.commit();
    }
}