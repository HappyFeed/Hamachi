package app.moviles.kamachi.repository;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import app.moviles.kamachi.model.Event;
import app.moviles.kamachi.model.User;

public class EventRepositoryImpl implements EventRepositoryInterface{

    private FirebaseFirestore db;
    private Event event;
    private List<Event> events;

    public EventRepositoryImpl(){
        db = FirebaseFirestore.getInstance();
        events = new ArrayList<Event>();
    }

    @Override
    public void save(Event event) {
        db.collection("events").document(event.getIdEvent()).set(event);
        //db.collection("events").document(event.getIdEvent()).collection("eventParticipant").document().set()
    }

    @Override
    public void edit(Event event) {
        db.collection("events").document(event.getIdEvent()).set(event);
    }

    @Override
    public Event findById(String id) {
        db.collection("events").document(id).get()
                .addOnSuccessListener(
                        command -> {
                            event = command.toObject(Event.class);
                        }
                );
        return event;
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
}
