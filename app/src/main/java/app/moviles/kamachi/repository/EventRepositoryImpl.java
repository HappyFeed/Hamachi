package app.moviles.kamachi.repository;

import android.util.Log;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import app.moviles.kamachi.comunication.ServiceEventInfo;
import app.moviles.kamachi.model.Event;
import app.moviles.kamachi.model.Participant;
import app.moviles.kamachi.model.User;

public class EventRepositoryImpl implements EventRepositoryInterface{

    private FirebaseFirestore db;
    private FirebaseStorage storage;

    private Event event;
    private List<Event> events;
    private ServiceEventInfo sei;

    public EventRepositoryImpl(){

        this.db = FirebaseFirestore.getInstance();
        this.storage = FirebaseStorage.getInstance();
        events = new ArrayList<Event>();
    }

    @Override
    public void save(Event event) {
        db.collection("events").document(event.getIdEvent()).set(event);
        //db.collection("events").document(event.getIdEvent()).collection("eventParticipant").document().set()
    }

    @Override
    public void addPicEvent(Event event) throws FileNotFoundException {
        String path = event.getEventImage();
        FileInputStream fis = null;
        fis= new FileInputStream(new File(path));
        storage.getReference().child("postEvent").child(path).putStream(fis).addOnSuccessListener(
                runnable -> {
                    edit(event);
                }
        ).addOnFailureListener(
                command -> {
                    Log.e(">>>","no se pudo guardar la imagen");
                }
        );
    }

    @Override
    public void edit(Event event) {
        db.collection("events").document(event.getIdEvent()).set(event);
    }

    @Override
    public void findById(String id) {
        db.collection("events").document(id).get()
                .addOnSuccessListener(
                        command -> {
                            event = command.toObject(Event.class);
                        }
                );
    }

    @Override
    public void delete(String id) {
        db.collection("events").document(id).delete();
    }

    @Override
    public List<Event> findAll() {
        db.collection("events").limit(10).get().addOnSuccessListener(
                command -> {
                    for(DocumentSnapshot doc: command.getDocuments()){
                        Event e = doc.toObject(Event.class);
                        events.add(e);
                    }
                }
        );
        return events;
    }

    @Override
    public void getOwner(String id){
        db.collection("events").document(id).get()
                .addOnSuccessListener(
                        command -> {
                            event = command.toObject(Event.class);
                            db.collection("users").document(event.getEventOwnerId()).get().addOnSuccessListener(
                                    command1 -> {
                                        User user = command1.toObject(User.class);
                                        System.out.println(user.getUserName());
                                        //sei.enviarDatos(id, user.getUserId());
                                    }
                            );
                        }
                );
    }

    @Override
    public void addParticipant(Event e ,String id, String name, String email) {
        db.collection("events").document(e.getIdEvent()).collection("eventParticipants").document(id).set(new Participant(name, email));
    }

    public void listParticipants(Event e){
         db.collection("events").document(e.getIdEvent()).collection("eventParticipants").limit(10).get().addOnSuccessListener(
                 command -> {
                     for(DocumentSnapshot doc: command.getDocuments()){
                         Participant f = doc.toObject(Participant.class);
                         System.out.println(f.getName());
                     }
                 }
         );
    }

}
