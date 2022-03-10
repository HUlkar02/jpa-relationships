package pdp.uz.jparelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pdp.uz.jparelationships.entity.Address;
import pdp.uz.jparelationships.entity.University;
import pdp.uz.jparelationships.payload.UniversityDto;
import pdp.uz.jparelationships.repository.AddressRepo;
import pdp.uz.jparelationships.repository.UniversityRepo;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/university")
public class UniversityController {

    @Autowired
    UniversityRepo universityRepo;

    @Autowired
    AddressRepo addressRepo;


    @GetMapping
    public List<University> getUniversities() {
        return universityRepo.findAll();
    }


    @GetMapping("/{id}")
    public University getUniversity(@PathVariable Integer id) {
        Optional<University> repoById = universityRepo.findById(id);
        return repoById.orElseGet(University::new);
    }


    @PostMapping
    public String addUniversity(@RequestBody UniversityDto universityDto) {
        Optional<Address> addressOptional = addressRepo.findById(universityDto.getAddressId());
        if (addressOptional.isPresent()) {
            try {
                University university = new University();
                university.setName(universityDto.getName());
                university.setAddress(addressOptional.get());
                return "added";
            } catch (Exception e) {
                return "Already exist";
            }
        }
        return "adress not found";
    }


    @DeleteMapping("/{id}")
    public String deleteUniversity(@PathVariable Integer id) {
        try {
            universityRepo.deleteById(id);
        } catch (Exception e) {
            return "not deleted";
        }
        return "deleted";
    }

    @PutMapping("/{id}")
    public String updateUniversity(@PathVariable Integer id, @RequestBody UniversityDto universityDto) {
        Optional<University> universityOptional = universityRepo.findById(id);
        if (universityOptional.isPresent()) {
            Optional<Address> addressOptional = addressRepo.findById(id);
            if (addressOptional.isPresent()) {
                try {
                    universityRepo.save(new University(id, universityDto.getName(), addressOptional.get()));
                    return "Updated";
                } catch (Exception e) {
                    return "Already exist";
                }
            } else {
                return "adress not found";
            }
        }
        return "University not found";
    }
}
