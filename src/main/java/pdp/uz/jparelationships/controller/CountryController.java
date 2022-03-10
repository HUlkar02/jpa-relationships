package pdp.uz.jparelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pdp.uz.jparelationships.entity.Continent;
import pdp.uz.jparelationships.entity.Country;
import pdp.uz.jparelationships.payload.CountryDto;
import pdp.uz.jparelationships.repository.ContinentRepo;
import pdp.uz.jparelationships.repository.CountryRepo;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/country")
public class CountryController {

    @Autowired
    CountryRepo countryRepo;

    @Autowired
    ContinentRepo continentRepo;

    @GetMapping
    public List<Country> getAllCountry() {
        return countryRepo.findAll();
    }

    @GetMapping("/{id}")
    public Country getOneCountry(@PathVariable Integer id) {
        final Optional<Country> optionalCountry = countryRepo.findById(id);
        return optionalCountry.orElseGet(Country::new);
    }



    @PostMapping
    public String addCountry(@RequestBody CountryDto countryDto) {
        final Optional<Continent> optionalContinent = continentRepo.findById(countryDto.getContinentId());
        if (optionalContinent.isPresent()) {
            try {
                Country country = new Country(null, countryDto.getName(), optionalContinent.get());
                countryRepo.save(country);
                return "added";
            } catch (Exception e) {
                return "already exist";
            }
        }
        return "not found";

    }


    @PutMapping("/{id}")
    public String updateCountry(@PathVariable Integer id, @RequestBody CountryDto countryDto){
        final Optional<Country> optionalCountry = countryRepo.findById(id);
        if (optionalCountry.isPresent()){
            final Optional<Continent> optionalContinent = continentRepo.findById(countryDto.getContinentId());
            if (optionalContinent.isPresent()){
            try {
                countryRepo.save(new Country(id,countryDto.getName(),optionalContinent.get()));
                return "Updated";
            }catch (Exception e){
                return "already exist";
            }
            }else {
                return "not found";
            }
        }
        return "Country  not found";
    }

    @DeleteMapping("/{id}")
         public String deleteCountry(@PathVariable Integer id){
            try {
                countryRepo.deleteById(id);
            }catch (Exception e){
                return "Not Deleted";
            }
            return "Deleted";

    }
}
