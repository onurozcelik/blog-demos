package net.onurozcelik.ejbsample;

import java.io.Serializable;
import java.util.List;

import jakarta.ejb.Remote;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Stateless
@Remote(UserBean.class)
public class UserBeanImpl implements UserBean, Serializable {

    @PersistenceContext(unitName = "userDatabase")
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
