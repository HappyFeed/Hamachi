package app.moviles.kamachi.model;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class Event {

    private String idEvent;
    private String eventName;
    private String description;
    private Date dateEvent;
    private EventType type;
    private String url;
    private double price;
    private int maxParticipants;
    private User eventOwner;
    private User[] eventParticipants;
    private String[] eventImage;

    public Event(String eventName, String description, Date dateEvent, EventType type, String url, double price, int maxParticipants) {
        this.idEvent = UUID.randomUUID().toString();
        this.eventName = eventName;
        this.description = description;
        this.dateEvent = dateEvent;
        this.type = type;
        this.url = url;
        this.price = price;
        this.maxParticipants = maxParticipants;
        this.eventParticipants = new User[this.maxParticipants];
    }

    public String getIdEvent() {
        return idEvent;
    }

    public String getEventName() {
        return eventName;
    }

    public String getDescription() {
        return description;
    }

    public Date getDateEvent() {
        return dateEvent;
    }

    public EventType getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public double getPrice() {
        return price;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public User getEventOwner() {
        return eventOwner;
    }

    public User[] getEventParticipants() {
        return eventParticipants;
    }

    public String[] getEventImage() {
        return eventImage;
    }

    public void setIdEvent(String idEvent) {
        this.idEvent = idEvent;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDateEvent(Date dateEvent) {
        this.dateEvent = dateEvent;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public void setEventOwner(User eventOwner) {
        this.eventOwner = eventOwner;
    }

    public void setEventParticipants(User[] eventParticipants) {
        this.eventParticipants = eventParticipants;
    }

    public void setEventImage(String[] eventImage) {
        this.eventImage = eventImage;
    }
}
