package app.moviles.kamachi.repository;

import java.util.List;

import app.moviles.kamachi.model.Event;

public interface EventRepositoryInterface {

    public void save(Event event);

    public void edit(Event event);

    public Event findById(String id);

    public void delete(String id);

    public List<Event> findAll();
}
