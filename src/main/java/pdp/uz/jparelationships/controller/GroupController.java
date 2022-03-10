package pdp.uz.jparelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pdp.uz.jparelationships.entity.Faculty;
import pdp.uz.jparelationships.entity.Group;
import pdp.uz.jparelationships.payload.GroupDto;
import pdp.uz.jparelationships.repository.FacultyRepo;
import pdp.uz.jparelationships.repository.GroupRepo;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "/group")
public class GroupController {

    @Autowired
    GroupRepo groupsRepo;

    @Autowired
    FacultyRepo facultyRepo;


    @GetMapping
    public List<Group> getAllGroup() {
        return groupsRepo.findAll();
    }

//
//    @GetMapping("/{id}")
//    public Group getOneGroups(@PathVariable Integer id) {
//        final Optional<Group> optionalFaculty = groupsRepo.findById(id);
//        return optionalFaculty.orElseGet(Group::new);
//    }
//
//
//
//    @PostMapping
//    private String addGroup(@RequestBody GroupDto groupsDto) {
//        final Optional<Faculty> optionalFaculty = facultyRepo.findById(groupsDto.getFacultyId());
//        if (optionalFaculty.isPresent()) {
//            groupsRepo.save(new Group(null, groupsDto.getName(), optionalFaculty.get()));
//            return "group added";
//        }
//        return "not found";
//    }
//
//    @PutMapping("/{id}" )
//    private String updateGroup(@PathVariable Integer id,@RequestBody GroupDto groupsDto) {
//        final Optional<Group> optionalGroups = groupsRepo.findById(id);
//        if (optionalGroups.isPresent()) {
//            final Optional<Faculty> optionalFaculty = facultyRepo.findById(groupsDto.getFacultyId());
//            if (optionalFaculty.isPresent()){
//                groupsRepo.save(new Group(null,groupsDto.getName(),optionalFaculty.get()));
//                return "Updated";
//            }else {
//                return "Faculty not found";
//            }
//
//        }
//        return "group not found";
//    }
//
//    @DeleteMapping("/{id}")
//    public String deleteFaculty(@PathVariable Integer id){
//        try {
//            groupsRepo.deleteById(id);
//        }catch (Exception e){
//            return "Not Deleted";
//        }
//        return "Deleted";
//
//    }

    @GetMapping("/byUniversityId/{universityId")
    public List<Group> getGroupsUniversityId(@PathVariable Integer id){
        return  groupsRepo.getGroupsByUniversityIdNative(id);
    }

    @PostMapping
    public  String  addGroup(@RequestBody GroupDto groupDto){
        Group group= new Group();
        group.setName(groupDto.getName());
        Optional<Faculty> optionalFaculty = facultyRepo.findById(groupDto.getFacultyId());
        if (!optionalFaculty.isPresent()) {
            return "Faculty not found";
        }

        group.setFaculty(optionalFaculty.get());
        groupsRepo.save(group);
        return "Added";
    }

}
