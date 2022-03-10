package pdp.uz.jparelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pdp.uz.jparelationships.entity.Group;
import pdp.uz.jparelationships.entity.Teacher;
import pdp.uz.jparelationships.payload.TeacherDto;
import pdp.uz.jparelationships.repository.GroupRepo;
import pdp.uz.jparelationships.repository.TeacherRepo;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "/teacher")
public class TeacherController {

    @Autowired
    TeacherRepo teacherRepo;

    @Autowired
    GroupRepo groupRepo;

    @GetMapping
    public List<Teacher> getTeacherList() {
        return teacherRepo.findAll();
    }

    @GetMapping(value = "/{id}")
    public Teacher getTeacher(@PathVariable Integer id) {
        Optional<Teacher> optionalTeacher = teacherRepo.findById(id);
        return optionalTeacher.orElseGet(Teacher::new);
    }



    @PostMapping
    public String addTeacher(@RequestBody TeacherDto teacherDto) {
        Optional<Group> optionalGroups = groupRepo.findById(teacherDto.getGroupId());
        if (optionalGroups.isPresent()) {
            List<Group> groups = new ArrayList<>();
            groups.add(optionalGroups.get());
            Teacher teacher = new Teacher(null, teacherDto.getFio(), groups);
            teacherRepo.save(teacher);
            return "Added";
        }
        return "Not found group";
    }


    @DeleteMapping("/{id}")
    public String DeleteTeacher(@PathVariable Integer id) {
        try {
            teacherRepo.deleteById(id);
            return "deleted";
        } catch (Exception e) {
            return "Not Deleted";
        }
    }

    @PutMapping("/{id}")
    public String updateTeacher(@PathVariable Integer id, @RequestBody TeacherDto teacherDto) {
        final Optional<Group> optionalGroups = groupRepo.findById(teacherDto.getGroupId());
        if (optionalGroups.isPresent()) {
            List<Group> groups = new ArrayList<>();
            groups.add(optionalGroups.get());
            Teacher teacher = new Teacher(id, teacherDto.getFio(), groups);
            teacherRepo.save(teacher);
            return "Updated teacher";
        }
        return "not found group";
    }

}
