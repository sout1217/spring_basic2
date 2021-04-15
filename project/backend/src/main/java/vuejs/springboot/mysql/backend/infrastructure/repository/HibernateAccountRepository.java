package vuejs.springboot.mysql.backend.infrastructure.repository;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import vuejs.springboot.mysql.backend.domain.model.account.Account;
import vuejs.springboot.mysql.backend.domain.model.account.AccountRepository;

import javax.persistence.EntityManager;
import java.util.Optional;

@Repository
public class HibernateAccountRepository extends HibernateSupport implements AccountRepository {

    public HibernateAccountRepository(EntityManager em) {
        super(em);
    }

    @Override
    public Account save(Account account) {
        em.persist(account);
        em.flush();
        // em.persist 를 하면 id 값이 매겨진다

        return account;
    }

    @Override
    public Optional<Account> findByUsername(String username) {
        Query<Account> query = getSession().createQuery("from Account where username = :username", Account.class);
        query.setParameter("username", username);
        return Optional.ofNullable(query.uniqueResult());
    }

    @Override
    public Optional<Account> findByEmailAddress(String emailAddress) {
        Query<Account> query = getSession().createQuery("from Account where emailAddress = :emailAddress", Account.class);
        query.setParameter("emailAddress", emailAddress);
        return Optional.ofNullable(query.uniqueResult());
    }

}
