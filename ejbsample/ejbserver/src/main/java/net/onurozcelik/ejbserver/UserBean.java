package net.onurozcelik.ejbserver;

import java.util.List;

public interface UserBean {
    void create(User entity);
    List<User> findAll();
}
