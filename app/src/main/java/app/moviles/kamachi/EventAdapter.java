package app.moviles.kamachi;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.UUID;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import app.moviles.kamachi.model.Event;
import app.moviles.kamachi.model.EventView;
import app.moviles.kamachi.model.User;

public class EventAdapter extends RecyclerView.Adapter<EventView> {
    private ArrayList<Event> events;

    private FirebaseAuth auth;
    private FirebaseFirestore db;
    private FirebaseStorage storage;
    private View row;

    public EventAdapter(){
        events = new ArrayList<>();
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();
    }

    @NonNull
    @Override
    public EventView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        row = inflater.inflate(R.layout.eventrow, null);
        ConstraintLayout rowroot = (ConstraintLayout) row;
        EventView eventView = new EventView(rowroot);
        return eventView;
    }

    @Override
    public void onBindViewHolder(@NonNull EventView holder, int position) {
        Log.e(">>>","entro aqui");
        db.collection("users").document(events.get(position).getEventOwnerId()).get()
                .addOnSuccessListener(
                        command -> {
                            User u = command.toObject(User.class);
                            holder.getNameTextView().setText(u.getUserName());
                            String pathP = u.getProfilePic();
                            if(u.getProfilePic() == null){
                                pathP="fotoPerfil.jpg";
                                storage.getReference().child(pathP).getDownloadUrl()
                                        .addOnSuccessListener(
                                                command1 -> {
                                                    String url = command1.toString();
                                                    Glide.with(row).load(url).apply(RequestOptions.circleCropTransform()).into(holder.getImageViewColabPic());
                                                }
                                        );
                            }else{
                                pathP =u.getProfilePic();
                                storage.getReference().child("postUser").child(pathP).getDownloadUrl()
                                        .addOnSuccessListener(
                                                command1 -> {
                                                    String url = command1.toString();
                                                    Glide.with(row).load(url).apply(RequestOptions.circleCropTransform()).into(holder.getImageViewColabPic());
                                                }
                                        );
                            }
                        }
                ).addOnFailureListener(
                        command -> {
                            System.out.println("fallo");
                        }
        );

        String path =events.get(position).getEventImage();
        if(path != null){
            storage.getReference().child("postEvent").child(path).getDownloadUrl()
                    .addOnSuccessListener(
                            command1 -> {
                                String url = command1.toString();
                                Glide.with(row).load(url).centerCrop().into(holder.getImgEvent());
                            }
                    );

        }else{
            path = "sin_imagen.png";
            storage.getReference().child(path).getDownloadUrl()
                    .addOnSuccessListener(
                            command1 -> {
                                String url = command1.toString();
                                Glide.with(row).load(url).centerCrop().into(holder.getImgEvent());
                            }
                    );
        }

    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public void addEvent(Event event){
        events.add(event);
        notifyDataSetChanged();
    }

    public String lastEvent(){
        return events.get(events.size()-1).getEventName();
    }


}
