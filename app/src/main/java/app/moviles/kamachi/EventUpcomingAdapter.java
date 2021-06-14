package app.moviles.kamachi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import app.moviles.kamachi.model.Event;
import app.moviles.kamachi.model.EventUpcomingView;
import app.moviles.kamachi.model.EventView;
import app.moviles.kamachi.model.User;

public class EventUpcomingAdapter extends RecyclerView.Adapter<EventUpcomingView> {

    private ArrayList<Event> events;
    private FirebaseAuth auth;
    private FirebaseFirestore db;
    private FirebaseStorage storage;
    private View row;

    public EventUpcomingAdapter() {
        events = new ArrayList<>();
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();
    }

    @NonNull
    @Override
    public EventUpcomingView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        row = inflater.inflate(R.layout.eventrow, null);
        ConstraintLayout rowroot = (ConstraintLayout) row;
        EventUpcomingView eventView = new EventUpcomingView(rowroot);
        return eventView;
    }

    @Override
    public void onBindViewHolder(@NonNull EventUpcomingView holder, int position) {
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

    public long lastOne(){
        return events.get(events.size()-1).getDateEvent();
    }

    public void addEvent(Event event){
        events.add(event);
        notifyDataSetChanged();
    }
}
