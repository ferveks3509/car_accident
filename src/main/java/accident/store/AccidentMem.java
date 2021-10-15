package accident.store;

import accident.model.Accident;
import accident.model.AccidentType;
import org.springframework.stereotype.Repository;

import java.util.*;

import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {
    private final Map<Integer, Accident> accidents = new HashMap<>();
    private AtomicInteger ACCIDENT_ID = new AtomicInteger(2);

    private final Map<Integer, AccidentType> types = new HashMap<>();

    public AccidentMem() {
        add(new Accident(1, "name1", "speed", "Kirov-1"));
        add(new Accident(2, "name2", "dtp", "Kirov-2"));

        types.put(1, AccidentType.of(1, "Две машины"));
        types.put(2, AccidentType.of(2, "Машина и человек"));
        types.put(3, AccidentType.of(3, "Машина и велосипед"));
    }

    public void add(Accident accident) {
      if (accident.getId() == 0) {
          accident.setId(ACCIDENT_ID.incrementAndGet());
      }
      accidents.put(accident.getId(), accident);
    }

    public Collection<Accident> findAll() {
        return  accidents.values();
    }

    public Accident findById(int id) {
        return accidents.get(id);
    }

    public void update(Accident accident) {
        accidents.replace(accident.getId(), accident);
    }
    public Collection<AccidentType> accidentTypesAll() {
        return types.values();
    }
}
