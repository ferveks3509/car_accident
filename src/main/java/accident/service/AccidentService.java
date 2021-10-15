package accident.service;

import accident.model.Accident;
import accident.model.AccidentType;
import accident.model.Rule;
import accident.store.AccidentMem;
import org.springframework.stereotype.Service;
import java.util.Collection;

@Service
public class AccidentService {

    private AccidentMem accidentMem;

    public AccidentService(AccidentMem accident) {
        this.accidentMem = accident;
    }

    public void addAccident(Accident accident) {
       accidentMem.add(accident);
    }

    public Collection<Accident> allValues() {
        return accidentMem.findAll();
    }

    public Accident findById(int id) {
        return accidentMem.findById(id);
    }

    public void update(Accident accident) {
        accidentMem.update(accident);
    }

    public Collection<AccidentType> getAllTypes() {
        return accidentMem.accidentTypesAll();
    }

    public Collection<Rule> getAllRules() {
        return accidentMem.accidentRuleAll();
    }
}
