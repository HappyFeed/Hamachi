package app.moviles.kamachi;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.DialogFragment;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import app.moviles.kamachi.model.User;
import app.moviles.kamachi.model.UtilDomi;

public class activity_editprofile extends AppCompatActivity implements View.OnClickListener{

    public static final int GALLERY_CALLBACK = 1;

    private String path;

    private TextView editNameTxt;
    private EditText editDescriptionTxt;
    private Button saveChangesBtn;
    private Button imgButton;
    private Button optionsBtn;
    private ImageView imgEditProfile;

    private FirebaseAuth auth;
    private FirebaseFirestore db;
    private FirebaseStorage storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();
        setContentView(R.layout.activity_editprofile);

        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();

        imgEditProfile= findViewById(R.id.imgEditProfile);
        imgButton = findViewById(R.id.imgButton);
        editNameTxt = findViewById(R.id.editNameTxt);
        editDescriptionTxt = findViewById(R.id.editDescriptionTxt);
        saveChangesBtn = findViewById(R.id.saveChangesBtn);
        optionsBtn = findViewById(R.id.optionsBtn);

        cargarDatos();

        imgButton.setOnClickListener(this);
        saveChangesBtn.setOnClickListener(this);
        optionsBtn.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgButton:
                Intent i = new Intent(Intent.ACTION_GET_CONTENT);
                i.setType("image/*");
                startActivityForResult(i, GALLERY_CALLBACK);
                break;
            case R.id.saveChangesBtn:
                edit();
                break;
            case R.id.optionsBtn:
                Intent i2 = new Intent(this, activity_options.class);
                startActivity(i2);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GALLERY_CALLBACK && resultCode == Activity.RESULT_OK){
            System.out.println("entro");
            Uri uri = data.getData();
            String path = UtilDomi.getPath(this, uri);
            if(path != null){
                FileInputStream fis = null;
                try {
                    fis= new FileInputStream(new File(path));
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                storage.getReference().child("postUser").child(path).putStream(fis)
                        .addOnFailureListener(
                                command2 -> {
                                    Log.e(">>>","no se pudo guardar la imagen");
                                }
                        );
                db.collection("users").document(FirebaseAuth.getInstance().getUid()).get()
                        .addOnSuccessListener(
                                command -> {
                                    User userFind = command.toObject(User.class);
                                    userFind.setProfilePic(path);
                                    db.collection("users").document(FirebaseAuth.getInstance().getUid()).set(userFind)
                                            .addOnSuccessListener(
                                                    command1 -> {
                                                        Glide.with(this).load(path).apply(RequestOptions.circleCropTransform()).into(imgEditProfile);
                                                        Toast.makeText(this, "profile image changed", Toast.LENGTH_SHORT).show();
                                                    }
                                            );
                                }
                        );
            }

        }
    }

    public void edit(){
        String newName = editNameTxt.getText().toString();
        String newDescription = editDescriptionTxt.getText().toString();
        db.collection("users").document(FirebaseAuth.getInstance().getUid()).get()
                .addOnSuccessListener(
                        command1 -> {
                            User user = command1.toObject(User.class);
                            boolean flag = false;
                            if(newName !=null || !newName.isEmpty()){
                                System.out.println(newName);
                                user.setUserName(newName);
                                flag = true;
                            }
                            if(newDescription !=null || !newDescription.isEmpty()){
                                user.setDescripion(newDescription);
                                flag = true;
                            }
                            if(flag == true){
                                db.collection("users").document(FirebaseAuth.getInstance().getUid()).set(user)
                                        .addOnSuccessListener(
                                                command2 -> {

                                                    Toast.makeText(this, "profile edited", Toast.LENGTH_SHORT).show();
                                                }
                                        );
                            }
                        }
                );
    }

    public void cargarDatos(){
        db.collection("users").document(FirebaseAuth.getInstance().getUid()).get()
                .addOnSuccessListener(
                        command -> {
                            User userFind = command.toObject(User.class);
                            editNameTxt.setText(userFind.getUserName());
                            if(userFind.getDescripion() != null){
                                editDescriptionTxt.setText(userFind.getDescripion());
                            }
                            String path ="";
                            if(userFind.getProfilePic() == null){
                                path="fotoPerfil.jpg";
                                storage.getReference().child(path).getDownloadUrl()
                                        .addOnSuccessListener(
                                                command1 -> {
                                                    String url = command1.toString();
                                                    Glide.with(this).load(url).apply(RequestOptions.circleCropTransform()).into(imgEditProfile);
                                                }
                                        );
                            }else{
                                path =userFind.getProfilePic();
                                storage.getReference().child("postUser").child(path).getDownloadUrl()
                                        .addOnSuccessListener(
                                                command1 -> {
                                                    String url = command1.toString();
                                                    Glide.with(this).load(url).apply(RequestOptions.circleCropTransform()).into(imgEditProfile);
                                                }
                                        );
                            }

                        }
                );
    }
}