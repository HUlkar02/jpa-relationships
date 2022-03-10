package pdp.uz.jparelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pdp.uz.jparelationships.entity.Group;
import pdp.uz.jparelationships.entity.Student;
import pdp.uz.jparelationships.payload.StudentDto;
import pdp.uz.jparelationships.repository.GroupRepo;
import pdp.uz.jparelationships.repository.StudentRepo;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/student")
public class StudentController {

    @Autowired
    StudentRepo studentRepo;

    @Autowired
    GroupRepo groupRepo;

    @GetMapping
    public List<Student> getAllStudent() {
        return studentRepo.findAll();
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable Integer id) {
        try {
            return studentRepo.getById(id);
        } catch (Exception e) {
            return new Student();
        }

    }

    @PostMapping
    public String addStudent(@RequestBody StudentDto studentDto) {
        Optional<Group> optionalGroups = groupRepo.findById(studentDto.getGroupId());
        if (optionalGroups.isPresent()) {
            studentRepo.save(new Student(null, studentDto.getFio(), studentDto.getClasses(), optionalGroups.get()));
            return "Student added";
        }
        return "not found";
    }

    @PutMapping("/{id}")
    public String updateStudent(@RequestBody StudentDto studentDto, @PathVariable Integer id) {
        final Optional<Student> optionalStudent = studentRepo.findById(id);
        if (optionalStudent.isPresent()) {
            final Optional<Group> optionalGroups = groupRepo.findById(studentDto.getGroupId());
            if (optionalGroups.isPresent()) {
                studentRepo.save(new Student(id, studentDto.getFio(), studentDto.getClasses(), optionalGroups.get()));
                return "Updated";
            } else {
                return "group not found";
            }
        }
        return "Student not found";
    }

    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Integer id) {
        try {
            studentRepo.deleteById(id);
        } catch (Exception e) {
            return "Not deleted";
        }
        return "Deleted";
    }


    @GetMapping("/forMinistry")
    public Page<Student> getAllStudentForMinistry(@RequestParam int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Student> studentPage = studentRepo.findAll(pageable);
        return studentPage;

    }


    @GetMapping("/forUniversity/{id}")
    public Page<Student> getAllStudentForUniversity(@PathVariable Integer universityId,
                                                    @RequestParam int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Student> studentPage = studentRepo.findAllByGroup_Faculty_UniversityId(universityId, pageable);
        return studentPage;

    }


}

