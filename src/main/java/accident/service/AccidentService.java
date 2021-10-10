package accident.service;

import accident.model.Accident;
import accident.store.AccidentMem;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AccidentService {

    private AccidentMem accidentMem;

    public AccidentService(AccidentMem accidentMem) {
        this.accidentMem = accidentMem;
    }

    public void addAccident(Accident accident) {
        accidentMem.add(accident);
    }
    public Collection<Accident> allValues() {
        return accidentMem.findAll();
    }
}
