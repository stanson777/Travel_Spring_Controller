package codecademy.demo.repositories;

import java.util.List;

import codecademy.demo.entities.Adventure;
import org.springframework.data.repository.CrudRepository;



public interface AdventureRepository extends CrudRepository<Adventure, Integer> {
    public List<Adventure> findByCountry(String country);
    public List<Adventure> findByState(String state);
}
