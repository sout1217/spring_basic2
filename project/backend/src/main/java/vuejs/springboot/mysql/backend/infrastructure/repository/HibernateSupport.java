package vuejs.springboot.mysql.backend.infrastructure.repository;

import org.hibernate.Session;

import javax.persistence.EntityManager;

public abstract class HibernateSupport {

    protected EntityManager em;

    public HibernateSupport(EntityManager em) {
        this.em = em;
    }

    public Session getSession() {
        return em.unwrap(Session.class);
    }
}
