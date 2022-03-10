package pdp.uz.jparelationships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.jparelationships.entity.Continent;


public interface ContinentRepo extends JpaRepository<Continent, Integer> {

}
