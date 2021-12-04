package accident.store;

import accident.model.Accident;
import accident.model.AccidentType;
import accident.model.Rule;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.function.Function;

public class AccidentHibernate {
    private final SessionFactory sf;

    public AccidentHibernate(SessionFactory sf) {
        this.sf = sf;
    }

    public void save(Accident accident, String[] ids, String[] idt) {
        Set rsl = new LinkedHashSet();
        for (String els : ids) {
            rsl.add(findRuleById(Integer.parseInt(els)));
            accident.setRules(rsl);
        }
        for (String elt : idt) {
            accident.setAccidentType(findAccidentTypeById(Integer.parseInt(elt)));
        }
        tx(session -> {
            if (accident.getId() == 0) {
                session.save(accident);
            } else {
                session.update(accident);
            }
            return true;
        });
    }

    public Collection<AccidentType> getAllAccidentType() {
        List rsl = new ArrayList();
        try {
            rsl = tx(session -> session.createQuery("from  accident.model.AccidentType").list());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }

    public AccidentType findAccidentTypeById(int id) {
        AccidentType accidentType = null;
        try {
            accidentType = tx(session -> session.createQuery("select a from AccidentType a where a.id = :id", AccidentType.class)
                    .setParameter("id", id)
                    .uniqueResult());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accidentType;
    }

    public Rule findRuleById(int id) {
        Rule rule = null;
        try {
            rule = tx(session -> session.createQuery("select r from Rule r where r.id = :id", Rule.class)
                    .setParameter("id", id)
                    .uniqueResult());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rule;
    }

    public Accident findAccidentById(int id) {
        Accident accident = null;
        try {
            accident = tx(session -> session.createQuery("select distinct a from Accident a left join " +
                    "fetch a.rules r where a.id = :id", Accident.class)
                    .setParameter("id", id)
                    .uniqueResult());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accident;
    }

    public Collection<Rule> getAllRule() {
        List rsl = new ArrayList();
        try {
            rsl = tx(session -> session.createQuery("from accident.model.Rule").list());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }

    public List<Accident> getAllAccident() {
        List rsl = new ArrayList();
        try {
            rsl = tx(session -> session.createQuery("select distinct a from Accident a join fetch a.rules").list());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }

    private <T> T tx(Function<Session, T> function) {
        final Session session = sf.openSession();
        final Transaction transaction = session.beginTransaction();
        try {
            T result = function.apply(session);
            transaction.commit();
            return result;
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}
