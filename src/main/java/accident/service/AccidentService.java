package accident.service;

import accident.model.Accident;
import accident.model.AccidentType;
import accident.model.Rule;
import accident.repository.AccidentRepository;
import accident.repository.AccidentTypeRepository;
import accident.repository.RuleRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AccidentService {

    private final AccidentRepository accidentRepository;
    private final AccidentTypeRepository accidentTypeRepository;
    private final RuleRepository ruleRepository;

    public AccidentService(AccidentRepository accidentRepository, AccidentTypeRepository accidentTypeRepository, RuleRepository ruleRepository) {
        this.accidentRepository = accidentRepository;
        this.accidentTypeRepository = accidentTypeRepository;
        this.ruleRepository = ruleRepository;
    }

    public void addAccident(Accident accident, String[] ids, String[] idt) {
        Set<Rule> rsl = new LinkedHashSet<>();
        for (String els : ids) {
            rsl.add(findRuleById(Integer.parseInt(els)));
        }
        accident.setRules(rsl);
        for (String elt : idt) {
            accident.setAccidentType(findAccidentTypeById(Integer.parseInt(elt)));
        }
        accidentRepository.save(accident);
    }

    public Accident findAccidentById(int id) {
        Accident accident = accidentRepository.findAccidentById(id);
        return accident;
    }

    public Rule findRuleById(int id) {
        Rule rule = null;
        rule = ruleRepository.findById(id).get();
        return rule;
    }

    public AccidentType findAccidentTypeById(int id) {
        AccidentType accidentType = null;
        accidentType = accidentTypeRepository.findById(id).get();
        return accidentType;
    }

    public Set<Rule> getAllRule() {
        Set<Rule> rsl = new LinkedHashSet<>();
        ruleRepository.findAll().forEach(rsl::add);
        return rsl;
    }

    public List<AccidentType> getAllAccidentType() {
        List<AccidentType> rsl = new ArrayList<>();
        accidentTypeRepository.findAll().forEach(rsl::add);
        return rsl;
    }

    public List<Accident> getAllAccident() {
        List<Accident> rsl = new ArrayList<>();
        accidentRepository.findAllAccident().forEach(rsl::add);
        return rsl;
    }
}
