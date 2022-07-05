package ru.uh.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.uh.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
@Transactional
public class UserDaoImp implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(User user) {
        entityManager.persist(user);
   }

    @Override
    public User getUser(Long id) {
        User user = entityManager.find(User.class, id);
       return user;
    }

    @Override
    public void updateUser(User user) {
       User updatingUser = entityManager.find(User.class, user.getId());
        updatingUser.setEmail(user.getEmail());
        updatingUser.setUsername(user.getUsername());
    }

    @Override
    public void remove(long id) {
        entityManager.remove(entityManager.find(User.class, id));
    }

    @Override
    public List<User> listUsers() {
        Query query = entityManager.createQuery("from User");
        List<User> users = query.getResultList();
        return users;
    }
}