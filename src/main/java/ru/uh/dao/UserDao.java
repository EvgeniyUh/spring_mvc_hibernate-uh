package ru.uh.dao;

import ru.uh.models.User;

import java.util.List;

public interface UserDao {
    public void add(User user);

    public User getUser(Long id);

    public void updateUser(User user);

    public void remove(long id);

    public List<User> listUsers();

}
