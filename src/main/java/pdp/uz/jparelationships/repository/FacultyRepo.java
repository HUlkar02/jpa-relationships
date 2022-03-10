package pdp.uz.jparelationships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pdp.uz.jparelationships.entity.Faculty;

import java.util.List;

@Repository
public interface FacultyRepo extends JpaRepository<Faculty,Integer> {
boolean existsByNameAndUniversityId(String  name, Integer id);
List<Faculty> findAllByUniversityId(Integer university_id);
}
