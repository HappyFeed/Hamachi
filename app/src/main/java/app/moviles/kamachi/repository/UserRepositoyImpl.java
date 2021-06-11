package app.moviles.kamachi.repository;

import android.util.Log;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import app.moviles.kamachi.actions.Actions;
import app.moviles.kamachi.model.Event;
import app.moviles.kamachi.model.User;

public class UserRepositoyImpl implements UserRepositoryInterface{

    FirebaseFirestore db;
    FirebaseStorage storage;
    private ArrayList<User> users;
    private User u;


    public UserRepositoyImpl() {
        this.db = FirebaseFirestore.getInstance();
        this.users = new ArrayList<User>();
        this.storage = FirebaseStorage.getInstance();
    }

    @Override
    public void save(User user) {
        db.collection("users").document(user.getUserId()).set(user)
        .addOnSuccessListener(
                command -> {

                }
        );
    }

    @Override
    public void edit(User user) {
        db.collection("users").document(user.getUserId()).set(user);
    }



    @Override
    public void findById(Actions.OnFindById onFindById, String id) {
        db.collection("users").document(id).get()
                .addOnSuccessListener(
                        command -> {
                            User use = command.toObject(User.class);
                            onFindById.onFindById(use);
                        }
                );
    }

    @Override
    public void delete(String id) {
        db.collection("users").document(id).delete();
    }



    public void findAll(Actions.OnUserList onResponse) {

        db.collection("users").limit(10).get().addOnSuccessListener(
                command -> {
                    for(DocumentSnapshot doc: command.getDocuments()){
                        User u = doc.toObject(User.class);
                        users.add(u);
                        onResponse.onFindAll(users);
                    }
                }
        );
    }

    public ArrayList<User> getUsers(){
        return users;
    }

    @Override
    public void addUserProfilePic(User user) throws FileNotFoundException {
        String path = user.getProfilePic();
        FileInputStream fis = null;
        fis= new FileInputStream(new File(path));
        storage.getReference().child("postUser").child(path).putStream(fis).addOnSuccessListener(
                runnable -> {
                    edit(user);
                }
        ).addOnFailureListener(
                command -> {
                    Log.e(">>>","no se pudo guardar la imagen");
                }
        );
    }

   /*
    public void example1(){
        findById(
                userFind -> {
                    u = ajam(userFind);
                }, "adsad"
        );
        Log.e(">>", "");
    }*/

}
