package pdp.uz.jparelationships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;
import pdp.uz.jparelationships.entity.TimeTable;

import java.sql.Time;
import java.util.List;

@Repository
public interface TimeTableRepo extends JpaRepository<TimeTable,Integer> {

    List<TimeTable> findByGroupId(@RequestParam Integer id);
}
