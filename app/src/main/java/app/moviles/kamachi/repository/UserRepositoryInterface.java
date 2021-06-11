package app.moviles.kamachi.repository;

import java.util.List;

import app.moviles.kamachi.actions.Actions;
import app.moviles.kamachi.model.User;

public interface UserRepositoryInterface {

    public void save(User user);

    public void edit(User user);

    public void findById(Actions.OnFindById onFindById, String id);

    public void delete(String id);

    public void findAll(Actions.OnUserList onResponse);

}
