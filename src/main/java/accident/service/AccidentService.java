package accident.service;

import accident.model.Accident;
import accident.model.AccidentType;
import accident.model.Rule;
import accident.store.AccidentJdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class AccidentService {

    private AccidentJdbcTemplate accidentJdbcTemplate;

    public AccidentService(AccidentJdbcTemplate accidentJdbcTemplate) {
        this.accidentJdbcTemplate = accidentJdbcTemplate;
    }

    public void addAccident(Accident accident, String[] ids, String[] idT) {
        accidentJdbcTemplate.save(accident, ids, idT);
    }

    public Accident findAccidentById(int id) {
        return accidentJdbcTemplate.findAccidentById(id);
    }

    public Collection<Accident> getAll() {
        return accidentJdbcTemplate.getAll();
    }

    public List<Rule> getAllRules() {
        return accidentJdbcTemplate.getAllRule();
    }

    public List<AccidentType> getAllAccidentType() {
        return accidentJdbcTemplate.getAllAccidentType();
    }
}
