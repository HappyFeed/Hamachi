package app.moviles.kamachi.model;

import java.util.ArrayList;
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
    private String eventOwnerId;
    private String eventImage;
    private String nameInstructor;

    public Event() {
    }

    public Event(String eventName){
        this.idEvent = UUID.randomUUID().toString();
        this.eventName = eventName;
    }

    public Event(String nameInstructor, String eventImage){
        this.idEvent = UUID.randomUUID().toString();
        this.nameInstructor = nameInstructor;
        this.eventImage = eventImage;
    }

    public Event(String eventName, String description, Date dateEvent, EventType type, String url, double price, int maxParticipants) {
        this.idEvent = UUID.randomUUID().toString();
        this.eventName = eventName;
        this.description = description;
        this.dateEvent = dateEvent;
        this.type = type;
        this.url = url;
        this.price = price;
        this.maxParticipants = maxParticipants;

    }

    public String getEventOwnerId() {
        return eventOwnerId;
    }

    public String getEventImage() {
        return eventImage;
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

    public void setEventOwnerId(String eventOwnerId) {
        this.eventOwnerId = eventOwnerId;
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

    public void setEventImage(String eventImage) {
        this.eventImage = eventImage;
    }

    public String getNameInstructor() {
        return nameInstructor;
    }

    public void setNameInstructor(String nameInstructor) {
        this.nameInstructor = nameInstructor;
    }
}
