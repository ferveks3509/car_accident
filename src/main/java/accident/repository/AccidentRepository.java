package accident.repository;

import accident.model.Accident;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccidentRepository extends CrudRepository<Accident, Integer> {

    @Query("select distinct a from Accident a left join fetch a.rules")
    List<Accident> findAllAccident();

    @Query("select distinct a from Accident a left join fetch a.rules r where a.id = :id")
    Accident findAccidentById(@Param("id") int id);
}
