package app.moviles.kamachi.repository;

import java.io.FileNotFoundException;
import java.util.List;

import app.moviles.kamachi.model.Event;

public interface EventRepositoryInterface {

    public void save(Event event);

    public void edit(Event event);

    public void findById(String id);

    public void delete(String id);

    public List<Event> findAll();

    public void addParticipant(Event event, String id, String name, String email);

    public void getOwner(String id);

    public void addPicEvent(Event event) throws FileNotFoundException;

    public void listParticipants(Event e);
}
