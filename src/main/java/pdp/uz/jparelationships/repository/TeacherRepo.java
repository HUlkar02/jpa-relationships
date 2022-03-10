package pdp.uz.jparelationships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pdp.uz.jparelationships.entity.Teacher;
@Repository
public interface TeacherRepo extends JpaRepository<Teacher,Integer> {
}
