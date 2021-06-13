package app.moviles.kamachi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import app.moviles.kamachi.model.Event;
import app.moviles.kamachi.model.EventUpcomingView;
import app.moviles.kamachi.model.EventView;

public class EventUpcomingAdapter extends RecyclerView.Adapter<EventUpcomingView> {

    private ArrayList<Event> events;

    public EventUpcomingAdapter() {
        events = new ArrayList<>();
    }

    @NonNull
    @Override
    public EventUpcomingView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View row = inflater.inflate(R.layout.eventrow, null);
        ConstraintLayout rowroot = (ConstraintLayout) row;
        EventUpcomingView eventView = new EventUpcomingView(rowroot);
        return eventView;
    }

    @Override
    public void onBindViewHolder(@NonNull EventUpcomingView holder, int position) {
        holder.getNameInstructor().setText(events.get(position).getNameInstructor());
       // ------> holder.getImg().No me acuerdo como se maneja la imagen
        // tambien hay que acomodar el Boton :c

    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public void EventUpcomingAdapter(Event event){
        events.add(event);
    }
}
