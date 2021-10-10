package accident.store;

import accident.model.Accident;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;

import java.util.Map;

@Repository
public class AccidentMem {
    Map<Integer, Accident> accidents = new HashMap<>();

    public AccidentMem() {
        add(new Accident(1, "name1", "speed", "Kirov-1"));
        add(new Accident(2, "name2", "dtp", "Kirov-2"));
    }

    public void add(Accident accident) {
        accidents.put(accident.getId(), accident);
    }

    public Collection<Accident> findAll() {
        return  accidents.values();
    }

}
