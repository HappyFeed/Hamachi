package app.moviles.kamachi.repository;

import android.util.Log;
import android.widget.Toast;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import app.moviles.kamachi.actions.Actions;
import app.moviles.kamachi.model.User;

public class UserRepositoyImpl implements UserRepositoryInterface{

    FirebaseFirestore db;
    private ArrayList<User> users;
    private User u;


    public UserRepositoyImpl() {
        this.db = FirebaseFirestore.getInstance();
        this.users = new ArrayList<User>();
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
    public User findById(String id) {
        db.collection("users").document(id).get()
                .addOnSuccessListener(
                        command -> {
                            u = command.toObject(User.class);
                        }
                );
        return u;
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

    public void example(){
        findAll(
                userList -> {

                }
        );
        Log.e(">>", "");
    }


}
