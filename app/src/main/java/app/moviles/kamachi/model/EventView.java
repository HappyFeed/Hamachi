package app.moviles.kamachi.model;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import app.moviles.kamachi.R;

public class EventView extends RecyclerView.ViewHolder {

    private ConstraintLayout root;
    private TextView nameTextView;
    private Button informationBtn;
    private ImageView imgEvent;
    private ImageView imageViewColabPic;

    public EventView(ConstraintLayout root) {
        super(root);
        this.root = root;
        nameTextView = root.findViewById(R.id.nameTextView);
        informationBtn = root.findViewById(R.id.informationBtn);
        imgEvent = root.findViewById(R.id.imgEvent);
        imageViewColabPic = root.findViewById(R.id.imageViewColabPic);
    }

    public ImageView getImgEvent() {
        return imgEvent;
    }

    public TextView getNameTextView() {
        return nameTextView;
    }

    public ImageView getImageViewColabPic() {
        return imageViewColabPic;
    }

    public ConstraintLayout getRoot() {
        return root;
    }

    public void setRoot(ConstraintLayout root) {
        this.root = root;
    }

    public TextView getNameEvent() {
        return nameTextView;
    }

    public void setNameEvent(TextView nameEvent) {
        this.nameTextView = nameEvent;
    }

    public Button getInformation() {
        return informationBtn;
    }

    public void setInformation(Button information) {
        this.informationBtn = information;
    }

    public void setImgEvent(ImageView imgEvent) {
        this.imgEvent = imgEvent;
    }

    public void setImageViewColabPic(ImageView imageViewColabPic) {
        this.imageViewColabPic = imageViewColabPic;
    }
}
