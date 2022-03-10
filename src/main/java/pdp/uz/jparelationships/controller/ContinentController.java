package pdp.uz.jparelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pdp.uz.jparelationships.entity.Continent;
import pdp.uz.jparelationships.repository.ContinentRepo;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/continent")
public class ContinentController {

    @Autowired
    ContinentRepo continentRepo;

    @GetMapping
    public List<Continent> allContinent(){
        return continentRepo.findAll();
    }

    @GetMapping("/{id}")
    public Continent getContinent(@PathVariable Integer id){
        final Optional<Continent> optionalContinent = continentRepo.findById(id);
        return optionalContinent.orElseGet(Continent::new);
    }

    @PostMapping
    public String addContinent(@RequestBody Continent continent){
        try{
            continentRepo.save(continent);
        }catch (Exception e){
            return "Not found";
        }
        return "added";
    }

    @PutMapping("/{id}")
    public String updateContinent(@PathVariable Integer id, @RequestBody Continent continent){
        final Optional<Continent> optionalContinent = continentRepo.findById(id);
        if (optionalContinent.isPresent()){
            continent.setId(id);
            continentRepo.save(continent);
            return "Updated";
        }
        return "Not Updated";
    }

    @DeleteMapping("/{id}")
    public String deleteContinent(@PathVariable Integer id){
        try {
            continentRepo.deleteById(id);
        }catch (Exception e){
            return "Not Deleted";
        }
        return "Deleted";

    }

}
