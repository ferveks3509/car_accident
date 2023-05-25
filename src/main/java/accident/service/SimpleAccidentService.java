package accident.service;

import accident.model.Accident;
import accident.model.AccidentType;
import accident.model.Rule;

import java.util.List;
import java.util.Set;

public interface SimpleAccidentService {
    void addAccident(Accident accident, String[] ids, String[] idt);
    Accident findAccidentById(int id);
    Rule findRuleById(int id);
    AccidentType findAccidentTypeById(int id);
    Set<Rule> getAllRule();
    List<AccidentType> getAllAccidentType();
    List<Accident> getAllAccident();
}
