package accident.store;

import accident.model.Accident;
import org.springframework.stereotype.Repository;
import java.util.Collection;
import java.util.HashMap;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class AccidentMem {
    private final Map<Integer, Accident> accidents = new HashMap<>();
    private AtomicInteger ACCIDENT_ID = new AtomicInteger(2);

    public AccidentMem() {
        add(new Accident(1, "name1", "speed", "Kirov-1"));
        add(new Accident(2, "name2", "dtp", "Kirov-2"));
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
}
