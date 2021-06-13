package app.moviles.kamachi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.UUID;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import app.moviles.kamachi.model.Event;
import app.moviles.kamachi.model.EventView;

public class EventAdapter extends RecyclerView.Adapter<EventView> {
    private ArrayList<Event> events;

    public EventAdapter(){
        events = new ArrayList<>();
        events.add(new Event(UUID.randomUUID().toString(), "evento 1"));
    }

    @NonNull
    @Override
    public EventView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View row = inflater.inflate(R.layout.eventrow, null);
        ConstraintLayout rowroot = (ConstraintLayout) row;
        EventView eventView = new EventView(rowroot);
        return eventView;
    }

    @Override
    public void onBindViewHolder(@NonNull EventView holder, int position) {
        holder.getNameEvent().setText(events.get(position).getEventName());
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public void EventAdapter(Event event){
        events.add(event);
    }
}
