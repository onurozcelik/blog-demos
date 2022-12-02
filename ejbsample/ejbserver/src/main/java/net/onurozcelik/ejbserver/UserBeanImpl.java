package net.onurozcelik.ejbserver;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;

@Stateless
@Remote(UserBean.class)
public class UserBeanImpl implements UserBean, Serializable {

    @PersistenceContext(unitName = "default")
    private EntityManager em;

    @Override
    public void create(User entity) {
        em.persist(entity);
    }

    @Override
    public List<User> findAll() {
        TypedQuery<User> query = em.createQuery("select u from User u", User.class);
        return query.getResultList();
    }
}
