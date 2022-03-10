package pdp.uz.jparelationships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.jparelationships.entity.Region;


public interface RegionRepo extends JpaRepository<Region,Integer> {
}
