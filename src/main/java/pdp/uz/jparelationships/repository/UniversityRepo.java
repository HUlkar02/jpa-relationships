package pdp.uz.jparelationships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pdp.uz.jparelationships.entity.University;

@Repository
public interface UniversityRepo extends JpaRepository<University,Integer> {


}
