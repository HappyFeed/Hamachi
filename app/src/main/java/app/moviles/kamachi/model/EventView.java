package app.moviles.kamachi.model;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import app.moviles.kamachi.R;

public class EventView extends RecyclerView.ViewHolder {

    private ConstraintLayout root;
    private TextView nameEvent;
    private Button information;

    public EventView(ConstraintLayout root) {
        super(root);
        this.root = root;
        nameEvent = root.findViewById(R.id.nameTextView);
        information = root.findViewById(R.id.informationBtn);
    }

    public ConstraintLayout getRoot() {
        return root;
    }

    public void setRoot(ConstraintLayout root) {
        this.root = root;
    }

    public TextView getNameEvent() {
        return nameEvent;
    }

    public void setNameEvent(TextView nameEvent) {
        this.nameEvent = nameEvent;
    }

    public Button getInformation() {
        return information;
    }

    public void setInformation(Button information) {
        this.information = information;
    }
}
