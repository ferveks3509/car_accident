package accident.service;

import accident.model.Accident;
import accident.model.AccidentType;
import accident.model.Rule;
import accident.store.AccidentHibernate;
import accident.store.AccidentJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccidentService {

    private AccidentHibernate accidentHibernate;

    public AccidentService(AccidentHibernate accidentHibernate) {
        this.accidentHibernate = accidentHibernate;
    }

    public Collection<Accident> getAllAccident() {
        return accidentHibernate.getAllAccident();
    }

    public Collection<Rule> getAllRule() {
        return accidentHibernate.getAllRule();
    }

    public Collection<AccidentType> getAllAccidentType() {
        return accidentHibernate.getAllAccidentType();
    }

    public void addAccident(Accident accident, String[] ids, String[] idt) {
        accidentHibernate.save(accident, ids, idt);
    }

    public Accident findAccidentById(int id) {
       return accidentHibernate.findAccidentById(id);
    }
}
