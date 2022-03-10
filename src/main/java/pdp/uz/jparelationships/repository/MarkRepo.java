package pdp.uz.jparelationships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import pdp.uz.jparelationships.entity.Mark;

import java.util.List;

public interface MarkRepo extends JpaRepository<Mark,Integer> {

    List<Mark> findByStudentIdAndSubjectId(@RequestParam Integer studentId, @RequestParam Integer subjectId);
}
