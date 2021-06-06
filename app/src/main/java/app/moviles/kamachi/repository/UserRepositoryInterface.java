package app.moviles.kamachi.repository;

import java.util.List;

import app.moviles.kamachi.model.User;

public interface UserRepositoryInterface {

    public void save(User user);

    public void edit(User user);

    public User findById(String id);

    public void delete(String id);

    public List<User> findAll();

}
