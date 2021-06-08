package app.moviles.kamachi.actions;

import java.util.ArrayList;

import app.moviles.kamachi.model.User;

public class Actions {

    public interface OnUserList{
         void onFindAll(ArrayList<User> userList);
    }
}
