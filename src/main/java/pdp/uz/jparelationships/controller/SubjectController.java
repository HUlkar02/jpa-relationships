package pdp.uz.jparelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pdp.uz.jparelationships.entity.Subject;
import pdp.uz.jparelationships.repository.SubjectRepo;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/subjects")
public class SubjectController {
    @Autowired
    SubjectRepo subjectRepo;

    @GetMapping
    public List<Subject> allSubject() {
        return subjectRepo.findAll();
    }

    @GetMapping("/{id}")
    public Subject getSubject(@PathVariable Integer id) {

        final Optional<Subject> optionalSubject = subjectRepo.findById(id);
        return optionalSubject.orElseGet(Subject::new);
    }

    @PostMapping
    public String addSubject(@RequestBody Subject subject) {
        boolean existsByName = subjectRepo.existsByName(subject.getName());
        if (existsByName)
            return "This already exist";

        subjectRepo.save(subject);
        return "added";
    }

    @PutMapping("/{id}")
    public String editSubject(@PathVariable Integer id, @RequestBody Subject subject) {
        final Optional<Subject> optionalSubject = subjectRepo.findById(id);
        if (optionalSubject.isPresent()) {
            final Subject editingSubject = optionalSubject.get();
            editingSubject.setName(subject.getName());
            editingSubject.setId(id);
            subjectRepo.save(editingSubject);
            return "Updated";
        }
        return "Not found";
    }

    @DeleteMapping("/{id}")
    public String deleteSubject(@PathVariable Integer id) {
        try {
            subjectRepo.deleteById(id);
        } catch (Exception e) {
            return "Not deleted";
        }
        return "Deleted";
    }
}
