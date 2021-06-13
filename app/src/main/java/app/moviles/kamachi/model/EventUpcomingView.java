package app.moviles.kamachi.model;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import app.moviles.kamachi.R;

public class EventUpcomingView extends RecyclerView.ViewHolder{

        private ConstraintLayout root;
        private TextView nameInstructor;
        private ImageView img;
        private Button inscritionButton;

        public EventUpcomingView(ConstraintLayout root) {
            super(root);
            this.root = root;
            nameInstructor= root.findViewById(R.id.nameInstructor);
            img = root.findViewById(R.id.img);
            inscritionButton = root.findViewById(R.id.inscriptionButton);
        }

        public ConstraintLayout getRoot() {
            return root;
        }

        public void setRoot(ConstraintLayout root) {
            this.root = root;
        }

    public TextView getNameInstructor() {
        return nameInstructor;
    }

    public void setNameInstructor(TextView nameInstructor) {
        this.nameInstructor = nameInstructor;
    }

    public ImageView getImg() {
        return img;
    }

    public void setImg(ImageView img) {
        this.img = img;
    }

    public Button getInscritionButton() {
        return inscritionButton;
    }

    public void setInscritionButton(Button inscritionButton) {
        this.inscritionButton = inscritionButton;
    }
}
