package app.moviles.kamachi.model;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import app.moviles.kamachi.R;

public class EventUpcomingView extends RecyclerView.ViewHolder{

        private ConstraintLayout root;
        private TextView nameTextView2;
        private Button informationBtn2;
        private ImageView imgEvent2;
        private ImageView imageViewColabPic2;

        public EventUpcomingView(ConstraintLayout root) {
            super(root);
            this.root = root;
            nameTextView2 = root.findViewById(R.id.nameTextView2);
            informationBtn2 = root.findViewById(R.id.informationBtn2);
            imgEvent2 = root.findViewById(R.id.imgEvent2);
            imageViewColabPic2 = root.findViewById(R.id.imageViewColabPic2);

        }

    public ConstraintLayout getRoot() {
            return root;
        }

        public void setRoot(ConstraintLayout root) {
            this.root = root;
        }

    public TextView getNameTextView() {
        return nameTextView2;
    }

    public Button getInformationBtn() {
        return informationBtn2;
    }

    public ImageView getImgEvent() {
        return imgEvent2;
    }

    public ImageView getImageViewColabPic() {
        return imageViewColabPic2;
    }

    public void setNameTextView(TextView nameTextView) {
        this.nameTextView2 = nameTextView;
    }

    public void setInformationBtn(Button informationBtn) {
        this.informationBtn2 = informationBtn;
    }

    public void setImgEvent(ImageView imgEvent) {
        this.imgEvent2 = imgEvent;
    }

    public void setImageViewColabPic(ImageView imageViewColabPic) {
        this.imageViewColabPic2 = imageViewColabPic;
    }
}
