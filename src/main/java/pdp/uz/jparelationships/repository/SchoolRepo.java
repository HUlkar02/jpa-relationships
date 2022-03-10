package pdp.uz.jparelationships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pdp.uz.jparelationships.entity.School;
@Repository
public interface SchoolRepo extends JpaRepository<School,Integer> {
}
