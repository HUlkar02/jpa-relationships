package pdp.uz.jparelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pdp.uz.jparelationships.entity.Faculty;
import pdp.uz.jparelationships.entity.University;
import pdp.uz.jparelationships.payload.FacultyDto;
import pdp.uz.jparelationships.repository.FacultyRepo;
import pdp.uz.jparelationships.repository.UniversityRepo;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    @Autowired
    FacultyRepo facultyRepo;

    @Autowired
    UniversityRepo universityRepo;

    @PostMapping
    public String addFaculty(@RequestBody FacultyDto facultyDto) {

        boolean exists = facultyRepo.existsByNameAndUniversityId(facultyDto.getName(), facultyDto.getUniversityId());
        if (exists)
            return "This university such faculty exist";

        Faculty faculty = new Faculty();
        faculty.setName(facultyDto.getName());
        Optional<University> optionalUniversity = universityRepo.findById(facultyDto.getUniversityId());
        if (optionalUniversity.isPresent()) {
            faculty.setUniversity(optionalUniversity.get());
        } else {
            return "university not found";
        }

        facultyRepo.save(faculty);
        return "Added";
    }

    @GetMapping
    public List<Faculty> getAllFaculty() {
        return facultyRepo.findAll();
    }


    @GetMapping("/byUniversityId/{universityId}")
    public Faculty getOneFaculty(@PathVariable Integer id) {
        Optional<Faculty> optionalFaculty = facultyRepo.findById(id);
        return optionalFaculty.orElseGet(Faculty::new);
    }


    @DeleteMapping("/{id}")
    public String deleteFaculty(@PathVariable Integer id) {
        try {
            facultyRepo.deleteById(id);
        } catch (Exception e) {
            return "Not Deleted";
        }
        return "Deleted";

    }

    @PutMapping
    public String updateFaculty(@PathVariable Integer id,@RequestBody FacultyDto facultyDto){

        final Optional<Faculty> optionalGroups = facultyRepo.findById(id);
        if (optionalGroups.isPresent()) {
            final Optional<University> optionalFaculty = universityRepo.findById(facultyDto.getUniversityId());
            if (optionalFaculty.isPresent()){
                facultyRepo.save(new Faculty(null,facultyDto.getName(),optionalFaculty.get()));
                return "Updated";
            }else {
                return "Faculty not found";
            }

        }
        return "group not found";
    }

}
