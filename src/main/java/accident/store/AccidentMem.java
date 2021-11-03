package accident.store;

import accident.model.Accident;
import accident.model.AccidentType;
import accident.model.Rule;
import org.springframework.stereotype.Repository;

import java.util.*;

import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {
    private final Map<Integer, Accident> accidents = new HashMap<>();
    private AtomicInteger ACCIDENT_ID = new AtomicInteger(2);

    private final Map<Integer, AccidentType> types = new HashMap<>();

    private final Map<Integer, Rule> rules = new HashMap<>();

    public AccidentMem() {
        types.put(1, AccidentType.of(1, "Две машины"));
        types.put(2, AccidentType.of(2, "Машина и человек"));
        types.put(3, AccidentType.of(3, "Машина и велосипед"));

        rules.put(1, Rule.of(1, "Статья. 1"));
        rules.put(2, Rule.of(2, "Статья. 2"));
        rules.put(3, Rule.of(3, "Статья. 3"));

        Set<Rule> ruleSet1 = new LinkedHashSet<>();
        ruleSet1.add(rules.get(1));
        Set<Rule> ruleSet2 = new LinkedHashSet<>();
        ruleSet2.add(rules.get(2));
        accidents.put(1 ,new Accident(1, "name1", "speed", "Kirov-1", types.get(1), ruleSet1));
        accidents.put(2 ,new Accident(2, "name2", "dtp", "Kirov-2", types.get(2), ruleSet2));
    }

    public void add(Accident accident, String[] ids, String[] idT) {
        int id = accident.getId();
        AccidentType accidentType = findByIdAccidentType(Integer.parseInt(idT[0]));
        accident.setAccidentType(accidentType);
        if (!accidents.containsKey(id)) {
            id = ACCIDENT_ID.incrementAndGet();
            accident.setId(id);
        }
        accident.setRules(getRulesAccident(ids));
        accidents.put(accident.getId(), accident);
    }

    public Collection<Accident> findAll() {
        return  accidents.values();
    }

    public Accident findById(int id) {
        return accidents.get(id);
    }

    public Collection<AccidentType> accidentTypesAll() {
        return types.values();
    }

    public AccidentType findByIdAccidentType(int id) {
        return types.get(id);
    }

    public Rule findByIdRule(int id) {
        return rules.get(id);
    }

    public Collection<Rule> accidentRuleAll(){
        return rules.values();
    }

    private Set<Rule> getRulesAccident(String[] ids) {
        Set<Rule> rules = new LinkedHashSet<>();
        for (String el : ids) {
            rules.add(findByIdRule(Integer.parseInt(el)));
        }
        return rules;
    }
}
