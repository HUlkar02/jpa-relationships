package pdp.uz.jparelationships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pdp.uz.jparelationships.entity.Group;

import java.util.List;

@Repository
public interface GroupRepo extends JpaRepository<Group,Integer> {
  List<Group>findAllByFaculty_UniversityId(Integer faculty_university_id);

  @Query("select  gr from groups gr where  gr.faculty.university.id=:universityId")
  List<Group> getGroupsByUnversityID(Integer universityId);


  @Query(value = "SELECT  * from  groups g" +
          " join faculty f on f.id=g.faculty_id" +
          " join  university u on u.id=f.unversity_id" +
          "where u.id=:universityId",nativeQuery = true)
  List<Group> getGroupsByUniversityIdNative(Integer universityId);

}
